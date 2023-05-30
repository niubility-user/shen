package com.jingdong.manto.pkg.b;

import android.text.TextUtils;
import com.jingdong.manto.utils.MantoLog;
import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes16.dex */
public class e {

    /* renamed from: g */
    public static final ByteOrder f13978g = ByteOrder.BIG_ENDIAN;
    public final File a;
    private volatile FileChannel b;

    /* renamed from: c */
    public volatile boolean f13979c;
    private volatile int d;

    /* renamed from: e */
    private volatile int f13980e;

    /* renamed from: f */
    public volatile Map<String, a> f13981f;

    /* loaded from: classes16.dex */
    public final class a {
        public int a;
        public int b;

        public a(e eVar, String str, int i2, int i3) {
            this.a = i2;
            this.b = i3;
        }
    }

    public e(File file) {
        this.b = null;
        this.f13979c = true;
        this.d = 0;
        this.f13980e = -1;
        this.f13981f = null;
        this.a = file;
        if (((file == null || !file.exists() || file.length() <= 14) ? (char) 0 : '\uffff') == 0 || !a()) {
            this.f13979c = false;
        }
    }

    public e(String str) {
        this(new File(str));
    }

    private boolean a() {
        if (this.b == null) {
            try {
                this.b = new RandomAccessFile(this.a, "r").getChannel();
            } catch (Throwable unused) {
            }
        }
        if (this.b == null) {
            return false;
        }
        try {
            this.b.position(0L);
            ByteBuffer allocate = ByteBuffer.allocate(14);
            allocate.order(f13978g);
            this.b.read(allocate);
            if (-66 == allocate.get(0) && -19 == allocate.get(13)) {
                byte[] array = allocate.array();
                b.a(array, 1);
                this.d = b.a(array, 5);
                b.a(array, 9);
                return true;
            }
        } catch (Throwable unused2) {
        }
        return false;
    }

    public final InputStream a(String str) {
        a aVar;
        if (!this.f13979c) {
            MantoLog.e("MantoPkg", "pkg file is invalid");
            return null;
        } else if (this.f13981f == null || TextUtils.isEmpty(str) || (aVar = this.f13981f.get(b.a(str))) == null) {
            return null;
        } else {
            try {
                MappedByteBuffer map = this.b.map(FileChannel.MapMode.READ_ONLY, aVar.a, aVar.b);
                map.order(f13978g);
                map.limit(aVar.b);
                return new com.jingdong.manto.pkg.b.a(map);
            } catch (Throwable unused) {
                return null;
            }
        }
    }

    public final boolean b() {
        if (this.f13979c && this.b != null) {
            int i2 = 4;
            if (this.d > 4) {
                if (this.f13981f != null && this.f13980e >= 0 && this.f13980e == this.f13981f.size()) {
                    return true;
                }
                try {
                    this.b.position(14L);
                    ByteBuffer allocate = ByteBuffer.allocate(this.d);
                    allocate.order(f13978g);
                    this.b.read(allocate);
                    byte[] array = allocate.array();
                    this.f13980e = b.a(array, 0);
                    HashMap hashMap = new HashMap();
                    a aVar = null;
                    int i3 = 0;
                    while (i3 < this.f13980e) {
                        int a2 = b.a(array, i2);
                        int i4 = i2 + 4;
                        String str = new String(array, i4, a2);
                        int i5 = i4 + a2;
                        int a3 = b.a(array, i5);
                        int i6 = i5 + 4;
                        int a4 = b.a(array, i6);
                        i2 = i6 + 4;
                        a aVar2 = new a(this, str, a3, a4);
                        hashMap.put(str, aVar2);
                        i3++;
                        aVar = aVar2;
                    }
                    this.f13981f = hashMap;
                    if (aVar != null) {
                        if (aVar.a + aVar.b > this.a.length()) {
                            return false;
                        }
                    }
                    return true;
                } catch (Throwable unused) {
                }
            }
        }
        return false;
    }
}
