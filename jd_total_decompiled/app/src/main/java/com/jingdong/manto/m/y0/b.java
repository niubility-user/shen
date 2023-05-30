package com.jingdong.manto.m.y0;

import android.util.Base64;
import com.jingdong.jdreact.plugin.viewshot.ViewShot;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.codec.CharEncoding;

/* loaded from: classes15.dex */
interface b {
    public static final Charset a = Charset.forName("UTF-8");

    /* loaded from: classes15.dex */
    public static class a {
        static final Map<String, b> a;

        /* renamed from: com.jingdong.manto.m.y0.b$a$a  reason: collision with other inner class name */
        /* loaded from: classes15.dex */
        class C0636a implements b {
            private final Charset b = Charset.forName(CharEncoding.US_ASCII);

            C0636a() {
            }

            @Override // com.jingdong.manto.m.y0.b
            public final ByteBuffer a(String str) {
                return ByteBuffer.wrap(str.getBytes(this.b));
            }
        }

        /* renamed from: com.jingdong.manto.m.y0.b$a$b  reason: collision with other inner class name */
        /* loaded from: classes15.dex */
        class C0637b implements b {
            C0637b() {
            }

            @Override // com.jingdong.manto.m.y0.b
            public final ByteBuffer a(String str) {
                return ByteBuffer.wrap(Base64.decode(str.getBytes(b.a), 2));
            }
        }

        /* loaded from: classes15.dex */
        class c implements b {
            c() {
            }

            @Override // com.jingdong.manto.m.y0.b
            public final ByteBuffer a(String str) {
                return ByteBuffer.wrap(new BigInteger(str, 16).toByteArray());
            }
        }

        /* loaded from: classes15.dex */
        class d implements b {
            final /* synthetic */ Charset b;

            d(Charset charset) {
                this.b = charset;
            }

            @Override // com.jingdong.manto.m.y0.b
            public final ByteBuffer a(String str) {
                return ByteBuffer.wrap(str.getBytes(this.b)).order(ByteOrder.LITTLE_ENDIAN);
            }
        }

        /* loaded from: classes15.dex */
        class e implements b {
            final /* synthetic */ Charset b;

            e(Charset charset) {
                this.b = charset;
            }

            @Override // com.jingdong.manto.m.y0.b
            public final ByteBuffer a(String str) {
                return ByteBuffer.wrap(str.getBytes(this.b)).order(ByteOrder.LITTLE_ENDIAN);
            }
        }

        /* loaded from: classes15.dex */
        class f implements b {
            f() {
            }

            @Override // com.jingdong.manto.m.y0.b
            public final ByteBuffer a(String str) {
                return ByteBuffer.wrap(str.getBytes(b.a));
            }
        }

        /* loaded from: classes15.dex */
        class g implements b {
            private final Charset b = Charset.forName(CharEncoding.ISO_8859_1);

            g() {
            }

            @Override // com.jingdong.manto.m.y0.b
            public final ByteBuffer a(String str) {
                return ByteBuffer.wrap(str.getBytes(this.b));
            }
        }

        static {
            HashMap hashMap = new HashMap();
            a = hashMap;
            hashMap.put("ascii", new C0636a());
            hashMap.put(ViewShot.Results.BASE_64, new C0637b());
            hashMap.put("hex", new c());
            d dVar = new d(Charset.forName("ISO-10646-UCS-2"));
            hashMap.put("ucs2", dVar);
            hashMap.put("ucs-2", dVar);
            e eVar = new e(Charset.forName(CharEncoding.UTF_16LE));
            hashMap.put("utf16le", eVar);
            hashMap.put("utf-16le", eVar);
            f fVar = new f();
            hashMap.put("utf8", fVar);
            hashMap.put("utf-8", fVar);
            g gVar = new g();
            hashMap.put("latin1", gVar);
            hashMap.put("binary", gVar);
        }
    }

    ByteBuffer a(String str);
}
