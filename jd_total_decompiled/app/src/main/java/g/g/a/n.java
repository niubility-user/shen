package g.g.a;

import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.jd.dynamic.DYConstants;
import com.meizu.cloud.pushsdk.constants.PushConstants;

/* loaded from: classes18.dex */
public class n {
    private String a = "1";
    private String b = "2";

    /* renamed from: c  reason: collision with root package name */
    private String f19632c = "0";
    private String d = "2.0";

    /* renamed from: e  reason: collision with root package name */
    private String f19633e = "1.0";

    /* renamed from: f  reason: collision with root package name */
    private String f19634f = "16000";

    /* renamed from: g  reason: collision with root package name */
    private String f19635g = "https://aiapi.jd.com/jdai/tts";

    /* renamed from: h  reason: collision with root package name */
    private String f19636h = "68D8FB1BB8E415F1AC8090F527C28241";

    /* renamed from: i  reason: collision with root package name */
    private String f19637i = "CDCD9EFA04047E13656F0B408C1365C8";

    /* renamed from: j  reason: collision with root package name */
    private String f19638j = "d1bd6e9f-8dbf-4ea6-b92f-75dfa263304c";

    /* renamed from: k  reason: collision with root package name */
    private String f19639k = "1";

    /* renamed from: l  reason: collision with root package name */
    private String f19640l = "1";

    /* renamed from: m  reason: collision with root package name */
    private String f19641m = "0";

    /* renamed from: n  reason: collision with root package name */
    private String f19642n = "1";
    private String o = "0";
    private String p = DYConstants.DY_ASSETS;
    private String q = "5000";
    private String r = "10000";
    private String s = "2";
    private String t = "http1";
    private String u = "";
    private String v = "3";
    private String w = "100";
    private String x = "0";

    public String a(String str) {
        if (str.equals("tte")) {
            return this.a;
        }
        if (str.equals("aue")) {
            return this.b;
        }
        if (str.equals("tim")) {
            return this.f19632c;
        }
        if (str.equals("vol")) {
            return this.d;
        }
        if (str.equals("sp")) {
            return this.f19633e;
        }
        if (str.equals("sr")) {
            return this.f19634f;
        }
        if (str.equals("serverURL")) {
            return this.f19635g;
        }
        if (str.equals(PairKey.APP_KEY)) {
            return this.f19636h;
        }
        if (str.equals("appSecret")) {
            return this.f19637i;
        }
        if (str.equals("streamMode")) {
            return this.f19639k;
        }
        if (str.equals("appID")) {
            return this.f19638j;
        }
        if (str.equals("CustomerType")) {
            return this.f19640l;
        }
        if (str.equals(PushConstants.PUSH_NOTIFICATION_CREATE_TIMES_TAMP)) {
            return this.f19641m;
        }
        if (str.equals("ttsMode")) {
            return this.f19642n;
        }
        if (str.equals("ttsModel")) {
            return this.o;
        }
        if (str.equals("assetsPath")) {
            return this.p;
        }
        if (str.equals("connectTimeout")) {
            return this.q;
        }
        if (str.equals("readTimeout")) {
            return this.r;
        }
        if (str.equals("playCacheNum")) {
            return this.s;
        }
        if (str.equals("httpProtocols")) {
            return this.t;
        }
        if (str.equals("authID")) {
            return this.u;
        }
        if (str.equals("httpTryCount")) {
            return this.v;
        }
        if (str.equals("playerMaxCache")) {
            return this.w;
        }
        if (str.equals("endPackSleepMs")) {
            return this.x;
        }
        System.out.println("key=" + str + "not support");
        return null;
    }

    public int b(String str, String str2) {
        if (str.equals("tte")) {
            this.a = str2;
        } else if (str.equals("aue")) {
            this.b = str2;
        } else if (str.equals("tim")) {
            this.f19632c = str2;
        } else if (str.equals("vol")) {
            this.d = str2;
        } else if (str.equals("sp")) {
            this.f19633e = str2;
        } else if (str.equals("sr")) {
            this.f19634f = str2;
        } else if (str.equals("serverURL")) {
            this.f19635g = str2;
        } else if (str.equals(PairKey.APP_KEY)) {
            this.f19636h = str2;
        } else if (str.equals("appSecret")) {
            this.f19637i = str2;
        } else if (str.equals("streamMode")) {
            this.f19639k = str2;
        } else if (str.equals("appID")) {
            this.f19638j = str2;
        } else if (str.equals("CustomerType")) {
            this.f19640l = str2;
        } else if (str.equals(PushConstants.PUSH_NOTIFICATION_CREATE_TIMES_TAMP)) {
            this.f19641m = str2;
        } else if (str.equals("ttsMode")) {
            this.f19642n = str2;
        } else if (str.equals("ttsModel")) {
            this.o = str2;
        } else if (str.equals("assetsPath")) {
            this.p = str2;
        } else if (str.equals("connectTimeout")) {
            this.q = str2;
        } else if (str.equals("readTimeout")) {
            this.r = str2;
        } else if (str.equals("playCacheNum")) {
            this.s = str2;
        } else if (str.equals("httpProtocols")) {
            this.t = str2;
        } else if (str.equals("authID")) {
            this.u = str2;
        } else if (str.equals("httpTryCount")) {
            this.v = str2;
        } else if (str.equals("playerMaxCache")) {
            this.w = str2;
        } else if (str.equals("endPackSleepMs")) {
            this.x = str2;
        } else {
            System.out.println("key=" + str + "not support");
            return -1;
        }
        toString();
        return 0;
    }

    public String toString() {
        return "toString\uff1a\ntte(\u6587\u672c\u7f16\u7801)=" + this.a + "\naue\uff08\u97f3\u9891\u7f16\u7801\uff09=" + this.b + "\ntim\uff08\u97f3\u8272\uff09=" + this.f19632c + "\nvol\uff08\u5408\u6210\u97f3\u91cf\uff09=" + this.d + "\nsp\uff08\u97f3\u901f\uff09=" + this.f19633e + "\nsr\uff08\u91c7\u6837\u7387\uff09=" + this.f19634f + "\nserverURL\uff08\u670d\u52a1\u5668\uff09=" + this.f19635g + "\nappKey=" + this.f19636h + "\nappSecret=" + this.f19637i + "\nstreamMode\uff08\u6d41\u5f0f\uff09=" + this.f19639k + "\nappID=" + this.f19638j + "\nCustomerType\uff08\u5185\u5916\u670d\u52a1\u5668\uff09=" + this.f19640l + "\ntt\uff08\u6587\u672c\u683c\u5f0f\uff09=" + this.f19641m + "\nttsMode\uff08\u79bb\u5728\u7ebf\uff09=" + this.f19642n + "\nttsModel\uff08\u6a21\u578b\uff09=" + this.o + "\nassetPath=" + this.p + "\nconnectTimeout=" + this.q + "\nreadTimeout=" + this.r + "\nplayCacheNum=" + this.s + "\nhttpProtocols=" + this.t + "\nauthID=" + this.u + "\nhttpTryCount=" + this.v + "\nendPackSleepMs=" + this.x;
    }
}
