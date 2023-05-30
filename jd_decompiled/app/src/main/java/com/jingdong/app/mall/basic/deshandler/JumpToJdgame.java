package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.lib.babel.task.viewkit.VKEventUtil;
import com.jingdong.app.mall.JDGameActivity;
import com.jingdong.app.mall.JDGamePortraitActivity;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.login.ILogin;
import com.jingdong.common.login.LoginUserHelper;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;

@Des(des = "jdgame")
/* loaded from: classes19.dex */
public class JumpToJdgame extends com.jingdong.app.mall.basic.deshandler.a {

    /* loaded from: classes19.dex */
    class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Bundle f8047g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Context f8048h;

        a(Bundle bundle, Context context) {
            this.f8047g = bundle;
            this.f8048h = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            Intent intent;
            String string = this.f8047g.getString("action");
            String string2 = this.f8047g.getString("url");
            String string3 = this.f8047g.getString("des");
            String string4 = this.f8047g.getString("isHorizon");
            if (Log.D) {
                Log.d(JumpToJdgame.this.a, " -->> url : " + string2);
                Log.d(JumpToJdgame.this.a, " -->> action : " + string);
            }
            if (TextUtils.isEmpty(string)) {
                string = RemoteMessageConst.TO;
            }
            if (JumpUtil.VAULE_DES_JDTHIRDLOGIN.equals(string3)) {
                string2 = LoginUserHelper.addAppUpTypeToUrl(string2);
            }
            URLParamMap uRLParamMap = new URLParamMap();
            if (!TextUtils.isEmpty(string2)) {
                uRLParamMap.put(RemoteMessageConst.TO, string2);
            }
            if (this.f8048h != null) {
                if ("YES".equals(string4)) {
                    intent = new Intent(this.f8048h, JDGameActivity.class);
                } else {
                    intent = new Intent(this.f8048h, JDGamePortraitActivity.class);
                }
                SerializableContainer serializableContainer = new SerializableContainer();
                serializableContainer.setMap(uRLParamMap);
                intent.putExtra("urlParamMap", serializableContainer);
                intent.putExtra("urlAction", string);
                intent.putExtras(this.f8047g);
                this.f8048h.startActivity(intent);
            }
        }
    }

    /* loaded from: classes19.dex */
    class b implements ILogin {
        final /* synthetic */ Runnable a;

        b(JumpToJdgame jumpToJdgame, Runnable runnable) {
            this.a = runnable;
        }

        @Override // com.jingdong.common.login.ILogin
        public void onSuccess(String str) {
            if ("forwardJDGame".equals(str)) {
                this.a.run();
            }
        }
    }

    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        a aVar = new a(bundle, context);
        String string = bundle.getString(VKEventUtil.JUMP_NEEDLOGIN);
        if ("0".equals(string) | TextUtils.isEmpty(string)) {
            aVar.run();
            c(context);
            return;
        }
        DeepLinkLoginHelper.startLoginActivity(context, null, new b(this, aVar), "forwardJDGame");
        c(context);
    }
}
