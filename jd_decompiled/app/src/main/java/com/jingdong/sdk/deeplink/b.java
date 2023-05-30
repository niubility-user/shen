package com.jingdong.sdk.deeplink;

import android.content.Context;
import com.jingdong.sdk.deeplink.a;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class b {

    /* renamed from: c  reason: collision with root package name */
    private static volatile b f14706c;
    private ArrayList<a> a;
    private Context b;

    private b() {
    }

    public static b a() {
        if (f14706c == null) {
            synchronized (b.class) {
                if (f14706c == null) {
                    f14706c = new b();
                }
            }
        }
        return f14706c;
    }

    private int c(String str) {
        InputStream open;
        try {
            open = this.b.getAssets().open(str);
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (JSONException e3) {
            e3.printStackTrace();
        }
        if (open == null) {
            return -1;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(open);
        byte[] bArr = new byte[4096];
        while (true) {
            int read = bufferedInputStream.read(bArr);
            if (read == -1) {
                break;
            }
            bufferedOutputStream.write(bArr, 0, read);
        }
        bufferedInputStream.close();
        bufferedOutputStream.flush();
        JSONArray jSONArray = new JSONArray(byteArrayOutputStream.toString());
        int length = jSONArray.length();
        for (int i2 = 0; i2 < length; i2++) {
            JSONObject jSONObject = (JSONObject) jSONArray.get(i2);
            int optInt = jSONObject.optInt("type");
            this.a.add(new a(jSONObject.optString("bundle"), jSONObject.getString("uri"), a.EnumC0712a.values()[optInt], jSONObject.getString("activity"), jSONObject.optString("method")));
        }
        return 0;
    }

    public synchronized void b(Context context) {
        this.b = context;
    }

    public synchronized a d(String str) {
        if (this.b != null) {
            if (this.a == null) {
                this.a = new ArrayList<>();
                c("deeplinkMain.json");
            }
            if (this.a.size() <= 0) {
                return null;
            }
            Iterator<a> it = this.a.iterator();
            while (it.hasNext()) {
                a next = it.next();
                if (next.d(str)) {
                    return next;
                }
            }
            return null;
        }
        throw new NullPointerException("mContext is null, should call initDeepLinks() when application init");
    }
}
