package com.jd.lib.productdetail.tradein.selectdevice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.productdetail.tradein.R;
import com.jd.lib.productdetail.tradein.selectdevice.TradeInSelectDeviceData;
import java.util.List;

/* loaded from: classes16.dex */
public class TradeInSelectDeviceCateTagAdapter extends RecyclerView.Adapter<a> {
    public final List<TradeInSelectDeviceData.Data.TagsInfo.TagItem> a;
    public View.OnClickListener b;

    /* renamed from: c  reason: collision with root package name */
    public TradeInSelectDeviceData.Data.TagsInfo.TagItem f5498c;

    /* loaded from: classes16.dex */
    public static final class a extends RecyclerView.ViewHolder {
        public final TextView a;

        public a(@NonNull View view) {
            super(view);
            TextView textView = (TextView) view.findViewById(R.id.tradein_select_device_cate_tag_item_name);
            this.a = textView;
            textView.setBackgroundResource(com.jd.lib.productdetail.core.R.drawable.lib_pd_common_item_background_corners_40_light);
            textView.setTextColor(textView.getResources().getColorStateList(com.jd.lib.productdetail.core.R.color.lib_pd_common_item_textcolor_light));
        }
    }

    public TradeInSelectDeviceCateTagAdapter(List<TradeInSelectDeviceData.Data.TagsInfo.TagItem> list, View.OnClickListener onClickListener) {
        this.a = list;
        this.b = onClickListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h(View view) {
        if ((view.getTag() instanceof TradeInSelectDeviceData.Data.TagsInfo.TagItem) && view.getTag() != this.f5498c && ((TradeInSelectDeviceData.Data.TagsInfo.TagItem) view.getTag()).enable) {
            TradeInSelectDeviceData.Data.TagsInfo.TagItem tagItem = this.f5498c;
            if (tagItem != null) {
                tagItem.selected = false;
            }
            TradeInSelectDeviceData.Data.TagsInfo.TagItem tagItem2 = (TradeInSelectDeviceData.Data.TagsInfo.TagItem) view.getTag();
            this.f5498c = tagItem2;
            tagItem2.selected = true;
            View.OnClickListener onClickListener = this.b;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
            notifyDataSetChanged();
        }
    }

    @NonNull
    public a a(@NonNull ViewGroup viewGroup) {
        return new a(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tradein_select_device_cate_tag_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.a.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(@NonNull a aVar, int i2) {
        TradeInSelectDeviceData.Data.TagsInfo.TagItem tagItem = this.a.get(i2);
        if (tagItem != null) {
            if (tagItem.selected) {
                this.f5498c = tagItem;
            }
            aVar.a.setText(tagItem.tagName);
            aVar.a.setSelected(tagItem.selected);
            aVar.itemView.setTag(tagItem);
            aVar.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.selectdevice.e
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    TradeInSelectDeviceCateTagAdapter.this.h(view);
                }
            });
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public /* bridge */ /* synthetic */ a onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        return a(viewGroup);
    }
}
