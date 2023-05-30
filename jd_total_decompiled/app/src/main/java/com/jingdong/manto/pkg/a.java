package com.jingdong.manto.pkg;

import com.jingdong.manto.jsapi.auth.tools.AuthInfo;
import com.jingdong.manto.pkg.db.entity.DomainBlackListEntity;
import com.jingdong.manto.pkg.db.entity.LocalExtAuthEntity;
import com.jingdong.manto.pkg.db.entity.MantoAuthEntity;
import com.jingdong.manto.pkg.db.entity.PkgCollectEntity;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.pkg.db.entity.PkgHistoryEntity;
import com.jingdong.manto.utils.MantoProcessUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes16.dex */
public class a {
    private static volatile a b;
    private final com.jingdong.manto.pkg.c.a a;

    private a(com.jingdong.manto.pkg.c.a aVar) {
        this.a = aVar;
    }

    public static a a(com.jingdong.manto.pkg.c.a aVar) {
        if (b == null) {
            synchronized (a.class) {
                if (b == null) {
                    b = new a(aVar);
                }
            }
        }
        return b;
    }

    public List<AuthInfo> a(String str) {
        ArrayList arrayList = new ArrayList();
        List<MantoAuthEntity> b2 = com.jingdong.manto.pkg.c.a.b(str);
        if (b2 != null) {
            for (MantoAuthEntity mantoAuthEntity : b2) {
                arrayList.add(new AuthInfo(mantoAuthEntity.scope, mantoAuthEntity.permission, mantoAuthEntity.title, mantoAuthEntity.state, mantoAuthEntity.description));
            }
        }
        return arrayList;
    }

    public void a() {
        com.jingdong.manto.pkg.c.a.a();
    }

    public void a(DomainBlackListEntity domainBlackListEntity) {
        com.jingdong.manto.pkg.c.a.a(domainBlackListEntity);
    }

    public void a(PkgCollectEntity pkgCollectEntity) {
        com.jingdong.manto.pkg.c.a.a(pkgCollectEntity);
    }

    public void a(PkgDetailEntity pkgDetailEntity) {
        com.jingdong.manto.pkg.c.a.a(pkgDetailEntity);
    }

    public void a(String str, AuthInfo authInfo) {
        com.jingdong.manto.pkg.c.a.b(str, authInfo);
    }

    public void a(String str, String str2) {
        com.jingdong.manto.pkg.c.a.a(str, str2);
    }

    public void a(List<PkgCollectEntity> list) {
        com.jingdong.manto.pkg.c.a.a(list);
    }

    public List<AuthInfo> b(String str) {
        ArrayList arrayList = new ArrayList();
        List<LocalExtAuthEntity> a = com.jingdong.manto.pkg.c.a.a(str);
        if (a != null) {
            for (LocalExtAuthEntity localExtAuthEntity : a) {
                arrayList.add(new AuthInfo(localExtAuthEntity.scope, localExtAuthEntity.permission, localExtAuthEntity.title, localExtAuthEntity.state, localExtAuthEntity.description));
            }
        }
        return arrayList;
    }

    public void b() {
        com.jingdong.manto.pkg.c.a.b();
    }

    public void b(String str, AuthInfo authInfo) {
        com.jingdong.manto.pkg.c.a.a(str, authInfo);
    }

    public void b(String str, String str2) {
        com.jingdong.manto.pkg.c.a.b(str, str2);
    }

    public void b(List<PkgHistoryEntity> list) {
        com.jingdong.manto.provider.db.a.b(MantoProcessUtil.getContext()).e(PkgHistoryEntity.class);
        com.jingdong.manto.pkg.c.a.b(list);
    }

    public DomainBlackListEntity c() {
        return null;
    }

    public PkgDetailEntity c(String str, String str2) {
        return (PkgDetailEntity) com.jingdong.manto.provider.db.a.b(MantoProcessUtil.getContext()).a(PkgDetailEntity.class, "appId=? AND type=?", new String[]{str, str2}, null);
    }

    public List<PkgHistoryEntity> d() {
        return this.a.d();
    }
}
