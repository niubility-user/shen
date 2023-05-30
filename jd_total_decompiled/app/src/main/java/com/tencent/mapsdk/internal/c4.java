package com.tencent.mapsdk.internal;

import android.text.TextUtils;
import com.jingdong.sdk.lib.puppetlayout.ylayout.model.Action;
import com.jingdong.sdk.lib.puppetlayout.ylayout.model.TemplateViewBase;
import com.tencent.map.tools.json.JsonParser;
import com.tencent.map.tools.json.JsonUtils;
import com.tencent.mapsdk.internal.f4;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class c4 implements JsonParser.Deserializer<f4.a.C0792a.AbstractC0793a> {
    @Override // com.tencent.map.tools.json.JsonParser.Deserializer
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public f4.a.C0792a.AbstractC0793a deserialize(Object obj, String str, Object obj2) {
        Object parseToModel;
        if (obj2 != null && (obj2 instanceof JSONObject)) {
            JSONObject jSONObject = (JSONObject) obj2;
            String string = jSONObject.getString("type");
            if (TextUtils.isEmpty(string)) {
                return null;
            }
            if (Action.ActionType_Point.equalsIgnoreCase(string)) {
                parseToModel = JsonUtils.parseToModel(jSONObject, f4.a.C0792a.d.class, new Object[0]);
            } else if ("Points".equalsIgnoreCase(string)) {
                parseToModel = JsonUtils.parseToModel(jSONObject, f4.a.C0792a.e.class, new Object[0]);
            } else if (TemplateViewBase.ELEM_TYPE_LINE.equalsIgnoreCase(string)) {
                parseToModel = JsonUtils.parseToModel(jSONObject, f4.a.C0792a.b.class, new Object[0]);
            } else if (!"Model".equalsIgnoreCase(string)) {
                return null;
            } else {
                parseToModel = JsonUtils.parseToModel(jSONObject, f4.a.C0792a.c.class, new Object[0]);
            }
            return (f4.a.C0792a.AbstractC0793a) parseToModel;
        }
        return null;
    }
}
