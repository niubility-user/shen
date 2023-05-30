package com.jingdong.common.unification.filter.filter;

import android.content.Context;
import android.graphics.PointF;
import android.opengl.GLES20;
import com.jingdong.common.UnLog;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.LinkedList;
import java.util.Scanner;

/* loaded from: classes6.dex */
public class CommonFilter {
    public static final String NO_FILTER_FRAGMENT_SHADER = "varying highp vec2 textureCoordinate;\n \nuniform sampler2D inputImageTexture;\n \nvoid main()\n{\n     gl_FragColor = texture2D(inputImageTexture, textureCoordinate);\n}";
    public static final String NO_FILTER_VERTEX_SHADER = "attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}";
    private static final String TAG = "CommonFilter";
    private final String mFragmentShader;
    protected int mGLAttribPosition;
    protected int mGLAttribTextureCoordinate;
    protected FloatBuffer mGLCubeBuffer;
    protected int mGLProgId;
    protected FloatBuffer mGLTextureBuffer;
    protected int mGLUniformTexture;
    private boolean mIsInitialized;
    protected int mOutputHeight;
    protected int mOutputWidth;
    private final LinkedList<Runnable> mRunOnDraw;
    private final String mVertexShader;

    public CommonFilter() {
        this(NO_FILTER_VERTEX_SHADER, NO_FILTER_FRAGMENT_SHADER);
    }

    public static String convertStreamToString(InputStream inputStream) {
        Scanner useDelimiter = new Scanner(inputStream).useDelimiter("\\A");
        return useDelimiter.hasNext() ? useDelimiter.next() : "";
    }

    public static String loadShader(String str, Context context) {
        try {
            InputStream open = context.getAssets().open(str);
            String convertStreamToString = convertStreamToString(open);
            open.close();
            return convertStreamToString;
        } catch (Exception e2) {
            UnLog.e(TAG, e2.toString());
            return "";
        }
    }

    public final void destroy() {
        this.mIsInitialized = false;
        GLES20.glDeleteProgram(this.mGLProgId);
        onDestroy();
    }

    public int getAttribPosition() {
        return this.mGLAttribPosition;
    }

    public int getAttribTextureCoordinate() {
        return this.mGLAttribTextureCoordinate;
    }

    public int getOutputHeight() {
        return this.mOutputHeight;
    }

    public int getOutputWidth() {
        return this.mOutputWidth;
    }

    public int getProgram() {
        return this.mGLProgId;
    }

    public int getUniformTexture() {
        return this.mGLUniformTexture;
    }

    public final void init() {
        onInit();
        this.mIsInitialized = true;
        onInitialized();
    }

    public boolean isInitialized() {
        return this.mIsInitialized;
    }

    public void onDestroy() {
    }

    public void onDraw(int i2, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        GLES20.glUseProgram(this.mGLProgId);
        runPendingOnDrawTasks();
        if (this.mIsInitialized) {
            floatBuffer.position(0);
            GLES20.glVertexAttribPointer(this.mGLAttribPosition, 2, (int) R2.dimen.LargeBtnPadding, false, 0, (Buffer) floatBuffer);
            GLES20.glEnableVertexAttribArray(this.mGLAttribPosition);
            floatBuffer2.position(0);
            GLES20.glVertexAttribPointer(this.mGLAttribTextureCoordinate, 2, (int) R2.dimen.LargeBtnPadding, false, 0, (Buffer) floatBuffer2);
            GLES20.glEnableVertexAttribArray(this.mGLAttribTextureCoordinate);
            if (i2 != -1) {
                GLES20.glActiveTexture(33984);
                GLES20.glBindTexture(R2.color.c_FF0017, i2);
                GLES20.glUniform1i(this.mGLUniformTexture, 0);
            }
            onDrawArraysPre();
            GLES20.glDrawArrays(5, 0, 4);
            GLES20.glDisableVertexAttribArray(this.mGLAttribPosition);
            GLES20.glDisableVertexAttribArray(this.mGLAttribTextureCoordinate);
            onDrawArraysAfter();
            GLES20.glBindTexture(R2.color.c_FF0017, 0);
        }
    }

    protected void onDrawArraysAfter() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onDrawArraysPre() {
    }

    public void onInit() {
        int loadProgram = OpenGlUtils.loadProgram(this.mVertexShader, this.mFragmentShader);
        this.mGLProgId = loadProgram;
        this.mGLAttribPosition = GLES20.glGetAttribLocation(loadProgram, "position");
        this.mGLUniformTexture = GLES20.glGetUniformLocation(this.mGLProgId, "inputImageTexture");
        this.mGLAttribTextureCoordinate = GLES20.glGetAttribLocation(this.mGLProgId, "inputTextureCoordinate");
        this.mIsInitialized = true;
    }

