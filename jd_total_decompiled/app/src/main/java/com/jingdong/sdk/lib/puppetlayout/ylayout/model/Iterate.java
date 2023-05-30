package com.jingdong.sdk.lib.puppetlayout.ylayout.model;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.sdk.lib.puppetlayout.f.b.c;
import com.jingdong.sdk.lib.puppetlayout.g.b;
import com.jingdong.sdk.lib.puppetlayout.h.a;
import com.jingdong.sdk.lib.puppetlayout.ylayout.PuppetContext;
import com.jingdong.sdk.lib.puppetlayout.ylayout.TemplateParser;

/* loaded from: classes8.dex */
public class Iterate {
    public String arrayVar;
    public String item;
    public TemplateViewBase itemViewBase;
    private Iterate parent = null;
    public String index = "index";
    private TemplateParser parser = new TemplateParser(null, null);

    private JDJSONArray getArrayFromData(String str, JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return null;
        }
        try {
            String[] split = str.split("\\.");
            JDJSONObject jDJSONObject2 = null;
            JDJSONArray jDJSONArray = null;
            for (int i2 = 0; i2 < split.length; i2++) {
                if (i2 == 0) {
                    if (i2 == split.length - 1) {
                        return jDJSONObject.optJSONArray(split[i2]);
                    }
                    jDJSONObject2 = jDJSONObject.optJSONObject(split[i2]);
                } else if (jDJSONObject2 == null) {
                    return jDJSONArray;
                } else {
                    if (i2 == split.length - 1) {
                        jDJSONArray = jDJSONObject2.optJSONArray(split[i2]);
                    } else {
                        jDJSONObject2 = jDJSONObject2.optJSONObject(split[i2]);
                    }
                }
            }
            return jDJSONArray;
        } catch (Exception e2) {
            if (b.a) {
                e2.printStackTrace();
            }
            return null;
        }
    }

    public void bindData(ViewGroup viewGroup, JDJSONObject jDJSONObject) {
        PuppetContext.bindView(viewGroup, jDJSONObject);
    }

    public void bindItemAction(JDJSONObject jDJSONObject) {
    }

    public void bindItemData(View view, Object obj, boolean z, boolean z2, int i2) {
        if (TextUtils.isEmpty(this.item)) {
            return;
        }
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("$" + this.item, obj);
        if (z && (obj instanceof JDJSONObject)) {
            ((JDJSONObject) obj).put("index_first", (Object) Boolean.TRUE);
        }
        if (z2 && (obj instanceof JDJSONObject)) {
            ((JDJSONObject) obj).put("index_last", (Object) Boolean.TRUE);
        }
        if (i2 >= 0 && (obj instanceof JDJSONObject)) {
            ((JDJSONObject) obj).put(this.index, (Object) Integer.valueOf(i2));
        }
        PuppetContext.bindView(view, jDJSONObject);
    }

    public ViewGroup createItemView(Context context, PuppetContext puppetContext) {
        c parseViewNode = this.parser.parseViewNode(this.itemViewBase);
        com.jingdong.sdk.lib.puppetlayout.d.b bVar = new com.jingdong.sdk.lib.puppetlayout.d.b();
        View b = parseViewNode.b(context, null, false, bVar, null, puppetContext);
        b.setTag(PuppetContext.KEY_VALUES, bVar);
        return (ViewGroup) b;
    }

    public void setData(a aVar, JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        JDJSONArray jDJSONArray = null;
        try {
            if (!this.arrayVar.isEmpty() && this.arrayVar.startsWith("{") && this.arrayVar.endsWith("}")) {
                String str = this.arrayVar;
                jDJSONArray = getArrayFromData(str.substring(1, str.length() - 1), jDJSONObject);
            }
            aVar.m(jDJSONObject, jDJSONArray, this);
        } catch (Exception e2) {
            if (b.a) {
                b.a("Iterate", "setAdapter Exception : " + e2);
            }
        }
    }
}
