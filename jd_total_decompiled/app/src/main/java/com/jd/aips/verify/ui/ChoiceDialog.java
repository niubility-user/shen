package com.jd.aips.verify.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import com.jd.aips.verify.identity.R;

/* loaded from: classes12.dex */
public class ChoiceDialog extends Dialog {
    @LayoutRes
    protected final int contentLayoutId;
    protected final ChoiceDelegate delegate;
    protected final String message;
    protected final String positive;

    /* loaded from: classes12.dex */
    public interface ChoiceDelegate {
        void onNegative();

        void onPositive();
    }

    public ChoiceDialog(@NonNull Context context, @NonNull String str, @NonNull String str2, @NonNull ChoiceDelegate choiceDelegate) {
        this(context, R.layout.aips_dialog_choice, str, str2, choiceDelegate);
    }

    void doCancel() {
        this.delegate.onNegative();
        dismiss();
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(this.contentLayoutId);
        Window window = getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 17;
        attributes.width = -1;
        attributes.height = -2;
        window.setAttributes(attributes);
        ((TextView) findViewById(R.id.aips_choice_message)).setText(this.message);
        TextView textView = (TextView) findViewById(R.id.aips_choice_positive);
        textView.setText(this.positive);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.jd.aips.verify.ui.ChoiceDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ChoiceDialog.this.delegate.onPositive();
                ChoiceDialog.this.dismiss();
            }
        });
        findViewById(R.id.aips_choice_negative).setOnClickListener(new View.OnClickListener() { // from class: com.jd.aips.verify.ui.ChoiceDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ChoiceDialog.this.doCancel();
            }
        });
        setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.jd.aips.verify.ui.ChoiceDialog.3
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                ChoiceDialog.this.doCancel();
            }
        });
        setCanceledOnTouchOutside(false);
    }

    public ChoiceDialog(@NonNull Context context, @LayoutRes int i2, @NonNull String str, @NonNull String str2, @NonNull ChoiceDelegate choiceDelegate) {
        super(context, R.style.AipsTheme_Dialog);
        this.contentLayoutId = i2;
        this.message = str;
        this.positive = str2;
        this.delegate = choiceDelegate;
    }
}
