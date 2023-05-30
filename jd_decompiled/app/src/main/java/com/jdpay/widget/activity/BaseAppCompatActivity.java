package com.jdpay.widget.activity;

import androidx.appcompat.app.AppCompatActivity;
import com.jdpay.widget.dialog.DialogManager;

/* loaded from: classes18.dex */
public class BaseAppCompatActivity extends AppCompatActivity implements DialogManager.Manageable {
    private DialogManager dialogManager;

    @Override // com.jdpay.widget.dialog.DialogManager.Manageable
    public DialogManager getDialogManager() {
        if (this.dialogManager == null) {
            this.dialogManager = new DialogManager();
        }
        return this.dialogManager;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        DialogManager dialogManager = this.dialogManager;
        if (dialogManager != null) {
            dialogManager.dismissAndRemoveAll();
        }
        super.onDestroy();
    }
}
