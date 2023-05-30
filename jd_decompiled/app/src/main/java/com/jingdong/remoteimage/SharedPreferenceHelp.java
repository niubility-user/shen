package com.jingdong.remoteimage;

import android.content.Context;
import com.jingdong.sdk.utils.d.a;

/* loaded from: classes7.dex */
public class SharedPreferenceHelp {
    private static volatile SharedPreferenceHelp defaultInstance;
    private Context context;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SharedPreferenceHelp getDefault(Context context) {
        SharedPreferenceHelp sharedPreferenceHelp = defaultInstance;
        if (sharedPreferenceHelp == null) {
            synchronized (a.class) {
                sharedPreferenceHelp = defaultInstance;
                if (sharedPreferenceHelp == null) {
                    sharedPreferenceHelp = new SharedPreferenceHelp();
                    sharedPreferenceHelp.context = context;
                    defaultInstance = sharedPreferenceHelp;
                }
            }
        }
        return sharedPreferenceHelp;
    }

    public long getLong(String str, long j2) {
        return a.b(this.context).a(str, j2);
    }

    public String getString(String str, String str2) {
        return a.b(this.context).c(str, str2);
    }

    public void putLong(String str, Long l2) {
        a.b(this.context).d(str, l2.longValue());
    }

    public void putString(String str, String str2) {
        a.b(this.context).e(str, str2);
    }
}
