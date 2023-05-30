package com.jingdong.common.web.ui;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.web.uilistener.impl.WebViewClientListenerImpl;
import com.jingdong.common.web.util.URLUtils;
import com.jingdong.common.widget.NavigatorHolder;
import com.jingdong.common.widget.custom.CustomChannelFollowView;
import com.jingdong.sdk.navigatorholder.R;
import com.jingdong.sdk.utils.DPIUtil;
import com.tencent.smtt.sdk.WebView;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/* loaded from: classes12.dex */
public class FollowMFragment extends CommonMFragment {
    private static final String ID_KEY = "collectionId";
    private CustomChannelFollowView followView;
    private String oldChannelId;
    private boolean firstResume = true;
    private int width = DPIUtil.dip2px(59.0f);
    private int height = DPIUtil.dip2px(22.0f);

    public void dissGuanzhu() {
        CustomChannelFollowView customChannelFollowView = this.followView;
        if (customChannelFollowView == null || customChannelFollowView.getParent() == null) {
            return;
        }
        ((ViewGroup) this.followView.getParent()).removeView(this.followView);
        this.followView = null;
    }

    public void initGuanzhu(String str) {
        NavigatorHolder naviHolder;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if ((this.followView == null || !str.equals(this.oldChannelId)) && (naviHolder = getNaviHolder()) != null) {
            dissGuanzhu();
            this.oldChannelId = str;
            CustomChannelFollowView customChannelFollowView = new CustomChannelFollowView(getActivity());
            this.followView = customChannelFollowView;
            customChannelFollowView.setChannelId(str, true);
            this.followView.resetFollowViewWidthAndHeight(this.width, this.height);
            boolean z = getArguments().getBoolean(MBaseKeyNames.IS_SHOW_MORE_BTN, true);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            if (z) {
                layoutParams.addRule(0, R.id.common_navi_first_seat);
            } else {
                layoutParams.addRule(0, R.id.common_title_right_empty_view);
            }
            layoutParams.addRule(15);
            naviHolder.getRightLayout().addView(this.followView, layoutParams);
            naviHolder.getTitleTextView().setMaxWidth(DPIUtil.dip2px(160.0f));
            naviHolder.setOutSideShow(false);
            naviHolder.setNaviStateListener(new NavigatorHolder.NaviStateListener() { // from class: com.jingdong.common.web.ui.FollowMFragment.1
                {
                    FollowMFragment.this = this;
                }

                @Override // com.jingdong.common.widget.NavigatorHolder.NaviStateListener
                public void onStyleChanged(int i2) {
                    if (FollowMFragment.this.followView != null) {
                        FollowMFragment.this.followView.changeIcon(i2 != 2);
                    }
                }
            });
            getJdWebView().setWebViewClientListener(new WebViewClientListenerImpl(getWebUiBinder()) { // from class: com.jingdong.common.web.ui.FollowMFragment.2
                {
                    FollowMFragment.this = this;
                }

                @Override // com.jingdong.common.web.uilistener.impl.WebViewClientListenerImpl, com.jingdong.common.web.uilistener.WebViewClientListener
                public void onPageStarted(WebView webView, String str2, Bitmap bitmap) {
                    super.onPageStarted(webView, str2, bitmap);
                    if (TextUtils.isEmpty(str2) || str2.startsWith("https://un.m.jd.com/") || str2.startsWith("https://beta-un.m.jd.com/")) {
                        return;
                    }
                    String str3 = null;
                    try {
                        str3 = URLUtils.getQueryParameter(URLDecoder.decode(str2, "utf-8"), FollowMFragment.ID_KEY);
                    } catch (UnsupportedEncodingException e2) {
                        e2.printStackTrace();
                    }
                    if (TextUtils.isEmpty(str3)) {
                        FollowMFragment.this.dissGuanzhu();
                        FollowMFragment.this.getNaviHolder().setOutSideShow(true);
                        return;
                    }
                    FollowMFragment.this.initGuanzhu(str3);
                }
            });
        }
    }

    @Override // com.jingdong.common.web.ui.CommonMFragment, com.jingdong.sdk.platform.lib.ui.CompactFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.firstResume) {
            this.firstResume = false;
            if (getJdWebView() != null && this.followView == null) {
                String str = getWebEntity().urlFromIntent;
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                try {
                    str = URLDecoder.decode(str, "utf-8");
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                initGuanzhu(URLUtils.getQueryParameter(str, ID_KEY));
            }
        }
    }
}
