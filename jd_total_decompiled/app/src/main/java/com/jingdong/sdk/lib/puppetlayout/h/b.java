package com.jingdong.sdk.lib.puppetlayout.h;

import android.util.LruCache;
import com.jingdong.sdk.lib.puppetlayout.view.ui.builder.CarouselCreator;
import com.jingdong.sdk.lib.puppetlayout.view.ui.builder.HorizontalViewCreator;
import com.jingdong.sdk.lib.puppetlayout.view.ui.builder.c;
import com.jingdong.sdk.lib.puppetlayout.view.ui.builder.g;
import com.jingdong.sdk.lib.puppetlayout.view.ui.builder.h;
import com.jingdong.sdk.lib.puppetlayout.view.ui.builder.i;
import com.jingdong.sdk.lib.puppetlayout.view.ui.builder.j;
import com.jingdong.sdk.lib.puppetlayout.view.ui.builder.k;
import com.jingdong.sdk.lib.puppetlayout.view.ui.builder.l;
import com.jingdong.sdk.lib.puppetlayout.view.ui.builder.m;
import com.jingdong.sdk.lib.puppetlayout.view.ui.builder.n;
import com.jingdong.sdk.lib.puppetlayout.view.ui.builder.o;
import com.jingdong.sdk.lib.puppetlayout.view.ui.builder.p;
import com.jingdong.sdk.lib.puppetlayout.ylayout.PuppetContext;
import com.jingdong.sdk.lib.puppetlayout.ylayout.model.TemplateViewBase;
import java.util.HashMap;

/* loaded from: classes8.dex */
public class b {
    private static HashMap<String, Class<? extends a>> a = new HashMap<>();

    static {
        new LruCache(30);
        try {
            a.put("TextWidget", n.class);
            a.put("ButtonWidget", com.jingdong.sdk.lib.puppetlayout.view.ui.builder.a.class);
            a.put("CheckWidget", com.jingdong.sdk.lib.puppetlayout.view.ui.builder.b.class);
            a.put("ImageWidget", h.class);
            a.put("JDImageWidget", k.class);
            a.put("LineWidget", l.class);
            a.put("Widget", o.class);
            a.put("YLayout", p.class);
            a.put(TemplateViewBase.ELEM_TYPE_CAROUSEL, CarouselCreator.class);
            a.put(TemplateViewBase.ELEM_TYPE_INDICATOR_1, i.class);
            a.put(TemplateViewBase.ELEM_TYPE_INDICATOR_2, j.class);
            a.put(TemplateViewBase.ELEM_TYPE_HORIZONTAL_VIEW, HorizontalViewCreator.class);
            a.put(TemplateViewBase.ELEM_TYPE_CUSTOM_WIDGET, g.class);
            a.put(TemplateViewBase.ELEM_TYPE_SPAN_TEXT, m.class);
            a.put("Countdown", c.class);
        } catch (Exception e2) {
            if (com.jingdong.sdk.lib.puppetlayout.g.b.a) {
                e2.printStackTrace();
            }
        }
    }

    public static a a(PuppetContext puppetContext, String str, String str2) {
        if ("TextWidget".equals(str)) {
            return new n();
        }
        if ("ButtonWidget".equals(str)) {
            return new com.jingdong.sdk.lib.puppetlayout.view.ui.builder.a();
        }
        if ("CheckWidget".equals(str)) {
            return new com.jingdong.sdk.lib.puppetlayout.view.ui.builder.b();
        }
        if ("ImageWidget".equals(str)) {
            return new h();
        }
        if ("JDImageWidget".equals(str)) {
            return new k();
        }
        if ("LineWidget".equals(str)) {
            return new l();
        }
        if ("Widget".equals(str)) {
            return new o();
        }
        if ("YLayout".equals(str)) {
            return new p();
        }
        if (TemplateViewBase.ELEM_TYPE_CAROUSEL.equals(str)) {
            return new CarouselCreator();
        }
        if (TemplateViewBase.ELEM_TYPE_INDICATOR_1.equals(str)) {
            return new i();
        }
        if (TemplateViewBase.ELEM_TYPE_INDICATOR_2.equals(str)) {
            return new j();
        }
        if (TemplateViewBase.ELEM_TYPE_HORIZONTAL_VIEW.equals(str)) {
            return new HorizontalViewCreator();
        }
        if (!TemplateViewBase.ELEM_TYPE_CARD_BUTTON.equals(str)) {
            if (TemplateViewBase.ELEM_TYPE_SPAN_TEXT.equals(str)) {
                return new m();
            }
            if (TemplateViewBase.ELEM_TYPE_CUSTOM_WIDGET.equals(str)) {
                return puppetContext.createFromClass(str2);
            }
            if ("Countdown".equals(str)) {
                return new c();
            }
        }
        return null;
    }
}
