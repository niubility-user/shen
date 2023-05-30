package com.jd.lib.productdetail.mainimage.holder.comment;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.LinearLayout;
import com.facebook.drawee.drawable.RoundedBitmapDrawable;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener;

/* loaded from: classes15.dex */
public class m extends JDSimpleImageLoadingListener {

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ SimpleDraweeView f4781g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ LinearLayout.LayoutParams f4782h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ int f4783i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ PdMImageZcxView f4784j;

    public m(PdMImageZcxView pdMImageZcxView, SimpleDraweeView simpleDraweeView, LinearLayout.LayoutParams layoutParams, int i2) {
        this.f4784j = pdMImageZcxView;
        this.f4781g = simpleDraweeView;
        this.f4782h = layoutParams;
        this.f4783i = i2;
    }

    @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
    public void onLoadingComplete(String str, View view, Bitmap bitmap) {
        if (this.f4784j.getResources() == null || this.f4781g == null) {
            return;
        }
        RoundedBitmapDrawable roundedBitmapDrawable = new RoundedBitmapDrawable(this.f4784j.getResources(), bitmap);
        float f2 = this.f4784j.f4760j;
        roundedBitmapDrawable.setRadii(new float[]{f2, f2, f2, f2, f2, f2, f2, f2});
        this.f4781g.setImageDrawable(roundedBitmapDrawable);
        PdMImageZcxView pdMImageZcxView = this.f4784j;
        if (pdMImageZcxView.f4763m) {
            pdMImageZcxView.post(new n(pdMImageZcxView, this.f4781g, this.f4782h, this.f4783i));
            this.f4784j.f4763m = false;
        }
    }
}
