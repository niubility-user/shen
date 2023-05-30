package jd.wjlogin_sdk.net;

import android.text.TextUtils;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.sdk.platform.business.personal.R2;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import jd.wjlogin_sdk.model.IpModel;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class a {
    private static final String b = "wlogin.m.jd.com";

    /* renamed from: c  reason: collision with root package name */
    private static final String f19843c = "ccf.m.jd.com";
    private Map<String, List<IpModel>> a;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static class b {
        private static a a = new a();

        private b() {
        }
    }

    /* loaded from: classes11.dex */
    public static class c {
        public String a;
        public String b;

        /* renamed from: c  reason: collision with root package name */
        public String f19844c;
        public String d;

        /* renamed from: e  reason: collision with root package name */
        public boolean f19845e;
    }

    private String a(int i2, int i3, int i4, int i5) {
        return i2 + OrderISVUtil.MONEY_DECIMAL + i3 + OrderISVUtil.MONEY_DECIMAL + i4 + OrderISVUtil.MONEY_DECIMAL + i5;
    }

    private void b() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(IpModel.create(b, a(211, 144, 24, 150)));
        arrayList.add(IpModel.create(b, a(120, 52, R2.anim.mtrl_bottom_sheet_slide_in, 150)));
        arrayList.add(IpModel.create(b, a(106, 39, 169, 150)));
        arrayList.add(IpModel.create(b, a(36, 110, R2.anim.push_right_out, 150)));
        arrayList.add(IpModel.create(b, a(111, 13, 29, 150)));
        arrayList.add(IpModel.create(b, a(111, 13, R2.anim.mtrl_bottom_sheet_slide_out, 247)));
        this.a.put(b, arrayList);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(IpModel.create(f19843c, a(211, 144, 24, 150)));
        arrayList2.add(IpModel.create(f19843c, a(120, 52, R2.anim.mtrl_bottom_sheet_slide_in, 150)));
        arrayList2.add(IpModel.create(f19843c, a(106, 39, 169, 150)));
        arrayList2.add(IpModel.create(f19843c, a(36, 110, R2.anim.push_right_out, 150)));
        arrayList2.add(IpModel.create(f19843c, a(111, 13, 29, 150)));
        this.a.put(f19843c, arrayList2);
    }

    public static c c(String str) {
        IpModel b2;
        c cVar = new c();
        if (TextUtils.isEmpty(str)) {
            return cVar;
        }
        try {
            String host = new URL(str).getHost();
            if (!TextUtils.isEmpty(host) && (b2 = a().b(host)) != null && !TextUtils.isEmpty(b2.master)) {
                cVar.a = str;
                cVar.b = str.replaceFirst(host, b2.master);
                cVar.f19844c = host;
                cVar.d = b2.master;
                cVar.f19845e = true;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return cVar;
    }

    private a() {
        this.a = new HashMap();
        b();
    }

    private IpModel a(String str) {
        List<IpModel> list;
        if (TextUtils.isEmpty(str) || (list = this.a.get(str)) == null || list.isEmpty()) {
            return null;
        }
        try {
            return list.get(new Random(System.currentTimeMillis()).nextInt(list.size()));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static boolean c() {
        return jd.wjlogin_sdk.config.a.c().k();
    }

    private static a a() {
        return b.a;
    }

    private IpModel b(String str) {
        IpModel a = jd.wjlogin_sdk.common.h.a.a(str);
        if (a == null || TextUtils.isEmpty(a.master)) {
            IpModel a2 = a(str);
            if (a2 == null || !TextUtils.isEmpty(a2.master)) {
            }
            return a2;
        }
        return a;
    }
}
