package tv.danmaku.ijk.media.alpha.util;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes11.dex */
public class TextureLoadUtil {
    public static int loadTexture(Bitmap bitmap) {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        if (iArr[0] == 0) {
            return 0;
        }
        if (bitmap == null) {
            GLES20.glDeleteTextures(1, iArr, 0);
            return 0;
        } else if (bitmap.isRecycled()) {
            return 0;
        } else {
            GLES20.glBindTexture(R2.color.c_FF0017, iArr[0]);
            GLES20.glTexParameteri(R2.color.c_FF0017, R2.drawable.main_bottom_tab_personal_normal, R2.drawable.libpdstyleinfoview_add2car_corner_bg_v8);
            GLES20.glTexParameteri(R2.color.c_FF0017, 10240, R2.drawable.lib_pd_core_sendto_v8);
            GLUtils.texImage2D(R2.color.c_FF0017, 0, bitmap, 0);
            GLES20.glGenerateMipmap(R2.color.c_FF0017);
            GLES20.glBindTexture(R2.color.c_FF0017, 0);
            return iArr[0];
        }
    }

    public static void releaseTexture(int i2) {
        if (i2 != 0) {
            GLES20.glDeleteTextures(1, new int[]{i2}, 0);
        }
    }
}
