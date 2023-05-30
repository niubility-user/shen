package com.jingdong.common.entity.cart;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.cart.CartBaseTool;
import com.jingdong.common.cart.CartUniformState;
import com.jingdong.common.entity.Pack;
import com.jingdong.common.entity.Product;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class CartResponseInfoEntity implements SubmitOrderProductInfo, Parcelable {
    public static final Parcelable.Creator<CartResponseInfoEntity> CREATOR = new Parcelable.Creator<CartResponseInfoEntity>() { // from class: com.jingdong.common.entity.cart.CartResponseInfoEntity.1
        @Override // android.os.Parcelable.Creator
        public CartResponseInfoEntity createFromParcel(Parcel parcel) {
            return new CartResponseInfoEntity(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public CartResponseInfoEntity[] newArray(int i2) {
            return new CartResponseInfoEntity[i2];
        }
    };
    private static final String TAG = "CartResponseInfoEntity";
    public HashMap<String, String> abCards;
    public long abConfigs;
    public Bundle additionalDataBundle;
    public int allCheckedSku;
    public JDJSONArray asyncData;
    public String autoChangeProm;
    public String autoChangePromMsg;
    public CartBalanceInfo balanceInfo;
    public String bottomCoudanMsg;
    public String bottomRuleMsg;
    public int bottomType;
    public List<String> bubblesPriority;
    public boolean buyLimitSwitch;
    public ArrayList<CartCanGetCoupon> canGetCouponList;
    public String capacityFlag;
    public int cartAddClearGuide;
    public int cartClearGuide;
    public boolean cartClearShow;
    public int cartNum;
    public HashMap<String, Integer> cartNumGroup;
    private ArrayList<CartResponseShop> cartResponseShops;
    public CartResponseSoldOffShop cartResponseSoldOffShop;
    public CartSurveyInfo cartSurveyInfo;
    public int checkType;
    private HashMap<CartPackSummary, ArrayList<CartSkuSummary>> checkedCartPackMap;
    private ArrayList<CartResponseServiceSelected> checkedCartServiceList;
    private ArrayList<CartSkuSummary> checkedCartSkuList;
    public int checkedCartState;
    private ArrayList<CartResonseYBSelected> checkedCartYBList;
    private ArrayList<CartSkuSummary> checkedSkuOfThePacks;
    private ArrayList<CartPackSummary> checkedStatisticsPackList;
    private ArrayList<CartSkuSummary> checkedStatisticsSkuList;
    public int checkedWareNum;
    public int configVersion;
    public ConsolidatorAddress consolidatorAddress;
    public long countDownDelay;
    public boolean dataPaging;
    public boolean dataSplit;
    public long dcTagETS;
    public HashMap<String, String> dcTagUrl;
    public double discount;
    public CartEasyCombineEntity easyCombine;
    public CartEconomicalCard economicalCard;
    public CartEconomicalCardCoupon economicalCardCoupon;
    public int editCheckType;
    public HashMap<String, String> expLabelMap;
    public String fareDetailMsg;
    public HashMap<String, CartShopFareInfo> fareMap;
    public String fareRule;
    public int fareType;
    public CartJingTie firstJingTie;
    public int guideNum;
    public Boolean hasDaJiaDian;
    public String imageDomain;
    public int isEditNoCheck;
    public Boolean isEmpty;
    public int isNoCheck;
    public boolean isRequestEgg;
    public boolean isRequestPlus;
    public String jdBeanStatus;
    public int limitBookNum;
    public String limitBuyTip;
    public Integer limitNotBookNum;
    public CartLuxuryTopFloors luxuryTopFloors;
    public CartCheckOutDetail mCartCheckOutDetail;
    public JSONObject mcubeBottomJson;
    public String newPriceLimit;
    public CartNoticeNote noticeNote;
    public String noticeType;
    public int num;
    public int orderNumLimit;
    public int pageSize;
    public CartPlatformPerDiscountTopStrip platformDiscountBubbles;
    public CartPlatformPerDiscount platformPerDiscount;
    public CartPlatformPerDiscountTopStrip platformPerDiscountTopStrip;
    public ArrayList<CartPlatformPerDiscountTopStrip> platformPerDiscountTopStripList;
    public String plus95Balance;
    public boolean plusCartShow;
    public String plusFloorRequestParam;
    public ArrayList<CartPreferentialItem> preferentialDetail;
    public CartPreferentialSummary preferentialSummary;
    public PreferentialTitle preferentialTitle;
    public CartPresaleBubble presaleBubble;
    public HashMap<String, CartPrescriptionInfo> prescriptionMap;
    public double price;
    public String priceShow;
    public ArrayList<CartCanGetCoupon> promotionCouponList;
    public JDJSONObject promotionRelation;
    public int promotionSavePrice;
    public String promotionSavePriceTotal;
    public int promotionSwitch;
    public JDJSONObject promotionTotalInfo;
    public double rePrice;
    public String replaceSkus;
    public boolean showAddress;
    public boolean sortSwitchOpen;
    public HashMap<String, CartSplitBar> splitBarMap;
    public SpuLimitVo spuLimitVo;
    public int status;
    public boolean subFlow;
    public CartSuperSubsidy superBonus;
    public String tabClassifyName;
    public String tip_1;
    public String tip_2;
    public TodayPromotionVo todayPromotion;
    public String totalJdBeansNum;
    public String unJieSuan;
    public String updatePrice;

    public CartResponseInfoEntity() {
        this.dcTagUrl = new HashMap<>();
        this.expLabelMap = new HashMap<>();
    }

    private boolean isCanAdd(CartResponseSku cartResponseSku) {
        if (OKLog.D) {
            OKLog.d(TAG, " isCanAdd ---> getName  : " + cartResponseSku.getName());
        }
        int i2 = CartUniformState.specialId;
        if (i2 == 10) {
            if (!CartBaseTool.isSpecial(cartResponseSku.getSpecialId(), 0) && !CartBaseTool.isSpecial(cartResponseSku.getSpecialId(), 9)) {
                if (OKLog.D) {
                    OKLog.d(TAG, " isCanAdd ---> SETTLEMENT_TYPE_GLOBAL false : ");
                }
                return false;
            }
            if (OKLog.D) {
                OKLog.d(TAG, " isCanAdd ---> SETTLEMENT_TYPE_GLOBAL true : ");
            }
            return true;
        } else if (i2 == 12) {
            if (CartBaseTool.isSpecial(cartResponseSku.getSpecialId(), 4)) {
                if (OKLog.D) {
                    OKLog.d(TAG, " isCanAdd ---> SETTLEMENT_TYPE_OTC true : ");
                }
                return true;
            }
            if (OKLog.D) {
                OKLog.d(TAG, " isCanAdd ---> SETTLEMENT_TYPE_OTC false : ");
            }
            return false;
        } else if (i2 == 13) {
            if (CartBaseTool.isSpecial(cartResponseSku.getSpecialId(), 0) || CartBaseTool.isSpecial(cartResponseSku.getSpecialId(), 9)) {
                return false;
            }
            if (OKLog.D) {
                OKLog.d(TAG, " isCanAdd ---> normal true : ");
            }
            return true;
        } else if (!CartBaseTool.isSpecial(cartResponseSku.getSpecialId(), 0) && !CartBaseTool.isSpecial(cartResponseSku.getSpecialId(), 9) && !CartBaseTool.isSpecial(cartResponseSku.getSpecialId(), 4)) {
            if (OKLog.D) {
                OKLog.d(TAG, " isCanAdd ---> normal true : ");
            }
            return true;
        } else {
            if (OKLog.D) {
                OKLog.d(TAG, " isCanAdd ---> normal false : ");
            }
            return false;
        }
    }

    private void makeCheckedSkusAndPacks() {
        this.checkedStatisticsSkuList = new ArrayList<>();
        this.checkedStatisticsPackList = new ArrayList<>();
        this.checkedCartSkuList = new ArrayList<>();
        this.checkedCartPackMap = new HashMap<>();
        this.checkedCartYBList = new ArrayList<>();
        this.checkedCartServiceList = new ArrayList<>();
        this.checkedSkuOfThePacks = new ArrayList<>();
        if (this.cartResponseShops == null) {
            return;
        }
        for (int i2 = 0; i2 < this.cartResponseShops.size(); i2++) {
            ArrayList<? super CartSummary> cartSummary = this.cartResponseShops.get(i2).getCartSummary();
            if (cartSummary != null && cartSummary.size() != 0) {
                for (int i3 = 0; i3 < cartSummary.size(); i3++) {
                    CartSummary cartSummary2 = cartSummary.get(i3);
                    if (cartSummary2 != null) {
                        if (cartSummary2 instanceof CartResponseSku) {
                            CartResponseSku cartResponseSku = (CartResponseSku) cartSummary2;
                            if (cartResponseSku.isChecked() && isCanAdd(cartResponseSku)) {
                                this.checkedStatisticsSkuList.add(cartResponseSku);
                                this.checkedCartSkuList.add(cartResponseSku);
                                if (cartResponseSku.getYbSkus() != null && cartResponseSku.getYbSkus().size() > 0) {
                                    this.checkedCartYBList.addAll(cartResponseSku.getYbSkus());
                                }
                                if (cartResponseSku.getHomeServiceInfo() != null && cartResponseSku.getHomeServiceInfo().size() > 0) {
                                    this.checkedCartServiceList.addAll(cartResponseSku.getHomeServiceInfo());
                                }
                            }
                        } else if (cartSummary2 instanceof CartResponseSuit) {
                            CartResponseSuit cartResponseSuit = (CartResponseSuit) cartSummary2;
                            if (cartResponseSuit.itemType == 4) {
                                makeCommonPackList(cartResponseSuit);
                            } else {
                                ArrayList<? super CartSummary> childItems = cartResponseSuit.getChildItems();
                                ArrayList<CartSkuSummary> arrayList = new ArrayList<>();
                                for (int i4 = 0; i4 < childItems.size(); i4++) {
                                    CartSummary cartSummary3 = childItems.get(i4);
                                    int i5 = cartSummary3.itemType;
                                    if (i5 == 1) {
                                        CartResponseSku cartResponseSku2 = (CartResponseSku) cartSummary3;
                                        if (cartResponseSku2.isChecked() && isCanAdd(cartResponseSku2)) {
                                            this.checkedStatisticsSkuList.add(cartResponseSku2);
                                            this.checkedSkuOfThePacks.add(cartResponseSku2);
                                            arrayList.add(cartResponseSku2);
                                            if (cartResponseSku2.getYbSkus() != null && cartResponseSku2.getYbSkus().size() > 0) {
                                                this.checkedCartYBList.addAll(cartResponseSku2.getYbSkus());
                                            }
                                            if (cartResponseSku2.getHomeServiceInfo() != null && cartResponseSku2.getHomeServiceInfo().size() > 0) {
                                                this.checkedCartServiceList.addAll(cartResponseSku2.getHomeServiceInfo());
                                            }
                                        }
                                    } else if (i5 == 4) {
                                        makeCommonPackList((CartResponseSuit) cartSummary3);
                                    }
                                }
                                if (arrayList.size() > 0) {
                                    this.checkedCartPackMap.put(cartResponseSuit, arrayList);
                                }
                            }
                        }
                    }
                }
            }
        }
        this.allCheckedSku = this.checkedStatisticsSkuList.size() + this.checkedStatisticsPackList.size();
    }

    private void makeCommonPackList(CartResponseSuit cartResponseSuit) {
        if (cartResponseSuit.isChecked()) {
            ArrayList<? super CartSummary> childItems = cartResponseSuit.getChildItems();
            boolean z = false;
            for (int i2 = 0; i2 < childItems.size(); i2++) {
                CartSummary cartSummary = childItems.get(i2);
                if (cartSummary.itemType == 1) {
                    CartResponseSku cartResponseSku = (CartResponseSku) cartSummary;
                    boolean isCanAdd = isCanAdd(cartResponseSku);
                    if (!z && isCanAdd) {
                        z = true;
                    }
                    if (isCanAdd) {
                        if (cartResponseSku.getYbSkus() != null && cartResponseSku.getYbSkus().size() > 0) {
                            this.checkedCartYBList.addAll(cartResponseSku.getYbSkus());
                        }
                        if (cartResponseSku.getHomeServiceInfo() != null && cartResponseSku.getHomeServiceInfo().size() > 0) {
                            this.checkedCartServiceList.addAll(cartResponseSku.getHomeServiceInfo());
                        }
                        this.checkedSkuOfThePacks.add(cartResponseSku);
                    }
                } else if (OKLog.D) {
                    OKLog.d(TAG, " makeCheckedSkusAndPacks --->  : ");
                }
            }
            if (z) {
                this.checkedStatisticsPackList.add(new CartPackSummary(cartResponseSuit.getPackId(), Integer.valueOf(cartResponseSuit.getNum()), "", cartResponseSuit.skuUuid, cartResponseSuit.storeId, cartResponseSuit.useUuid));
                this.checkedCartPackMap.put(cartResponseSuit, null);
            }
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAbResult(String str) {
        return (this.abCards == null || TextUtils.isEmpty(str) || this.abCards.get(str) == null) ? "A" : this.abCards.get(str);
    }

    @Override // com.jingdong.common.entity.cart.SubmitOrderProductInfo
    public Bundle getAdditionalDataBundle() {
        return this.additionalDataBundle;
    }

    public ArrayList<Pack> getAllBuyPack() {
        ArrayList<Pack> arrayList = new ArrayList<>();
        for (int i2 = 0; i2 < this.cartResponseShops.size(); i2++) {
            ArrayList<? super CartSummary> cartSummary = this.cartResponseShops.get(i2).getCartSummary();
            if (cartSummary != null && cartSummary.size() != 0) {
                for (int i3 = 0; i3 < cartSummary.size(); i3++) {
                    CartSummary cartSummary2 = cartSummary.get(i3);
                    if (cartSummary2 != null && (cartSummary2 instanceof CartResponseSuit)) {
                        CartResponseSuit cartResponseSuit = (CartResponseSuit) cartSummary2;
                        if (cartResponseSuit.itemType == 4) {
                            arrayList.add(cartResponseSuit.toPack());
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public ArrayList<Product> getAllBuyProduct() {
        ArrayList<Product> arrayList = new ArrayList<>();
        if (this.cartResponseShops == null) {
            return null;
        }
        for (int i2 = 0; i2 < this.cartResponseShops.size(); i2++) {
            ArrayList<? super CartSummary> cartSummary = this.cartResponseShops.get(i2).getCartSummary();
            if (cartSummary != null && cartSummary.size() != 0) {
                for (int i3 = 0; i3 < cartSummary.size(); i3++) {
                    CartSummary cartSummary2 = cartSummary.get(i3);
                    if (cartSummary2 != null) {
                        if (cartSummary2 instanceof CartResponseSku) {
                            arrayList.add(((CartResponseSku) cartSummary2).toProduct());
                        } else if (cartSummary2 instanceof CartResponseSuit) {
                            CartResponseSuit cartResponseSuit = (CartResponseSuit) cartSummary2;
                            if (!TextUtils.equals(cartResponseSuit.getsType(), "4")) {
                                ArrayList<? super CartSummary> childItems = cartResponseSuit.getChildItems();
                                for (int i4 = 0; i4 < childItems.size(); i4++) {
                                    CartSummary cartSummary3 = childItems.get(i4);
                                    if (cartSummary3.itemType == 1) {
                                        arrayList.add(((CartSkuSummary) cartSummary3).toProduct());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public JSONArray getAllSelectedSkuId() {
        if (getCartResponseShops() == null) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        Iterator<CartResponseShop> it = getCartResponseShops().iterator();
        while (it.hasNext()) {
            CartResponseShop next = it.next();
            if (next != null) {
                Iterator<? super CartSummary> it2 = next.getCartSummary().iterator();
                while (it2.hasNext()) {
                    CartSummary next2 = it2.next();
                    if (next2 != null) {
                        if (next2 instanceof CartResponseSku) {
                            CartResponseSku cartResponseSku = (CartResponseSku) next2;
                            if (cartResponseSku.isChecked()) {
                                jSONArray.put(cartResponseSku.getSkuId());
                            }
                        } else if (next2 instanceof CartResponseSuit) {
                            CartResponseSuit cartResponseSuit = (CartResponseSuit) next2;
                            int size = cartResponseSuit.getChildItems().size();
                            for (int i2 = 0; i2 < size; i2++) {
                                CartSummary cartSummary = cartResponseSuit.getChildItems().get(i2);
                                if (cartSummary.itemType == 1) {
                                    CartResponseSku cartResponseSku2 = (CartResponseSku) cartSummary;
                                    if (cartResponseSku2.isChecked()) {
                                        jSONArray.put(cartResponseSku2.getSkuId());
                                    }
                                } else {
                                    ArrayList<? super CartSummary> childItems = ((CartResponseSuit) cartSummary).getChildItems();
                                    if (childItems != null) {
                                        int size2 = childItems.size();
                                        for (int i3 = 0; i3 < size2; i3++) {
                                            CartSummary cartSummary2 = childItems.get(i3);
                                            if (cartSummary2.itemType == 1) {
                                                CartResponseSku cartResponseSku3 = (CartResponseSku) cartSummary2;
                                                if (cartResponseSku3.isChecked()) {
                                                    jSONArray.put(cartResponseSku3.getSkuId());
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return jSONArray;
    }

    public List<String> getAllSkuId() {
        if (getCartResponseShops() == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<CartResponseShop> it = getCartResponseShops().iterator();
        while (it.hasNext()) {
            CartResponseShop next = it.next();
            if (next != null) {
                Iterator<? super CartSummary> it2 = next.getCartSummary().iterator();
                while (it2.hasNext()) {
                    CartSummary next2 = it2.next();
                    if (next2 != null) {
                        if (next2 instanceof CartResponseSku) {
                            arrayList.add(((CartResponseSku) next2).getSkuId());
                        } else if (next2 instanceof CartResponseSuit) {
                            CartResponseSuit cartResponseSuit = (CartResponseSuit) next2;
                            int size = cartResponseSuit.getChildItems().size();
                            for (int i2 = 0; i2 < size; i2++) {
                                CartSummary cartSummary = cartResponseSuit.getChildItems().get(i2);
                                if (cartSummary.itemType == 1) {
                                    arrayList.add(((CartResponseSku) cartSummary).getSkuId());
                                } else {
                                    ArrayList<? super CartSummary> childItems = ((CartResponseSuit) cartSummary).getChildItems();
                                    if (childItems != null) {
                                        int size2 = childItems.size();
                                        for (int i3 = 0; i3 < size2; i3++) {
                                            CartSummary cartSummary2 = childItems.get(i3);
                                            if (cartSummary2.itemType == 1) {
                                                arrayList.add(((CartResponseSku) cartSummary2).getSkuId());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public ArrayList<CartResponseShop> getCartResponseShops() {
        return this.cartResponseShops;
    }

    @Override // com.jingdong.common.entity.cart.SubmitOrderProductInfo
    public HashMap<CartPackSummary, ArrayList<CartSkuSummary>> getCheckedCartPackMap() {
        if (this.checkedCartPackMap == null) {
            makeCheckedSkusAndPacks();
        }
        return this.checkedCartPackMap;
    }

    public ArrayList<CartResponseServiceSelected> getCheckedCartServiceList() {
        if (this.checkedCartServiceList == null) {
            makeCheckedSkusAndPacks();
        }
        return this.checkedCartServiceList;
    }

    public ArrayList<CartSkuSummary> getCheckedCartSkuList() {
        if (this.checkedCartSkuList == null) {
            makeCheckedSkusAndPacks();
        }
        return this.checkedCartSkuList;
    }

    public ArrayList<CartResonseYBSelected> getCheckedCartYBList() {
        if (this.checkedCartYBList == null) {
            makeCheckedSkusAndPacks();
        }
        return this.checkedCartYBList;
    }

    public ArrayList<CartSkuSummary> getCheckedSkuOfThePacks() {
        if (this.checkedSkuOfThePacks == null) {
            makeCheckedSkusAndPacks();
        }
        return this.checkedSkuOfThePacks;
    }

    @Override // com.jingdong.common.entity.cart.SubmitOrderProductInfo
    public ArrayList<CartPackSummary> getCheckedStatisticsPackList() {
        if (this.checkedStatisticsPackList == null) {
            makeCheckedSkusAndPacks();
        }
        return this.checkedStatisticsPackList;
    }

    @Override // com.jingdong.common.entity.cart.SubmitOrderProductInfo
    public ArrayList<CartSkuSummary> getCheckedStatisticsSkuList() {
        if (this.checkedStatisticsSkuList == null) {
            makeCheckedSkusAndPacks();
        }
        return this.checkedStatisticsSkuList;
    }

    @Override // com.jingdong.common.entity.cart.SubmitOrderProductInfo
    public int getCheckedWareNum() {
        return this.checkedWareNum;
    }

    public double getDiscount() {
        return this.discount;
    }

    @Override // com.jingdong.common.entity.cart.SubmitOrderProductInfo
    public String getEasyBuySkuId() {
        return null;
    }

    public Boolean getIsEmpty() {
        Boolean bool = this.isEmpty;
        return bool == null ? Boolean.FALSE : bool;
    }

    public double getPrice() {
        return this.price;
    }

    public String getPriceShow() {
        String str = this.priceShow;
        return str == null ? "" : str;
    }

    public double getRePrice() {
        return this.rePrice;
    }

    public String getTip_1() {
        return TextUtils.isEmpty(this.tip_1) ? "" : this.tip_1;
    }

    public String getTip_2() {
        return TextUtils.isEmpty(this.tip_2) ? "" : this.tip_2;
    }

    @Override // com.jingdong.common.entity.cart.SubmitOrderProductInfo
    public String getUnJieSuan() {
        return this.unJieSuan;
    }

    public ArrayList<CartResponseServiceSelected> getUnOverlapCheckedCartServiceList() {
        if (this.checkedCartServiceList == null) {
            return null;
        }
        HashMap hashMap = new HashMap(16);
        Iterator<CartResponseServiceSelected> it = this.checkedCartServiceList.iterator();
        while (it.hasNext()) {
            CartResponseServiceSelected next = it.next();
            CartResponseServiceSelected cartResponseServiceSelected = (CartResponseServiceSelected) hashMap.get(next.getServiceSkuId());
            if (cartResponseServiceSelected == null) {
                hashMap.put(next.getServiceSkuId(), (CartResponseServiceSelected) next.clone());
            } else {
                CartResponseSku cartResponseSku = cartResponseServiceSelected.serviceSku;
                if (cartResponseSku != null && next.serviceSku != null) {
                    cartResponseServiceSelected.serviceSku.setNum(cartResponseSku.getNum() + next.serviceSku.getNum());
                }
            }
        }
        return new ArrayList<>(hashMap.values());
    }

    public ArrayList<CartResonseYBSelected> getUnOverlapCheckedCartYBList() {
        if (this.checkedCartYBList == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        Iterator<CartResonseYBSelected> it = this.checkedCartYBList.iterator();
        while (it.hasNext()) {
            CartResonseYBSelected next = it.next();
            CartResonseYBSelected cartResonseYBSelected = (CartResonseYBSelected) hashMap.get(next.getYbId());
            if (cartResonseYBSelected == null) {
                hashMap.put(next.getYbId(), (CartResonseYBSelected) next.clone());
            } else if (cartResonseYBSelected.getYbSku() != null && next.getYbSku() != null) {
                cartResonseYBSelected.getYbSku().setNum(cartResonseYBSelected.getYbSku().getNum() + next.getYbSku().getNum());
            }
        }
        return new ArrayList<>(hashMap.values());
    }

    public boolean isEditNoCheckStatus() {
        return this.isEditNoCheck == 1;
    }

    public void setCartResponseShops(ArrayList<CartResponseShop> arrayList) {
        this.cartResponseShops = arrayList;
    }

    @Override // com.jingdong.common.entity.cart.SubmitOrderProductInfo
    public JSONObject toCheckedCartStr() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            ArrayList<CartSkuSummary> checkedCartSkuList = getCheckedCartSkuList();
            if (checkedCartSkuList != null) {
                Iterator<CartSkuSummary> it = checkedCartSkuList.iterator();
                while (it.hasNext()) {
                    jSONArray.put(it.next().toSummaryParams());
                }
            }
            ArrayList<CartResonseYBSelected> unOverlapCheckedCartYBList = getUnOverlapCheckedCartYBList();
            if (unOverlapCheckedCartYBList != null) {
                Iterator<CartResonseYBSelected> it2 = unOverlapCheckedCartYBList.iterator();
                while (it2.hasNext()) {
                    CartResonseYBSelected next = it2.next();
                    if (next.getYbSku() != null) {
                        jSONArray.put(next.getYbSku().toSummaryParams());
                    }
                }
            }
            ArrayList<CartResponseServiceSelected> unOverlapCheckedCartServiceList = getUnOverlapCheckedCartServiceList();
            if (unOverlapCheckedCartServiceList != null) {
                Iterator<CartResponseServiceSelected> it3 = unOverlapCheckedCartServiceList.iterator();
                while (it3.hasNext()) {
                    CartResponseSku cartResponseSku = it3.next().serviceSku;
                    if (cartResponseSku != null) {
                        jSONArray.put(cartResponseSku.toSummaryParams());
                    }
                }
            }
            if (jSONArray.length() > 0) {
                jSONObject.put(CartConstant.KEY_THE_SKUS, jSONArray);
            }
            HashMap<CartPackSummary, ArrayList<CartSkuSummary>> checkedCartPackMap = getCheckedCartPackMap();
            JSONArray jSONArray2 = new JSONArray();
            if (checkedCartPackMap != null) {
                for (Map.Entry<CartPackSummary, ArrayList<CartSkuSummary>> entry : checkedCartPackMap.entrySet()) {
                    CartPackSummary key = entry.getKey();
                    ArrayList<CartSkuSummary> value = entry.getValue();
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("Id", key.getPackId());
                    jSONObject2.put("num", key.getNum());
                    if (value != null) {
                        if (TextUtils.equals(key.getsType(), "16")) {
                            jSONObject2.put("suitType", 10);
                            jSONObject2.put("sType", 13);
                        } else if (TextUtils.equals(key.getsType(), "11")) {
                            jSONObject2.put("suitType", 10);
                            jSONObject2.put("sType", "11");
                        }
                        JSONArray jSONArray3 = new JSONArray();
                        Iterator<CartSkuSummary> it4 = value.iterator();
                        while (it4.hasNext()) {
                            jSONArray3.put(it4.next().toSummaryParams());
                        }
                        if (jSONArray3.length() > 0) {
                            jSONObject2.put(CartConstant.KEY_THE_SKUS, jSONArray3);
                        }
                        ArrayList<? super CartSkuSummary> gifts = key.getGifts();
                        JSONArray jSONArray4 = new JSONArray();
                        for (int i2 = 0; i2 < gifts.size(); i2++) {
                            JSONObject summaryParams = gifts.get(i2).toSummaryParams();
                            summaryParams.put("awardType", 1);
                            jSONArray4.put(summaryParams);
                        }
                        if (jSONArray4.length() > 0) {
                            jSONObject2.put(CartConstant.KEY_GIFTS, jSONArray4);
                        }
                    } else {
                        jSONObject2.put("suitType", 6);
                        jSONObject2.put("sType", "4");
                    }
                    jSONArray2.put(jSONObject2);
                }
            }
            if (jSONArray2.length() > 0) {
                jSONObject.put(CartConstant.KEY_THE_PACKS, jSONArray2);
            }
            ArrayList<CartSkuSummary> checkedSkuOfThePacks = getCheckedSkuOfThePacks();
            if (checkedSkuOfThePacks != null && checkedSkuOfThePacks.size() > 0) {
                JSONArray jSONArray5 = new JSONArray();
                Iterator<CartSkuSummary> it5 = checkedSkuOfThePacks.iterator();
                while (it5.hasNext()) {
                    jSONArray5.put(it5.next().getSkuId());
                }
                jSONObject.put(CartConstant.KEY_SKU_OF_THEPACKS, jSONArray5);
            }
            ArrayList<CartResonseYBSelected> checkedCartYBList = getCheckedCartYBList();
            if (checkedCartYBList != null && checkedCartYBList.size() > 0) {
                JSONObject jSONObject3 = new JSONObject();
                Iterator<CartResonseYBSelected> it6 = checkedCartYBList.iterator();
                while (it6.hasNext()) {
                    CartResonseYBSelected next2 = it6.next();
                    jSONObject3.put(next2.toOrderParamsString(), next2.getYbNum());
                }
                jSONObject.put(CartConstant.KEY_YB_INFO, jSONObject3);
            }
            ArrayList<CartResponseServiceSelected> checkedCartServiceList = getCheckedCartServiceList();
            if (checkedCartServiceList != null && checkedCartServiceList.size() > 0) {
                JSONObject jSONObject4 = new JSONObject();
                Iterator<CartResponseServiceSelected> it7 = checkedCartServiceList.iterator();
                while (it7.hasNext()) {
                    CartResponseServiceSelected next3 = it7.next();
                    jSONObject4.put(next3.toOrderParamsString(), next3.getServiceSkuNum());
                }
                jSONObject.put(CartConstant.KEY_SERVICE_INFO, jSONObject4);
            }
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
        return jSONObject;
    }

    @Override // com.jingdong.common.entity.cart.SubmitOrderProductInfo
    public JSONObject toCheckedStatisticsStr() {
        JSONObject jSONObject = new JSONObject();
        try {
            ArrayList<CartSkuSummary> checkedStatisticsSkuList = getCheckedStatisticsSkuList();
            JSONArray jSONArray = new JSONArray();
            if (checkedStatisticsSkuList != null) {
                Iterator<CartSkuSummary> it = checkedStatisticsSkuList.iterator();
                while (it.hasNext()) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("Id", it.next().getSkuId());
                    SourceEntity sourceEntity = new SourceEntity("shoppingCart_webSite", null);
                    jSONObject2.put(CartConstant.KEY_SOURCE_TYPE, sourceEntity.getSourceType());
                    jSONObject2.put(CartConstant.KEY_SOURCE_VALUE, sourceEntity.getSourceValue());
                    jSONArray.put(jSONObject2);
                }
            }
            jSONObject.put(CartConstant.KEY_THE_SKUS, jSONArray);
            ArrayList<CartPackSummary> checkedStatisticsPackList = getCheckedStatisticsPackList();
            JSONArray jSONArray2 = new JSONArray();
            if (checkedStatisticsPackList != null) {
                Iterator<CartPackSummary> it2 = checkedStatisticsPackList.iterator();
                while (it2.hasNext()) {
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("Id", it2.next().getPackId());
                    SourceEntity sourceEntity2 = new SourceEntity("shoppingCart_webSite", null);
                    jSONObject3.put(CartConstant.KEY_SOURCE_TYPE, sourceEntity2.getSourceType());
                    jSONObject3.put(CartConstant.KEY_SOURCE_VALUE, sourceEntity2.getSourceValue());
                    jSONArray2.put(jSONObject3);
                }
            }
            jSONObject.put(CartConstant.KEY_THE_PACKS, jSONArray2);
        } catch (Exception e2) {
            if (OKLog.D) {
                OKLog.e("Temp", " toCheckedStatisticsStr -->> Exception " + e2.getMessage());
                OKLog.e(TAG, e2);
            }
        }
        return jSONObject;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.checkedWareNum);
        parcel.writeTypedList(this.cartResponseShops);
        parcel.writeString(this.unJieSuan);
        parcel.writeBundle(this.additionalDataBundle);
        parcel.writeInt(this.isNoCheck);
        parcel.writeInt(this.checkType);
    }

    /* JADX WARN: Removed duplicated region for block: B:178:0x01f7  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0225  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x027c  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x02a6  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x038d  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x03c9  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x03cb  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x0548  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public CartResponseInfoEntity(com.jd.framework.json.JDJSONObject r11, java.lang.String r12) {
        /*
            Method dump skipped, instructions count: 1372
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.entity.cart.CartResponseInfoEntity.<init>(com.jd.framework.json.JDJSONObject, java.lang.String):void");
    }

    protected CartResponseInfoEntity(Parcel parcel) {
        this.dcTagUrl = new HashMap<>();
        this.expLabelMap = new HashMap<>();
        this.checkedWareNum = parcel.readInt();
        this.cartResponseShops = parcel.createTypedArrayList(CartResponseShop.CREATOR);
        this.unJieSuan = parcel.readString();
        this.additionalDataBundle = parcel.readBundle(getClass().getClassLoader());
        this.isNoCheck = parcel.readInt();
        this.checkType = parcel.readInt();
    }
}
