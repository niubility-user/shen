package com.jingdong.app.mall.home.floor.common.i;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import androidx.annotation.IntRange;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.activity.MainRightWebActivity;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor;
import com.jingdong.app.mall.miaosha.MiaoShaPublicConstants;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.login.LoginConstans;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class l {
    public static String a = "";
    public static String b = "Home_Floor";

    /* renamed from: c */
    private static long f9317c;
    private static long d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements View.OnClickListener {

        /* renamed from: g */
        final /* synthetic */ com.jingdong.app.mall.home.r.e.f f9318g;

        /* renamed from: h */
        final /* synthetic */ BaseMallFloor f9319h;

        /* renamed from: i */
        final /* synthetic */ String f9320i;

        /* renamed from: j */
        final /* synthetic */ String f9321j;

        /* renamed from: k */
        final /* synthetic */ String f9322k;

        a(com.jingdong.app.mall.home.r.e.f fVar, BaseMallFloor baseMallFloor, String str, String str2, String str3) {
            this.f9318g = fVar;
            this.f9319h = baseMallFloor;
            this.f9320i = str;
            this.f9321j = str2;
            this.f9322k = str3;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.f9318g == null || this.f9319h == null || l.k()) {
                return;
            }
            l.o(this.f9319h.getContext(), this.f9319h, this.f9320i, this.f9321j, "", this.f9322k, this.f9318g.getJump(), new String[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b implements View.OnClickListener {

        /* renamed from: g */
        final /* synthetic */ BaseMallFloor f9323g;

        /* renamed from: h */
        final /* synthetic */ com.jingdong.app.mall.home.r.e.f f9324h;

        /* renamed from: i */
        final /* synthetic */ String f9325i;

        /* renamed from: j */
        final /* synthetic */ String f9326j;

        /* renamed from: k */
        final /* synthetic */ int f9327k;

        b(BaseMallFloor baseMallFloor, com.jingdong.app.mall.home.r.e.f fVar, String str, String str2, int i2) {
            this.f9323g = baseMallFloor;
            this.f9324h = fVar;
            this.f9325i = str;
            this.f9326j = str2;
            this.f9327k = i2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.f9323g == null || l.k()) {
                return;
            }
            this.f9323g.postUrl(this.f9324h.e());
            JumpEntity jump = this.f9324h.getJump();
            String o = this.f9324h.o();
            if (jump == null) {
                return;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("extension_id", o);
            l.onClickJsonEvent(this.f9323g.getContext(), jump, this.f9325i, this.f9326j, jump.getSrvJson(), this.f9327k, hashMap);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c implements View.OnClickListener {

        /* renamed from: g */
        final /* synthetic */ BaseMallFloor f9328g;

        /* renamed from: h */
        final /* synthetic */ com.jingdong.app.mall.home.r.e.f f9329h;

        /* renamed from: i */
        final /* synthetic */ String f9330i;

        /* renamed from: j */
        final /* synthetic */ String f9331j;

        /* renamed from: k */
        final /* synthetic */ String f9332k;

        /* renamed from: l */
        final /* synthetic */ String f9333l;

        /* renamed from: m */
        final /* synthetic */ int f9334m;

        c(BaseMallFloor baseMallFloor, com.jingdong.app.mall.home.r.e.f fVar, String str, String str2, String str3, String str4, int i2) {
            this.f9328g = baseMallFloor;
            this.f9329h = fVar;
            this.f9330i = str;
            this.f9331j = str2;
            this.f9332k = str3;
            this.f9333l = str4;
            this.f9334m = i2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.f9328g == null || l.k()) {
                return;
            }
            this.f9328g.postUrl(this.f9329h.e());
            JumpEntity jump = this.f9329h.getJump();
            String o = this.f9329h.o();
            if (jump == null) {
                return;
            }
            l.n(this.f9328g.getContext(), this, this.f9330i, this.f9331j, this.f9332k, this.f9333l, jump, this.f9334m, "extension_id", o);
        }
    }

    public static void a(JumpEntity jumpEntity) {
        b(jumpEntity, "ignoreSku", "1");
    }

    public static void b(JumpEntity jumpEntity, String str, String str2) {
        if (jumpEntity == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        jumpEntity.addParam(str, str2);
        if (Log.D) {
            Log.d("MallFloorClickUtil", "jump: " + jumpEntity.toJsonString());
        }
    }

    public static void c(JumpEntity jumpEntity, String str) {
        b(jumpEntity, "innerSkuImgUrl", str);
    }

    public static void d(Context context, JumpEntity jumpEntity, int i2) {
        if (i2 != -1) {
            if (i2 == 0) {
                i2 = 1;
            }
            r(jumpEntity, i2);
        }
        e(context, jumpEntity);
    }

    public static boolean e(Context context, JumpEntity jumpEntity) {
        if (i() || jumpEntity == null) {
            return false;
        }
        JumpUtil.execJump(context, jumpEntity, 1);
        return true;
    }

    public static String f() {
        String[] split = a.split(CartConstant.KEY_YB_INFO_LINK);
        return split.length >= 1 ? split[0] : "";
    }

    public static void g(String str) {
        if (TextUtils.split(str, CartConstant.KEY_YB_INFO_LINK).length <= 1) {
            a = str.concat(CartConstant.KEY_YB_INFO_LINK);
        } else {
            a = str;
        }
    }

    public static boolean h() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - d > 600) {
            d = elapsedRealtime;
            return false;
        }
        return true;
    }

    private static boolean i() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - f9317c > 600) {
            f9317c = elapsedRealtime;
            return false;
        }
        return true;
    }

    public static boolean j(Context context) {
        if (MainRightWebActivity.S()) {
            String switchStringValue = SwitchQueryFetcher.getSwitchStringValue("mp_magic_switch", "");
            if (com.jingdong.app.mall.home.o.a.f.k0() && !TextUtils.isEmpty(switchStringValue)) {
                String str = g.o;
                String portalHost = Configuration.getPortalHost();
                if (TextUtils.isEmpty(str)) {
                    str = "10235";
                }
                if (TextUtils.equals(portalHost, "api.m.jd.care")) {
                    str = "11413";
                }
                com.jingdong.app.mall.home.d.b(str, true);
            } else {
                m(context, true);
            }
            return true;
        }
        return false;
    }

    public static boolean k() {
        return !NetUtils.isNetworkAvailable();
    }

    public static void l(Context context) {
        m(context, false);
    }

    public static void m(Context context, boolean z) {
        try {
            Bundle bundle = new Bundle();
            bundle.putInt(LoginConstans.NEED_REFRESH_MODE, LoginConstans.REFRESH_MODE_VALUE);
            if (z) {
                bundle.putString("foldFlag", "foldSecondPage");
            }
            DeepLinkLoginHelper.startLoginActivity(context, bundle);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void n(Context context, Object obj, String str, String str2, String str3, String str4, JumpEntity jumpEntity, int i2, String... strArr) {
        String str5 = jumpEntity.params;
        r(jumpEntity, i2);
        if (Log.D) {
            Log.d("MallFloorClickUtil", "jump " + jumpEntity.toJsonString());
        }
        o(context, obj, str, str2, str3, str4, jumpEntity, strArr);
        jumpEntity.params = str5;
    }

    public static void o(Context context, Object obj, String str, String str2, String str3, String str4, JumpEntity jumpEntity, String... strArr) {
        e(context, jumpEntity);
        String str5 = jumpEntity == null ? "" : jumpEntity.des;
        String s = (TextUtils.isEmpty(str4) || !b.equals(str4)) ? str3 : s(str3);
        try {
            if ("m".equals(str5) && "Home_FloatingFloor".equals(str4)) {
                JDMtaUtils.sendCommonData(context, str4, str, "", obj, s, "WebActivity", "", RecommendMtaUtils.Home_PageId);
            } else {
                JDMtaUtils.sendCommonDataWithExtParam(context, str4, str, "", obj, s, "", "", RecommendMtaUtils.Home_PageId, strArr);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void onClickJsonEvent(Context context, JumpEntity jumpEntity, String str, String str2, String str3) {
        onClickJsonEvent(context, jumpEntity, str, str2, str3, 0);
    }

    public static void p(View view, JumpEntity jumpEntity, com.jingdong.app.mall.home.r.c.b bVar, String str, @IntRange(from = 0, to = 10) int i2, @IntRange(from = 1, to = 10) int i3) {
        if (view == null || jumpEntity == null || k()) {
            return;
        }
        if (bVar == null) {
            bVar = com.jingdong.app.mall.home.r.c.b.c(jumpEntity.getSrvJson());
        }
        com.jingdong.app.mall.home.r.c.b a2 = com.jingdong.app.mall.home.floor.common.h.a.a(view, bVar);
        a2.put("skuposition", String.valueOf(i2));
        String jSONObject = a2.toString();
        c(jumpEntity, str);
        onClickJsonEvent(view.getContext(), jumpEntity, "", jumpEntity.getSrv(), jSONObject, i3);
    }

    public static void q(View view, JumpEntity jumpEntity, String str, @IntRange(from = 0, to = 10) int i2, @IntRange(from = 1, to = 10) int i3) {
        p(view, jumpEntity, null, str, i2, i3);
    }

    private static void r(JumpEntity jumpEntity, int i2) {
        Object paramValue;
        int max = Math.max(i2, 1);
        jumpEntity.addParam(MiaoShaPublicConstants.MIAO_SHA_INNER_INDEX, Integer.valueOf(max));
        Object paramValue2 = jumpEntity.getParamValue("param");
        if (paramValue2 instanceof JDJSONObject) {
            JDJSONObject jDJSONObject = (JDJSONObject) paramValue2;
            jDJSONObject.put(MiaoShaPublicConstants.MIAO_SHA_INNER_INDEX, (Object) Integer.valueOf(max));
            jumpEntity.addParam("param", jDJSONObject);
        }
        String str = jumpEntity.des;
        if (str == null || !str.equalsIgnoreCase("m") || (paramValue = jumpEntity.getParamValue("url")) == null) {
            return;
        }
        try {
            jumpEntity.addParam("url", Uri.parse(String.valueOf(paramValue)).buildUpon().appendQueryParameter(MiaoShaPublicConstants.MIAO_SHA_INNER_INDEX, String.valueOf(max)).build().toString());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static String s(String str) {
        String concat = a.concat(CartConstant.KEY_YB_INFO_LINK).concat(str);
        String[] split = TextUtils.split(concat, CartConstant.KEY_YB_INFO_LINK);
        if (split.length <= 2) {
            return concat.concat(CartConstant.KEY_YB_INFO_LINK).concat(CartConstant.KEY_YB_INFO_LINK);
        }
        return split.length == 3 ? concat.concat(CartConstant.KEY_YB_INFO_LINK) : concat;
    }

    public static void t(JumpEntity jumpEntity) {
        u(jumpEntity, "ignoreSku");
    }

    public static void u(JumpEntity jumpEntity, String str) {
        if (jumpEntity == null || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JDJSONObject jDJSONObject = (JDJSONObject) JDJSON.parse(jumpEntity.params);
            if (jDJSONObject != null) {
                jDJSONObject.remove(str);
                jumpEntity.params = jDJSONObject.toJSONString();
            }
        } catch (Exception e2) {
            OKLog.e("MallFloorClickUtil", e2);
        }
    }

    public static void v(BaseMallFloor baseMallFloor, View view, com.jingdong.app.mall.home.r.e.f fVar, int i2) {
        if (fVar == null || view == null) {
            return;
        }
        String b0 = fVar.b0();
        JDJSONArray V = fVar.V();
        view.setOnClickListener(new b(baseMallFloor, fVar, (V == null || V.size() <= 0 || i2 <= 0 || V.size() < i2) ? "" : fVar.U(i2 - 1), b0, i2));
    }

    public static void w(BaseMallFloor baseMallFloor, View view, com.jingdong.app.mall.home.r.e.f fVar, int i2, String str) {
        String str2;
        if (fVar == null || view == null) {
            return;
        }
        String b0 = fVar.b0();
        String H = fVar.H();
        JDJSONArray V = fVar.V();
        int i3 = i2 == 0 ? 1 : i2;
        if (V != null && V.size() > 0 && i2 > 0 && V.size() >= i2) {
            str2 = i2 + CartConstant.KEY_YB_INFO_LINK + fVar.U(i2 - 1);
        } else {
            str2 = i2 + CartConstant.KEY_YB_INFO_LINK;
        }
        view.setOnClickListener(new c(baseMallFloor, fVar, b0, H, str2, str, i3));
    }

    public static void x(BaseMallFloor baseMallFloor, View view, String str, String str2, String str3, com.jingdong.app.mall.home.r.e.f fVar) {
        if (view == null) {
            return;
        }
        view.setOnClickListener(new a(fVar, baseMallFloor, str, str2, str3));
    }

    public static void onClickJsonEvent(View view, JumpEntity jumpEntity, String str, String str2, String str3, int i2) {
        onClickJsonEvent(view.getContext(), jumpEntity, str, str2, com.jingdong.app.mall.home.floor.common.h.a.b(view, str3), i2, null);
    }

    public static void onClickJsonEvent(Context context, JumpEntity jumpEntity, String str, String str2, String str3, int i2) {
        onClickJsonEvent(context, jumpEntity, str, str2, str3, i2, null);
    }

    public static void onClickJsonEvent(Context context, JumpEntity jumpEntity, String str, String str2, String str3, int i2, HashMap<String, String> hashMap) {
        onClickJsonEvent(context, jumpEntity, b, str, str2, str3, i2, hashMap);
    }

    public static void onClickJsonEvent(Context context, JumpEntity jumpEntity, String str, String str2, String str3, String str4, int i2, HashMap<String, String> hashMap) {
        String str5 = jumpEntity.params;
        r(jumpEntity, i2);
        e(context, jumpEntity);
        jumpEntity.params = str5;
        JDMtaUtils.sendClickDataWithExt(JdSdk.getInstance().getApplicationContext(), str, str3, "", RecommendMtaUtils.Home_PageId, com.jingdong.app.mall.home.r.c.a.f10642k, s(String.valueOf(i2).concat(CartConstant.KEY_YB_INFO_LINK).concat(str2 == null ? "" : str2)), "", str4, hashMap);
    }
}
