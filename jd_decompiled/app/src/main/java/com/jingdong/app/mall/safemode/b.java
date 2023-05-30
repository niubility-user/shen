package com.jingdong.app.mall.safemode;

import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.utils.ABTestUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.utils.PackageInfoUtil;
import java.io.File;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class b extends com.jingdong.app.mall.aura.internal.d {
    private File a;

    /* renamed from: com.jingdong.app.mall.safemode.b$b  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    private static class C0376b {
        public static b a = new b();
    }

    public static b a() {
        return C0376b.a;
    }

    public int b() {
        JSONObject b = h.b(this.a);
        return Math.max(b != null ? b.optInt("triggerCount") : 3, 3);
    }

    public boolean c() {
        return "1".equals(JDMobileConfig.getInstance().getConfig(ABTestUtils.KEY_BASE_ARCH_CONFIG_NAMESPACE, "safeModeSwitcher", "enable", "1"));
    }

    private b() {
        File file = new File(JdSdk.getInstance().getApplication().getCacheDir() + "/safe_mode_config" + CartConstant.KEY_YB_INFO_LINK + PackageInfoUtil.getVersionCode() + ".dat");
        this.a = file;
        if (file.exists()) {
            return;
        }
        try {
            this.a.createNewFile();
        } catch (Throwable unused) {
        }
    }
}
