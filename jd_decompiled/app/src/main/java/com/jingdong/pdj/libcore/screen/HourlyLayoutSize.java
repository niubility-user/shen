package com.jingdong.pdj.libcore.screen;

import android.graphics.Rect;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.pdj.libcore.utils.HourlyGoSafeRunnable;
import com.jingdong.pdj.libdjbasecore.utils.DJHandlerUtils;

/* loaded from: classes7.dex */
public class HourlyLayoutSize {
    public static final int MP = -1;
    public static final int WC = -2;
    private int height;
    public Rect margin;
    private int offsetHeight;
    private int offsetWidth;
    public Rect padding;
    private int preWidth = HourlyGoDpi750.H_WIDTH;
    private int width;

    public HourlyLayoutSize(int i2, int i3) {
        this.width = i2;
        this.height = i3;
    }

    public static boolean checkSizeChanged(View view, int i2, int i3) {
        ViewGroup.LayoutParams layoutParams;
        if (view == null || (layoutParams = view.getLayoutParams()) == null) {
            return false;
        }
        if (layoutParams.width == i2 && layoutParams.height == i3) {
            return false;
        }
        layoutParams.width = i2;
        layoutParams.height = i3;
        view.setLayoutParams(layoutParams);
        return true;
    }

    public static boolean checkWidthChanged(View view, int i2, int i3) {
        ViewGroup.LayoutParams layoutParams;
        if (view == null || (layoutParams = view.getLayoutParams()) == null || layoutParams.width == i2) {
            return false;
        }
        layoutParams.width = i2;
        layoutParams.height = i3;
        view.setLayoutParams(layoutParams);
        return true;
    }

