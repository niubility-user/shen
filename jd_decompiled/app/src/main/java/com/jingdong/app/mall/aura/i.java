package com.jingdong.app.mall.aura;

import com.jingdong.aura.wrapper.AuraConfig;
import com.jingdong.common.deeplinkhelper.DeepLinkSwitch;
import com.jingdong.common.utils.ProcessUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.utils.PackageInfoUtil;

/* loaded from: classes19.dex */
public class i {

    /* loaded from: classes19.dex */
    class a implements c {
        a() {
        }
    }

    /* loaded from: classes19.dex */
    class b implements DeepLinkSwitch.SwitchListener {
        b() {
        }

        @Override // com.jingdong.common.deeplinkhelper.DeepLinkSwitch.SwitchListener
        public boolean isSwitchOpen(long j2) {
            if (j2 != AuraBundleInfos.getSwitchMaskFromBundleId(0)) {
                boolean e2 = i.e(j2);
                e.q("AuraMaiDianAuraSwitch", AuraBundleInfos.getBundleNameFromSwitchMask(j2) + CartConstant.KEY_YB_INFO_LINK + e2, "SwitchListener.isSwitchOpen", "" + PackageInfoUtil.getVersionCode());
                return e2;
            }
            return i.a();
        }
    }

    /* loaded from: classes19.dex */
    public interface c {
    }

    static /* synthetic */ boolean a() {
        return d();
    }

    public static DeepLinkSwitch.SwitchListener c() {
        return new b();
    }

    private static boolean d() {
        return AuraConfig.isUseAura();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean e(long j2) {
        String bundleNameFromSwitchMask;
        if (d() && com.jingdong.app.mall.aura.internal.c.f().h(j2) && (bundleNameFromSwitchMask = AuraBundleInfos.getBundleNameFromSwitchMask(j2)) != null) {
            return AuraConfig.isUseBundle(bundleNameFromSwitchMask);
        }
        return false;
    }

    public static void f() {
        if (ProcessUtil.isMainProcess() || ProcessUtil.isPushProcess() || ProcessUtil.isMantoProcess()) {
            boolean d = d();
            Log.d("AuraSwitch", "current Control = " + d);
            if (d) {
                com.jingdong.app.mall.aura.internal.c.f().a(new a());
            }
        }
    }
}
