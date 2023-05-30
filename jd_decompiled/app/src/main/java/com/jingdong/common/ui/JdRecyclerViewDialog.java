package com.jingdong.common.ui;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.un.basewidget.R;
import java.util.List;

/* loaded from: classes6.dex */
public class JdRecyclerViewDialog extends JDDialog {
    private RecyclerView.Adapter adapter;
    public boolean isMessageShowWithRecycler;
    public RecyclerView recyclerView;

    /* loaded from: classes6.dex */
    static class DefaultAdapter extends RecyclerView.Adapter<DefaultViewHolder> {
        private List<ListDialogEntity> array;

        public DefaultAdapter(List<ListDialogEntity> list) {
            this.array = list;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.array.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(@NonNull DefaultViewHolder defaultViewHolder, int i2) {
            ListDialogEntity listDialogEntity = this.array.get(i2);
            if (!TextUtils.isEmpty(listDialogEntity.getTitle())) {
                defaultViewHolder.title.setText(listDialogEntity.getTitle());
                defaultViewHolder.title.setVisibility(0);
            } else {
                defaultViewHolder.title.setVisibility(8);
            }
            if (!TextUtils.isEmpty(listDialogEntity.getContent())) {
                defaultViewHolder.content.setText(listDialogEntity.getContent());
                defaultViewHolder.content.setVisibility(0);
                return;
            }
            defaultViewHolder.content.setVisibility(8);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        @NonNull
        public DefaultViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
            return new DefaultViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.jd_common_dialog_style_8_item, viewGroup, false));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class DefaultViewHolder extends RecyclerView.ViewHolder {
        public TextView content;
        public TextView title;

        public DefaultViewHolder(@NonNull View view) {
            super(view);
            this.title = (TextView) view.findViewById(R.id.jd_common_dialog_style_8_item_title);
            this.content = (TextView) view.findViewById(R.id.jd_common_dialog_style_8_item_text);
        }
    }

    public JdRecyclerViewDialog(Context context) {
        super(context);
    }

    private void setViewVisibility() {
        if (this.adapter == null) {
            this.adapter = this.recyclerView.getAdapter();
        }
        if (!this.isMessageShowWithRecycler) {
            if (this.adapter.getItemCount() > 0) {
                this.messageView.setVisibility(8);
                this.recyclerView.setVisibility(0);
                return;
            }
            if (!TextUtils.isEmpty(this.messageView.getText())) {
                this.messageView.setVisibility(0);
            }
            this.recyclerView.setVisibility(8);
            return;
        }
        if (this.adapter.getItemCount() > 0) {
            this.recyclerView.setVisibility(0);
        }
        if (TextUtils.isEmpty(this.messageView.getText())) {
            return;
        }
        this.messageView.setVisibility(0);
    }

    public void initCustomAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
        this.recyclerView.setAdapter(adapter);
    }

    public void initDefaultRecyclerView(List<ListDialogEntity> list) {
        DefaultAdapter defaultAdapter = new DefaultAdapter(list);
        this.adapter = defaultAdapter;
        this.recyclerView.setAdapter(defaultAdapter);
    }

    public void refreshRecyclerView() {
        setViewVisibility();
        this.adapter.notifyDataSetChanged();
        resetContentViewHeight();
    }

    @Override // com.jingdong.common.ui.JDDialog, android.app.Dialog
    public void show() {
        setViewVisibility();
        super.show();
        resetContentViewHeight();
    }

    public JdRecyclerViewDialog(Context context, int i2) {
        super(context, i2);
    }
}
