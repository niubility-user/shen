package com.jingdong.app.mall.dynamicImpl;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.interfaces.IAppRouter;
import com.jd.dynamic.base.interfaces.ICancelLogin;
import com.jd.dynamic.base.interfaces.IRouterCallBackListener;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkMHelper;
import com.jingdong.common.jump.OpenAppJumpBuilder;
import com.jingdong.common.login.ILogin;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.unification.router.CallBackWithReturnListener;
import com.jingdong.common.unification.router.JDRouter;
import com.jingdong.common.widget.NavigatorHolder;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.jdtoast.ToastUtils;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class m implements IAppRouter {

    /* loaded from: classes3.dex */
    class b implements ILogin {
        final /* synthetic */ ICancelLogin a;

        b(m mVar, ICancelLogin iCancelLogin) {
            this.a = iCancelLogin;
        }

        @Override // com.jingdong.common.login.ILogin
        public void onSuccess(String str) {
            ICancelLogin iCancelLogin = this.a;
            if (iCancelLogin != null) {
                iCancelLogin.onSuccess(str);
            }
        }
    }

    @Override // com.jd.dynamic.base.interfaces.IAppRouter
    public boolean isHasLogin() {
        return LoginUserBase.hasLogin();
    }

    @Override // com.jd.dynamic.base.interfaces.IAppRouter
    public void jumpToH5(Context context, String str) {
        try {
            DeepLinkMHelper.startWebActivity(context, str);
        } catch (Exception unused) {
        }
    }

    @Override // com.jd.dynamic.base.interfaces.IAppRouter
    public void jumpWithParams(Context context, JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                OpenAppJumpBuilder.Builder des = new OpenAppJumpBuilder.Builder().scheme(jSONObject.optString("scheme")).host(jSONObject.optString("host")).category(jSONObject.optString("category")).des(jSONObject.optString("des"));
                JSONObject optJSONObject = jSONObject.optJSONObject(NavigatorHolder.NaviEntity.TYPE_CUSTOM);
                if (optJSONObject != null && optJSONObject.names() != null && optJSONObject.names().length() > 0) {
                    for (int i2 = 0; i2 < optJSONObject.names().length(); i2++) {
                        String optString = optJSONObject.names().optString(i2);
                        des.map(optString, optJSONObject.optString(optString));
                    }
                }
                des.build().jump(context);
            } catch (Exception unused) {
            }
        }
    }

    @Override // com.jd.dynamic.base.interfaces.IAppRouter
    public void jumpWithUrl(Context context, String str) {
        try {
            new OpenAppJumpBuilder.Builder(Uri.parse(str)).build().jump(context);
        } catch (Exception unused) {
        }
    }

    @Override // com.jd.dynamic.base.interfaces.IAppRouter
    public void openJDRouter(String str, IRouterCallBackListener iRouterCallBackListener) {
        if (DynamicSdk.getEngine() == null || DynamicSdk.getEngine().getContext() == null) {
            return;
        }
        JDRouter.build(DynamicSdk.getEngine().getContext(), str).callBackListener((CallBackWithReturnListener) new a(this, iRouterCallBackListener)).open();
    }

    @Override // com.jd.dynamic.base.interfaces.IAppRouter
    public void showCustomToast(Context context, JSONObject jSONObject) {
        String optString = jSONObject.optString("type");
        String optString2 = jSONObject.optString("msg");
        if (TextUtils.isEmpty(optString2)) {
            return;
        }
        if (context == null) {
            context = JdSdk.getInstance().getApplicationContext();
        }
        if (TextUtils.equals(optString, "centerMsg")) {
            ToastUtils.showToastInCenter(context, optString2);
        } else {
            ToastUtils.showToast(context, optString2);
        }
    }

    @Override // com.jd.dynamic.base.interfaces.IAppRouter
    public Dialog showDialog(Context context, IAppRouter.DialogConfig dialogConfig) {
        return n.j(context, dialogConfig);
    }

    @Override // com.jd.dynamic.base.interfaces.IAppRouter
    public Dialog showPopView(Context context, IAppRouter.PopViewConfig popViewConfig) {
        return n.k(context, popViewConfig);
    }

    @Override // com.jd.dynamic.base.interfaces.IAppRouter
    public void startLoginActivity(Context context, Bundle bundle, ICancelLogin iCancelLogin, String str) {
        DeepLinkLoginHelper.startLoginActivity(context, bundle, new b(this, iCancelLogin), str);
    }

    /* loaded from: classes3.dex */
    class a implements CallBackWithReturnListener {
        final /* synthetic */ IRouterCallBackListener a;

        a(m mVar, IRouterCallBackListener iRouterCallBackListener) {
            this.a = iRouterCallBackListener;
        }

        @Override // com.jingdong.common.unification.router.CallBackWithReturnListener
        public void onComplete(Object obj) {
            IRouterCallBackListener iRouterCallBackListener = this.a;
            if (iRouterCallBackListener != null) {
                iRouterCallBackListener.onComplete(obj);
            }
        }

        @Override // com.jingdong.common.unification.router.CallBackListener
        public void onError(int i2) {
            IRouterCallBackListener iRouterCallBackListener = this.a;
            if (iRouterCallBackListener != null) {
                iRouterCallBackListener.onComplete(Integer.valueOf(i2));
            }
        }

        @Override // com.jingdong.common.unification.router.CallBackListener
        public void onComplete() {
            IRouterCallBackListener iRouterCallBackListener = this.a;
            if (iRouterCallBackListener != null) {
                iRouterCallBackListener.onComplete(new JSONObject());
            }
        }
    }
}
