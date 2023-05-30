package com.jingdong.app.mall.home.floor.view.view.title;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.NinePatch;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Looper;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;
import com.jingdong.app.mall.home.floor.view.view.JDViewFlipper;
import com.jingdong.app.mall.home.o.a.b;
import com.jingdong.app.mall.home.t.a;

/* loaded from: classes4.dex */
public class TitleStretchLabel extends RelativeLayout {
    private final int CAROUSEL_INTERVAL;
    private final int LEFT_MARGIN;
    private final int RIGHT_MARGIN;
    private final int TARGET_HEIGHT;
    private final int defaultBgResId;
    private final f flipperSize;
    private boolean lastLoadSuccess;
    private String lastLoadTag;
    private final JDViewFlipper mFlipper;
    private final b mFlipperRunnable;
    private final Handler mHandler;
    private NinePatch mNinePatch;
    private final RectF mRectF;
    private int preHeight;

    public TitleStretchLabel(Context context) {
        super(context);
        this.CAROUSEL_INTERVAL = 3000;
        this.defaultBgResId = R.drawable.home_title_emo_logo_bg;
        this.TARGET_HEIGHT = 52;
        this.RIGHT_MARGIN = 28;
        this.LEFT_MARGIN = 66;
        this.mRectF = new RectF();
        f fVar = new f(-2, -1);
        this.flipperSize = fVar;
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mFlipperRunnable = new b() { // from class: com.jingdong.app.mall.home.floor.view.view.title.TitleStretchLabel.2
            @Override // com.jingdong.app.mall.home.o.a.b
            protected void safeRun() {
                TitleStretchLabel.this.mHandler.removeCallbacks(this);
                TitleStretchLabel.this.mFlipper.showNext();
                TitleStretchLabel.this.mHandler.postDelayed(this, 3000L);
            }
        };
        JDViewFlipper jDViewFlipper = new JDViewFlipper(context);
        this.mFlipper = jDViewFlipper;
        jDViewFlipper.setInAnimation(getContext(), R.anim.home_title_search_box_anim_in);
        jDViewFlipper.setOutAnimation(getContext(), R.anim.home_title_search_box_anim_out);
        fVar.E(66, 0, 28, 0);
        addView(jDViewFlipper, fVar.u(jDViewFlipper));
        initFlipper();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean buildNinePatch(Bitmap bitmap) {
        if (bitmap != null && bitmap.getWidth() > 3 && bitmap.getHeight() > 3) {
            Bitmap x = e.x(bitmap, d.d(52));
            this.preHeight = d.d(52);
            byte[] y = e.y(x, 0.5f);
            if (y != null) {
                try {
                    this.mNinePatch = new NinePatch(x, y, null);
                    return true;
                } catch (Throwable th) {
                    th.printStackTrace();
                    this.mNinePatch = null;
                    return false;
                }
            }
            return false;
        }
        this.mNinePatch = null;
        return false;
    }

    private void initFlipper() {
        for (int i2 = 0; i2 < 2; i2++) {
            GradientTextView gradientTextView = new GradientTextView(getContext());
            gradientTextView.setFocusable(false);
            gradientTextView.setTextSize(0, d.d(24));
            gradientTextView.setTextColor(-1);
            gradientTextView.setGravity(16);
            gradientTextView.getPaint().setFakeBoldText(true);
            gradientTextView.setBackgroundColor(0);
            gradientTextView.setSingleLine(true);
            gradientTextView.setEllipsize(TextUtils.TruncateAt.END);
            gradientTextView.setPadding(0, -3, 0, -3);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -1);
            layoutParams.gravity = 17;
            this.mFlipper.addView(gradientTextView, layoutParams);
        }
    }

    private boolean isWidthChanged() {
        return d.d(52) != this.preHeight;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void useDefaultBg() {
        this.lastLoadTag = null;
        this.lastLoadSuccess = false;
        buildNinePatch(com.jingdong.app.mall.home.floor.ctrl.f.c(getResources(), this.defaultBgResId));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        if (this.mNinePatch != null) {
            this.mRectF.set(0.0f, 0.0f, getWidth(), getHeight());
            try {
                this.mNinePatch.draw(canvas, this.mRectF);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        super.dispatchDraw(canvas);
    }

    public void setBg(final String str) {
        boolean z = (TextUtils.isEmpty(str) || str.toLowerCase().endsWith(".gif")) ? false : true;
        if (z && TextUtils.equals(str, this.lastLoadTag) && this.lastLoadSuccess && !isWidthChanged()) {
            return;
        }
        this.lastLoadSuccess = false;
        if (!z) {
            useDefaultBg();
        } else {
            com.jingdong.app.mall.home.floor.ctrl.f.i(str, new a() { // from class: com.jingdong.app.mall.home.floor.view.view.title.TitleStretchLabel.1
                @Override // com.jingdong.app.mall.home.t.a
                public void onBitmapWithUiNull(Bitmap bitmap) {
                    if (!TitleStretchLabel.this.buildNinePatch(bitmap)) {
                        TitleStretchLabel.this.useDefaultBg();
                        return;
                    }
                    TitleStretchLabel.this.lastLoadTag = str;
                    TitleStretchLabel.this.lastLoadSuccess = true;
                }
            });
        }
    }

    public void setText(String str, String str2, int i2) {
        this.mHandler.removeCallbacks(this.mFlipperRunnable);
        View currentView = this.mFlipper.getCurrentView();
        com.jingdong.app.mall.home.o.a.f.n(currentView);
        GradientTextView gradientTextView = (GradientTextView) currentView;
        int displayedChild = this.mFlipper.getDisplayedChild() + 1;
        if (displayedChild >= 2) {
            displayedChild = 0;
        }
        View childAt = this.mFlipper.getChildAt(displayedChild);
        com.jingdong.app.mall.home.o.a.f.n(childAt);
        GradientTextView gradientTextView2 = (GradientTextView) childAt;
        String str3 = !TextUtils.isEmpty(str) ? str : !TextUtils.isEmpty(str2) ? str2 : "";
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            str2 = "";
        }
        int d = i2 - d.d(94);
        int d2 = d.d(93);
        String l2 = com.jingdong.app.mall.home.o.a.f.l(gradientTextView, d, str3);
        String l3 = com.jingdong.app.mall.home.o.a.f.l(gradientTextView, d, str2);
        TextPaint paint = gradientTextView.getPaint();
        if (paint != null) {
            d2 = Math.max(((int) paint.measureText(l3)) + 10, Math.max(((int) paint.measureText(l2)) + 10, d2));
        }
        f.b(this.mFlipper, d2, d.d(52));
        gradientTextView.setText(l2);
        gradientTextView2.setText(l3);
        if (TextUtils.isEmpty(l2) || TextUtils.isEmpty(l3)) {
            return;
        }
        this.mHandler.postDelayed(this.mFlipperRunnable, 3000L);
    }

    public void setTextColor(String str) {
        int[] p = m.p(str, -1, true);
        for (int i2 = 0; i2 < 2; i2++) {
            View childAt = this.mFlipper.getChildAt(i2);
            com.jingdong.app.mall.home.o.a.f.n(childAt);
            ((GradientTextView) childAt).setTextGradient(GradientTextView.GradientType.LeftToRight, p);
        }
    }
}
