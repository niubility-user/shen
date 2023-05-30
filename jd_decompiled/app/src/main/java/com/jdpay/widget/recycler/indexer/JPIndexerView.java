package com.jdpay.widget.recycler.indexer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jdpay.system.SystemInfo;

/* loaded from: classes18.dex */
public class JPIndexerView extends View {
    protected int charGap;
    protected float charHeight;
    protected float[] charWidths;
    protected float charsHeight;
    protected CharSequence indexs;
    protected boolean isBoldHighlight;
    protected float maxCharWidth;
    protected final Paint.FontMetricsInt metrics;
    protected OnItemSelectListener onItemSelectListener;
    protected final RecyclerView.OnScrollListener onRecyclerViewScrollListener;
    protected final TextPaint paint;
    protected char selected;
    protected int textColor;
    protected int textHighlightColor;
    protected int textSize;

    /* loaded from: classes18.dex */
    public interface OnItemSelectListener {
        void onSelected(char c2);
    }

    /* loaded from: classes18.dex */
    protected class OnRecyclerViewScrollListener extends RecyclerView.OnScrollListener {
        protected OnRecyclerViewScrollListener() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(@NonNull RecyclerView recyclerView, int i2, int i3) {
            if ((recyclerView.getLayoutManager() instanceof LinearLayoutManager) && (recyclerView.getAdapter() instanceof JPIndexableAdapter)) {
                JPIndexableAdapter jPIndexableAdapter = (JPIndexableAdapter) recyclerView.getAdapter();
                int findFirstVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                if (findFirstVisibleItemPosition < 0 || findFirstVisibleItemPosition >= jPIndexableAdapter.getItemCount()) {
                    return;
                }
                JPIndexerView.this.setSelected(((JPIndexableBean) jPIndexableAdapter.get(findFirstVisibleItemPosition)).getIndexChar());
            }
        }
    }

    public JPIndexerView(Context context) {
        super(context);
        this.onRecyclerViewScrollListener = new OnRecyclerViewScrollListener();
        this.paint = new TextPaint(1);
        this.metrics = new Paint.FontMetricsInt();
        init(context);
    }

    private void measureText() {
        CharSequence charSequence = this.indexs;
        if (charSequence == null || charSequence.length() == 0) {
            return;
        }
        int length = this.indexs.length();
        float[] fArr = new float[length];
        TextPaint textPaint = this.paint;
        CharSequence charSequence2 = this.indexs;
        textPaint.getTextWidths(charSequence2, 0, charSequence2.length(), fArr);
        this.paint.getFontMetricsInt(this.metrics);
        Paint.FontMetricsInt fontMetricsInt = this.metrics;
        this.charHeight = fontMetricsInt.descent - fontMetricsInt.ascent;
        float f2 = 0.0f;
        float f3 = 0.0f;
        for (int i2 = 0; i2 < length; i2++) {
            f2 = Math.max(f2, fArr[i2]);
            f3 += this.charHeight + this.charGap;
        }
        this.charWidths = fArr;
        this.maxCharWidth = f2;
        this.charsHeight = f3 > 0.0f ? f3 - this.charGap : 0.0f;
    }

