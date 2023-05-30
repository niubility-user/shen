package com.jingdong.app.mall.bundle.jd_qq_share;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.app.mall.basic.ShareActivity;
import com.jingdong.common.entity.ShareInfo;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.common.utils.ShareUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.business.personal.R2;
import com.tencent.tauth.Tencent;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class QQUtils implements com.jingdong.c.a.b.a {
    private static final int QQ_SHARE_SUMMARY_LIMIT = 30;
    private static final int QQ_SHARE_TITLE_LIMIT = 30;
    private static final int QZONE_SHARE_SUMMARY_LIMIT = 600;
    private static final int QZONE_SHARE_TITLE_LIMIT = 200;
    private static final String TAG = "QQUtil";
    private static a mBaseUiListener;
    private static Tencent mTencent;

    private String getIconUrl(Activity activity, String str) {
        return TextUtils.isEmpty(str) ? activity.getString(R.string.share_sdk_default_icon_url) : str;
    }

    @Override // com.jingdong.c.a.b.a
    public boolean check(Context context) {
        Tencent.resetQQAppInfoCache();
        if (getTencentInstance().isQQInstalled(context)) {
            return true;
        }
        ToastUtils.showToastY(JdSdk.getInstance().getApplicationContext(), R.string.qq_no_install);
        return false;
    }

    @Override // com.jingdong.c.a.b.a
    public Tencent getTencentInstance() {
        try {
            if (mTencent == null) {
                Application application = JdSdk.getInstance().getApplication();
                mTencent = Tencent.createInstance(application.getPackageManager().getApplicationInfo(application.getPackageName(), 128).metaData.getString("QQ_SHARE_APPID"), JdSdk.getInstance().getApplicationContext(), "com.jingdong.app.mall.provider.shareprovider");
                if (JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplication())) {
                    Tencent.setIsPermissionGranted(true, BaseInfo.getDeviceModel());
                }
            }
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, "Create QQ API failed.");
                OKLog.e(TAG, e2);
            }
        }
        return mTencent;
    }

    @Override // com.jingdong.c.a.b.a
    public void injectIUiListener(ShareActivity shareActivity) {
        mBaseUiListener = new a(shareActivity);
    }

    @Override // com.jingdong.c.a.b.a
    public boolean onActivityResultData(int i2, int i3, Intent intent) {
        a aVar = mBaseUiListener;
        if (aVar == null) {
            return false;
        }
        return Tencent.onActivityResultData(i2, i3, intent, aVar);
    }

    @Override // com.jingdong.c.a.b.a
    public void shareToQQ(Activity activity, ShareInfo shareInfo) {
        if (activity == null || shareInfo == null || mBaseUiListener == null) {
            return;
        }
        if (shareInfo.getTitle().length() > 30) {
            shareInfo.setTitle(shareInfo.getTitle().substring(0, 27) + "...");
        }
        if (shareInfo.getTitleLottery().length() > 30) {
            shareInfo.setTitleLottery(shareInfo.getTitleLottery().substring(0, 27) + "...");
        }
        if (shareInfo.getSummary().length() > 30) {
            shareInfo.setSummary(shareInfo.getSummary().substring(0, 27) + "...");
        }
        if (shareInfo.getSummaryLottery().length() > 30) {
            shareInfo.setSummaryLottery(shareInfo.getSummaryLottery().substring(0, 27) + "...");
        }
        String title = shareInfo.getTitle();
        String summary = shareInfo.getSummary();
        String cpsUrl = shareInfo.getCpsUrl();
        if (TextUtils.isEmpty(cpsUrl)) {
            cpsUrl = ShareUtil.getShareUrl(shareInfo.getUrl(), "QQfriends");
        } else {
            if (!TextUtils.isEmpty(shareInfo.getTitleLottery())) {
                title = shareInfo.getTitleLottery();
            }
            if (!TextUtils.isEmpty(shareInfo.getSummaryLottery())) {
                summary = shareInfo.getSummaryLottery();
            }
        }
        Bundle bundle = new Bundle();
        bundle.putInt("req_type", 1);
        bundle.putInt("cflag", 2);
        bundle.putString("targetUrl", cpsUrl);
        bundle.putString("title", title);
        bundle.putString("summary", summary);
        bundle.putString("imageUrl", getIconUrl(activity, shareInfo.getIconUrl()));
        bundle.putString("appName", activity.getString(R.string.back_to_jd));
        if (getTencentInstance() != null) {
            getTencentInstance().shareToQQ(activity, bundle, mBaseUiListener);
        }
    }

    @Override // com.jingdong.c.a.b.a
    public void shareToQZone(Activity activity, ShareInfo shareInfo) {
        if (activity == null || shareInfo == null || mBaseUiListener == null) {
            return;
        }
        if (shareInfo.getTitle().length() > 200) {
            shareInfo.setTitle(shareInfo.getTitle().substring(0, R2.anim.slide_out_to_top) + "...");
        }
        if (shareInfo.getTitleLottery().length() > 200) {
            shareInfo.setTitleLottery(shareInfo.getTitleLottery().substring(0, R2.anim.slide_out_to_top) + "...");
        }
        if (shareInfo.getSummary().length() > 600) {
            shareInfo.setSummary(shareInfo.getSummary().substring(0, R2.attr.circleCrop) + "...");
        }
        if (shareInfo.getSummaryLottery().length() > 600) {
            shareInfo.setSummaryLottery(shareInfo.getSummaryLottery().substring(0, R2.attr.circleCrop) + "...");
        }
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(getIconUrl(activity, shareInfo.getIconUrl()));
        String title = shareInfo.getTitle();
        String summary = shareInfo.getSummary();
        String cpsUrl = shareInfo.getCpsUrl();
        if (TextUtils.isEmpty(cpsUrl)) {
            cpsUrl = ShareUtil.getShareUrl(shareInfo.getUrl(), "QQzone");
        } else {
            if (!TextUtils.isEmpty(shareInfo.getTitleLottery())) {
                title = shareInfo.getTitleLottery();
            }
            if (!TextUtils.isEmpty(shareInfo.getSummaryLottery())) {
                summary = shareInfo.getSummaryLottery();
            }
        }
        Bundle bundle = new Bundle();
        bundle.putInt("req_type", 1);
        bundle.putString("title", title);
        bundle.putString("summary", summary);
        bundle.putString("targetUrl", cpsUrl);
        bundle.putStringArrayList("imageUrl", arrayList);
        if (getTencentInstance() != null) {
            getTencentInstance().shareToQzone(activity, bundle, mBaseUiListener);
        }
    }

    @Override // com.jingdong.c.a.b.a
    public void shareToQQ(Activity activity, ShareInfo shareInfo, String str) {
        if (activity == null || shareInfo == null || TextUtils.isEmpty(str) || mBaseUiListener == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("req_type", 5);
        bundle.putInt("cflag", 2);
        bundle.putString("imageLocalUrl", str);
        bundle.putString("appName", activity.getString(R.string.back_to_jd));
        if (getTencentInstance() != null) {
            getTencentInstance().shareToQQ(activity, bundle, mBaseUiListener);
        }
    }
}
