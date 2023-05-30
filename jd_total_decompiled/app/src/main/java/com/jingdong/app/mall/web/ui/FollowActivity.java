package com.jingdong.app.mall.web.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.WebActivity;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.web.ui.CommonMFragment;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.uilistener.impl.WebViewClientListenerImpl;
import com.jingdong.common.web.util.URLUtils;
import com.jingdong.common.widget.NavigatorHolder;
import com.jingdong.common.widget.custom.CustomChannelFollowView;
import com.jingdong.sdk.utils.DPIUtil;
import com.tencent.smtt.sdk.WebView;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/* loaded from: classes4.dex */
public class FollowActivity extends WebActivity {

    /* renamed from: g  reason: collision with root package name */
    private CustomChannelFollowView f12019g;

    /* renamed from: h  reason: collision with root package name */
    private String f12020h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f12021i = true;

    /* renamed from: j  reason: collision with root package name */
    private int f12022j = DPIUtil.dip2px(59.0f);

    /* renamed from: k  reason: collision with root package name */
    private int f12023k = DPIUtil.dip2px(22.0f);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements NavigatorHolder.NaviStateListener {
        a() {
        }

        @Override // com.jingdong.common.widget.NavigatorHolder.NaviStateListener
        public void onStyleChanged(int i2) {
            if (FollowActivity.this.f12019g != null) {
                FollowActivity.this.f12019g.changeIcon(i2 != 2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b extends WebViewClientListenerImpl {
        b(IWebUiBinder iWebUiBinder) {
            super(iWebUiBinder);
        }

        @Override // com.jingdong.common.web.uilistener.impl.WebViewClientListenerImpl, com.jingdong.common.web.uilistener.WebViewClientListener
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            if (TextUtils.isEmpty(str) || str.startsWith("https://un.m.jd.com/") || str.startsWith("https://beta-un.m.jd.com/")) {
                return;
            }
            String str2 = null;
            try {
                str2 = URLUtils.getQueryParameter(URLDecoder.decode(str, "utf-8"), "collectionId");
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
            if (TextUtils.isEmpty(str2)) {
                FollowActivity.this.y();
                ((WebActivity) FollowActivity.this).mFragment.getNaviHolder().setOutSideShow(true);
                return;
            }
            FollowActivity.this.z(str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        CustomChannelFollowView customChannelFollowView = this.f12019g;
        if (customChannelFollowView == null || customChannelFollowView.getParent() == null) {
            return;
        }
        ((ViewGroup) this.f12019g.getParent()).removeView(this.f12019g);
        this.f12019g = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z(String str) {
        NavigatorHolder naviHolder;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if ((this.f12019g == null || !str.equals(this.f12020h)) && (naviHolder = this.mFragment.getNaviHolder()) != null) {
            y();
            this.f12020h = str;
            CustomChannelFollowView customChannelFollowView = new CustomChannelFollowView(this);
            this.f12019g = customChannelFollowView;
            customChannelFollowView.setChannelId(str, true);
            this.f12019g.resetFollowViewWidthAndHeight(this.f12022j, this.f12023k);
            boolean booleanExtra = getIntent().getBooleanExtra(MBaseKeyNames.IS_SHOW_MORE_BTN, true);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            if (booleanExtra) {
                layoutParams.addRule(0, R.id.vg);
            } else {
                layoutParams.addRule(0, R.id.vf);
            }
            layoutParams.addRule(15);
            naviHolder.getRightLayout().addView(this.f12019g, layoutParams);
            naviHolder.getTitleTextView().setMaxWidth(DPIUtil.dip2px(160.0f));
            naviHolder.setOutSideShow(false);
            naviHolder.setNaviStateListener(new a());
            this.mFragment.getJdWebView().setWebViewClientListener(new b(this.mFragment.getWebUiBinder()));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.WebActivity, com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity
    public void onResumeFragments() {
        super.onResumeFragments();
        if (this.f12021i) {
            this.f12021i = false;
            CommonMFragment commonMFragment = this.mFragment;
            if (commonMFragment == null || commonMFragment.getJdWebView() == null || this.f12019g != null) {
                return;
            }
            String str = this.mFragment.getWebEntity().urlFromIntent;
            if (TextUtils.isEmpty(str)) {
                return;
            }
            try {
                str = URLDecoder.decode(str, "utf-8");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            z(URLUtils.getQueryParameter(str, "collectionId"));
        }
    }
}
