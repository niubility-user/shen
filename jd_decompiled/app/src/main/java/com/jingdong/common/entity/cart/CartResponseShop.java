package com.jingdong.common.entity.cart;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jd.dynamic.base.CachePool;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.cart.CartCommonUtil;
import com.jingdong.common.cart.CartJsonUtil;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class CartResponseShop implements Parcelable {
    public static final Parcelable.Creator<CartResponseShop> CREATOR = new Parcelable.Creator<CartResponseShop>() { // from class: com.jingdong.common.entity.cart.CartResponseShop.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartResponseShop createFromParcel(Parcel parcel) {
            return new CartResponseShop(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartResponseShop[] newArray(int i2) {
            return new CartResponseShop[i2];
        }
    };
    private static final String TAG = "CartResponseShop";
    public int anchorPointType;
    public String belowMoney;
    public String bizType;
    public boolean cannotJump;
    private ArrayList<? super CartSummary> cartSummary;
    public int checkType;
    public boolean complete;
    public long completeExp;
    public String crdImg;
    public int djStatus;
    public int editCheckType;
    public int effectiveness;
    public String essentialItemsMissing;
    public String fareBubbleMsg;
    public String fareMsg;
    public int fareNewFlag;
    public String fareRule;
    public int fareType;
    public int freeFreight;
    public int hasCoupon;
    public boolean hasSplitBar;
    public String iconImg;
    public Integer inCoverage;
    public int indexInAllShops;
    public String indexStr;
    public boolean isChecked;
    public boolean isCwbg;
    public int isEditNoCheck;
    public Integer isJumpShop;
    public int isNoCheck;
    public boolean isNoPage;
    public boolean isShowShoppingBag;
    public String jumpOrderUrl;
    public int jumpType;
    public String linkUrl;
    public String locationStoreId;
    public List<String> mustChooseCategory;
    public String overWeight;
    public int pageIndex;
    public String promotionId;
    public String serviceTime;
    public String serviceTimeNotice;
    public int shechiType;
    public int shopId;
    public String shopMsg;
    public String shopName;
    public int skuGiftType;
    public String skuNumberText;
    public String skuOfPromotion;
    public HashMap<String, CartShopFareInfo> skuOrderInfoMap;
    public int specialId;
    public String stillNeed;
    public String storeId;
    public Long subVendorType;
    public String tipOfPromotion;
    public String toAddEssentialItems;
    public int totalJBean;
    public long venderId;
    public String venderName;
    public Integer venderSource;
    public int venderType;
    public ArrayList<LabelProperty> vendorHead;
    public String vendorIcon;
    public double vendorPrice;
    public String vendorUid;
    public int zhibo;

    public CartResponseShop() {
        this.shopName = "";
        this.linkUrl = "";
        this.promotionId = "";
        this.indexInAllShops = -1;
    }

    public static ArrayList<CartResponseShop> toList(JDJSONObject jDJSONObject, String str) {
        if (OKLog.D) {
            OKLog.d("CartResponseShop toList", "  -->> start : ");
        }
        ArrayList<CartResponseShop> arrayList = null;
        if (jDJSONObject == null) {
            return null;
        }
        JDJSONArray optJSONArray = jDJSONObject.optJSONArray("vendors");
        if (optJSONArray != null && optJSONArray.size() != 0) {
            arrayList = new ArrayList<>(optJSONArray.size());
            int size = optJSONArray.size();
            for (int i2 = 0; i2 < size; i2++) {
                JDJSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                if (optJSONObject != null) {
                    arrayList.add(new CartResponseShop(optJSONObject, str));
                }
            }
            if (OKLog.D) {
                OKLog.d("CartResponseShop toList", "  -->> end : ");
            }
        }
        return arrayList;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ArrayList<? super CartSummary> getCartSummary() {
        return this.cartSummary;
    }

    public boolean isChecked() {
        return this.checkType == 1;
    }

    public boolean isEditChecked() {
        return !isEditNoCheckStatus() && this.editCheckType == 1;
    }

    public boolean isEditNoCheckStatus() {
        return this.isEditNoCheck == 1;
    }

    public void setCartSummary(ArrayList<? super CartSummary> arrayList) {
        this.cartSummary = arrayList;
    }

    public void setSpecialId(String str) {
        if (TextUtils.isEmpty(str)) {
            this.specialId = 0;
            return;
        }
        try {
            this.specialId = Integer.parseInt(str);
        } catch (Exception unused) {
            this.specialId = 0;
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.shopId);
        parcel.writeString(this.shopName);
        parcel.writeLong(this.venderId);
        parcel.writeString(this.venderName);
        parcel.writeInt(this.venderType);
        parcel.writeList(this.cartSummary);
        parcel.writeByte(this.isChecked ? (byte) 1 : (byte) 0);
        parcel.writeTypedList(this.vendorHead);
        parcel.writeInt(this.isNoCheck);
        parcel.writeInt(this.checkType);
    }

    public CartResponseShop(JDJSONObject jDJSONObject, String str) {
        JDJSONArray optJSONArray;
        this.shopName = "";
        this.linkUrl = "";
        this.promotionId = "";
        this.indexInAllShops = -1;
        if (jDJSONObject == null) {
            return;
        }
        this.venderId = jDJSONObject.optLong("vendorId", -1L);
        this.venderName = jDJSONObject.optString(CartConstant.KEY_VENDOR_NAME, "");
        this.venderType = jDJSONObject.optInt(CartConstant.KEY_VENDOR_TYPE, -1);
        this.shopId = jDJSONObject.optInt("shopId", -1);
        this.shopName = jDJSONObject.optString("shopName", "");
        this.hasCoupon = jDJSONObject.optInt(CartConstant.KEY_HAS_COUPON, 0);
        this.vendorPrice = jDJSONObject.optDouble(CartConstant.KEY_VENDOR_PRICE, 0.0d);
        this.freeFreight = jDJSONObject.optInt(CartConstant.KEY_CART_FREEFREIGHT, -1);
        setSpecialId(jDJSONObject.optString("specialId", ""));
        this.fareMsg = jDJSONObject.optString(CartConstant.KEY_CART_FAREMSG);
        this.shopMsg = jDJSONObject.optString(CartConstant.KEY_CART_SHOPMSG);
        this.fareType = jDJSONObject.optInt("fareType");
        this.skuOrderInfoMap = CartShopFareInfo.parseJson(jDJSONObject.optJSONObject(CartConstant.KEY_CART_FAREMAP));
        this.essentialItemsMissing = jDJSONObject.optString("essentialItemsMissing");
        this.toAddEssentialItems = jDJSONObject.optString("toAddEssentialItems");
        this.linkUrl = jDJSONObject.optString("linkUrl");
        this.fareRule = jDJSONObject.optString(CartConstant.KEY_CART_FARERULE, "");
        this.isNoCheck = jDJSONObject.optInt("isNoCheck");
        this.checkType = jDJSONObject.optInt("checkType");
        this.isEditNoCheck = jDJSONObject.optInt("isEditNoCheck", this.isNoCheck);
        this.editCheckType = jDJSONObject.optInt("editCheckType", this.checkType);
        this.serviceTime = jDJSONObject.optString(CartConstant.KEY_SHOP_SERVICETIME, "");
        this.iconImg = jDJSONObject.optString("iconImg", "");
        this.crdImg = jDJSONObject.optString("crdImg", "");
        this.storeId = jDJSONObject.optString("storeId", "");
        this.bizType = jDJSONObject.optString("bizType", "");
        this.isCwbg = jDJSONObject.optBoolean("isCwbg", false);
        this.djStatus = jDJSONObject.optInt(CartConstant.KEY_SHOP_DJSTATUS, 1);
        this.inCoverage = CartJsonUtil.optInt(jDJSONObject, "inCoverage");
        this.overWeight = jDJSONObject.optString(CartConstant.KEY_SHOP_OVERWEIGHT, "");
        this.belowMoney = jDJSONObject.optString(CartConstant.KEY_SHOP_BELOWMONEY, "");
        this.venderSource = Integer.valueOf(jDJSONObject.optInt(CartConstant.KEY_SHOP_VENDERSOURCE, -1));
        this.fareBubbleMsg = jDJSONObject.optString(CartConstant.KEY_SHOP_FAREBUBBLEMSG, "");
        this.fareNewFlag = jDJSONObject.optInt(CartConstant.KEY_SHOP_FARENEWFLAG, 0);
        this.zhibo = jDJSONObject.optInt("zhibo", -1);
        this.subVendorType = CartJsonUtil.optLong(jDJSONObject, "subVendorType");
        this.skuGiftType = jDJSONObject.optInt("skuGiftType");
        this.effectiveness = jDJSONObject.optInt("effectiveness");
        this.serviceTimeNotice = jDJSONObject.optString("serviceTimeNotice");
        this.cannotJump = jDJSONObject.optBoolean("cannotJump", false);
        this.isShowShoppingBag = jDJSONObject.optBoolean("isShowShoppingBag", false);
        this.complete = jDJSONObject.optBoolean("complete", true);
        this.pageIndex = jDJSONObject.optInt(CachePool.K_TAG_PAGE_INDEX, 1);
        this.indexStr = jDJSONObject.optString("indexStr", "");
        this.vendorUid = jDJSONObject.optString("vendorUid", "");
        this.hasSplitBar = jDJSONObject.optBoolean("hasSplitBar", false);
        this.completeExp = jDJSONObject.optLong("completeExp", 0L);
        this.isNoPage = jDJSONObject.optBoolean("isNoPage", false);
        this.shechiType = jDJSONObject.optInt("shechiType");
        this.vendorIcon = jDJSONObject.optString("vendorIcon", "");
        this.skuNumberText = jDJSONObject.optString("skuNumberText", "");
        this.jumpOrderUrl = jDJSONObject.optString("jumpOrderUrl", "");
        this.isJumpShop = Integer.valueOf(jDJSONObject.optInt("isJumpShop"));
        this.jumpType = jDJSONObject.optInt("jumpType");
        this.anchorPointType = jDJSONObject.optInt("anchorPointType");
        this.locationStoreId = jDJSONObject.optString("locationStoreId");
        JDJSONArray optJSONArray2 = jDJSONObject.optJSONArray("mustChooseCategory");
        if (optJSONArray2 != null && optJSONArray2.size() != 0) {
            int size = optJSONArray2.size();
            ArrayList arrayList = new ArrayList(size);
            for (int i2 = 0; i2 < size; i2++) {
                String optString = optJSONArray2.optString(i2);
                if (optString != null) {
                    arrayList.add(optString);
                }
            }
            this.mustChooseCategory = arrayList;
        }
        JDJSONObject optJSONObject = jDJSONObject.optJSONObject(CartConstant.KEY_VENDOR_LABELS);
        this.vendorHead = new ArrayList<>(3);
        if (optJSONObject != null && (optJSONArray = optJSONObject.optJSONArray(CartConstant.KEY_VENDOR_HEAD)) != null && optJSONArray.size() > 0) {
            int size2 = optJSONArray.size() <= 3 ? optJSONArray.size() : 3;
            for (int i3 = 0; i3 < size2; i3++) {
                JDJSONObject optJSONObject2 = optJSONArray.optJSONObject(i3);
                if (optJSONObject2 != null) {
                    this.vendorHead.add(LabelProperty.parseJson(optJSONObject2));
                }
            }
        }
        JDJSONArray optJSONArray3 = jDJSONObject.optJSONArray(CartConstant.KEY_VENDOR_SORTED);
        if (optJSONArray3 == null || optJSONArray3.size() == 0) {
            return;
        }
        int size3 = optJSONArray3.size();
        ArrayList<? super CartSummary> arrayList2 = new ArrayList<>(size3);
        int i4 = 1;
        int i5 = 0;
        for (int i6 = 0; i6 < size3; i6++) {
            JDJSONObject optJSONObject3 = optJSONArray3.optJSONObject(i6);
            if (optJSONObject3 != null) {
                int optInt = optJSONObject3.optInt(CartConstant.KEY_VENDOR_ITEM_TYPE, -1);
                JDJSONObject optJSONObject4 = optJSONObject3.optJSONObject(CartConstant.KEY_VENDOR_ITEM);
                if (optJSONObject4 != null) {
                    if (optInt == 1) {
                        CartResponseSku cartResponseSku = new CartResponseSku(optJSONObject4, str);
                        cartResponseSku.itemType = optInt;
                        if (i4 == 1 && cartResponseSku.isEditNoCheck == 0 && cartResponseSku.editCheckType == 0) {
                            i4 = 0;
                        }
                        if (cartResponseSku.getJBeanPromotion() != null && cartResponseSku.getJBeanPromotion().checkType == 1) {
                            i5 += cartResponseSku.getJBeanPromotion().needJBeanNum;
                        }
                        arrayList2.add(cartResponseSku);
                    } else if (optInt == 4 || optInt == 9 || optInt == 12) {
                        CartResponseSuit cartResponseSuit = new CartResponseSuit(optJSONObject4, str, optInt);
                        cartResponseSuit.itemType = optInt;
                        i5 += cartResponseSuit.totalJBean;
                        if (i4 == 1 && cartResponseSuit.isEditNoCheck == 0 && cartResponseSuit.editCheckType == 0) {
                            i4 = 0;
                        }
                        arrayList2.add(cartResponseSuit);
                    }
                }
            }
        }
        if (!CartCommonUtil.isCustomizedShop(this)) {
            this.editCheckType = i4;
        }
        this.totalJBean = i5;
        this.isChecked = this.checkType == 1;
        setCartSummary(arrayList2);
        if (this.venderType == 2) {
            ArrayList<? super CartSummary> arrayList3 = this.cartSummary;
            if ((arrayList3 == null ? 0 : arrayList3.size()) > 0) {
                CartSummary cartSummary = this.cartSummary.get(0);
                if (cartSummary.itemType == 1 || !(cartSummary instanceof CartResponseSuit)) {
                    return;
                }
                CartResponseSuit cartResponseSuit2 = (CartResponseSuit) cartSummary;
                this.promotionId = cartResponseSuit2.promotionId;
                this.stillNeed = cartResponseSuit2.stillNeed;
                ArrayList<? super CartSummary> arrayList4 = cartResponseSuit2.childItems;
                if ((arrayList4 == null ? 0 : arrayList4.size()) > 0) {
                    CartSummary cartSummary2 = cartResponseSuit2.childItems.get(0);
                    if (cartSummary2.itemType == 1) {
                        CartResponseSku cartResponseSku2 = (CartResponseSku) cartSummary2;
                        this.skuOfPromotion = cartResponseSku2.getSkuId();
                        Iterator<CartPromotion> it = cartResponseSku2.getCanSelectPromotions().iterator();
                        while (it.hasNext()) {
                            CartPromotion next = it.next();
                            if (next.checkType == 1) {
                                this.tipOfPromotion = next.title;
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    protected CartResponseShop(Parcel parcel) {
        this.shopName = "";
        this.linkUrl = "";
        this.promotionId = "";
        this.indexInAllShops = -1;
        this.shopId = parcel.readInt();
        this.shopName = parcel.readString();
        this.venderId = parcel.readLong();
        this.venderName = parcel.readString();
        this.venderType = parcel.readInt();
        ArrayList<? super CartSummary> arrayList = new ArrayList<>();
        this.cartSummary = arrayList;
        parcel.readList(arrayList, CartSummary.class.getClassLoader());
        this.isChecked = parcel.readByte() != 0;
        this.vendorHead = parcel.createTypedArrayList(LabelProperty.CREATOR);
        this.isNoCheck = parcel.readInt();
        this.checkType = parcel.readInt();
    }
}