    private ViewGroup.LayoutParams getLayoutParams(View view, View view2) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            return layoutParams;
        }
        if (view2 instanceof RelativeLayout) {
            return new RelativeLayout.LayoutParams(-2, -2);
        }
        if (view2 instanceof LinearLayout) {
            return new LinearLayout.LayoutParams(-2, -2);
        }
        return view2 instanceof FrameLayout ? new FrameLayout.LayoutParams(-2, -2) : layoutParams;
    }

    public static void initSingleBoldText(TextView textView, boolean z) {
        if (textView != null) {
            textView.setSingleLine(true);
            textView.setIncludeFontPadding(false);
            textView.getPaint().setFakeBoldText(z);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setGravity(17);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetViewOnMainThread(View view, View view2) {
        if (view == null) {
            return;
        }
        if (this.padding != null) {
            view.setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams(view, view2);
        if (layoutParams == null) {
            return;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) DJHandlerUtils.convert(layoutParams);
        marginLayoutParams.width = getWidth();
        marginLayoutParams.height = getHeight();
        if (this.margin != null) {
            marginLayoutParams.setMargins(getMarginLeft(), getMarginTop(), getMarginRight(), getMarginBottom());
        }
        view.setLayoutParams(marginLayoutParams);
    }

    public static void setMargin(HourlyLayoutSize hourlyLayoutSize, ViewGroup.MarginLayoutParams marginLayoutParams) {
        if (hourlyLayoutSize == null || !hourlyLayoutSize.hasMargin() || marginLayoutParams == null) {
            return;
        }
        marginLayoutParams.setMargins(hourlyLayoutSize.getMarginLeft(), hourlyLayoutSize.getMarginTop(), hourlyLayoutSize.getMarginRight(), hourlyLayoutSize.getMarginBottom());
    }

    public static void setPadding(HourlyLayoutSize hourlyLayoutSize, View view) {
        if (hourlyLayoutSize == null || !hourlyLayoutSize.hasPadding() || view == null) {
            return;
        }
        view.setPadding(hourlyLayoutSize.getPaddingLeft(), hourlyLayoutSize.getPaddingTop(), hourlyLayoutSize.getPaddingRight(), hourlyLayoutSize.getPaddingBottom());
    }

    public static void setTextSize(TextView textView, int i2) {
        if (textView != null) {
            textView.setTextSize(0, HourlyGoDpi750.getSizeBy750(i2));
        }
    }

    public int get750Height() {
        return this.height;
    }

    public Rect get750Margin() {
        return this.margin;
    }

    public Rect get750Padding() {
        return this.padding;
    }

    public ConstraintLayout.LayoutParams getCosParams(@NonNull View view) {
        ConstraintLayout.LayoutParams layoutParams;
        ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
        if (layoutParams2 instanceof ConstraintLayout.LayoutParams) {
            layoutParams = (ConstraintLayout.LayoutParams) layoutParams2;
            ((ViewGroup.MarginLayoutParams) layoutParams).width = getWidth();
            ((ViewGroup.MarginLayoutParams) layoutParams).height = getHeight();
        } else {
            layoutParams = new ConstraintLayout.LayoutParams(getWidth(), getHeight());
        }
        setMargin(this, layoutParams);
        setPadding(this, view);
        setPreWidth(HourlyGoDpi750.H_WIDTH);
        return layoutParams;
    }

    public FrameLayout.LayoutParams getFLParams(@NonNull View view) {
        FrameLayout.LayoutParams layoutParams;
        ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
        if (layoutParams2 instanceof FrameLayout.LayoutParams) {
            layoutParams = (FrameLayout.LayoutParams) layoutParams2;
            layoutParams.width = getWidth();
            layoutParams.height = getHeight();
        } else {
            layoutParams = new FrameLayout.LayoutParams(getWidth(), getHeight());
        }
        setMargin(this, layoutParams);
        setPadding(this, view);
        setPreWidth(HourlyGoDpi750.H_WIDTH);
        return layoutParams;
    }

    public int getHeight() {
        int i2 = this.height;
        return i2 >= 0 ? HourlyGoDpi750.getSizeBy750(i2) + this.offsetHeight : i2;
    }

    public LinearLayout.LayoutParams getLLParams(@NonNull View view) {
        LinearLayout.LayoutParams layoutParams;
        ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
        if (layoutParams2 instanceof LinearLayout.LayoutParams) {
            layoutParams = (LinearLayout.LayoutParams) layoutParams2;
            layoutParams.width = getWidth();
            layoutParams.height = getHeight();
        } else {
            layoutParams = new LinearLayout.LayoutParams(getWidth(), getHeight());
        }
        setMargin(this, layoutParams);
        setPadding(this, view);
        setPreWidth(HourlyGoDpi750.H_WIDTH);
        return layoutParams;
    }

    public int getMarginBottom() {
        Rect rect = this.margin;
        if (rect != null) {
            return HourlyGoDpi750.getSizeBy750(rect.bottom);
        }
        return 0;
    }

    public int getMarginLeft() {
        Rect rect = this.margin;
        if (rect != null) {
            return HourlyGoDpi750.getSizeBy750(rect.left);
        }
        return 0;
    }

    public int getMarginRight() {
        Rect rect = this.margin;
        if (rect != null) {
            return HourlyGoDpi750.getSizeBy750(rect.right);
        }
        return 0;
    }

    public int getMarginTop() {
        Rect rect = this.margin;
        if (rect != null) {
            return HourlyGoDpi750.getSizeBy750(rect.top);
        }
        return 0;
    }

    public int getOffsetHeight() {
        return this.offsetHeight;
    }

    public int getOffsetWidth() {
        return this.offsetWidth;
    }

    public int getPaddingBottom() {
        Rect rect = this.padding;
        if (rect != null) {
            return HourlyGoDpi750.getSizeBy750(rect.bottom);
        }
        return 0;
    }

    public int getPaddingLeft() {
        Rect rect = this.padding;
        if (rect != null) {
            return HourlyGoDpi750.getSizeBy750(rect.left);
        }
        return 0;
    }

    public int getPaddingRight() {
        Rect rect = this.padding;
        if (rect != null) {
            return HourlyGoDpi750.getSizeBy750(rect.right);
        }
        return 0;
    }

    public int getPaddingTop() {
        Rect rect = this.padding;
        if (rect != null) {
            return HourlyGoDpi750.getSizeBy750(rect.top);
        }
        return 0;
    }

    int getPreWidth() {
        return this.preWidth;
    }

    public RelativeLayout.LayoutParams getRLParams(@NonNull View view) {
        RelativeLayout.LayoutParams layoutParams;
        ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
        if (layoutParams2 instanceof RelativeLayout.LayoutParams) {
            layoutParams = (RelativeLayout.LayoutParams) layoutParams2;
            layoutParams.width = getWidth();
            layoutParams.height = getHeight();
        } else {
            layoutParams = new RelativeLayout.LayoutParams(getWidth(), getHeight());
        }
        setMargin(this, layoutParams);
        setPadding(this, view);
        setPreWidth(HourlyGoDpi750.H_WIDTH);
        return layoutParams;
    }

    public RecyclerView.LayoutParams getRlvParams(@NonNull View view) {
        RecyclerView.LayoutParams layoutParams;
        ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
        if (layoutParams2 instanceof RecyclerView.LayoutParams) {
            layoutParams = (RecyclerView.LayoutParams) layoutParams2;
            ((ViewGroup.MarginLayoutParams) layoutParams).width = getWidth();
            ((ViewGroup.MarginLayoutParams) layoutParams).height = getHeight();
        } else {
            layoutParams = new RecyclerView.LayoutParams(getWidth(), getHeight());
        }
        setMargin(this, layoutParams);
        setPadding(this, view);
        setPreWidth(HourlyGoDpi750.H_WIDTH);
        return layoutParams;
    }

    public int getWidth() {
        int i2 = this.width;
        return i2 >= 0 ? HourlyGoDpi750.getSizeBy750(i2) + this.offsetWidth : i2;
    }

    public boolean hasMargin() {
        return this.margin != null;
    }

    public boolean hasPadding() {
        return this.padding != null;
    }

    public void resetView(final View view, final View view2) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            resetViewOnMainThread(view, view2);
        } else {
            DJHandlerUtils.runOnUiThread(new HourlyGoSafeRunnable() { // from class: com.jingdong.pdj.libcore.screen.HourlyLayoutSize.1
                @Override // com.jingdong.pdj.libcore.utils.HourlyGoSafeRunnable
                public void safeRun() {
                    HourlyLayoutSize.this.resetViewOnMainThread(view, view2);
                }
            });
        }
    }

    public void setHeight(int i2) {
        this.height = i2;
    }

    public void setLayoutMargin(ViewGroup.LayoutParams layoutParams) {
        if (this.margin == null || !(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            return;
        }
        ((ViewGroup.MarginLayoutParams) layoutParams).setMargins(getMarginLeft(), getMarginTop(), getMarginRight(), getMarginBottom());
    }

    public void setOffsetHeight(int i2) {
        this.offsetHeight = i2;
    }

    public void setOffsetWidth(int i2) {
        this.offsetWidth = i2;
    }

    void setPreWidth(int i2) {
        this.preWidth = i2;
    }

    public void setViewPadding(View view) {
        if (view == null || this.padding == null) {
            return;
        }
        view.setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
    }

    public void setWidth(int i2) {
        this.width = i2;
    }

    public Rect get750Margin(boolean z) {
        if (z && this.margin == null) {
            this.margin = new Rect(0, 0, 0, 0);
        }
        return this.margin;
    }

    public HourlyLayoutSize setMargin(Rect rect) {
        this.margin = rect;
        return this;
    }

    public void setPadding(Rect rect, View view) {
        this.padding = rect;
        setViewPadding(view);
    }

    public void resetView(View view) {
        resetView(view, null);
    }

    public void setMargin(Rect rect, ViewGroup.LayoutParams layoutParams) {
        this.margin = rect;
        setLayoutMargin(layoutParams);
    }

    public HourlyLayoutSize(int i2, int i3, int i4, int i5) {
        this.width = i2;
        this.offsetWidth = i3;
        this.height = i4;
        this.offsetHeight = i5;
    }

    public HourlyLayoutSize setPadding(int i2, int i3, int i4, int i5) {
        Rect rect = this.padding;
        if (rect == null) {
            this.padding = new Rect(i2, i3, i4, i5);
        } else {
            rect.set(i2, i3, i4, i5);
        }
        return this;
    }

    public static boolean checkSizeChanged(View view, HourlyLayoutSize hourlyLayoutSize) {
        return checkSizeChanged(view, hourlyLayoutSize, false);
    }

    public HourlyLayoutSize setMargin(int i2, int i3, int i4, int i5) {
        Rect rect = this.margin;
        if (rect == null) {
            this.margin = new Rect(i2, i3, i4, i5);
        } else {
            rect.set(i2, i3, i4, i5);
        }
        return this;
    }

    public static boolean checkSizeChanged(View view, HourlyLayoutSize hourlyLayoutSize, boolean z) {
        ViewGroup.LayoutParams layoutParams;
        if (view == null || hourlyLayoutSize == null || (layoutParams = view.getLayoutParams()) == null) {
            return false;
        }
        if (hourlyLayoutSize.getPreWidth() == HourlyGoDpi750.H_WIDTH && layoutParams.width == hourlyLayoutSize.getWidth() && layoutParams.height == hourlyLayoutSize.getHeight() && !z) {
            return false;
        }
        hourlyLayoutSize.setPreWidth(HourlyGoDpi750.H_WIDTH);
        hourlyLayoutSize.resetView(view);
        return true;
    }
}
