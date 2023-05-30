package com.jd.stat.security.jma.a;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.jingdong.common.unification.navigationbar.db.NavigationDbConstants;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class h extends b {
    private static String b = "JDMob.Security.ScreenFeature";

    /* renamed from: c  reason: collision with root package name */
    private String f7076c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private String f7077e;

    /* renamed from: f  reason: collision with root package name */
    private String f7078f;

    /* renamed from: g  reason: collision with root package name */
    private String f7079g;

    /* renamed from: h  reason: collision with root package name */
    private int f7080h = 0;

    /* renamed from: i  reason: collision with root package name */
    private Map<String, List<WeakReference<View>>> f7081i = new HashMap();

    private List<WeakReference<View>> b(Activity activity) {
        ArrayList arrayList = new ArrayList();
        a(arrayList, activity.getWindow().getDecorView());
        return arrayList;
    }

    private boolean e(String str) {
        return com.jd.stat.security.d.a().B() != null && com.jd.stat.security.d.a().B().contains(str);
    }

    @Override // com.jd.stat.security.jma.a.g
    public JSONObject a(Context context) {
        MotionEvent motionEvent;
        float[] a;
        JSONObject jSONObject = this.a;
        if (jSONObject == null || (motionEvent = (MotionEvent) jSONObject.opt("motionparam")) == null || (a = a(motionEvent, this.a.optString("pagename"))) == null) {
            return null;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            long currentTimeMillis = System.currentTimeMillis();
            if (motionEvent != null) {
                float rawX = motionEvent.getRawX();
                float rawY = motionEvent.getRawY();
                if (a != null) {
                    this.f7076c = rawX + CartConstant.KEY_YB_INFO_LINK + rawY + CartConstant.KEY_YB_INFO_LINK + (rawX - a[0]) + CartConstant.KEY_YB_INFO_LINK + (rawY - a[1]) + CartConstant.KEY_YB_INFO_LINK + currentTimeMillis;
                }
                if (com.jd.stat.common.b.b.a) {
                    com.jd.stat.common.b.b.b(b, "MotionEvent.ACTION_DOWN = " + this.f7076c);
                }
            }
        } else if (action == 1) {
            this.f7080h = 0;
            long currentTimeMillis2 = System.currentTimeMillis();
            if (motionEvent != null) {
                float rawX2 = motionEvent.getRawX();
                float rawY2 = motionEvent.getRawY();
                if (a != null) {
                    float f2 = rawX2 - a[0];
                    float f3 = rawY2 - a[1];
                    this.d = rawX2 + CartConstant.KEY_YB_INFO_LINK + rawY2 + CartConstant.KEY_YB_INFO_LINK + f2 + CartConstant.KEY_YB_INFO_LINK + f3 + CartConstant.KEY_YB_INFO_LINK + currentTimeMillis2;
                    String str = b;
                    StringBuilder sb = new StringBuilder();
                    sb.append("MotionEvent.ACTION_UP  Cal = ");
                    sb.append(f2);
                    sb.append(LangUtils.SINGLE_SPACE);
                    sb.append(f3);
                    com.jd.stat.common.b.b.b(str, sb.toString());
                }
            }
            try {
                JSONArray jSONArray = new JSONArray();
                if (!TextUtils.isEmpty(this.f7076c)) {
                    jSONArray.put(this.f7076c);
                }
                if (!TextUtils.isEmpty(this.f7077e)) {
                    jSONArray.put(this.f7077e);
                    this.f7077e = "";
                }
                if (!TextUtils.isEmpty(this.f7078f)) {
                    jSONArray.put(this.f7078f);
                    this.f7078f = "";
                }
                if (!TextUtils.isEmpty(this.f7079g)) {
                    jSONArray.put(this.f7079g);
                    this.f7079g = "";
                }
                if (!TextUtils.isEmpty(this.d)) {
                    jSONArray.put(this.d);
                }
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("point", jSONArray);
                if (com.jd.stat.common.b.b.a) {
                    com.jd.stat.common.b.b.b(b, "touch point info = " + jSONObject2.toString());
                }
                return jSONObject2;
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        } else if (action == 2) {
            int i2 = this.f7080h + 1;
            this.f7080h = i2;
            if (motionEvent != null) {
                if (i2 == 2) {
                    long currentTimeMillis3 = System.currentTimeMillis();
                    float rawX3 = motionEvent.getRawX();
                    float rawY3 = motionEvent.getRawY();
                    if (a != null) {
                        this.f7077e = rawX3 + CartConstant.KEY_YB_INFO_LINK + rawY3 + CartConstant.KEY_YB_INFO_LINK + (rawX3 - a[0]) + CartConstant.KEY_YB_INFO_LINK + (rawY3 - a[1]) + CartConstant.KEY_YB_INFO_LINK + currentTimeMillis3;
                    }
                    com.jd.stat.common.b.b.b(b, "MotionEvent.ACTION_MOVE  evMoveA = " + this.f7077e);
                } else if (i2 == 4) {
                    long currentTimeMillis4 = System.currentTimeMillis();
                    float rawX4 = motionEvent.getRawX();
                    float rawY4 = motionEvent.getRawY();
                    if (a != null) {
                        this.f7078f = rawX4 + CartConstant.KEY_YB_INFO_LINK + rawY4 + CartConstant.KEY_YB_INFO_LINK + (rawX4 - a[0]) + CartConstant.KEY_YB_INFO_LINK + (rawY4 - a[1]) + CartConstant.KEY_YB_INFO_LINK + currentTimeMillis4;
                    }
                    com.jd.stat.common.b.b.b(b, "MotionEvent.ACTION_MOVE  evMoveB = " + this.f7078f);
                } else if (i2 == 6) {
                    long currentTimeMillis5 = System.currentTimeMillis();
                    float rawX5 = motionEvent.getRawX();
                    float rawY5 = motionEvent.getRawY();
                    if (a != null) {
                        this.f7079g = rawX5 + CartConstant.KEY_YB_INFO_LINK + rawY5 + CartConstant.KEY_YB_INFO_LINK + (rawX5 - a[0]) + CartConstant.KEY_YB_INFO_LINK + (rawY5 - a[1]) + CartConstant.KEY_YB_INFO_LINK + currentTimeMillis5;
                    }
                    com.jd.stat.common.b.b.b(b, "MotionEvent.ACTION_MOVE  evMoveC = " + this.f7079g);
                }
            }
        }
        return null;
    }

    public boolean c(String str) {
        boolean z = com.jd.stat.security.d.a().j() && com.jd.stat.security.d.a().d();
        if (z && e(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_POSITION_ALL)) {
            return true;
        }
        return z && !TextUtils.isEmpty(str) && e(str);
    }

    public void d(String str) {
        if (str != null) {
            this.f7081i.remove(str);
        }
    }

    public void a(Activity activity) {
        if (activity == null) {
            return;
        }
        String canonicalName = activity.getClass().getCanonicalName();
        if (c(canonicalName)) {
            if (!this.f7081i.containsKey(canonicalName)) {
                List<WeakReference<View>> b2 = b(activity);
                if (b2.size() > 0) {
                    this.f7081i.put(canonicalName, b2);
                    return;
                }
                return;
            }
            this.f7081i.remove(canonicalName);
            List<WeakReference<View>> b3 = b(activity);
            if (b3.size() > 0) {
                this.f7081i.put(canonicalName, b3);
            }
        }
    }

    private float[] a(MotionEvent motionEvent, String str) {
        View view;
        List<WeakReference<View>> list = this.f7081i.get(str);
        if (list != null) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                WeakReference<View> weakReference = list.get(i2);
                if (weakReference != null && (view = weakReference.get()) != null) {
                    view.getLocationOnScreen(new int[2]);
                    if (a(view, motionEvent) && view != null && view.isShown()) {
                        if (com.jd.stat.common.b.b.a) {
                            com.jd.stat.common.b.b.b(b, "view.type = " + view.getClass().getCanonicalName());
                        }
                        return new float[]{r4[0] * 1.0f, r4[1] * 1.0f};
                    }
                }
            }
            return null;
        }
        return null;
    }

    private boolean a(View view, MotionEvent motionEvent) {
        if (view != null && motionEvent != null) {
            int[] iArr = new int[2];
            view.getLocationOnScreen(iArr);
            int i2 = iArr[0];
            int i3 = iArr[1];
            if (motionEvent.getX() >= i2 && motionEvent.getX() <= i2 + view.getWidth() && motionEvent.getY() >= i3 && motionEvent.getY() <= i3 + view.getHeight()) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0026, code lost:
        if (java.lang.Class.forName("androidx.recyclerview.widget.RecyclerView").isAssignableFrom(r5.getClass()) != false) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void a(List<WeakReference<View>> list, View view) {
        boolean z;
        if (view != null && (view instanceof ViewGroup)) {
            try {
                if (!Class.forName("android.support.v7.widget.RecyclerView").isAssignableFrom(view.getClass())) {
                }
                z = true;
            } catch (ClassNotFoundException e2) {
                e2.printStackTrace();
            } catch (NullPointerException e3) {
                e3.printStackTrace();
            }
            if ((view instanceof ListView) && !z) {
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                View view2 = null;
                for (int i2 = 0; i2 < childCount; i2++) {
                    try {
                        view2 = viewGroup.getChildAt(i2);
                    } catch (Exception unused) {
                    }
                    a(list, view2);
                }
                return;
            }
            list.add(new WeakReference<>(view));
        }
        return;
        z = false;
        if (view instanceof ListView) {
        }
        list.add(new WeakReference<>(view));
    }
}
