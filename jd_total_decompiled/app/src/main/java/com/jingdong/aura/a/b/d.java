package com.jingdong.aura.a.b;

import com.jingdong.aura.wrapper.AuraDowngradeBundle;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes4.dex */
public class d {
    public static List<AuraDowngradeBundle> a = new CopyOnWriteArrayList();

    public static void a(List<AuraDowngradeBundle> list) {
        a.clear();
        if (list == null) {
            return;
        }
        a.addAll(list);
    }
}
