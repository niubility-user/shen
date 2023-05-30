package com.jingdong.manto.r;

import android.os.Process;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.manto.utils.MantoLog;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes16.dex */
public class a {
    /* JADX WARN: Code restructure failed: missing block: B:30:0x005c, code lost:
        if (r4 == 0) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0073, code lost:
        if (r4 == 0) goto L45;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:68:0x008b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0081 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v14, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r4v18 */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v20 */
    /* JADX WARN: Type inference failed for: r4v21, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int a(int i2) {
        BufferedReader bufferedReader;
        FileReader fileReader;
        IOException e2;
        FileNotFoundException e3;
        ?? r4 = "/sys/devices/system/cpu/cpu" + i2 + "/cpufreq/cpuinfo_max_freq";
        FileReader fileReader2 = null;
        int i3 = 0;
        try {
            try {
                fileReader = new FileReader((String) r4);
                try {
                    r4 = new BufferedReader(fileReader);
                } catch (FileNotFoundException e4) {
                    e3 = e4;
                    r4 = 0;
                } catch (IOException e5) {
                    e2 = e5;
                    r4 = 0;
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = null;
                    fileReader2 = fileReader;
                    if (fileReader2 != null) {
                    }
                    if (bufferedReader != null) {
                    }
                    throw th;
                }
            } catch (FileNotFoundException e6) {
                fileReader = null;
                e3 = e6;
                r4 = 0;
            } catch (IOException e7) {
                fileReader = null;
                e2 = e7;
                r4 = 0;
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = null;
                if (fileReader2 != null) {
                    try {
                        fileReader2.close();
                    } catch (IOException e8) {
                        e8.printStackTrace();
                    }
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e9) {
                        e9.printStackTrace();
                    }
                }
                throw th;
            }
            try {
                i3 = Integer.parseInt(r4.readLine().trim());
                try {
                    fileReader.close();
                } catch (IOException e10) {
                    e10.printStackTrace();
                }
            } catch (FileNotFoundException e11) {
                e3 = e11;
                e3.printStackTrace();
                if (fileReader != null) {
                    try {
                        fileReader.close();
                    } catch (IOException e12) {
                        e12.printStackTrace();
                    }
                }
            } catch (IOException e13) {
                e2 = e13;
                e2.printStackTrace();
                if (fileReader != null) {
                    try {
                        fileReader.close();
                    } catch (IOException e14) {
                        e14.printStackTrace();
                    }
                }
            }
            try {
                r4.close();
            } catch (IOException e15) {
                e15.printStackTrace();
            }
            return i3;
        } catch (Throwable th3) {
            bufferedReader = r4;
            th = th3;
        }
    }

    public static List<Integer> a() {
        BufferedReader bufferedReader;
        String readLine;
        String str;
        int intValue;
        String str2;
        int intValue2;
        String str3;
        ArrayList arrayList = new ArrayList();
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/" + Process.myPid() + "/task/" + Process.myTid() + "/status")), 1000);
        } catch (Throwable th) {
            th = th;
        }
        try {
            do {
                try {
                    readLine = bufferedReader.readLine();
                    if (readLine != null) {
                    }
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader2 = bufferedReader;
                    try {
                        th.printStackTrace();
                        if (bufferedReader2 != null) {
                            bufferedReader = bufferedReader2;
                            break;
                            bufferedReader.close();
                        }
                        return arrayList;
                    } catch (Throwable th3) {
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (IOException unused) {
                            }
                        }
                        throw th3;
                    }
                }
                break;
            } while (!readLine.startsWith("Cpus_allowed_list"));
            break;
            bufferedReader.close();
        } catch (IOException unused2) {
        }
        String[] split = readLine.trim().split(":");
        if (split == null || split.length != 2) {
            str = "";
        } else {
            str = split[1];
            if (str.startsWith("\t")) {
                str = str.substring(1);
            }
        }
        if (!TextUtils.isEmpty(str)) {
            String[] split2 = str.split(DYConstants.DY_REGEX_COMMA);
            if (split2.length > 1) {
                for (String str4 : split2) {
                    String[] split3 = str4.split("-");
                    if (split3.length != 2) {
                        try {
                            bufferedReader.close();
                        } catch (IOException unused3) {
                        }
                        return null;
                    }
                    if (Integer.valueOf(split3[0]).intValue() < Integer.valueOf(split3[1]).intValue()) {
                        intValue2 = Integer.valueOf(split3[0]).intValue();
                        str3 = split3[1];
                    } else {
                        intValue2 = Integer.valueOf(split3[1]).intValue();
                        str3 = split3[0];
                    }
                    int intValue3 = Integer.valueOf(str3).intValue();
                    while (intValue2 < intValue3 + 1) {
                        arrayList.add(Integer.valueOf(intValue2));
                        intValue2++;
                    }
                }
            } else {
                String[] split4 = split2[0].split("-");
                if (split4.length > 1) {
                    if (Integer.valueOf(split4[0]).intValue() < Integer.valueOf(split4[1]).intValue()) {
                        intValue = Integer.valueOf(split4[0]).intValue();
                        str2 = split4[1];
                    } else {
                        intValue = Integer.valueOf(split4[1]).intValue();
                        str2 = split4[0];
                    }
                    int intValue4 = Integer.valueOf(str2).intValue();
                    while (intValue < intValue4 + 1) {
                        arrayList.add(Integer.valueOf(intValue));
                        intValue++;
                    }
                } else {
                    arrayList.add(Integer.valueOf(Integer.valueOf(split2[0]).intValue()));
                }
            }
        }
        return arrayList;
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x0074, code lost:
        if (r4 == null) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0088, code lost:
        if (r4 == null) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x008a, code lost:
        r4.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x008e, code lost:
        r4 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x008f, code lost:
        r6 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0094, code lost:
        r6 = 0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static List<Integer> a(List<Integer> list) {
        BufferedReader bufferedReader;
        IOException e2;
        FileReader fileReader;
        FileNotFoundException e3;
        int i2;
        FileReader fileReader2 = null;
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < list.size(); i3++) {
            try {
                fileReader = new FileReader("/sys/devices/system/cpu/cpu" + list.get(i3) + "/cpufreq/scaling_cur_freq");
                try {
                    bufferedReader = new BufferedReader(fileReader);
                } catch (FileNotFoundException e4) {
                    e3 = e4;
                    bufferedReader = null;
                } catch (IOException e5) {
                    e2 = e5;
                    bufferedReader = null;
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = null;
                }
            } catch (FileNotFoundException e6) {
                e3 = e6;
                bufferedReader = null;
                fileReader = null;
            } catch (IOException e7) {
                e2 = e7;
                bufferedReader = null;
                fileReader = null;
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = null;
            }
            try {
                try {
                    i2 = Integer.parseInt(bufferedReader.readLine().trim());
                    try {
                        fileReader.close();
                    } catch (IOException e8) {
                        e8.printStackTrace();
                    }
                } catch (FileNotFoundException e9) {
                    e3 = e9;
                    e3.printStackTrace();
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (IOException e10) {
                            e10.printStackTrace();
                        }
                    }
                } catch (IOException e11) {
                    e2 = e11;
                    e2.printStackTrace();
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (IOException e12) {
                            e12.printStackTrace();
                        }
                    }
                }
                try {
                    bufferedReader.close();
                } catch (IOException e13) {
                    e = e13;
                    e.printStackTrace();
                    arrayList.add(Integer.valueOf(i2));
                }
                arrayList.add(Integer.valueOf(i2));
            } catch (Throwable th3) {
                th = th3;
                fileReader2 = fileReader;
                if (fileReader2 != null) {
                    try {
                        fileReader2.close();
                    } catch (IOException e14) {
                        e14.printStackTrace();
                    }
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e15) {
                        e15.printStackTrace();
                    }
                }
                throw th;
            }
        }
        return arrayList;
    }

    public static float b() {
        List<Integer> a = a();
        List<Integer> a2 = a(a);
        float f2 = 0.0f;
        if (a != null && a.size() != 0 && a2 != null && a2.size() != 0) {
            float size = 1.0f / a2.size();
            for (int i2 = 0; i2 < a.size(); i2++) {
                int intValue = a.get(i2).intValue();
                f2 += ((100.0f * size) * a2.get(i2).intValue()) / a(intValue);
            }
            MantoLog.d("cpuUtil", "rate: " + f2);
        }
        return f2;
    }
}
