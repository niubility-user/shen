package com.jdcloud.vsr;

import android.graphics.SurfaceTexture;
import android.text.TextUtils;
import android.view.Surface;
import androidx.annotation.NonNull;
import com.jd.dynamic.DYConstants;
import com.jdcloud.media.common.JDTAuthUtil;
import com.jdcloud.media.common.base.SDKType;
import com.jdcloud.vsr.android.Context;
import com.jdcloud.vsr.android.ExternalTexture;
import com.jdcloud.vsr.exceptions.CoreException;
import com.jdcloud.vsr.imaging.PixelFormat;
import com.jdcloud.vsr.imaging.UpscalerX2;
import com.jdcloud.vsr.pipelining.Multitask;
import com.jdcloud.vsr.pipelining.TaskHolder;
import com.jdcloud.vsr.rendering.Scene;
import com.jdcloud.vsr.rendering.SceneRenderer;
import com.jdcloud.vsr.shading.ImageShader;
import com.jdcloud.vsr.shading.ShaderApplicator;
import com.jingdong.jdsdk.constant.JshopConst;
import java.lang.Thread;
import java.util.List;
import java.util.concurrent.TimeoutException;

/* loaded from: classes18.dex */
public final class JDTVSRRender {
    private static final String TAG = "JDTVSRRender";
    private static final Thread.UncaughtExceptionHandler defaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
    private static volatile boolean mIsLibLoaded;
    private TaskHolder drawingTaskHolder;
    private JDTBitmap input;
    private Scene.BitmapLayer layer;
    private VSRCallback mCallback;
    private Task mDrawingTask;
    private ExternalTexture mExternalTexture;
    private ImageShader mImageShader;
    private Multitask mMultitask;
    private SceneRenderer mRenderer;
    private Scene mScene;
    private UpscalerX2 mUpscaler;
    private Context mVSRContext;
    private int mVideoHeight;
    private int mVideoWidth;
    private JDTBitmap output;
    private ShaderApplicator textureCopy;
    private TaskHolder upscalerTaskHolder;
    private boolean isInited = false;
    private int drawingJob = -1;
    private SurfaceTexture mOwnSurfaceTexture = null;
    private SurfaceTexture mSurfaceTexture = null;
    private Surface mSurface = null;
    private volatile boolean mEnableVSR = false;

    /* loaded from: classes18.dex */
    public interface VSRCallback {
        void onError(int i2, String str);

        void onEvent(int i2, String str);
    }

