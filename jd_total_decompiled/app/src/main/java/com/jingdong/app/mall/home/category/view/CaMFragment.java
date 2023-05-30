package com.jingdong.app.mall.home.category.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.app.mall.home.floor.common.e;
import com.jingdong.app.mall.home.floor.model.entity.CategoryEntity;
import com.jingdong.app.mall.home.floor.model.entity.IconFloorEntity;
import com.jingdong.common.web.javainterface.WebPlugin;
import com.jingdong.common.web.ui.CommonMFragment;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.uilistener.impl.WebViewClientListenerImpl;
import com.tencent.smtt.sdk.WebView;
import de.greenrobot.event.EventBus;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class CaMFragment extends CommonMFragment {

    /* renamed from: g  reason: collision with root package name */
    private boolean f8746g = true;

    /* renamed from: h  reason: collision with root package name */
    private boolean f8747h = false;

    /* renamed from: i  reason: collision with root package name */
    private final CategoryEntity.CaItem f8748i;

    /* renamed from: j  reason: collision with root package name */
    private final boolean f8749j;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends WebViewClientListenerImpl {
        a(IWebUiBinder iWebUiBinder) {
            super(iWebUiBinder);
        }

        @Override // com.jingdong.common.web.uilistener.impl.WebViewClientListenerImpl, com.jingdong.common.web.uilistener.WebViewClientListener
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            ((CommonMFragment) CaMFragment.this).mJdWebView.setCanPullRefresh(CaMFragment.this.f8749j);
        }
    }

    public CaMFragment(CategoryEntity.CaItem caItem, boolean z) {
        this.f8748i = caItem;
        this.f8749j = z;
    }

    private void q(String str, WebPlugin.CallBack callBack) {
        try {
            JSONObject jSONObject = new JSONObject();
            String optString = com.jingdong.app.mall.home.r.c.b.c(str).optString("headImg");
            if (TextUtils.isEmpty(optString)) {
                jSONObject.put("status", "-1");
                jSONObject.put("msg", "url is Empty");
            } else {
                jSONObject.put("status", "0");
                jSONObject.put("msg", "current url is :" + optString);
            }
            this.f8748i.setHeadSkinUrl(optString);
            EventBus.getDefault().post(new e("home_refresh_top_skin"));
            callBack.callback(jSONObject);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void t() {
        this.mJdWebView.getPullToRefreshView().setBackgroundColor(IconFloorEntity.BGCOLOR_DEFAULT);
        this.mJdWebView.setWebViewClientListener(new a(getWebUiBinder()));
    }

    public void n() {
        if (this.f8746g) {
            onPause();
            onStop();
        }
    }

    @Override // com.jingdong.common.web.ui.CommonMFragment
    protected boolean needDestroyWebViewOnDestroy() {
        return this.f8747h;
    }

    public void o() {
        if (this.f8746g) {
            return;
        }
        onResume();
    }

    @Override // com.jingdong.common.web.ui.CommonMFragment, com.jingdong.common.unification.navigationbar.JDTabFragment, com.jingdong.cleanmvp.ui.MvpBaseFragment, com.jingdong.sdk.platform.lib.ui.CompactFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            t();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jingdong.common.web.ui.CommonMFragment, com.jingdong.common.web.ui.IXFragment
    public void onJsMessage(String str, WebPlugin.CallBack callBack) {
        q(str, callBack);
    }

    @Override // com.jingdong.common.web.ui.CommonMFragment, com.jingdong.sdk.platform.lib.ui.CompactFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.f8746g = false;
    }

    @Override // com.jingdong.common.web.ui.CommonMFragment, com.jingdong.sdk.platform.lib.ui.CompactFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.f8746g = true;
        if (getUserVisibleHint()) {
            return;
        }
        onPause();
        onStop();
    }

    public void r() {
        this.f8746g = false;
        this.f8747h = true;
        setUserVisibleHint(false);
        onPause();
        onStop();
        onDestroy();
    }

    public void s() {
        JDWebView jdWebView = getJdWebView();
        if (jdWebView == null || this.f8747h) {
            return;
        }
        jdWebView.reload();
    }
}
