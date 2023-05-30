package jd.wjlogin_sdk.common.communion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import jd.wjlogin_sdk.net.d;
import jd.wjlogin_sdk.util.p;

/* loaded from: classes.dex */
public class b {
    private static final String a = "WJLogin.ShareDataBaseHelper";

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public class a implements Runnable {
        final /* synthetic */ jd.wjlogin_sdk.tlvtype.a a;

        a(jd.wjlogin_sdk.tlvtype.a aVar) {
            this.a = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Uri parse = Uri.parse("content://" + jd.wjlogin_sdk.common.b.a().getPackageName() + WJLoginUnionProvider.f19750f + "/first");
                Cursor query = jd.wjlogin_sdk.common.b.a().getContentResolver().query(parse, null, null, null, null);
                if (query != null && query.getCount() > 0) {
                    jd.wjlogin_sdk.common.b.a().getContentResolver().delete(parse, null, null);
                }
                String a = this.a.D().a();
                p.b(b.a, "0X77.getKey=" + a);
                if (TextUtils.isEmpty(a)) {
                    return;
                }
                ContentValues contentValues = new ContentValues();
                contentValues.put("finger", a);
                jd.wjlogin_sdk.common.b.a().getContentResolver().insert(parse, contentValues);
            } catch (Exception e2) {
                if (p.b) {
                    p.a("", e2);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: jd.wjlogin_sdk.common.communion.b$b  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class RunnableC0845b implements Runnable {
        RunnableC0845b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Uri parse = Uri.parse("content://" + jd.wjlogin_sdk.common.b.a().getPackageName() + WJLoginUnionProvider.f19750f + "/first");
                if (jd.wjlogin_sdk.common.b.a().getContentResolver().query(parse, null, null, null, null) != null) {
                    jd.wjlogin_sdk.common.b.a().getContentResolver().delete(parse, null, null);
                }
            } catch (Exception e2) {
                if (p.b) {
                    p.a("", e2);
                }
            }
        }
    }

    public static String a(Context context, String str) {
        Cursor query = context.getContentResolver().query(Uri.parse("content://" + str + WJLoginUnionProvider.f19750f + "/first"), null, null, null, null);
        String str2 = "";
        if (query == null) {
            return "";
        }
        while (query.moveToNext()) {
            String string = query.getString(query.getColumnIndex("finger"));
            p.b(a, query.getString(query.getColumnIndex("finger")));
            str2 = string;
        }
        query.close();
        return str2;
    }

    public static String b() {
        return a(jd.wjlogin_sdk.common.b.a(), jd.wjlogin_sdk.common.b.a().getPackageName());
    }

    public static void a(jd.wjlogin_sdk.tlvtype.a aVar) {
        if (aVar != null && aVar.D() != null) {
            d.a().a(new a(aVar));
        } else {
            p.b(a, "0X77 NULL");
        }
    }

    public static void a(String str) {
        if (TextUtils.isEmpty(str)) {
            a();
        }
    }

    public static void a() {
        d.a().a(new RunnableC0845b());
    }
}
