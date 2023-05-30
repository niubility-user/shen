package com.jingdong.common.unification.router.module;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.common.deeplinkhelper.DeepLinkFriendHelper;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.login.LoginUserHelper;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.JDRouterUtil;
import java.util.Iterator;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JDFriendModule {
    /* JADX INFO: Access modifiers changed from: private */
    public Bundle parseBundle(JSONObject jSONObject, Bundle bundle) {
        if (jSONObject == null) {
            return bundle;
        }
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            if (!TextUtils.isEmpty(next)) {
                String optString = jSONObject.optString(next);
                if (!TextUtils.isEmpty(optString)) {
                    if (bundle == null) {
                        bundle = new Bundle();
                    }
                    bundle.putString(next, optString);
                }
            }
        }
        return bundle;
    }

    public void showFriendList(final Context context, final JSONObject jSONObject, final Bundle bundle, final CallBackListener callBackListener) {
        if (!(context instanceof IMyActivity)) {
            JDRouterUtil.callBackError(callBackListener, 703);
        } else {
            LoginUserHelper.getInstance().executeLoginRunnable((IMyActivity) context, new Runnable() { // from class: com.jingdong.common.unification.router.module.JDFriendModule.1
                @Override // java.lang.Runnable
                public void run() {
                    DeepLinkFriendHelper.startFriendListActivity(context, JDFriendModule.this.parseBundle(jSONObject, bundle));
                    JumpUtil.finishInterfaceActivity(context);
                    CallBackListener callBackListener2 = callBackListener;
                    if (callBackListener2 != null) {
                        callBackListener2.onComplete();
                    }
                }
            });
        }
    }

    public void showFriendManager(final Context context, final JSONObject jSONObject, final Bundle bundle, final CallBackListener callBackListener) {
        if (!(context instanceof IMyActivity)) {
            JDRouterUtil.callBackError(callBackListener, 703);
        } else {
            LoginUserHelper.getInstance().executeLoginRunnable((IMyActivity) context, new Runnable() { // from class: com.jingdong.common.unification.router.module.JDFriendModule.2
                @Override // java.lang.Runnable
                public void run() {
                    DeepLinkFriendHelper.startFriendManagerActivity(context, JDFriendModule.this.parseBundle(jSONObject, bundle));
                    JumpUtil.finishInterfaceActivity(context);
                    CallBackListener callBackListener2 = callBackListener;
                    if (callBackListener2 != null) {
                        callBackListener2.onComplete();
                    }
                }
            });
        }
    }

    public void showShareFriendList(final Context context, final JSONObject jSONObject, final Bundle bundle, final CallBackListener callBackListener) {
        if (!(context instanceof IMyActivity)) {
            JDRouterUtil.callBackError(callBackListener, 703);
        } else {
            LoginUserHelper.getInstance().executeLoginRunnable((IMyActivity) context, new Runnable() { // from class: com.jingdong.common.unification.router.module.JDFriendModule.3
                @Override // java.lang.Runnable
                public void run() {
                    DeepLinkFriendHelper.showShareFriendList(context, JDFriendModule.this.parseBundle(jSONObject, bundle));
                    JumpUtil.finishInterfaceActivity(context);
                    CallBackListener callBackListener2 = callBackListener;
                    if (callBackListener2 != null) {
                        callBackListener2.onComplete();
                    }
                }
            });
        }
    }
}
