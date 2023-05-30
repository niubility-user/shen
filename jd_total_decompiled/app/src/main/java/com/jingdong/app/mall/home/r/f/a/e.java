package com.jingdong.app.mall.home.r.f.a;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Pair;
import com.jingdong.app.mall.home.floor.model.entity.DynamicIconEntity;
import com.jingdong.app.mall.home.floor.presenter.engine.DynamicIconEngine;
import com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter;
import com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI;
import com.jingdong.app.mall.home.floor.view.view.DynamicIcon;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.jdsdk.utils.Md5Encrypt;

/* loaded from: classes4.dex */
public class e extends b<DynamicIconEntity, DynamicIconEngine, DynamicIcon> implements ICursorContentViewPresenter {

    /* renamed from: h  reason: collision with root package name */
    private volatile String f10741h = "";

    /* renamed from: i  reason: collision with root package name */
    private Pair<String, Bitmap> f10742i;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.t.a {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        a(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        @Override // com.jingdong.app.mall.home.t.a
        public void onBitmapWithUiNull(Bitmap bitmap) {
            if (e.this.f10741h.compareToIgnoreCase(this.a) != 0) {
                return;
            }
            try {
                e.this.k0();
                if (bitmap == null) {
                    e.this.j0(null, null);
                } else {
                    e.this.f10742i = new Pair(this.b, bitmap);
                    bitmap.setHasAlpha(true);
                    e.this.i0(this.a, bitmap);
                }
            } catch (Throwable th) {
                com.jingdong.app.mall.home.o.a.f.s0(this, th);
            }
        }
    }

    private Bitmap V(String str) {
        Object obj;
        Pair<String, Bitmap> pair = this.f10742i;
        if (pair == null || (obj = pair.second) == null || ((Bitmap) obj).isRecycled()) {
            return null;
        }
        if (TextUtils.isEmpty(str) || str.equals(this.f10742i.first)) {
            return (Bitmap) this.f10742i.second;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i0(String str, Bitmap bitmap) {
        DynamicIcon dynamicIcon = (DynamicIcon) c();
        if (dynamicIcon == null) {
            return;
        }
        dynamicIcon.onLoadingBgComplete(str, bitmap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j0(String str, JDFailReason jDFailReason) {
        DynamicIcon dynamicIcon = (DynamicIcon) c();
        if (dynamicIcon == null) {
            return;
        }
        dynamicIcon.onLoadingBgFailed(str, jDFailReason);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void k0() {
        Object obj;
        Pair<String, Bitmap> pair = this.f10742i;
        if (pair != null && (obj = pair.second) != null && !((Bitmap) obj).isRecycled()) {
            ((Bitmap) this.f10742i.second).recycle();
        }
    }

    private void l0() {
        if (((DynamicIcon) c()) == null) {
            return;
        }
        String b = com.jingdong.app.mall.home.m.a.b(((DynamicIconEntity) this.d).getBgUrl());
        this.f10741h = b;
        if (!TextUtils.isEmpty(this.f10741h)) {
            String md5 = Md5Encrypt.md5(this.f10741h);
            Bitmap V = V(md5);
            if (V != null && !V.isRecycled()) {
                V.setHasAlpha(true);
                i0(b, V);
                return;
            }
            com.jingdong.app.mall.home.floor.ctrl.f.i(b, new a(b, md5));
        } else if (this.f10741h.compareToIgnoreCase(b) != 0) {
        } else {
            k0();
            j0(null, null);
        }
    }

    public int U() {
        return ((DynamicIconEntity) this.d).getBgColor();
    }

    public float W() {
        return com.jingdong.app.mall.home.floor.common.d.d(g0().clipDegree);
    }

    public int X(int i2) {
        if (g0().isLineType) {
            return ((DynamicIconEntity) this.d).getItemCount();
        }
        return ((DynamicIconEntity) this.d).getIconSizePerPage(i2);
    }

    public int Y() {
        float itemCount = ((DynamicIconEntity) this.d).getItemCount() / g0().iconCountPerPage;
        if (itemCount <= 1.0f) {
            return -1;
        }
        if (itemCount <= 2.0f) {
            return 0;
        }
        return itemCount <= 3.0f ? 1 : 2;
    }

    public com.jingdong.app.mall.home.r.e.k.b Z(int i2) {
        return ((DynamicIconEntity) this.d).getItemByPos(i2);
    }

    public com.jingdong.app.mall.home.r.e.k.b a0(int i2, int i3) {
        return ((DynamicIconEntity) this.d).getItemByPos(i2, i3);
    }

    public int b0() {
        return ((DynamicIconEntity) this.d).getItemCount();
    }

    public int c0() {
        DynamicIconEntity.ViewConfig g0 = g0();
        if (g0.isLineType) {
            return g0.iconContainerWidth;
        }
        return ((750 - g0.containerLPadding) - g0.containerRPadding) / g0.iconCountPerRow;
    }

    public int d0() {
        return com.jingdong.app.mall.home.floor.common.d.d(c0());
    }

    public int e0() {
        return ((DynamicIconEntity) this.d).getPageCount();
    }

    public int f0() {
        return ((DynamicIconEntity) this.d).getTextColor();
    }

    public DynamicIconEntity.ViewConfig g0() {
        return ((DynamicIconEntity) this.d).getViewConfig();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getBannerCursorColor() {
        return ((DynamicIconEntity) this.d).getPointerUnselectColor();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorHeight() {
        DynamicIconEntity.ViewConfig g0 = g0();
        if (g0 == null) {
            return 0;
        }
        return com.jingdong.app.mall.home.floor.common.d.d(g0.indicatorHeight);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorMarginBottom() {
        DynamicIconEntity.ViewConfig g0 = g0();
        if (g0 == null) {
            return 0;
        }
        return com.jingdong.app.mall.home.floor.common.d.d(g0.indicatorBottomMargin);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorSelectColor() {
        return ((DynamicIconEntity) this.d).getPointerSelectedColor();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorSpace() {
        DynamicIconEntity.ViewConfig g0 = g0();
        if (g0 == null) {
            return 0;
        }
        return com.jingdong.app.mall.home.floor.common.d.d(g0.cursorSpaceWidth);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorSpaceColor() {
        return ((DynamicIconEntity) this.d).getPointerSpaceColor();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getCursorWidthUnSelect() {
        DynamicIconEntity.ViewConfig g0 = g0();
        if (g0 == null) {
            return 0;
        }
        return com.jingdong.app.mall.home.floor.common.d.d(g0.cursorUnSelectWidth);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter
    public int getSelectWidth() {
        DynamicIconEntity.ViewConfig g0 = g0();
        if (g0 == null) {
            return 0;
        }
        return com.jingdong.app.mall.home.floor.common.d.d(g0.cursorSelectWidth);
    }

    public boolean h0() {
        return ((DynamicIconEntity) this.d).isDataFromCache();
    }

    public boolean m0() {
        return ((DynamicIconEntity) this.d).useClip();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.r.f.a.b
    public void v(com.jingdong.app.mall.home.r.e.h hVar, com.jingdong.app.mall.home.r.e.d dVar) {
        IMallFloorUI iMallFloorUI = (IMallFloorUI) c();
        if (iMallFloorUI == null) {
            return;
        }
        if (((DynamicIconEntity) this.d).isValid()) {
            iMallFloorUI.onSetVisible(true);
            iMallFloorUI.onRefreshView();
        } else {
            iMallFloorUI.onSetVisible(false);
        }
        l0();
    }
}
