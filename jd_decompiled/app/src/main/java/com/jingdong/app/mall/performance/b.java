package com.jingdong.app.mall.performance;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import java.text.DecimalFormat;
import java.util.HashMap;
import javax.annotation.Nullable;
import performance.jd.jdreportperformance.entity.StategyEntity;

/* loaded from: classes4.dex */
public class b {

    /* renamed from: f  reason: collision with root package name */
    private static volatile b f11523f;
    private long a;
    private ReactMarker.MarkerListener b;

    /* renamed from: c  reason: collision with root package name */
    private HashMap<String, HashMap<String, String>> f11524c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f11525e;

    /* loaded from: classes4.dex */
    class a implements ReactMarker.MarkerListener {
        a() {
        }

        @Override // com.facebook.react.bridge.ReactMarker.MarkerListener
        public void logMarker(ReactMarkerConstants reactMarkerConstants, @Nullable String str, int i2) {
            b bVar = b.this;
            bVar.d = bVar.o();
            if (b.this.d == null) {
                return;
            }
            b.this.a = System.currentTimeMillis();
            int i3 = C0371b.a[reactMarkerConstants.ordinal()];
            if (i3 == 1) {
                b bVar2 = b.this;
                if (bVar2.l(bVar2.d)) {
                    b bVar3 = b.this;
                    bVar3.k(bVar3.d);
                }
                b bVar4 = b.this;
                bVar4.u(bVar4.d, "moduleName", b.this.d);
                b bVar5 = b.this;
                bVar5.u(bVar5.d, "startTime", String.valueOf(b.this.a));
                b bVar6 = b.this;
                bVar6.u(bVar6.d, "rnVersion", "0.59.9");
                b bVar7 = b.this;
                bVar7.u(bVar7.d, "memBefore", b.this.n());
            } else if (i3 == 2) {
                b bVar8 = b.this;
                bVar8.u(bVar8.d, "preLoadEnd", String.valueOf(b.this.a));
            } else if (i3 == 3) {
                b bVar9 = b.this;
                bVar9.u(bVar9.d, "jsLoadEnd", String.valueOf(b.this.a));
            } else if (i3 != 4) {
            } else {
                b bVar10 = b.this;
                bVar10.u(bVar10.d, "mountEnd", String.valueOf(b.this.a));
                b bVar11 = b.this;
                bVar11.u(bVar11.d, "memAfter", b.this.n());
                b bVar12 = b.this;
                bVar12.u(bVar12.d, "sessionId", System.currentTimeMillis() + "-" + i2);
                b bVar13 = b.this;
                String str2 = bVar13.d;
                b bVar14 = b.this;
                bVar13.u(str2, "moduleVersion", bVar14.p(bVar14.d));
                b bVar15 = b.this;
                bVar15.r(bVar15.d);
            }
        }
    }

