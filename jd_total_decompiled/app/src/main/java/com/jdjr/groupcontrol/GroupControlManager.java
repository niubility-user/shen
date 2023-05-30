package com.jdjr.groupcontrol;

import android.app.Activity;
import android.content.Context;
import com.wangyin.platform.ACMUtil;

/* loaded from: classes18.dex */
public class GroupControlManager {
    private static GroupControlManager instance;
    private static final Object lock = new Object();
    private ACMUtil mACMUtil;

    public GroupControlManager(Context context) {
        this.mACMUtil = ACMUtil.newInstance(context.getApplicationContext());
    }

    public static GroupControlManager newInstance(Context context) {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new GroupControlManager(context);
                }
            }
        }
        return instance;
    }

    public boolean onInitializeAccessibilityNodeInfo(int i2, Object obj, Object obj2) {
        byte[] NativeonInitializeAccessibilityNodeInfo = this.mACMUtil.NativeonInitializeAccessibilityNodeInfo(i2, obj, obj2);
        byte[] bArr = new byte[5];
        System.arraycopy(NativeonInitializeAccessibilityNodeInfo, 0, bArr, 0, 5);
        return new String(bArr).equals("00000");
    }

    public boolean performAccessibilityAction(int i2, Object obj, int i3, Object obj2) {
        return this.mACMUtil.NativePerformAccessibilityAction(i2, obj, i3, obj2);
    }

    public boolean setCheckAccessibility(Activity activity, boolean z, String str, String str2, CheckAccessibilityCallback checkAccessibilityCallback) {
        if (z && (str == null || str2 == null)) {
            return false;
        }
        byte[] NativeSetCheckAccessibility = this.mACMUtil.NativeSetCheckAccessibility(activity, z, str, str2, checkAccessibilityCallback);
        byte[] bArr = new byte[5];
        System.arraycopy(NativeSetCheckAccessibility, 0, bArr, 0, 5);
        return new String(bArr).equals("00000");
    }
}
