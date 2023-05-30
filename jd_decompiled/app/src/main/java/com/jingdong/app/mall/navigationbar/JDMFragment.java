package com.jingdong.app.mall.navigationbar;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.MainFrameActivity;
import com.jingdong.app.mall.basic.JDTaskModule;
import com.jingdong.common.web.ui.CommonMFragment;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.common.web.util.WebUtils;
import com.jingdong.corelib.utils.Log;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class JDMFragment extends CommonMFragment {

    /* renamed from: g */
    private static final String f11293g = JDMFragment.class.getSimpleName();

    /* renamed from: h */
    private static Map<Integer, JDMFragment> f11294h = new HashMap();

    /* renamed from: i */
    private static JDMFragment f11295i;

    /* loaded from: classes4.dex */
    public static class JDMM extends JDTaskModule {

        /* renamed from: g */
        private JDMFragment f11296g;

        @Override // com.jingdong.app.mall.basic.JDTaskModule
        public void a() {
            JDMFragment l2 = JDMFragment.l(Integer.valueOf(c().getInt("com.360buy:navigationFlag")));
            this.f11296g = l2;
            if (l2.getArguments() == null) {
                this.f11296g.setArguments(c());
            }
        }

        @Override // com.jingdong.app.mall.basic.JDTaskModule
        public void b() {
            j(this.f11296g, Integer.valueOf(c().getInt("com.360buy:navigationFlag")));
        }
    }

    public static synchronized JDMFragment l(Integer num) {
        JDMFragment jDMFragment;
        synchronized (JDMFragment.class) {
            if (f11294h.containsKey(num)) {
                f11295i = f11294h.get(num);
            } else {
                JDMFragment jDMFragment2 = new JDMFragment();
                f11295i = jDMFragment2;
                f11294h.put(num, jDMFragment2);
            }
            jDMFragment = f11295i;
        }
        return jDMFragment;
    }

    public static void m() {
        f11295i = null;
        f11294h.clear();
    }

    @Override // com.jingdong.common.web.ui.CommonMFragment, com.jingdong.common.unification.navigationbar.JDTabFragment, com.jingdong.common.unification.navigationbar.INavigationPage
    public void clickNavigation(int i2, int i3, String str) {
        JDWebView jdWebView = getJdWebView();
        if (jdWebView != null) {
            String string = getArguments() != null ? getArguments().getString("url") : "";
            if (Log.D) {
                Log.d(f11293g, "clickNavigation-old-->" + i2 + " now-->" + i3 + " jdWebView-->" + jdWebView + " oldUrl-->" + string + " isError-->" + jdWebView.isError() + " url-->" + str);
            }
            if (jdWebView.isError()) {
                jdWebView.reload();
            }
            if (i2 != i3 && !TextUtils.isEmpty(string) && !TextUtils.isEmpty(str) && !str.equals(string)) {
                jdWebView.loadUrl(str);
            }
            try {
                JDJSONObject jDJSONObject = new JDJSONObject();
                jDJSONObject.put("old", (Object) Integer.valueOf(i2));
                jDJSONObject.put("now", (Object) Integer.valueOf(i3));
                if (Log.D) {
                    Log.d(f11293g, "clickNavigation-jdjsonObject-->" + jDJSONObject);
                }
                WebUtils.evaluateJavascript(getWebUiBinder(), "clickNavigation", jDJSONObject.toJSONString());
            } catch (Exception e2) {
                if (Log.E) {
                    e2.printStackTrace();
                }
            }
        }
    }

    @Override // com.jingdong.common.web.ui.CommonMFragment
    protected boolean needDestroyFragmentOnDestroy() {
        return false;
    }

    @Override // com.jingdong.common.web.ui.CommonMFragment
    protected boolean needDestroyWebViewOnDestroy() {
        return false;
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override // com.jingdong.common.web.ui.CommonMFragment, com.jingdong.common.unification.navigationbar.JDTabFragment, com.jingdong.cleanmvp.ui.MvpBaseFragment, com.jingdong.sdk.platform.lib.ui.CompactFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        this.isNavigationTab = true;
        super.onCreate(bundle);
    }

    @Override // com.jingdong.common.web.ui.CommonMFragment, com.jingdong.common.unification.navigationbar.JDTabFragment, com.jingdong.cleanmvp.ui.BaseFragment
    public View onCreateViews(LayoutInflater layoutInflater, Bundle bundle) {
        this.isTransStatusbar = true;
        return super.onCreateViews(layoutInflater, bundle);
    }

    @Override // com.jingdong.common.web.ui.CommonMFragment, com.jingdong.cleanmvp.ui.MvpBaseFragment, com.jingdong.sdk.platform.lib.ui.CompactFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
    }

    @Override // com.jingdong.common.web.ui.CommonMFragment, com.jingdong.sdk.platform.lib.ui.CompactFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (Log.D) {
            Log.d(f11293g, "isNeedReLoadH5-->" + MainFrameActivity.b0 + " isLoginStateSyncing=" + isLoginStateSyncing());
        }
        if (MainFrameActivity.b0 && !isLoginStateSyncing() && getJdWebView() != null) {
            getJdWebView().reload();
        }
        MainFrameActivity.b0 = false;
    }

    @Override // com.jingdong.cleanmvp.ui.MvpBaseFragment, com.jingdong.sdk.platform.lib.ui.CompactFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
    }
}
