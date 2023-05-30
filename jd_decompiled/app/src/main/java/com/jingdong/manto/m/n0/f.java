package com.jingdong.manto.m.n0;

import android.view.View;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.c0;
import com.jingdong.manto.m.l0;
import com.jingdong.manto.q.n;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.MantoThreadUtils;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class f extends l0 {

    /* loaded from: classes15.dex */
    class a implements Runnable {
        final /* synthetic */ com.jingdong.manto.h a;
        final /* synthetic */ String[] b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ CountDownLatch f13455c;

        a(com.jingdong.manto.h hVar, String[] strArr, CountDownLatch countDownLatch) {
            this.a = hVar;
            this.b = strArr;
            this.f13455c = countDownLatch;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jingdong.manto.widget.g.d dVar;
            n pageView = c0.getPageView(this.a);
            if (pageView == null || (dVar = pageView.q) == null) {
                this.b[0] = f.this.putErrMsg("fail:page error", null, "getMenuButtonBoundingClientRect");
            } else {
                View menuButtonContainer = dVar.getMenuButtonContainer();
                if (menuButtonContainer == null) {
                    this.b[0] = f.this.putErrMsg("fail:menu error", null, "getMenuButtonBoundingClientRect");
                } else {
                    int[] iArr = new int[2];
                    menuButtonContainer.getLocationInWindow(iArr);
                    int pixel2dip = MantoDensityUtils.pixel2dip(iArr[0]);
                    int pixel2dip2 = MantoDensityUtils.pixel2dip(iArr[1]);
                    int pixel2dip3 = MantoDensityUtils.pixel2dip(menuButtonContainer.getWidth());
                    int pixel2dip4 = MantoDensityUtils.pixel2dip(menuButtonContainer.getHeight());
                    if (menuButtonContainer.getVisibility() == 8) {
                        pixel2dip3 = 0;
                        pixel2dip4 = 0;
                    }
                    HashMap hashMap = new HashMap();
                    hashMap.put("left", Integer.valueOf(pixel2dip));
                    hashMap.put("top", Integer.valueOf(pixel2dip2));
                    hashMap.put("right", Integer.valueOf(pixel2dip + pixel2dip3));
                    hashMap.put("bottom", Integer.valueOf(pixel2dip2 + pixel2dip4));
                    hashMap.put("width", Integer.valueOf(pixel2dip3));
                    hashMap.put("height", Integer.valueOf(pixel2dip4));
                    this.b[0] = f.this.putErrMsg(IMantoBaseModule.SUCCESS, hashMap, "getMenuButtonBoundingClientRect");
                }
            }
            this.f13455c.countDown();
        }
    }

    @Override // com.jingdong.manto.m.l0
    public String a(com.jingdong.manto.h hVar, JSONObject jSONObject) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        String[] strArr = new String[1];
        MantoThreadUtils.runOnUIThread(new a(hVar, strArr, countDownLatch));
        try {
            countDownLatch.await(2000L, TimeUnit.MILLISECONDS);
        } catch (Throwable unused) {
            strArr[0] = putErrMsg("fail:internal error", null, "getMenuButtonBoundingClientRect");
        }
        return strArr[0];
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "getMenuButtonBoundingClientRect";
    }
}
