package com.jd.libs.xwin.http;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.annotation.Keep;
import com.huawei.hms.framework.common.ContainerUtils;
import java.io.File;
import java.util.Arrays;
import java.util.Locale;

@Keep
/* loaded from: classes16.dex */
public class BreakPointHelper {
    public static boolean breakPointSwitch;
    private static volatile BreakPointHelper instance;
    private SharedPreferences.Editor editor;
    private SharedPreferences sp;

    private BreakPointHelper() {
    }

    public static BreakPointHelper getInstance() {
        if (instance == null) {
            synchronized (BreakPointHelper.class) {
                if (instance == null) {
                    instance = new BreakPointHelper();
                }
            }
        }
        return instance;
    }

    private void removeUrl(String str, boolean z) {
        if (z) {
            File file = new File(this.sp.getString(str, "noBreakPoint"));
            if (file.exists()) {
                file.delete();
            }
        }
        this.editor.remove(str);
        this.editor.remove(str + "Id");
        this.editor.apply();
    }

    public void addBreakPointInfo(String str, String str2) {
        SharedPreferences.Editor editor = this.editor;
        if (editor != null) {
            editor.putString(str, str2);
            this.editor.apply();
        }
    }

    public String getBreakPointBytes(String str, String str2) {
        SharedPreferences sharedPreferences = this.sp;
        if (sharedPreferences == null) {
            boolean z = breakPointSwitch;
            return "";
        }
        String string = sharedPreferences.getString(str, "noBreakPoint");
        if ("noBreakPoint".equals(string)) {
            return "";
        }
        if (!str2.equals(string)) {
            removeUrl(str, true);
            return "";
        }
        File file = new File(string);
        if (file.exists()) {
            return String.format(Locale.CHINESE, "bytes=%d-", Long.valueOf(file.length()));
        }
        removeUrl(str, false);
        return "";
    }

    public String getFilePath(String str) {
        return getFilePath(null, str);
    }

    public String getFilePath(String str, String str2) {
        String str3;
        if (this.sp == null) {
            return "";
        }
        if (!TextUtils.isEmpty(str)) {
            String string = this.sp.getString("Id" + str, "");
            if (!Arrays.asList(string.split(ContainerUtils.FIELD_DELIMITER)).contains(str2)) {
                this.editor.putString(str2 + "Id", str);
                SharedPreferences.Editor editor = this.editor;
                String str4 = "Id" + str;
                if (TextUtils.isEmpty(string)) {
                    str3 = str2;
                } else {
                    str3 = string + ContainerUtils.FIELD_DELIMITER + str2;
                }
                editor.putString(str4, str3);
                this.editor.apply();
            }
        }
        String string2 = this.sp.getString(str2, "noBreakPoint");
        return new File(string2).exists() ? string2 : "";
    }

    public synchronized BreakPointHelper init(Context context) {
        if (this.sp == null && context != null) {
            SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("BreakPoint", 0);
            this.sp = sharedPreferences;
            this.editor = sharedPreferences.edit();
        }
        return this;
    }

    public void removeBreakPointInfo(String str) {
        if (this.editor != null) {
            String string = this.sp.getString(str + "Id", "");
            removeUrl(str, false);
            if (TextUtils.isEmpty(string)) {
                return;
            }
            removeId(string);
        }
    }

    public void removeId(String str) {
        SharedPreferences sharedPreferences = this.sp;
        if (sharedPreferences == null) {
            return;
        }
        for (String str2 : sharedPreferences.getString("Id" + str, "").split(ContainerUtils.FIELD_DELIMITER)) {
            removeUrl(str2, true);
        }
        this.editor.remove("Id" + str);
        this.editor.apply();
    }
}
