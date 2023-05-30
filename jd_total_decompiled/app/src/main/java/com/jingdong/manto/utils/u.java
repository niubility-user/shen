package com.jingdong.manto.utils;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import com.jingdong.jdreact.plugin.viewshot.ViewShot;
import com.jingdong.manto.m.l0;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class u {
    private static Pair<JSONArray, Integer> a(boolean z, Map map, com.jingdong.manto.jsengine.a aVar, boolean z2) {
        JSONObject jSONObject;
        int nativeBufferId;
        JSONArray jSONArray = new JSONArray();
        Iterator it = map.entrySet().iterator();
        int i2 = 0;
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String str = (String) entry.getKey();
            Object value = entry.getValue();
            if (value != null && (value instanceof ByteBuffer)) {
                try {
                    jSONObject = new JSONObject();
                    jSONObject.put("key", str);
                } catch (Exception unused) {
                }
                if (z2) {
                    jSONObject.put(ViewShot.Results.BASE_64, Base64.encodeToString(t.a((ByteBuffer) value), 2));
                } else {
                    if (z && (nativeBufferId = aVar.getNativeBufferId()) != -1) {
                        ByteBuffer byteBuffer = (ByteBuffer) value;
                        aVar.setNativeBuffer(nativeBufferId, byteBuffer);
                        jSONObject.put("id", nativeBufferId);
                        i2 += byteBuffer.capacity();
                    }
                    it.remove();
                }
                jSONArray.put(jSONObject);
                it.remove();
            }
        }
        return new Pair<>(jSONArray, Integer.valueOf(i2));
    }

    public static boolean a(com.jingdong.manto.m.e0 e0Var, Map map, com.jingdong.manto.m.a aVar) {
        if (e0Var != null && e0Var.g() != null && map != null && aVar != null) {
            com.jingdong.manto.jsengine.a aVar2 = (com.jingdong.manto.jsengine.a) e0Var.g().getInterface(com.jingdong.manto.jsengine.a.class);
            boolean z = aVar2 != null && aVar2.canUseNativeBuffer();
            Pair<JSONArray, Integer> a = a(z, map, aVar2, !z);
            int intValue = ((Integer) a.second).intValue();
            JSONArray jSONArray = (JSONArray) a.first;
            if (intValue > e0Var.h().s.p.d) {
                if (aVar instanceof com.jingdong.manto.m.d) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("message", "readAs native buffer parameter fail, event=" + aVar.getJsApiName() + ", error=native buffer exceed size limit");
                    hashMap.put("stack", "");
                    e0Var.a("onError", new JSONObject(hashMap).toString(), 0);
                } else if (aVar instanceof com.jingdong.manto.m.c0) {
                    ((com.jingdong.manto.m.c0) aVar).msg = "fail readAs native buffer parameter fail. native buffer exceed size limit";
                }
                return false;
            } else if (jSONArray.length() > 0) {
                map.put("__nativeBuffers__", jSONArray);
            }
        }
        return true;
    }

    public static boolean a(com.jingdong.manto.m.e0 e0Var, JSONObject jSONObject, com.jingdong.manto.m.a aVar) {
        JSONArray optJSONArray;
        ByteBuffer nativeBuffer;
        if (e0Var == null || jSONObject == null || (optJSONArray = jSONObject.optJSONArray("__nativeBuffers__")) == null) {
            return true;
        }
        jSONObject.remove("__nativeBuffers__");
        boolean z = aVar instanceof l0;
        if (z) {
            if (((l0) aVar).a == Thread.currentThread().getId()) {
                throw new IllegalArgumentException("processFromJs can not use in SyncThread");
            }
        }
        com.jingdong.manto.jsengine.a aVar2 = (com.jingdong.manto.jsengine.a) e0Var.g().getInterface(com.jingdong.manto.jsengine.a.class);
        boolean z2 = aVar2 != null && aVar2.canUseNativeBuffer();
        int i2 = 0;
        for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i3);
            if (optJSONObject != null) {
                String optString = optJSONObject.optString("key");
                if (!MantoStringUtils.isEmpty(optString)) {
                    int optInt = optJSONObject.optInt("id", -1);
                    if (optInt == -1) {
                        try {
                            String optString2 = optJSONObject.optString(ViewShot.Results.BASE_64, "");
                            jSONObject.put(optString, TextUtils.isEmpty(optString2) ? ByteBuffer.allocate(0) : ByteBuffer.wrap(Base64.decode(optString2.getBytes(Charset.forName("UTF-8")), 2)));
                        } catch (JSONException unused) {
                        }
                    } else if (z2 && (nativeBuffer = aVar2.getNativeBuffer(optInt)) != null) {
                        nativeBuffer.position(0);
                        i2 += nativeBuffer.capacity();
                        try {
                            jSONObject.put(optString, nativeBuffer);
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }
        }
        if (i2 <= e0Var.h().s.p.d) {
            return true;
        }
        if (z) {
            ((l0) aVar).msg = "fail convert native buffer parameter fail. native buffer exceed size limit";
        }
        return false;
    }

    public static boolean a(com.jingdong.manto.m.e0 e0Var, JSONObject jSONObject, Map map, boolean z) {
        if (e0Var != null && e0Var.g() != null && map != null) {
            com.jingdong.manto.jsengine.a aVar = (com.jingdong.manto.jsengine.a) e0Var.g().getInterface(com.jingdong.manto.jsengine.a.class);
            Pair<JSONArray, Integer> a = a(aVar != null && aVar.canUseNativeBuffer(), map, aVar, z);
            int intValue = ((Integer) a.second).intValue();
            JSONArray jSONArray = (JSONArray) a.first;
            if (intValue <= e0Var.h().s.p.d && jSONArray.length() > 0) {
                try {
                    jSONObject.put("__nativeBuffers__", jSONArray);
                    return true;
                } catch (JSONException unused) {
                }
            }
        }
        return false;
    }

    public static boolean a(JSONObject jSONObject) {
        return (jSONObject == null || jSONObject.optJSONArray("__nativeBuffers__") == null) ? false : true;
    }

    public static byte[] a(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            return new byte[0];
        }
        if (byteBuffer.isDirect()) {
            int position = byteBuffer.position();
            byteBuffer.position(0);
            byte[] bArr = new byte[byteBuffer.remaining()];
            byteBuffer.get(bArr);
            byteBuffer.position(position);
            return bArr;
        }
        return byteBuffer.array();
    }

    public static int[] a(byte[] bArr) {
        int length = bArr.length / 4;
        int[] iArr = new int[length];
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            int i4 = i3 + 3;
            int i5 = i3 + 4;
            int i6 = (bArr[i3 + 1] & 255) << 8;
            iArr[i2] = ((bArr[i3] & 255) << 16) | i6 | (bArr[i3 + 2] & 255) | ((bArr[i4] & 255) << 24);
            i2++;
            i3 = i5;
        }
        return iArr;
    }
}
