package tv.danmaku.ijk.media.alpha;

import com.jingdong.jdsdk.constant.JshopConst;
import java.io.Serializable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.alpha.mask.MaskConfig;

/* loaded from: classes11.dex */
public class AlphaConfig {
    private static final String TAG = "AlphaConfig";
    public int fps;
    public int height;
    public boolean isMix;
    public JSONObject jsonConfig;
    public MaskConfig maskConfig;
    public int videoHeight;
    public int videoWidth;
    public int width;
    public int totalFrames = 0;
    public int orientation = 0;
    public PointRect alphaPointRect = new PointRect(0, 0, 0, 0);
    public PointRect rgbPointRect = new PointRect(0, 0, 0, 0);
    public boolean isDefaultConfig = false;

    /* loaded from: classes11.dex */
    public static class PointRect implements Serializable {

        /* renamed from: h  reason: collision with root package name */
        public int f20500h;
        public int w;
        public int x;
        public int y;

        public PointRect() {
        }

        public String toString() {
            return "PointRect{x=" + this.x + ", y=" + this.y + ", w=" + this.w + ", h=" + this.f20500h + '}';
        }

        public PointRect(int i2, int i3, int i4, int i5) {
            this.x = i2;
            this.y = i3;
            this.w = i4;
            this.f20500h = i5;
        }
    }

    /* loaded from: classes11.dex */
    public static class RefVec2 implements Serializable {

        /* renamed from: h  reason: collision with root package name */
        public int f20501h;
        public int w;

        public RefVec2(int i2, int i3) {
            this.w = i2;
            this.f20501h = i3;
        }
    }

    public Boolean parse(JSONObject jSONObject) {
        try {
            JSONObject optJSONObject = jSONObject.optJSONObject("info");
            if (optJSONObject == null) {
                return Boolean.FALSE;
            }
            this.totalFrames = optJSONObject.getInt("f");
            this.width = optJSONObject.getInt(JshopConst.JSHOP_PROMOTIO_W);
            this.height = optJSONObject.getInt(JshopConst.JSHOP_PROMOTIO_H);
            this.videoWidth = optJSONObject.getInt("videoW");
            this.videoHeight = optJSONObject.getInt("videoH");
            this.orientation = optJSONObject.getInt("orien");
            this.fps = optJSONObject.getInt("fps");
            this.isMix = optJSONObject.getInt("isVapx") == 1;
            JSONArray optJSONArray = optJSONObject.optJSONArray("aFrame");
            if (optJSONArray != null) {
                this.alphaPointRect = new PointRect(optJSONArray.getInt(0), optJSONArray.getInt(1), optJSONArray.getInt(2), optJSONArray.getInt(3));
            }
            JSONArray optJSONArray2 = optJSONObject.optJSONArray("rgbFrame");
            if (optJSONArray2 != null) {
                this.rgbPointRect = new PointRect(optJSONArray2.getInt(0), optJSONArray2.getInt(1), optJSONArray2.getInt(2), optJSONArray2.getInt(3));
            }
            return Boolean.TRUE;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return Boolean.FALSE;
        }
    }
}
