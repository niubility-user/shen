package com.jd.libs.hybrid.offlineload.entity;

import androidx.annotation.WorkerThread;
import com.jd.dynamic.DYConstants;
import com.jd.libs.hybrid.base.util.Log;
import com.jingdong.jdsdk.constant.JshopConst;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class PreReadInputStream extends InputStream {

    /* renamed from: g  reason: collision with root package name */
    private BufferedInputStream f6007g;

    /* renamed from: h  reason: collision with root package name */
    private BufferedInputStream f6008h;

    /* renamed from: i  reason: collision with root package name */
    private ByteArrayOutputStream f6009i;

    /* renamed from: j  reason: collision with root package name */
    private boolean f6010j;

    /* renamed from: k  reason: collision with root package name */
    private boolean f6011k = true;

    /* renamed from: l  reason: collision with root package name */
    private AtomicBoolean f6012l = new AtomicBoolean(false);

    /* renamed from: m  reason: collision with root package name */
    private AtomicBoolean f6013m = new AtomicBoolean(false);

    /* renamed from: n  reason: collision with root package name */
    private AtomicBoolean f6014n = new AtomicBoolean(false);

    public PreReadInputStream(BufferedInputStream bufferedInputStream) {
        this.f6010j = true;
        this.f6007g = bufferedInputStream;
        this.f6010j = false;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.f6014n.compareAndSet(false, true)) {
            finishPreRead();
            Log.d(com.jd.jdcache.match.PreReadInputStream.TAG, String.format(Locale.getDefault(), "close pre-read stream, readStreamFinish=%b, unreadStreamFinish=%b", Boolean.valueOf(this.f6011k), Boolean.valueOf(this.f6010j)));
            BufferedInputStream bufferedInputStream = this.f6008h;
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                    this.f6008h = null;
                } catch (Throwable th) {
                    th = th;
                    this.f6008h = null;
                }
            }
            th = null;
            BufferedInputStream bufferedInputStream2 = this.f6007g;
            if (bufferedInputStream2 != null) {
                try {
                    bufferedInputStream2.close();
                } catch (Throwable th2) {
                    th = th2;
                }
                this.f6007g = null;
            }
            this.f6009i = null;
            if (th != null) {
                Log.e(com.jd.jdcache.match.PreReadInputStream.TAG, th);
                if (th instanceof IOException) {
                    throw th;
                }
                throw new IOException(th);
            }
        }
    }

    public void finishPreRead() {
        if (this.f6014n.get() || !this.f6013m.compareAndSet(false, true)) {
            return;
        }
        Log.d(com.jd.jdcache.match.PreReadInputStream.TAG, "Stop pre-read stream.");
        synchronized (this) {
            ByteArrayOutputStream byteArrayOutputStream = this.f6009i;
            if (byteArrayOutputStream != null) {
                this.f6008h = new BufferedInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
                this.f6011k = false;
                Log.d(com.jd.jdcache.match.PreReadInputStream.TAG, String.format(Locale.getDefault(), "Pre-read data size=%d, unreadStreamFinish=%b", Integer.valueOf(byteArrayOutputStream.size()), Boolean.valueOf(this.f6010j)));
            }
        }
    }

    public JSONObject getStateInfo() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("unreadFinish", this.f6010j);
        jSONObject.put("readFinish", this.f6011k);
        jSONObject.put("preReadStarted", this.f6012l.get());
        jSONObject.put("preReadStop", this.f6013m.get());
        ByteArrayOutputStream byteArrayOutputStream = this.f6009i;
        jSONObject.put("readDataSize", byteArrayOutputStream == null ? DYConstants.DY_NULL_STR : Integer.valueOf(byteArrayOutputStream.size()));
        jSONObject.put(JshopConst.JSKEY_SHOP_CLOSED, this.f6014n.get());
        return jSONObject;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        int i2;
        try {
            if (this.f6011k) {
                i2 = -1;
            } else {
                BufferedInputStream bufferedInputStream = this.f6008h;
                i2 = bufferedInputStream != null ? bufferedInputStream.read() : -1;
                if (-1 == i2) {
                    Log.d(com.jd.jdcache.match.PreReadInputStream.TAG, "Read from readStream finished.");
                }
            }
            if (-1 == i2) {
                this.f6011k = true;
                if (!this.f6010j) {
                    BufferedInputStream bufferedInputStream2 = this.f6007g;
                    if (bufferedInputStream2 != null) {
                        i2 = bufferedInputStream2.read();
                    }
                    if (-1 == i2) {
                        this.f6010j = true;
                        Log.d(com.jd.jdcache.match.PreReadInputStream.TAG, "Read from unreadStream finished.");
                    }
                }
            }
            return i2;
        } catch (Throwable th) {
            Log.e(com.jd.jdcache.match.PreReadInputStream.TAG, th);
            if (th instanceof IOException) {
                throw th;
            }
            throw new IOException(th);
        }
    }

    @WorkerThread
    public void startPreRead() {
        BufferedInputStream bufferedInputStream = this.f6007g;
        if (bufferedInputStream == null) {
            return;
        }
        if (!this.f6012l.compareAndSet(false, true)) {
            Log.e(com.jd.jdcache.match.PreReadInputStream.TAG, "Pre-read already started, cannot start twice.");
            return;
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            this.f6009i = byteArrayOutputStream;
            byte[] bArr = new byte[10240];
            synchronized (this) {
                Log.d(com.jd.jdcache.match.PreReadInputStream.TAG, "Start to pre-read stream.");
                int i2 = 0;
                while (!this.f6013m.get() && (i2 = bufferedInputStream.read(bArr)) != -1) {
                    byteArrayOutputStream.write(bArr, 0, i2);
                }
                if (-1 == i2) {
                    this.f6010j = true;
                    finishPreRead();
                }
            }
        } catch (Exception unused) {
            Log.e(com.jd.jdcache.match.PreReadInputStream.TAG, "Pre-read stream error");
        }
    }
}
