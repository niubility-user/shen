package com.jingdong.common.utils;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.ui.address.entity.PickUpSiteBean;
import com.jingdong.jdsdk.utils.Md5Encrypt;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes6.dex */
public class PickUpSiteUtil {
    private static final String PICK_UP_SITEL_TAG = "Site_";
    private static final String PICK_UP_SITE_ALL = "PickUpSiteAll";
    private static final String TAG = "PickUpSiteUtil";
    private static SharedPreferences sharedPreferences = CommonBase.getJdSharedPreferences();

    private static String getGlobalUserKeyMD5() {
        String loginUserName = LoginUserBase.getLoginUserName();
        String str = "";
        if (!TextUtils.isEmpty(loginUserName)) {
            try {
                str = Md5Encrypt.md5(loginUserName);
            } catch (Exception unused) {
            }
        }
        return PICK_UP_SITEL_TAG + str;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0070 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x005c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static PickUpSiteBean getPickUpSite() {
        String str;
        String str2;
        PickUpSiteBean pickUpSiteBean;
        PickUpSiteBean pickUpSiteBean2;
        String globalUserKeyMD5 = getGlobalUserKeyMD5();
        try {
            str = sharedPreferences.getString(globalUserKeyMD5, "");
            try {
                str2 = sharedPreferences.getString(PICK_UP_SITE_ALL, "");
                try {
                    if (OKLog.D) {
                        OKLog.d(TAG, "1.\u83b7\u53d6\u5168\u5c40\u5730\u5740\u7f13\u5b58 PICK_UP_SITE_USER " + globalUserKeyMD5 + " = " + str);
                        StringBuilder sb = new StringBuilder();
                        sb.append("2.\u83b7\u53d6\u5168\u5c40\u5730\u5740\u7f13\u5b58 PICK_UP_SITE_ALL = ");
                        sb.append(str2);
                        OKLog.d(TAG, sb.toString());
                    }
                } catch (Exception e2) {
                    e = e2;
                    OKLog.e(TAG, e);
                    if (!TextUtils.isEmpty(str)) {
                    }
                    pickUpSiteBean = null;
                    if (!TextUtils.isEmpty(str2)) {
                    }
                    pickUpSiteBean2 = null;
                    if (LoginUserBase.hasLogin()) {
                    }
                    return null;
                }
            } catch (Exception e3) {
                e = e3;
                str2 = null;
                OKLog.e(TAG, e);
                if (!TextUtils.isEmpty(str)) {
                }
                pickUpSiteBean = null;
                if (!TextUtils.isEmpty(str2)) {
                }
                pickUpSiteBean2 = null;
                if (LoginUserBase.hasLogin()) {
                }
                return null;
            }
        } catch (Exception e4) {
            e = e4;
            str = "";
        }
        if (!TextUtils.isEmpty(str)) {
            try {
                pickUpSiteBean = (PickUpSiteBean) JDJSON.parseObject(str, PickUpSiteBean.class);
            } catch (Exception e5) {
                OKLog.e(TAG, e5);
            }
            if (!TextUtils.isEmpty(str2)) {
                try {
                    pickUpSiteBean2 = (PickUpSiteBean) JDJSON.parseObject(str2, PickUpSiteBean.class);
                } catch (Exception e6) {
                    OKLog.e(TAG, e6);
                }
                if (LoginUserBase.hasLogin()) {
                    if (pickUpSiteBean != null) {
                        return pickUpSiteBean;
                    }
                    if (pickUpSiteBean2 != null) {
                        return pickUpSiteBean2;
                    }
                } else if (pickUpSiteBean2 != null) {
                    return pickUpSiteBean2;
                }
                return null;
            }
            pickUpSiteBean2 = null;
            if (LoginUserBase.hasLogin()) {
            }
            return null;
        }
        pickUpSiteBean = null;
        if (!TextUtils.isEmpty(str2)) {
        }
        pickUpSiteBean2 = null;
        if (LoginUserBase.hasLogin()) {
        }
        return null;
    }

    public static boolean updatePickUpSite(PickUpSiteBean pickUpSiteBean) {
        if (pickUpSiteBean == null) {
            return false;
        }
        String loginUserName = LoginUserBase.getLoginUserName();
        SharedPreferences.Editor edit = sharedPreferences.edit();
        if (!TextUtils.isEmpty(loginUserName)) {
            if (OKLog.D) {
                OKLog.d(TAG, "1.\u66f4\u65b0\u5168\u5c40\u81ea\u63d0\u70b9\u7f13\u5b58 PICK_UP_SITE_USER = " + loginUserName + " \u8be6\u7ec6\u5185\u5bb9=" + pickUpSiteBean.getSiteId() + pickUpSiteBean.getSiteName() + pickUpSiteBean.getWeight());
            }
            return edit.putString(getGlobalUserKeyMD5(), pickUpSiteBean.toString()).commit();
        }
        if (OKLog.D) {
            OKLog.d(TAG, "1.\u66f4\u65b0\u5168\u5c40\u81ea\u63d0\u70b9 PICK_UP_SITE_ALL = PickUpSiteAll \u8be6\u7ec6\u5185\u5bb9=" + pickUpSiteBean.getSiteId() + pickUpSiteBean.getSiteName() + pickUpSiteBean.getWeight());
        }
        return edit.putString(PICK_UP_SITE_ALL, pickUpSiteBean.toString()).commit();
    }
}
