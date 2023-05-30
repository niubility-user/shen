package com.jingdong.manto.sdk.api;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import com.jingdong.manto.jsapi.auth.tools.AuthInfo;
import com.jingdong.manto.sdk.IMantoSdkBase;
import com.jingdong.manto.ui.auth.MantoAuthDialog;
import java.util.LinkedList;

/* loaded from: classes16.dex */
public interface IMantoDialog extends IMantoSdkBase {
    MantoAuthDialog getDeviceAuthDialog(Context context, String str, String str2, MantoAuthDialog.Callback callback);

    Dialog getMantoDialog(Context context, String str, String str2, String str3, String str4, DialogInterface.OnClickListener onClickListener, DialogInterface.OnClickListener onClickListener2, DialogInterface.OnCancelListener onCancelListener, DialogInterface.OnDismissListener onDismissListener, DialogInterface.OnKeyListener onKeyListener);

    MantoAuthDialog getUserInfoAuthDialog(Context context, LinkedList<AuthInfo> linkedList, String str, String str2, MantoAuthDialog.Callback callback);
}
