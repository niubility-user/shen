package com.jingdong.common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import com.jingdong.appshare.R;
import com.jingdong.common.entity.ShareInfo;
import com.jingdong.common.unification.navigationbar.db.NavigationDbConstants;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.platform.business.personal.R2;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class QQUtil {
    private static final String APP_ID = "100273020";
    private static final int QQ_SHARE_SUMMARY_LIMIT = 30;
    private static final int QQ_SHARE_TITLE_LIMIT = 30;
    private static final int QZONE_SHARE_SUMMARY_LIMIT = 600;
    private static final int QZONE_SHARE_TITLE_LIMIT = 200;
    private static final String TAG = "QQUtil";
    private static Tencent mTencent;

    public static boolean check(Context context) {
        Tencent.resetQQAppInfoCache();
        if (getTencentInstance().isQQInstalled(context)) {
            return true;
        }
        ToastUtils.showToastY(JdSdk.getInstance().getApplicationContext(), R.string.qq_no_install);
        return false;
    }

    private static boolean checkQQInstall() {
        try {
            JdSdk.getInstance().getApplication().getPackageManager().getApplicationInfo("com.tencent.mobileqq", 8192);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            ToastUtils.showToastY(JdSdk.getInstance().getApplicationContext(), R.string.share_check_install_qq);
            return false;
        }
    }

    private static String getIconUrl(Activity activity, String str) {
        return TextUtils.isEmpty(str) ? activity.getString(R.string.share_sdk_default_icon_url) : str;
    }

    public static Tencent getTencentInstance() {
        if (mTencent == null) {
            mTencent = Tencent.createInstance("100273020", JdSdk.getInstance().getApplicationContext(), "com.jingdong.app.mall.provider.shareprovider");
            if (JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplication())) {
                Tencent.setIsPermissionGranted(true, BaseInfo.getDeviceModel());
            }
        }
        return mTencent;
    }

    private static void hasAlreadyLogin(Activity activity) {
        Tencent tencentInstance = getTencentInstance();
        if (tencentInstance.isSessionValid()) {
            tencentInstance.logout(activity);
        }
    }

    public static void qqOpenSDKLogin(Activity activity, IUiListener iUiListener) {
        hasAlreadyLogin(activity);
        try {
            getTencentInstance().login(activity, NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_POSITION_ALL, iUiListener);
        } catch (Exception unused) {
        }
    }

    public static void qqOpenSDKLoginWithToast(Activity activity, IUiListener iUiListener) {
        if (checkQQInstall()) {
            hasAlreadyLogin(activity);
            try {
                getTencentInstance().login(activity, NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_POSITION_ALL, iUiListener);
            } catch (Exception unused) {
            }
        }
    }

    public static void shareToQQ(Activity activity, ShareInfo shareInfo, IUiListener iUiListener) {
        if (activity == null || shareInfo == null || iUiListener == null) {
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
            getTencentInstance().shareToQQ(activity, bundle, iUiListener);
        }
    }

    public static void shareToQZone(Activity activity, ShareInfo shareInfo, IUiListener iUiListener) {
        if (activity == null || shareInfo == null || iUiListener == null) {
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
            getTencentInstance().shareToQzone(activity, bundle, iUiListener);
        }
    }

    public static void qqOpenSDKLogin(Fragment fragment, IUiListener iUiListener) {
        hasAlreadyLogin(fragment.getActivity());
        try {
            getTencentInstance().login(fragment.getActivity(), NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_POSITION_ALL, iUiListener);
        } catch (Exception unused) {
        }
    }

    public static void qqOpenSDKLoginWithToast(Fragment fragment, IUiListener iUiListener) {
        if (checkQQInstall()) {
            hasAlreadyLogin(fragment.getActivity());
            try {
                getTencentInstance().login(fragment.getActivity(), NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_POSITION_ALL, iUiListener);
            } catch (Exception unused) {
            }
        }
    }

    public static void shareToQQ(Activity activity, ShareInfo shareInfo, String str, IUiListener iUiListener) {
        if (activity == null || shareInfo == null || TextUtils.isEmpty(str) || iUiListener == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("req_type", 5);
        bundle.putInt("cflag", 2);
        bundle.putString("imageLocalUrl", str);
        bundle.putString("appName", activity.getString(R.string.back_to_jd));
        if (getTencentInstance() != null) {
            getTencentInstance().shareToQQ(activity, bundle, iUiListener);
        }
    }
}
