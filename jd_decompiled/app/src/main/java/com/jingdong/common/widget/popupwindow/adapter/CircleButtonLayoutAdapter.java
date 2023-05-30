package com.jingdong.common.widget.popupwindow.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.jd.lib.un.basewidget.R;
import com.jingdong.common.widget.popupwindow.entity.BaseCircleButtonInfo;
import java.util.List;

/* loaded from: classes12.dex */
public class CircleButtonLayoutAdapter extends BaseAdapter {
    private CircleButtonOnItemClickListener circleButtonOnItemClickListener;
    private Context context;
    private List<BaseCircleButtonInfo> list;

    /* loaded from: classes12.dex */
    public interface CircleButtonOnItemClickListener {
        void onItemClick(View view, int i2);
    }

    public CircleButtonLayoutAdapter(Context context, CircleButtonOnItemClickListener circleButtonOnItemClickListener) {
        this.context = context;
        this.circleButtonOnItemClickListener = circleButtonOnItemClickListener;
    }

    public void bindData(List<BaseCircleButtonInfo> list) {
        this.list = list;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<BaseCircleButtonInfo> list = this.list;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        return this.list.get(i2);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.Adapter
    public View getView(final int i2, View view, ViewGroup viewGroup) {
        TextView textView = (TextView) LayoutInflater.from(this.context).inflate(R.layout.lib_item_circle_button, (ViewGroup) null).findViewById(R.id.f5682tv);
        BaseCircleButtonInfo baseCircleButtonInfo = this.list.get(i2);
        textView.setText(baseCircleButtonInfo.text);
        if (baseCircleButtonInfo.enable) {
            textView.setEnabled(true);
            textView.setBackgroundResource(baseCircleButtonInfo.clickableCircleColorRes);
            textView.setTextColor(this.context.getResources().getColor(baseCircleButtonInfo.clickableButtonTextColorRes));
        } else {
            textView.setEnabled(false);
            textView.setBackgroundResource(baseCircleButtonInfo.unClickableCirclecolorRes);
            textView.setTextColor(this.context.getResources().getColor(baseCircleButtonInfo.unClickableButtonTextColorRes));
        }
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.popupwindow.adapter.CircleButtonLayoutAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (CircleButtonLayoutAdapter.this.circleButtonOnItemClickListener != null) {
                    CircleButtonLayoutAdapter.this.circleButtonOnItemClickListener.onItemClick(view2, i2);
                }
            }
        });
        return textView;
    }
}
