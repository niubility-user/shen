package com.jdcloud.vsr;

import android.graphics.SurfaceTexture;
import com.jd.dynamic.DYConstants;
import com.jdcloud.vsr.android.Context;
import com.jdcloud.vsr.android.ExternalTexture;
import com.jdcloud.vsr.imaging.PixelFormat;
import com.jdcloud.vsr.imaging.UpscalerX2;
import com.jdcloud.vsr.pipelining.Multitask;
import com.jdcloud.vsr.pipelining.TaskHolder;
import com.jdcloud.vsr.rendering.Scene;
import com.jdcloud.vsr.rendering.SceneRenderer;
import com.jdcloud.vsr.shading.ImageShader;
import com.jdcloud.vsr.shading.ShaderApplicator;
import com.jdcloud.vsr.visual.android.BaseVSRTextureView;
import com.jdcloud.vsr.visual.android.Display;

@Deprecated
/* loaded from: classes18.dex */
public final class JDTVSREngine {
    private static final String TAG = "JDTVSREngine";
    private Task drawingTask;
    private TaskHolder drawingTaskHolder;
    private JDTBitmap input;
    Scene.BitmapLayer layer;
    private VSRCallback mCallback;
    private Display mDisplay;
    private int mVideoHeight;
    private int mVideoWidth;
    private Multitask multitask;
    private Context myContext;
    private JDTBitmap output;
    private ExternalTexture outputImage;
    private SceneRenderer renderer;
    private TaskHolder resampleTaskHolder;
    private ShaderApplicator textureCopy;
    private UpscalerX2 upscaler;
    private boolean isInit = false;
    private int drawingJob = -1;
    private Scene mScene = null;
    private boolean mEnableVSR = false;

    /* loaded from: classes18.dex */
    public interface VSRCallback {
        void onError(int i2, String str);

        void onEvent(int i2, String str);
    }

    private void buildVSRPipeline(int i2, int i3, boolean z) {
        JDTContext context = this.drawingTask.getContext();
        PixelFormat pixelFormat = PixelFormat.TripleByte;
        this.input = new JDTBitmap(context, i2, i3, pixelFormat);
        this.output = new JDTBitmap(context, i2 * 2, i3 * 2, pixelFormat);
        this.mVideoHeight = i3;
        this.mVideoWidth = i2;
        ShaderApplicator shaderApplicator = new ShaderApplicator(context);
        this.textureCopy = shaderApplicator;
        shaderApplicator.addSampler(this.outputImage);
        this.textureCopy.setOutput(this.input);
        this.textureCopy.setShader(new ImageShader(context));
        this.textureCopy.getShader().setSourceCode("uniform jdtVSRSampler image;varying highp vec2 texCoord;void main() { gl_FragColor = jdtVSRTexture(image, texCoord);}");
        UpscalerX2 upscalerX2 = new UpscalerX2(context);
        this.upscaler = upscalerX2;
        upscalerX2.setMode(UpscalerX2.Mode.CONVNET);
        this.upscaler.setInput(this.input);
        this.upscaler.setOutput(this.output);
        Multitask multitask = new Multitask(context);
        this.multitask = multitask;
        ShaderApplicator shaderApplicator2 = this.textureCopy;
        Multitask.RepetitionPolicy repetitionPolicy = Multitask.RepetitionPolicy.REPEAT_ALWAYS;
        multitask.addTask(shaderApplicator2, repetitionPolicy);
        this.drawingTaskHolder = this.multitask.addTask(this.drawingTask, repetitionPolicy);
        Scene scene = new Scene();
        this.mScene = scene;
        Scene.BitmapLayer newBitmapLayer = scene.newBitmapLayer();
        this.layer = newBitmapLayer;
        newBitmapLayer.setCenterPosition(0.5f, 0.5f);
        this.layer.setBitmap(z ? this.output : this.input);
        handleEnableVSR(z);
        this.isInit = true;
    }

    private void handleEnableVSR(boolean z) {
        if (z) {
            if (this.resampleTaskHolder == null) {
                this.resampleTaskHolder = this.multitask.insertTask(this.upscaler, this.drawingTaskHolder);
                this.multitask.measure();
            }
            this.multitask.measure();
        } else {
            TaskHolder taskHolder = this.resampleTaskHolder;
            if (taskHolder != null) {
                this.multitask.removeTask(taskHolder);
                this.resampleTaskHolder = null;
                this.multitask.measure();
            }
        }
        if (this.renderer.getScene() == null) {
            this.renderer.setScene(this.mScene);
        }
        this.layer.recycleBitmap();
        this.layer.setBitmap(z ? this.output : this.input);
        this.drawingJob = this.myContext.submitPersistentTask(this.multitask);
        StringBuilder sb = new StringBuilder();
        sb.append("Enable VSR  ");
        sb.append(this.multitask.getTaskCount() == 3 ? DYConstants.DY_TRUE : DYConstants.DY_FALSE);
        sb.toString();
        this.mEnableVSR = z;
    }

