package g.f.a.a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.common.net.HttpHeaders;
import com.jingdong.common.messagecenter.MIPushMsg;
import com.jingdong.sdk.platform.business.personal.R2;
import com.tencent.connect.common.Constants;
import com.tencent.smtt.sdk.ProxyConfig;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* loaded from: classes12.dex */
public class g {
    private static String a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 1).versionName;
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static Map<String, String> b(Context context, String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        HashMap hashMap = new HashMap();
        hashMap.put("Domain", jSONObject.optString("DOMAIN", "search"));
        hashMap.put("Application-Id", jSONObject.optString(MIPushMsg.APP_ID));
        String uuid = UUID.randomUUID().toString();
        g.f.a.b.b.b("SpeechHttpHeaderUtils", "requestId: " + uuid);
        hashMap.put("Request-Id", uuid);
        hashMap.put("Sequence-Id", "-1");
        hashMap.put("Asr-Protocol", "1");
        hashMap.put("Net-State", "" + d(context));
        hashMap.put("Applicator", "0");
        hashMap.put(HttpHeaders.ACCEPT_ENCODING, ProxyConfig.MATCH_ALL_SCHEMES);
        hashMap.put("User-Id", g.f.a.b.a.a(context));
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("channel", 1);
        jSONObject2.put("format", "opus");
        jSONObject2.put(IjkMediaMeta.IJKM_KEY_SAMPLE_RATE, jSONObject.optInt("SAMPLE_RATE", R2.id.rn_redbox_report_label));
        if (jSONObject.has("POST_PROCESS")) {
            jSONObject2.put("post_process", jSONObject.optInt("POST_PROCESS"));
        }
        if (jSONObject.has("PARTIAL_RESULT")) {
            jSONObject2.put("partial_result", jSONObject.getInt("PARTIAL_RESULT"));
        } else {
            jSONObject2.put("partial_result", 0);
        }
        if (jSONObject.has("PUNC_PROCESS")) {
            jSONObject2.put("punc_partial_process", jSONObject.getInt("PUNC_PROCESS"));
        } else {
            jSONObject2.put("punc_partial_process", 0);
        }
        if (jSONObject.has("PUNC_END_PROCESS")) {
            jSONObject2.put("punc_end_process", jSONObject.getInt("PUNC_END_PROCESS"));
        }
        if (jSONObject.has("ONLINE_VAD")) {
            jSONObject2.put("online_vad", jSONObject.getInt("ONLINE_VAD"));
        }
        if (jSONObject.has("SMART_VAD")) {
            jSONObject2.put("smart_vad", jSONObject.getInt("SMART_VAD"));
        }
        JSONObject jSONObject3 = new JSONObject();
        JSONArray optJSONArray = jSONObject.optJSONArray("PHRASE_LIST");
        int i2 = 0;
        for (int i3 = 0; optJSONArray != null && i3 < optJSONArray.length(); i3++) {
            try {
                i2 += optJSONArray.getString(i3).getBytes("UTF-8").length;
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
            if (i2 > 2048) {
                optJSONArray.remove(i3);
            }
        }
        jSONObject3.put("phrase", optJSONArray);
        jSONObject3.put("context_id", jSONObject.optString("CONTEXT_ID"));
        JSONObject jSONObject4 = new JSONObject();
        jSONObject4.put("autoend", jSONObject.optBoolean("SERVER_VAD_ENABLE", false));
        jSONObject4.put(Constants.PARAM_PLATFORM, "Android");
        jSONObject4.put("deviceid", g.f.a.b.a.a(context));
        jSONObject4.put("version", a(context));
        jSONObject4.put("biasing_context", jSONObject3);
        if (jSONObject.has("VPR_MODE")) {
            jSONObject4.put("vpr_mode", jSONObject.getString("VPR_MODE"));
        }
        jSONObject4.put("encode", jSONObject2);
        hashMap.put("Property", jSONObject4.toString());
        hashMap.put(HttpHeaders.CONTENT_TYPE, "application/octet-stream");
        hashMap.put("Connection", "Keep-Alive");
        return hashMap;
    }

    public static String c(int i2) {
        return "{\"err_code\":\"" + i2 + "\",\"err_msg\":\"" + f.a(i2) + "\",\"content\":[{\"text\":\"\"}]}";
    }

    private static int d(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            if (activeNetworkInfo.getType() == 1) {
                return 2;
            }
            if (activeNetworkInfo.getType() == 0) {
                return 5;
            }
        }
        return 0;
    }
}
