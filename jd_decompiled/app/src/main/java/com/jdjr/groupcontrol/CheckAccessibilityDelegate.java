package com.jdjr.groupcontrol;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;

/* loaded from: classes18.dex */
public final class CheckAccessibilityDelegate extends View.AccessibilityDelegate {
    private int mCode;
    private GroupControlManager mGroupControlManager;

    public CheckAccessibilityDelegate(Context context, int i2) {
        this.mCode = i2;
        this.mGroupControlManager = GroupControlManager.newInstance(context);
    }

    @Override // android.view.View.AccessibilityDelegate
    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
        this.mGroupControlManager.onInitializeAccessibilityNodeInfo(this.mCode, view, accessibilityNodeInfo);
    }

    @Override // android.view.View.AccessibilityDelegate
    public boolean performAccessibilityAction(View view, int i2, Bundle bundle) {
        this.mGroupControlManager.performAccessibilityAction(this.mCode, view, i2, bundle);
        return false;
    }
}
