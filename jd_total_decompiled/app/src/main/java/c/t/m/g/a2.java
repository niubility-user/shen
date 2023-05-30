package c.t.m.g;

import com.jd.dynamic.DYConstants;
import com.jingdong.jdsdk.constant.JshopConst;
import java.util.HashMap;

/* loaded from: classes.dex */
public class a2 {
    public static boolean a = false;
    public static boolean b = false;

    /* renamed from: c  reason: collision with root package name */
    public static boolean f290c = false;
    public static boolean d = false;

    /* renamed from: e  reason: collision with root package name */
    public static boolean f291e = true;

    /* renamed from: f  reason: collision with root package name */
    public static boolean f292f;

    /* renamed from: g  reason: collision with root package name */
    public static boolean f293g;

    /* renamed from: h  reason: collision with root package name */
    public static boolean f294h;

    /* renamed from: i  reason: collision with root package name */
    public static volatile x f295i;

    /* renamed from: j  reason: collision with root package name */
    public static final h f296j = new h();

    /* renamed from: k  reason: collision with root package name */
    public static e5 f297k = e5.a;

    public static final int a(String str) {
        if (str.startsWith("fc4") || str.startsWith("dc4")) {
            return 4;
        }
        if (str.startsWith("fc3") || str.startsWith("dc3")) {
            return 3;
        }
        if (str.startsWith("fc2") || str.startsWith("dc2")) {
            return 2;
        }
        return (str.startsWith("fc1") || str.startsWith("fc") || str.startsWith("dc")) ? 1 : -1;
    }

    public static final String b(int i2) {
        if (a) {
            StringBuilder sb = new StringBuilder();
            sb.append(f294h ? "https://lstest.map.qq.com/nlpdr?sf" : "https://nlp.map.qq.com/?sf");
            sb.append(i2);
            return sb.toString();
        }
        String str = f294h ? "https://testdatalbs.sparta.html5.qq.com/tr?sf" : "https://analytics.map.qq.com/?sf";
        if (i2 <= 1) {
            return i2 == 1 ? str : "";
        }
        return str + i2;
    }

    public static HashMap<String, String> c() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("D_CH_ID", "fc_sdk");
        hashMap.put("D_FC_SRC", "209");
        hashMap.put("D_UP_INTERVAL", "1800000");
        hashMap.put("D_UP_USE_HTTPS", DYConstants.DY_TRUE);
        hashMap.put("D_MAX_1F_SIZE", "102400");
        hashMap.put("D_NUM_UP", "1");
        hashMap.put("D_MAX_BUF_WF", "25600");
        hashMap.put("D_MAX_FOLDER_SIZE", "104857600");
        hashMap.put("D_MAX_DAY_RENAME", "3");
        hashMap.put("D_MAX_DAY_DELETE", "30");
        hashMap.put("D_MAX_SIZE_UP_1DAY", "10485760");
        hashMap.put("D_UP_NET", JshopConst.JSHOP_PROMOTIO_W);
        hashMap.put("D_POS_COLL", DYConstants.DY_FALSE);
        hashMap.put("D_WRITE_MAC", DYConstants.DY_FALSE);
        hashMap.put("D_UP_WF_INFO", DYConstants.DY_TRUE);
        hashMap.put("D_UP_U_TRACK_INFO", DYConstants.DY_FALSE);
        hashMap.put("D_UP_GPS_FOR_NAVI", DYConstants.DY_FALSE);
        return hashMap;
    }

    public static final String d() {
        return "fc4.";
    }
}
