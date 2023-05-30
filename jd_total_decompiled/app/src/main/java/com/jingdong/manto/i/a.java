package com.jingdong.manto.i;

import android.text.TextUtils;
import com.facebook.react.uimanager.ViewProps;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.common.unification.navigationbar.db.NavigationDbConstants;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.common.widget.NavigatorHolder;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.mta.ExposureRvUtils;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoStringUtils;
import com.jingdong.manto.utils.t;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public final class a {

    /* renamed from: n  reason: collision with root package name */
    private static final String f13045n = "a";
    private static JSONObject o = new JSONObject();
    public String a;
    public h b;

    /* renamed from: c  reason: collision with root package name */
    public d f13046c;
    public c d;

    /* renamed from: e  reason: collision with root package name */
    public JSONObject f13047e;

    /* renamed from: f  reason: collision with root package name */
    public Map<String, j> f13048f;

    /* renamed from: g  reason: collision with root package name */
    public Map<String, String> f13049g;

    /* renamed from: h  reason: collision with root package name */
    public e f13050h;

    /* renamed from: i  reason: collision with root package name */
    public boolean f13051i;

    /* renamed from: k  reason: collision with root package name */
    public ArrayList<f> f13053k;

    /* renamed from: m  reason: collision with root package name */
    public Map<String, g> f13055m;

    /* renamed from: j  reason: collision with root package name */
    public boolean f13052j = false;

    /* renamed from: l  reason: collision with root package name */
    public String f13054l = "";

    /* loaded from: classes15.dex */
    public static class b {
    }

    /* loaded from: classes15.dex */
    public static class c extends j {
    }

    /* loaded from: classes15.dex */
    public static class d {
        public int a;
        public int b;

        /* renamed from: c  reason: collision with root package name */
        public int f13056c;
        public int d;
    }

    /* loaded from: classes15.dex */
    public static class e {
        public boolean a = true;
        public boolean b = true;

        /* renamed from: c  reason: collision with root package name */
        public boolean f13057c = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes15.dex */
    public static class f {
        public String a;
        public ArrayList<String> b;

        private f() {
        }
    }

    /* loaded from: classes15.dex */
    public static class g {
        public String a;
        public ArrayList<String> b;
    }

    /* loaded from: classes15.dex */
    public static class h {
        public boolean a;
        public String b;

        /* renamed from: c  reason: collision with root package name */
        public String f13058c;
        public String d;

        /* renamed from: e  reason: collision with root package name */
        public String f13059e;

        /* renamed from: f  reason: collision with root package name */
        public String f13060f;

        /* renamed from: g  reason: collision with root package name */
        public List<i> f13061g = new ArrayList();

        public final boolean a(String str) {
            Iterator<i> it = this.f13061g.iterator();
            while (it.hasNext()) {
                if (it.next().a.equals(t.b(str))) {
                    return true;
                }
            }
            return false;
        }
    }

    /* loaded from: classes15.dex */
    public static class i {
        public String a;
        public String b;

        /* renamed from: c  reason: collision with root package name */
        public String f13062c;
        public String d;
    }

    /* loaded from: classes15.dex */
    public static class j {
        public String a;
        public String b;

        /* renamed from: c  reason: collision with root package name */
        public String f13063c = "default";
        public String d = JDDarkUtil.COLOR_0000000;

        /* renamed from: e  reason: collision with root package name */
        public String f13064e;

        /* renamed from: f  reason: collision with root package name */
        public boolean f13065f;

        /* renamed from: g  reason: collision with root package name */
        public String f13066g;

        /* renamed from: h  reason: collision with root package name */
        public boolean f13067h;

        /* renamed from: i  reason: collision with root package name */
        public boolean f13068i;

        /* renamed from: j  reason: collision with root package name */
        public String f13069j;

        /* renamed from: k  reason: collision with root package name */
        public String f13070k;

        /* renamed from: l  reason: collision with root package name */
        public boolean f13071l;

        public final boolean a() {
            return NavigatorHolder.NaviEntity.TYPE_CUSTOM.equalsIgnoreCase(this.f13063c);
        }
    }

    private a() {
    }

    private static c a(JSONObject jSONObject, c cVar) {
        c cVar2 = new c();
        if (jSONObject == null) {
            jSONObject = o;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject("window");
        if (optJSONObject == null) {
            optJSONObject = o;
        }
        cVar2.a = optJSONObject.optString("navigationBarTitleText", cVar.a);
        cVar2.b = optJSONObject.optString("navigationBarTextStyle", cVar.b);
        cVar2.f13063c = optJSONObject.optString("navigationStyle", cVar.f13063c);
        cVar2.d = optJSONObject.optString("navigationBarBackgroundColor", cVar.d);
        cVar2.f13064e = optJSONObject.optString(ViewProps.BACKGROUND_COLOR, cVar.f13064e);
        cVar2.f13065f = optJSONObject.optBoolean("enablePullDownRefresh", cVar.f13065f);
        cVar2.f13066g = optJSONObject.optString("backgroundTextStyle", cVar.f13066g);
        cVar2.f13067h = optJSONObject.optBoolean("enableFullScreen", cVar.f13067h);
        cVar2.f13071l = optJSONObject.optBoolean("disablePopGesture", false);
        JSONObject optJSONObject2 = optJSONObject.optJSONObject("navigationBarRightButton");
        if (optJSONObject2 == null) {
            optJSONObject2 = o;
        }
        cVar2.f13068i = optJSONObject2.optBoolean(ExposureRvUtils.HIDE, cVar.f13068i);
        cVar2.f13069j = optJSONObject2.optString("text", cVar.f13069j);
        cVar2.f13070k = optJSONObject2.optString("iconPath", cVar.f13070k);
        return cVar2;
    }

    public static a a(com.jingdong.manto.f fVar) {
        a aVar = new a();
        JSONObject jSONObject = new JSONObject();
        if (com.jingdong.manto.k.a.b().a() == 1) {
            try {
                String b2 = com.jingdong.manto.pkg.b.g.b(fVar, "app-config-darkmode.json");
                if (!TextUtils.isEmpty(b2)) {
                    jSONObject = new JSONObject(b2);
                    aVar.f13052j = true;
                }
            } catch (Throwable unused) {
                jSONObject = new JSONObject();
            }
        }
        if (!aVar.f13052j) {
            String b3 = com.jingdong.manto.pkg.b.g.b(fVar, "app-config.json");
            if (b3 == null) {
                return null;
            }
            try {
                jSONObject = new JSONObject(b3);
            } catch (Throwable th) {
                MantoLog.e(f13045n, th.getMessage());
                jSONObject = new JSONObject();
            }
        }
        aVar.f13047e = jSONObject;
        aVar.a = jSONObject.optString("entryPagePath");
        aVar.d = g(jSONObject.optJSONObject(JshopConst.JSKEY_IS_GLOBAL));
        aVar.f13048f = b(jSONObject.optJSONObject("page"), aVar.d);
        aVar.b = f(jSONObject.optJSONObject("tabBar"));
        aVar.f13046c = c(jSONObject.optJSONObject("networkTimeout"));
        if (fVar.w()) {
            b(jSONObject);
        }
        jSONObject.optBoolean("preloadEnabled", true);
        aVar.f13050h = d(jSONObject.optJSONObject("quickMenu"));
        aVar.f13049g = a(jSONObject.optJSONObject("pageAlias"));
        aVar.f13051i = jSONObject.optBoolean("darkmode", false);
        aVar.f13053k = a(jSONObject.optJSONArray("subPackages"));
        aVar.f13054l = jSONObject.optString("renderingMode", "");
        aVar.f13055m = e(jSONObject.optJSONObject("preloadRule"));
        return aVar;
    }

    private static ArrayList<f> a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        ArrayList<f> arrayList = new ArrayList<>();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            try {
                JSONObject optJSONObject = jSONArray.optJSONObject(i2);
                if (optJSONObject != null) {
                    String optString = optJSONObject.optString("root");
                    ArrayList<String> arrayList2 = new ArrayList<>();
                    JSONArray optJSONArray = optJSONObject.optJSONArray("pages");
                    if (optJSONArray != null) {
                        for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                            arrayList2.add(optJSONArray.optString(i3));
                        }
                    }
                    f fVar = new f();
                    fVar.a = optString;
                    fVar.b = arrayList2;
                    arrayList.add(fVar);
                }
            } catch (Exception unused) {
            }
        }
        return arrayList;
    }

    private static Map<String, String> a(JSONObject jSONObject) {
        HashMap hashMap = new HashMap();
        if (jSONObject != null) {
            try {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    hashMap.put(next, jSONObject.optString(next));
                }
            } catch (Throwable unused) {
            }
        }
        return hashMap;
    }

    private static b b(JSONObject jSONObject) {
        b bVar = new b();
        if (jSONObject == null) {
            jSONObject = o;
        }
        jSONObject.optString("deviceOrientation", "portrait");
        jSONObject.optBoolean("showStatusBar", false);
        return bVar;
    }

    private static Map<String, j> b(JSONObject jSONObject, c cVar) {
        HashMap hashMap = new HashMap();
        if (jSONObject == null) {
            return hashMap;
        }
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            hashMap.put(next, a(jSONObject.optJSONObject(next), cVar));
        }
        return hashMap;
    }

    private static d c(JSONObject jSONObject) {
        d dVar = new d();
        if (jSONObject == null) {
            jSONObject = o;
        }
        dVar.a = jSONObject.optInt("request");
        dVar.b = jSONObject.optInt("connectSocket");
        dVar.d = jSONObject.optInt("downloadFile");
        dVar.f13056c = jSONObject.optInt("uploadFile");
        return dVar;
    }

    private static e d(JSONObject jSONObject) {
        e eVar = new e();
        if (jSONObject != null) {
            eVar.a = jSONObject.optBoolean("favorite", true);
            eVar.b = jSONObject.optBoolean("share", true);
            eVar.f13057c = jSONObject.optBoolean("sendToDesktop", true);
        }
        return eVar;
    }

    private static Map<String, g> e(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            JSONObject optJSONObject = jSONObject.optJSONObject(next);
            if (optJSONObject != null) {
                g gVar = new g();
                gVar.a = optJSONObject.optString("network", NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_POSITION_ALL);
                ArrayList<String> arrayList = new ArrayList<>();
                JSONArray optJSONArray = optJSONObject.optJSONArray("packages");
                if (optJSONArray != null) {
                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                        arrayList.add(optJSONArray.optString(i2));
                    }
                }
                gVar.b = arrayList;
                hashMap.put(next, gVar);
            }
        }
        return hashMap;
    }

    private static h f(JSONObject jSONObject) {
        h hVar = new h();
        if (jSONObject == null) {
            jSONObject = o;
        }
        hVar.a = jSONObject.optBoolean(NavigatorHolder.NaviEntity.TYPE_CUSTOM);
        hVar.b = jSONObject.optString("position");
        hVar.f13058c = jSONObject.optString("color");
        hVar.d = jSONObject.optString("selectedColor");
        hVar.f13059e = jSONObject.optString(ViewProps.BACKGROUND_COLOR);
        hVar.f13060f = jSONObject.optString("borderStyle");
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray(ThemeTitleConstant.TITLE_LIST_DRAWABLE_ID);
            if (optJSONArray != null) {
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    List<i> list = hVar.f13061g;
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i2);
                    i iVar = new i();
                    iVar.a = jSONObject2.optString("pagePath");
                    iVar.b = jSONObject2.optString("text");
                    iVar.f13062c = jSONObject2.optString("iconData");
                    iVar.d = jSONObject2.optString("selectedIconData");
                    list.add(iVar);
                }
            }
        } catch (Throwable th) {
            MantoLog.e(f13045n, th.getMessage());
        }
        return hVar;
    }

    private static c g(JSONObject jSONObject) {
        c cVar = new c();
        if (jSONObject == null) {
            jSONObject = o;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject("window");
        if (optJSONObject == null) {
            optJSONObject = o;
        }
        cVar.a = optJSONObject.optString("navigationBarTitleText", null);
        cVar.b = optJSONObject.optString("navigationBarTextStyle", null);
        cVar.f13063c = optJSONObject.optString("navigationStyle", null);
        cVar.d = optJSONObject.optString("navigationBarBackgroundColor", null);
        cVar.f13064e = optJSONObject.optString(ViewProps.BACKGROUND_COLOR, null);
        cVar.f13065f = optJSONObject.optBoolean("enablePullDownRefresh", false);
        cVar.f13066g = optJSONObject.optString("backgroundTextStyle", null);
        cVar.f13067h = optJSONObject.optBoolean("enableFullScreen", false);
        JSONObject optJSONObject2 = optJSONObject.optJSONObject("navigationBarRightButton");
        if (optJSONObject2 == null) {
            optJSONObject2 = o;
        }
        cVar.f13068i = optJSONObject2.optBoolean(ExposureRvUtils.HIDE, false);
        cVar.f13069j = optJSONObject2.optString("text", null);
        cVar.f13070k = optJSONObject2.optString("iconPath", null);
        return cVar;
    }

    public final String a() {
        return MantoStringUtils.isEmpty(this.a) ? "index.html" : this.a;
    }

    public String a(String str) {
        if (!TextUtils.isEmpty(str) && this.f13053k != null) {
            String b2 = t.b(str);
            if (b2.endsWith(".html")) {
                b2 = b2.replace(".html", "");
            }
            Iterator<f> it = this.f13053k.iterator();
            while (it.hasNext()) {
                f next = it.next();
                if (b2.startsWith(next.a) && next.b.contains(b2)) {
                    return next.a;
                }
            }
        }
        return null;
    }

    public final j b(String str) {
        return this.f13048f.containsKey(str) ? this.f13048f.get(str) : this.d;
    }

    public boolean b() {
        return TextUtils.equals(this.f13054l, "mixed");
    }
}
