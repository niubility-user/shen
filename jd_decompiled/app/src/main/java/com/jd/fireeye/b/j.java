package com.jd.fireeye.b;

import android.text.TextUtils;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes13.dex */
public class j {
    public static String a() {
        String str;
        String str2 = null;
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Method declaredMethod = cls.getDeclaredMethod(IMantoServerRequester.GET, String.class);
            str = (String) declaredMethod.invoke(cls, "ro.huaweiMarket.jingdong.path");
            try {
                str2 = (String) declaredMethod.invoke(cls, "ro.preinstall.path");
            } catch (Exception unused) {
            }
        } catch (Exception unused2) {
            str = null;
        }
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str)) {
            arrayList.add(str);
        }
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
        }
        arrayList.add("/data/etc/appchannel");
        arrayList.add("/system/etc");
        arrayList.add("/system/etc/appchannel");
        try {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                File file = new File((String) it.next(), "jdPreInstalledInfo.dat");
                if (file.exists() && file.isFile()) {
                    FileReader fileReader = new FileReader(file);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine != null) {
                            sb.append(readLine);
                        } else {
                            bufferedReader.close();
                            fileReader.close();
                            return sb.toString();
                        }
                    }
                }
            }
            return "";
        } catch (Exception unused3) {
            return "";
        }
    }
}
