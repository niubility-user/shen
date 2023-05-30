package com.jingdong.common.login;

import com.jingdong.cleanmvp.common.BaseEvent;

/* loaded from: classes.dex */
public class LoginEvent extends BaseEvent {
    public static final String TYPE_DIALOGLOGIN_CLOSE = "type_dialoglogin_close";
    public static final String TYPE_LOGIN = "type_login";
    public static final String TYPE_LOGOUT = "type_logout";
    public static final String TYPE_READY_LOGOUT = "type_ready_logout";
    public static final String TYPE_SELECTED_COUNTRY = "type_selected_country";

    public LoginEvent(String str) {
        super(str);
    }
}
