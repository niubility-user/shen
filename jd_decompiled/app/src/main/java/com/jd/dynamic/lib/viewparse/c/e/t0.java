package com.jd.dynamic.lib.viewparse.c.e;

import com.jd.dynamic.lib.views.SpanView;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes13.dex */
public class t0 extends p0<SpanView> {
    @Override // com.jd.dynamic.lib.viewparse.c.e.q0
    /* renamed from: f  reason: merged with bridge method [inline-methods] */
    public void a(HashMap<String, String> hashMap, SpanView spanView) {
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            String value = entry.getValue();
            String key = entry.getKey();
            key.hashCode();
            char c2 = '\uffff';
            switch (key.hashCode()) {
                case -1867567750:
                    if (key.equals("subtype")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case -1351902487:
                    if (key.equals("onClick")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case 114148:
                    if (key.equals("src")) {
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
                case 220498249:
                    if (key.equals("selectedSrc")) {
                        c2 = 4;
                        break;
                    }
                    break;
                case 1844968884:
                    if (key.equals("newLine")) {
                        c2 = 5;
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                    spanView.subtype = value;
                    break;
                case 1:
                    spanView.onClick = value;
                    break;
                case 2:
                    spanView.src = value;
                    break;
                case 3:
                    spanView.type = value;
                    break;
                case 4:
                    spanView.selectedSrc = value;
                    break;
                case 5:
                    spanView.newLine = value;
                    break;
            }
        }
    }
}
