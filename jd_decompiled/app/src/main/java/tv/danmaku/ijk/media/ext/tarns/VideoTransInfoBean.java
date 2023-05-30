package tv.danmaku.ijk.media.ext.tarns;

import android.graphics.Bitmap;
import java.io.Serializable;

/* loaded from: classes11.dex */
public class VideoTransInfoBean implements Serializable {
    public Bitmap coverBitmap;
    public int playPos;
    public String url;

    public VideoTransInfoBean(String str, int i2, Bitmap bitmap) {
        this.url = str;
        this.playPos = i2;
        this.coverBitmap = bitmap;
    }

    public void update(String str, int i2, Bitmap bitmap) {
        this.url = str;
        this.playPos = i2;
        this.coverBitmap = bitmap;
    }
}
