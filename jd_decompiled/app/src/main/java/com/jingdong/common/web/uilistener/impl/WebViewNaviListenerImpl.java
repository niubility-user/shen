package com.jingdong.common.web.uilistener.impl;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.deeplinkhelper.DeepLinkProductListHelper;
import com.jingdong.common.entity.ShareInfo;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.jump.OpenAppJumpController;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.utils.JumpMessageActivityUtil;
import com.jingdong.common.utils.ShareUtil;
import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.entity.JsBridgeEntity;
import com.jingdong.common.web.ui.CommonMFragment;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.uilistener.WebViewNaviListener;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.URLParamMap;

/* loaded from: classes12.dex */
public class WebViewNaviListenerImpl extends BaseWebComponent implements WebViewNaviListener {
    private final String TAG;
    private JsBridgeEntity jsBridgeEntity;

    public WebViewNaviListenerImpl(IWebUiBinder iWebUiBinder) {
        super(iWebUiBinder);
        this.TAG = WebViewNaviListenerImpl.class.getSimpleName();
        this.jsBridgeEntity = iWebUiBinder.getWebEntity().jsBridgeEntity;
    }

    @Override // com.jingdong.common.web.uilistener.WebViewNaviListener
    public void onClickCalendar() {
    }

    @Override // com.jingdong.common.web.uilistener.WebViewNaviListener
    public void onClickCart() {
        JumpUtil.execJumpByDes(JumpUtil.VALUE_DES_GO_CART, this.webUiBinder.getBaseActivity(), null);
        JDMtaUtils.onClick(this.webUiBinder.getBaseActivity(), "MWebview_RightTopShopcart", this.TAG, "", this.webUiBinder.getJdWebView().getFinalUrl());
    }

