package com.jingdong.sdk.lib.puppetlayout.ylayout;

import com.jingdong.sdk.lib.puppetlayout.a;
import com.jingdong.sdk.lib.puppetlayout.b;
import com.jingdong.sdk.lib.puppetlayout.f.b.c;
import com.jingdong.sdk.lib.puppetlayout.ylayout.model.Action;
import com.jingdong.sdk.lib.puppetlayout.ylayout.model.Span;
import com.jingdong.sdk.lib.puppetlayout.ylayout.model.TemplateModel;
import com.jingdong.sdk.lib.puppetlayout.ylayout.model.TemplateViewBase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes8.dex */
public class TemplateParser {
    private static final String TAG = "TemplateParser";
    private a puppetCacheHandler;
    private b puppetViewIdGenerator;

    public TemplateParser(a aVar, b bVar) {
        this.puppetCacheHandler = aVar;
        this.puppetViewIdGenerator = bVar;
    }

    private com.jingdong.sdk.lib.puppetlayout.f.c.b createAttributes(TemplateViewBase templateViewBase, b bVar) {
        com.jingdong.sdk.lib.puppetlayout.f.c.b bVar2 = new com.jingdong.sdk.lib.puppetlayout.f.c.b(bVar);
        try {
            HashMap<String, String> hashMap = templateViewBase.layoutMap;
            if (hashMap != null) {
                for (String str : hashMap.keySet()) {
                    bVar2.a(str, templateViewBase.layoutMap.get(str), "layout", null);
                }
            }
            HashMap<String, String> hashMap2 = templateViewBase.attributeMap;
            if (hashMap2 != null) {
                for (String str2 : hashMap2.keySet()) {
                    bVar2.a(str2, templateViewBase.attributeMap.get(str2), "attribute", null);
                }
            }
            ArrayList<Action> arrayList = templateViewBase.actions;
            if (arrayList != null && arrayList.size() > 0) {
                for (int i2 = 0; i2 < templateViewBase.actions.size(); i2++) {
                    Action action = templateViewBase.actions.get(i2);
                    bVar2.a(action.key, action.value, "actions", action.m25clone());
                }
                if (com.jingdong.sdk.lib.puppetlayout.g.b.a) {
                    com.jingdong.sdk.lib.puppetlayout.g.b.a("setClickAction", "setClickAction createAttributes : " + templateViewBase.actions);
                }
            }
            ArrayList<Span> arrayList2 = templateViewBase.spans;
            if (arrayList2 != null && arrayList2.size() > 0) {
                bVar2.d(new ArrayList<>(templateViewBase.spans), templateViewBase.isDynamicSpans);
            }
        } catch (Exception e2) {
            if (com.jingdong.sdk.lib.puppetlayout.g.b.a) {
                e2.printStackTrace();
            }
            if (com.jingdong.sdk.lib.puppetlayout.g.b.a) {
                com.jingdong.sdk.lib.puppetlayout.g.b.a(TAG, "createAttributes jsonObject template : " + templateViewBase);
            }
        }
        return bVar2;
    }

    private c createNode(String str, TemplateViewBase templateViewBase, b bVar) {
        try {
            return com.jingdong.sdk.lib.puppetlayout.f.b.b.a(str, createAttributes(templateViewBase, bVar));
        } catch (Exception e2) {
            if (com.jingdong.sdk.lib.puppetlayout.g.b.a) {
                e2.printStackTrace();
            }
            if (com.jingdong.sdk.lib.puppetlayout.g.b.a) {
                com.jingdong.sdk.lib.puppetlayout.g.b.a(TAG, "createNode nodeName : " + str);
                return null;
            }
            return null;
        }
    }

