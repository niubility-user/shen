package com.jingdong.common.unification.filter.filter;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import com.jingdong.common.UnLog;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes6.dex */
public class OpenGlUtils {
    public static final int NOT_INIT = -1;
    public static final int NO_TEXTURE = -1;
    public static final int ON_DRAWN = 1;

    public static int loadProgram(String str, String str2) {
        int[] iArr = new int[1];
        int loadShader = loadShader(str, 35633);
        if (loadShader == 0) {
            UnLog.d("Load Program", "Vertex Shader Failed");
            return 0;
        }
        int loadShader2 = loadShader(str2, 35632);
        if (loadShader2 == 0) {
            UnLog.d("Load Program", "Fragment Shader Failed");
            return 0;
        }
        int glCreateProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(glCreateProgram, loadShader);
        GLES20.glAttachShader(glCreateProgram, loadShader2);
        GLES20.glLinkProgram(glCreateProgram);
        GLES20.glGetProgramiv(glCreateProgram, 35714, iArr, 0);
        if (iArr[0] <= 0) {
            UnLog.d("Load Program", "Linking Failed");
            return 0;
        }
        GLES20.glDeleteShader(loadShader);
        GLES20.glDeleteShader(loadShader2);
        return glCreateProgram;
    }

    public static int loadShader(String str, int i2) {
        int[] iArr = new int[1];
        int glCreateShader = GLES20.glCreateShader(i2);
        GLES20.glShaderSource(glCreateShader, str);
        GLES20.glCompileShader(glCreateShader);
        GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
        if (iArr[0] == 0) {
            UnLog.d("Load Shader Failed", "Compilation\n" + GLES20.glGetShaderInfoLog(glCreateShader));
            return 0;
        }
        return glCreateShader;
    }

    public static int loadTexture(Bitmap bitmap, int i2, boolean z) {
        if (bitmap == null) {
            return -1;
        }
        int[] iArr = new int[1];
        if (i2 == -1) {
            GLES20.glGenTextures(1, iArr, 0);
            GLES20.glBindTexture(R2.color.c_FF0017, iArr[0]);
            GLES20.glTexParameterf(R2.color.c_FF0017, 10240, 9729.0f);
            GLES20.glTexParameterf(R2.color.c_FF0017, R2.drawable.main_bottom_tab_personal_normal, 9729.0f);
            GLES20.glTexParameterf(R2.color.c_FF0017, R2.drawable.main_bottom_tab_personal_normal_dark, 33071.0f);
            GLES20.glTexParameterf(R2.color.c_FF0017, R2.drawable.main_bottom_tab_search_focus, 33071.0f);
            GLUtils.texImage2D(R2.color.c_FF0017, 0, bitmap, 0);
        } else {
            GLES20.glBindTexture(R2.color.c_FF0017, i2);
            GLUtils.texSubImage2D(R2.color.c_FF0017, 0, 0, 0, bitmap);
            iArr[0] = i2;
        }
        if (z) {
            bitmap.recycle();
        }
        return iArr[0];
    }
}
