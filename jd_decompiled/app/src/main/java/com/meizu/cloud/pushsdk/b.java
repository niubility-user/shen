package com.meizu.cloud.pushsdk;

import android.content.Context;
import android.content.Intent;
import android.util.SparseArray;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.handler.MzPushMessage;
import com.meizu.cloud.pushsdk.handler.c;
import com.meizu.cloud.pushsdk.handler.f.d;
import com.meizu.cloud.pushsdk.handler.f.e;
import com.meizu.cloud.pushsdk.handler.f.f;
import com.meizu.cloud.pushsdk.handler.f.g;
import com.meizu.cloud.pushsdk.notification.PushNotificationBuilder;
import com.meizu.cloud.pushsdk.platform.message.PushSwitchStatus;
import com.meizu.cloud.pushsdk.platform.message.RegisterStatus;
import com.meizu.cloud.pushsdk.platform.message.SubAliasStatus;
import com.meizu.cloud.pushsdk.platform.message.SubTagsStatus;
import com.meizu.cloud.pushsdk.platform.message.UnRegisterStatus;
import com.meizu.cloud.pushsdk.util.MinSdkChecker;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes13.dex */
public class b {

    /* renamed from: e */
    private static volatile b f15685e;
    private final SparseArray<c> a;
    private Map<String, com.meizu.cloud.pushsdk.handler.a> b;

    /* renamed from: c */
    private com.meizu.cloud.pushsdk.handler.f.l.a f15686c;
    private com.meizu.cloud.pushsdk.handler.f.b.a d;

    /* loaded from: classes13.dex */
    public class a extends com.meizu.cloud.pushsdk.handler.a {
        public a() {
            b.this = r1;
        }

        @Override // com.meizu.cloud.pushsdk.handler.b
        public void a(Context context, String str) {
            Iterator it = b.this.b.entrySet().iterator();
            while (it.hasNext()) {
                com.meizu.cloud.pushsdk.handler.a aVar = (com.meizu.cloud.pushsdk.handler.a) ((Map.Entry) it.next()).getValue();
                if (aVar != null) {
                    aVar.a(context, str);
                }
            }
        }

        @Override // com.meizu.cloud.pushsdk.handler.b
        public void a(Context context, String str, String str2) {
            Iterator it = b.this.b.entrySet().iterator();
            while (it.hasNext()) {
                com.meizu.cloud.pushsdk.handler.a aVar = (com.meizu.cloud.pushsdk.handler.a) ((Map.Entry) it.next()).getValue();
                if (aVar != null) {
                    aVar.a(context, str, str2);
                }
            }
        }

        @Override // com.meizu.cloud.pushsdk.handler.b
        public void b(Context context, RegisterStatus registerStatus) {
            Iterator it = b.this.b.entrySet().iterator();
            while (it.hasNext()) {
                com.meizu.cloud.pushsdk.handler.a aVar = (com.meizu.cloud.pushsdk.handler.a) ((Map.Entry) it.next()).getValue();
                if (aVar != null) {
                    aVar.b(context, registerStatus);
                }
            }
        }

        @Override // com.meizu.cloud.pushsdk.handler.b
        public void b(Context context, String str) {
            Iterator it = b.this.b.entrySet().iterator();
            while (it.hasNext()) {
                com.meizu.cloud.pushsdk.handler.a aVar = (com.meizu.cloud.pushsdk.handler.a) ((Map.Entry) it.next()).getValue();
                if (aVar != null) {
                    aVar.b(context, str);
                }
            }
        }

        @Override // com.meizu.cloud.pushsdk.handler.b
        public void c(Context context, String str) {
            Iterator it = b.this.b.entrySet().iterator();
            while (it.hasNext()) {
                com.meizu.cloud.pushsdk.handler.a aVar = (com.meizu.cloud.pushsdk.handler.a) ((Map.Entry) it.next()).getValue();
                if (aVar != null) {
                    aVar.c(context, str);
                }
            }
        }

        @Override // com.meizu.cloud.pushsdk.handler.b
        public void c(PushNotificationBuilder pushNotificationBuilder) {
            Iterator it = b.this.b.entrySet().iterator();
            while (it.hasNext()) {
                com.meizu.cloud.pushsdk.handler.a aVar = (com.meizu.cloud.pushsdk.handler.a) ((Map.Entry) it.next()).getValue();
                if (aVar != null) {
                    aVar.c(pushNotificationBuilder);
                }
            }
        }

