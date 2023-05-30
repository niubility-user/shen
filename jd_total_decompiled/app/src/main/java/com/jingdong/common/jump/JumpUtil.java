package com.jingdong.common.jump;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.entity.ShareEntity;
import com.jingdong.common.entity.ShareInfo;
import com.jingdong.common.login.LoginConstans;
import com.jingdong.common.messagecenter.NotificationMessageSummary;
import com.jingdong.common.unification.i18n.UnI18NUtils;
import com.jingdong.common.utils.JsonParser;
import com.jingdong.common.web.managers.WebWhiteScreenHolder;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class JumpUtil {
    public static final String HOST_VIDEO_PRODUCT_RECOMMEND = "productRecommendVideoList";
    public static final String JDPAY_OPEN_SCHEME = "jdpayopen";
    public static final String JDPAY_SCHEME = "jdpay";
    public static final String OPENAPP_SCHEME1 = "openapp.jdmobile";
    public static final String OPENAPP_SCHEME2 = "openjd";
    public static final String OPENAPP_SCHEME_THAI = "openjdthai";
    private static final String TAG = "JumpUtil";
    public static final String VALUES_DES_RANK_RECOMMEND_INNER = "rankrecommend";
    public static final String VALUES_DES_RANK_TOPIC = "rankTopic";
    public static final String VALUE_DES_2G_ALBUM_DETAIL = "worthbuy_newAlbum_detail";
    public static final String VALUE_DES_ABOUT = "aboutApp";
    public static final String VALUE_DES_AGGREGATE_PAGE = "aggregatePage";
    public static final String VALUE_DES_ALBUM_DETAIL = "album_detail";
    public static final String VALUE_DES_ALBUM_LIKE = "album_like";
    public static final String VALUE_DES_ALBUM_LIST = "album_list";
    public static final String VALUE_DES_ALBUM_SELECTED_LIST = "album_selected_inner_list";
    public static final String VALUE_DES_APPCENTER = "appcenter";
    public static final String VALUE_DES_APPCENTER1 = "native_appcenter";
    public static final String VALUE_DES_APPHOME = "HomePage";
    public static final String VALUE_DES_APPHOMETOPTAB = "HomePageTopTab";
    public static final String VALUE_DES_AR = "arCapture";
    public static final String VALUE_DES_ARBOOK = "arbook";
    public static final String VALUE_DES_ARFACE = "arface";
    public static final String VALUE_DES_ARMART = "armart";
    public static final String VALUE_DES_ARTICLE_DETAIL = "article_detail";
    public static final String VALUE_DES_ARVR_PUBLIC_INTERFACE = "arvr_public_interface";
    public static final String VALUE_DES_AR_BUY = "arbuy";
    public static final String VALUE_DES_AR_FLOOR = "arfloor";
    public static final String VALUE_DES_AR_MAKEUP = "armakeup";
    public static final String VALUE_DES_ASSISTANT_CHAT_BACKGROUND_SETTING = "chat_background_setting";
    public static final String VALUE_DES_BABEL = "babel";
    public static final String VALUE_DES_BARCODE_PURCHASE = "barcodePurchase";
    public static final String VALUE_DES_BRAND_SECKILL = "brandSeckill";
    public static final String VALUE_DES_BUSINESS_MAP = "businessMap";
    public static final String VALUE_DES_CANCEL_PROGRESS = "cancelProgress";
    public static final String VALUE_DES_CASH_REWARD = "cashreward";
    public static final String VALUE_DES_CATEGORY = "category";
    public static final String VALUE_DES_CATEGORY_LIST_PAGE = "categoryListPage";
    public static final String VALUE_DES_CHANNEL_PRODUCTLIST = "channelProductList";
    public static final String VALUE_DES_CHANNEL_SEARCH = "channelSearch";
    public static final String VALUE_DES_CHONGZHI = "recharge";
    public static final String VALUE_DES_CHONGZHI1 = "chongzhi";
    public static final String VALUE_DES_COMMENT = "comment";
    public static final String VALUE_DES_COMMENT_DETAIL = "commentDetail";
    public static final String VALUE_DES_COMMENT_REPORT_DETAIL = "wareCommentReportDetail";
    public static final String VALUE_DES_COMMUNE = "commune";
    public static final String VALUE_DES_CONTENT_COMMENTS = "content_comments";
    public static final String VALUE_DES_COUPON = "mycoupon";
    public static final String VALUE_DES_CPS_UNION = "union";
    public static final String VALUE_DES_DARK_MODE_PREFERENCE = "dark_mode_preference";
    public static final String VALUE_DES_DASHMAIN = "jdnow";
    public static final String VALUE_DES_DD_NEW_CHAT = "jd_dongdong_chat";
    public static final String VALUE_DES_DEFAULT_BROWSER = "OpenByDefaultBrowser";
    public static final String VALUE_DES_DOLPHIN = "dolphin";
    public static final String VALUE_DES_ENJOYBUY = "enjoybuy";
    public static final String VALUE_DES_ENJOYBUY_BRAND = "enjoybuybrand";
    public static final String VALUE_DES_EVALUATE_CENTER = "commentCenter";
    public static final String VALUE_DES_EVALUATE_CENTER_COMMENT_AND_SHARE = "commentAndShare";
    public static final String VALUE_DES_EVALUATE_EDIT = "shareOrder";
    public static final String VALUE_DES_EVALUATION_OFFICER_PERSONAL_INFO = "evaluationOfficerPersonalInfo";
    public static final String VALUE_DES_EVALUATION_OFFICER_REGISTER = "evaluationOfficerRegister";
    public static final String VALUE_DES_EVALUATION_UGC_CONTENT_REGISTER = "shareOrderUgcContent";
    public static final String VALUE_DES_FAMILY_HELPER = "jdfamilyhelper";
    public static final String VALUE_DES_FAXIAN_ALL_COMMENT = "finder_all_comment";
    public static final String VALUE_DES_FAXIAN_ARTICLE = "faxian_article";
    public static final String VALUE_DES_FAXIAN_AUTHOR = "faxian_author";
    public static final String VALUE_DES_FAXIAN_AUTHOR_NEW = "faxian_author_new";
    public static final String VALUE_DES_FAXIAN_COMMENT_DETAIL = "finder_comment_detail";
    public static final String VALUE_DES_FAXIAN_COMMENT_INPUT = "faxian_comment_input";
    public static final String VALUE_DES_FAXIAN_MY_FOLLOW = "myfollow";
    public static final String VALUE_DES_FEEDBACK = "feedback";
    public static final String VALUE_DES_FEETMEASUEREMENT = "feetmeasuerement";
    public static final String VALUE_DES_FETCH_COUPON = "fetchcoupon";
    public static final String VALUE_DES_FILLORDER = "orderInfoView";
    public static final String VALUE_DES_FINDATTENTION_SHOPDISC = "findattention_shopdisc";
    public static final String VALUE_DES_FIND_LIVE_LIST = "FindLivePlayList";
    public static final String VALUE_DES_FIND_LIVE_PRE = "FindLivePreDetail";
    public static final String VALUE_DES_FIRE_EYE = "fireEye";
    public static final String VALUE_DES_FIT_SHOES = "3d_try_shoes";
    public static final String VALUE_DES_FLASH_RANK_LIST = "flashRanklist";
    public static final String VALUE_DES_FOLLOW_CHANNEL = "followChannel";
    public static final String VALUE_DES_FRIEND_LIST = "showfriendlist";
    public static final String VALUE_DES_FRIEND_MANAGER = "showfriendmanager";
    public static final String VALUE_DES_FSMUTISHOP = "FSMutiShop";
    public static final String VALUE_DES_GAME_CHONGZHI = "gamechongzhi";
    public static final String VALUE_DES_GENERIC_CHANNEL = "genericChannel";
    public static final String VALUE_DES_GENE_RECOM = "geneRecom";
    public static final String VALUE_DES_GOODS_FAXIAN4EVENT = "faxian";
    public static final String VALUE_DES_GOODS_RECOMMEND = "goodsRecommend";
    public static final String VALUE_DES_GOODS_RECOMMEND1 = "recommend";
    public static final String VALUE_DES_GO_CART = "goCart";
    public static final String VALUE_DES_GROUP_CHAT_GROUP = "dongdong_group_chat";
    public static final String VALUE_DES_GROUP_MIAOSHA_NEWPRODUCT = "group_seckill_newproduct";
    public static final String VALUE_DES_GROUP_SHOPPING = "groupShopping";
    public static final String VALUE_DES_GUANGGUANG1 = "native_guangguang";
    public static final String VALUE_DES_GUANGGUANG2 = "guangguangnew";
    public static final String VALUE_DES_GUANZHU = "guanzhu";
    public static final String VALUE_DES_H5_GAME = "h5Game";
    public static final String VALUE_DES_HISTORY_LIST = "historyList";
    public static final String VALUE_DES_HOME_OVERSEA = "home_oversea";
    public static final String VALUE_DES_HOURLY_GO = "hourlygo";
    public static final String VALUE_DES_IDCARDLIST = "idcardlist";
    public static final String VALUE_DES_IM = "jd_native_im";
    public static final String VALUE_DES_IM_O1 = "im";
    public static final String VALUE_DES_INNER_BRAND_CATEGORY = "seckillbrand";
    public static final String VALUE_DES_INNER_NEW_BRAND = "seckillnewbrand";
    public static final String VALUE_DES_INNOVATION_LAB = "innovationLab";
    public static final String VALUE_DES_INTELLIGENT_ASSISTANT = "IntelligentAssistant";
    public static final String VALUE_DES_INTELLIGENT_ASSISTANT_CHAT_TIMBRE = "intelligent_assistant_chat_timbre";
    public static final String VALUE_DES_INTELLIGENT_ASSISTANT_MALL = "intelligent_assistant_mall";
    public static final String VALUE_DES_INTELLIGENT_ASSISTANT_NICKNAME_MODIFICATION = "chat_modify_username";
    public static final String VALUE_DES_INTELLIGENT_ASSISTANT_SETTING = "intelligent_assistant_setting";
    public static final String VALUE_DES_INTELLIGENT_ASSISTANT_SKILL_INTRODUCTION = "intelligent_assistant_skill_introduction";
    public static final String VALUE_DES_INTELLIGENT_ASSISTANT_VOICE_REPORT_CONTROL = "intelligent_assistant_voice_report_control";
    public static final String VALUE_DES_JDALLYARDACTIVITY = "djxy_allgarden";
    public static final String VALUE_DES_JDAUTH = "jdAuth";
    public static final String VALUE_DES_JDBRANDACTIVITY = "djxy_brand";
    public static final String VALUE_DES_JDBUYPACK2ACTIVITY = "packDetail_v200";
    public static final String VALUE_DES_JDCUSTOMCHANNEL_MAIN = "jddjxy";
    public static final String VALUE_DES_JDLOGIN = "jdLogin";
    public static final String VALUE_DES_JDPAY_CODE = "jdpaymentcode";
    public static final String VALUE_DES_JDREACT_COMMON = "jdreactcommon";
    public static final String VALUE_DES_JDREMINDER = "JDReminder";
    public static final String VALUE_DES_JD_DYNAMIC = "jddynamic";
    public static final String VALUE_DES_JD_TO_MOBIKE = "mobikeMView";
    public static final String VALUE_DES_JIMI = "jd_native_jimi";
    public static final String VALUE_DES_JSHOP_ACTIVITY_PAGE = "jshopActivityPage";
    public static final String VALUE_DES_JSHOP_BRAND_LIST = "jshopBrand";
    public static final String VALUE_DES_JSHOP_DETAIL = "jshopDetail";
    public static final String VALUE_DES_JSHOP_DYNAMIC_DETAIL = "jshopDynamicDetail";
    public static final String VALUE_DES_JSHOP_GUESS_WORD = "jshopGuessWord";
    public static final String VALUE_DES_JSHOP_MEMBER = "jshopMember";
    public static final String VALUE_DES_JSHOP_PRODUCT_LIST = "jshopProductList";
    public static final String VALUE_DES_JSHOP_SIGN = "jshopSign";
    public static final String VALUE_DES_JSHOP_SIGN_RANK = "signRank";
    public static final String VALUE_DES_LIFE_CIRCLE = "shenghuoquan";
    public static final String VALUE_DES_LIFE_TRAVEL = "lifetravel";
    public static final String VALUE_DES_LIVE_ROOM = "LivePlayerRoom";
    public static final String VALUE_DES_LOOK_SIMILAR = "lookSimilar";
    public static final String VALUE_DES_LOTTERY = "lottery";
    public static final String VALUE_DES_LOTTERY1 = "caipiao";
    public static final String VALUE_DES_MATCH_DETAIL = "outfit_detail";
    public static final String VALUE_DES_MATRIXAR = "matrixar";
    public static final String VALUE_DES_MEMBERCODE = "MemberCode";
    public static final String VALUE_DES_MESSAGE_BOX = "myMessageBox";
    public static final String VALUE_DES_MESSAGE_M = "myMessageM";
    public static final String VALUE_DES_MESSAGE_SEC = "myMessageAccount";
    public static final String VALUE_DES_MESSAGE_SET = "myMessageSet";
    public static final String VALUE_DES_MESSAGE_SET_RN = "myMessageSetRN";
    public static final String VALUE_DES_MESSAGE_SHOW = "myMessageShow";
    public static final String VALUE_DES_MIAOSHA_BANNER = "seckillBannerGoods";
    public static final String VALUE_DES_MIAOSHA_BRAND = "brandinner";
    public static final String VALUE_DES_MIAOSHA_JISHI_INNER = "seckillMarketGoods";
    public static final String VALUE_DES_MIAOSHA_LIANGFAN = "liangfan";
    public static final String VALUE_DES_MIAOSHA_LIANGFAN_ACTIVITY = "miaosha_liangfan";
    public static final String VALUE_DES_MIAOSHA_MYCONCERN = "myRemind";
    public static final String VALUE_DES_MIAOSHA_NEWPRODUCT = "seckill_newproduct";
    public static final String VALUE_DES_MIAOSHA_PARITY = "paritySeckill";
    public static final String VALUE_DES_MIAOSHA_PARITY_LANDING = "parityDoubleNineLandPage";
    public static final String VALUE_DES_MIAOSHA_RANKLIST = "seckillranklist";
    public static final String VALUE_DES_MIAOSHA_SEARCH_RESULT = "seckill_search_result";
    public static final String VALUE_DES_MIAOSHA_SELLOUT_ACTIVITY = "seckillsellout";
    public static final String VALUE_DES_MINI_PRODUCTDETAIL_DYN = "miniProductdetail";
    public static final String VALUE_DES_MORE = "more";
    public static final String VALUE_DES_MORE_GAME_INTERACTION_ACTIVITY = "moregameinteraction";
    public static final String VALUE_DES_MORE_SETTING = "moreSetting";
    public static final String VALUE_DES_MOVIE = "Movie";
    public static final String VALUE_DES_MOVIE1 = "dianyingpiao";
    public static final String VALUE_DES_MYCOLLECT = "mycollect";
    public static final String VALUE_DES_MYCOLLECT1 = "Mguanzhu";
    public static final String VALUE_DES_MYCOLLECT2 = "jshopList";
    public static final String VALUE_DES_MYJD = "myJd";
    public static final String VALUE_DES_MYSTREET = "mystreet";
    public static final String VALUE_DES_MY_BANKCARD = "myBankcard";
    public static final String VALUE_DES_MY_DNA = "myDNA";
    public static final String VALUE_DES_MY_GIFTCARD = "myGiftCard";
    public static final String VALUE_DES_MY_WALLET = "myWallet";
    public static final String VALUE_DES_MY_WALLET_PLATFORM = "myPlatformWallet";
    public static final String VALUE_DES_MY_WALLET_PLATFORM_NEW = "platformwalletnew";
    public static final String VALUE_DES_NEW_CATEGORY = "newCategory";
    public static final String VALUE_DES_NEW_GOODSHOP = "newgoodshop";
    public static final String VALUE_DES_NEW_NEW_CATEGORY = "Classification";
    public static final String VALUE_DES_NEW_RECHARGE = "newrecharge";
    public static final String VALUE_DES_NEW_THEMESTREET = "newthemestreet";
    public static final String VALUE_DES_NOTIFICATION_HANDLER = "notificationHandler";
    public static final String VALUE_DES_OFTEN_BUY = "oftenBuy";
    public static final String VALUE_DES_ONEBOX_PRODUCTLIST = "oneBoxProductList";
    public static final String VALUE_DES_ONEHOUR_SEARCH = "hourSearch";
    public static final String VALUE_DES_ORDERDETAIL = "orderDetail";
    public static final String VALUE_DES_ORDER_CONFIRM_SUCCESS = "orderConfirmSuccessPage";
    public static final String VALUE_DES_ORDER_DETAIL_WEAR = "orderDetail_wear";
    public static final String VALUE_DES_ORDER_DRAWABLE_TRACK = "orderdrawabletrack";
    public static final String VALUE_DES_ORDER_INSTALL = "orderInstall";
    public static final String VALUE_DES_ORDER_MODIFY = "ordermodify";
    public static final String VALUE_DES_ORDER_RECYCLE = "orderRecycle";
    public static final String VALUE_DES_ORDER_TRACK = "orderTrack";
    public static final String VALUE_DES_PERSONAL_FONT_SETTING = "personal_font_setting";
    public static final String VALUE_DES_PERSONAL_MORE_CHANNEL = "showpersonalmorechannel";
    public static final String VALUE_DES_PERSONAL_MYBACK_AND_EXCHANGE = "personal_myback_and_exchange";
    public static final String VALUE_DES_PERSONAL_SEARCH_CHANNEL = "showpersonalsearchchannel";
    public static final String VALUE_DES_PERSONAL_SEC_ACTIVITY = "userinfoeditpage";
    public static final String VALUE_DES_PHONE_SALE = "phoneSale";
    public static final String VALUE_DES_PHONE_SALE1 = "mobile-only";
    public static final String VALUE_DES_PHOTOBUY = "photobuy";
    public static final String VALUE_DES_PHOTOBUY1 = "native_photobuy";
    public static final String VALUE_DES_PLAN_B_USER_MANAGER = "accountinfopage";
    public static final String VALUE_DES_PLATFORM_FEEDBACK = "platformfeedback";
    public static final String VALUE_DES_POP_IM = "pop_native_im";
    public static final String VALUE_DES_PREVIEW_LIVE = "LivePreviewList";
    public static final String VALUE_DES_PRIVACY_POLICY = "privacypolicy";
    public static final String VALUE_DES_PRODUCT = "product";
    public static final String VALUE_DES_PRODUCT_THREED = "threedproduct";
    public static final String VALUE_DES_PROMOTION_JUMP = "promotionJump";
    public static final String VALUE_DES_PROMOTION_NEW = "promotionNew";
    public static final String VALUE_DES_QQ_CHONGZHI = "QQchongzhi";
    public static final String VALUE_DES_QUICK_PASS = "quickpass";
    public static final String VALUE_DES_RANKINGLISTHOME = "JDRankHome";
    public static final String VALUE_DES_RANKING_PAGE = "RankingMain";
    public static final String VALUE_DES_RANKING_PAGE1 = "goodranking";
    public static final String VALUE_DES_RANKLIST = "ranklist";
    public static final String VALUE_DES_REAR_LINK_LOGIN = "rearLinkLogin";
    public static final String VALUE_DES_RECODER = "recoder_activity";
    public static final String VALUE_DES_RECOMMEND_GOOD_PRODUCTS = "RecommendGoodProducts";
    public static final String VALUE_DES_RECOMMEND_SCENE_DETAIL = "RecommendSceneLabelDetail";
    public static final String VALUE_DES_RECOMMEND_SIMILAR = "recommend_Similar";
    public static final String VALUE_DES_RELATED_PRODUCTS = "related_products";
    public static final String VALUE_DES_SCAN = "scan";
    public static final String VALUE_DES_SCAN1 = "native_scan";
    public static final String VALUE_DES_SCAN2 = "saoasao";
    public static final String VALUE_DES_SCAN_LOGIN = "scanLogin";
    public static final String VALUE_DES_SEARCH = "search";
    public static final String VALUE_DES_SECKILL_AREA_CATEGORY_ACTIVITY = "seckillAreaCategory";
    public static final String VALUE_DES_SECKILL_LIVE_LIST = "SecKillLivePlayList";
    public static final String VALUE_DES_SECKILL_LIVE_PRE = "SecKillLivePreDetail";
    public static final String VALUE_DES_SHAKE1 = "native_shake";
    public static final String VALUE_DES_SHAREORDER_TEXT_EDIT = "shareOrderTextEdit";
    public static final String VALUE_DES_SHARE_ORDER_COMMENT = "ShareOrderComment";
    public static final String VALUE_DES_SHARE_ORDER_COMMENT_BIG_PHOTO = "shareOrderCommentBigPhoto";
    public static final String VALUE_DES_SHOW_SHARE_FRIEND_LIST = "showsharefriendlist";
    public static final String VALUE_DES_SH_ACTIVITY_VIDEO = "SH_Activity_Video";
    public static final String VALUE_DES_SMALLVIDEO = "LiveSmallVideo";
    public static final String VALUE_DES_SMART_DEVICE = "smartdevice";
    public static final String VALUE_DES_SSS_TAB_CONTAINER = "SSSTabContainer";
    public static final String VALUE_DES_STOREREND = "storetrend";
    public static final String VALUE_DES_STOREREND1 = "native_storetrend";
    public static final String VALUE_DES_SUBJECT_LIST = "disc_subject_list";
    public static final String VALUE_DES_SUBJECT_NEW_LIST = "DiscSubjectList";
    public static final String VALUE_DES_TOKEN = "token";
    public static final String VALUE_DES_TOPIC_TAG_LIST = "tag_list";
    public static final String VALUE_DES_TRIAL_REPORT_DETAIL = "JDNPTrialDetail";
    public static final String VALUE_DES_TRIAL_REPORT_LIST = "JDNPTrialReportList";
    public static final String VALUE_DES_TRY_CLOTHES = "tryClothes";
    public static final String VALUE_DES_TRY_CLOTHES_3D = "tryClothes3D";
    public static final String VALUE_DES_UNIFYDETAIL = "unifieddetail";
    public static final String VALUE_DES_USER_PAGE_HOME = "userPageHome";
    public static final String VALUE_DES_VANGOPH_COLLECTION_DETAIL = "pMyCollectionDetail";
    public static final String VALUE_DES_VANGOPH_DETAIL = "pPinDetail";
    public static final String VALUE_DES_VANGOPH_MAIN = "pInterestSet";
    public static final String VALUE_DES_VIDEOLIFE = "videolife";
    public static final String VALUE_DES_VIDEO_BUY = "videoBuy";
    public static final String VALUE_DES_VIDEO_IMMERSION = "VideoImmersion";
    public static final String VALUE_DES_VIDEO_QUIZ = "jump_VideoRedPacket";
    public static final String VALUE_DES_VIDEO_RANK = "VideoRank";
    public static final String VALUE_DES_VIRTUAL_BUSINESS = "virtualbusiness";
    public static final String VALUE_DES_VOICEINPUT = "jdvoiceinput";
    public static final String VALUE_DES_WAITING_GOODS = "waitingforGoods";
    public static final String VALUE_DES_WEB_BZ = "webBz";
    public static final String VALUE_DES_WEB_LANDPAGE = "webLandPage";
    public static final String VALUE_DES_WIDGET_EDIT_SETTING = "widgetEditSetting";
    public static final String VALUE_DES_WJ = "nfc_wj";
    public static final String VALUE_DES_WL_DEVICE_DETAILS = "wl_device_details";
    public static final String VALUE_DES_WL_HANDLE_QRCODE = "wl_handler_qrcode";
    public static final String VALUE_DES_WL_WAITADD_LIST = "wl_waitadd_list";
    public static final String VALUE_DES_WORTHBUY_2G_INVENTORY_DETAIL = "worthbuy_2GInventory_detail";
    public static final String VALUE_DES_WORTHBUY_ALBUM = "worthbuy_album";
    public static final String VALUE_DES_WORTHBUY_AUTHOR = "worthbuy_author";
    public static final String VALUE_DES_WORTHBUY_DETAIL = "worthbuy_detail";
    public static final String VALUE_DES_WORTHBUY_INVENTORY_DETAIL = "worthbuy_inventory_detail";
    public static final String VALUE_DES_WORTHBUY_LIKE = "worthbuy_like";
    public static final String VALUE_DES_WORTHBUY_LIST = "worthbuy_list";
    public static final String VALUE_DES_WORTHBUY_NEW_PRODUCT = "worthbuyNewProductDetail";
    public static final String VALUE_DES_WORTHBUY_TAG = "worthbuy_tag";
    public static final String VALUE_DES_WORTHBUY_TICKET_LIST = "worthbuyTicketList";
    public static final String VALUE_DES_WORTHBUY_WISH_LIST = "worthbuyWishList";
    public static final String VALUE_DES_WORTH_BUY = "worthBuy";
    public static final String VALUE_DES_WORTH_BUY1 = "goodchoice";
    public static final String VALUE_DES_WORTH_BUY_TOGETHER = "product_together_list";
    public static final String VALUE_DES_WU_LIU_CHA_XUN = "wuliuchaxun";
    public static final String VALUE_DES_WU_LIU_CHA_XUN1 = "Mwuliuchaxun";
    public static final String VALUE_DES_WU_LIU_CHA_XUN2 = "logistics";
    public static final String VALUE_DES_YIYUANQIANGBAO = "yiyuanqiangbao";
    public static final String VALUE_DES_ZC_PRESALE = "ZcPresale";
    public static final String VALUE_DISCOVERY_RECOMMEND_FEEDLIST = "findRecommendFeedList";
    public static final String VALUE_GIFT_SHARE_PAGE = "sendGiftPage";
    public static final String VALUE_JUMP = "jump";
    public static final String VALUE_NEWPRODUCT = "newProductHomePage";
    public static final String VALUE_NEWPRODUCT_CORE = "newProductCore";
    public static final String VALUE_NEWPRODUCT_DROP = "newsReductionMainPage";
    public static final String VALUE_NEWPRODUCT_HOME_TAB = "mainPageNewProductTab";
    public static final String VALUE_NEWPRODUCT_INFO = "onlyJDNPInfoPage";
    public static final String VALUE_NEWPRODUCT_RN = "newProductRNPage";
    public static final String VALUE_NEWPRODUCT_TENDENCY = "newProductTendencyPage";
    public static final String VALUE_NEW_ARRIVALS = "newArrivalsHomeTabPage";
    public static final String VALUE_NEW_PRODUCT_PAGE = "newProductPage";
    public static final String VALUE_PRODUCTDETAIL_REFRESH = "productDetail_refresh";
    public static final String VALUE_PRODUCT_GIFT_PAGE = "givingGiftPage";
    public static final String VAULE_DES_ACTIVITY = "activity";
    public static final String VAULE_DES_AIREXORDER = "exTravelOrder";
    public static final String VAULE_DES_AIRINORDER = "inTravelOrder";
    public static final String VAULE_DES_AIRLINE = "airTicket";
    public static final String VAULE_DES_AIRLINE1 = "jipiao";
    public static final String VAULE_DES_AIRLINE_ORDER = "airTicketOrder";
    public static final String VAULE_DES_AIRLIST = "airList";
    public static final String VAULE_DES_CART = "cart";
    public static final String VAULE_DES_CARTB = "cartB";
    public static final String VAULE_DES_CASHIER_COMPLETE = "cashiercomplete";
    public static final String VAULE_DES_CASHIER_FRIEND_PAY = "friendpay";
    public static final String VAULE_DES_CASHIER_FRIEND_PAY_Dialog = "friendpayDialog";
    public static final String VAULE_DES_CASHIER_PAY = "cashierpay";
    public static final String VAULE_DES_COUPON_CENTER = "couponCenter";
    public static final String VAULE_DES_COUPON_CENTER1 = "couponcenter";
    public static final String VAULE_DES_DISCOVERY = "Discovery";
    public static final String VAULE_DES_DM = "DM";
    public static final String VAULE_DES_FLOW_ORDERDETAIL = "flowOrderDetail";
    public static final String VAULE_DES_GET_COUPON = "getCoupon";
    public static final String VAULE_DES_HOME_ICONS = "homeIcons";
    public static final String VAULE_DES_JDGAME = "jdgame";
    public static final String VAULE_DES_JDPAY_SETTING = "jdpaysetting";
    public static final String VAULE_DES_JDTHIRDLOGIN = "ThirdPartyLogin";
    public static final String VAULE_DES_JSHOP = "jshopMain";
    public static final String VAULE_DES_JSHOP_ACTIVITY = "jshopActivity";
    public static final String VAULE_DES_M = "m";
    public static final String VAULE_DES_MEME_DISCSEARCH = "DiscSearch";
    public static final String VAULE_DES_MEME_DISCSEARCH_RESULT = "DiscSearchResult";
    public static final String VAULE_DES_NATIVE_CASHIER = "nativeCashier";
    public static final String VAULE_DES_ORDER_LIST = "orderlist";
    public static final String VAULE_DES_ORDER_TRACE = "ordertrace";
    public static final String VAULE_DES_PRODUCT_DETAIL = "productDetail";
    public static final String VAULE_DES_PRODUCT_DETAIL1 = "skudetail";
    public static final String VAULE_DES_PRODUCT_LIST = "productList";
    public static final String VAULE_DES_PROMOTION = "promotion";
    public static final String VAULE_DES_QQANDGAME_ORDERDETAIL = "qqAndGameOrderDetail";
    public static final String VAULE_DES_REACTNATIVE_PAYSUCCESS = "paySuccess";
    public static final String VAULE_DES_REACTNATIVE_VERSION = "jdreactversion";
    public static final String VAULE_DES_RECHARGE_ORDERDETAIL = "rechargeOrderDetail";
    public static final String VAULE_DES_SECK_KILL = "seckill";
    public static final String VAULE_DES_SECK_KILL1 = "miaosha";
    public static final String VAULE_DES_SHARE = "share";
    public static final String VAULE_DES_TEXT_CONTAINER = "textContainer";
    public static final String VAULE_DES_XIAOBING = "getXB";
    public static final String VAULE_DES_XIAOBING1 = "native_littlebing";
    public static final String VAULE_DES_XIAOBING2 = "xiaobing";
    public static final String XVIEW2_NXVIEW = "nxview";
    public static boolean isPcStream;
    private static volatile JSONObject mDesJsonObj;

    /* loaded from: classes5.dex */
    public static class JumpFrom {
        public static final int FROM_APPCENTER = 2;
        public static final int FROM_BABEL = 6;
        public static final int FROM_FAXIAN = 4;
        public static final int FROM_GENERIC_CHANNEL = 3;
        public static final int FROM_GOODSTUFF = 7;
        public static final int FROM_HOME = 1;
        public static final int FROM_OPENAPP = 0;
        public static final int FROM_OTHERS = -1;
        public static final int FROM_PERSONAL = 5;
        public static final int FROM_WORTHBUY = 8;
    }

    private static JSONArray convertJDJsonArrayToJson(JDJSONArray jDJSONArray) throws Exception {
        if (jDJSONArray == null) {
            return null;
        }
        if (jDJSONArray.isEmpty()) {
            return new JSONArray();
        }
        JSONArray jSONArray = new JSONArray();
        for (int i2 = 0; i2 < jDJSONArray.size(); i2++) {
            Object obj = jDJSONArray.get(i2);
            if (obj instanceof JDJSONObject) {
                jSONArray.put(convertJDJsonObjectToJson((JDJSONObject) obj));
            } else if (obj instanceof JDJSONArray) {
                jSONArray.put(convertJDJsonArrayToJson((JDJSONArray) obj));
            } else {
                jSONArray.put(obj);
            }
        }
        return jSONArray;
    }

    private static JSONObject convertJDJsonObjectToJson(JDJSONObject jDJSONObject) throws Exception {
        if (jDJSONObject == null) {
            return null;
        }
        if (jDJSONObject.isEmpty()) {
            return new JSONObject();
        }
        JSONObject jSONObject = new JSONObject();
        for (String str : jDJSONObject.keySet()) {
            if (!TextUtils.isEmpty(str)) {
                Object obj = jDJSONObject.get(str);
                if (obj instanceof JDJSONObject) {
                    jSONObject.put(str, convertJDJsonObjectToJson((JDJSONObject) obj));
                } else if (obj instanceof JDJSONArray) {
                    jSONObject.put(str, convertJDJsonArrayToJson((JDJSONArray) obj));
                } else {
                    jSONObject.put(str, jDJSONObject.get(str));
                }
            }
        }
        return jSONObject;
    }

    public static JSONObject convertJDJsonToJson(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return null;
        }
        if (jDJSONObject.isEmpty()) {
            return new JSONObject();
        }
        JSONObject jSONObject = new JSONObject();
        try {
            for (String str : jDJSONObject.keySet()) {
                if (!TextUtils.isEmpty(str)) {
                    Object obj = jDJSONObject.get(str);
                    if (obj instanceof JDJSONObject) {
                        jSONObject.put(str, convertJDJsonObjectToJson((JDJSONObject) obj));
                    } else if (obj instanceof JDJSONArray) {
                        jSONObject.put(str, convertJDJsonArrayToJson((JDJSONArray) obj));
                    } else {
                        jSONObject.put(str, jDJSONObject.get(str));
                    }
                }
            }
        } catch (Throwable th) {
            ExceptionReporter.reportOpenAppJumpException("Jump_ErrorInJDJsonToJson", null, "JDJson: " + jDJSONObject.toJSONString() + ", e: " + ExceptionReporter.getStackStringFromException(th));
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
        return jSONObject;
    }

    public static void execJump(Context context, JumpEntity jumpEntity, int i2) {
        if (jumpEntity == null || context == null) {
            return;
        }
        String str = jumpEntity.des;
        String str2 = jumpEntity.params;
        ShareEntity shareInfo = jumpEntity.getShareInfo();
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (str2 == null) {
            str2 = "";
        }
        JSONObjectProxy parseParamsJsonFromString = JsonParser.parseParamsJsonFromString(str2);
        Bundle bundleFromJson = getBundleFromJson(parseParamsJsonFromString);
        if (str.equals(VALUE_DES_FILLORDER)) {
            bundleFromJson.putString("json", parseParamsJsonFromString.toString());
        }
        if (str.equals("productDetail") || str.equals("skudetail")) {
            bundleFromJson.putString("sourceValue", jumpEntity.getSrv());
        }
        if (str.equals(VALUE_DES_PROMOTION_NEW)) {
            bundleFromJson.putString("sourceValue", jumpEntity.getSrv());
        }
        if (str.equals(VALUE_DES_PROMOTION_JUMP)) {
            bundleFromJson.putString("sourceValue", jumpEntity.getSrv());
        }
        if (str.equals("faxian_article")) {
            bundleFromJson.putString(NotificationMessageSummary.TEST_ID, jumpEntity.getSrv());
        }
        if (str.equals(VALUE_DES_WORTHBUY_LIST) || str.equals(VALUE_DES_ALBUM_LIST) || str.equals(VAULE_DES_MEME_DISCSEARCH)) {
            bundleFromJson.putString("params", jumpEntity.params);
        }
        if ((shareInfo == null || shareInfo.url == null) ? false : true) {
            ShareInfo shareInfo2 = new ShareInfo();
            shareInfo2.setUrl(shareInfo.url);
            shareInfo2.setIconUrl(shareInfo.avatar);
            shareInfo2.setTitle(shareInfo.title);
            shareInfo2.setSummary(shareInfo.content);
            bundleFromJson.putParcelable("shareInfo", shareInfo2);
            bundleFromJson.putString("isShare", "1");
            bundleFromJson.putBoolean(MBaseKeyNames.IS_NEED_SHARE, true);
        }
        bundleFromJson.putString(LoginConstans.JUMP_DES, str);
        bundleFromJson.putInt("jumpFrom", i2);
        OKLog.d(TAG, "JumpUtil.execJump():" + str + "--->" + str2);
        execJumpByDes(str, context, bundleFromJson);
    }

    public static void execJumpByDes(String str, Context context, Bundle bundle) {
        execJumpByDes(str, context, bundle, null);
    }

    public static void finishInterfaceActivity(Context context) {
        if (context == null || !(context instanceof BaseActivity)) {
            return;
        }
        BaseActivity baseActivity = (BaseActivity) context;
        String simpleName = baseActivity.getClass().getSimpleName();
        if (TextUtils.isEmpty(simpleName) || !simpleName.contains("InterfaceActivity")) {
            return;
        }
        baseActivity.finish();
    }

    public static Bundle getBundleFromJDJson(JDJSONObject jDJSONObject) {
        Set<String> keySet;
        Bundle bundle = new Bundle();
        if (jDJSONObject == null || (keySet = jDJSONObject.keySet()) == null) {
            return bundle;
        }
        for (String str : keySet) {
            Object obj = jDJSONObject.get(str);
            if (obj != null) {
                if (obj instanceof String) {
                    bundle.putString(str, (String) obj);
                } else if (obj instanceof Integer) {
                    bundle.putInt(str, ((Integer) obj).intValue());
                } else if (obj instanceof Boolean) {
                    bundle.putBoolean(str, ((Boolean) obj).booleanValue());
                } else if (obj instanceof Long) {
                    bundle.putLong(str, ((Long) obj).longValue());
                } else if (obj instanceof JDJSONArray) {
                    bundle.putString(str, obj.toString());
                } else if (obj instanceof JDJSONObject) {
                    bundle.putString(str, obj.toString());
                } else if (OKLog.D) {
                    OKLog.d(TAG, " getBundleFromJson ---> object : " + obj);
                }
            }
        }
        return bundle;
    }

    public static Bundle getBundleFromJson(JSONObjectProxy jSONObjectProxy) {
        Iterator keys;
        Bundle bundle = new Bundle();
        if (jSONObjectProxy == null || (keys = jSONObjectProxy.keys()) == null) {
            return bundle;
        }
        while (keys.hasNext()) {
            Object next = keys.next();
            if (next != null && (next instanceof String)) {
                String str = (String) next;
                Object obj = null;
                try {
                    obj = jSONObjectProxy.get(str);
                } catch (JSONException e2) {
                    if (OKLog.E) {
                        OKLog.e(TAG, e2);
                    }
                }
                if (obj != null) {
                    if (obj instanceof String) {
                        bundle.putString(str, (String) obj);
                    } else if (obj instanceof Integer) {
                        bundle.putInt(str, ((Integer) obj).intValue());
                    } else if (obj instanceof Boolean) {
                        bundle.putBoolean(str, ((Boolean) obj).booleanValue());
                    } else if (obj instanceof Long) {
                        bundle.putLong(str, ((Long) obj).longValue());
                    } else if (obj instanceof JSONArray) {
                        bundle.putString(str, obj.toString());
                    } else if (obj instanceof JSONObject) {
                        bundle.putString(str, obj.toString());
                    } else if (OKLog.D) {
                        OKLog.d(TAG, " getBundleFromJson ---> object : " + obj);
                    }
                }
            }
        }
        return bundle;
    }

    public static String getDesFromModuleId(int i2) {
        return i2 != 216 ? VALUE_DES_APPHOME : VALUE_DES_JDAUTH;
    }

    public static boolean isJdPayOpenScheme(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.toLowerCase().equals("jdpayopen");
    }

    public static boolean isJdPayScheme(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.toLowerCase().equals("jdpay");
    }

    public static boolean isJdScheme(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            if (UnI18NUtils.isThApp()) {
                return str.toLowerCase().equals(OPENAPP_SCHEME_THAI);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return str.toLowerCase().equals("openapp.jdmobile") || str.toLowerCase().equals("openjd");
    }

    @Deprecated
    public static boolean isOpenAppScheme(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            if (UnI18NUtils.isThApp()) {
                return str.toLowerCase().startsWith(OPENAPP_SCHEME_THAI);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return str.toLowerCase().startsWith("openapp.jdmobile") || str.toLowerCase().startsWith("openjd");
    }

    public static synchronized void loadDesData(Context context) {
        synchronized (JumpUtil.class) {
            if (context == null) {
                return;
            }
            try {
                long currentTimeMillis = System.currentTimeMillis();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open("DesJumpClassMap.json")));
                String str = "";
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    str = str + readLine;
                }
                long currentTimeMillis2 = System.currentTimeMillis();
                bufferedReader.close();
                mDesJsonObj = new JSONObject(str);
                String str2 = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("object :");
                sb.append(mDesJsonObj);
                OKLog.d(str2, sb.toString() == null ? DYConstants.DY_NULL_STR : mDesJsonObj.toString());
                if (mDesJsonObj != null && mDesJsonObj.length() > 0) {
                    OKLog.d(str2, "get desMap data success,costs time:" + (currentTimeMillis2 - currentTimeMillis));
                } else {
                    OKLog.d(str2, "can't load DesJumpClassMap");
                }
            } catch (Exception e2) {
                String str3 = TAG;
                OKLog.e(str3, "DesJumpUtil loadDesData error:" + e2.getMessage());
                OKLog.e(str3, e2);
                mDesJsonObj = null;
            }
        }
    }

    public static JDJSONObject string2JDJsonObject(String str, Uri uri) {
        try {
            JDJSONObject parseParamsJDJsonFromString = JsonParser.parseParamsJDJsonFromString(str);
            if (parseParamsJDJsonFromString != null && parseParamsJDJsonFromString.size() >= 1) {
                return parseParamsJDJsonFromString;
            }
            String uri2 = uri.toString();
            if (uri2.indexOf("params=") < 0) {
                return null;
            }
            str = uri2.substring(uri2.indexOf("params=") + 7, uri2.length());
            if (str.startsWith("{%22") || str.startsWith("%7B%22") || str.startsWith("%7b%22")) {
                str = URLDecoder.decode(str, "utf-8");
            }
            JDJSONObject parseParamsJDJsonUnsafe = JsonParser.parseParamsJDJsonUnsafe(str);
            if (!TextUtils.isEmpty(str) && (parseParamsJDJsonUnsafe == null || parseParamsJDJsonUnsafe.isEmpty())) {
                String uri3 = uri.toString();
                StringBuilder sb = new StringBuilder();
                sb.append(parseParamsJDJsonUnsafe == null ? "JDJsonObject is null, " : "");
                sb.append("Decoded params: ");
                sb.append(str);
                ExceptionReporter.reportOpenAppJumpException("Jump_EmptyJDJson", uri3, sb.toString());
            }
            return parseParamsJDJsonUnsafe;
        } catch (Exception e2) {
            ExceptionReporter.reportOpenAppJumpException("Jump_ErrorInStrToJDJson", uri != null ? uri.toString() : "", "Params: " + str + ", e: " + ExceptionReporter.getStackStringFromException(e2));
            if (OKLog.E) {
                OKLog.e(TAG, e2);
                return null;
            }
            return null;
        }
    }

    public static JSONObject string2JsonObject(String str, Uri uri) {
        try {
            JSONObjectProxy parseParamsJsonFromString = JsonParser.parseParamsJsonFromString(str);
            if (parseParamsJsonFromString != null && parseParamsJsonFromString.length() >= 1) {
                return parseParamsJsonFromString;
            }
            String uri2 = uri.toString();
            if (uri2.indexOf("params=") < 0) {
                return null;
            }
            str = uri2.substring(uri2.indexOf("params=") + 7, uri2.length());
            if (str.startsWith("{%22") || str.startsWith("%7B%22") || str.startsWith("%7b%22")) {
                str = URLDecoder.decode(str, "utf-8");
            }
            JSONObjectProxy parseParamsJsonUnsafe = JsonParser.parseParamsJsonUnsafe(str);
            if (!TextUtils.isEmpty(str) && (parseParamsJsonUnsafe == null || parseParamsJsonUnsafe.length() == 0)) {
                String uri3 = uri.toString();
                StringBuilder sb = new StringBuilder();
                sb.append(parseParamsJsonUnsafe == null ? "JsonObject is null, " : "");
                sb.append("Decoded params: ");
                sb.append(str);
                ExceptionReporter.reportOpenAppJumpException("Jump_EmptyJson", uri3, sb.toString());
            }
            return parseParamsJsonUnsafe;
        } catch (Exception e2) {
            ExceptionReporter.reportOpenAppJumpException("Jump_ErrorInStrToJson", uri != null ? uri.toString() : "", "Params: " + str + ", e: " + ExceptionReporter.getStackStringFromException(e2));
            if (OKLog.E) {
                OKLog.e(TAG, e2);
                return null;
            }
            return null;
        }
    }

    public static void toTargetDes(String str, Context context, Bundle bundle) {
        toTargetDes(str, context, bundle, null);
    }

    @Deprecated
    public static void toTargetPage(Context context, int i2, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        execJumpByDes(getDesFromModuleId(i2), context, bundle);
    }

    public static synchronized void execJumpByDes(String str, Context context, Bundle bundle, JumpCallbackListener jumpCallbackListener) {
        String str2;
        synchronized (JumpUtil.class) {
            if (context == null) {
                return;
            }
            if (mDesJsonObj == null || mDesJsonObj.length() <= 0) {
                loadDesData(context);
            }
            if (mDesJsonObj != null && !TextUtils.isEmpty(str)) {
                if (bundle == null) {
                    bundle = new Bundle();
                }
                String string = bundle.getString("token");
                String string2 = bundle.getString("loginType");
                if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
                    isPcStream = true;
                    new PcStreamUtil(context).accessPCStream(str, bundle);
                } else {
                    isPcStream = false;
                    toTargetDes(str, context, bundle, jumpCallbackListener);
                }
                return;
            }
            if (mDesJsonObj == null) {
                ExceptionReporter.reportOpenAppJumpException("Jump_NullDesJson", null, "context: " + context.getClass().getSimpleName() + ", des = " + str + ", bundle = " + bundle.toString());
            }
            if (TextUtils.isEmpty(str)) {
                ExceptionReporter.reportOpenAppJumpException("Jump_EmptyDes", null, "context: " + context.getClass().getSimpleName() + ", bundle = " + bundle.toString());
            }
            if (OKLog.D) {
                String str3 = TAG;
                if (str == null) {
                    str2 = "des: null";
                } else {
                    str2 = "des: " + str;
                }
                OKLog.d(str3, str2);
            }
            if (jumpCallbackListener != null) {
                jumpCallbackListener.onError();
            }
        }
    }

    public static synchronized void toTargetDes(String str, Context context, Bundle bundle, JumpCallbackListener jumpCallbackListener) {
        String optString;
        synchronized (JumpUtil.class) {
            boolean z = bundle.getBoolean(WebWhiteScreenHolder.IS_FROM_M_INSIDE, true);
            try {
                optString = mDesJsonObj.optString(str);
            } catch (Exception e2) {
                OKLog.e(TAG, e2);
                if (jumpCallbackListener != null) {
                    jumpCallbackListener.onError();
                } else if (!z) {
                    execJumpByDes(VALUE_DES_APPHOME, context, new Bundle());
                } else {
                    finishInterfaceActivity(context);
                }
                StringBuilder sb = new StringBuilder();
                sb.append("context: ");
                sb.append(context != null ? context.getClass().getSimpleName() : "");
                sb.append(", des = ");
                sb.append(str);
                sb.append(", bundle = ");
                sb.append(bundle.toString());
                sb.append(",  ");
                sb.append(ExceptionReporter.getStackStringFromException(e2));
                ExceptionReporter.reportOpenAppJumpException("Jump_ErrorInTargetDes", null, sb.toString());
            }
            if (TextUtils.isEmpty(optString)) {
                if (OKLog.D) {
                    String str2 = TAG;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("class: ");
                    sb2.append((Object) null);
                    OKLog.d(str2, sb2.toString() == optString ? DYConstants.DY_NULL_STR : "");
                }
                if (jumpCallbackListener != null) {
                    jumpCallbackListener.onError();
                } else if (!z) {
                    execJumpByDes(VALUE_DES_APPHOME, context, new Bundle());
                } else {
                    finishInterfaceActivity(context);
                }
                StringBuilder sb3 = new StringBuilder();
                sb3.append("context: ");
                sb3.append(context != null ? context.getClass().getSimpleName() : "");
                sb3.append(", des = ");
                sb3.append(str);
                sb3.append(", bundle = ");
                sb3.append(bundle.toString());
                ExceptionReporter.reportOpenAppJumpException("Jump_EmptyClassName", null, sb3.toString());
                return;
            }
            Class<?> cls = Class.forName(optString);
            cls.getMethod("handleDesJumpRequest", Context.class, Bundle.class).invoke(cls.newInstance(), context, bundle);
            if (jumpCallbackListener != null) {
                jumpCallbackListener.onSuccess();
            }
        }
    }
}
