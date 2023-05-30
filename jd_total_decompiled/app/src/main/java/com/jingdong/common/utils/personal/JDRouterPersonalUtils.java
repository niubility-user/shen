package com.jingdong.common.utils.personal;

import android.text.TextUtils;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.JDRouter;
import com.jingdong.common.unification.router.JDRouterMtaUtil;
import com.jingdong.common.unification.router.JDRouterUrlBuilder;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.oklog.OKLog;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/* loaded from: classes6.dex */
public class JDRouterPersonalUtils {
    private static final String TAG = "JDRouterPersonalUtils";

    public static void jdRouterToContentFocusActivity(final BaseActivity baseActivity, String str) {
        if (baseActivity == null) {
            return;
        }
        JDRouterUrlBuilder jDRouterUrlBuilder = new JDRouterUrlBuilder();
        if (!TextUtils.isEmpty(str)) {
            jDRouterUrlBuilder.putStringParam(JDPersonalConstant.PERSONAL_CONTENT_FOCUS_TITLE, str);
        }
        final String build = jDRouterUrlBuilder.setModuleName("JDMyJdModule").setMethodName("showPersonalContentFocus").build();
        JDRouter.build(baseActivity, build).callBackListener(new CallBackListener() { // from class: com.jingdong.common.utils.personal.JDRouterPersonalUtils.3
            @Override // com.jingdong.common.unification.router.CallBackListener
            public void onComplete() {
                if (OKLog.D) {
                    OKLog.d(JDRouterPersonalUtils.TAG, "jdRouterToContentFocusActivity onComplete");
                }
            }

            @Override // com.jingdong.common.unification.router.CallBackListener
            public void onError(int i2) {
                if (OKLog.D) {
                    OKLog.d(JDRouterPersonalUtils.TAG, "jdRouterToPersonalHomePage onError");
                }
                JDRouterMtaUtil.routerErrorMta(BaseActivity.this, "PersonalPageHomeActivity", build + CartConstant.KEY_YB_INFO_LINK + Integer.toString(i2));
            }
        }).open();
    }

    public static void jdRouterToPersonalHomePage(BaseActivity baseActivity) {
        jdRouterToPersonalHomePage(baseActivity, "");
    }

    public static void jdRouterToPersonalHomePageWithRequestCode(BaseActivity baseActivity, int i2) {
        jdRouterToPersonalHomePageWithRequestCode(baseActivity, "", i2);
    }

    public static void jdRouterToPersonalHomePage(final BaseActivity baseActivity, String str) {
        if (baseActivity == null) {
            return;
        }
        JDRouterUrlBuilder jDRouterUrlBuilder = new JDRouterUrlBuilder();
        if (!TextUtils.isEmpty(str)) {
            try {
                jDRouterUrlBuilder.putStringParam("pin", URLEncoder.encode(str, "utf-8"));
            } catch (UnsupportedEncodingException e2) {
                OKLog.e(TAG, e2);
            }
        }
        final String build = jDRouterUrlBuilder.setModuleName("JDMyJdModule").setMethodName("showPersonalHomePageWithRequestCode").build();
        if (OKLog.D) {
            OKLog.d(TAG, "url===" + build);
        }
        JDRouter.build(baseActivity, build).callBackListener(new CallBackListener() { // from class: com.jingdong.common.utils.personal.JDRouterPersonalUtils.1
            @Override // com.jingdong.common.unification.router.CallBackListener
            public void onComplete() {
                if (OKLog.D) {
                    OKLog.d(JDRouterPersonalUtils.TAG, "jdRouterToPersonalHomePage onComplete");
                }
            }

            @Override // com.jingdong.common.unification.router.CallBackListener
            public void onError(int i2) {
                if (OKLog.D) {
                    OKLog.d(JDRouterPersonalUtils.TAG, "jdRouterToPersonalHomePage onError");
                }
                JDRouterMtaUtil.routerErrorMta(BaseActivity.this, "PersonalPageHomeActivity", build + CartConstant.KEY_YB_INFO_LINK + Integer.toString(i2));
            }
        }).requestCode(-1).open();
    }

    public static void jdRouterToPersonalHomePageWithRequestCode(final BaseActivity baseActivity, String str, int i2) {
        if (baseActivity == null) {
            return;
        }
        JDRouterUrlBuilder jDRouterUrlBuilder = new JDRouterUrlBuilder();
        if (!TextUtils.isEmpty(str)) {
            try {
                jDRouterUrlBuilder.putStringParam("pin", URLEncoder.encode(str, "utf-8"));
            } catch (UnsupportedEncodingException e2) {
                OKLog.e(TAG, e2);
            }
        }
        final String build = jDRouterUrlBuilder.setModuleName("JDMyJdModule").setMethodName("showPersonalHomePageWithRequestCode").build();
        if (OKLog.D) {
            OKLog.d(TAG, "url===" + build);
        }
        JDRouter.build(baseActivity, build).callBackListener(new CallBackListener() { // from class: com.jingdong.common.utils.personal.JDRouterPersonalUtils.2
            @Override // com.jingdong.common.unification.router.CallBackListener
            public void onComplete() {
                if (OKLog.D) {
                    OKLog.d(JDRouterPersonalUtils.TAG, "jdRouterToPersonalHomePage onComplete");
                }
            }

            @Override // com.jingdong.common.unification.router.CallBackListener
            public void onError(int i3) {
                if (OKLog.D) {
                    OKLog.d(JDRouterPersonalUtils.TAG, "jdRouterToPersonalHomePage onError");
                }
                JDRouterMtaUtil.routerErrorMta(BaseActivity.this, "PersonalPageHomeActivity", build + CartConstant.KEY_YB_INFO_LINK + Integer.toString(i3));
            }
        }).requestCode(i2).open();
    }
}
