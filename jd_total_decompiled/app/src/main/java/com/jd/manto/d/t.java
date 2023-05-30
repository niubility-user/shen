package com.jd.manto.d;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.manto.sdk.api.IModalDialog;

/* loaded from: classes17.dex */
public class t implements IModalDialog {

    /* loaded from: classes17.dex */
    class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Dialog f6664g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ DialogInterface.OnClickListener f6665h;

        a(t tVar, Dialog dialog, DialogInterface.OnClickListener onClickListener) {
            this.f6664g = dialog;
            this.f6665h = onClickListener;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f6664g.dismiss();
            DialogInterface.OnClickListener onClickListener = this.f6665h;
            if (onClickListener != null) {
                onClickListener.onClick(this.f6664g, -2);
            }
        }
    }

    /* loaded from: classes17.dex */
    class b implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Dialog f6666g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ DialogInterface.OnClickListener f6667h;

        b(t tVar, Dialog dialog, DialogInterface.OnClickListener onClickListener) {
            this.f6666g = dialog;
            this.f6667h = onClickListener;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f6666g.dismiss();
            DialogInterface.OnClickListener onClickListener = this.f6667h;
            if (onClickListener != null) {
                onClickListener.onClick(this.f6666g, -1);
            }
        }
    }

    /* loaded from: classes17.dex */
    class c implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Dialog f6668g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ DialogInterface.OnClickListener f6669h;

        c(t tVar, Dialog dialog, DialogInterface.OnClickListener onClickListener) {
            this.f6668g = dialog;
            this.f6669h = onClickListener;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f6668g.dismiss();
            DialogInterface.OnClickListener onClickListener = this.f6669h;
            if (onClickListener != null) {
                onClickListener.onClick(this.f6668g, -1);
            }
        }
    }

    @Override // com.jingdong.manto.sdk.api.IModalDialog
    public void show(Activity activity, String str, String str2, String str3, String str4, String str5, boolean z, String str6, DialogInterface.OnClickListener onClickListener, DialogInterface.OnClickListener onClickListener2, DialogInterface.OnCancelListener onCancelListener) {
        JDDialog createJdDialogWithStyle1;
        JDDialog createJdDialogWithStyle2;
        boolean z2 = !TextUtils.isEmpty(str2);
        if (z) {
            if (z2) {
                createJdDialogWithStyle2 = JDDialogFactory.getInstance().createJdDialogWithStyle6(activity, str2, str, str4, str3);
            } else {
                createJdDialogWithStyle2 = JDDialogFactory.getInstance().createJdDialogWithStyle2(activity, str, str4, str3);
            }
            createJdDialogWithStyle2.setAutoDarkMode(true);
            createJdDialogWithStyle2.setOnLeftButtonClickListener(new a(this, createJdDialogWithStyle2, onClickListener2));
            createJdDialogWithStyle2.setOnRightButtonClickListener(new b(this, createJdDialogWithStyle2, onClickListener));
            createJdDialogWithStyle2.setOnCancelListener(onCancelListener);
            createJdDialogWithStyle2.show();
            Button button = createJdDialogWithStyle2.posButton;
            if (button != null) {
                button.setTextColor(Color.parseColor(str6));
            }
            Button button2 = createJdDialogWithStyle2.negButton;
            if (button2 != null) {
                button2.setTextColor(Color.parseColor(str5));
                return;
            }
            return;
        }
        if (z2) {
            createJdDialogWithStyle1 = JDDialogFactory.getInstance().createJdDialogWithStyle5(activity, str2, str, str3);
        } else {
            createJdDialogWithStyle1 = JDDialogFactory.getInstance().createJdDialogWithStyle1(activity, str, str3);
        }
        createJdDialogWithStyle1.setAutoDarkMode(true);
        createJdDialogWithStyle1.setOnLeftButtonClickListener(new c(this, createJdDialogWithStyle1, onClickListener));
        createJdDialogWithStyle1.setOnCancelListener(onCancelListener);
        createJdDialogWithStyle1.show();
        Button button3 = createJdDialogWithStyle1.posButton;
        if (button3 != null) {
            button3.setTextColor(Color.parseColor(str5));
        }
    }
}
