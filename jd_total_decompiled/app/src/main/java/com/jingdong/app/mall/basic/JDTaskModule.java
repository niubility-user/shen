package com.jingdong.app.mall.basic;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.jingdong.app.mall.MainFrameActivity;
import com.jingdong.app.mall.R;
import com.jingdong.corelib.utils.Log;
import java.util.HashMap;

/* loaded from: classes19.dex */
public class JDTaskModule {
    public boolean b;
    private Bundle d;

    /* renamed from: e  reason: collision with root package name */
    private JDTaskModule f7948e;

    /* renamed from: f  reason: collision with root package name */
    private int f7949f;
    public boolean a = false;

    /* renamed from: c  reason: collision with root package name */
    public boolean f7947c = true;

    static {
        new HashMap();
    }

    private MainFrameActivity e() {
        if (this.f7949f >= 5) {
            this.f7949f = 0;
            return null;
        } else if (com.jingdong.app.mall.n.a.a().b() == null) {
            this.f7949f++;
            if (Log.D) {
                Log.d("JDTaskModule", "getFrameActivity() optCount : " + this.f7949f);
            }
            try {
                Thread.sleep(50L);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            return e();
        } else {
            return com.jingdong.app.mall.n.a.a().b();
        }
    }

    protected void a() {
    }

    protected void b() {
    }

    public Bundle c() {
        Bundle bundle = this.d;
        return bundle == null ? new Bundle() : bundle;
    }

    public int d() {
        return R.id.tf;
    }

    public JDTaskModule f() {
        return this.f7948e;
    }

    public void g() {
        a();
    }

    public boolean h() {
        return true;
    }

    public void i(int i2, Fragment fragment, String str) {
        try {
            MainFrameActivity e2 = e();
            if (e2 == null) {
                return;
            }
            FragmentManager supportFragmentManager = e2.getSupportFragmentManager();
            FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
            Fragment findFragmentByTag = TextUtils.isEmpty(str) ? null : supportFragmentManager.findFragmentByTag(str);
            if (findFragmentByTag != null && findFragmentByTag != fragment) {
                beginTransaction.remove(findFragmentByTag);
            }
            if (!fragment.isAdded()) {
                beginTransaction.replace(i2, fragment, str);
            }
            beginTransaction.setTransitionStyle(16973827);
            beginTransaction.commitAllowingStateLoss();
        } catch (Throwable th) {
            if (Log.D) {
                th.printStackTrace();
            }
        }
    }

    public void j(Fragment fragment, Integer num) {
        i(d(), fragment, num.toString());
    }

    public void k(Bundle bundle) {
        this.d = bundle;
    }

    public void l(JDTaskModule jDTaskModule) {
        this.f7948e = jDTaskModule;
    }

    public void m() {
        b();
    }
}
