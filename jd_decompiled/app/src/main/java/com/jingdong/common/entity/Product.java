package com.jingdong.common.entity;

import android.text.TextUtils;
import com.alibaba.fastjson.parser.Feature;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.framework.json.TypeToken;
import com.jingdong.common.R;
import com.jingdong.common.apkcenter.ApkDownloadTable;
import com.jingdong.common.entity.settlement.NewFillOrderConstant;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.JDJSONObjectWithDefaultUtils;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.common.utils.StringUtil;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.constant.PDConstant;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.manto.sdk.api.IRequestPayment;
import com.jingdong.sdk.oklog.OKLog;
import com.unionpay.tsmservice.mi.data.Constant;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jd.wjweblogin.d.c;
import org.json.JSONArray;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* loaded from: classes5.dex */
public class Product implements Serializable {
    public static final int BEGINGTOORDER = 2;
    public static final int BEGINTOBUY = 4;
    public static final int BROWSER_HISTORY = 36;
    public static final int CART_LIST = 9;
    public static final int CHANNEL_LIST = 31;
    public static final int CMS_ACTIVITY = 22;
    public static final int COLLECT_LIST = 7;
    public static final int CRAZY = 0;
    public static final int DELIVER = 19;
    public static final int EASY_LIST = 12;
    public static final int ENDBUY = 5;
    public static final int FAVO_RECOMMEND = 32;
    public static final int FLAG_SIZE_CLOTHES = 1;
    public static final int FLAG_SIZE_NO = 0;
    public static final int FLAG_SIZE_SHOES = 2;
    public static final int HOME_RECOMMEND = 30;
    public static final int HOT_SALES = 21;
    public static final int LIMIT_TIMEBUY = 14;
    public static final int MATCH_WARE_INFO_LIST = 16;
    public static final int MIAO_SHA_BRAND_INNER = 33;
    public static final int MIAO_SHA_LIST = 17;
    public static final int MIAO_SHA_PAGE_INNER = 34;
    public static final int MIAO_SHA_PLUS = 37;
    public static final int MULTI_SELLER = 29;
    public static final int MY_DNA_PRODUCT_LIST = 10011;
    public static final int M_PROMOTION = 20;
    public static final int ORDER_DETAIL = 106;
    public static final int ORDER_LIST = 6;
    public static final int ORDER_STATUS_ID_DENGDAIFUKUAN = 1;
    public static final int ORDER_STATUS_ID_DENGDAISHOUHUO = 3;
    public static final int ORDER_STATUS_ID_ERROR = -1;
    public static final int ORDER_STATUS_ID_FUKUANCHENGGONG = 2;
    public static final int ORDER_STATUS_ID_OTHER = 0;
    public static final int ORDER_STATUS_ID_SHOUHUOCHENGGONG = 4;
    public static final int ORDER_STATUS_ID_YIQUXIAO = 6;
    public static final int ORDER_STATUS_ID_YIWANCHENG = 5;
    public static final int PACKS = 24;
    public static final int PACKS_LIST = 13;
    public static final int PACKS_MAIN = 25;
    public static final int PHOTO_SHOPPING_WARE_INFO_LIST = 26;
    public static final int PRODUCT_DETAIL = 3;
    public static final int PRODUCT_DETAIL_CRUX = 28;
    public static final int PROMOTION = 10;
    public static final int PROMOTION_INFO = 18;
    public static final int PURCHASEWARE = 35;
    public static final int RECOMAND_PRODECT = 4;
    public static final int SEARCH_CATEGORY = 1;
    public static final int SHOPPING = 23;
    public static final int SKU_COLOR_SIZE = 15;
    private static final String TAG = "Product";
    public static final int USER_INFO = 5;
    public static final int WAITINGTOBUY = 3;
    public static final int WAITINGTOORDER = 1;
    public static final int WARE_ID_BY_BAR_CODE_LIST = 11;
    private static final long serialVersionUID = -6454309392186382154L;
    private String adEventId;
    private String adWord;
    private String addFavoriteMsg;
    private String areaTips;
    private String author;
    private boolean available;
    private Boolean availableInStore;
    private Long averageScore;
    public String biInfo;
    private String bn;
    private long browserTime;
    private long buyAgain;
    private Long buyBegin;
    private int buyCount;
    private Long buyEnd;
    private String cId;
    private String cName;
    private boolean canBeDelete;
    private Boolean canConsultFlag;
    private boolean canEasyBuy;
    private Boolean canFreeRead;
    private Boolean canGoToShop;
    private Long catId;
    private String cityId;
    private Integer cityIdMode1;
    private HashMap<Long, CityMode1> cityMode1Map;
    private ArrayList<AddressBaseMode> cityModeList;
    private String cityName;
    private ArrayList<CommentCount> commentCountList;
    private String commentGuid;
    public String commentText;
    private String consultationCount;
    private String countyId;
    private ArrayList<AddressBaseMode> countyModeList;
    private String countyName;
    private ArrayList<Coupon> couponList;
    private String customAttr;
    private JDJSONArray customAttrListJson;
    private DefaultAddressMode defaultAddressMode;
    private ArrayList<DefaultAddressMode> defaultAddressModeList;
    private String deliver;
    private String deliveryCommentFlag;
    private String deliveryFlag;
    private String desc;
    private String deviceidTail;
    private String diffMobilePrice;
    private String diffPice;
    public String diffPriceMobile;
    private String discount;
    private String discountNew;
    private String ebookUrl;
    private Long endTime;
    private ArrayList<ExcutableButton> excutableButtonsList;
    private String expid;
    public String exposureJsonSourceValue;
    private String exposureSourceValue;
    private String exprid;
    public List<ExtTagItem> extTagItems;
    private long fatherId;
    private String fid;
    private String flags;
    private Integer flowOrder;
    private String freeReadUrl;
    private String fullCut;
    private ArrayList<Product> giftList;
    private String good;
    private Boolean hasChat;
    private String hasComment;
    private String hasDiscuss;
    private boolean hasPacks;
    private int hasPriceDiff;
    private Boolean hasShop;
    private String historyPrice;
    private Long id;
    private List<Image> imageList;
    private String imgPrice;
    private String impr;
    private String index;
    private String interlImgUrl;
    private int internationalOrder;
    private int internationalType;
    public int isAddCar;
    private Boolean isBook;
    private Boolean isBookDisc;
    private boolean isBooking;
    public boolean isBrowsed;
    private Boolean isCarBlocked;
    private boolean isCheckNext;
    private Boolean isEbook;
    private Boolean isEche;
    private boolean isFavoCheckBoxChecked;
    private boolean isFavorited;
    private Integer isFlashSale;
    private Boolean isFood;
    public boolean isHaveProductLogTag;
    public boolean isHistoryProduct;
    private boolean isHot;
    private Boolean isInternational;
    public int isMobileVip;
    private boolean isNew;
    private int isNewGoods;
    private Boolean isOrder;
    private Boolean isPhoneVipPrice;
    private Boolean isPromotion;
    private Boolean isPromotionDou;
    private Boolean isPromotionJiang;
    private Boolean isPromotionQuan;
    private Boolean isPromotionShan;
    private Boolean isPromotionTuan;
    private Boolean isPromotionZeng;
    private boolean isSaleExpand;
    private Boolean isShowDelButton;
    private Boolean isShowNetContent;
    public int isSoldOut;
    public int isUnderCarriage;
    private boolean isVirtualOrder;
    private String jdPrice;
    public JumpEntity jump;
    private String logid;
    private String lookSimilar;
    private String mPaymentType;
    private ArrayList<Product> mProductList;
    private ArrayList<ServerIcon> mServerIcons;
    private String mShaShopId;
    private String marketPrice;
    private String marketPriceDescription;
    public MaterialRedirectProtocol materialRedirectProtocol;
    private String message;
    private String messageTime;
    private Boolean miaoSha;
    private String miaoShaPrice;
    private String moreFunId;
    private String mpageAddress;
    public int msItemType;
    public String msPromotionTag;
    private String msgTypeName;
    private MultiSuppliers multiSuppliers;
    List<String> mySearchProductTagInfos;
    private int nItemCount;
    private String name;
    public int newBuyAgain;
    public String newBuyAgainMUrl;
    public String newBuyAgainSku;
    private String notifyPrice;
    private Integer num;
    private Boolean online;
    private boolean onshelf;
    private String openAppUrl;
    private String operateWord;
    private Long orderBegin;
    private String orderCheckCode;
    private String orderCommentCount;
    private Long orderEnd;
    public List<OrderOptButton> orderOptButtons;
    public int orderStatusId;
    private String orderStatusShow;
    private int orderType;
    private String p13nFlags;
    private String payTypeCode;
    public String plusPrice;
    private Boolean postByjd;
    public JDJSONObject prdObject;
    private String priceDiff;
    private String priceDiffText;
    private boolean priceKeep;
    public String priceLabel;
    private Boolean priceReport;
    private String priority;
    private ProductDetailBasicInfo productDetailBasicInfo;
    private ProductDetailCruxBasicInfo productDetailCruxBasicInfo;
    private DefaultAddressMode productDetailDefaultAddress;
    private ProductDetailPrice productDetailJprice;
    private ProductDetailPrice productDetailMprice;
    private ProductDetailPrice productDetailPcprice;
    private ArrayList<ProductDetailSkuColor> productDetailSkuColor;
    private ArrayList<ProductDetailSkuSize> productDetailSkuSize;
    private ProductFeeInfo productFeeInfo;
    private Integer productStatusType;
    private int promFlag;
    private String promName;
    private String promotionIconUrl;
    private String promotionInfo;
    private String promotionTitle;
    private String provinceID;
    private Integer provinceIdMode1;
    private ArrayList<Province> provinceList;
    private ArrayList<ProvinceMode1> provinceMode1List;
    private HashMap<Integer, Integer> provinceMode1Map;
    private ArrayList<AddressBaseMode> provinceModeList;
    private String provinceName;
    private Integer provinceStockCode;
    private String provinceStockContent;
    private Boolean provinceStockFlag;
    private Integer provinceStockMode;
    private String psPrice;
    private int purchaseReminder;
    private String purchaseReminderIcon;
    private String rate;
    private Integer remainNum;
    private String rid;
    private List<String> sImgUrlList;
    private String sItemPrice;
    private String sMsgBody;
    private String sMsgFlag;
    private String sMsgId;
    private String sMsgTime;
    private String sMsgType;
    private String sMsgUpdateTime;
    private String sOrderId;
    private String sOrderStatus;
    private String sPriceShow;
    private String sSkuID;
    private String sSkuName;
    private String sSubmitTime;
    private String sTotalPrice;
    private String sUerScore;
    private String sUserBalance;
    private String sUserClass;
    private String sUserName;
    private List<Sale> salesList;
    public String samsPrice;
    public boolean self;
    private String sendPay;
    private long shopId;
    private String shopName;
    private String shopUrl;
    public String shouldPayTip;
    private boolean showCartIcon;
    private Long showId;
    private String showLabel;
    private Integer showLabelId;
    private Boolean showMarketPrice;
    private ArrayList<SkuColor> skuColorList;
    private String skuId;
    private ArrayList<SkuSize> skuSizeList;
    private String skuType;
    private int skuTypeId;
    private Integer soldRate;
    private SourceEntity sourceEntity;
    private int sourceID;
    private String sourceValue;
    private Integer specialKill;
    public String specificationLabel;
    private String spuId;
    private DiliverManInfo staffInfo;
    public int starGood;
    public String starWord;
    private Long startTime;
    private String startTimeContent;
    public long startTimeMills;
    private String startTimeShow;
    public int stock;
    private String stockFunction;
    private int stockQuantity;
    private Integer stockState;
    private Integer stockStateId;
    private String stockStateStr;
    private Boolean subOrderFlag;
    private String superNew;
    private int supportSizeType;
    private String tag;
    private String tagText;
    private int tagType;
    private String targetUrl;
    private String toMURL;
    private Integer totalCount;
    private String townId;
    private ArrayList<AddressBaseMode> townModeList;
    private String townName;
    private String userPriceContent;
    private String userPriceLabel;
    private long venderId;
    private String venderName;
    private Integer venderType;
    private String viewInvitationUrl;
    private VirtualOrderInfo virtualOrderInfo;
    public String virtualOrderPayPlan;
    private String voucherStatus;
    public String wareColor;
    public int wareCount;
    private WarePromotionInfo warePromotionInfo;
    public WareRankInfo wareRank;
    public String wareSize;
    private String wareType;
    private YushouOrder yushouOrder;
    private Integer yuyueNum;
    private static final String CONST_NO_PRICE = StringUtil.getString(R.string.product_entity_no_price);
    private static final String FREE = StringUtil.getString(R.string.product_entity_for_free);
    private static boolean isValid = true;

    /* loaded from: classes5.dex */
    public static class ExtTagItem implements Serializable {
        public String clickPoint;
        public String content;
        public String exposurePoint;
        public String extTag;
        public String param;
        public String subTitle;
        public String title;
        public String url;
    }

    /* loaded from: classes5.dex */
    public static class MaterialRedirectProtocol implements Serializable {
        public String type;
        public String url;
    }

    /* loaded from: classes5.dex */
    public static class OrderOptButton implements Serializable {
        public String showLabel;
        public int showLabelId;
    }

    /* loaded from: classes5.dex */
    public static class Sale {
        public String giftId;
        public String lable;
        public Long productId;
        public String promotionId;
        public String type;
        public String url;
        public String value;
    }

    public Product() {
        this.supportSizeType = 0;
        this.isHistoryProduct = false;
        this.starGood = 0;
        this.msItemType = 0;
        this.canBeDelete = false;
        this.imageList = new LinkedList();
        this.showMarketPrice = null;
        this.provinceStockMode = 0;
        this.canEasyBuy = false;
        this.hasPacks = false;
        this.isFavoCheckBoxChecked = false;
        Boolean bool = Boolean.FALSE;
        this.priceReport = bool;
        this.canFreeRead = bool;
        this.postByjd = bool;
        this.toMURL = "";
        this.isCheckNext = false;
        this.isEbook = bool;
        this.ebookUrl = "";
        this.isCarBlocked = bool;
        this.isFood = bool;
        this.isShowNetContent = bool;
        this.customAttr = "";
        this.diffMobilePrice = "";
        this.available = true;
        this.onshelf = true;
        this.showCartIcon = true;
        this.promFlag = 0;
        this.isNew = false;
        this.isHot = false;
        this.isShowDelButton = Boolean.TRUE;
        this.orderType = 0;
        this.internationalType = 0;
        this.orderStatusId = -1;
        this.isHaveProductLogTag = false;
        this.self = false;
        this.sImgUrlList = new LinkedList();
    }

    private void SetDiffPrice(String str) {
        this.diffPice = str;
    }

    private ArrayList<ExcutableButton> getExcutableButtonsList(JDJSONArray jDJSONArray) {
        ArrayList<ExcutableButton> arrayList = new ArrayList<>();
        if (jDJSONArray != null && jDJSONArray.size() > 0) {
            for (int i2 = 0; i2 < jDJSONArray.size(); i2++) {
                arrayList.add(new ExcutableButton(jDJSONArray.getJSONObject(i2)));
            }
        }
        return arrayList;
    }

