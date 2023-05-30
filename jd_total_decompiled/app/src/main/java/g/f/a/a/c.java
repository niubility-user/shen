package g.f.a.a;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Handler;
import com.jd.ai.asr.jni.JDOpusJni;
import com.jd.stat.network.ExceptionEnum;
import com.jdpay.membercode.bean.CodeResultInfoBean;
import com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleError;
import com.jingdong.sdk.platform.business.personal.R2;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* loaded from: classes12.dex */
public class c implements g.f.a.c.d {

    /* renamed from: g  reason: collision with root package name */
    private g.f.a.c.d f19478g;

    /* renamed from: h  reason: collision with root package name */
    private j f19479h;

    /* renamed from: j  reason: collision with root package name */
    private Context f19481j;

    /* renamed from: l  reason: collision with root package name */
    private boolean f19483l;

    /* renamed from: i  reason: collision with root package name */
    private int f19480i = 0;

    /* renamed from: k  reason: collision with root package name */
    private boolean f19482k = false;

    /* renamed from: m  reason: collision with root package name */
    private String f19484m = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ int f19485g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ boolean f19486h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ byte[] f19487i;

        a(int i2, boolean z, byte[] bArr) {
            this.f19485g = i2;
            this.f19486h = z;
            this.f19487i = bArr;
        }

