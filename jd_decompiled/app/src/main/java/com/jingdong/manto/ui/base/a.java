package com.jingdong.manto.ui.base;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import com.jingdong.manto.utils.MantoLog;

/* loaded from: classes16.dex */
public class a extends PopupWindow {
    private Context a;
    private View.OnTouchListener b;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.jingdong.manto.ui.base.a$a  reason: collision with other inner class name */
    /* loaded from: classes16.dex */
    public class C0686a extends FrameLayout {
        C0686a(Context context) {
            super(context);
        }

        @Override // android.view.ViewGroup, android.view.View
        public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
            KeyEvent.DispatcherState keyDispatcherState;
            if (keyEvent.getKeyCode() == 4 && getKeyDispatcherState() != null) {
                if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                    KeyEvent.DispatcherState keyDispatcherState2 = getKeyDispatcherState();
                    if (keyDispatcherState2 == null) {
                        return true;
                    }
                    keyDispatcherState2.startTracking(keyEvent, this);
                    return true;
                } else if (keyEvent.getAction() != 1 || (keyDispatcherState = getKeyDispatcherState()) == null || !keyDispatcherState.isTracking(keyEvent) || keyEvent.isCanceled()) {
                    return super.dispatchKeyEvent(keyEvent);
                } else {
                    a.this.dismiss();
                    return true;
                }
            }
            return super.dispatchKeyEvent(keyEvent);
        }

        @Override // android.view.ViewGroup, android.view.View
        public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
            if (a.this.b == null || !a.this.b.onTouch(this, motionEvent)) {
                return super.dispatchTouchEvent(motionEvent);
            }
            return true;
        }

        @Override // android.view.ViewGroup, android.view.View
        protected final int[] onCreateDrawableState(int i2) {
            return super.onCreateDrawableState(i2);
        }

        @Override // android.view.View
        public final boolean onTouchEvent(MotionEvent motionEvent) {
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if ((motionEvent.getAction() != 0 || (x >= 0 && x < getWidth() && y >= 0 && y < getHeight())) && motionEvent.getAction() != 4) {
                return super.onTouchEvent(motionEvent);
            }
            a.this.dismiss();
            return true;
        }

        @Override // android.view.View, android.view.accessibility.AccessibilityEventSource
        public final void sendAccessibilityEvent(int i2) {
            if (getChildCount() == 1) {
                getChildAt(0).sendAccessibilityEvent(i2);
            } else {
                super.sendAccessibilityEvent(i2);
            }
            super.sendAccessibilityEvent(i2);
        }
    }

    public a(Context context) {
        super(context);
        this.a = null;
        this.a = context;
        super.setBackgroundDrawable(null);
        setContentView(new C0686a(this.a));
    }

    @Override // android.widget.PopupWindow
    public void dismiss() {
        try {
            super.dismiss();
        } catch (Exception e2) {
            MantoLog.v("MantoBasePopWindow", "dismiss exception, e = " + e2.getMessage());
        }
    }

    @Override // android.widget.PopupWindow
    public Drawable getBackground() {
        View contentView = getContentView();
        if (contentView == null || !(contentView instanceof C0686a)) {
            return null;
        }
        return contentView.getBackground();
    }

    @Override // android.widget.PopupWindow
    public void setBackgroundDrawable(Drawable drawable) {
        View contentView = getContentView();
        if (contentView != null) {
            Context context = contentView.getContext();
            if (contentView instanceof C0686a) {
                contentView.setBackgroundDrawable(drawable);
                return;
            }
            ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
            int i2 = (layoutParams == null || layoutParams.height != -2) ? -1 : -2;
            C0686a c0686a = new C0686a(context);
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, i2);
            c0686a.setBackgroundDrawable(drawable);
            c0686a.addView(contentView, layoutParams2);
            super.setContentView(c0686a);
        }
    }

    @Override // android.widget.PopupWindow
    public void setContentView(View view) {
        View contentView = getContentView();
        if (contentView == null) {
            super.setContentView(view);
        } else if (contentView instanceof C0686a) {
            C0686a c0686a = (C0686a) contentView;
            c0686a.removeAllViews();
            if (view == null) {
                super.setContentView(c0686a);
                return;
            }
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            c0686a.addView(view, new FrameLayout.LayoutParams(-1, (layoutParams == null || layoutParams.height != -2) ? -1 : -2));
            super.setContentView(c0686a);
            return;
        }
        super.setContentView(view);
    }

    @Override // android.widget.PopupWindow
    public void setTouchInterceptor(View.OnTouchListener onTouchListener) {
        this.b = onTouchListener;
    }
}
