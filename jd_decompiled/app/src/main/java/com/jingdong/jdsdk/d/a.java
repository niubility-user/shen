package com.jingdong.jdsdk.d;

import android.content.Context;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.runTimeConfig.ConfigUtil;
import com.jingdong.common.unification.router.config.JDRouterConfig;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.un.lib.popup.JDTopPopupWindowHelper;

/* loaded from: classes14.dex */
public class a {

    /* renamed from: com.jingdong.jdsdk.d.a$a  reason: collision with other inner class name */
    /* loaded from: classes14.dex */
    class C0501a implements JDRouterConfig.RouterOpen {
        C0501a() {
        }

        @Override // com.jingdong.common.unification.router.config.JDRouterConfig.RouterOpen
        public boolean isRouterOpen() {
            return a.b();
        }
    }

    /* loaded from: classes14.dex */
    class b implements JDRouterConfig.RouterLog {
        b() {
        }

        @Override // com.jingdong.common.unification.router.config.JDRouterConfig.RouterLog
        public void d(String str, String str2) {
            if (OKLog.D) {
                OKLog.d(str, str2);
            }
        }

        @Override // com.jingdong.common.unification.router.config.JDRouterConfig.RouterLog
        public void e(String str, String str2) {
            if (OKLog.D) {
                OKLog.e(str, str2);
            }
        }

        @Override // com.jingdong.common.unification.router.config.JDRouterConfig.RouterLog
        public void i(String str, String str2) {
            if (OKLog.D) {
                OKLog.i(str, str2);
            }
        }
    }

    /* loaded from: classes14.dex */
    class c implements JDRouterConfig.RouterMta {
        c() {
        }

        @Override // com.jingdong.common.unification.router.config.JDRouterConfig.RouterMta
        public void onMtaEvent(Context context, String str, String str2, String str3) {
            JDMtaUtils.sendCommonData(context, str, str2, "", str3, "", "", "");
        }
    }

    /* loaded from: classes14.dex */
    class d implements JDRouterConfig.RouterClassLoader {
        d() {
        }

        @Override // com.jingdong.common.unification.router.config.JDRouterConfig.RouterClassLoader
        public Class<?> loadClass(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            try {
                return JdSdk.getInstance().getApplication().getClassLoader().loadClass(str);
            } catch (ClassNotFoundException e2) {
                if (OKLog.D) {
                    e2.printStackTrace();
                    return null;
                }
                return null;
            }
        }
    }

    public static void a(boolean z) {
        JDRouterConfig.config(JDRouterConfig.ConfigBuilder.create(z, JDTopPopupWindowHelper.CUSTOM_ROUTER_TYPE).routerClassLoader(new d()).routerMta(new c()).routerLog(new b()).routerOpen(new C0501a()));
        JDRouterConfig.registerPackage("com.jingdong.common.unification.router.module");
        JDRouterConfig.register("JDCartModule", "com.jd.lib.cart.router.JDCartModuleNew");
        JDRouterConfig.register("JDCartCleanDialogModule", "com.jd.lib.cart.router.JDCartCleanDialogModule");
        JDRouterConfig.register("JDAddressModule", "com.jingdong.common.ui.address.JDAddressRouter");
        JDRouterConfig.register("JDPickUpSiteModule", "com.jingdong.common.ui.address.JDPickUpSiteRouter");
        JDRouterConfig.register("PerRecRouterImpl", "com.jingdong.app.mall.recommend.PerRecRouterImpl");
        JDRouterConfig.register("PasteStateRouterImpl", "com.jingdong.app.mall.global.PasteStateRouterImpl");
        JDRouterConfig.register("JDMainPageModule", "com.jingdong.app.mall.home.JDHomeRouter");
        JDRouterConfig.register("JDMPRouter", "com.jd.manto.router.MantoRouter");
        JDRouterConfig.register("JDReactNativeRouter", "com.jingdong.common.jdreactFramework.utils.JDReactNativeRouter");
        JDRouterConfig.register("JDMessageNoticeManager", "com.jingdong.common.messagepop.JDMessageNoticeManager");
        JDRouterConfig.register("JdPermissionModule", "com.jingdong.common.permission.JdPermissionRouter");
    }

    public static boolean b() {
        return !DYConstants.DY_TRUE.equals(ConfigUtil.getStringFromPreference(ConfigUtil.KEY_UNITED_JUMP_DEGRADE_SWITCH).trim());
    }
}