    /* renamed from: com.jingdong.app.mall.performance.b$b  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    static /* synthetic */ class C0371b {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[ReactMarkerConstants.values().length];
            a = iArr;
            try {
                iArr[ReactMarkerConstants.REACT_CONTEXT_THREAD_START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[ReactMarkerConstants.PRE_RUN_JS_BUNDLE_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[ReactMarkerConstants.NATIVE_MODULE_INITIALIZE_START.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[ReactMarkerConstants.CONTENT_APPEARED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private b() {
        if (this.f11524c == null) {
            this.f11524c = new HashMap<>();
        } else {
            j();
        }
    }

    private void j() {
        HashMap<String, HashMap<String, String>> hashMap = this.f11524c;
        if (hashMap == null || hashMap.size() <= 0) {
            return;
        }
        this.f11524c.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k(String str) {
        HashMap<String, HashMap<String, String>> hashMap = this.f11524c;
        if (hashMap == null || hashMap.size() <= 0 || !this.f11524c.containsKey(str)) {
            return;
        }
        this.f11524c.remove(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean l(String str) {
        HashMap<String, HashMap<String, String>> hashMap = this.f11524c;
        if (hashMap == null || hashMap.size() <= 0) {
            return false;
        }
        return this.f11524c.containsKey(str);
    }

    public static synchronized b m() {
        b bVar;
        synchronized (b.class) {
            if (f11523f == null) {
                f11523f = new b();
            }
            bVar = f11523f;
        }
        return bVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String p(String str) {
        Intent intent;
        IMyActivity currentMyActivity = BaseFrameUtil.getInstance().getCurrentMyActivity();
        if (!(currentMyActivity instanceof Activity) || (intent = ((Activity) currentMyActivity).getIntent()) == null || intent.getExtras() == null) {
            return null;
        }
        String string = intent.getExtras().getString(JDReactConstant.IntentConstant.MODULE_NAME);
        if (TextUtils.isEmpty(str) || !str.equals(string)) {
            return null;
        }
        Log.d("RNMonitorListener", "Module Name: " + string + "  -  Version: " + intent.getExtras().getString("version"));
        return intent.getExtras().getString("version");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void r(String str) {
        try {
            HashMap<String, HashMap<String, String>> hashMap = this.f11524c;
            if (hashMap != null && hashMap.containsKey(str)) {
                HashMap<String, String> hashMap2 = this.f11524c.get(str);
                hashMap2.put("occurTime", new DecimalFormat("0.000000").format(System.currentTimeMillis() / 1000));
                hashMap2.put("typeId", "10");
                hashMap2.put("chId", "3");
                hashMap2.put("rtype", "auto");
                PerformanceReporter.reportData(hashMap2);
            }
        } finally {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u(String str, String str2, String str3) {
        HashMap<String, HashMap<String, String>> hashMap;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || (hashMap = this.f11524c) == null) {
            return;
        }
        if (hashMap.containsKey(str)) {
            HashMap<String, String> hashMap2 = this.f11524c.get(str);
            if (hashMap2 != null) {
                hashMap2.put(str2, str3);
                return;
            }
            return;
        }
        HashMap<String, String> hashMap3 = new HashMap<>();
        hashMap3.put(str2, str3);
        this.f11524c.put(str, hashMap3);
    }

    public String n() {
        long freeMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(freeMemory);
        return TextUtils.isEmpty(sb.toString()) ? "" : String.valueOf(freeMemory);
    }

    public String o() {
        Intent intent;
        IMyActivity currentMyActivity = BaseFrameUtil.getInstance().getCurrentMyActivity();
        if (!(currentMyActivity instanceof Activity) || (intent = ((Activity) currentMyActivity).getIntent()) == null || intent.getExtras() == null) {
            return null;
        }
        return intent.getExtras().getString(JDReactConstant.IntentConstant.MODULE_NAME);
    }

    public String q(String str, String str2) {
        HashMap<String, String> hashMap;
        HashMap<String, HashMap<String, String>> hashMap2 = this.f11524c;
        return (hashMap2 == null || hashMap2.size() <= 0 || !this.f11524c.containsKey(str) || (hashMap = this.f11524c.get(str)) == null || hashMap.size() <= 0 || !hashMap.containsKey(str2)) ? "" : hashMap.get(str2);
    }

    public synchronized void s() {
        if (!SwitchQueryFetcher.getSwitchBooleanValue(SwitchQueryFetcher.PerformanceReportForRN, false)) {
            Log.d("RNMonitorListener", "performanceReportForRN is false");
            t();
            return;
        }
        StategyEntity stategyEntitiy = PerformanceReporter.getStategyEntitiy(JdSdk.getInstance().getApplicationContext(), "10", "3");
        if (stategyEntitiy != null && "1".equals(stategyEntitiy.ret)) {
            if (!this.f11525e) {
                a aVar = new a();
                this.b = aVar;
                ReactMarker.addListener(aVar);
                this.f11525e = true;
            }
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("StategyEntity ret: ");
        sb.append(stategyEntitiy == null ? DYConstants.DY_NULL_STR : stategyEntitiy.ret);
        Log.d("RNMonitorListener", sb.toString());
        t();
    }

    public synchronized void t() {
        ReactMarker.MarkerListener markerListener = this.b;
        if (markerListener != null) {
            ReactMarker.removeListener(markerListener);
        }
        HashMap<String, HashMap<String, String>> hashMap = this.f11524c;
        if (hashMap != null) {
            hashMap.clear();
            this.f11524c = null;
        }
        this.f11525e = false;
    }
}
