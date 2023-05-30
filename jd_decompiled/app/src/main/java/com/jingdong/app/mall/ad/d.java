package com.jingdong.app.mall.ad;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.utils.CommonBase;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes19.dex */
public class d {
    public static final SimpleDateFormat D = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    public String A;
    public String B;
    private Date C;
    public int a;
    public int b;

    /* renamed from: c */
    public int f7903c;
    public int d;

    /* renamed from: e */
    public int f7904e;

    /* renamed from: f */
    public int f7905f;

    /* renamed from: g */
    public String f7906g;

    /* renamed from: h */
    public String f7907h;

    /* renamed from: i */
    public String f7908i;

    /* renamed from: j */
    public JumpEntity f7909j;

    /* renamed from: k */
    public ArrayList<b> f7910k = new ArrayList<>();

    /* renamed from: l */
    public String f7911l;

    /* renamed from: m */
    public String f7912m;

    /* renamed from: n */
    public String f7913n;
    public String o;
    public String p;
    public String q;
    public String r;
    public String s;
    public String t;
    public String u;
    public String v;
    public String w;
    public String x;
    public int y;
    public int z;

    public boolean a() {
        return TextUtils.equals(this.w, "0");
    }

    public boolean b() {
        Date date = this.C;
        return date == null || date.before(new Date());
    }

    public void c(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        this.b = jDJSONObject.optInt("time", 0);
        this.f7903c = jDJSONObject.optInt("ynSkip", 0);
        this.d = jDJSONObject.optInt("type", 0);
        this.f7904e = jDJSONObject.optInt("groupId", 0);
        this.f7905f = jDJSONObject.optInt("showTimes", 0);
        this.f7906g = jDJSONObject.optString("videoId", "");
        jDJSONObject.optInt("displaceStartTime", 0);
        jDJSONObject.optInt("displaceDirection", 4);
        jDJSONObject.optInt("displaceEnable", 0);
        this.u = jDJSONObject.optString("miniConflict", "0");
        this.q = jDJSONObject.optString("targetFloorType", "");
        this.r = jDJSONObject.optString("targetIdsStr", "");
        this.f7911l = jDJSONObject.optString("exposalUrl", "");
        this.f7912m = jDJSONObject.optString("clickUrl", "");
        this.f7913n = jDJSONObject.optString("expoLog", "");
        this.o = jDJSONObject.optString("clkLog", "");
        this.p = jDJSONObject.optString("closeLog", "");
        this.f7908i = jDJSONObject.optString("videoUrl", "");
        this.x = jDJSONObject.optString("extension_id", "");
        this.y = jDJSONObject.optInt("ynRedirect", 0);
        this.z = jDJSONObject.optInt("jumpButton", 0);
        this.A = jDJSONObject.optString("buttonText", "");
        this.B = jDJSONObject.optString("buttonImg", "");
        this.v = CommonBase.getStringFromPreference("start_image_ynDenoise", "0");
        this.a = CommonBase.getIntFromPreference("start_image_countdown", 0);
        CommonBase.getIntFromPreference("start_image_show_scan", 0);
        CommonBase.getIntFromPreference("start_image_saoasao_location", 1);
        CommonBase.getStringFromPreference("start_image_scan_url", "");
        try {
            this.f7909j = (JumpEntity) JDJSON.parseObject(jDJSONObject.optString("jump", ""), JumpEntity.class);
        } catch (Exception e2) {
            f.s0(this, e2);
        }
        if (TextUtils.isEmpty(this.f7906g)) {
            return;
        }
        String l2 = a.l(this.f7906g);
        if (TextUtils.isEmpty(l2) || !a.c(new File(l2), this.f7908i)) {
            return;
        }
        this.f7907h = l2;
    }

    public boolean d(JDJSONObject jDJSONObject) {
        Date date;
        if (jDJSONObject == null) {
            return false;
        }
        String optString = jDJSONObject.optString("onlineTime", "");
        String optString2 = jDJSONObject.optString("referralsTime", "");
        if (!TextUtils.isEmpty(optString) && !TextUtils.isEmpty(optString2)) {
            Date date2 = new Date();
            try {
                SimpleDateFormat simpleDateFormat = D;
                Date parse = simpleDateFormat.parse(optString);
                this.C = simpleDateFormat.parse(optString2);
                if (parse == null || parse.after(date2) || (date = this.C) == null || date.before(date2)) {
                    return false;
                }
                b bVar = null;
                try {
                    String optString3 = jDJSONObject.optString("url", "");
                    String l2 = a.l(optString3);
                    if (!TextUtils.isEmpty(l2) && new File(l2).exists()) {
                        bVar = new b(jDJSONObject.optString("sourceValue", ""), optString3, l2);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                if (bVar == null) {
                    return false;
                }
                this.w = jDJSONObject.optString("tag", "0");
                this.f7904e = jDJSONObject.optInt("groupId", 0);
                this.f7905f = jDJSONObject.optInt("showTimes", 0);
                this.f7910k.add(bVar);
                this.s = jDJSONObject.optString("sourceValue", "");
                this.t = jDJSONObject.optString("sourceValueJson", "");
                return true;
            } catch (ParseException e3) {
                e3.printStackTrace();
            }
        }
        return false;
    }
}