    private void setRemainNum(Integer num) {
        this.remainNum = num;
    }

    public static ArrayList<Product> toList(JSONArrayPoxy jSONArrayPoxy, int i2) {
        return toList(jSONArrayPoxy, i2, (Object[]) null);
    }

    public void addJshopIcon(JSONObjectProxy jSONObjectProxy) {
        if (jSONObjectProxy == null) {
            return;
        }
        addJshopIcon(JDJSON.parseObject(jSONObjectProxy.toString()));
    }

    public void addNewProductListScore(JSONObjectProxy jSONObjectProxy) {
        if (jSONObjectProxy == null) {
            return;
        }
        addNewProductListScore(JDJSON.parseObject(jSONObjectProxy.toString()));
    }

    public void addProductListScore(JSONObjectProxy jSONObjectProxy) {
        if (jSONObjectProxy == null) {
            return;
        }
        addProductListScore(JDJSON.parseObject(jSONObjectProxy.toString()));
    }

    public void appendImgUrl(String str, int i2) {
        if (i2 < 0) {
            this.sImgUrlList.add(str.toString());
        } else {
            this.sImgUrlList.add(i2, str.toString());
        }
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public int geItemCount() {
        int i2 = this.nItemCount;
        return i2 <= 0 ? Integer.parseInt("1") : i2;
    }

    public String getAdEventId() {
        return this.adEventId;
    }

    public String getAdWord() {
        String str = this.adWord;
        return str == null ? "" : str;
    }

    public String getAddFavoriteMsg() {
        return this.addFavoriteMsg;
    }

    public String getAreaTips() {
        String str = this.areaTips;
        return str == null ? "" : str;
    }

    public String getAuthor() {
        return this.author;
    }

    public Boolean getAvailableInStore() {
        return this.availableInStore;
    }

    public Long getAverageScore() {
        return this.averageScore;
    }

    public String getBn() {
        return this.bn;
    }

    public long getBrowserTime() {
        return this.browserTime;
    }

    public long getBuyAgain() {
        return this.buyAgain;
    }

    public Long getBuyBegintime() {
        return this.buyBegin;
    }

    public int getBuyCount() {
        return this.buyCount;
    }

    public Long getBuyEndtime() {
        Long l2 = this.buyEnd;
        if (l2 == null) {
            return 0L;
        }
        return l2;
    }

    public String getCId() {
        return TextUtils.isEmpty(this.cId) ? "" : this.cId;
    }

    public String getCName() {
        return TextUtils.isEmpty(this.cName) ? "" : this.cName;
    }

    public Boolean getCanConsultFlag() {
        Boolean bool = this.canConsultFlag;
        return bool == null ? Boolean.FALSE : bool;
    }

    public Boolean getCanFreeRead() {
        Boolean bool = this.canFreeRead;
        return bool == null ? Boolean.FALSE : bool;
    }

    public Long getCatId() {
        return this.catId;
    }

    public boolean getCheckNext() {
        return this.isCheckNext;
    }

    public String getCityId() {
        return this.cityId;
    }

    public Integer getCityIdMode1() {
        return this.cityIdMode1;
    }

    public CityMode1 getCityMode1BySkuId(Long l2) {
        HashMap<Long, CityMode1> hashMap = this.cityMode1Map;
        if (hashMap != null) {
            return hashMap.get(l2);
        }
        return null;
    }

    public ArrayList<AddressBaseMode> getCityModeList() {
        return this.cityModeList;
    }

    public String getCityName() {
        return this.cityName;
    }

    public ArrayList<CommentCount> getCommentCountList() {
        return this.commentCountList;
    }

    public String getCommentGuid() {
        return this.commentGuid;
    }

    public String getCommentText() {
        return this.commentText;
    }

    public String getConsultationCount() {
        return this.consultationCount;
    }

    public String getCountyID() {
        return this.countyId;
    }

    public ArrayList<AddressBaseMode> getCountyModeList() {
        return this.countyModeList;
    }

    public String getCountyName() {
        return this.countyName;
    }

    public ArrayList<Coupon> getCouponList() {
        return this.couponList;
    }

    public String getCustomAttr() {
        return this.customAttr;
    }

    public List<String> getCustomAttrList() {
        ArrayList arrayList;
        Exception e2;
        try {
            JDJSONArray jDJSONArray = this.customAttrListJson;
            if (jDJSONArray != null && jDJSONArray.size() != 0) {
                arrayList = new ArrayList();
                for (int i2 = 0; i2 < this.customAttrListJson.size(); i2++) {
                    try {
                        if (!TextUtils.isEmpty(this.customAttrListJson.getString(i2))) {
                            arrayList.add(this.customAttrListJson.getString(i2));
                        }
                    } catch (Exception e3) {
                        e2 = e3;
                        if (OKLog.E) {
                            OKLog.e(TAG, e2);
                        }
                        return arrayList;
                    }
                }
                return arrayList;
            }
            return null;
        } catch (Exception e4) {
            arrayList = null;
            e2 = e4;
        }
    }

    public DefaultAddressMode getDefaultAddressMode() {
        return this.defaultAddressMode;
    }

    public ArrayList<DefaultAddressMode> getDefaultAddressModeList() {
        return this.defaultAddressModeList;
    }

    public String getDeliver() {
        return this.deliver;
    }

    public String getDeliveryCommentFlag() {
        return this.deliveryCommentFlag;
    }

    public String getDeliveryFlag() {
        return this.deliveryFlag;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getDiffMobilePrice() {
        String str = this.diffMobilePrice;
        return str != null ? str.trim() : str;
    }

    public String getDiffPrice() {
        return this.diffPice;
    }

    public String getDiscount() {
        return !TextUtils.isEmpty(this.discount) ? this.discount : "0";
    }

    public String getDiscountNew() {
        return this.discountNew;
    }

    public String getEbookUrl() {
        return this.ebookUrl;
    }

    public Long getEndTime() {
        return this.endTime;
    }

    public String getExpid() {
        return TextUtils.isEmpty(this.expid) ? "" : this.expid;
    }

    public String getExposureJsonSourceValue() {
        return this.exposureJsonSourceValue;
    }

    public String getExposureSourceValue() {
        return this.exposureSourceValue;
    }

    public long getFatherId() {
        return this.fatherId;
    }

    public String getFid() {
        return this.fid;
    }

    public String getFlags() {
        return this.flags;
    }

    public Integer getFlowOrder() {
        return this.flowOrder;
    }

    public String getFreeReadUrl() {
        return this.freeReadUrl;
    }

    public String getFullCut() {
        return this.fullCut;
    }

    public ArrayList<Product> getGiftList() {
        return this.giftList;
    }

    public String getGood() {
        return this.good;
    }

    public Boolean getHasChat() {
        Boolean bool = this.hasChat;
        return Boolean.valueOf(bool == null ? false : bool.booleanValue());
    }

    public String getHasComment() {
        String str = this.hasComment;
        return str == null ? "" : str;
    }

    public String getHasDiscuss() {
        String str = this.hasDiscuss;
        return str == null ? "" : str;
    }

    public int getHasPriceDiff() {
        return this.hasPriceDiff;
    }

    public Boolean getHasShop() {
        Boolean bool = this.hasShop;
        return Boolean.valueOf(bool == null ? false : bool.booleanValue());
    }

    public String getHistoryPrice() {
        return this.historyPrice;
    }

    public Long getId() {
        return this.id;
    }

    public Image getImage() {
        if (this.imageList.size() > 0) {
            return this.imageList.get(0);
        }
        return null;
    }

    public List<Image> getImageList() {
        return this.imageList;
    }

    public String getImageUrl() {
        if (this.imageList.size() > 0) {
            return this.imageList.get(0).small;
        }
        return null;
    }

    public String getImgPrice() {
        return this.imgPrice;
    }

    public String getImpr() {
        return this.impr;
    }

    public String getIndex() {
        return TextUtils.isEmpty(this.index) ? "" : this.index;
    }

    public String getInterlImgUrl() {
        return this.interlImgUrl;
    }

    public int getInternationalOrder() {
        return this.internationalOrder;
    }

    public int getInternationalType() {
        return this.internationalType;
    }

    public Boolean getIsBook() {
        return this.isBook;
    }

    public Boolean getIsBookDisc() {
        return this.isBookDisc;
    }

    public boolean getIsBooking() {
        return this.isBooking;
    }

    public Boolean getIsCarBlocked() {
        Boolean bool = this.isCarBlocked;
        return Boolean.valueOf(bool == null ? false : bool.booleanValue());
    }

    public Boolean getIsEbook() {
        Boolean bool = this.isEbook;
        return Boolean.valueOf(bool == null ? false : bool.booleanValue());
    }

    public Boolean getIsEche() {
        Boolean bool = this.isEche;
        return Boolean.valueOf(bool == null ? false : bool.booleanValue());
    }

    public Integer getIsFlashSale() {
        Integer num = this.isFlashSale;
        return Integer.valueOf(num == null ? 0 : num.intValue());
    }

    public Boolean getIsFood() {
        Boolean bool = this.isFood;
        return Boolean.valueOf(bool == null ? false : bool.booleanValue());
    }

    public boolean getIsHaveProductLogTag() {
        return this.isHaveProductLogTag;
    }

    public Boolean getIsInternational() {
        Boolean bool = this.isInternational;
        return Boolean.valueOf(bool == null ? false : bool.booleanValue());
    }

    public int getIsNewGoods() {
        return this.isNewGoods;
    }

    public Boolean getIsOrderProduct() {
        Boolean bool = this.isOrder;
        return bool == null ? Boolean.FALSE : bool;
    }

    public Boolean getIsPhoneVipPrice() {
        Boolean bool = this.isPhoneVipPrice;
        return Boolean.valueOf(bool == null ? false : bool.booleanValue());
    }

    public Boolean getIsPromotionDou() {
        Boolean bool = this.isPromotionDou;
        return Boolean.valueOf(bool == null ? false : bool.booleanValue());
    }

    public Boolean getIsPromotionJiang() {
        Boolean bool = this.isPromotionJiang;
        return Boolean.valueOf(bool == null ? false : bool.booleanValue());
    }

    public Boolean getIsPromotionQuan() {
        Boolean bool = this.isPromotionQuan;
        return Boolean.valueOf(bool == null ? false : bool.booleanValue());
    }

    public Boolean getIsPromotionShan() {
        Boolean bool = this.isPromotionShan;
        return Boolean.valueOf(bool == null ? false : bool.booleanValue());
    }

    public Boolean getIsPromotionTuan() {
        Boolean bool = this.isPromotionTuan;
        return Boolean.valueOf(bool == null ? false : bool.booleanValue());
    }

    public Boolean getIsPromotionZeng() {
        Boolean bool = this.isPromotionZeng;
        return Boolean.valueOf(bool == null ? false : bool.booleanValue());
    }

    public Boolean getIsShowDelButton() {
        return this.isShowDelButton;
    }

    public Boolean getIsShowNetContent() {
        Boolean bool = this.isShowNetContent;
        return Boolean.valueOf(bool == null ? false : bool.booleanValue());
    }

    public synchronized boolean getIsValid() {
        return isValid;
    }

    public String getJdDixcount() {
        Double valueOf;
        String string = StringUtil.getString(R.string.product_entity_no_dicount);
        try {
            String str = this.discount;
            if (str != null && (valueOf = Double.valueOf(str)) != null && valueOf.doubleValue() >= 0.0d) {
                return new DecimalFormat("0.00").format(valueOf);
            }
            return string;
        } catch (Exception unused) {
            return string;
        }
    }

    public String getJdPrice() {
        Double valueOf;
        String string = StringUtil.getString(R.string.product_entity_no_price);
        if (getIsEbook().booleanValue()) {
            string = FREE;
        }
        try {
            String str = this.jdPrice;
            if (str != null && (valueOf = Double.valueOf(str)) != null && valueOf.doubleValue() > 0.0d) {
                return new DecimalFormat("0.00").format(valueOf);
            }
            return string;
        } catch (Exception unused) {
            return string;
        }
    }

    public String getJdPriceNoNull() {
        return TextUtils.isEmpty(this.jdPrice) ? "" : this.jdPrice;
    }

    public String getJdPriceRMB() {
        String jdPrice = getJdPrice();
        if (TextUtils.equals(jdPrice, StringUtil.getString(R.string.product_entity_no_price))) {
            return jdPrice;
        }
        return "\u00a5" + jdPrice;
    }

    public String getJdPriceWithOutFormat() {
        return (TextUtils.isEmpty(this.jdPrice) || "-1".equals(this.jdPrice)) ? StringUtil.getString(R.string.product_entity_no_price) : this.jdPrice;
    }

    public String getJdPriceWithOutZero() {
        Double valueOf;
        String string = StringUtil.getString(R.string.product_entity_no_price);
        if (getIsEbook().booleanValue()) {
            string = FREE;
        }
        try {
            String str = this.jdPrice;
            if (str != null && (valueOf = Double.valueOf(str)) != null && valueOf.doubleValue() > 0.0d) {
                return new DecimalFormat("#.#").format(valueOf);
            }
            return string;
        } catch (Exception unused) {
            return string;
        }
    }

    public String getJdPriceWithZero() {
        String str = getIsEbook().booleanValue() ? FREE : "";
        try {
            String str2 = this.jdPrice;
            if (str2 != null) {
                Double valueOf = Double.valueOf(str2);
                return valueOf.doubleValue() < 0.0d ? str : new DecimalFormat("0.00").format(valueOf);
            }
            return str;
        } catch (Exception unused) {
            return str;
        }
    }

    public String getLogid() {
        return this.logid;
    }

    public String getLongImageUrl() {
        if (this.imageList.size() > 0) {
            return TextUtils.isEmpty(this.imageList.get(0).big) ? this.imageList.get(0).small : this.imageList.get(0).big;
        }
        return null;
    }

    public String getMarketPrice() {
        boolean z = !CommonBase.getMarketPriceFlag();
        if (OKLog.D) {
            OKLog.d("abc", "flag -->> " + z);
        }
        if (getShowMarketPrice() != null) {
            z = !getShowMarketPrice().booleanValue();
            if (OKLog.D) {
                OKLog.d(TAG, "xxxxx getShowMarketPrice-->> " + getShowMarketPrice());
            }
        }
        if (OKLog.D) {
            OKLog.d(TAG, "xxxxx getMarketPrice-->> " + z);
        }
        if (z) {
            return null;
        }
        return getMarketPriceValues();
    }

    public String getMarketPriceDescription() {
        String str = this.marketPriceDescription;
        if (str != null && !"".equals(str)) {
            return this.marketPriceDescription;
        }
        if (isBook().booleanValue()) {
            return StringUtil.getString(R.string.product_entity_fixed_price);
        }
        return StringUtil.getString(R.string.product_entity_market_price);
    }

    public String getMarketPriceValues() {
        Double valueOf;
        String str = CONST_NO_PRICE;
        try {
            String str2 = this.marketPrice;
            if (str2 != null && (valueOf = Double.valueOf(str2)) != null && valueOf.doubleValue() > 0.0d) {
                return new DecimalFormat("0.00").format(valueOf);
            }
            return str;
        } catch (Exception unused) {
            return str;
        }
    }

    public String getMessage() {
        return this.message;
    }

    public Boolean getMiaoSha() {
        Boolean bool = this.miaoSha;
        return Boolean.valueOf(bool != null ? bool.booleanValue() : false);
    }

    public String getMiaoShaPrice() {
        return (TextUtils.isEmpty(this.miaoShaPrice) || "-1".equals(this.miaoShaPrice)) ? StringUtil.getString(R.string.product_entity_no_price) : this.miaoShaPrice;
    }

    public String getMoreFunId() {
        return TextUtils.isEmpty(this.moreFunId) ? "" : this.moreFunId;
    }

    public String getMpageAddress() {
        return this.mpageAddress;
    }

    public String getMsgBody() {
        return this.sMsgBody.length() <= 0 ? LangUtils.SINGLE_SPACE : this.sMsgBody;
    }

    public String getMsgFlag() {
        return this.sMsgFlag.length() <= 0 ? LangUtils.SINGLE_SPACE : this.sMsgFlag;
    }

    public String getMsgTime() {
        return this.sMsgTime.length() <= 0 ? LangUtils.SINGLE_SPACE : this.sMsgTime;
    }

    public String getMsgType() {
        return this.sMsgType.length() <= 0 ? LangUtils.SINGLE_SPACE : this.sMsgType;
    }

    public String getMsgTypeName() {
        return this.msgTypeName;
    }

    public MultiSuppliers getMultiSuppliers() {
        return this.multiSuppliers;
    }

    public List<String> getMySearchProductTagInfos() {
        return this.mySearchProductTagInfos;
    }

    public String getName() {
        String str = this.name;
        return str != null ? str : StringUtil.getString(R.string.product_entity_no_name);
    }

    public String getNotifyPrice() {
        return this.notifyPrice;
    }

    public Integer getNum() {
        Integer num = this.num;
        if (num != null && num.intValue() > 0) {
            return this.num;
        }
        return 0;
    }

    public Boolean getOnline() {
        Boolean bool = this.online;
        return Boolean.valueOf(bool == null ? false : bool.booleanValue());
    }

    public String getOpenAppUrl() {
        return this.openAppUrl;
    }

    public String getOperateWord() {
        return this.operateWord;
    }

    public Long getOrderBegintime() {
        Long l2 = this.orderBegin;
        if (l2 == null) {
            return 0L;
        }
        return l2;
    }

    public String getOrderCheckCode() {
        return this.orderCheckCode;
    }

    public String getOrderCommentCount() {
        return this.orderCommentCount;
    }

    public Long getOrderEndtime() {
        Long l2 = this.orderEnd;
        if (l2 == null) {
            return 0L;
        }
        return l2;
    }

    public String getOrderId() {
        String str = this.sOrderId;
        return (str == null || str.length() <= 0) ? LangUtils.SINGLE_SPACE : this.sOrderId;
    }

    public String getOrderPrice() {
        String str = this.sTotalPrice;
        return (str == null || str.length() <= 0) ? "" : this.sTotalPrice.trim();
    }

    public String getOrderShopName() {
        return this.shopName;
    }

    public String getOrderStatus() {
        return this.sOrderStatus.length() <= 0 ? LangUtils.SINGLE_SPACE : this.sOrderStatus;
    }

    public String getOrderStatusShow() {
        return this.orderStatusShow;
    }

    public String getOrderSubtime() {
        return this.sSubmitTime.length() <= 0 ? LangUtils.SINGLE_SPACE : this.sSubmitTime;
    }

    public int getOrderType() {
        return this.orderType;
    }

    public String getP13nFlags() {
        return this.p13nFlags;
    }

    public String getPayTypeCode() {
        return this.payTypeCode;
    }

    public Boolean getPostByJd() {
        Boolean bool = this.postByjd;
        return Boolean.valueOf(bool == null ? false : bool.booleanValue());
    }

    public String getPriceDiff() {
        return this.priceDiff;
    }

    public String getPriceDiffText() {
        return this.priceDiffText;
    }

    public String getPriceForAfterDiscount() {
        String string = StringUtil.getString(R.string.product_entity_no_price);
        try {
            double doubleValue = Double.valueOf(this.jdPrice).doubleValue() - Double.valueOf(this.discount).doubleValue();
            if (doubleValue <= 0.0d) {
                return string;
            }
            return "\u00a5" + new DecimalFormat("0.00").format(doubleValue);
        } catch (Exception unused) {
            return string;
        }
    }

    public boolean getPriceKeep() {
        return this.priceKeep;
    }

    public Boolean getPriceReport() {
        Boolean bool = this.priceReport;
        return Boolean.valueOf(bool == null ? false : bool.booleanValue());
    }

    public String getPriority() {
        return this.priority;
    }

    public ProductDetailBasicInfo getProductDetailBasicInfo() {
        if (this.productDetailBasicInfo == null) {
            this.productDetailBasicInfo = new ProductDetailBasicInfo();
        }
        return this.productDetailBasicInfo;
    }

    public ProductDetailCruxBasicInfo getProductDetailCruxBasicInfo() {
        if (this.productDetailCruxBasicInfo == null) {
            this.productDetailCruxBasicInfo = new ProductDetailCruxBasicInfo();
        }
        return this.productDetailCruxBasicInfo;
    }

    public DefaultAddressMode getProductDetailDefaultAddress() {
        if (this.productDetailDefaultAddress == null) {
            this.productDetailDefaultAddress = new DefaultAddressMode();
        }
        return this.productDetailDefaultAddress;
    }

    public ProductDetailPrice getProductDetailJprice() {
        if (this.productDetailJprice == null) {
            this.productDetailJprice = new ProductDetailPrice();
        }
        return this.productDetailJprice;
    }

    public ProductDetailPrice getProductDetailMprice() {
        if (this.productDetailMprice == null) {
            this.productDetailMprice = new ProductDetailPrice();
        }
        return this.productDetailMprice;
    }

    public ProductDetailPrice getProductDetailPcprice() {
        if (this.productDetailPcprice == null) {
            this.productDetailPcprice = new ProductDetailPrice();
        }
        return this.productDetailPcprice;
    }

    public ArrayList<ProductDetailSkuColor> getProductDetailSkuColor() {
        ArrayList<ProductDetailSkuColor> arrayList = this.productDetailSkuColor;
        if (arrayList == null || arrayList.size() == 0) {
            this.productDetailSkuColor = new ArrayList<>();
        }
        return this.productDetailSkuColor;
    }

    public ArrayList<ProductDetailSkuSize> getProductDetailSkuSize() {
        ArrayList<ProductDetailSkuSize> arrayList = this.productDetailSkuSize;
        if (arrayList == null || arrayList.size() == 0) {
            this.productDetailSkuSize = new ArrayList<>();
        }
        return this.productDetailSkuSize;
    }

    public ProductFeeInfo getProductFeeInfo() {
        return this.productFeeInfo;
    }

    public ArrayList<Product> getProductList() {
        return this.mProductList;
    }

    public Integer getProductStatusType() {
        Integer num = this.productStatusType;
        if (num == null) {
            return 5;
        }
        return num;
    }

    public int getPromFlag() {
        return this.promFlag;
    }

    public String getPromName() {
        return this.promName;
    }

    public String getPromotionIconUrl() {
        return this.promotionIconUrl;
    }

    public String getPromotionInfo() {
        return this.promotionInfo;
    }

    public String getPromotionTitle() {
        return this.promotionTitle;
    }

    public String getProvinceID() {
        return this.provinceID;
    }

    public Integer getProvinceIdMode1() {
        return this.provinceIdMode1;
    }

    public ArrayList<Province> getProvinceList() {
        return this.provinceList;
    }

    public Integer getProvinceMode1IndexById(int i2) {
        return this.provinceMode1Map.get(Integer.valueOf(i2));
    }

    public ArrayList<ProvinceMode1> getProvinceMode1List() {
        return this.provinceMode1List;
    }

    public ArrayList<AddressBaseMode> getProvinceModeList() {
        return this.provinceModeList;
    }

    public String getProvinceName() {
        return this.provinceName;
    }

    public String getProvinceStockContent() {
        return (TextUtils.isEmpty(this.provinceStockContent) || DYConstants.DY_NULL_STR.equals(this.provinceStockContent) || "NULL".equals(this.provinceStockContent)) ? "" : this.provinceStockContent;
    }

    public Boolean getProvinceStockFlag() {
        return this.provinceStockFlag;
    }

    public Integer getProvinceStockMode() {
        return this.provinceStockMode;
    }

    public String getPsPrice() {
        Double valueOf;
        String string = StringUtil.getString(R.string.product_entity_no_price);
        if (getIsEbook().booleanValue()) {
            string = FREE;
        }
        try {
            String str = this.psPrice;
            if (str != null && (valueOf = Double.valueOf(str)) != null && valueOf.doubleValue() > 0.0d) {
                return new DecimalFormat("0.00").format(valueOf);
            }
            return string;
        } catch (Exception unused) {
            return string;
        }
    }

    public int getPurchaseReminder() {
        return this.purchaseReminder;
    }

    public String getPurchaseReminderIcon() {
        return this.purchaseReminderIcon;
    }

    public String getRate() {
        return this.rate;
    }

    public Integer getRemainNum() {
        return this.remainNum;
    }

    public String getRid() {
        return TextUtils.isEmpty(this.rid) ? "" : this.rid;
    }

    public List<Sale> getSalesList() {
        return this.salesList;
    }

    public String getSendPay() {
        return this.sendPay;
    }

    public ArrayList<ServerIcon> getServerIconList() {
        return this.mServerIcons;
    }

    public long getShopId() {
        return this.shopId;
    }

    public String getShopName() {
        return this.shopName;
    }

    public String getShopUrl() {
        return this.shopUrl;
    }

    public Long getShowId() {
        Long l2 = this.showId;
        return l2 == null ? getId() : l2;
    }

    public String getShowLabel() {
        return TextUtils.isEmpty(this.showLabel) ? "" : this.showLabel;
    }

    public Integer getShowLabelId() {
        return this.showLabelId;
    }

    public Boolean getShowMarketPrice() {
        return this.showMarketPrice;
    }

    public ArrayList<SkuColor> getSkuColorList() {
        return this.skuColorList;
    }

    public String getSkuId() {
        return TextUtils.isEmpty(this.skuId) ? "" : this.skuId;
    }

    public ArrayList<SkuSize> getSkuSizeList() {
        return this.skuSizeList;
    }

    public String getSkuType() {
        return this.skuType;
    }

    public int getSkuTypeId() {
        return this.skuTypeId;
    }

    public Integer getSoldRate() {
        return this.soldRate;
    }

    public SourceEntity getSourceEntity() {
        return this.sourceEntity;
    }

    public int getSourceID() {
        return this.sourceID;
    }

    public String getSourceValue() {
        return TextUtils.isEmpty(this.sourceValue) ? "" : this.sourceValue;
    }

    public Integer getSpecialKill() {
        return this.specialKill;
    }

    public String getSpuId() {
        return TextUtils.isEmpty(this.spuId) ? "" : this.spuId;
    }

    public DiliverManInfo getStaffInfo() {
        return this.staffInfo;
    }

    public Long getStartTime() {
        Long l2 = this.startTime;
        if (l2 == null) {
            return 0L;
        }
        return l2;
    }

    public String getStartTimeContent() {
        return this.startTimeContent;
    }

    public String getStartTimeShow() {
        return this.startTimeShow;
    }

    public String getStockFunction() {
        return this.stockFunction;
    }

    public int getStockQuantity() {
        return this.stockQuantity;
    }

    public Integer getStockState() {
        return this.stockState;
    }

    public Integer getStockStateId() {
        return this.stockStateId;
    }

    public String getStockStateStr() {
        return this.stockStateStr;
    }

    public Boolean getSubOrderFlag() {
        return this.subOrderFlag;
    }

    public String getSuperNew() {
        return this.superNew;
    }

    public int getSupportSizeType() {
        return this.supportSizeType;
    }

    public String getTag() {
        return this.tag;
    }

    public String getTagText() {
        return this.tagText;
    }

    public int getTagType() {
        return this.tagType;
    }

    public String getTargetUrl() {
        return this.targetUrl;
    }

    public String getToMURL() {
        return this.toMURL;
    }

    public Integer getTotalCount() {
        return this.totalCount;
    }

    public String getTownID() {
        return this.townId;
    }

    public ArrayList<AddressBaseMode> getTownModeList() {
        return this.townModeList;
    }

    public String getTownName() {
        return this.townName;
    }

    public String getTraceMessageTime() {
        return this.messageTime;
    }

    public String getUserBalance() {
        String str = this.sUserBalance;
        return str == null ? "" : str;
    }

    public String getUserClass() {
        String str = this.sUserClass;
        return str == null ? "" : str;
    }

    public String getUserPriceContent() {
        return this.userPriceContent;
    }

    public String getUserPriceLabel() {
        return this.userPriceLabel;
    }

    public String getUserScore() {
        return this.sUerScore.length() > 0 ? this.sUerScore : "0";
    }

    public String getUsername() {
        return this.sUserName.length() <= 0 ? "Customer" : this.sUserName;
    }

    public long getVenderId() {
        return this.venderId;
    }

    public String getVenderName() {
        if (OKLog.D) {
            OKLog.d("abc", "venderName = " + this.venderName);
        }
        if (!TextUtils.isEmpty(this.venderName) && !TextUtils.equals(this.venderName, DYConstants.DY_NULL_STR)) {
            return this.venderName;
        }
        return StringUtil.getString(R.string.product_entity_self_support);
    }

    public int getVenderType() {
        return this.venderType.intValue();
    }

    public String getViewInvitationUrl() {
        return this.viewInvitationUrl;
    }

    public VirtualOrderInfo getVirtualOrderInfo() {
        return this.virtualOrderInfo;
    }

    public String getVoucherStatus() {
        return this.voucherStatus;
    }

    public int getWareCount() {
        return this.wareCount;
    }

    public WarePromotionInfo getWarePromotionInfo() {
        return this.warePromotionInfo;
    }

    public WareRankInfo getWareRank() {
        return this.wareRank;
    }

    public String getWareType() {
        return this.wareType;
    }

    public YushouOrder getYushouOrder() {
        return this.yushouOrder;
    }

    public Integer getYuyueNum() {
        Integer num = this.yuyueNum;
        if (num == null) {
            return 0;
        }
        return num;
    }

    public String getmPaymentType() {
        return this.mPaymentType;
    }

    public String getmShaShopId() {
        return this.mShaShopId;
    }

    public String getsMsgId() {
        return this.sMsgId;
    }

    public String getsMsgUpdateTime() {
        return this.sMsgUpdateTime;
    }

    public boolean isAvailable() {
        return this.available;
    }

    public Boolean isBook() {
        Boolean bool = this.isBook;
        return Boolean.valueOf(bool != null ? bool.booleanValue() : false);
    }

    public boolean isCanBeDelete() {
        return this.canBeDelete;
    }

    public boolean isCanEasyBuy() {
        return this.canEasyBuy;
    }

    public Boolean isCanGoToShop() {
        Boolean bool = this.canGoToShop;
        return Boolean.valueOf(bool == null ? false : bool.booleanValue());
    }

    public boolean isFavoCheckBoxChecked() {
        return this.isFavoCheckBoxChecked;
    }

    public boolean isFavorited() {
        return this.isFavorited;
    }

    public Boolean isFiledExist(JSONObjectProxy jSONObjectProxy, String str) {
        return Boolean.valueOf(jSONObjectProxy.toString().contains(str));
    }

    public boolean isHasPacks() {
        return this.hasPacks;
    }

    public boolean isHot() {
        return this.isHot;
    }

    public boolean isLookSimilar() {
        return !TextUtils.isEmpty(this.lookSimilar) && "1".equals(this.lookSimilar);
    }

    public boolean isNew() {
        return this.isNew;
    }

    public boolean isOnshelf() {
        return this.onshelf;
    }

    public Boolean isPromotion() {
        Boolean bool = this.isPromotion;
        return Boolean.valueOf(bool != null ? bool.booleanValue() : false);
    }

    public boolean isSaleExpand() {
        return this.isSaleExpand;
    }

    public boolean isSelf() {
        return this.self;
    }

    public boolean isShowCartIcon() {
        return this.showCartIcon;
    }

    public void isVirtualOrder(boolean z) {
        this.isVirtualOrder = z;
    }

    public String popImgUrl(int i2) {
        if (i2 <= -1 || this.sImgUrlList.size() <= 0 || i2 >= this.sImgUrlList.size()) {
            return null;
        }
        return this.sImgUrlList.get(i2).toString();
    }

    public void putInCityMode1Map(Long l2, CityMode1 cityMode1) {
        if (this.cityMode1Map == null) {
            this.cityMode1Map = new HashMap<>();
        }
        if (this.cityMode1Map.containsKey(l2)) {
            return;
        }
        this.cityMode1Map.put(l2, cityMode1);
    }

    public void setAdEventId(String str) {
        this.adEventId = str;
    }

    public void setAdWord(String str) {
        this.adWord = str;
    }

    public void setAddFavoriteMsg(String str) {
        this.addFavoriteMsg = str;
    }

    public void setAreaTips(String str) {
        this.areaTips = str;
    }

    public void setAuthor(String str) {
        this.author = str;
    }

    public void setAvailable(boolean z) {
        this.available = z;
    }

    public void setAvailableInStore(Boolean bool) {
        this.availableInStore = bool;
    }

    public void setAverageScore(Long l2) {
        this.averageScore = l2;
    }

    public void setBn(String str) {
        this.bn = str;
    }

    public void setBook(Boolean bool) {
        this.isBook = bool;
    }

    public void setBrowserTime(long j2) {
        this.browserTime = j2;
    }

    public void setBuyAgain(long j2) {
        this.buyAgain = j2;
    }

    public void setBuyBegintime(long j2) {
        this.buyBegin = Long.valueOf(j2);
    }

    public void setBuyCount(int i2) {
        this.buyCount = i2;
    }

    public void setBuyEndtime(long j2) {
        this.buyEnd = Long.valueOf(j2);
    }

    public void setCId(String str) {
        this.cId = str;
    }

    public void setCName(String str) {
        this.cName = str;
    }

    public void setCanBeDelete(boolean z) {
        this.canBeDelete = z;
    }

    public void setCanConsultFlag(Boolean bool) {
        this.canConsultFlag = bool;
    }

    public void setCanEasyBuy(boolean z) {
        this.canEasyBuy = z;
    }

    public void setCanFreeRead(Boolean bool) {
        this.canFreeRead = bool;
    }

    public void setCanGoToShop(Boolean bool) {
        this.canGoToShop = bool;
    }

    public void setCatId(Long l2) {
        this.catId = l2;
    }

    public void setCheckNext(boolean z) {
        this.isCheckNext = z;
        if (OKLog.D) {
            OKLog.d(TAG, "isCheckNext -->> " + z);
        }
    }

    public void setCityId(String str) {
        this.cityId = str;
    }

    public void setCityIdMode1(Integer num) {
        this.cityIdMode1 = num;
    }

    public void setCityModeList(ArrayList<AddressBaseMode> arrayList) {
        this.cityModeList = arrayList;
    }

    public void setCityName(String str) {
        this.cityName = str;
    }

    public void setCommentCountList(ArrayList<CommentCount> arrayList) {
        this.commentCountList = arrayList;
    }

    public void setCommentGuid(String str) {
        this.commentGuid = str;
    }

    public void setCommentText(String str) {
        this.commentText = str;
    }

    public void setConsultationCount(String str) {
        this.consultationCount = str;
    }

    public void setCountyID(String str) {
        this.countyId = str;
    }

    public void setCountyModeList(ArrayList<AddressBaseMode> arrayList) {
        this.countyModeList = arrayList;
    }

    public void setCountyName(String str) {
        this.countyName = str;
    }

    public void setCouponList(ArrayList<Coupon> arrayList) {
        this.couponList = arrayList;
    }

    public void setCustomAttr(String str) {
        this.customAttr = str;
    }

    public void setCustomAttrList(JSONArrayPoxy jSONArrayPoxy) {
        if (jSONArrayPoxy == null) {
            this.customAttrListJson = null;
        } else {
            this.customAttrListJson = JDJSON.parseArray(jSONArrayPoxy.toString());
        }
    }

    public void setDefaultAddressMode(DefaultAddressMode defaultAddressMode) {
        this.defaultAddressMode = defaultAddressMode;
    }

    public void setDefaultAddressModeList(ArrayList<DefaultAddressMode> arrayList) {
        this.defaultAddressModeList = arrayList;
    }

    public void setDeliver(String str) {
        this.deliver = str;
    }

    public void setDeliveryCommentFlag(String str) {
        this.deliveryCommentFlag = str;
    }

    public void setDeliveryFlag(String str) {
        this.deliveryFlag = str;
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public void setDiffMobilePrice(String str) {
        this.diffMobilePrice = str;
    }

    public void setDiscount(String str) {
        this.discount = str;
    }

    public void setDiscountNew(String str) {
        this.discountNew = str;
    }

    public void setEbookUrl(String str) {
        this.ebookUrl = str;
    }

    public void setEndTime(Long l2) {
        this.endTime = Long.valueOf(l2.longValue() * 1000);
    }

    public void setExcutableButtonsList(ArrayList<ExcutableButton> arrayList) {
        this.excutableButtonsList = arrayList;
    }

    public void setExpid(String str) {
        this.expid = str;
    }

    public void setExposureJsonSourceValue(String str) {
        this.exposureJsonSourceValue = str;
    }

    public void setExposureSourceValue(String str) {
        this.exposureSourceValue = str;
    }

    public void setFatherId(long j2) {
        this.fatherId = j2;
    }

    public void setFavoCheckBoxChecked(boolean z) {
        this.isFavoCheckBoxChecked = z;
    }

    public void setFavorited(boolean z) {
        this.isFavorited = z;
    }

    public void setFid(String str) {
        this.fid = str;
    }

    public void setFlags(String str) {
        this.flags = str;
    }

    public void setFlowOrder(Integer num) {
        this.flowOrder = num;
    }

    public void setFreeReadUrl(String str) {
        this.freeReadUrl = str;
    }

    public void setFullCut(String str) {
        this.fullCut = str;
    }

    public void setGiftList(ArrayList<Product> arrayList) {
        this.giftList = arrayList;
    }

    public void setGood(String str) {
        this.good = str;
    }

    public void setHasChat(Boolean bool) {
        this.hasChat = bool;
    }

    public void setHasComment(String str) {
        this.hasComment = str;
    }

    public void setHasDiscuss(String str) {
        this.hasDiscuss = str;
    }

    public void setHasPacks(boolean z) {
        this.hasPacks = z;
    }

    public void setHasPriceDiff(int i2) {
        this.hasPriceDiff = i2;
    }

    public void setHasShop(Boolean bool) {
        this.hasShop = bool;
    }

    public void setHistoryPrice(String str) {
        this.historyPrice = str;
    }

    public void setId(Long l2) {
        this.id = l2;
    }

    public void setImage(String str, String str2) {
        this.imageList.add(new Image(str, str2));
    }

    public void setImgPrice(String str) {
        this.imgPrice = str;
    }

    public void setImpr(String str) {
        this.impr = str;
    }

    public void setIndex(String str) {
        this.index = str;
    }

    public void setInterlImgUrl(String str) {
        this.interlImgUrl = str;
    }

    public void setInternationalOrder(int i2) {
        this.internationalOrder = i2;
    }

    public void setInternationalType(int i2) {
        this.internationalType = i2;
    }

    public void setIsBook(Boolean bool) {
        this.isBook = bool;
    }

    public void setIsBookDisc(Boolean bool) {
        this.isBookDisc = bool;
    }

    public void setIsBooking(boolean z) {
        this.isBooking = z;
    }

    public void setIsCarBlocked(Boolean bool) {
        this.isCarBlocked = bool;
    }

    public void setIsEbook(Boolean bool) {
        this.isEbook = bool;
    }

    public void setIsEche(Boolean bool) {
        this.isEche = bool;
    }

    public void setIsFlashSale(Integer num) {
        this.isFlashSale = num;
    }

    public void setIsFood(Boolean bool) {
        this.isFood = bool;
    }

    public void setIsHaveProductLogTag(boolean z) {
        this.isHaveProductLogTag = z;
    }

    public void setIsHot(boolean z) {
        this.isHot = z;
    }

    public void setIsInternational(Boolean bool) {
        this.isInternational = bool;
    }

    public void setIsNew(boolean z) {
        this.isNew = z;
    }

    public void setIsNewGoods(int i2) {
        this.isNewGoods = i2;
    }

    public void setIsPhoneVipPrice(Boolean bool) {
        this.isPhoneVipPrice = bool;
    }

    public void setIsPromotionDou(Boolean bool) {
        this.isPromotionDou = bool;
    }

    public void setIsPromotionJiang(Boolean bool) {
        this.isPromotionJiang = bool;
    }

    public void setIsPromotionQuan(Boolean bool) {
        this.isPromotionQuan = bool;
    }

    public void setIsPromotionShan(Boolean bool) {
        this.isPromotionShan = bool;
    }

    public void setIsPromotionTuan(Boolean bool) {
        this.isPromotionTuan = bool;
    }

    public void setIsPromotionZeng(Boolean bool) {
        this.isPromotionZeng = bool;
    }

    public void setIsShowDelButton(Boolean bool) {
        this.isShowDelButton = bool;
    }

    public void setIsShowNetcontent(Boolean bool) {
        this.isShowNetContent = bool;
    }

    public synchronized void setIsValid(boolean z) {
        isValid = z;
    }

    public void setItemCount(int i2) {
        this.nItemCount = i2;
    }

    public void setJdDixcount(String str) {
        this.discount = str;
    }

    public void setJdPrice(String str) {
        this.jdPrice = str;
    }

    public void setLogid(String str) {
        this.logid = str;
    }

    public void setLookSimilar(String str) {
        this.lookSimilar = str;
    }

    public void setMarketPrice(String str) {
        this.marketPrice = str;
    }

    public void setMarketPriceDescription(String str) {
        this.marketPriceDescription = str;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setMessageBody(String str) {
        this.sMsgBody = str;
    }

    public void setMessageFlag(String str) {
        this.sMsgFlag = str;
    }

    public void setMessageTime(String str) {
        this.sMsgTime = str;
    }

    public void setMessageType(String str) {
        this.sMsgType = str;
    }

    public void setMiaoSha(Boolean bool) {
        this.miaoSha = bool;
    }

    public void setMiaoShaPrice(String str) {
        this.miaoShaPrice = str;
    }

    public void setMoreFunId(String str) {
        this.moreFunId = str;
    }

    public void setMpageAddress(String str) {
        this.mpageAddress = str;
    }

    public void setMsgTypeName(String str) {
        this.msgTypeName = str;
    }

    public void setMultiSuppliers(MultiSuppliers multiSuppliers) {
        this.multiSuppliers = multiSuppliers;
    }

    public void setMySearchProductTagInfos(List<String> list) {
        this.mySearchProductTagInfos = list;
    }

    public void setName(String str) {
        if (str == null) {
            this.name = str;
            return;
        }
        try {
            Matcher matcher = Pattern.compile("([^a-zA-Z0-9\uff08\uff09\\(\\) ])([a-zA-Z\uff08\\(])|([^ ])([\uff08\\(])|([\uff08\\(])([^ ])|([A-Z0-9])(\\-)|(\\-)([A-Z0-9])|([0-9]*[A-Z]+[0-9]*)([^a-zA-Z0-9\uff08\uff09\\(\\) ])").matcher(str);
            StringBuffer stringBuffer = new StringBuffer();
            while (matcher.find()) {
                StringBuffer stringBuffer2 = new StringBuffer();
                int i2 = 1;
                while (true) {
                    if (i2 > matcher.groupCount()) {
                        break;
                    } else if (matcher.group(i2) != null) {
                        stringBuffer2.append(matcher.group(i2));
                        stringBuffer2.append(LangUtils.SINGLE_SPACE);
                        stringBuffer2.append(matcher.group(i2 + 1));
                        break;
                    } else {
                        i2++;
                    }
                }
                if (OKLog.D) {
                    OKLog.d("Temp", "name -->> " + str);
                    OKLog.d("Temp", "stringBuffer.toString() -->> " + stringBuffer.toString());
                    OKLog.d("Temp", "sb.toString() -->> " + stringBuffer2.toString());
                }
                matcher.appendReplacement(stringBuffer, stringBuffer2.toString());
            }
            matcher.appendTail(stringBuffer);
            stringBuffer.append(LangUtils.SINGLE_SPACE);
            this.name = stringBuffer.toString();
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
            this.name = str;
        }
    }

    public void setNotifyPrice(String str) {
        this.notifyPrice = str;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public void setOnline(Boolean bool) {
        this.online = bool;
    }

    public void setOnshelf(boolean z) {
        this.onshelf = z;
    }

    public void setOpenAppUrl(String str) {
        this.openAppUrl = str;
    }

    public void setOperateWord(String str) {
        this.operateWord = str;
    }

    public void setOrderCheckCode(String str) {
        this.orderCheckCode = str;
    }

    public void setOrderCommentCount(String str) {
        this.orderCommentCount = str;
    }

    public void setOrderEndtime(long j2) {
        this.orderEnd = Long.valueOf(j2);
    }

    public void setOrderId(String str) {
        this.sOrderId = str;
    }

    public void setOrderInfo(JSONObjectProxy jSONObjectProxy) {
        if (jSONObjectProxy == null) {
            return;
        }
        setOrderInfo(JDJSON.parseObject(jSONObjectProxy.toString()));
    }

    public void setOrderPrice(String str) {
        this.sTotalPrice = str;
    }

    public void setOrderStatus(String str) {
        this.sOrderStatus = str;
    }

    public void setOrderStatusShow(String str) {
        this.orderStatusShow = str;
    }

    public void setOrderSubtime(String str) {
        this.sSubmitTime = str;
    }

    public void setOrderType(int i2) {
        this.orderType = i2;
    }

    public void setP13nFlags(String str) {
        this.p13nFlags = str;
    }

    public void setPayTypeCode(String str) {
        this.payTypeCode = str;
    }

    public void setPostByJd(Boolean bool) {
        this.postByjd = bool;
    }

    public void setPriceDiff(String str) {
        this.priceDiff = str;
    }

    public void setPriceDiffText(String str) {
        this.priceDiffText = str;
    }

    public void setPriceKeep(boolean z) {
        this.priceKeep = z;
    }

    public void setPriceReport(Boolean bool) {
        this.priceReport = bool;
    }

    public void setPriority(String str) {
        this.priority = str;
    }

    public void setProductDetailBasicInfo(ProductDetailBasicInfo productDetailBasicInfo) {
        this.productDetailBasicInfo = productDetailBasicInfo;
    }

    public void setProductDetailCruxBasicInfo(ProductDetailCruxBasicInfo productDetailCruxBasicInfo) {
        this.productDetailCruxBasicInfo = productDetailCruxBasicInfo;
    }

    public void setProductDetailDefaultAddress(DefaultAddressMode defaultAddressMode) {
        this.productDetailDefaultAddress = defaultAddressMode;
    }

    public void setProductDetailJprice(ProductDetailPrice productDetailPrice) {
        this.productDetailJprice = productDetailPrice;
    }

    public void setProductDetailMprice(ProductDetailPrice productDetailPrice) {
        this.productDetailMprice = productDetailPrice;
    }

    public void setProductDetailPcprice(ProductDetailPrice productDetailPrice) {
        this.productDetailPcprice = productDetailPrice;
    }

    public void setProductDetailSkuColor(ArrayList<ProductDetailSkuColor> arrayList) {
        this.productDetailSkuColor = arrayList;
    }

    public void setProductDetailSkuSize(ArrayList<ProductDetailSkuSize> arrayList) {
        this.productDetailSkuSize = arrayList;
    }

    public void setProductFeeInfo(ProductFeeInfo productFeeInfo) {
        this.productFeeInfo = productFeeInfo;
    }

    public void setProductList(ArrayList<Product> arrayList) {
        this.mProductList = arrayList;
    }

    public void setPromFlag(int i2) {
        this.promFlag = i2;
    }

    public void setPromName(String str) {
        this.promName = str;
    }

    public void setPromotion(Boolean bool) {
        this.isPromotion = bool;
    }

    public void setPromotionIconUrl(String str) {
        this.promotionIconUrl = str;
    }

    public void setPromotionInfo(String str) {
        this.promotionInfo = str;
    }

    public void setPromotionTitle(String str) {
        this.promotionTitle = str;
    }

    public void setProvinceID(String str) {
        this.provinceID = str;
    }

    public void setProvinceIdMode1(Integer num) {
        this.provinceIdMode1 = num;
    }

    public void setProvinceList(ArrayList<Province> arrayList) {
        this.provinceList = arrayList;
    }

    public void setProvinceMode1List(ArrayList<ProvinceMode1> arrayList) {
        this.provinceMode1List = arrayList;
        this.provinceMode1Map = new HashMap<>();
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            this.provinceMode1Map.put(Integer.valueOf(arrayList.get(i2).id), Integer.valueOf(i2));
        }
    }

    public void setProvinceModeList(ArrayList<AddressBaseMode> arrayList) {
        this.provinceModeList = arrayList;
    }

    public void setProvinceName(String str) {
        this.provinceName = str;
    }

    public void setProvinceStockContent(String str) {
        this.provinceStockContent = str;
    }

    public void setProvinceStockFlag(Boolean bool) {
        this.provinceStockFlag = bool;
    }

    public void setProvinceStockMode(Integer num) {
        this.provinceStockMode = num;
    }

    public void setPsPrice(String str) {
        this.psPrice = str;
    }

    public void setPurchaseReminder(int i2) {
        this.purchaseReminder = i2;
    }

    public void setPurchaseReminderIcon(String str) {
        this.purchaseReminderIcon = str;
    }

    public void setRate(String str) {
        this.rate = str;
    }

    public void setRid(String str) {
        this.rid = str;
    }

    public void setSaleExpand(boolean z) {
        this.isSaleExpand = z;
    }

    public void setSelf(boolean z) {
        this.self = z;
    }

    public void setSendPay(String str) {
        this.sendPay = str;
    }

    public void setServerIconList(ArrayList<ServerIcon> arrayList) {
        this.mServerIcons = arrayList;
    }

    public void setShopId(long j2) {
        this.shopId = j2;
    }

    public void setShopName(String str) {
        this.shopName = str;
    }

    public void setShopUrl(String str) {
        this.shopUrl = str;
    }

    public void setShowCartIcon(boolean z) {
        this.showCartIcon = z;
    }

    public void setShowId(Long l2) {
        this.showId = l2;
    }

    public void setShowLabel(String str) {
        this.showLabel = str;
    }

    public void setShowLabelId(Integer num) {
        this.showLabelId = num;
    }

    public void setShowMarketPrice(Boolean bool) {
        this.showMarketPrice = bool;
    }

    public void setSkuColorList(ArrayList<SkuColor> arrayList) {
        this.skuColorList = arrayList;
    }

    public void setSkuId(String str) {
        this.skuId = str;
    }

    public void setSkuSizeList(ArrayList<SkuSize> arrayList) {
        this.skuSizeList = arrayList;
    }

    public void setSkuType(String str) {
        this.skuType = str;
    }

    public void setSkuTypeId(int i2) {
        this.skuTypeId = i2;
    }

    public void setSoldRate(Integer num) {
        this.soldRate = num;
    }

    public void setSourceEntity(SourceEntity sourceEntity) {
        this.sourceEntity = sourceEntity;
    }

    public void setSourceID(int i2) {
        this.sourceID = i2;
    }

    public void setSourceValue(String str) {
        this.sourceValue = str;
    }

    public void setSpecialKill(Integer num) {
        this.specialKill = num;
    }

    public void setSpuId(String str) {
        this.spuId = str;
    }

    public void setStaffInfo(DiliverManInfo diliverManInfo) {
        this.staffInfo = diliverManInfo;
    }

    public void setStartTime(Long l2) {
        if (l2 != null) {
            this.startTime = Long.valueOf(l2.longValue() * 1000);
        }
    }

    public void setStartTimeContent(String str) {
        this.startTimeContent = str;
    }

    public void setStartTimeShow(String str) {
        this.startTimeShow = str;
    }

    public void setStockFunction(String str) {
        this.stockFunction = str;
    }

    public void setStockQuantity(int i2) {
        this.stockQuantity = i2;
    }

    public void setStockState(Integer num) {
        this.stockState = num;
    }

    public void setStockStateId(Integer num) {
        this.stockStateId = num;
    }

    public void setStockStateStr(String str) {
        this.stockStateStr = str;
    }

    public void setSubOrderFlag(Boolean bool) {
        this.subOrderFlag = bool;
    }

    public void setSuperNew(String str) {
        this.superNew = str;
    }

    public void setSupportSizeType(int i2) {
        this.supportSizeType = i2;
    }

    public void setTagText(String str) {
        this.tagText = str;
    }

    public void setTagType(int i2) {
        this.tagType = i2;
    }

    public void setTargetUrl(String str) {
        this.targetUrl = str;
    }

    public void setToMURL(String str) {
        this.toMURL = str;
    }

    public void setTotalCount(Integer num) {
        this.totalCount = num;
    }

    public void setTownID(String str) {
        this.townId = str;
    }

    public void setTownModeList(ArrayList<AddressBaseMode> arrayList) {
        this.townModeList = arrayList;
    }

    public void setTownName(String str) {
        this.townName = str;
    }

    public void setTraceMessageTime(String str) {
        this.messageTime = str;
    }

    public void setUserBalance(String str) {
        this.sUserBalance = str;
    }

    public void setUserClass(String str) {
        this.sUserClass = str;
    }

    public void setUserPriceContent(String str) {
        this.userPriceContent = str;
    }

    public void setUserPriceLabel(String str) {
        this.userPriceLabel = str;
    }

    public void setUserScore(String str) {
        this.sUerScore = str;
    }

    public void setUsername(String str) {
        this.sUserName = str;
    }

    public void setVenderId(long j2) {
        this.venderId = j2;
    }

    public void setVenderName(String str) {
        this.venderName = str;
    }

    public void setVenderType(Integer num) {
        this.venderType = num;
    }

    public void setViewInvitationUrl(String str) {
        this.viewInvitationUrl = str;
    }

    public void setVirtualOrderInfo(VirtualOrderInfo virtualOrderInfo) {
        this.virtualOrderInfo = virtualOrderInfo;
    }

    public void setVoucherStatus(String str) {
        this.voucherStatus = str;
    }

    public void setWareCount(int i2) {
        this.wareCount = i2;
    }

    public void setWarePromotionInfo(WarePromotionInfo warePromotionInfo) {
        this.warePromotionInfo = warePromotionInfo;
    }

    public void setWareRank(WareRankInfo wareRankInfo) {
        this.wareRank = wareRankInfo;
    }

    public void setWareType(String str) {
        this.wareType = str;
    }

    public void setmPaymentType(String str) {
        this.mPaymentType = str;
    }

    public void setmShaShopId(String str) {
        this.mShaShopId = str;
    }

    public void setsMsgId(String str) {
        this.sMsgId = str;
    }

    public void setsMsgUpdateTime(String str) {
        this.sMsgUpdateTime = str;
    }

    public void update(JSONObjectProxy jSONObjectProxy, JSONArray jSONArray, int i2) {
        update(jSONObjectProxy, jSONArray, i2, (Object[]) null);
    }

    public static ArrayList<Product> toList(JDJSONArray jDJSONArray, int i2) {
        return toList(jDJSONArray, i2, (Object[]) null);
    }

    public Boolean isFiledExist(JDJSONObject jDJSONObject, String str) {
        return Boolean.valueOf(jDJSONObject.toString().contains(str));
    }

    public boolean isVirtualOrder() {
        return this.isVirtualOrder;
    }

    public void update(JDJSONObject jDJSONObject, JDJSONArray jDJSONArray, int i2) {
        update(jDJSONObject, jDJSONArray, i2, (Object[]) null);
    }

    /* loaded from: classes5.dex */
    public class ExcutableButton {
        private String icon;
        private int id;
        private String text;

        public ExcutableButton(JSONObjectProxy jSONObjectProxy) {
            Product.this = r1;
            if (jSONObjectProxy == null) {
                return;
            }
            update(JDJSON.parseObject(jSONObjectProxy.toString()));
        }

        private void update(JDJSONObject jDJSONObject) {
            if (jDJSONObject != null) {
                setText(jDJSONObject.getString("text"));
                setId(jDJSONObject.getIntValue("id"));
                setIcon(jDJSONObject.getString("icon"));
            }
        }

        public String getIcon() {
            return this.icon;
        }

        public int getId() {
            return this.id;
        }

        public String getText() {
            return this.text;
        }

        public void setIcon(String str) {
            this.icon = str;
        }

        public void setId(int i2) {
            this.id = i2;
        }

        public void setText(String str) {
            this.text = str;
        }

        public ExcutableButton(JDJSONObject jDJSONObject) {
            Product.this = r1;
            update(jDJSONObject);
        }
    }

    /* loaded from: classes5.dex */
    public class PromotionInfo {
        private String msg;
        private String text;
        private int type;

        public PromotionInfo(JSONObjectProxy jSONObjectProxy) {
            Product.this = r1;
            if (jSONObjectProxy == null) {
                return;
            }
            update(JDJSON.parseObject(jSONObjectProxy.toString()));
        }

        private void update(JDJSONObject jDJSONObject) {
            if (jDJSONObject == null) {
                setMsg("");
                setText("");
                setType(0);
                return;
            }
            setText(jDJSONObject.getString("text"));
            setType(jDJSONObject.getIntValue("type"));
            setMsg(jDJSONObject.getString("msg"));
        }

        public String getMsg() {
            return this.msg;
        }

        public String getText() {
            return this.text;
        }

        public int getType() {
            return this.type;
        }

        public void setMsg(String str) {
            this.msg = str;
        }

        public void setText(String str) {
            this.text = str;
        }

        public void setType(int i2) {
            this.type = i2;
        }

        public PromotionInfo(JDJSONObject jDJSONObject) {
            Product.this = r1;
            update(jDJSONObject);
        }
    }

    /* loaded from: classes5.dex */
    public class WarePromotionInfo {
        private ArrayList<PromotionInfo> promotionInfoList;
        private Long sku;

        public WarePromotionInfo(JSONObjectProxy jSONObjectProxy) {
            Product.this = r1;
            if (jSONObjectProxy == null) {
                return;
            }
            update(JDJSON.parseObject(jSONObjectProxy.toString()));
        }

        private void update(JDJSONObject jDJSONObject) {
            setSku(jDJSONObject.getLong("sku"));
            this.promotionInfoList = new ArrayList<>();
            JDJSONArray jSONArray = jDJSONObject.getJSONArray("promotionInfos");
            if (jSONArray == null || jSONArray.size() <= 0) {
                return;
            }
            for (int i2 = 0; i2 < jSONArray.size(); i2++) {
                this.promotionInfoList.add(new PromotionInfo(jSONArray.getJSONObject(i2)));
            }
        }

        public ArrayList<PromotionInfo> getPromotionInfoList() {
            return this.promotionInfoList;
        }

        public Long getSku() {
            return this.sku;
        }

        public void setSku(Long l2) {
            this.sku = l2;
        }

        public WarePromotionInfo(JDJSONObject jDJSONObject) {
            Product.this = r1;
            update(jDJSONObject);
        }
    }

    public static ArrayList<Product> toList(JSONArrayPoxy jSONArrayPoxy, int i2, Object[] objArr) {
        if (jSONArrayPoxy == null) {
            return null;
        }
        return toList(JDJSON.parseArray(jSONArrayPoxy.toString()), i2, objArr);
    }

    public void addJshopIcon(JDJSONObject jDJSONObject) {
        setPromFlag(jDJSONObject.getIntValue("promFlag"));
        setPromName(jDJSONObject.getString("promName"));
        setIsNew(jDJSONObject.getBooleanValue("isNew"));
        setSuperNew(jDJSONObject.getString("superNew"));
        setIsHot(jDJSONObject.getBooleanValue("isHot"));
    }

    public void addNewProductListScore(JDJSONObject jDJSONObject) {
        setAverageScore(jDJSONObject.getLong("averageScore"));
        setTotalCount(jDJSONObject.getInteger("totalCount"));
        setCommentText(jDJSONObject.getString("commentText"));
        try {
            JDJSONArray jSONArray = jDJSONObject.getJSONArray(JshopConst.JSKEY_PRODUCT_PROMOFLAG);
            if (jSONArray != null) {
                int size = jSONArray.size();
                for (int i2 = 0; i2 < size; i2++) {
                    String str = (String) jSONArray.get(i2);
                    if ("5".equals(str)) {
                        setIsPromotionZeng(Boolean.TRUE);
                    } else if ("1".equals(str)) {
                        setIsPromotionJiang(Boolean.TRUE);
                    } else if ("3".equals(str)) {
                        setIsPromotionQuan(Boolean.TRUE);
                    } else if ("4".equals(str)) {
                        setIsPromotionDou(Boolean.TRUE);
                    } else if ("100".equals(str)) {
                        setIsFlashSale(1);
                    }
                }
            }
        } catch (Exception unused) {
        }
    }

    public void addProductListScore(JDJSONObject jDJSONObject) {
        setAverageScore(jDJSONObject.getLong("averageScore"));
        setTotalCount(jDJSONObject.getInteger("totalCount"));
        try {
            JDJSONObject jSONObject = jDJSONObject.getJSONObject(JshopConst.JSKEY_PRODUCT_PROMOFLAG);
            if (jSONObject != null) {
                if (jSONObject.getString("5") != null) {
                    setIsPromotionZeng(Boolean.TRUE);
                } else {
                    setIsPromotionZeng(Boolean.FALSE);
                }
                if (jSONObject.getString("1") != null) {
                    setIsPromotionJiang(Boolean.TRUE);
                } else {
                    setIsPromotionJiang(Boolean.FALSE);
                }
                if (jSONObject.getString("3") != null) {
                    setIsPromotionQuan(Boolean.TRUE);
                } else {
                    setIsPromotionQuan(Boolean.FALSE);
                }
                if (jSONObject.getString("4") != null) {
                    setIsPromotionDou(Boolean.TRUE);
                } else {
                    setIsPromotionDou(Boolean.FALSE);
                }
                if (jSONObject.getString("100") != null) {
                    setIsFlashSale(1);
                } else {
                    setIsFlashSale(0);
                }
            }
        } catch (Exception unused) {
            Boolean bool = Boolean.FALSE;
            setIsPromotionZeng(bool);
            setIsPromotionJiang(bool);
        }
    }

    public void setOrderInfo(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            this.isOrder = Boolean.FALSE;
            return;
        }
        this.isOrder = jDJSONObject.getBoolean("isYuYue");
        this.orderBegin = jDJSONObject.getLong("yuYueStartTime");
        this.orderEnd = jDJSONObject.getLong("yuYueEndTime");
        this.buyBegin = jDJSONObject.getLong("buyStartTime");
        this.buyEnd = jDJSONObject.getLong("buyEndTime");
        this.productStatusType = jDJSONObject.getInteger("yuyueType");
        this.yuyueNum = jDJSONObject.getInteger("yuyueNum");
    }

    public void update(JSONObjectProxy jSONObjectProxy, JSONArray jSONArray, int i2, Object[] objArr) {
        if (jSONObjectProxy == null) {
            return;
        }
        JDJSONObject parseObject = JDJSON.parseObject(jSONObjectProxy.toString());
        if (jSONArray == null) {
            update(parseObject, (JDJSONArray) null, i2, objArr);
        } else {
            update(parseObject, JDJSON.parseArray(jSONArray.toString()), i2, objArr);
        }
    }

    public void setCustomAttrList(JDJSONArray jDJSONArray) {
        this.customAttrListJson = jDJSONArray;
    }

    public static ArrayList<Product> toList(JDJSONArray jDJSONArray, int i2, Object[] objArr) {
        ArrayList<Product> arrayList = null;
        if (jDJSONArray == null) {
            return null;
        }
        try {
            ArrayList<Product> arrayList2 = new ArrayList<>();
            for (int i3 = 0; i3 < jDJSONArray.size(); i3++) {
                try {
                    Product product = new Product(jDJSONArray.getJSONObject(i3), i2, objArr);
                    if (isValid) {
                        arrayList2.add(product);
                    }
                } catch (Exception e2) {
                    e = e2;
                    arrayList = arrayList2;
                    OKLog.e(TAG, e);
                    return arrayList;
                }
            }
            return arrayList2;
        } catch (Exception e3) {
            e = e3;
        }
    }

    public ArrayList<ExcutableButton> getExcutableButtonsList() {
        return this.excutableButtonsList;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void update(JDJSONObject jDJSONObject, JDJSONArray jDJSONArray, int i2, Object[] objArr) {
        JDJSONArray jSONArray;
        this.prdObject = jDJSONObject;
        setGood(jDJSONObject.getString("good"));
        setIsCarBlocked(jDJSONObject.getBoolean("needShield"));
        setIsEbook(jDJSONObject.getBoolean("eBookFlag"));
        setEbookUrl(jDJSONObject.getString("eBookUrl"));
        if (i2 == 0) {
            setId(jDJSONObject.getLong("wareId"));
            setName(jDJSONObject.getString("wname"));
            setImage(jDJSONObject.getString("imageurl"), null);
            setJdPrice(jDJSONObject.getString(JshopConst.JSKEY_PRODUCT_JDPRICE));
            setImgPrice(jDJSONObject.getString("wmaprice"));
            setMarketPrice(jDJSONObject.getString("marketPrice"));
            if (isFiledExist(jDJSONObject, "adword").booleanValue()) {
                setAdWord(jDJSONObject.getString("adword"));
            }
            addProductListScore(jDJSONObject);
            setGood(jDJSONObject.getString("good"));
        } else if (i2 == 1) {
            if (OKLog.D) {
                OKLog.d(TAG, "SEARCH_CATEGORY jsonObject = " + jDJSONObject);
            }
            this.samsPrice = jDJSONObject.getString("spPrice");
            this.plusPrice = jDJSONObject.getString("ppPrice");
            this.stock = JDJSONObjectWithDefaultUtils.getJSONIntWithDefault(jDJSONObject, "stock", -1);
            this.isUnderCarriage = JDJSONObjectWithDefaultUtils.getJSONIntWithDefault(jDJSONObject, "isUnderCarriage", 0);
            this.priceLabel = jDJSONObject.getString("priceLabel");
            setId(jDJSONObject.getLong("wareId"));
            setImage(jDJSONObject.getString("imageurl"), jDJSONObject.getString("longImgUrl"));
            setStockQuantity(JDJSONObjectWithDefaultUtils.getJSONIntWithDefault(jDJSONObject, "stockQuantity", 0));
            setName(jDJSONObject.getString("wname"));
            setAdWord(jDJSONObject.getString("adword"));
            setMarketPrice(jDJSONObject.getString("martPrice"));
            setAuthor(jDJSONObject.getString("author"));
            setBook(jDJSONObject.getBoolean("isbook"));
            if (objArr != null && objArr.length > 0) {
                setIsBookDisc((Boolean) objArr[0]);
                setIsFood((Boolean) objArr[1]);
                setIsShowNetcontent((Boolean) objArr[2]);
                setLogid((String) objArr[3]);
                setAdEventId((String) objArr[4]);
            }
            setJdPrice(jDJSONObject.getString(JshopConst.JSKEY_PRODUCT_JDPRICE));
            addJshopIcon(jDJSONObject);
            addNewProductListScore(jDJSONObject);
            setMultiSuppliers(new MultiSuppliers(jDJSONObject));
            setShopName(jDJSONObject.getString("shopName"));
            setAvailableInStore(jDJSONObject.getBoolean("availableInStore"));
            setPriority(jDJSONObject.getString(RemoteMessageConst.Notification.PRIORITY));
            setStockStateStr(jDJSONObject.getString(CartConstant.KEY_SKU_STOCKSTATE));
            JDJSONObject jSONObject = jDJSONObject.getJSONObject("wareRank");
            if (jSONObject != null) {
                WareRankInfo wareRankInfo = new WareRankInfo(jSONObject);
                this.wareRank = wareRankInfo;
                setWareRank(wareRankInfo);
            }
            setIsHaveProductLogTag(JDJSONObjectWithDefaultUtils.getJSONBooleanWithDefault(jDJSONObject, "loc", false));
            setSelf(JDJSONObjectWithDefaultUtils.getJSONBooleanWithDefault(jDJSONObject, "self", false));
            setIsInternational(jDJSONObject.getBoolean("international"));
            setIsPromotionShan(jDJSONObject.getBoolean("flashBuy"));
            setIsPromotionTuan(Boolean.valueOf(JDJSONObjectWithDefaultUtils.getJSONBooleanWithDefault(jDJSONObject, "groupBuyFlag", false)));
            setPromotionIconUrl(jDJSONObject.getString("promotionIconUrl"));
            setInterlImgUrl(jDJSONObject.getString("interlImgUrl"));
            setIsEche(jDJSONObject.getBoolean("eche"));
            setToMURL(jDJSONObject.getString("toMURL"));
            setTargetUrl(jDJSONObject.getString("targetUrl"));
            setFlowOrder(jDJSONObject.getInteger("flow_order"));
            setCustomAttr(jDJSONObject.getString("customAttr"));
            setCustomAttrList(jDJSONObject.getJSONArray("customAttrList"));
            setFullCut(jDJSONObject.getString("upToSaving"));
            setIsBooking(jDJSONObject.getBooleanValue("isBooking"));
            setDiffMobilePrice(jDJSONObject.getString("diffMobilePrice"));
            setFlags(jDJSONObject.getString("flags"));
            setP13nFlags(jDJSONObject.getString("p13nFlags"));
            setCatId(jDJSONObject.getLong("catid"));
            setPriceKeep(jDJSONObject.getBooleanValue("priceKeep"));
            JDJSONArray jSONArray2 = jDJSONObject.getJSONArray("mySearchWareTags");
            if (jSONArray2 != null) {
                try {
                    if (TextUtils.isEmpty(jSONArray2.toString())) {
                        return;
                    }
                    setMySearchProductTagInfos(JDJSON.parseArray(jSONArray2.toString(), String.class));
                } catch (Exception e2) {
                    OKLog.e(TAG, e2);
                }
            }
        } else if (i2 == 3) {
            try {
                JDJSONArray jSONArray3 = jDJSONObject.getJSONArray("image");
                this.imageList.clear();
                for (int i3 = 0; i3 < jSONArray3.size(); i3++) {
                    this.imageList.add(new Image(jSONArray3.getJSONObject(i3), 1));
                }
            } catch (Exception e3) {
                if (OKLog.E) {
                    OKLog.e(TAG, e3);
                }
            }
            if (!JDJSONObjectWithDefaultUtils.isNull(jDJSONObject, "jprice")) {
                setProductDetailJprice(new ProductDetailPrice(jDJSONObject.getJSONObject("jprice"), i2));
            }
            if (!JDJSONObjectWithDefaultUtils.isNull(jDJSONObject, "mprice")) {
                setProductDetailMprice(new ProductDetailPrice(jDJSONObject.getJSONObject("mprice"), i2));
            }
            setProductDetailBasicInfo(new ProductDetailBasicInfo(jDJSONObject.getJSONObject("basicInfo"), i2));
            setProductDetailSkuColor(ProductDetailSkuColor.toList(jDJSONObject.getJSONArray("skuColor"), i2));
            setProductDetailSkuSize(ProductDetailSkuSize.toList(jDJSONObject.getJSONArray("skuSize"), i2));
            setProductFeeInfo(JDJSONObjectWithDefaultUtils.isNull(jDJSONObject, "feeInfo") ? null : new ProductFeeInfo(jDJSONObject.getJSONObject("feeInfo")));
            setProductDetailDefaultAddress(new DefaultAddressMode(jDJSONObject.getJSONObject("defaultAddr"), i2));
            if (!JDJSONObjectWithDefaultUtils.isNull(jDJSONObject, "specialPrice")) {
                setIsPhoneVipPrice(jDJSONObject.getBoolean("specialPrice"));
            }
            setServerIconList(ServerIcon.toList(jDJSONObject.getJSONArray("iconList")));
            if (JDJSONObjectWithDefaultUtils.isNull(jDJSONObject, "yuyueInfo")) {
                return;
            }
            setOrderInfo(jDJSONObject.getJSONObject("yuyueInfo"));
        } else if (i2 == 4) {
            setId(jDJSONObject.getLong("wareId"));
            setName(jDJSONObject.getString("wname"));
            if (isFiledExist(jDJSONObject, "adword").booleanValue()) {
                setAdWord(jDJSONObject.getString("adword"));
            }
            setMarketPrice(jDJSONObject.getString("martPrice"));
            setJdPrice(jDJSONObject.getString(JshopConst.JSKEY_PRODUCT_JDPRICE));
            setImage(jDJSONObject.getString("imageurl"), null);
            setTargetUrl(jDJSONObject.getString("clickUrl"));
            setSourceValue(jDJSONObject.getString("sourceValue"));
        } else if (i2 != 5) {
            if (i2 != 6) {
                if (i2 == 7) {
                    setId(jDJSONObject.getLong("wareId"));
                    setImage(jDJSONObject.getString("imageurl"), null);
                    setName(jDJSONObject.getString("wname"));
                    setAdWord(jDJSONObject.getString("adword"));
                    setMarketPrice(jDJSONObject.getString("martPrice"));
                    setJdPrice(jDJSONObject.getString(JshopConst.JSKEY_PRODUCT_JDPRICE));
                    setOrderId(jDJSONObject.getString("orderId"));
                    setBook(jDJSONObject.getBoolean("book"));
                    this.fid = jDJSONObject.getString("fid");
                    addProductListScore(jDJSONObject);
                    setHasComment(jDJSONObject.getString("comment"));
                    setHasDiscuss(jDJSONObject.getString("discuss"));
                    setStockStateId(jDJSONObject.getInteger("stockStateId"));
                    setRemainNum(jDJSONObject.getInteger(CartConstant.KEY_SKU_REMAINNUM));
                    SetDiffPrice(jDJSONObject.getString("diffPrice"));
                    setNotifyPrice(jDJSONObject.getString("notifyPrice"));
                    setPsPrice(jDJSONObject.getString("psPrice"));
                    setWareType(jDJSONObject.getString("wareType"));
                    JDJSONArray jSONArray4 = jDJSONObject.getJSONArray("salesList");
                    if (jSONArray4 != null && jSONArray4.size() > 0) {
                        this.salesList = new ArrayList();
                        for (int i4 = 0; i4 < jSONArray4.size(); i4++) {
                            JDJSONObject jSONObject2 = jSONArray4.getJSONObject(i4);
                            if (jSONObject2 != null) {
                                String string = jSONObject2.getString("type");
                                if (!TextUtils.isEmpty(string)) {
                                    Sale sale = new Sale();
                                    sale.type = string;
                                    sale.lable = jSONObject2.getString("text");
                                    sale.value = jSONObject2.getString("value");
                                    sale.promotionId = jSONObject2.getString("promotionId");
                                    sale.productId = this.id;
                                    JDJSONObject jSONObject3 = jSONObject2.getJSONObject("giftInfo");
                                    if (jSONObject3 != null) {
                                        sale.giftId = jSONObject3.getString("id");
                                    }
                                    sale.url = jSONObject2.getString("adurl");
                                    this.salesList.add(sale);
                                }
                            }
                        }
                    }
                    setVoucherStatus(JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "voucherStatus", ""));
                    setCommentGuid(JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "commentGuid", ""));
                    this.isSoldOut = JDJSONObjectWithDefaultUtils.getJSONIntWithDefault(jDJSONObject, "isSoldOut", -1);
                    this.isMobileVip = JDJSONObjectWithDefaultUtils.getJSONIntWithDefault(jDJSONObject, "isMobileVip", -1);
                    this.diffPriceMobile = jDJSONObject.getString("diffPriceMobile");
                    this.isAddCar = JDJSONObjectWithDefaultUtils.getJSONIntWithDefault(jDJSONObject, "isAddCar", -1);
                    this.sourceValue = jDJSONObject.getString("sourceValue");
                    return;
                } else if (i2 != 106) {
                    if (i2 != 10011) {
                        switch (i2) {
                            case 9:
                                setId(jDJSONObject.getLong("Id"));
                                setName(jDJSONObject.getString("Name"));
                                setJdPrice(jDJSONObject.getString("Price"));
                                setJdDixcount(jDJSONObject.getString(CartConstant.KEY_DISCOUNT));
                                setImgPrice(jDJSONObject.getString("PriceImg"));
                                setItemCount(jDJSONObject.getIntValue(CartConstant.KEY_NUM_BIG));
                                setNum(jDJSONObject.getInteger(CartConstant.KEY_NUM_BIG));
                                if (objArr != null && objArr[0] != null) {
                                    setImage(((String) objArr[0]) + jDJSONObject.getString("ImgUrl"), null);
                                    return;
                                }
                                setImage(jDJSONObject.getString("ImgUrl"), null);
                                return;
                            case 10:
                                setId(jDJSONObject.getLong("wareId"));
                                setAdWord(jDJSONObject.getString("adword"));
                                setBook(jDJSONObject.getBoolean("book"));
                                setName(jDJSONObject.getString("wname"));
                                setNum(jDJSONObject.getInteger("num"));
                                return;
                            case 11:
                                setAdWord(jDJSONObject.getString("adword"));
                                setBook(jDJSONObject.getBoolean("book"));
                                setImage(jDJSONObject.getString("imageurl"), null);
                                setMarketPrice(jDJSONObject.getString("martPrice"));
                                setId(jDJSONObject.getLong("wareId"));
                                setName(jDJSONObject.getString("wname"));
                                return;
                            case 12:
                                setName(jDJSONObject.getString("Name"));
                                return;
                            case 13:
                                setId(jDJSONObject.getLong("SkuId"));
                                if (objArr[0] != null) {
                                    setImage(((String) objArr[0]) + jDJSONObject.getString("SkuPicUrl"), null);
                                } else {
                                    setImage(jDJSONObject.getString("SkuPicUrl"), null);
                                }
                                setName(jDJSONObject.getString(CartConstant.KEY_YB_SKU_NAME));
                                return;
                            case 14:
                                setId(jDJSONObject.getLong("wareId"));
                                setName(jDJSONObject.getString("wname"));
                                setImage(jDJSONObject.getString("imageurl"), null);
                                setJdPrice(jDJSONObject.getString(JshopConst.JSKEY_PRODUCT_JDPRICE));
                                setImgPrice(jDJSONObject.getString("wmaprice"));
                                setMarketPrice(jDJSONObject.getString("marketPrice"));
                                setEndTime(Long.valueOf(jDJSONObject.getLongValue("endTime")));
                                if (isFiledExist(jDJSONObject, "adword").booleanValue()) {
                                    setAdWord(jDJSONObject.getString("adword"));
                                    return;
                                }
                                return;
                            case 15:
                                setSkuColorList(SkuColor.toList(jDJSONObject.getJSONArray("skuColor"), 0));
                                setSkuSizeList(SkuSize.toList(jDJSONObject.getJSONArray("skuSize"), 0));
                                return;
                            case 16:
                                setAdWord(jDJSONObject.getString("adword"));
                                setBook(jDJSONObject.getBoolean("book"));
                                setImage(jDJSONObject.getString("imageurl"), null);
                                setId(jDJSONObject.getLong("wareId"));
                                setName(jDJSONObject.getString("wname"));
                                setMarketPrice(jDJSONObject.getString("martPrice"));
                                setJdPrice(jDJSONObject.getString(JshopConst.JSKEY_PRODUCT_JDPRICE));
                                return;
                            case 17:
                                setAdWord(jDJSONObject.getString("adword"));
                                setBook(jDJSONObject.getBoolean("book"));
                                setImage(jDJSONObject.getString("imageurl"), null);
                                setId(jDJSONObject.getLong("wareId"));
                                setName(jDJSONObject.getString("wname"));
                                setMiaoShaPrice(jDJSONObject.getString("miaoShaPrice"));
                                setIsNewGoods(jDJSONObject.getIntValue("isNewGoods"));
                                setJdPrice(jDJSONObject.getString(JshopConst.JSKEY_PRODUCT_JDPRICE));
                                setStartTime(Long.valueOf(JDJSONObjectWithDefaultUtils.getJSONLongWithDefault(jDJSONObject, "startRemainTime", 0L)));
                                setEndTime(Long.valueOf(JDJSONObjectWithDefaultUtils.getJSONLongWithDefault(jDJSONObject, "endRemainTime", 0L)));
                                setRate(jDJSONObject.getString(NewFillOrderConstant.RATE));
                                setsMsgId(jDJSONObject.getString("activeId"));
                                setMiaoSha(jDJSONObject.getBoolean("miaoSha"));
                                setJdDixcount(jDJSONObject.getString("discount"));
                                setDiscountNew(jDJSONObject.getString("discountNew"));
                                setMessage(jDJSONObject.getString("message"));
                                setCId(jDJSONObject.getString("cid"));
                                setCName(jDJSONObject.getString("cName"));
                                setSpuId(jDJSONObject.getString("spuId"));
                                setSkuId(jDJSONObject.getString("skuId"));
                                setMoreFunId(jDJSONObject.getString("moreFunId"));
                                setExpid(jDJSONObject.getString(PDConstant.EXTRA_EXPID));
                                setIndex(jDJSONObject.getString("index"));
                                setRid(jDJSONObject.getString(PDConstant.EXTRA_RID));
                                setSourceValue(jDJSONObject.getString("sourceValue"));
                                setmShaShopId(jDJSONObject.getString("shopId"));
                                setMpageAddress(jDJSONObject.getString("mpageAddress"));
                                setOperateWord(jDJSONObject.getString("operateWord"));
                                setSpecialKill(jDJSONObject.getInteger("specialKill"));
                                setSoldRate(jDJSONObject.getInteger("soldRate"));
                                setNum(jDJSONObject.getInteger("seckillNum"));
                                setTagType(jDJSONObject.getIntValue("tagType"));
                                setTagText(jDJSONObject.getString("tagText"));
                                this.yuyueNum = jDJSONObject.getInteger("clockNum");
                                setProvinceStockContent(jDJSONObject.getString("tips"));
                                setAreaTips(jDJSONObject.getString("areaTips"));
                                setStartTimeShow(JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "startTimeShow", ""));
                                this.startTimeMills = JDJSONObjectWithDefaultUtils.getJSONLongWithDefault(jDJSONObject, "startTimeMills", 0L);
                                setStartTimeContent(JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "startTimeContent", ""));
                                this.isHistoryProduct = JDJSONObjectWithDefaultUtils.getJSONBooleanWithDefault(jDJSONObject, "isHistoryProduct", false);
                                this.msItemType = JDJSONObjectWithDefaultUtils.getJSONIntWithDefault(jDJSONObject, "type", 1);
                                this.msPromotionTag = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "promotionTag", "");
                                if (jDJSONObject.getJSONObject("jump") != null) {
                                    this.jump = (JumpEntity) JDJSON.parseObject(jDJSONObject.getJSONObject("jump").toString(), JumpEntity.class);
                                }
                                this.specificationLabel = jDJSONObject.getString("specificationLabel");
                                return;
                            case 18:
                                setPromotionTitle(jDJSONObject.getString("promotionInfoTitle"));
                                setPromotionInfo(jDJSONObject.getString(CartConstant.KEY_CART_PROMOTIONINFO));
                                return;
                            case 19:
                                String string2 = jDJSONObject.getString("deliver");
                                setDeliver((TextUtils.isEmpty(string2) || TextUtils.equals(string2, DYConstants.DY_NULL_STR)) ? null : string2);
                                setPostByJd(jDJSONObject.getBoolean("isPostByJd"));
                                return;
                            case 20:
                                try {
                                    setId(Long.valueOf(Long.parseLong(jDJSONObject.getString("wareId"))));
                                } catch (Exception e4) {
                                    OKLog.e(TAG, e4);
                                }
                                Long l2 = this.id;
                                if (l2 != null && !TextUtils.isEmpty(l2.toString())) {
                                    setIsValid(true);
                                } else {
                                    setIsValid(false);
                                }
                                setName(jDJSONObject.getString("wname"));
                                setImage(jDJSONObject.getString("imageurl"), null);
                                setJdPrice(jDJSONObject.getString(JshopConst.JSKEY_PRODUCT_JDPRICE));
                                return;
                            case 21:
                                setId(jDJSONObject.getLong("wareId"));
                                setName(jDJSONObject.getString("wname"));
                                setImage(jDJSONObject.getString("imageurl"), null);
                                setMarketPrice(jDJSONObject.getString("martPrice"));
                                setStartTime(jDJSONObject.getLong("startRemainTime"));
                                setEndTime(jDJSONObject.getLong("endRemainTime"));
                                setBook(jDJSONObject.getBoolean("book"));
                                setJdPrice(jDJSONObject.getString(JshopConst.JSKEY_PRODUCT_JDPRICE));
                                if (isFiledExist(jDJSONObject, "adword").booleanValue()) {
                                    setAdWord(jDJSONObject.getString("adword"));
                                    return;
                                }
                                return;
                            default:
                                switch (i2) {
                                    case 23:
                                        setAdWord(jDJSONObject.getString("adword"));
                                        setBook(jDJSONObject.getBoolean("book"));
                                        setImage(jDJSONObject.getString("imageurl"), null);
                                        setId(jDJSONObject.getLong("wareId"));
                                        setName(jDJSONObject.getString("wname"));
                                        setJdPrice(jDJSONObject.getString(JshopConst.JSKEY_PRODUCT_JDPRICE));
                                        setStartTime(jDJSONObject.getLong("startRemainTime"));
                                        setEndTime(jDJSONObject.getLong("endRemainTime"));
                                        setMarketPrice(jDJSONObject.getString("martPrice"));
                                        setCanFreeRead(jDJSONObject.getBoolean("canFreeRead"));
                                        return;
                                    case 24:
                                        setId(jDJSONObject.getLong("SkuId"));
                                        setName(jDJSONObject.getString(CartConstant.KEY_YB_SKU_NAME));
                                        setImage(jDJSONObject.getString("SkuPicUrl"), null);
                                        return;
                                    case 25:
                                        setName(jDJSONObject.getString("MainSkuName"));
                                        setId(jDJSONObject.getLong("MainSkuId"));
                                        setImage(jDJSONObject.getString("MainSkuPicUrl"), null);
                                        return;
                                    case 26:
                                        setId(jDJSONObject.getLong("wareId"));
                                        setName(jDJSONObject.getString("wname"));
                                        setImage(jDJSONObject.getString("imageurl"), null);
                                        setJdPrice(jDJSONObject.getString(JshopConst.JSKEY_PRODUCT_JDPRICE));
                                        setImgPrice(jDJSONObject.getString("wmaprice"));
                                        setMarketPrice(jDJSONObject.getString("marketPrice"));
                                        if (isFiledExist(jDJSONObject, "adword").booleanValue()) {
                                            setAdWord(jDJSONObject.getString("adword"));
                                        }
                                        addProductListScore(jDJSONObject);
                                        setGood(jDJSONObject.getString("good"));
                                        return;
                                    default:
                                        switch (i2) {
                                            case 28:
                                                setProductDetailCruxBasicInfo(new ProductDetailCruxBasicInfo(jDJSONObject, i2));
                                                return;
                                            case 29:
                                                setName(jDJSONObject.getString("wname"));
                                                setId(jDJSONObject.getLong("skuId"));
                                                setJdPrice(jDJSONObject.getString(JshopConst.JSKEY_PRODUCT_JDPRICE));
                                                setShopId(jDJSONObject.getLongValue("shopId"));
                                                setShopUrl(jDJSONObject.getString("shopUrl"));
                                                setImage(jDJSONObject.getString("imageUrl"), null);
                                                setFatherId(jDJSONObject.getLongValue("fatherId"));
                                                setVenderId(jDJSONObject.getLongValue("venderId"));
                                                setVenderType(jDJSONObject.getInteger(JshopConst.JSKEY_JDSHOP));
                                                setStockState(jDJSONObject.getInteger(CartConstant.KEY_SKU_STOCKSTATE));
                                                setVenderName(jDJSONObject.getString("venderName"));
                                                return;
                                            case 30:
                                                setId(jDJSONObject.getLong("wareId"));
                                                setName(jDJSONObject.getString("wname"));
                                                setJdPrice(jDJSONObject.getString(JshopConst.JSKEY_PRODUCT_JDPRICE));
                                                setImage(jDJSONObject.getString("imageurl"), null);
                                                setSourceValue(jDJSONObject.getString("sourceValue"));
                                                setLookSimilar(jDJSONObject.getString("lookSimilar"));
                                                setTargetUrl(jDJSONObject.getString("clickUrl"));
                                                return;
                                            case 31:
                                                setmPaymentType(JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "pType", ""));
                                                this.tag = jDJSONObject.getString("pText");
                                                setItemCount(jDJSONObject.getIntValue(IMediaPlayer.OnNativeInvokeListener.ARG_OFFSET));
                                                setId(jDJSONObject.getLong("sku"));
                                                setName(jDJSONObject.getString("name"));
                                                setJdPrice(jDJSONObject.getString("p"));
                                                setImage(jDJSONObject.getString("img"), null);
                                                setAdWord(jDJSONObject.getString("slogan"));
                                                setMessageType(jDJSONObject.getString("tagType"));
                                                setMessage(JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "newTagText", ""));
                                                setSourceValue(JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "sourceValue", ""));
                                                setSoldRate(jDJSONObject.getInteger("onSell"));
                                                return;
                                            case 32:
                                                setId(jDJSONObject.getLong("wareId"));
                                                setName(jDJSONObject.getString("wname"));
                                                setJdPrice(jDJSONObject.getString(JshopConst.JSKEY_PRODUCT_JDPRICE));
                                                setImage(jDJSONObject.getString("imageurl"), null);
                                                setSourceValue(jDJSONObject.getString("sourceValue"));
                                                return;
                                            case 33:
                                                setId(Long.valueOf(JDJSONObjectWithDefaultUtils.getJSONLongWithDefault(jDJSONObject, "wareId", 0L)));
                                                setName(JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "wname", ""));
                                                setImage(JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "imageurl", ""), null);
                                                setJdPrice(jDJSONObject.getString(JshopConst.JSKEY_PRODUCT_JDPRICE));
                                                setMiaoShaPrice(JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "miaoShaPrice", ""));
                                                setNum(Integer.valueOf(JDJSONObjectWithDefaultUtils.getJSONIntWithDefault(jDJSONObject, "seckillNum", 0)));
                                                setSourceValue(JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "sourceValue", ""));
                                                setTagType(jDJSONObject.getIntValue("tagType"));
                                                setTagText(jDJSONObject.getString("tagText"));
                                                setSoldRate(jDJSONObject.getInteger("soldRate"));
                                                this.yuyueNum = jDJSONObject.getInteger("clockNum");
                                                setMiaoSha(jDJSONObject.getBoolean("miaoSha"));
                                                setSpuId(jDJSONObject.getString("spuId"));
                                                setProvinceStockContent(jDJSONObject.getString("tips"));
                                                setAreaTips(jDJSONObject.getString("areaTips"));
                                                setStartTimeShow(JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "startTimeShow", ""));
                                                this.startTimeMills = JDJSONObjectWithDefaultUtils.getJSONLongWithDefault(jDJSONObject, "startTimeMills", 0L);
                                                this.biInfo = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "biInfo", "");
                                                this.starGood = JDJSONObjectWithDefaultUtils.getJSONIntWithDefault(jDJSONObject, "starGood", 0);
                                                this.starWord = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "starWord", "");
                                                return;
                                            case 34:
                                                setId(Long.valueOf(JDJSONObjectWithDefaultUtils.getJSONLongWithDefault(jDJSONObject, "skuId", 0L)));
                                                setName(JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "skuName", ""));
                                                setImage(JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "skuImg", ""), null);
                                                setJdPrice(jDJSONObject.getString(JshopConst.JSKEY_PRODUCT_JDPRICE));
                                                setSourceValue(JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "sourceValue", ""));
                                                setTagType(jDJSONObject.getIntValue("tagType"));
                                                setTagText(jDJSONObject.getString("tagText"));
                                                setMiaoSha(jDJSONObject.getBoolean("miaoSha"));
                                                return;
                                            case 35:
                                                setId(jDJSONObject.getLong("sku"));
                                                setBuyCount(jDJSONObject.getIntValue("buyCount"));
                                                setDesc(jDJSONObject.getString("desc"));
                                                setHistoryPrice(jDJSONObject.getString("historyPrice"));
                                                setImage(jDJSONObject.getString("imgUrl"), null);
                                                setJdPrice(jDJSONObject.getString(JshopConst.JSKEY_PRODUCT_JDPRICE));
                                                setHasPriceDiff(jDJSONObject.getIntValue("hasPriceDiff"));
                                                setPriceDiff(jDJSONObject.getString("priceDiff"));
                                                setPriceDiffText(jDJSONObject.getString("priceDiffText"));
                                                if (jDJSONObject.getJSONObject("warePromotionInfo") != null) {
                                                    setWarePromotionInfo(new WarePromotionInfo(jDJSONObject.getJSONObject("warePromotionInfo")));
                                                }
                                                setPurchaseReminder(jDJSONObject.getIntValue("purchaseReminder"));
                                                setSourceValue(jDJSONObject.getString("sourceValue"));
                                                setPurchaseReminderIcon(jDJSONObject.getString("purchaseReminderIcon"));
                                                setSourceID(jDJSONObject.getIntValue("sourceID"));
                                                setSkuType(jDJSONObject.getString(CartConstant.KEY_SKU_TYPE));
                                                setSkuTypeId(jDJSONObject.getIntValue("skuTypeId"));
                                                setExcutableButtonsList(getExcutableButtonsList(jDJSONObject.getJSONArray("buttons")));
                                                break;
                                            case 36:
                                                break;
                                            default:
                                                return;
                                        }
                                        if (OKLog.D) {
                                            OKLog.d(TAG, "BROWSER_HISTORY jsonObject = " + jDJSONObject);
                                        }
                                        setBrowserTime(jDJSONObject.getLongValue("ts"));
                                        setId(jDJSONObject.getLong("sku"));
                                        setName(jDJSONObject.getString("name"));
                                        setJdPrice(jDJSONObject.getString("jprice"));
                                        setImage(jDJSONObject.getString("img"), null);
                                        setTargetUrl(jDJSONObject.getString("clk"));
                                        setImpr(jDJSONObject.getString("impr"));
                                        setBn(jDJSONObject.getString("bn"));
                                        setAvailable(jDJSONObject.getBooleanValue(Constant.KEY_PROMOTION_AVAILABLE));
                                        setOnshelf(jDJSONObject.getBooleanValue("onshelf"));
                                        setShowCartIcon(jDJSONObject.getBooleanValue("showCartIcon"));
                                        this.priceLabel = jDJSONObject.getString("priceLabel");
                                        return;
                                }
                        }
                    }
                    setId(jDJSONObject.getLong("sku"));
                    setName(jDJSONObject.getString("wname"));
                    setJdPrice(jDJSONObject.getString(JshopConst.JSKEY_PRODUCT_JDPRICE));
                    setImage(jDJSONObject.getString("imgUrl"), null);
                    setSourceValue(jDJSONObject.getString("sourceValue"));
                    setGood(jDJSONObject.getString("good"));
                    setExposureSourceValue(jDJSONObject.getString("exposureSourceValue"));
                    setExposureJsonSourceValue(jDJSONObject.getString("exposureJsonSourceValue"));
                    addNewProductListScore(jDJSONObject);
                    return;
                }
            }
            setOrderId(jDJSONObject.getString("orderId"));
            setOrderCheckCode(jDJSONObject.getString("checkCode"));
            setOrderStatus(jDJSONObject.getString("orderStatus"));
            setOrderPrice(jDJSONObject.getString("price"));
            setOrderSubtime(jDJSONObject.getString("dataSubmit"));
            if (i2 + c.f20058m == 0) {
                setNum(jDJSONObject.getInteger("num"));
            } else {
                setNum(jDJSONObject.getInteger("buyCount"));
            }
            setId(jDJSONObject.getLong("wareId"));
            setName(jDJSONObject.getString("wname"));
            setImage(jDJSONObject.getString("imageurl"), null);
            setmPaymentType(jDJSONObject.getString(IRequestPayment.V_PAY_TYPE));
            setSubOrderFlag(jDJSONObject.getBoolean("subOrder"));
            setShowLabel(jDJSONObject.getString("showLabel"));
            setViewInvitationUrl(jDJSONObject.getString("viewInvitationUrl"));
            setShowLabelId(jDJSONObject.getInteger("showLabelId"));
            setVenderId(Long.valueOf(JDJSONObjectWithDefaultUtils.getJSONLongWithDefault(jDJSONObject, "venderId", -1L)).longValue());
            setWareCount(jDJSONObject.getIntValue("wareCount"));
            setJdPrice(jDJSONObject.getString(JshopConst.JSKEY_PRODUCT_JDPRICE));
            setOrderStatusShow(jDJSONObject.getString("orderStatusShow"));
            setCanGoToShop(jDJSONObject.getBoolean("canGoToShop"));
            setShopName(jDJSONObject.getString("shopName"));
            setMessage(jDJSONObject.getString("message"));
            setTraceMessageTime(jDJSONObject.getString("messageTime"));
            long jSONLongWithDefault = JDJSONObjectWithDefaultUtils.getJSONLongWithDefault(jDJSONObject, "buyAgain", -1L);
            setInternationalOrder(JDJSONObjectWithDefaultUtils.getJSONIntWithDefault(jDJSONObject, "internationalOrder", -1));
            setPayTypeCode(JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "payTypeCode", "0"));
            setBuyAgain(jSONLongWithDefault);
            JDJSONObject jSONObject4 = jDJSONObject.getJSONObject("orderMsg");
            if (jSONObject4 != null && (jSONArray = jSONObject4.getJSONArray("wareInfoList")) != null && jSONArray.size() > 0) {
                setProductList(toList(jSONArray, 6));
            }
            JDJSONObject jSONObject5 = jDJSONObject.getJSONObject("staffInfo");
            if (jSONObject5 != null) {
                DiliverManInfo diliverManInfo = new DiliverManInfo(jSONObject5);
                this.staffInfo = diliverManInfo;
                setStaffInfo(diliverManInfo);
            }
            setHasComment(jDJSONObject.getString("comment"));
            setHasDiscuss(jDJSONObject.getString("discuss"));
            this.yushouOrder = YushouOrder.fromJson(jDJSONObject);
            setIsShowDelButton(Boolean.valueOf(JDJSONObjectWithDefaultUtils.getJSONBooleanWithDefault(jDJSONObject, "showDelButton", false)));
            setOrderType(JDJSONObjectWithDefaultUtils.getJSONIntWithDefault(jDJSONObject, "orderType", 0));
            setSendPay(jDJSONObject.getString("sendPay"));
            setInternationalType(JDJSONObjectWithDefaultUtils.getJSONIntWithDefault(jDJSONObject, "internationalType", 0));
            isVirtualOrder(JDJSONObjectWithDefaultUtils.getJSONBooleanWithDefault(jDJSONObject, "isVirtualOrder", false));
            if (this.isVirtualOrder) {
                setVirtualOrderInfo(VirtualOrderInfo.parse(JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "virtualOrderInfo", "")));
            }
            this.materialRedirectProtocol = (MaterialRedirectProtocol) JDJSON.parseObject(String.valueOf(jDJSONObject.getJSONObject("materialRedirectProtocol")), new TypeToken<MaterialRedirectProtocol>() { // from class: com.jingdong.common.entity.Product.1
                {
                    Product.this = this;
                }
            }.getType(), new Feature[0]);
            this.orderOptButtons = (List) JDJSON.parseObject(String.valueOf(jDJSONObject.getJSONArray("buttons")), new TypeToken<List<OrderOptButton>>() { // from class: com.jingdong.common.entity.Product.2
                {
                    Product.this = this;
                }
            }.getType(), new Feature[0]);
            this.orderStatusId = JDJSONObjectWithDefaultUtils.getJSONIntWithDefault(jDJSONObject, "orderStatusId", -1);
            this.wareColor = jDJSONObject.getString("color");
            this.wareSize = jDJSONObject.getString(ApkDownloadTable.FIELD_SIZE);
            this.virtualOrderPayPlan = jDJSONObject.getString("virtualOrderPayPlan");
            this.newBuyAgain = JDJSONObjectWithDefaultUtils.getJSONIntWithDefault(jDJSONObject, "newBuyAgain", -1);
            this.newBuyAgainMUrl = jDJSONObject.getString("newBuyAgainUrl");
            this.newBuyAgainSku = jDJSONObject.getString("newBuyAgainSku");
            this.shouldPayTip = jDJSONObject.getString("shouldPayTip");
            this.extTagItems = JDJSON.parseArray(String.valueOf(jDJSONObject.getJSONArray("extTagList")), ExtTagItem.class);
        } else {
            setUsername(jDJSONObject.getString("unickName"));
            setImage(jDJSONObject.getString("imgUrl"), null);
            setUserClass(jDJSONObject.getString("uclass"));
        }
    }

    public Product(JSONObjectProxy jSONObjectProxy, int i2) {
        this(jSONObjectProxy, (JSONArray) null, i2);
    }

    public Product(JSONObjectProxy jSONObjectProxy, int i2, Object[] objArr) {
        this(jSONObjectProxy, (JSONArray) null, i2, objArr);
    }

    public Product(JDJSONObject jDJSONObject, int i2, Object[] objArr) {
        this(jDJSONObject, (JDJSONArray) null, i2, objArr);
    }

    public Product(JSONObjectProxy jSONObjectProxy, JSONArray jSONArray, int i2) {
        this(jSONObjectProxy, jSONArray, i2, (Object[]) null);
    }

    public Product(JDJSONObject jDJSONObject, JDJSONArray jDJSONArray, int i2) {
        this(jDJSONObject, jDJSONArray, i2, (Object[]) null);
    }

    private Product(JSONObjectProxy jSONObjectProxy, JSONArray jSONArray, int i2, Object[] objArr) {
        this.supportSizeType = 0;
        this.isHistoryProduct = false;
        this.starGood = 0;
        this.msItemType = 0;
        this.canBeDelete = false;
        this.imageList = new LinkedList();
        this.showMarketPrice = null;
        this.provinceStockMode = 0;
        this.canEasyBuy = false;
        this.hasPacks = false;
        this.isFavoCheckBoxChecked = false;
        Boolean bool = Boolean.FALSE;
        this.priceReport = bool;
        this.canFreeRead = bool;
        this.postByjd = bool;
        this.toMURL = "";
        this.isCheckNext = false;
        this.isEbook = bool;
        this.ebookUrl = "";
        this.isCarBlocked = bool;
        this.isFood = bool;
        this.isShowNetContent = bool;
        this.customAttr = "";
        this.diffMobilePrice = "";
        this.available = true;
        this.onshelf = true;
        this.showCartIcon = true;
        this.promFlag = 0;
        this.isNew = false;
        this.isHot = false;
        this.isShowDelButton = Boolean.TRUE;
        this.orderType = 0;
        this.internationalType = 0;
        this.orderStatusId = -1;
        this.isHaveProductLogTag = false;
        this.self = false;
        this.sImgUrlList = new LinkedList();
        update(jSONObjectProxy, jSONArray, i2, objArr);
    }

    private Product(JDJSONObject jDJSONObject, JDJSONArray jDJSONArray, int i2, Object[] objArr) {
        this.supportSizeType = 0;
        this.isHistoryProduct = false;
        this.starGood = 0;
        this.msItemType = 0;
        this.canBeDelete = false;
        this.imageList = new LinkedList();
        this.showMarketPrice = null;
        this.provinceStockMode = 0;
        this.canEasyBuy = false;
        this.hasPacks = false;
        this.isFavoCheckBoxChecked = false;
        Boolean bool = Boolean.FALSE;
        this.priceReport = bool;
        this.canFreeRead = bool;
        this.postByjd = bool;
        this.toMURL = "";
        this.isCheckNext = false;
        this.isEbook = bool;
        this.ebookUrl = "";
        this.isCarBlocked = bool;
        this.isFood = bool;
        this.isShowNetContent = bool;
        this.customAttr = "";
        this.diffMobilePrice = "";
        this.available = true;
        this.onshelf = true;
        this.showCartIcon = true;
        this.promFlag = 0;
        this.isNew = false;
        this.isHot = false;
        this.isShowDelButton = Boolean.TRUE;
        this.orderType = 0;
        this.internationalType = 0;
        this.orderStatusId = -1;
        this.isHaveProductLogTag = false;
        this.self = false;
        this.sImgUrlList = new LinkedList();
        update(jDJSONObject, jDJSONArray, i2, objArr);
    }
}
