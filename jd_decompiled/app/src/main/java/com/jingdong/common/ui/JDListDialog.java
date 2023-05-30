package com.jingdong.common.ui;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.jd.lib.un.basewidget.R;
import java.util.List;

/* loaded from: classes6.dex */
public class JDListDialog extends JDDialog {
    public ListView listView;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class Style8Adapter extends BaseAdapter {
        private List<ListDialogEntity> array;
        private Context context;

        /* loaded from: classes6.dex */
        private class ViewHolder {
            public TextView contentTv;
            public TextView titleTv;

            private ViewHolder() {
            }
        }

        public Style8Adapter(Context context, List<ListDialogEntity> list) {
            this.array = list;
            this.context = context;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.array.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i2) {
            return (i2 < 0 || i2 > this.array.size() + (-1)) ? "" : this.array.get(i2);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view != null && view.getTag() != null) {
                viewHolder = (ViewHolder) view.getTag();
            } else {
                view = LayoutInflater.from(this.context).inflate(R.layout.jd_common_dialog_style_8_item, (ViewGroup) null);
                viewHolder = new ViewHolder();
                viewHolder.titleTv = (TextView) view.findViewById(R.id.jd_common_dialog_style_8_item_title);
                viewHolder.contentTv = (TextView) view.findViewById(R.id.jd_common_dialog_style_8_item_text);
                view.setTag(viewHolder);
            }
            ListDialogEntity listDialogEntity = this.array.get(i2);
            String title = listDialogEntity.getTitle();
            String content = listDialogEntity.getContent();
            if (!TextUtils.isEmpty(title)) {
                viewHolder.titleTv.setText(listDialogEntity.getTitle());
                viewHolder.titleTv.setVisibility(0);
            } else {
                viewHolder.titleTv.setVisibility(8);
            }
            if (!TextUtils.isEmpty(content)) {
                viewHolder.contentTv.setText(listDialogEntity.getContent());
                viewHolder.contentTv.setVisibility(0);
            } else {
                viewHolder.contentTv.setVisibility(8);
            }
            return view;
        }
    }

    public JDListDialog(Context context) {
        super(context);
    }

    public void initListView(Context context, BaseAdapter baseAdapter, List<ListDialogEntity> list) {
        View findViewById = findViewById(R.id.jd_dialog_list);
        if (findViewById == null || !(findViewById instanceof ListView)) {
            return;
        }
        this.listView = (ListView) findViewById;
        if (baseAdapter == null) {
            baseAdapter = new Style8Adapter(context, list);
        }
        this.listView.setAdapter((ListAdapter) baseAdapter);
        setTotalHeightofListView(this.listView);
    }

    public void setListViewItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        ListView listView = this.listView;
        if (listView == null || onItemClickListener == null) {
            return;
        }
        listView.setOnItemClickListener(onItemClickListener);
    }

    public void setListViewItemLongClickListener(AdapterView.OnItemLongClickListener onItemLongClickListener) {
        ListView listView = this.listView;
        if (listView == null || onItemLongClickListener == null) {
            return;
        }
        listView.setOnItemLongClickListener(onItemLongClickListener);
    }

    public JDListDialog(Context context, int i2) {
        super(context, i2);
    }
}
