package com.jingdong.app.mall.global;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/* loaded from: classes3.dex */
public class b {
    public static String a(File file) {
        BufferedReader bufferedReader = null;
        if (file == null || !file.exists()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        try {
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file));
                while (true) {
                    try {
                        String readLine = bufferedReader2.readLine();
                        if (readLine == null) {
                            break;
                        }
                        sb.append(readLine);
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = bufferedReader2;
                        try {
                            th.printStackTrace();
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            return sb.toString().trim();
                        } catch (Throwable th2) {
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            throw th2;
                        }
                    }
                }
                bufferedReader2.close();
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (IOException e3) {
            e3.printStackTrace();
        }
        return sb.toString().trim();
    }

    public static void b(String str, File file) {
        FileWriter fileWriter;
        if (file == null) {
            return;
        }
        FileWriter fileWriter2 = null;
        try {
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
                fileWriter = new FileWriter(file);
            } catch (Throwable th) {
                th = th;
            }
            try {
                fileWriter.write(str);
                fileWriter.close();
            } catch (Throwable th2) {
                th = th2;
                fileWriter2 = fileWriter;
                try {
                    th.printStackTrace();
                    if (fileWriter2 != null) {
                        fileWriter2.close();
                    }
                } catch (Throwable th3) {
                    if (fileWriter2 != null) {
                        try {
                            fileWriter2.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    throw th3;
                }
            }
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }
}