    @Override // com.jingdong.common.web.uilistener.WebViewNaviListener
    public void onClickCustom(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                Uri parse = Uri.parse(str);
                if (parse.getScheme().contains("http")) {
                    this.webUiBinder.getWebEntity().urlMap = new URLParamMap();
                    this.webUiBinder.getWebEntity().urlMap.put(RemoteMessageConst.TO, str);
                    if (this.webUiBinder.getUi() instanceof CommonMFragment) {
                        ((CommonMFragment) this.webUiBinder.getUi()).gentoken();
                    }
                } else if (JumpUtil.isJdScheme(parse.getScheme())) {
                    Intent intent = new Intent();
                    intent.setData(parse);
                    OpenAppJumpController.dispatchJumpRequest(this.webUiBinder.getBaseActivity(), intent);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        JDMtaUtils.onClick(this.webUiBinder.getBaseActivity(), "MWebview_RightTopDIY", this.TAG, str, this.webUiBinder.getJdWebView().getFinalUrl());
    }

    @Override // com.jingdong.common.web.uilistener.WebViewNaviListener
    public void onClickHome() {
        JumpUtil.execJumpByDes(JumpUtil.VALUE_DES_APPHOME, this.webUiBinder.getBaseActivity(), null);
    }

    @Override // com.jingdong.common.web.uilistener.WebViewNaviListener
    public void onClickMore() {
        JDMtaUtils.onClick(this.webUiBinder.getBaseActivity(), "MWebview_MoreFuntion", this.TAG, "", this.webUiBinder.getJdWebView().getFinalUrl());
    }

    @Override // com.jingdong.common.web.uilistener.WebViewNaviListener
    public void onClickMsg() {
        if (LoginUserBase.hasLogin()) {
            this.webUiBinder.getJdWebView().setMsgRedPointNum(0);
            this.webUiBinder.getJdWebView().setRedPointVisibility(false);
        }
        JumpMessageActivityUtil.jumpToMessageCenter(this.webUiBinder.getBaseActivity());
    }

    @Override // com.jingdong.common.web.uilistener.WebViewNaviListener
    public void onClickPopCart() {
        JumpUtil.execJumpByDes(JumpUtil.VALUE_DES_GO_CART, this.webUiBinder.getBaseActivity(), null);
        JDMtaUtils.onClick(this.webUiBinder.getBaseActivity(), "MWebview_MoretoCart", this.TAG, "", this.webUiBinder.getJdWebView().getFinalUrl());
    }

    @Override // com.jingdong.common.web.uilistener.WebViewNaviListener
    public void onClickPopCustom(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                Uri parse = Uri.parse(str);
                if (parse.getScheme().contains("http")) {
                    this.webUiBinder.getWebEntity().urlMap = new URLParamMap();
                    this.webUiBinder.getWebEntity().urlMap.put(RemoteMessageConst.TO, str);
                    if (this.webUiBinder.getUi() instanceof CommonMFragment) {
                        ((CommonMFragment) this.webUiBinder.getUi()).gentoken();
                    }
                } else if (JumpUtil.isJdScheme(parse.getScheme())) {
                    Intent intent = new Intent();
                    intent.setData(parse);
                    OpenAppJumpController.dispatchJumpRequest(this.webUiBinder.getBaseActivity(), intent);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        JDMtaUtils.onClick(this.webUiBinder.getBaseActivity(), "MWebview_MoretoDIY", this.TAG, str, this.webUiBinder.getJdWebView().getFinalUrl());
    }

    @Override // com.jingdong.common.web.uilistener.WebViewNaviListener
    public void onClickPopFeedback() {
        JumpUtil.execJumpByDes("feedback", this.webUiBinder.getBaseActivity(), null);
        JDMtaUtils.onClick(this.webUiBinder.getBaseActivity(), "MWebview_MoretoFeedback", this.TAG, "", this.webUiBinder.getJdWebView().getFinalUrl());
    }

    @Override // com.jingdong.common.web.uilistener.WebViewNaviListener
    public void onClickPopHome() {
        JumpUtil.execJumpByDes(JumpUtil.VALUE_DES_APPHOME, this.webUiBinder.getBaseActivity(), null);
        JDMtaUtils.onClick(this.webUiBinder.getBaseActivity(), "MWebview_MoretoHome", this.TAG, "", this.webUiBinder.getJdWebView().getFinalUrl());
    }

    @Override // com.jingdong.common.web.uilistener.WebViewNaviListener
    public void onClickPopMsg() {
        if (LoginUserBase.hasLogin()) {
            this.webUiBinder.getJdWebView().setMsgRedPointNum(0);
            this.webUiBinder.getJdWebView().setRedPointVisibility(false);
        }
        JumpMessageActivityUtil.jumpToMessageCenter(this.webUiBinder.getBaseActivity());
        JDMtaUtils.onClick(this.webUiBinder.getBaseActivity(), "MWebview_MoretoMessage", this.TAG, "", this.webUiBinder.getJdWebView().getFinalUrl());
    }

    @Override // com.jingdong.common.web.uilistener.WebViewNaviListener
    public void onClickPopSearch() {
        DeepLinkProductListHelper.startSearchActivity(this.webUiBinder.getBaseActivity(), null);
        JDMtaUtils.onClick(this.webUiBinder.getBaseActivity(), "MWebview_MoretoSearch", this.TAG, "", this.webUiBinder.getJdWebView().getFinalUrl());
    }

    @Override // com.jingdong.common.web.uilistener.WebViewNaviListener
    public void onClickPopShare() {
        ShareInfo shareInfo = this.jsBridgeEntity.shareInfo;
        if (shareInfo != null) {
            if (shareInfo.isShareGift()) {
                ShareUtil.lottery(this.webUiBinder.getBaseActivity(), shareInfo, shareInfo.getIncentiveBizType(), shareInfo.getIncentiveBizId());
            } else {
                BaseActivity baseActivity = this.webUiBinder.getBaseActivity();
                JsBridgeEntity jsBridgeEntity = this.jsBridgeEntity;
                ShareUtil.showShareDialog(baseActivity, shareInfo, jsBridgeEntity.shareCallbackListener, jsBridgeEntity.shareClickCallbackListener);
            }
            JDMtaUtils.onClick(this.webUiBinder.getBaseActivity(), "MWebview_MoretoShare", this.TAG, shareInfo.getUrl(), this.webUiBinder.getJdWebView().getFinalUrl());
        }
    }

    @Override // com.jingdong.common.web.uilistener.WebViewNaviListener
    public void onClickSearch() {
        DeepLinkProductListHelper.startSearchActivity(this.webUiBinder.getBaseActivity(), null);
        JDMtaUtils.onClick(this.webUiBinder.getBaseActivity(), "MWebview_RightTopSearch", this.TAG, "", this.webUiBinder.getJdWebView().getFinalUrl());
    }

    @Override // com.jingdong.common.web.uilistener.WebViewNaviListener
    public void onClickShare() {
        ShareInfo shareInfo = this.jsBridgeEntity.shareInfo;
        if (shareInfo != null) {
            if (shareInfo.isShareGift()) {
                ShareUtil.lottery(this.webUiBinder.getBaseActivity(), shareInfo, shareInfo.getIncentiveBizType(), shareInfo.getIncentiveBizId());
            } else {
                BaseActivity baseActivity = this.webUiBinder.getBaseActivity();
                JsBridgeEntity jsBridgeEntity = this.jsBridgeEntity;
                ShareUtil.showShareDialog(baseActivity, shareInfo, jsBridgeEntity.shareCallbackListener, jsBridgeEntity.shareClickCallbackListener);
            }
            JDMtaUtils.onClick(this.webUiBinder.getBaseActivity(), "MWebview_RightTopShare", this.TAG, shareInfo.getUrl(), this.webUiBinder.getJdWebView().getFinalUrl());
        }
    }
}
