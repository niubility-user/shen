package tv.danmaku.ijk.media.alpha.util;

import android.opengl.GLES20;
import com.jingdong.sdk.platform.business.personal.R2;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/* loaded from: classes11.dex */
public class GlFloatArray {
    public final float[] floatArray;
    private final FloatBuffer floatBuffer;

    public GlFloatArray() {
        float[] fArr = new float[8];
        this.floatArray = fArr;
        this.floatBuffer = ByteBuffer.allocateDirect(fArr.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer().put(fArr);
    }

    public void setArray(float[] fArr) {
        this.floatBuffer.position(0);
        this.floatBuffer.put(fArr);
    }

    public void setVertexAttribPointer(int i2) {
        this.floatBuffer.position(0);
        GLES20.glVertexAttribPointer(i2, 2, (int) R2.dimen.LargeBtnPadding, false, 0, (Buffer) this.floatBuffer);
        GLES20.glEnableVertexAttribArray(i2);
    }
}
