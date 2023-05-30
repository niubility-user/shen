package com.jingdong.common.cart;

import android.util.SparseArray;
import com.jingdong.common.entity.cart.CartResponseShop;
import com.jingdong.common.entity.cart.tabMenu.TabMenuEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes5.dex */
public class CartUniformResult {
    public TabMenuEntity currentTabMenu;
    public ArrayList<Object> allDatas = new ArrayList<>();
    public SparseArray<Integer> shopIndex = new SparseArray<>();
    public SparseArray<ArrayList<Object>> shopChilds = new SparseArray<>();
    public List<Integer> mGroup = new ArrayList();
    public int currentPosition = 0;
    public int group = 0;
    public long abConfigs = 0;
    public ArrayList<CartResponseShop> selectedCartResponseShops = new ArrayList<>();
    public ArrayList<CartResponseShop> unSelectedCartResponseShops = new ArrayList<>();
    public HashMap<String, TabMenuEntity> tabMenuMap = new HashMap<>();
    public ArrayList<CartResponseShop> allResponseShops = new ArrayList<>();
    public boolean filterSwitchOpen = true;
    public String filterTag = "";
}
