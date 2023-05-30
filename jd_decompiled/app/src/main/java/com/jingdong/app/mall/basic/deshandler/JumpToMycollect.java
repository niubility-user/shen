package com.jingdong.app.mall.basic.deshandler;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkFavouritesHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkSwitch;
import com.jingdong.common.login.ILogin;
import com.jingdong.common.search.SearchConstants;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;

@Des(des = "mycollect,Mguanzhu,jshopList")
/* loaded from: classes19.dex */
public class JumpToMycollect extends com.jingdong.app.mall.basic.deshandler.a {

    /* loaded from: classes19.dex */
    class a implements ILogin {
        final /* synthetic */ Context a;
        final /* synthetic */ Bundle b;

        a(JumpToMycollect jumpToMycollect, Context context, Bundle bundle) {
            this.a = context;
            this.b = bundle;
        }

        @Override // com.jingdong.common.login.ILogin
        public void onSuccess(String str) {
            if ("forwardMyCollect".equals(str)) {
                Bundle bundle = new Bundle();
                bundle.putString("title", this.a.getString(R.string.content_my_attention));
                bundle.putInt("com.360buy:navigationDisplayFlag", -1);
                Bundle bundle2 = this.b;
                if (bundle2 != null) {
                    int i2 = bundle2.getInt(DeepLinkFavouritesHelper.CURRENT_TAB, 0);
                    if (i2 != 0 && i2 != 1) {
                        i2 = 0;
                    }
                    bundle.putInt(DeepLinkFavouritesHelper.CURRENT_TAB, i2);
                    if (this.b.getInt("jumpFrom") == 1) {
                        bundle.putBoolean(SearchConstants.PATH_IS_FROM_HOME, true);
                    }
                    bundle.putInt("sortType", this.b.getInt("sortType", 0));
                }
                DeepLinkFavouritesHelper.startFavouritesActivity2((Activity) this.a, bundle);
            }
        }
    }

    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (!(context instanceof Activity)) {
            c(context);
        } else if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(8))) {
            DeepLinkLoginHelper.startLoginActivity(context, null, new a(this, context, bundle), "forwardMyCollect");
            c(context);
        }
    }
}