        @Override // com.meizu.cloud.pushsdk.handler.b
        public void d(Context context, SubAliasStatus subAliasStatus) {
            Iterator it = b.this.b.entrySet().iterator();
            while (it.hasNext()) {
                com.meizu.cloud.pushsdk.handler.a aVar = (com.meizu.cloud.pushsdk.handler.a) ((Map.Entry) it.next()).getValue();
                if (aVar != null) {
                    aVar.d(context, subAliasStatus);
                }
            }
        }

        @Override // com.meizu.cloud.pushsdk.handler.b
        public void e(Context context, MzPushMessage mzPushMessage) {
            Iterator it = b.this.b.entrySet().iterator();
            while (it.hasNext()) {
                com.meizu.cloud.pushsdk.handler.a aVar = (com.meizu.cloud.pushsdk.handler.a) ((Map.Entry) it.next()).getValue();
                if (aVar != null) {
                    aVar.e(context, mzPushMessage);
                }
            }
        }

        @Override // com.meizu.cloud.pushsdk.handler.b
        public void f(Context context, MzPushMessage mzPushMessage) {
            Iterator it = b.this.b.entrySet().iterator();
            while (it.hasNext()) {
                com.meizu.cloud.pushsdk.handler.a aVar = (com.meizu.cloud.pushsdk.handler.a) ((Map.Entry) it.next()).getValue();
                if (aVar != null) {
                    aVar.f(context, mzPushMessage);
                }
            }
        }

        @Override // com.meizu.cloud.pushsdk.handler.b
        public void g(Context context, MzPushMessage mzPushMessage) {
            Iterator it = b.this.b.entrySet().iterator();
            while (it.hasNext()) {
                com.meizu.cloud.pushsdk.handler.a aVar = (com.meizu.cloud.pushsdk.handler.a) ((Map.Entry) it.next()).getValue();
                if (aVar != null) {
                    aVar.g(context, mzPushMessage);
                }
            }
        }

        @Override // com.meizu.cloud.pushsdk.handler.b
        public void h(Context context, PushSwitchStatus pushSwitchStatus) {
            Iterator it = b.this.b.entrySet().iterator();
            while (it.hasNext()) {
                com.meizu.cloud.pushsdk.handler.a aVar = (com.meizu.cloud.pushsdk.handler.a) ((Map.Entry) it.next()).getValue();
                if (aVar != null) {
                    aVar.h(context, pushSwitchStatus);
                }
            }
        }

        @Override // com.meizu.cloud.pushsdk.handler.b
        public void i(Context context, boolean z) {
            Iterator it = b.this.b.entrySet().iterator();
            while (it.hasNext()) {
                com.meizu.cloud.pushsdk.handler.a aVar = (com.meizu.cloud.pushsdk.handler.a) ((Map.Entry) it.next()).getValue();
                if (aVar != null) {
                    aVar.i(context, z);
                }
            }
        }

        @Override // com.meizu.cloud.pushsdk.handler.b
        public void j(Context context, SubTagsStatus subTagsStatus) {
            Iterator it = b.this.b.entrySet().iterator();
            while (it.hasNext()) {
                com.meizu.cloud.pushsdk.handler.a aVar = (com.meizu.cloud.pushsdk.handler.a) ((Map.Entry) it.next()).getValue();
                if (aVar != null) {
                    aVar.j(context, subTagsStatus);
                }
            }
        }

        @Override // com.meizu.cloud.pushsdk.handler.b
        public void k(Context context, UnRegisterStatus unRegisterStatus) {
            Iterator it = b.this.b.entrySet().iterator();
            while (it.hasNext()) {
                com.meizu.cloud.pushsdk.handler.a aVar = (com.meizu.cloud.pushsdk.handler.a) ((Map.Entry) it.next()).getValue();
                if (aVar != null) {
                    aVar.k(context, unRegisterStatus);
                }
            }
        }

        @Override // com.meizu.cloud.pushsdk.handler.a
        public void l(Context context, Intent intent) {
            Iterator it = b.this.b.entrySet().iterator();
            while (it.hasNext()) {
                com.meizu.cloud.pushsdk.handler.a aVar = (com.meizu.cloud.pushsdk.handler.a) ((Map.Entry) it.next()).getValue();
                if (aVar != null) {
                    aVar.l(context, intent);
                }
            }
        }
    }

