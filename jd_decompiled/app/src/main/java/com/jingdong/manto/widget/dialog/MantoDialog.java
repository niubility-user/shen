package com.jingdong.manto.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.jingdong.manto.R;
import com.jingdong.manto.k.a;
import com.jingdong.manto.utils.MantoThreadUtils;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;

/* loaded from: classes16.dex */
public class MantoDialog extends Dialog implements a.b {
    private View btnDivider;
    private Reference<DialogInterface.OnCancelListener> cancelListenerRef;
    private View contentView;
    private Reference<DialogInterface.OnDismissListener> dismissListenerRef;
    private EditText editText;
    private boolean editable;
    private TextView group_btn_1;
    private TextView group_btn_2;
    private View mainView;
    private TextView msgView;
    private TextView titleView;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class a implements View.OnClickListener {
        final /* synthetic */ DialogInterface.OnClickListener a;

        a(DialogInterface.OnClickListener onClickListener) {
            this.a = onClickListener;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MantoDialog.this.dismiss();
            DialogInterface.OnClickListener onClickListener = this.a;
            if (!(onClickListener instanceof d)) {
                if (onClickListener != null) {
                    onClickListener.onClick(MantoDialog.this, -1);
                    return;
                }
                return;
            }
            d dVar = (d) onClickListener;
            if (dVar != null) {
                if (!MantoDialog.this.editable) {
                    dVar.onClick(MantoDialog.this, -1);
                    return;
                }
                MantoDialog mantoDialog = MantoDialog.this;
                dVar.a(mantoDialog, -1, mantoDialog.editText != null ? MantoDialog.this.editText.getText().toString() : "");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class b implements View.OnClickListener {
        final /* synthetic */ DialogInterface.OnClickListener a;

        b(DialogInterface.OnClickListener onClickListener) {
            this.a = onClickListener;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MantoDialog.this.dismiss();
            DialogInterface.OnClickListener onClickListener = this.a;
            if (onClickListener != null) {
                onClickListener.onClick(MantoDialog.this, -2);
            }
        }
    }

    /* loaded from: classes16.dex */
    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (MantoDialog.this.editText != null) {
                MantoDialog.this.editText.setFocusable(true);
                MantoDialog.this.editText.setFocusableInTouchMode(true);
                MantoDialog.this.editText.requestFocus();
                MantoDialog.this.editText.setSelection(MantoDialog.this.editText.getText().length());
                InputMethodManager inputMethodManager = (InputMethodManager) MantoDialog.this.getContext().getSystemService("input_method");
                if (inputMethodManager != null) {
                    inputMethodManager.showSoftInput(MantoDialog.this.editText, 0);
                }
            }
        }
    }

    /* loaded from: classes16.dex */
    public interface d extends DialogInterface.OnClickListener {
        void a(DialogInterface dialogInterface, int i2, String str);

        @Override // android.content.DialogInterface.OnClickListener
        void onClick(DialogInterface dialogInterface, int i2);
    }

    public MantoDialog(@NonNull Context context) {
        super(context);
        TextView textView;
        int i2;
        requestWindowFeature(1);
        View inflate = LayoutInflater.from(context).inflate(R.layout.manto_dialog, (ViewGroup) null);
        this.contentView = inflate;
        this.editText = (EditText) inflate.findViewById(R.id.dialog_edit);
        this.mainView = this.contentView.findViewById(R.id.dialog_main);
        this.titleView = (TextView) this.contentView.findViewById(R.id.dialog_title);
        this.msgView = (TextView) this.contentView.findViewById(R.id.dialog_content);
        this.group_btn_1 = (TextView) this.contentView.findViewById(R.id.dialog_btn_group_btn1);
        this.group_btn_2 = (TextView) this.contentView.findViewById(R.id.dialog_btn_group_btn2);
        this.btnDivider = this.contentView.findViewById(R.id.manto_dialog_btn_divider);
        setContentView(this.contentView);
        setCanceledOnTouchOutside(false);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        com.jingdong.manto.k.a.b().a(this);
        if (com.jingdong.manto.b.g() != null) {
            if (TextUtils.equals("1", com.jingdong.manto.b.g().b("dialog_content_gravity_left"))) {
                textView = this.msgView;
                i2 = 19;
            } else {
                textView = this.msgView;
                i2 = 17;
            }
            textView.setGravity(i2);
        }
        this.msgView.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    private void setDarkMode(int i2) {
        TextView textView;
        Resources resources;
        int i3;
        if (i2 == 0) {
            int color = getContext().getResources().getColor(R.color.manto_day_text_weight);
            this.mainView.setBackgroundResource(R.drawable.manto_dialog_bg);
            this.titleView.setTextColor(color);
            this.msgView.setTextColor(color);
            this.editText.setTextColor(color);
            this.editText.setBackgroundResource(R.drawable.dialog_edittext_bg);
            textView = this.group_btn_1;
            if (textView == null) {
                return;
            }
            resources = getContext().getResources();
            i3 = R.color.manto_open_main_color;
        } else {
            int color2 = getContext().getResources().getColor(R.color.manto_dark_text_weight);
            this.mainView.setBackgroundResource(R.drawable.manto_dialog_bg_dark);
            this.titleView.setTextColor(color2);
            this.msgView.setTextColor(color2);
            this.editText.setTextColor(color2);
            this.editText.setBackgroundResource(R.drawable.dialog_edittext_bg_dark);
            textView = this.group_btn_1;
            if (textView == null) {
                return;
            }
            resources = getContext().getResources();
            i3 = R.color.manto_dark_open_main_color;
        }
        textView.setTextColor(resources.getColor(i3));
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void cancel() {
        super.cancel();
        Reference<DialogInterface.OnCancelListener> reference = this.cancelListenerRef;
        DialogInterface.OnCancelListener onCancelListener = reference != null ? reference.get() : null;
        if (onCancelListener != null) {
            onCancelListener.onCancel(this);
        }
        com.jingdong.manto.k.a.b().b(this);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        Reference<DialogInterface.OnDismissListener> reference = this.dismissListenerRef;
        DialogInterface.OnDismissListener onDismissListener = reference != null ? reference.get() : null;
        if (onDismissListener != null) {
            onDismissListener.onDismiss(this);
        }
        com.jingdong.manto.k.a.b().b(this);
    }

    public View getContentView() {
        return this.contentView;
    }

    @Override // com.jingdong.manto.k.a.b
    public void onDeepModeChanged(int i2) {
        setDarkMode(i2);
    }

    public void setCancelTextColor(int i2) {
        TextView textView = this.group_btn_2;
        if (textView != null) {
            textView.setTextColor(i2);
        }
    }

    public void setConfirmTextColor(int i2) {
        TextView textView = this.group_btn_1;
        if (textView != null) {
            textView.setTextColor(i2);
        }
    }

    public void setEditText(String str, boolean z) {
        this.editable = z;
        EditText editText = this.editText;
        if (editText != null) {
            editText.setHint(str);
            this.editText.setVisibility(z ? 0 : 8);
            if (z) {
                this.msgView.setVisibility(8);
            }
        }
    }

    public void setMessage(String str) {
        EditText editText;
        if (this.editable && (editText = this.editText) != null) {
            editText.setText(str);
        }
        if (this.msgView == null || TextUtils.isEmpty(str)) {
            return;
        }
        this.msgView.setText(str);
    }

    public final void setNegativeBtn(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        if (this.group_btn_2 != null) {
            this.btnDivider.setVisibility(0);
            this.group_btn_2.setVisibility(0);
            this.group_btn_2.setText(charSequence);
            this.group_btn_2.setOnClickListener(new b(onClickListener));
        }
    }

    @Override // android.app.Dialog
    public void setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
        super.setOnCancelListener(onCancelListener);
        this.cancelListenerRef = new SoftReference(onCancelListener);
    }

    @Override // android.app.Dialog
    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        super.setOnDismissListener(onDismissListener);
        this.dismissListenerRef = new SoftReference(onDismissListener);
    }

    public final void setPositiveBtn(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        TextView textView = this.group_btn_1;
        if (textView != null) {
            textView.setVisibility(0);
            this.group_btn_1.setText(charSequence);
            this.group_btn_1.setOnClickListener(new a(onClickListener));
        }
    }

    public void setTitle(String str) {
        if (this.titleView == null || TextUtils.isEmpty(str)) {
            return;
        }
        this.titleView.setVisibility(0);
        this.titleView.setText(str);
    }

    @Override // android.app.Dialog
    public void show() {
        try {
            super.show();
            if (this.editable) {
                MantoThreadUtils.post(new c(), 500);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
