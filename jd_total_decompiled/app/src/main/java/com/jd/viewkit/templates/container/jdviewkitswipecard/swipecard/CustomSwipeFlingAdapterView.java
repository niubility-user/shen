package com.jd.viewkit.templates.container.jdviewkitswipecard.swipecard;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/* loaded from: classes18.dex */
public class CustomSwipeFlingAdapterView extends SwipeFlingAdapterView {
    private float mDownX;
    private float mDownY;
    private int mScaledTouchSlop;
    private int[] offsetSteps;
    private float[] scaleSteps;

    public CustomSwipeFlingAdapterView(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.viewkit.templates.container.jdviewkitswipecard.swipecard.SwipeFlingAdapterView
    public void adjustChildView(View view, int i2) {
        float[] fArr;
        int[] iArr = this.offsetSteps;
        if (iArr != null) {
            int length = iArr.length;
            int i3 = this.MAX_VISIBLE;
            if (length >= i3 && (fArr = this.scaleSteps) != null && fArr.length >= i3) {
                if (i2 <= -1 || i2 >= i3) {
                    return;
                }
                if (i2 > 3) {
                    i2 = 3;
                }
                if (this.showOtherInBottom) {
                    view.offsetTopAndBottom(iArr[i2]);
                } else {
                    view.offsetLeftAndRight(iArr[i2]);
                }
                view.setScaleX(1.0f - this.scaleSteps[i2]);
                view.setScaleY(1.0f - this.scaleSteps[i2]);
                return;
            }
        }
        super.adjustChildView(view, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.viewkit.templates.container.jdviewkitswipecard.swipecard.SwipeFlingAdapterView
    public void adjustChildrenOfUnderTopView(float f2) {
        float[] fArr;
        int i2;
        int[] iArr = this.offsetSteps;
        if (iArr != null) {
            int length = iArr.length;
            int i3 = this.MAX_VISIBLE;
            if (length >= i3 && (fArr = this.scaleSteps) != null && fArr.length >= i3) {
                int childCount = getChildCount();
                if (childCount > 1) {
                    int i4 = 3;
                    if (childCount == 2) {
                        i2 = this.LAST_OBJECT_IN_STACK - 1;
                        i4 = 1;
                    } else if (childCount == 3) {
                        i2 = this.LAST_OBJECT_IN_STACK - 2;
                        i4 = 2;
                    } else {
                        i2 = this.LAST_OBJECT_IN_STACK - 3;
                    }
                    float abs = Math.abs(f2);
                    while (i2 < this.LAST_OBJECT_IN_STACK) {
                        View childAt = getChildAt(i2);
                        if (i4 < this.MAX_VISIBLE) {
                            int i5 = 0;
                            if (i4 >= 1) {
                                int[] iArr2 = this.offsetSteps;
                                int i6 = i4 - 1;
                                i5 = (int) (((iArr2[i4] - iArr2[i6]) * (1.0f - abs)) + iArr2[i6]);
                            }
                            if (this.showOtherInBottom) {
                                childAt.offsetTopAndBottom((i5 - childAt.getTop()) + this.initTop);
                            } else {
                                childAt.offsetLeftAndRight((i5 - childAt.getLeft()) + this.initLeft);
                            }
                            float f3 = 0.0f;
                            if (i4 >= 1) {
                                float[] fArr2 = this.scaleSteps;
                                int i7 = i4 - 1;
                                f3 = fArr2[i7] + ((fArr2[i4] - fArr2[i7]) * (1.0f - abs));
                            }
                            float f4 = 1.0f - f3;
                            childAt.setScaleX(f4);
                            childAt.setScaleY(f4);
                        }
                        i2++;
                        i4--;
                    }
                    return;
                }
                return;
            }
        }
        super.adjustChildrenOfUnderTopView(f2);
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
            this.mDownX = motionEvent.getX();
            this.mDownY = motionEvent.getY();
        }
        int abs = (int) Math.abs(this.mDownX - motionEvent.getX());
        if (abs > ((int) Math.abs(this.mDownY - motionEvent.getY())) && abs > this.mScaledTouchSlop) {
            return true;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public void setOffsetSteps(int[] iArr) {
        this.offsetSteps = iArr;
    }

    public void setScaleSteps(float[] fArr) {
        this.scaleSteps = fArr;
    }

    public CustomSwipeFlingAdapterView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CustomSwipeFlingAdapterView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mScaledTouchSlop = new ViewConfiguration().getScaledTouchSlop();
    }
}
