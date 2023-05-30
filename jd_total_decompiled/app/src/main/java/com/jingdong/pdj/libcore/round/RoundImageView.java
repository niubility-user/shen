package com.jingdong.pdj.libcore.round;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.pdj.libdjbasecore.view.round.RoundHelper;
import com.jingdong.pdj.libdjbasecore.view.round.RoundMethodInterface;
import point.DJPointVisibleUtil;
import point.delegate.DJPointViewDelegate;
import point.interfaces.DJPointView;

/* loaded from: classes7.dex */
public class RoundImageView extends SimpleDraweeView implements RoundMethodInterface, DJPointView {
    private DJPointViewDelegate djPointViewDelegate;
    private final RoundHelper mHelper;

    public RoundImageView(Context context) {
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
    }

    @Override // point.interfaces.DJPointView
    public boolean isVisible() {
        DJPointViewDelegate dJPointViewDelegate = this.djPointViewDelegate;
        return dJPointViewDelegate != null && dJPointViewDelegate.isVisible();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.drawee.view.DraweeView, android.widget.ImageView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        DJPointViewDelegate dJPointViewDelegate = this.djPointViewDelegate;
        if (dJPointViewDelegate != null) {
            dJPointViewDelegate.onAttachedToWindow();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.drawee.view.DraweeView, android.widget.ImageView, android.view.View
    public void onDetachedFromWindow() {
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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.drawee.view.DraweeView, android.view.View
    public void onVisibilityChanged(View view, int i2) {
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

    public RoundImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    @Override // com.jingdong.pdj.libdjbasecore.view.round.RoundMethodInterface
    public void setRadius(float f2, float f3, float f4, float f5) {
        this.mHelper.setRadius(f2, f3, f4, f5);
    }

    public RoundImageView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        RoundHelper roundHelper = new RoundHelper();
        this.mHelper = roundHelper;
        roundHelper.init(context, attributeSet, this);
        this.djPointViewDelegate = new DJPointViewDelegate(this);
    }
}
