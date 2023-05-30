package com.jingdong.app.mall.bundle.productdetailcard.viewholder;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.bundle.productdetailcard.PdCardView;
import com.jingdong.app.mall.bundle.productdetailcard.R;
import com.jingdong.app.mall.bundle.productdetailcard.entity.PdCardFloorInfo;
import com.jingdong.app.mall.bundle.productdetailcard.entity.PdCardNameData;
import com.jingdong.common.unification.uniconfig.UnIconConfigHelper;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes3.dex */
public class PdCardNameViewHolder extends RecyclerView.ViewHolder implements a {

    /* renamed from: g  reason: collision with root package name */
    private final TextView f8262g;

    /* renamed from: h  reason: collision with root package name */
    private Context f8263h;

    public PdCardNameViewHolder(Context context, @NonNull View view) {
        super(view);
        this.f8263h = context;
        TextView textView = (TextView) view.findViewById(R.id.productdetailcard_name_tv);
        this.f8262g = textView;
        if (Log.D) {
            Log.d(PdCardView.TAG, "PdCardNameViewHolder:\u521b\u5efafind textView" + textView);
        }
    }

    private CharSequence b(PdCardNameData pdCardNameData) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(!TextUtils.isEmpty(pdCardNameData.nameInfo) ? pdCardNameData.nameInfo : "");
        Bitmap bitmap = UnIconConfigHelper.getBitmap(pdCardNameData.iconInfo);
        if (bitmap != null && !bitmap.isRecycled()) {
            spannableStringBuilder.insert(0, (CharSequence) "\b\b");
            spannableStringBuilder.setSpan(new com.jingdong.app.mall.bundle.productdetailcard.a(com.jingdong.app.mall.bundle.productdetailcard.a.a(this.f8263h, bitmap)), 0, 1, 17);
        }
        return spannableStringBuilder;
    }

    @Override // com.jingdong.app.mall.bundle.productdetailcard.viewholder.a
    public void a(PdCardFloorInfo pdCardFloorInfo) {
        if (Log.D) {
            Log.d(PdCardView.TAG, "PdCardNameViewHolder:bindView info:" + pdCardFloorInfo);
        }
        if (pdCardFloorInfo != null) {
            Object obj = pdCardFloorInfo.data;
            if (obj instanceof JDJSONObject) {
                PdCardNameData pdCardNameData = (PdCardNameData) JDJSON.parseObject(((JDJSONObject) obj).toJSONString(), PdCardNameData.class);
                if (Log.D) {
                    Log.d(PdCardView.TAG, "PdCardNameViewHolder:bindView nameData" + pdCardNameData.nameInfo);
                }
                if (pdCardNameData != null) {
                    this.f8262g.setText(b(pdCardNameData));
                }
            }
        }
    }
}
