package com.jingdong.common.utils;

import android.content.SharedPreferences;
import android.text.TextUtils;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.jingdong.common.ui.address.entity.PickUpSiteBean getPickUpSite() {
        /*
            java.lang.String r0 = ""
            java.lang.String r1 = "PickUpSiteUtil"
            java.lang.String r2 = getGlobalUserKeyMD5()
            r3 = 0
            android.content.SharedPreferences r4 = com.jingdong.common.utils.PickUpSiteUtil.sharedPreferences     // Catch: java.lang.Exception -> L50
            java.lang.String r4 = r4.getString(r2, r0)     // Catch: java.lang.Exception -> L50
            android.content.SharedPreferences r5 = com.jingdong.common.utils.PickUpSiteUtil.sharedPreferences     // Catch: java.lang.Exception -> L4e
            java.lang.String r6 = "PickUpSiteAll"
            java.lang.String r0 = r5.getString(r6, r0)     // Catch: java.lang.Exception -> L4e
            boolean r5 = com.jingdong.sdk.oklog.OKLog.D     // Catch: java.lang.Exception -> L4c
            if (r5 == 0) goto L56
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L4c
            r5.<init>()     // Catch: java.lang.Exception -> L4c
            java.lang.String r6 = "1.\u83b7\u53d6\u5168\u5c40\u5730\u5740\u7f13\u5b58 PICK_UP_SITE_USER "
            r5.append(r6)     // Catch: java.lang.Exception -> L4c
            r5.append(r2)     // Catch: java.lang.Exception -> L4c
            java.lang.String r2 = " = "
            r5.append(r2)     // Catch: java.lang.Exception -> L4c
            r5.append(r4)     // Catch: java.lang.Exception -> L4c
            java.lang.String r2 = r5.toString()     // Catch: java.lang.Exception -> L4c
            com.jingdong.sdk.oklog.OKLog.d(r1, r2)     // Catch: java.lang.Exception -> L4c
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L4c
            r2.<init>()     // Catch: java.lang.Exception -> L4c
            java.lang.String r5 = "2.\u83b7\u53d6\u5168\u5c40\u5730\u5740\u7f13\u5b58 PICK_UP_SITE_ALL = "
            r2.append(r5)     // Catch: java.lang.Exception -> L4c
            r2.append(r0)     // Catch: java.lang.Exception -> L4c
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Exception -> L4c
            com.jingdong.sdk.oklog.OKLog.d(r1, r2)     // Catch: java.lang.Exception -> L4c
            goto L56
        L4c:
            r2 = move-exception
            goto L53
        L4e:
            r2 = move-exception
            goto L52
        L50:
            r2 = move-exception
            r4 = r0
        L52:
            r0 = r3
        L53:
            com.jingdong.sdk.oklog.OKLog.e(r1, r2)
        L56:
            boolean r2 = android.text.TextUtils.isEmpty(r4)
            if (r2 != 0) goto L69
            java.lang.Class<com.jingdong.common.ui.address.entity.PickUpSiteBean> r2 = com.jingdong.common.ui.address.entity.PickUpSiteBean.class
            java.lang.Object r2 = com.jd.framework.json.JDJSON.parseObject(r4, r2)     // Catch: java.lang.Exception -> L65
            com.jingdong.common.ui.address.entity.PickUpSiteBean r2 = (com.jingdong.common.ui.address.entity.PickUpSiteBean) r2     // Catch: java.lang.Exception -> L65
            goto L6a
        L65:
            r2 = move-exception
            com.jingdong.sdk.oklog.OKLog.e(r1, r2)
        L69:
            r2 = r3
        L6a:
            boolean r4 = android.text.TextUtils.isEmpty(r0)
            if (r4 != 0) goto L7d
            java.lang.Class<com.jingdong.common.ui.address.entity.PickUpSiteBean> r4 = com.jingdong.common.ui.address.entity.PickUpSiteBean.class
            java.lang.Object r0 = com.jd.framework.json.JDJSON.parseObject(r0, r4)     // Catch: java.lang.Exception -> L79
            com.jingdong.common.ui.address.entity.PickUpSiteBean r0 = (com.jingdong.common.ui.address.entity.PickUpSiteBean) r0     // Catch: java.lang.Exception -> L79
            goto L7e
        L79:
            r0 = move-exception
            com.jingdong.sdk.oklog.OKLog.e(r1, r0)
        L7d:
            r0 = r3
        L7e:
            boolean r1 = com.jingdong.common.login.LoginUserBase.hasLogin()
            if (r1 == 0) goto L8a
            if (r2 == 0) goto L87
            return r2
        L87:
            if (r0 == 0) goto L8d
            return r0
        L8a:
            if (r0 == 0) goto L8d
            return r0
        L8d:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.utils.PickUpSiteUtil.getPickUpSite():com.jingdong.common.ui.address.entity.PickUpSiteBean");
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
