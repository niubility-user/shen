package com.xiaomi.push;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.xiaomi.push.i;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class z2 extends i.a {

    /* renamed from: g */
    private Context f19347g;

    /* renamed from: h */
    private SharedPreferences f19348h;

    /* renamed from: i */
    private com.xiaomi.push.service.b0 f19349i;

    public z2(Context context) {
        this.f19347g = context;
        this.f19348h = context.getSharedPreferences("mipush_extra", 0);
        this.f19349i = com.xiaomi.push.service.b0.d(context);
    }

    private void a() {
        SharedPreferences.Editor edit = this.f19348h.edit();
        edit.putLong("last_upload_data_timestamp", System.currentTimeMillis() / 1000);
        edit.commit();
    }

    private List<k7> c(File file) {
        RandomAccessFile randomAccessFile;
        FileInputStream fileInputStream;
        q2 a = r2.b().a();
        String a2 = a == null ? "" : a.a();
        FileLock fileLock = null;
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        byte[] bArr = new byte[4];
        synchronized (u2.a) {
            try {
                File file2 = new File(this.f19347g.getExternalFilesDir(null), "push_cdata.lock");
                u9.f(file2);
                randomAccessFile = new RandomAccessFile(file2, "rw");
                try {
                    FileLock lock = randomAccessFile.getChannel().lock();
                    try {
                        fileInputStream = new FileInputStream(file);
                        while (fileInputStream.read(bArr) == 4) {
                            try {
                                int a3 = c.a(bArr);
                                byte[] bArr2 = new byte[a3];
                                if (fileInputStream.read(bArr2) != a3) {
                                    break;
                                }
                                byte[] c2 = t2.c(a2, bArr2);
                                if (c2 != null && c2.length != 0) {
                                    k7 k7Var = new k7();
                                    m8.e(k7Var, c2);
                                    arrayList.add(k7Var);
                                    d(k7Var);
                                }
                            } catch (Exception unused) {
                                fileLock = lock;
                                if (fileLock != null && fileLock.isValid()) {
                                    try {
                                        fileLock.release();
                                    } catch (IOException unused2) {
                                    }
                                }
                                u9.b(fileInputStream);
                                u9.b(randomAccessFile);
                                return arrayList;
                            } catch (Throwable th) {
                                th = th;
                                fileLock = lock;
                                if (fileLock != null && fileLock.isValid()) {
                                    try {
                                        fileLock.release();
                                    } catch (IOException unused3) {
                                    }
                                }
                                u9.b(fileInputStream);
                                u9.b(randomAccessFile);
                                throw th;
                            }
                        }
                        if (lock != null && lock.isValid()) {
                            try {
                                lock.release();
                            } catch (IOException unused4) {
                            }
                        }
                        u9.b(fileInputStream);
                    } catch (Exception unused5) {
                        fileInputStream = null;
                    } catch (Throwable th2) {
                        th = th2;
                        fileInputStream = null;
                    }
                } catch (Exception unused6) {
                    fileInputStream = null;
                } catch (Throwable th3) {
                    th = th3;
                    fileInputStream = null;
                }
            } catch (Exception unused7) {
                randomAccessFile = null;
                fileInputStream = null;
            } catch (Throwable th4) {
                th = th4;
                randomAccessFile = null;
                fileInputStream = null;
            }
            u9.b(randomAccessFile);
        }
        return arrayList;
    }

    private void d(k7 k7Var) {
        if (k7Var.f164a != e7.AppInstallList || k7Var.f165a.startsWith("same_")) {
            return;
        }
        SharedPreferences.Editor edit = this.f19348h.edit();
        edit.putLong("dc_job_result_time_4", k7Var.a);
        edit.putString("dc_job_result_4", p0.b(k7Var.f165a));
        edit.commit();
    }

    private boolean e() {
        if (j0.s(this.f19347g)) {
            return false;
        }
        if ((j0.u(this.f19347g) || j0.t(this.f19347g)) && !g()) {
            return true;
        }
        return (j0.v(this.f19347g) && !f()) || j0.w(this.f19347g);
    }

    private boolean f() {
        if (this.f19349i.m(h7.Upload3GSwitch.a(), true)) {
            return Math.abs((System.currentTimeMillis() / 1000) - this.f19348h.getLong("last_upload_data_timestamp", -1L)) > ((long) Math.max((int) RemoteMessageConst.DEFAULT_TTL, this.f19349i.a(h7.Upload3GFrequency.a(), 432000)));
        }
        return false;
    }

    private boolean g() {
        if (this.f19349i.m(h7.Upload4GSwitch.a(), true)) {
            return Math.abs((System.currentTimeMillis() / 1000) - this.f19348h.getLong("last_upload_data_timestamp", -1L)) > ((long) Math.max((int) RemoteMessageConst.DEFAULT_TTL, this.f19349i.a(h7.Upload4GFrequency.a(), 259200)));
        }
        return false;
    }

    @Override // com.xiaomi.push.i.a
    public String b() {
        return "1";
    }

    @Override // java.lang.Runnable
    public void run() {
        File file = new File(this.f19347g.getExternalFilesDir(null), "push_cdata.data");
        if (!j0.r(this.f19347g)) {
            if (file.length() > 1863680) {
                file.delete();
            }
        } else if (!e() && file.exists()) {
            List<k7> c2 = c(file);
            if (!d.a(c2)) {
                int size = c2.size();
                if (size > 4000) {
                    c2 = c2.subList(size - 4000, size);
                }
                v7 v7Var = new v7();
                v7Var.a(c2);
                byte[] h2 = u9.h(m8.f(v7Var));
                c8 c8Var = new c8("-1", false);
                c8Var.c(m7.DataCollection.f179a);
                c8Var.a(h2);
                q2 a = r2.b().a();
                if (a != null) {
                    a.a(c8Var, c7.Notification, null);
                }
                a();
            }
            file.delete();
        }
    }
}
