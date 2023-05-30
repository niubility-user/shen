package com.jingdong.app.mall.bundle.jd_wx_share.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.bundle.jd_wx_share.R;
import com.jingdong.c.a.b.c;
import com.jingdong.common.entity.ShareInfo;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMiniProgramObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/* loaded from: classes2.dex */
public class WeChatUtils implements c {
    public static final String SEPARATOR_SIGN = "##";
    public static final String S_WX_FRIENDS = "Wxfriends";
    public static final String S_WX_MOMENTS = "Wxmoments";
    private static final String TAG = "WeixinUtil";
    public static final int WX_SHARE_DESCRIPTION_LIMIT = 1024;
    public static final int WX_SHARE_TITLE_LIMIT = 512;
    private String appId = "";
    private IWXAPI wxApi;

    public static String addShareUrlParam(String str, String str2, String str3) {
        if (str.contains(str2 + ContainerUtils.KEY_VALUE_DELIMITER)) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str.contains("?") ? ContainerUtils.FIELD_DELIMITER : "?");
        return sb.toString() + str2 + ContainerUtils.KEY_VALUE_DELIMITER + str3;
    }

    public static String getShareUrl(String str, String str2) {
        if (str == null) {
            str = "";
        }
        return addShareUrlParam(addShareUrlParam(addShareUrlParam(addShareUrlParam(addShareUrlParam(str, "ad_od", "share"), "utm_source", "androidapp"), "utm_medium", "appshare"), "utm_campaign", "t_335139774"), "utm_term", str2);
    }

    @Override // com.jingdong.c.a.b.c
    public boolean check() {
        if (!isWXInstalled()) {
            ToastUtils.showToastY(JdSdk.getInstance().getApplicationContext(), R.string.share_wx_no_install);
            return false;
        } else if (isWXAppSupportAPI()) {
            return true;
        } else {
            ToastUtils.showToastY(JdSdk.getInstance().getApplicationContext(), R.string.share_wx_no_support);
            return false;
        }
    }

    @Override // com.jingdong.c.a.b.c
    public boolean checkSupportFileProvider() {
        return Build.VERSION.SDK_INT >= 24 && this.wxApi.getWXAppSupportAPI() >= 654314752;
    }

    public void createAndRegisterWX(Context context, Boolean bool) {
        try {
            String string = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getString("WX_SHARE_APPID");
            this.appId = string;
            IWXAPI createWXAPI = WXAPIFactory.createWXAPI(context, string, bool.booleanValue());
            this.wxApi = createWXAPI;
            createWXAPI.registerApp(this.appId);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, "Create weixin API failed.");
                OKLog.e(TAG, e2);
            }
        }
    }

    @Override // com.jingdong.c.a.b.c
    public void doWXShare(ShareInfo shareInfo, boolean z, boolean z2, byte[] bArr) {
        String titleTimeline;
        String wxMomentsContent;
        String str;
        WXMediaMessage wXMediaMessage;
        if (shareInfo == null || bArr == null) {
            return;
        }
        if (shareInfo.getTitle().length() > 512) {
            shareInfo.setTitle(shareInfo.getTitle().substring(0, 509) + "...");
        }
        if (shareInfo.getTitleTimeline().length() > 512) {
            shareInfo.setTitleTimeline(shareInfo.getTitleTimeline().substring(0, 509) + "...");
        }
        if (shareInfo.getTitleLottery().length() > 512) {
            shareInfo.setTitleLottery(shareInfo.getTitleLottery().substring(0, 509) + "...");
        }
        if (shareInfo.getSummaryLottery().length() > 1024) {
            shareInfo.setSummaryLottery(shareInfo.getSummaryLottery().substring(0, 1021) + "...");
        }
        if (shareInfo.getWxcontent().length() > 1024) {
            shareInfo.setWxcontent(shareInfo.getWxcontent().substring(0, 1021) + "...");
        }
        if (shareInfo.getWxMomentsContent().length() > 1024) {
            shareInfo.setWxMomentsContent(shareInfo.getWxMomentsContent().substring(0, 1021) + "...");
        }
        String cpsUrl = shareInfo.getCpsUrl();
        String transaction = shareInfo.getTransaction();
        if (z2) {
            titleTimeline = shareInfo.getTitle();
            wxMomentsContent = shareInfo.getWxcontent();
            str = transaction + "##Wxfriends";
            if (TextUtils.isEmpty(cpsUrl)) {
                cpsUrl = getShareUrl(shareInfo.getUrl(), "Wxfriends");
            } else {
                if (!TextUtils.isEmpty(shareInfo.getTitleLottery())) {
                    titleTimeline = shareInfo.getTitleLottery();
                }
                if (!TextUtils.isEmpty(shareInfo.getSummaryLottery())) {
                    wxMomentsContent = shareInfo.getSummaryLottery();
                }
            }
        } else {
            titleTimeline = shareInfo.getTitleTimeline();
            if (TextUtils.isEmpty(titleTimeline) || titleTimeline.equalsIgnoreCase(DYConstants.DY_NULL_STR)) {
                titleTimeline = shareInfo.getTitle();
            }
            wxMomentsContent = shareInfo.getWxMomentsContent();
            String str2 = transaction + "##Wxmoments";
            if (TextUtils.isEmpty(cpsUrl)) {
                cpsUrl = getShareUrl(shareInfo.getUrl(), "Wxmoments");
            } else {
                if (!TextUtils.isEmpty(shareInfo.getTitleLottery())) {
                    titleTimeline = shareInfo.getTitleLottery();
                }
                if (!TextUtils.isEmpty(shareInfo.getSummaryLottery())) {
                    wxMomentsContent = shareInfo.getSummaryLottery();
                }
            }
            str = str2;
        }
        if (z2 && z) {
            WXMiniProgramObject wXMiniProgramObject = new WXMiniProgramObject();
            wXMiniProgramObject.miniprogramType = shareInfo.getMpType();
            wXMiniProgramObject.webpageUrl = cpsUrl;
            wXMiniProgramObject.userName = shareInfo.getMpId();
            wXMiniProgramObject.path = getShareUrl(shareInfo.getMpPath(), "Wxfriends");
            wXMediaMessage = new WXMediaMessage(wXMiniProgramObject);
        } else {
            WXWebpageObject wXWebpageObject = new WXWebpageObject();
            wXWebpageObject.webpageUrl = cpsUrl;
            wXMediaMessage = new WXMediaMessage(wXWebpageObject);
        }
        wXMediaMessage.title = titleTimeline;
        wXMediaMessage.thumbData = bArr;
        wXMediaMessage.description = wxMomentsContent;
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = str;
        req.message = wXMediaMessage;
        req.scene = !z2 ? 1 : 0;
        getWXApi().sendReq(req);
    }

    @Override // com.jingdong.c.a.b.c
    public IWXAPI getWXApi() {
        if (this.wxApi == null) {
            createAndRegisterWX(JdSdk.getInstance().getApplication(), Boolean.TRUE);
        }
        return this.wxApi;
    }

    public boolean isWXAppSupportAPI() {
        return this.wxApi.getWXAppSupportAPI() >= 620823552;
    }

    public boolean isWXInstalled() {
        return this.wxApi.isWXAppInstalled();
    }

    @Override // com.jingdong.c.a.b.c
    public void doWXShare(ShareInfo shareInfo, boolean z, byte[] bArr, Bitmap bitmap) {
        if (shareInfo == null || bArr == null || bitmap == null) {
            return;
        }
        WXImageObject wXImageObject = new WXImageObject(bitmap);
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.mediaObject = wXImageObject;
        wXMediaMessage.title = shareInfo.getTitle();
        wXMediaMessage.thumbData = bArr;
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.message = wXMediaMessage;
        if (z) {
            req.transaction = shareInfo.getTransaction() + "##Wxfriends";
            req.scene = 0;
        } else {
            req.transaction = shareInfo.getTransaction() + "##Wxmoments";
            req.scene = 1;
        }
        getWXApi().sendReq(req);
    }

    @Override // com.jingdong.c.a.b.c
    public void doWXShare(ShareInfo shareInfo, boolean z, byte[] bArr, String str) {
        if (shareInfo == null || bArr == null || TextUtils.isEmpty(str)) {
            return;
        }
        WXImageObject wXImageObject = new WXImageObject();
        wXImageObject.setImagePath(str);
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.mediaObject = wXImageObject;
        wXMediaMessage.title = shareInfo.getTitle();
        wXMediaMessage.thumbData = bArr;
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.message = wXMediaMessage;
        if (z) {
            req.transaction = shareInfo.getTransaction() + "##Wxfriends";
            req.scene = 0;
        } else {
            req.transaction = shareInfo.getTransaction() + "##Wxmoments";
            req.scene = 1;
        }
        getWXApi().sendReq(req);
    }
}
