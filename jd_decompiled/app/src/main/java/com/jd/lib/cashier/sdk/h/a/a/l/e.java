package com.jd.lib.cashier.sdk.h.a.a.l;

import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpConfig;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpSetting;
import com.jd.lib.cashier.sdk.core.utils.o;
import com.jd.lib.cashier.sdk.pay.bean.CloseBottomRecommendInfo;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public abstract class e extends com.jd.lib.cashier.sdk.d.f.a<com.jd.lib.cashier.sdk.h.f.e, CloseBottomRecommendInfo> {
    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: k  reason: merged with bridge method [inline-methods] */
    public void c(@Nullable IHttpSetting iHttpSetting, @Nullable com.jd.lib.cashier.sdk.h.f.e eVar) {
        if (iHttpSetting == null || eVar == null) {
            return;
        }
        iHttpSetting.setFunctionId("platCloseBottomRecommend");
        iHttpSetting.putJsonParam("channelId", eVar.a());
        iHttpSetting.putJsonParam("recommendDesc", eVar.b());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.lib.cashier.sdk.d.f.a
    @NotNull
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public CloseBottomRecommendInfo d(@Nullable String str) {
        if (!TextUtils.isEmpty(str)) {
            CloseBottomRecommendInfo closeBottomRecommendInfo = (CloseBottomRecommendInfo) o.a(str, CloseBottomRecommendInfo.class);
            return closeBottomRecommendInfo != null ? closeBottomRecommendInfo : new CloseBottomRecommendInfo();
        }
        return new CloseBottomRecommendInfo();
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    @NotNull
    /* renamed from: m  reason: merged with bridge method [inline-methods] */
    public CloseBottomRecommendInfo i(@Nullable String str) {
        Object a = o.a(str, CloseBottomRecommendInfo.class);
        Intrinsics.checkExpressionValueIsNotNull(a, "CashierJsonParser.parseJ\u2026ecommendInfo::class.java)");
        return (CloseBottomRecommendInfo) a;
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.http.HttpListener
    public void onReady(@Nullable IHttpConfig iHttpConfig) {
    }
}
