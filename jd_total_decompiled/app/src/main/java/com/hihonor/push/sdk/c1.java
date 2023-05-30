package com.hihonor.push.sdk;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.hihonor.push.framework.aidl.DataBuffer;
import com.hihonor.push.framework.aidl.IMessageEntity;
import com.hihonor.push.framework.aidl.IPushInvoke;
import com.hihonor.push.framework.aidl.MessageCodec;
import com.hihonor.push.framework.aidl.entity.RequestHeader;
import com.hihonor.push.sdk.g;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes12.dex */
public class c1 implements Handler.Callback {

    /* renamed from: i */
    public static final c1 f1081i = new c1();

    /* renamed from: g */
    public final Handler f1082g;

    /* renamed from: h */
    public final Map<x0, a> f1083h = new ConcurrentHashMap(5, 0.75f, 1);

    /* loaded from: classes12.dex */
    public class a implements g.a {
        public final Queue<u<?>> a = new LinkedList();
        public final Queue<u<?>> b = new LinkedList();

        /* renamed from: c */
        public final g f1084c = new m(this);
        public com.hihonor.push.sdk.b0.a d = null;

        /* renamed from: e */
        public final x0 f1085e;

        public a(x0 x0Var) {
            c1.this = r1;
            this.f1085e = x0Var;
        }

        public void a() {
            i.h(c1.this.f1082g);
            m mVar = (m) this.f1084c;
            int i2 = mVar.a.get();
            String str = "enter disconnect, connection Status: " + i2;
            if (i2 != 3) {
                if (i2 != 5) {
                    return;
                }
                mVar.a.set(4);
                return;
            }
            t tVar = mVar.d;
            if (tVar != null) {
                tVar.c();
            }
            mVar.a.set(1);
        }

        public final synchronized void b(u<?> uVar) {
            Type type;
            this.b.add(uVar);
            g gVar = this.f1084c;
            b bVar = new b(uVar);
            uVar.getClass();
            Object obj = null;
            try {
                Type genericSuperclass = uVar.getClass().getGenericSuperclass();
                Class cls = (genericSuperclass == null || (type = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]) == null) ? null : (Class) type;
                if (cls != null && !cls.isPrimitive()) {
                    obj = cls.newInstance();
                }
            } catch (Exception e2) {
                l.a("In newResponseInstance, instancing exception." + e2.getMessage());
            }
            y yVar = new y(obj, bVar);
            String str = "start transport parse. " + uVar.b;
            IPushInvoke iPushInvoke = ((m) gVar).b;
            String str2 = uVar.b;
            RequestHeader requestHeader = uVar.f1126e;
            IMessageEntity iMessageEntity = uVar.f1125c;
            Bundle bundle = new Bundle();
            Bundle bundle2 = new Bundle();
            MessageCodec.formMessageEntity(requestHeader, bundle);
            MessageCodec.formMessageEntity(iMessageEntity, bundle2);
            DataBuffer dataBuffer = new DataBuffer(str2, bundle, bundle2);
            if (iPushInvoke != null) {
                try {
                    iPushInvoke.call(dataBuffer, yVar);
                } catch (Exception e3) {
                    String str3 = "transport remote error. " + e3;
                }
            }
        }

        public final synchronized void c(com.hihonor.push.sdk.b0.a aVar) {
            i.h(c1.this.f1082g);
            Iterator<u<?>> it = this.a.iterator();
            while (it.hasNext()) {
                it.next().b(aVar.toApiException(), null);
            }
            this.a.clear();
            this.d = aVar;
            a();
            c1.this.f1083h.remove(this.f1085e);
        }

