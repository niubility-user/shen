package com.jdpay.net;

import android.util.SparseArray;
import androidx.annotation.NonNull;
import com.jdpay.lib.converter.Converter;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes18.dex */
public abstract class Request<RPC extends Converter> {
    private static final AtomicInteger count = new AtomicInteger();
    protected boolean encrypt;
    protected final SparseArray<Object> extras;
    protected final int id;
    protected RPC responseConverter;

    /* loaded from: classes18.dex */
    public interface Builder<T extends Request, RPC extends Converter> {
        T build();

        Builder setEncrypt(boolean z);

        Builder setExtras(SparseArray<Object> sparseArray);

        Builder setResponseConverter(@NonNull RPC rpc);
    }

    public Request() {
        this(count.incrementAndGet(), 3);
    }

    public void clearExtra() {
        this.extras.clear();
    }

    public Object getExtra(int i2) {
        return this.extras.get(i2);
    }

    public int getId() {
        return this.id;
    }

    public RPC getResponseConverter() {
        return this.responseConverter;
    }

    public boolean isEncrypt() {
        return this.encrypt;
    }

    public void putExtra(int i2, Object obj) {
        this.extras.put(i2, obj);
    }

    public void removeExtra(int i2) {
        this.extras.remove(i2);
    }

    public void setEncrypt(boolean z) {
        this.encrypt = z;
    }

    public Request(int i2) {
        this(count.incrementAndGet(), i2);
    }

    public Request(int i2, int i3) {
        this.id = i2;
        this.extras = new SparseArray<>(i3);
    }
}
