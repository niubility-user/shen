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
    */
    public void j(final DynamicTemplateEngine dynamicTemplateEngine, String str, JSONObject jSONObject) {
        JSONObject optJSONObject;
        int i2;
        SharedPreferences sharedPreferences;
        JSONArray jSONArray;
        int i3;
        int i4;
        if (dynamicTemplateEngine == null) {
            return;
        }
        String optString = jSONObject.optString("index");
        optJSONObject = jSONObject.optJSONObject("map");
        if (optJSONObject == null || TextUtils.isEmpty(optJSONObject.toString()) || TextUtils.equals(DYConstants.DY_NULL_STR, optJSONObject.toString())) {
            return;
        }
        sharedPreferences = dynamicTemplateEngine.getActivity().getSharedPreferences(str, 0);
        try {
            jSONArray = new JSONArray(sharedPreferences.getString("data", "[]"));
            i3 = sharedPreferences.getInt(AlbumListActivity.KEY_MAX_COUNT, 20);
            try {
                i4 = Integer.parseInt(optString);
            } catch (Exception unused) {
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
            return;
        }
        i4 = 0;
        final JSONArray jSONArray2 = new JSONArray();
        if (jSONArray.length() < 1) {
            jSONArray2.put(optJSONObject);
        } else {
            for (i2 = 0; i2 < Math.min(jSONArray.length(), i3 - 1); i2++) {
                if (i4 == i2) {
                    jSONArray2.put(optJSONObject);
                }
                jSONArray2.put(jSONArray.optJSONObject(i2));
            }
        }
        sharedPreferences.edit().putString("data", jSONArray2.toString()).apply();
        Observable.from(com.jd.dynamic.lib.utils.s.i(jSONObject.optString("success"))).forEach(new Action1() { // from class: com.jd.dynamic.lib.b.a.a.p
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                i1.this.i(jSONArray2, dynamicTemplateEngine, (String) obj);
            }
        }, new Action1() { // from class: com.jd.dynamic.lib.b.a.a.n
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                i1.k((Throwable) obj);
            }
        });
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
