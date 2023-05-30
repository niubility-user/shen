package tv.danmaku.ijk.media.alpha.mix;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.alpha.AlphaConfig;

/* loaded from: classes11.dex */
public class Frame {
    private AlphaConfig.PointRect frame;
    private AlphaConfig.PointRect mFrame;
    private int mt;
    private String srcId;
    private int z;

    public Frame(int i2, JSONObject jSONObject) {
        this.srcId = "";
        this.z = 0;
        this.mt = 0;
        try {
            this.srcId = jSONObject.getString("srcId");
            this.z = jSONObject.getInt("z");
            JSONArray jSONArray = jSONObject.getJSONArray("frame");
            this.frame = new AlphaConfig.PointRect(jSONArray.getInt(0), jSONArray.getInt(1), jSONArray.getInt(2), jSONArray.getInt(3));
            JSONArray jSONArray2 = jSONObject.getJSONArray("mFrame");
            this.mFrame = new AlphaConfig.PointRect(jSONArray2.getInt(0), jSONArray2.getInt(1), jSONArray2.getInt(2), jSONArray2.getInt(3));
            this.mt = jSONObject.getInt("mt");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public AlphaConfig.PointRect getFrame() {
        return this.frame;
    }

    public int getMt() {
        return this.mt;
    }

    public String getSrcId() {
        return this.srcId;
    }

    public int getZ() {
        return this.z;
    }
}
