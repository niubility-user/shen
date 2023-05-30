package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.lib.babel.task.viewkit.VKEventUtil;
import com.jingdong.app.mall.WebActivity;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.app.mall.messagecenter.view.activity.MessageMActivity;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.login.ILogin;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;

@Des(des = JumpUtil.VALUE_DES_MESSAGE_M)
/* loaded from: classes19.dex */
public class JumpToMessageM extends com.jingdong.app.mall.basic.deshandler.a {

    /* loaded from: classes19.dex */
    class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Bundle f8052g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Context f8053h;

        a(JumpToMessageM jumpToMessageM, Bundle bundle, Context context) {
            this.f8052g = bundle;
            this.f8053h = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            String string = this.f8052g.getString("action");
            String string2 = this.f8052g.getString("url");
            String string3 = this.f8052g.getString("msg");
            if (TextUtils.isEmpty(string)) {
                string = RemoteMessageConst.TO;
            }
            URLParamMap uRLParamMap = new URLParamMap();
            if (!TextUtils.isEmpty(string2)) {
                uRLParamMap.put(RemoteMessageConst.TO, string2);
            }
            if (this.f8053h != null) {
                if (!TextUtils.isEmpty(string3)) {
                    Intent intent = new Intent(this.f8053h, MessageMActivity.class);
                    SerializableContainer serializableContainer = new SerializableContainer();
                    serializableContainer.setMap(uRLParamMap);
                    intent.putExtra("urlParamMap", serializableContainer);
                    intent.putExtra("urlAction", string);
                    intent.putExtra("msg", string3);
                    intent.putExtras(this.f8052g);
                    this.f8053h.startActivity(intent);
                    return;
                }
                Intent intent2 = new Intent(this.f8053h, WebActivity.class);
                SerializableContainer serializableContainer2 = new SerializableContainer();
                serializableContainer2.setMap(uRLParamMap);
                intent2.putExtra("urlParamMap", serializableContainer2);
                intent2.putExtra("urlAction", string);
                intent2.putExtras(this.f8052g);
                this.f8053h.startActivity(intent2);
            }
        }
    }

    /* loaded from: classes19.dex */
    class b implements ILogin {
        final /* synthetic */ Runnable a;

        b(JumpToMessageM jumpToMessageM, Runnable runnable) {
            this.a = runnable;
        }

        @Override // com.jingdong.common.login.ILogin
        public void onSuccess(String str) {
            if ("MessageCenterM".equals(str)) {
                this.a.run();
            }
        }
    }

    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        a aVar = new a(this, bundle, context);
        String string = bundle.getString(VKEventUtil.JUMP_NEEDLOGIN);
        if ("0".equals(string) | TextUtils.isEmpty(string)) {
            aVar.run();
            c(context);
            return;
        }
        DeepLinkLoginHelper.startLoginActivity(context, null, new b(this, aVar), "MessageCenterM");
        c(context);
    }
}
