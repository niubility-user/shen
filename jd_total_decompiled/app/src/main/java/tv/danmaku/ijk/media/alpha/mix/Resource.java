package tv.danmaku.ijk.media.alpha.mix;

import android.graphics.Bitmap;
import tv.danmaku.ijk.media.alpha.AlphaConfig;
import tv.danmaku.ijk.media.alpha.mix.Src;

/* loaded from: classes11.dex */
public class Resource {
    private Bitmap bitmap;
    private AlphaConfig.PointRect curPoint;
    private String id;
    private Src.LoadType loadType;
    private String tag;
    private Src.SrcType type;

    public Resource(Src src) {
        this.id = src.getSrcId();
        this.type = src.getSrcType();
        this.loadType = src.getLoadType();
        this.tag = src.getSrcTag();
        this.bitmap = src.getBitmap();
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public AlphaConfig.PointRect getCurPoint() {
        return this.curPoint;
    }

    public String getId() {
        return this.id;
    }

    public Src.LoadType getLoadType() {
        return this.loadType;
    }

    public String getTag() {
        return this.tag;
    }

    public Src.SrcType getType() {
        return this.type;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setCurPoint(AlphaConfig.PointRect pointRect) {
        this.curPoint = pointRect;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setLoadType(Src.LoadType loadType) {
        this.loadType = loadType;
    }

    public void setTag(String str) {
        this.tag = str;
    }

    public void setType(Src.SrcType srcType) {
        this.type = srcType;
    }
}
