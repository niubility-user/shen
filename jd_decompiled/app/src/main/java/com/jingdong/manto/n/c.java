package com.jingdong.manto.n;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.manto.jsapi.webview.DownGradeToH5Activity;
import com.jingdong.manto.launch.i;
import com.jingdong.manto.message.MantoAcrossProcessMain;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.sdk.MantoSdkManager;
import com.jingdong.manto.sdk.api.IDeepDarkManager;
import com.jingdong.manto.sdk.api.IMPDownGrade;
import com.jingdong.manto.ui.MantoActivitySingleProcess;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.m;

/* loaded from: classes16.dex */
public class c {
    private static final e a = new e();
    private static final h b = new h();

    /* renamed from: c */
    private static final b f13865c = new b();

    /* loaded from: classes16.dex */
    public class a implements com.jingdong.manto.message.a {
        a() {
        }

        @Override // com.jingdong.manto.message.a
        public void a(String str) {
            MantoLog.d("MantoMPManager", "Process Died " + str);
        }

        @Override // com.jingdong.manto.message.a
        public void b(String str) {
        }
    }

    public static void a() {
        MantoAcrossProcessMain.a(new a());
    }

    public static void a(int i2) {
        a.a(i2);
        b.a(i2);
    }

    public static void a(Context context, com.jingdong.manto.i.c cVar) {
        Intent intent;
        if (TextUtils.equals("1", m.a("downGradeToH5", "1")) && cVar.a()) {
            String a2 = cVar.a(PkgDetailEntity.CONFIG_JSON_KEY_DOWN_GRADE_H5_URL);
            if (!cVar.e()) {
                IMPDownGrade iMPDownGrade = (IMPDownGrade) MantoSdkManager.instanceOf(IMPDownGrade.class);
                if (iMPDownGrade != null && !TextUtils.isEmpty(a2)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("url", a2);
                    iMPDownGrade.onDownGrade(context, bundle);
                    return;
                }
            } else if (!TextUtils.isEmpty(a2)) {
                DownGradeToH5Activity.a(context, a2, cVar.b);
                return;
            }
        }
        boolean z = !com.jingdong.manto.b.o() && cVar.d();
        IDeepDarkManager iDeepDarkManager = (IDeepDarkManager) com.jingdong.a.n(IDeepDarkManager.class);
        int curreentDeepDarkMode = iDeepDarkManager != null ? iDeepDarkManager.getCurreentDeepDarkMode() : 0;
        if (z) {
            MantoLog.d("MantoMPManager", "launchMiniProgram in MainTask " + MantoActivitySingleProcess.class.getSimpleName());
            b.b().a(cVar.a, cVar.f13082e, null);
            if (context == null) {
                context = com.jingdong.manto.c.a();
            }
            Bundle bundle2 = new Bundle();
            bundle2.putParcelable("key_manto_init_config", cVar);
            bundle2.putInt("darkMode", curreentDeepDarkMode);
            bundle2.putLong("infoCost", i.f13270e);
            bundle2.putLong("launchInfoStartTime", i.f13269c);
            bundle2.putLong("loadingStartTime", System.currentTimeMillis());
            intent = new Intent(context, MantoActivitySingleProcess.class);
            intent.putExtra("bundles", bundle2);
        } else {
            MantoLog.d("MantoMPManager", "launchMiniProgram in individual task");
            e eVar = a;
            d a3 = eVar.a(cVar.a);
            if (a3 == null) {
                MantoLog.d("MantoMPManager", "launchMiniProgram cannot find record with appId " + cVar.a);
                a3 = eVar.b();
                eVar.a(a3);
            } else {
                MantoLog.d("MantoMPManager", "launchMiniProgram find record " + a3.a + " with appId " + cVar.a);
            }
            a3.a(cVar.a, cVar.f13082e, null);
            if (context == null) {
                context = com.jingdong.manto.c.a();
            }
            Bundle bundle3 = new Bundle();
            bundle3.putParcelable("key_manto_init_config", cVar);
            bundle3.putInt("darkMode", curreentDeepDarkMode);
            bundle3.putLong("infoCost", i.f13270e);
            bundle3.putLong("launchInfoStartTime", i.f13269c);
            bundle3.putLong("loadingStartTime", System.currentTimeMillis());
            intent = new Intent(context, a3.b);
            intent.putExtra("bundles", bundle3);
        }
        intent.setExtrasClassLoader(com.jingdong.manto.i.c.class.getClassLoader());
        intent.addFlags(268435456);
        context.startActivity(intent);
        i.f13269c = 0L;
        i.f13270e = 0L;
    }

    public static void a(f fVar) {
        int i2 = fVar.f13874j;
        if (i2 == 0) {
            a.a(fVar);
        } else if (i2 == 1) {
            b.a(fVar);
        } else if (i2 != 2) {
        } else {
            f13865c.a(fVar);
        }
    }

    public static void a(String str, String str2) {
        a.a(str, str2);
        b.a(str, str2);
    }

    public static boolean a(String str) {
        return (a.a(str) == null && b.a(str) == null) ? false : true;
    }

    public static void b(f fVar) {
        if (fVar.f13874j == 0) {
            a.c();
        }
    }

    public static void b(String str) {
        a.c(str);
        b.b(str);
    }

    public static void c(f fVar) {
        int i2 = fVar.f13874j;
        if (i2 == 0) {
            a.b(fVar);
        } else if (i2 == 1) {
            b.b(fVar);
        } else if (i2 != 2) {
        } else {
            f13865c.b(fVar);
        }
    }
}
