package com.tencent.mapsdk.internal;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.common.util.UriUtil;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jingdong.common.model.datetime.JDDateTimePickerDialog;
import com.jingdong.jdsdk.constant.CartConstant;
import com.tencent.mapsdk.internal.ba;
import com.tencent.mapsdk.internal.ca;
import com.tencent.tencentmap.mapsdk.maps.TencentMapOptions;
import java.io.File;
import java.io.FileFilter;
import java.lang.Thread;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/* loaded from: classes9.dex */
public class oa implements na {

    /* renamed from: h  reason: collision with root package name */
    public static Set<String> f16922h;

    /* renamed from: i  reason: collision with root package name */
    private static final int[] f16923i;
    private boolean a;
    private long b;

    /* renamed from: c  reason: collision with root package name */
    private int f16924c;
    private final Map<String, int[]> d = new HashMap();

    /* renamed from: e  reason: collision with root package name */
    private final File f16925e;

    /* renamed from: f  reason: collision with root package name */
    private final Context f16926f;

    /* renamed from: g  reason: collision with root package name */
    private boolean f16927g;

    /* loaded from: classes9.dex */
    public class a implements FileFilter {
        public a() {
        }

        @Override // java.io.FileFilter
        public boolean accept(File file) {
            return Pattern.compile("log-.*.log").matcher(file.getName()).matches();
        }
    }

    /* loaded from: classes9.dex */
    public class b implements Thread.UncaughtExceptionHandler {
        public b() {
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(Thread thread, Throwable th) {
            oa.this.a(6, la.f16817c, "UncaughtException: t[" + thread + "]", th);
            throw new RuntimeException(th);
        }
    }

    /* loaded from: classes9.dex */
    public class c extends ba.i<Void> {
        public c() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Void call() {
            int i2;
            Calendar calendar = Calendar.getInstance();
            Date date = new Date();
            calendar.setTime(date);
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            sb.append("(");
            sb2.append("(");
            int i3 = 2;
            while (true) {
                i2 = 0;
                if (i3 == 0) {
                    break;
                }
                calendar.add(2, -1);
                String str = calendar.get(1) + "";
                String format = String.format(Locale.CHINA, JDDateTimePickerDialog.TWO_DIGIT_FORMAT, Integer.valueOf(calendar.get(2) + 1));
                sb.append(str);
                sb.append("|");
                sb2.append(format);
                sb2.append("|");
                String str2 = str + CartConstant.KEY_YB_INFO_LINK + format;
                File[] c2 = fa.c(oa.this.f16925e, ".*" + str2 + ".*.log.*");
                if (c2 != null && c2.length > 0) {
                    if (ia.a(c2, oa.this.f16925e, "archive-" + str2)) {
                        int length = c2.length;
                        while (i2 < length) {
                            fa.d(c2[i2]);
                            i2++;
                        }
                    }
                }
                i3--;
            }
            calendar.setTime(date);
            sb.deleteCharAt(sb.lastIndexOf("|")).append(")");
            sb2.deleteCharAt(sb2.lastIndexOf("|")).append(")");
            String str3 = "archive-" + sb.toString() + CartConstant.KEY_YB_INFO_LINK + sb2.toString() + ".zip";
            File[] c3 = fa.c(oa.this.f16925e, "archive-.*.zip");
            if (c3 != null) {
                int length2 = c3.length;
                while (i2 < length2) {
                    File file = c3[i2];
                    if (!file.getName().matches(str3)) {
                        fa.d(file);
                    }
                    i2++;
                }
                return null;
            }
            return null;
        }
    }

    /* loaded from: classes9.dex */
    public class d implements Runnable {
        public final /* synthetic */ File a;
        public final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        public final /* synthetic */ String f16928c;

        public d(File file, String str, String str2) {
            this.a = file;
            this.b = str;
            this.f16928c = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            StringBuilder sb;
            String str;
            File file = new File(this.a, this.b + "-" + oa.d() + ".log");
            if (file.exists()) {
                sb = new StringBuilder();
                sb.append(this.f16928c);
                str = ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE;
            } else {
                fa.b(file);
                sb = new StringBuilder();
                sb.append(oa.this.e());
                str = this.f16928c;
            }
            sb.append(str);
            String sb2 = sb.toString();
            if (file.length() >= 2097152) {
                File file2 = null;
                boolean z = false;
                int i2 = 1;
                while (true) {
                    if (i2 > 500) {
                        break;
                    }
                    file2 = new File(file.getParent(), file.getName() + ".part" + i2);
                    if (!file2.exists()) {
                        z = true;
                        break;
                    }
                    i2++;
                }
                if (z) {
                    fa.b(file, file2);
                    fa.b(file);
                }
            }
            fa.e(file, sb2);
        }
    }

