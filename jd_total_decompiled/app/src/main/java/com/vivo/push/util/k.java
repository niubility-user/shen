package com.vivo.push.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.vivo.push.d.r;
import com.vivo.push.model.InsideNotificationItem;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public final class k extends AsyncTask<String, Void, List<Bitmap>> {
    private Context a;
    private InsideNotificationItem b;

    /* renamed from: c  reason: collision with root package name */
    private long f18308c;
    private boolean d;

    /* renamed from: e  reason: collision with root package name */
    private int f18309e = 0;

    /* renamed from: f  reason: collision with root package name */
    private r.a f18310f;

    public k(Context context, InsideNotificationItem insideNotificationItem, long j2, boolean z, r.a aVar) {
        this.a = context;
        this.b = insideNotificationItem;
        this.f18308c = j2;
        this.d = z;
        this.f18310f = aVar;
    }

    @Override // android.os.AsyncTask
    protected final /* synthetic */ void onPostExecute(List<Bitmap> list) {
        List<Bitmap> list2 = list;
        super.onPostExecute(list2);
        p.c("ImageDownTask", "onPostExecute");
        com.vivo.push.m.c(new l(this, list2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0088, code lost:
        if (r5 == null) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x008a, code lost:
        r5.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0094, code lost:
        if (r5 == null) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0097, code lost:
        r6 = null;
     */
    @Override // android.os.AsyncTask
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public List<Bitmap> doInBackground(String... strArr) {
        InputStream inputStream;
        Bitmap bitmap;
        this.f18309e = this.b.getNotifyDisplayStatus();
        InputStream inputStream2 = null;
        if (!this.d) {
            p.d("ImageDownTask", "bitmap is not display by forbid net");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < 2; i2++) {
            String str = strArr[i2];
            p.d("ImageDownTask", "imgUrl=" + str + " i=" + i2);
            if (!TextUtils.isEmpty(str)) {
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                    httpURLConnection.setConnectTimeout(30000);
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setUseCaches(false);
                    httpURLConnection.connect();
                    int responseCode = httpURLConnection.getResponseCode();
                    p.c("ImageDownTask", "code=".concat(String.valueOf(responseCode)));
                    if (responseCode == 200) {
                        inputStream = httpURLConnection.getInputStream();
                        try {
                            try {
                                bitmap = BitmapFactory.decodeStream(inputStream);
                            } catch (MalformedURLException unused) {
                                p.a("ImageDownTask", "MalformedURLException");
                            } catch (IOException unused2) {
                                p.a("ImageDownTask", "IOException");
                            }
                        } catch (Throwable th) {
                            th = th;
                            inputStream2 = inputStream;
                            if (inputStream2 != null) {
                                try {
                                    inputStream2.close();
                                } catch (Exception unused3) {
                                }
                            }
                            throw th;
                        }
                    } else {
                        inputStream = null;
                        bitmap = null;
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception unused4) {
                        }
                    }
                } catch (MalformedURLException unused5) {
                    inputStream = null;
                } catch (IOException unused6) {
                    inputStream = null;
                } catch (Throwable th2) {
                    th = th2;
                }
                arrayList.add(bitmap);
            } else if (i2 == 0) {
                arrayList.add(null);
            }
        }
        return arrayList;
    }
}
