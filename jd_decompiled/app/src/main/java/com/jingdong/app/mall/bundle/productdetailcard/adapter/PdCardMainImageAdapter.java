package com.jingdong.app.mall.bundle.productdetailcard.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.bundle.productdetailcard.R;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.display.JDRoundedBitmapDisplayer;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class PdCardMainImageAdapter extends RecyclerView.Adapter {
    private Context a;
    private ArrayList<String> b;

    /* loaded from: classes3.dex */
    class a extends RecyclerView.ViewHolder {
        private final SimpleDraweeView a;
        private final JDDisplayImageOptions b;

        public a(@NonNull PdCardMainImageAdapter pdCardMainImageAdapter, View view) {
            super(view);
            this.a = (SimpleDraweeView) view.findViewById(R.id.productdetailcard_main_image_item_iv);
            JDDisplayImageOptions createSimple = JDDisplayImageOptions.createSimple();
            this.b = createSimple;
            createSimple.displayer(new JDRoundedBitmapDisplayer(DPIUtil.dip2px(6.0f)));
        }

        public void b(String str) {
            JDImageUtils.displayImage(str, (ImageView) this.a, this.b, true);
        }
    }

    public PdCardMainImageAdapter(Context context) {
        this.a = context;
    }

    public void a(ArrayList<String> arrayList) {
        this.b = arrayList;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        ArrayList<String> arrayList = this.b;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i2) {
        ((a) viewHolder).b(this.b.get(i2));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        return new a(this, ImageUtil.inflate(this.a, R.layout.productdetailcard_main_image_item, viewGroup, false));
    }
}
