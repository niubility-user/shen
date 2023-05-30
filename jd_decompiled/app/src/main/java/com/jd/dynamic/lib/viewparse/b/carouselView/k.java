package com.jd.dynamic.lib.viewparse.b.carouselView;

import com.jd.dynamic.lib.viewparse.c.e.a0;
import com.jd.dynamic.lib.viewparse.c.e.p0;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes13.dex */
public class k extends p0<f> {
    @Override // com.jd.dynamic.lib.viewparse.c.e.q0
    /* renamed from: f  reason: merged with bridge method [inline-methods] */
    public void a(HashMap<String, String> hashMap, f fVar) {
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            String str = hashMap.get(entry.getKey());
            String key = entry.getKey();
            key.hashCode();
            char c2 = '\uffff';
            switch (key.hashCode()) {
                case -1480965523:
                    if (key.equals("isAutoCycle")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case -437083024:
                    if (key.equals("defaultPage")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case 3076010:
                    if (key.equals("data")) {
                        c2 = 2;
                        break;
                    }
                    break;
                case 3575610:
                    if (key.equals("type")) {
                        c2 = 3;
                        break;
                    }
                    break;
                case 570418373:
                    if (key.equals("interval")) {
                        c2 = 4;
                        break;
                    }
                    break;
                case 591231242:
                    if (key.equals("loadMoreEnterText")) {
                        c2 = 5;
                        break;
                    }
                    break;
                case 1064436415:
                    if (key.equals("loadMoreFunc")) {
                        c2 = 6;
                        break;
                    }
                    break;
                case 1064508532:
                    if (key.equals("loadMoreIcon")) {
                        c2 = 7;
                        break;
                    }
                    break;
                case 1064838440:
                    if (key.equals("loadMoreText")) {
                        c2 = '\b';
                        break;
                    }
                    break;
                case 1359955401:
                    if (key.equals("onPageSelected")) {
                        c2 = '\t';
                        break;
                    }
                    break;
                case 1614714674:
                    if (key.equals("scrollDirection")) {
                        c2 = '\n';
                        break;
                    }
                    break;
                case 1900483250:
                    if (key.equals("scrollInterval")) {
                        c2 = 11;
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                    fVar.E("1".equals(str));
                    break;
                case 1:
                    fVar.I(a0.f(str, 0));
                    break;
                case 2:
                    fVar.F(str);
                    break;
                case 3:
                    fVar.H(str);
                    break;
                case 4:
                    fVar.G(a0.f(str, 0));
                    break;
                case 5:
                    fVar.M(str);
                    break;
                case 6:
                    fVar.J(str);
                    break;
                case 7:
                    fVar.K(str);
                    break;
                case '\b':
                    fVar.L(str);
                    break;
                case '\t':
                    fVar.N(str);
                    break;
                case '\n':
                    fVar.O(str);
                    break;
                case 11:
                    fVar.D(a0.f(str, 0));
                    break;
            }
        }
        fVar.d();
    }
}
