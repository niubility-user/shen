package com.jd.aips.detect.face.bean;

import android.graphics.Bitmap;
import com.jd.aips.common.utils.FsImageUtils;
import java.io.Serializable;

/* loaded from: classes12.dex */
public class FaceImageData implements Serializable {
    static final long serialVersionUID = 3619554597720343718L;
    public int channel;
    public int[] data;
    public int height;
    private volatile byte[] output;
    public byte[] securityCode;
    public int width;

    public synchronized byte[] getOutputData() {
        int[] iArr;
        int i2;
        int i3;
        if (this.output == null && (iArr = this.data) != null && iArr.length > 0 && (i2 = this.width) > 0 && (i3 = this.height) > 0) {
            Bitmap createBitmap = Bitmap.createBitmap(iArr, 0, i2, i2, i3, Bitmap.Config.ARGB_8888);
            if (createBitmap != null) {
                this.output = FsImageUtils.toJpeg(createBitmap);
            }
            if (createBitmap != null && !createBitmap.isRecycled()) {
                createBitmap.recycle();
            }
        }
        return this.output;
    }
}
