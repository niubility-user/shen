package com.jingdong.common.permission;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.jingdong.common.R;
import com.jingdong.common.permission.entity.ListPermissionEntity;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.jdsdk.utils.DPIUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class JDPermissionDialogFactory {
    private static JDPermissionDialogFactory instance;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public class MutiPermissionAdapter extends BaseAdapter {
        private List<ListPermissionEntity> array;
        private Context context;

        /* loaded from: classes5.dex */
        private class ViewHolder {
            public TextView contentTv;
            public ImageView iconImg;
            public TextView titleTv;

            private ViewHolder() {
            }
        }

        public MutiPermissionAdapter(Context context, List<ListPermissionEntity> list) {
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
                view = LayoutInflater.from(this.context).inflate(R.layout.jd_common_dialog_style_permission_item, (ViewGroup) null);
                viewHolder = new ViewHolder();
                viewHolder.titleTv = (TextView) view.findViewById(R.id.jd_common_dialog_style_permission_item_title);
                viewHolder.contentTv = (TextView) view.findViewById(R.id.jd_common_dialog_style_permission_item_text);
                view.setTag(viewHolder);
            }
            ListPermissionEntity listPermissionEntity = this.array.get(i2);
            viewHolder.titleTv.setText(listPermissionEntity.getTitle());
            viewHolder.contentTv.setText(listPermissionEntity.getContent());
            return view;
        }
    }

    public static synchronized JDPermissionDialogFactory getInstance() {
        JDPermissionDialogFactory jDPermissionDialogFactory;
        synchronized (JDPermissionDialogFactory.class) {
            if (instance == null) {
                instance = new JDPermissionDialogFactory();
            }
            jDPermissionDialogFactory = instance;
        }
        return jDPermissionDialogFactory;
    }

    public static List<ListPermissionEntity> removeDuplicate(List<ListPermissionEntity> list) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (!arrayList.contains(list.get(i2))) {
                arrayList.add(list.get(i2));
            }
        }
        return arrayList;
    }

    public JDDialog createJdMutiPermissionDialog(Context context, String str, List<ListPermissionEntity> list, String str2, String str3, String str4) throws IllegalArgumentException {
        if (context != null) {
            ListView listView = new ListView(context);
            listView.setSelector(new ColorDrawable(0));
            listView.setAdapter((ListAdapter) new MutiPermissionAdapter(context, list));
            listView.setDivider(null);
            if (context.getResources() != null) {
                listView.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.jd_common_dialog_style_permission_bg));
            }
            JDDialog createJdDialogWithStyle10 = JDDialogFactory.getInstance().createJdDialogWithStyle10(context, str, str2, listView, str3, str4);
            TextView textView = createJdDialogWithStyle10.messageView;
            if (textView != null) {
                textView.setTextSize(2, 12.0f);
                if (createJdDialogWithStyle10.messageView.getLayoutParams() instanceof LinearLayout.LayoutParams) {
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams.setMargins(0, DPIUtil.dip2px(16.0f), 0, 0);
                    layoutParams.gravity = 17;
                    createJdDialogWithStyle10.messageView.setLayoutParams(layoutParams);
                }
            }
            return createJdDialogWithStyle10;
        }
        throw new IllegalArgumentException("the param context can not be null in this dialog style");
    }

    public JDDialog createJdSinglePermissionDialog(Context context, String str, String str2, String str3, String str4) throws IllegalArgumentException {
        if (context != null) {
            if (!TextUtils.isEmpty(str2)) {
                JDDialog createJdDialogWithStyle9 = JDDialogFactory.getInstance().createJdDialogWithStyle9(context, str, str2, null, str3, str4);
                TextView textView = createJdDialogWithStyle9.messageView;
                if (textView != null && (textView.getLayoutParams() instanceof LinearLayout.LayoutParams)) {
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams.setMargins(0, DPIUtil.dip2px(12.0f), 0, DPIUtil.dip2px(4.0f));
                    createJdDialogWithStyle9.messageView.setLayoutParams(layoutParams);
                }
                return createJdDialogWithStyle9;
            }
            throw new IllegalArgumentException("the param message can not be empty in this dialog style");
        }
        throw new IllegalArgumentException("the param context can not be null in this dialog style");
    }

    public JDDialog createJdTopPermissionDialog(Context context, String str, String str2) {
        if (context != null) {
            if (!TextUtils.isEmpty(str2)) {
                JDDialog createJdDialogWithStyleTop = JDDialogFactory.getInstance().createJdDialogWithStyleTop(context, str, str2);
                createJdDialogWithStyleTop.isTop = true;
                createJdDialogWithStyleTop.setCancelable(true);
                createJdDialogWithStyleTop.setCanceledOnTouchOutside(true);
                return createJdDialogWithStyleTop;
            }
            throw new IllegalArgumentException("the param message can not be empty in this dialog style");
        }
        throw new IllegalArgumentException("the param context can not be null in this dialog style");
    }

    public JDDialog createJdSinglePermissionDialog(Context context, String str, String str2, String str3) throws IllegalArgumentException {
        return createJdSinglePermissionDialog(context, str, str2, "", str3);
    }

    public JDDialog createJdMutiPermissionDialog(Context context, String str, List<ListPermissionEntity> list, String str2, String str3) throws IllegalArgumentException {
        return createJdMutiPermissionDialog(context, str, list, str2, "", str3);
    }
}