        @Override // java.lang.Runnable
        public void run() {
            g.f.a.b.b.b("seqId: " + this.f19485g + "  isLast: " + this.f19486h, new String[0]);
            j jVar = c.this.f19479h;
            boolean z = this.f19486h;
            int i2 = this.f19485g;
            if (z) {
                i2 = -i2;
            }
            g.f.a.a.a e2 = jVar.e(i2, z ? new byte[0] : this.f19487i);
            try {
                Map i3 = c.this.i(e2);
                g.f.a.b.b.b("asrResponse: " + e2.b(), new String[0]);
                boolean booleanValue = ((Boolean) i3.get("isFinish")).booleanValue();
                g.f.a.b.b.b("isFinish " + booleanValue, new String[0]);
                h.a(c.this.f19478g, booleanValue ? "DETECT.FINISH" : "DETECT.PARITAL", (String) i3.get("result"), null, 0, 0);
                if (booleanValue && c.this.f19483l) {
                    c cVar = c.this;
                    cVar.n(cVar.f19484m);
                }
                if (booleanValue && !c.this.f19483l) {
                    c.this.r();
                }
            } catch (JSONException e3) {
                e3.printStackTrace();
                h.a(c.this.f19478g, "DETECT.FINISH", g.c(IMediaPlayer.MEDIA_ERROR_MALFORMED), null, 0, 0);
                c.this.r();
            }
            g.f.a.b.b.b("asrResponse: " + e2.toString(), new String[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public enum b {
        SUCCESS,
        ERROR,
        NO_MATCH
    }

    public c(Context context) {
        new Handler();
        this.f19481j = context;
    }

    private String j(String str, String str2, long j2, String str3) {
        return str + "?appkey=" + str2 + "&timestamp=" + j2 + "&sign=" + str3;
    }

    private b k(JSONObject jSONObject) {
        if (jSONObject.has("code") && jSONObject.has("msg")) {
            try {
                if (Integer.valueOf(jSONObject.getString("code")).intValue() == 10000) {
                    return b.SUCCESS;
                }
                return b.ERROR;
            } catch (Exception e2) {
                e2.printStackTrace();
                return b.ERROR;
            }
        }
        return b.NO_MATCH;
    }

    private void l(byte[] bArr) {
        s(bArr, true);
    }

    private int m(int i2) {
        int intValue = Integer.valueOf(i2).intValue();
        if (intValue < 32000) {
            if (i2 != 0) {
                switch (i2) {
                    case 31001:
                        return -1006;
                    case 31002:
                        return ExceptionEnum.CANCELLED;
                    case 31003:
                        return -1008;
                    case 31004:
                        return -1011;
                    case 31005:
                        return -1012;
                    case 31006:
                        return -1009;
                    default:
                        return IMediaPlayer.MEDIA_ERROR_MALFORMED;
                }
            }
            return 0;
        } else if (intValue > 61001) {
            switch (intValue) {
                case 61001:
                    return -2001;
                case 61002:
                    return JDRiskHandleError.CODE_CHECK_ERROR_FIND_ZERO;
                case 61005:
                    return JDRiskHandleError.CODE_CHECK_HTTP_RESPONSE_ERROR;
                case 63001:
                    return -2005;
                case 63002:
                    return -2006;
                case 63003:
                    return -2007;
                case 64004:
                    return -2008;
                default:
                    return JDRiskHandleError.CODE_SDK_NOT_INIT;
            }
        } else {
            return IMediaPlayer.MEDIA_ERROR_MALFORMED;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n(String str) throws JSONException {
        Map<String, String> b2 = g.b(this.f19481j, str);
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optString("APPKEY", "");
        String optString2 = jSONObject.optString("SECRETKEY", "");
        long currentTimeMillis = System.currentTimeMillis();
        String j2 = j(jSONObject.optString(CodeResultInfoBean.NEXT_STEP_URL, "https://aiapi.jd.com/jdai/asr"), optString, currentTimeMillis, p(optString2 + currentTimeMillis));
        j jVar = this.f19479h;
        if (jVar != null) {
            jVar.d(j2, b2);
        }
    }

    private String p(String str) {
        if (str == null || "".equals(str)) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            messageDigest.update(str.getBytes("UTF-8"));
            byte[] digest = messageDigest.digest();
            for (int i2 = 0; i2 < digest.length; i2++) {
                if ((digest[i2] & 255) < 16) {
                    stringBuffer.append("0" + Integer.toHexString(digest[i2] & 255));
                } else {
                    stringBuffer.append(Integer.toHexString(digest[i2] & 255));
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return stringBuffer.toString();
    }

    @TargetApi(19)
    private void q(String str) throws JSONException {
        if (str != null) {
            this.f19484m = str;
        }
        Map<String, String> b2 = g.b(this.f19481j, str);
        JSONObject jSONObject = new JSONObject(this.f19484m);
        String optString = jSONObject.optString("APPKEY", "");
        String optString2 = jSONObject.optString("SECRETKEY", "");
        long currentTimeMillis = System.currentTimeMillis();
        String p = p(optString2 + currentTimeMillis);
        String optString3 = jSONObject.optString(CodeResultInfoBean.NEXT_STEP_URL, "https://aiapi.jd.com/jdai/asr");
        int optInt = jSONObject.optInt("SAMPLE_RATE", R2.id.rn_redbox_report_label);
        String j2 = j(optString3, optString, currentTimeMillis, p);
        if (this.f19479h == null) {
            this.f19479h = new j();
        }
        g.f.a.b.b.c("signUrl: " + j2, new String[0]);
        this.f19479h.d(j2, b2);
        this.f19483l = jSONObject.optBoolean("LONG_SPEECH", false);
        JDOpusJni.Initial(optInt, 0);
        this.f19480i = 0;
        this.f19482k = true;
    }

    private void s(byte[] bArr, boolean z) {
        if ((!this.f19482k || bArr == null) && !this.f19483l) {
            return;
        }
        int i2 = this.f19480i + 1;
        this.f19480i = i2;
        if (!z) {
            byte[] process = JDOpusJni.process(bArr, bArr.length, false);
            StringBuilder sb = new StringBuilder();
            sb.append("process buffer len: ");
            sb.append(process != null ? Integer.valueOf(process.length) : null);
            g.f.a.b.b.b(sb.toString(), new String[0]);
            t(this.f19480i, process, z);
            return;
        }
        t(i2, bArr, z);
        this.f19480i = 0;
        this.f19482k = false;
    }

    private void t(int i2, byte[] bArr, boolean z) {
        i.b().a(new a(i2, z, bArr));
    }

    @Override // g.f.a.c.d
    public void a(g.f.a.c.c cVar) {
    }

    @Override // g.f.a.c.d
    public void b(String str, String str2, byte[] bArr, int i2, int i3) {
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -1460730128:
                if (str.equals("DETECT.END")) {
                    c2 = 0;
                    break;
                }
                break;
            case -71499739:
                if (str.equals("DETECT.CANCEL")) {
                    c2 = 1;
                    break;
                }
                break;
            case 705760567:
                if (str.equals("DETECT.START")) {
                    c2 = 2;
                    break;
                }
                break;
            case 1961964565:
                if (str.equals("DETECT.DATA")) {
                    c2 = 3;
                    break;
                }
                break;
            case 1962429549:
                if (str.equals("DETECT.STOP")) {
                    c2 = 4;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                l(bArr);
                return;
            case 1:
                r();
                return;
            case 2:
                try {
                    q(str2);
                    return;
                } catch (JSONException e2) {
                    e2.printStackTrace();
                    return;
                }
            case 3:
                s(bArr, false);
                return;
            case 4:
                l(new byte[0]);
                return;
            default:
                return;
        }
    }

    @Override // g.f.a.c.d
    public void c(String str, String str2) {
    }

    public Map i(g.f.a.a.a aVar) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        HashMap hashMap = new HashMap();
        int a2 = aVar.a();
        boolean z = true;
        if (a2 != 0) {
            jSONObject.put("err_code", a2);
            jSONObject.put("err_msg", f.a(a2));
            jSONObject.put("content", new JSONArray().put(new JSONObject().put("text", "")));
        } else {
            JSONObject jSONObject2 = new JSONObject(aVar.b());
            b k2 = k(jSONObject2);
            if (k2 == b.ERROR) {
                jSONObject.put("err_code", -1014);
                jSONObject.put("err_msg", "jd clound gateway error");
            } else {
                if (k2 != b.NO_MATCH) {
                    jSONObject2 = jSONObject2.getJSONObject("result");
                }
                int i2 = jSONObject2.getInt("status");
                int i3 = jSONObject2.getInt("index");
                if (i2 == 0 && i3 >= 0) {
                    z = false;
                }
                int m2 = m(i2);
                jSONObject.put("err_code", m2);
                jSONObject.put("err_msg", f.a(m2));
                if (jSONObject2.has("content")) {
                    jSONObject.put("content", jSONObject2.getJSONArray("content"));
                } else if (jSONObject2.has("result")) {
                    jSONObject.put("content", jSONObject2.getJSONArray("result"));
                }
                jSONObject.put("request_id", jSONObject2.getString("request_id"));
            }
        }
        hashMap.put("result", jSONObject.toString());
        hashMap.put("isFinish", Boolean.valueOf(z));
        return hashMap;
    }

    public void o(g.f.a.c.d dVar) {
        this.f19478g = dVar;
    }

    public void r() {
        this.f19480i = 0;
        this.f19482k = false;
        g.f.a.b.b.b("Opus destory", new String[0]);
    }
}
