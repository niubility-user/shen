package com.jingdong.app.mall.home.tips;

import android.content.Context;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes4.dex */
public class c extends com.jingdong.app.mall.home.r.e.b {

    /* renamed from: n  reason: collision with root package name */
    public static final SimpleDateFormat f10927n = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    private String a;
    private int b;

    /* renamed from: c  reason: collision with root package name */
    private int f10928c;
    private int d;

    /* renamed from: e  reason: collision with root package name */
    private int f10929e;

    /* renamed from: f  reason: collision with root package name */
    private int f10930f;

    /* renamed from: g  reason: collision with root package name */
    private int f10931g;

    /* renamed from: h  reason: collision with root package name */
    private String f10932h;

    /* renamed from: i  reason: collision with root package name */
    private String f10933i;

    /* renamed from: j  reason: collision with root package name */
    private JumpEntity f10934j;

    /* renamed from: k  reason: collision with root package name */
    private String f10935k;

    /* renamed from: l  reason: collision with root package name */
    private String f10936l;

    /* renamed from: m  reason: collision with root package name */
    private String f10937m;

    public c(JDJSONObject jDJSONObject) {
        super(jDJSONObject);
        this.a = getJsonString("img");
        this.f10931g = getJsonInt("tipsShowType", 0);
        this.b = getJsonInt("tipsStyle", -1);
        this.d = getJsonInt("tipsIdleTime", -1);
        this.f10928c = getJsonInt("tipsShowTime", 8);
        this.f10929e = getJsonInt("tipsShowScene", 0);
        this.f10930f = getJsonInt("tipsShowInterval", 0);
        this.f10932h = getJsonString("expoJson", "");
        this.f10935k = getJsonString("expoLog");
        this.f10936l = getJsonString("clkLog");
        this.f10937m = getJsonString("closeLog");
        JDJSONObject jsonObject = getJsonObject("jump");
        if (jsonObject != null) {
            JumpEntity jumpEntity = (JumpEntity) jsonObject.toJavaObject(JumpEntity.class);
            this.f10934j = jumpEntity;
            this.f10933i = jumpEntity.srvJson;
        } else {
            this.f10934j = null;
            this.f10933i = "";
        }
        l();
    }

    public static int j() {
        String u = f.u("home tips show date", "1970-01-01 00:00:00");
        if (u != null && u.length() > 10) {
            u = u.substring(0, 10);
        }
        String substring = f10927n.format(new Date(System.currentTimeMillis())).substring(0, 10);
        if (TextUtils.isEmpty(u) || !substring.equals(u)) {
            f.w0("show times", 0);
            f.x0("home tips show date", "1970-01-01 00:00:00");
        }
        return f.t("show times", 0);
    }

    private void l() {
        e.z(this.a, null);
    }

    public int a() {
        return this.d;
    }

    public String b() {
        return this.f10932h;
    }

    public String c() {
        return this.f10935k;
    }

    public String d() {
        return this.a;
    }

    public int e() {
        return this.f10929e;
    }

    public long f() {
        int i2 = this.f10928c;
        if (i2 <= 0) {
            i2 = 8;
        }
        return i2 * 1000;
    }

    public int g() {
        return this.f10931g;
    }

    public JumpEntity getJump() {
        return this.f10934j;
    }

    public String h() {
        return this.f10933i;
    }

    public int i() {
        return this.b;
    }

    public boolean k() {
        if (this.f10930f <= 0) {
            return true;
        }
        long j2 = 0;
        try {
            j2 = (new Date(System.currentTimeMillis()).getTime() - f10927n.parse(f.u("home tips show date", "1970-01-01 00:00:00")).getTime()) / 60000;
        } catch (ParseException e2) {
            e2.printStackTrace();
        }
        return j2 > ((long) this.f10930f);
    }

    public void m(Context context) {
        n(context, this.f10933i);
    }

    public void n(Context context, String str) {
        new com.jingdong.app.mall.home.q.a("Tips\u70b9\u51fb", true, this.f10936l).b();
        p(context, "Home_Tips", str);
    }

    public void o(Context context) {
        new com.jingdong.app.mall.home.q.a("Tips\u5173\u95ed", true, this.f10937m).b();
        p(context, "Home_TipsClose", this.f10933i);
    }

    public void p(Context context, String str, String str2) {
        JDMtaUtils.sendClickDataWithExt(context, str, "", "", RecommendMtaUtils.Home_PageId, com.jingdong.app.mall.home.r.c.a.f10642k, "", "", str2, null);
    }
}
