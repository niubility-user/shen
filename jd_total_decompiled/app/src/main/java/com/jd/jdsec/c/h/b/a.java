package com.jd.jdsec.c.h.b;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import androidx.annotation.RequiresApi;
import com.facebook.react.uimanager.ViewProps;
import com.jd.jdsec.a.c;
import com.jd.jdsec.a.e;
import com.jd.jdsec.a.h;
import com.jd.jdsec.a.k.i;
import com.jd.jdsec.a.k.j;
import com.jd.jdsec.a.k.k;
import com.jd.jdsec.a.l.b;
import com.jd.jdsec.a.l.d;
import com.jd.jdsec.c.f;
import com.jd.jdsec.c.g;
import com.jd.lib.productdetail.core.entitys.NoStockRecommendHead;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.common.jdmiaosha.utils.cache.JDNetCacheManager;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.platform.business.personal.R2;
import com.jingdong.union.common.config.UnionConstants;
import com.tencent.connect.common.Constants;
import com.tencent.smtt.sdk.ProxyConfig;
import com.unionpay.tsmservice.data.Constant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONObject;

@SuppressLint({"MissingPermission"})
/* loaded from: classes13.dex */
public class a {
    private static JSONObject d;
    private int a = 0;
    private Timer b;

    /* renamed from: c */
    private static final List<List<String>> f2755c = new CopyOnWriteArrayList();

    /* renamed from: e */
    public static final String f2756e = g.a.getPackageName();

    /* renamed from: com.jd.jdsec.c.h.b.a$a */
    /* loaded from: classes13.dex */
    public class C0091a extends TimerTask {

        /* renamed from: g */
        final /* synthetic */ long f2757g;

        /* renamed from: h */
        final /* synthetic */ Context f2758h;

        C0091a(long j2, Context context) {
            a.this = r1;
            this.f2757g = j2;
            this.f2758h = context;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            boolean z;
            try {
                if (System.currentTimeMillis() - this.f2757g > 600000) {
                    a.this.b.cancel();
                    a.l();
                } else {
                    try {
                        z = BaseInfo.isAppForeground();
                    } catch (Exception e2) {
                        b.b("JDSec.Security.DeviceFix", e2.getMessage());
                        z = false;
                    }
                    if (z) {
                        b.e("JDSec.Security.DeviceFix", "\u9ad8\u9891\u91c7\u96c6\u542f\u52a8\uff1a\u5f00\u59cb\u91c7\u96c6");
                        String valueOf = String.valueOf(a.this.a == 0 ? System.currentTimeMillis() : System.currentTimeMillis() - this.f2757g);
                        ((List) a.f2755c.get(0)).add(Arrays.toString(new String[]{valueOf, c.a(this.f2758h)}));
                        ((List) a.f2755c.get(1)).add(Arrays.toString(new String[]{valueOf, Build.VERSION.RELEASE}));
                        ((List) a.f2755c.get(2)).add(Arrays.toString(new String[]{valueOf, e.a()}));
                        Object c2 = c.c(this.f2758h);
                        if (c2 == null) {
                            c2 = new ArrayList();
                        }
                        ((List) a.f2755c.get(3)).add(Arrays.toString(new String[]{valueOf, c2.toString()}));
                        ((List) a.f2755c.get(4)).add(Arrays.toString(new String[]{valueOf, BaseInfo.getNetworkType()}));
                        ((List) a.f2755c.get(5)).add(Arrays.toString(new String[]{valueOf, BaseInfo.getCpuCurFreq()}));
                        ((List) a.f2755c.get(6)).add(Arrays.toString(new String[]{valueOf, String.valueOf(com.jd.jdsec.a.g.i(this.f2758h)[0])}));
                        ((List) a.f2755c.get(7)).add(Arrays.toString(new String[]{valueOf, String.valueOf(com.jd.jdsec.a.g.B(this.f2758h))}));
                    }
                }
                a.i(a.this);
            } catch (Exception e3) {
                b.b("JDSec.Security.DeviceFix", " High Frequency Report Exception" + e3.getMessage());
            }
        }
    }

    private void d(Context context) {
        long currentTimeMillis = System.currentTimeMillis();
        this.b = new Timer();
        this.b.schedule(new C0091a(currentTimeMillis, context), 20L, Integer.parseInt(j().optString("freq")));
    }

    public static void e(JSONObject jSONObject) {
        d = jSONObject;
    }

