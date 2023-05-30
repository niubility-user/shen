package com.jd.dynamic.lib.viewparse.c;

import com.jd.dynamic.DYConstants;
import com.jd.dynamic.lib.views.UnIconView;
import java.util.HashMap;
import java.util.Map;
import rx.Observable;
import rx.functions.Action1;

/* loaded from: classes13.dex */
public class s implements j<UnIconView> {
    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void c(UnIconView unIconView, Map.Entry entry) {
        String str = (String) entry.getValue();
        String str2 = (String) entry.getKey();
        str2.hashCode();
        char c2 = '\uffff';
        switch (str2.hashCode()) {
            case -1221029593:
                if (str2.equals("height")) {
                    c2 = 0;
                    break;
                }
                break;
            case -1067396154:
                if (str2.equals("trackId")) {
                    c2 = 1;
                    break;
                }
                break;
            case -339016920:
                if (str2.equals(DYConstants.SHOW_NAME)) {
                    c2 = 2;
                    break;
                }
                break;
            case 113126854:
                if (str2.equals("width")) {
                    c2 = 3;
                    break;
                }
                break;
            case 1096059565:
                if (str2.equals(DYConstants.RES_CODE)) {
                    c2 = 4;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                unIconView.height = str;
                return;
            case 1:
                unIconView.trackId = str;
                return;
            case 2:
                unIconView.showName = str;
                return;
            case 3:
                unIconView.width = str;
                return;
            case 4:
                unIconView.resCode = str;
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void d(Throwable th) {
    }

    @Override // com.jd.dynamic.lib.viewparse.c.j
    public /* bridge */ /* synthetic */ UnIconView a(HashMap hashMap, UnIconView unIconView) {
        UnIconView unIconView2 = unIconView;
        b(hashMap, unIconView2);
        return unIconView2;
    }

    public UnIconView b(HashMap<String, String> hashMap, final UnIconView unIconView) {
        Observable.from(hashMap.entrySet()).forEach(new Action1() { // from class: com.jd.dynamic.lib.viewparse.c.a
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                s.c(UnIconView.this, (Map.Entry) obj);
            }
        }, new Action1() { // from class: com.jd.dynamic.lib.viewparse.c.b
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                s.d((Throwable) obj);
            }
        });
        unIconView.onSetResFinish(hashMap);
        return unIconView;
    }
}
