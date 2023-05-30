package com.jd.jdsec.a.i;

import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.telephony.TelephonyManager;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.database.table.SignUpTable;
import com.jingdong.sdk.platform.business.personal.R2;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/* loaded from: classes13.dex */
public abstract class a {
    protected static List<Integer> b;

    /* renamed from: c  reason: collision with root package name */
    public static WeakHashMap<Integer, WeakReference<a>> f2722c = new WeakHashMap<>();
    private final TelephonyManager a = (TelephonyManager) com.jd.jdsec.c.g.a.getSystemService(SignUpTable.TB_COLUMN_PHONE);

    public a() {
        if (b == null && h()) {
            i();
        }
    }

    public static a a(int i2) {
        if (f2722c.get(Integer.valueOf(i2)) != null && f2722c.get(Integer.valueOf(i2)).get() != null) {
            return f2722c.get(Integer.valueOf(i2)).get();
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
        f2722c.put(Integer.valueOf(i2), new WeakReference<>(aVar));
        return aVar;
    }

    private String d(String str, int i2) {
        if (com.jd.jdsec.c.g.k()) {
            return "";
        }
        try {
            Class<?> cls = Class.forName("android.telephony.TelephonyManager");
            int i3 = Build.VERSION.SDK_INT;
            return i3 > 21 ? (String) cls.getMethod(str, Integer.TYPE).invoke(this.a, Integer.valueOf(i2)) : i3 == 21 ? (String) cls.getMethod(str, Long.TYPE).invoke(this.a, Long.valueOf(i2)) : "";
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private static void i() {
        if (com.jd.jdsec.c.g.k()) {
            return;
        }
        b = new ArrayList();
        Cursor query = com.jd.jdsec.c.g.a.getContentResolver().query(Uri.parse("content://telephony/siminfo"), new String[]{"_id", "sim_id"}, "sim_id>=0", new String[0], "_id DESC");
        if (query != null) {
            while (query.moveToNext()) {
                b.add(Integer.valueOf(query.getInt(query.getColumnIndex("_id"))));
            }
            query.close();
        }
    }

    protected abstract String b();

    /* JADX INFO: Access modifiers changed from: protected */
    public String c(String str) {
        String str2 = "";
        if (com.jd.jdsec.c.g.k()) {
            return "";
        }
        for (int i2 = 0; i2 < b.size(); i2++) {
            str2 = str2 + com.jd.jdsec.a.l.e.h(d(str, b.get(i2).intValue()));
            if (!f(i2)) {
                str2 = str2 + DYConstants.DY_REGEX_COMMA;
            }
        }
        return str2;
    }

    protected abstract String e();

    protected boolean f(int i2) {
        return i2 == b.size() - 1;
    }

    public String g() {
        if (j() && h() && !com.jd.jdsec.c.g.k()) {
            if (Build.VERSION.SDK_INT >= 22) {
                return b();
            }
            return e();
        }
        return "";
    }

    public boolean h() {
        return false;
    }

    protected abstract boolean j();
}
