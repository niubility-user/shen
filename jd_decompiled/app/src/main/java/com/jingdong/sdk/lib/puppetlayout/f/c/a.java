package com.jingdong.sdk.lib.puppetlayout.f.c;

import android.text.TextUtils;
import com.facebook.react.uimanager.ViewProps;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.sdk.lib.puppetlayout.view.ui.builder.n;
import com.jingdong.sdk.lib.puppetlayout.ylayout.DynamicHelper;
import com.jingdong.sdk.lib.puppetlayout.ylayout.ThreeParser;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/* loaded from: classes8.dex */
public class a {
    private String a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    public String f15206c;
    public int d;

    /* renamed from: e  reason: collision with root package name */
    private ThreeParser f15207e;

    /* renamed from: f  reason: collision with root package name */
    private DynamicHelper f15208f;

    public a(String str, String str2) {
        this.f15206c = "attribute";
        this.d = 0;
        this.f15207e = null;
        this.f15208f = new DynamicHelper();
        this.a = str;
        this.b = str2;
    }

    public String a() {
        return this.b;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v13 */
    public void b(com.jingdong.sdk.lib.puppetlayout.h.a aVar, JDJSONObject jDJSONObject) {
        List<String> dynamicList;
        String str;
        String valueFromData;
        LinkedHashMap<String, String> linkedHashMap;
        List<String> list;
        if (jDJSONObject != null) {
            try {
                if (this.b == null) {
                    return;
                }
                if (this.d == 1) {
                    if (this.f15207e == null) {
                        ThreeParser threeParser = new ThreeParser();
                        this.f15207e = threeParser;
                        threeParser.compile(this.b);
                    }
                    ThreeParser threeParser2 = this.f15207e;
                    if (threeParser2 != null) {
                        Object valueFromEL = threeParser2.getValueFromEL(jDJSONObject);
                        if (valueFromEL instanceof String) {
                            str = (String) valueFromEL;
                            list = null;
                            dynamicList = list;
                            linkedHashMap = list;
                        }
                    }
                    str = null;
                    list = null;
                    dynamicList = list;
                    linkedHashMap = list;
                } else {
                    LinkedHashMap<String, String> linkedHashMap2 = new LinkedHashMap<>();
                    dynamicList = DynamicHelper.getDynamicList(aVar, this.b);
                    for (String str2 : dynamicList) {
                        if (!linkedHashMap2.containsKey(str2) && (valueFromData = DynamicHelper.getValueFromData(aVar.b, str2, jDJSONObject)) != null) {
                            linkedHashMap2.put(str2, valueFromData);
                        }
                    }
                    if (linkedHashMap2.size() > 0) {
                        if (aVar instanceof n) {
                            ((n) aVar).B(this.b, linkedHashMap2);
                        }
                        str = DynamicHelper.replaceAllValue(this.b, linkedHashMap2);
                        linkedHashMap = linkedHashMap2;
                    } else {
                        str = null;
                        linkedHashMap = linkedHashMap2;
                    }
                }
                String str3 = "none";
                if (str != null) {
                    if ("layout".equals(this.f15206c)) {
                        if (ViewProps.DISPLAY.equals(this.a)) {
                            if (this.d != 1) {
                                String str4 = this.a;
                                if (!TextUtils.isEmpty(str)) {
                                    str3 = ViewProps.FLEX;
                                }
                                aVar.o(str4, str3);
                                return;
                            }
                            aVar.o(this.a, str);
                            return;
                        }
                        aVar.o(this.a, str);
                        return;
                    }
                    aVar.r(this.a, str);
                } else if ("hiddenType".equals(this.a)) {
                    aVar.r("hiddenType", "");
                } else if ("showType".equals(this.a)) {
                    aVar.r("showType", "");
                } else if (!"imageUrl".equals(this.a)) {
                    if ("layout".equals(this.f15206c) && ViewProps.DISPLAY.equals(this.a)) {
                        aVar.o(this.a, "none");
                    } else if ((aVar instanceof n) && this.a.equals("text")) {
                        aVar.r("text", null);
                    }
                } else if (dynamicList != null) {
                    Iterator<String> it = dynamicList.iterator();
                    while (it.hasNext()) {
                        linkedHashMap.put(it.next(), "");
                    }
                    String replaceAllValue = DynamicHelper.replaceAllValue(this.b, linkedHashMap);
                    if (replaceAllValue != null) {
                        aVar.r("imageUrl", replaceAllValue);
                    }
                }
            } catch (Exception e2) {
                if (com.jingdong.sdk.lib.puppetlayout.g.b.a) {
                    com.jingdong.sdk.lib.puppetlayout.g.b.a("PuppetNodeDynamicProperty", "PuppetNodeDynamicProperty Exception : " + e2);
                }
            }
        }
    }

    public a(String str, String str2, String str3) {
        this(str, str2);
        this.f15206c = str3;
    }

    public a(String str, String str2, String str3, int i2) {
        this(str, str2);
        this.f15206c = str3;
        this.d = i2;
    }
}
