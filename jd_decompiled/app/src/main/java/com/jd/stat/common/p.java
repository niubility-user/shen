package com.jd.stat.common;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Display;
import android.view.WindowManager;
import com.jingdong.common.entity.personal.PersonalConstants;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes18.dex */
public class p {
    private static String a = "";
    private static String b = "";

    /* renamed from: c  reason: collision with root package name */
    private static final String[] f6999c = {"_data", "datetaken", "date_added"};
    private static final String[] d = {"_data", "datetaken", "date_added", "width", "height"};

    /* renamed from: e  reason: collision with root package name */
    private static final String[] f7000e = {PersonalConstants.FUNCTION_ID_SCREEN_SHOT, "screen_shot", "screen-shot", "screen shot", "screencapture", "screen_capture", "screen-capture", "screen capture", "screencap", "screen_cap", "screen-cap", "screen cap", "\u622a\u5c4f"};

    /* renamed from: f  reason: collision with root package name */
    private static Point f7001f;

    /* renamed from: h  reason: collision with root package name */
    private Context f7003h;

    /* renamed from: i  reason: collision with root package name */
    private b f7004i;

    /* renamed from: k  reason: collision with root package name */
    private long f7006k;

    /* renamed from: l  reason: collision with root package name */
    private a f7007l;

    /* renamed from: m  reason: collision with root package name */
    private a f7008m;

    /* renamed from: g  reason: collision with root package name */
    private final List<String> f7002g = new ArrayList();

    /* renamed from: j  reason: collision with root package name */
    private boolean f7005j = false;

    /* renamed from: n  reason: collision with root package name */
    private final Handler f7009n = new Handler(Looper.getMainLooper());

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes18.dex */
    public class a extends ContentObserver {
        private final Uri b;

        public a(Uri uri, Handler handler) {
            super(handler);
            this.b = uri;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            super.onChange(z);
            if (BaseInfo.isAppForeground() && p.this.f7005j) {
                try {
                    com.jd.stat.common.b.b.b("MediaContentObserver onChange invoked!");
                    p.this.a(this.b);
                } catch (Throwable th) {
                    com.jd.stat.common.b.b.a(th);
                }
            }
        }
    }

    /* loaded from: classes18.dex */
    public interface b {
        void a(String str);
    }

    private p(Context context) {
        this.f7003h = context;
        if (f7001f == null) {
            Point g2 = g();
            f7001f = g2;
            if (g2 != null) {
                com.jd.stat.common.b.b.b("Screen Real Size: " + f7001f.x + " * " + f7001f.y);
                return;
            }
            com.jd.stat.common.b.b.b("Get screen real size failed.");
        }
    }

    public static String d() {
        try {
            String str = a;
            if (TextUtils.isEmpty(str)) {
                str = f() ? com.jingdong.jdsdk.a.a.a : "b";
            }
            com.jd.stat.common.b.f.a("lastShotTime", "");
            return str;
        } catch (Throwable unused) {
            return "c";
        }
    }

    public static String e() {
        try {
            String str = b;
            if (TextUtils.isEmpty(str)) {
                str = f() ? com.jingdong.jdsdk.a.a.a : "b";
            }
            com.jd.stat.common.b.f.a("lastShotPage", "");
            return str;
        } catch (Throwable unused) {
            return "c";
        }
    }

    private static boolean f() {
        if (com.jd.stat.security.d.a().f() && com.jd.stat.security.c.a != null) {
            return Build.VERSION.SDK_INT < 23 || com.jd.stat.security.c.a.checkSelfPermission(new StringBuffer("EGAROTS_LANRETXE_DAER.noissimrep.diordna").reverse().toString()) == 0;
        }
        return false;
    }

