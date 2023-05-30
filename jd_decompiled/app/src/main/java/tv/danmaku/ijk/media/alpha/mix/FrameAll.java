package tv.danmaku.ijk.media.alpha.mix;

import android.util.SparseArray;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes11.dex */
public class FrameAll {
    private SparseArray<FrameSet> map = new SparseArray<>();

    public FrameAll(JSONObject jSONObject) {
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("frame");
            int length = jSONArray.length();
            for (int i2 = 0; i2 < length; i2++) {
                FrameSet frameSet = new FrameSet(jSONArray.getJSONObject(i2));
                this.map.put(frameSet.getIndex(), frameSet);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public SparseArray<FrameSet> getMap() {
        return this.map;
    }
}
