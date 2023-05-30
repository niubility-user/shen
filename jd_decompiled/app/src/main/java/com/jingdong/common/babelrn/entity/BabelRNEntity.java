package com.jingdong.common.babelrn.entity;

import android.text.TextUtils;

/* loaded from: classes5.dex */
public class BabelRNEntity {
    public String activityId;
    public String appName;
    public String buildIn;
    public String debugHttpHost;
    public String forceRebuild;
    public String forceRn;
    public int isDebug;
    public String isDelete;
    public String jsBundleName;
    public String jsUrl;
    public String md5;
    public String moduleName;
    public String params;
    public String split;
    public String titleName;

    public boolean forceRebuild() {
        return "1".equals(this.forceRebuild);
    }

    public boolean forceToShowRN() {
        return "1".equals(this.forceRn);
    }

    public boolean isBuildIn() {
        return "1".equals(this.buildIn) && !TextUtils.isEmpty(this.moduleName);
    }

    public boolean isLegalToDownLoad() {
        return ("1".equals(this.buildIn) || TextUtils.isEmpty(this.moduleName) || TextUtils.isEmpty(this.jsUrl) || TextUtils.isEmpty(this.jsBundleName)) ? false : true;
    }
}
