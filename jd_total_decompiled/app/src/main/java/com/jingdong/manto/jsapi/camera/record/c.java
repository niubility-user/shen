package com.jingdong.manto.jsapi.camera.record;

import android.content.Context;
import android.hardware.Camera;
import android.view.WindowManager;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;

/* loaded from: classes15.dex */
public final class c {

    /* renamed from: f */
    private static c f13175f;
    private Camera.Parameters a;
    private final f b = new f();

    /* renamed from: c */
    private final f f13176c = new f();
    private final a d = a.b(16, 9);

    /* renamed from: e */
    private a f13177e;

    private c() {
    }

    private a a() {
        Iterator<a> it = this.b.b().iterator();
        a aVar = null;
        while (it.hasNext()) {
            aVar = it.next();
            if (aVar.equals(this.d)) {
                break;
            }
        }
        return aVar;
    }

    private e a(SortedSet<e> sortedSet, int i2, int i3, int i4) {
        if (!a(i4)) {
            i3 = i2;
            i2 = i3;
        }
        e eVar = null;
        Iterator<e> it = sortedSet.iterator();
        while (it.hasNext()) {
            eVar = it.next();
            if (i3 <= eVar.b() && i2 <= eVar.a()) {
                break;
            }
        }
        return eVar;
    }

    public static boolean a(int i2) {
        return i2 == 90 || i2 == 270;
    }

    public static c b() {
        c cVar = f13175f;
        if (cVar == null) {
            c cVar2 = new c();
            f13175f = cVar2;
            return cVar2;
        }
        return cVar;
    }

    public int a(Context context, int i2) {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(i2, cameraInfo);
        int rotation = ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRotation();
        int i3 = 0;
        if (rotation != 0) {
            if (rotation == 1) {
                i3 = 90;
            } else if (rotation == 2) {
                i3 = 180;
            } else if (rotation == 3) {
                i3 = 270;
            }
        }
        return (cameraInfo.facing == 1 ? 360 - ((cameraInfo.orientation + i3) % R2.attr.additionBottom) : (cameraInfo.orientation - i3) + R2.attr.additionBottom) % R2.attr.additionBottom;
    }

    public Camera.Size a(List<Camera.Size> list, int i2, int i3) {
        for (Camera.Size size : list) {
            if (i2 <= size.width && i3 <= size.height) {
                return size;
            }
        }
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public void a(Camera camera, int i2, int i3, int i4) {
        this.a = camera.getParameters();
        this.b.a();
        for (Camera.Size size : this.a.getSupportedPreviewSizes()) {
            this.b.a(new e(size.width, size.height));
        }
        this.f13176c.a();
        for (Camera.Size size2 : this.a.getSupportedPictureSizes()) {
            this.f13176c.a(new e(size2.width, size2.height));
        }
        if (this.f13177e == null) {
            this.f13177e = this.d;
        }
        SortedSet<e> a = this.b.a(this.f13177e);
        if (a == null) {
            a a2 = a();
            this.f13177e = a2;
            a = this.b.a(a2);
        }
        e a3 = a(a, i2, i3, i4);
        e last = this.f13176c.a(this.f13177e).last();
        if (this.f13176c.a(this.f13177e).contains(a3) && a3.b() > 400) {
            last = a3;
        }
        this.a.setPreviewSize(a3.b(), a3.a());
        this.a.setPictureSize(last.b(), last.a());
        camera.setParameters(this.a);
    }

    public boolean a(List<Integer> list, int i2) {
        for (int i3 = 0; i3 < list.size(); i3++) {
            if (i2 == list.get(i3).intValue()) {
                return true;
            }
        }
        return false;
    }

    public boolean a(List<String> list, String str) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (str.equals(list.get(i2))) {
                return true;
            }
        }
        return false;
    }

    public int b(Context context, int i2) {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(i2, cameraInfo);
        return cameraInfo.orientation;
    }
}
