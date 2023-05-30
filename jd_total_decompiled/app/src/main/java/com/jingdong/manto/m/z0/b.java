package com.jingdong.manto.m.z0;

import android.graphics.Color;
import android.text.TextUtils;
import com.facebook.react.uimanager.ViewProps;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.q.n;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoUtils;
import com.jingdong.manto.utils.c;
import com.jingdong.manto.widget.input.a0.a;
import java.lang.ref.WeakReference;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes15.dex */
public abstract class b<P extends com.jingdong.manto.widget.input.a0.a> extends d0 {
    /* JADX INFO: Access modifiers changed from: protected */
    public static n a(int i2) {
        WeakReference weakReference;
        c.a a = com.jingdong.manto.utils.c.a().a("JsInput@" + i2);
        if (a == null || (weakReference = (WeakReference) a.a("webview_reference")) == null) {
            return null;
        }
        return (n) weakReference.get();
    }

    public static void a(int i2, n nVar) {
        if (nVar != null) {
            com.jingdong.manto.utils.c.a().a("JsInput@" + i2, true).a("webview_reference", new WeakReference(nVar));
        }
    }

    public static void a(int i2, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        MantoLog.i("JsApiInputBase", "cache: data:" + str);
        com.jingdong.manto.utils.c.a().a("JsInput@" + i2, true).b("passing_data", str);
    }

    public static String b(int i2) {
        return com.jingdong.manto.utils.c.a().a("JsInput@" + i2, true).a("passing_data", "");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean a(P p, JSONObject jSONObject, n nVar, int i2) {
        JSONObject jSONObject2;
        try {
            jSONObject2 = new JSONObject(jSONObject.getString(DeeplinkProductDetailHelper.LAYER_STYLE));
            try {
                p.f14402c = Integer.valueOf(MantoDensityUtils.convertToDeviceSize(jSONObject2, "width"));
                try {
                    p.d = Integer.valueOf(MantoDensityUtils.convertToDeviceSize(jSONObject2, "height"));
                } catch (Exception unused) {
                }
                p.f14403e = Integer.valueOf(MantoDensityUtils.convertToDeviceSize(jSONObject2, "top"));
                p.f14404f = Integer.valueOf(MantoDensityUtils.convertToDeviceSize(jSONObject2, "left"));
            } catch (Exception unused2) {
            }
        } catch (Exception unused3) {
            jSONObject2 = null;
        }
        if (jSONObject2 != null) {
            try {
                p.b = Boolean.valueOf(jSONObject2.optBoolean("inputScrollY", false));
            } catch (Throwable unused4) {
            }
            p.f14411m = jSONObject2.optString(ViewProps.FONT_WEIGHT, null);
            try {
                p.f14405g = Integer.valueOf(MantoDensityUtils.convertToDeviceSize(jSONObject2, ViewProps.MIN_HEIGHT));
            } catch (Exception unused5) {
            }
            try {
                p.f14406h = Integer.valueOf(MantoDensityUtils.convertToDeviceSize(jSONObject2, "maxHeight"));
            } catch (Exception unused6) {
            }
            p.f14407i = jSONObject2.optString(ViewProps.TEXT_ALIGN, null);
            try {
                p.f14409k = Integer.valueOf(Color.parseColor(jSONObject2.getString("color")));
            } catch (Exception unused7) {
            }
            try {
                p.f14408j = Integer.valueOf(Color.parseColor(jSONObject2.getString(ViewProps.BACKGROUND_COLOR)));
            } catch (Exception unused8) {
            }
            try {
                p.f14410l = Float.valueOf(MantoDensityUtils.convertToDeviceSizeByFloat(jSONObject2, ViewProps.FONT_SIZE));
            } catch (Exception unused9) {
            }
            try {
                p.y = Integer.valueOf(MantoDensityUtils.convertToDeviceSize(jSONObject2, "marginBottom"));
            } catch (Exception unused10) {
                p.y = null;
            }
            Integer a = com.jingdong.manto.widget.input.a0.e.a(jSONObject2.optString("lineSpace"));
            p.C = a;
            if (a != null) {
                p.C = Integer.valueOf(MantoDensityUtils.convertToDeviceSizeByInt(a.intValue()));
            }
            Integer a2 = com.jingdong.manto.widget.input.a0.e.a(jSONObject2.optString(ViewProps.LINE_HEIGHT));
            p.D = a2;
            if (a2 != null) {
                p.D = Integer.valueOf(MantoDensityUtils.convertToDeviceSizeByInt(a2.intValue()));
            }
        }
        try {
            p.w = Boolean.valueOf(jSONObject.getBoolean(DYConstants.DY_AUTO_SIZE));
        } catch (Exception unused11) {
        }
        p.a = jSONObject.optString("defaultValue", jSONObject.optString("value", null));
        try {
            p.f14412n = Integer.valueOf(jSONObject.getInt("maxLength"));
        } catch (Exception unused12) {
        }
        p.o = jSONObject.optString("placeholder");
        try {
            JSONObject jSONObject3 = new JSONObject(jSONObject.getString("placeholderStyle"));
            p.r = Integer.valueOf(Color.parseColor(jSONObject3.getString("color")));
            p.q = Integer.valueOf(MantoDensityUtils.convertToDeviceSize(jSONObject3, ViewProps.FONT_SIZE));
            p.p = jSONObject3.optString(ViewProps.FONT_WEIGHT, "normal");
        } catch (Exception unused13) {
        }
        try {
            p.t = Boolean.valueOf(jSONObject.getBoolean(ViewProps.HIDDEN));
        } catch (JSONException unused14) {
            p.t = null;
        }
        try {
            p.s = Boolean.valueOf(jSONObject.getBoolean("disabled"));
        } catch (Exception unused15) {
            p.s = null;
        }
        try {
            p.x = Boolean.valueOf(jSONObject.getBoolean("fixed"));
        } catch (Exception unused16) {
            p.x = null;
        }
        p.z = com.jingdong.manto.widget.input.a0.b.a(jSONObject.optString("confirmType"));
        try {
            p.A = Boolean.valueOf(jSONObject.getBoolean("confirmHold"));
        } catch (Exception unused17) {
            p.A = null;
        }
        p.B = MantoUtils.isTrue(jSONObject.opt("adjustPosition"));
        return true;
    }
}
