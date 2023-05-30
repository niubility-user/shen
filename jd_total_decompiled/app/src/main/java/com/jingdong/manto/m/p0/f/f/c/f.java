package com.jingdong.manto.m.p0.f.f.c;

import android.media.AudioRecord;
import com.jingdong.manto.m.p0.f.f.c.b;
import com.jingdong.manto.m.p0.f.f.c.l;
import java.io.OutputStream;

/* loaded from: classes15.dex */
public interface f {

    /* loaded from: classes15.dex */
    public static abstract class a implements f {
        final g a;
        final c b;

        /* renamed from: c */
        final d f13548c;
        private final i d = new i();

        /* renamed from: com.jingdong.manto.m.p0.f.f.c.f$a$a */
        /* loaded from: classes15.dex */
        public class RunnableC0602a implements Runnable {
            final /* synthetic */ com.jingdong.manto.m.p0.f.f.c.b a;

            RunnableC0602a(com.jingdong.manto.m.p0.f.f.c.b bVar) {
                a.this = r1;
                this.a = bVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.b.a(this.a);
            }
        }

        a(g gVar, c cVar, d dVar) {
            this.a = gVar;
            this.b = cVar;
            this.f13548c = dVar;
        }

        @Override // com.jingdong.manto.m.p0.f.f.c.f
        public g a() {
            return this.a;
        }

        abstract void a(AudioRecord audioRecord, int i2, OutputStream outputStream);

        void a(com.jingdong.manto.m.p0.f.f.c.b bVar) {
            this.d.a(new RunnableC0602a(bVar));
        }

        @Override // com.jingdong.manto.m.p0.f.f.c.f
        public void a(OutputStream outputStream) {
            a(this.a.b(), this.a.e(), outputStream);
        }

        @Override // com.jingdong.manto.m.p0.f.f.c.f
        public void stop() {
            this.a.a(false);
            this.a.d().stop();
            this.a.d().release();
        }
    }

    /* loaded from: classes15.dex */
    public static final class b extends a {

        /* renamed from: e */
        private final l f13549e;

        /* renamed from: f */
        private long f13550f;

        /* renamed from: g */
        private long f13551g;

        /* renamed from: h */
        private long f13552h;

        public b(g gVar, long j2, d dVar) {
            this(gVar, null, new l.a(), dVar);
            this.f13550f = j2;
        }

        public b(g gVar, c cVar, l lVar, d dVar) {
            super(gVar, cVar, dVar);
            this.f13550f = Long.MAX_VALUE;
            this.f13549e = lVar;
        }

        @Override // com.jingdong.manto.m.p0.f.f.c.f.a
        void a(AudioRecord audioRecord, int i2, OutputStream outputStream) {
            d dVar;
            b.a aVar = new b.a(new byte[i2]);
            long currentTimeMillis = System.currentTimeMillis();
            while (true) {
                this.f13552h = currentTimeMillis;
                if (!this.a.a()) {
                    this.f13552h = System.currentTimeMillis();
                    return;
                }
                currentTimeMillis = System.currentTimeMillis();
                long j2 = this.f13551g + (currentTimeMillis - this.f13552h);
                this.f13551g = j2;
                if (j2 >= this.f13550f && (dVar = this.f13548c) != null) {
                    dVar.a(800);
                }
                aVar.a(audioRecord.read(aVar.b(), 0, i2));
                if (-3 != aVar.a() && -2 != aVar.a()) {
                    if (this.b != null) {
                        a(aVar);
                    }
                    this.f13549e.a(aVar, outputStream);
                }
            }
        }

        @Override // com.jingdong.manto.m.p0.f.f.c.f.a, com.jingdong.manto.m.p0.f.f.c.f
        public void stop() {
            super.stop();
            this.f13552h = 0L;
        }
    }

    /* loaded from: classes15.dex */
    public interface c {
        void a(com.jingdong.manto.m.p0.f.f.c.b bVar);
    }

    /* loaded from: classes15.dex */
    public interface d {
        void a(int i2);
    }

    g a();

    void a(OutputStream outputStream);

    void stop();
}
