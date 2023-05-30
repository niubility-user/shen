package com.jingdong.manto.m.u0.o;

import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.text.TextUtils;
import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes15.dex */
public final class t implements i {
    private static boolean a(com.jingdong.manto.m.u0.c cVar, String str) {
        PorterDuffXfermode porterDuffXfermode;
        com.jingdong.manto.m.u0.n nVar = cVar.f13730f;
        if (TextUtils.equals(str, "luminosity")) {
            return true;
        }
        if (TextUtils.equals(str, "destination-out")) {
            porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
        } else if (TextUtils.equals(str, "source-in")) {
            porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        } else if (TextUtils.equals(str, "darken")) {
            porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DARKEN);
        } else if (TextUtils.equals(str, "source-out")) {
            porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT);
        } else if (TextUtils.equals(str, "overlay")) {
            porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.OVERLAY);
        } else if (TextUtils.equals(str, "source-atop")) {
            porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP);
        } else if (TextUtils.equals(str, "source-over")) {
            porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER);
        } else if (TextUtils.equals(str, "xor")) {
            porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.XOR);
        } else if (TextUtils.equals(str, JDViewKitEventHelper.ActionType_Copy)) {
            porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.ADD);
        } else if (TextUtils.equals(str, "lighten")) {
            porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.LIGHTEN);
        } else if (TextUtils.equals(str, "lighter")) {
            porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.LIGHTEN);
        } else if (TextUtils.equals(str, "multiply")) {
            porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY);
        } else if (TextUtils.equals(str, "destination-in")) {
            porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        } else if (TextUtils.equals(str, "destination-atop")) {
            porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP);
        } else if (!TextUtils.equals(str, "destination-over")) {
            if (TextUtils.equals(str, "color-burn") || TextUtils.equals(str, "exclusion") || TextUtils.equals(str, "difference") || TextUtils.equals(str, "color-dodge") || TextUtils.equals(str, "hue") || TextUtils.equals(str, "hard-light") || TextUtils.equals(str, "soft-light") || TextUtils.equals(str, "saturation")) {
                return true;
            }
            TextUtils.equals(str, "color");
            return true;
        } else {
            porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER);
        }
        nVar.setXfermode(porterDuffXfermode);
        return true;
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final String a() {
        return "setGlobalCompositeOperation";
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, com.jingdong.manto.m.u0.o.k0.c0.c cVar2) {
        com.jingdong.manto.m.u0.o.k0.q qVar = (com.jingdong.manto.m.u0.o.k0.q) cVar2;
        if (qVar == null) {
            return false;
        }
        return a(cVar, qVar.b);
    }

    @Override // com.jingdong.manto.m.u0.o.i
    public final boolean a(com.jingdong.manto.m.u0.c cVar, Canvas canvas, JSONArray jSONArray) {
        if (jSONArray.length() <= 0) {
            return false;
        }
        try {
            return a(cVar, jSONArray.getString(0));
        } catch (JSONException unused) {
            return false;
        }
    }
}
