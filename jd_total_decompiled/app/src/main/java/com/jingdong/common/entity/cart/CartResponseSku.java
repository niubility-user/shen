package com.jingdong.common.entity.cart;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.alibaba.fastjson.parser.Feature;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.framework.json.TypeToken;
import com.jingdong.common.cart.CartCommonUtil;
import com.jingdong.common.cart.CartConfigState;
import com.jingdong.common.cart.CartJsonUtil;
import com.jingdong.common.entity.Product;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes5.dex */
public class CartResponseSku extends CartSkuSummary implements Parcelable, Cloneable {
    public static final Parcelable.Creator<CartResponseSku> CREATOR = new Parcelable.Creator<CartResponseSku>() { // from class: com.jingdong.common.entity.cart.CartResponseSku.2
        @Override // android.os.Parcelable.Creator
        public CartResponseSku createFromParcel(Parcel parcel) {
            return new CartResponseSku(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public CartResponseSku[] newArray(int i2) {
            return new CartResponseSku[i2];
        }
    };
    public String ad2cartTime;
    public long addCartTime;
    public String addressDetail;
    private ArrayList<CartResponseGift> affixes;
    private int awardType;
    public String backRedPacket;
    public String balanceEndTime;
    public long balanceEndTimeStamp;
    public String balanceStartTime;
    public long balanceStartTimeStamp;
    private String beanScore;
    public String beforePriceText;
    public Long brandId;
    private ArrayList<CartPromotion> canSelectPrices;
    private ArrayList<CartPromotion> canSelectPromotions;
    public int cardSpecialId;
    public CartCustomInfo cartCustomInfo;
    public CartExtraCommonData cartExtraCommonData;
    private CartStock cartStock;
    public String checkBoxText;
    public int checkType;
    private Integer cid;
    public String cidNames;
    public boolean complete;
    public long completeExp;
    public String contentCode;
    public String contentName;
    public String contentTitle;
    public String contentUrl;
    public CartResponseSku contractPhoneItem;
    public int contractPhoneType;
    private String countBgColor;
    private String countColor;
    public Integer countDownState;
    public Integer countdownType;
    public Integer czType;
    public int delGiftFlag;
    public String deleteBeforeType;
    public DialogEntryBelowName dialogEntryBelowName;
    private String discount;
    public HashMap<String, JDJSONObject> dynExtraFloors;
    public String editCheckBoxText;
    public boolean editCheckOnly;
    public int editCheckType;
    public ArrayList<CartExtFloor> extFloors;
    public LinkedHashMap<Integer, CartExtraInfo> extraInfos;
    public int extraTotalCount;
    public Map<String, String> fields;
    private String firpricetype;
    public Integer firstCid;
    public List<JDJSONObject> floorItems;
    public HashMap<String, CartFloorData> floorMap;
    public int freshType;
    public String frontPrice;
    public ArrayList<CartResponseSku> furnitureServices;
    private String giftMsg;
    public ArrayList<CartResponseSku> giftPackings;
    private ArrayList<CartResponseGift> giftPoolGifts;
    public String giftPoolPromoId;
    public int giftPoolType;
    private ArrayList<GiftPool> giftPools;
    private int giftsType;
    public boolean hasChange;
    public int hasUnitedText;
    private ArrayList<CartResponseServiceSelected> homeServiceInfo;
    public int homeWishFlagNums;
    public List<String> homeWishInfos;
    public LabelProperty imageTop;
    public boolean isAFOS;
    private Boolean isBook;
    public Integer isCanUseDQ;
    public Integer isCanUseJQ;
    public boolean isChufangYao;
    public boolean isContent;
    private boolean isDelete;
    public boolean isDonatedSku;
    public int isEditNoCheck;
    public boolean isIdentityBuyLimit;
    public boolean isInValidPhone;
    public int isInvalid;
    public boolean isKdmj;
    public boolean isLocService;
    public boolean isLsNewService;
    public int isNoCheck;
    public boolean isPetPrescription;
    public int isProvideService;
    public boolean isShowTime;
    public boolean isYjs;
    public boolean isYxChufangYao;
    public List<Map<String, String>> items;
    private CartPromotion jBeanPromotion;
    private ArrayList<String> jdCoupons;
    public CartEggPlus jingEggDaily;
    private String jingEggPromotionInfo;
    private String jingEggTitle;
    public String jtPromoText;
    public String jtTagDark;
    public String jtTagNormal;
    public String landedPrice;
    public int leftSlideInt;
    public int leftSlideIntForEdit;
    public int limitType;
    public String linkUrl;
    public CartResponseThreeCcSelected locInfo;
    private String locName;
    public String locationBalanceEndTime;
    public long locationBalanceEndTimeStamp;
    public Integer lowestBuy;
    public TimeOrderInfo mTimeOrderInfo;
    public ArrayList<CartResponseGift> manGifts;
    public String maskText;
    public int maskType;
    public String maskUrl;
    public String maxLimitMsg;
    public int maxNum;
    public Integer maxType;
    public String memberPriceType;
    public String menuRelationTag;
    public long mergeCountDown;
    public String mergeInfo;
    private String mergePlus;
    public String mergeTime;
    public String mergeTitle;
    private String message;
    public String minLimitMsg;
    public String miniPriceRevert;
    public boolean msbybt;
    public String mtaLimitType;
    public String multiPromoLimitMsg;
    private ArrayList<CartResponseGift> mustGifts;
    private String name;
    public boolean needShortTitle;
    public String nextEndTime;
    public boolean noPriceLoadYB;
    public String oftenBuy;
    public String orderColor;
    public String orderText;
    private int overseaPurchase;
    public String payBalancePlan;
    public String payBargainReal;
    public String pdpromType;
    public String plusPrice;
    public String plusPriceSpread;
    private String popAutoServiceName;
    private int popAutoServiceNums;
    private String popAutoStoreName;
    public String preSalePrice;
    public int preSaleState;
    public String preferentialTags;
    public String prescriptionId;
    private String price;
    private String priceAfter;
    private String priceDes;
    private String priceDesColor;
    private String pricePrim;
    public String priceRevert;
    private String priceShow;
    public ArrayList<LabelProperty> priceTail;
    public ArrayList<LabelProperty> priceTop;
    public String promoPrice;
    private CartPropertyTag propertyTags;
    private String propertyTagsShow;
    public Map<String, String> qualityInfo;
    public String rSuitId;
    private double rePrice;
    public String realSkuId;
    private String remainNum;
    private int remainNumInt;
    public String samePopSkuId;
    public String secKillBId;
    public int seckillType;
    public Integer secondCid;
    private String secpricetype;
    public String seriesName;
    public int showJT;
    private ArrayList<CartResponseGift> sipGifts;
    public String sipMarkText;
    public String sipPromotionId;
    public String sipSType;
    public boolean sipUseUuid;
    public String skHighPri;
    public ArrayList<LabelProperty> skuActivity;
    public ArrayList<LabelProperty> skuHeadNew;
    public ArrayList<LabelProperty> skuMiddle;
    public String skuMiddleMta;
    public int skuType;
    public String soldRate;
    private long specialId;
    public String stockBigPromotionShow;
    public int stockCode;
    private String stockState;
    public String strategyId;
    public CartSuperimposePromotion superimposePromotions;
    public List<CartChangePromotionText> superimposePromotionsText;
    public String tailPrice;
    private ArrayList<CartResponseThreeCcSelected> threeCcInfo;
    public ArrayList<CartResponseThreeCcSelected> threeCcInfoWithoutLoc;
    public ArrayList<LabelProperty> titleBelow;
    public ArrayList<LabelProperty> titleHead;
    public HashSet<String> titleType;
    public boolean unReleasePrice;
    public String uniqueId;
    public String unitedText;
    public String unitedTextShow;
    public String urlStr;
    public int urlType;
    public int vendorId;
    private String weight;
    private ArrayList<CartResonseYB> ybCanSelectedCategorys;
    protected ArrayList<CartResonseYBSelected> ybSkus;
    public String year;
    public int yuyueState;

    public CartResponseSku() {
        this.qualityInfo = new HashMap();
        this.overseaPurchase = -1;
        this.urlType = -1;
        this.extraTotalCount = 0;
    }

    private CartEggPlus parseEggPlusData(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return null;
        }
        return new CartEggPlus(jDJSONObject);
    }

    private void resetEditCheckType() {
        if (this.editCheckOnly && CartConfigState.getInstance().specProductCheckedSkuIds.contains(this.uniqueId)) {
            this.editCheckType = 1;
        }
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override // com.jingdong.common.entity.cart.CartSkuSummary, com.jingdong.common.entity.cart.CartSummary, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ArrayList<CartResponseGift> getAffixes() {
        return this.affixes;
    }

    public int getAwardType() {
        return this.awardType;
    }

    public String getBeanScore() {
        return this.beanScore;
    }

    public ArrayList<CartPromotion> getCanSelectPrices() {
        return this.canSelectPrices;
    }

    public ArrayList<CartPromotion> getCanSelectPromotions() {
        return this.canSelectPromotions;
    }

    public Integer getCid() {
        return this.cid;
    }

    public String getCountBgColor() {
        return this.countBgColor;
    }

    public String getCountColor() {
        return this.countColor;
    }

    public String getDiscount() {
        String str = this.discount;
        return str == null ? "" : str;
    }

    public String getFirpricetype() {
        return this.firpricetype;
    }

    public String getGiftMsg() {
        return this.giftMsg;
    }

    public ArrayList<CartResponseGift> getGiftPoolGifts() {
        return this.giftPoolGifts;
    }

    public ArrayList<GiftPool> getGiftPools() {
        return this.giftPools;
    }

    public int getGiftsType() {
        return this.giftsType;
    }

    public ArrayList<CartResponseServiceSelected> getHomeServiceInfo() {
        if (this.homeServiceInfo == null) {
            this.homeServiceInfo = new ArrayList<>();
        }
        return this.homeServiceInfo;
    }

    public CartPromotion getJBeanPromotion() {
        return this.jBeanPromotion;
    }

    public ArrayList<String> getJdCoupons() {
        return this.jdCoupons;
    }

    public String getJingEggPromotionInfo() {
        return this.jingEggPromotionInfo;
    }

    public String getJingEggTitle() {
        return this.jingEggTitle;
    }

    public String getLocName() {
        return this.locName;
    }

    public String getMergePlus() {
        return this.mergePlus;
    }

    public String getMessage() {
        return this.message;
    }

    public ArrayList<CartResponseGift> getMustGifts() {
        return this.mustGifts;
    }

    public String getName() {
        String str = this.name;
        return str == null ? "" : str;
    }

    public int getOverseaPurchase() {
        return this.overseaPurchase;
    }

    public String getPopAutoServiceName() {
        return this.popAutoServiceName;
    }

    public int getPopAutoServiceNums() {
        return this.popAutoServiceNums;
    }

    public String getPopAutoStoreName() {
        return this.popAutoStoreName;
    }

    public String getPrice() {
        String str = this.price;
        return str == null ? "" : str;
    }

    public String getPriceAfter() {
        return this.priceAfter;
    }

    public String getPriceDes() {
        return this.priceDes;
    }

    public String getPriceDesColor() {
        return this.priceDesColor;
    }

    public String getPricePrim() {
        return this.pricePrim;
    }

    public String getPriceShow() {
        return this.priceShow;
    }

    public CartPropertyTag getPropertyTags() {
        return this.propertyTags;
    }

    public String getPropertyTagsShow() {
        return this.propertyTagsShow;
    }

    public double getRePrice() {
        return this.rePrice;
    }

    public String getRemainNum() {
        return this.remainNum;
    }

    public int getRemainNumInt() {
        return this.remainNumInt;
    }

    public String getSamePopSkuId() {
        return this.samePopSkuId;
    }

    public String getSecpricetype() {
        return this.secpricetype;
    }

    public String getSeriesName() {
        return this.seriesName;
    }

    public ArrayList<CartResponseGift> getSipGifts() {
        return this.sipGifts;
    }

    public long getSpecialId() {
        return this.specialId;
    }

    public int getStockCode() {
        return this.stockCode;
    }

    public String getStockState() {
        return this.stockState;
    }

    public ArrayList<CartResponseThreeCcSelected> getThreeCcInfo() {
        if (this.threeCcInfo == null) {
            this.threeCcInfo = new ArrayList<>();
        }
        return this.threeCcInfo;
    }

    public String getWeight() {
        return this.weight;
    }

    public ArrayList<CartResonseYB> getYbCanSelectedCategorys() {
        return this.ybCanSelectedCategorys;
    }

    public ArrayList<CartResonseYBSelected> getYbSkus() {
        if (this.ybSkus == null) {
            this.ybSkus = new ArrayList<>();
        }
        return this.ybSkus;
    }

    public String getYear() {
        return this.year;
    }

    public boolean isBook() {
        Boolean bool = this.isBook;
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
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

    public void setAwardType(int i2) {
        this.awardType = i2;
    }

    public void setBeanScore(String str) {
        this.beanScore = str;
    }

    public void setBook(Boolean bool) {
        this.isBook = bool;
    }

    public void setCanSelectPrices(ArrayList<CartPromotion> arrayList) {
        if (arrayList == null) {
            this.canSelectPrices = new ArrayList<>();
        } else {
            this.canSelectPrices = arrayList;
        }
    }

    public void setCanSelectPromotions(ArrayList<CartPromotion> arrayList) {
        if (arrayList == null) {
            this.canSelectPromotions = new ArrayList<>();
        } else {
            this.canSelectPromotions = arrayList;
        }
    }

    public void setCartStock(CartStock cartStock) {
        this.cartStock = cartStock;
    }

    public void setCheckType(int i2) {
        this.checkType = i2;
    }

    public void setCid(Integer num) {
        this.cid = num;
    }

    public void setCountBgColor(String str) {
        this.countBgColor = str;
    }

    public void setCountColor(String str) {
        this.countColor = str;
    }

    public void setDelete(boolean z) {
        this.isDelete = z;
    }

    public void setDiscount(String str) {
        this.discount = str;
    }

    public void setFirpricetype(String str) {
        this.firpricetype = str;
    }

    public void setGiftMsg(String str) {
        this.giftMsg = str;
    }

    public void setGiftPoolGifts(ArrayList<CartResponseGift> arrayList) {
        this.giftPoolGifts = arrayList;
    }

    public void setGiftPools(ArrayList<GiftPool> arrayList) {
        this.giftPools = arrayList;
    }

    public void setHomeServiceInfo(ArrayList<CartResponseServiceSelected> arrayList) {
        this.homeServiceInfo = arrayList;
    }

    public void setJBeanPromotion(CartPromotion cartPromotion) {
        this.jBeanPromotion = cartPromotion;
    }

    public void setJdCoupons(ArrayList<String> arrayList) {
        this.jdCoupons = arrayList;
    }

    public void setJingEggPromotionInfo(String str) {
        this.jingEggPromotionInfo = str;
    }

    public void setJingEggTitle(String str) {
        this.jingEggTitle = str;
    }

    public void setLocName(String str) {
        this.locName = str;
    }

    public void setMergePlus(String str) {
        this.mergePlus = str;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setMustGifts(ArrayList<CartResponseGift> arrayList) {
        this.mustGifts = arrayList;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setOverseaPurchase(int i2) {
        this.overseaPurchase = i2;
    }

    public void setPopAutoServiceName(String str) {
        this.popAutoServiceName = str;
    }

    public void setPopAutoServiceNums(int i2) {
        this.popAutoServiceNums = i2;
    }

    public void setPopAutoStoreName(String str) {
        this.popAutoStoreName = str;
    }

    public void setPrice(String str) {
        this.price = str;
    }

    public void setPriceAfter(String str) {
        this.priceAfter = str;
    }

    public void setPriceDes(String str) {
        this.priceDes = str;
    }

    public void setPriceDesColor(String str) {
        this.priceDesColor = str;
    }

    public void setPricePrim(String str) {
        this.pricePrim = str;
    }

    public void setPriceShow(String str) {
        this.priceShow = str;
    }

    public void setPropertyTags(CartPropertyTag cartPropertyTag) {
        this.propertyTags = cartPropertyTag;
    }

    public void setPropertyTagsShow(String str) {
        this.propertyTagsShow = str;
    }

    public void setRePrice(double d) {
        this.rePrice = d;
    }

    public void setRemainNum(String str) {
        this.remainNum = str;
    }

    public void setRemainNumInt(int i2) {
        this.remainNumInt = i2;
    }

    public void setSamePopSkuId(String str) {
        this.samePopSkuId = str;
    }

    public void setSecpricetype(String str) {
        this.secpricetype = str;
    }

    public void setSeriesName(String str) {
        this.seriesName = str;
    }

    public void setSipGifts(ArrayList<CartResponseGift> arrayList) {
        this.sipGifts = arrayList;
    }

    public void setSpecialId(String str) {
        if (TextUtils.isEmpty(str)) {
            this.specialId = 0L;
            return;
        }
        try {
            this.specialId = Long.parseLong(str);
        } catch (Exception unused) {
            this.specialId = 0L;
        }
    }

    public void setStockState(String str) {
        this.stockState = str;
    }

    public void setThreeCcInfo(ArrayList<CartResponseThreeCcSelected> arrayList) {
        this.threeCcInfo = arrayList;
    }

    public void setYbCanSelectedCategorys(ArrayList<CartResonseYB> arrayList) {
        this.ybCanSelectedCategorys = arrayList;
    }

    public void setYbCategorys(ArrayList<CartResonseYBSelected> arrayList) {
        this.ybSkus = arrayList;
    }

    public void setYear(String str) {
        this.year = str;
    }

    @Override // com.jingdong.common.entity.cart.CartSkuSummary
    public Product toProduct() {
        Product product = super.toProduct();
        product.setName(this.name);
        return product;
    }

    @Override // com.jingdong.common.entity.cart.CartSkuSummary, com.jingdong.common.entity.cart.CartSummary, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeString(this.name);
        parcel.writeTypedList(this.ybCanSelectedCategorys);
        parcel.writeTypedList(this.homeServiceInfo);
        parcel.writeTypedList(this.threeCcInfo);
        parcel.writeTypedList(this.giftPackings);
        parcel.writeInt(this.isNoCheck);
        parcel.writeInt(this.checkType);
        parcel.writeString(this.stockState);
        parcel.writeInt(this.stockCode);
        parcel.writeLong(this.specialId);
        parcel.writeString(this.giftMsg);
        parcel.writeInt(this.giftsType);
        parcel.writeInt(this.overseaPurchase);
        parcel.writeTypedList(this.mustGifts);
        parcel.writeTypedList(this.giftPoolGifts);
        parcel.writeTypedList(this.affixes);
        parcel.writeTypedList(this.giftPools);
        parcel.writeTypedList(this.furnitureServices);
        parcel.writeString(this.giftPoolPromoId);
        parcel.writeInt(this.isInvalid);
        parcel.writeTypedList(this.ybSkus);
    }

    /* loaded from: classes5.dex */
    public static class Tag implements Parcelable {
        public static final Parcelable.Creator<Tag> CREATOR = new Parcelable.Creator<Tag>() { // from class: com.jingdong.common.entity.cart.CartResponseSku.Tag.1
            @Override // android.os.Parcelable.Creator
            public Tag createFromParcel(Parcel parcel) {
                return new Tag(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public Tag[] newArray(int i2) {
                return new Tag[i2];
            }
        };
        private Integer tag;

        public Tag(Integer num) {
            this.tag = num;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public Integer getTag() {
            Integer num = this.tag;
            if (num == null) {
                return 0;
            }
            return num;
        }

        public void setTag(Integer num) {
            this.tag = num;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeValue(this.tag);
        }

        protected Tag(Parcel parcel) {
            this.tag = (Integer) parcel.readValue(Integer.class.getClassLoader());
        }
    }

    public CartResponseSku(String str, Integer num) {
        super(str, num.intValue());
        this.qualityInfo = new HashMap();
        this.overseaPurchase = -1;
        this.urlType = -1;
        this.extraTotalCount = 0;
    }

    public CartResponseSku(JDJSONObject jDJSONObject, String str) {
        super(jDJSONObject);
        StringBuilder sb;
        String str2;
        JDJSONObject optJSONObject;
        JDJSONArray optJSONArray;
        JDJSONArray optJSONArray2;
        this.qualityInfo = new HashMap();
        this.overseaPurchase = -1;
        this.urlType = -1;
        this.extraTotalCount = 0;
        if (jDJSONObject == null) {
            return;
        }
        this.imageDomail = str;
        setPrice(jDJSONObject.optString("Price"));
        setRePrice(jDJSONObject.optDouble(CartConstant.KEY_REPRICE, 0.0d));
        setDiscount(jDJSONObject.optString(CartConstant.KEY_DISCOUNT));
        setPriceShow(jDJSONObject.optString("PriceShow"));
        this.priceRevert = jDJSONObject.optString(CartConstant.KEY_PRICE_REVERT);
        this.miniPriceRevert = jDJSONObject.optString(CartConstant.KEY_MINIPRICEREVERT);
        setYear(jDJSONObject.optString(CartConstant.KEY_CART_YEAR));
        setSeriesName(jDJSONObject.optString(CartConstant.KEY_CART_SERIESNAME));
        setAwardType(jDJSONObject.optInt(CartConstant.KEY_AWARD_TYPE));
        setName(jDJSONObject.optString("Name"));
        this.isNoCheck = jDJSONObject.optInt("isNoCheck");
        setCheckType(jDJSONObject.optInt("CheckType"));
        int optInt = jDJSONObject.optInt("editCheckType", -1);
        this.editCheckType = optInt;
        if (optInt == -1) {
            this.editCheckType = jDJSONObject.optInt("CheckType");
        }
        this.editCheckOnly = jDJSONObject.optBoolean("editCheckOnly");
        this.isDelete = jDJSONObject.optBoolean("isDelete", false);
        this.isKdmj = jDJSONObject.optBoolean("isKdmj");
        setImgUrl(jDJSONObject.optString("ImgUrl"));
        setPropertyTags(new CartPropertyTag(jDJSONObject.optJSONObject("propertyTags")));
        setPropertyTagsShow(jDJSONObject.optString(CartConstant.KEY_SKU_PROPERTY_TAGS_SHOW));
        setJingEggTitle(jDJSONObject.optString(CartConstant.KEY_JING_EGG_TITLE));
        setJingEggPromotionInfo(jDJSONObject.optString(CartConstant.KEY_JING_EGG_PROMOTION_INFO));
        this.jingEggDaily = parseEggPlusData(jDJSONObject.optJSONObject("jingEggDaily"));
        this.isShowTime = jDJSONObject.optBoolean("isShowTime");
        setBook(Boolean.valueOf(jDJSONObject.optBoolean(CartConstant.KEY_IS_BOOK)));
        setMessage(jDJSONObject.optString("msg"));
        setSpecialId(jDJSONObject.optString("specialId"));
        setGiftMsg(jDJSONObject.optString(CartConstant.KEY_SKU_GIFTMSG));
        setRemainNum(jDJSONObject.optString(CartConstant.KEY_SKU_REMAINNUM));
        setStockState(jDJSONObject.optString(CartConstant.KEY_SKU_STOCKSTATE));
        setSamePopSkuId(jDJSONObject.optString(CartConstant.KEY_SKU_SAMEPOP));
        setRemainNumInt(jDJSONObject.optInt(CartConstant.KEY_SKU_REMAINNUM_INT));
        setCid(CartJsonUtil.optInt(jDJSONObject, "cid"));
        this.skHighPri = jDJSONObject.optString("skHighPri");
        this.firstCid = CartJsonUtil.optInt(jDJSONObject, CartConstant.KEY_SKU_FIRSTCID);
        this.secondCid = CartJsonUtil.optInt(jDJSONObject, CartConstant.KEY_SKU_SECONDCID);
        this.brandId = CartJsonUtil.optLong(jDJSONObject, CartConstant.KEY_SKU_BRANDID);
        this.lowestBuy = CartJsonUtil.optInt(jDJSONObject, CartConstant.KEY_SKU_LOWESTBUY);
        this.stockCode = jDJSONObject.optInt(CartConstant.KEY_SKU_STOCKCODE);
        this.weight = jDJSONObject.optString(CartConstant.KEY_SKU_WEIGHT);
        this.leftSlideInt = jDJSONObject.optInt("leftSlideInt");
        this.leftSlideIntForEdit = jDJSONObject.optInt("leftSlideIntForEdit");
        this.isAFOS = jDJSONObject.optBoolean("isAFOS");
        this.noPriceLoadYB = jDJSONObject.optBoolean("noPriceLoadYB");
        this.isDonatedSku = jDJSONObject.optBoolean("isDonatedSku");
        this.isIdentityBuyLimit = jDJSONObject.optBoolean("isIdentityBuyLimit");
        if (this.isBook.booleanValue()) {
            this.maxNum = jDJSONObject.optInt(CartConstant.KEY_SKU_MAXNUM, 1000);
        } else {
            this.maxNum = jDJSONObject.optInt(CartConstant.KEY_SKU_MAXNUM, 200);
        }
        this.limitType = jDJSONObject.optInt(CartConstant.KEY_SKU_LIMITTYPE, 0);
        this.cardSpecialId = jDJSONObject.optInt(CartConstant.KEY_SKU_CARDSPECIALID);
        this.isProvideService = jDJSONObject.optInt(CartConstant.KEY_CART_IS_PROVIDE_SERVICE, 0);
        this.giftsType = jDJSONObject.optInt(CartConstant.KEY_GIFTS_TYPE);
        this.overseaPurchase = jDJSONObject.optInt("overseaPurchase");
        this.popAutoServiceName = jDJSONObject.optString(CartConstant.KEY_CART_POPAUTOSERVICENAME);
        this.popAutoServiceNums = jDJSONObject.optInt(CartConstant.KEY_CART_POP_SERVICES_NUM);
        this.popAutoStoreName = jDJSONObject.optString(CartConstant.KEY_CART_POPAUTOSTORENAME);
        setJBeanPromotion(new CartPromotion(jDJSONObject.optJSONObject(CartConstant.KEY_JBEANS_PROMOTIONS), 0));
        setGiftPools(GiftPool.toList(jDJSONObject.optJSONArray("giftPools"), str));
        this.preferentialTags = jDJSONObject.optString("preferentialTags");
        this.countdownType = CartJsonUtil.optInt(jDJSONObject, CartConstant.KEY_COUNT_DOWN_TYPE);
        this.showJT = jDJSONObject.optInt(CartConstant.KEY_SKU_SHOW_JING_TIE);
        this.mergeTitle = jDJSONObject.optString(CartConstant.KEY_COUNT_DOWN_TITLE);
        this.mergeInfo = jDJSONObject.optString(CartConstant.KEY_COUNT_DOWN_INFO);
        this.pricePrim = jDJSONObject.optString("pricePrim");
        this.firpricetype = jDJSONObject.optString("firpricetype");
        this.secpricetype = jDJSONObject.optString("secpricetype");
        this.priceDes = jDJSONObject.optString("priceDes");
        this.priceDesColor = jDJSONObject.optString("priceDesColor");
        this.mergePlus = jDJSONObject.optString("mergePlus");
        this.countColor = jDJSONObject.optString("countColor");
        this.countBgColor = jDJSONObject.optString("countBgColor");
        this.priceAfter = jDJSONObject.optString("priceAfter");
        this.nextEndTime = jDJSONObject.optString("nextEndTime");
        this.mergeTime = jDJSONObject.optString(CartConstant.KEY_COUNT_DOWN_TIME);
        this.mergeCountDown = jDJSONObject.optLong(CartConstant.KEY_COUNT_DOWN_TMIE_STAMP);
        this.freshType = jDJSONObject.optInt(CartConstant.KEY_FRESH_TYPE);
        this.countDownState = CartJsonUtil.optInt(jDJSONObject, "countDownState");
        JDJSONObject optJSONObject2 = jDJSONObject.optJSONObject(CartConstant.KEY_EXTFLAG);
        if (optJSONObject2 != null) {
            this.extFlag = new CartExtFlag(optJSONObject2);
        }
        this.mtaLimitType = jDJSONObject.optString("mtaLimitType");
        setBeanScore(jDJSONObject.optString(CartConstant.KEY_SKU_BEANSCORE));
        this.plusPrice = jDJSONObject.optString(CartConstant.KEY_PLUSPRICE);
        this.plusPriceSpread = jDJSONObject.optString(CartConstant.KEY_PLUSPRICESPREAD);
        JDJSONArray optJSONArray3 = jDJSONObject.optJSONArray(CartConstant.KEY_SKU_JDCOUPON);
        if (optJSONArray3 != null && optJSONArray3.size() != 0) {
            int size = optJSONArray3.size();
            ArrayList<String> arrayList = new ArrayList<>(size);
            for (int i2 = 0; i2 < size; i2++) {
                String optString = optJSONArray3.optString(i2);
                if (optString != null) {
                    arrayList.add(optString);
                }
            }
            setJdCoupons(arrayList);
        }
        setLocName(jDJSONObject.optString(CartConstant.KEY_SKU_LOCNAME));
        this.yuyueState = jDJSONObject.optInt(CartConstant.KEY_YUYUE_STATE, 0);
        this.delGiftFlag = jDJSONObject.optInt(CartConstant.KEY_CART_DEL_GIFT_FLAG);
        this.giftPoolType = jDJSONObject.optInt(CartConstant.KEY_CART_GIFT_POOL_TYPE);
        this.giftPoolPromoId = jDJSONObject.optString(CartConstant.KEY_CART_GIFT_POOL_PROMOID);
        this.oftenBuy = jDJSONObject.optString("oftenBuy");
        JDJSONObject optJSONObject3 = jDJSONObject.optJSONObject("timeOrderInfo");
        if (optJSONObject3 != null) {
            this.mTimeOrderInfo = new TimeOrderInfo(optJSONObject3);
        }
        this.maskText = jDJSONObject.optString("maskText");
        this.maskType = jDJSONObject.optInt("maskType");
        this.maskUrl = jDJSONObject.optString("maskUrl");
        JDJSONObject optJSONObject4 = jDJSONObject.optJSONObject("qualityInfo");
        if (optJSONObject4 != null && optJSONObject4.size() > 0) {
            for (String str3 : optJSONObject4.keySet()) {
                if (this.qualityInfo != null && !TextUtils.isEmpty(str3)) {
                    this.qualityInfo.put(str3, optJSONObject4.optString(str3, ""));
                }
            }
        }
        JDJSONArray optJSONArray4 = jDJSONObject.optJSONArray("homeServiceInfo");
        if (optJSONArray4 != null && optJSONArray4.size() > 0) {
            int size2 = optJSONArray4.size();
            this.homeServiceInfo = new ArrayList<>(size2);
            this.serviceSkus = new ArrayList<>();
            for (int i3 = 0; i3 < size2; i3++) {
                JDJSONObject optJSONObject5 = optJSONArray4.optJSONObject(i3);
                if (optJSONObject5 != null) {
                    CartResponseServiceSelected cartResponseServiceSelected = new CartResponseServiceSelected(optJSONObject5, str, this.skuId, this.rSuitId);
                    this.homeServiceInfo.add(cartResponseServiceSelected);
                    CartResponseSku cartResponseSku = cartResponseServiceSelected.serviceSku;
                    if (cartResponseSku != null) {
                        this.serviceSkus.add(cartResponseSku.skuId);
                    }
                }
            }
        }
        JDJSONArray optJSONArray5 = jDJSONObject.optJSONArray(CartConstant.KEY_THREECC_SKUS);
        if (optJSONArray5 != null && optJSONArray5.size() > 0) {
            int size3 = optJSONArray5.size();
            this.threeCcInfo = new ArrayList<>(size3);
            this.threeCcInfoWithoutLoc = new ArrayList<>(size3);
            for (int i4 = 0; i4 < size3; i4++) {
                JDJSONObject optJSONObject6 = optJSONArray5.optJSONObject(i4);
                if (optJSONObject6 != null) {
                    CartResponseThreeCcSelected cartResponseThreeCcSelected = new CartResponseThreeCcSelected(optJSONObject6, str, this.skuId, this.rSuitId);
                    this.threeCcInfo.add(cartResponseThreeCcSelected);
                    CartResponseSku cartResponseSku2 = cartResponseThreeCcSelected.threeCcSku;
                    if (cartResponseSku2 != null && cartResponseSku2.isLocService) {
                        if (this.locInfo == null) {
                            this.locInfo = cartResponseThreeCcSelected;
                        }
                    } else {
                        this.threeCcInfoWithoutLoc.add(cartResponseThreeCcSelected);
                    }
                }
            }
        }
        JDJSONArray optJSONArray6 = jDJSONObject.optJSONArray(CartConstant.KEY_GIFT_PACKINGS);
        if (optJSONArray6 != null && optJSONArray6.size() > 0) {
            int size4 = optJSONArray6.size();
            this.giftPackings = new ArrayList<>(size4);
            for (int i5 = 0; i5 < size4; i5++) {
                JDJSONObject optJSONObject7 = optJSONArray6.optJSONObject(i5);
                if (optJSONObject7 != null) {
                    this.giftPackings.add(new CartResponseSku(optJSONObject7, ""));
                }
            }
        }
        JDJSONArray optJSONArray7 = jDJSONObject.optJSONArray(CartConstant.KEY_GIFTS);
        if (optJSONArray7 != null && optJSONArray7.size() > 0) {
            int size5 = optJSONArray7.size();
            this.mustGifts = new ArrayList<>(size5);
            for (int i6 = 0; i6 < size5; i6++) {
                JDJSONObject optJSONObject8 = optJSONArray7.optJSONObject(i6);
                if (optJSONObject8 != null) {
                    this.mustGifts.add(new CartResponseGift(optJSONObject8, str));
                }
            }
        }
        JDJSONArray optJSONArray8 = jDJSONObject.optJSONArray(CartConstant.KEY_SIP_GIFTS);
        if (optJSONArray8 != null && optJSONArray8.size() > 0) {
            int size6 = optJSONArray8.size();
            this.sipGifts = new ArrayList<>(size6);
            for (int i7 = 0; i7 < size6; i7++) {
                JDJSONObject optJSONObject9 = optJSONArray8.optJSONObject(i7);
                if (optJSONObject9 != null) {
                    this.sipGifts.add(new CartResponseGift(optJSONObject9, str));
                }
            }
        }
        JDJSONArray optJSONArray9 = jDJSONObject.optJSONArray(CartConstant.KEY_MAN_GIFTS);
        if (optJSONArray9 != null && optJSONArray9.size() > 0) {
            int size7 = optJSONArray9.size();
            this.manGifts = new ArrayList<>(size7);
            for (int i8 = 0; i8 < size7; i8++) {
                JDJSONObject optJSONObject10 = optJSONArray9.optJSONObject(i8);
                if (optJSONObject10 != null) {
                    this.manGifts.add(new CartResponseGift(optJSONObject10, str));
                }
            }
        }
        String optString2 = jDJSONObject.optString("sType");
        if (!TextUtils.isEmpty(optString2)) {
            setsType(optString2);
        }
        JDJSONArray optJSONArray10 = jDJSONObject.optJSONArray(CartConstant.KEY_GIFTPOOL_GIFTS);
        if (optJSONArray10 != null && optJSONArray10.size() > 0) {
            int size8 = optJSONArray10.size();
            this.giftPoolGifts = new ArrayList<>(size8);
            for (int i9 = 0; i9 < size8; i9++) {
                JDJSONObject optJSONObject11 = optJSONArray10.optJSONObject(i9);
                if (optJSONObject11 != null) {
                    this.giftPoolGifts.add(new CartResponseGift(optJSONObject11, str));
                }
            }
        }
        JDJSONArray optJSONArray11 = jDJSONObject.optJSONArray(CartConstant.KEY_AFFIXES);
        if (optJSONArray11 != null && optJSONArray11.size() > 0) {
            int size9 = optJSONArray11.size();
            this.affixes = new ArrayList<>(size9);
            for (int i10 = 0; i10 < size9; i10++) {
                JDJSONObject optJSONObject12 = optJSONArray11.optJSONObject(i10);
                if (optJSONObject12 != null) {
                    this.affixes.add(new CartResponseGift(optJSONObject12, str));
                }
            }
        }
        setCanSelectPromotions(CartPromotion.toList(jDJSONObject.optJSONArray(CartConstant.KEY_SELECT_PROMOTIONS)));
        JDJSONObject optJSONObject13 = jDJSONObject.optJSONObject("skuLabels");
        if (optJSONObject13 != null) {
            for (String str4 : optJSONObject13.keySet()) {
                if ("skuHeadNew".equals(str4) && (optJSONArray2 = optJSONObject13.optJSONArray(str4)) != null && optJSONArray2.size() > 0) {
                    int size10 = optJSONArray2.size();
                    this.skuHeadNew = new ArrayList<>(size10);
                    for (int i11 = 0; i11 < size10; i11++) {
                        JDJSONObject optJSONObject14 = optJSONArray2.optJSONObject(i11);
                        if (optJSONObject14 != null) {
                            this.skuHeadNew.add(LabelProperty.parseJson(optJSONObject14));
                        }
                    }
                }
                if ("titleHead".equals(str4)) {
                    JDJSONArray optJSONArray12 = optJSONObject13.optJSONArray(str4);
                    if (optJSONArray12 != null && optJSONArray12.size() > 0) {
                        int size11 = optJSONArray12.size();
                        this.titleHead = new ArrayList<>(size11);
                        for (int i12 = 0; i12 < size11; i12++) {
                            JDJSONObject optJSONObject15 = optJSONArray12.optJSONObject(i12);
                            if (optJSONObject15 != null) {
                                this.titleHead.add(LabelProperty.parseJson(optJSONObject15));
                            }
                        }
                    }
                } else if ("priceTop".equals(str4)) {
                    JDJSONArray optJSONArray13 = optJSONObject13.optJSONArray(str4);
                    if (optJSONArray13 != null) {
                        int size12 = optJSONArray13.size();
                        this.priceTop = new ArrayList<>(size12);
                        for (int i13 = 0; i13 < size12; i13++) {
                            JDJSONObject optJSONObject16 = optJSONArray13.optJSONObject(i13);
                            if (optJSONObject16 != null) {
                                this.priceTop.add(LabelProperty.parseJson(optJSONObject16));
                            }
                        }
                    }
                } else if ("priceTail".equals(str4)) {
                    JDJSONArray optJSONArray14 = optJSONObject13.optJSONArray(str4);
                    if (optJSONArray14 != null) {
                        int size13 = optJSONArray14.size();
                        this.priceTail = new ArrayList<>(size13);
                        for (int i14 = 0; i14 < size13; i14++) {
                            JDJSONObject optJSONObject17 = optJSONArray14.optJSONObject(i14);
                            if (optJSONObject17 != null) {
                                this.priceTail.add(LabelProperty.parseJson(optJSONObject17));
                            }
                        }
                    }
                } else if ("skuActivity".equals(str4)) {
                    JDJSONArray optJSONArray15 = optJSONObject13.optJSONArray(str4);
                    if (optJSONArray15 != null) {
                        int size14 = optJSONArray15.size();
                        this.skuActivity = new ArrayList<>(size14);
                        for (int i15 = 0; i15 < size14; i15++) {
                            JDJSONObject optJSONObject18 = optJSONArray15.optJSONObject(i15);
                            if (optJSONObject18 != null) {
                                this.skuActivity.add(LabelProperty.parseJson(optJSONObject18));
                            }
                        }
                    }
                } else if ("imageTop".equals(str4)) {
                    JDJSONArray optJSONArray16 = optJSONObject13.optJSONArray(str4);
                    if (optJSONArray16 != null && optJSONArray16.size() > 0 && (optJSONObject = optJSONArray16.optJSONObject(0)) != null) {
                        this.imageTop = LabelProperty.parseJson(optJSONObject);
                    }
                } else if ("skuMiddle".equals(str4)) {
                    JDJSONArray optJSONArray17 = optJSONObject13.optJSONArray(str4);
                    if (optJSONArray17 != null && optJSONArray17.size() > 0) {
                        this.skuMiddle = new ArrayList<>();
                        for (int i16 = 0; i16 < optJSONArray17.size(); i16++) {
                            JDJSONObject optJSONObject19 = optJSONArray17.optJSONObject(i16);
                            if (optJSONObject19 != null) {
                                this.skuMiddle.add(LabelProperty.parseJson(optJSONObject19));
                            }
                        }
                    }
                } else if ("titleBelow".equals(str4) && (optJSONArray = optJSONObject13.optJSONArray(str4)) != null && optJSONArray.size() > 0) {
                    this.titleBelow = new ArrayList<>();
                    for (int i17 = 0; i17 < optJSONArray.size(); i17++) {
                        JDJSONObject optJSONObject20 = optJSONArray.optJSONObject(i17);
                        if (optJSONObject20 != null) {
                            this.titleBelow.add(LabelProperty.parseJson(optJSONObject20));
                        }
                    }
                }
            }
        }
        setCanSelectPrices(CartPromotion.toList(jDJSONObject.optJSONArray(CartConstant.KEY_SELECT_SELECTPRICES)));
        JDJSONArray optJSONArray18 = jDJSONObject.optJSONArray(CartConstant.KEY_CARTFURNITURESERVICES);
        if (optJSONArray18 != null && optJSONArray18.size() > 0) {
            int size15 = optJSONArray18.size();
            this.furnitureServices = new ArrayList<>(size15);
            for (int i18 = 0; i18 < size15; i18++) {
                JDJSONObject optJSONObject21 = optJSONArray18.optJSONObject(i18);
                if (optJSONObject21 != null) {
                    this.furnitureServices.add(new CartResponseSku(optJSONObject21, ""));
                }
            }
        }
        JDJSONArray optJSONArray19 = jDJSONObject.optJSONArray(CartConstant.KEY_CARTEXTFLOORS);
        if (optJSONArray19 != null && optJSONArray19.size() > 0) {
            int size16 = optJSONArray19.size();
            this.extFloors = new ArrayList<>(size16);
            for (int i19 = 0; i19 < size16; i19++) {
                JDJSONObject optJSONObject22 = optJSONArray19.optJSONObject(i19);
                if (optJSONObject22 != null) {
                    this.extFloors.add(new CartExtFloor(optJSONObject22));
                }
            }
        }
        JDJSONArray optJSONArray20 = jDJSONObject.optJSONArray(CartConstant.KEY_ITEMS);
        if (optJSONArray20 != null && optJSONArray20.size() > 0) {
            int size17 = optJSONArray20.size();
            this.items = new ArrayList(size17);
            this.floorItems = new ArrayList(size17);
            for (int i20 = 0; i20 < size17; i20++) {
                JDJSONObject optJSONObject23 = optJSONArray20.optJSONObject(i20);
                if (optJSONObject23 != null) {
                    this.floorItems.add(optJSONObject23);
                    HashMap hashMap = new HashMap(optJSONObject23.keySet().size());
                    for (String str5 : optJSONObject23.keySet()) {
                        String optString3 = optJSONObject23.optString(str5);
                        if (!TextUtils.isEmpty(str5) && !TextUtils.isEmpty(optString3)) {
                            hashMap.put(str5, optString3);
                        }
                    }
                    if (hashMap.size() > 0) {
                        this.items.add(hashMap);
                    }
                }
            }
        }
        this.menuRelationTag = jDJSONObject.optString("menuRelationTag", "");
        this.isInvalid = jDJSONObject.optInt(CartConstant.KEY_IS_INVALID);
        JDJSONObject optJSONObject24 = jDJSONObject.optJSONObject("fields");
        if (optJSONObject24 != null && optJSONObject24.keySet() != null && optJSONObject24.keySet().size() > 0) {
            this.fields = new HashMap(optJSONObject24.keySet().size());
            for (String str6 : optJSONObject24.keySet()) {
                String optString4 = optJSONObject24.optString(str6);
                if (!TextUtils.isEmpty(str6) && !TextUtils.isEmpty(optString4)) {
                    this.fields.put(str6, optString4);
                }
            }
        }
        this.vendorId = jDJSONObject.optInt("vendorId");
        JDJSONArray optJSONArray21 = jDJSONObject.optJSONArray(CartConstant.KEY_YB_SKUS);
        if (optJSONArray21 != null && optJSONArray21.size() > 0) {
            int size18 = optJSONArray21.size();
            this.ybSkus = new ArrayList<>(size18);
            this.yanbaoSkus = new ArrayList<>();
            for (int i21 = 0; i21 < size18; i21++) {
                JDJSONObject optJSONObject25 = optJSONArray21.optJSONObject(i21);
                if (optJSONObject25 != null) {
                    CartResonseYBSelected cartResonseYBSelected = new CartResonseYBSelected(optJSONObject25, str);
                    this.ybSkus.add(cartResonseYBSelected);
                    if (cartResonseYBSelected.getYbSku() != null) {
                        this.yanbaoSkus.add(cartResonseYBSelected.getYbSku().skuId);
                    }
                }
            }
        }
        this.hasUnitedText = jDJSONObject.optInt(CartConstant.KEY_SKU_HASUNITEDTEXT);
        this.unitedText = jDJSONObject.optString(CartConstant.KEY_SKU_UNITEDTEXT);
        this.unitedTextShow = jDJSONObject.optString(CartConstant.KEY_SKU_UNITEDTEXT_SHOW);
        JDJSONObject optJSONObject26 = jDJSONObject.optJSONObject(CartConstant.KEY_SKU_CONTRACTPHONEITEM);
        if (optJSONObject26 != null) {
            this.contractPhoneItem = new CartResponseSku(optJSONObject26, str);
        }
        this.contractPhoneType = jDJSONObject.optInt(CartConstant.KEY_SKU_CONTRACTPHONETYPE);
        this.isInValidPhone = jDJSONObject.optBoolean(CartConstant.KEY_SKU_ISINVALIDPHONE);
        this.needShortTitle = jDJSONObject.optBoolean(CartConstant.KEY_SKU_IS_NEED_SHORT_TITLE);
        this.homeWishFlagNums = jDJSONObject.optInt(CartConstant.KEY_SKU_HOMEWISHFLAGNUMS);
        JDJSONArray optJSONArray22 = jDJSONObject.optJSONArray(CartConstant.KEY_SKU_HOMEWISHINFOS);
        if (optJSONArray22 != null && optJSONArray22.size() != 0) {
            int size19 = optJSONArray22.size();
            this.homeWishInfos = new ArrayList(size19);
            for (int i22 = 0; i22 < size19; i22++) {
                String optString5 = optJSONArray22.optString(i22);
                if (optString5 != null) {
                    this.homeWishInfos.add(optString5);
                }
            }
        }
        this.ad2cartTime = jDJSONObject.optString(CartConstant.KEY_SKU_AD2CARTTIME);
        this.pdpromType = jDJSONObject.optString(CartConstant.KEY_SKU_PDPROMTYPE);
        this.memberPriceType = jDJSONObject.optString("memberPriceType");
        this.landedPrice = jDJSONObject.optString(CartConstant.KEY_SKU_LANDEDPRICE);
        this.backRedPacket = jDJSONObject.optString(CartConstant.KEY_SKU_BACKREDPACKET);
        this.stockBigPromotionShow = jDJSONObject.optString(CartConstant.KEY_SKU_STOCKBIGPROMOTIONSHOW);
        this.multiPromoLimitMsg = jDJSONObject.optString(CartConstant.KEY_SKU_MULTIPROMOLIMITMSG);
        this.skuType = jDJSONObject.optInt(CartConstant.KEY_SKU_TYPE);
        JDJSONObject optJSONObject27 = jDJSONObject.optJSONObject("customInfo");
        if (optJSONObject27 != null) {
            this.cartCustomInfo = new CartCustomInfo(optJSONObject27);
        }
        this.czType = Integer.valueOf(jDJSONObject.optInt("czType"));
        this.payBargainReal = jDJSONObject.optString("payBargainReal");
        this.payBalancePlan = jDJSONObject.optString("payBalancePlan");
        this.balanceStartTime = jDJSONObject.optString("balanceStartTime");
        this.balanceEndTime = jDJSONObject.optString("balanceEndTime");
        this.balanceStartTimeStamp = jDJSONObject.optLong("balanceStartTimeStamp", -1L);
        this.balanceEndTimeStamp = jDJSONObject.optLong("balanceEndTimeStamp", -1L);
        this.addressDetail = jDJSONObject.optString("addressDetail");
        int optInt2 = jDJSONObject.optInt("isEditNoCheck", -1);
        this.isEditNoCheck = optInt2;
        if (optInt2 == -1) {
            this.isEditNoCheck = this.isNoCheck;
        }
        this.checkBoxText = jDJSONObject.optString("checkBoxText");
        this.editCheckBoxText = jDJSONObject.optString("editCheckBoxText");
        if (CartCommonUtil.useUuid) {
            this.uniqueId = this.skuUuid;
        } else if (TextUtils.isEmpty(this.skuId)) {
            this.uniqueId = "";
        } else {
            if (TextUtils.isEmpty(this.storeId) || CartCommonUtil.isScfSku(this)) {
                sb = new StringBuilder();
                str2 = "null_";
            } else {
                sb = new StringBuilder();
                sb.append(this.storeId);
                str2 = CartConstant.KEY_YB_INFO_LINK;
            }
            sb.append(str2);
            sb.append(this.skuId);
            this.uniqueId = sb.toString();
        }
        this.superimposePromotions = new CartSuperimposePromotion(jDJSONObject.optJSONObject("superimposePromotions"));
        this.sipPromotionId = jDJSONObject.optString("sipPromotionId");
        this.sipMarkText = jDJSONObject.optString("sipMarkText");
        this.sipSType = jDJSONObject.optString("sipSType");
        this.sipUseUuid = jDJSONObject.optBoolean("sipUseUuid");
        this.superimposePromotionsText = CartChangePromotionText.parseCartChangePromotionTextList(jDJSONObject.optJSONArray("superimposePromotionsText"));
        resetEditCheckType();
        this.seckillType = jDJSONObject.optInt("seckillType", -1);
        this.promoPrice = jDJSONObject.optString("promoPrice");
        this.soldRate = jDJSONObject.optString("soldRate");
        this.skuMiddleMta = jDJSONObject.optString("skuMiddleMta");
        this.cidNames = jDJSONObject.optString("cidNames");
        this.jtPromoText = jDJSONObject.optString("jtPromoText");
        this.jtTagNormal = jDJSONObject.optString("jtTagNormal");
        this.jtTagDark = jDJSONObject.optString("jtTagDark");
        this.urlType = jDJSONObject.optInt("urlType", -1);
        this.urlStr = jDJSONObject.optString("urlStr");
        this.isLsNewService = jDJSONObject.optBoolean("isLsNewService");
        this.isLocService = jDJSONObject.optBoolean("isLocService");
        this.locId = jDJSONObject.optString("locId");
        this.prescriptionId = jDJSONObject.optString("prescriptionId", "");
        this.isChufangYao = jDJSONObject.optBoolean("isChufangYao", false);
        this.isPetPrescription = jDJSONObject.optBoolean("isPetPrescription", false);
        this.isYxChufangYao = jDJSONObject.optBoolean("isYxChufangYao", false);
        this.realSkuId = jDJSONObject.optString("realSkuId", "");
        this.linkUrl = jDJSONObject.optString("linkUrl", "");
        this.isContent = jDJSONObject.optBoolean("isContent", false);
        this.contentCode = jDJSONObject.optString("contentCode", "");
        this.contentTitle = jDJSONObject.optString("contentTitle", "");
        this.contentName = jDJSONObject.optString("contentName", "");
        this.contentUrl = jDJSONObject.optString("contentUrl", "");
        this.complete = jDJSONObject.optBoolean("complete", true);
        this.isCanUseJQ = CartJsonUtil.optInt(jDJSONObject, "isCanUseJQ");
        this.isCanUseDQ = CartJsonUtil.optInt(jDJSONObject, "isCanUseDQ");
        this.isYjs = jDJSONObject.optBoolean("isYjs", false);
        this.completeExp = jDJSONObject.optLong("completeExp", 0L);
        this.hasChange = jDJSONObject.optBoolean("hasChange", false);
        this.frontPrice = jDJSONObject.optString("frontPrice");
        this.tailPrice = jDJSONObject.optString("tailPrice");
        this.preSaleState = jDJSONObject.optInt("preSaleState");
        this.orderText = jDJSONObject.optString("orderText");
        this.orderColor = jDJSONObject.optString("orderColor");
        this.preSalePrice = jDJSONObject.optString("preSalePrice");
        this.secKillBId = jDJSONObject.optString("secKillBId");
        this.addCartTime = jDJSONObject.optLong("addCartTime");
        this.unReleasePrice = jDJSONObject.optBoolean("unReleasePrice");
        this.msbybt = jDJSONObject.optBoolean("msbybt");
        this.deleteBeforeType = jDJSONObject.optString("deleteBeforeType");
        this.beforePriceText = jDJSONObject.optString("beforePriceText");
        this.minLimitMsg = jDJSONObject.optString("minLimitMsg");
        this.maxLimitMsg = jDJSONObject.optString("maxLimitMsg");
        this.maxType = CartJsonUtil.optInt(jDJSONObject, "maxType");
        this.strategyId = jDJSONObject.optString("strategyId");
        String optString6 = jDJSONObject.optString("extraCommonData");
        if (!TextUtils.isEmpty(optString6)) {
            this.cartExtraCommonData = (CartExtraCommonData) JDJSON.parseObject(optString6, CartExtraCommonData.class);
        }
        JDJSONObject optJSONObject28 = jDJSONObject.optJSONObject("dialogEntryBelowName");
        if (optJSONObject28 != null) {
            this.dialogEntryBelowName = new DialogEntryBelowName(optJSONObject28);
        }
        JDJSONObject optJSONObject29 = jDJSONObject.optJSONObject("dynExtraFloors");
        if (optJSONObject29 != null) {
            this.dynExtraFloors = new HashMap<>();
            Set<String> keySet = optJSONObject29.keySet();
            if (keySet != null) {
                for (String str7 : keySet) {
                    JDJSONObject optJSONObject30 = optJSONObject29.optJSONObject(str7);
                    if (optJSONObject30 != null && optJSONObject30.size() > 0) {
                        this.dynExtraFloors.put(str7, optJSONObject30);
                    }
                }
            }
        }
        JDJSONObject optJSONObject31 = jDJSONObject.optJSONObject("floorMap");
        if (optJSONObject31 == null || optJSONObject31.isEmpty()) {
            return;
        }
        this.floorMap = (HashMap) JDJSON.parseObject(optJSONObject31.toString(), new TypeToken<HashMap<String, CartFloorData>>() { // from class: com.jingdong.common.entity.cart.CartResponseSku.1
            {
                CartResponseSku.this = this;
            }
        }.getType(), new Feature[0]);
    }

    public CartResponseSku(Parcel parcel) {
        super(parcel);
        this.qualityInfo = new HashMap();
        this.overseaPurchase = -1;
        this.urlType = -1;
        this.extraTotalCount = 0;
        this.name = parcel.readString();
        this.ybCanSelectedCategorys = parcel.createTypedArrayList(CartResonseYB.CREATOR);
        this.homeServiceInfo = parcel.createTypedArrayList(CartResponseServiceSelected.CREATOR);
        this.threeCcInfo = parcel.createTypedArrayList(CartResponseThreeCcSelected.CREATOR);
        Parcelable.Creator<CartResponseSku> creator = CREATOR;
        this.giftPackings = parcel.createTypedArrayList(creator);
        this.isNoCheck = parcel.readInt();
        this.checkType = parcel.readInt();
        this.stockState = parcel.readString();
        this.stockCode = parcel.readInt();
        this.specialId = parcel.readLong();
        this.giftMsg = parcel.readString();
        this.giftsType = parcel.readInt();
        this.overseaPurchase = parcel.readInt();
        Parcelable.Creator<CartResponseGift> creator2 = CartResponseGift.CREATOR;
        this.mustGifts = parcel.createTypedArrayList(creator2);
        this.giftPoolGifts = parcel.createTypedArrayList(creator2);
        this.affixes = parcel.createTypedArrayList(creator2);
        this.giftPools = parcel.createTypedArrayList(GiftPool.CREATOR);
        this.furnitureServices = parcel.createTypedArrayList(creator);
        this.giftPoolPromoId = parcel.readString();
        this.isInvalid = parcel.readInt();
        this.ybSkus = parcel.createTypedArrayList(CartResonseYBSelected.CREATOR);
    }
}
