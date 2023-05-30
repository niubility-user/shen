package com.jingdong.jdsdk.network.dependency;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import com.jingdong.common.utils.JDGetWayQueueTools;

/* loaded from: classes.dex */
public interface ICustomUIComponent {
    Dialog createJdDialogNewStyle(Context context, JDGetWayQueueTools.JdDialogParam jdDialogParam, View.OnClickListener onClickListener, View.OnClickListener onClickListener2, DialogInterface.OnCancelListener onCancelListener);

    Dialog createJdDialogWithStyleTimer(Context context, String str, String str2, int i2, View.OnClickListener onClickListener, DialogInterface.OnCancelListener onCancelListener);

    Dialog createJdDialogWithStyleTimer(Context context, String str, String str2, String str3, int i2, String str4, View.OnClickListener onClickListener, View.OnClickListener onClickListener2, DialogInterface.OnCancelListener onCancelListener);

    View createProgressBar();

    void releaseResource(View view);

    void startTimeCountNew(Dialog dialog);

    void updateCountDown(Dialog dialog, int i2);

    @Deprecated
    void updateTick(Dialog dialog, long j2);
}