    public static void f(boolean z) {
        if (z) {
            return;
        }
        for (int i2 = 0; i2 < 8; i2++) {
            b.e("JDSec.Security.DeviceFix", "removeDataInSp " + f2755c.get(i2).toString());
            d.e("List" + i2, "");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:74:0x005a, code lost:
        if (r2.contains(r1) == false) goto L75;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private boolean g(Context context, String str) {
        boolean z = false;
        try {
            String deviceModel = BaseInfo.getDeviceModel();
            ArrayList<String> d2 = com.jd.jdsec.a.l.e.d(j(), "ban-device");
            ArrayList<String> d3 = com.jd.jdsec.a.l.e.d(j(), NoStockRecommendHead.DEVICE);
            if (j().optBoolean("on") && !j().optString("ban-appversion").contains(str)) {
                if (!j().optBoolean("totalTrafficOn")) {
                    if (d3.size() > 0 && d3.contains(deviceModel)) {
                        z = true;
                    }
                    if (d3.size() == 0) {
                    }
                }
                z = true;
            }
            if (z) {
                d(context);
            }
        } catch (Exception e2) {
            b.b("JDSec.Security.DeviceFix", "timeLoopConfig invoke: " + e2.getMessage());
        }
        return z;
    }

    private boolean h(String str, String str2) {
        if (str == null) {
            return true;
        }
        if (str.equals(ProxyConfig.MATCH_ALL_SCHEMES)) {
            return false;
        }
        try {
            return !Arrays.asList(str.split("\\s*,\\s*")).contains(str2);
        } catch (Exception e2) {
            b.b("JDSec.Security.DeviceFix", e2.getMessage());
            return true;
        }
    }

    static /* synthetic */ int i(a aVar) {
        int i2 = aVar.a;
        aVar.a = i2 + 1;
        return i2;
    }

    public static JSONObject j() {
        return d;
    }

    public static void l() {
        if (f2755c.size() == 8) {
            for (int i2 = 0; i2 < 8; i2++) {
                StringBuilder sb = new StringBuilder();
                sb.append("logIntoSp ");
                List<List<String>> list = f2755c;
                sb.append(list.get(i2).toString());
                b.e("JDSec.Security.DeviceFix", sb.toString());
                d.e("List" + i2, list.get(i2).toString());
            }
        }
    }

    private void m() {
        this.a = 0;
        Timer timer = this.b;
        if (timer != null) {
            timer.cancel();
        }
        l();
        f2755c.clear();
        for (int i2 = 0; i2 < 8; i2++) {
            f2755c.add(new CopyOnWriteArrayList());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:1313:0x0175 A[Catch: Exception -> 0x0162, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x0162, blocks: (B:1307:0x0157, B:1313:0x0175, B:1317:0x018a, B:1321:0x019f, B:1325:0x01b9, B:1329:0x01d0, B:1332:0x01e9, B:1334:0x01f0, B:1338:0x0204, B:1340:0x020a, B:1344:0x021f, B:1350:0x0246, B:1352:0x024c, B:1354:0x0252, B:1357:0x0265, B:1359:0x026b, B:1361:0x0271, B:1365:0x0286, B:1368:0x029b, B:1370:0x02a1, B:1372:0x02ab, B:1376:0x02c0, B:1379:0x02d9, B:1381:0x02df, B:1382:0x02e8, B:1386:0x02f9, B:1390:0x0304, B:1394:0x0315, B:1397:0x031f, B:1401:0x0332, B:1405:0x0349, B:1409:0x0360, B:1413:0x0377, B:1417:0x038e, B:1421:0x03a5, B:1425:0x03bc, B:1429:0x03d3, B:1433:0x03ea, B:1437:0x0405, B:1441:0x041c, B:1445:0x0433, B:1449:0x044a, B:1453:0x0461, B:1457:0x047c, B:1461:0x0493, B:1463:0x0499, B:1465:0x049f, B:1470:0x04b2, B:1472:0x04b8, B:1473:0x04bc, B:1476:0x04d1, B:1479:0x04e4, B:1482:0x04f7, B:1485:0x050c, B:1489:0x0522, B:1493:0x0539, B:1497:0x0550, B:1501:0x0567, B:1505:0x057e, B:1509:0x0595, B:1513:0x05b1, B:1517:0x05ca, B:1521:0x05e4, B:1525:0x05fe, B:1529:0x0617, B:1533:0x062e, B:1537:0x0645, B:1541:0x065c, B:1544:0x0675, B:1546:0x067f, B:1550:0x0696, B:1554:0x06b1, B:1558:0x06cc, B:1562:0x06e7, B:1565:0x06fc, B:1395:0x0319, B:1388:0x02fe), top: B:1925:0x0157, inners: #1, #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:1316:0x0188  */
    /* JADX WARN: Removed duplicated region for block: B:1321:0x019f A[Catch: Exception -> 0x0162, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x0162, blocks: (B:1307:0x0157, B:1313:0x0175, B:1317:0x018a, B:1321:0x019f, B:1325:0x01b9, B:1329:0x01d0, B:1332:0x01e9, B:1334:0x01f0, B:1338:0x0204, B:1340:0x020a, B:1344:0x021f, B:1350:0x0246, B:1352:0x024c, B:1354:0x0252, B:1357:0x0265, B:1359:0x026b, B:1361:0x0271, B:1365:0x0286, B:1368:0x029b, B:1370:0x02a1, B:1372:0x02ab, B:1376:0x02c0, B:1379:0x02d9, B:1381:0x02df, B:1382:0x02e8, B:1386:0x02f9, B:1390:0x0304, B:1394:0x0315, B:1397:0x031f, B:1401:0x0332, B:1405:0x0349, B:1409:0x0360, B:1413:0x0377, B:1417:0x038e, B:1421:0x03a5, B:1425:0x03bc, B:1429:0x03d3, B:1433:0x03ea, B:1437:0x0405, B:1441:0x041c, B:1445:0x0433, B:1449:0x044a, B:1453:0x0461, B:1457:0x047c, B:1461:0x0493, B:1463:0x0499, B:1465:0x049f, B:1470:0x04b2, B:1472:0x04b8, B:1473:0x04bc, B:1476:0x04d1, B:1479:0x04e4, B:1482:0x04f7, B:1485:0x050c, B:1489:0x0522, B:1493:0x0539, B:1497:0x0550, B:1501:0x0567, B:1505:0x057e, B:1509:0x0595, B:1513:0x05b1, B:1517:0x05ca, B:1521:0x05e4, B:1525:0x05fe, B:1529:0x0617, B:1533:0x062e, B:1537:0x0645, B:1541:0x065c, B:1544:0x0675, B:1546:0x067f, B:1550:0x0696, B:1554:0x06b1, B:1558:0x06cc, B:1562:0x06e7, B:1565:0x06fc, B:1395:0x0319, B:1388:0x02fe), top: B:1925:0x0157, inners: #1, #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:1324:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:1328:0x01ce  */
    /* JADX WARN: Removed duplicated region for block: B:1332:0x01e9 A[Catch: Exception -> 0x0162, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x0162, blocks: (B:1307:0x0157, B:1313:0x0175, B:1317:0x018a, B:1321:0x019f, B:1325:0x01b9, B:1329:0x01d0, B:1332:0x01e9, B:1334:0x01f0, B:1338:0x0204, B:1340:0x020a, B:1344:0x021f, B:1350:0x0246, B:1352:0x024c, B:1354:0x0252, B:1357:0x0265, B:1359:0x026b, B:1361:0x0271, B:1365:0x0286, B:1368:0x029b, B:1370:0x02a1, B:1372:0x02ab, B:1376:0x02c0, B:1379:0x02d9, B:1381:0x02df, B:1382:0x02e8, B:1386:0x02f9, B:1390:0x0304, B:1394:0x0315, B:1397:0x031f, B:1401:0x0332, B:1405:0x0349, B:1409:0x0360, B:1413:0x0377, B:1417:0x038e, B:1421:0x03a5, B:1425:0x03bc, B:1429:0x03d3, B:1433:0x03ea, B:1437:0x0405, B:1441:0x041c, B:1445:0x0433, B:1449:0x044a, B:1453:0x0461, B:1457:0x047c, B:1461:0x0493, B:1463:0x0499, B:1465:0x049f, B:1470:0x04b2, B:1472:0x04b8, B:1473:0x04bc, B:1476:0x04d1, B:1479:0x04e4, B:1482:0x04f7, B:1485:0x050c, B:1489:0x0522, B:1493:0x0539, B:1497:0x0550, B:1501:0x0567, B:1505:0x057e, B:1509:0x0595, B:1513:0x05b1, B:1517:0x05ca, B:1521:0x05e4, B:1525:0x05fe, B:1529:0x0617, B:1533:0x062e, B:1537:0x0645, B:1541:0x065c, B:1544:0x0675, B:1546:0x067f, B:1550:0x0696, B:1554:0x06b1, B:1558:0x06cc, B:1562:0x06e7, B:1565:0x06fc, B:1395:0x0319, B:1388:0x02fe), top: B:1925:0x0157, inners: #1, #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:1338:0x0204 A[Catch: Exception -> 0x0162, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x0162, blocks: (B:1307:0x0157, B:1313:0x0175, B:1317:0x018a, B:1321:0x019f, B:1325:0x01b9, B:1329:0x01d0, B:1332:0x01e9, B:1334:0x01f0, B:1338:0x0204, B:1340:0x020a, B:1344:0x021f, B:1350:0x0246, B:1352:0x024c, B:1354:0x0252, B:1357:0x0265, B:1359:0x026b, B:1361:0x0271, B:1365:0x0286, B:1368:0x029b, B:1370:0x02a1, B:1372:0x02ab, B:1376:0x02c0, B:1379:0x02d9, B:1381:0x02df, B:1382:0x02e8, B:1386:0x02f9, B:1390:0x0304, B:1394:0x0315, B:1397:0x031f, B:1401:0x0332, B:1405:0x0349, B:1409:0x0360, B:1413:0x0377, B:1417:0x038e, B:1421:0x03a5, B:1425:0x03bc, B:1429:0x03d3, B:1433:0x03ea, B:1437:0x0405, B:1441:0x041c, B:1445:0x0433, B:1449:0x044a, B:1453:0x0461, B:1457:0x047c, B:1461:0x0493, B:1463:0x0499, B:1465:0x049f, B:1470:0x04b2, B:1472:0x04b8, B:1473:0x04bc, B:1476:0x04d1, B:1479:0x04e4, B:1482:0x04f7, B:1485:0x050c, B:1489:0x0522, B:1493:0x0539, B:1497:0x0550, B:1501:0x0567, B:1505:0x057e, B:1509:0x0595, B:1513:0x05b1, B:1517:0x05ca, B:1521:0x05e4, B:1525:0x05fe, B:1529:0x0617, B:1533:0x062e, B:1537:0x0645, B:1541:0x065c, B:1544:0x0675, B:1546:0x067f, B:1550:0x0696, B:1554:0x06b1, B:1558:0x06cc, B:1562:0x06e7, B:1565:0x06fc, B:1395:0x0319, B:1388:0x02fe), top: B:1925:0x0157, inners: #1, #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:1343:0x021d  */
    /* JADX WARN: Removed duplicated region for block: B:1350:0x0246 A[Catch: Exception -> 0x0162, TRY_ENTER, TryCatch #9 {Exception -> 0x0162, blocks: (B:1307:0x0157, B:1313:0x0175, B:1317:0x018a, B:1321:0x019f, B:1325:0x01b9, B:1329:0x01d0, B:1332:0x01e9, B:1334:0x01f0, B:1338:0x0204, B:1340:0x020a, B:1344:0x021f, B:1350:0x0246, B:1352:0x024c, B:1354:0x0252, B:1357:0x0265, B:1359:0x026b, B:1361:0x0271, B:1365:0x0286, B:1368:0x029b, B:1370:0x02a1, B:1372:0x02ab, B:1376:0x02c0, B:1379:0x02d9, B:1381:0x02df, B:1382:0x02e8, B:1386:0x02f9, B:1390:0x0304, B:1394:0x0315, B:1397:0x031f, B:1401:0x0332, B:1405:0x0349, B:1409:0x0360, B:1413:0x0377, B:1417:0x038e, B:1421:0x03a5, B:1425:0x03bc, B:1429:0x03d3, B:1433:0x03ea, B:1437:0x0405, B:1441:0x041c, B:1445:0x0433, B:1449:0x044a, B:1453:0x0461, B:1457:0x047c, B:1461:0x0493, B:1463:0x0499, B:1465:0x049f, B:1470:0x04b2, B:1472:0x04b8, B:1473:0x04bc, B:1476:0x04d1, B:1479:0x04e4, B:1482:0x04f7, B:1485:0x050c, B:1489:0x0522, B:1493:0x0539, B:1497:0x0550, B:1501:0x0567, B:1505:0x057e, B:1509:0x0595, B:1513:0x05b1, B:1517:0x05ca, B:1521:0x05e4, B:1525:0x05fe, B:1529:0x0617, B:1533:0x062e, B:1537:0x0645, B:1541:0x065c, B:1544:0x0675, B:1546:0x067f, B:1550:0x0696, B:1554:0x06b1, B:1558:0x06cc, B:1562:0x06e7, B:1565:0x06fc, B:1395:0x0319, B:1388:0x02fe), top: B:1925:0x0157, inners: #1, #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:1357:0x0265 A[Catch: Exception -> 0x0162, TRY_ENTER, TryCatch #9 {Exception -> 0x0162, blocks: (B:1307:0x0157, B:1313:0x0175, B:1317:0x018a, B:1321:0x019f, B:1325:0x01b9, B:1329:0x01d0, B:1332:0x01e9, B:1334:0x01f0, B:1338:0x0204, B:1340:0x020a, B:1344:0x021f, B:1350:0x0246, B:1352:0x024c, B:1354:0x0252, B:1357:0x0265, B:1359:0x026b, B:1361:0x0271, B:1365:0x0286, B:1368:0x029b, B:1370:0x02a1, B:1372:0x02ab, B:1376:0x02c0, B:1379:0x02d9, B:1381:0x02df, B:1382:0x02e8, B:1386:0x02f9, B:1390:0x0304, B:1394:0x0315, B:1397:0x031f, B:1401:0x0332, B:1405:0x0349, B:1409:0x0360, B:1413:0x0377, B:1417:0x038e, B:1421:0x03a5, B:1425:0x03bc, B:1429:0x03d3, B:1433:0x03ea, B:1437:0x0405, B:1441:0x041c, B:1445:0x0433, B:1449:0x044a, B:1453:0x0461, B:1457:0x047c, B:1461:0x0493, B:1463:0x0499, B:1465:0x049f, B:1470:0x04b2, B:1472:0x04b8, B:1473:0x04bc, B:1476:0x04d1, B:1479:0x04e4, B:1482:0x04f7, B:1485:0x050c, B:1489:0x0522, B:1493:0x0539, B:1497:0x0550, B:1501:0x0567, B:1505:0x057e, B:1509:0x0595, B:1513:0x05b1, B:1517:0x05ca, B:1521:0x05e4, B:1525:0x05fe, B:1529:0x0617, B:1533:0x062e, B:1537:0x0645, B:1541:0x065c, B:1544:0x0675, B:1546:0x067f, B:1550:0x0696, B:1554:0x06b1, B:1558:0x06cc, B:1562:0x06e7, B:1565:0x06fc, B:1395:0x0319, B:1388:0x02fe), top: B:1925:0x0157, inners: #1, #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:1364:0x0284  */
    /* JADX WARN: Removed duplicated region for block: B:1368:0x029b A[Catch: Exception -> 0x0162, TRY_ENTER, TryCatch #9 {Exception -> 0x0162, blocks: (B:1307:0x0157, B:1313:0x0175, B:1317:0x018a, B:1321:0x019f, B:1325:0x01b9, B:1329:0x01d0, B:1332:0x01e9, B:1334:0x01f0, B:1338:0x0204, B:1340:0x020a, B:1344:0x021f, B:1350:0x0246, B:1352:0x024c, B:1354:0x0252, B:1357:0x0265, B:1359:0x026b, B:1361:0x0271, B:1365:0x0286, B:1368:0x029b, B:1370:0x02a1, B:1372:0x02ab, B:1376:0x02c0, B:1379:0x02d9, B:1381:0x02df, B:1382:0x02e8, B:1386:0x02f9, B:1390:0x0304, B:1394:0x0315, B:1397:0x031f, B:1401:0x0332, B:1405:0x0349, B:1409:0x0360, B:1413:0x0377, B:1417:0x038e, B:1421:0x03a5, B:1425:0x03bc, B:1429:0x03d3, B:1433:0x03ea, B:1437:0x0405, B:1441:0x041c, B:1445:0x0433, B:1449:0x044a, B:1453:0x0461, B:1457:0x047c, B:1461:0x0493, B:1463:0x0499, B:1465:0x049f, B:1470:0x04b2, B:1472:0x04b8, B:1473:0x04bc, B:1476:0x04d1, B:1479:0x04e4, B:1482:0x04f7, B:1485:0x050c, B:1489:0x0522, B:1493:0x0539, B:1497:0x0550, B:1501:0x0567, B:1505:0x057e, B:1509:0x0595, B:1513:0x05b1, B:1517:0x05ca, B:1521:0x05e4, B:1525:0x05fe, B:1529:0x0617, B:1533:0x062e, B:1537:0x0645, B:1541:0x065c, B:1544:0x0675, B:1546:0x067f, B:1550:0x0696, B:1554:0x06b1, B:1558:0x06cc, B:1562:0x06e7, B:1565:0x06fc, B:1395:0x0319, B:1388:0x02fe), top: B:1925:0x0157, inners: #1, #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:1375:0x02be  */
    /* JADX WARN: Removed duplicated region for block: B:1379:0x02d9 A[Catch: Exception -> 0x0162, TRY_ENTER, TryCatch #9 {Exception -> 0x0162, blocks: (B:1307:0x0157, B:1313:0x0175, B:1317:0x018a, B:1321:0x019f, B:1325:0x01b9, B:1329:0x01d0, B:1332:0x01e9, B:1334:0x01f0, B:1338:0x0204, B:1340:0x020a, B:1344:0x021f, B:1350:0x0246, B:1352:0x024c, B:1354:0x0252, B:1357:0x0265, B:1359:0x026b, B:1361:0x0271, B:1365:0x0286, B:1368:0x029b, B:1370:0x02a1, B:1372:0x02ab, B:1376:0x02c0, B:1379:0x02d9, B:1381:0x02df, B:1382:0x02e8, B:1386:0x02f9, B:1390:0x0304, B:1394:0x0315, B:1397:0x031f, B:1401:0x0332, B:1405:0x0349, B:1409:0x0360, B:1413:0x0377, B:1417:0x038e, B:1421:0x03a5, B:1425:0x03bc, B:1429:0x03d3, B:1433:0x03ea, B:1437:0x0405, B:1441:0x041c, B:1445:0x0433, B:1449:0x044a, B:1453:0x0461, B:1457:0x047c, B:1461:0x0493, B:1463:0x0499, B:1465:0x049f, B:1470:0x04b2, B:1472:0x04b8, B:1473:0x04bc, B:1476:0x04d1, B:1479:0x04e4, B:1482:0x04f7, B:1485:0x050c, B:1489:0x0522, B:1493:0x0539, B:1497:0x0550, B:1501:0x0567, B:1505:0x057e, B:1509:0x0595, B:1513:0x05b1, B:1517:0x05ca, B:1521:0x05e4, B:1525:0x05fe, B:1529:0x0617, B:1533:0x062e, B:1537:0x0645, B:1541:0x065c, B:1544:0x0675, B:1546:0x067f, B:1550:0x0696, B:1554:0x06b1, B:1558:0x06cc, B:1562:0x06e7, B:1565:0x06fc, B:1395:0x0319, B:1388:0x02fe), top: B:1925:0x0157, inners: #1, #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:1386:0x02f9 A[Catch: Exception -> 0x0162, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x0162, blocks: (B:1307:0x0157, B:1313:0x0175, B:1317:0x018a, B:1321:0x019f, B:1325:0x01b9, B:1329:0x01d0, B:1332:0x01e9, B:1334:0x01f0, B:1338:0x0204, B:1340:0x020a, B:1344:0x021f, B:1350:0x0246, B:1352:0x024c, B:1354:0x0252, B:1357:0x0265, B:1359:0x026b, B:1361:0x0271, B:1365:0x0286, B:1368:0x029b, B:1370:0x02a1, B:1372:0x02ab, B:1376:0x02c0, B:1379:0x02d9, B:1381:0x02df, B:1382:0x02e8, B:1386:0x02f9, B:1390:0x0304, B:1394:0x0315, B:1397:0x031f, B:1401:0x0332, B:1405:0x0349, B:1409:0x0360, B:1413:0x0377, B:1417:0x038e, B:1421:0x03a5, B:1425:0x03bc, B:1429:0x03d3, B:1433:0x03ea, B:1437:0x0405, B:1441:0x041c, B:1445:0x0433, B:1449:0x044a, B:1453:0x0461, B:1457:0x047c, B:1461:0x0493, B:1463:0x0499, B:1465:0x049f, B:1470:0x04b2, B:1472:0x04b8, B:1473:0x04bc, B:1476:0x04d1, B:1479:0x04e4, B:1482:0x04f7, B:1485:0x050c, B:1489:0x0522, B:1493:0x0539, B:1497:0x0550, B:1501:0x0567, B:1505:0x057e, B:1509:0x0595, B:1513:0x05b1, B:1517:0x05ca, B:1521:0x05e4, B:1525:0x05fe, B:1529:0x0617, B:1533:0x062e, B:1537:0x0645, B:1541:0x065c, B:1544:0x0675, B:1546:0x067f, B:1550:0x0696, B:1554:0x06b1, B:1558:0x06cc, B:1562:0x06e7, B:1565:0x06fc, B:1395:0x0319, B:1388:0x02fe), top: B:1925:0x0157, inners: #1, #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:1394:0x0315 A[Catch: Exception -> 0x0162, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x0162, blocks: (B:1307:0x0157, B:1313:0x0175, B:1317:0x018a, B:1321:0x019f, B:1325:0x01b9, B:1329:0x01d0, B:1332:0x01e9, B:1334:0x01f0, B:1338:0x0204, B:1340:0x020a, B:1344:0x021f, B:1350:0x0246, B:1352:0x024c, B:1354:0x0252, B:1357:0x0265, B:1359:0x026b, B:1361:0x0271, B:1365:0x0286, B:1368:0x029b, B:1370:0x02a1, B:1372:0x02ab, B:1376:0x02c0, B:1379:0x02d9, B:1381:0x02df, B:1382:0x02e8, B:1386:0x02f9, B:1390:0x0304, B:1394:0x0315, B:1397:0x031f, B:1401:0x0332, B:1405:0x0349, B:1409:0x0360, B:1413:0x0377, B:1417:0x038e, B:1421:0x03a5, B:1425:0x03bc, B:1429:0x03d3, B:1433:0x03ea, B:1437:0x0405, B:1441:0x041c, B:1445:0x0433, B:1449:0x044a, B:1453:0x0461, B:1457:0x047c, B:1461:0x0493, B:1463:0x0499, B:1465:0x049f, B:1470:0x04b2, B:1472:0x04b8, B:1473:0x04bc, B:1476:0x04d1, B:1479:0x04e4, B:1482:0x04f7, B:1485:0x050c, B:1489:0x0522, B:1493:0x0539, B:1497:0x0550, B:1501:0x0567, B:1505:0x057e, B:1509:0x0595, B:1513:0x05b1, B:1517:0x05ca, B:1521:0x05e4, B:1525:0x05fe, B:1529:0x0617, B:1533:0x062e, B:1537:0x0645, B:1541:0x065c, B:1544:0x0675, B:1546:0x067f, B:1550:0x0696, B:1554:0x06b1, B:1558:0x06cc, B:1562:0x06e7, B:1565:0x06fc, B:1395:0x0319, B:1388:0x02fe), top: B:1925:0x0157, inners: #1, #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:1400:0x0330  */
    /* JADX WARN: Removed duplicated region for block: B:1404:0x0347  */
    /* JADX WARN: Removed duplicated region for block: B:1408:0x035e  */
    /* JADX WARN: Removed duplicated region for block: B:1412:0x0375  */
    /* JADX WARN: Removed duplicated region for block: B:1416:0x038c  */
    /* JADX WARN: Removed duplicated region for block: B:1420:0x03a3  */
    /* JADX WARN: Removed duplicated region for block: B:1424:0x03ba  */
    /* JADX WARN: Removed duplicated region for block: B:1428:0x03d1  */
    /* JADX WARN: Removed duplicated region for block: B:1432:0x03e8  */
    /* JADX WARN: Removed duplicated region for block: B:1436:0x0403  */
    /* JADX WARN: Removed duplicated region for block: B:1440:0x041a  */
    /* JADX WARN: Removed duplicated region for block: B:1444:0x0431  */
    /* JADX WARN: Removed duplicated region for block: B:1448:0x0448  */
    /* JADX WARN: Removed duplicated region for block: B:1452:0x045f  */
    /* JADX WARN: Removed duplicated region for block: B:1456:0x047a  */
    /* JADX WARN: Removed duplicated region for block: B:1460:0x0491  */
    /* JADX WARN: Removed duplicated region for block: B:1470:0x04b2 A[Catch: Exception -> 0x0162, TRY_ENTER, TryCatch #9 {Exception -> 0x0162, blocks: (B:1307:0x0157, B:1313:0x0175, B:1317:0x018a, B:1321:0x019f, B:1325:0x01b9, B:1329:0x01d0, B:1332:0x01e9, B:1334:0x01f0, B:1338:0x0204, B:1340:0x020a, B:1344:0x021f, B:1350:0x0246, B:1352:0x024c, B:1354:0x0252, B:1357:0x0265, B:1359:0x026b, B:1361:0x0271, B:1365:0x0286, B:1368:0x029b, B:1370:0x02a1, B:1372:0x02ab, B:1376:0x02c0, B:1379:0x02d9, B:1381:0x02df, B:1382:0x02e8, B:1386:0x02f9, B:1390:0x0304, B:1394:0x0315, B:1397:0x031f, B:1401:0x0332, B:1405:0x0349, B:1409:0x0360, B:1413:0x0377, B:1417:0x038e, B:1421:0x03a5, B:1425:0x03bc, B:1429:0x03d3, B:1433:0x03ea, B:1437:0x0405, B:1441:0x041c, B:1445:0x0433, B:1449:0x044a, B:1453:0x0461, B:1457:0x047c, B:1461:0x0493, B:1463:0x0499, B:1465:0x049f, B:1470:0x04b2, B:1472:0x04b8, B:1473:0x04bc, B:1476:0x04d1, B:1479:0x04e4, B:1482:0x04f7, B:1485:0x050c, B:1489:0x0522, B:1493:0x0539, B:1497:0x0550, B:1501:0x0567, B:1505:0x057e, B:1509:0x0595, B:1513:0x05b1, B:1517:0x05ca, B:1521:0x05e4, B:1525:0x05fe, B:1529:0x0617, B:1533:0x062e, B:1537:0x0645, B:1541:0x065c, B:1544:0x0675, B:1546:0x067f, B:1550:0x0696, B:1554:0x06b1, B:1558:0x06cc, B:1562:0x06e7, B:1565:0x06fc, B:1395:0x0319, B:1388:0x02fe), top: B:1925:0x0157, inners: #1, #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:1476:0x04d1 A[Catch: Exception -> 0x0162, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x0162, blocks: (B:1307:0x0157, B:1313:0x0175, B:1317:0x018a, B:1321:0x019f, B:1325:0x01b9, B:1329:0x01d0, B:1332:0x01e9, B:1334:0x01f0, B:1338:0x0204, B:1340:0x020a, B:1344:0x021f, B:1350:0x0246, B:1352:0x024c, B:1354:0x0252, B:1357:0x0265, B:1359:0x026b, B:1361:0x0271, B:1365:0x0286, B:1368:0x029b, B:1370:0x02a1, B:1372:0x02ab, B:1376:0x02c0, B:1379:0x02d9, B:1381:0x02df, B:1382:0x02e8, B:1386:0x02f9, B:1390:0x0304, B:1394:0x0315, B:1397:0x031f, B:1401:0x0332, B:1405:0x0349, B:1409:0x0360, B:1413:0x0377, B:1417:0x038e, B:1421:0x03a5, B:1425:0x03bc, B:1429:0x03d3, B:1433:0x03ea, B:1437:0x0405, B:1441:0x041c, B:1445:0x0433, B:1449:0x044a, B:1453:0x0461, B:1457:0x047c, B:1461:0x0493, B:1463:0x0499, B:1465:0x049f, B:1470:0x04b2, B:1472:0x04b8, B:1473:0x04bc, B:1476:0x04d1, B:1479:0x04e4, B:1482:0x04f7, B:1485:0x050c, B:1489:0x0522, B:1493:0x0539, B:1497:0x0550, B:1501:0x0567, B:1505:0x057e, B:1509:0x0595, B:1513:0x05b1, B:1517:0x05ca, B:1521:0x05e4, B:1525:0x05fe, B:1529:0x0617, B:1533:0x062e, B:1537:0x0645, B:1541:0x065c, B:1544:0x0675, B:1546:0x067f, B:1550:0x0696, B:1554:0x06b1, B:1558:0x06cc, B:1562:0x06e7, B:1565:0x06fc, B:1395:0x0319, B:1388:0x02fe), top: B:1925:0x0157, inners: #1, #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:1479:0x04e4 A[Catch: Exception -> 0x0162, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x0162, blocks: (B:1307:0x0157, B:1313:0x0175, B:1317:0x018a, B:1321:0x019f, B:1325:0x01b9, B:1329:0x01d0, B:1332:0x01e9, B:1334:0x01f0, B:1338:0x0204, B:1340:0x020a, B:1344:0x021f, B:1350:0x0246, B:1352:0x024c, B:1354:0x0252, B:1357:0x0265, B:1359:0x026b, B:1361:0x0271, B:1365:0x0286, B:1368:0x029b, B:1370:0x02a1, B:1372:0x02ab, B:1376:0x02c0, B:1379:0x02d9, B:1381:0x02df, B:1382:0x02e8, B:1386:0x02f9, B:1390:0x0304, B:1394:0x0315, B:1397:0x031f, B:1401:0x0332, B:1405:0x0349, B:1409:0x0360, B:1413:0x0377, B:1417:0x038e, B:1421:0x03a5, B:1425:0x03bc, B:1429:0x03d3, B:1433:0x03ea, B:1437:0x0405, B:1441:0x041c, B:1445:0x0433, B:1449:0x044a, B:1453:0x0461, B:1457:0x047c, B:1461:0x0493, B:1463:0x0499, B:1465:0x049f, B:1470:0x04b2, B:1472:0x04b8, B:1473:0x04bc, B:1476:0x04d1, B:1479:0x04e4, B:1482:0x04f7, B:1485:0x050c, B:1489:0x0522, B:1493:0x0539, B:1497:0x0550, B:1501:0x0567, B:1505:0x057e, B:1509:0x0595, B:1513:0x05b1, B:1517:0x05ca, B:1521:0x05e4, B:1525:0x05fe, B:1529:0x0617, B:1533:0x062e, B:1537:0x0645, B:1541:0x065c, B:1544:0x0675, B:1546:0x067f, B:1550:0x0696, B:1554:0x06b1, B:1558:0x06cc, B:1562:0x06e7, B:1565:0x06fc, B:1395:0x0319, B:1388:0x02fe), top: B:1925:0x0157, inners: #1, #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:1482:0x04f7 A[Catch: Exception -> 0x0162, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x0162, blocks: (B:1307:0x0157, B:1313:0x0175, B:1317:0x018a, B:1321:0x019f, B:1325:0x01b9, B:1329:0x01d0, B:1332:0x01e9, B:1334:0x01f0, B:1338:0x0204, B:1340:0x020a, B:1344:0x021f, B:1350:0x0246, B:1352:0x024c, B:1354:0x0252, B:1357:0x0265, B:1359:0x026b, B:1361:0x0271, B:1365:0x0286, B:1368:0x029b, B:1370:0x02a1, B:1372:0x02ab, B:1376:0x02c0, B:1379:0x02d9, B:1381:0x02df, B:1382:0x02e8, B:1386:0x02f9, B:1390:0x0304, B:1394:0x0315, B:1397:0x031f, B:1401:0x0332, B:1405:0x0349, B:1409:0x0360, B:1413:0x0377, B:1417:0x038e, B:1421:0x03a5, B:1425:0x03bc, B:1429:0x03d3, B:1433:0x03ea, B:1437:0x0405, B:1441:0x041c, B:1445:0x0433, B:1449:0x044a, B:1453:0x0461, B:1457:0x047c, B:1461:0x0493, B:1463:0x0499, B:1465:0x049f, B:1470:0x04b2, B:1472:0x04b8, B:1473:0x04bc, B:1476:0x04d1, B:1479:0x04e4, B:1482:0x04f7, B:1485:0x050c, B:1489:0x0522, B:1493:0x0539, B:1497:0x0550, B:1501:0x0567, B:1505:0x057e, B:1509:0x0595, B:1513:0x05b1, B:1517:0x05ca, B:1521:0x05e4, B:1525:0x05fe, B:1529:0x0617, B:1533:0x062e, B:1537:0x0645, B:1541:0x065c, B:1544:0x0675, B:1546:0x067f, B:1550:0x0696, B:1554:0x06b1, B:1558:0x06cc, B:1562:0x06e7, B:1565:0x06fc, B:1395:0x0319, B:1388:0x02fe), top: B:1925:0x0157, inners: #1, #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:1485:0x050c A[Catch: Exception -> 0x0162, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x0162, blocks: (B:1307:0x0157, B:1313:0x0175, B:1317:0x018a, B:1321:0x019f, B:1325:0x01b9, B:1329:0x01d0, B:1332:0x01e9, B:1334:0x01f0, B:1338:0x0204, B:1340:0x020a, B:1344:0x021f, B:1350:0x0246, B:1352:0x024c, B:1354:0x0252, B:1357:0x0265, B:1359:0x026b, B:1361:0x0271, B:1365:0x0286, B:1368:0x029b, B:1370:0x02a1, B:1372:0x02ab, B:1376:0x02c0, B:1379:0x02d9, B:1381:0x02df, B:1382:0x02e8, B:1386:0x02f9, B:1390:0x0304, B:1394:0x0315, B:1397:0x031f, B:1401:0x0332, B:1405:0x0349, B:1409:0x0360, B:1413:0x0377, B:1417:0x038e, B:1421:0x03a5, B:1425:0x03bc, B:1429:0x03d3, B:1433:0x03ea, B:1437:0x0405, B:1441:0x041c, B:1445:0x0433, B:1449:0x044a, B:1453:0x0461, B:1457:0x047c, B:1461:0x0493, B:1463:0x0499, B:1465:0x049f, B:1470:0x04b2, B:1472:0x04b8, B:1473:0x04bc, B:1476:0x04d1, B:1479:0x04e4, B:1482:0x04f7, B:1485:0x050c, B:1489:0x0522, B:1493:0x0539, B:1497:0x0550, B:1501:0x0567, B:1505:0x057e, B:1509:0x0595, B:1513:0x05b1, B:1517:0x05ca, B:1521:0x05e4, B:1525:0x05fe, B:1529:0x0617, B:1533:0x062e, B:1537:0x0645, B:1541:0x065c, B:1544:0x0675, B:1546:0x067f, B:1550:0x0696, B:1554:0x06b1, B:1558:0x06cc, B:1562:0x06e7, B:1565:0x06fc, B:1395:0x0319, B:1388:0x02fe), top: B:1925:0x0157, inners: #1, #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:1488:0x0520  */
    /* JADX WARN: Removed duplicated region for block: B:1492:0x0537  */
    /* JADX WARN: Removed duplicated region for block: B:1496:0x054e  */
    /* JADX WARN: Removed duplicated region for block: B:1500:0x0565  */
    /* JADX WARN: Removed duplicated region for block: B:1504:0x057c  */
    /* JADX WARN: Removed duplicated region for block: B:1508:0x0593  */
    /* JADX WARN: Removed duplicated region for block: B:1512:0x05ae  */
    /* JADX WARN: Removed duplicated region for block: B:1516:0x05c8  */
    /* JADX WARN: Removed duplicated region for block: B:1520:0x05e1  */
    /* JADX WARN: Removed duplicated region for block: B:1524:0x05fb  */
    /* JADX WARN: Removed duplicated region for block: B:1528:0x0615  */
    /* JADX WARN: Removed duplicated region for block: B:1532:0x062c  */
    /* JADX WARN: Removed duplicated region for block: B:1536:0x0643  */
    /* JADX WARN: Removed duplicated region for block: B:1540:0x065a  */
    /* JADX WARN: Removed duplicated region for block: B:1544:0x0675 A[Catch: Exception -> 0x0162, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x0162, blocks: (B:1307:0x0157, B:1313:0x0175, B:1317:0x018a, B:1321:0x019f, B:1325:0x01b9, B:1329:0x01d0, B:1332:0x01e9, B:1334:0x01f0, B:1338:0x0204, B:1340:0x020a, B:1344:0x021f, B:1350:0x0246, B:1352:0x024c, B:1354:0x0252, B:1357:0x0265, B:1359:0x026b, B:1361:0x0271, B:1365:0x0286, B:1368:0x029b, B:1370:0x02a1, B:1372:0x02ab, B:1376:0x02c0, B:1379:0x02d9, B:1381:0x02df, B:1382:0x02e8, B:1386:0x02f9, B:1390:0x0304, B:1394:0x0315, B:1397:0x031f, B:1401:0x0332, B:1405:0x0349, B:1409:0x0360, B:1413:0x0377, B:1417:0x038e, B:1421:0x03a5, B:1425:0x03bc, B:1429:0x03d3, B:1433:0x03ea, B:1437:0x0405, B:1441:0x041c, B:1445:0x0433, B:1449:0x044a, B:1453:0x0461, B:1457:0x047c, B:1461:0x0493, B:1463:0x0499, B:1465:0x049f, B:1470:0x04b2, B:1472:0x04b8, B:1473:0x04bc, B:1476:0x04d1, B:1479:0x04e4, B:1482:0x04f7, B:1485:0x050c, B:1489:0x0522, B:1493:0x0539, B:1497:0x0550, B:1501:0x0567, B:1505:0x057e, B:1509:0x0595, B:1513:0x05b1, B:1517:0x05ca, B:1521:0x05e4, B:1525:0x05fe, B:1529:0x0617, B:1533:0x062e, B:1537:0x0645, B:1541:0x065c, B:1544:0x0675, B:1546:0x067f, B:1550:0x0696, B:1554:0x06b1, B:1558:0x06cc, B:1562:0x06e7, B:1565:0x06fc, B:1395:0x0319, B:1388:0x02fe), top: B:1925:0x0157, inners: #1, #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:1549:0x0694  */
    /* JADX WARN: Removed duplicated region for block: B:1553:0x06af  */
    /* JADX WARN: Removed duplicated region for block: B:1557:0x06ca  */
    /* JADX WARN: Removed duplicated region for block: B:1561:0x06e5  */
    /* JADX WARN: Removed duplicated region for block: B:1565:0x06fc A[Catch: Exception -> 0x0162, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x0162, blocks: (B:1307:0x0157, B:1313:0x0175, B:1317:0x018a, B:1321:0x019f, B:1325:0x01b9, B:1329:0x01d0, B:1332:0x01e9, B:1334:0x01f0, B:1338:0x0204, B:1340:0x020a, B:1344:0x021f, B:1350:0x0246, B:1352:0x024c, B:1354:0x0252, B:1357:0x0265, B:1359:0x026b, B:1361:0x0271, B:1365:0x0286, B:1368:0x029b, B:1370:0x02a1, B:1372:0x02ab, B:1376:0x02c0, B:1379:0x02d9, B:1381:0x02df, B:1382:0x02e8, B:1386:0x02f9, B:1390:0x0304, B:1394:0x0315, B:1397:0x031f, B:1401:0x0332, B:1405:0x0349, B:1409:0x0360, B:1413:0x0377, B:1417:0x038e, B:1421:0x03a5, B:1425:0x03bc, B:1429:0x03d3, B:1433:0x03ea, B:1437:0x0405, B:1441:0x041c, B:1445:0x0433, B:1449:0x044a, B:1453:0x0461, B:1457:0x047c, B:1461:0x0493, B:1463:0x0499, B:1465:0x049f, B:1470:0x04b2, B:1472:0x04b8, B:1473:0x04bc, B:1476:0x04d1, B:1479:0x04e4, B:1482:0x04f7, B:1485:0x050c, B:1489:0x0522, B:1493:0x0539, B:1497:0x0550, B:1501:0x0567, B:1505:0x057e, B:1509:0x0595, B:1513:0x05b1, B:1517:0x05ca, B:1521:0x05e4, B:1525:0x05fe, B:1529:0x0617, B:1533:0x062e, B:1537:0x0645, B:1541:0x065c, B:1544:0x0675, B:1546:0x067f, B:1550:0x0696, B:1554:0x06b1, B:1558:0x06cc, B:1562:0x06e7, B:1565:0x06fc, B:1395:0x0319, B:1388:0x02fe), top: B:1925:0x0157, inners: #1, #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:1569:0x0727  */
    /* JADX WARN: Removed duplicated region for block: B:1572:0x0738 A[Catch: Exception -> 0x0742, TRY_ENTER, TRY_LEAVE, TryCatch #8 {Exception -> 0x0742, blocks: (B:1567:0x071b, B:1572:0x0738, B:1579:0x0756, B:1582:0x076e, B:1586:0x0787, B:1590:0x07a2, B:1594:0x07bd, B:1606:0x0804, B:1609:0x0825, B:1613:0x083d, B:1617:0x0857, B:1621:0x086c, B:1625:0x0883, B:1629:0x089a, B:1633:0x08af, B:1637:0x08c5, B:1641:0x08db, B:1645:0x08f0, B:1649:0x0908, B:1653:0x091f, B:1657:0x093b, B:1660:0x0952, B:1663:0x096e, B:1667:0x098c, B:1671:0x09a3, B:1675:0x09ba, B:1679:0x09d3, B:1683:0x09ec, B:1687:0x0a03, B:1691:0x0a1a, B:1694:0x0a2f, B:1696:0x0a35, B:1700:0x0a51, B:1704:0x0a6a, B:1708:0x0a8f, B:1712:0x0aa8, B:1716:0x0ac1, B:1720:0x0ada, B:1724:0x0af3, B:1728:0x0b0c, B:1732:0x0b25, B:1736:0x0b3e, B:1740:0x0b57, B:1743:0x0b6c, B:1745:0x0b72, B:1747:0x0b7c, B:1751:0x0b91, B:1753:0x0b97, B:1755:0x0ba1, B:1759:0x0bb4, B:1766:0x0be0, B:1770:0x0be9, B:1774:0x0bfc, B:1778:0x0c22, B:1782:0x0c48, B:1786:0x0c5f, B:1790:0x0c76, B:1794:0x0c9c, B:1798:0x0cb3, B:1802:0x0cca, B:1806:0x0ce1, B:1809:0x0cf6, B:1813:0x0d18, B:1817:0x0d2f, B:1821:0x0d55, B:1825:0x0d5e, B:1829:0x0d71, B:1833:0x0d7a, B:1837:0x0d8d, B:1841:0x0d96, B:1845:0x0da9, B:1849:0x0db1, B:1853:0x0dc4, B:1857:0x0dea, B:1861:0x0e01, B:1865:0x0e18, B:1868:0x0e2e, B:1872:0x0e43, B:1876:0x0e5a, B:1880:0x0e71, B:1884:0x0e88, B:1888:0x0e9f), top: B:1923:0x071b }] */
    /* JADX WARN: Removed duplicated region for block: B:1579:0x0756 A[Catch: Exception -> 0x0742, TRY_ENTER, TRY_LEAVE, TryCatch #8 {Exception -> 0x0742, blocks: (B:1567:0x071b, B:1572:0x0738, B:1579:0x0756, B:1582:0x076e, B:1586:0x0787, B:1590:0x07a2, B:1594:0x07bd, B:1606:0x0804, B:1609:0x0825, B:1613:0x083d, B:1617:0x0857, B:1621:0x086c, B:1625:0x0883, B:1629:0x089a, B:1633:0x08af, B:1637:0x08c5, B:1641:0x08db, B:1645:0x08f0, B:1649:0x0908, B:1653:0x091f, B:1657:0x093b, B:1660:0x0952, B:1663:0x096e, B:1667:0x098c, B:1671:0x09a3, B:1675:0x09ba, B:1679:0x09d3, B:1683:0x09ec, B:1687:0x0a03, B:1691:0x0a1a, B:1694:0x0a2f, B:1696:0x0a35, B:1700:0x0a51, B:1704:0x0a6a, B:1708:0x0a8f, B:1712:0x0aa8, B:1716:0x0ac1, B:1720:0x0ada, B:1724:0x0af3, B:1728:0x0b0c, B:1732:0x0b25, B:1736:0x0b3e, B:1740:0x0b57, B:1743:0x0b6c, B:1745:0x0b72, B:1747:0x0b7c, B:1751:0x0b91, B:1753:0x0b97, B:1755:0x0ba1, B:1759:0x0bb4, B:1766:0x0be0, B:1770:0x0be9, B:1774:0x0bfc, B:1778:0x0c22, B:1782:0x0c48, B:1786:0x0c5f, B:1790:0x0c76, B:1794:0x0c9c, B:1798:0x0cb3, B:1802:0x0cca, B:1806:0x0ce1, B:1809:0x0cf6, B:1813:0x0d18, B:1817:0x0d2f, B:1821:0x0d55, B:1825:0x0d5e, B:1829:0x0d71, B:1833:0x0d7a, B:1837:0x0d8d, B:1841:0x0d96, B:1845:0x0da9, B:1849:0x0db1, B:1853:0x0dc4, B:1857:0x0dea, B:1861:0x0e01, B:1865:0x0e18, B:1868:0x0e2e, B:1872:0x0e43, B:1876:0x0e5a, B:1880:0x0e71, B:1884:0x0e88, B:1888:0x0e9f), top: B:1923:0x071b }] */
    /* JADX WARN: Removed duplicated region for block: B:1582:0x076e A[Catch: Exception -> 0x0742, TRY_ENTER, TRY_LEAVE, TryCatch #8 {Exception -> 0x0742, blocks: (B:1567:0x071b, B:1572:0x0738, B:1579:0x0756, B:1582:0x076e, B:1586:0x0787, B:1590:0x07a2, B:1594:0x07bd, B:1606:0x0804, B:1609:0x0825, B:1613:0x083d, B:1617:0x0857, B:1621:0x086c, B:1625:0x0883, B:1629:0x089a, B:1633:0x08af, B:1637:0x08c5, B:1641:0x08db, B:1645:0x08f0, B:1649:0x0908, B:1653:0x091f, B:1657:0x093b, B:1660:0x0952, B:1663:0x096e, B:1667:0x098c, B:1671:0x09a3, B:1675:0x09ba, B:1679:0x09d3, B:1683:0x09ec, B:1687:0x0a03, B:1691:0x0a1a, B:1694:0x0a2f, B:1696:0x0a35, B:1700:0x0a51, B:1704:0x0a6a, B:1708:0x0a8f, B:1712:0x0aa8, B:1716:0x0ac1, B:1720:0x0ada, B:1724:0x0af3, B:1728:0x0b0c, B:1732:0x0b25, B:1736:0x0b3e, B:1740:0x0b57, B:1743:0x0b6c, B:1745:0x0b72, B:1747:0x0b7c, B:1751:0x0b91, B:1753:0x0b97, B:1755:0x0ba1, B:1759:0x0bb4, B:1766:0x0be0, B:1770:0x0be9, B:1774:0x0bfc, B:1778:0x0c22, B:1782:0x0c48, B:1786:0x0c5f, B:1790:0x0c76, B:1794:0x0c9c, B:1798:0x0cb3, B:1802:0x0cca, B:1806:0x0ce1, B:1809:0x0cf6, B:1813:0x0d18, B:1817:0x0d2f, B:1821:0x0d55, B:1825:0x0d5e, B:1829:0x0d71, B:1833:0x0d7a, B:1837:0x0d8d, B:1841:0x0d96, B:1845:0x0da9, B:1849:0x0db1, B:1853:0x0dc4, B:1857:0x0dea, B:1861:0x0e01, B:1865:0x0e18, B:1868:0x0e2e, B:1872:0x0e43, B:1876:0x0e5a, B:1880:0x0e71, B:1884:0x0e88, B:1888:0x0e9f), top: B:1923:0x071b }] */
    /* JADX WARN: Removed duplicated region for block: B:1585:0x0785  */
    /* JADX WARN: Removed duplicated region for block: B:1589:0x07a0  */
    /* JADX WARN: Removed duplicated region for block: B:1593:0x07bb  */
    /* JADX WARN: Removed duplicated region for block: B:1606:0x0804 A[Catch: Exception -> 0x0742, TRY_ENTER, TRY_LEAVE, TryCatch #8 {Exception -> 0x0742, blocks: (B:1567:0x071b, B:1572:0x0738, B:1579:0x0756, B:1582:0x076e, B:1586:0x0787, B:1590:0x07a2, B:1594:0x07bd, B:1606:0x0804, B:1609:0x0825, B:1613:0x083d, B:1617:0x0857, B:1621:0x086c, B:1625:0x0883, B:1629:0x089a, B:1633:0x08af, B:1637:0x08c5, B:1641:0x08db, B:1645:0x08f0, B:1649:0x0908, B:1653:0x091f, B:1657:0x093b, B:1660:0x0952, B:1663:0x096e, B:1667:0x098c, B:1671:0x09a3, B:1675:0x09ba, B:1679:0x09d3, B:1683:0x09ec, B:1687:0x0a03, B:1691:0x0a1a, B:1694:0x0a2f, B:1696:0x0a35, B:1700:0x0a51, B:1704:0x0a6a, B:1708:0x0a8f, B:1712:0x0aa8, B:1716:0x0ac1, B:1720:0x0ada, B:1724:0x0af3, B:1728:0x0b0c, B:1732:0x0b25, B:1736:0x0b3e, B:1740:0x0b57, B:1743:0x0b6c, B:1745:0x0b72, B:1747:0x0b7c, B:1751:0x0b91, B:1753:0x0b97, B:1755:0x0ba1, B:1759:0x0bb4, B:1766:0x0be0, B:1770:0x0be9, B:1774:0x0bfc, B:1778:0x0c22, B:1782:0x0c48, B:1786:0x0c5f, B:1790:0x0c76, B:1794:0x0c9c, B:1798:0x0cb3, B:1802:0x0cca, B:1806:0x0ce1, B:1809:0x0cf6, B:1813:0x0d18, B:1817:0x0d2f, B:1821:0x0d55, B:1825:0x0d5e, B:1829:0x0d71, B:1833:0x0d7a, B:1837:0x0d8d, B:1841:0x0d96, B:1845:0x0da9, B:1849:0x0db1, B:1853:0x0dc4, B:1857:0x0dea, B:1861:0x0e01, B:1865:0x0e18, B:1868:0x0e2e, B:1872:0x0e43, B:1876:0x0e5a, B:1880:0x0e71, B:1884:0x0e88, B:1888:0x0e9f), top: B:1923:0x071b }] */
    /* JADX WARN: Removed duplicated region for block: B:1609:0x0825 A[Catch: Exception -> 0x0742, TRY_ENTER, TRY_LEAVE, TryCatch #8 {Exception -> 0x0742, blocks: (B:1567:0x071b, B:1572:0x0738, B:1579:0x0756, B:1582:0x076e, B:1586:0x0787, B:1590:0x07a2, B:1594:0x07bd, B:1606:0x0804, B:1609:0x0825, B:1613:0x083d, B:1617:0x0857, B:1621:0x086c, B:1625:0x0883, B:1629:0x089a, B:1633:0x08af, B:1637:0x08c5, B:1641:0x08db, B:1645:0x08f0, B:1649:0x0908, B:1653:0x091f, B:1657:0x093b, B:1660:0x0952, B:1663:0x096e, B:1667:0x098c, B:1671:0x09a3, B:1675:0x09ba, B:1679:0x09d3, B:1683:0x09ec, B:1687:0x0a03, B:1691:0x0a1a, B:1694:0x0a2f, B:1696:0x0a35, B:1700:0x0a51, B:1704:0x0a6a, B:1708:0x0a8f, B:1712:0x0aa8, B:1716:0x0ac1, B:1720:0x0ada, B:1724:0x0af3, B:1728:0x0b0c, B:1732:0x0b25, B:1736:0x0b3e, B:1740:0x0b57, B:1743:0x0b6c, B:1745:0x0b72, B:1747:0x0b7c, B:1751:0x0b91, B:1753:0x0b97, B:1755:0x0ba1, B:1759:0x0bb4, B:1766:0x0be0, B:1770:0x0be9, B:1774:0x0bfc, B:1778:0x0c22, B:1782:0x0c48, B:1786:0x0c5f, B:1790:0x0c76, B:1794:0x0c9c, B:1798:0x0cb3, B:1802:0x0cca, B:1806:0x0ce1, B:1809:0x0cf6, B:1813:0x0d18, B:1817:0x0d2f, B:1821:0x0d55, B:1825:0x0d5e, B:1829:0x0d71, B:1833:0x0d7a, B:1837:0x0d8d, B:1841:0x0d96, B:1845:0x0da9, B:1849:0x0db1, B:1853:0x0dc4, B:1857:0x0dea, B:1861:0x0e01, B:1865:0x0e18, B:1868:0x0e2e, B:1872:0x0e43, B:1876:0x0e5a, B:1880:0x0e71, B:1884:0x0e88, B:1888:0x0e9f), top: B:1923:0x071b }] */
    /* JADX WARN: Removed duplicated region for block: B:1613:0x083d A[Catch: Exception -> 0x0742, TRY_ENTER, TRY_LEAVE, TryCatch #8 {Exception -> 0x0742, blocks: (B:1567:0x071b, B:1572:0x0738, B:1579:0x0756, B:1582:0x076e, B:1586:0x0787, B:1590:0x07a2, B:1594:0x07bd, B:1606:0x0804, B:1609:0x0825, B:1613:0x083d, B:1617:0x0857, B:1621:0x086c, B:1625:0x0883, B:1629:0x089a, B:1633:0x08af, B:1637:0x08c5, B:1641:0x08db, B:1645:0x08f0, B:1649:0x0908, B:1653:0x091f, B:1657:0x093b, B:1660:0x0952, B:1663:0x096e, B:1667:0x098c, B:1671:0x09a3, B:1675:0x09ba, B:1679:0x09d3, B:1683:0x09ec, B:1687:0x0a03, B:1691:0x0a1a, B:1694:0x0a2f, B:1696:0x0a35, B:1700:0x0a51, B:1704:0x0a6a, B:1708:0x0a8f, B:1712:0x0aa8, B:1716:0x0ac1, B:1720:0x0ada, B:1724:0x0af3, B:1728:0x0b0c, B:1732:0x0b25, B:1736:0x0b3e, B:1740:0x0b57, B:1743:0x0b6c, B:1745:0x0b72, B:1747:0x0b7c, B:1751:0x0b91, B:1753:0x0b97, B:1755:0x0ba1, B:1759:0x0bb4, B:1766:0x0be0, B:1770:0x0be9, B:1774:0x0bfc, B:1778:0x0c22, B:1782:0x0c48, B:1786:0x0c5f, B:1790:0x0c76, B:1794:0x0c9c, B:1798:0x0cb3, B:1802:0x0cca, B:1806:0x0ce1, B:1809:0x0cf6, B:1813:0x0d18, B:1817:0x0d2f, B:1821:0x0d55, B:1825:0x0d5e, B:1829:0x0d71, B:1833:0x0d7a, B:1837:0x0d8d, B:1841:0x0d96, B:1845:0x0da9, B:1849:0x0db1, B:1853:0x0dc4, B:1857:0x0dea, B:1861:0x0e01, B:1865:0x0e18, B:1868:0x0e2e, B:1872:0x0e43, B:1876:0x0e5a, B:1880:0x0e71, B:1884:0x0e88, B:1888:0x0e9f), top: B:1923:0x071b }] */
    /* JADX WARN: Removed duplicated region for block: B:1616:0x0855  */
    /* JADX WARN: Removed duplicated region for block: B:1620:0x086a  */
    /* JADX WARN: Removed duplicated region for block: B:1624:0x0881  */
    /* JADX WARN: Removed duplicated region for block: B:1628:0x0898  */
    /* JADX WARN: Removed duplicated region for block: B:1633:0x08af A[Catch: Exception -> 0x0742, TRY_ENTER, TRY_LEAVE, TryCatch #8 {Exception -> 0x0742, blocks: (B:1567:0x071b, B:1572:0x0738, B:1579:0x0756, B:1582:0x076e, B:1586:0x0787, B:1590:0x07a2, B:1594:0x07bd, B:1606:0x0804, B:1609:0x0825, B:1613:0x083d, B:1617:0x0857, B:1621:0x086c, B:1625:0x0883, B:1629:0x089a, B:1633:0x08af, B:1637:0x08c5, B:1641:0x08db, B:1645:0x08f0, B:1649:0x0908, B:1653:0x091f, B:1657:0x093b, B:1660:0x0952, B:1663:0x096e, B:1667:0x098c, B:1671:0x09a3, B:1675:0x09ba, B:1679:0x09d3, B:1683:0x09ec, B:1687:0x0a03, B:1691:0x0a1a, B:1694:0x0a2f, B:1696:0x0a35, B:1700:0x0a51, B:1704:0x0a6a, B:1708:0x0a8f, B:1712:0x0aa8, B:1716:0x0ac1, B:1720:0x0ada, B:1724:0x0af3, B:1728:0x0b0c, B:1732:0x0b25, B:1736:0x0b3e, B:1740:0x0b57, B:1743:0x0b6c, B:1745:0x0b72, B:1747:0x0b7c, B:1751:0x0b91, B:1753:0x0b97, B:1755:0x0ba1, B:1759:0x0bb4, B:1766:0x0be0, B:1770:0x0be9, B:1774:0x0bfc, B:1778:0x0c22, B:1782:0x0c48, B:1786:0x0c5f, B:1790:0x0c76, B:1794:0x0c9c, B:1798:0x0cb3, B:1802:0x0cca, B:1806:0x0ce1, B:1809:0x0cf6, B:1813:0x0d18, B:1817:0x0d2f, B:1821:0x0d55, B:1825:0x0d5e, B:1829:0x0d71, B:1833:0x0d7a, B:1837:0x0d8d, B:1841:0x0d96, B:1845:0x0da9, B:1849:0x0db1, B:1853:0x0dc4, B:1857:0x0dea, B:1861:0x0e01, B:1865:0x0e18, B:1868:0x0e2e, B:1872:0x0e43, B:1876:0x0e5a, B:1880:0x0e71, B:1884:0x0e88, B:1888:0x0e9f), top: B:1923:0x071b }] */
    /* JADX WARN: Removed duplicated region for block: B:1637:0x08c5 A[Catch: Exception -> 0x0742, TRY_ENTER, TRY_LEAVE, TryCatch #8 {Exception -> 0x0742, blocks: (B:1567:0x071b, B:1572:0x0738, B:1579:0x0756, B:1582:0x076e, B:1586:0x0787, B:1590:0x07a2, B:1594:0x07bd, B:1606:0x0804, B:1609:0x0825, B:1613:0x083d, B:1617:0x0857, B:1621:0x086c, B:1625:0x0883, B:1629:0x089a, B:1633:0x08af, B:1637:0x08c5, B:1641:0x08db, B:1645:0x08f0, B:1649:0x0908, B:1653:0x091f, B:1657:0x093b, B:1660:0x0952, B:1663:0x096e, B:1667:0x098c, B:1671:0x09a3, B:1675:0x09ba, B:1679:0x09d3, B:1683:0x09ec, B:1687:0x0a03, B:1691:0x0a1a, B:1694:0x0a2f, B:1696:0x0a35, B:1700:0x0a51, B:1704:0x0a6a, B:1708:0x0a8f, B:1712:0x0aa8, B:1716:0x0ac1, B:1720:0x0ada, B:1724:0x0af3, B:1728:0x0b0c, B:1732:0x0b25, B:1736:0x0b3e, B:1740:0x0b57, B:1743:0x0b6c, B:1745:0x0b72, B:1747:0x0b7c, B:1751:0x0b91, B:1753:0x0b97, B:1755:0x0ba1, B:1759:0x0bb4, B:1766:0x0be0, B:1770:0x0be9, B:1774:0x0bfc, B:1778:0x0c22, B:1782:0x0c48, B:1786:0x0c5f, B:1790:0x0c76, B:1794:0x0c9c, B:1798:0x0cb3, B:1802:0x0cca, B:1806:0x0ce1, B:1809:0x0cf6, B:1813:0x0d18, B:1817:0x0d2f, B:1821:0x0d55, B:1825:0x0d5e, B:1829:0x0d71, B:1833:0x0d7a, B:1837:0x0d8d, B:1841:0x0d96, B:1845:0x0da9, B:1849:0x0db1, B:1853:0x0dc4, B:1857:0x0dea, B:1861:0x0e01, B:1865:0x0e18, B:1868:0x0e2e, B:1872:0x0e43, B:1876:0x0e5a, B:1880:0x0e71, B:1884:0x0e88, B:1888:0x0e9f), top: B:1923:0x071b }] */
    /* JADX WARN: Removed duplicated region for block: B:1640:0x08d9  */
    /* JADX WARN: Removed duplicated region for block: B:1645:0x08f0 A[Catch: Exception -> 0x0742, TRY_ENTER, TRY_LEAVE, TryCatch #8 {Exception -> 0x0742, blocks: (B:1567:0x071b, B:1572:0x0738, B:1579:0x0756, B:1582:0x076e, B:1586:0x0787, B:1590:0x07a2, B:1594:0x07bd, B:1606:0x0804, B:1609:0x0825, B:1613:0x083d, B:1617:0x0857, B:1621:0x086c, B:1625:0x0883, B:1629:0x089a, B:1633:0x08af, B:1637:0x08c5, B:1641:0x08db, B:1645:0x08f0, B:1649:0x0908, B:1653:0x091f, B:1657:0x093b, B:1660:0x0952, B:1663:0x096e, B:1667:0x098c, B:1671:0x09a3, B:1675:0x09ba, B:1679:0x09d3, B:1683:0x09ec, B:1687:0x0a03, B:1691:0x0a1a, B:1694:0x0a2f, B:1696:0x0a35, B:1700:0x0a51, B:1704:0x0a6a, B:1708:0x0a8f, B:1712:0x0aa8, B:1716:0x0ac1, B:1720:0x0ada, B:1724:0x0af3, B:1728:0x0b0c, B:1732:0x0b25, B:1736:0x0b3e, B:1740:0x0b57, B:1743:0x0b6c, B:1745:0x0b72, B:1747:0x0b7c, B:1751:0x0b91, B:1753:0x0b97, B:1755:0x0ba1, B:1759:0x0bb4, B:1766:0x0be0, B:1770:0x0be9, B:1774:0x0bfc, B:1778:0x0c22, B:1782:0x0c48, B:1786:0x0c5f, B:1790:0x0c76, B:1794:0x0c9c, B:1798:0x0cb3, B:1802:0x0cca, B:1806:0x0ce1, B:1809:0x0cf6, B:1813:0x0d18, B:1817:0x0d2f, B:1821:0x0d55, B:1825:0x0d5e, B:1829:0x0d71, B:1833:0x0d7a, B:1837:0x0d8d, B:1841:0x0d96, B:1845:0x0da9, B:1849:0x0db1, B:1853:0x0dc4, B:1857:0x0dea, B:1861:0x0e01, B:1865:0x0e18, B:1868:0x0e2e, B:1872:0x0e43, B:1876:0x0e5a, B:1880:0x0e71, B:1884:0x0e88, B:1888:0x0e9f), top: B:1923:0x071b }] */
    /* JADX WARN: Removed duplicated region for block: B:1648:0x0906  */
    /* JADX WARN: Removed duplicated region for block: B:1652:0x091d  */
    /* JADX WARN: Removed duplicated region for block: B:1656:0x0939  */
    /* JADX WARN: Removed duplicated region for block: B:1660:0x0952 A[Catch: Exception -> 0x0742, TRY_ENTER, TRY_LEAVE, TryCatch #8 {Exception -> 0x0742, blocks: (B:1567:0x071b, B:1572:0x0738, B:1579:0x0756, B:1582:0x076e, B:1586:0x0787, B:1590:0x07a2, B:1594:0x07bd, B:1606:0x0804, B:1609:0x0825, B:1613:0x083d, B:1617:0x0857, B:1621:0x086c, B:1625:0x0883, B:1629:0x089a, B:1633:0x08af, B:1637:0x08c5, B:1641:0x08db, B:1645:0x08f0, B:1649:0x0908, B:1653:0x091f, B:1657:0x093b, B:1660:0x0952, B:1663:0x096e, B:1667:0x098c, B:1671:0x09a3, B:1675:0x09ba, B:1679:0x09d3, B:1683:0x09ec, B:1687:0x0a03, B:1691:0x0a1a, B:1694:0x0a2f, B:1696:0x0a35, B:1700:0x0a51, B:1704:0x0a6a, B:1708:0x0a8f, B:1712:0x0aa8, B:1716:0x0ac1, B:1720:0x0ada, B:1724:0x0af3, B:1728:0x0b0c, B:1732:0x0b25, B:1736:0x0b3e, B:1740:0x0b57, B:1743:0x0b6c, B:1745:0x0b72, B:1747:0x0b7c, B:1751:0x0b91, B:1753:0x0b97, B:1755:0x0ba1, B:1759:0x0bb4, B:1766:0x0be0, B:1770:0x0be9, B:1774:0x0bfc, B:1778:0x0c22, B:1782:0x0c48, B:1786:0x0c5f, B:1790:0x0c76, B:1794:0x0c9c, B:1798:0x0cb3, B:1802:0x0cca, B:1806:0x0ce1, B:1809:0x0cf6, B:1813:0x0d18, B:1817:0x0d2f, B:1821:0x0d55, B:1825:0x0d5e, B:1829:0x0d71, B:1833:0x0d7a, B:1837:0x0d8d, B:1841:0x0d96, B:1845:0x0da9, B:1849:0x0db1, B:1853:0x0dc4, B:1857:0x0dea, B:1861:0x0e01, B:1865:0x0e18, B:1868:0x0e2e, B:1872:0x0e43, B:1876:0x0e5a, B:1880:0x0e71, B:1884:0x0e88, B:1888:0x0e9f), top: B:1923:0x071b }] */
    /* JADX WARN: Removed duplicated region for block: B:1663:0x096e A[Catch: Exception -> 0x0742, TRY_ENTER, TRY_LEAVE, TryCatch #8 {Exception -> 0x0742, blocks: (B:1567:0x071b, B:1572:0x0738, B:1579:0x0756, B:1582:0x076e, B:1586:0x0787, B:1590:0x07a2, B:1594:0x07bd, B:1606:0x0804, B:1609:0x0825, B:1613:0x083d, B:1617:0x0857, B:1621:0x086c, B:1625:0x0883, B:1629:0x089a, B:1633:0x08af, B:1637:0x08c5, B:1641:0x08db, B:1645:0x08f0, B:1649:0x0908, B:1653:0x091f, B:1657:0x093b, B:1660:0x0952, B:1663:0x096e, B:1667:0x098c, B:1671:0x09a3, B:1675:0x09ba, B:1679:0x09d3, B:1683:0x09ec, B:1687:0x0a03, B:1691:0x0a1a, B:1694:0x0a2f, B:1696:0x0a35, B:1700:0x0a51, B:1704:0x0a6a, B:1708:0x0a8f, B:1712:0x0aa8, B:1716:0x0ac1, B:1720:0x0ada, B:1724:0x0af3, B:1728:0x0b0c, B:1732:0x0b25, B:1736:0x0b3e, B:1740:0x0b57, B:1743:0x0b6c, B:1745:0x0b72, B:1747:0x0b7c, B:1751:0x0b91, B:1753:0x0b97, B:1755:0x0ba1, B:1759:0x0bb4, B:1766:0x0be0, B:1770:0x0be9, B:1774:0x0bfc, B:1778:0x0c22, B:1782:0x0c48, B:1786:0x0c5f, B:1790:0x0c76, B:1794:0x0c9c, B:1798:0x0cb3, B:1802:0x0cca, B:1806:0x0ce1, B:1809:0x0cf6, B:1813:0x0d18, B:1817:0x0d2f, B:1821:0x0d55, B:1825:0x0d5e, B:1829:0x0d71, B:1833:0x0d7a, B:1837:0x0d8d, B:1841:0x0d96, B:1845:0x0da9, B:1849:0x0db1, B:1853:0x0dc4, B:1857:0x0dea, B:1861:0x0e01, B:1865:0x0e18, B:1868:0x0e2e, B:1872:0x0e43, B:1876:0x0e5a, B:1880:0x0e71, B:1884:0x0e88, B:1888:0x0e9f), top: B:1923:0x071b }] */
    /* JADX WARN: Removed duplicated region for block: B:1666:0x098a  */
    /* JADX WARN: Removed duplicated region for block: B:1670:0x09a1  */
    /* JADX WARN: Removed duplicated region for block: B:1674:0x09b8  */
    /* JADX WARN: Removed duplicated region for block: B:1678:0x09d1  */
    /* JADX WARN: Removed duplicated region for block: B:1682:0x09ea  */
    /* JADX WARN: Removed duplicated region for block: B:1686:0x0a01  */
    /* JADX WARN: Removed duplicated region for block: B:1690:0x0a18  */
    /* JADX WARN: Removed duplicated region for block: B:1694:0x0a2f A[Catch: Exception -> 0x0742, TRY_ENTER, TRY_LEAVE, TryCatch #8 {Exception -> 0x0742, blocks: (B:1567:0x071b, B:1572:0x0738, B:1579:0x0756, B:1582:0x076e, B:1586:0x0787, B:1590:0x07a2, B:1594:0x07bd, B:1606:0x0804, B:1609:0x0825, B:1613:0x083d, B:1617:0x0857, B:1621:0x086c, B:1625:0x0883, B:1629:0x089a, B:1633:0x08af, B:1637:0x08c5, B:1641:0x08db, B:1645:0x08f0, B:1649:0x0908, B:1653:0x091f, B:1657:0x093b, B:1660:0x0952, B:1663:0x096e, B:1667:0x098c, B:1671:0x09a3, B:1675:0x09ba, B:1679:0x09d3, B:1683:0x09ec, B:1687:0x0a03, B:1691:0x0a1a, B:1694:0x0a2f, B:1696:0x0a35, B:1700:0x0a51, B:1704:0x0a6a, B:1708:0x0a8f, B:1712:0x0aa8, B:1716:0x0ac1, B:1720:0x0ada, B:1724:0x0af3, B:1728:0x0b0c, B:1732:0x0b25, B:1736:0x0b3e, B:1740:0x0b57, B:1743:0x0b6c, B:1745:0x0b72, B:1747:0x0b7c, B:1751:0x0b91, B:1753:0x0b97, B:1755:0x0ba1, B:1759:0x0bb4, B:1766:0x0be0, B:1770:0x0be9, B:1774:0x0bfc, B:1778:0x0c22, B:1782:0x0c48, B:1786:0x0c5f, B:1790:0x0c76, B:1794:0x0c9c, B:1798:0x0cb3, B:1802:0x0cca, B:1806:0x0ce1, B:1809:0x0cf6, B:1813:0x0d18, B:1817:0x0d2f, B:1821:0x0d55, B:1825:0x0d5e, B:1829:0x0d71, B:1833:0x0d7a, B:1837:0x0d8d, B:1841:0x0d96, B:1845:0x0da9, B:1849:0x0db1, B:1853:0x0dc4, B:1857:0x0dea, B:1861:0x0e01, B:1865:0x0e18, B:1868:0x0e2e, B:1872:0x0e43, B:1876:0x0e5a, B:1880:0x0e71, B:1884:0x0e88, B:1888:0x0e9f), top: B:1923:0x071b }] */
    /* JADX WARN: Removed duplicated region for block: B:1699:0x0a4f  */
    /* JADX WARN: Removed duplicated region for block: B:1703:0x0a66  */
    /* JADX WARN: Removed duplicated region for block: B:1707:0x0a8d  */
    /* JADX WARN: Removed duplicated region for block: B:1711:0x0aa6  */
    /* JADX WARN: Removed duplicated region for block: B:1715:0x0abf  */
    /* JADX WARN: Removed duplicated region for block: B:1719:0x0ad8  */
    /* JADX WARN: Removed duplicated region for block: B:1723:0x0af1  */
    /* JADX WARN: Removed duplicated region for block: B:1727:0x0b0a  */
    /* JADX WARN: Removed duplicated region for block: B:1731:0x0b23  */
    /* JADX WARN: Removed duplicated region for block: B:1735:0x0b3c  */
    /* JADX WARN: Removed duplicated region for block: B:1739:0x0b55  */
    /* JADX WARN: Removed duplicated region for block: B:1743:0x0b6c A[Catch: Exception -> 0x0742, TRY_ENTER, TryCatch #8 {Exception -> 0x0742, blocks: (B:1567:0x071b, B:1572:0x0738, B:1579:0x0756, B:1582:0x076e, B:1586:0x0787, B:1590:0x07a2, B:1594:0x07bd, B:1606:0x0804, B:1609:0x0825, B:1613:0x083d, B:1617:0x0857, B:1621:0x086c, B:1625:0x0883, B:1629:0x089a, B:1633:0x08af, B:1637:0x08c5, B:1641:0x08db, B:1645:0x08f0, B:1649:0x0908, B:1653:0x091f, B:1657:0x093b, B:1660:0x0952, B:1663:0x096e, B:1667:0x098c, B:1671:0x09a3, B:1675:0x09ba, B:1679:0x09d3, B:1683:0x09ec, B:1687:0x0a03, B:1691:0x0a1a, B:1694:0x0a2f, B:1696:0x0a35, B:1700:0x0a51, B:1704:0x0a6a, B:1708:0x0a8f, B:1712:0x0aa8, B:1716:0x0ac1, B:1720:0x0ada, B:1724:0x0af3, B:1728:0x0b0c, B:1732:0x0b25, B:1736:0x0b3e, B:1740:0x0b57, B:1743:0x0b6c, B:1745:0x0b72, B:1747:0x0b7c, B:1751:0x0b91, B:1753:0x0b97, B:1755:0x0ba1, B:1759:0x0bb4, B:1766:0x0be0, B:1770:0x0be9, B:1774:0x0bfc, B:1778:0x0c22, B:1782:0x0c48, B:1786:0x0c5f, B:1790:0x0c76, B:1794:0x0c9c, B:1798:0x0cb3, B:1802:0x0cca, B:1806:0x0ce1, B:1809:0x0cf6, B:1813:0x0d18, B:1817:0x0d2f, B:1821:0x0d55, B:1825:0x0d5e, B:1829:0x0d71, B:1833:0x0d7a, B:1837:0x0d8d, B:1841:0x0d96, B:1845:0x0da9, B:1849:0x0db1, B:1853:0x0dc4, B:1857:0x0dea, B:1861:0x0e01, B:1865:0x0e18, B:1868:0x0e2e, B:1872:0x0e43, B:1876:0x0e5a, B:1880:0x0e71, B:1884:0x0e88, B:1888:0x0e9f), top: B:1923:0x071b }] */
    /* JADX WARN: Removed duplicated region for block: B:1750:0x0b8f  */
    /* JADX WARN: Removed duplicated region for block: B:1758:0x0bb2  */
    /* JADX WARN: Removed duplicated region for block: B:1761:0x0bca  */
    /* JADX WARN: Removed duplicated region for block: B:1765:0x0bde  */
    /* JADX WARN: Removed duplicated region for block: B:1773:0x0bfa  */
    /* JADX WARN: Removed duplicated region for block: B:1777:0x0c20  */
    /* JADX WARN: Removed duplicated region for block: B:1781:0x0c46  */
    /* JADX WARN: Removed duplicated region for block: B:1785:0x0c5d  */
    /* JADX WARN: Removed duplicated region for block: B:1789:0x0c74  */
    /* JADX WARN: Removed duplicated region for block: B:1793:0x0c9a  */
    /* JADX WARN: Removed duplicated region for block: B:1797:0x0cb1  */
    /* JADX WARN: Removed duplicated region for block: B:1801:0x0cc8  */
    /* JADX WARN: Removed duplicated region for block: B:1805:0x0cdf  */
    /* JADX WARN: Removed duplicated region for block: B:1809:0x0cf6 A[Catch: Exception -> 0x0742, TRY_ENTER, TRY_LEAVE, TryCatch #8 {Exception -> 0x0742, blocks: (B:1567:0x071b, B:1572:0x0738, B:1579:0x0756, B:1582:0x076e, B:1586:0x0787, B:1590:0x07a2, B:1594:0x07bd, B:1606:0x0804, B:1609:0x0825, B:1613:0x083d, B:1617:0x0857, B:1621:0x086c, B:1625:0x0883, B:1629:0x089a, B:1633:0x08af, B:1637:0x08c5, B:1641:0x08db, B:1645:0x08f0, B:1649:0x0908, B:1653:0x091f, B:1657:0x093b, B:1660:0x0952, B:1663:0x096e, B:1667:0x098c, B:1671:0x09a3, B:1675:0x09ba, B:1679:0x09d3, B:1683:0x09ec, B:1687:0x0a03, B:1691:0x0a1a, B:1694:0x0a2f, B:1696:0x0a35, B:1700:0x0a51, B:1704:0x0a6a, B:1708:0x0a8f, B:1712:0x0aa8, B:1716:0x0ac1, B:1720:0x0ada, B:1724:0x0af3, B:1728:0x0b0c, B:1732:0x0b25, B:1736:0x0b3e, B:1740:0x0b57, B:1743:0x0b6c, B:1745:0x0b72, B:1747:0x0b7c, B:1751:0x0b91, B:1753:0x0b97, B:1755:0x0ba1, B:1759:0x0bb4, B:1766:0x0be0, B:1770:0x0be9, B:1774:0x0bfc, B:1778:0x0c22, B:1782:0x0c48, B:1786:0x0c5f, B:1790:0x0c76, B:1794:0x0c9c, B:1798:0x0cb3, B:1802:0x0cca, B:1806:0x0ce1, B:1809:0x0cf6, B:1813:0x0d18, B:1817:0x0d2f, B:1821:0x0d55, B:1825:0x0d5e, B:1829:0x0d71, B:1833:0x0d7a, B:1837:0x0d8d, B:1841:0x0d96, B:1845:0x0da9, B:1849:0x0db1, B:1853:0x0dc4, B:1857:0x0dea, B:1861:0x0e01, B:1865:0x0e18, B:1868:0x0e2e, B:1872:0x0e43, B:1876:0x0e5a, B:1880:0x0e71, B:1884:0x0e88, B:1888:0x0e9f), top: B:1923:0x071b }] */
    /* JADX WARN: Removed duplicated region for block: B:1812:0x0d16  */
    /* JADX WARN: Removed duplicated region for block: B:1816:0x0d2d  */
    /* JADX WARN: Removed duplicated region for block: B:1820:0x0d53  */
    /* JADX WARN: Removed duplicated region for block: B:1828:0x0d6f  */
    /* JADX WARN: Removed duplicated region for block: B:1836:0x0d8b  */
    /* JADX WARN: Removed duplicated region for block: B:1844:0x0da7  */
    /* JADX WARN: Removed duplicated region for block: B:1852:0x0dc2  */
    /* JADX WARN: Removed duplicated region for block: B:1856:0x0de8  */
    /* JADX WARN: Removed duplicated region for block: B:1860:0x0dff  */
    /* JADX WARN: Removed duplicated region for block: B:1864:0x0e16  */
    /* JADX WARN: Removed duplicated region for block: B:1868:0x0e2e A[Catch: Exception -> 0x0742, TRY_ENTER, TRY_LEAVE, TryCatch #8 {Exception -> 0x0742, blocks: (B:1567:0x071b, B:1572:0x0738, B:1579:0x0756, B:1582:0x076e, B:1586:0x0787, B:1590:0x07a2, B:1594:0x07bd, B:1606:0x0804, B:1609:0x0825, B:1613:0x083d, B:1617:0x0857, B:1621:0x086c, B:1625:0x0883, B:1629:0x089a, B:1633:0x08af, B:1637:0x08c5, B:1641:0x08db, B:1645:0x08f0, B:1649:0x0908, B:1653:0x091f, B:1657:0x093b, B:1660:0x0952, B:1663:0x096e, B:1667:0x098c, B:1671:0x09a3, B:1675:0x09ba, B:1679:0x09d3, B:1683:0x09ec, B:1687:0x0a03, B:1691:0x0a1a, B:1694:0x0a2f, B:1696:0x0a35, B:1700:0x0a51, B:1704:0x0a6a, B:1708:0x0a8f, B:1712:0x0aa8, B:1716:0x0ac1, B:1720:0x0ada, B:1724:0x0af3, B:1728:0x0b0c, B:1732:0x0b25, B:1736:0x0b3e, B:1740:0x0b57, B:1743:0x0b6c, B:1745:0x0b72, B:1747:0x0b7c, B:1751:0x0b91, B:1753:0x0b97, B:1755:0x0ba1, B:1759:0x0bb4, B:1766:0x0be0, B:1770:0x0be9, B:1774:0x0bfc, B:1778:0x0c22, B:1782:0x0c48, B:1786:0x0c5f, B:1790:0x0c76, B:1794:0x0c9c, B:1798:0x0cb3, B:1802:0x0cca, B:1806:0x0ce1, B:1809:0x0cf6, B:1813:0x0d18, B:1817:0x0d2f, B:1821:0x0d55, B:1825:0x0d5e, B:1829:0x0d71, B:1833:0x0d7a, B:1837:0x0d8d, B:1841:0x0d96, B:1845:0x0da9, B:1849:0x0db1, B:1853:0x0dc4, B:1857:0x0dea, B:1861:0x0e01, B:1865:0x0e18, B:1868:0x0e2e, B:1872:0x0e43, B:1876:0x0e5a, B:1880:0x0e71, B:1884:0x0e88, B:1888:0x0e9f), top: B:1923:0x071b }] */
    /* JADX WARN: Removed duplicated region for block: B:1871:0x0e41  */
    /* JADX WARN: Removed duplicated region for block: B:1875:0x0e58  */
    /* JADX WARN: Removed duplicated region for block: B:1879:0x0e6f  */
    /* JADX WARN: Removed duplicated region for block: B:1883:0x0e86  */
    /* JADX WARN: Removed duplicated region for block: B:1887:0x0e9d  */
    /* JADX WARN: Removed duplicated region for block: B:1925:0x0157 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @RequiresApi(api = 16)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public JSONObject c(Context context, HashMap<String, String> hashMap) {
        String str;
        Exception exc;
        String str2;
        com.jd.jdsec.c.a e2;
        String str3;
        String str4;
        String str5;
        JSONObject jSONObject = new JSONObject();
        try {
            b.e("JDSec.Security.DeviceFix", "\u5f00\u59cb\u65f6\u5168\u90e8\u5185\u5b58" + Runtime.getRuntime().totalMemory());
            b.e("JDSec.Security.DeviceFix", "\u5f00\u59cb\u65f6\u5269\u4f59\u5185\u5b58" + Runtime.getRuntime().freeMemory());
            Long valueOf = Long.valueOf(System.currentTimeMillis());
            m();
            String a = c.a(context);
            String c2 = d.c("prevUUID", "");
            String uuid = UUID.randomUUID().toString();
            com.jd.jdsec.a.j.b.p(c2);
            com.jd.jdsec.a.j.b.n(uuid);
            f(g(context, a));
            try {
                jSONObject.put("ct", String.valueOf(valueOf));
                StringBuilder sb = new StringBuilder();
                sb.append(String.valueOf(valueOf));
                sb.append(g.a());
                String deviceModel = BaseInfo.getDeviceModel();
                sb.append(deviceModel);
                str2 = "cputype";
                try {
                    sb.append(c.a(context));
                    String sb2 = sb.toString();
                    b.b("JDSec.Security.DeviceFix", String.valueOf(valueOf));
                    b.b("JDSec.Security.DeviceFix", g.a());
                    b.b("JDSec.Security.DeviceFix", deviceModel);
                    b.b("JDSec.Security.DeviceFix", c.a(context));
                    b.b("JDSec.Security.DeviceFix", sb2);
                    b.b("JDSec.Security.DeviceFix", h.a(sb2));
                    b.b("JDSec.Security.DeviceFix", h.a(sb2).substring(0, 8));
                    jSONObject.put("si", h.a(sb2).substring(0, 8));
                } catch (Exception e3) {
                    e = e3;
                    try {
                        b.b("JDSec.Security.DeviceFix", e.getMessage());
                        jSONObject.put("prevUUID", c2);
                        jSONObject.put("currentUUID", uuid);
                        d.e("prevUUID", uuid);
                        jSONObject.put("eventId", d.c("eventId", ""));
                        jSONObject.put(UnionConstants.BUNDLE_REFER, d.c(UnionConstants.BUNDLE_REFER, ""));
                        d.e("eventId", com.jd.jdsec.c.h.a.c());
                        d.e(UnionConstants.BUNDLE_REFER, com.jd.jdsec.c.h.a.d());
                        e2 = g.e();
                        if (h(hashMap.get(JDNetCacheManager.BRAND_BIZKEY), a)) {
                        }
                        if (h(hashMap.get("devicename"), a)) {
                        }
                        if (h(hashMap.get("cpuid"), a)) {
                        }
                        str3 = str2;
                        if (h(hashMap.get(str3), a)) {
                        }
                        if (h(hashMap.get("cpufrequency"), a)) {
                        }
                        if (h(hashMap.get("freediskspace"), a)) {
                        }
                        if (h(hashMap.get("mcc"), a)) {
                        }
                        if (h(hashMap.get("mnc"), a)) {
                        }
                        if (h(hashMap.get("country"), a)) {
                        }
                        jSONObject.put("timeZone", com.jd.jdsec.a.g.x());
                        jSONObject.put("testHasPermission", e2.hasPermission());
                        if (h(hashMap.get("ssid"), a)) {
                        }
                        if (h(hashMap.get("bssid"), a)) {
                        }
                        if (h(hashMap.get("isoCountryCode"), a)) {
                        }
                        if (h(hashMap.get("rssi"), a)) {
                        }
                        if (h(hashMap.get("linkSpeed"), a)) {
                        }
                        List<String> netAddressesForIPv4 = BaseInfo.getNetAddressesForIPv4();
                        if (h(hashMap.get("routerIp"), a)) {
                        }
                        if (h(hashMap.get("dns1"), a)) {
                        }
                        if (h(hashMap.get("dns2"), a)) {
                        }
                        if (h(hashMap.get("netmask"), a)) {
                        }
                        if (h(hashMap.get("makePhoneAvailable"), a)) {
                        }
                        if (h(hashMap.get("magnetometerAvailable"), a)) {
                        }
                        if (h(hashMap.get("headingAvailable"), a)) {
                        }
                        if (h(hashMap.get("frontCamera"), a)) {
                        }
                        if (h(hashMap.get("backCamera"), a)) {
                        }
                        if (h(hashMap.get("headsetOn"), a)) {
                        }
                        if (h(hashMap.get(Constant.KEY_NFC_ENABLE), a)) {
                        }
                        if (h(hashMap.get("screenScale"), a)) {
                        }
                        if (h(hashMap.get("sdCid"), a)) {
                        }
                        if (h(hashMap.get("btMac"), a)) {
                        }
                        if (h(hashMap.get("simSerialNumber"), a)) {
                        }
                        if (h(hashMap.get("dpi"), a)) {
                        }
                        if (h(hashMap.get("abi"), a)) {
                        }
                        if (h(hashMap.get("bluetoothName"), a)) {
                        }
                        if (h(hashMap.get("wifiList"), a)) {
                        }
                        if (h(hashMap.get("ua"), a)) {
                        }
                        if (h(hashMap.get("processcount"), a)) {
                        }
                        if (h(hashMap.get("processlist"), a)) {
                        }
                        if (h(hashMap.get("appList"), a)) {
                        }
                        if (h(hashMap.get("appcount"), a)) {
                        }
                        if (h(hashMap.get("af"), a)) {
                        }
                        if (h(hashMap.get("sof"), a)) {
                        }
                        if (h(hashMap.get("sl"), a)) {
                        }
                        if (h(hashMap.get("rc"), a)) {
                        }
                        if (h(hashMap.get("isOnThePhone"), a)) {
                        }
                        if (h(hashMap.get("vpnConnect"), a)) {
                        }
                        int[] i2 = com.jd.jdsec.a.g.i(context);
                        if (h(hashMap.get("batteryLevel"), a)) {
                        }
                        if (h(hashMap.get("batteryVoltage"), a)) {
                        }
                        if (h(hashMap.get("batteryStatus"), a)) {
                        }
                        if (h(hashMap.get("batteryHealth"), a)) {
                        }
                        if (h(hashMap.get("brightness"), a)) {
                        }
                        if (h(hashMap.get("wifiEnable"), a)) {
                        }
                        if (h(hashMap.get("network"), a)) {
                        }
                        if (h(hashMap.get("isRoot"), a)) {
                        }
                        if (h(hashMap.get("emulator"), a)) {
                        }
                        if (h(hashMap.get("isHooked"), a)) {
                        }
                        if (h(hashMap.get("isMoreOpen"), a)) {
                        }
                        if (h(hashMap.get("isDebug"), a)) {
                        }
                        if (h(hashMap.get("sensorInfo"), a)) {
                        }
                        if (h(hashMap.get("isCloudEnv"), a)) {
                        }
                        if (h(hashMap.get("Lock_awake_receiver"), str5)) {
                        }
                        if (h(hashMap.get("totalDiskSpace"), str5)) {
                        }
                        if (h(hashMap.get("vapp"), str5)) {
                        }
                        if (h(hashMap.get("isModifier"), str5)) {
                        }
                        if (h(hashMap.get("isMalicious"), str5)) {
                        }
                        if (h(hashMap.get("ifpad"), str5)) {
                        }
                        jSONObject.put("extkey", g.a());
                        jSONObject.put("client", "android");
                        jSONObject.put("clientversion", c.a(context));
                        jSONObject.put(HybridSDK.OS_VERSION, Build.VERSION.RELEASE);
                        jSONObject.put(Configuration.UNION_ID, g.i());
                        jSONObject.put(Configuration.SUB_UNION_ID, g.g());
                        if (h(hashMap.get("androidId"), str5)) {
                        }
                        jSONObject.put("imei", com.jingdong.jdsdk.a.a.a);
                        if (h(hashMap.get("memSize"), str5)) {
                        }
                        if (h(hashMap.get(CustomThemeConstance.NAVI_MODEL), str5)) {
                        }
                        if (h(hashMap.get("bootloader"), str5)) {
                        }
                        if (h(hashMap.get(ViewProps.DISPLAY), str5)) {
                        }
                        if (h(hashMap.get("isPipeExist"), str5)) {
                        }
                        if (h(hashMap.get("isQEmuDriverExist"), str5)) {
                        }
                        if (h(hashMap.get("board"), str5)) {
                        }
                        if (h(hashMap.get(NoStockRecommendHead.DEVICE), str5)) {
                        }
                        if (h(hashMap.get("sensors"), str5)) {
                        }
                        if (h(hashMap.get("hardware"), str5)) {
                        }
                        if (h(hashMap.get("carrierName"), str5)) {
                        }
                        if (h(hashMap.get("fingerprint"), str5)) {
                        }
                        jSONObject.put("fcgn-brand", com.jingdong.jdsdk.a.a.a);
                        if (h(hashMap.get("fcgn-fingerprint"), str5)) {
                        }
                        if (h(hashMap.get("maxCpuFrequency"), str5)) {
                        }
                        if (h(hashMap.get("minCpuFrequency"), str5)) {
                        }
                        if (h(hashMap.get("screen"), str5)) {
                        }
                        if (h(hashMap.get(Constants.PARAM_PLATFORM), str5)) {
                        }
                        if (h(hashMap.get("slan"), str5)) {
                        }
                        if (h(hashMap.get("ulan"), str5)) {
                        }
                        if (h(hashMap.get("buildInfo"), str5)) {
                        }
                        if (h(hashMap.get("ipAddress"), str5)) {
                        }
                        if (h(hashMap.get("lach"), str5)) {
                        }
                        if (h(hashMap.get("physicalCpu"), str5)) {
                        }
                        if (h(hashMap.get("xtbb"), str5)) {
                        }
                        if (h(hashMap.get("mobileCountryCode"), str5)) {
                        }
                        jSONObject.put("DNS", com.jingdong.jdsdk.a.a.a);
                        jSONObject.put("fcgn-ncpu", "");
                        if (h(hashMap.get("range_clientversion"), str5)) {
                        }
                        if (h(hashMap.get("range_osversion"), str5)) {
                        }
                        if (h(hashMap.get("range_virtualapp"), str5)) {
                        }
                        if (h(hashMap.get("range_lock_awake_receiver"), str5)) {
                        }
                        if (h(hashMap.get("range_network"), str5)) {
                        }
                        if (h(hashMap.get("range_cpufrequency"), str5)) {
                        }
                        if (h(hashMap.get("range_batterylevel"), str5)) {
                        }
                        if (h(hashMap.get("range_brightness"), str5)) {
                        }
                        if (h(hashMap.get("osversiontags"), str5)) {
                        }
                        if (h(hashMap.get("wifissidlist"), str5)) {
                        }
                        if (h(hashMap.get("wifilinkspeed"), str5)) {
                        }
                        if (!h(hashMap.get("isfakegps"), str5)) {
                        }
                        String str6 = "1";
                        if (h(hashMap.get("haspipes"), str5)) {
                        }
                        if (h(hashMap.get("firstinstalltime"), str5)) {
                        }
                        if (h(hashMap.get("lastupdatetime"), str5)) {
                        }
                        if (h(hashMap.get("appname"), str5)) {
                        }
                        if (h(hashMap.get("packagename"), str5)) {
                        }
                        if (h(hashMap.get("signaturehash"), str5)) {
                        }
                        if (h(hashMap.get("manufacture"), str5)) {
                        }
                        if (h(hashMap.get("lastoaid"), str5)) {
                        }
                        if (h(hashMap.get("oaid"), str5)) {
                        }
                        if (h(hashMap.get("radioversion"), str5)) {
                        }
                        if (h(hashMap.get("realscreensize"), str5)) {
                        }
                        if (h(hashMap.get("romname"), str5)) {
                        }
                        if (h(hashMap.get("statusbarheight"), str5)) {
                        }
                        if (h(hashMap.get("fingerprintavailable"), str5)) {
                        }
                        if (h(hashMap.get("gpsavailable"), str5)) {
                        }
                        if (h(hashMap.get("hasqemudriverfile"), str5)) {
                        }
                        if (h(hashMap.get("hassdcard"), str5)) {
                        }
                        if (h(hashMap.get("systemVolume"), str5)) {
                        }
                        if (h(hashMap.get("androidSDKVersion"), str5)) {
                        }
                        if (h(hashMap.get("linuxVersion"), str5)) {
                        }
                        if (h(hashMap.get("scaledDensity"), str5)) {
                        }
                        if (h(hashMap.get("runningServices"), str5)) {
                        }
                        if (h(hashMap.get("externalStorageSize"), str5)) {
                        }
                        if (h(hashMap.get("sdCardId"), str5)) {
                        }
                        if (h(hashMap.get("moce"), str5)) {
                        }
                        if (h(hashMap.get("appct"), str5)) {
                        }
                        if (h(hashMap.get("wlspq"), str5)) {
                        }
                        com.jd.jdsec.a.l.e.i("fixinfo: " + jSONObject);
                        com.jd.jdsec.a.j.a.e(jSONObject);
                        str = str4;
                    } catch (Exception e4) {
                        e = e4;
                        str = "JDSec.Security.DeviceFix";
                        exc = e;
                        b.b(str, "\u83b7\u53d6\u8bbe\u5907\u4fe1\u606f\u62a5\u9519: " + exc.getMessage());
                        return jSONObject;
                    }
                    try {
                        b.e(str, "\u6267\u884c\u91c7\u96c6\u8017\u65f6: " + (System.currentTimeMillis() - valueOf.longValue()));
                    } catch (Exception e5) {
                        e = e5;
                        exc = e;
                        b.b(str, "\u83b7\u53d6\u8bbe\u5907\u4fe1\u606f\u62a5\u9519: " + exc.getMessage());
                        return jSONObject;
                    }
                    return jSONObject;
                }
            } catch (Exception e6) {
                e = e6;
                str2 = "cputype";
            }
            jSONObject.put("prevUUID", c2);
            jSONObject.put("currentUUID", uuid);
            d.e("prevUUID", uuid);
            jSONObject.put("eventId", d.c("eventId", ""));
            jSONObject.put(UnionConstants.BUNDLE_REFER, d.c(UnionConstants.BUNDLE_REFER, ""));
            d.e("eventId", com.jd.jdsec.c.h.a.c());
            d.e(UnionConstants.BUNDLE_REFER, com.jd.jdsec.c.h.a.d());
            e2 = g.e();
            try {
                if (h(hashMap.get(JDNetCacheManager.BRAND_BIZKEY), a)) {
                    try {
                        String deviceBrand = BaseInfo.getDeviceBrand();
                        d.e(JDNetCacheManager.BRAND_BIZKEY, deviceBrand);
                        jSONObject.put(JDNetCacheManager.BRAND_BIZKEY, deviceBrand);
                    } catch (Exception e7) {
                        exc = e7;
                        str = "JDSec.Security.DeviceFix";
                        b.b(str, "\u83b7\u53d6\u8bbe\u5907\u4fe1\u606f\u62a5\u9519: " + exc.getMessage());
                        return jSONObject;
                    }
                }
                if (h(hashMap.get("devicename"), a)) {
                    jSONObject.put("devicename", "");
                }
                if (h(hashMap.get("cpuid"), a)) {
                    jSONObject.put("cpuid", BaseInfo.getCPUSerialNo());
                }
                str3 = str2;
                if (h(hashMap.get(str3), a)) {
                    String cpuName = BaseInfo.getCpuName();
                    d.e(str3, cpuName);
                    jSONObject.put(str3, cpuName);
                }
                if (h(hashMap.get("cpufrequency"), a)) {
                    jSONObject.put("cpufrequency", BaseInfo.getCpuCurFreq());
                }
                if (h(hashMap.get("freediskspace"), a)) {
                    jSONObject.put("freediskspace", String.valueOf(com.jd.jdsec.a.g.t()));
                }
                if (h(hashMap.get("mcc"), a)) {
                    jSONObject.put("mcc", com.jd.jdsec.a.g.u(context)[0]);
                }
                if (h(hashMap.get("mnc"), a)) {
                    jSONObject.put("mnc", com.jd.jdsec.a.g.u(context)[1]);
                }
                if (h(hashMap.get("country"), a)) {
                    jSONObject.put("country", com.jd.jdsec.a.g.k(context));
                }
                jSONObject.put("timeZone", com.jd.jdsec.a.g.x());
                jSONObject.put("testHasPermission", e2.hasPermission());
                if (h(hashMap.get("ssid"), a)) {
                    jSONObject.put("ssid", e2.hasPermission() ? BaseInfo.getWifiSSID(context) : "");
                }
                if (h(hashMap.get("bssid"), a)) {
                    jSONObject.put("bssid", e2.hasPermission() ? BaseInfo.getWifiBSSID(context) : "");
                }
                if (h(hashMap.get("isoCountryCode"), a)) {
                    jSONObject.put("isoCountryCode", BaseInfo.getNetworkISO(context));
                }
                if (h(hashMap.get("rssi"), a)) {
                    jSONObject.put("rssi", e2.hasPermission() ? String.valueOf(BaseInfo.getWifiRssi(context)) : "");
                }
                if (h(hashMap.get("linkSpeed"), a)) {
                    jSONObject.put("linkSpeed", com.jd.jdsec.a.g.H(context));
                }
                List<String> netAddressesForIPv42 = BaseInfo.getNetAddressesForIPv4();
                if (h(hashMap.get("routerIp"), a)) {
                    if (!netAddressesForIPv42.isEmpty()) {
                        jSONObject.put("routerIp", netAddressesForIPv42.get(0));
                    } else {
                        jSONObject.put("routerIp", "");
                    }
                }
                if (h(hashMap.get("dns1"), a)) {
                    try {
                        jSONObject.put("dns1", com.jd.jdsec.a.g.o(context)[0]);
                    } catch (Exception unused) {
                        jSONObject.put("dns1", "");
                    }
                }
                if (h(hashMap.get("dns2"), a)) {
                    try {
                        jSONObject.put("dns2", com.jd.jdsec.a.g.o(context)[1]);
                    } catch (Exception unused2) {
                        jSONObject.put("dns2", "");
                    }
                }
                if (h(hashMap.get("netmask"), a)) {
                    jSONObject.put("netmask", com.jd.jdsec.a.g.y(context));
                }
                if (h(hashMap.get("makePhoneAvailable"), a)) {
                    jSONObject.put("makePhoneAvailable", com.jd.jdsec.a.g.b(context));
                }
                if (h(hashMap.get("magnetometerAvailable"), a)) {
                    jSONObject.put("magnetometerAvailable", com.jd.jdsec.a.g.I(context));
                }
                if (h(hashMap.get("headingAvailable"), a)) {
                    jSONObject.put("headingAvailable", BaseInfo.isGPSAvailable());
                }
                if (h(hashMap.get("frontCamera"), a)) {
                    jSONObject.put("frontCamera", com.jd.jdsec.a.g.f(context));
                }
                if (h(hashMap.get("backCamera"), a)) {
                    jSONObject.put("backCamera", com.jd.jdsec.a.g.c());
                }
                if (h(hashMap.get("headsetOn"), a)) {
                    jSONObject.put("headsetOn", com.jd.jdsec.a.g.J(context));
                }
                if (h(hashMap.get(Constant.KEY_NFC_ENABLE), a)) {
                    jSONObject.put(Constant.KEY_NFC_ENABLE, BaseInfo.isNFCAvailable(context));
                }
                if (h(hashMap.get("screenScale"), a)) {
                    jSONObject.put("screenScale", String.valueOf(BaseInfo.getScaledDensity()));
                }
                if (h(hashMap.get("sdCid"), a)) {
                    jSONObject.put("sdCid", BaseInfo.getSDCardId());
                }
                if (h(hashMap.get("btMac"), a)) {
                    jSONObject.put("btMac", BaseInfo.getBluetoothMac(context));
                }
                if (h(hashMap.get("simSerialNumber"), a)) {
                    jSONObject.put("simSerialNumber", BaseInfo.getSimSerialNo());
                }
                if (h(hashMap.get("dpi"), a)) {
                    jSONObject.put("dpi", BaseInfo.getDensityDpi());
                }
                if (h(hashMap.get("abi"), a)) {
                    jSONObject.put("abi", Arrays.toString(BaseInfo.getDeviceSuppportedABIs()));
                }
                if (h(hashMap.get("bluetoothName"), a)) {
                    jSONObject.put("bluetoothName", BaseInfo.getBluetoothName(context));
                }
                if (h(hashMap.get("wifiList"), a)) {
                    jSONObject.put("wifiList", e2.hasPermission() ? BaseInfo.getWifiBSSIDList(context) : "");
                }
                if (h(hashMap.get("ua"), a)) {
                    f h2 = g.h();
                    if (h2 == null) {
                        jSONObject.put("ua", com.jingdong.jdsdk.a.a.a);
                    } else {
                        jSONObject.put("ua", h2.getUserAgent());
                    }
                }
                if (h(hashMap.get("processcount"), a)) {
                    jSONObject.put("processcount", "");
                }
                if (h(hashMap.get("processlist"), a)) {
                    jSONObject.put("processlist", "");
                }
                if (h(hashMap.get("appList"), a)) {
                    jSONObject.put("appList", "[]");
                }
                if (h(hashMap.get("appcount"), a)) {
                    jSONObject.put("appcount", 0);
                }
                if (h(hashMap.get("af"), a)) {
                    jSONObject.put("af", BaseInfo.isAppForeground());
                }
                if (h(hashMap.get("sof"), a)) {
                    jSONObject.put("sof", com.jd.jdsec.a.g.K(context));
                }
                if (h(hashMap.get("sl"), a)) {
                    jSONObject.put("sl", com.jd.jdsec.a.g.L(context));
                }
                if (h(hashMap.get("rc"), a)) {
                    jSONObject.put("rc", com.jd.jdsec.a.g.M(context));
                }
                if (h(hashMap.get("isOnThePhone"), a)) {
                    jSONObject.put("isOnThePhone", com.jd.jdsec.a.g.N(context));
                }
                if (h(hashMap.get("vpnConnect"), a)) {
                    jSONObject.put("vpnConnect", com.jd.jdsec.a.g.O(context));
                }
                int[] i22 = com.jd.jdsec.a.g.i(context);
                if (h(hashMap.get("batteryLevel"), a)) {
                    jSONObject.put("batteryLevel", String.valueOf(i22[0]));
                }
                if (h(hashMap.get("batteryVoltage"), a)) {
                    jSONObject.put("batteryVoltage", String.valueOf(i22[1]));
                }
                if (h(hashMap.get("batteryStatus"), a)) {
                    jSONObject.put("batteryStatus", String.valueOf(i22[2]));
                }
                if (h(hashMap.get("batteryHealth"), a)) {
                    jSONObject.put("batteryHealth", String.valueOf(i22[3]));
                }
                if (h(hashMap.get("brightness"), a)) {
                    jSONObject.put("brightness", com.jd.jdsec.a.g.B(context));
                }
                if (h(hashMap.get("wifiEnable"), a)) {
                    jSONObject.put("wifiEnable", com.jd.jdsec.a.g.P(context));
                }
                if (h(hashMap.get("network"), a)) {
                    jSONObject.put("network", BaseInfo.getNetworkType());
                }
                if (h(hashMap.get("isRoot"), a)) {
                    jSONObject.put("isRoot", String.valueOf(BaseInfo.isRoot()));
                }
                if (h(hashMap.get("emulator"), a)) {
                    jSONObject.put("emulator", String.valueOf(i.f(context).longValue()));
                }
                if (h(hashMap.get("isHooked"), a)) {
                    jSONObject.put("isHooked", String.valueOf(com.jd.jdsec.a.k.e.d(context)));
                }
                if (h(hashMap.get("isMoreOpen"), a)) {
                    jSONObject.put("isMoreOpen", String.valueOf(j.c(context)));
                }
                if (h(hashMap.get("isDebug"), a)) {
                    jSONObject.put("isDebug", String.valueOf(com.jd.jdsec.a.k.b.c(context)));
                }
                if (h(hashMap.get("sensorInfo"), a)) {
                    jSONObject.put("sensorInfo", k.a(context));
                }
                if (h(hashMap.get("isCloudEnv"), a)) {
                    str4 = "JDSec.Security.DeviceFix";
                    str5 = a;
                } else {
                    str4 = "JDSec.Security.DeviceFix";
                    str5 = a;
                    try {
                        jSONObject.put("isCloudEnv", String.valueOf(com.jd.jdsec.a.k.a.a(context, i.f(context).longValue(), com.jd.jdsec.a.g.F(context), e.a(), c.c(context))));
                    } catch (Exception e8) {
                        exc = e8;
                        str = str4;
                        b.b(str, "\u83b7\u53d6\u8bbe\u5907\u4fe1\u606f\u62a5\u9519: " + exc.getMessage());
                        return jSONObject;
                    }
                }
                try {
                    if (h(hashMap.get("Lock_awake_receiver"), str5)) {
                        jSONObject.put("Lock_awake_receiver", c.c(context));
                    }
                    if (h(hashMap.get("totalDiskSpace"), str5)) {
                        String F = com.jd.jdsec.a.g.F(context);
                        d.e("totalDiskSpace", F);
                        jSONObject.put("totalDiskSpace", F);
                    }
                    if (h(hashMap.get("vapp"), str5)) {
                        jSONObject.put("vapp", e.a());
                    }
                    if (h(hashMap.get("isModifier"), str5)) {
                        jSONObject.put("isModifier", String.valueOf(com.jd.jdsec.a.k.h.b(context)));
                    }
                    if (h(hashMap.get("isMalicious"), str5)) {
                        jSONObject.put("isMalicious", String.valueOf(com.jd.jdsec.a.k.g.b(context)));
                    }
                    if (h(hashMap.get("ifpad"), str5)) {
                        jSONObject.put("ifpad", com.jd.jdsec.a.k.f.a(context));
                    }
                    jSONObject.put("extkey", g.a());
                    jSONObject.put("client", "android");
                    jSONObject.put("clientversion", c.a(context));
                    jSONObject.put(HybridSDK.OS_VERSION, Build.VERSION.RELEASE);
                    jSONObject.put(Configuration.UNION_ID, g.i());
                    jSONObject.put(Configuration.SUB_UNION_ID, g.g());
                    if (h(hashMap.get("androidId"), str5)) {
                        String androidId = BaseInfo.getAndroidId();
                        d.e("androidid", androidId);
                        jSONObject.put("androidId", androidId);
                    }
                    jSONObject.put("imei", com.jingdong.jdsdk.a.a.a);
                    if (h(hashMap.get("memSize"), str5)) {
                        String G = com.jd.jdsec.a.g.G(context);
                        d.e("memSize", G);
                        jSONObject.put("memSize", G);
                    }
                    if (h(hashMap.get(CustomThemeConstance.NAVI_MODEL), str5)) {
                        String deviceModel2 = BaseInfo.getDeviceModel();
                        d.e(CustomThemeConstance.NAVI_MODEL, deviceModel2);
                        jSONObject.put(CustomThemeConstance.NAVI_MODEL, deviceModel2);
                    }
                    if (h(hashMap.get("bootloader"), str5)) {
                        jSONObject.put("bootloader", Build.BOOTLOADER);
                    }
                    if (h(hashMap.get(ViewProps.DISPLAY), str5)) {
                        jSONObject.put(ViewProps.DISPLAY, BaseInfo.getOSName());
                    }
                    if (h(hashMap.get("isPipeExist"), str5)) {
                        jSONObject.put("isPipeExist", com.jd.jdsec.a.g.e());
                    }
                    if (h(hashMap.get("isQEmuDriverExist"), str5)) {
                        jSONObject.put("isQEmuDriverExist", com.jd.jdsec.a.g.h());
                    }
                    if (h(hashMap.get("board"), str5)) {
                        String str7 = Build.BOARD;
                        d.e("board", str7);
                        jSONObject.put("board", str7);
                    }
                    if (h(hashMap.get(NoStockRecommendHead.DEVICE), str5)) {
                        d.e(NoStockRecommendHead.DEVICE, "");
                        jSONObject.put(NoStockRecommendHead.DEVICE, "");
                    }
                    if (h(hashMap.get("sensors"), str5)) {
                        jSONObject.put("sensors", com.jd.jdsec.a.g.C());
                    }
                    if (h(hashMap.get("hardware"), str5)) {
                        String str8 = Build.HARDWARE;
                        d.e("hardware", str8);
                        jSONObject.put("hardware", str8);
                    }
                    if (h(hashMap.get("carrierName"), str5)) {
                        jSONObject.put("carrierName", BaseInfo.getNetworkOperatorName(context));
                    }
                    if (h(hashMap.get("fingerprint"), str5)) {
                        jSONObject.put("fingerprint", BaseInfo.getOSFingerprint());
                    }
                    jSONObject.put("fcgn-brand", com.jingdong.jdsdk.a.a.a);
                    if (h(hashMap.get("fcgn-fingerprint"), str5)) {
                        jSONObject.put("fcgn-fingerprint", com.jd.jdsec.a.f.a("ro.build.fingerprint"));
                    }
                    if (h(hashMap.get("maxCpuFrequency"), str5)) {
                        String m2 = com.jd.jdsec.a.g.m();
                        d.e("maxCpuFrequency", m2);
                        jSONObject.put("maxCpuFrequency", m2);
                    }
                    if (h(hashMap.get("minCpuFrequency"), str5)) {
                        String n2 = com.jd.jdsec.a.g.n();
                        d.e("minCpuFrequency", n2);
                        jSONObject.put("minCpuFrequency", n2);
                    }
                    if (h(hashMap.get("screen"), str5)) {
                        jSONObject.put("screen", com.jd.jdsec.a.g.D(context));
                    }
                    if (h(hashMap.get(Constants.PARAM_PLATFORM), str5)) {
                        jSONObject.put(Constants.PARAM_PLATFORM, BaseInfo.getDeviceModel());
                    }
                    if (h(hashMap.get("slan"), str5)) {
                        jSONObject.put("slan", com.jd.jdsec.a.f.a("ro.product.locale"));
                    }
                    if (h(hashMap.get("ulan"), str5)) {
                        jSONObject.put("ulan", com.jd.jdsec.a.f.a("persist.sys.locale"));
                    }
                    if (h(hashMap.get("buildInfo"), str5)) {
                        jSONObject.put("buildInfo", com.jd.jdsec.a.g.j());
                    }
                    if (h(hashMap.get("ipAddress"), str5)) {
                        jSONObject.put("ipAddress", com.jd.jdsec.a.g.v());
                    }
                    if (h(hashMap.get("lach"), str5)) {
                        jSONObject.put("lach", com.jd.jdsec.a.b.a());
                    }
                    if (h(hashMap.get("physicalCpu"), str5)) {
                        int p = com.jd.jdsec.a.g.p();
                        d.e("physicalcpu", String.valueOf(p));
                        jSONObject.put("physicalcpu", p);
                    }
                    if (h(hashMap.get("xtbb"), str5)) {
                        jSONObject.put("xtbb", com.jd.jdsec.a.g.z());
                    }
                    if (h(hashMap.get("mobileCountryCode"), str5)) {
                        jSONObject.put("mobileCountryCode", com.jd.jdsec.a.i.a.a(R2.drawable.button_p_02).g());
                    }
                    jSONObject.put("DNS", com.jingdong.jdsdk.a.a.a);
                    jSONObject.put("fcgn-ncpu", "");
                    if (h(hashMap.get("range_clientversion"), str5)) {
                        jSONObject.put("range_clientversion", d.c("List0", ""));
                    }
                    if (h(hashMap.get("range_osversion"), str5)) {
                        jSONObject.put("range_osversion", d.c("List1", ""));
                    }
                    if (h(hashMap.get("range_virtualapp"), str5)) {
                        jSONObject.put("range_virtualapp", d.c("List2", ""));
                    }
                    if (h(hashMap.get("range_lock_awake_receiver"), str5)) {
                        jSONObject.put("range_lock_awake_receiver", d.c("List3", ""));
                    }
                    if (h(hashMap.get("range_network"), str5)) {
                        jSONObject.put("range_network", d.c("List4", ""));
                    }
                    if (h(hashMap.get("range_cpufrequency"), str5)) {
                        jSONObject.put("range_cpufrequency", d.c("List5", ""));
                    }
                    if (h(hashMap.get("range_batterylevel"), str5)) {
                        jSONObject.put("range_batterylevel", d.c("List6", ""));
                    }
                    if (h(hashMap.get("range_brightness"), str5)) {
                        jSONObject.put("range_brightness", d.c("List7", ""));
                    }
                    if (h(hashMap.get("osversiontags"), str5)) {
                        jSONObject.put("osversiontags", BaseInfo.getOSVersionTags());
                    }
                    if (h(hashMap.get("wifissidlist"), str5)) {
                        jSONObject.put("wifissidlist", e2.hasPermission() ? String.valueOf(BaseInfo.getWifiSSIDList()) : "");
                    }
                    if (h(hashMap.get("wifilinkspeed"), str5)) {
                        jSONObject.put("wifilinkspeed", e2.hasPermission() ? String.valueOf(BaseInfo.getWifiLinkSpeed()) : "");
                    }
                    if (!h(hashMap.get("isfakegps"), str5)) {
                        jSONObject.put("isfakegps", String.valueOf(com.jd.jdsec.a.k.c.g(context, String.valueOf(com.jd.jdsec.a.k.e.d(context)))));
                    }
                    String str62 = "1";
                    if (h(hashMap.get("haspipes"), str5)) {
                        jSONObject.put("haspipes", BaseInfo.checkPipes() ? "1" : "0");
                    }
                    if (h(hashMap.get("firstinstalltime"), str5)) {
                        jSONObject.put("firstinstalltime", "" + BaseInfo.getAppFirstInstallTime());
                    }
                    if (h(hashMap.get("lastupdatetime"), str5)) {
                        jSONObject.put("lastupdatetime", "" + BaseInfo.getAppLastUpdateTime());
                    }
                    if (h(hashMap.get("appname"), str5)) {
                        jSONObject.put("appname", BaseInfo.getAppName());
                    }
                    if (h(hashMap.get("packagename"), str5)) {
                        jSONObject.put("packagename", BaseInfo.getAppPackageName());
                    }
                    if (h(hashMap.get("signaturehash"), str5)) {
                        jSONObject.put("signaturehash", "" + BaseInfo.getAppSignatureHash());
                    }
                    if (h(hashMap.get("manufacture"), str5)) {
                        jSONObject.put("manufacture", BaseInfo.getDeviceManufacture());
                    }
                    if (h(hashMap.get("lastoaid"), str5)) {
                        jSONObject.put("lastoaid", BaseInfo.getLastOAID());
                    }
                    if (h(hashMap.get("oaid"), str5)) {
                        jSONObject.put("oaid", BaseInfo.getOAID());
                    }
                    if (h(hashMap.get("radioversion"), str5)) {
                        jSONObject.put("radioversion", BaseInfo.getRadioVersion());
                    }
                    if (h(hashMap.get("realscreensize"), str5)) {
                        String screenSize = BaseInfo.getRealScreenSize().toString();
                        d.e("screen", screenSize);
                        jSONObject.put("realscreensize", screenSize);
                    }
                    if (h(hashMap.get("romname"), str5)) {
                        jSONObject.put("romname", BaseInfo.getRomName());
                    }
                    if (h(hashMap.get("statusbarheight"), str5)) {
                        jSONObject.put("statusbarheight", "" + BaseInfo.getStatusBarHeight());
                    }
                    if (h(hashMap.get("fingerprintavailable"), str5)) {
                        jSONObject.put("fingerprintavailable", BaseInfo.isFingerprintAvailable() ? "1" : "0");
                    }
                    if (h(hashMap.get("gpsavailable"), str5)) {
                        jSONObject.put("gpsavailable", BaseInfo.isGPSAvailable() ? "1" : "0");
                    }
                    if (h(hashMap.get("hasqemudriverfile"), str5)) {
                        jSONObject.put("hasqemudriverfile", BaseInfo.isQEmuDriverFile() ? "1" : "0");
                    }
                    if (h(hashMap.get("hassdcard"), str5)) {
                        if (!BaseInfo.isStorageRemovable()) {
                            str62 = "0";
                        }
                        jSONObject.put("hassdcard", str62);
                    }
                    if (h(hashMap.get("systemVolume"), str5)) {
                        jSONObject.put("systemVolume", "" + com.jd.jdsec.a.g.l(context));
                    }
                    if (h(hashMap.get("androidSDKVersion"), str5)) {
                        jSONObject.put("androidSDKVersion", BaseInfo.getAndroidSDKVersion());
                    }
                    if (h(hashMap.get("linuxVersion"), str5)) {
                        jSONObject.put("linuxVersion", BaseInfo.getLinuxVersion());
                    }
                    if (h(hashMap.get("scaledDensity"), str5)) {
                        jSONObject.put("scaledDensity", BaseInfo.getScaledDensity());
                    }
                    if (h(hashMap.get("runningServices"), str5)) {
                        jSONObject.put("runningServices", "");
                    }
                    if (h(hashMap.get("externalStorageSize"), str5)) {
                        jSONObject.put("externalStorageSize", BaseInfo.getExternalStorageSize());
                    }
                    if (h(hashMap.get("sdCardId"), str5)) {
                        jSONObject.put("sdCardId", BaseInfo.getSDCardId());
                    }
                    if (h(hashMap.get("moce"), str5)) {
                        jSONObject.put("moce", com.jd.jdsec.a.k.d.e(context));
                    }
                    if (h(hashMap.get("appct"), str5)) {
                        jSONObject.put("appct", com.jd.jdsec.a.k.d.a());
                    }
                    if (h(hashMap.get("wlspq"), str5)) {
                        jSONObject.put("wlspq", com.jd.jdsec.a.g.A(context));
                    }
                    com.jd.jdsec.a.l.e.i("fixinfo: " + jSONObject);
                    com.jd.jdsec.a.j.a.e(jSONObject);
                    str = str4;
                    b.e(str, "\u6267\u884c\u91c7\u96c6\u8017\u65f6: " + (System.currentTimeMillis() - valueOf.longValue()));
                } catch (Exception e9) {
                    e = e9;
                    str = str4;
                }
            } catch (Exception e10) {
                e = e10;
                str = "JDSec.Security.DeviceFix";
                exc = e;
                b.b(str, "\u83b7\u53d6\u8bbe\u5907\u4fe1\u606f\u62a5\u9519: " + exc.getMessage());
                return jSONObject;
            }
        } catch (Exception e11) {
            e = e11;
        }
        return jSONObject;
    }
}
