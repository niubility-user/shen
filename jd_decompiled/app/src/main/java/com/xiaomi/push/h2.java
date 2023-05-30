package com.xiaomi.push;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes11.dex */
class h2 {

    /* renamed from: h  reason: collision with root package name */
    private static String f18683h = "/MiPushLog";
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f18684c;
    private boolean d;

    /* renamed from: e  reason: collision with root package name */
    private int f18685e;
    @SuppressLint({"SimpleDateFormat"})
    private final SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /* renamed from: f  reason: collision with root package name */
    private int f18686f = 2097152;

    /* renamed from: g  reason: collision with root package name */
    private ArrayList<File> f18687g = new ArrayList<>();

    private void e(BufferedReader bufferedReader, BufferedWriter bufferedWriter, Pattern pattern) {
        char[] cArr = new char[4096];
        int read = bufferedReader.read(cArr);
        boolean z = false;
        while (read != -1 && !z) {
            String str = new String(cArr, 0, read);
            Matcher matcher = pattern.matcher(str);
            int i2 = 0;
            int i3 = 0;
            while (true) {
                if (i2 >= read || !matcher.find(i2)) {
                    break;
                }
                int start = matcher.start();
                String substring = str.substring(start, this.b.length() + start);
                if (this.d) {
                    if (substring.compareTo(this.f18684c) > 0) {
                        read = start;
                        z = true;
                        break;
                    }
                } else if (substring.compareTo(this.b) >= 0) {
                    this.d = true;
                    i3 = start;
                }
                int indexOf = str.indexOf(10, start);
                if (indexOf == -1) {
                    indexOf = this.b.length();
                }
                i2 = start + indexOf;
            }
            if (this.d) {
                int i4 = read - i3;
                this.f18685e += i4;
                bufferedWriter.write(cArr, i3, i4);
                if (z || this.f18685e > this.f18686f) {
                    return;
                }
            }
            read = bufferedReader.read(cArr);
        }
    }

    private void f(File file) {
        BufferedReader bufferedReader;
        String str;
        Pattern compile = Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}");
        BufferedReader bufferedReader2 = null;
        try {
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
                try {
                    bufferedWriter.write("model :" + BaseInfo.getDeviceModel() + "; os :" + Build.VERSION.INCREMENTAL + "; uid :" + com.xiaomi.push.service.z0.g() + "; lng :" + Locale.getDefault().toString() + "; sdk :48; andver :" + Build.VERSION.SDK_INT + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                    this.f18685e = 0;
                    Iterator<File> it = this.f18687g.iterator();
                    while (it.hasNext()) {
                        bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(it.next())));
                        try {
                            e(bufferedReader, bufferedWriter, compile);
                            bufferedReader.close();
                            bufferedReader2 = bufferedReader;
                        } catch (FileNotFoundException e2) {
                            e = e2;
                            bufferedReader2 = bufferedWriter;
                            str = "LOG: filter error = " + e.getMessage();
                            g.j.a.a.a.c.B(str);
                            u9.b(bufferedReader2);
                            u9.b(bufferedReader);
                            return;
                        } catch (IOException e3) {
                            e = e3;
                            bufferedReader2 = bufferedWriter;
                            str = "LOG: filter error = " + e.getMessage();
                            g.j.a.a.a.c.B(str);
                            u9.b(bufferedReader2);
                            u9.b(bufferedReader);
                            return;
                        } catch (Throwable th) {
                            th = th;
                            bufferedReader2 = bufferedWriter;
                            u9.b(bufferedReader2);
                            u9.b(bufferedReader);
                            throw th;
                        }
                    }
                    bufferedWriter.write(f1.c().v());
                    u9.b(bufferedWriter);
                    u9.b(bufferedReader2);
                } catch (FileNotFoundException e4) {
                    e = e4;
                    bufferedReader = bufferedReader2;
                } catch (IOException e5) {
                    e = e5;
                    bufferedReader = bufferedReader2;
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader = bufferedReader2;
                }
            } catch (FileNotFoundException e6) {
                e = e6;
                bufferedReader = null;
            } catch (IOException e7) {
                e = e7;
                bufferedReader = null;
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = null;
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }

    h2 a(File file) {
        if (file.exists()) {
            this.f18687g.add(file);
        }
        return this;
    }

    h2 b(Date date, Date date2) {
        String format;
        if (date.after(date2)) {
            this.b = this.a.format(date2);
            format = this.a.format(date);
        } else {
            this.b = this.a.format(date);
            format = this.a.format(date2);
        }
        this.f18684c = format;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public File c(Context context, Date date, Date date2, File file) {
        File file2;
        File file3;
        if ("com.xiaomi.xmsf".equalsIgnoreCase(context.getPackageName())) {
            file2 = new File(context.getExternalFilesDir(null), com.xiaomi.push.service.m0.N);
            if (!file2.exists()) {
                file2 = new File(context.getFilesDir(), com.xiaomi.push.service.m0.N);
            }
            if (!file2.exists()) {
                file2 = context.getFilesDir();
            }
            a(new File(file2, "xmsf.log.1"));
            file3 = new File(file2, "xmsf.log");
        } else {
            file2 = new File(context.getExternalFilesDir(null) + f18683h);
            a(new File(file2, "log0.txt"));
            file3 = new File(file2, "log1.txt");
        }
        a(file3);
        if (file2.isDirectory()) {
            File file4 = new File(file, date.getTime() + "-" + date2.getTime() + ".zip");
            if (file4.exists()) {
                return null;
            }
            b(date, date2);
            long currentTimeMillis = System.currentTimeMillis();
            File file5 = new File(file, "log.txt");
            f(file5);
            g.j.a.a.a.c.B("LOG: filter cost = " + (System.currentTimeMillis() - currentTimeMillis));
            if (file5.exists()) {
                long currentTimeMillis2 = System.currentTimeMillis();
                u9.c(file4, file5);
                g.j.a.a.a.c.B("LOG: zip cost = " + (System.currentTimeMillis() - currentTimeMillis2));
                file5.delete();
                if (file4.exists()) {
                    return file4;
                }
            }
            return null;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(int i2) {
        if (i2 != 0) {
            this.f18686f = i2;
        }
    }
}
