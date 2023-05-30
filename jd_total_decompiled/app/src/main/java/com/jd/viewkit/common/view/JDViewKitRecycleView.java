package com.jd.viewkit.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes18.dex */
public class JDViewKitRecycleView extends RecyclerView {
    float lastX;
    float lastY;
    private RecyclerView.OnScrollListener myOnScrollListener;
    private TouchEventListener touchEventListener;

    /* loaded from: classes18.dex */
    public interface TouchEventListener {
        void touchEventCancel();

        void touchEventDown();

        void touchEventUp();
    }

    public JDViewKitRecycleView(@NonNull Context context) {
        super(context);
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0010, code lost:
        if (r0 != 3) goto L21;
     */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action != 1) {
                if (action == 2) {
                    return super.dispatchTouchEvent(MotionEvent.obtain(motionEvent.getDownTime(), motionEvent.getEventTime(), motionEvent.getAction(), motionEvent.getX() - ((motionEvent.getX() - this.lastX) * 0.6f), motionEvent.getY(), motionEvent.getMetaState()));
                }
            }
            TouchEventListener touchEventListener = this.touchEventListener;
            if (touchEventListener != null) {
                touchEventListener.touchEventUp();
            }
            return super.dispatchTouchEvent(MotionEvent.obtain(motionEvent.getDownTime(), motionEvent.getEventTime(), motionEvent.getAction(), motionEvent.getX() - ((motionEvent.getX() - this.lastX) * 0.6f), motionEvent.getY(), motionEvent.getMetaState()));
        }
        this.lastX = motionEvent.getX();
        this.lastY = motionEvent.getY();
        TouchEventListener touchEventListener2 = this.touchEventListener;
        if (touchEventListener2 != null) {
            touchEventListener2.touchEventDown();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public RecyclerView.OnScrollListener getMyOnScrollListener() {
        return this.myOnScrollListener;
    }

    public TouchEventListener getTouchEventListener() {
        return this.touchEventListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void onScrollStateChanged(int i2) {
        super.onScrollStateChanged(i2);
        RecyclerView.OnScrollListener onScrollListener = this.myOnScrollListener;
        if (onScrollListener != null) {
            onScrollListener.onScrollStateChanged(this, i2);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void onScrolled(int i2, int i3) {
        super.onScrolled(i2, i3);
        RecyclerView.OnScrollListener onScrollListener = this.myOnScrollListener;
        if (onScrollListener != null) {
            onScrollListener.onScrolled(this, i2, i3);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public void scrollBy(int i2, int i3) {
        super.scrollBy(i2, i3);
    }

    public void setMyOnScrollListener(RecyclerView.OnScrollListener onScrollListener) {
        this.myOnScrollListener = onScrollListener;
    }

    public void setTouchEventListener(TouchEventListener touchEventListener) {
        this.touchEventListener = touchEventListener;
    }

    public JDViewKitRecycleView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public JDViewKitRecycleView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
