package com.jingdong.common.entity.cart;

import android.view.ViewGroup;
import com.jingdong.common.utils.CartHttpCacheUtil;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class CartRequest {
    public int appleCare;
    public String businessName;
    private CartRequestCommon cartCommon;
    private ArrayList<CartRequestOperate> cartOperates;
    public String contractPhoneSource;
    public HashMap<String, String> extFlag;
    public HashMap<String, Object> extendParams;
    private HttpGroup group;
    public String gsOperateType;
    public String hsOperateType;
    public boolean isEffect;
    public boolean isNeedCache;
    public String isNewMode;
    public boolean isNotify;
    public boolean isTouch;
    private ViewGroup loadingViewRoot;
    private CartHttpCacheUtil modelGroupUtil;
    private boolean noResponse;
    public HashMap<String, String> operationType;
    public String refer;
    public String replaceSkus;
    public String response;
    public String storeId;
    public String threeCcOperateType;
    public String ybOperateType;

    public CartRequest() {
        this.noResponse = false;
        this.cartOperates = new ArrayList<>();
        this.isNeedCache = true;
        this.operationType = new HashMap<>();
        this.extFlag = new HashMap<>();
        this.extendParams = new HashMap<>();
    }

    public CartRequestCommon getCartCommon() {
        return this.cartCommon;
    }

    public ArrayList<CartRequestOperate> getCartOperates() {
        return this.cartOperates;
    }

    public HashMap<String, String> getExtFlag() {
        return this.extFlag;
    }

    public HttpGroup getGroup() {
        return this.group;
    }

    public ViewGroup getLoadingViewRoot() {
        return this.loadingViewRoot;
    }

    public CartHttpCacheUtil getModelGroupUtil() {
        if (this.modelGroupUtil == null) {
            this.modelGroupUtil = new CartHttpCacheUtil();
        }
        return this.modelGroupUtil;
    }

    public boolean getNoResponse() {
        return this.noResponse;
    }

    public HashMap<String, String> getOperationType() {
        return this.operationType;
    }

    public void setCartCommon(CartRequestCommon cartRequestCommon) {
        this.cartCommon = cartRequestCommon;
    }

    public void setCartOperates(ArrayList<CartRequestOperate> arrayList) {
        this.cartOperates = arrayList;
    }

    public void setExtFlag(HashMap<String, String> hashMap) {
        this.extFlag = hashMap;
    }

    public void setGroup(HttpGroup httpGroup) {
        this.group = httpGroup;
    }

    public void setLoadingViewRoot(ViewGroup viewGroup) {
        this.loadingViewRoot = viewGroup;
    }

    public void setModelGroupUtil(CartHttpCacheUtil cartHttpCacheUtil) {
        this.modelGroupUtil = cartHttpCacheUtil;
    }

    public void setNoResponse(boolean z) {
        this.noResponse = z;
    }

    public void setOperationType(HashMap<String, String> hashMap) {
        this.operationType = hashMap;
    }

    public CartRequest(ArrayList<CartRequestOperate> arrayList) {
        this.noResponse = false;
        this.cartOperates = new ArrayList<>();
        this.isNeedCache = true;
        this.operationType = new HashMap<>();
        this.extFlag = new HashMap<>();
        this.extendParams = new HashMap<>();
        this.cartOperates = arrayList;
    }

    public CartRequest(CartRequestOperate cartRequestOperate) {
        this.noResponse = false;
        this.cartOperates = new ArrayList<>();
        this.isNeedCache = true;
        this.operationType = new HashMap<>();
        this.extFlag = new HashMap<>();
        this.extendParams = new HashMap<>();
        if (cartRequestOperate != null) {
            this.cartOperates.add(cartRequestOperate);
        }
    }

    public CartRequest(ArrayList<CartRequestOperate> arrayList, CartRequestCommon cartRequestCommon) {
        this.noResponse = false;
        this.cartOperates = new ArrayList<>();
        this.isNeedCache = true;
        this.operationType = new HashMap<>();
        this.extFlag = new HashMap<>();
        this.extendParams = new HashMap<>();
        this.cartOperates = arrayList;
        this.cartCommon = cartRequestCommon;
    }
}
