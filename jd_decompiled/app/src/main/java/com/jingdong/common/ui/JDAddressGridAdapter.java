package com.jingdong.common.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.jd.lib.un.address.R;
import com.jingdong.common.ui.address.entity.HotAreaBean;
import java.util.List;

/* loaded from: classes6.dex */
public class JDAddressGridAdapter extends BaseAdapter {
    private List<HotAreaBean> infos;
    private boolean isDarkMode;
    private Context mContext;

    public JDAddressGridAdapter(Context context, List<HotAreaBean> list, boolean z) {
        this.infos = list;
        this.mContext = context;
        this.isDarkMode = z;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.infos.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.jd_address_hot_area_item, viewGroup, false);
        TextView textView = (TextView) inflate.findViewById(R.id.hot_txt);
        textView.setText(this.infos.get(i2).getName());
        if (this.isDarkMode) {
            textView.setTextColor(this.mContext.getResources().getColor(R.color.un_content_level_1_dark));
        } else {
            textView.setTextColor(this.mContext.getResources().getColor(R.color.un_content_level_1));
        }
        return inflate;
    }

    public void setDarkMode(boolean z) {
        this.isDarkMode = z;
    }

    public void updateContent(List<HotAreaBean> list) {
        this.infos = list;
        notifyDataSetChanged();
    }
}
