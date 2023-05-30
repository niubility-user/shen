package com.jingdong.app.mall.update.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jingdong.app.mall.R;
import com.jingdong.common.appupdate.UpdateSharedPreferenceUtil;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.common.utils.StringUtil;
import com.jingdong.jdsdk.constant.Constants;
import com.jingdong.jdsdk.utils.DPIUtil;

/* loaded from: classes4.dex */
public class a {
    private static a a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.app.mall.update.view.a$a  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    public class ViewOnClickListenerC0379a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ CheckBox f11718g;

        ViewOnClickListenerC0379a(a aVar, CheckBox checkBox) {
            this.f11718g = checkBox;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f11718g.performClick();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b implements CompoundButton.OnCheckedChangeListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ boolean f11719g;

        b(a aVar, boolean z) {
            this.f11719g = z;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (this.f11719g) {
                UpdateSharedPreferenceUtil.putBoolean("app_install_dialog_delete_status", z);
                return;
            }
            UpdateSharedPreferenceUtil.putBoolean(Constants.UPGRADE_WIFI_AUTO_KEY, z, 1);
            UpdateSharedPreferenceUtil.putBoolean(Constants.UPGRADE_WIFI_SETTED_KEY, true, 1);
        }
    }

    private LinearLayout e(Context context, boolean z, String str) {
        int dip2px = DPIUtil.dip2px(10.0f);
        int dip2px2 = DPIUtil.dip2px(6.0f);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        CheckBox checkBox = new CheckBox(context);
        checkBox.setButtonDrawable(new ColorDrawable(0));
        checkBox.setBackgroundResource(R.drawable.bt);
        checkBox.setPadding(dip2px2, dip2px, dip2px2, dip2px);
        linearLayout.addView(checkBox);
        ViewGroup.LayoutParams layoutParams = checkBox.getLayoutParams();
        layoutParams.width = DPIUtil.dip2px(24.0f);
        layoutParams.height = DPIUtil.dip2px(28.0f);
        checkBox.setLayoutParams(layoutParams);
        TextView textView = new TextView(context);
        textView.setTextSize(13.0f);
        textView.setText(str);
        textView.setTextColor(context.getResources().getColor(R.color.nk));
        textView.setPadding(dip2px, 0, 0, 0);
        linearLayout.addView(textView);
        linearLayout.setOnClickListener(new ViewOnClickListenerC0379a(this, checkBox));
        if (z) {
            checkBox.setChecked(UpdateSharedPreferenceUtil.getBoolean("app_install_dialog_delete_status", true));
            UpdateSharedPreferenceUtil.putBoolean("app_install_dialog_delete_status", checkBox.isChecked());
        } else {
            checkBox.setChecked(UpdateSharedPreferenceUtil.getBoolean(Constants.UPGRADE_WIFI_AUTO_KEY, true, 1));
        }
        checkBox.setOnCheckedChangeListener(new b(this, z));
        return linearLayout;
    }

    public static synchronized a f() {
        a aVar;
        synchronized (a.class) {
            if (a == null) {
                a = new a();
            }
            aVar = a;
        }
        return aVar;
    }

    public JDDialog a(Context context, String str, String str2, String str3, String str4, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        if (context != null) {
            com.jingdong.app.mall.update.view.b bVar = new com.jingdong.app.mall.update.view.b(context);
            bVar.b(str, str2, str3, str4, onClickListener, onClickListener2);
            return bVar;
        }
        throw new IllegalArgumentException("the param context can not be null in this dialog style");
    }

    public JDDialog b(Context context, String str, String str2, String str3, String str4) throws IllegalArgumentException {
        if (context != null) {
            JDDialog createJdDialogWithStyle9 = JDDialogFactory.getInstance().createJdDialogWithStyle9(context, str, str2, e(context, true, context.getResources().getString(R.string.upgrade_delete_apkfile)), 3, str4, str3);
            createJdDialogWithStyle9.messageView.setMovementMethod(new ScrollingMovementMethod());
            createJdDialogWithStyle9.messageView.setTextSize(14.0f);
            return createJdDialogWithStyle9;
        }
        throw new IllegalArgumentException("the param context can not be null in this dialog style");
    }

    public JDDialog c(Context context, String str, int i2, String str2, String str3, String str4, String str5) throws IllegalArgumentException {
        JDDialog createJdDialogWithStyle9;
        if (context != null) {
            if (i2 == 303) {
                createJdDialogWithStyle9 = JDDialogFactory.getInstance().createJdDialogWithStyle9(context, str, str2, null, str5, str4);
            } else if (!UpdateSharedPreferenceUtil.getBoolean(Constants.UPGRADE_WIFI_AUTO_KEY, true, 1) && !StringUtil.isEmpty(str3)) {
                createJdDialogWithStyle9 = JDDialogFactory.getInstance().createJdDialogWithStyle9(context, str, str2, e(context, false, str3), 3, str5, str4);
            } else {
                createJdDialogWithStyle9 = JDDialogFactory.getInstance().createJdDialogWithStyle9(context, str, str2, null, str5, str4);
            }
            createJdDialogWithStyle9.messageView.setMovementMethod(new ScrollingMovementMethod());
            createJdDialogWithStyle9.messageView.setTextSize(14.0f);
            return createJdDialogWithStyle9;
        }
        throw new IllegalArgumentException("the param context can not be null in this dialog style");
    }

    public c d(Context context, com.jingdong.app.mall.update.b bVar, String str, String str2) throws IllegalArgumentException {
        if (context != null) {
            c cVar = new c(context, str, str2);
            cVar.e(bVar);
            return cVar;
        }
        throw new IllegalArgumentException("the param context can not be null in this dialog style");
    }
}
