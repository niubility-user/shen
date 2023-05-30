package com.jingdong.app.mall.home.floor.view.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.View;
import com.jingdong.app.mall.home.e;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.model.entity.DynamicIconEntity;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor;
import com.jingdong.app.mall.home.floor.view.widget.c;
import com.jingdong.app.mall.home.n.h.g;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.r.f.a.e;
import com.jingdong.app.util.image.assist.JDFailReason;

/* loaded from: classes4.dex */
public abstract class DynamicIcon extends BaseMallFloor<e> {
    private Drawable bgDrawable;
    private String lastConfigId;
    private final GradientDrawable mBgShadeDrawable;
    private final Paint mBgShadePaint;
    private final Path mBgShadePath;

    public DynamicIcon(Context context) {
        super(context);
        this.lastConfigId = "";
        this.mBgShadePaint = new Paint(1);
        this.mBgShadePath = new Path();
        this.bgDrawable = null;
        this.mBgShadeDrawable = new GradientDrawable() { // from class: com.jingdong.app.mall.home.floor.view.view.DynamicIcon.1
            {
                DynamicIcon.this = this;
            }

            @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
            public void draw(Canvas canvas) {
                super.draw(canvas);
                canvas.drawPath(DynamicIcon.this.mBgShadePath, DynamicIcon.this.mBgShadePaint);
            }
        };
    }

    private boolean isViewConfigChanged(String str) {
        return (TextUtils.isEmpty(str) || TextUtils.equals(str, this.lastConfigId)) ? false : true;
    }

    private void registerTextScaleMode() {
        if (((e) this.mPresenter).g0().iconTextScaleSwitch) {
            com.jingdong.app.mall.home.e.b().a(new e.b() { // from class: com.jingdong.app.mall.home.floor.view.view.DynamicIcon.2
                {
                    DynamicIcon.this = this;
                }

                @Override // com.jingdong.app.mall.home.e.b
                public int getTextSize() {
                    return ((com.jingdong.app.mall.home.r.f.a.e) ((BaseMallColorFloor) DynamicIcon.this).mPresenter).g0().iconTextSize;
                }

                @Override // com.jingdong.app.mall.home.e.b
                public void onTextScaleModeChanged(int i2) {
                    if (((com.jingdong.app.mall.home.r.f.a.e) ((BaseMallColorFloor) DynamicIcon.this).mPresenter).g0().iconTextScaleSwitch) {
                        DynamicIcon.this.updateItemTextSize(i2);
                    }
                }
            });
        }
    }

    private void setBgShadeColor(int i2, int i3) {
        this.mBgShadePath.reset();
        this.mBgShadePaint.setColor(i3);
        g.g(i2, 0.0f, d.f9279g - i2, getLayoutHeight(), ((com.jingdong.app.mall.home.r.f.a.e) this.mPresenter).W(), this.mBgShadePath);
    }

    public void doClip(View view) {
        if (((com.jingdong.app.mall.home.r.f.a.e) this.mPresenter).m0()) {
            int d = d.d(((com.jingdong.app.mall.home.r.f.a.e) this.mPresenter).g0().clipPadding);
            com.jingdong.app.mall.home.n.h.e.f(view, true, new Rect(d, 0, d, 0), 0);
            return;
        }
        com.jingdong.app.mall.home.n.h.e.i(view);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    protected void drawBackground(Canvas canvas) {
        try {
            Drawable drawable = this.bgDrawable;
            if (drawable != null) {
                drawable.draw(canvas);
            }
        } catch (Exception e2) {
            f.s0(this, e2);
        }
    }

    protected abstract void onBindView();

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    protected void onLoadingBgCompleteOnMainThread(String str, Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled()) {
            c cVar = new c(bitmap);
            this.bgDrawable = cVar;
            cVar.setBounds(0, 0, d.f9279g, getLayoutHeight());
            return;
        }
        onLoadingBgFailed(null, null);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    protected void onLoadingBgFailedOnMainThread(String str, JDFailReason jDFailReason) {
        DynamicIconEntity.ViewConfig g0 = ((com.jingdong.app.mall.home.r.f.a.e) this.mPresenter).g0();
        int i2 = g0 != null ? g0.clipPadding : 0;
        if (((com.jingdong.app.mall.home.r.f.a.e) this.mPresenter).U() != 0) {
            setBgShadeColor(((com.jingdong.app.mall.home.r.f.a.e) this.mPresenter).m0() ? d.d(i2) : 0, ((com.jingdong.app.mall.home.r.f.a.e) this.mPresenter).U());
            this.bgDrawable = this.mBgShadeDrawable;
            return;
        }
        this.bgDrawable = null;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public void onRefreshViewOnMainThread() {
        String str = ((com.jingdong.app.mall.home.r.f.a.e) this.mPresenter).g0().configId;
        if (isViewConfigChanged(str)) {
            onViewConfigChanged();
        }
        this.lastConfigId = str;
        registerTextScaleMode();
        onBindView();
    }

    protected abstract void onViewConfigChanged();

    protected void updateItemTextSize(int i2) {
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    protected boolean useRoundMarginColor() {
        com.jingdong.app.mall.home.r.e.d dVar = this.mFloorBindElement;
        return dVar != null && dVar.t > 1;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public com.jingdong.app.mall.home.r.f.a.e createPresenter() {
        return new com.jingdong.app.mall.home.r.f.a.e();
    }
}
