package com.jingdong.manto.sdk.api;

import android.app.Activity;
import android.content.DialogInterface;
import com.jingdong.manto.sdk.IMantoSdkBase;

/* loaded from: classes16.dex */
public interface IModalDialog extends IMantoSdkBase {
    void show(Activity activity, String str, String str2, String str3, String str4, String str5, boolean z, String str6, DialogInterface.OnClickListener onClickListener, DialogInterface.OnClickListener onClickListener2, DialogInterface.OnCancelListener onCancelListener);
}