    public JDTVSRRender() {
        loadLibrariesOnce();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: com.jdcloud.vsr.JDTVSRRender.1
            @Override // java.lang.Thread.UncaughtExceptionHandler
            public void uncaughtException(@NonNull Thread thread, @NonNull Throwable th) {
                if (TextUtils.equals("FinalizerWatchdogDaemon", thread.getName()) && (th instanceof TimeoutException)) {
                    return;
                }
                JDTVSRRender.defaultExceptionHandler.uncaughtException(thread, th);
            }
        });
    }

    private native boolean bindSurfaceToContext(JDTContext jDTContext, Surface surface) throws CoreException;

    private void handleEnableVSR(boolean z) {
        if (z) {
            if (this.upscalerTaskHolder == null) {
                this.upscalerTaskHolder = this.mMultitask.insertTask(this.mUpscaler, this.drawingTaskHolder);
            }
            this.mMultitask.measure();
        } else {
            TaskHolder taskHolder = this.upscalerTaskHolder;
            if (taskHolder != null) {
                this.mMultitask.removeTask(taskHolder);
                this.upscalerTaskHolder = null;
            }
            this.mMultitask.measure();
        }
        if (this.mRenderer.getScene() == null) {
            this.mRenderer.setScene(this.mScene);
        }
        if (this.layer.getBitmap() != null) {
            this.layer.setBitmap(null);
        }
        this.layer.setBitmap(z ? this.output : this.input);
        this.drawingJob = this.mVSRContext.submitPersistentTask(this.mMultitask);
        StringBuilder sb = new StringBuilder();
        sb.append("Enable VSR ");
        sb.append(this.mMultitask.getTaskCount() == 3 ? DYConstants.DY_TRUE : DYConstants.DY_FALSE);
        sb.toString();
        this.mEnableVSR = z;
    }

    private boolean hasAuth() {
        List<String> feature = JDTAuthUtil.getInstance().getFeature();
        return feature != null && feature.contains(SDKType.VSR.getType());
    }

    public static void loadLibrariesOnce() {
        synchronized (JDTVSRRender.class) {
            if (!mIsLibLoaded) {
                System.loadLibrary("jdtvsr");
                mIsLibLoaded = true;
            }
        }
    }

    public void bindSurface(SurfaceTexture surfaceTexture) {
        String str = "bindSurface " + surfaceTexture;
        if (hasAuth() && surfaceTexture != null) {
            if (this.mSurfaceTexture != surfaceTexture) {
                this.mSurface = new Surface(surfaceTexture);
            }
            this.mSurfaceTexture = surfaceTexture;
            try {
                int i2 = this.drawingJob;
                if (i2 != -1) {
                    this.mVSRContext.abortJob(i2);
                }
                bindSurfaceToContext(this.mRenderer.getContext(), this.mSurface);
                if (this.drawingJob != -1) {
                    this.drawingJob = this.mVSRContext.submitPersistentTask(this.mMultitask);
                }
            } catch (CoreException e2) {
                e2.printStackTrace();
            }
        }
    }

    public Scene buildVSRPipeline(int i2, int i3, boolean z) {
        String str = "buildVSRPipeline " + i2 + JshopConst.JSHOP_PROMOTIO_X + i3 + " isInit " + this.isInited;
        if (hasAuth() && !this.isInited) {
            JDTContext context = this.mDrawingTask.getContext();
            PixelFormat pixelFormat = PixelFormat.TripleByte;
            this.input = new JDTBitmap(context, i2, i3, pixelFormat);
            this.output = new JDTBitmap(context, i2 * 2, i3 * 2, pixelFormat);
            this.mVideoHeight = i3;
            this.mVideoWidth = i2;
            this.mImageShader = new ImageShader(context);
            ShaderApplicator shaderApplicator = new ShaderApplicator(context);
            this.textureCopy = shaderApplicator;
            shaderApplicator.addSampler(this.mExternalTexture);
            this.textureCopy.setOutput(this.input);
            this.textureCopy.setShader(this.mImageShader);
            this.textureCopy.getShader().setSourceCode("uniform jdtVSRSampler image;varying highp vec2 texCoord;void main() { gl_FragColor = jdtVSRTexture(image, texCoord);}");
            UpscalerX2 upscalerX2 = new UpscalerX2(context);
            this.mUpscaler = upscalerX2;
            upscalerX2.setMode(UpscalerX2.Mode.CONVNET);
            this.mUpscaler.setInput(this.input);
            this.mUpscaler.setOutput(this.output);
            Multitask multitask = new Multitask(context);
            this.mMultitask = multitask;
            ShaderApplicator shaderApplicator2 = this.textureCopy;
            Multitask.RepetitionPolicy repetitionPolicy = Multitask.RepetitionPolicy.REPEAT_ALWAYS;
            multitask.addTask(shaderApplicator2, repetitionPolicy);
            this.drawingTaskHolder = this.mMultitask.addTask(this.mDrawingTask, repetitionPolicy);
            Scene scene = new Scene();
            this.mScene = scene;
            Scene.BitmapLayer newBitmapLayer = scene.newBitmapLayer();
            this.layer = newBitmapLayer;
            newBitmapLayer.setCenterPosition(0.5f, 0.5f);
            this.layer.setBitmap(z ? this.output : this.input);
            handleEnableVSR(z);
            this.isInited = true;
        }
        return null;
    }

    public void destroyEngine() {
        Context context;
        if (hasAuth() && (context = this.mVSRContext) != null) {
            int i2 = this.drawingJob;
            if (i2 != -1) {
                context.abortJob(i2);
                this.drawingJob = -1;
            }
            SceneRenderer sceneRenderer = this.mRenderer;
            if (sceneRenderer != null) {
                sceneRenderer.dispose();
                this.mRenderer = null;
            }
            Scene scene = this.mScene;
            if (scene != null) {
                scene.dispose();
                this.mScene = null;
            }
            ImageShader imageShader = this.mImageShader;
            if (imageShader != null) {
                imageShader.dispose();
                this.mImageShader = null;
            }
            ShaderApplicator shaderApplicator = this.textureCopy;
            if (shaderApplicator != null) {
                shaderApplicator.dispose();
                this.textureCopy = null;
            }
            UpscalerX2 upscalerX2 = this.mUpscaler;
            if (upscalerX2 != null) {
                upscalerX2.dispose();
                this.mUpscaler = null;
            }
            this.mVSRContext.dispose();
            this.mVSRContext = null;
        }
    }

    public void enableVSR(boolean z) {
        String str = "enableVSR " + z;
        if (hasAuth() && this.mEnableVSR != z) {
            this.mEnableVSR = z;
            if (this.isInited) {
                int i2 = this.drawingJob;
                if (i2 != -1) {
                    this.mVSRContext.abortJob(i2);
                }
                handleEnableVSR(z);
            }
        }
    }

    public VSRCallback getCallback() {
        return this.mCallback;
    }

    public ExternalTexture getOutputTexture() {
        if (hasAuth()) {
            return this.mExternalTexture;
        }
        return null;
    }

    public SurfaceTexture getSurfaceTexture() {
        if (hasAuth()) {
            if (this.mOwnSurfaceTexture == null) {
                this.mOwnSurfaceTexture = this.mExternalTexture.getSurfaceTexture();
            }
            String str = "getSurfaceTexture " + this.mOwnSurfaceTexture;
            return this.mOwnSurfaceTexture;
        }
        return null;
    }

    public float getVSRFps() {
        TaskHolder taskHolder;
        if (hasAuth() && this.mEnableVSR && (taskHolder = this.upscalerTaskHolder) != null) {
            return 1000.0f / taskHolder.getRunTime();
        }
        return 0.0f;
    }

    public void init(VSRCallback vSRCallback) {
        VSRCallback vSRCallback2;
        this.mVSRContext = new Context(2);
        this.mCallback = vSRCallback;
        if (!hasAuth() && (vSRCallback2 = this.mCallback) != null) {
            vSRCallback2.onError(-1000, "has no auth");
            return;
        }
        this.mVSRContext.limitWorkerCount(1, 1);
        SceneRenderer sceneRenderer = new SceneRenderer(this.mVSRContext, hasAuth());
        this.mRenderer = sceneRenderer;
        if (sceneRenderer != null) {
            sceneRenderer.setOutputMapping(SceneRenderer.OutputMapping.FIT_WIDTH_TO_TOP);
            this.mRenderer.setOutputPixelsFetching(false);
        }
        this.mExternalTexture = new ExternalTexture(this.mVSRContext, hasAuth());
        String str = "create mExternalTexture " + this.mExternalTexture;
        this.mDrawingTask = this.mRenderer;
    }

    public void notifyUpdate(int i2, int i3) {
        if (hasAuth()) {
            this.mVideoHeight = i3;
            this.mVideoWidth = i2;
            if (!this.isInited) {
                buildVSRPipeline(i2, i3, this.mEnableVSR);
                this.mExternalTexture.notifyUpdate(i2, i3);
                return;
            }
            this.mExternalTexture.notifyUpdate(i2, i3);
        }
    }

    public void rebindSurface(SurfaceTexture surfaceTexture) {
        String str = "rebindSurface " + surfaceTexture;
        if (hasAuth() && this.mSurfaceTexture == surfaceTexture) {
            try {
                int i2 = this.drawingJob;
                if (i2 != -1) {
                    this.mVSRContext.abortJob(i2);
                }
                bindSurfaceToContext(this.mRenderer.getContext(), this.mSurface);
                if (this.drawingJob != -1) {
                    this.drawingJob = this.mVSRContext.submitPersistentTask(this.mMultitask);
                }
            } catch (CoreException e2) {
                e2.printStackTrace();
            }
        }
    }

    public void setCallback(VSRCallback vSRCallback) {
        this.mCallback = vSRCallback;
    }

    public void unbindSurface() {
        if (hasAuth() && this.mRenderer != null) {
            try {
                this.mVSRContext.abortJob(this.drawingJob);
                bindSurfaceToContext(this.mRenderer.getContext(), null);
                this.mRenderer.resetOutput();
            } catch (CoreException e2) {
                e2.printStackTrace();
            }
        }
    }
}
