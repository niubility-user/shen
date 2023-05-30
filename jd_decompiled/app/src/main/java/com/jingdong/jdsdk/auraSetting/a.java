package com.jingdong.jdsdk.auraSetting;

import com.jingdong.common.cart.CartBaseUtil;
import com.jingdong.common.deeplinkhelper.DeepLinkBrowserHistoryHelper;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.login.LoginConstans;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import java.util.List;
import java.util.Set;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* loaded from: classes14.dex */
class a implements d {
    private static a b;
    private b a;

    private a() {
    }

    public static synchronized a j() {
        a aVar;
        synchronized (a.class) {
            if (b == null) {
                a aVar2 = new a();
                b = aVar2;
                aVar2.k();
            }
            aVar = b;
        }
        return aVar;
    }

    private void k() {
        b bVar = new b();
        this.a = bVar;
        bVar.l(b.f12873i, 4611686018427387903L);
        this.a.l(b.f12876l, 4611686018427387903L);
        this.a.l(b.f12877m, 0L);
        this.a.k(0, "BUNDLENAME_TOTAL", 1L, "BUNDLE_UPDATE_ID_TOTAL");
        this.a.k(41, "com.jingdong.global.bundles.login", 8796093022208L, LoginConstans.FREGMENT_LOGIN_FLAG);
        this.a.k(16, "com.jingdong.global.bundles.jshop", IjkMediaMeta.AV_CH_TOP_FRONT_RIGHT, "jshop");
        this.a.k(21, "com.jingdong.global.bundles.evaluatecenter", 524288L, "evaluatecenter");
        this.a.k(12, "com.jingdong.global.bundles.search", 1024L, "search");
        this.a.k(26, "com.jingdong.global.bundles.category", 33554432L, "category");
        this.a.k(9, "com.jingdong.global.bundles.productdetail", 128L, "productdetail");
        this.a.k(45, "com.jingdong.global.bundles.cart", 140737488355328L, "cart");
        this.a.k(24, "com.jingdong.global.bundles.browserhistory", 8388608L, DeepLinkBrowserHistoryHelper.HOST_BROWSERHISTORY);
        this.a.k(34, "com.jingdong.global.bundles.usermanager", IjkMediaMeta.AV_CH_SURROUND_DIRECT_RIGHT, "usermanager");
        this.a.k(39, "com.jingdong.global.bundles.setting", 2199023255552L, ThemeTitleConstant.TITLE_SETTING_DRAWABLE_ID);
        this.a.k(55, "com.jingdong.global.bundles.settlement", 144115188075855872L, CartBaseUtil.COMBINEORDER_USER_SETTLEMENT);
        this.a.k(58, "com.jingdong.global.bundles.personal", 1152921504606846976L, "personal");
        this.a.k(13, "com.jingdong.global.bundles.scan", 2048L, "scan");
        this.a.k(29, "com.jingdong.global.bundles.address", 268435456L, ThemeTitleConstant.TITLE_ADDRESS_DRAWABLE_ID);
        this.a.k(18, "com.jingdong.global.bundles.ordercenter", IjkMediaMeta.AV_CH_TOP_BACK_CENTER, "ordercenter");
        this.a.k(17, "com.jingdong.global.bundles.mycoupon", IjkMediaMeta.AV_CH_TOP_BACK_LEFT, JumpUtil.VALUE_DES_COUPON);
        this.a.k(54, "com.jingdong.global.bundles.push", 72057594037927936L, "push");
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
