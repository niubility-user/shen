package com.jingdong.common.unification.router.module;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.deeplinkhelper.DeepLinkAddressHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkHomePageHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkMyWalletHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkPersonalHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkSettingHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkUserManagerHelper;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.login.ILogin;
import com.jingdong.common.login.LoginConstans;
import com.jingdong.common.login.LoginUserHelper;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.JDRouterUtil;
import com.jingdong.common.unification.router.builder.RouterEntry;
import java.util.Iterator;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JDMyJdModule implements IJDModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void a(Context context, Bundle bundle, CallBackListener callBackListener, String str) {
        DeepLinkPersonalHelper.startJoyWelfareActivity(context, bundle);
        JumpUtil.finishInterfaceActivity(context);
        if (callBackListener != null) {
            callBackListener.onComplete();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void b(Context context, Bundle bundle, CallBackListener callBackListener, String str) {
        DeepLinkPersonalHelper.startPersonalChannelActivity(context, bundle);
        JumpUtil.finishInterfaceActivity(context);
        if (callBackListener != null) {
            callBackListener.onComplete();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void c(Context context, Bundle bundle, CallBackListener callBackListener, String str) {
        DeepLinkPersonalHelper.startPersonalMoreChannelActivity(context, bundle);
        JumpUtil.finishInterfaceActivity(context);
        if (callBackListener != null) {
            callBackListener.onComplete();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void d(Context context, Bundle bundle, CallBackListener callBackListener, String str) {
        DeepLinkPersonalHelper.startPersonalSearchChannelActivity(context, bundle);
        JumpUtil.finishInterfaceActivity(context);
        if (callBackListener != null) {
            callBackListener.onComplete();
        }
    }

    @Override // com.jingdong.common.unification.router.module.IJDModule
    public void show(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
    }

    public void showAboutVC(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null) {
            JDRouterUtil.callBackError(callBackListener, 703);
            return;
        }
        DeepLinkSettingHelper.startAboutActivity(context, bundle);
        if (callBackListener != null) {
            callBackListener.onComplete();
        }
    }

    public void showAddressListVC(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null) {
            JDRouterUtil.callBackError(callBackListener, 703);
            return;
        }
        DeepLinkAddressHelper.startNewEasyAddressListDynamic(context, bundle);
        if (callBackListener != null) {
            callBackListener.onComplete();
        }
    }

    public void showFeedbackVC(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null) {
            JDRouterUtil.callBackError(callBackListener, 703);
            return;
        }
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            String next = keys.next();
            bundle.putString(next, jSONObject.optString(next));
        }
        DeepLinkSettingHelper.startFeedbackActivity(context, bundle);
        if (callBackListener != null) {
            callBackListener.onComplete();
        }
    }

    public void showGameInteraction(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null) {
            JDRouterUtil.callBackError(callBackListener, 703);
            return;
        }
        if (jSONObject != null) {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                if (bundle == null) {
                    bundle = new Bundle();
                }
                String next = keys.next();
                bundle.putString(next, jSONObject.optString(next));
            }
        }
        DeepLinkCommonHelper.startMoreGameInteractionActivity(context, bundle);
        JumpUtil.finishInterfaceActivity(context);
        if (callBackListener != null) {
            callBackListener.onComplete();
        }
    }

    public void showIDCardListVC(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null) {
            JDRouterUtil.callBackError(callBackListener, 703);
            return;
        }
        DeepLinkUserManagerHelper.startCardListActivity(context, bundle);
        JumpUtil.finishInterfaceActivity(context);
        if (callBackListener != null) {
            callBackListener.onComplete();
        }
    }

    public void showInnovationLabPage(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null) {
            JDRouterUtil.callBackError(callBackListener, 703);
            return;
        }
        DeepLinkUserManagerHelper.startInnovationLabActivity(context, bundle);
        JumpUtil.finishInterfaceActivity(context);
        if (callBackListener != null) {
            callBackListener.onComplete();
        }
    }

    public void showJoyWelfare(final Context context, JSONObject jSONObject, final Bundle bundle, final CallBackListener callBackListener) {
        if (context == null) {
            JDRouterUtil.callBackError(callBackListener, 703);
            return;
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (jSONObject != null) {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                bundle.putString(next, jSONObject.optString(next));
            }
        }
        Bundle bundle2 = new Bundle();
        bundle2.putInt(LoginConstans.NEED_REFRESH_MODE, LoginConstans.REFRESH_MODE_VALUE);
        DeepLinkLoginHelper.startLoginActivity(context, bundle2, new ILogin() { // from class: com.jingdong.common.unification.router.module.c
            @Override // com.jingdong.common.login.ILogin
            public final void onSuccess(String str) {
                JDMyJdModule.a(context, bundle, callBackListener, str);
            }
        }, "MY_JD");
    }

    public void showModeSwitchPage(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null) {
            JDRouterUtil.callBackError(callBackListener, 703);
            return;
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (jSONObject != null) {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                bundle.putString(next, jSONObject.optString(next));
            }
        }
        DeepLinkCommonHelper.startSwitchModeActivity(context, bundle);
        JumpUtil.finishInterfaceActivity(context);
    }

    public void showMoreEntranceList(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null) {
            JDRouterUtil.callBackError(callBackListener, 703);
            return;
        }
        if (jSONObject != null) {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                if (bundle == null) {
                    bundle = new Bundle();
                }
                String next = keys.next();
                bundle.putString(next, jSONObject.optString(next));
            }
        }
        DeepLinkCommonHelper.startMoreEntranceListActivity(context, bundle);
        JumpUtil.finishInterfaceActivity(context);
        if (callBackListener != null) {
            callBackListener.onComplete();
        }
    }

    public void showMyBankCard(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        CallBackListener callBackListener;
        if (context == null) {
            if (routerEntry == null || (callBackListener = routerEntry.callBackListener) == null) {
                return;
            }
            JDRouterUtil.callBackError(callBackListener, 703);
            return;
        }
        if (jDJSONObject != null && jDJSONObject.keySet() != null) {
            if (routerEntry == null) {
                routerEntry = new RouterEntry();
            }
            if (routerEntry.extraBundle == null) {
                routerEntry.extraBundle = new Bundle();
            }
            for (String str : jDJSONObject.keySet()) {
                routerEntry.extraBundle.putString(str, jDJSONObject.optString(str));
            }
        }
        if (routerEntry != null) {
            DeepLinkCommonHelper.startBindingCardListActivity(context, routerEntry.extraBundle);
            CallBackListener callBackListener2 = routerEntry.callBackListener;
            if (callBackListener2 != null) {
                callBackListener2.onComplete();
                return;
            }
            return;
        }
        DeepLinkCommonHelper.startBindingCardListActivity(context, null);
    }

    public void showMyGiftCard(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        CallBackListener callBackListener;
        if (context == null) {
            if (routerEntry == null || (callBackListener = routerEntry.callBackListener) == null) {
                return;
            }
            JDRouterUtil.callBackError(callBackListener, 703);
            return;
        }
        if (jDJSONObject != null && jDJSONObject.keySet() != null) {
            if (routerEntry == null) {
                routerEntry = new RouterEntry();
            }
            if (routerEntry.extraBundle == null) {
                routerEntry.extraBundle = new Bundle();
            }
            for (String str : jDJSONObject.keySet()) {
                routerEntry.extraBundle.putString(str, jDJSONObject.optString(str));
            }
        }
        if (routerEntry != null) {
            DeepLinkCommonHelper.startMyGiftCardActivity(context, routerEntry.extraBundle);
            CallBackListener callBackListener2 = routerEntry.callBackListener;
            if (callBackListener2 != null) {
                callBackListener2.onComplete();
                return;
            }
            return;
        }
        DeepLinkCommonHelper.startMyGiftCardActivity(context, null);
    }

    public void showMyJdHomeVC(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null) {
            JDRouterUtil.callBackError(callBackListener, 703);
            return;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putString("target_page", "personal");
        DeepLinkCommonHelper.startActivity(context, DeepLinkCommonHelper.HOST_JD_TASK_CLEAR_ACTIVITY, bundle2, true, 67108864, false, "");
        if (callBackListener != null) {
            callBackListener.onComplete();
        }
    }

    public void showMyToolsPage(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null) {
            JDRouterUtil.callBackError(callBackListener, 703);
            return;
        }
        if (jSONObject != null) {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                if (bundle == null) {
                    bundle = new Bundle();
                }
                String next = keys.next();
                bundle.putString(next, jSONObject.optString(next));
            }
        }
        DeepLinkPersonalHelper.startPersonalToolsActivity(context, bundle);
        JumpUtil.finishInterfaceActivity(context);
        if (callBackListener != null) {
            callBackListener.onComplete();
        }
    }

    public void showNewFeedback(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context != null && jSONObject != null) {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                if (bundle == null) {
                    bundle = new Bundle();
                }
                String next = keys.next();
                bundle.putString(next, jSONObject.optString(next));
            }
            DeepLinkSettingHelper.startNewFeedBackActivity(context, bundle);
            if (callBackListener != null) {
                callBackListener.onComplete();
                return;
            }
            return;
        }
        JDRouterUtil.callBackError(callBackListener, 703);
    }

    public void showNewPlatformWallet(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null) {
            JDRouterUtil.callBackError(callBackListener, 703);
            return;
        }
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            String next = keys.next();
            bundle.putString(next, jSONObject.optString(next));
        }
        DeepLinkMyWalletHelper.startPlatformWalletNewActivity(context, bundle);
        if (callBackListener != null) {
            callBackListener.onComplete();
        }
    }

    public void showPersonalCardList(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null) {
            JDRouterUtil.callBackError(callBackListener, 703);
            return;
        }
        if (jSONObject != null) {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                if (bundle == null) {
                    bundle = new Bundle();
                }
                String next = keys.next();
                bundle.putString(next, jSONObject.optString(next));
            }
        }
        DeepLinkPersonalHelper.startPersonalCardListActivity(context, bundle);
        JumpUtil.finishInterfaceActivity(context);
        if (callBackListener != null) {
            callBackListener.onComplete();
        }
    }

    public void showPersonalChannel(final Context context, JSONObject jSONObject, final Bundle bundle, final CallBackListener callBackListener) {
        if (context == null) {
            JDRouterUtil.callBackError(callBackListener, 703);
            return;
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (jSONObject != null) {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                bundle.putString(next, jSONObject.optString(next));
            }
        }
        Bundle bundle2 = new Bundle();
        bundle2.putInt(LoginConstans.NEED_REFRESH_MODE, LoginConstans.REFRESH_MODE_VALUE);
        DeepLinkLoginHelper.startLoginActivity(context, bundle2, new ILogin() { // from class: com.jingdong.common.unification.router.module.d
            @Override // com.jingdong.common.login.ILogin
            public final void onSuccess(String str) {
                JDMyJdModule.b(context, bundle, callBackListener, str);
            }
        }, "MY_JD");
    }

    public void showPersonalHomePageWithRequestCode(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context != null && jSONObject != null) {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                bundle.putString(next, jSONObject.optString(next));
            }
            int i2 = bundle.getInt("requestCode", 404);
            if (i2 == -1) {
                DeepLinkHomePageHelper.startHomePageActivity(context, bundle);
            } else if (i2 != 404 && (context instanceof Activity)) {
                DeepLinkHomePageHelper.startHomePageActivityForResult((Activity) context, bundle, i2);
            } else {
                JDRouterUtil.callBackError(callBackListener, 703);
                return;
            }
            JDRouterUtil.callBackComplete(callBackListener);
            return;
        }
        JDRouterUtil.callBackError(callBackListener, 703);
    }

    public void showPersonalInfoSecVC(final Context context, JSONObject jSONObject, final Bundle bundle, final CallBackListener callBackListener) {
        if (context == null) {
            JDRouterUtil.callBackError(callBackListener, 703);
            return;
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (jSONObject != null) {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                bundle.putString(next, jSONObject.optString(next));
            }
        }
        LoginUserHelper.getInstance().executeLoginRunnable((IMyActivity) context, new Runnable() { // from class: com.jingdong.common.unification.router.module.JDMyJdModule.1
            @Override // java.lang.Runnable
            public void run() {
                DeepLinkUserManagerHelper.startUserSecondActivity((IMyActivity) context, bundle);
                CallBackListener callBackListener2 = callBackListener;
                if (callBackListener2 != null) {
                    callBackListener2.onComplete();
                }
            }
        });
    }

    public void showPersonalInfoVC(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null) {
            JDRouterUtil.callBackError(callBackListener, 703);
            return;
        }
        DeepLinkCommonHelper.startUserManagerActivity(context, null);
        if (callBackListener != null) {
            callBackListener.onComplete();
        }
    }

    public void showPersonalMoreChannel(final Context context, JSONObject jSONObject, final Bundle bundle, final CallBackListener callBackListener) {
        if (context == null) {
            JDRouterUtil.callBackError(callBackListener, 703);
            return;
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (jSONObject != null) {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                bundle.putString(next, jSONObject.optString(next));
            }
        }
        DeepLinkLoginHelper.startLoginActivity(context, null, new ILogin() { // from class: com.jingdong.common.unification.router.module.b
            @Override // com.jingdong.common.login.ILogin
            public final void onSuccess(String str) {
                JDMyJdModule.c(context, bundle, callBackListener, str);
            }
        }, "MY_JD");
    }

    public void showPersonalSearchChannel(final Context context, JSONObject jSONObject, final Bundle bundle, final CallBackListener callBackListener) {
        if (context == null) {
            JDRouterUtil.callBackError(callBackListener, 703);
            return;
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (jSONObject != null) {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                bundle.putString(next, jSONObject.optString(next));
            }
        }
        DeepLinkLoginHelper.startLoginActivity(context, null, new ILogin() { // from class: com.jingdong.common.unification.router.module.a
            @Override // com.jingdong.common.login.ILogin
            public final void onSuccess(String str) {
                JDMyJdModule.d(context, bundle, callBackListener, str);
            }
        }, "MY_JD");
    }

    public void showPlatformFeedBackVC(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context != null && jSONObject != null) {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                if (bundle == null) {
                    bundle = new Bundle();
                }
                String next = keys.next();
                bundle.putString(next, jSONObject.optString(next));
            }
            DeepLinkSettingHelper.startPlatformFeedBackActivity(context, bundle);
            if (callBackListener != null) {
                callBackListener.onComplete();
                return;
            }
            return;
        }
        JDRouterUtil.callBackError(callBackListener, 703);
    }

    public void showPlatformWallet(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null) {
            JDRouterUtil.callBackError(callBackListener, 703);
            return;
        }
        DeepLinkMyWalletHelper.startPlatformWalletActivity(context, bundle);
        if (callBackListener != null) {
            callBackListener.onComplete();
        }
    }

    public void showSetTextScaleMode(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null) {
            JDRouterUtil.callBackError(callBackListener, 703);
            return;
        }
        DeepLinkSettingHelper.startSetTextScaleModePage(context, bundle);
        JumpUtil.finishInterfaceActivity(context);
        if (callBackListener != null) {
            callBackListener.onComplete();
        }
    }

    public void showSettingVC(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null) {
            JDRouterUtil.callBackError(callBackListener, 703);
            return;
        }
        DeepLinkSettingHelper.startSettingActivity(context, bundle);
        JumpUtil.finishInterfaceActivity(context);
        if (callBackListener != null) {
            callBackListener.onComplete();
        }
    }
}
