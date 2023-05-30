package com.jingdong.app.mall.home.floor.view.view.title.tab;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.text.TextUtils;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.ctrl.h;
import com.jingdong.app.mall.home.o.a.b;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.t.a;
import java.lang.ref.SoftReference;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public abstract class TitleTabSkin {
    private String currentSkinUrl;
    private final RectF mBgRect = new RectF();
    private final Matrix mTabMatrix = new Matrix();
    private final ConcurrentHashMap<String, SoftReference<Bitmap>> sSkinCache = new ConcurrentHashMap<>();
    private int skinPosition;

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshTabSkinBitmap(final Bitmap bitmap) {
        if (f.p0()) {
            f.E0(new b() { // from class: com.jingdong.app.mall.home.floor.view.view.title.tab.TitleTabSkin.1
                @Override // com.jingdong.app.mall.home.o.a.b
                protected void safeRun() {
                    TitleTabSkin.this.refreshTabSkinBitmapOnMainThread(bitmap);
                }
            });
        } else {
            refreshTabSkinBitmapOnMainThread(bitmap);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshTabSkinBitmapOnMainThread(Bitmap bitmap) {
        boolean z = true;
        Bitmap bitmap2 = null;
        if (getCurrentPosition() == 0) {
            bitmap = null;
        }
        if (bitmap == null || bitmap.isRecycled()) {
            z = false;
        } else {
            this.mBgRect.bottom = bitmap.getHeight();
            this.mBgRect.right = bitmap.getWidth();
            float f2 = this.mBgRect.right / d.f9279g;
            float K = h.N().K(-1) + d.d(20);
            float min = Math.min(K - (this.mBgRect.bottom / f2), 0.0f);
            this.mTabMatrix.reset();
            this.mTabMatrix.setRectToRect(this.mBgRect, new RectF(0.0f, min, d.f9279g, K), Matrix.ScaleToFit.FILL);
            bitmap2 = bitmap;
        }
        notifyTabSkinBitmap(z);
        h.N().y0(bitmap2, this.mTabMatrix);
    }

    protected abstract int getCurrentPosition();

    public void loadHourlyGoHeadSkin(String str) {
        if (this.skinPosition == -1) {
            loadTabSkinBitmap(-1, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void loadTabSkinBitmap(int i2, final String str) {
        this.currentSkinUrl = str;
        this.skinPosition = i2;
        if (TextUtils.isEmpty(str)) {
            refreshTabSkinBitmap(null);
            return;
        }
        SoftReference<Bitmap> softReference = this.sSkinCache.get(str);
        Bitmap bitmap = softReference != null ? softReference.get() : null;
        if (bitmap != null && !bitmap.isRecycled()) {
            refreshTabSkinBitmap(bitmap);
            return;
        }
        refreshTabSkinBitmap(null);
        com.jingdong.app.mall.home.floor.ctrl.f.i(str, new a() { // from class: com.jingdong.app.mall.home.floor.view.view.title.tab.TitleTabSkin.2
            @Override // com.jingdong.app.mall.home.t.a
            public void onBitmapWithUiNull(Bitmap bitmap2) {
                if (TextUtils.equals(str, TitleTabSkin.this.currentSkinUrl)) {
                    if (bitmap2 == null || bitmap2.isRecycled()) {
                        TitleTabSkin.this.refreshTabSkinBitmap(null);
                        return;
                    }
                    TitleTabSkin.this.sSkinCache.put(str, new SoftReference(bitmap2));
                    TitleTabSkin.this.refreshTabSkinBitmap(bitmap2);
                }
            }
        });
    }

    protected abstract void notifyTabSkinBitmap(boolean z);
}
