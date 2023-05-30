package com.jd.viewkit.common.view;

import android.content.Context;
import android.util.AttributeSet;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean dispatchTouchEvent(android.view.MotionEvent r12) {
        /*
            r11 = this;
            int r0 = r12.getAction()
            if (r0 == 0) goto L72
            r1 = 1
            r2 = 1058642330(0x3f19999a, float:0.6)
            if (r0 == r1) goto L3f
            r1 = 2
            if (r0 == r1) goto L13
            r1 = 3
            if (r0 == r1) goto L3f
            goto L85
        L13:
            long r3 = r12.getDownTime()
            long r5 = r12.getEventTime()
            int r7 = r12.getAction()
            float r0 = r12.getX()
            float r1 = r12.getX()
            float r8 = r11.lastX
            float r1 = r1 - r8
            float r1 = r1 * r2
            float r8 = r0 - r1
            float r9 = r12.getY()
            int r10 = r12.getMetaState()
            android.view.MotionEvent r12 = android.view.MotionEvent.obtain(r3, r5, r7, r8, r9, r10)
            boolean r12 = super.dispatchTouchEvent(r12)
            return r12
        L3f:
            com.jd.viewkit.common.view.JDViewKitRecycleView$TouchEventListener r0 = r11.touchEventListener
            if (r0 == 0) goto L46
            r0.touchEventUp()
        L46:
            long r3 = r12.getDownTime()
            long r5 = r12.getEventTime()
            int r7 = r12.getAction()
            float r0 = r12.getX()
            float r1 = r12.getX()
            float r8 = r11.lastX
            float r1 = r1 - r8
            float r1 = r1 * r2
            float r8 = r0 - r1
            float r9 = r12.getY()
            int r10 = r12.getMetaState()
            android.view.MotionEvent r12 = android.view.MotionEvent.obtain(r3, r5, r7, r8, r9, r10)
            boolean r12 = super.dispatchTouchEvent(r12)
            return r12
        L72:
            float r0 = r12.getX()
            r11.lastX = r0
            float r0 = r12.getY()
            r11.lastY = r0
            com.jd.viewkit.common.view.JDViewKitRecycleView$TouchEventListener r0 = r11.touchEventListener
            if (r0 == 0) goto L85
            r0.touchEventDown()
        L85:
            boolean r12 = super.dispatchTouchEvent(r12)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.viewkit.common.view.JDViewKitRecycleView.dispatchTouchEvent(android.view.MotionEvent):boolean");
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
