package com.sina.weibo.sdk.net;

import android.os.Bundle;
import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public final class e implements d {

    /* renamed from: i  reason: collision with root package name */
    private String f16078i;

    /* renamed from: j  reason: collision with root package name */
    private Bundle f16079j = new Bundle();

    /* renamed from: k  reason: collision with root package name */
    private Bundle f16080k = new Bundle();

    /* renamed from: l  reason: collision with root package name */
    private Map<String, Object<File>> f16081l = new HashMap();

    /* renamed from: m  reason: collision with root package name */
    private Map<String, byte[]> f16082m = new HashMap();

    /* renamed from: n  reason: collision with root package name */
    private int f16083n;
    private int o;

    /* loaded from: classes9.dex */
    public static final class a {

        /* renamed from: i  reason: collision with root package name */
        public String f16084i;

        /* renamed from: j  reason: collision with root package name */
        Bundle f16085j = new Bundle();

        /* renamed from: k  reason: collision with root package name */
        Bundle f16086k = new Bundle();

        /* renamed from: l  reason: collision with root package name */
        Map<String, Object<File>> f16087l = new HashMap();

        /* renamed from: m  reason: collision with root package name */
        Map<String, byte[]> f16088m = new HashMap();

        /* renamed from: n  reason: collision with root package name */
        int f16089n = 30000;
        int o = 60000;

        public final a a(String str, Object obj) {
            a(this.f16085j, str, obj);
            return this;
        }

        public final a b(String str, Object obj) {
            a(this.f16086k, str, obj);
            return this;
        }

        public final e e() {
            return new e(this);
        }

        private void a(Bundle bundle, String str, Object obj) {
            if (obj != null) {
                if (obj instanceof String) {
                    bundle.putString(str, String.valueOf(obj));
                } else if (obj instanceof Integer) {
                    bundle.putInt(str, ((Integer) obj).intValue());
                } else if (obj instanceof Short) {
                    bundle.putShort(str, ((Short) obj).shortValue());
                } else if (obj instanceof Character) {
                    bundle.putChar(str, ((Character) obj).charValue());
                } else if (obj instanceof Byte) {
                    bundle.putByte(str, ((Byte) obj).byteValue());
                } else if (obj instanceof Long) {
                    bundle.putLong(str, ((Long) obj).longValue());
                } else if (obj instanceof Float) {
                    bundle.putFloat(str, ((Float) obj).floatValue());
                } else if (obj instanceof Double) {
                    bundle.putDouble(str, ((Double) obj).doubleValue());
                } else if (obj instanceof Boolean) {
                    bundle.putBoolean(str, ((Boolean) obj).booleanValue());
                } else if (obj instanceof byte[]) {
                    this.f16088m.put(str, (byte[]) obj);
                } else if (obj instanceof Serializable) {
                    bundle.putSerializable(str, (Serializable) obj);
                } else {
                    throw new IllegalArgumentException("Unsupported params type!");
                }
            }
        }
    }

    public e(a aVar) {
        this.f16078i = aVar.f16084i;
        this.f16079j.putAll(aVar.f16085j);
        this.f16080k.putAll(aVar.f16086k);
        this.f16081l.putAll(aVar.f16087l);
        this.f16082m.putAll(aVar.f16088m);
        this.f16083n = aVar.f16089n;
        this.o = aVar.o;
    }

    @Override // com.sina.weibo.sdk.net.d
    public final Bundle d() {
        return this.f16080k;
    }

    @Override // com.sina.weibo.sdk.net.d
    public final int getConnectTimeout() {
        return this.f16083n;
    }

    @Override // com.sina.weibo.sdk.net.d
    public final Bundle getParams() {
        return this.f16079j;
    }

    @Override // com.sina.weibo.sdk.net.d
    public final int getReadTimeout() {
        return this.o;
    }

    @Override // com.sina.weibo.sdk.net.d
    public final String getUrl() {
        return this.f16078i;
    }
}
