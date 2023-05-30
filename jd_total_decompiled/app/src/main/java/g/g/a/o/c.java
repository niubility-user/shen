package g.g.a.o;

import android.content.Context;
import android.media.AudioTrack;
import android.os.Process;
import g.g.a.f;
import g.g.a.l;
import java.lang.Thread;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: classes18.dex */
public class c extends g.g.a.o.a {
    static Object u = new Object();

    /* renamed from: n  reason: collision with root package name */
    private Thread f19654n;
    private boolean o;
    private BlockingQueue<g.g.a.b> p;
    private int q;
    private String r;
    private boolean s;
    private Runnable t;

    /* loaded from: classes18.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            f.c("TTSBufPlayer", "TTSBufPlayer Thread Start");
            Process.setThreadPriority(-1);
            while (!c.this.o) {
                f.c("TTSBufPlayer", "TTSBufPlayer playRunnable, size=" + c.this.p.size());
                synchronized (c.this.p) {
                    try {
                        if (c.this.p.size() == 0) {
                            c.this.p.wait();
                        }
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                }
                f.c("TTSBufPlayer", "mAudioBuf wake\uff0c playerSatus=" + c.this.f19649j + ", isCancel=" + c.this.o);
                if (c.this.f19649j == b.Pause) {
                    try {
                        Thread.sleep(800L);
                    } catch (InterruptedException unused) {
                    }
                } else {
                    while (true) {
                        c cVar = c.this;
                        if (cVar.f19649j == b.Start && !cVar.o) {
                            c cVar2 = c.this;
                            if (cVar2.f19647h) {
                                int i2 = cVar2.f19644e - cVar2.f19645f;
                                cVar2.f19646g = i2;
                                if (i2 <= 0) {
                                    cVar2.f19648i = true;
                                    break;
                                } else if (cVar2.f19648i) {
                                    if (i2 > cVar2.d) {
                                        cVar2.f19648i = false;
                                    } else {
                                        cVar2.f19648i = true;
                                        break;
                                    }
                                }
                            }
                            g.g.a.b bVar = (g.g.a.b) cVar2.p.poll();
                            if (bVar == null) {
                                c.this.f19649j = b.Idle;
                                break;
                            }
                            l a = bVar.a();
                            if (a.getErrno() < 0) {
                                c.this.a.onError(bVar.f(), a);
                                f.c("TTSBufPlayer", "player post TTSErrorCode=" + a.getErrno());
                                break;
                            }
                            c cVar3 = c.this;
                            cVar3.f19645f++;
                            cVar3.j(bVar);
                        }
                    }
                }
            }
        }
    }

    public c(Context context, int i2, int i3, int i4) {
        super(i2, i3, i4);
        this.f19654n = null;
        this.o = false;
        this.p = new LinkedBlockingQueue();
        this.q = 0;
        this.r = "";
        this.s = false;
        this.t = new a();
        f.c("TTSBufPlayer", "New TTSBufPlayer:sampleRate=" + i2 + ", audioFormat=" + i3 + ", channel=" + i4);
        l();
    }

    private void h() {
        f.c("TTSBufPlayer", "destroyPlayThread");
        try {
            try {
                Thread thread = this.f19654n;
                if (thread != null && Thread.State.RUNNABLE == thread.getState()) {
                    try {
                        Thread.sleep(500L);
                        this.f19654n.interrupt();
                    } catch (Exception unused) {
                        this.f19654n = null;
                    }
                }
                this.f19654n = null;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } finally {
            this.f19654n = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j(g.g.a.b bVar) {
        int length = bVar.c().length;
        if (length <= 0) {
            f.c("TTSBufPlayer", "audio data len == 0, process=" + bVar.d());
            return;
        }
        f.c("TTSBufPlayer", "play Current:textID=" + bVar.f() + ", process=" + bVar.d() + ", index=" + bVar.b() + ", data size=" + length);
        this.r = bVar.f();
        if (bVar.e() == 1 && bVar.a().getErrno() >= 0) {
            this.a.c(bVar.f());
            this.s = true;
        }
        if (bVar.a().getErrno() >= 0) {
            this.a.d(bVar.f(), bVar.d());
        }
        if (bVar.a() == l.OK_NO) {
            byte[] c2 = bVar.c();
            this.b.play();
            this.b.write(c2, 0, length);
        }
        if (bVar.b() >= 0 || bVar.a().getErrno() < 0) {
            return;
        }
        this.a.f(bVar.f());
        this.s = false;
        int i2 = this.q;
        if (i2 > 0) {
            try {
                Thread.sleep(i2);
            } catch (InterruptedException unused) {
            }
        }
    }

    private void l() {
        h();
        if (this.f19654n == null) {
            Thread thread = new Thread(this.t);
            this.f19654n = thread;
            thread.start();
        }
    }

    public void f(g.g.a.b bVar) {
        if (bVar == null) {
            f.b("TTSBufPlayer", "AudioDataRecv  data null");
            return;
        }
        int b = bVar.b();
        f.c("TTSBufPlayer", "TTSBufPlayer data:index=" + bVar.e() + "process=" + bVar.d() + ", mAudioBuf len=" + this.p.size() + ", playerSatus=" + this.f19649j);
        synchronized (u) {
            this.p.add(bVar);
            this.f19644e++;
            if (b == 1) {
                this.f19647h = true;
            } else if (b < 0) {
                this.f19647h = false;
            }
            if (this.f19649j == b.Idle || b == 1) {
                this.f19649j = b.Start;
            }
            synchronized (this.p) {
                this.p.notifyAll();
            }
        }
    }

    public int g() {
        f.c("TTSBufPlayer", "TTSBufPlayer cancel");
        synchronized (u) {
            this.o = true;
            synchronized (this.p) {
                this.p.notifyAll();
            }
            h();
            AudioTrack audioTrack = this.b;
            if (audioTrack != null) {
                audioTrack.release();
                this.b = null;
            }
        }
        return 0;
    }

    public void i() {
        f.c("TTSBufPlayer", "TTSBufPlayer PlayerSatus=" + this.f19649j + ", to=pause");
        synchronized (u) {
            this.f19649j = b.Pause;
            this.b.pause();
            this.b.flush();
            d dVar = this.a;
            if (dVar != null) {
                dVar.b(this.r);
            }
        }
        f.c("TTSBufPlayer", "TTSBufPlayer PlayerSatus =" + this.f19649j + ", to=pause, end");
    }

    public void k() {
        f.c("TTSBufPlayer", "TTSBufPlayer PlayerSatus=" + this.f19649j + ", to=resume");
        synchronized (u) {
            synchronized (this.p) {
                this.p.notifyAll();
                this.f19649j = b.Start;
                this.b.play();
                d dVar = this.a;
                if (dVar != null) {
                    dVar.a(this.r);
                }
            }
        }
    }

    public void m() {
        f.c("TTSBufPlayer", "TTSBufPlayer PlayerSatus=" + this.f19649j + ", to=stop");
        synchronized (u) {
            this.f19649j = b.Stop;
            AudioTrack audioTrack = this.b;
            if (audioTrack != null) {
                audioTrack.stop();
            }
            this.p.clear();
            d dVar = this.a;
            if (dVar != null) {
                dVar.e(this.r);
                if (this.s) {
                    this.s = false;
                    this.a.f(this.r);
                }
            }
        }
        this.f19649j = b.Idle;
    }
}
