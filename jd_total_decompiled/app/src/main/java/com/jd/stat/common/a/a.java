package com.jd.stat.common.a;

import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.telephony.TelephonyManager;
import com.jingdong.common.database.table.SignUpTable;
import com.jingdong.sdk.platform.business.personal.R2;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/* loaded from: classes18.dex */
public abstract class a {
    protected static List<Integer> a;
    public static WeakHashMap<Integer, WeakReference<a>> b = new WeakHashMap<>();

    /* renamed from: c  reason: collision with root package name */
    private final TelephonyManager f6980c = (TelephonyManager) com.jd.stat.security.c.a.getSystemService(SignUpTable.TB_COLUMN_PHONE);

    public a() {
        if (a == null && d()) {
            f();
        }
    }

    public static a a(int i2) {
        if (b.get(Integer.valueOf(i2)) != null && b.get(Integer.valueOf(i2)).get() != null) {
            return b.get(Integer.valueOf(i2)).get();
        }
        a aVar = null;
        switch (i2) {
            case R2.drawable.button_p_02 /* 8193 */:
                aVar = new c();
                break;
            case 8194:
                aVar = new d();
                break;
            case R2.drawable.button_p_left_normal /* 8195 */:
                aVar = new e();
                break;
            case R2.drawable.button_p_left_selected /* 8196 */:
                aVar = new i();
                break;
            case R2.drawable.button_p_right /* 8197 */:
                aVar = new f();
                break;
            case R2.drawable.button_p_right_normal /* 8198 */:
                aVar = new g();
                break;
            case R2.drawable.button_p_right_selected /* 8199 */:
                aVar = new h();
                break;
            case R2.drawable.button_q_01 /* 8201 */:
                aVar = new b();
                break;
        }
        b.put(Integer.valueOf(i2), new WeakReference<>(aVar));
        return aVar;
    }

    private static void f() {
        if (com.jd.stat.security.c.k()) {
            a = new ArrayList();
            Cursor query = com.jd.stat.security.c.a.getContentResolver().query(Uri.parse("content://telephony/siminfo"), new String[]{"_id", "sim_id"}, "sim_id>=0", new String[0], "_id DESC");
            if (query != null) {
                while (query.moveToNext()) {
                    int columnIndex = query.getColumnIndex("_id");
                    if (columnIndex >= 0) {
                        a.add(Integer.valueOf(query.getInt(columnIndex)));
                    }
                }
                query.close();
            }
        }
    }

    protected abstract String a();

    protected abstract String b();

    protected abstract boolean c();

    public boolean d() {
        return false;
    }

    public String e() {
        if (c() && d() && com.jd.stat.security.c.k()) {
            if (Build.VERSION.SDK_INT >= 22) {
                return b();
            }
            return a();
        }
        return "";
    }
}
