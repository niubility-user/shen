package com.jingdong.common.entity;

import android.text.TextUtils;

/* loaded from: classes5.dex */
public class ShareEntity {
    public String avatar;
    public String content;
    public String srv;
    public String title;
    public String url;

    public String getSrv() {
        return TextUtils.isEmpty(this.srv) ? "" : this.srv;
    }
}
