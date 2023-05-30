package com.jingdong.sdk.jdupgrade;

import android.content.Context;
import android.view.View;
import com.jingdong.sdk.jdupgrade.a.b;
import com.jingdong.sdk.jdupgrade.inner.ui.c;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public abstract class RemindView implements b {
    private c remindRef;

    public final void cancel(boolean z) {
        c cVar = this.remindRef;
        if (cVar != null) {
            cVar.a(z);
        }
    }

    public final void confirm() {
        if (this.remindRef != null) {
            if (!isInstallView()) {
                this.remindRef.b(true);
            }
            this.remindRef.b();
        }
    }

    public final String getCancelButtonText() {
        try {
            JSONObject remindInfo = getRemindInfo();
            return remindInfo == null ? "" : remindInfo.optString("cancelButton");
        } catch (Throwable unused) {
            return "";
        }
    }

    public final String getConfirmButtonText() {
        try {
            JSONObject remindInfo = getRemindInfo();
            return remindInfo == null ? "" : remindInfo.optString("confirmButton");
        } catch (Throwable unused) {
            return "";
        }
    }

    public final String getCustomInfo() {
        c cVar = this.remindRef;
        return cVar != null ? cVar.c() : "";
    }

    public final JSONObject getPackageInfo() {
        c cVar = this.remindRef;
        if (cVar == null) {
            return null;
        }
        return cVar.e();
    }

    public final String getRemindContent() {
        try {
            JSONObject remindInfo = getRemindInfo();
            return remindInfo == null ? "" : remindInfo.optString("content");
        } catch (Throwable unused) {
            return "";
        }
    }

    public final JSONObject getRemindInfo() {
        c cVar = this.remindRef;
        if (cVar == null) {
            return null;
        }
        return cVar.f();
    }

    public final String getRemindSubTitle() {
        try {
            JSONObject remindInfo = getRemindInfo();
            return remindInfo == null ? "" : remindInfo.optString("subtitle");
        } catch (Throwable unused) {
            return "";
        }
    }

    public final String getRemindTitle() {
        try {
            JSONObject remindInfo = getRemindInfo();
            return remindInfo == null ? "" : remindInfo.optString("title");
        } catch (Throwable unused) {
            return "";
        }
    }

    public final RemindType getRemindType() {
        c cVar = this.remindRef;
        if (cVar == null) {
            return null;
        }
        return cVar.g();
    }

    public final boolean hideRejectCheckbox() {
        c cVar = this.remindRef;
        if (cVar == null) {
            return false;
        }
        return cVar.j();
    }

    public final boolean isForceUpgrade() {
        c cVar = this.remindRef;
        if (cVar == null) {
            return false;
        }
        return cVar.k();
    }

    public boolean isInstallView() {
        return RemindType.INSTALL_REMIND == getRemindType();
    }

    @Override // com.jingdong.sdk.jdupgrade.a.b
    public final void onAttach() {
        if (this.remindRef == null) {
            this.remindRef = c.f15146j;
        }
    }

    @Override // com.jingdong.sdk.jdupgrade.a.b
    public abstract View onCreateView(Context context);

    @Override // com.jingdong.sdk.jdupgrade.a.b
    public final void onDetach() {
    }

    @Override // com.jingdong.sdk.jdupgrade.a.b
    public void onResume() {
    }
}
