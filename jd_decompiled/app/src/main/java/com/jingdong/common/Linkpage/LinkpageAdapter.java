package com.jingdong.common.Linkpage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import androidx.viewpager.widget.PagerAdapter;
import com.jingdong.app.mall.R;
import java.util.List;

/* loaded from: classes5.dex */
public class LinkpageAdapter extends PagerAdapter {
    private LayoutInflater inflater;
    private ILinkpageListener listener;
    private List<Integer> posterList;

    /* loaded from: classes5.dex */
    static class ViewHolder {
        ImageView icon;
        Button main;

        ViewHolder() {
        }
    }

    public LinkpageAdapter(Context context, List<Integer> list, ILinkpageListener iLinkpageListener) {
        this.posterList = list;
        this.listener = iLinkpageListener;
        this.inflater = LayoutInflater.from(context);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i2, Object obj) {
        viewGroup.removeView((View) obj);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        if (this.posterList.isEmpty()) {
            return 0;
        }
        return this.posterList.size();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, final int i2) {
        View view;
        ViewHolder viewHolder;
        View findViewById = viewGroup.findViewById(i2);
        if (findViewById == null) {
            viewHolder = new ViewHolder();
            view = this.inflater.inflate(R.layout.item_linkpage, (ViewGroup) null);
            view.setId(i2);
            viewHolder.icon = (ImageView) view.findViewById(R.id.image_pic);
            viewHolder.main = (Button) view.findViewById(R.id.btn_main);
            viewGroup.addView(view);
            view.setTag(viewHolder);
        } else {
            view = findViewById;
            viewHolder = (ViewHolder) findViewById.getTag();
        }
        if (i2 == this.posterList.size() - 1) {
            viewHolder.main.setVisibility(0);
        } else {
            viewHolder.main.setVisibility(4);
        }
        viewHolder.icon.setBackgroundResource(this.posterList.get(i2).intValue());
        viewHolder.icon.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.Linkpage.LinkpageAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                LinkpageAdapter.this.listener.onBgClickListener(i2);
            }
        });
        viewHolder.main.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.Linkpage.LinkpageAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                LinkpageAdapter.this.listener.onBtnClickListener(i2);
            }
        });
        return view;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void startUpdate(ViewGroup viewGroup) {
        super.startUpdate(viewGroup);
    }
}
