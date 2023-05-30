package com.jingdong.common.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.un.address.R;
import com.jingdong.common.ui.JDAddressSelectView;
import com.jingdong.common.ui.address.entity.AreaBeanVO;
import java.util.List;

/* loaded from: classes6.dex */
public class JDAddressHotAreaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<AreaBeanVO> infos;
    private JDAddressSelectView.OnItemHotClickListener onItemClickListener;

    /* loaded from: classes6.dex */
    class HotAreaHolder extends RecyclerView.ViewHolder {
        TextView hotText;

        public HotAreaHolder(@NonNull View view) {
            super(view);
            this.hotText = (TextView) view.findViewById(R.id.hot_txt);
        }
    }

    public JDAddressHotAreaAdapter(List<AreaBeanVO> list) {
        this.infos = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.infos.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i2) {
        final AreaBeanVO areaBeanVO = this.infos.get(i2);
        HotAreaHolder hotAreaHolder = (HotAreaHolder) viewHolder;
        hotAreaHolder.hotText.setText(areaBeanVO.getName());
        hotAreaHolder.hotText.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.ui.JDAddressHotAreaAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                JDAddressHotAreaAdapter.this.onItemClickListener.onItemClick(areaBeanVO);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        this.context = viewGroup.getContext();
        return new HotAreaHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.jd_address_hot_area_item, viewGroup, false));
    }

    public void setOnItemClickListener(JDAddressSelectView.OnItemHotClickListener onItemHotClickListener) {
        this.onItemClickListener = onItemHotClickListener;
    }
}
