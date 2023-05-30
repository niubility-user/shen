package com.jingdong.manto.pkg.c;

import android.content.Context;
import com.jingdong.manto.b;
import com.jingdong.manto.jsapi.auth.tools.AuthInfo;
import com.jingdong.manto.pkg.db.entity.DomainBlackListEntity;
import com.jingdong.manto.pkg.db.entity.LocalExtAuthEntity;
import com.jingdong.manto.pkg.db.entity.MantoAuthEntity;
import com.jingdong.manto.pkg.db.entity.PkgCollectEntity;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.pkg.db.entity.PkgHistoryEntity;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoProcessUtil;
import java.util.List;

/* loaded from: classes16.dex */
public final class a {
    private static a a;

    private static a a(Context context) {
        com.jingdong.manto.provider.db.a.b(context).a(b.m() + ".db");
        return new a();
    }

    public static List<LocalExtAuthEntity> a(String str) {
        return com.jingdong.manto.provider.db.a.b(MantoProcessUtil.getContext()).b(LocalExtAuthEntity.class, "APP_ID=?", new String[]{str}, null);
    }

    public static void a() {
        com.jingdong.manto.provider.db.a.b(MantoProcessUtil.getContext()).e(PkgCollectEntity.class);
    }

    public static void a(DomainBlackListEntity domainBlackListEntity) {
        com.jingdong.manto.provider.db.a.b(MantoProcessUtil.getContext()).d(domainBlackListEntity);
    }

    public static void a(PkgCollectEntity pkgCollectEntity) {
        com.jingdong.manto.provider.db.a.b(MantoProcessUtil.getContext()).d(pkgCollectEntity);
    }

    public static void a(PkgDetailEntity pkgDetailEntity) {
        com.jingdong.manto.provider.db.a.b(MantoProcessUtil.getContext()).d(pkgDetailEntity);
    }

    public static void a(String str, AuthInfo authInfo) {
        com.jingdong.manto.provider.db.a.b(MantoProcessUtil.getContext()).d(new LocalExtAuthEntity(str, authInfo));
    }

    public static void a(String str, String str2) {
        com.jingdong.manto.provider.db.a.b(MantoProcessUtil.getContext()).a(LocalExtAuthEntity.class, "APP_ID=? AND SCOPE=?", new String[]{str, str2});
    }

    public static void a(List<PkgCollectEntity> list) {
        com.jingdong.manto.provider.db.a.b(MantoProcessUtil.getContext()).a((List) list);
    }

    public static a b(Context context) {
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    a = a(context.getApplicationContext());
                }
            }
        }
        return a;
    }

    public static List<MantoAuthEntity> b(String str) {
        return com.jingdong.manto.provider.db.a.b(MantoProcessUtil.getContext()).b(MantoAuthEntity.class, "APP_ID=?", new String[]{str}, null);
    }

    public static void b() {
        com.jingdong.manto.provider.db.a.b(MantoProcessUtil.getContext()).e(PkgHistoryEntity.class);
    }

    public static void b(String str, AuthInfo authInfo) {
        com.jingdong.manto.provider.db.a.b(MantoProcessUtil.getContext()).d(new MantoAuthEntity(str, authInfo));
    }

    public static void b(String str, String str2) {
        com.jingdong.manto.provider.db.a.b(MantoProcessUtil.getContext()).a(PkgCollectEntity.class, "appId=? AND type=?", new String[]{str, str2});
        com.jingdong.manto.provider.db.a.b(MantoProcessUtil.getContext()).a(PkgDetailEntity.class, "appId=? AND type=?", new String[]{str, str2});
        com.jingdong.manto.provider.db.a.b(MantoProcessUtil.getContext()).a(PkgHistoryEntity.class, "appId=? AND type=?", new String[]{str, str2});
    }

    public static void b(List<PkgHistoryEntity> list) {
        com.jingdong.manto.provider.db.a.b(MantoProcessUtil.getContext()).a((List) list);
    }

    public static void c(String str) {
        synchronized (a.class) {
            String str2 = str + ".db";
            MantoLog.e("better", "resetDB: " + str2);
            com.jingdong.manto.provider.db.a.b(MantoProcessUtil.getContext()).a(str2);
        }
    }

    public List<PkgCollectEntity> c() {
        return com.jingdong.manto.provider.db.a.b(MantoProcessUtil.getContext()).d(PkgCollectEntity.class);
    }

    public List<PkgHistoryEntity> d() {
        return com.jingdong.manto.provider.db.a.b(MantoProcessUtil.getContext()).d(PkgHistoryEntity.class);
    }
}
