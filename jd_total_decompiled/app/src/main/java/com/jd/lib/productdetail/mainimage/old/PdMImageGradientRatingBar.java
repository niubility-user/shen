package com.jd.lib.productdetail.mainimage.old;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.core.content.ContextCompat;
import com.jd.lib.productdetail.mainimage.R;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes15.dex */
public class PdMImageGradientRatingBar extends AppCompatRatingBar {

    /* renamed from: g  reason: collision with root package name */
    public ArrayList<Drawable> f5039g;

    /* renamed from: h  reason: collision with root package name */
    public boolean f5040h;

    public PdMImageGradientRatingBar(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.GradientRatingBar, i2, 0);
        this.f5040h = obtainStyledAttributes.getBoolean(R.styleable.GradientRatingBar_isElder, false);
        obtainStyledAttributes.recycle();
        String str = "GradientRatingBar isElder = " + this.f5040h;
        a();
    }

    private int b() {
        return ((int) ((c() * ((getWidth() - getPaddingLeft()) - getPaddingRight())) + 0.5f)) + getPaddingLeft();
    }

    private float c() {
        int max = getMax();
        if (max > 0) {
            return getProgress() / max;
        }
        return 0.0f;
    }

    public final void a() {
        ArrayList<Drawable> arrayList = new ArrayList<>();
        this.f5039g = arrayList;
        if (this.f5040h) {
            arrayList.add(ContextCompat.getDrawable(getContext(), com.jd.lib.productdetail.core.R.drawable.lib_pd_core_comment_ratingbar_small_progress_1_b_elder));
            this.f5039g.add(ContextCompat.getDrawable(getContext(), com.jd.lib.productdetail.core.R.drawable.lib_pd_core_comment_ratingbar_small_progress_2_b_elder));
            this.f5039g.add(ContextCompat.getDrawable(getContext(), com.jd.lib.productdetail.core.R.drawable.lib_pd_core_comment_ratingbar_small_progress_3_b_elder));
            this.f5039g.add(ContextCompat.getDrawable(getContext(), com.jd.lib.productdetail.core.R.drawable.lib_pd_core_comment_ratingbar_small_progress_4_b_elder));
            this.f5039g.add(ContextCompat.getDrawable(getContext(), com.jd.lib.productdetail.core.R.drawable.lib_pd_core_comment_ratingbar_small_progress_5_b_elder));
            return;
        }
        arrayList.add(ContextCompat.getDrawable(getContext(), com.jd.lib.productdetail.core.R.drawable.lib_pd_core_comment_ratingbar_small_progress_1_b));
        this.f5039g.add(ContextCompat.getDrawable(getContext(), com.jd.lib.productdetail.core.R.drawable.lib_pd_core_comment_ratingbar_small_progress_2_b));
        this.f5039g.add(ContextCompat.getDrawable(getContext(), com.jd.lib.productdetail.core.R.drawable.lib_pd_core_comment_ratingbar_small_progress_3_b));
        this.f5039g.add(ContextCompat.getDrawable(getContext(), com.jd.lib.productdetail.core.R.drawable.lib_pd_core_comment_ratingbar_small_progress_4_b));
        this.f5039g.add(ContextCompat.getDrawable(getContext(), com.jd.lib.productdetail.core.R.drawable.lib_pd_core_comment_ratingbar_small_progress_5_b));
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (getProgressDrawable() != null) {
            canvas.save();
            canvas.clipRect(0, 0, b(), getHeight());
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            ArrayList<Drawable> arrayList = this.f5039g;
            if (arrayList != null && !arrayList.isEmpty()) {
                Iterator<Drawable> it = this.f5039g.iterator();
                while (it.hasNext()) {
                    Drawable next = it.next();
                    next.setBounds(paddingLeft, paddingTop, next.getIntrinsicWidth() + paddingLeft, next.getIntrinsicHeight() + paddingTop);
                    paddingLeft += next.getIntrinsicWidth();
                    next.draw(canvas);
                }
            }
            canvas.restore();
        }
    }

    public PdMImageGradientRatingBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }
}
