package com.jingdong.common.ui.address;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.un.address.R;
import com.jingdong.common.ui.address.UnAddressSelectView;
import com.jingdong.common.ui.address.entity.UnAddressInfo;
import java.util.List;

/* loaded from: classes6.dex */
public class UnAddressAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<UnAddressInfo> infos;
    private boolean isDarkMode;
    private UnAddressSelectView.OnItemClickListener onItemClickListener;
    private long selectId;
    private boolean coverageInfoEnable = true;
    private int showEnablePosition = -1;
    private int showDisablePosition = -1;

    /* loaded from: classes6.dex */
    class InfoViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout contentLayout;
        TextView groupName;
        TextView mainTitle;
        TextView secondTitle;
        ImageView tag;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public InfoViewHolder(@NonNull View view) {
            super(view);
            UnAddressAdapter.this = r1;
            this.tag = (ImageView) view.findViewById(R.id.tag);
            this.mainTitle = (TextView) view.findViewById(R.id.mainTitle);
            this.secondTitle = (TextView) view.findViewById(R.id.secondTitle);
            this.groupName = (TextView) view.findViewById(R.id.groupName);
            this.contentLayout = (RelativeLayout) view.findViewById(R.id.contentLayout);
        }
    }

    public UnAddressAdapter(List<UnAddressInfo> list, long j2, boolean z) {
        this.infos = list;
        this.selectId = j2;
        this.isDarkMode = z;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.infos.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i2) {
        final UnAddressInfo unAddressInfo = this.infos.get(i2);
        InfoViewHolder infoViewHolder = (InfoViewHolder) viewHolder;
        infoViewHolder.secondTitle.setText(unAddressInfo.fullAddress.replace(unAddressInfo.detailAddress, ""));
        infoViewHolder.mainTitle.setText(unAddressInfo.detailAddress);
        if (this.isDarkMode) {
            TextView textView = infoViewHolder.groupName;
            Resources resources = this.context.getResources();
            int i3 = R.color.un_content_level_2_dark;
            textView.setTextColor(resources.getColor(i3));
            infoViewHolder.secondTitle.setTextColor(this.context.getResources().getColor(i3));
        } else {
            TextView textView2 = infoViewHolder.groupName;
            Resources resources2 = this.context.getResources();
            int i4 = R.color.c_8c8c8c;
            textView2.setTextColor(resources2.getColor(i4));
            infoViewHolder.secondTitle.setTextColor(this.context.getResources().getColor(i4));
        }
        if (this.coverageInfoEnable && !unAddressInfo.isCoverage) {
            infoViewHolder.mainTitle.setTypeface(Typeface.defaultFromStyle(0));
            if (this.isDarkMode) {
                infoViewHolder.mainTitle.setTextColor(this.context.getResources().getColor(R.color.un_content_level_2_dark));
                infoViewHolder.tag.setImageResource(R.drawable.un_locaion_disable_dark);
            } else {
                infoViewHolder.mainTitle.setTextColor(this.context.getResources().getColor(R.color.c_8c8c8c));
                infoViewHolder.tag.setImageResource(R.drawable.un_locaion_disable);
            }
            int i5 = this.showDisablePosition;
            if (i5 != -1 && i5 != i2) {
                infoViewHolder.groupName.setVisibility(8);
            } else {
                infoViewHolder.groupName.setVisibility(0);
                infoViewHolder.groupName.setText("\u4e0d\u53ef\u914d\u9001\u5730\u5740");
                this.showDisablePosition = i2;
            }
        } else {
            if (this.isDarkMode) {
                infoViewHolder.mainTitle.setTextColor(this.context.getResources().getColor(R.color.un_content_level_1_dark));
            } else {
                infoViewHolder.mainTitle.setTextColor(this.context.getResources().getColor(R.color.un_content_level_1));
            }
            if (this.selectId == unAddressInfo.addressId) {
                infoViewHolder.mainTitle.setTypeface(Typeface.defaultFromStyle(1));
                infoViewHolder.tag.setImageResource(R.drawable.jd_address_select);
            } else {
                infoViewHolder.mainTitle.setTypeface(Typeface.defaultFromStyle(0));
                if (this.isDarkMode) {
                    infoViewHolder.tag.setImageResource(R.drawable.un_icon_location_success_dark);
                } else {
                    infoViewHolder.tag.setImageResource(R.drawable.un_icon_location_success);
                }
            }
            if (this.coverageInfoEnable) {
                int i6 = this.showEnablePosition;
                if (i6 != -1 && i6 != i2) {
                    infoViewHolder.groupName.setVisibility(8);
                } else {
                    infoViewHolder.groupName.setVisibility(0);
                    infoViewHolder.groupName.setText("\u53ef\u914d\u9001\u5730\u5740");
                    this.showEnablePosition = i2;
                }
            }
        }
        infoViewHolder.contentLayout.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.ui.address.UnAddressAdapter.1
            {
                UnAddressAdapter.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (UnAddressAdapter.this.onItemClickListener != null) {
                    UnAddressAdapter.this.onItemClickListener.onItemClick(unAddressInfo);
                }
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        this.context = viewGroup.getContext();
        return new InfoViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.un_address_select_item, viewGroup, false));
    }

    public void setCoverageInfoEnable(boolean z) {
        this.coverageInfoEnable = z;
    }

    public void setDarkMode(boolean z) {
        this.isDarkMode = z;
    }

    public void setOnItemClickListener(UnAddressSelectView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