    private String getNameByElemType(String str) {
        if (str.equals(TemplateViewBase.ELEM_TYPE_VIEW_GROUP)) {
            return "YLayout";
        }
        if (str.equals(TemplateViewBase.ELEM_TYPE_TEXT)) {
            return "TextWidget";
        }
        if (str.equals(TemplateViewBase.ELEM_TYPE_IMAGE)) {
            return "JDImageWidget";
        }
        if (str.equals(TemplateViewBase.ELEM_TYPE_BUTTON)) {
            return "ButtonWidget";
        }
        if (!str.equals(TemplateViewBase.ELEM_TYPE_RADIO)) {
            if (str.equals(TemplateViewBase.ELEM_TYPE_LINE)) {
                return "LineWidget";
            }
            if (str.equals(TemplateViewBase.ELEM_TYPE_CHECKBOX)) {
                return "CheckWidget";
            }
            if (str.equals(TemplateViewBase.ELEM_TYPE_VIEW)) {
                return "YLayout";
            }
            if (str.equals(TemplateViewBase.ELEM_TYPE_CAROUSEL)) {
                return TemplateViewBase.ELEM_TYPE_CAROUSEL;
            }
            if (str.equals(TemplateViewBase.ELEM_TYPE_HORIZONTAL_VIEW)) {
                return TemplateViewBase.ELEM_TYPE_HORIZONTAL_VIEW;
            }
            if (str.equals(TemplateViewBase.ELEM_TYPE_CUSTOM_WIDGET)) {
                return TemplateViewBase.ELEM_TYPE_CUSTOM_WIDGET;
            }
            if (str.equals(TemplateViewBase.ELEM_TYPE_SPAN_TEXT)) {
                return TemplateViewBase.ELEM_TYPE_SPAN_TEXT;
            }
        }
        return TemplateViewBase.getCustomElemType(str);
    }

    public synchronized com.jingdong.sdk.lib.puppetlayout.f.a parseModel2ViewTree(TemplateModel templateModel, boolean z) {
        com.jingdong.sdk.lib.puppetlayout.f.a a;
        c cVar = null;
        if (templateModel == null) {
            return null;
        }
        if (!z) {
            a aVar = this.puppetCacheHandler;
            if (aVar != null && (a = aVar.a(templateModel.templateId)) != null) {
                return a;
            }
        }
        try {
            cVar = parseViewNode(templateModel.template);
        } catch (Exception e2) {
            if (com.jingdong.sdk.lib.puppetlayout.g.b.a) {
                e2.printStackTrace();
            }
            if (com.jingdong.sdk.lib.puppetlayout.g.b.a) {
                com.jingdong.sdk.lib.puppetlayout.g.b.a(TAG, "parseModel2ViewTree mid : " + templateModel.templateId);
            }
        }
        return new com.jingdong.sdk.lib.puppetlayout.f.a(cVar);
    }

    public c parseViewNode(TemplateViewBase templateViewBase) {
        ArrayList<TemplateViewBase> arrayList;
        c cVar = null;
        if (templateViewBase == null) {
            return null;
        }
        try {
            cVar = createNode(getNameByElemType(templateViewBase.elemType), templateViewBase, this.puppetViewIdGenerator);
            if (cVar != null) {
                cVar.f15205e = templateViewBase.iterate;
            }
            if (cVar != null && (arrayList = templateViewBase.elems) != null && arrayList.size() > 0) {
                Iterator<TemplateViewBase> it = templateViewBase.elems.iterator();
                while (it.hasNext()) {
                    TemplateViewBase next = it.next();
                    c parseViewNode = parseViewNode(next);
                    if (parseViewNode != null) {
                        parseViewNode.f15205e = next.iterate;
                        cVar.a(parseViewNode);
                        if (TemplateViewBase.ELEM_TYPE_CAROUSEL.equals(parseViewNode.b)) {
                            TemplateViewBase templateViewBase2 = next.indicator1;
                            if (templateViewBase2 != null) {
                                cVar.a(parseViewNode(templateViewBase2));
                            }
                            TemplateViewBase templateViewBase3 = next.indicator2;
                            if (templateViewBase3 != null) {
                                cVar.a(parseViewNode(templateViewBase3));
                            }
                        }
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            if (com.jingdong.sdk.lib.puppetlayout.g.b.a) {
                com.jingdong.sdk.lib.puppetlayout.g.b.a(TAG, "parseViewNode template : " + templateViewBase);
            }
        }
        return cVar;
    }
}
