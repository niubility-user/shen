package com.jingdong.common.settlement;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

/* loaded from: classes6.dex */
public class ViewHolder {
    private Context mContext;
    private View mConvertView;
    private int mLayoutId;
    private int mPosition;
    private SparseArray<View> mViews = new SparseArray<>();

    public ViewHolder(Context context, ViewGroup viewGroup, int i2, int i3) {
        this.mContext = context;
        this.mLayoutId = i2;
        this.mPosition = i3;
        View inflate = LayoutInflater.from(context).inflate(i2, viewGroup, false);
        this.mConvertView = inflate;
        inflate.setTag(this);
    }

    public static ViewHolder get(Context context, View view, ViewGroup viewGroup, int i2, int i3) {
        if (view == null) {
            return new ViewHolder(context, viewGroup, i2, i3);
        }
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.mPosition = i3;
        return viewHolder;
    }

    public View getConvertView() {
        return this.mConvertView;
    }

    public int getLayoutId() {
        return this.mLayoutId;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public <T extends View> T getView(int i2) {
        T t = (T) this.mViews.get(i2);
        if (t == null) {
            T t2 = (T) this.mConvertView.findViewById(i2);
            this.mViews.put(i2, t2);
            return t2;
        }
        return t;
    }

    public ViewHolder linkify(int i2) {
        Linkify.addLinks((TextView) getView(i2), 15);
        return this;
    }

    @SuppressLint({"NewApi"})
    public ViewHolder setAlpha(int i2, float f2) {
        if (Build.VERSION.SDK_INT >= 11) {
            getView(i2).setAlpha(f2);
        } else {
            AlphaAnimation alphaAnimation = new AlphaAnimation(f2, f2);
            alphaAnimation.setDuration(0L);
            alphaAnimation.setFillAfter(true);
            getView(i2).startAnimation(alphaAnimation);
        }
        return this;
    }

    public ViewHolder setBackgroundColor(int i2, int i3) {
        getView(i2).setBackgroundColor(i3);
        return this;
    }

    public ViewHolder setBackgroundRes(int i2, int i3) {
        getView(i2).setBackgroundResource(i3);
        return this;
    }

    public ViewHolder setChecked(int i2, boolean z) {
        ((Checkable) getView(i2)).setChecked(z);
        return this;
    }

    public ViewHolder setImageBitmap(int i2, Bitmap bitmap) {
        ((ImageView) getView(i2)).setImageBitmap(bitmap);
        return this;
    }

    public ViewHolder setImageDrawable(int i2, Drawable drawable) {
        ((ImageView) getView(i2)).setImageDrawable(drawable);
        return this;
    }

    public ViewHolder setImageResource(int i2, int i3) {
        ((ImageView) getView(i2)).setImageResource(i3);
        return this;
    }

    public ViewHolder setMax(int i2, int i3) {
        ((ProgressBar) getView(i2)).setMax(i3);
        return this;
    }

    public ViewHolder setOnClickListener(int i2, View.OnClickListener onClickListener) {
        getView(i2).setOnClickListener(onClickListener);
        return this;
    }

    public ViewHolder setOnLongClickListener(int i2, View.OnLongClickListener onLongClickListener) {
        getView(i2).setOnLongClickListener(onLongClickListener);
        return this;
    }

    public ViewHolder setOnTouchListener(int i2, View.OnTouchListener onTouchListener) {
        getView(i2).setOnTouchListener(onTouchListener);
        return this;
    }

    public ViewHolder setProgress(int i2, int i3) {
        ((ProgressBar) getView(i2)).setProgress(i3);
        return this;
    }

    public ViewHolder setRating(int i2, float f2) {
        ((RatingBar) getView(i2)).setRating(f2);
        return this;
    }

    public ViewHolder setTag(int i2, Object obj) {
        getView(i2).setTag(obj);
        return this;
    }

    public ViewHolder setText(int i2, String str) {
        ((TextView) getView(i2)).setText(str);
        return this;
    }

    public ViewHolder setTextColor(int i2, int i3) {
        ((TextView) getView(i2)).setTextColor(i3);
        return this;
    }

    public ViewHolder setTextColorRes(int i2, int i3) {
        ((TextView) getView(i2)).setTextColor(this.mContext.getResources().getColor(i3));
        return this;
    }

    public ViewHolder setTypeface(Typeface typeface, int... iArr) {
        for (int i2 : iArr) {
            TextView textView = (TextView) getView(i2);
            textView.setTypeface(typeface);
            textView.setPaintFlags(textView.getPaintFlags() | 128);
        }
        return this;
    }

    public ViewHolder setVisible(int i2, boolean z) {
        getView(i2).setVisibility(z ? 0 : 8);
        return this;
    }

    public ViewHolder setProgress(int i2, int i3, int i4) {
        ProgressBar progressBar = (ProgressBar) getView(i2);
        progressBar.setMax(i4);
        progressBar.setProgress(i3);
        return this;
    }

    public ViewHolder setRating(int i2, float f2, int i3) {
        RatingBar ratingBar = (RatingBar) getView(i2);
        ratingBar.setMax(i3);
        ratingBar.setRating(f2);
        return this;
    }

    public ViewHolder setTag(int i2, int i3, Object obj) {
        getView(i2).setTag(i3, obj);
        return this;
    }
}
