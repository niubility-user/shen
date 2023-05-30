package tv.danmaku.ijk.media.alpha.mix;

import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.alpha.mix.Src;

/* loaded from: classes11.dex */
public class SrcMap {
    private HashMap<String, Src> map = new HashMap<>();

    public SrcMap(JSONObject jSONObject) {
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("src");
            int length = jSONArray.length();
            for (int i2 = 0; i2 < length; i2++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                if (jSONObject2 != null) {
                    Src src = new Src(jSONObject2);
                    if (src.getSrcType() != Src.SrcType.UNKNOWN) {
                        this.map.put(src.getSrcId(), src);
                    }
                }
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public HashMap<String, Src> getMap() {
        return this.map;
    }
}