    public void onInitialized() {
    }

    public void onOutputSizeChanged(int i2, int i3) {
        this.mOutputWidth = i2;
        this.mOutputHeight = i3;
    }

    protected void runOnDraw(Runnable runnable) {
        synchronized (this.mRunOnDraw) {
            this.mRunOnDraw.addLast(runnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void runPendingOnDrawTasks() {
        synchronized (this.mRunOnDraw) {
            while (!this.mRunOnDraw.isEmpty()) {
                this.mRunOnDraw.removeFirst().run();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setFloat(final int i2, final float f2) {
        runOnDraw(new Runnable() { // from class: com.jingdong.common.unification.filter.filter.CommonFilter.2
            @Override // java.lang.Runnable
            public void run() {
                GLES20.glUniform1f(i2, f2);
            }
        });
    }

    protected void setFloatArray(final int i2, final float[] fArr) {
        runOnDraw(new Runnable() { // from class: com.jingdong.common.unification.filter.filter.CommonFilter.6
            @Override // java.lang.Runnable
            public void run() {
                int i3 = i2;
                float[] fArr2 = fArr;
                GLES20.glUniform1fv(i3, fArr2.length, FloatBuffer.wrap(fArr2));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setFloatVec2(final int i2, final float[] fArr) {
        runOnDraw(new Runnable() { // from class: com.jingdong.common.unification.filter.filter.CommonFilter.3
            @Override // java.lang.Runnable
            public void run() {
                GLES20.glUniform2fv(i2, 1, FloatBuffer.wrap(fArr));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setFloatVec3(final int i2, final float[] fArr) {
        runOnDraw(new Runnable() { // from class: com.jingdong.common.unification.filter.filter.CommonFilter.4
            @Override // java.lang.Runnable
            public void run() {
                GLES20.glUniform3fv(i2, 1, FloatBuffer.wrap(fArr));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setFloatVec4(final int i2, final float[] fArr) {
        runOnDraw(new Runnable() { // from class: com.jingdong.common.unification.filter.filter.CommonFilter.5
            @Override // java.lang.Runnable
            public void run() {
                GLES20.glUniform4fv(i2, 1, FloatBuffer.wrap(fArr));
            }
        });
    }

    protected void setInteger(final int i2, final int i3) {
        runOnDraw(new Runnable() { // from class: com.jingdong.common.unification.filter.filter.CommonFilter.1
            @Override // java.lang.Runnable
            public void run() {
                GLES20.glUniform1i(i2, i3);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setPoint(final int i2, final PointF pointF) {
        runOnDraw(new Runnable() { // from class: com.jingdong.common.unification.filter.filter.CommonFilter.7
            @Override // java.lang.Runnable
            public void run() {
                PointF pointF2 = pointF;
                GLES20.glUniform2fv(i2, 1, new float[]{pointF2.x, pointF2.y}, 0);
            }
        });
    }

    protected void setUniformMatrix3f(final int i2, final float[] fArr) {
        runOnDraw(new Runnable() { // from class: com.jingdong.common.unification.filter.filter.CommonFilter.8
            @Override // java.lang.Runnable
            public void run() {
                GLES20.glUniformMatrix3fv(i2, 1, false, fArr, 0);
            }
        });
    }

    protected void setUniformMatrix4f(final int i2, final float[] fArr) {
        runOnDraw(new Runnable() { // from class: com.jingdong.common.unification.filter.filter.CommonFilter.9
            @Override // java.lang.Runnable
            public void run() {
                GLES20.glUniformMatrix4fv(i2, 1, false, fArr, 0);
            }
        });
    }

    public CommonFilter(String str, String str2) {
        this.mRunOnDraw = new LinkedList<>();
        this.mVertexShader = str;
        this.mFragmentShader = str2;
        float[] fArr = TextureRotationUtil.CUBE;
        FloatBuffer asFloatBuffer = ByteBuffer.allocateDirect(fArr.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.mGLCubeBuffer = asFloatBuffer;
        asFloatBuffer.put(fArr).position(0);
        FloatBuffer asFloatBuffer2 = ByteBuffer.allocateDirect(TextureRotationUtil.TEXTURE_NO_ROTATION.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.mGLTextureBuffer = asFloatBuffer2;
        asFloatBuffer2.put(TextureRotationUtil.getRotation(Rotation.NORMAL, false, true)).position(0);
    }
}
