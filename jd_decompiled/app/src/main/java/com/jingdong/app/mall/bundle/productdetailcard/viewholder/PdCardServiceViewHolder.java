package com.jingdong.app.mall.bundle.productdetailcard.viewholder;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.bundle.productdetailcard.R;
import com.jingdong.app.mall.bundle.productdetailcard.entity.PdCardFloorInfo;
import com.jingdong.app.mall.bundle.productdetailcard.entity.PdCardServiceData;

/* loaded from: classes3.dex */
public class PdCardServiceViewHolder extends RecyclerView.ViewHolder implements a {

    /* renamed from: g  reason: collision with root package name */
    private final TextView f8278g;

    /* renamed from: h  reason: collision with root package name */
    private final TextView f8279h;

    public PdCardServiceViewHolder(@NonNull View view) {
        super(view);
        this.f8278g = (TextView) view.findViewById(R.id.productdetailcard_service_title_tv);
        this.f8279h = (TextView) view.findViewById(R.id.productdetailcard_service_content_tv);
    }

    @Override // com.jingdong.app.mall.bundle.productdetailcard.viewholder.a
    public void a(PdCardFloorInfo pdCardFloorInfo) {
        if (pdCardFloorInfo != null) {
            if (!TextUtils.isEmpty(pdCardFloorInfo.title)) {
                this.f8278g.setText(pdCardFloorInfo.title);
            }
            Object obj = pdCardFloorInfo.data;
            if (obj instanceof JDJSONObject) {
                PdCardServiceData pdCardServiceData = (PdCardServiceData) JDJSON.parseObject(((JDJSONObject) obj).toJSONString(), PdCardServiceData.class);
                if (pdCardServiceData != null && !TextUtils.isEmpty(pdCardServiceData.serviceInfo)) {
                    this.f8279h.setText(pdCardServiceData.serviceInfo);
                    this.f8279h.setVisibility(0);
                    return;
                }
                this.f8279h.setVisibility(8);
            }
        }
    }
}
