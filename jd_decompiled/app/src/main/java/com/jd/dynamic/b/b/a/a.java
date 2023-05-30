package com.jd.dynamic.b.b.a;

import androidx.core.util.Pair;
import com.jd.dynamic.base.CommFunction;
import com.jd.dynamic.base.IFunctionFactory;
import com.jd.dynamic.lib.b.a.a.f1;
import com.jd.dynamic.lib.b.a.a.g;
import com.jd.dynamic.lib.b.a.a.g1;
import com.jd.dynamic.lib.b.a.a.h1;
import com.jd.dynamic.lib.b.a.a.i1;
import com.jd.dynamic.lib.b.a.a.j1;
import com.jd.dynamic.lib.b.a.a.k1;
import com.jd.dynamic.lib.b.a.a.l1;
import com.jd.dynamic.lib.b.a.a.m1;
import com.jd.dynamic.lib.b.a.a.n1;
import com.jingdong.common.widget.NavigatorHolder;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class a implements IFunctionFactory {
    public static Pair<String, JSONObject> a(JSONObject jSONObject) {
        try {
            return new Pair<>(jSONObject.optString("class"), jSONObject.optJSONObject("params"));
        } catch (Exception e2) {
            e2.printStackTrace();
            return new Pair<>(null, null);
        }
    }

    @Override // com.jd.dynamic.base.IFunctionFactory
    public CommFunction createCommFunction(String str) {
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -1349088399:
                if (str.equals(NavigatorHolder.NaviEntity.TYPE_CUSTOM)) {
                    c2 = 0;
                    break;
                }
                break;
            case -1316951455:
                if (str.equals("dynMta")) {
                    c2 = 1;
                    break;
                }
                break;
            case -861311717:
                if (str.equals("condition")) {
                    c2 = 2;
                    break;
                }
                break;
            case 3143036:
                if (str.equals("file")) {
                    c2 = 3;
                    break;
                }
                break;
            case 3599307:
                if (str.equals("user")) {
                    c2 = 4;
                    break;
                }
                break;
            case 3600386:
                if (str.equals("util")) {
                    c2 = 5;
                    break;
                }
                break;
            case 3619493:
                if (str.equals("view")) {
                    c2 = 6;
                    break;
                }
                break;
            case 95467907:
                if (str.equals("delay")) {
                    c2 = 7;
                    break;
                }
                break;
            case 110364485:
                if (str.equals("timer")) {
                    c2 = '\b';
                    break;
                }
                break;
            case 1095692943:
                if (str.equals("request")) {
                    c2 = '\t';
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                return new g1();
            case 1:
                return new j1();
            case 2:
                return new f1();
            case 3:
                return new i1();
            case 4:
                return new l1();
            case 5:
                return new m1();
            case 6:
                return new n1();
            case 7:
                return new h1();
            case '\b':
                return new k1();
            case '\t':
                return new g();
            default:
                return null;
        }
    }
}
