package com.jingdong.common.listui.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import com.jingdong.common.R;
import com.jingdong.common.listui.AListItem;
import com.jingdong.common.listui.ViewHolder;

/* loaded from: classes5.dex */
public class LastReadHereItem extends AListItem<String, ViewHolder> {
    private int backgroundColor;
    private boolean isRefreshIconVisible = true;
    private ImageView iv_refresh;
    private LinearLayout ll_root;
    private String refreshStr;
    private TextView tv_refresh;

    @Override // com.jingdong.common.listui.AListItem
    public int getLayoutId() {
        return R.layout.listui_last_read_item;
    }

    public void setBackground(@ColorInt int i2) {
        this.backgroundColor = i2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setData(String str) {
        this.data = str;
    }

    public void setRefreshIconVisibility(boolean z) {
        this.isRefreshIconVisible = z;
    }

    public void setRefreshStr(String str) {
        this.refreshStr = str;
    }

    @Override // com.jingdong.common.listui.AListItem
    public void onBindViewHolder(ViewHolder viewHolder, Context context) {
        this.ll_root = (LinearLayout) viewHolder.getView(R.id.ll_root);
        viewHolder.setText(R.id.desc, (String) this.data);
        this.tv_refresh = (TextView) viewHolder.getView(R.id.tv_refresh);
        this.iv_refresh = (ImageView) viewHolder.getView(R.id.iv_refresh);
        int i2 = this.backgroundColor;
        if (i2 != 0) {
            this.ll_root.setBackgroundColor(i2);
        }
        if (TextUtils.isEmpty(this.refreshStr)) {
            this.tv_refresh.setVisibility(8);
        } else {
            this.tv_refresh.setVisibility(0);
            this.tv_refresh.setText(this.refreshStr);
        }
        if (this.isRefreshIconVisible) {
            this.iv_refresh.setVisibility(0);
        } else {
            this.iv_refresh.setVisibility(8);
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.listui.view.LastReadHereItem.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LastReadHereItem.this.getWrapBundle().itemClickHook();
            }
        });
    }

    @Override // com.jingdong.common.listui.AListItem
    public ViewHolder onCreateViewHolder(View view) {
        return new ViewHolder(view);
    }
}
