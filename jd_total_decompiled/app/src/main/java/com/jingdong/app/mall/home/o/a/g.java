package com.jingdong.app.mall.home.o.a;

import android.os.SystemClock;
import com.jingdong.common.XView2.business.PermissionBridge;
import com.jingdong.common.entity.AddressGlobal;
import com.jingdong.common.lbs.businessAddress.JDGlobalAddressManager;
import com.jingdong.common.lbs.http.JDLbsHttpOption;
import com.jingdong.common.lbs.jdlocation.JDLocation;
import com.jingdong.common.lbs.jdlocation.JDLocationCache;
import com.jingdong.common.lbs.jdlocation.JDLocationCacheOption;
import com.jingdong.common.lbs.jdlocation.JDLocationError;
import com.jingdong.common.lbs.jdlocation.JDLocationListener;
import com.jingdong.common.lbs.jdlocation.JDLocationOption;
import com.jingdong.common.lbs.jdlocation.JDLocationSDK;
import com.tencent.map.geolocation.TencentLocationUtils;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes4.dex */
public class g {

    /* renamed from: g  reason: collision with root package name */
    private static volatile long f10473g;

    /* renamed from: i  reason: collision with root package name */
    private static final i f10475i;
    private static final JDLbsHttpOption a = new JDLbsHttpOption(PermissionBridge.HOME_COMMON_LBS_ID);
    private static final JDLocationOption b = new JDLocationOption();

    /* renamed from: c  reason: collision with root package name */
    private static final JDLocationCacheOption f10470c = new JDLocationCacheOption();
    private static final List<b> d = new CopyOnWriteArrayList();

    /* renamed from: e  reason: collision with root package name */
    private static final AtomicBoolean f10471e = new AtomicBoolean(false);

    /* renamed from: f  reason: collision with root package name */
    private static final i f10472f = new i();

    /* renamed from: h  reason: collision with root package name */
    private static final JDLocationListener f10474h = new a();

    /* loaded from: classes4.dex */
    class a implements JDLocationListener {
        a() {
        }

        @Override // com.jingdong.common.lbs.jdlocation.JDLocationListener
        public void onFail(JDLocationError jDLocationError) {
            g.m(jDLocationError);
        }

        @Override // com.jingdong.common.lbs.jdlocation.JDLocationListener
        public void onSuccess(JDLocation jDLocation) {
            if (g.k(jDLocation)) {
                g.n(jDLocation);
            } else {
                onFail(new JDLocationError());
            }
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class b {
        protected void onFail(JDLocationError jDLocationError) {
        }

        protected abstract void onLbsChanged(i iVar);
    }

    static {
        new i();
        f10475i = new i();
    }

    public static double d(@NotNull i iVar, @NotNull i iVar2) {
        if (iVar.e() || iVar2.e()) {
            return -1.0d;
        }
        if (iVar.f(iVar2)) {
            return 0.0d;
        }
        return TencentLocationUtils.distanceBetween(iVar.a, iVar.b, iVar2.a, iVar2.b);
    }

    public static i e() {
        AddressGlobal addressGlobal = JDGlobalAddressManager.getAddressGlobal(a);
        if (addressGlobal == null || addressGlobal.getAddressType() == 0) {
            return null;
        }
        i iVar = f10475i;
        iVar.j(addressGlobal);
        return iVar;
    }

    public static void f(long j2, b bVar) {
        h(PermissionBridge.HOME_COMMON_LBS_ID, j2, bVar);
    }

    public static void g(b bVar) {
        h(PermissionBridge.HOME_COMMON_LBS_ID, 300000L, bVar);
    }

    private static void h(String str, long j2, b bVar) {
        if (bVar == null) {
            return;
        }
        if (f.j0()) {
            bVar.onFail(new JDLocationError());
            return;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (f10473g > 0 && elapsedRealtime - f10473g < j2) {
            bVar.onLbsChanged(f10472f);
            l("\u9996\u9875\u5b9a\u4f4d\u7f13\u5b58,\u6709\u6548\u65f6\u95f4\u95f4\u9694: " + (elapsedRealtime - f10473g));
            return;
        }
        d.add(bVar);
        AtomicBoolean atomicBoolean = f10471e;
        if (atomicBoolean.get()) {
            return;
        }
        atomicBoolean.set(true);
        JDLocationOption jDLocationOption = b;
        jDLocationOption.setBusinessId(str);
        jDLocationOption.setNeedDetail(true);
        jDLocationOption.setSceneId("basicShoppingProcess");
        l("\u9996\u9875\u5b9a\u4f4d\uff1a\u4e1a\u52a1id= " + str);
        JDLocationSDK.getInstance().getAddress(jDLocationOption, f10474h);
    }

    public static JDLbsHttpOption i() {
        JDLbsHttpOption jDLbsHttpOption = a;
        jDLbsHttpOption.setBusinessId(PermissionBridge.HOME_COMMON_LBS_ID);
        jDLbsHttpOption.setSceneId("basicShoppingProcess");
        return jDLbsHttpOption;
    }

    public static JDLocation j() {
        JDLocationCacheOption jDLocationCacheOption = f10470c;
        jDLocationCacheOption.setBusinessId(PermissionBridge.HOME_COMMON_LBS_ID);
        jDLocationCacheOption.setSceneId("basicShoppingProcess");
        return JDLocationCache.getInstance().getLocation(jDLocationCacheOption);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean k(JDLocation jDLocation) {
        return (jDLocation == null || jDLocation.getLat() == jDLocation.getLng()) ? false : true;
    }

    public static void l(String str) {
        f.Z(true, g.class, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void m(JDLocationError jDLocationError) {
        try {
            Iterator<b> it = d.iterator();
            while (it.hasNext()) {
                it.next().onFail(jDLocationError);
            }
            d.clear();
            f10471e.set(false);
            StringBuilder sb = new StringBuilder();
            sb.append("\u9996\u9875\u5b9a\u4f4d\u5931\u8d25\uff1a");
            sb.append(jDLocationError == null ? "\u7ecf\u7eac\u5ea6\u76f8\u7b49" : jDLocationError.getMsg());
            l(sb.toString());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void n(JDLocation jDLocation) {
        try {
            f10472f.l(jDLocation);
            f10473g = SystemClock.elapsedRealtime();
            Iterator<b> it = d.iterator();
            while (it.hasNext()) {
                it.next().onLbsChanged(f10472f);
            }
            d.clear();
            f10471e.set(false);
            StringBuilder sb = new StringBuilder();
            sb.append("\u9996\u9875\u5b9a\u4f4d\u6210\u529f\uff1aLat= ");
            i iVar = f10472f;
            sb.append(iVar.a);
            sb.append(" Lng= ");
            sb.append(iVar.b);
            l(sb.toString());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
