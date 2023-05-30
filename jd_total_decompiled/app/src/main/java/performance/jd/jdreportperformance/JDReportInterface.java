package performance.jd.jdreportperformance;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import performance.jd.jdreportperformance.e.c;
import performance.jd.jdreportperformance.entity.StategyEntity;
import performance.jd.jdreportperformance.minterface.InitInformation;

/* loaded from: classes.dex */
public class JDReportInterface {
    public static void destroy() {
    }

    public static StategyEntity getEntity(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        a.b().a(context);
        return c.a().a(str, str2);
    }

    public static void init(Context context, InitInformation initInformation) {
        a.b().a(context, initInformation);
    }

    public static boolean sendData(Context context, InitInformation initInformation, HashMap<String, String> hashMap) {
        return a.b().a(context, initInformation, hashMap);
    }

    public static boolean sendData(Context context, InitInformation initInformation, ArrayList<HashMap<String, String>> arrayList) {
        return a.b().a(context, initInformation, arrayList);
    }
}
