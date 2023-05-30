package com.jingdong.manto.m;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import androidx.core.app.NotificationCompat;
import com.jingdong.common.jdmiaosha.utils.cache.JDNetCacheManager;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.MantoUtils;
import com.jingdong.manto.widget.MantoStatusBarUtil;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.jweb.JWebType;
import com.jingdong.sdk.language.LanguageController;
import java.util.HashMap;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* loaded from: classes15.dex */
public class f0 extends l0 {

    /* loaded from: classes15.dex */
    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[JWebType.values().length];
            a = iArr;
            try {
                iArr[JWebType.WV_TYPE_SYS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[JWebType.MV_TYPE_X5_SYS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[JWebType.MV_TYPE_X5_X5.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private static View a(com.jingdong.manto.h hVar) {
        com.jingdong.manto.q.n pageView = c0.getPageView(hVar);
        if (pageView == null) {
            return null;
        }
        return pageView.o();
    }

    @Override // com.jingdong.manto.m.l0
    public String a(com.jingdong.manto.h hVar, JSONObject jSONObject) {
        int[] iArr;
        int i2;
        HashMap hashMap = new HashMap();
        hashMap.put(JDNetCacheManager.BRAND_BIZKEY, BaseInfo.getDeviceBrand());
        hashMap.put(CustomThemeConstance.NAVI_MODEL, BaseInfo.getDeviceModel());
        if (hVar.p() != null) {
            hashMap.put("pixelRatio", Float.valueOf(MantoDensityUtils.getDensity(hVar.p())));
        }
        Rect b = com.jingdong.manto.utils.y.b(hVar.p());
        int i3 = b.left;
        int i4 = b.top;
        int i5 = b.right;
        int i6 = b.bottom;
        int i7 = i5 - i3;
        int i8 = i6 - i4;
        char c2 = 1;
        if (a(hVar) != null && a(hVar).getWidth() > 0) {
            iArr = new int[]{MantoDensityUtils.pixel2dip(a(hVar).getWidth()), MantoDensityUtils.pixel2dip(a(hVar).getHeight())};
        } else if (hVar.p() instanceof Activity) {
            Rect rect = new Rect();
            hVar.p().getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            int[] iArr2 = {MantoDensityUtils.pixel2dip(rect.right - rect.left), MantoDensityUtils.pixel2dip((rect.bottom - rect.top) - MantoDensityUtils.dip2pixel(hVar.p(), 48))};
            if (hVar != null) {
                if (hVar.h() != null && hVar.h().f13014f != null) {
                    com.jingdong.manto.q.j firstPage = hVar.h().f13014f.getFirstPage();
                    boolean z = firstPage instanceof com.jingdong.manto.q.q;
                    if (firstPage != null) {
                        com.jingdong.manto.q.n i9 = firstPage.i();
                        int dip2pixel = (i9 == null || !i9.x) ? (rect.bottom - rect.top) - MantoDensityUtils.dip2pixel(hVar.p(), 48) : rect.bottom;
                        if (z) {
                            dip2pixel -= ((com.jingdong.manto.q.q) firstPage).f14092i.getHeight();
                        }
                        iArr = new int[]{MantoDensityUtils.pixel2dip(rect.right - rect.left), MantoDensityUtils.pixel2dip(dip2pixel)};
                        c2 = 1;
                    }
                }
            }
            iArr = iArr2;
            c2 = 1;
        } else {
            c2 = 1;
            iArr = new int[]{i5, i6};
        }
        hashMap.put("windowWidth", Integer.valueOf(iArr[0]));
        hashMap.put("windowHeight", Integer.valueOf(iArr[c2]));
        hashMap.put("screenWidth", Integer.valueOf(i5));
        hashMap.put("screenHeight", Integer.valueOf(i6));
        HashMap hashMap2 = new HashMap();
        hashMap2.put("left", Integer.valueOf(i3));
        hashMap2.put("top", Integer.valueOf(i4));
        hashMap2.put("right", Integer.valueOf(i5));
        hashMap2.put("bottom", Integer.valueOf(i6));
        hashMap2.put("width", Integer.valueOf(i7));
        hashMap2.put("height", Integer.valueOf(i8));
        try {
            i2 = MantoDensityUtils.pixel2dip(MantoStatusBarUtil.getStatusBarHeight(hVar.p()));
        } catch (Exception unused) {
            i2 = 24;
        }
        hashMap.put("statusBarHeight", Integer.valueOf(i2));
        hashMap.put(IjkMediaMeta.IJKM_KEY_LANGUAGE, LanguageController.LANGUAGE_CODE_ZH_CN);
        hashMap.put("version", 1);
        hashMap.put("hostVersionName", com.jingdong.manto.b.g().b("versionName"));
        hashMap.put("hostCode", com.jingdong.manto.b.g().b("versionCode"));
        hashMap.put("system", "Android " + Build.VERSION.RELEASE);
        hashMap.put("mapEnabled", Boolean.valueOf(MantoUtils.hasInstalledMapApp(com.jingdong.manto.c.a())));
        hashMap.put("safeArea", hashMap2);
        hashMap.put(CustomThemeConstance.TABLE_NAME, com.jingdong.manto.k.a.b().a() == 0 ? FontsUtil.KEY_MULTI_LIGHT : CustomThemeConstance.NAVI_IMAGE_DARK_TAG);
        hashMap.put("hostName", com.jingdong.manto.b.g().b(Configuration.PARTNER));
        hashMap.put("service", hVar.g().getName());
        com.jingdong.manto.q.n pageView = c0.getPageView(hVar);
        if (pageView != null && pageView.s() != null) {
            int i10 = a.a[pageView.s().getWebType().ordinal()];
            hashMap.put("webview", i10 != 1 ? i10 != 2 ? i10 != 3 ? "" : "x5" : "x5_sys" : NotificationCompat.CATEGORY_SYSTEM);
        }
        return putErrMsg(IMantoBaseModule.SUCCESS, hashMap);
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "getSystemInfo";
    }
}
