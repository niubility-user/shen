package com.tencent.mapsdk.internal;

import android.content.Context;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.tencent.map.lib.models.GeoPoint;
import com.tencent.mapsdk.internal.ba;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class sh {

    /* renamed from: g */
    private static ConcurrentHashMap<String, w5[]> f17253g = new ConcurrentHashMap<>();

    /* renamed from: h */
    private static volatile sh f17254h = null;

    /* renamed from: i */
    public static final String f17255i = "china";

    /* renamed from: j */
    public static final String f17256j = "inland";

    /* renamed from: k */
    public static final String f17257k = "taiwan";

    /* renamed from: l */
    public static final String f17258l = "outOfChina";

    /* renamed from: m */
    private static final String f17259m = "124.240000,39.863000:127.060000,41.420000:128.320000,41.340000:128.338164,41.966811:129.070015,42.006633:129.392187,42.394602:129.544000,42.337000:129.757733,42.443019:129.915455,42.958121:130.581000,42.411000:130.664000,42.409000:130.660000,42.750000:131.056800,42.832500:131.362267,43.147780:131.359097,44.050378:131.184574,44.758711:131.911825,45.219501:133.163642,44.937724:135.144366,48.211013:135.128000,48.597000:131.071828,47.811040:130.773650,49.035551:128.751969,49.726565:125.969075,53.154351:123.347269,53.704738:120.827269,53.390754:119.713597,52.637780:120.321762,52.210396:118.926328,50.226355:117.662701,49.700280:116.622716,49.956516:115.284508,48.000368:116.104389,47.451176:118.485448,47.755809:119.550866,46.911548:117.463642,46.804509:113.640000,45.105329:111.969090,45.243847:111.267254,44.465714:111.589045,43.737317:109.905388,42.763982:107.448179,42.614694:105.337313,41.946215:100.992746,42.800196:96.838239,42.914056:95.070866,44.957807:91.816477,45.242319:91.161836,46.742245:90.130007,47.948495:88.699097,48.336174:87.883642,49.202090:86.809933,49.172099:85.459963,48.255788:85.328157,47.119427:82.919985,47.328453:82.134523,45.619623:82.396343,45.291415:81.735685,45.446091:79.797183,45.018009:80.618157,43.259401:80.112694,42.868284:80.120896,42.260590:77.976351,41.282314:76.788172,41.096389:76.197254,40.491205:75.361880,40.846808:73.365418,39.794560:73.634523,38.503352:74.669067,38.423003:74.896815,37.310540:74.429528,37.294106:74.454501,36.972073:75.734530,36.578999:75.962701,35.784605:77.852731,35.299899:78.205470,34.574291:78.973568,32.910437:78.263619,32.582183:78.649985,30.992536:81.199112,29.930890:81.625396,30.231654:85.988179,27.769037:88.674612,27.787987:88.840910,27.047339:89.241858,27.247275:89.644552,28.077447:90.426358,28.002589:91.425425,27.605415:92.127284,26.721880:93.834582,26.907073:95.855604,28.198876:97.000074,27.593593:97.491836,27.849236:97.700896,28.296779:98.301769,27.394792:98.605433,27.417494:98.502702,26.026786:97.440895,25.088802:97.485448,23.745403:98.497224,24.030523:98.809985,23.174956:99.324515,22.945024:99.099993,22.084196:100.205485,21.391178:101.003735,21.463004:101.278198,21.122876:101.931836,21.129823:101.785481,22.197373:102.650063,22.558783:103.074619,22.382137:103.979093,22.474798:104.809933,22.767793:105.399978,23.049961:106.469971,22.704082:106.610899,21.787060:107.897261,21.372454:107.490036,19.305984:109.748489,14.674666:110.039063,11.480025:107.666016,6.271618:111.752930,3.281824:112.939454,3.413421:115.018257,6.054474:118.674316,10.790140:119.164223,12.212996:119.707031,18.020528:121.959229,21.677848:122.699226,23.809795:127.303391,24.447079:127.390663,31.568056:124.335754,32.823666";

    /* renamed from: n */
    private static final String f17260n = "124.240000,39.863000:127.060000,41.420000:128.320000,41.340000:128.338164,41.966811:129.070015,42.006633:129.392187,42.394602:129.544000,42.337000:129.757733,42.443019:129.915455,42.958121:130.581000,42.411000:130.664000,42.409000:130.660000,42.750000:131.056800,42.832500:131.362267,43.147780:131.359097,44.050378:131.184574,44.758711:131.911825,45.219501:133.163642,44.937724:135.144366,48.211013:135.128000,48.597000:131.071828,47.811040:130.773650,49.035551:128.751969,49.726565:125.969075,53.154351:123.347269,53.704738:120.827269,53.390754:119.713597,52.637780:120.321762,52.210396:118.926328,50.226355:117.662701,49.700280:116.622716,49.956516:115.284508,48.000368:116.104389,47.451176:118.485448,47.755809:119.550866,46.911548:117.463642,46.804509:113.640000,45.105329:111.969090,45.243847:111.267254,44.465714:111.589045,43.737317:109.905388,42.763982:107.448179,42.614694:105.337313,41.946215:100.992746,42.800196:96.838239,42.914056:95.070866,44.957807:91.816477,45.242319:91.161836,46.742245:90.130007,47.948495:88.699097,48.336174:87.883642,49.202090:86.809933,49.172099:85.459963,48.255788:85.328157,47.119427:82.919985,47.328453:82.134523,45.619623:82.396343,45.291415:81.735685,45.446091:79.797183,45.018009:80.618157,43.259401:80.112694,42.868284:80.120896,42.260590:77.976351,41.282314:76.788172,41.096389:76.197254,40.491205:75.361880,40.846808:73.365418,39.794560:73.634523,38.503352:74.669067,38.423003:74.896815,37.310540:74.429528,37.294106:74.454501,36.972073:75.734530,36.578999:75.962701,35.784605:77.852731,35.299899:78.205470,34.574291:78.973568,32.910437:78.263619,32.582183:78.649985,30.992536:81.199112,29.930890:81.625396,30.231654:85.988179,27.769037:88.674612,27.787987:88.840910,27.047339:89.241858,27.247275:89.644552,28.077447:90.426358,28.002589:91.425425,27.605415:92.127284,26.721880:93.834582,26.907073:95.855604,28.198876:97.000074,27.593593:97.491836,27.849236:97.700896,28.296779:98.301769,27.394792:98.605433,27.417494:98.502702,26.026786:97.440895,25.088802:97.485448,23.745403:98.497224,24.030523:98.809985,23.174956:99.324515,22.945024:99.099993,22.084196:100.205485,21.391178:101.003735,21.463004:101.278198,21.122876:101.931836,21.129823:101.785481,22.197373:102.650063,22.558783:103.074619,22.382137:103.979093,22.474798:104.809933,22.767793:105.399978,23.049961:106.469971,22.704082:106.610899,21.787060:107.897261,21.372454:107.490036,19.305984:109.748489,14.674666:110.039063,11.480025:107.666016,6.271618:111.752930,3.281824:112.939454,3.413421:115.018257,6.054474:118.674316,10.790140:119.164223,12.212996:119.707031,18.020528:121.397269,20.720622:118.487770,21.778166:119.578789,24.089008:120.465831,25.121382:121.207406,25.815692:121.869972,25.849447:122.742859,25.209509:122.699226,23.809795:127.303391,24.447079:127.390663,31.568056:124.335754,32.823666";
    private static final String o = "121.397269,20.720622:118.487770,21.778166:119.578789,24.089008:120.465831,25.121382:121.207406,25.815692:121.869972,25.849447:122.742859,25.209509:122.699226,23.809795:121.959229,21.677848";

    /* renamed from: e */
    private volatile boolean f17262e;
    private ReentrantReadWriteLock a = new ReentrantReadWriteLock();
    private String b = null;

    /* renamed from: c */
    private String f17261c = null;
    private boolean d = false;

    /* renamed from: f */
    private AtomicInteger f17263f = new AtomicInteger(0);

    /* loaded from: classes9.dex */
    public class a extends ba.i<Boolean> {
        public a() {
            sh.this = r1;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Boolean call() {
            sh.this.f17262e = true;
            if (sh.this.f17263f.getAndIncrement() <= 0) {
                String d = sh.this.d();
                if (TextUtils.isEmpty(d)) {
                    sh.f17253g.put(sh.f17255i, sh.b(sh.f17259m));
                    sh.f17253g.put(sh.f17256j, sh.b(sh.f17260n));
                    sh.f17253g.put(sh.f17257k, sh.b(sh.o));
                } else {
                    sh.this.d(d);
                }
            }
            sh.this.f17262e = false;
            sh.this.d = true;
            return Boolean.TRUE;
        }
    }

    private sh() {
    }

    public static boolean a(w5 w5Var, w5[] w5VarArr) {
        if (w5VarArr == null || w5VarArr.length < 3) {
            return false;
        }
        int length = w5VarArr.length - 1;
        boolean z = false;
        for (int i2 = 0; i2 < w5VarArr.length; i2++) {
            if (((w5VarArr[i2].c() < w5Var.c() && w5VarArr[length].c() >= w5Var.c()) || (w5VarArr[length].c() < w5Var.c() && w5VarArr[i2].c() >= w5Var.c())) && (w5VarArr[i2].b() <= w5Var.b() || w5VarArr[length].b() <= w5Var.b())) {
                z ^= w5VarArr[i2].b() + (((w5Var.c() - w5VarArr[i2].c()) / (w5VarArr[length].c() - w5VarArr[i2].c())) * (w5VarArr[length].b() - w5VarArr[i2].b())) < w5Var.b();
            }
            length = i2;
        }
        return z;
    }

    public static boolean a(w5[] w5VarArr, w5[] w5VarArr2) {
        boolean z;
        int length = w5VarArr.length;
        int i2 = 0;
        boolean z2 = false;
        while (true) {
            if (i2 >= length) {
                z = false;
                break;
            }
            z2 |= a(w5VarArr[i2], w5VarArr2);
            if (z2) {
                z = true;
                break;
            }
            i2++;
        }
        boolean z3 = false;
        boolean z4 = false;
        for (w5 w5Var : w5VarArr2) {
            z3 |= a(w5Var, w5VarArr);
            if (z3) {
                z4 = true;
            }
        }
        return z || z4;
    }

    public static sh b() {
        if (f17254h == null) {
            synchronized (sh.class) {
                if (f17254h == null) {
                    f17254h = new sh();
                }
            }
        }
        return f17254h;
    }

    public static w5[] b(String str) {
        w5[] w5VarArr = null;
        if (str != null && str.length() != 0) {
            String[] split = str.split(":");
            if (split.length == 0) {
                return null;
            }
            w5VarArr = new w5[split.length];
            for (int i2 = 0; i2 < split.length; i2++) {
                String[] split2 = split[i2].split(DYConstants.DY_REGEX_COMMA);
                w5VarArr[i2] = y.b(new GeoPoint((int) (Double.parseDouble(split2[1]) * 1000000.0d), (int) (Double.parseDouble(split2[0]) * 1000000.0d)));
            }
        }
        return w5VarArr;
    }

    public void a(Context context) {
        if (this.d || this.f17262e) {
            return;
        }
        if (context != null) {
            this.b = context.getFilesDir() + "/frontiers.dat";
            this.f17261c = this.b + ".bak";
        }
        ba.a((ba.i) new a()).b((ba.d.b) Boolean.FALSE);
    }

    public void c() {
        if (this.f17263f.decrementAndGet() == 0) {
            f17253g.clear();
            this.d = false;
        }
    }

    public w5[] c(String str) {
        return f17253g.get(str);
    }

    public int d(String str) {
        w5[] b;
        w5[] b2;
        w5[] b3;
        int i2 = 0;
        if (str != null && str.length() != 0) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    if ("version".equals(next)) {
                        i2 = jSONObject.optInt(next);
                    } else {
                        w5[] b4 = b(jSONObject.optString(next));
                        if (b4 != null) {
                            f17253g.put(next, b4);
                        }
                    }
                }
                if (!f17253g.containsKey(f17255i) && (b3 = b(f17259m)) != null) {
                    f17253g.put(f17255i, b3);
                }
                if (!f17253g.containsKey(f17256j) && (b2 = b(f17260n)) != null) {
                    f17253g.put(f17256j, b2);
                }
                if (!f17253g.containsKey(f17257k) && (b = b(o)) != null) {
                    f17253g.put(f17257k, b);
                }
            } catch (Throwable unused) {
            }
        }
        return i2;
    }

    public String d() {
        FileInputStream fileInputStream;
        this.a.readLock().lock();
        try {
            fileInputStream = new FileInputStream(new File(this.b));
            try {
                String str = new String(ga.b(fileInputStream), "UTF-8");
                ga.a((Closeable) fileInputStream);
                this.a.readLock().unlock();
                return str;
            } catch (Throwable unused) {
                ga.a((Closeable) fileInputStream);
                this.a.readLock().unlock();
                return null;
            }
        } catch (Throwable unused2) {
            fileInputStream = null;
        }
    }

    public boolean e(String str) {
        this.a.writeLock().lock();
        File file = new File(this.b);
        File file2 = new File(this.f17261c);
        if (file2.exists() && !file2.delete()) {
            file2.deleteOnExit();
            return false;
        }
        FileOutputStream fileOutputStream = null;
        try {
            if (!file.exists() || file.renameTo(file2)) {
                FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                try {
                    fileOutputStream2.write(str.getBytes("UTF-8"));
                    fileOutputStream2.flush();
                    ga.a(fileOutputStream2);
                    file2.delete();
                    this.a.writeLock().unlock();
                    return true;
                } catch (Throwable unused) {
                    fileOutputStream = fileOutputStream2;
                }
            }
        } catch (Throwable unused2) {
        }
        ga.a(fileOutputStream);
        file2.renameTo(file);
        this.a.writeLock().unlock();
        return false;
    }
}
