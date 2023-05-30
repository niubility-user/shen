package com.jingdong.common.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.jd.lib.un.basewidget.R;
import com.jd.lib.un.global.GlobalThemeController;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class JDCheckDialog extends JDDialog {
    private static GlobalThemeController controller;
    private BaseAdapter adapter;
    public HashMap<Integer, Object> checkItems;
    public boolean isAllowListCheckMultiItem;
    public ListView listView;

    /* loaded from: classes6.dex */
    public class StyleAdapter extends BaseAdapter {
        private ArrayList<String> array;
        private Context context;
        private String style;

        /* loaded from: classes6.dex */
        public class ViewHolder {
            public CheckBox cb;

            /* renamed from: tv */
            public TextView f12434tv;

            private ViewHolder() {
                StyleAdapter.this = r1;
            }
        }

        public StyleAdapter(Context context, ArrayList<String> arrayList, String str) {
            JDCheckDialog.this = r1;
            this.array = arrayList;
            this.context = context;
            this.style = str;
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
        public View getView(final int i2, View view, ViewGroup viewGroup) {
            View inflate;
            final String str = (String) getItem(i2);
            if (view == null || view.getTag() == null) {
                ViewHolder viewHolder = new ViewHolder();
                if ("style6".equals(this.style)) {
                    inflate = LayoutInflater.from(this.context).inflate(R.layout.jd_common_dialog_style_6_item, (ViewGroup) null);
                    viewHolder.cb = (CheckBox) inflate.findViewById(R.id.jd_common_dialog_style_6_item_check);
                    viewHolder.f12434tv = (TextView) inflate.findViewById(R.id.jd_common_dialog_style_6_item_text);
                } else {
                    inflate = LayoutInflater.from(this.context).inflate(R.layout.jd_common_dialog_style_4_item, (ViewGroup) null);
                    viewHolder.cb = (CheckBox) inflate.findViewById(R.id.jd_common_dialog_style_4_item_check);
                    viewHolder.f12434tv = (TextView) inflate.findViewById(R.id.jd_common_dialog_style_4_item_text);
                }
                if (JDCheckDialog.this.isDarkMode()) {
                    viewHolder.f12434tv.setTextColor(JDCheckDialog.this.getContext().getResources().getColor(R.color.un_content_level_1_dark));
                    viewHolder.cb.setBackgroundResource(R.drawable.button_j_new_dark);
                } else {
                    viewHolder.f12434tv.setTextColor(JDCheckDialog.this.getContext().getResources().getColor(R.color.un_content_level_1));
                    viewHolder.cb.setBackgroundResource(R.drawable.button_j_new);
                }
                inflate.setTag(viewHolder);
                view = inflate;
            }
            final ViewHolder viewHolder2 = (ViewHolder) view.getTag();
            viewHolder2.cb.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.ui.JDCheckDialog.StyleAdapter.1
                {
                    StyleAdapter.this = this;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    if (viewHolder2.cb.isChecked()) {
                        JDCheckDialog jDCheckDialog = JDCheckDialog.this;
                        if (!jDCheckDialog.isAllowListCheckMultiItem && jDCheckDialog.checkItems.size() > 0) {
                            JDCheckDialog.this.checkItems.clear();
                            StyleAdapter.this.notifyDataSetChanged();
                        }
                        JDCheckDialog.this.checkItems.put(Integer.valueOf(i2), str);
                        return;
                    }
                    JDCheckDialog.this.checkItems.remove(Integer.valueOf(i2));
                    if (!"style4".equals(StyleAdapter.this.style) || JDCheckDialog.this.checkItems.size() >= 1) {
                        return;
                    }
                    JDCheckDialog.this.checkItems.put(Integer.valueOf(i2), str);
                    StyleAdapter.this.notifyDataSetChanged();
                }
            });
            view.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.ui.JDCheckDialog.StyleAdapter.2
                {
                    StyleAdapter.this = this;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    viewHolder2.cb.performClick();
                }
            });
            JDCheckDialog jDCheckDialog = JDCheckDialog.this;
            if (!jDCheckDialog.isAllowListCheckMultiItem) {
                if (jDCheckDialog.checkItems.size() > 0) {
                    if (JDCheckDialog.this.checkItems.get(Integer.valueOf(i2)) != null) {
                        viewHolder2.cb.setChecked(true);
                    } else {
                        viewHolder2.cb.setChecked(false);
                    }
                } else {
                    viewHolder2.cb.setChecked(false);
                }
            } else if (jDCheckDialog.checkItems.get(Integer.valueOf(i2)) != null) {
                viewHolder2.cb.setChecked(true);
            } else {
                viewHolder2.cb.setChecked(false);
            }
            viewHolder2.f12434tv.setText(str);
            return view;
        }
    }

    public JDCheckDialog(Context context) {
        super(context);
        this.isAllowListCheckMultiItem = false;
        this.checkItems = new HashMap<>();
    }

    private int getLowest(Integer[] numArr) {
        int intValue = numArr[0].intValue();
        for (int i2 = 1; i2 < numArr.length; i2++) {
            if (intValue >= numArr[i2].intValue()) {
                intValue = numArr[i2].intValue();
            }
        }
        return intValue;
    }

    public static JDCheckDialog newJDCheckDialog(Context context) {
        GlobalThemeController newInstance = GlobalThemeController.newInstance();
        controller = newInstance;
        if (newInstance != null && newInstance.isCustomTheme() && controller.getThemeConfig().c() > 0) {
            return new JDCheckDialog(context, controller.getThemeConfig().c());
        }
        return new JDCheckDialog(context);
    }

    public HashMap<Integer, Object> getMultiResult() throws UnsupportedOperationException {
        if (this.isAllowListCheckMultiItem) {
            return this.checkItems;
        }
        throw new UnsupportedOperationException("this result only retun with multi mode");
    }

    public Object getSingleResult() throws UnsupportedOperationException {
        if (this.isAllowListCheckMultiItem) {
            throw new UnsupportedOperationException("this result only retun with single mode");
        }
        return this.checkItems.size() < 1 ? "" : this.checkItems.values().toArray()[0];
    }

    public void initListView(Context context, BaseAdapter baseAdapter, ArrayList<String> arrayList, String str) {
        View findViewById = findViewById(R.id.jd_dialog_list);
        if (findViewById == null || !(findViewById instanceof ListView)) {
            return;
        }
        if (baseAdapter == null && arrayList == null) {
            return;
        }
        this.listView = (ListView) findViewById;
        if (baseAdapter == null) {
            baseAdapter = new StyleAdapter(context, arrayList, str);
            if ("style4".equals(str) && arrayList.size() > 0 && this.checkItems.size() < 1) {
                this.checkItems.put(0, arrayList.get(0));
            }
        }
        this.adapter = baseAdapter;
        this.listView.setAdapter((ListAdapter) baseAdapter);
        setTotalHeightofListView(this.listView);
    }

    @Override // com.jingdong.common.unification.dialog.UnBaseDialog
    public void refresh() {
        super.refresh();
        BaseAdapter baseAdapter = this.adapter;
        if (baseAdapter != null) {
            baseAdapter.notify();
        }
    }

    public void setDefaultSelectItem(HashMap<Integer, Object> hashMap) {
        this.checkItems = hashMap;
        if (this.listView == null || hashMap.size() <= 0) {
            return;
        }
        Integer[] numArr = new Integer[hashMap.size()];
        hashMap.keySet().toArray(numArr);
        this.listView.setSelection(getLowest(numArr));
    }

    public JDCheckDialog(Context context, int i2) {
        super(context, i2);
        this.isAllowListCheckMultiItem = false;
        this.checkItems = new HashMap<>();
    }
}
