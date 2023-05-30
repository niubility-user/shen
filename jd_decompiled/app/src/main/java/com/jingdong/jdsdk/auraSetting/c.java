package com.jingdong.jdsdk.auraSetting;

import com.jd.aips.verify.bankcard.SdkEngineLauncher;
import com.jingdong.common.cart.CartBaseUtil;
import com.jingdong.common.deeplinkhelper.DeepLinkBingHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkBrowserHistoryHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkFavouritesHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkLiveHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkVangophHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkWorthbuyHelper;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.login.LoginConstans;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import java.util.List;
import java.util.Set;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* loaded from: classes14.dex */
class c implements d {
    private static c b;
    private b a;

    private c() {
    }

    public static synchronized c j() {
        c cVar;
        synchronized (c.class) {
            if (b == null) {
                c cVar2 = new c();
                b = cVar2;
                cVar2.k();
            }
            cVar = b;
        }
        return cVar;
    }

    private void k() {
        b bVar = new b();
        this.a = bVar;
        bVar.l(b.f12873i, 4611686018427387903L);
        this.a.l(b.f12874j, 4611686018427387903L);
        this.a.l(b.f12875k, Long.MAX_VALUE);
        this.a.l(b.f12876l, Long.MAX_VALUE);
        this.a.l(b.f12877m, 0L);
        this.a.k(0, "BUNDLENAME_TOTAL", 1L, "BUNDLE_UPDATE_ID_TOTAL");
        this.a.k(1, "com.jd.lib.jdsdk.external", 1L, "jdsdkExternal");
        this.a.k(2, "com.jd.lib.fairy", 1L, "fairy");
        this.a.k(6, "com.jd.lib.story", 16L, "aura_story");
        this.a.k(7, "com.jd.lib.mystreet", 32L, "mystreet");
        this.a.k(8, "com.jd.lib.favourites", 64L, DeepLinkFavouritesHelper.HOST_FAVOURITES);
        this.a.k(9, "com.jd.lib.productdetail", 128L, "productdetail");
        this.a.k(10, "com.jd.lib.jdpaysdk", 256L, "jdpaysdk");
        this.a.k(11, "com.jd.lib.coupon", 512L, "coupon");
        this.a.k(12, "com.jd.lib.search", 1024L, "search");
        this.a.k(13, "com.jd.lib.scan", 2048L, "scan");
        this.a.k(14, "com.jd.lib.bing", 4096L, DeepLinkBingHelper.HOST_BING);
        this.a.k(15, "com.jd.lib.rank", IjkMediaMeta.AV_CH_TOP_FRONT_CENTER, "rank");
        this.a.k(16, "com.jd.lib.jshop", IjkMediaMeta.AV_CH_TOP_FRONT_RIGHT, "jshop");
        this.a.k(17, "com.jd.lib.mycoupon", IjkMediaMeta.AV_CH_TOP_BACK_LEFT, JumpUtil.VALUE_DES_COUPON);
        this.a.k(18, "com.jd.lib.ordercenter", IjkMediaMeta.AV_CH_TOP_BACK_CENTER, "ordercenter");
        this.a.k(19, "com.jd.lib.worthbuy", IjkMediaMeta.AV_CH_TOP_BACK_RIGHT, DeepLinkWorthbuyHelper.HOST_WORTHBUY_LIST);
        this.a.k(20, "com.jd.lib.commune", 262144L, JumpUtil.VALUE_DES_COMMUNE);
        this.a.k(21, "com.jd.lib.evaluatecenter", 524288L, "evaluatecenter");
        this.a.k(33, "com.jd.lib.mylive", IjkMediaMeta.AV_CH_SURROUND_DIRECT_LEFT, DeepLinkLiveHelper.HOST_LIVE);
        this.a.k(22, "com.jd.lib.mybankcard", 2097152L, "mybankcard");
        this.a.k(24, "com.jd.lib.browserhistory", 8388608L, DeepLinkBrowserHistoryHelper.HOST_BROWSERHISTORY);
        this.a.k(25, "com.jd.lib.charge", 16777216L, "charge");
        this.a.k(26, "com.jd.lib.category", 33554432L, "category");
        this.a.k(27, "com.jd.lib.jshopcustom", 67108864L, "jshopcustom");
        this.a.k(28, "com.jd.lib.mycalendar", 134217728L, "mycalendar");
        this.a.k(29, "com.jd.lib.address", 268435456L, ThemeTitleConstant.TITLE_ADDRESS_DRAWABLE_ID);
        this.a.k(30, "com.jd.lib.liveverification", IjkMediaMeta.AV_CH_STEREO_LEFT, "liveverification");
        this.a.k(31, "com.jd.lib.mywallet", IjkMediaMeta.AV_CH_STEREO_RIGHT, "mywallet");
        this.a.k(32, "com.jd.lib.voice", IjkMediaMeta.AV_CH_WIDE_LEFT, "voice");
        this.a.k(34, "com.jd.lib.usermanager", IjkMediaMeta.AV_CH_SURROUND_DIRECT_RIGHT, "usermanager");
        this.a.k(35, "com.jd.lib.jdmiaosha", 68719476736L, "jdmiaosha");
        this.a.k(36, "com.jd.lib.androidpay", 137438953472L, "androidpay");
        this.a.k(38, "com.jd.lib.facerecognition", 1099511627776L, "facerecognition");
        this.a.k(39, "com.jd.lib.setting", 2199023255552L, ThemeTitleConstant.TITLE_SETTING_DRAWABLE_ID);
        this.a.k(40, "com.jd.lib.opencv", 4398046511104L, "opencv");
        this.a.k(41, "com.jd.lib.login", 8796093022208L, LoginConstans.FREGMENT_LOGIN_FLAG);
        this.a.k(43, "com.jd.lib.link", 35184372088832L, "link");
        this.a.k(44, "com.jd.lib.jdlivelist", 70368744177664L, DeepLinkLiveHelper.HOST_LIVE_LIST);
        this.a.k(45, "com.jd.lib.cart", 140737488355328L, "cart");
        this.a.k(46, "com.jd.lib.goodstuff", 281474976710656L, "goodstuff");
        this.a.k(47, "com.jd.lib.follow", 562949953421312L, "follow");
        this.a.k(48, "com.jd.lib.threedproduct", 1125899906842624L, JumpUtil.VALUE_DES_PRODUCT_THREED);
        this.a.k(49, "com.jd.lib.quickpass", 2251799813685248L, JumpUtil.VALUE_DES_QUICK_PASS);
        this.a.k(50, "com.jd.lib.frostfire", 4503599627370496L, "frostfire");
        this.a.k(51, "com.jd.lib.jdpaycode", 9007199254740992L, "jdpaycode");
        this.a.k(52, "com.jd.lib.jdpaycommon", 18014398509481984L, "jdpaycommon");
        this.a.k(53, "com.jd.lib.icssdk", 36028797018963968L, "icssdk");
        this.a.k(54, "com.jd.lib.push", 72057594037927936L, "push");
        this.a.k(55, "com.jd.lib.settlement", 144115188075855872L, CartBaseUtil.COMBINEORDER_USER_SETTLEMENT);
        this.a.k(56, "com.jd.lib.intelligentassistance", 288230376151711744L, "intelligentassistance");
        this.a.k(57, "com.jd.lib.intelligentsdkextend", 576460752303423488L, "intelligentsdkextend");
        this.a.k(58, "com.jd.lib.personal", 1152921504606846976L, "personal");
        this.a.k(60, "com.jd.lib.armakeup", 4611686018427387905L, JumpUtil.VALUE_DES_AR_MAKEUP);
        this.a.k(61, "com.jd.lib.developermode", 4611686018427387906L, "developermode");
        this.a.k(62, "com.jd.lib.tryclothes", 4611686018427387908L, "tryclothes");
        this.a.k(63, "com.jd.lib.incomingcall", 4611686018427387912L, "incomingcall");
        this.a.k(64, "com.jd.lib.meme", 4611686018427387920L, "meme");
        this.a.k(65, "com.jd.lib.community", 4611686018427387936L, "community");
        this.a.k(66, "com.jd.lib.headshoulders", 4611686018427387968L, "headshoulders");
        this.a.k(67, "com.jd.lib.jdpaygeneral", 4611686018427388032L, "jdpaygeneral");
        this.a.k(68, "com.jd.lib.unification", 4611686018427388160L, "unification");
        this.a.k(69, "com.jd.lib.threedtryclothes", 4611686018427388416L, "threedtryclothes");
        this.a.k(70, "com.jd.lib.vangoph", 4611686018427388928L, DeepLinkVangophHelper.HOST_VANGOPH);
        this.a.k(71, "com.jd.lib.sonicredpacket", 4611686018427389952L, "sonicredpacket");
        this.a.k(72, "com.jd.lib.matrixar", 4611686018427392000L, JumpUtil.VALUE_DES_MATRIXAR);
        this.a.k(73, "com.jd.lib.mycard", 4611686018427396096L, "mycard");
        this.a.k(74, "com.jd.lib.enjoybuy", 4611686018427404288L, JumpUtil.VALUE_DES_ENJOYBUY);
        this.a.k(75, "com.jd.lib.jdpayverify", 4611686018427420672L, "jdpayverify");
        this.a.k(76, "com.jd.lib.jdmade", 4611686018427453440L, "jdmade");
        this.a.k(78, "com.jd.lib.jdpaywithhold", 4611686018427650048L, "jdpaywithhold");
        this.a.k(79, "com.jd.lib.message", 4611686018427912192L, "message");
        this.a.k(80, "com.jd.lib.jdfan", 4611686018428436480L, "jdfan");
        this.a.k(81, "com.jd.lib.shopattention", 4611686018429485056L, "shopattention");
        this.a.k(82, "com.jd.lib.jdcustomchannel", 4611686018435776512L, "jdcustomchannel");
        this.a.k(83, "com.jd.lib.shareorder", 4611686018444165120L, "shareorder");
        this.a.k(84, "com.jd.lib.card", 4611686018460942336L, "card");
        this.a.k(85, "com.jd.lib.avsdk", 4611686018494496768L, "avsdk");
        this.a.k(86, "com.jd.lib.jdflutter", 4611686018561605632L, "jdflutter");
        this.a.k(87, "com.jd.lib.insightar", 4611686018695823360L, "insightar");
        this.a.k(88, "com.jd.lib.arbook", 4611686019501129728L, JumpUtil.VALUE_DES_ARBOOK);
        this.a.k(89, "com.jd.lib.uphone", 4611686020574871552L, "uphone");
        this.a.k(90, "com.jd.lib.babel", 4611686022722355200L, "babel");
        this.a.k(91, "com.jd.lib.home", 4611686027017322496L, "home");
        this.a.k(92, "com.jd.lib.armart", 4611686035607257088L, JumpUtil.VALUE_DES_ARMART);
        this.a.k(93, "com.jd.lib.brandshangou", 4611686052787126272L, "brandshangou");
        this.a.k(94, "com.jd.lib.Discovery", 4611686087146864640L, JumpUtil.VAULE_DES_DISCOVERY);
        this.a.k(95, "com.jd.lib.DiscoverAttention", 4611686155866341376L, "DiscoverAttention");
        this.a.k(96, "com.jd.lib.cashier", 4611686293305294848L, "cashier");
        this.a.k(97, "com.jd.lib.dolphin", 4611686568183201792L, JumpUtil.VALUE_DES_DOLPHIN);
        this.a.k(98, "com.jd.lib.JDCashReward", 4611687117939015680L, "JDCashReward");
        this.a.k(99, "com.jd.lib.NewProduct", 4611688217450643456L, "NewProduct");
        this.a.k(100, "com.jd.lib.jdfriend", 4611690416473899008L, "jdfriend");
        this.a.k(101, "com.jd.lib.mocklocation", 4611694814520410112L, "mocklocation");
        this.a.k(102, "com.jd.lib.JDBusinessAddress", 4611703610613432320L, "JDBusinessAddress");
        this.a.k(103, "com.jd.lib.RankingList", 4611721202799476736L, "RankingList");
        this.a.k(104, "com.jd.lib.arclothes", 4611756387171565568L, "arclothes");
        this.a.k(105, "com.jd.lib.virtualhuman", 4611826755915743232L, "virtualhuman");
        this.a.k(106, "com.jd.lib.jdlivelistlib", 4611967493404098560L, "jdlivelistlib");
        this.a.k(107, "com.jd.lib.cronet", 4612248968380809216L, "cronet");
        this.a.k(108, "com.jd.lib.newarrivals", 4612811918334230528L, "newarrivals");
        this.a.k(109, "com.jd.lib.productdetailfeeds", 4613937818241073152L, "productdetailfeeds");
        this.a.k(110, "com.jd.lib.omnichannelsearch", 4616189618054758400L, "omnichannelsearch");
        this.a.k(111, "com.jd.lib.orderinfocard", 4620693217682128896L, "order_center_card");
        this.a.k(112, "com.jd.lib.netvirta", 4629700416936869888L, "netvirta");
        this.a.k(113, "com.jd.lib.dcepsdk", 4647714815446351872L, "jdt-dcep");
        this.a.k(114, "com.jd.lib.hourlygo", 4683743612465315840L, JumpUtil.VALUE_DES_HOURLY_GO);
        this.a.k(115, "com.jd.lib.order_unit", 4755801206503243776L, "order_unit");
        this.a.k(116, "com.jd.lib.ISVPdHourShop", 4899916394579099648L, "ISVPdHourShop");
        this.a.k(117, "com.jd.lib.ISVHourRecommend", 5188146770730811392L, "ISVHourRecommend");
        this.a.k(118, SdkEngineLauncher.BUNDLE_NAME_BANKCARD_OCR, 5764607523034234881L, "risk_bankcard_ocr");
        this.a.k(119, "com.jd.lib.productdetailmini", 5764607523034234882L, "productdetailmini");
        this.a.k(120, "com.jd.lib.IsvSttlement", 5764607523034234884L, "IsvSttlement");
        this.a.k(121, "com.jd.lib.device_model_compute", 5764607523034234888L, "device_model_compute");
        this.a.k(122, com.jdjr.risk.identity.verify.SdkEngineLauncher.BUNDLE_NAME_IDCARD_NFC_VERIFY, 5764607523034234896L, "idcard_nfc_verify");
        this.a.k(123, "com.jd.lib.mMonitor", 5764607523034234912L, "mMonitor");
        this.a.k(124, com.jdjr.risk.identity.verify.SdkEngineLauncher.BUNDLE_NAME_IDCARD_OCR_VERIFY, 5764607523034234944L, "idcard_ocr_verify");
        this.a.k(125, "com.jd.lib.newcategory", 5764607523034235008L, "newcategory");
        this.a.k(126, "com.jd.lib.edge", 5764607523034235136L, "edge");
        this.a.j("home");
        this.a.j("babel");
        this.a.j("jshop");
        this.a.j("unification");
        this.a.j("follow");
        this.a.j(ThemeTitleConstant.TITLE_ADDRESS_DRAWABLE_ID);
        this.a.j("usermanager");
        this.a.j(JumpUtil.VALUE_DES_COUPON);
        this.a.j("scan");
        this.a.j("charge");
        this.a.j(ThemeTitleConstant.TITLE_SETTING_DRAWABLE_ID);
        this.a.j("meme");
        this.a.j("rank");
        this.a.j("evaluatecenter");
        this.a.j("shareorder");
        this.a.j(JumpUtil.VALUE_DES_COMMUNE);
        this.a.j(DeepLinkFavouritesHelper.HOST_FAVOURITES);
        this.a.j(DeepLinkBrowserHistoryHelper.HOST_BROWSERHISTORY);
        this.a.j(JumpUtil.VALUE_DES_ENJOYBUY);
        this.a.j("intelligentassistance");
        this.a.j("mystreet");
        this.a.j("mywallet");
        this.a.j(DeepLinkLiveHelper.HOST_LIVE_LIST);
        this.a.j("jshopcustom");
        this.a.j("mycard");
        this.a.j("mycalendar");
        this.a.j("goodstuff");
        this.a.j(DeepLinkLiveHelper.HOST_LIVE);
        this.a.j("story");
        this.a.j("jdpaycode");
        this.a.j("jdmade");
        this.a.j("jdcustomchannel");
        this.a.j("shopattention");
        this.a.j("jdfan");
        this.a.j("threedtryclothes");
        this.a.j("tryclothes");
        this.a.j(JumpUtil.VALUE_DES_AR_MAKEUP);
        this.a.j("facerecognition");
        this.a.j("liveverification");
        this.a.j(DeepLinkBingHelper.HOST_BING);
        this.a.j(JumpUtil.VALUE_DES_MATRIXAR);
        this.a.j("jdflutter");
        this.a.j("insightar");
        this.a.j(JumpUtil.VALUE_DES_ARBOOK);
        this.a.j("uphone");
        this.a.j("developermode");
        this.a.j(JumpUtil.VALUE_DES_ARMART);
        this.a.j("brandshangou");
        this.a.j(JumpUtil.VAULE_DES_DISCOVERY);
        this.a.j("DiscoverAttention");
        this.a.j("cashier");
        this.a.j(JumpUtil.VALUE_DES_DOLPHIN);
        this.a.j("JDCashReward");
        this.a.j("NewProduct");
        this.a.j("com.jd.lib.jdfriend");
        this.a.j("com.jd.lib.mocklocation");
        this.a.j("com.jd.lib.JDBusinessAddress");
        this.a.j("com.jd.lib.RankingList");
        this.a.j("arclothes");
        this.a.j("virtualhuman");
        this.a.j("jdlivelistlib");
        this.a.j("cronet");
        this.a.j("newarrivals");
        this.a.j("productdetailfeeds");
        this.a.j("omnichannelsearch");
        this.a.j("order_center_card");
        this.a.j("netvirta");
        this.a.j("jdt-dcep");
        this.a.j(JumpUtil.VALUE_DES_HOURLY_GO);
        this.a.j("order_unit");
        this.a.j("ISVPdHourShop");
        this.a.j("ISVHourRecommend");
        this.a.j("risk_bankcard_ocr");
        this.a.j("productdetailmini");
        this.a.j("IsvSttlement");
        this.a.j("device_model_compute");
        this.a.j("idcard_nfc_verify");
        this.a.j("mMonitor");
        this.a.j("idcard_ocr_verify");
        this.a.j("newcategory");
        this.a.j("edge");
    }

