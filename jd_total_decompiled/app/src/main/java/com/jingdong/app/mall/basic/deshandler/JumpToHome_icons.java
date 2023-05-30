package com.jingdong.app.mall.basic.deshandler;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.e;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.app.mall.utils.s;
import com.jingdong.common.deeplinkhelper.DeepLinkFavouritesHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.login.ILogin;
import com.jingdong.common.search.SearchConstants;
import com.jingdong.common.utils.CommonBridge;
import com.jingdong.corelib.utils.Log;

@Des(des = JumpUtil.VAULE_DES_HOME_ICONS)
/* loaded from: classes19.dex */
public class JumpToHome_icons extends com.jingdong.app.mall.basic.deshandler.a {

    /* loaded from: classes19.dex */
    class a implements ILogin {
        final /* synthetic */ Context a;

        a(JumpToHome_icons jumpToHome_icons, Context context) {
            this.a = context;
        }

        @Override // com.jingdong.common.login.ILogin
        public void onSuccess(String str) {
            if ("forwardFavourites".equals(str) && (this.a instanceof Activity)) {
                Bundle bundle = new Bundle();
                bundle.putString("title", this.a.getString(R.string.content_my_attention));
                bundle.putInt("com.360buy:navigationDisplayFlag", -1);
                bundle.putBoolean(SearchConstants.PATH_IS_FROM_HOME, true);
                DeepLinkFavouritesHelper.startFavouritesActivity2((Activity) this.a, bundle);
            }
        }
    }

    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        String string = bundle.getString("type");
        String string2 = bundle.getString("ctype");
        String string3 = bundle.getString("functionId");
        String string4 = bundle.getString("classParam");
        SourceEntity.getOpenAppSourceEntity(bundle);
        if (Log.D) {
            Log.d("TEST", " -->> functionId : " + string3);
        }
        if (TextUtils.isEmpty(string)) {
            c(context);
            return;
        }
        if ("5".equals(string)) {
            if ("1".equals(string2)) {
                s.f(e.b().a(), string4);
            } else if ("2".equals(string2)) {
                if (context instanceof Activity) {
                    s.c((Activity) context);
                }
            } else if (TextUtils.isEmpty(string3)) {
                c(context);
                return;
            } else {
                CommonBridge.forwardWebActivity(h(), string3);
            }
        } else if ("7".equals(string)) {
            DeepLinkLoginHelper.startLoginActivity(context, null, new a(this, context), "forwardFavourites");
        } else if ("9".equals(string) && (context instanceof Activity)) {
            s.c((Activity) context);
        }
        c(context);
    }
}
