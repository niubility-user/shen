package g.d.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import com.heytap.msp.push.callback.ICallBackResultService;
import com.heytap.msp.push.callback.IGetAppNotificationCallBackService;
import com.heytap.msp.push.callback.ISetAppNotificationCallBackService;
import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.jingdong.sdk.platform.business.personal.R2;
import com.tencent.mapsdk.internal.l4;
import g.d.a.g.d;
import g.d.a.h.c;
import g.d.a.j.h;
import g.h.a.a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class b {

    /* renamed from: k */
    private static final int[] f19406k = {99, 111, 109, 46, 99, 111, 108, 111, 114, 111, 115, 46, 109, 99, 115};

    /* renamed from: l */
    private static final int[] f19407l = {99, 111, 109, 46, 99, 111, 108, 111, 114, 111, 115, 46, 109, 99, 115, 115, 100, 107, 46, 97, 99, 116, 105, 111, 110, 46, 82, 69, 67, 69, 73, 86, 69, 95, 83, 68, 75, 95, 77, 69, 83, 83, 65, 71, 69};

    /* renamed from: m */
    private static String f19408m = "";

    /* renamed from: n */
    private static int f19409n = 0;
    private static String o;
    private static boolean p;
    private Context a;
    private List<c> b;

    /* renamed from: c */
    private List<d> f19410c;
    private String d;

    /* renamed from: e */
    private String f19411e;

    /* renamed from: f */
    private String f19412f;

    /* renamed from: g */
    private ICallBackResultService f19413g;

    /* renamed from: h */
    private ISetAppNotificationCallBackService f19414h;

    /* renamed from: i */
    private IGetAppNotificationCallBackService f19415i;

    /* renamed from: j */
    private ConcurrentHashMap<Integer, g.d.a.e.a> f19416j;

    /* loaded from: classes12.dex */
    public class a implements ServiceConnection {

        /* renamed from: g */
        final /* synthetic */ Intent f19417g;

        a(Intent intent) {
            b.this = r1;
            this.f19417g = intent;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Bundle bundle = new Bundle();
            bundle.putAll(this.f19417g.getExtras());
            try {
                a.AbstractBinderC0840a.d(iBinder).c(bundle);
            } catch (Exception e2) {
                g.d.a.j.d.a("bindMcsService exception:" + e2);
            }
            b.this.a.unbindService(this);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }
    }

    /* renamed from: g.d.a.b$b */
    /* loaded from: classes12.dex */
    public static class C0829b {
        private static final b a = new b(null);
    }

    /* synthetic */ b(a aVar) {
        this();
    }

    public static int I() {
        return R2.color.btn_white_color_pressed;
    }

    public static String J() {
        return "3.1.0";
    }

    private boolean N(Context context) {
        if (this.a == null) {
            this.a = context.getApplicationContext();
        }
        String u = u(this.a);
        return h.f(this.a, u) && h.c(this.a, u) >= 1019 && h.g(this.a, u, "supportOpenPush");
    }

    private void Y(int i2, String str, JSONObject jSONObject) {
        if (h(i2)) {
            ICallBackResultService iCallBackResultService = this.f19413g;
            if (iCallBackResultService != null) {
                iCallBackResultService.onError(r(i2), "api_call_too_frequently");
                return;
            }
            return;
        }
        try {
            this.a.startService(t(i2, str, jSONObject));
        } catch (Exception e2) {
            g.d.a.j.d.b("startMcsService--Exception" + e2.getMessage());
        }
    }

    private void Z(int i2, JSONObject jSONObject) {
        Y(i2, "", jSONObject);
    }

    private g.d.a.e.a b(int i2) {
        if (this.f19416j.containsKey(Integer.valueOf(i2))) {
            g.d.a.e.a aVar = this.f19416j.get(Integer.valueOf(i2));
            if (k(aVar)) {
                aVar.c(1);
                aVar.d(System.currentTimeMillis());
                g.d.a.j.d.a("addCommandToMap : appLimitBean.setCount(1)");
                return aVar;
            }
            aVar.c(aVar.a() + 1);
            g.d.a.j.d.a("addCommandToMap :appLimitBean.getCount() + 1");
            return aVar;
        }
        g.d.a.e.a aVar2 = new g.d.a.e.a(System.currentTimeMillis(), 1);
        this.f19416j.put(Integer.valueOf(i2), aVar2);
        g.d.a.j.d.a("addCommandToMap :appBean is null");
        return aVar2;
    }

    private synchronized void c(d dVar) {
        if (dVar != null) {
            this.f19410c.add(dVar);
        }
    }

    private synchronized void d(c cVar) {
        if (cVar != null) {
            this.b.add(cVar);
        }
    }

    private boolean g() throws IllegalArgumentException {
        return i() && j();
    }

    private boolean i() {
        return this.a != null;
    }

    private boolean j() {
        return this.f19412f != null;
    }

    private boolean k(g.d.a.e.a aVar) {
        long b = aVar.b();
        long currentTimeMillis = System.currentTimeMillis();
        g.d.a.j.d.a("checkTimeNeedUpdate : lastedTime " + b + " currentTime:" + currentTimeMillis);
        return currentTimeMillis - b > 1000;
    }

    public static b s() {
        return C0829b.a;
    }

    private Intent t(int i2, String str, JSONObject jSONObject) {
        Intent intent = new Intent();
        intent.setAction(F(this.a));
        intent.setPackage(u(this.a));
        intent.putExtra("type", i2);
        JSONObject jSONObject2 = new JSONObject();
        try {
            Context context = this.a;
            jSONObject2.putOpt("versionName", h.e(context, context.getPackageName()));
            Context context2 = this.a;
            jSONObject2.putOpt("versionCode", Integer.valueOf(h.c(context2, context2.getPackageName())));
            if (jSONObject != null) {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    jSONObject2.putOpt(next, jSONObject.get(next));
                }
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            intent.putExtra("extra", jSONObject2.toString());
            throw th;
        }
        intent.putExtra("extra", jSONObject2.toString());
        intent.putExtra("params", str);
        intent.putExtra("appPackage", this.a.getPackageName());
        intent.putExtra(PairKey.APP_KEY, this.d);
        intent.putExtra("appSecret", this.f19411e);
        intent.putExtra("registerID", this.f19412f);
        intent.putExtra(l4.f16791e, J());
        return intent;
    }

    private String v(Context context) {
        boolean z;
        boolean z2;
        if (TextUtils.isEmpty(f19408m)) {
            f19408m = new String(g.d.a.c.a.l("Y29tLm1jcy5hY3Rpb24uUkVDRUlWRV9TREtfTUVTU0FHRQ=="));
        }
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(new Intent(f19408m), 8192);
        if (Build.VERSION.SDK_INT >= 24) {
            Iterator<ResolveInfo> it = queryIntentServices.iterator();
            while (it.hasNext()) {
                String str = it.next().serviceInfo.packageName;
                try {
                    z = (context.getPackageManager().getApplicationInfo(str, 0).flags & 1) == 1;
                    z2 = context.getPackageManager().getPackageUid(str, 0) == context.getPackageManager().getPackageUid("android", 0);
                } catch (PackageManager.NameNotFoundException unused) {
                }
                if (z || z2) {
                    return str;
                }
            }
            return null;
        }
        return null;
    }

    public IGetAppNotificationCallBackService A() {
        return this.f19415i;
    }

    public ISetAppNotificationCallBackService B() {
        return this.f19414h;
    }

    public void C() {
        if (g()) {
            Z(12306, null);
        } else if (z() != null) {
            z().onGetPushStatus(-2, 0);
        }
    }

    public int D() {
        if (i()) {
            Context context = this.a;
            return h.c(context, u(context));
        }
        return 0;
    }

    public String E() {
        if (i()) {
            Context context = this.a;
            return h.e(context, u(context));
        }
        return "";
    }

    public String F(Context context) {
        if (o == null) {
            v(context);
        }
        if (p) {
            if (TextUtils.isEmpty(f19408m)) {
                f19408m = new String(g.d.a.c.a.l("Y29tLm1jcy5hY3Rpb24uUkVDRUlWRV9TREtfTUVTU0FHRQ=="));
            }
            return f19408m;
        }
        return h.a(f19407l);
    }

    public void G(JSONObject jSONObject) {
        if (i()) {
            Z(12289, jSONObject);
        } else if (z() != null) {
            z().onRegister(-2, null);
        }
    }

    public String H() {
        return this.f19412f;
    }

    public b K(Context context, boolean z) {
        if (context != null) {
            L(context);
            new g.d.a.d.a().c(this.a);
            g.d.a.j.d.d(z);
            return this;
        }
        throw new IllegalArgumentException("context can't be null");
    }

    public void L(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.a = applicationContext;
        if (o == null) {
            String v = v(applicationContext);
            if (v == null) {
                o = h.a(f19406k);
                p = false;
                return;
            }
            o = v;
            p = true;
        }
    }

    public boolean M(Context context) {
        return N(context);
    }

    public void O(JSONObject jSONObject) {
        if (g()) {
            Z(12310, jSONObject);
        } else {
            g.d.a.j.d.c("mcssdk---", "please call the register first!");
        }
    }

    public void P(JSONObject jSONObject) {
        if (g()) {
            Z(12299, jSONObject);
        } else {
            g.d.a.j.d.c("mcssdk---", "please call the register first!");
        }
    }

    public void Q(Context context, String str, String str2, JSONObject jSONObject, ICallBackResultService iCallBackResultService) {
        if (context == null) {
            if (iCallBackResultService != null) {
                iCallBackResultService.onRegister(-2, null);
                return;
            }
            return;
        }
        if (this.a == null) {
            this.a = context.getApplicationContext();
        }
        if (!h.h(this.a)) {
            if (iCallBackResultService != null) {
                iCallBackResultService.onRegister(-2, null);
                return;
            }
            return;
        }
        this.d = str;
        this.f19411e = str2;
        this.f19413g = iCallBackResultService;
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        try {
            jSONObject.putOpt("appVersionCode", Integer.valueOf(h.b(context)));
            jSONObject.putOpt("appVersionName", h.d(context));
        } catch (JSONException e2) {
            g.d.a.j.d.b("register-Exception:" + e2.getMessage());
        }
        Z(12289, jSONObject);
    }

    public void R() {
        if (i()) {
            e(12313);
        } else {
            g.d.a.j.d.c("mcssdk---", "please call the register first!");
        }
    }

    public void S(JSONObject jSONObject) {
        if (g()) {
            Z(12300, jSONObject);
        } else {
            g.d.a.j.d.c("mcssdk---", "please call the register first!");
        }
    }

    public void T(String str, String str2) {
        this.d = str;
        this.f19411e = str2;
    }

    public void U(int i2, JSONObject jSONObject) {
        if (g()) {
            Y(12307, i2 + "", jSONObject);
            return;
        }
        g.d.a.j.d.c("mcssdk---", "please call the register first!");
    }

    public void V(ICallBackResultService iCallBackResultService) {
        this.f19413g = iCallBackResultService;
    }

    public void W(List<Integer> list, int i2, int i3, int i4, int i5, JSONObject jSONObject) throws IllegalArgumentException {
        if (g()) {
            if (list != null && list.size() > 0 && i2 >= 0 && i3 >= 0 && i4 >= i2 && i4 <= 23 && i5 >= i3 && i5 <= 59) {
                try {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("weekDays", g.d.a.e.b.c(list));
                    jSONObject2.put("startHour", i2);
                    jSONObject2.put("startMin", i3);
                    jSONObject2.put("endHour", i4);
                    jSONObject2.put("endMin", i5);
                    Y(12298, jSONObject2.toString(), jSONObject);
                    return;
                } catch (JSONException e2) {
                    g.d.a.j.d.c("mcssdk---", e2.getLocalizedMessage());
                    return;
                }
            }
            throw new IllegalArgumentException("params are not all right,please check params");
        } else if (z() != null) {
            z().onSetPushTime(-2, "please call the register first!");
        }
    }

    public void X(String str) {
        this.f19412f = str;
    }

    public void a0(Context context, String str, String str2, JSONObject jSONObject, ICallBackResultService iCallBackResultService) {
        this.d = str;
        this.f19411e = str2;
        this.a = context.getApplicationContext();
        this.f19413g = iCallBackResultService;
        b0(jSONObject);
    }

    public void b0(JSONObject jSONObject) {
        if (i()) {
            Z(12290, jSONObject);
        } else if (z() != null) {
            z().onUnRegister(-2);
        }
    }

    public void e(int i2) {
        if (h(i2)) {
            ICallBackResultService iCallBackResultService = this.f19413g;
            if (iCallBackResultService != null) {
                iCallBackResultService.onError(r(i2), "api_call_too_frequently");
                return;
            }
            return;
        }
        Intent t = t(i2, "", null);
        this.a.bindService(t, new a(t), 1);
    }

    public void f(JSONObject jSONObject) {
        if (g()) {
            Z(12319, jSONObject);
        } else {
            g.d.a.j.d.c("mcssdk---", "please call the register first!");
        }
    }

    public boolean h(int i2) {
        return (i2 == 12291 || i2 == 12312 || b(i2).a() <= 2) ? false : true;
    }

    public void l(JSONObject jSONObject) {
        if (g()) {
            Z(12308, jSONObject);
        } else {
            g.d.a.j.d.c("mcssdk---", "please call the register first!");
        }
    }

    public void m(JSONObject jSONObject) {
        if (i()) {
            Z(12311, jSONObject);
        } else {
            g.d.a.j.d.c("mcssdk---", "please call the register first!");
        }
    }

    public void n(ISetAppNotificationCallBackService iSetAppNotificationCallBackService) {
        if (i()) {
            this.f19414h = iSetAppNotificationCallBackService;
            Z(12317, null);
        } else if (z() != null) {
            this.f19414h.onSetAppNotificationSwitch(-2);
        }
    }

    public void o(ISetAppNotificationCallBackService iSetAppNotificationCallBackService) {
        if (i()) {
            this.f19414h = iSetAppNotificationCallBackService;
            Z(12316, null);
            return;
        }
        ISetAppNotificationCallBackService iSetAppNotificationCallBackService2 = this.f19414h;
        if (iSetAppNotificationCallBackService2 != null) {
            iSetAppNotificationCallBackService2.onSetAppNotificationSwitch(-2);
        }
    }

    public void p(IGetAppNotificationCallBackService iGetAppNotificationCallBackService) {
        if (i()) {
            this.f19415i = iGetAppNotificationCallBackService;
            Z(12318, null);
            return;
        }
        IGetAppNotificationCallBackService iGetAppNotificationCallBackService2 = this.f19415i;
        if (iGetAppNotificationCallBackService2 != null) {
            iGetAppNotificationCallBackService2.onGetAppNotificationSwitch(-2, 0);
        }
    }

    public Context q() {
        return this.a;
    }

    public int r(int i2) {
        switch (i2) {
            case 12289:
                return -1;
            case 12290:
                return -2;
            case 12291:
                return -14;
            default:
                switch (i2) {
                    case 12298:
                        return -11;
                    case 12299:
                        return -3;
                    case 12300:
                        return -4;
                    default:
                        switch (i2) {
                            case 12306:
                                return -10;
                            case 12307:
                                return -6;
                            case 12308:
                                return -7;
                            case 12309:
                                return -5;
                            case 12310:
                                return -8;
                            case 12311:
                                return -9;
                            case 12312:
                                return -13;
                            case 12313:
                                return -12;
                            default:
                                switch (i2) {
                                    case 12316:
                                        return -15;
                                    case 12317:
                                        return -16;
                                    case 12318:
                                        return -17;
                                    default:
                                        return 0;
                                }
                        }
                }
        }
    }

    public String u(Context context) {
        if (o == null) {
            String v = v(context);
            if (v == null) {
                o = h.a(f19406k);
                p = false;
            } else {
                o = v;
                p = true;
            }
        }
        return o;
    }

    public void w(JSONObject jSONObject) {
        if (g()) {
            Z(12309, jSONObject);
        } else if (z() != null) {
            z().onGetNotificationStatus(-2, 0);
        }
    }

    public List<d> x() {
        return this.f19410c;
    }

    public List<c> y() {
        return this.b;
    }

    public ICallBackResultService z() {
        return this.f19413g;
    }

    private b() {
        this.b = new ArrayList();
        this.f19410c = new ArrayList();
        this.f19412f = null;
        synchronized (b.class) {
            int i2 = f19409n;
            if (i2 <= 0) {
                f19409n = i2 + 1;
            } else {
                throw new RuntimeException("PushService can't create again!");
            }
        }
        c(new g.d.a.g.b());
        c(new g.d.a.g.a());
        d(new g.d.a.h.b());
        d(new g.d.a.h.a());
        this.f19416j = new ConcurrentHashMap<>();
    }
}
