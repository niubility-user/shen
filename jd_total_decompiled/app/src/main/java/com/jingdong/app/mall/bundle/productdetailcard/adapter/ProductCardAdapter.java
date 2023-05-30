package com.jingdong.app.mall.bundle.productdetailcard.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.app.mall.bundle.productdetailcard.R;
import com.jingdong.app.mall.bundle.productdetailcard.entity.PdCardFloorInfo;
import com.jingdong.app.mall.bundle.productdetailcard.viewholder.PdCardEmptyViewHolder;
import com.jingdong.app.mall.bundle.productdetailcard.viewholder.PdCardGiftViewHolder;
import com.jingdong.app.mall.bundle.productdetailcard.viewholder.PdCardMainImageViewHolder;
import com.jingdong.app.mall.bundle.productdetailcard.viewholder.PdCardNameViewHolder;
import com.jingdong.app.mall.bundle.productdetailcard.viewholder.PdCardPraiseViewHolder;
import com.jingdong.app.mall.bundle.productdetailcard.viewholder.PdCardPriceViewHolder;
import com.jingdong.app.mall.bundle.productdetailcard.viewholder.PdCardSelectedViewHolder;
import com.jingdong.app.mall.bundle.productdetailcard.viewholder.PdCardServiceViewHolder;
import com.jingdong.app.mall.bundle.productdetailcard.viewholder.a;
import com.jingdong.common.utils.ImageUtil;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class ProductCardAdapter extends RecyclerView.Adapter {
    private ArrayList<PdCardFloorInfo> a;
    private Context b;

    public ProductCardAdapter(Context context) {
        this.b = context;
    }

    public void a(ArrayList<PdCardFloorInfo> arrayList) {
        this.a = arrayList;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        ArrayList<PdCardFloorInfo> arrayList = this.a;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        PdCardFloorInfo pdCardFloorInfo;
        ArrayList<PdCardFloorInfo> arrayList = this.a;
        if (arrayList != null && (pdCardFloorInfo = arrayList.get(i2)) != null) {
            if (TextUtils.equals(pdCardFloorInfo.biz, "image")) {
                return 0;
            }
            if (TextUtils.equals(pdCardFloorInfo.biz, "name")) {
                return 1;
            }
            if (TextUtils.equals(pdCardFloorInfo.biz, "price")) {
                return 2;
            }
            if (TextUtils.equals(pdCardFloorInfo.biz, "select")) {
                return 3;
            }
            if (TextUtils.equals(pdCardFloorInfo.biz, "gift")) {
                return 4;
            }
            if (TextUtils.equals(pdCardFloorInfo.biz, "service")) {
                return 5;
            }
            if (TextUtils.equals(pdCardFloorInfo.biz, "praise")) {
                return 6;
            }
        }
        return -1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i2) {
        if (viewHolder instanceof a) {
            ((a) viewHolder).a(this.a.get(i2));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        if (i2 == 0) {
            Context context = this.b;
            return new PdCardMainImageViewHolder(context, ImageUtil.inflate(context, R.layout.productdetailcard_main_image_layout, viewGroup, false));
        } else if (i2 == 1) {
            Context context2 = this.b;
            return new PdCardNameViewHolder(context2, ImageUtil.inflate(context2, R.layout.productdetailcard_name_layout, viewGroup, false));
        } else if (i2 == 2) {
            Context context3 = this.b;
            return new PdCardPriceViewHolder(context3, ImageUtil.inflate(context3, R.layout.productdetailcard_pirce_layout, viewGroup, false));
        } else if (i2 == 3) {
            return new PdCardSelectedViewHolder(ImageUtil.inflate(this.b, R.layout.productdetailcard_selected_layout, viewGroup, false));
        } else {
            if (i2 == 4) {
                Context context4 = this.b;
                return new PdCardGiftViewHolder(context4, ImageUtil.inflate(context4, R.layout.productdetailcard_gift_layout, viewGroup, false));
            } else if (i2 == 5) {
                return new PdCardServiceViewHolder(ImageUtil.inflate(this.b, R.layout.productdetailcard_service_layout, viewGroup, false));
            } else {
                if (i2 == 6) {
                    Context context5 = this.b;
                    return new PdCardPraiseViewHolder(context5, ImageUtil.inflate(context5, R.layout.productdetailcard_praise_layout, viewGroup, false));
                }
                return new PdCardEmptyViewHolder(new LinearLayout(this.b));
            }
        }
    }
}
