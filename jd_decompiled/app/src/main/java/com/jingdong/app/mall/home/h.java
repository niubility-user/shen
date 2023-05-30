package com.jingdong.app.mall.home;

import com.jingdong.app.mall.MainFrameActivity;
import com.jingdong.app.mall.open.BaseEntryActivity;
import com.jingdong.app.mall.utils.LoginUser;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes4.dex */
public class h extends f implements LoginUserBase.LoginListener {
    private final g a = new g();

    /* loaded from: classes4.dex */
    class a extends com.jingdong.app.mall.home.o.a.b {
        a(h hVar) {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void safeRun() {
            MainFrameActivity b = com.jingdong.app.mall.n.a.a().b();
            if (b != null) {
                b.checkTargetActivity();
            }
        }
    }

    @Override // com.jingdong.app.mall.home.f
    public g a() {
        this.a.a = BaseEntryActivity.w();
        return this.a;
    }

    @Override // com.jingdong.app.mall.home.f
    public void b(BaseActivity baseActivity, String str) {
        LoginUser.getInstance().homeAutoLogin(baseActivity, this, str);
    }

    @Override // com.jingdong.app.mall.home.f
    public void c() {
        com.jingdong.app.mall.basic.a.a();
        try {
            com.jingdong.app.mall.navigationbar.c.G().a0(0);
        } catch (Exception e2) {
            if (Log.D) {
                e2.printStackTrace();
            }
        }
    }

    @Override // com.jingdong.app.mall.home.f
    public boolean d() {
        MainFrameActivity b = com.jingdong.app.mall.n.a.a().b();
        if (b == null || b.removeGuideView()) {
            return false;
        }
        BaseFrameUtil.exitControl(b, h.class.getName());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.jingdong.app.mall.home.f
    public void e() {
    }

    @Override // com.jingdong.common.login.LoginUserBase.LoginListener
    public void loginCompletedNotify() {
        com.jingdong.app.mall.home.o.a.f.E0(new a(this));
    }
}
