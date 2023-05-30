package com.jingdong.app.mall.bundle.xanimation.f;

import android.text.TextUtils;
import com.airbnb.lottie.animation.keyframe.TransformKeyframeAnimation;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class b {
    public static TransformKeyframeAnimation a(String str, List<com.jingdong.app.mall.bundle.xanimation.c> list) {
        TransformKeyframeAnimation transformKeyframeAnimation = null;
        if (list == null || list.isEmpty()) {
            return null;
        }
        if (!c.a(str)) {
            Iterator<com.jingdong.app.mall.bundle.xanimation.c> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                com.jingdong.app.mall.bundle.xanimation.c next = it.next();
                if (TextUtils.equals(str, next.b().getName())) {
                    transformKeyframeAnimation = next.c();
                    break;
                }
            }
        }
        return (transformKeyframeAnimation != null || list.isEmpty()) ? transformKeyframeAnimation : list.get(0).c();
    }
}
