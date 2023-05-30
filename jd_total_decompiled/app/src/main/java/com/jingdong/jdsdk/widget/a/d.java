package com.jingdong.jdsdk.widget.a;

import android.app.Application;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;
import android.widget.Toast;
import java.lang.reflect.Field;

/* loaded from: classes14.dex */
public final class d extends com.jingdong.jdsdk.widget.a.a {

    /* loaded from: classes14.dex */
    static final class a extends Handler {
        private Handler a;

        a(Handler handler) {
            this.a = handler;
        }

        @Override // android.os.Handler
        public void dispatchMessage(Message message) {
            this.a.dispatchMessage(message);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            try {
                this.a.handleMessage(message);
            } catch (WindowManager.BadTokenException unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(Application application) {
        super(application);
        try {
            Field declaredField = Toast.class.getDeclaredField("mTN");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(this);
            Field declaredField2 = declaredField.getType().getDeclaredField("mHandler");
            declaredField2.setAccessible(true);
            declaredField2.set(obj, new a((Handler) declaredField2.get(obj)));
        } catch (Exception unused) {
        }
    }
}