    @Override // com.jingdong.jdsdk.auraSetting.d
    public String[] a() {
        return this.a.a();
    }

    @Override // com.jingdong.jdsdk.auraSetting.d
    public long b() {
        return this.a.b();
    }

    @Override // com.jingdong.jdsdk.auraSetting.d
    public long c(long j2) {
        return this.a.c(j2);
    }

    @Override // com.jingdong.jdsdk.auraSetting.d
    public long d(int i2) {
        return this.a.d(i2);
    }

    @Override // com.jingdong.jdsdk.auraSetting.d
    public long e(String str) {
        return this.a.e(str);
    }

    @Override // com.jingdong.jdsdk.auraSetting.d
    public long f() {
        return this.a.f();
    }

    @Override // com.jingdong.jdsdk.auraSetting.d
    public String g(long j2) {
        return this.a.g(j2);
    }

    @Override // com.jingdong.jdsdk.auraSetting.d
    public List<String> getBundleDownloadOrder() {
        return this.a.getBundleDownloadOrder();
    }

    @Override // com.jingdong.jdsdk.auraSetting.d
    public String getBundleNameFromBundleId(int i2) {
        return this.a.getBundleNameFromBundleId(i2);
    }

    @Override // com.jingdong.jdsdk.auraSetting.d
    public String getBundleNameFromUpdateID(String str) {
        return this.a.getBundleNameFromUpdateID(str);
    }

    @Override // com.jingdong.jdsdk.auraSetting.d
    public String getUpdateIdFromBundleName(String str) {
        return this.a.getUpdateIdFromBundleName(str);
    }

    @Override // com.jingdong.jdsdk.auraSetting.d
    public long h(long j2) {
        return this.a.h(j2);
    }

    @Override // com.jingdong.jdsdk.auraSetting.d
    public Set<String> i() {
        return this.a.i();
    }
}
