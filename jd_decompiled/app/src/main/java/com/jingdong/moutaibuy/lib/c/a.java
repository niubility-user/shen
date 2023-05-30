package com.jingdong.moutaibuy.lib.c;

import android.content.Context;
import android.graphics.Point;
import android.hardware.Camera;
import android.view.WindowManager;
import com.jingdong.moutaibuy.lib.i.c;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes16.dex */
public final class a {
    private final Context a;
    private Point b;

    /* renamed from: c  reason: collision with root package name */
    private Point f14582c;
    private Camera.Size d;

    /* renamed from: e  reason: collision with root package name */
    private Camera.Size f14583e;

    /* renamed from: f  reason: collision with root package name */
    List<Camera.Size> f14584f;

    public a(Context context) {
        this.a = context;
    }

    private static Point a(List<Camera.Size> list, Point point2) {
        Iterator<Camera.Size> it = list.iterator();
        int i2 = 0;
        int i3 = 0;
        int i4 = Integer.MAX_VALUE;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Camera.Size next = it.next();
            int i5 = next.width;
            int i6 = next.height;
            int abs = Math.abs(i5 - point2.x) + Math.abs(i6 - point2.y);
            if (abs == 0) {
                i3 = i6;
                i2 = i5;
                break;
            } else if (abs < i4) {
                i3 = i6;
                i2 = i5;
                i4 = abs;
            }
        }
        if (i2 <= 0 || i3 <= 0) {
            return null;
        }
        return new Point(i2, i3);
    }

    private static Point e(List<Camera.Size> list, Point point2) {
        Point a = a(list, point2);
        return a == null ? new Point((point2.x >> 3) << 3, (point2.y >> 3) << 3) : a;
    }

    private int[] h(Camera camera, float f2) {
        int i2 = (int) (f2 * 1000.0f);
        int[] iArr = null;
        int i3 = Integer.MAX_VALUE;
        for (int[] iArr2 : camera.getParameters().getSupportedPreviewFpsRange()) {
            int abs = Math.abs(i2 - iArr2[0]) + Math.abs(i2 - iArr2[1]);
            if (abs < i3) {
                iArr = iArr2;
                i3 = abs;
            }
        }
        return iArr;
    }

    public Point b() {
        return this.b;
    }

    public int c() {
        int i2;
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        int i3 = 0;
        Camera.getCameraInfo(0, cameraInfo);
        WindowManager windowManager = (WindowManager) this.a.getSystemService("window");
        if (windowManager != null) {
            int rotation = windowManager.getDefaultDisplay().getRotation();
            if (rotation != 0) {
                if (rotation == 1) {
                    i3 = 90;
                } else if (rotation == 2) {
                    i3 = 180;
                } else if (rotation == 3) {
                    i3 = 270;
                }
            }
            if (cameraInfo.facing == 1) {
                i2 = (360 - ((cameraInfo.orientation + i3) % R2.attr.additionBottom)) % R2.attr.additionBottom;
            } else {
                i2 = ((cameraInfo.orientation - i3) + R2.attr.additionBottom) % R2.attr.additionBottom;
            }
            return i2;
        }
        return 0;
    }

    public Camera.Size d() {
        return this.f14583e;
    }

    public List<Camera.Size> f() {
        return this.f14584f;
    }

    public void g(Camera camera) {
        Point i2 = c.i(this.a);
        Point point2 = new Point();
        point2.x = i2.x;
        point2.y = i2.y;
        if (c.k(this.a)) {
            point2.x = i2.y;
            point2.y = i2.x;
        }
        List<Camera.Size> supportedPreviewSizes = camera.getParameters().getSupportedPreviewSizes();
        this.f14584f = supportedPreviewSizes;
        this.f14582c = e(supportedPreviewSizes, point2);
        if (c.k(this.a)) {
            Point point3 = this.f14582c;
            this.b = new Point(point3.y, point3.x);
            return;
        }
        this.b = this.f14582c;
    }

    public void i(Camera camera) {
        Camera.Parameters parameters = camera.getParameters();
        Camera.Size size = this.d;
        if (size == null) {
            Point point2 = this.f14582c;
            parameters.setPreviewSize(point2.x, point2.y);
        } else {
            parameters.setPreviewSize(size.width, size.height);
        }
        this.f14583e = parameters.getPreviewSize();
        int[] h2 = h(camera, 60.0f);
        if (h2 != null) {
            parameters.setPreviewFpsRange(h2[0], h2[1]);
        }
        camera.setDisplayOrientation(c());
        camera.setParameters(parameters);
    }

    public void j(Camera.Size size) {
        this.d = size;
    }
}
