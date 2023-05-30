package com.doupo.linker;

import android.app.Application;
import android.content.Intent;
import android.content.pm.ComponentInfo;
import android.os.Build;
import android.os.Message;
import com.huawei.hms.support.api.entity.core.CommonCode;
import java.lang.reflect.Field;
import java.util.List;

/* loaded from: classes.dex */
public class a {
    public static boolean a;
    public static C0019a b;

    /* renamed from: com.doupo.linker.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0019a {
        public final int a;
        public final Intent b;

        /* renamed from: c  reason: collision with root package name */
        public final ComponentInfo f1045c;

        public C0019a(int i2, Intent intent, ComponentInfo componentInfo) {
            this.a = i2;
            this.b = intent;
            this.f1045c = componentInfo;
        }

        public Intent a() {
            return this.b;
        }

        public String toString() {
            return "LinkerIntent{mType=" + this.a + ", mIntent=" + this.b + ", mInfo=" + this.f1045c + '}';
        }
    }

    /* loaded from: classes.dex */
    public static class b {
        public Object a;
        public Class<?> b;

        /* renamed from: c  reason: collision with root package name */
        public String f1046c;

        public static b a(Object obj) {
            b bVar = new b();
            bVar.a = obj;
            return bVar;
        }

        public Object b() {
            Object obj = this.a;
            if (obj == null || this.f1046c == null) {
                return null;
            }
            Class<?> cls = this.b;
            if (cls == null) {
                cls = obj.getClass();
            }
            while (cls != null) {
                try {
                    Field declaredField = cls.getDeclaredField(this.f1046c);
                    declaredField.setAccessible(true);
                    return declaredField.get(this.a);
                } catch (Throwable th) {
                    th.printStackTrace();
                    cls = cls.getSuperclass();
                }
            }
            return null;
        }
    }

    public static C0019a a(Message message) {
        int i2 = message.what;
        Object obj = null;
        if (i2 == 100) {
            if (Build.VERSION.SDK_INT < 28) {
                b a2 = b.a(message.obj);
                a2.f1046c = CommonCode.Resolution.HAS_RESOLUTION_FROM_APK;
                Intent intent = (Intent) a2.b();
                return new C0019a(1, intent != null ? (Intent) intent.clone() : null, null);
            }
            return null;
        } else if (i2 != 159) {
            if (i2 == 113 || i2 == 114) {
                return b(message.obj, i2 == 113 ? 3 : 2, CommonCode.Resolution.HAS_RESOLUTION_FROM_APK, "info");
            }
            return null;
        } else if (Build.VERSION.SDK_INT >= 28) {
            b a3 = b.a(message.obj);
            a3.f1046c = "mActivityCallbacks";
            Object b2 = a3.b();
            if (b2 instanceof List) {
                List list = (List) b2;
                if (!list.isEmpty()) {
                    Object obj2 = list.get(0);
                    try {
                        if (Class.forName("android.app.servertransaction.LaunchActivityItem").isInstance(obj2)) {
                            obj = obj2;
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            }
            return b(obj, 1, "mIntent", "mInfo");
        } else {
            return null;
        }
    }

    public static C0019a b(Object obj, int i2, String str, String str2) {
        if (obj != null) {
            b a2 = b.a(obj);
            a2.f1046c = str;
            Intent intent = (Intent) a2.b();
            b a3 = b.a(obj);
            a3.f1046c = str2;
            return new C0019a(i2, intent != null ? (Intent) intent.clone() : null, (ComponentInfo) a3.b());
        }
        return null;
    }

    public static C0019a c(Application application) {
        Message message;
        C0019a c0019a = b;
        if (c0019a != null) {
            return c0019a;
        }
        b a2 = b.a(application.getMainLooper());
        a2.f1046c = "mQueue";
        Object b2 = a2.b();
        if (b2 != null) {
            b a3 = b.a(b2);
            a3.f1046c = "mMessages";
            message = (Message) a3.b();
        } else {
            message = null;
        }
        if (a) {
            String str = "getAppIntent message:" + message;
        }
        if (message == null) {
            return null;
        }
        try {
            C0019a a4 = a(message);
            if (a4 != null) {
                b = a4;
            }
            return a4;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }
}
