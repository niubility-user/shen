package com.jingdong.app.mall.home.o.a;

import android.app.Activity;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.messagepop.JDMessageNoticeCallback;
import com.jingdong.common.messagepop.JDMessageNoticeManager;
import com.jingdong.common.utils.DeepDarkChangeManager;
import com.jingdong.common.utils.inter.JDOverseasUtil;
import com.jingdong.sdk.bmode.util.JDBModeUtils;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes4.dex */
public class c {
    private static final AtomicInteger a = new AtomicInteger(0);
    private static boolean b;

    /* renamed from: c  reason: collision with root package name */
    private static boolean f10446c;
    private static boolean d;

    /* renamed from: e  reason: collision with root package name */
    private static boolean f10447e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Activity f10448g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ IHomeTitle f10449h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ com.jingdong.app.mall.home.u.b f10450i;

        a(Activity activity, IHomeTitle iHomeTitle, com.jingdong.app.mall.home.u.b bVar) {
            this.f10448g = activity;
            this.f10449h = iHomeTitle;
            this.f10450i = bVar;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            if (com.jingdong.app.mall.home.c.f().l(this.f10448g)) {
                k.e("Check Dialog >>> PrivacyUpdateDialog show");
                h.n();
                return;
            }
            com.jingdong.app.mall.home.u.a.w().J(this.f10448g, this.f10449h, this.f10450i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b implements JDMessageNoticeCallback {
        b() {
        }

        @Override // com.jingdong.common.messagepop.JDMessageNoticeCallback
        public void onJDMessageNotice(boolean z) {
            if (z) {
                h.o();
            }
        }
    }

    public static void a(Activity activity, IHomeTitle iHomeTitle, com.jingdong.app.mall.home.u.b bVar) {
        if (a.incrementAndGet() > 1 || !f.k0() || h.c()) {
            return;
        }
        f.E0(new a(activity, iHomeTitle, bVar));
    }

    public static void b(BaseActivity baseActivity) {
        if (baseActivity == null || !f.k0() || h.c()) {
            return;
        }
        k.e("Check Dialog >>> checkOtherDialog start");
        boolean z = false;
        if (b && com.jingdong.app.mall.home.v.d.a.d() && JDBModeUtils.needShowNormalModeDialog()) {
            JDBModeUtils.showNormalModeDialog(baseActivity);
            h.l();
            k.e("Check Dialog >>> ModeDialog isShow = true");
            return;
        }
        if (f10447e && !com.jingdong.app.mall.home.v.d.a.g()) {
            if (JDElderModeUtils.isNeedShowElderModeDialog()) {
                z = JDElderModeUtils.showElderModeDialog(baseActivity, "shouye");
                k.e("Check Dialog >>> ElderModeDialog isShow = " + z);
            } else if (JDElderModeUtils.isNeedShowNormalModeDialog()) {
                z = JDElderModeUtils.showNormalModeDialog(baseActivity, "shouye");
                k.e("Check Dialog >>> NormalModeDialog isShow = " + z);
            }
            if (z) {
                h.i();
            }
        }
        if (d && !com.jingdong.app.mall.home.state.old.a.f() && !z) {
            z = DeepDarkChangeManager.getInstance().showDarkGuide(baseActivity);
            if (z) {
                h.h();
            }
            k.e("Check Dialog >>> Dark isShow = " + z);
        }
        if (!f10446c || z) {
            return;
        }
        JDMessageNoticeManager.getInstance().showPushOpenGuide("10000_Home", new b());
        k.e("Check Dialog >>> use MessageOpen");
    }

    public static void c(JDJSONObject jDJSONObject) {
        JDJSONObject optJSONObject = jDJSONObject.optJSONObject("darkMode");
        com.jingdong.app.mall.home.state.dark.a.i(optJSONObject != null ? optJSONObject.optInt("darkModePercent", 10) : 10);
        b = com.jingdong.app.mall.home.floor.common.i.g.q();
        d = optJSONObject != null && DYConstants.DY_TRUE.equals(optJSONObject.optString("darkModeTipSwitch"));
        f10446c = jDJSONObject.optInt("popMessage", 0) == 1;
        f10447e = jDJSONObject.optInt("oldSwitch", 0) == 1 && JDOverseasUtil.getCurrentOverseasArea() <= 0;
        k.e("DarkDialog >>> " + d + " ADialog >>> " + b + " MessageDialog >>> " + f10446c + " ElderDialog >>> " + f10447e);
    }
}
