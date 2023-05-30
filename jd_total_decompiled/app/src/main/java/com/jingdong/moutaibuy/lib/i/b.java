package com.jingdong.moutaibuy.lib.i;

import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class b {
    public static HashMap<String, Integer> a(String str) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, Integer.valueOf(jSONObject.getInt(next)));
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return hashMap;
    }
}
