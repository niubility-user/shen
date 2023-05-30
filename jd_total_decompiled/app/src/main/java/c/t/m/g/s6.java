package c.t.m.g;

import android.content.ContentProviderClient;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

/* loaded from: classes.dex */
public class s6 {
    public Context a;

    public s6(Context context) {
        this.a = context;
    }

    public String a() {
        Bundle call;
        Uri parse = Uri.parse("content://cn.nubia.identity/identity");
        try {
            int i2 = Build.VERSION.SDK_INT;
            if (i2 > 17) {
                ContentProviderClient acquireContentProviderClient = this.a.getContentResolver().acquireContentProviderClient(parse);
                call = acquireContentProviderClient.call("getOAID", null, null);
                if (acquireContentProviderClient != null) {
                    if (i2 >= 24) {
                        acquireContentProviderClient.close();
                    } else {
                        acquireContentProviderClient.release();
                    }
                }
            } else {
                call = this.a.getContentResolver().call(parse, "getOAID", (String) null, (Bundle) null);
            }
        } catch (Exception unused) {
        }
        if (call.getInt("code", -1) == 0) {
            return call.getString("id");
        }
        call.getString("message");
        return null;
    }
}
