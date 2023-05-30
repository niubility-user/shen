package com.jingdong.common.unification.jdbottomdialogview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/* loaded from: classes6.dex */
public class JDBottomDialogViewBuilder {
    private View contentView;
    private Context context;
    private ViewGroup decorView;
    private int defaultContentHeight;
    private boolean expanded;
    private int expandedHeight;
    private String leftButtonText;
    private OnCancelListener onCancelListener;
    private OnDismissListener onDismissListener;
    private View.OnClickListener onLeftBtClickListener;
    private View.OnClickListener onRightBtClickListener;
    private String rightButtonText;
    private final FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(-1, -2, 80);
    private boolean isCancelable = true;
    private boolean isRound = true;

    public JDBottomDialogViewBuilder(Context context) {
        this.context = context;
    }

    public JDBottomDialogView create() {
        return new JDBottomDialogView(this);
    }

    public int getContentHeight() {
        if (isExpanded()) {
            return this.expandedHeight;
        }
        return this.defaultContentHeight;
    }

    public FrameLayout.LayoutParams getContentParams() {
        if (isExpanded()) {
            this.params.height = this.expandedHeight;
        } else {
            this.params.height = this.defaultContentHeight;
        }
        return this.params;
    }

    public View getContentView() {
        return this.contentView;
    }

    public Context getContext() {
        return this.context;
    }

    public ViewGroup getDecorView() {
        return this.decorView;
    }

    public int getDefaultContentHeight() {
        return this.defaultContentHeight;
    }

    public String getLeftBtText() {
        return this.leftButtonText;
    }

    public OnCancelListener getOnCancelListener() {
        return this.onCancelListener;
    }

    public OnDismissListener getOnDismissListener() {
        return this.onDismissListener;
    }

    public View.OnClickListener getOnLeftBtClickListener() {
        return this.onLeftBtClickListener;
    }

    public View.OnClickListener getOnRightBtClickListener() {
        return this.onRightBtClickListener;
    }

    public FrameLayout.LayoutParams getOutmostLayoutParams() {
        return new FrameLayout.LayoutParams(-1, -1);
    }

    public String getRightBtText() {
        return this.rightButtonText;
    }

    public boolean isCancelable() {
        return this.isCancelable;
    }

    public boolean isExpanded() {
        return this.expanded;
    }

    public boolean isRound() {
        return this.isRound;
    }

    public JDBottomDialogViewBuilder setCancelable(boolean z) {
        this.isCancelable = z;
        return this;
    }

    public JDBottomDialogViewBuilder setContentView(View view) {
        this.contentView = view;
        return this;
    }

    public JDBottomDialogViewBuilder setContentWidth(int i2) {
        this.params.width = i2;
        return this;
    }

    public JDBottomDialogViewBuilder setDecorView(ViewGroup viewGroup) {
        this.decorView = viewGroup;
        return this;
    }

    public JDBottomDialogViewBuilder setExpanded(boolean z, int i2) {
        this.expanded = z;
        this.defaultContentHeight = i2;
        return this;
    }

    public JDBottomDialogViewBuilder setLeftButton(String str, View.OnClickListener onClickListener) {
        this.leftButtonText = str;
        this.onLeftBtClickListener = onClickListener;
        return this;
    }

    public JDBottomDialogViewBuilder setOnCancelListener(OnCancelListener onCancelListener) {
        this.onCancelListener = onCancelListener;
        return this;
    }

    public JDBottomDialogViewBuilder setOnDismissListener(OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
        return this;
    }

    public JDBottomDialogViewBuilder setRightButton(String str, View.OnClickListener onClickListener) {
        this.rightButtonText = str;
        this.onRightBtClickListener = onClickListener;
        return this;
    }

    public JDBottomDialogViewBuilder setRound(boolean z) {
        this.isRound = z;
        return this;
    }

    public JDBottomDialogViewBuilder setExpanded(boolean z, int i2, int i3) {
        this.expanded = z;
        this.defaultContentHeight = i2;
        this.expandedHeight = i3;
        return this;
    }
}
