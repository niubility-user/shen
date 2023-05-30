package com.jd.dynamic.lib.viewparse;

import android.text.TextUtils;
import android.view.View;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.dynamic.lib.utils.m;
import com.jd.dynamic.lib.viewparse.b.carouselView.j;
import com.jd.dynamic.lib.viewparse.e.b;
import com.jd.dynamic.lib.viewparse.e.c;
import com.jd.dynamic.lib.viewparse.e.e;
import com.jd.dynamic.lib.viewparse.e.g;
import com.jd.dynamic.lib.viewparse.e.h;
import com.jd.dynamic.lib.viewparse.e.i;
import com.jd.dynamic.lib.viewparse.e.k;
import com.jd.dynamic.lib.viewparse.e.l;
import com.jd.dynamic.lib.viewparse.e.n;
import com.jd.dynamic.lib.viewparse.e.p;
import com.jd.dynamic.lib.viewparse.e.q;
import com.jingdong.sdk.lib.puppetlayout.ylayout.model.TemplateViewBase;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.Map;

/* loaded from: classes13.dex */
public class f {
    public static <T extends View> p<T> a(String str, Map<String, String> map, DynamicTemplateEngine dynamicTemplateEngine, boolean z, boolean z2) {
        return b(str, map, dynamicTemplateEngine, z, z2, false);
    }

    public static <T extends View> p<T> b(String str, Map<String, String> map, DynamicTemplateEngine dynamicTemplateEngine, boolean z, boolean z2, boolean z3) {
        p<T> qVar;
        System.nanoTime();
        if (TextUtils.isEmpty(str)) {
            DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_PARSE, "ViewParseFactory create view error name is null", dynamicTemplateEngine == null ? null : dynamicTemplateEngine.getBizField(), null, R2.attr.loadProgressColor, new Exception(), dynamicTemplateEngine != null ? m.q(dynamicTemplateEngine.getZipVersion(), null) : null);
            p<T> pVar = new p<>();
            pVar.b(dynamicTemplateEngine);
            return pVar;
        }
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -1790321190:
                if (str.equals("YogaLayout")) {
                    c2 = 0;
                    break;
                }
                break;
            case -938935918:
                if (str.equals("TextView")) {
                    c2 = 1;
                    break;
                }
                break;
            case -690967933:
                if (str.equals(DYConstants.DYN_COLLECTION_VIEW)) {
                    c2 = 2;
                    break;
                }
                break;
            case -680675108:
                if (str.equals(DYConstants.DY_FLEXBOX_LAYOUT)) {
                    c2 = 3;
                    break;
                }
                break;
            case -673223970:
                if (str.equals("IconView")) {
                    c2 = 4;
                    break;
                }
                break;
            case -330082412:
                if (str.equals("MarqueeTextView")) {
                    c2 = 5;
                    break;
                }
                break;
            case 2583402:
                if (str.equals("Span")) {
                    c2 = 6;
                    break;
                }
                break;
            case 2666181:
                if (str.equals(TemplateViewBase.ELEM_TYPE_VIEW)) {
                    c2 = 7;
                    break;
                }
                break;
            case 82909838:
                if (str.equals("RichTextView")) {
                    c2 = '\b';
                    break;
                }
                break;
            case 115714367:
                if (str.equals(DYConstants.DYN_TAG_VIEW)) {
                    c2 = '\t';
                    break;
                }
                break;
            case 1125864064:
                if (str.equals("ImageView")) {
                    c2 = '\n';
                    break;
                }
                break;
            case 1666676343:
                if (str.equals("EditText")) {
                    c2 = 11;
                    break;
                }
                break;
            case 1785922696:
                if (str.equals("SectionCollectionView")) {
                    c2 = '\f';
                    break;
                }
                break;
            case 1884337221:
                if (str.equals(DYConstants.DY_CAROUSEL_VIEW)) {
                    c2 = '\r';
                    break;
                }
                break;
            case 2001146706:
                if (str.equals(TemplateViewBase.ELEM_TYPE_BUTTON)) {
                    c2 = 14;
                    break;
                }
                break;
            case 2059813682:
                if (str.equals("ScrollView")) {
                    c2 = 15;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
            case 3:
            case 7:
                if (!TextUtils.equals(map.get(DYConstants.DY_SHADOW_ENABLE), "1")) {
                    qVar = new q();
                    break;
                } else {
                    qVar = new q(true);
                    break;
                }
            case 1:
                qVar = new com.jd.dynamic.lib.viewparse.e.m();
                break;
            case 2:
            case '\f':
                qVar = new b();
                break;
            case 4:
                qVar = new n();
                break;
            case 5:
                qVar = new h();
                break;
            case 6:
                qVar = new k();
                break;
            case '\b':
                qVar = new i();
                break;
            case '\t':
                qVar = new l();
                break;
            case '\n':
                qVar = new g();
                break;
            case 11:
                qVar = new com.jd.dynamic.lib.viewparse.e.d();
                break;
            case '\r':
                qVar = new j();
                break;
            case 14:
                qVar = new com.jd.dynamic.lib.viewparse.e.a();
                break;
            case 15:
                if (map == null) {
                    qVar = new com.jd.dynamic.lib.viewparse.e.j();
                    break;
                } else if (!TextUtils.equals(DYConstants.DY_SCROLL_HORIZONTAL, map.get("scrollDirection"))) {
                    qVar = new com.jd.dynamic.lib.viewparse.e.j();
                    break;
                } else {
                    qVar = new e();
                    break;
                }
            default:
                if (DynamicSdk.getEngine().getCustomViewFactory() == null) {
                    qVar = new p<>();
                    break;
                } else {
                    qVar = new c();
                    break;
                }
        }
        qVar.b(dynamicTemplateEngine);
        qVar.c(z);
        qVar.g(z2);
        qVar.h(z3);
        return qVar;
    }
}
