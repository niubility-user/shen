package com.jd.lib.productdetail.mainimage.old;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.productdetail.core.views.PdAutoChangeTextSize;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.old.PdMImageRcServiceIconAdapterB;

/* loaded from: classes15.dex */
public class PdMTrustTitleHolderB extends RecyclerView.ViewHolder {
    public PdMImageRcServiceIconAdapterB.d a;
    public SimpleDraweeView b;

    /* renamed from: c  reason: collision with root package name */
    public TextView f5126c;
    public ImageView d;

    /* renamed from: e  reason: collision with root package name */
    public PdAutoChangeTextSize f5127e;

    /* renamed from: f  reason: collision with root package name */
    public boolean f5128f;

    public PdMTrustTitleHolderB(Context context, View view, boolean z, PdMImageRcServiceIconAdapterB.d dVar, boolean z2) {
        super(view);
        this.a = dVar;
        this.b = (SimpleDraweeView) view.findViewById(R.id.pd_service_title_icon);
        this.f5126c = (TextView) view.findViewById(R.id.pd_service_title_text);
        this.d = (ImageView) view.findViewById(R.id.iv_arrow);
        this.f5127e = (PdAutoChangeTextSize) view.findViewById(R.id.lib_pd_left_tv);
        this.f5128f = z2;
        view.setBackgroundColor(ContextCompat.getColor(context, z ? R.color.lib_pd_image_color_1d1b1b : R.color.lib_pd_image_white));
        b(context, z);
    }

    public void a() {
        this.b.setVisibility(8);
        this.f5126c.setVisibility(8);
    }

    public final void b(Context context, boolean z) {
        int color = ContextCompat.getColor(context, z ? R.color.lib_pd_image_848383 : R.color.lib_pd_image_black_8C8C8C);
        int color2 = ContextCompat.getColor(context, z ? R.color.lib_pd_image_color_ececec : R.color.lib_pd_image_color_1A1A1A);
        this.f5126c.setTextColor(color);
        PdAutoChangeTextSize pdAutoChangeTextSize = this.f5127e;
        if (pdAutoChangeTextSize != null) {
            pdAutoChangeTextSize.setTextColor(color2);
        }
        this.d.setImageResource(z ? com.jd.lib.productdetail.core.R.drawable.lib_pd_core_right_arrow_dark_theme : com.jd.lib.productdetail.core.R.drawable.lib_pd_core_arrow_r);
    }
}
