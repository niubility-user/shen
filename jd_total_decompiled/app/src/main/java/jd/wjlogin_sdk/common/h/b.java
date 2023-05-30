package jd.wjlogin_sdk.common.h;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import jd.wjlogin_sdk.b.d;
import jd.wjlogin_sdk.model.WUserSigInfo;
import jd.wjlogin_sdk.util.e;
import jd.wjlogin_sdk.util.p;
import jd.wjlogin_sdk.util.v;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class b {

    /* renamed from: f  reason: collision with root package name */
    private static final String f19776f = "WJLogin.UserManager";

    /* renamed from: g  reason: collision with root package name */
    private static final String f19777g = "wjlogin_users_v3";

    /* renamed from: h  reason: collision with root package name */
    private static final String f19778h = "key_wj_login_version";

    /* renamed from: i  reason: collision with root package name */
    private static final String f19779i = "1";
    private List<WUserSigInfo> a = new ArrayList();
    private int b = 10;

    /* renamed from: c  reason: collision with root package name */
    private WUserSigInfo f19780c;
    private Object d;

    /* renamed from: e  reason: collision with root package name */
    private jd.wjlogin_sdk.common.a f19781e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements Comparator<WUserSigInfo> {
        a() {
        }

        @Override // java.util.Comparator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public int compare(WUserSigInfo wUserSigInfo, WUserSigInfo wUserSigInfo2) {
            return (wUserSigInfo.getA2CreateDate() == null || wUserSigInfo2.getA2CreateDate() == null || wUserSigInfo.getA2CreateDate().getTime() <= wUserSigInfo2.getA2CreateDate().getTime()) ? 1 : -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(Object obj) {
        this.d = obj;
    }

    private void d() {
        String a2;
        try {
            String f2 = v.f(f19777g);
            if (!TextUtils.isEmpty(f2)) {
                a2 = d.c(f2);
            } else {
                a2 = d.a(v.e());
            }
            if (TextUtils.isEmpty(a2)) {
                return;
            }
            a(new JSONObject(a2));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void e() {
        try {
            String a2 = d.a(v.e());
            if (TextUtils.isEmpty(a2)) {
                return;
            }
            a(new JSONObject(a2));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void h() {
        try {
            String e2 = v.e(e.t);
            if (TextUtils.isEmpty(e2)) {
                return;
            }
            String b = d.b(e2);
            if (TextUtils.isEmpty(b)) {
                return;
            }
            JSONObject jSONObject = new JSONObject(b);
            jd.wjlogin_sdk.common.a aVar = new jd.wjlogin_sdk.common.a();
            aVar.a(jSONObject);
            this.f19781e = aVar;
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    private void k() {
        try {
            p.b("saveLocalUsers");
            if (this.a.isEmpty()) {
                return;
            }
            JSONObject l2 = l();
            String f2 = d.f();
            String b = jd.wjlogin_sdk.b.b.b(f2, l2.toString());
            v.b(f19777g, b);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("k", f2);
            jSONObject.put("d", b);
            v.i(jSONObject.toString());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private JSONObject l() {
        p.b("toLocalJson");
        JSONObject jSONObject = new JSONObject();
        try {
            if (!this.a.isEmpty()) {
                p.b("toLocalJson !mUsers.isEmpty()");
                JSONArray jSONArray = new JSONArray();
                jSONObject.put("users", jSONArray);
                for (int i2 = 0; i2 < this.a.size(); i2++) {
                    jSONArray.put(this.a.get(i2).toJSONString());
                }
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(List<WUserSigInfo> list) {
        if (list.isEmpty()) {
            return;
        }
        Collections.sort(list, new a());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean b(WUserSigInfo wUserSigInfo) {
        p.b(f19776f, "deleteUser");
        if (wUserSigInfo == null) {
            p.b(f19776f, "deleteUser user == null");
            return false;
        }
        synchronized (this.d) {
            if (wUserSigInfo.isCurrentMainUser()) {
                p.b(f19776f, "deleteUser user.isCurrentMainUser()");
                WUserSigInfo wUserSigInfo2 = this.f19780c;
                if (wUserSigInfo2 != null) {
                    wUserSigInfo2.empty();
                }
                this.a.remove(wUserSigInfo);
                wUserSigInfo.empty();
                this.a.add(wUserSigInfo);
            } else {
                this.a.remove(wUserSigInfo);
            }
            k();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean c(WUserSigInfo wUserSigInfo) {
        return a(wUserSigInfo, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<WUserSigInfo> f() {
        ArrayList arrayList = new ArrayList();
        synchronized (this.d) {
            if (this.a.isEmpty()) {
                return arrayList;
            }
            for (int i2 = 0; i2 < this.a.size(); i2++) {
                arrayList.add(this.a.get(i2));
            }
            return arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g() {
        d();
        h();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean i() {
        if ("1".equals(v.e(f19778h))) {
            return true;
        }
        v.a(f19778h, "1");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j() {
        e();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public jd.wjlogin_sdk.common.a c() {
        jd.wjlogin_sdk.common.a aVar;
        synchronized (this.d) {
            aVar = this.f19781e;
        }
        return aVar;
    }

    private void a(WUserSigInfo wUserSigInfo) {
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (int i2 = 0; i2 < this.a.size(); i2++) {
            WUserSigInfo wUserSigInfo2 = this.a.get(i2);
            if (!TextUtils.isEmpty(wUserSigInfo2.getPin()) && !TextUtils.isEmpty(wUserSigInfo.getPin()) && wUserSigInfo2.getPin().equals(wUserSigInfo.getPin())) {
                wUserSigInfo.setCurrentMainUser(true);
                arrayList.add(0, wUserSigInfo);
                z = true;
            } else if (!TextUtils.isEmpty(wUserSigInfo2.getPin())) {
                wUserSigInfo2.setCurrentMainUser(false);
                arrayList.add(0, wUserSigInfo2);
            }
        }
        if (!z) {
            wUserSigInfo.setCurrentMainUser(true);
            arrayList.add(0, wUserSigInfo);
        }
        this.f19780c = wUserSigInfo;
        this.a.clear();
        this.a = arrayList;
        k();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WUserSigInfo b(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        synchronized (this.d) {
            for (int i2 = 0; i2 < this.a.size(); i2++) {
                WUserSigInfo wUserSigInfo = this.a.get(i2);
                if (wUserSigInfo.getPin().equals(str)) {
                    return wUserSigInfo;
                }
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(WUserSigInfo wUserSigInfo, boolean z) {
        if (wUserSigInfo == null) {
            return false;
        }
        synchronized (this.d) {
            if (!z) {
                List<WUserSigInfo> list = this.a;
                if (list != null && list.size() >= this.b) {
                    a(this.a);
                    List<WUserSigInfo> list2 = this.a;
                    list2.remove(list2.size() - 1);
                    a(wUserSigInfo);
                }
            }
            a(wUserSigInfo);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WUserSigInfo b() {
        WUserSigInfo wUserSigInfo;
        synchronized (this.d) {
            wUserSigInfo = this.f19780c;
        }
        return wUserSigInfo;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(String str) {
        int i2 = 0;
        if (str == null || str.equals("")) {
            return false;
        }
        synchronized (this.d) {
            while (true) {
                if (i2 >= this.a.size()) {
                    break;
                }
                WUserSigInfo wUserSigInfo = this.a.get(i2);
                if (wUserSigInfo == null || !str.equals(wUserSigInfo.getPin())) {
                    i2++;
                } else if (wUserSigInfo.isCurrentMainUser()) {
                    WUserSigInfo wUserSigInfo2 = this.f19780c;
                    if (wUserSigInfo2 != null) {
                        wUserSigInfo2.empty();
                    }
                    this.a.remove(i2);
                    wUserSigInfo.empty();
                    this.a.add(wUserSigInfo);
                } else {
                    this.a.remove(wUserSigInfo);
                }
            }
            k();
        }
        return true;
    }

    private void a(JSONObject jSONObject) {
        if (p.b) {
            p.b(f19776f, "fromLocalJson  ");
        }
        if (jSONObject == null) {
            if (p.b) {
                p.b(f19776f, "fromLocalJson  json == null");
                return;
            }
            return;
        }
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray("users");
            if (optJSONArray != null) {
                ArrayList arrayList = new ArrayList();
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    String optString = optJSONArray.optString(i2);
                    WUserSigInfo wUserSigInfo = new WUserSigInfo();
                    wUserSigInfo.createUserInfoFromJSON(new JSONObject(optString));
                    arrayList.add(wUserSigInfo);
                    if (wUserSigInfo.isCurrentMainUser()) {
                        this.f19780c = wUserSigInfo;
                    }
                }
                if (arrayList.isEmpty()) {
                    return;
                }
                this.a.clear();
                this.a = arrayList;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(jd.wjlogin_sdk.common.a aVar) {
        if (aVar != null) {
            this.f19781e = aVar;
            v.a(e.t, d.e(aVar.toString()));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        if (p.b) {
            p.b(f19776f, "clearLocalA4");
        }
        this.f19781e = new jd.wjlogin_sdk.common.a();
        if (v.a(e.t)) {
            v.g(e.t);
        }
    }
}
