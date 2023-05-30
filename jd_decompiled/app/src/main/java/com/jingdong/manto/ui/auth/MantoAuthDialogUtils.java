package com.jingdong.manto.ui.auth;

import android.content.Context;
import com.jingdong.a;
import com.jingdong.manto.jsapi.auth.tools.AuthInfo;
import com.jingdong.manto.sdk.api.IMantoDialog;
import com.jingdong.manto.ui.auth.MantoAuthDialog;
import java.util.LinkedList;

/* loaded from: classes16.dex */
public class MantoAuthDialogUtils {
    public static MantoAuthDialog getDeviceAuthDialog(Context context, String str, String str2, MantoAuthDialog.Callback callback) {
        IMantoDialog iMantoDialog = (IMantoDialog) a.n(IMantoDialog.class);
        return iMantoDialog != null ? iMantoDialog.getDeviceAuthDialog(context, str, str2, callback) : new MantoDeviceAuthDialog(context, str, str2, callback);
    }

    public static MantoAuthDialog getUserInfoAuthDialog(Context context, LinkedList<AuthInfo> linkedList, String str, String str2, MantoAuthDialog.Callback callback) {
        IMantoDialog iMantoDialog = (IMantoDialog) a.n(IMantoDialog.class);
        return iMantoDialog != null ? iMantoDialog.getUserInfoAuthDialog(context, linkedList, str, str2, callback) : new MantoUserInfoAuthDialog(context, linkedList, str, str2, callback);
    }
}
