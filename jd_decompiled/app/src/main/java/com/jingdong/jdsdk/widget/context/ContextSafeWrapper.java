package com.jingdong.jdsdk.widget.context;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/* loaded from: classes14.dex */
public class ContextSafeWrapper extends ContextWrapper {
    private static final String TAG = "JDToast";

    /* loaded from: classes14.dex */
    private static final class ApplicationContextSafeWrapper extends ContextWrapper {
        public ApplicationContextSafeWrapper(Context context) {
            super(context);
        }

        @Override // android.content.ContextWrapper, android.content.Context
        public Object getSystemService(String str) {
            if ("window".equals(str)) {
                return new WindowManagerSafeWrapper((WindowManager) super.getSystemService(str));
            }
            return super.getSystemService(str);
        }
    }

    /* loaded from: classes14.dex */
    private static final class WindowManagerSafeWrapper implements WindowManager {
        private WindowManager base;

        public WindowManagerSafeWrapper(WindowManager windowManager) {
            this.base = windowManager;
        }

        private boolean isAddedException(Exception exc) {
            if (exc == null) {
                return false;
            }
            return (exc instanceof IllegalStateException) && exc.toString().contains("has already been added to the window manager");
        }

        private void tryDealAddedException(View view, ViewGroup.LayoutParams layoutParams) {
            removeViewImmediate(view);
            addView(view, layoutParams, false);
        }

        @Override // android.view.ViewManager
        public void addView(View view, ViewGroup.LayoutParams layoutParams) {
            addView(view, layoutParams, true);
        }

        @Override // android.view.WindowManager
        public Display getDefaultDisplay() {
            return this.base.getDefaultDisplay();
        }

        @Override // android.view.ViewManager
        public void removeView(View view) {
            try {
                this.base.removeView(view);
            } catch (Exception unused) {
            }
        }

        @Override // android.view.WindowManager
        public void removeViewImmediate(View view) {
            try {
                this.base.removeViewImmediate(view);
            } catch (Exception unused) {
            }
        }

        @Override // android.view.ViewManager
        public void updateViewLayout(View view, ViewGroup.LayoutParams layoutParams) {
            this.base.updateViewLayout(view, layoutParams);
        }

        private void addView(View view, ViewGroup.LayoutParams layoutParams, boolean z) {
            try {
                this.base.addView(view, layoutParams);
            } catch (Exception e2) {
                if (z && isAddedException(e2)) {
                    tryDealAddedException(view, layoutParams);
                }
            }
        }
    }

    public ContextSafeWrapper(Context context) {
        super(context);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Context getApplicationContext() {
        return new ApplicationContextSafeWrapper(super.getApplicationContext());
    }
}
