package point.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jingdong.pdj.libdjbasecore.view.dark.DarkViewInterface;
import com.jingdong.pdj.libdjbasecore.view.round.RoundHelper;
import com.jingdong.pdj.libdjbasecore.view.round.RoundMethodInterface;
import point.DJPointVisibleUtil;
import point.delegate.DJPointViewDelegate;
import point.interfaces.DJPointView;

/* loaded from: classes11.dex */
public class DJPointFrameLayout extends FrameLayout implements DJPointView, RoundMethodInterface, DarkViewInterface {
    private final DJPointViewDelegate djPointViewDelegate;
    private int mDarkColor;
    private RoundHelper mHelper;
    private boolean mSwitchDark;

    public DJPointFrameLayout(Context context) {
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

    @Override // android.view.View
    public void draw(Canvas canvas) {
        this.mHelper.preDraw(canvas);
        super.draw(canvas);
        this.mHelper.drawPath(canvas);
        if (this.mSwitchDark) {
            int i2 = this.mDarkColor;
            if (i2 == 0) {
                i2 = 436207616;
            }
            canvas.drawColor(i2);
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
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        this.mHelper.onSizeChanged(i2, i3);
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

    @Override // com.jingdong.pdj.libdjbasecore.view.dark.DarkViewInterface
    public void setDarkColor(int i2) {
        this.mDarkColor = i2;
    }

    @Override // com.jingdong.pdj.libdjbasecore.view.round.RoundMethodInterface
    public void setRadius(float f2) {
        this.mHelper.setRadius(f2);
    }

    @Override // com.jingdong.pdj.libdjbasecore.view.round.RoundMethodInterface
    public void setRadiusBottom(float f2) {
        this.mHelper.setRadiusBottom(f2);
    }

    @Override // com.jingdong.pdj.libdjbasecore.view.round.RoundMethodInterface
    public void setRadiusBottomLeft(float f2) {
        this.mHelper.setRadiusBottomLeft(f2);
    }

    @Override // com.jingdong.pdj.libdjbasecore.view.round.RoundMethodInterface
    public void setRadiusBottomRight(float f2) {
        this.mHelper.setRadiusBottomRight(f2);
    }

    @Override // com.jingdong.pdj.libdjbasecore.view.round.RoundMethodInterface
    public void setRadiusLeft(float f2) {
        this.mHelper.setRadiusLeft(f2);
    }

    @Override // com.jingdong.pdj.libdjbasecore.view.round.RoundMethodInterface
    public void setRadiusRight(float f2) {
        this.mHelper.setRadiusRight(f2);
    }

    @Override // com.jingdong.pdj.libdjbasecore.view.round.RoundMethodInterface
    public void setRadiusTop(float f2) {
        this.mHelper.setRadiusTop(f2);
    }

    @Override // com.jingdong.pdj.libdjbasecore.view.round.RoundMethodInterface
    public void setRadiusTopLeft(float f2) {
        this.mHelper.setRadiusTopLeft(f2);
    }

    @Override // com.jingdong.pdj.libdjbasecore.view.round.RoundMethodInterface
    public void setRadiusTopRight(float f2) {
        this.mHelper.setRadiusTopRight(f2);
    }

    @Override // com.jingdong.pdj.libdjbasecore.view.round.RoundMethodInterface
    public void setStrokeColor(int i2) {
        this.mHelper.setStrokeColor(i2);
    }

    @Override // com.jingdong.pdj.libdjbasecore.view.round.RoundMethodInterface
    public void setStrokeWidth(float f2) {
        this.mHelper.setStrokeWidth(f2);
    }

    @Override // com.jingdong.pdj.libdjbasecore.view.round.RoundMethodInterface
    public void setStrokeWidthColor(float f2, int i2) {
        this.mHelper.setStrokeWidthColor(f2, i2);
    }

    @Override // point.interfaces.DJPointView
    public void startTimerReport() {
        DJPointViewDelegate dJPointViewDelegate = this.djPointViewDelegate;
        if (dJPointViewDelegate != null) {
            dJPointViewDelegate.startTimerReport();
        }
    }

    @Override // com.jingdong.pdj.libdjbasecore.view.dark.DarkViewInterface
    public void switchDark(boolean z) {
        this.mSwitchDark = z;
    }

    public DJPointFrameLayout(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    @Override // com.jingdong.pdj.libdjbasecore.view.round.RoundMethodInterface
    public void setRadius(float f2, float f3, float f4, float f5) {
        this.mHelper.setRadius(f2, f3, f4, f5);
    }

    public DJPointFrameLayout(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mHelper = new RoundHelper();
        this.djPointViewDelegate = new DJPointViewDelegate(this);
        this.mHelper.init(context, attributeSet, this);
    }
}