    public void attachRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(this.onRecyclerViewScrollListener);
    }

    public void detachRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.removeOnScrollListener(this.onRecyclerViewScrollListener);
    }

    protected void init(@NonNull Context context) {
        this.paint.density = SystemInfo.getDensity();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        CharSequence charSequence = this.indexs;
        if (charSequence == null || charSequence.length() == 0) {
            return;
        }
        int width = getWidth();
        int paddingTop = getPaddingTop();
        int paddingRight = ((width - (width - getPaddingRight())) + getPaddingLeft()) / 2;
        int i2 = 0;
        while (i2 < this.indexs.length()) {
            char charAt = this.indexs.charAt(i2);
            float f2 = paddingRight + ((this.maxCharWidth - this.charWidths[i2]) / 2.0f);
            float descent = ((paddingTop + this.charHeight) - this.paint.descent()) + ((this.charHeight + this.charGap) * i2);
            if (charAt == this.selected) {
                this.paint.setFakeBoldText(this.isBoldHighlight);
                this.paint.setColor(this.textHighlightColor);
            } else {
                this.paint.setColor(this.textColor);
                this.paint.setFakeBoldText(false);
            }
            int i3 = i2 + 1;
            canvas.drawText(this.indexs, i2, i3, f2, descent, this.paint);
            i2 = i3;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0063  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onMeasure(int r7, int r8) {
        /*
            r6 = this;
            int r0 = android.view.View.MeasureSpec.getMode(r7)
            int r1 = android.view.View.MeasureSpec.getMode(r8)
            int r7 = android.view.View.MeasureSpec.getSize(r7)
            int r8 = android.view.View.MeasureSpec.getSize(r8)
            r2 = 1073741824(0x40000000, float:2.0)
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r0 != r3) goto L30
            float r7 = (float) r7
            float r0 = r6.maxCharWidth
            int r4 = r6.getPaddingLeft()
            float r4 = (float) r4
            float r0 = r0 + r4
            int r4 = r6.getPaddingRight()
            float r4 = (float) r4
            float r0 = r0 + r4
            float r7 = java.lang.Math.min(r7, r0)
            double r4 = (double) r7
            double r4 = java.lang.Math.ceil(r4)
        L2e:
            int r7 = (int) r4
            goto L47
        L30:
            if (r0 != r2) goto L33
            goto L47
        L33:
            float r7 = r6.maxCharWidth
            int r0 = r6.getPaddingLeft()
            float r0 = (float) r0
            float r7 = r7 + r0
            int r0 = r6.getPaddingRight()
            float r0 = (float) r0
            float r7 = r7 + r0
            double r4 = (double) r7
            double r4 = java.lang.Math.ceil(r4)
            goto L2e
        L47:
            if (r1 != r3) goto L63
            float r8 = (float) r8
            float r0 = r6.charsHeight
            int r1 = r6.getPaddingTop()
            float r1 = (float) r1
            float r0 = r0 + r1
            int r1 = r6.getPaddingBottom()
            float r1 = (float) r1
            float r0 = r0 + r1
            float r8 = java.lang.Math.min(r8, r0)
            double r0 = (double) r8
            double r0 = java.lang.Math.ceil(r0)
        L61:
            int r8 = (int) r0
            goto L7a
        L63:
            if (r1 != r2) goto L66
            goto L7a
        L66:
            float r8 = r6.charsHeight
            int r0 = r6.getPaddingTop()
            float r0 = (float) r0
            float r8 = r8 + r0
            int r0 = r6.getPaddingBottom()
            float r0 = (float) r0
            float r8 = r8 + r0
            double r0 = (double) r8
            double r0 = java.lang.Math.ceil(r0)
            goto L61
        L7a:
            r6.setMeasuredDimension(r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jdpay.widget.recycler.indexer.JPIndexerView.onMeasure(int, int):void");
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        CharSequence charSequence;
        if (motionEvent.getAction() == 1) {
            int y = (int) (((motionEvent.getY() - getPaddingTop()) - getPaddingBottom()) / (this.charHeight + this.charGap));
            if (y >= 0 && (charSequence = this.indexs) != null && y < charSequence.length()) {
                this.selected = this.indexs.charAt(y);
            }
            invalidate();
            OnItemSelectListener onItemSelectListener = this.onItemSelectListener;
            if (onItemSelectListener != null) {
                onItemSelectListener.onSelected(this.selected);
            }
        }
        return true;
    }

    @MainThread
    public void setBoldHighlight(boolean z) {
        this.isBoldHighlight = z;
    }

    @MainThread
    public void setCharGap(int i2) {
        this.charGap = i2;
    }

    @MainThread
    public void setIndexs(CharSequence charSequence) {
        this.indexs = charSequence;
        if (charSequence != null && charSequence.length() > 0) {
            this.selected = charSequence.charAt(0);
        }
        measureText();
    }

    public void setOnItemSelectListener(@Nullable OnItemSelectListener onItemSelectListener) {
        this.onItemSelectListener = onItemSelectListener;
    }

    public void setSelected(char c2) {
        this.selected = c2;
        invalidate();
    }

    @MainThread
    public void setTextColor(int i2) {
        this.textColor = i2;
        this.paint.setTextSize(this.textSize);
        measureText();
    }

    @MainThread
    public void setTextHighlightColor(int i2) {
        this.textHighlightColor = i2;
    }

    @MainThread
    public void setTextSize(int i2) {
        this.textSize = i2;
        this.paint.setTextSize(i2);
        measureText();
    }

    public JPIndexerView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.onRecyclerViewScrollListener = new OnRecyclerViewScrollListener();
        this.paint = new TextPaint(1);
        this.metrics = new Paint.FontMetricsInt();
        init(context);
    }

    public JPIndexerView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.onRecyclerViewScrollListener = new OnRecyclerViewScrollListener();
        this.paint = new TextPaint(1);
        this.metrics = new Paint.FontMetricsInt();
        init(context);
    }

    @RequiresApi(api = 21)
    public JPIndexerView(Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.onRecyclerViewScrollListener = new OnRecyclerViewScrollListener();
        this.paint = new TextPaint(1);
        this.metrics = new Paint.FontMetricsInt();
        init(context);
    }
}
