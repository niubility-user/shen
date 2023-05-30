package com.jingdong.manto.pkg.db.entity;

import androidx.annotation.NonNull;
import com.jingdong.common.messagecenter.NotificationMessageSummary;
import com.jingdong.manto.jsapi.auth.tools.AuthInfo;
import com.jingdong.manto.provider.db.anno.Table;
import com.jingdong.manto.provider.db.anno.TableField;

@Table(primaryKeys = {"APP_ID", "SCOPE"}, value = "localExtAuth")
/* loaded from: classes16.dex */
public class LocalExtAuthEntity {
    @NonNull
    @TableField("APP_ID")
    public String appId;
    @NonNull
    @TableField("DESCRIPTION")
    public String description;
    @TableField("PERMISSION")
    public String permission;
    @NonNull
    @TableField("SCOPE")
    public String scope;
    @TableField("STATE")
    public int state;
    @NonNull
    @TableField(NotificationMessageSummary.TITLE_KEY)
    public String title;

    public LocalExtAuthEntity() {
        this.appId = "";
        this.scope = "";
        this.permission = "";
        this.title = "";
        this.description = "";
        this.state = 0;
    }

    public LocalExtAuthEntity(@NonNull String str, AuthInfo authInfo) {
        this.appId = "";
        this.scope = "";
        this.permission = "";
        this.title = "";
        this.description = "";
        this.state = 0;
        this.appId = str;
        this.title = authInfo.title;
        this.scope = authInfo.scope;
        this.permission = authInfo.permission;
        this.description = authInfo.description;
        this.state = authInfo.state.value();
    }
}
