package com.jingdong.sdk.jdupgrade.a.j;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import java.io.FileInputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes7.dex */
public final class b {
    private static boolean a;
    private static List<a> b = new CopyOnWriteArrayList();

    /* renamed from: c */
    private static List<WeakReference<Activity>> f15098c = new ArrayList();

    /* loaded from: classes7.dex */
    public interface a {
        void a();

        void b();
    }

    public static synchronized Bitmap a(int i2) {
        Drawable applicationIcon;
        synchronized (b.class) {
            Context j2 = com.jingdong.sdk.jdupgrade.a.c.j();
            Bitmap decodeResource = BitmapFactory.decodeResource(j2.getResources(), i2);
            if (decodeResource != null) {
                return decodeResource;
            }
            try {
                try {
                    applicationIcon = j2.getPackageManager().getApplicationIcon(com.jingdong.sdk.jdupgrade.a.c.c());
                } catch (Throwable unused) {
                    return BitmapFactory.decodeResource(j2.getResources(), j2.getApplicationInfo().icon);
                }
            } catch (Throwable unused2) {
            }
            if (applicationIcon instanceof BitmapDrawable) {
                return ((BitmapDrawable) applicationIcon).getBitmap();
            }
            if (Build.VERSION.SDK_INT >= 26 && (applicationIcon instanceof AdaptiveIconDrawable)) {
                LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{((AdaptiveIconDrawable) applicationIcon).getBackground(), ((AdaptiveIconDrawable) applicationIcon).getForeground()});
                Bitmap createBitmap = Bitmap.createBitmap(layerDrawable.getIntrinsicWidth(), layerDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(createBitmap);
                layerDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                layerDrawable.draw(canvas);
                return createBitmap;
            }
            return null;
        }
    }

    public static void a() {
        Iterator<WeakReference<Activity>> it = f15098c.iterator();
        while (it.hasNext()) {
            Activity activity = it.next().get();
            if (activity != null && !activity.isFinishing()) {
                activity.finish();
            }
        }
        f15098c.clear();
        Process.killProcess(Process.myPid());
        System.exit(0);
    }

    public static void a(Activity activity) {
        f15098c.add(new WeakReference<>(activity));
    }

    public static void a(a aVar) {
        b.add(aVar);
    }

    public static void a(boolean z) {
        if (a == z) {
            return;
        }
        a = z;
        List<a> list = b;
        if (list == null || list.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList(b);
        if (z) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                try {
                    ((a) it.next()).b();
                } catch (Exception unused) {
                }
            }
            return;
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            try {
                ((a) it2.next()).a();
            } catch (Exception unused2) {
            }
        }
    }

    public static boolean a(Context context) {
        if (context == null) {
            return false;
        }
        String c2 = c();
        return !TextUtils.isEmpty(c2) && TextUtils.equals(c2, context.getPackageName());
    }

    public static boolean a(String str) {
        for (WeakReference<Activity> weakReference : f15098c) {
            if (weakReference.get() != null && str.equals(weakReference.get().getClass().getName())) {
                return true;
            }
        }
        return false;
    }

    public static void b(a aVar) {
        b.remove(aVar);
    }

    public static void b(boolean z) {
        if (a == z) {
            return;
        }
        a = z;
    }

    public static boolean b() {
        return a;
    }

    public static String c() {
        FileInputStream fileInputStream;
        String processName = Build.VERSION.SDK_INT > 28 ? Application.getProcessName() : "";
        if (TextUtils.isEmpty(processName)) {
            int myPid = Process.myPid();
            if (myPid <= 0) {
                return "";
            }
            byte[] bArr = new byte[128];
            FileInputStream fileInputStream2 = null;
            try {
                fileInputStream = new FileInputStream("/proc/" + myPid + "/cmdline");
            } catch (Throwable unused) {
            }
            try {
                int read = fileInputStream.read(bArr);
                if (read > 0) {
                    for (int i2 = 0; i2 < read; i2++) {
                        if (bArr[i2] <= 128 && bArr[i2] > 0) {
                        }
                        read = i2;
                        break;
                    }
                    String str = new String(bArr, 0, read);
                    try {
                        fileInputStream.close();
                    } catch (Throwable unused2) {
                    }
                    return str;
                }
            } catch (Throwable unused3) {
                fileInputStream2 = fileInputStream;
                if (fileInputStream2 != null) {
                    fileInputStream = fileInputStream2;
                    fileInputStream.close();
                }
                return "";
            }
            try {
                fileInputStream.close();
            } catch (Throwable unused4) {
            }
            return "";
        }
        return processName;
    }
}
