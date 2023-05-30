package com.jingdong.manto.q.u;

import android.content.Context;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import com.tencent.smtt.export.external.embeddedwidget.interfaces.IEmbeddedWidgetClient;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes16.dex */
public abstract class a {
    public Map<Integer, e> a = new ConcurrentHashMap();

    /* renamed from: com.jingdong.manto.q.u.a$a  reason: collision with other inner class name */
    /* loaded from: classes16.dex */
    public class C0663a implements IEmbeddedWidgetClient {
        e a;

        public C0663a(a aVar, e eVar) {
            this.a = eVar;
        }

        @Override // com.tencent.smtt.export.external.embeddedwidget.interfaces.IEmbeddedWidgetClient
        public void onActive() {
        }

        @Override // com.tencent.smtt.export.external.embeddedwidget.interfaces.IEmbeddedWidgetClient
        public void onDeactive() {
        }

        @Override // com.tencent.smtt.export.external.embeddedwidget.interfaces.IEmbeddedWidgetClient
        public void onDestroy() {
            this.a.a();
        }

        @Override // com.tencent.smtt.export.external.embeddedwidget.interfaces.IEmbeddedWidgetClient
        public void onRectChanged(Rect rect) {
        }

        @Override // com.tencent.smtt.export.external.embeddedwidget.interfaces.IEmbeddedWidgetClient
        public void onRequestRedraw() {
        }

        @Override // com.tencent.smtt.export.external.embeddedwidget.interfaces.IEmbeddedWidgetClient
        public void onSurfaceCreated(Surface surface) {
            this.a.a(surface);
        }

        @Override // com.tencent.smtt.export.external.embeddedwidget.interfaces.IEmbeddedWidgetClient
        public void onSurfaceDestroyed(Surface surface) {
        }

        @Override // com.tencent.smtt.export.external.embeddedwidget.interfaces.IEmbeddedWidgetClient
        public boolean onTouchEvent(MotionEvent motionEvent) {
            this.a.a(motionEvent);
            return false;
        }

        @Override // com.tencent.smtt.export.external.embeddedwidget.interfaces.IEmbeddedWidgetClient
        public void onVisibilityChanged(boolean z) {
        }
    }

    public View a(int i2) {
        e eVar = this.a.get(Integer.valueOf(i2));
        if (eVar == null) {
            return null;
        }
        return eVar.b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0663a a(Context context, int i2) {
        return a(context, i2, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0663a a(Context context, int i2, boolean z) {
        e eVar = this.a.get(Integer.valueOf(i2));
        if (eVar == null) {
            eVar = new e(context);
            eVar.a(z);
            this.a.put(Integer.valueOf(i2), eVar);
        }
        return new C0663a(this, eVar);
    }

    public abstract C0663a a(Context context, Map<String, String> map);

    public void a(int i2, View view, int i3, int i4, boolean z) {
        boolean z2;
        e eVar = this.a.get(Integer.valueOf(i2));
        if (eVar == null) {
            eVar = new e(view.getContext());
            eVar.a(z);
            this.a.put(Integer.valueOf(i2), eVar);
            z2 = false;
        } else {
            z2 = true;
        }
        eVar.a(view, i3, i4, z2);
    }

    public void a(int i2, float[] fArr, int i3) {
        e eVar = this.a.get(Integer.valueOf(i2));
        if (eVar != null) {
            eVar.b(fArr, true, i3);
        }
    }

    public void b(int i2) {
        this.a.remove(Integer.valueOf(i2));
    }
}
