package com.jingdong.common.entity;

import java.util.ArrayList;

/* loaded from: classes5.dex */
public class JdCartInfo extends JdTradeProduct {
    private ArrayList<JdCartItemInfo> mCartItemList = new ArrayList<>();
    public int nItemCount = 0;
    public double dTotalPrice = 0.0d;

    public boolean appendCartItem(JdCartItemInfo jdCartItemInfo) {
        if (this.mCartItemList.size() >= 50 || jdCartItemInfo == null) {
            return false;
        }
        this.mCartItemList.add(jdCartItemInfo);
        this.nItemCount++;
        return true;
    }

    public void clearCart() {
        this.mCartItemList.clear();
        this.mCartItemList = null;
        this.nItemCount = 0;
    }

    public ArrayList<JdCartItemInfo> getAllCartItems() {
        return this.mCartItemList;
    }

    public JdCartItemInfo getCartItem(int i2) {
        return this.mCartItemList.get(i2);
    }
}
