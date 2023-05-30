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
import com.jingdong.app.mall.bundle.productdetailcard.entity.PdCardSelectedData;

/* loaded from: classes3.dex */
public class PdCardSelectedViewHolder extends RecyclerView.ViewHolder implements a {

    /* renamed from: g  reason: collision with root package name */
    private final TextView f8276g;

    /* renamed from: h  reason: collision with root package name */
    private final TextView f8277h;

    public PdCardSelectedViewHolder(@NonNull View view) {
        super(view);
        this.f8276g = (TextView) view.findViewById(R.id.productdetailcard_selected_title_tv);
        this.f8277h = (TextView) view.findViewById(R.id.productdetailcard_selected_content_tv);
    }

    @Override // com.jingdong.app.mall.bundle.productdetailcard.viewholder.a
    public void a(PdCardFloorInfo pdCardFloorInfo) {
        if (pdCardFloorInfo != null) {
            if (!TextUtils.isEmpty(pdCardFloorInfo.title)) {
                this.f8276g.setText(pdCardFloorInfo.title);
            }
            Object obj = pdCardFloorInfo.data;
            if (obj instanceof JDJSONObject) {
                PdCardSelectedData pdCardSelectedData = (PdCardSelectedData) JDJSON.parseObject(((JDJSONObject) obj).toJSONString(), PdCardSelectedData.class);
                if (pdCardSelectedData != null && !TextUtils.isEmpty(pdCardSelectedData.selectInfo)) {
                    this.f8277h.setText(pdCardSelectedData.selectInfo);
                    this.f8277h.setVisibility(0);
                    return;
                }
                this.f8277h.setVisibility(8);
            }
        }
    }
}
