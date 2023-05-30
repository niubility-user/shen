package tv.danmaku.ijk.media.alpha.mix;

import android.graphics.Bitmap;
import android.graphics.Color;
import androidx.annotation.Nullable;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.jdsdk.constant.JshopConst;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes11.dex */
public class Src {
    private static final String TAG = "Src";
    @Nullable
    private Bitmap bitmap;
    private int color;
    private int drawHeight;
    private int drawWidth;
    private FitType fitType;

    /* renamed from: h  reason: collision with root package name */
    private int f20508h;
    private LoadType loadType;
    private String srcId;
    private String srcTag;
    private int srcTextureId;
    private SrcType srcType;
    private Style style;
    private String txt;
    private int w;

    /* loaded from: classes11.dex */
    public enum FitType {
        FIT_XY("fitXY"),
        CENTER_FULL("centerFull");
        
        private final String type;

        FitType(String str) {
            this.type = str;
        }

        public final String getType() {
            return this.type;
        }
    }

    /* loaded from: classes11.dex */
    public enum LoadType {
        UNKNOWN("unknown"),
        NET("net"),
        LOCAL("local");
        
        private final String type;

        LoadType(String str) {
            this.type = str;
        }

        public final String getType() {
            return this.type;
        }
    }

    /* loaded from: classes11.dex */
    public enum SrcType {
        UNKNOWN("unknown"),
        IMG("img"),
        TXT("txt");
        
        private final String type;

        SrcType(String str) {
            this.type = str;
        }

        public final String getType() {
            return this.type;
        }
    }

    /* loaded from: classes11.dex */
    public enum Style {
        DEFAULT("default"),
        BOLD("b");
        
        private final String style;

        Style(String str) {
            this.style = str;
        }

        public final String getStyle() {
            return this.style;
        }
    }

    public Src(JSONObject jSONObject) {
        this.srcId = "";
        SrcType srcType = SrcType.UNKNOWN;
        this.srcType = srcType;
        LoadType loadType = LoadType.UNKNOWN;
        this.loadType = loadType;
        Style style = Style.DEFAULT;
        this.style = style;
        FitType fitType = FitType.FIT_XY;
        this.fitType = fitType;
        try {
            this.srcId = jSONObject.getString("srcId");
            this.w = jSONObject.getInt(JshopConst.JSHOP_PROMOTIO_W);
            this.f20508h = jSONObject.getInt(JshopConst.JSHOP_PROMOTIO_H);
            this.color = Color.parseColor(jSONObject.optString("color", JDDarkUtil.COLOR_0000000));
            String string = jSONObject.getString("srcTag");
            this.srcTag = string;
            this.txt = string;
            String string2 = jSONObject.getString("srcType");
            SrcType srcType2 = SrcType.IMG;
            if (srcType2.getType().equals(string2)) {
                this.srcType = srcType2;
            } else {
                SrcType srcType3 = SrcType.TXT;
                if (srcType3.getType().equals(string2)) {
                    this.srcType = srcType3;
                } else {
                    this.srcType = srcType;
                }
            }
            String string3 = jSONObject.getString("loadType");
            LoadType loadType2 = LoadType.NET;
            if (loadType2.getType().equals(string3)) {
                this.loadType = loadType2;
            } else {
                LoadType loadType3 = LoadType.LOCAL;
                if (loadType3.getType().equals(string3)) {
                    this.loadType = loadType3;
                } else {
                    this.loadType = loadType;
                }
            }
            FitType fitType2 = FitType.CENTER_FULL;
            this.fitType = fitType2.getType().equals(jSONObject.getString("loadType")) ? fitType2 : fitType;
            Style style2 = Style.BOLD;
            this.style = style2.getStyle().equals(jSONObject.optString(DeeplinkProductDetailHelper.LAYER_STYLE, "")) ? style2 : style;
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        String str = "Src(" + toString() + ")";
    }

    private void genDrawSize(Bitmap bitmap) {
        int i2;
        int i3;
        int width = bitmap != null ? bitmap.getWidth() : this.w;
        int height = bitmap != null ? bitmap.getHeight() : this.f20508h;
        this.drawWidth = width;
        this.drawHeight = height;
        if (this.fitType != FitType.CENTER_FULL || (i2 = this.w) == 0 || (i3 = this.f20508h) == 0) {
            return;
        }
        float f2 = width / height;
        if (f2 >= i2 / i3) {
            this.drawHeight = i3;
            this.drawWidth = (int) (i3 * f2);
            return;
        }
        this.drawWidth = i2;
        this.drawHeight = (int) (i2 / f2);
    }

    @Nullable
    public final Bitmap getBitmap() {
        return this.bitmap;
    }

    public final int getColor() {
        return this.color;
    }

    public final int getDrawHeight() {
        return this.drawHeight;
    }

    public final int getDrawWidth() {
        return this.drawWidth;
    }

    public final FitType getFitType() {
        return this.fitType;
    }

    public final int getH() {
        return this.f20508h;
    }

    public final LoadType getLoadType() {
        return this.loadType;
    }

    public final String getSrcId() {
        return this.srcId;
    }

    public final String getSrcTag() {
        return this.srcTag;
    }

    public final int getSrcTextureId() {
        return this.srcTextureId;
    }

    public final SrcType getSrcType() {
        return this.srcType;
    }

    public final Style getStyle() {
        return this.style;
    }

    public final String getTxt() {
        return this.txt;
    }

    public final int getW() {
        return this.w;
    }

    public final void setBitmap(@Nullable Bitmap bitmap) {
        this.bitmap = bitmap;
        genDrawSize(bitmap);
    }

    public final void setColor(int i2) {
        this.color = i2;
    }

    public final void setDrawHeight(int i2) {
        this.drawHeight = i2;
    }

    public final void setDrawWidth(int i2) {
        this.drawWidth = i2;
    }

    public final void setFitType(FitType fitType) {
        this.fitType = fitType;
    }

    public final void setH(int i2) {
        this.f20508h = i2;
    }

    public final void setLoadType(LoadType loadType) {
        this.loadType = loadType;
    }

    public final void setSrcId(String str) {
        this.srcId = str;
    }

    public final void setSrcTag(String str) {
        this.srcTag = str;
    }

    public final void setSrcTextureId(int i2) {
        this.srcTextureId = i2;
    }

    public final void setSrcType(SrcType srcType) {
        this.srcType = srcType;
    }

    public final void setStyle(Style style) {
        this.style = style;
    }

    public final void setTxt(String str) {
        this.txt = str;
    }

    public final void setW(int i2) {
        this.w = i2;
    }

    public String toString() {
        return "Src{srcId='" + this.srcId + "', srcType=" + this.srcType + ", loadType=" + this.loadType + ", srcTag='" + this.srcTag + "', txt='" + this.txt + "', style=" + this.style + ", fitType=" + this.fitType + ", bitmap=" + this.bitmap + '}';
    }
}
