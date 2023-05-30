package com.jingdong.app.mall.utils.ui;

import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.app.mall.e;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.login.ILogin;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.recommend.RecommendMtaUtils;

/* loaded from: classes4.dex */
public class a {

    /* renamed from: com.jingdong.app.mall.utils.ui.a$a  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    class C0388a implements ILogin {
        final /* synthetic */ BaseActivity a;
        final /* synthetic */ String b;

        C0388a(BaseActivity baseActivity, String str) {
            this.a = baseActivity;
            this.b = str;
        }

        @Override // com.jingdong.common.login.ILogin
        public void onSuccess(String str) {
            if (this.a == null || TextUtils.isEmpty(this.b)) {
                return;
            }
            if (TextUtils.equals(RecommendMtaUtils.MyJD_Page_Name, str)) {
                Bundle bundle = new Bundle();
                bundle.putString("pinName", LoginUserBase.getLoginUserName());
                JumpUtil.execJumpByDes(JumpUtil.VALUE_DES_MYJD, this.a, bundle);
            } else if (TextUtils.equals("JDDna", str)) {
                com.jingdong.app.mall.personel.a.a.a.a.b(this.a, "", true);
            }
        }
    }

    public static void a(BaseActivity baseActivity, String str) {
        DeepLinkLoginHelper.startLoginActivity(e.b().a(), null, new C0388a(baseActivity, str), str);
    }
}