    public b(Context context) {
        this(context, null);
    }

    public b(Context context, List<c> list) {
        this(context, list, null);
    }

    public b(Context context, List<c> list, com.meizu.cloud.pushsdk.handler.a aVar) {
        this.a = new SparseArray<>();
        if (context == null) {
            throw new IllegalArgumentException("Context must not be null.");
        }
        Context applicationContext = context.getApplicationContext();
        this.b = new HashMap();
        a aVar2 = new a();
        if (PushConstants.PUSH_PACKAGE_NAME.equalsIgnoreCase(applicationContext.getPackageName())) {
            this.f15686c = new com.meizu.cloud.pushsdk.handler.f.l.a(applicationContext);
            if (MinSdkChecker.isSupportNotificationSort()) {
                this.d = new com.meizu.cloud.pushsdk.handler.f.b.a(applicationContext);
            }
        }
        if (list != null) {
            d(list);
            return;
        }
        b(new d(applicationContext, aVar2));
        b(new com.meizu.cloud.pushsdk.handler.f.c(applicationContext, aVar2));
        b(new f(applicationContext, aVar2));
        b(new com.meizu.cloud.pushsdk.handler.f.j.b(applicationContext, aVar2));
        b(new e(applicationContext, aVar2));
        b(new g(applicationContext, aVar2));
        b(new com.meizu.cloud.pushsdk.handler.f.j.d(applicationContext, aVar2));
        b(new com.meizu.cloud.pushsdk.handler.f.k.a(applicationContext, aVar2));
        b(new com.meizu.cloud.pushsdk.handler.f.k.c(applicationContext, aVar2));
        b(new com.meizu.cloud.pushsdk.handler.f.k.f(applicationContext, aVar2));
        b(new com.meizu.cloud.pushsdk.handler.f.k.d(applicationContext, aVar2));
        b(new com.meizu.cloud.pushsdk.handler.f.k.e(applicationContext, aVar2));
        b(new com.meizu.cloud.pushsdk.handler.f.l.c(applicationContext, aVar2));
        b(new com.meizu.cloud.pushsdk.handler.f.k.b(applicationContext, aVar2));
        b(new com.meizu.cloud.pushsdk.handler.f.j.e(applicationContext, aVar2));
        b(new com.meizu.cloud.pushsdk.handler.f.i.a(applicationContext, aVar2));
        b(new com.meizu.cloud.pushsdk.handler.f.j.a(applicationContext, aVar2));
        b(new com.meizu.cloud.pushsdk.handler.f.j.f(applicationContext, aVar2));
        b(new com.meizu.cloud.pushsdk.handler.f.l.b(applicationContext, aVar2));
        b(new com.meizu.cloud.pushsdk.handler.f.j.c(applicationContext, aVar2));
    }

    public static b a(Context context) {
        if (f15685e == null) {
            synchronized (b.class) {
                if (f15685e == null) {
                    DebugLogger.i("PushMessageProxy", "PushMessageProxy init");
                    f15685e = new b(context);
                }
            }
        }
        return f15685e;
    }

    public b b(c cVar) {
        this.a.put(cVar.a(), cVar);
        return this;
    }

    public b c(String str, com.meizu.cloud.pushsdk.handler.a aVar) {
        this.b.put(str, aVar);
        return this;
    }

    public b d(List<c> list) {
        if (list != null) {
            Iterator<c> it = list.iterator();
            while (it.hasNext()) {
                b(it.next());
            }
            return this;
        }
        throw new IllegalArgumentException("messageManagerList must not be null.");
    }

    public com.meizu.cloud.pushsdk.handler.f.b.a e() {
        return this.d;
    }

    public void g(Intent intent) {
        DebugLogger.e("PushMessageProxy", "process message start");
        try {
            DebugLogger.i("PushMessageProxy", "receive action " + intent.getAction() + " method " + intent.getStringExtra("method"));
            for (int i2 = 0; i2 < this.a.size() && !this.a.valueAt(i2).a(intent); i2++) {
            }
        } catch (Exception e2) {
            DebugLogger.e("PushMessageProxy", "process message error " + e2.getMessage());
        }
    }

    public com.meizu.cloud.pushsdk.handler.f.l.a h() {
        return this.f15686c;
    }
}
