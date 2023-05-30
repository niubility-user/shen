package com.jd.lib.productdetail.mainimage.old;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.productdetail.mainimage.R;

/* loaded from: classes15.dex */
public class PdMIconListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    /* renamed from: g  reason: collision with root package name */
    public SimpleDraweeView f5034g;

    /* renamed from: h  reason: collision with root package name */
    public TextView f5035h;

    /* renamed from: i  reason: collision with root package name */
    public TextView f5036i;

    /* renamed from: j  reason: collision with root package name */
    public SimpleDraweeView f5037j;

    public PdMIconListViewHolder(Context context, View view, boolean z) {
        super(view);
        view.setOnClickListener(this);
        this.f5035h = (TextView) view.findViewById(R.id.detail_service_item_short_name);
        this.f5034g = (SimpleDraweeView) view.findViewById(R.id.detail_service_item_img);
        this.f5036i = (TextView) view.findViewById(R.id.detail_service_item_name);
        this.f5037j = (SimpleDraweeView) view.findViewById(R.id.pd_service_arrow_img);
        b(context, z);
    }

    public final void b(Context context, boolean z) {
        this.f5035h.setTextColor(ContextCompat.getColor(context, z ? R.color.lib_pd_image_color_ececec : R.color.lib_pd_image_color_1D1E1E));
        this.f5036i.setTextColor(ContextCompat.getColor(context, z ? R.color.lib_pd_image_848383 : R.color.lib_pd_image_black_8C8C8C));
        this.f5037j.setImageResource(z ? com.jd.lib.productdetail.core.R.drawable.lib_pd_core_right_arrow_dark_theme : com.jd.lib.productdetail.core.R.drawable.lib_pd_core_arrow_r);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }
}
