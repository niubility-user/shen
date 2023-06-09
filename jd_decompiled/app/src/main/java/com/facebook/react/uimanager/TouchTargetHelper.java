package com.facebook.react.uimanager;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.touch.ReactHitSlopView;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public class TouchTargetHelper {
    private static final float[] mEventCoords = new float[2];
    private static final PointF mTempPoint = new PointF();
    private static final float[] mMatrixTransformCoords = new float[2];
    private static final Matrix mInverseMatrix = new Matrix();

    private static View findClosestReactAncestor(View view) {
        while (view != null && view.getId() <= 0) {
            view = (View) view.getParent();
        }
        return view;
    }

    public static int findTargetTagAndCoordinatesForTouch(float f2, float f3, ViewGroup viewGroup, float[] fArr, @Nullable int[] iArr) {
        View findClosestReactAncestor;
        UiThreadUtil.assertOnUiThread();
        int id = viewGroup.getId();
        fArr[0] = f2;
        fArr[1] = f3;
        View findTouchTargetView = findTouchTargetView(fArr, viewGroup);
        if (findTouchTargetView == null || (findClosestReactAncestor = findClosestReactAncestor(findTouchTargetView)) == null) {
            return id;
        }
        if (iArr != null) {
            iArr[0] = findClosestReactAncestor.getId();
        }
        return getTouchTargetForView(findClosestReactAncestor, fArr[0], fArr[1]);
    }

    public static int findTargetTagForTouch(float f2, float f3, ViewGroup viewGroup) {
        return findTargetTagAndCoordinatesForTouch(f2, f3, viewGroup, mEventCoords, null);
    }

    private static View findTouchTargetView(float[] fArr, ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        ReactZIndexedViewGroup reactZIndexedViewGroup = viewGroup instanceof ReactZIndexedViewGroup ? (ReactZIndexedViewGroup) viewGroup : null;
        for (int i2 = childCount - 1; i2 >= 0; i2--) {
            View childAt = viewGroup.getChildAt(reactZIndexedViewGroup != null ? reactZIndexedViewGroup.getZIndexMappedChildIndex(i2) : i2);
            PointF pointF = mTempPoint;
            if (isTransformedTouchPointInView(fArr[0], fArr[1], viewGroup, childAt, pointF)) {
                float f2 = fArr[0];
                float f3 = fArr[1];
                fArr[0] = pointF.x;
                fArr[1] = pointF.y;
                View findTouchTargetViewWithPointerEvents = findTouchTargetViewWithPointerEvents(fArr, childAt);
                if (findTouchTargetViewWithPointerEvents != null) {
                    return findTouchTargetViewWithPointerEvents;
                }
                fArr[0] = f2;
                fArr[1] = f3;
            }
        }
        return viewGroup;
    }

    @Nullable
    private static View findTouchTargetViewWithPointerEvents(float[] fArr, View view) {
        PointerEvents pointerEvents = view instanceof ReactPointerEventsView ? ((ReactPointerEventsView) view).getPointerEvents() : PointerEvents.AUTO;
        if (!view.isEnabled()) {
            if (pointerEvents == PointerEvents.AUTO) {
                pointerEvents = PointerEvents.BOX_NONE;
            } else if (pointerEvents == PointerEvents.BOX_ONLY) {
                pointerEvents = PointerEvents.NONE;
            }
        }
        if (pointerEvents == PointerEvents.NONE) {
            return null;
        }
        if (pointerEvents == PointerEvents.BOX_ONLY) {
            return view;
        }
        if (pointerEvents == PointerEvents.BOX_NONE) {
            if (view instanceof ViewGroup) {
                View findTouchTargetView = findTouchTargetView(fArr, (ViewGroup) view);
                if (findTouchTargetView != view) {
                    return findTouchTargetView;
                }
                if ((view instanceof ReactCompoundView) && ((ReactCompoundView) view).reactTagForTouch(fArr[0], fArr[1]) != view.getId()) {
                    return view;
                }
            }
            return null;
        } else if (pointerEvents == PointerEvents.AUTO) {
            return (!((view instanceof ReactCompoundViewGroup) && ((ReactCompoundViewGroup) view).interceptsTouchEvent(fArr[0], fArr[1])) && (view instanceof ViewGroup)) ? findTouchTargetView(fArr, (ViewGroup) view) : view;
        } else {
            throw new JSApplicationIllegalArgumentException("Unknown pointer event type: " + pointerEvents.toString());
        }
    }

    private static int getTouchTargetForView(View view, float f2, float f3) {
        if (view instanceof ReactCompoundView) {
            return ((ReactCompoundView) view).reactTagForTouch(f2, f3);
        }
        return view.getId();
    }

    private static boolean isTransformedTouchPointInView(float f2, float f3, ViewGroup viewGroup, View view, PointF pointF) {
        float scrollX = (f2 + viewGroup.getScrollX()) - view.getLeft();
        float scrollY = (f3 + viewGroup.getScrollY()) - view.getTop();
        Matrix matrix = view.getMatrix();
        if (!matrix.isIdentity()) {
            float[] fArr = mMatrixTransformCoords;
            fArr[0] = scrollX;
            fArr[1] = scrollY;
            Matrix matrix2 = mInverseMatrix;
            matrix.invert(matrix2);
            matrix2.mapPoints(fArr);
            scrollX = fArr[0];
            scrollY = fArr[1];
        }
        if (view instanceof ReactHitSlopView) {
            ReactHitSlopView reactHitSlopView = (ReactHitSlopView) view;
            if (reactHitSlopView.getHitSlopRect() != null) {
                Rect hitSlopRect = reactHitSlopView.getHitSlopRect();
                if (scrollX < (-hitSlopRect.left) || scrollX >= (view.getRight() - view.getLeft()) + hitSlopRect.right || scrollY < (-hitSlopRect.top) || scrollY >= (view.getBottom() - view.getTop()) + hitSlopRect.bottom) {
                    return false;
                }
                pointF.set(scrollX, scrollY);
                return true;
            }
        }
        if (scrollX < 0.0f || scrollX >= view.getRight() - view.getLeft() || scrollY < 0.0f || scrollY >= view.getBottom() - view.getTop()) {
            return false;
        }
        pointF.set(scrollX, scrollY);
        return true;
    }

    public static int findTargetTagForTouch(float f2, float f3, ViewGroup viewGroup, @Nullable int[] iArr) {
        return findTargetTagAndCoordinatesForTouch(f2, f3, viewGroup, mEventCoords, iArr);
    }
}
