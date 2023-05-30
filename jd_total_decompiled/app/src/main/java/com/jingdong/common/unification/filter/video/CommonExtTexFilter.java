package com.jingdong.common.unification.filter.video;

import android.opengl.GLES20;
import com.jingdong.common.unification.filter.filter.CommonFilter;
import com.jingdong.sdk.platform.business.personal.R2;
import java.nio.Buffer;
import java.nio.FloatBuffer;

/* loaded from: classes6.dex */
public class CommonExtTexFilter extends CommonFilter {
    private static final String FRAGMENT_SHADER_EXT = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 textureCoordinate;\nuniform samplerExternalOES inputImageTexture;\nvoid main() {\n    gl_FragColor = texture2D(inputImageTexture, textureCoordinate);\n}\n";

    public CommonExtTexFilter() {
        super(CommonFilter.NO_FILTER_VERTEX_SHADER, FRAGMENT_SHADER_EXT);
    }

    @Override // com.jingdong.common.unification.filter.filter.CommonFilter
    public void onDraw(int i2, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        GLES20.glUseProgram(this.mGLProgId);
        runPendingOnDrawTasks();
        floatBuffer.position(0);
        GLES20.glVertexAttribPointer(this.mGLAttribPosition, 2, (int) R2.dimen.LargeBtnPadding, false, 0, (Buffer) floatBuffer);
        GLES20.glEnableVertexAttribArray(this.mGLAttribPosition);
        floatBuffer2.position(0);
        GLES20.glVertexAttribPointer(this.mGLAttribTextureCoordinate, 2, (int) R2.dimen.LargeBtnPadding, false, 0, (Buffer) floatBuffer2);
        GLES20.glEnableVertexAttribArray(this.mGLAttribTextureCoordinate);
        if (i2 != -1) {
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(36197, i2);
            GLES20.glUniform1i(this.mGLUniformTexture, 0);
        }
        onDrawArraysPre();
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glDisableVertexAttribArray(this.mGLAttribPosition);
        GLES20.glDisableVertexAttribArray(this.mGLAttribTextureCoordinate);
        GLES20.glBindTexture(R2.color.c_FF0017, 0);
    }
}
