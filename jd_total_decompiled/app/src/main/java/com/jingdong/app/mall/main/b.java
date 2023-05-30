package com.jingdong.app.mall.main;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Handler;
import android.view.ViewGroup;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.widget.video.IjkVideoViewWithReport;
import com.jingdong.corelib.utils.Log;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class b extends c {

    /* renamed from: g  reason: collision with root package name */
    private static AtomicBoolean f11241g = new AtomicBoolean(false);
    private ViewGroup d;

    /* renamed from: e  reason: collision with root package name */
    private IjkVideoViewWithReport f11242e;

    /* renamed from: f  reason: collision with root package name */
    private Handler f11243f;

    public b(Activity activity, c cVar) {
        super(activity, cVar);
        this.d = null;
        this.f11242e = null;
    }

    private boolean f() {
        boolean h2 = h();
        if (h2) {
            SharedPreferences.Editor edit = CommonBase.getJdSharedPreferences().edit();
            edit.putBoolean("first_start_flag_g_v_10_0_0", false);
            edit.apply();
        }
        return h2;
    }

    private void g() {
        try {
            f11241g.set(true);
            f();
            c();
        } catch (Exception unused) {
            c();
        }
    }

    private boolean h() {
        return CommonBase.getJdSharedPreferences().getBoolean("first_start_flag_g_v_10_0_0", true);
    }

    private void i() {
        try {
            if (Log.D) {
                Log.d("GuideVideo", "pauseVideo " + this.f11242e);
            }
            IjkVideoViewWithReport ijkVideoViewWithReport = this.f11242e;
            if (ijkVideoViewWithReport != null) {
                ijkVideoViewWithReport.pause();
            }
            k();
        } catch (Exception e2) {
            e2.printStackTrace();
            g();
        }
    }

    private void j() {
        long j2 = 0;
        try {
            if (Log.D) {
                j2 = System.currentTimeMillis();
                Log.d("GuideVideo", "releaseVideo a " + this.f11242e);
            }
            ViewGroup viewGroup = this.d;
            if (viewGroup != null) {
                viewGroup.setVisibility(8);
                this.d = null;
            }
            IjkVideoViewWithReport ijkVideoViewWithReport = this.f11242e;
            if (ijkVideoViewWithReport != null) {
                ijkVideoViewWithReport.releaseInThread(true);
                this.f11242e = null;
            }
            k();
            if (Log.D) {
                Log.d("GuideVideo", "releaseVideo b cost time:" + (System.currentTimeMillis() - j2));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            g();
        }
    }

    private void k() {
        Handler handler = this.f11243f;
        if (handler != null) {
            handler.removeMessages(1);
            this.f11243f = null;
        }
    }

    @Override // com.jingdong.app.mall.main.c
    public void a() {
        super.a();
        j();
    }

    @Override // com.jingdong.app.mall.main.c
    public void b() {
        c();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.main.c
    public void d() {
        super.d();
        i();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.main.c
    public void e() {
        super.e();
        i();
    }
}