    private boolean hasAuth() {
        return true;
    }

    private void initListener(Display display) {
        display.addSizeChangeListener(new BaseVSRTextureView.OnSizeChangeListener() { // from class: com.jdcloud.vsr.JDTVSREngine.1
            @Override // com.jdcloud.vsr.visual.android.BaseVSRTextureView.OnSizeChangeListener
            public void sizeChanged(int i2, int i3) {
                String str = "Display sizeChanged " + i2 + " x " + i3;
            }
        });
        display.addBindingListener(new BaseVSRTextureView.OnBindingListener() { // from class: com.jdcloud.vsr.JDTVSREngine.2
            @Override // com.jdcloud.vsr.visual.android.BaseVSRTextureView.OnBindingListener
            public void afterBinding(boolean z) {
            }

            @Override // com.jdcloud.vsr.visual.android.BaseVSRTextureView.OnBindingListener
            public void beforeBinding(boolean z) {
                if (JDTVSREngine.this.drawingTask != null) {
                    JDTVSREngine.this.myContext.abortJob(JDTVSREngine.this.drawingJob);
                }
            }
        });
        display.addUpdateListener(new BaseVSRTextureView.OnUpdateListener() { // from class: com.jdcloud.vsr.JDTVSREngine.3
            @Override // com.jdcloud.vsr.visual.android.BaseVSRTextureView.OnUpdateListener
            public void update(int i2, int i3) {
                if (JDTVSREngine.this.isInit) {
                    JDTVSREngine.this.outputImage.notifyUpdate(JDTVSREngine.this.mVideoWidth, JDTVSREngine.this.mVideoHeight);
                }
            }
        });
    }

    public void destroyEngine() {
        Context context = this.myContext;
        if (context != null) {
            int i2 = this.drawingJob;
            if (i2 != -1) {
                context.abortJob(i2);
                this.drawingJob = -1;
            }
            this.myContext.dispose();
        }
    }

    public VSRCallback getCallback() {
        return this.mCallback;
    }

    public ExternalTexture getOutputImage() {
        return this.outputImage;
    }

    public SurfaceTexture getSurfaceTexture() {
        ExternalTexture externalTexture = this.outputImage;
        if (externalTexture == null) {
            return null;
        }
        return externalTexture.getSurfaceTexture();
    }

    public float getVSRFps() {
        TaskHolder taskHolder;
        if (!this.mEnableVSR || (taskHolder = this.resampleTaskHolder) == null) {
            return 0.0f;
        }
        return 1000.0f / taskHolder.getRunTime();
    }

    public void render(int i2, int i3, boolean z) {
        if (hasAuth()) {
            if (!this.isInit) {
                buildVSRPipeline(i2, i3, z);
            }
            if (this.mEnableVSR != z) {
                JDTContext context = this.drawingTask.getContext();
                if (this.drawingJob != -1) {
                    context.abortJob(this.drawingJob);
                }
                handleEnableVSR(z);
            }
        }
    }

    public void setCallback(VSRCallback vSRCallback) {
        this.mCallback = vSRCallback;
    }

    public void setDisplay(Display display) {
        VSRCallback vSRCallback;
        if (display == null) {
            return;
        }
        this.mDisplay = display;
        if (!hasAuth() && (vSRCallback = this.mCallback) != null) {
            vSRCallback.onError(-1000, "has no auth");
            return;
        }
        Context context = new Context(2);
        this.myContext = context;
        context.limitWorkerCount(1, 1);
        this.outputImage = new ExternalTexture(this.myContext, hasAuth());
        initListener(display);
    }

    public void setRenderer() {
        SceneRenderer sceneRenderer = this.renderer;
        if (sceneRenderer != null) {
            sceneRenderer.resetOutput();
            this.renderer.dispose();
        }
        SceneRenderer sceneRenderer2 = new SceneRenderer(this.myContext, hasAuth());
        this.renderer = sceneRenderer2;
        if (sceneRenderer2 != null) {
            sceneRenderer2.setOutputMapping(SceneRenderer.OutputMapping.STRETCH);
            this.renderer.setOutputPixelsFetching(false);
            this.mDisplay.setRenderer(this.renderer);
        }
        this.drawingTask = this.renderer;
        if (this.isInit) {
            this.multitask.dispose();
            this.resampleTaskHolder = null;
            Multitask multitask = new Multitask(this.renderer.getContext());
            this.multitask = multitask;
            multitask.addTask(this.textureCopy, Multitask.RepetitionPolicy.REPEAT_ALWAYS);
            this.drawingTaskHolder = this.multitask.addTask(this.drawingTask);
            Scene scene = new Scene();
            this.mScene = scene;
            Scene.BitmapLayer newBitmapLayer = scene.newBitmapLayer();
            this.layer = newBitmapLayer;
            newBitmapLayer.setCenterPosition(0.5f, 0.5f);
            this.layer.setBitmap(this.output);
            handleEnableVSR(true);
            String str = "xiujing task count: " + this.multitask.getTaskCount();
        }
    }
}
