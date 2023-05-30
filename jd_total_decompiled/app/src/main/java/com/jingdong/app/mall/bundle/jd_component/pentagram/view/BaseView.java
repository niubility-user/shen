package com.jingdong.app.mall.bundle.jd_component.pentagram.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* loaded from: classes2.dex */
public abstract class BaseView<T> extends FrameLayout implements View.OnClickListener {
    protected T data;

    public BaseView(@NonNull Context context) {
        this(context, null);
    }

    private void init(@NonNull Context context) {
        int layoutId = layoutId();
        if (layoutId > 0) {
            LayoutInflater.from(context).inflate(layoutId, this);
        }
        initView();
        bindEvent();
    }

    public abstract void bind(T t);

    protected void bindEvent() {
    }

    protected abstract void initView();

    protected abstract int layoutId();

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    public abstract void reset(Object obj);

    public BaseView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BaseView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        init(context);
    }
}
