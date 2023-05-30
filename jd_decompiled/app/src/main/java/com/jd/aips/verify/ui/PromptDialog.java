package com.jd.aips.verify.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import com.jd.aips.verify.identity.R;

/* loaded from: classes12.dex */
public class PromptDialog extends Dialog {
    @LayoutRes
    protected final int contentLayoutId;
    protected final PromptDelegate delegate;
    protected final String message;

    /* loaded from: classes12.dex */
    public interface PromptDelegate {
        void onConfirm();
    }

    public PromptDialog(@NonNull Context context, @NonNull String str, @NonNull PromptDelegate promptDelegate) {
        this(context, R.layout.aips_dialog_prompt, str, promptDelegate);
    }

    void doConfirm() {
        this.delegate.onConfirm();
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
        ((TextView) findViewById(R.id.aips_prompt_message)).setText(this.message);
        findViewById(R.id.aips_prompt_confirm).setOnClickListener(new View.OnClickListener() { // from class: com.jd.aips.verify.ui.PromptDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PromptDialog.this.doConfirm();
            }
        });
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

    public PromptDialog(@NonNull Context context, @LayoutRes int i2, @NonNull String str, @NonNull PromptDelegate promptDelegate) {
        super(context, R.style.AipsTheme_Dialog);
        this.contentLayoutId = i2;
        this.message = str;
        this.delegate = promptDelegate;
    }
}
