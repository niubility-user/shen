package com.jingdong.common.sample.jshopmember.ui;

import android.content.Context;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.common.sample.jshopmember.adapter.JShopHomePopWindowAdapter;
import com.jingdong.common.sample.jshopmember.entity.JShopListPopupWindowMoudle;
import com.jingdong.common.widget.popupwindow.JDPopupWindow;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class JShopHomePopWindow extends JDPopupWindow {
    private JShopListPopupWindowMoudle detailItem;
    private JShopListPopupWindowMoudle homeItem;
    private List<JShopListPopupWindowMoudle> items;
    private Context mContext;
    private JShopListPopupWindowMoudle msgItem;
    private JShopListPopupWindowMoudle shareItem;

    public JShopHomePopWindow(MyActivity myActivity) {
        super(myActivity);
        this.items = new ArrayList();
        this.mContext = myActivity;
        this.msgItem = new JShopListPopupWindowMoudle("res:///2130838459", myActivity.getString(R.string.jshop_message), false, 0);
        this.homeItem = new JShopListPopupWindowMoudle("res:///2130838458", this.mContext.getString(R.string.g1), false, 0);
        this.shareItem = new JShopListPopupWindowMoudle("res:///2130838457", this.mContext.getString(R.string.jshop_share), false, 0);
        this.detailItem = new JShopListPopupWindowMoudle("res:///2130839646", this.mContext.getString(R.string.jshop_shop_detail), false, 0);
        this.items.add(this.msgItem);
        this.items.add(this.homeItem);
        addContent(new JShopHomePopWindowAdapter(this.items));
    }

    public void setShopColosed(boolean z) {
        JShopListPopupWindowMoudle jShopListPopupWindowMoudle;
        List<JShopListPopupWindowMoudle> list = this.items;
        if (list != null && (jShopListPopupWindowMoudle = this.detailItem) != null) {
            if (z && list.contains(jShopListPopupWindowMoudle)) {
                this.items.remove(this.detailItem);
            } else if (!z && !this.items.contains(this.detailItem)) {
                this.items.add(this.detailItem);
            }
        }
        refreshListView();
    }

    public void showRedPoint(boolean z, int i2, int i3) {
        JShopListPopupWindowMoudle jShopListPopupWindowMoudle = this.msgItem;
        if (jShopListPopupWindowMoudle != null) {
            if (i2 <= 0) {
                i2 = 0;
            }
            jShopListPopupWindowMoudle.setType(i3);
            JShopListPopupWindowMoudle jShopListPopupWindowMoudle2 = this.msgItem;
            jShopListPopupWindowMoudle2.count = i2;
            jShopListPopupWindowMoudle2.isShowRedPoint = z;
        }
        refreshListView();
    }
}
