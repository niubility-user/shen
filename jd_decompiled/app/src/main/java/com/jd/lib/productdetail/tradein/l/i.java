package com.jd.lib.productdetail.tradein.l;

import android.graphics.Bitmap;
import com.jd.lib.productdetail.tradein.widget.TradeInTitle;

/* loaded from: classes16.dex */
public class i implements Runnable {

    /* renamed from: g */
    public final /* synthetic */ Bitmap f5396g;

    /* renamed from: h */
    public final /* synthetic */ TradeInTitle.a f5397h;

    public i(TradeInTitle.a aVar, Bitmap bitmap) {
        this.f5397h = aVar;
        this.f5396g = bitmap;
    }

    @Override // java.lang.Runnable
    public void run() {
        Bitmap bitmap;
        if (!TradeInTitle.a(TradeInTitle.this) || (bitmap = this.f5396g) == null || bitmap.isRecycled() || this.f5396g.getWidth() <= 0) {
            return;
        }
        float width = this.f5396g.getWidth();
        float height = this.f5396g.getHeight();
        if (width != 0.0f) {
            float f2 = height / width;
            float width2 = TradeInTitle.this.f5647g.getWidth();
            float f3 = f2 * width2;
            if (width2 <= 0.0f || f3 <= 0.0f) {
                return;
            }
            try {
                TradeInTitle.this.f5647g.setImageBitmap(Bitmap.createScaledBitmap(this.f5396g, (int) width2, (int) f3, true));
            } catch (Exception unused) {
            }
        }
    }
}