        public final synchronized void d() {
            i.h(c1.this.f1082g);
            this.d = null;
            Iterator<u<?>> it = this.a.iterator();
            while (it.hasNext()) {
                b(it.next());
            }
            this.a.clear();
        }
    }

    /* loaded from: classes12.dex */
    public static class b implements a0 {
        public u<?> a;

        public b(u<?> uVar) {
            this.a = uVar;
        }
    }

    public c1() {
        HandlerThread handlerThread = new HandlerThread("HonorApiManager");
        handlerThread.start();
        this.f1082g = new Handler(handlerThread.getLooper(), this);
    }

    public <TResult> e<TResult> a(u<TResult> uVar) {
        j0<TResult> j0Var = new j0<>();
        uVar.a = j0Var;
        Handler handler = this.f1082g;
        handler.sendMessage(handler.obtainMessage(1, uVar));
        return j0Var.a;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        a aVar;
        int i2 = message.what;
        if (i2 != 1) {
            if (i2 == 2) {
                u uVar = (u) message.obj;
                x0 x0Var = uVar.d;
                if (x0Var != null && this.f1083h.containsKey(x0Var) && (aVar = this.f1083h.get(x0Var)) != null) {
                    synchronized (aVar) {
                        String str = "resolveResult apiCall " + uVar.b;
                        aVar.b.remove(uVar);
                        if (aVar.a.peek() == null || aVar.b.peek() == null) {
                            aVar.a();
                            c1.this.f1083h.remove(aVar.f1085e);
                        }
                    }
                }
                return true;
            }
            return false;
        }
        u<?> uVar2 = (u) message.obj;
        x0 x0Var2 = uVar2.d;
        a aVar2 = this.f1083h.get(x0Var2);
        if (aVar2 == null) {
            aVar2 = new a(x0Var2);
            this.f1083h.put(x0Var2, aVar2);
        }
        synchronized (aVar2) {
            i.h(c1.this.f1082g);
            String str2 = "sendRequest " + uVar2.b;
            if (((m) aVar2.f1084c).b()) {
                aVar2.b(uVar2);
            } else {
                aVar2.a.add(uVar2);
                com.hihonor.push.sdk.b0.a aVar3 = aVar2.d;
                if (aVar3 != null && aVar3.getErrorCode() != 0) {
                    aVar2.c(aVar2.d);
                } else {
                    synchronized (aVar2) {
                        i.h(c1.this.f1082g);
                        if (!((m) aVar2.f1084c).b()) {
                            if (!(((m) aVar2.f1084c).a.get() == 5)) {
                                m mVar = (m) aVar2.f1084c;
                                mVar.getClass();
                                int i3 = mVar.a.get();
                                String str3 = "enter connect, connection Status: " + i3;
                                if (i3 != 3 && i3 != 5 && i3 != 4) {
                                    f0 f0Var = f0.f1091e;
                                    int m2 = i.m(f0Var.a());
                                    if (m2 == com.hihonor.push.sdk.b0.a.SUCCESS.getErrorCode()) {
                                        mVar.a.set(5);
                                        com.hihonor.push.sdk.j.a c2 = i.c(f0Var.a());
                                        t tVar = new t(c2);
                                        mVar.d = tVar;
                                        tVar.f1119h = new k(mVar);
                                        if (!c2.a()) {
                                            String str4 = "bind core is null : " + tVar.f1118g;
                                            tVar.b(8002004);
                                        } else {
                                            Intent intent = new Intent();
                                            String c3 = tVar.f1118g.c();
                                            String b2 = tVar.f1118g.b();
                                            String d = tVar.f1118g.d();
                                            if (!TextUtils.isEmpty(d)) {
                                                intent.setComponent(new ComponentName(c3, d));
                                            } else {
                                                intent.setAction(b2);
                                                intent.setPackage(c3);
                                            }
                                            synchronized (t.f1117k) {
                                                if (f0Var.a().bindService(intent, tVar, 1)) {
                                                    Handler handler = tVar.f1120i;
                                                    if (handler != null) {
                                                        handler.removeMessages(1001);
                                                    } else {
                                                        tVar.f1120i = new Handler(Looper.getMainLooper(), new p(tVar));
                                                    }
                                                    tVar.f1120i.sendEmptyMessageDelayed(1001, 10000L);
                                                } else {
                                                    tVar.f1121j = true;
                                                    tVar.b(8002001);
                                                }
                                            }
                                        }
                                    } else {
                                        mVar.a(m2);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
