package com.tencent.smtt.sdk;

import android.content.Context;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/* loaded from: classes9.dex */
public class l {
    private static l a;
    private static Context b;

    private l() {
    }

    public static l a(Context context) {
        if (a == null) {
            synchronized (l.class) {
                if (a == null) {
                    a = new l();
                }
            }
        }
        b = context.getApplicationContext();
        return a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:225:0x004f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:231:0x0059 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private Properties e() {
        FileInputStream fileInputStream;
        BufferedInputStream bufferedInputStream;
        Properties properties;
        FileInputStream fileInputStream2;
        Exception e2;
        BufferedInputStream bufferedInputStream2;
        FileInputStream fileInputStream3;
        BufferedInputStream bufferedInputStream3 = null;
        try {
            try {
                try {
                    File a2 = a();
                    properties = new Properties();
                    if (a2 != null) {
                        try {
                            fileInputStream2 = new FileInputStream(a2);
                            try {
                                bufferedInputStream2 = new BufferedInputStream(fileInputStream2);
                            } catch (Exception e3) {
                                bufferedInputStream2 = null;
                                e2 = e3;
                            } catch (Throwable th) {
                                bufferedInputStream = null;
                                th = th;
                                fileInputStream = fileInputStream2;
                                if (bufferedInputStream != null) {
                                    try {
                                        bufferedInputStream.close();
                                    } catch (IOException e4) {
                                        e4.printStackTrace();
                                    }
                                }
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e5) {
                                        e5.printStackTrace();
                                    }
                                }
                                throw th;
                            }
                        } catch (Exception e6) {
                            e = e6;
                            fileInputStream2 = 0;
                            e2 = e;
                            bufferedInputStream2 = fileInputStream2;
                            e2.printStackTrace();
                            if (bufferedInputStream2 != null) {
                            }
                            if (fileInputStream2 != 0) {
                            }
                            return properties;
                        }
                        try {
                            properties.load(bufferedInputStream2);
                            bufferedInputStream3 = bufferedInputStream2;
                            fileInputStream3 = fileInputStream2;
                        } catch (Exception e7) {
                            e2 = e7;
                            e2.printStackTrace();
                            if (bufferedInputStream2 != null) {
                                try {
                                    bufferedInputStream2.close();
                                } catch (IOException e8) {
                                    e8.printStackTrace();
                                }
                            }
                            if (fileInputStream2 != 0) {
                                try {
                                    fileInputStream2.close();
                                } catch (IOException e9) {
                                    e9.printStackTrace();
                                }
                            }
                            return properties;
                        }
                    } else {
                        fileInputStream3 = null;
                    }
                    if (bufferedInputStream3 != null) {
                        try {
                            bufferedInputStream3.close();
                        } catch (IOException e10) {
                            e10.printStackTrace();
                        }
                    }
                    if (fileInputStream3 != null) {
                        try {
                            fileInputStream3.close();
                        } catch (IOException e11) {
                            e11.printStackTrace();
                        }
                    }
                    return properties;
                } catch (Exception e12) {
                    e = e12;
                    properties = null;
                    fileInputStream2 = null;
                }
            } catch (Throwable th2) {
                fileInputStream = null;
                th = th2;
                bufferedInputStream = null;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    File a() {
        File file = new File(m.o(b), "tbscoreinstall.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e2) {
                e2.printStackTrace();
                return null;
            }
        }
        return file;
    }

    public void a(int i2) {
        a("dexopt_retry_num", i2);
    }

    public void a(int i2, int i3) {
        a("copy_core_ver", i2);
        a("copy_status", i3);
    }

    public void a(String str) {
        a("install_apk_path", str);
    }

    public void a(String str, int i2) {
        a(str, String.valueOf(i2));
    }

    public void a(String str, String str2) {
        FileOutputStream fileOutputStream = null;
        try {
            try {
                try {
                    Properties e2 = e();
                    if (e2 != null) {
                        e2.setProperty(str, str2);
                        File a2 = a();
                        if (a2 != null) {
                            FileOutputStream fileOutputStream2 = new FileOutputStream(a2);
                            try {
                                e2.store(fileOutputStream2, "update " + str + " and status!");
                                fileOutputStream = fileOutputStream2;
                            } catch (Exception e3) {
                                e = e3;
                                fileOutputStream = fileOutputStream2;
                                e.printStackTrace();
                                if (fileOutputStream != null) {
                                    fileOutputStream.close();
                                }
                                return;
                            } catch (Throwable th) {
                                th = th;
                                fileOutputStream = fileOutputStream2;
                                if (fileOutputStream != null) {
                                    try {
                                        fileOutputStream.close();
                                    } catch (IOException e4) {
                                        e4.printStackTrace();
                                    }
                                }
                                throw th;
                            }
                        }
                    }
                } catch (Exception e5) {
                    e = e5;
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e6) {
            e6.printStackTrace();
        }
    }

    public int b() {
        return c("install_core_ver");
    }

    public int b(String str) {
        Properties e2 = e();
        if (e2 != null && e2.getProperty(str) != null) {
            try {
                return Integer.parseInt(e2.getProperty(str));
            } catch (Throwable unused) {
            }
        }
        return -1;
    }

    public void b(int i2) {
        a("unzip_retry_num", i2);
    }

    public void b(int i2, int i3) {
        a("tpatch_ver", i2);
        a("tpatch_status", i3);
    }

    public int c() {
        return b("install_status");
    }

    public int c(String str) {
        Properties e2 = e();
        if (e2 != null && e2.getProperty(str) != null) {
            try {
                return Integer.parseInt(e2.getProperty(str));
            } catch (Throwable unused) {
            }
        }
        return 0;
    }

    public void c(int i2) {
        a("incrupdate_status", i2);
    }

    public void c(int i2, int i3) {
        a("install_core_ver", i2);
        a("install_status", i3);
    }

    public int d() {
        return b("incrupdate_status");
    }

    public String d(String str) {
        Properties e2 = e();
        if (e2 == null || e2.getProperty(str) == null) {
            return null;
        }
        return e2.getProperty(str);
    }

    public void d(int i2) {
        a("unlzma_status", i2);
    }
}
