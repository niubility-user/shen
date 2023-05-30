package com.jd.dynamic.lib.b.a.a;

import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.widget.photo.AlbumListActivity;
import java.lang.reflect.Field;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import rx.Observable;
import rx.functions.Action1;

/* loaded from: classes13.dex */
public class i1 extends e1 {
    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(DynamicTemplateEngine dynamicTemplateEngine, String str) {
        com.jd.dynamic.lib.utils.s.b(str, null, dynamicTemplateEngine, this.mTargetView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void d(Throwable th) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void e(JSONArray jSONArray, DynamicTemplateEngine dynamicTemplateEngine, String str) {
        com.jd.dynamic.lib.utils.s.b(str, jSONArray, dynamicTemplateEngine, this.mTargetView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f(DynamicTemplateEngine dynamicTemplateEngine, String str) {
        com.jd.dynamic.lib.utils.s.b(str, null, dynamicTemplateEngine, this.mTargetView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void h(Throwable th) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void i(JSONArray jSONArray, DynamicTemplateEngine dynamicTemplateEngine, String str) {
        com.jd.dynamic.lib.utils.s.b(str, jSONArray, dynamicTemplateEngine, this.mTargetView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void k(Throwable th) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void l(JSONArray jSONArray, DynamicTemplateEngine dynamicTemplateEngine, String str) {
        com.jd.dynamic.lib.utils.s.b(str, jSONArray, dynamicTemplateEngine, this.mTargetView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void n(Throwable th) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void o(JSONArray jSONArray, DynamicTemplateEngine dynamicTemplateEngine, String str) {
        com.jd.dynamic.lib.utils.s.b(str, jSONArray, dynamicTemplateEngine, this.mTargetView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void p(Throwable th) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void q(JSONArray jSONArray, DynamicTemplateEngine dynamicTemplateEngine, String str) {
        com.jd.dynamic.lib.utils.s.b(str, jSONArray, dynamicTemplateEngine, this.mTargetView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void r(Throwable th) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void s(Throwable th) {
    }

    public JSONArray a(final DynamicTemplateEngine dynamicTemplateEngine, String str, JSONObject jSONObject) {
        final JSONArray jSONArray;
        if (dynamicTemplateEngine == null) {
            return null;
        }
        try {
            jSONArray = new JSONArray(dynamicTemplateEngine.getActivity().getSharedPreferences(str, 0).getString("data", "[]"));
        } catch (JSONException e2) {
            e2.printStackTrace();
            final JSONArray jSONArray2 = new JSONArray();
            if (jSONObject != null) {
                Observable.from(com.jd.dynamic.lib.utils.s.i(jSONObject.optString("error"))).forEach(new Action1() { // from class: com.jd.dynamic.lib.b.a.a.v
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        i1.this.l(jSONArray2, dynamicTemplateEngine, (String) obj);
                    }
                }, new Action1() { // from class: com.jd.dynamic.lib.b.a.a.w
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        i1.p((Throwable) obj);
                    }
                });
            }
        }
        if (jSONArray.length() > 0) {
            Observable.from(com.jd.dynamic.lib.utils.s.i(jSONObject.optString("success"))).forEach(new Action1() { // from class: com.jd.dynamic.lib.b.a.a.t
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    i1.this.q(jSONArray, dynamicTemplateEngine, (String) obj);
                }
            }, new Action1() { // from class: com.jd.dynamic.lib.b.a.a.q
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    i1.s((Throwable) obj);
                }
            });
            return jSONArray;
        }
        if (jSONObject != null) {
            Observable.from(com.jd.dynamic.lib.utils.s.i(jSONObject.optString("error"))).forEach(new Action1() { // from class: com.jd.dynamic.lib.b.a.a.s
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    i1.this.o(jSONArray, dynamicTemplateEngine, (String) obj);
                }
            }, new Action1() { // from class: com.jd.dynamic.lib.b.a.a.o
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    i1.r((Throwable) obj);
                }
            });
        }
        return null;
    }

    public void c(final DynamicTemplateEngine dynamicTemplateEngine, String str, String str2, JSONObject jSONObject) {
        int i2;
        if (dynamicTemplateEngine == null) {
            return;
        }
        SharedPreferences sharedPreferences = dynamicTemplateEngine.getActivity().getSharedPreferences(str, 0);
        try {
            i2 = Integer.parseInt(str2);
        } catch (Exception unused) {
            i2 = 20;
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt(AlbumListActivity.KEY_MAX_COUNT, i2);
        edit.apply();
        Observable.from(com.jd.dynamic.lib.utils.s.i(jSONObject.optString("success"))).forEach(new Action1() { // from class: com.jd.dynamic.lib.b.a.a.u
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                i1.this.b(dynamicTemplateEngine, (String) obj);
            }
        }, new Action1() { // from class: com.jd.dynamic.lib.b.a.a.j
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                i1.h((Throwable) obj);
            }
        });
    }

    @Override // com.jd.dynamic.base.CommFunction
    public Object exec(DynamicTemplateEngine dynamicTemplateEngine, JSONObject jSONObject) {
        this.mEngine = dynamicTemplateEngine;
        String str = (String) jSONObject.remove("fun");
        String optString = jSONObject.optString("path");
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(optString)) {
            return null;
        }
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -1716004091:
                if (str.equals("selectAll")) {
                    c2 = 0;
                    break;
                }
                break;
            case -1335458389:
                if (str.equals("delete")) {
                    c2 = 1;
                    break;
                }
                break;
            case -358737930:
                if (str.equals("deleteAll")) {
                    c2 = 2;
                    break;
                }
                break;
            case 96417:
                if (str.equals("add")) {
                    c2 = 3;
                    break;
                }
                break;
            case 3237136:
                if (str.equals(XView2Constants.XVIEW2_ACTION_INIT)) {
                    c2 = 4;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                return a(dynamicTemplateEngine, optString, jSONObject);
            case 1:
                m(dynamicTemplateEngine, optString, jSONObject);
                break;
            case 2:
                g(dynamicTemplateEngine, optString, jSONObject);
                break;
            case 3:
                j(dynamicTemplateEngine, optString, jSONObject);
                break;
            case 4:
                c(dynamicTemplateEngine, optString, jSONObject.optString(AlbumListActivity.KEY_MAX_COUNT), jSONObject);
                break;
        }
        return null;
    }

    public void g(final DynamicTemplateEngine dynamicTemplateEngine, String str, JSONObject jSONObject) {
        if (dynamicTemplateEngine == null) {
            return;
        }
        SharedPreferences.Editor edit = dynamicTemplateEngine.getActivity().getSharedPreferences(str, 0).edit();
        edit.remove("data");
        edit.apply();
        Observable.from(com.jd.dynamic.lib.utils.s.i(jSONObject.optString("success"))).forEach(new Action1() { // from class: com.jd.dynamic.lib.b.a.a.m
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                i1.this.f(dynamicTemplateEngine, (String) obj);
            }
        }, new Action1() { // from class: com.jd.dynamic.lib.b.a.a.l
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                i1.n((Throwable) obj);
            }
        });
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x004f, code lost:
        if (r0 >= r5.length()) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void j(final com.jd.dynamic.base.DynamicTemplateEngine r11, java.lang.String r12, org.json.JSONObject r13) {
        /*
            r10 = this;
            if (r11 != 0) goto L3
            return
        L3:
            java.lang.String r0 = "index"
            java.lang.String r0 = r13.optString(r0)
            java.lang.String r1 = "map"
            org.json.JSONObject r1 = r13.optJSONObject(r1)
            if (r1 == 0) goto La9
            java.lang.String r2 = r1.toString()
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 != 0) goto La9
            java.lang.String r2 = r1.toString()
            java.lang.String r3 = "null"
            boolean r2 = android.text.TextUtils.equals(r3, r2)
            if (r2 == 0) goto L29
            goto La9
        L29:
            android.app.Activity r2 = r11.getActivity()
            r3 = 0
            android.content.SharedPreferences r12 = r2.getSharedPreferences(r12, r3)
            java.lang.String r2 = "data"
            java.lang.String r4 = "[]"
            java.lang.String r4 = r12.getString(r2, r4)
            org.json.JSONArray r5 = new org.json.JSONArray     // Catch: org.json.JSONException -> La5
            r5.<init>(r4)     // Catch: org.json.JSONException -> La5
            java.lang.String r4 = "maxCount"
            r6 = 20
            int r4 = r12.getInt(r4, r6)     // Catch: org.json.JSONException -> La5
            int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.Exception -> L51
            int r6 = r5.length()     // Catch: java.lang.Exception -> L51
            if (r0 < r6) goto L52
        L51:
            r0 = 0
        L52:
            org.json.JSONArray r6 = new org.json.JSONArray     // Catch: org.json.JSONException -> La5
            r6.<init>()     // Catch: org.json.JSONException -> La5
            int r7 = r5.length()     // Catch: org.json.JSONException -> La5
            r8 = 1
            if (r7 >= r8) goto L62
            r6.put(r1)     // Catch: org.json.JSONException -> La5
            goto L7d
        L62:
            int r7 = r5.length()     // Catch: org.json.JSONException -> La5
            int r9 = r4 + (-1)
            int r7 = java.lang.Math.min(r7, r9)     // Catch: org.json.JSONException -> La5
            if (r3 >= r7) goto L7d
            if (r0 != r3) goto L73
            r6.put(r1)     // Catch: org.json.JSONException -> La5
        L73:
            org.json.JSONObject r7 = r5.optJSONObject(r3)     // Catch: org.json.JSONException -> La5
            r6.put(r7)     // Catch: org.json.JSONException -> La5
            int r3 = r3 + 1
            goto L62
        L7d:
            android.content.SharedPreferences$Editor r12 = r12.edit()     // Catch: org.json.JSONException -> La5
            java.lang.String r0 = r6.toString()     // Catch: org.json.JSONException -> La5
            android.content.SharedPreferences$Editor r12 = r12.putString(r2, r0)     // Catch: org.json.JSONException -> La5
            r12.apply()     // Catch: org.json.JSONException -> La5
            java.lang.String r12 = "success"
            java.lang.String r12 = r13.optString(r12)     // Catch: org.json.JSONException -> La5
            java.util.List r12 = com.jd.dynamic.lib.utils.s.i(r12)     // Catch: org.json.JSONException -> La5
            rx.Observable r12 = rx.Observable.from(r12)     // Catch: org.json.JSONException -> La5
            com.jd.dynamic.lib.b.a.a.p r13 = new com.jd.dynamic.lib.b.a.a.p     // Catch: org.json.JSONException -> La5
            r13.<init>()     // Catch: org.json.JSONException -> La5
            com.jd.dynamic.lib.b.a.a.n r11 = new rx.functions.Action1() { // from class: com.jd.dynamic.lib.b.a.a.n
                static {
                    /*
                        com.jd.dynamic.lib.b.a.a.n r0 = new com.jd.dynamic.lib.b.a.a.n
                        r0.<init>()
                        
                        // error: 0x0005: SPUT (r0 I:com.jd.dynamic.lib.b.a.a.n) com.jd.dynamic.lib.b.a.a.n.g com.jd.dynamic.lib.b.a.a.n
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.lib.b.a.a.n.<clinit>():void");
                }

                {
                    /*
                        r0 = this;
                        r0.<init>()
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.lib.b.a.a.n.<init>():void");
                }

                @Override // rx.functions.Action1
                public final void call(java.lang.Object r1) {
                    /*
                        r0 = this;
                        java.lang.Throwable r1 = (java.lang.Throwable) r1
                        com.jd.dynamic.lib.b.a.a.i1.x(r1)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.lib.b.a.a.n.call(java.lang.Object):void");
                }
            }     // Catch: org.json.JSONException -> La5
            r12.forEach(r13, r11)     // Catch: org.json.JSONException -> La5
            goto La9
        La5:
            r11 = move-exception
            r11.printStackTrace()
        La9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.lib.b.a.a.i1.j(com.jd.dynamic.base.DynamicTemplateEngine, java.lang.String, org.json.JSONObject):void");
    }

    public void m(final DynamicTemplateEngine dynamicTemplateEngine, String str, JSONObject jSONObject) {
        JSONObject optJSONObject;
        if (dynamicTemplateEngine == null || (optJSONObject = jSONObject.optJSONObject("map")) == null || TextUtils.isEmpty(optJSONObject.toString()) || TextUtils.equals(DYConstants.DY_NULL_STR, optJSONObject.toString())) {
            return;
        }
        SharedPreferences sharedPreferences = dynamicTemplateEngine.getActivity().getSharedPreferences(str, 0);
        try {
            final JSONArray jSONArray = new JSONArray(sharedPreferences.getString("data", "[]"));
            int i2 = -1;
            for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                if (TextUtils.equals(jSONArray.getJSONObject(i3).toString(), optJSONObject.toString())) {
                    i2 = i3;
                }
            }
            if (i2 != -1 && i2 < jSONArray.length()) {
                if (Build.VERSION.SDK_INT >= 19) {
                    jSONArray.remove(i2);
                } else {
                    Field declaredField = JSONArray.class.getDeclaredField("values");
                    declaredField.setAccessible(true);
                    List list = (List) declaredField.get(jSONArray);
                    if (list != null && i2 < list.size()) {
                        list.remove(i2);
                    }
                }
            }
            sharedPreferences.edit().putString("data", jSONArray.toString()).apply();
            Observable.from(com.jd.dynamic.lib.utils.s.i(jSONObject.optString("success"))).forEach(new Action1() { // from class: com.jd.dynamic.lib.b.a.a.k
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    i1.this.e(jSONArray, dynamicTemplateEngine, (String) obj);
                }
            }, new Action1() { // from class: com.jd.dynamic.lib.b.a.a.r
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    i1.d((Throwable) obj);
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
