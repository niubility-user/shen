package androidx.transition;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes.dex */
class GhostViewApi14 extends View implements GhostViewImpl {
    Matrix mCurrentMatrix;
    private int mDeltaX;
    private int mDeltaY;
    private final Matrix mMatrix;
    private final ViewTreeObserver.OnPreDrawListener mOnPreDrawListener;
    int mReferences;
    ViewGroup mStartParent;
    View mStartView;
    final View mView;

    GhostViewApi14(View view) {
        super(view.getContext());
        this.mMatrix = new Matrix();
        this.mOnPreDrawListener = new ViewTreeObserver.OnPreDrawListener() { // from class: androidx.transition.GhostViewApi14.1
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                View view2;
                GhostViewApi14 ghostViewApi14 = GhostViewApi14.this;
                ghostViewApi14.mCurrentMatrix = ghostViewApi14.mView.getMatrix();
                ViewCompat.postInvalidateOnAnimation(GhostViewApi14.this);
                GhostViewApi14 ghostViewApi142 = GhostViewApi14.this;
                ViewGroup viewGroup = ghostViewApi142.mStartParent;
                if (viewGroup == null || (view2 = ghostViewApi142.mStartView) == null) {
                    return true;
                }
                viewGroup.endViewTransition(view2);
                ViewCompat.postInvalidateOnAnimation(GhostViewApi14.this.mStartParent);
                GhostViewApi14 ghostViewApi143 = GhostViewApi14.this;
                ghostViewApi143.mStartParent = null;
                ghostViewApi143.mStartView = null;
                return true;
            }
        };
        this.mView = view;
        setLayerType(2, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static GhostViewImpl addGhost(View view, ViewGroup viewGroup) {
        GhostViewApi14 ghostView = getGhostView(view);
        if (ghostView == null) {
            FrameLayout findFrameLayout = findFrameLayout(viewGroup);
            if (findFrameLayout == null) {
                return null;
            }
            ghostView = new GhostViewApi14(view);
            findFrameLayout.addView(ghostView);
        }
        ghostView.mReferences++;
        return ghostView;
    }

    private static FrameLayout findFrameLayout(ViewGroup viewGroup) {
        while (!(viewGroup instanceof FrameLayout)) {
            ViewParent parent = viewGroup.getParent();
            if (!(parent instanceof ViewGroup)) {
                return null;
            }
            viewGroup = (ViewGroup) parent;
        }
        return (FrameLayout) viewGroup;
    }

    static GhostViewApi14 getGhostView(@NonNull View view) {
        return (GhostViewApi14) view.getTag(R.id.ghost_view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void removeGhost(View view) {
        GhostViewApi14 ghostView = getGhostView(view);
        if (ghostView != null) {
            int i2 = ghostView.mReferences - 1;
            ghostView.mReferences = i2;
            if (i2 <= 0) {
                ViewParent parent = ghostView.getParent();
                if (parent instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) parent;
                    viewGroup.endViewTransition(ghostView);
                    viewGroup.removeView(ghostView);
                }
            }
        }
    }

    private static void setGhostView(@NonNull View view, GhostViewApi14 ghostViewApi14) {
        view.setTag(R.id.ghost_view, ghostViewApi14);
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setGhostView(this.mView, this);
        int[] iArr = new int[2];
        getLocationOnScreen(iArr);
        this.mView.getLocationOnScreen(r0);
        int[] iArr2 = {(int) (iArr2[0] - this.mView.getTranslationX()), (int) (iArr2[1] - this.mView.getTranslationY())};
        this.mDeltaX = iArr2[0] - iArr[0];
        this.mDeltaY = iArr2[1] - iArr[1];
        this.mView.getViewTreeObserver().addOnPreDrawListener(this.mOnPreDrawListener);
        this.mView.setVisibility(4);
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        this.mView.getViewTreeObserver().removeOnPreDrawListener(this.mOnPreDrawListener);
        this.mView.setVisibility(0);
        setGhostView(this.mView, null);
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        this.mMatrix.set(this.mCurrentMatrix);
        this.mMatrix.postTranslate(this.mDeltaX, this.mDeltaY);
        canvas.setMatrix(this.mMatrix);
        this.mView.draw(canvas);
    }

    @Override // androidx.transition.GhostViewImpl
    public void reserveEndViewTransition(ViewGroup viewGroup, View view) {
        this.mStartParent = viewGroup;
        this.mStartView = view;
    }

    @Override // android.view.View, androidx.transition.GhostViewImpl
    public void setVisibility(int i2) {
        super.setVisibility(i2);
        this.mView.setVisibility(i2 == 0 ? 4 : 0);
    }
}
