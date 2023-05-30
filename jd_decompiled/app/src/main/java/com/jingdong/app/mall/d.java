package com.jingdong.app.mall;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Process;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.common.utils.ABTestUtils;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.Constants;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.widget.ToastUtils;

/* loaded from: classes19.dex */
public class d extends com.jingdong.sdk.jdcrashreport.recover.b {

    /* renamed from: h */
    private Context f8340h;

    /* renamed from: i */
    private String f8341i;

    /* renamed from: j */
    private String f8342j;

    /* loaded from: classes19.dex */
    public class a implements View.OnClickListener {

        /* renamed from: g */
        final /* synthetic */ EditText f8343g;

        /* renamed from: h */
        final /* synthetic */ EditText f8344h;

        /* renamed from: i */
        final /* synthetic */ JDDialog f8345i;

        a(EditText editText, EditText editText2, JDDialog jDDialog) {
            d.this = r1;
            this.f8343g = editText;
            this.f8344h = editText2;
            this.f8345i = jDDialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            String str;
            String obj = this.f8343g.getText().toString();
            String obj2 = this.f8344h.getText().toString();
            d dVar = d.this;
            StringBuilder sb = new StringBuilder();
            if (TextUtils.isEmpty(obj)) {
                str = "";
            } else {
                str = "phone:" + obj + ";";
            }
            sb.append(str);
            sb.append("description:");
            sb.append(obj2);
            dVar.f8342j = sb.toString();
            this.f8345i.dismiss();
            d.this.p(-1);
        }
    }

    /* loaded from: classes19.dex */
    public class b implements View.OnClickListener {

        /* renamed from: g */
        final /* synthetic */ EditText f8347g;

        /* renamed from: h */
        final /* synthetic */ EditText f8348h;

        /* renamed from: i */
        final /* synthetic */ JDDialog f8349i;

        b(EditText editText, EditText editText2, JDDialog jDDialog) {
            d.this = r1;
            this.f8347g = editText;
            this.f8348h = editText2;
            this.f8349i = jDDialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            String str;
            String obj = this.f8347g.getText().toString();
            String obj2 = this.f8348h.getText().toString();
            d dVar = d.this;
            StringBuilder sb = new StringBuilder();
            if (TextUtils.isEmpty(obj)) {
                str = "";
            } else {
                str = "phone:" + obj + ";";
            }
            sb.append(str);
            sb.append("description:");
            sb.append(obj2);
            dVar.f8342j = sb.toString();
            this.f8349i.dismiss();
            d.this.p(0);
        }
    }

    /* loaded from: classes19.dex */
    public class c implements DialogInterface.OnKeyListener {
        c(d dVar) {
        }

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i2, KeyEvent keyEvent) {
            return i2 == 4 || i2 == 84;
        }
    }

    private void o() {
        a();
        Process.killProcess(Process.myPid());
        System.exit(0);
    }

    public void p(int i2) {
        String str = i2 != -1 ? i2 != 0 ? "" : "AppCrash_PopupSummit" : "AppCrash_PopupClose";
        try {
            Context context = this.f8340h;
            JDMtaUtils.sendCommonData(context, str, "", "", context, "", "", "", "", "");
        } catch (Throwable unused) {
        }
        l(this.f8342j, JDMobileConfig.getInstance().getConfig(ABTestUtils.KEY_BASE_ARCH_CONFIG_NAMESPACE, ABTestUtils.KEY_CONFIG_CRASH_SETTINGS, ABTestUtils.KEY_CONFIG_CRASH_RECOVER_STACK_ENABLE, "1").equals("1") ? com.jingdong.sdk.jdcrashreport.recover.a.RECOVER_STACK : com.jingdong.sdk.jdcrashreport.recover.a.RESTART);
        a();
    }

    private void q() {
        try {
            int intFromPreference = CommonBase.getIntFromPreference(Constants.SCREEN_SLEEP_SETTING_TIME, 0);
            if (intFromPreference <= 0 || !Settings.System.putInt(this.f8340h.getContentResolver(), "screen_off_timeout", intFromPreference)) {
                return;
            }
            CommonBase.putIntToPreference(Constants.SCREEN_SLEEP_SETTING_TIME, 0);
        } catch (Throwable th) {
            if (Log.D) {
                th.printStackTrace();
            }
        }
    }

    private void r() {
        View inflate = LayoutInflater.from(this.f8340h).inflate(R.layout.crash_dialog_feedback, (ViewGroup) null);
        JDDialogFactory jDDialogFactory = JDDialogFactory.getInstance();
        Context context = this.f8340h;
        JDDialog createJdDialogWithStyle10 = jDDialogFactory.createJdDialogWithStyle10(context, context.getString(R.string.app_error_title), "", inflate, this.f8340h.getString(R.string.app_error_close), this.f8340h.getString(R.string.app_error_submit));
        EditText editText = (EditText) createJdDialogWithStyle10.findViewById(R.id.feedback_phone_input_edit);
        EditText editText2 = (EditText) createJdDialogWithStyle10.findViewById(R.id.feedback_des_input_edit);
        createJdDialogWithStyle10.setOnLeftButtonClickListener(new a(editText, editText2, createJdDialogWithStyle10));
        createJdDialogWithStyle10.setOnRightButtonClickListener(new b(editText, editText2, createJdDialogWithStyle10));
        createJdDialogWithStyle10.setOnKeyListener(new c(this));
        createJdDialogWithStyle10.setCancelable(false);
        createJdDialogWithStyle10.show();
        try {
            Context context2 = this.f8340h;
            JDMtaUtils.sendExposureData(context2, context2, "", "", "AppCrash_PopupExpo", "", "", "", "");
        } catch (Throwable unused) {
        }
    }

    @Override // com.jingdong.sdk.jdcrashreport.recover.b
    @SuppressLint({"InflateParams"})
    public View c(Context context) {
        k(1024, 1024);
        j(1);
        this.f8340h = context;
        this.f8341i = context.getString(R.string.app_error_msg);
        q();
        return LayoutInflater.from(context).inflate(R.layout.error_activity, (ViewGroup) null);
    }

    @Override // com.jingdong.sdk.jdcrashreport.recover.b
    public boolean f(int i2, KeyEvent keyEvent) {
        o();
        return false;
    }

    @Override // com.jingdong.sdk.jdcrashreport.recover.b
    public void h() {
        super.h();
        ToastUtils.longToast(this.f8340h, this.f8341i);
        r();
    }
}