    static {
        HashSet hashSet = new HashSet();
        f16922h = hashSet;
        hashSet.add(la.f16821h);
        f16922h.add("NetManager");
        f16922h.add(UriUtil.LOCAL_ASSET_SCHEME);
        f16923i = new int[]{2, 3, 4, 5, 6};
    }

    public oa(Context context, TencentMapOptions tencentMapOptions) {
        String[] debugTags;
        this.f16926f = context;
        File file = new File(lc.a(context, tencentMapOptions).b().getAbsolutePath());
        this.f16925e = fa.a(file, "logs");
        if (x9.d("4.5.10", "4.3.4", 3) < 0) {
            fa.a(file, new a());
        }
        if (tencentMapOptions != null && (debugTags = tencentMapOptions.getDebugTags()) != null) {
            a(true, debugTags);
        }
        h();
        if (li.d) {
            Thread.setDefaultUncaughtExceptionHandler(new b());
        }
    }

    private void a(int i2, String str, String str2) {
        a(i2, str, str2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2, String str, String str2, Throwable th) {
        if (!la.f16817c.equals(str)) {
            if (TextUtils.isEmpty(str)) {
                str = la.f16817c;
            } else {
                str = "TMS-" + str;
            }
        }
        if (th == null) {
            Log.println(i2, str, str2);
        } else if (i2 == 7) {
            Log.wtf(str, str2, th);
        }
        if (this.f16927g) {
            if (th != null) {
                str2 = str2 + " [error]:" + th.getMessage();
            }
            System.out.println("[" + str + "]:" + str2);
        }
    }

    public static String d() {
        return new SimpleDateFormat("yyyy_MM_dd", Locale.CHINA).format(new Date());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String e() {
        return "###########\n" + qa.a(this.f16926f, b7.a("", "")) + "\n###########\n";
    }

    public static String f() {
        return new SimpleDateFormat("yyyy_MM", Locale.CHINA).format(new Date());
    }

    public static String g() {
        return new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.CHINA).format(new Date());
    }

    private void h() {
        ba.a((ba.i) new c()).b((ba.d.b) null);
    }

    @Override // com.tencent.mapsdk.internal.na
    public void a() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0061, code lost:
        if (r10 != null) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0063, code lost:
        r10.a(r9, 1).b();
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0071, code lost:
        if (r10 != null) goto L14;
     */
    @Override // com.tencent.mapsdk.internal.na
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void a(Context context, ca.a aVar) {
        if (context == null) {
            return;
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        long j2 = this.b;
        if (j2 != 0 && uptimeMillis - j2 >= 400) {
            this.f16924c = 0;
            this.b = 0L;
            this.a = false;
            return;
        }
        this.b = uptimeMillis;
        this.f16924c++;
        String str = "\u89e6\u53d1\u8c03\u8bd5\u6a21\u5f0f" + this.f16924c + "\u6b21";
        int i2 = this.f16924c;
        if (i2 < 5 || i2 >= 10) {
            if (i2 == 10) {
                this.a = true;
                str = "\u5f00\u53d1\u8005\u8c03\u8bd5\u5df2\u5f00\u542f";
            }
            a(5, la.f16817c, str);
            return;
        }
        str = "\u5f00\u53d1\u8005\u8c03\u8bd5\u5728" + (10 - this.f16924c) + "\u6b21\u540e\u5f00\u542f";
    }

    @Override // com.tencent.mapsdk.internal.na
    public void a(File file, String str, String str2) {
        if (!b() || TextUtils.isEmpty(str2)) {
            return;
        }
        new Thread(new d(file, str, str2)).start();
    }

    @Override // com.tencent.mapsdk.internal.na
    public void a(String str) {
        if (b()) {
            a(4, la.f16817c, str);
        }
    }

    @Override // com.tencent.mapsdk.internal.na
    public void a(String str, String str2) {
        if (a(4, str)) {
            a(4, str, str2);
        }
    }

    @Override // com.tencent.mapsdk.internal.na
    public void a(String str, String str2, Throwable th) {
        if (a(5, str)) {
            a(5, str, str2, th);
        }
    }

    @Override // com.tencent.mapsdk.internal.na
    public void a(String str, Throwable th) {
        if (b()) {
            a(4, la.f16817c, str, th);
        }
    }

    @Override // com.tencent.mapsdk.internal.na
    public void a(boolean z) {
        this.f16927g = z;
    }

    @Override // com.tencent.mapsdk.internal.na
    public void a(boolean z, int i2, String... strArr) {
        if (strArr != null) {
            for (String str : strArr) {
                this.d.remove(str);
                if (z) {
                    this.d.put(str, new int[]{i2});
                }
            }
        }
    }

    @Override // com.tencent.mapsdk.internal.na
    public void a(boolean z, String... strArr) {
        if (strArr != null) {
            for (String str : strArr) {
                this.d.remove(str);
                if (z) {
                    this.d.put(str, f16923i);
                }
            }
        }
    }

    @Override // com.tencent.mapsdk.internal.na
    public boolean a(int i2, String str) {
        if (!this.d.containsKey(str)) {
            if (this.a) {
                return true;
            }
            return li.d && !f16922h.contains(str);
        }
        int[] iArr = this.d.get(str);
        if (iArr != null) {
            for (int i3 : iArr) {
                if (i3 == i2) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.tencent.mapsdk.internal.na
    public void b(String str) {
        if (b()) {
            a(6, la.f16817c, str);
        }
    }

    @Override // com.tencent.mapsdk.internal.na
    public void b(String str, String str2) {
        if (a(6, str)) {
            a(6, str, str2);
        }
    }

    @Override // com.tencent.mapsdk.internal.na
    public void b(String str, String str2, Throwable th) {
        if (a(3, str)) {
            a(3, str, str2, th);
        }
    }

    @Override // com.tencent.mapsdk.internal.na
    public void b(String str, Throwable th) {
        if (b()) {
            a(3, la.f16817c, str, th);
        }
    }

    @Override // com.tencent.mapsdk.internal.na
    public boolean b() {
        return d(la.f16817c);
    }

    @Override // com.tencent.mapsdk.internal.na
    public String c() {
        return this.f16925e.getAbsolutePath();
    }

    @Override // com.tencent.mapsdk.internal.na
    public void c(String str) {
        if (b()) {
            a(2, la.f16817c, str);
        }
    }

    @Override // com.tencent.mapsdk.internal.na
    public void c(String str, String str2) {
        if (a(2, str)) {
            a(2, str, str2);
        }
    }

    @Override // com.tencent.mapsdk.internal.na
    public void c(String str, String str2, Throwable th) {
        if (a(4, str)) {
            a(4, str, str2, th);
        }
    }

    @Override // com.tencent.mapsdk.internal.na
    public void c(String str, Throwable th) {
        if (b()) {
            a(5, la.f16817c, str, th);
        }
    }

    @Override // com.tencent.mapsdk.internal.na
    public void d(String str, String str2) {
        a(this.f16925e, str, str2);
    }

    @Override // com.tencent.mapsdk.internal.na
    public void d(String str, String str2, Throwable th) {
        if (a(6, str)) {
            a(6, str, str2, th);
        }
    }

    @Override // com.tencent.mapsdk.internal.na
    public void d(String str, Throwable th) {
        if (b()) {
            a(2, la.f16817c, str, th);
        }
    }

    @Override // com.tencent.mapsdk.internal.na
    public boolean d(String str) {
        if (this.d.containsKey(str) || this.a) {
            return true;
        }
        return li.d && !f16922h.contains(str);
    }

    @Override // com.tencent.mapsdk.internal.na
    public void e(String str) {
        a(this.f16925e, "tms", str);
    }

    @Override // com.tencent.mapsdk.internal.na
    public void e(String str, String str2) {
        if (a(5, str)) {
            a(5, str, str2);
        }
    }

    @Override // com.tencent.mapsdk.internal.na
    public void e(String str, String str2, Throwable th) {
        if (a(2, str)) {
            a(2, str, str2, th);
        }
    }

    @Override // com.tencent.mapsdk.internal.na
    public void e(String str, Throwable th) {
        if (b()) {
            a(6, la.f16817c, str, th);
        }
    }

    @Override // com.tencent.mapsdk.internal.na
    public void f(String str) {
        if (b()) {
            a(3, la.f16817c, str);
        }
    }

    @Override // com.tencent.mapsdk.internal.na
    public void f(String str, String str2) {
        if (a(3, str)) {
            a(3, str, str2);
        }
    }

    @Override // com.tencent.mapsdk.internal.na
    public void g(String str) {
        if (b()) {
            a(5, la.f16817c, str);
        }
    }
}
