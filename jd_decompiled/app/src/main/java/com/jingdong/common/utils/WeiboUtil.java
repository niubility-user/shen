package com.jingdong.common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.basic.ShareActivity;
import com.jingdong.appshare.R;
import com.jingdong.common.entity.ShareInfo;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.openapi.IWBAPI;
import com.sina.weibo.sdk.openapi.WBAPIFactory;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* loaded from: classes6.dex */
public class WeiboUtil {
    private static final String APP_ID = "3677796771";
    private static final String REDIRECT_URL = "";
    private static final String SCOPE = "";
    private static final String TAG = "WeiboUtil";
    public static final int WB_SHARE_SUMMARY_LIMIT = 80;
    public static final int WB_SHARE_TITLE_LIMIT = 60;
    private static IWBAPI mWBShareApi;

    public static boolean check(Context context) {
        if (isWBInstalled(context)) {
            return true;
        }
        ToastUtils.showToastY(JdSdk.getInstance().getApplicationContext(), R.string.weibo_can_not_share);
        return false;
    }

    public static void createWBApi(Context context) {
        try {
            AuthInfo authInfo = new AuthInfo(context, APP_ID, "", "");
            IWBAPI createWBAPI = WBAPIFactory.createWBAPI(context);
            mWBShareApi = createWBAPI;
            createWBAPI.registerApp(context, authInfo);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, "Create weibo API failed.");
                OKLog.e(TAG, e2);
            }
        }
    }

    public static void doActivityResultIntent(Intent intent, ShareActivity shareActivity) {
        getWBShareApi(shareActivity).doResultIntent(intent, shareActivity);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13, types: [java.io.OutputStream, java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r0v24 */
    /* JADX WARN: Type inference failed for: r0v25 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r2v2, types: [com.sina.weibo.sdk.api.ImageObject] */
    /* JADX WARN: Type inference failed for: r7v1, types: [android.graphics.Bitmap] */
    public static void doWBShare(Activity activity, ShareInfo shareInfo, byte[] bArr) {
        Exception e2;
        if (activity == null || shareInfo == null || bArr == null) {
            return;
        }
        if (shareInfo.getTitle().length() > 60) {
            shareInfo.setTitle(shareInfo.getTitle().substring(0, 57) + "...");
        }
        if (shareInfo.getSummary().length() > 80) {
            shareInfo.setSummary(shareInfo.getSummary().substring(0, 77) + "...");
        }
        String title = shareInfo.getTitle();
        WeiboMultiMessage weiboMultiMessage = new WeiboMultiMessage();
        TextObject textObject = new TextObject();
        textObject.text = shareInfo.getSummary();
        weiboMultiMessage.textObject = textObject;
        ?? imageObject = new ImageObject();
        ?? decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        imageObject.setImageData(decodeByteArray);
        weiboMultiMessage.imageObject = imageObject;
        WebpageObject webpageObject = new WebpageObject();
        if (!title.contains(DYConstants.DY_REGEX_AT)) {
            title = title + JdSdk.getInstance().getApplication().getResources().getString(R.string.share_at_jingdong) + LangUtils.SINGLE_SPACE;
        }
        webpageObject.title = title;
        String shareUrl = ShareUtil.getShareUrl(shareInfo.getUrl(), "Sinaweibo");
        webpageObject.actionUrl = shareUrl;
        if (shareUrl.length() > 512) {
            ToastUtils.shortToast(JdSdk.getInstance().getApplicationContext(), R.string.weibo_url_too_long);
            return;
        }
        webpageObject.defaultText = title;
        webpageObject.description = shareInfo.getSummary();
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            try {
                try {
                    title = new ByteArrayOutputStream();
                } catch (Exception e3) {
                    title = 0;
                    e2 = e3;
                } catch (Throwable th) {
                    th = th;
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
                try {
                    decodeByteArray.compress(Bitmap.CompressFormat.JPEG, 85, title);
                    webpageObject.thumbData = title.toByteArray();
                    title.close();
                    title = title;
                } catch (Exception e5) {
                    e2 = e5;
                    e2.printStackTrace();
                    if (title != 0) {
                        title.close();
                        title = title;
                    }
                    weiboMultiMessage.mediaObject = webpageObject;
                    getWBShareApi(activity).shareMessage(activity, weiboMultiMessage, true);
                }
            } catch (IOException e6) {
                e6.printStackTrace();
            }
            weiboMultiMessage.mediaObject = webpageObject;
            try {
                getWBShareApi(activity).shareMessage(activity, weiboMultiMessage, true);
            } catch (Throwable th2) {
                ExceptionReporter.reportKeyShareException("applyError", "codeException", "\u5fae\u535a\u5206\u4eab\u5931\u8d25" + th2.getMessage(), "");
            }
        } catch (Throwable th3) {
            th = th3;
            byteArrayOutputStream = title;
        }
    }

    public static IWBAPI getWBShareApi(Context context) {
        if (mWBShareApi == null) {
            createWBApi(context);
        }
        return mWBShareApi;
    }

    public static boolean isWBInstalled(Context context) {
        return getWBShareApi(context).isWBAppInstalled();
    }
}
