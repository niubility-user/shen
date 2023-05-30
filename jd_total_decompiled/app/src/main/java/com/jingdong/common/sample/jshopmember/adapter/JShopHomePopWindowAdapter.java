package com.jingdong.common.sample.jshopmember.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.utils.ui.view.JDMultiTextView;
import com.jingdong.common.sample.jshopmember.entity.JShopListPopupWindowMoudle;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.common.utils.JDImageUtils;
import java.util.List;

/* loaded from: classes6.dex */
public class JShopHomePopWindowAdapter extends BaseAdapter {
    private List<JShopListPopupWindowMoudle> mList;

    /* loaded from: classes6.dex */
    static class ViewHolder {
        ImageView imageView;
        JDMultiTextView jshop_home_msg_num;
        View jshop_home_msg_num_rl;
        ImageView red;

        /* renamed from: tv  reason: collision with root package name */
        TextView f12433tv;

        ViewHolder() {
        }
    }

    public JShopHomePopWindowAdapter(List<JShopListPopupWindowMoudle> list) {
        this.mList = list;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.mList.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        return this.mList.get(i2);
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
            view = ImageUtil.inflate(R.layout.jshop_popup_list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) view.findViewById(R.id.a7n);
            viewHolder.f12433tv = (TextView) view.findViewById(R.id.a7q);
            viewHolder.red = (ImageView) view.findViewById(R.id.a7o);
            viewHolder.jshop_home_msg_num_rl = view.findViewById(R.id.jshop_home_msg_num_rl);
            viewHolder.jshop_home_msg_num = (JDMultiTextView) view.findViewById(R.id.jshop_home_msg_num);
            view.setTag(viewHolder);
        }
        JShopListPopupWindowMoudle jShopListPopupWindowMoudle = this.mList.get(i2);
        JDImageUtils.displayImage(jShopListPopupWindowMoudle.imageUrl, viewHolder.imageView);
        viewHolder.f12433tv.setText(jShopListPopupWindowMoudle.info);
        int i3 = jShopListPopupWindowMoudle.type;
        if (i3 == 2) {
            viewHolder.jshop_home_msg_num_rl.setVisibility(8);
            viewHolder.red.setVisibility(8);
        } else if (jShopListPopupWindowMoudle.count > 0 && (i3 == 0 || i3 == 4)) {
            viewHolder.jshop_home_msg_num_rl.setVisibility(0);
            viewHolder.red.setVisibility(8);
            int i4 = jShopListPopupWindowMoudle.type;
            if (i4 == 0) {
                viewHolder.red.setVisibility(8);
                viewHolder.jshop_home_msg_num_rl.setVisibility(0);
                if (jShopListPopupWindowMoudle.count <= 9) {
                    viewHolder.jshop_home_msg_num.setText(jShopListPopupWindowMoudle.count + "");
                    viewHolder.jshop_home_msg_num_rl.setBackgroundResource(R.drawable.message_door_red_bg3);
                } else {
                    viewHolder.jshop_home_msg_num.setText("9+");
                    viewHolder.jshop_home_msg_num_rl.setBackgroundResource(R.drawable.message_door_red_bg3);
                }
            } else if (i4 == 4) {
                viewHolder.red.setVisibility(8);
                viewHolder.jshop_home_msg_num_rl.setVisibility(0);
                int i5 = jShopListPopupWindowMoudle.count;
                if (i5 > 99) {
                    viewHolder.jshop_home_msg_num.setText("99+");
                    viewHolder.jshop_home_msg_num_rl.setBackgroundResource(R.drawable.message_door_red_bg3);
                } else if (i5 > 9) {
                    viewHolder.jshop_home_msg_num.setText(jShopListPopupWindowMoudle.count + "");
                    viewHolder.jshop_home_msg_num_rl.setBackgroundResource(R.drawable.message_door_red_bg3);
                } else {
                    viewHolder.jshop_home_msg_num.setText(jShopListPopupWindowMoudle.count + "");
                    viewHolder.jshop_home_msg_num_rl.setBackgroundResource(R.drawable.message_door_red_bg3);
                }
            }
        } else {
            viewHolder.jshop_home_msg_num_rl.setVisibility(8);
            if (jShopListPopupWindowMoudle.isShowRedPoint) {
                viewHolder.red.setVisibility(0);
            } else {
                viewHolder.red.setVisibility(8);
            }
        }
        return view;
    }
}
