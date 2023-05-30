package com.xiaomi.push;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes11.dex */
public class m2 implements g.j.a.a.a.a {

    /* renamed from: e  reason: collision with root package name */
    private static final SimpleDateFormat f18852e = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss aaa");

    /* renamed from: f  reason: collision with root package name */
    public static String f18853f = "/MiPushLog";

    /* renamed from: g  reason: collision with root package name */
    private static List<Pair<String, Throwable>> f18854g = Collections.synchronizedList(new ArrayList());

    /* renamed from: h  reason: collision with root package name */
    private static volatile m2 f18855h;
    private String a;
    private Context b;

    /* renamed from: c  reason: collision with root package name */
    private String f18856c = "";
    private Handler d;

    private m2(Context context) {
        this.b = context;
        if (context.getApplicationContext() != null) {
            this.b = context.getApplicationContext();
        }
        this.a = this.b.getPackageName() + "-" + Process.myPid();
        HandlerThread handlerThread = new HandlerThread("Log2FileHandlerThread");
        handlerThread.start();
        this.d = new Handler(handlerThread.getLooper());
    }

    public static m2 b(Context context) {
        if (f18855h == null) {
            synchronized (m2.class) {
                if (f18855h == null) {
                    f18855h = new m2(context);
                }
            }
        }
        return f18855h;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        FileLock fileLock;
        RandomAccessFile randomAccessFile;
        File file;
        File externalFilesDir;
        BufferedWriter bufferedWriter = null;
        try {
            if (TextUtils.isEmpty(this.f18856c) && (externalFilesDir = this.b.getExternalFilesDir(null)) != null) {
                this.f18856c = externalFilesDir.getAbsolutePath() + "";
            }
            file = new File(this.f18856c + f18853f);
        } catch (Exception unused) {
            fileLock = null;
            randomAccessFile = null;
        } catch (Throwable th) {
            th = th;
            fileLock = null;
            randomAccessFile = null;
        }
        if ((!file.exists() || !file.isDirectory()) && !file.mkdirs()) {
            return;
        }
        File file2 = new File(file, "log.lock");
        if (!file2.exists() || file2.isDirectory()) {
            file2.createNewFile();
        }
        randomAccessFile = new RandomAccessFile(file2, "rw");
        try {
            fileLock = randomAccessFile.getChannel().lock();
            try {
                BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(file, "log1.txt"), true)));
                while (!f18854g.isEmpty()) {
                    try {
                        Pair<String, Throwable> remove = f18854g.remove(0);
                        String str = (String) remove.first;
                        if (remove.second != null) {
                            str = (str + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE) + Log.getStackTraceString((Throwable) remove.second);
                        }
                        bufferedWriter2.write(str + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                    } catch (Exception unused2) {
                        bufferedWriter = bufferedWriter2;
                        if (bufferedWriter != null) {
                            try {
                                bufferedWriter.close();
                            } catch (IOException unused3) {
                            }
                        }
                        if (fileLock != null && fileLock.isValid()) {
                            try {
                                fileLock.release();
                            } catch (IOException unused4) {
                            }
                        }
                        if (randomAccessFile == null) {
                            return;
                        }
                        randomAccessFile.close();
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedWriter = bufferedWriter2;
                        if (bufferedWriter != null) {
                            try {
                                bufferedWriter.close();
                            } catch (IOException unused5) {
                            }
                        }
                        if (fileLock != null && fileLock.isValid()) {
                            try {
                                fileLock.release();
                            } catch (IOException unused6) {
                            }
                        }
                        if (randomAccessFile != null) {
                            try {
                                randomAccessFile.close();
                            } catch (IOException unused7) {
                            }
                        }
                        throw th;
                    }
                }
                bufferedWriter2.flush();
                bufferedWriter2.close();
                File file3 = new File(file, "log1.txt");
                if (file3.length() >= 1048576) {
                    File file4 = new File(file, "log0.txt");
                    if (file4.exists() && file4.isFile()) {
                        file4.delete();
                    }
                    file3.renameTo(file4);
                }
                if (fileLock != null && fileLock.isValid()) {
                    try {
                        fileLock.release();
                    } catch (IOException unused8) {
                    }
                }
            } catch (Exception unused9) {
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Exception unused10) {
            fileLock = null;
        } catch (Throwable th4) {
            th = th4;
            fileLock = null;
        }
        try {
            randomAccessFile.close();
        } catch (IOException unused11) {
        }
    }

    @Override // g.j.a.a.a.a
    public final void a(String str, Throwable th) {
        this.d.post(new n2(this, str, th));
    }

    @Override // g.j.a.a.a.a
    public final void log(String str) {
        a(str, null);
    }
}
