package tv.danmaku.ijk.media.player;

import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONObject;

/* loaded from: classes11.dex */
public class MsgExtInfoUtil {
    public static final String PRE_DEF_END_TIME = "endTime";
    public static final String PRE_DEF_START_TIME = "startTime";

    public static HashMap<String, Object> parseExtInfo(Object obj) {
        HashMap<String, Object> hashMap = new HashMap<>();
        if (obj == null) {
            return hashMap;
        }
        try {
            JSONObject jSONObject = new JSONObject(obj.toString());
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, jSONObject.get(next));
            }
        } catch (Exception unused) {
        }
        return hashMap;
    }
}
