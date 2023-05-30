package com.jingdong.common.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.appshare.R;
import com.jingdong.common.entity.ShareInfo;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMiniProgramObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/* loaded from: classes6.dex */
public class WeixinUtil {
    private static final String APP_ID = "wxe75a2e68877315fb";
    private static final String TAG = "WeixinUtil";
    public static final int WX_SHARE_DESCRIPTION_LIMIT = 1024;
    public static final int WX_SHARE_TITLE_LIMIT = 512;
    private static IWXAPI wxApi;

    public static boolean check() {
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

    public static boolean checkSupportFileProvider() {
        return Build.VERSION.SDK_INT >= 24 && getWXApi().getWXAppSupportAPI() >= 654314752;
    }

    public static void createAndRegisterWX(Context context, Boolean bool) {
        try {
            IWXAPI createWXAPI = WXAPIFactory.createWXAPI(context, "wxe75a2e68877315fb", bool.booleanValue());
            wxApi = createWXAPI;
            createWXAPI.registerApp("wxe75a2e68877315fb");
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, "Create weixin API failed.");
                OKLog.e(TAG, e2);
            }
        }
    }

    public static void doWXShare(ShareInfo shareInfo, boolean z, boolean z2, byte[] bArr) {
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
                cpsUrl = ShareUtil.getShareUrl(shareInfo.getUrl(), "Wxfriends");
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
                cpsUrl = ShareUtil.getShareUrl(shareInfo.getUrl(), "Wxmoments");
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
            wXMiniProgramObject.path = ShareUtil.getShareUrl(shareInfo.getMpPath(), "Wxfriends");
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

    public static IWXAPI getWXApi() {
        if (wxApi == null) {
            createAndRegisterWX(JdSdk.getInstance().getApplication(), Boolean.TRUE);
        }
        return wxApi;
    }

    public static boolean isWXAppSupportAPI() {
        return getWXApi().getWXAppSupportAPI() >= 620823552;
    }

    public static boolean isWXInstalled() {
        return getWXApi().isWXAppInstalled();
    }

    public static void wxLogin(String str) {
        if (!isWXInstalled()) {
            if (OKLog.D) {
                OKLog.i("JD_Smith", "Wechat not available.");
                return;
            }
            return;
        }
        if (OKLog.D) {
            OKLog.i("JD_Smith", "Wechat is ok.");
        }
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = str;
        getWXApi().sendReq(req);
    }

    public static void wxLoginWithToast(String str) {
        if (check()) {
            if (OKLog.D) {
                OKLog.i("JD_Smith", "Wechat is ok.");
            }
            SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = str;
            getWXApi().sendReq(req);
        }
    }

    public static void doWXShare(ShareInfo shareInfo, boolean z, byte[] bArr, Bitmap bitmap) {
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

    public static void doWXShare(ShareInfo shareInfo, boolean z, byte[] bArr, String str) {
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
