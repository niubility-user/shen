package com.jingdong.common.entity.cart;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.cart.CartConfigState;
import com.jingdong.common.entity.Pack;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class CartResponseSuit extends CartPackSummary implements Parcelable {
    public static final Parcelable.Creator<CartResponseSuit> CREATOR = new Parcelable.Creator<CartResponseSuit>() { // from class: com.jingdong.common.entity.cart.CartResponseSuit.1
        @Override // android.os.Parcelable.Creator
        public CartResponseSuit createFromParcel(Parcel parcel) {
            return new CartResponseSuit(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public CartResponseSuit[] newArray(int i2) {
            return new CartResponseSuit[i2];
        }
    };
    private static final String TAG = "CartResponseSuit";
    public Double addMoney;
    public String addressDetail;
    private ArrayList<CartResponseGift> canSelectGifts;
    public ArrayList<CartPromotion> canSelectPromotions;
    public Integer canSelectedGiftNum;
    public String checkBoxText;
    public int checkType;
    public int countdownType;
    public String discount;
    public String editCheckBoxText;
    public boolean editCheckOnly;
    public int editCheckType;
    public String entryLabel;
    public String entryTip;
    public Map<String, String> fields;
    public int freshType;
    public String frontPrice;
    public int fullRefundType;
    public boolean isChufangYao;
    private boolean isDelete;
    public int isEditNoCheck;
    public boolean isHasStockLimit;
    public boolean isNStock;
    public int isNoCheck;
    public boolean isPetPrescription;
    public boolean isQzcPromotion;
    public boolean isReachCondition;
    public boolean isShowShoppingBag;
    public String linkUrl;
    public int maxNum;
    public String menuRelationTag;
    public long mergeCountDown;
    public String mergeInfo;
    public String mergeTime;
    public String mergeTitle;
    public String name;
    public Integer needMoney;
    public String orderColor;
    public String orderText;
    public int preSaleState;
    public String preferentialTags;
    public String prescriptionId;
    public String price;
    public String priceShow;
    private String promoLimitMsg;
    public String promoPrice;
    public String promotionId;
    public boolean promotionSplited;
    public double rePrice;
    public Boolean selectedGift;
    public int specialId;
    public String stillNeed;
    public String suitLabel;
    public String suitLabelTab;
    public String suitTip;
    public String tailPrice;
    public int totalJBean;
    public boolean unReleasePrice;
    public String uncheckTip;
    public String vid;
    public String vskuId;
    public CartResponseSku vskuSelf;
    public int yuyueState;
    public boolean ztsp;

    public CartResponseSuit(String str, Integer num, String str2) {
        super(str, num, str2);
    }

    private void resetEditCheckType() {
        if (this.editCheckOnly && CartConfigState.getInstance().specProductCheckedSkuIds.contains(this.skuUuid)) {
            this.editCheckType = 1;
        }
    }

    @Override // com.jingdong.common.entity.cart.CartPackSummary, com.jingdong.common.entity.cart.CartSummary, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public double getAddMoney() {
        Double d = this.addMoney;
        if (d == null) {
            return 0.0d;
        }
        return d.doubleValue();
    }

    public ArrayList<CartResponseGift> getCanSelectGifts() {
        return this.canSelectGifts;
    }

    public Integer getCanSelectedGiftNum() {
        Integer num = this.canSelectedGiftNum;
        if (num == null) {
            return 0;
        }
        return num;
    }

    public String getDiscount() {
        String str = this.discount;
        return str == null ? "" : str;
    }

    public String getName() {
        String str = this.name;
        return str == null ? "" : str;
    }

    public Integer getNeedMoney() {
        Integer num = this.needMoney;
        if (num == null) {
            return 0;
        }
        return num;
    }

    public String getPrice() {
        String str = this.price;
        return str == null ? "" : str;
    }

    public String getPriceShow() {
        String str = this.priceShow;
        return str == null ? "" : str;
    }

    public String getPromoLimitMsg() {
        return this.promoLimitMsg;
    }

    public double getRePrice() {
        return this.rePrice;
    }

    public Boolean getSelectedGift() {
        if (this.selectedGift == null) {
            this.selectedGift = Boolean.FALSE;
        }
        return this.selectedGift;
    }

    public String getSuitId() {
        return super.getPackId();
    }

    public String getSuitTip() {
        String str = this.suitTip;
        return str == null ? "" : str;
    }

    public String getSuitType() {
        return super.getsType();
    }

    public boolean isChecked() {
        return this.checkType == 1;
    }

    public boolean isDelete() {
        return this.isDelete;
    }

    public boolean isEditChecked() {
        return !isEditNoCheckStatus() && this.editCheckType == 1;
    }

    public boolean isEditNoCheckStatus() {
        return this.isEditNoCheck == 1;
    }

    public void setCanSelectGifts(ArrayList<CartResponseGift> arrayList) {
        this.canSelectGifts = arrayList;
    }

    public void setDelete(boolean z) {
        this.isDelete = z;
    }

    public void setPromoLimitMsg(String str) {
        this.promoLimitMsg = str;
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

    @Override // com.jingdong.common.entity.cart.CartPackSummary
    public Pack toPack() {
        Pack pack = super.toPack();
        pack.setName(this.name);
        return pack;
    }

    @Override // com.jingdong.common.entity.cart.CartPackSummary, com.jingdong.common.entity.cart.CartSummary, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeTypedList(this.canSelectGifts);
        parcel.writeInt(this.isNoCheck);
        parcel.writeInt(this.checkType);
        parcel.writeString(this.vid);
        parcel.writeString(this.vskuId);
        parcel.writeString(this.promotionId);
        parcel.writeInt(this.specialId);
        parcel.writeParcelable(this.vskuSelf, i2);
    }

    public CartResponseSuit(JDJSONObject jDJSONObject, String str, int i2) {
        super(jDJSONObject);
        if (jDJSONObject != null) {
            this.num = jDJSONObject.optInt(CartConstant.KEY_NUM_BIG);
            this.price = jDJSONObject.optString("Price");
            this.rePrice = jDJSONObject.optDouble(CartConstant.KEY_REPRICE, 0.0d);
            this.discount = jDJSONObject.optString(CartConstant.KEY_DISCOUNT);
            this.priceShow = jDJSONObject.optString("PriceShow");
            this.name = jDJSONObject.optString("Name");
            this.needMoney = Integer.valueOf(jDJSONObject.optInt(CartConstant.KEY_NEED_MONEY));
            setsType(jDJSONObject.optString(CartConstant.KEY_SUIT_TYPE));
            this.suitTip = jDJSONObject.optString(CartConstant.KEY_SUIT_TIP);
            this.selectedGift = Boolean.valueOf(jDJSONObject.optBoolean(CartConstant.KEY_SELETEED_GIFT));
            this.addMoney = Double.valueOf(jDJSONObject.optDouble(CartConstant.KEY_ADD_MONEY, 0.0d));
            this.canSelectedGiftNum = Integer.valueOf(jDJSONObject.optInt(CartConstant.KEY_CAN_SELETED_GIFT_NUM));
            setPackId(jDJSONObject.optString("Id"));
            this.isNoCheck = jDJSONObject.optInt("isNoCheck");
            this.checkType = jDJSONObject.optInt("CheckType");
            int optInt = jDJSONObject.optInt("editCheckType", -1);
            this.editCheckType = optInt;
            if (optInt == -1) {
                this.editCheckType = jDJSONObject.optInt("CheckType");
            }
            int optInt2 = jDJSONObject.optInt("isEditNoCheck", -1);
            this.isEditNoCheck = optInt2;
            if (optInt2 == -1) {
                this.isEditNoCheck = this.isNoCheck;
            }
            this.editCheckOnly = jDJSONObject.optBoolean("editCheckOnly");
            this.linkUrl = jDJSONObject.optString("linkUrl");
            this.promotionId = jDJSONObject.optString("promotionId");
            setSpecialId(jDJSONObject.optString("specialId"));
            this.isReachCondition = jDJSONObject.optBoolean(CartConstant.KEY_REACHCONDITION);
            this.ztsp = jDJSONObject.optBoolean("ztsp", false);
            this.promotionSplited = jDJSONObject.optBoolean(CartConstant.KEY_PROMOTIONSPLITED);
            this.suitLabel = jDJSONObject.optString(CartConstant.KEY_SUITLABEL);
            this.vid = jDJSONObject.optString(CartConstant.KEY_CART_VID);
            this.vskuId = jDJSONObject.optString("vskuId");
            this.stillNeed = jDJSONObject.optString(CartConstant.KEY_CART_STILL_NEED);
            this.countdownType = jDJSONObject.optInt(CartConstant.KEY_COUNT_DOWN_TYPE);
            this.mergeTitle = jDJSONObject.optString(CartConstant.KEY_COUNT_DOWN_TITLE);
            this.mergeInfo = jDJSONObject.optString(CartConstant.KEY_COUNT_DOWN_INFO);
            this.mergeTime = jDJSONObject.optString(CartConstant.KEY_COUNT_DOWN_TIME);
            this.mergeCountDown = jDJSONObject.optLong(CartConstant.KEY_COUNT_DOWN_TMIE_STAMP);
            this.freshType = jDJSONObject.optInt(CartConstant.KEY_FRESH_TYPE, -1);
            this.preferentialTags = jDJSONObject.optString("preferentialTags");
            this.canSelectPromotions = CartPromotion.toList(jDJSONObject.optJSONArray(CartConstant.KEY_SELECT_PROMOTIONS));
            JDJSONArray optJSONArray = jDJSONObject.optJSONArray(CartConstant.KEY_ITEMS);
            this.skuUuid = jDJSONObject.optString("skuUuid");
            if (i2 == 4) {
                resetEditCheckType();
            }
            int i3 = 1;
            if (optJSONArray != null) {
                int size = optJSONArray.size();
                int i4 = 1;
                for (int i5 = 0; i5 < size; i5++) {
                    JDJSONObject optJSONObject = optJSONArray.optJSONObject(i5);
                    if (optJSONObject != null) {
                        int optInt3 = optJSONObject.optInt(CartConstant.KEY_VENDOR_ITEM_TYPE, -1);
                        JDJSONObject optJSONObject2 = optJSONObject.optJSONObject(CartConstant.KEY_VENDOR_ITEM);
                        if (optInt3 == 1) {
                            CartResponseSku cartResponseSku = new CartResponseSku(optJSONObject2, str);
                            cartResponseSku.itemType = optInt3;
                            if (i4 == 1 && cartResponseSku.isEditNoCheck == 0 && cartResponseSku.editCheckType == 0) {
                                i4 = 0;
                            }
                            if (cartResponseSku.getJBeanPromotion() != null && cartResponseSku.getJBeanPromotion().checkType == 1) {
                                this.totalJBean += cartResponseSku.getJBeanPromotion().needJBeanNum;
                            }
                            this.childItems.add(cartResponseSku);
                            if (i2 == 4) {
                                if (!TextUtils.isEmpty(this.vskuId)) {
                                    cartResponseSku.rSuitId = this.vskuId;
                                } else {
                                    cartResponseSku.rSuitId = this.vid;
                                }
                            }
                        } else if (optInt3 == 4) {
                            CartResponseSuit cartResponseSuit = new CartResponseSuit(optJSONObject2, str, optInt3);
                            cartResponseSuit.itemType = optInt3;
                            this.totalJBean += cartResponseSuit.totalJBean;
                            if (i4 == 1 && cartResponseSuit.isEditNoCheck == 0 && cartResponseSuit.editCheckType == 0) {
                                i4 = 0;
                            }
                            this.childItems.add(cartResponseSuit);
                        }
                    }
                }
                i3 = i4;
            }
            if (i2 != 4) {
                this.editCheckType = i3;
            }
            JDJSONArray optJSONArray2 = jDJSONObject.optJSONArray(CartConstant.KEY_GIFTS);
            if (optJSONArray2 != null && optJSONArray2.size() > 0) {
                int size2 = optJSONArray2.size();
                this.gifts = new ArrayList<>(size2);
                for (int i6 = 0; i6 < size2; i6++) {
                    JDJSONObject optJSONObject3 = optJSONArray2.optJSONObject(i6);
                    if (optJSONObject3 != null) {
                        this.gifts.add(new CartResponseGift(optJSONObject3, str));
                    }
                }
            }
            JDJSONArray optJSONArray3 = jDJSONObject.optJSONArray(CartConstant.KEY_CAN_SELECT_GIFTS);
            if (optJSONArray3 != null && optJSONArray3.size() > 0) {
                int size3 = optJSONArray3.size();
                this.canSelectGifts = new ArrayList<>(size3);
                for (int i7 = 0; i7 < size3; i7++) {
                    JDJSONObject optJSONObject4 = optJSONArray3.optJSONObject(i7);
                    if (optJSONObject4 != null) {
                        this.canSelectGifts.add(new CartResponseGift(optJSONObject4, str));
                    }
                }
            }
            this.fullRefundType = jDJSONObject.optInt(CartConstant.KEY_SUIT_FULLREFUNDTYPE);
            this.entryLabel = jDJSONObject.optString(CartConstant.KEY_SUIT_ENTRYLABEL);
            this.entryTip = jDJSONObject.optString(CartConstant.KEY_SUIT_ENTRYTIP);
            this.promoLimitMsg = jDJSONObject.optString(CartConstant.KEY_SKU_SUIT_PROMOLIMITMSG);
            this.yuyueState = jDJSONObject.optInt(CartConstant.KEY_YUYUE_STATE, 0);
            this.menuRelationTag = jDJSONObject.optString("menuRelationTag", "");
            if (!TextUtils.isEmpty(this.vskuId)) {
                this.vskuSelf = new CartResponseSku(jDJSONObject.optJSONObject("vskuSelf"), str);
            }
            JDJSONObject optJSONObject5 = jDJSONObject.optJSONObject("fields");
            if (optJSONObject5 != null && optJSONObject5.keySet() != null && optJSONObject5.keySet().size() > 0) {
                this.fields = new HashMap(optJSONObject5.keySet().size());
                for (String str2 : optJSONObject5.keySet()) {
                    String optString = optJSONObject5.optString(str2);
                    if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(optString)) {
                        this.fields.put(str2, optString);
                    }
                }
            }
            this.checkBoxText = jDJSONObject.optString("checkBoxText");
            this.editCheckBoxText = jDJSONObject.optString("editCheckBoxText");
            this.preSaleState = jDJSONObject.optInt("preSaleState", -1);
            this.frontPrice = jDJSONObject.optString("frontPrice");
            this.tailPrice = jDJSONObject.optString("tailPrice");
            this.addressDetail = jDJSONObject.optString("addressDetail");
            this.orderText = jDJSONObject.optString("orderText");
            this.orderColor = jDJSONObject.optString("orderColor");
            this.unReleasePrice = jDJSONObject.optBoolean("unReleasePrice");
            this.isDelete = jDJSONObject.optBoolean("isDelete", false);
            this.promoPrice = jDJSONObject.optString("promoPrice");
            this.suitLabelTab = jDJSONObject.optString("suitLabelTab");
            this.storeId = jDJSONObject.optString("storeId");
            this.useUuid = jDJSONObject.optBoolean("useUuid");
            this.uncheckTip = jDJSONObject.optString("uncheckTip");
            this.isShowShoppingBag = jDJSONObject.optBoolean("isShowShoppingBag");
            this.prescriptionId = jDJSONObject.optString("prescriptionId", "");
            this.isChufangYao = jDJSONObject.optBoolean("isChufangYao");
            this.isPetPrescription = jDJSONObject.optBoolean("isPetPrescription", false);
            this.isQzcPromotion = jDJSONObject.optBoolean("isQzcPromotion", false);
        }
    }

    protected CartResponseSuit(Parcel parcel) {
        super(parcel);
        this.canSelectGifts = parcel.createTypedArrayList(CartResponseGift.CREATOR);
        this.isNoCheck = parcel.readInt();
        this.checkType = parcel.readInt();
        this.vid = parcel.readString();
        this.vskuId = parcel.readString();
        this.promotionId = parcel.readString();
        this.specialId = parcel.readInt();
        this.vskuSelf = (CartResponseSku) parcel.readParcelable(CartResponseSku.class.getClassLoader());
    }
}
