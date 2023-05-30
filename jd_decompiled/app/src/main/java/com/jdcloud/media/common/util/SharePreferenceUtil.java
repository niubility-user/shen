package com.jdcloud.media.common.util;

import android.content.Context;
import android.content.SharedPreferences;
import com.jdcloud.media.common.bean.DescribeAuthenticateResult;

/* loaded from: classes18.dex */
public class SharePreferenceUtil {
    private static SharePreferenceUtil preferenceUtils;
    private String AUTH_BEAN = "auth_bean";
    private SharedPreferences sp;

    private SharePreferenceUtil(Context context) {
        this.sp = context.getSharedPreferences("auth_bean", 0);
    }

    public static synchronized SharePreferenceUtil getInstance(Context context) {
        SharePreferenceUtil sharePreferenceUtil;
        synchronized (SharePreferenceUtil.class) {
            if (preferenceUtils == null) {
                preferenceUtils = new SharePreferenceUtil(context);
            }
            sharePreferenceUtil = preferenceUtils;
        }
        return sharePreferenceUtil;
    }

    public DescribeAuthenticateResult getAuthResult() {
        DescribeAuthenticateResult describeAuthenticateResult = new DescribeAuthenticateResult();
        describeAuthenticateResult.setPId(this.sp.getString("pid", ""));
        describeAuthenticateResult.setStatus(Integer.valueOf(this.sp.getInt("status", 0)));
        describeAuthenticateResult.setBlacklist(Integer.valueOf(this.sp.getInt("blacklist", 0)));
        describeAuthenticateResult.setVer(Integer.valueOf(this.sp.getInt("ver", 0)));
        describeAuthenticateResult.setLicense(this.sp.getString("license", ""));
        return describeAuthenticateResult;
    }

    public void update(DescribeAuthenticateResult describeAuthenticateResult) {
        this.sp.edit().putString("pid", describeAuthenticateResult.getPId());
        this.sp.edit().putInt("ver", describeAuthenticateResult.getVer().intValue());
        this.sp.edit().putInt("blacklist", describeAuthenticateResult.getBlacklist().intValue());
        this.sp.edit().putInt("status", describeAuthenticateResult.getStatus().intValue());
        this.sp.edit().putString("license", describeAuthenticateResult.getLicense());
        this.sp.edit().commit();
    }
}
