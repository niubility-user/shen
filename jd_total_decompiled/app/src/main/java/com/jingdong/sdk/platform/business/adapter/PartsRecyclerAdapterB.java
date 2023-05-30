package com.jingdong.sdk.platform.business.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.sdk.platform.business.R;
import com.jingdong.sdk.platform.business.entity.PartsEntity;
import com.jingdong.sdk.platform.business.entity.PartsItemEntity;
import com.jingdong.sdk.platform.utils.PlatformTools;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.List;

/* loaded from: classes9.dex */
public class PartsRecyclerAdapterB extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ACCESSORY_ITEM = 1;
    private static final int ACCESSORY_ITEM_LENGTH_MOST = 7;
    private static final int ACCESSORY_MORE = 2;
    private boolean isDarkTheme;
    private boolean isTenthRevision;
    private Context mContext;
    private List<PartsItemEntity> mData;
    private LayoutInflater mLayoutInflater;
    private OnItemClickListener mOnItemClickListener;
    private PartsEntity partsEntity;
    private boolean viewMore;

    /* loaded from: classes9.dex */
    private static class AccessoryItemViewHolder extends RecyclerView.ViewHolder {
        private boolean isTenthRevision;
        SimpleDraweeView itemImg;
        TextView itemTitleTv;
        private RelativeLayout mItemLayout;

        AccessoryItemViewHolder(View view, boolean z) {
            super(view);
            this.isTenthRevision = z;
            this.mItemLayout = (RelativeLayout) view.findViewById(R.id.platform_parts_item_layout);
            this.itemTitleTv = (TextView) view.findViewById(R.id.platform_parts_item_title);
            this.itemImg = (SimpleDraweeView) view.findViewById(R.id.platform_parts_item_img);
            ViewCompat.setAccessibilityDelegate(view, new AccessibilityDelegateCompat() { // from class: com.jingdong.sdk.platform.business.adapter.PartsRecyclerAdapterB.AccessoryItemViewHolder.1
                @Override // androidx.core.view.AccessibilityDelegateCompat
                public void onInitializeAccessibilityNodeInfo(View view2, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                    if (accessibilityNodeInfoCompat != null) {
                        super.onInitializeAccessibilityNodeInfo(view2, accessibilityNodeInfoCompat);
                        accessibilityNodeInfoCompat.setClassName(Button.class.getName());
                    }
                }
            });
            ViewGroup.LayoutParams layoutParams = this.mItemLayout.getLayoutParams();
            RelativeLayout.LayoutParams layoutParams2 = this.itemImg.getLayoutParams() instanceof RelativeLayout.LayoutParams ? (RelativeLayout.LayoutParams) this.itemImg.getLayoutParams() : null;
            if (this.isTenthRevision) {
                layoutParams.width = DPIUtil.dip2px(84.0f);
                layoutParams.height = DPIUtil.dip2px(110.0f);
                this.mItemLayout.setBackgroundResource(0);
                this.mItemLayout.setPadding(0, 0, 0, 0);
                this.itemImg.setBackgroundResource(R.drawable.lib_platform_part_item_bg);
                this.itemImg.setPadding(DPIUtil.dip2px(0.5f), DPIUtil.dip2px(0.5f), DPIUtil.dip2px(0.5f), DPIUtil.dip2px(0.5f));
                if (layoutParams2 != null) {
                    layoutParams2.height = DPIUtil.dip2px(84.0f);
                    layoutParams2.width = DPIUtil.dip2px(84.0f);
                    layoutParams2.setMargins(0, 0, 0, 0);
                }
            } else {
                layoutParams.width = DPIUtil.dip2px(85.0f);
                layoutParams.height = DPIUtil.dip2px(100.0f);
                this.mItemLayout.setBackgroundResource(R.drawable.lib_platform_part_item_bg);
                this.mItemLayout.setPadding(DPIUtil.dip2px(0.5f), DPIUtil.dip2px(0.5f), DPIUtil.dip2px(0.5f), DPIUtil.dip2px(0.5f));
                this.itemImg.setPadding(0, 0, 0, 0);
                this.itemImg.setBackgroundResource(0);
                if (layoutParams2 != null) {
                    layoutParams2.height = DPIUtil.dip2px(60.0f);
                    layoutParams2.width = -1;
                    layoutParams2.setMargins(0, DPIUtil.dip2px(7.0f), 0, 0);
                }
            }
            if (layoutParams2 != null) {
                this.itemImg.setLayoutParams(layoutParams2);
            }
        }
    }

    /* loaded from: classes9.dex */
    private static class AccessoryMoreViewHolder extends RecyclerView.ViewHolder {
        TextView itemMoreTv;
        Context mContext;
        SimpleDraweeView moreImg;

        AccessoryMoreViewHolder(View view, Context context, boolean z) {
            super(view);
            this.mContext = context;
            TextView textView = (TextView) view.findViewById(R.id.platform_parts_seekmore_text);
            this.itemMoreTv = textView;
            textView.setTextColor(getColorTheme(z, R.color.platform_color_848484, R.color.pd_color_848383));
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.platform_parts_seekmore_icon);
            this.moreImg = simpleDraweeView;
            simpleDraweeView.setImageResource(z ? R.drawable.platform_slide_arrow_dark : R.drawable.platform_arrow_new);
            ViewCompat.setAccessibilityDelegate(view, new AccessibilityDelegateCompat() { // from class: com.jingdong.sdk.platform.business.adapter.PartsRecyclerAdapterB.AccessoryMoreViewHolder.1
                @Override // androidx.core.view.AccessibilityDelegateCompat
                public void onInitializeAccessibilityNodeInfo(View view2, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                    if (accessibilityNodeInfoCompat != null) {
                        super.onInitializeAccessibilityNodeInfo(view2, accessibilityNodeInfoCompat);
                        accessibilityNodeInfoCompat.setClassName(Button.class.getName());
                    }
                }
            });
        }

        private int getColorTheme(boolean z, int i2, int i3) {
            if (z) {
                return ContextCompat.getColor(this.mContext, i2);
            }
            return ContextCompat.getColor(this.mContext, i3);
        }
    }

    /* loaded from: classes9.dex */
    public interface OnItemClickListener {
        void onItemClick(PartsItemEntity partsItemEntity);
    }

    public PartsRecyclerAdapterB(Context context, PartsEntity partsEntity, boolean z, OnItemClickListener onItemClickListener) {
        this.mContext = context;
        this.partsEntity = partsEntity;
        this.isTenthRevision = z;
        List<PartsItemEntity> dealData = partsEntity.dealData();
        this.mData = dealData.size() > 7 ? dealData.subList(0, 7) : dealData;
        this.viewMore = partsEntity.viewMore;
        this.mOnItemClickListener = onItemClickListener;
        this.isDarkTheme = partsEntity.darkModel;
        this.mLayoutInflater = LayoutInflater.from(this.mContext);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.viewMore) {
            List<PartsItemEntity> list = this.mData;
            if (list != null) {
                return list.size() + 1;
            }
            return 0;
        }
        List<PartsItemEntity> list2 = this.mData;
        if (list2 != null) {
            return list2.size();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        return (this.viewMore && i2 == this.mData.size()) ? 2 : 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i2) {
        final PartsItemEntity partsItemEntity;
        if (!(viewHolder instanceof AccessoryItemViewHolder) || (partsItemEntity = this.mData.get(i2)) == null) {
            return;
        }
        if (!TextUtils.isEmpty(partsItemEntity.imgText)) {
            AccessoryItemViewHolder accessoryItemViewHolder = (AccessoryItemViewHolder) viewHolder;
            accessoryItemViewHolder.itemTitleTv.setVisibility(0);
            accessoryItemViewHolder.itemTitleTv.setText(partsItemEntity.imgText);
            if (this.isTenthRevision) {
                if (this.isDarkTheme) {
                    accessoryItemViewHolder.itemTitleTv.setTextColor(ContextCompat.getColor(this.mContext, R.color.platform_color_ececec));
                } else {
                    accessoryItemViewHolder.itemTitleTv.setTextColor(PlatformTools.getColorValue(this.partsEntity.text3C, ContextCompat.getColor(this.mContext, R.color.platform_color_2E2D2D)));
                }
                accessoryItemViewHolder.itemTitleTv.setBackgroundColor(0);
                accessoryItemViewHolder.itemTitleTv.setPadding(0, 0, 0, 0);
            } else {
                accessoryItemViewHolder.itemTitleTv.setTextColor(PlatformTools.getColorValue(this.partsEntity.text3C, ContextCompat.getColor(this.mContext, R.color.platform_color_2E2D2D)));
                accessoryItemViewHolder.itemTitleTv.setBackgroundColor(PlatformTools.getColorValue(this.partsEntity.text3Bgc, ContextCompat.getColor(this.mContext, R.color.platform_color_bfececec)));
                accessoryItemViewHolder.itemTitleTv.setPadding(2, 2, 2, 2);
            }
            accessoryItemViewHolder.itemTitleTv.setTextSize(1, PlatformTools.getTextSizeValue(this.partsEntity.text3S, 12));
        } else {
            ((AccessoryItemViewHolder) viewHolder).itemTitleTv.setVisibility(8);
        }
        AccessoryItemViewHolder accessoryItemViewHolder2 = (AccessoryItemViewHolder) viewHolder;
        JDImageUtils.displayImage(partsItemEntity.imgUrl, accessoryItemViewHolder2.itemImg);
        accessoryItemViewHolder2.itemImg.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.sdk.platform.business.adapter.PartsRecyclerAdapterB.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (PartsRecyclerAdapterB.this.mOnItemClickListener != null) {
                    PartsRecyclerAdapterB.this.mOnItemClickListener.onItemClick(partsItemEntity);
                }
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        if (i2 == 1) {
            return new AccessoryItemViewHolder(this.mLayoutInflater.inflate(R.layout.lib_platform_floor_parts_item_b, viewGroup, false), this.isTenthRevision);
        }
        return new AccessoryMoreViewHolder(this.mLayoutInflater.inflate(R.layout.lib_platform_floor_parts_seekmore_b, viewGroup, false), this.mContext, this.isDarkTheme);
    }
}