    private Point g() {
        Point point2;
        Throwable th;
        Context context;
        try {
            point2 = new Point();
        } catch (Throwable th2) {
            point2 = null;
            th = th2;
        }
        try {
            context = this.f7003h;
        } catch (Throwable th3) {
            th = th3;
            th.printStackTrace();
            return point2;
        }
        if (context == null) {
            return point2;
        }
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= 17) {
            defaultDisplay.getRealSize(point2);
        } else {
            point2.set(((Integer) Display.class.getMethod("getRawWidth", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue(), ((Integer) Display.class.getMethod("getRawHeight", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue());
        }
        return point2;
    }

    private static void h() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            com.jd.stat.common.b.b.a("Call the method must be in main thread: " + (stackTrace.length >= 4 ? stackTrace[3].toString() : null));
        }
    }

    public void b() {
        if (!this.f7005j && com.jd.stat.security.d.a().f()) {
            h();
            try {
                this.f7002g.clear();
                this.f7006k = System.currentTimeMillis();
                this.f7007l = new a(MediaStore.Images.Media.INTERNAL_CONTENT_URI, this.f7009n);
                this.f7008m = new a(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, this.f7009n);
                boolean z = Build.VERSION.SDK_INT >= 29;
                Context context = this.f7003h;
                if (context != null) {
                    context.getContentResolver().registerContentObserver(MediaStore.Images.Media.INTERNAL_CONTENT_URI, z, this.f7007l);
                    this.f7003h.getContentResolver().registerContentObserver(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, z, this.f7008m);
                }
                this.f7005j = true;
            } catch (Throwable th) {
                com.jd.stat.common.b.b.a(th);
            }
        }
    }

    public void c() {
        if (this.f7005j) {
            h();
            try {
                if (this.f7007l != null) {
                    try {
                        Context context = this.f7003h;
                        if (context != null) {
                            context.getContentResolver().unregisterContentObserver(this.f7007l);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    this.f7007l = null;
                }
                if (this.f7008m != null) {
                    try {
                        Context context2 = this.f7003h;
                        if (context2 != null) {
                            context2.getContentResolver().unregisterContentObserver(this.f7008m);
                        }
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                    this.f7008m = null;
                }
                this.f7006k = 0L;
                this.f7002g.clear();
                this.f7005j = false;
            } catch (Throwable th) {
                com.jd.stat.common.b.b.a(th);
            }
        }
    }

    public static p a(Context context) {
        h();
        return new p(context);
    }

    public static void a() {
        boolean f2 = f();
        String str = com.jingdong.jdsdk.a.a.a;
        b = com.jd.stat.common.b.f.c("lastShotPage", f2 ? com.jingdong.jdsdk.a.a.a : "b");
        if (!f()) {
            str = "b";
        }
        a = com.jd.stat.common.b.f.c("lastShotTime", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Uri uri) {
        int i2;
        int i3;
        int i4;
        com.jd.stat.common.b.b.b("handleMediaContentChange \u6765\u4e86");
        if (f()) {
            Cursor cursor = null;
            try {
                try {
                    Context context = this.f7003h;
                    if (context != null) {
                        cursor = context.getContentResolver().query(uri, Build.VERSION.SDK_INT < 16 ? f6999c : d, null, null, "date_added desc limit 1");
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    if (0 == 0 || cursor.isClosed()) {
                        return;
                    }
                }
                if (cursor == null) {
                    com.jd.stat.common.b.b.b("Deviant logic.");
                    if (cursor == null || cursor.isClosed()) {
                        return;
                    }
                    cursor.close();
                } else if (!cursor.moveToFirst()) {
                    com.jd.stat.common.b.b.b("Cursor no data.");
                    if (cursor == null || cursor.isClosed()) {
                        return;
                    }
                    cursor.close();
                } else {
                    int columnIndex = cursor.getColumnIndex("_data");
                    int columnIndex2 = cursor.getColumnIndex("datetaken");
                    int i5 = -1;
                    if (Build.VERSION.SDK_INT >= 16) {
                        i5 = cursor.getColumnIndex("width");
                        i2 = cursor.getColumnIndex("height");
                    } else {
                        i2 = -1;
                    }
                    String string = cursor.getString(columnIndex);
                    long j2 = cursor.getLong(columnIndex2);
                    if (i5 >= 0 && i2 >= 0) {
                        i4 = cursor.getInt(i5);
                        i3 = cursor.getInt(i2);
                    } else {
                        Point a2 = a(string);
                        int i6 = a2.x;
                        i3 = a2.y;
                        i4 = i6;
                    }
                    a(string, j2, i4, i3);
                    if (cursor == null || cursor.isClosed()) {
                        return;
                    }
                    cursor.close();
                }
            } catch (Throwable th) {
                if (0 != 0 && !cursor.isClosed()) {
                    cursor.close();
                }
                throw th;
            }
        }
    }

    private boolean b(String str, long j2, int i2, int i3) {
        int i4;
        com.jd.stat.common.b.b.b("dateTaken : " + j2);
        if (j2 >= this.f7006k && System.currentTimeMillis() - j2 <= 10000) {
            Point point2 = f7001f;
            if ((point2 == null || ((i2 <= (i4 = point2.x) && i3 <= point2.y) || (i3 <= i4 && i2 <= point2.y))) && !TextUtils.isEmpty(str)) {
                String lowerCase = str.toLowerCase();
                for (String str2 : f7000e) {
                    if (lowerCase.contains(str2)) {
                        return true;
                    }
                }
                return false;
            }
            return false;
        }
        com.jd.stat.common.b.b.b("\u65f6\u95f4\u4e0d\u5bf9 : " + System.currentTimeMillis());
        return false;
    }

    private boolean b(String str) {
        if (this.f7002g.contains(str)) {
            return true;
        }
        if (this.f7002g.size() >= 20) {
            for (int i2 = 0; i2 < 5; i2++) {
                this.f7002g.remove(0);
            }
        }
        this.f7002g.add(str);
        return false;
    }

    private Point a(String str) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        return new Point(options.outWidth, options.outHeight);
    }

    private void a(String str, long j2, int i2, int i3) {
        com.jd.stat.common.b.b.b("handleMediaRowData \u6765\u4e86");
        if (b(str, j2, i2, i3)) {
            com.jd.stat.common.b.b.b("ScreenShot: path = " + str + "; size = " + i2 + " * " + i3 + "; date = " + j2);
            if (this.f7004i == null || b(str)) {
                return;
            }
            this.f7004i.a(str);
            return;
        }
        com.jd.stat.common.b.b.b("Media content changed, but not screenshot: path = " + str + "; size = " + i2 + " * " + i3 + "; date = " + j2);
    }

    public void a(b bVar) {
        this.f7004i = bVar;
    }

    public static void a(String str, String str2) {
        a = str;
        b = str2;
        com.jd.stat.common.b.f.a("lastShotTime", str);
        com.jd.stat.common.b.f.a("lastShotPage", str2);
    }
}
