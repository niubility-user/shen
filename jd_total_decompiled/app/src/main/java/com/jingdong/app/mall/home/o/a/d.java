package com.jingdong.app.mall.home.o.a;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.ad.ADActivity;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.unification.navigationbar.NavigationBase;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.web.xrender.XRender;
import com.jingdong.jdsdk.JdSdk;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes4.dex */
public class d {
    public static boolean a;
    public static Set<String> b;

    /* renamed from: c */
    public static String f10451c;
    private static boolean d;

    /* renamed from: e */
    private static final String[] f10452e = {"BroadcastReceiver", "Service", "MainActivity", "WebBzActivity", "AppLinkActivity", "JDTransferActivity", "JDMAHelperActivity", "WXPayEntryActivity", "WXEntryActivity", "MessageNotificationActivity", "InterfaceActivity"};

    /* renamed from: f */
    private static JDJSONObject f10453f;

    /* renamed from: g */
    private static JDJSONArray f10454g;

    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g */
        final /* synthetic */ JDJSONObject f10455g;

        a(JDJSONObject jDJSONObject) {
            this.f10455g = jDJSONObject;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            d.f(this.f10455g);
        }
    }

    /* loaded from: classes4.dex */
    public class b extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g */
        final /* synthetic */ String f10456g;

        b(String str) {
            this.f10456g = str;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            XRender.getInstance().setRenderUrl(this.f10456g);
        }
    }

    public static boolean b(String str) {
        boolean z;
        Set<String> set = b;
        if (set != null && !set.isEmpty()) {
            if (!TextUtils.isEmpty(str) && !TextUtils.equals("0", str)) {
                if (TextUtils.equals("1", str)) {
                    z = b.contains("2");
                } else if (TextUtils.equals("2", str)) {
                    z = b.contains("3");
                } else {
                    z = TextUtils.equals("3", str) ? b.contains("4") : false;
                }
            } else {
                z = b.contains("1");
            }
        } else {
            z = a;
        }
        f.r0("NAV_BUBBLE", "floatMutex =" + f10451c + " ,uiStyle =" + str);
        StringBuilder sb = new StringBuilder();
        sb.append("float icon need mutex: ");
        sb.append(z);
        f.r0("NAV_BUBBLE", sb.toString());
        return z;
    }

    public static void c() {
        if (a) {
            if (!d) {
                g();
            } else if (NavigationBase.getInstance().getBubbleState() == 1) {
            } else {
                f.r0("NAV_BUBBLE", "invoke show bubble,", " state: " + NavigationBase.getInstance().handleBubble(true));
            }
        }
    }

    private static void d(JDJSONObject jDJSONObject, JDJSONArray jDJSONArray) {
        int size = jDJSONArray.size();
        if (size <= 0) {
            return;
        }
        String jSONString = jDJSONObject.toJSONString();
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = jDJSONArray.get(i2);
            if (obj instanceof String) {
                String str = (String) obj;
                if (jSONString.contains(str)) {
                    f.E0(new b(str));
                }
            }
        }
        k.e("preLoadUrl = ".concat(jDJSONArray.toString()));
    }

    public static void e(JDJSONObject jDJSONObject, boolean z) {
        if (z) {
            return;
        }
        com.jingdong.app.mall.home.w.a.b(new a(jDJSONObject), false);
    }

    public static void f(JDJSONObject jDJSONObject) {
        Object remove = jDJSONObject.remove("preLoadUrls");
        if (!(remove instanceof JDJSONArray)) {
            f10454g = null;
            return;
        }
        JDJSONArray jDJSONArray = (JDJSONArray) remove;
        f10454g = jDJSONArray;
        f10453f = jDJSONObject;
        d(jDJSONObject, jDJSONArray);
    }

    public static void g() {
        if (a) {
            f.r0("NAV_BUBBLE", "invoke close bubble,", " state: " + NavigationBase.getInstance().handleBubble(false));
        }
    }

    public static void h() {
        d = true;
        String str = TextUtils.isEmpty(f10451c) ? "" : f10451c;
        f10451c = str;
        b = TextUtils.isEmpty(str) ? new HashSet() : new HashSet(Arrays.asList(f10451c.split(DYConstants.DY_REGEX_COMMA)));
    }

    private static boolean i(String str) {
        for (String str2 : f10452e) {
            if (str.contains(str2)) {
                return true;
            }
        }
        return false;
    }

    public static void j() {
        d = false;
        f.r0("NAV_BUBBLE", "mutex, no need bubble");
    }

    public static void k() {
        JDJSONArray jDJSONArray;
        JDJSONObject jDJSONObject = f10453f;
        if (jDJSONObject == null || (jDJSONArray = f10454g) == null) {
            return;
        }
        d(jDJSONObject, jDJSONArray);
    }

    public static void l(long j2) {
        int switchIntValue;
        com.jingdong.app.mall.ad.d i2;
        IMyActivity currentMyActivity;
        Activity thisActivity;
        if (!com.jingdong.app.mall.home.c.f().h() && (switchIntValue = SwitchQueryFetcher.getSwitchIntValue("mp_popup_start_image", 0)) >= 1 && JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplicationContext()) && j2 != 0 && j2 + (switchIntValue * 60000) <= System.currentTimeMillis() && (i2 = com.jingdong.app.mall.ad.c.l().i()) != null) {
            int i3 = i2.d;
            if ((i3 != 0 && i3 != 3) || i2.f7910k.size() != 1 || (currentMyActivity = BaseFrameUtil.getInstance().getCurrentMyActivity()) == null || (thisActivity = currentMyActivity.getThisActivity()) == null || thisActivity.isFinishing() || i(thisActivity.toString())) {
                return;
            }
            try {
                thisActivity.startActivity(new Intent(thisActivity, ADActivity.class));
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}
