package com.jdcloud.media.common.auth;

import android.content.Context;
import android.text.TextUtils;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jdcloud.media.common.bean.DescribeAuthenticateResult;
import com.jdcloud.media.common.util.SharePreferenceUtil;
import java.util.ArrayList;
import java.util.List;
import tv.danmaku.ijk.media.ext.mta.bean.PlayerReportInfoEntity;

/* loaded from: classes18.dex */
public class AuthUtil {
    private static final String TAG = "AuthUtil";
    private static List<String> authResult = new ArrayList();
    private static boolean hasStorage;
    private static Context mContext;
    private static String mPkgNam;
    private static Integer mVer;

    private static void dealResult(DescribeAuthenticateResult describeAuthenticateResult, AuthCallback authCallback) {
        doRealAuth(describeAuthenticateResult, describeAuthenticateResult.getLicense(), authCallback);
    }

    public static List<String> doAuth(Context context, String str, String str2, AuthCallback authCallback) {
        mContext = context;
        mPkgNam = str2;
        doRealAuth(null, str, authCallback);
        return authResult;
    }

    private static void doRealAuth(DescribeAuthenticateResult describeAuthenticateResult, String str, AuthCallback authCallback) {
        authCallback.hasAuth(AuthResult.SUCC.getErrorCode(), false);
        String[] strArr = {WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_LIVE, PlayerReportInfoEntity.PAGE_ID, "sre", "editor"};
        for (int i2 = 0; i2 < 4; i2++) {
            authResult.add(strArr[i2]);
        }
        if (describeAuthenticateResult != null) {
            SharePreferenceUtil.getInstance(mContext).update(describeAuthenticateResult);
        }
    }

    public static void readLocalAuth(Context context, AuthCallback authCallback) {
        DescribeAuthenticateResult authResult2 = SharePreferenceUtil.getInstance(context).getAuthResult();
        if (authResult2 == null || TextUtils.isEmpty(authResult2.getPId())) {
            return;
        }
        hasStorage = true;
        mVer = authResult2.getVer();
        dealResult(authResult2, authCallback);
    }
}
