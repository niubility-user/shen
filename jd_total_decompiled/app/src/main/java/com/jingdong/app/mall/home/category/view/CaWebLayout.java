package com.jingdong.app.mall.home.category.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.floor.model.entity.CategoryEntity;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.babelrn.common.Constants;
import com.jingdong.common.login.LoginEvent;
import com.jingdong.common.web.MKeyNames;
import com.jingdong.jdsdk.auraSetting.AuraFragmentHelper;
import com.jingdong.pdj.libcore.home.HourlyGoFragment;

/* loaded from: classes4.dex */
public class CaWebLayout extends RelativeLayout {

    /* renamed from: g  reason: collision with root package name */
    private int f8781g;

    /* renamed from: h  reason: collision with root package name */
    private final CaContentLayout f8782h;

    /* renamed from: i  reason: collision with root package name */
    private Fragment f8783i;

    /* renamed from: j  reason: collision with root package name */
    private final FrameLayout f8784j;

    /* renamed from: k  reason: collision with root package name */
    private FragmentTransaction f8785k;

    /* renamed from: l  reason: collision with root package name */
    private CategoryEntity.CaItem f8786l;

    /* renamed from: m  reason: collision with root package name */
    private FragmentManager f8787m;

    /* renamed from: n  reason: collision with root package name */
    private HourlyGoFragment f8788n;
    private int o;

    public CaWebLayout(Context context, CaContentLayout caContentLayout, int i2) {
        super(context);
        this.f8781g = -1;
        this.f8782h = caContentLayout;
        this.o = i2;
        FrameLayout frameLayout = new FrameLayout(context);
        this.f8784j = frameLayout;
        if (i2 == -2) {
            frameLayout.setId(R.id.home_ca_tab_fragment1);
        } else if (i2 == -3) {
            frameLayout.setId(R.id.home_ca_tab_fragment2);
        } else if (i2 == -1) {
            frameLayout.setId(R.id.home_ca_tab_fragment3);
        } else if (i2 == 1) {
            frameLayout.setId(R.id.home_ca_tab_fragment4);
        } else if (i2 == 2) {
            frameLayout.setId(R.id.home_ca_tab_fragment5);
        } else if (i2 == 3) {
            frameLayout.setId(R.id.home_ca_tab_fragment6);
        } else {
            frameLayout.setId(R.id.home_ca_tab_fragment7);
        }
        f.G0(this);
        addView(frameLayout, new RelativeLayout.LayoutParams(-1, -1));
    }

    private void a(int i2, boolean z) {
        if (this.f8781g == i2) {
            return;
        }
        this.f8781g = i2;
        try {
            if (1 == i2) {
                l(true);
                f(z);
            } else {
                e(z);
            }
        } catch (Exception e2) {
            d(e2);
        }
    }

    private void b() {
        CaContentLayout caContentLayout = this.f8782h;
        if (caContentLayout != null) {
            caContentLayout.k();
        }
    }

    private void d(Exception exc) {
        if (exc != null) {
            f.o(exc.getMessage());
        }
        this.f8786l = null;
        CaMoreLayout.p();
    }

    private void e(boolean z) {
        Fragment fragment = this.f8783i;
        if (fragment == null) {
            return;
        }
        fragment.setUserVisibleHint(false);
        if (z) {
            Fragment fragment2 = this.f8783i;
            if (fragment2 instanceof CaMFragment) {
                ((CaMFragment) fragment2).n();
                return;
            }
            fragment2.onPause();
            this.f8783i.onStop();
        }
    }

    private void f(boolean z) {
        Fragment fragment;
        if (!JDHomeFragment.Q0() || (fragment = this.f8783i) == null) {
            return;
        }
        fragment.setUserVisibleHint(true);
        if (z) {
            Fragment fragment2 = this.f8783i;
            if (fragment2 instanceof CaMFragment) {
                ((CaMFragment) fragment2).o();
            } else {
                fragment2.onResume();
            }
        }
    }

    private boolean l(boolean z) {
        JDHomeFragment z0 = JDHomeFragment.z0();
        if (z0 == null) {
            d(null);
            return false;
        }
        FragmentManager childFragmentManager = z0.getChildFragmentManager();
        if (z && childFragmentManager == this.f8787m) {
            return false;
        }
        this.f8787m = childFragmentManager;
        FragmentTransaction beginTransaction = childFragmentManager.beginTransaction();
        this.f8785k = beginTransaction;
        beginTransaction.replace(this.f8784j.getId(), this.f8783i);
        this.f8785k.commitAllowingStateLoss();
        return true;
    }

    public void c(CategoryEntity.CaItem caItem, boolean z) {
        if (z && caItem.isSameInfo(this.f8786l)) {
            a(1, true);
            b();
            return;
        }
        this.f8786l = caItem;
        try {
            Fragment fragment = this.f8783i;
            if (fragment instanceof CaMFragment) {
                ((CaMFragment) fragment).r();
            }
            Bundle bundle = new Bundle();
            this.f8783i = null;
            if (caItem.isHourNative()) {
                if (this.f8788n == null) {
                    this.f8788n = new HourlyGoFragment();
                }
                this.f8783i = this.f8788n;
            } else if (caItem.isBabelType()) {
                Fragment newFragment = AuraFragmentHelper.getInstance().newFragment((Activity) BaseFrameUtil.getInstance().getMainFrameActivity(), "com.jd.lib.babel.view.activity.BabelNativeFragment");
                this.f8783i = newFragment;
                if (newFragment != null) {
                    bundle.putString("des", "m");
                    bundle.putBoolean(Constants.KEY_NEED_DESTROY_WEBVIEW, false);
                    bundle.putBoolean(Constants.KEY_HAS_TOP_NAV, true);
                    bundle.putBoolean(Constants.KEY_IS_ALLOW_SYNC, true);
                    bundle.putString(Constants.KEY_TOP_NAV_STYLE, "0");
                }
            }
            if (this.f8783i == null) {
                this.f8783i = new CaMFragment(caItem, true);
                bundle.putBoolean("isTopBarGone", true);
                bundle.putBoolean(MKeyNames.NEED_CHECK_NATIVE, false);
                bundle.putBoolean(MBaseKeyNames.KEY_SWITCH_IMMERSIVE_OPEN, false);
            }
            bundle.putString("url", caItem.webUrl);
            this.f8783i.setArguments(bundle);
            l(false);
            this.f8781g = 1;
            this.f8782h.k();
        } catch (Exception e2) {
            d(e2);
        }
    }

    public void g() {
        b();
        a(0, true);
    }

    public void h() {
        if (this.o < 0) {
            return;
        }
        this.f8786l = null;
        Fragment fragment = this.f8783i;
        if (fragment instanceof CaMFragment) {
            ((CaMFragment) fragment).r();
        }
    }

    public boolean i(int i2) {
        Fragment fragment = this.f8783i;
        if (fragment instanceof HourlyGoFragment) {
            return ((HourlyGoFragment) fragment).onKeyDown(i2, null);
        }
        return false;
    }

    public void j() {
        b();
        a(0, false);
    }

    public void k() {
        a(1, false);
    }

    public void onEventMainThread(BaseEvent baseEvent) {
        if (baseEvent instanceof LoginEvent) {
            Fragment fragment = this.f8783i;
            if (fragment instanceof CaMFragment) {
                ((CaMFragment) fragment).s();
            }
        }
    }
}
