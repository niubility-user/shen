package com.jd.dynamic.lib.viewparse.c.e;

import com.facebook.react.uimanager.ViewProps;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.base.DynamicMtaUtil;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.dynamic.lib.views.TagView;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes13.dex */
public class k0 extends p0<TagView> {
    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g(List list, TagView tagView) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            com.jd.dynamic.lib.utils.s.b((String) it.next(), null, this.a, tagView);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0055, code lost:
        if (r1.equals("data") == false) goto L10;
     */
    @Override // com.jd.dynamic.lib.viewparse.c.e.q0
    /* renamed from: f  reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void a(HashMap<String, String> hashMap, final TagView tagView) {
        Iterator<Map.Entry<String, String>> it = hashMap.entrySet().iterator();
        while (true) {
            char c2 = 3;
            int i2 = 0;
            if (!it.hasNext()) {
                long nanoTime = System.nanoTime();
                tagView.setAdapter();
                com.jd.dynamic.lib.utils.t.e("BindViewStep", DYConstants.DYN_TAG_VIEW, "bind time = " + DynamicMtaUtil.getCurMicroseconds(System.nanoTime() - nanoTime), "data = " + hashMap.get("data"));
                return;
            }
            Map.Entry<String, String> next = it.next();
            String str = hashMap.get(next.getKey());
            if (str != null) {
                String key = next.getKey();
                key.hashCode();
                switch (key.hashCode()) {
                    case -1989030046:
                        if (key.equals("minimumInterItemSpacing")) {
                            c2 = 0;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case -1621569922:
                        if (key.equals("dataListener")) {
                            c2 = 1;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case -1289582958:
                        if (key.equals("limitColumnSize")) {
                            c2 = 2;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case 3076010:
                        break;
                    case 99996865:
                        if (key.equals("minimumLineSpacing")) {
                            c2 = 4;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case 1288688105:
                        if (key.equals(ViewProps.ON_LAYOUT)) {
                            c2 = 5;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    default:
                        c2 = '\uffff';
                        break;
                }
                switch (c2) {
                    case 0:
                        tagView.setTagSpacing((int) com.jd.dynamic.lib.viewparse.d.a(str, tagView.getContext()));
                        break;
                    case 1:
                        final List<String> i3 = com.jd.dynamic.lib.utils.s.i(str);
                        tagView.post(new Runnable() { // from class: com.jd.dynamic.lib.viewparse.c.e.w
                            @Override // java.lang.Runnable
                            public final void run() {
                                k0.this.g(i3, tagView);
                            }
                        });
                        break;
                    case 2:
                        try {
                            i2 = Integer.parseInt(str);
                        } catch (Exception e2) {
                            DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_BIND, "AndroidTagViewParse parse limitColumnSize error", b(), d(), e2);
                        }
                        if (i2 == 0) {
                            i2 = Integer.MAX_VALUE;
                        }
                        tagView.setLimitColumnSize(i2);
                        break;
                    case 3:
                        tagView.setData(str);
                        break;
                    case 4:
                        tagView.setLineSpacing((int) com.jd.dynamic.lib.viewparse.d.a(str, tagView.getContext()));
                        break;
                    case 5:
                        tagView.setOnLayoutEvent(str);
                        break;
                }
            } else {
                return;
            }
        }
    }
}
