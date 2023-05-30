package com.jd.stat.security.jma.a;

import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes18.dex */
public class f {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0058 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r6v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static List<String> a(String str) {
        Throwable th;
        FileReader fileReader;
        FileReader fileReader2 = null;
        try {
            try {
                ArrayList arrayList = new ArrayList();
                File file = new File(str);
                if (file.exists() && file.isFile()) {
                    fileReader = new FileReader(file);
                    try {
                        BufferedReader bufferedReader = new BufferedReader(fileReader);
                        while (true) {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                break;
                            } else if (!TextUtils.isEmpty(readLine)) {
                                arrayList.add(readLine);
                            }
                        }
                        fileReader2 = fileReader;
                    } catch (FileNotFoundException unused) {
                        if (fileReader != null) {
                            try {
                                fileReader.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        return null;
                    } catch (IOException e3) {
                        e = e3;
                        e.printStackTrace();
                        if (fileReader != null) {
                            try {
                                fileReader.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        return null;
                    }
                }
                if (fileReader2 != null) {
                    try {
                        fileReader2.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
                return arrayList;
            } catch (FileNotFoundException unused2) {
                fileReader = null;
            } catch (IOException e6) {
                e = e6;
                fileReader = null;
            } catch (Throwable th2) {
                th = th2;
                str = 0;
                if (str != 0) {
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            if (str != 0) {
                try {
                    str.close();
                } catch (IOException e7) {
                    e7.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static void a(String str, String str2, boolean z) {
        File file = new File(str2);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        BufferedWriter bufferedWriter = null;
        try {
            try {
                try {
                    BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, z)));
                    try {
                        bufferedWriter2.write(str);
                        bufferedWriter2.newLine();
                        bufferedWriter2.close();
                    } catch (Exception e3) {
                        e = e3;
                        bufferedWriter = bufferedWriter2;
                        e.printStackTrace();
                        if (bufferedWriter != null) {
                            bufferedWriter.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        bufferedWriter = bufferedWriter2;
                        if (bufferedWriter != null) {
                            try {
                                bufferedWriter.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Exception e5) {
                    e = e5;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e6) {
            e6.printStackTrace();
        }
    }
}
