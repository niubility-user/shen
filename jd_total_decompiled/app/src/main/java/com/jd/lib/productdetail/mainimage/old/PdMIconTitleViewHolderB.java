package com.jd.lib.productdetail.mainimage.old;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessServiceIconEntity;
import com.jd.lib.productdetail.mainimage.R;

/* loaded from: classes15.dex */
public class PdMIconTitleViewHolderB extends RecyclerView.ViewHolder {
    public TextView a;
    public TextView b;

    /* renamed from: c  reason: collision with root package name */
    public TextView f5038c;
    public ImageView d;

    public PdMIconTitleViewHolderB(Context context, View view, boolean z) {
        super(view);
        this.a = (TextView) view.findViewById(R.id.pd_service_title);
        this.b = (TextView) view.findViewById(R.id.pd_service_title_text);
        this.f5038c = (TextView) view.findViewById(R.id.pd_service_content_text);
        this.d = (ImageView) view.findViewById(R.id.iv_arrow);
        b(context, z);
    }

    public final void b(Context context, boolean z) {
        this.a.setTextColor(ContextCompat.getColor(context, z ? R.color.lib_pd_image_color_ececec : R.color.lib_pd_image_black_262626));
        int color = ContextCompat.getColor(context, z ? R.color.lib_pd_image_848383 : R.color.lib_pd_image_black_8C8C8C);
        this.b.setTextColor(color);
        this.f5038c.setTextColor(color);
        this.d.setImageResource(z ? com.jd.lib.productdetail.core.R.drawable.lib_pd_core_right_arrow_dark_theme : com.jd.lib.productdetail.core.R.drawable.lib_pd_core_arrow_r);
    }

    public void c(WareBusinessServiceIconEntity wareBusinessServiceIconEntity, String str) {
        if (!TextUtils.isEmpty(wareBusinessServiceIconEntity.text)) {
            this.a.setText(wareBusinessServiceIconEntity.text);
        }
        if (!TextUtils.isEmpty(wareBusinessServiceIconEntity.jumpUrl)) {
            if (!TextUtils.isEmpty(wareBusinessServiceIconEntity.guideText)) {
                this.b.setText(wareBusinessServiceIconEntity.guideText);
            } else {
                this.b.setText(str);
            }
            this.b.setVisibility(0);
            this.d.setVisibility(0);
        } else {
            this.d.setVisibility(8);
            this.b.setVisibility(8);
        }
        if (!TextUtils.isEmpty(wareBusinessServiceIconEntity.tip)) {
            this.f5038c.setText(wareBusinessServiceIconEntity.tip);
            this.f5038c.setVisibility(0);
            return;
        }
        this.f5038c.setVisibility(8);
    }
}
