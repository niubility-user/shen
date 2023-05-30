package com.jd.dynamic.b.a;

import android.text.TextUtils;
import android.util.Pair;
import com.jd.dynamic.base.DynamicPrepareFetcher;
import com.jd.dynamic.base.DynamicSdk;
import com.jingdong.common.address.AddressConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.sdk.baseinfo.BaseInfo;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class b {
    private final com.jd.dynamic.b.a.a a;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.jd.dynamic.b.a.b$b  reason: collision with other inner class name */
    /* loaded from: classes13.dex */
    public static class C0067b {
        private static final b a = new b();
    }

    private b() {
        this.a = new com.jd.dynamic.b.a.a(DynamicSdk.getEngine().getAbConfig());
    }

    public static b o() {
        return C0067b.a;
    }

    public boolean A() {
        if (DynamicSdk.getEngine().isDebug()) {
            return true;
        }
        return "1".equals(this.a.a("lifeModule", "isAfterParse"));
    }

    public boolean B() {
        if (DynamicSdk.getEngine().isDebug()) {
            return true;
        }
        return "1".equals(this.a.b("preParse", "isPreParseOpen", "1"));
    }

    public boolean C() {
        return E() || D();
    }

    public boolean D() {
        return "1".equals(this.a.a("dy-fast-init", "fast_init"));
    }

    public boolean E() {
        return "2".equals(this.a.a("dy-fast-init", "fast_init"));
    }

    public Pair<String, String> F() {
        String a2 = this.a.a("dy-fast-init", "fast_module");
        String a3 = this.a.a("dy-fast-init", "fast_template");
        return (TextUtils.isEmpty(a2) || TextUtils.isEmpty(a3)) ? new Pair<>("", "") : new Pair<>(a2, a3);
    }

    public int G() {
        String a2 = this.a.a("dy-fast-init", "fast_times");
        if (TextUtils.isEmpty(a2)) {
            return 1;
        }
        try {
            return Math.max(Integer.parseInt(a2), 1);
        } catch (NumberFormatException unused) {
            return 1;
        }
    }

    public boolean H() {
        return true;
    }

    public boolean I() {
        String a2 = this.a.a("downloadFileFix", "fix");
        if (TextUtils.isEmpty(a2)) {
            return true;
        }
        return "1".equals(a2);
    }

    public int J() {
        String a2 = this.a.a("downloadRetry", "maxRetryCount");
        if (TextUtils.isEmpty(a2)) {
            return 0;
        }
        return Integer.parseInt(a2);
    }

    public boolean K() {
        return "1".equals(this.a.a("downloadRetry", "enableDownloadRetry"));
    }

    public String L() {
        return this.a.b("downgrade", "dynamic", "1");
    }

    public boolean M() {
        return "1".equals(this.a.a("cleanFile", "clean"));
    }

    public boolean N() {
        if (DynamicSdk.getEngine().isDebug()) {
            String a2 = this.a.a("debug", "force_backup");
            if (TextUtils.isEmpty(a2)) {
                return false;
            }
            return "1".equals(a2);
        }
        return false;
    }

    public boolean O() {
        return true;
    }

    public boolean P() {
        return "1".equals(this.a.a("businessDataMta", "businessDataUpload"));
    }

    public boolean Q() {
        return "1".equals(this.a.a("lifeModule", JshopConst.JSKEY_CATE_OPEN));
    }

    public boolean R() {
        return "1".equals(this.a.b("sharedRecyclerviewPool", "useSharedPool", "1"));
    }

    public boolean S() {
        return "1".equals(this.a.b("iconViewFix", "fix", "1"));
    }

    public int T() {
        String a2 = this.a.a("report", "count");
        if (TextUtils.isEmpty(a2)) {
            return 0;
        }
        try {
            return Integer.parseInt(a2);
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    public long a() {
        if (TextUtils.isEmpty(this.a.a("report", "gap"))) {
            return 0L;
        }
        try {
            return Integer.parseInt(r0);
        } catch (NumberFormatException unused) {
            return 0L;
        }
    }

    public boolean b() {
        int androidSDKVersion = BaseInfo.getAndroidSDKVersion();
        return "1".equals((!BaseInfo.getDeviceBrand().toLowerCase().contains("oppo") || androidSDKVersion < 21 || androidSDKVersion >= 23) ? this.a.a("textViewFix", "singleLine") : this.a.b("textViewFix", "singleLine", "1"));
    }

    public boolean c() {
        return "1".equals(this.a.b("collectionViewFix", "fix", "1"));
    }

    public boolean d() {
        return "1".equals(this.a.b("jsPostMain", JshopConst.JSKEY_CATE_OPEN, "1"));
    }

    public boolean e() {
        return "1".equals(this.a.a("cacheUseObject", JshopConst.JSKEY_CATE_OPEN));
    }

    public boolean f() {
        return "1".equals(this.a.b("autoPrepareModules", "enable", "0"));
    }

    public boolean g() {
        return "1".equals(this.a.a("fixImageWH", JshopConst.JSKEY_CATE_OPEN));
    }

    public boolean h() {
        return "1".equals(this.a.b("fixJSAddress", JshopConst.JSKEY_CATE_OPEN, "1"));
    }

    public boolean i() {
        return "1".equals(this.a.a("fixJSAddress", "trans"));
    }

    public boolean j() {
        return "1".equals(this.a.b("fixJSAddress", "retain", "1"));
    }

    public boolean k() {
        return "1".equals(this.a.a("fixJSAddress", "useGroup"));
    }

    public boolean l() {
        return "1".equals(this.a.b("fixJSAddress", "fixCalcWidth", "1"));
    }

    public boolean m() {
        return "1".equals(this.a.a("fixJSAddress", AddressConstant.INTENT_EXTAS_CHECK_ADDRESS_KEY));
    }

    public boolean n() {
        if (DynamicSdk.getEngine().isDebug()) {
            return true;
        }
        return "1".equals(this.a.a("fixJSAddress", "isWithActivity"));
    }

    public JSONObject p(String str) {
        String a2 = this.a.a("downgrade", DynamicPrepareFetcher.KEY_PREPARE_MODULES);
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        try {
            return new JSONObject(a2).optJSONObject(str);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public boolean q(String str, String str2) {
        if (!TextUtils.isEmpty(str) && "1".equals(this.a.a("expCache", JshopConst.JSKEY_CATE_OPEN))) {
            String a2 = this.a.a("expCache", "sysCode");
            if (!TextUtils.isEmpty(a2) && a2.contains(str)) {
                if (TextUtils.isEmpty(str2)) {
                    return true;
                }
                String a3 = this.a.a("expCache", "biz");
                if (TextUtils.isEmpty(a3)) {
                    return true;
                }
                return a3.contains(str2);
            }
            return false;
        }
        return false;
    }

    public boolean r() {
        if (DynamicSdk.getEngine().isDebug()) {
            return true;
        }
        return "1".equals(this.a.b("taskInThread", "mtaInThread", "1"));
    }

    public boolean s(String str) {
        if (TextUtils.isEmpty(this.a.a("viewAttrAlign", str))) {
            return true;
        }
        return !"0".equals(r3);
    }

    public boolean t(String str, String str2) {
        return false;
    }

    public String u(String str, String str2) {
        com.jd.dynamic.b.a.a aVar = this.a;
        return aVar == null ? "" : aVar.a(str, str2);
    }

    public boolean v() {
        if (DynamicSdk.getEngine().isDebug()) {
            return true;
        }
        return "1".equals(this.a.b("tagViewOptimize", "isEnable", "1"));
    }

    public boolean w(String str) {
        return "1".equals(this.a.b("viewAttrAlign", str, "1"));
    }

    public boolean x() {
        if (DynamicSdk.getEngine().isDebug()) {
            return true;
        }
        return "1".equals(this.a.a("tagViewOptimize", "isOpen"));
    }

    public boolean y() {
        if (DynamicSdk.getEngine().isDebug()) {
            return true;
        }
        return "1".equals(this.a.b("refreshViewOptimize", "refreshOnlyVisible", "1"));
    }

    public boolean z() {
        if (DynamicSdk.getEngine().isDebug()) {
            return true;
        }
        return "1".equals(this.a.a("preParse", "isUseInnerPreParse"));
    }
}
