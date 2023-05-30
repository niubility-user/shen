package com.jd.lib.flexcube.layout.floor.banner.swipe.swipecard;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/* loaded from: classes14.dex */
public class CustomSwipeFlingAdapterView extends SwipeFlingAdapterView {
    private float I;
    private float J;
    private int K;
    private int[] L;
    private float[] M;
    private float[] N;

    public CustomSwipeFlingAdapterView(Context context) {
        this(context, null);
    }

    public void D(float[] fArr) {
        this.N = fArr;
    }

    public void E(int[] iArr) {
        this.L = iArr;
    }

    public void F(float[] fArr) {
        this.M = fArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.lib.flexcube.layout.floor.banner.swipe.swipecard.SwipeFlingAdapterView
    public void l(View view, int i2) {
        float[] fArr;
        int[] iArr = this.L;
        if (iArr != null) {
            int length = iArr.length;
            int i3 = this.f4345l;
            if (length >= i3 && (fArr = this.M) != null && fArr.length >= i3) {
                if (i2 <= -1 || i2 >= i3) {
                    return;
                }
                if (i2 > 3) {
                    i2 = 3;
                }
                if (this.f4343j) {
                    view.offsetTopAndBottom(iArr[i2]);
                } else {
                    view.offsetLeftAndRight(iArr[i2]);
                }
                view.setScaleX(1.0f - this.M[i2]);
                view.setScaleY(1.0f - this.M[i2]);
                if (this.f4344k) {
                    float[] fArr2 = this.N;
                    if (fArr2 != null && fArr2.length >= this.f4345l) {
                        view.setAlpha(fArr2[i2]);
                        return;
                    } else {
                        view.setAlpha(1.0f - ((this.f4342i * i2) * ((int) Math.pow(2.0d, (double) (i2 - 1)))));
                        return;
                    }
                }
                return;
            }
        }
        super.l(view, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.lib.flexcube.layout.floor.banner.swipe.swipecard.SwipeFlingAdapterView
    public void m(float f2) {
        float[] fArr;
        int i2;
        int[] iArr = this.L;
        if (iArr != null) {
            int length = iArr.length;
            int i3 = this.f4345l;
            if (length >= i3 && (fArr = this.M) != null && fArr.length >= i3) {
                int childCount = getChildCount();
                if (childCount > 1) {
                    int i4 = 3;
                    if (childCount == 2) {
                        i2 = this.f4346m - 1;
                        i4 = 1;
                    } else if (childCount == 3) {
                        i2 = this.f4346m - 2;
                        i4 = 2;
                    } else {
                        i2 = this.f4346m - 3;
                    }
                    float abs = Math.abs(f2);
                    while (i2 < this.f4346m) {
                        View childAt = getChildAt(i2);
                        if (i4 < this.f4345l) {
                            int i5 = 0;
                            if (i4 >= 1) {
                                int[] iArr2 = this.L;
                                int i6 = i4 - 1;
                                i5 = (int) (((iArr2[i4] - iArr2[i6]) * (1.0f - abs)) + iArr2[i6]);
                            }
                            if (this.f4343j) {
                                childAt.offsetTopAndBottom((i5 - childAt.getTop()) + this.f4347n);
                            } else {
                                childAt.offsetLeftAndRight((i5 - childAt.getLeft()) + this.o);
                            }
                            float f3 = 0.0f;
                            if (i4 >= 1) {
                                float[] fArr2 = this.M;
                                int i7 = i4 - 1;
                                f3 = fArr2[i7] + ((fArr2[i4] - fArr2[i7]) * (1.0f - abs));
                            }
                            float f4 = 1.0f - f3;
                            childAt.setScaleX(f4);
                            childAt.setScaleY(f4);
                        }
                        if (this.f4344k) {
                            float[] fArr3 = this.N;
                            if (fArr3 != null) {
                                int length2 = fArr3.length;
                                int i8 = this.f4345l;
                                if (length2 >= i8 && i4 < i8) {
                                    int i9 = i4 - 1;
                                    childAt.setAlpha(((fArr3[i4] - fArr3[i9]) * (1.0f - abs)) + fArr3[i9]);
                                }
                            }
                            childAt.setAlpha(1.0f - ((this.f4342i * (i4 - abs)) * ((int) Math.pow(2.0d, (double) (i4 - 1)))));
                        }
                        i2++;
                        i4--;
                    }
                    return;
                }
                return;
            }
        }
        super.m(f2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x0007, code lost:
        if (r0 != 2) goto L14;
     */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.I = motionEvent.getX();
            this.J = motionEvent.getY();
        }
        int abs = (int) Math.abs(this.I - motionEvent.getX());
        if (abs > ((int) Math.abs(this.J - motionEvent.getY())) && abs > this.K) {
            return true;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public CustomSwipeFlingAdapterView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CustomSwipeFlingAdapterView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.K = new ViewConfiguration().getScaledTouchSlop();
    }
}
