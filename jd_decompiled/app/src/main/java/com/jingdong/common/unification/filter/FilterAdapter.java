package com.jingdong.common.unification.filter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.app.util.image.placeholder.JDPlaceholderDrawable;
import com.jingdong.common.R;
import java.util.List;

/* loaded from: classes6.dex */
public class FilterAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<FilterInfo> datas;
    private View.OnClickListener onClickListener;

    /* loaded from: classes6.dex */
    private class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        ImageView selectIv;

        /* renamed from: tv  reason: collision with root package name */
        TextView f12443tv;

        public MyViewHolder(View view) {
            super(view);
            this.f12443tv = (TextView) view.findViewById(R.id.itemTv);
            this.iv = (ImageView) view.findViewById(R.id.itemIv);
            this.selectIv = (ImageView) view.findViewById(R.id.selectIv);
        }
    }

    public FilterAdapter(Context context, List<FilterInfo> list) {
        this.context = context;
        this.datas = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.datas.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i2) {
        if (viewHolder instanceof MyViewHolder) {
            FilterInfo filterInfo = this.datas.get(i2);
            MyViewHolder myViewHolder = (MyViewHolder) viewHolder;
            myViewHolder.f12443tv.setText(filterInfo.name);
            Bitmap bitmap = filterInfo.bitmap;
            if (bitmap != null) {
                myViewHolder.iv.setImageBitmap(bitmap);
            } else {
                myViewHolder.iv.setImageDrawable(new JDPlaceholderDrawable(17));
            }
            if (filterInfo.isSelect) {
                myViewHolder.selectIv.setVisibility(0);
                myViewHolder.f12443tv.setTextColor(this.context.getResources().getColor(R.color.c_f23030));
            } else {
                myViewHolder.selectIv.setVisibility(8);
                myViewHolder.f12443tv.setTextColor(-1);
            }
            if (this.onClickListener != null) {
                myViewHolder.iv.setTag(filterInfo);
                myViewHolder.iv.setOnClickListener(this.onClickListener);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return new MyViewHolder(LayoutInflater.from(this.context).inflate(R.layout.common_filter_item, viewGroup, false));
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
