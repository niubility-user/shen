package com.jingdong.app.mall.home.floor.common.i;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.floor.common.i.j;
import com.jingdong.common.XView2.strategy.preDownLoadManager.PreDownLoadManager;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.jdsdk.utils.Md5Encrypt;
import java.io.File;

/* loaded from: classes4.dex */
public class h {
    public static c a = new c("bannerBgVideo", PreDownLoadManager.VIDEO_SKU_SUFFIX, "HOME_BANNER_VIDEO_PATH", "HOME_BANNER_VIDEO_SIZE", "HOME_BANNER_VIDEO_ID", "HOME_BANNER_VIDEO_URL");
    public static c b = new c("miniTopVideo", PreDownLoadManager.VIDEO_SKU_SUFFIX, "HOME_MINI_VIDEO_PATH", "HOME_MINI_VIDEO_SIZE", "HOME_MINI_VIDEO_ID", "HOME_MINI_VIDEO_URL");

    /* renamed from: c  reason: collision with root package name */
    public static c f9305c = new c("xViewVideo", PreDownLoadManager.VIDEO_SKU_SUFFIX, "HOME_X_VIDEO_PATH", "HOME_X_VIDEO_SIZE", "HOME_X_VIDEO_ID", "HOME_X_VIDEO_URL");

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ b f9306g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ c f9307h;

        /* renamed from: com.jingdong.app.mall.home.floor.common.i.h$a$a  reason: collision with other inner class name */
        /* loaded from: classes4.dex */
        class C0287a extends j.c {
            final /* synthetic */ String a;

            C0287a(String str) {
                this.a = str;
            }

            @Override // com.jingdong.app.mall.home.floor.common.i.j.c
            public void a(boolean z, String str) {
                if (z) {
                    File file = new File(str);
                    if (!file.exists() || file.length() <= 0) {
                        return;
                    }
                    h.f(file, this.a, a.this.f9307h);
                }
            }
        }

        a(b bVar, c cVar) {
            this.f9306g = bVar;
            this.f9307h = cVar;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            String md5 = Md5Encrypt.md5(this.f9306g.a);
            c cVar = this.f9307h;
            j.e(cVar.f9308c, cVar.d, md5, this.f9306g.b, new C0287a(md5));
        }
    }

    /* loaded from: classes4.dex */
    public static class b {
        String a;
        String b;

        public b(JDJSONObject jDJSONObject) {
            jDJSONObject.optInt("activityId", 0);
            jDJSONObject.optInt("sortno", 0);
            this.a = jDJSONObject.optString("videoId", "");
            this.b = jDJSONObject.optString("videoUrl", "");
            jDJSONObject.optString("startTime", "");
            jDJSONObject.optString("endTime", "");
        }
    }

    private static boolean b(c cVar) {
        String md5 = Md5Encrypt.md5(cVar.a);
        String g2 = j.g(cVar.f9308c, cVar.d, md5);
        return cVar.b.equals(c(!TextUtils.isEmpty(g2) ? new File(g2) : null, md5, cVar));
    }

    public static String c(File file, String str, c cVar) {
        if (file != null && file.exists()) {
            SharedPreferences jdSharedPreferences = CommonBase.getJdSharedPreferences();
            String string = jdSharedPreferences.getString(cVar.f9309e + str, null);
            long j2 = jdSharedPreferences.getLong(cVar.f9310f + str, 0L);
            String string2 = jdSharedPreferences.getString(cVar.f9311g + str, null);
            String string3 = jdSharedPreferences.getString(cVar.f9312h + str, null);
            if (j2 > 0 && j2 == file.length() && !TextUtils.isEmpty(string) && TextUtils.equals(string, file.getAbsolutePath()) && str.equals(string2)) {
                return string3;
            }
        }
        return null;
    }

    public static void d(JDJSONArray jDJSONArray) {
        if (jDJSONArray == null || jDJSONArray.size() < 1) {
            return;
        }
        int size = jDJSONArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            try {
                JDJSONObject optJSONObject = jDJSONArray.optJSONObject(i2);
                if (optJSONObject != null) {
                    if (!com.jingdong.app.mall.home.o.a.f.M0()) {
                        return;
                    }
                    String optString = optJSONObject.optString("type", "");
                    char c2 = '\uffff';
                    int hashCode = optString.hashCode();
                    if (hashCode != -1396342996) {
                        if (hashCode != 593032671) {
                            if (hashCode == 1064513918 && optString.equals("miniTop")) {
                                c2 = 1;
                            }
                        } else if (optString.equals("xviewGuide")) {
                            c2 = 2;
                        }
                    } else if (optString.equals("banner")) {
                        c2 = 0;
                    }
                    if (c2 == 0) {
                        e(optJSONObject.optJSONArray("content"), a);
                    } else if (c2 == 1) {
                        e(optJSONObject.optJSONArray("content"), b);
                    } else if (c2 == 2) {
                        e(optJSONObject.optJSONArray("content"), f9305c);
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private static void e(JDJSONArray jDJSONArray, c cVar) {
        if (jDJSONArray == null || jDJSONArray.size() < 1) {
            return;
        }
        j.c(j.f(cVar.f9308c));
        int size = jDJSONArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            try {
                JDJSONObject jSONObject = jDJSONArray.getJSONObject(i2);
                if (jSONObject != null) {
                    b bVar = new b(jSONObject);
                    if (!"".equals(bVar.a) && !"".equals(bVar.b)) {
                        c cVar2 = new c(cVar);
                        cVar2.a(bVar.a, bVar.b);
                        if (!b(cVar2)) {
                            com.jingdong.app.mall.home.w.a.a(new a(bVar, cVar2));
                        }
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void f(File file, String str, c cVar) {
        SharedPreferences.Editor edit = CommonBase.getJdSharedPreferences().edit();
        edit.putString(cVar.f9309e + str, file.getAbsolutePath());
        edit.putLong(cVar.f9310f + str, file.length());
        edit.putString(cVar.f9311g + str, str);
        edit.putString(cVar.f9312h + str, cVar.b);
        edit.apply();
    }

    /* loaded from: classes4.dex */
    public static class c {
        public String a = "";
        public String b = "";

        /* renamed from: c  reason: collision with root package name */
        public String f9308c;
        public String d;

        /* renamed from: e  reason: collision with root package name */
        public String f9309e;

        /* renamed from: f  reason: collision with root package name */
        public String f9310f;

        /* renamed from: g  reason: collision with root package name */
        public String f9311g;

        /* renamed from: h  reason: collision with root package name */
        public String f9312h;

        public c(c cVar) {
            this.f9308c = cVar.f9308c;
            this.d = cVar.d;
            this.f9309e = cVar.f9309e;
            this.f9310f = cVar.f9310f;
            this.f9311g = cVar.f9311g;
            this.f9312h = cVar.f9312h;
        }

        public void a(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        public c(String str, String str2, String str3, String str4, String str5, String str6) {
            this.f9308c = str;
            this.d = str2;
            this.f9309e = str3;
            this.f9310f = str4;
            this.f9311g = str5;
            this.f9312h = str6;
        }
    }
}
