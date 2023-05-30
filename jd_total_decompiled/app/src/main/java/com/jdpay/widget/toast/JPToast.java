package com.jdpay.widget.toast;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationManagerCompat;
import com.jdpay.lib.util.JDPayLog;
import com.jdpay.system.SystemInfo;
import com.jdpay.widget.dialog.ToastDialog;
import java.lang.reflect.Field;

/* loaded from: classes18.dex */
public class JPToast extends Toast {
    private ToastDialog dialog;
    private Handler handler;
    private final NotificationManagerCompat notification;
    private Toast original;
    private final Runnable showInMainThread;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes18.dex */
    public static class ToastHandler extends Handler {
        private Handler mProxy;

        public ToastHandler(Handler handler) {
            this.mProxy = handler;
        }

        @Override // android.os.Handler
        public void dispatchMessage(Message message) {
            try {
                Handler handler = this.mProxy;
                if (handler != null) {
                    handler.dispatchMessage(message);
                }
            } catch (Throwable th) {
                JDPayLog.e(th);
            }
        }
    }

    private JPToast(Context context) {
        super(context);
        this.showInMainThread = new Runnable() { // from class: com.jdpay.widget.toast.JPToast.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (!JPToast.this.notification.areNotificationsEnabled()) {
                        if (JPToast.this.dialog != null) {
                            JPToast.this.dialog.show();
                        }
                    } else if (JPToast.this.original != null) {
                        JPToast.this.original.show();
                    }
                } catch (Throwable th) {
                    JDPayLog.e(th);
                }
            }
        };
        this.notification = NotificationManagerCompat.from(context);
    }

    private static JPToast build(@NonNull Context context, @NonNull CharSequence charSequence, int i2) {
        JPToast jPToast = new JPToast(context);
        if (!jPToast.notification.areNotificationsEnabled()) {
            jPToast.buildDialogMode(context, charSequence);
        } else {
            jPToast.buildOriginalMode(context, charSequence, i2);
        }
        return jPToast;
    }

    private void buildDialogMode(@NonNull Context context, @NonNull CharSequence charSequence) {
        this.dialog = ToastDialog.makeText(context, charSequence);
    }

    @SuppressLint({"ShowToast"})
    private void buildOriginalMode(@NonNull Context context, @NonNull CharSequence charSequence, int i2) {
        Toast makeText = Toast.makeText(context, charSequence, i2);
        this.original = makeText;
        fixToastBadTokenException(makeText);
    }

    private void fixToastBadTokenException(Toast toast) {
        Object fieldValue;
        int i2;
        int i3;
        int i4;
        if (toast != null) {
            boolean z = true;
            boolean z2 = SystemInfo.is360() && Build.VERSION.SDK_INT == 27;
            boolean z3 = SystemInfo.isEmui() && ((i4 = Build.VERSION.SDK_INT) == 27 || i4 == 28);
            boolean z4 = SystemInfo.is1Jia() && ((i3 = Build.VERSION.SDK_INT) == 27 || i3 == 28);
            if (!SystemInfo.isVivo() || ((i2 = Build.VERSION.SDK_INT) != 27 && i2 != 28)) {
                z = false;
            }
            if ((Build.VERSION.SDK_INT < 26 || z2 || z3 || z4 || z) && (fieldValue = getFieldValue(toast, "mTN")) != null) {
                try {
                    Handler handler = (Handler) getFieldValue(fieldValue, "mHandler");
                    if (handler == null) {
                        return;
                    }
                    try {
                        setFieldValue(fieldValue, "mHandler", new ToastHandler(handler));
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
            }
        }
    }

    private Field getDeclaredField(Object obj, String str) {
        for (Class<?> cls = obj.getClass(); cls != Object.class; cls = cls.getSuperclass()) {
            try {
                return cls.getDeclaredField(str);
            } catch (Exception unused) {
            }
        }
        throw new RuntimeException("getDeclaredField exception, object = " + obj + ", fieldName = " + str);
    }

    public static JPToast makeText(@NonNull Context context, String str, int i2) {
        return build(context, str, i2);
    }

    public Object getFieldValue(Object obj, String str) {
        try {
            Field declaredField = getDeclaredField(obj, str);
            declaredField.setAccessible(true);
            return declaredField.get(obj);
        } catch (Exception unused) {
            return null;
        }
    }

    public void setFieldValue(Object obj, String str, Object obj2) throws Exception {
        try {
            Field declaredField = getDeclaredField(obj, str);
            declaredField.setAccessible(true);
            declaredField.set(obj, obj2);
        } catch (Exception e2) {
            throw new Exception("setFieldValue exception, object = " + obj + ", fieldName = " + str, e2);
        }
    }

    @Override // android.widget.Toast
    public void show() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            this.showInMainThread.run();
            return;
        }
        if (this.handler == null) {
            this.handler = new Handler(Looper.getMainLooper());
        }
        this.handler.post(this.showInMainThread);
    }

    public static JPToast makeText(@NonNull Context context, int i2, int i3) {
        return build(context, context.getString(i2), i3);
    }
}
