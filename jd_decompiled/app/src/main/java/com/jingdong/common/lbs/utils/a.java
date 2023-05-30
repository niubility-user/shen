package com.jingdong.common.lbs.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public final class a {
    private int a;
    private int b;

    /* renamed from: c  reason: collision with root package name */
    private int f12382c;
    private boolean d;

    /* renamed from: e  reason: collision with root package name */
    private String f12383e;

    /* renamed from: f  reason: collision with root package name */
    private String f12384f;

    /* renamed from: g  reason: collision with root package name */
    private Map<String, String> f12385g;

    /* renamed from: h  reason: collision with root package name */
    private Map<String, List<String>> f12386h;

    /* renamed from: com.jingdong.common.lbs.utils.a$a  reason: collision with other inner class name */
    /* loaded from: classes5.dex */
    public static class C0428a {
        public String a;
        public String d;

        /* renamed from: f  reason: collision with root package name */
        private boolean f12389f;
        public int b = 0;

        /* renamed from: c  reason: collision with root package name */
        public Map<String, String> f12387c = new HashMap();

        /* renamed from: e  reason: collision with root package name */
        public int f12388e = 1;

        /* renamed from: g  reason: collision with root package name */
        private int f12390g = 5000;

        public final a a() {
            return new a(this.a, this.b, this.f12387c, this.d, this.f12389f, this.f12388e, this.f12390g, (byte) 0);
        }
    }

    private a(String str, int i2, Map<String, String> map, String str2, boolean z, int i3, int i4) {
        this.b = 1;
        this.f12382c = 5000;
        this.f12383e = str;
        this.a = i2;
        this.f12385g = map;
        this.f12384f = str2;
        this.d = z;
        this.b = i3;
        this.f12382c = i4;
    }

    /* synthetic */ a(String str, int i2, Map map, String str2, boolean z, int i3, int i4, byte b) {
        this(str, i2, map, str2, z, i3, i4);
    }

    private static void a(HttpURLConnection httpURLConnection, String str) {
        if (httpURLConnection != null) {
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            if (str != null) {
                dataOutputStream.write(str.getBytes());
            }
            dataOutputStream.flush();
            dataOutputStream.close();
        }
    }

    private static byte[] a(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[8192];
        while (true) {
            int read = inputStream.read(bArr, 0, 8192);
            if (read == -1) {
                byteArrayOutputStream.flush();
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x00cc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:114:0x00c1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00a6 A[Catch: all -> 0x0109, TryCatch #5 {all -> 0x0109, blocks: (B:13:0x0056, B:15:0x005a, B:16:0x0062, B:18:0x0068, B:19:0x007a, B:22:0x0082, B:23:0x0085, B:26:0x0093, B:31:0x00a6, B:33:0x00af, B:32:0x00ab, B:24:0x0089, B:25:0x008f), top: B:104:0x0056 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00ab A[Catch: all -> 0x0109, TryCatch #5 {all -> 0x0109, blocks: (B:13:0x0056, B:15:0x005a, B:16:0x0062, B:18:0x0068, B:19:0x007a, B:22:0x0082, B:23:0x0085, B:26:0x0093, B:31:0x00a6, B:33:0x00af, B:32:0x00ab, B:24:0x0089, B:25:0x008f), top: B:104:0x0056 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.util.Pair<java.lang.Integer, byte[]> a() {
        /*
            Method dump skipped, instructions count: 329
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.lbs.utils.a.a():android.util.Pair");
    }
}
