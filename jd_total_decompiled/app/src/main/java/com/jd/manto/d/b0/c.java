package com.jd.manto.d.b0;

import android.os.Bundle;
import com.jd.manto.sdkimpl.live.v2.MantoLivePlayerV2;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener;
import com.jingdong.manto.jsapi.refact.live.ILiveInterface;
import com.jingdong.manto.jsapi.refact.live.JsApiLivePlayer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class c {
    private int a;
    private com.jd.manto.d.b0.b b;

    /* renamed from: c  reason: collision with root package name */
    private Map<Integer, com.jd.manto.d.b0.a> f6627c = new HashMap();
    public MantoLifecycleLisener d = new C0185c();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class a implements ILiveInterface {
        final /* synthetic */ MantoCore a;

        a(MantoCore mantoCore) {
            this.a = mantoCore;
        }

        @Override // com.jingdong.manto.jsapi.refact.live.ILiveInterface
        public void onLivePlayerEvent(int i2, int i3) {
            JSONObject jSONObject = new JSONObject();
            try {
                com.jd.manto.d.b0.a aVar = (com.jd.manto.d.b0.a) c.this.f6627c.get(Integer.valueOf(i2));
                if (aVar != null) {
                    jSONObject.put(JsApiLivePlayer.LIVE_PLAYER_ID, i2);
                    jSONObject.put("data", aVar.getData());
                    jSONObject.put("errCode", i3);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            c.this.b.dispatchEvent(this.a, ILiveInterface.onLivePlayerEvent, jSONObject, c.this.a);
        }

        @Override // com.jingdong.manto.jsapi.refact.live.ILiveInterface
        public void onLivePlayerFullScreenChange(int i2, boolean z, String str) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("fullScreen", z);
                jSONObject.put("direction", str);
                com.jd.manto.d.b0.a aVar = (com.jd.manto.d.b0.a) c.this.f6627c.get(Integer.valueOf(i2));
                if (aVar != null) {
                    jSONObject.put(JsApiLivePlayer.LIVE_PLAYER_ID, i2);
                    jSONObject.put("data", aVar.getData());
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            c.this.b.dispatchEvent(this.a, ILiveInterface.onLivePlayerFullScreenChange, jSONObject, c.this.a);
        }

        @Override // com.jingdong.manto.jsapi.refact.live.ILiveInterface
        public void onLivePlayerNetStatus(int i2, Bundle bundle) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class b implements d {
        final /* synthetic */ MantoCore a;

        b(MantoCore mantoCore) {
            this.a = mantoCore;
        }

        @Override // com.jd.manto.d.b0.c.d
        public void a(String str) {
            if (c.this.b != null) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("path", str);
                } catch (Exception unused) {
                }
                c.this.b.dispatchEvent(this.a, "leavePictureInPicture", jSONObject, c.this.a);
            }
        }

        @Override // com.jd.manto.d.b0.c.d
        public void b(com.jd.manto.d.b0.a aVar, boolean z, boolean z2) {
            if (aVar instanceof MantoLivePlayerV2) {
                if ((z || z2) && c.this.b != null) {
                    c.this.b.addPicInPicPage(this.a, c.this.a, z, z2);
                }
            }
        }

        @Override // com.jd.manto.d.b0.c.d
        public void clear() {
            if (c.this.b != null) {
                c.this.b.removePicInPicPage(this.a, c.this.a);
            }
        }

        @Override // com.jd.manto.d.b0.c.d
        public void onClose() {
            if (c.this.b != null) {
                c.this.b.removePicInPicPage(this.a, c.this.a);
                c.this.b.dispatchEvent(this.a, "exitPictureInPicture", null, c.this.a);
            }
        }
    }

    /* renamed from: com.jd.manto.d.b0.c$c  reason: collision with other inner class name */
    /* loaded from: classes17.dex */
    class C0185c implements MantoLifecycleLisener {
        C0185c() {
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener
        public void onBackground() {
            Iterator it = c.this.f6627c.values().iterator();
            while (it.hasNext()) {
                ((com.jd.manto.d.b0.a) it.next()).l();
            }
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener
        public void onDestroy() {
            Iterator it = c.this.f6627c.values().iterator();
            while (it.hasNext()) {
                ((com.jd.manto.d.b0.a) it.next()).destroy();
            }
            c.this.f6627c.clear();
            c.this.b.b(c.this.a);
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener
        public void onForeground() {
            Iterator it = c.this.f6627c.values().iterator();
            while (it.hasNext()) {
                ((com.jd.manto.d.b0.a) it.next()).n();
            }
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener
        public void onPause() {
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener
        public void onReady() {
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener
        public boolean onRemove() {
            Iterator it = c.this.f6627c.values().iterator();
            while (it.hasNext()) {
                ((com.jd.manto.d.b0.a) it.next()).i();
            }
            return false;
        }
    }

    /* loaded from: classes17.dex */
    public interface d {
        void a(String str);

        void b(com.jd.manto.d.b0.a aVar, boolean z, boolean z2);

        void clear();

        void onClose();
    }

    public c(int i2, com.jd.manto.d.b0.b bVar) {
        this.a = i2;
        this.b = bVar;
    }

    public void d(MantoCore mantoCore, com.jd.manto.d.b0.a aVar, int i2) {
        this.f6627c.put(Integer.valueOf(i2), aVar);
        aVar.g(new a(mantoCore));
        aVar.m(new b(mantoCore));
    }

    public void e(int i2) {
        if (this.f6627c.get(Integer.valueOf(i2)) != null) {
            this.f6627c.remove(Integer.valueOf(i2));
        }
    }
}
