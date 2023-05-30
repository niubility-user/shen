package com.xiaomi.push;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes11.dex */
public abstract class h4 extends f4 {
    private RemoteViews b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f18692c;
    protected Bitmap d;

    /* renamed from: e  reason: collision with root package name */
    protected CharSequence f18693e;

    /* renamed from: f  reason: collision with root package name */
    protected CharSequence f18694f;

    /* renamed from: g  reason: collision with root package name */
    protected Map<String, String> f18695g;

    /* renamed from: h  reason: collision with root package name */
    private int f18696h;

    /* renamed from: i  reason: collision with root package name */
    private String f18697i;

    /* renamed from: j  reason: collision with root package name */
    private boolean f18698j;

    /* renamed from: k  reason: collision with root package name */
    private ArrayList<Notification.Action> f18699k;

    /* renamed from: l  reason: collision with root package name */
    private int f18700l;

    public h4(Context context, int i2, String str) {
        super(context);
        this.f18699k = new ArrayList<>();
        this.f18700l = 0;
        this.f18697i = str;
        this.f18696h = i2;
        A();
    }

    public h4(Context context, String str) {
        this(context, 0, str);
    }

    private void A() {
        int a = a(c().getResources(), z(), "layout", c().getPackageName());
        if (a == 0) {
            g.j.a.a.a.c.o("create RemoteViews failed, no such layout resource was found");
            return;
        }
        this.b = new RemoteViews(c().getPackageName(), a);
        this.f18692c = t();
    }

    private boolean B() {
        Map<String, String> map = this.f18695g;
        return map != null && Boolean.parseBoolean(map.get("custom_builder_set_title"));
    }

    private void C() {
        super.setContentTitle(this.f18693e);
        super.setContentText(this.f18694f);
    }

    private boolean D() {
        return (TextUtils.isEmpty(w()) || TextUtils.isEmpty(this.f18697i)) ? false : true;
    }

    private boolean E() {
        return D() && F();
    }

    private boolean F() {
        List<StatusBarNotification> z;
        if (Build.VERSION.SDK_INT >= 20 && (z = com.xiaomi.push.service.y.e(c(), this.f18697i).z()) != null && !z.isEmpty()) {
            for (StatusBarNotification statusBarNotification : z) {
                if (statusBarNotification.getId() == this.f18696h) {
                    if (statusBarNotification.getNotification() == null) {
                        return false;
                    }
                    return !r0.extras.getBoolean("mipush.customCopyLayout", true);
                }
            }
        }
        return false;
    }

    private Bitmap j() {
        return com.xiaomi.push.service.n.n(y4.d(c(), this.f18697i));
    }

    private String z() {
        boolean E = E();
        this.f18698j = E;
        return E ? w() : q();
    }

    @Override // android.app.Notification.Builder
    public /* synthetic */ Notification.Builder addAction(int i2, CharSequence charSequence, PendingIntent pendingIntent) {
        m(i2, charSequence, pendingIntent);
        return this;
    }

    @Override // android.app.Notification.Builder
    public /* synthetic */ Notification.Builder addAction(Notification.Action action) {
        n(action);
        return this;
    }

    @Override // com.xiaomi.push.f4
    public f4 g(Map<String, String> map) {
        this.f18695g = map;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaomi.push.f4
    public void h() {
        super.h();
        Bundle bundle = new Bundle();
        if (D()) {
            bundle.putBoolean("mipush.customCopyLayout", this.f18698j);
        } else {
            bundle.putBoolean("mipush.customCopyLayout", false);
        }
        bundle.putBoolean("miui.customHeight", false);
        bundle.putBoolean("mipush.customNotification", true);
        bundle.putInt("mipush.customLargeIconId", b("large_icon"));
        if (this.f18699k.size() > 0) {
            Notification.Action[] actionArr = new Notification.Action[this.f18699k.size()];
            this.f18699k.toArray(actionArr);
            bundle.putParcelableArray("mipush.customActions", actionArr);
        }
        if (B() || !com.xiaomi.push.service.z.p(c().getContentResolver())) {
            C();
        } else {
            bundle.putCharSequence("mipush.customTitle", this.f18693e);
            bundle.putCharSequence("mipush.customContent", this.f18694f);
        }
        d(bundle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int i(float f2) {
        return (int) ((f2 * c().getResources().getDisplayMetrics().density) + 0.5f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Bitmap k(Bitmap bitmap, float f2) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        canvas.drawRoundRect(new RectF(rect), f2, f2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        if (!bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public final RemoteViews l() {
        return this.b;
    }

    public h4 m(int i2, CharSequence charSequence, PendingIntent pendingIntent) {
        n(new Notification.Action(i2, charSequence, pendingIntent));
        return this;
    }

    public h4 n(Notification.Action action) {
        if (action != null) {
            this.f18699k.add(action);
        }
        int i2 = this.f18700l;
        this.f18700l = i2 + 1;
        s(i2, action);
        return this;
    }

    @Override // android.app.Notification.Builder
    /* renamed from: o  reason: merged with bridge method [inline-methods] */
    public h4 setLargeIcon(Bitmap bitmap) {
        this.d = bitmap;
        return this;
    }

    public h4 p(CharSequence charSequence) {
        this.f18693e = charSequence;
        return this;
    }

    protected abstract String q();

    /* JADX INFO: Access modifiers changed from: protected */
    public void r(int i2) {
        Bitmap j2 = j();
        if (j2 != null) {
            l().setImageViewBitmap(i2, j2);
            return;
        }
        int l2 = y4.l(c(), this.f18697i);
        if (l2 != 0) {
            l().setImageViewResource(i2, l2);
        }
    }

    protected void s(int i2, Notification.Action action) {
    }

    @Override // android.app.Notification.Builder
    public /* synthetic */ Notification.Builder setContentText(CharSequence charSequence) {
        v(charSequence);
        return this;
    }

    @Override // android.app.Notification.Builder
    public /* synthetic */ Notification.Builder setContentTitle(CharSequence charSequence) {
        p(charSequence);
        return this;
    }

    protected abstract boolean t();

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean u(int i2) {
        double red = Color.red(i2);
        Double.isNaN(red);
        double green = Color.green(i2);
        Double.isNaN(green);
        double d = (red * 0.299d) + (green * 0.587d);
        double blue = Color.blue(i2);
        Double.isNaN(blue);
        return d + (blue * 0.114d) < 192.0d;
    }

    public h4 v(CharSequence charSequence) {
        this.f18694f = charSequence;
        return this;
    }

    protected abstract String w();

    /* JADX INFO: Access modifiers changed from: protected */
    public final void x() {
        super.setContentTitle(this.f18693e);
        super.setContentText(this.f18694f);
        Bitmap bitmap = this.d;
        if (bitmap != null) {
            super.setLargeIcon(bitmap);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean y() {
        return this.f18692c;
    }
}
