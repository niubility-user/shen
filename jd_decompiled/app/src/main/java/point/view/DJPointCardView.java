package point.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import point.DJPointVisibleUtil;
import point.delegate.DJPointViewDelegate;
import point.interfaces.DJPointView;

/* loaded from: classes11.dex */
public class DJPointCardView extends CardView implements DJPointView {
    private final DJPointViewDelegate djPointViewDelegate;

    public DJPointCardView(Context context) {
        this(context, null);
    }

    @Override // point.interfaces.DJPointView
    public void AgainAttach() {
        DJPointViewDelegate dJPointViewDelegate = this.djPointViewDelegate;
        if (dJPointViewDelegate != null) {
            dJPointViewDelegate.onAttachedToWindow();
        }
    }

    @Override // point.interfaces.DJPointView
    public void cancelTimerReport() {
        DJPointViewDelegate dJPointViewDelegate = this.djPointViewDelegate;
        if (dJPointViewDelegate != null) {
            dJPointViewDelegate.cancelTimerReport();
        }
    }

    @Override // point.interfaces.DJPointView
    public boolean isVisible() {
        DJPointViewDelegate dJPointViewDelegate = this.djPointViewDelegate;
        if (dJPointViewDelegate != null) {
            return dJPointViewDelegate.isVisible();
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        DJPointViewDelegate dJPointViewDelegate = this.djPointViewDelegate;
        if (dJPointViewDelegate != null) {
            dJPointViewDelegate.onAttachedToWindow();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        DJPointViewDelegate dJPointViewDelegate = this.djPointViewDelegate;
        if (dJPointViewDelegate != null) {
            dJPointViewDelegate.onDetachedFromWindow();
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    protected void onVisibilityChanged(@NonNull View view, int i2) {
        super.onVisibilityChanged(view, i2);
        DJPointViewDelegate dJPointViewDelegate = this.djPointViewDelegate;
        if (dJPointViewDelegate != null) {
            dJPointViewDelegate.onVisibilityChanged(view, i2);
        }
    }

    @Override // point.interfaces.DJPointView
    public void setDJPointVisibleUtil(DJPointVisibleUtil dJPointVisibleUtil) {
        DJPointViewDelegate dJPointViewDelegate = this.djPointViewDelegate;
        if (dJPointViewDelegate != null) {
            dJPointViewDelegate.setDJPointVisibleUtil(dJPointVisibleUtil);
        }
    }

    @Override // point.interfaces.DJPointView
    public void startTimerReport() {
        DJPointViewDelegate dJPointViewDelegate = this.djPointViewDelegate;
        if (dJPointViewDelegate != null) {
            dJPointViewDelegate.startTimerReport();
        }
    }

    public DJPointCardView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public DJPointCardView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.djPointViewDelegate = new DJPointViewDelegate(this);
    }
}
