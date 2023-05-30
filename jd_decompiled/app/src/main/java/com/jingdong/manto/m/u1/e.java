package com.jingdong.manto.m.u1;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import androidx.annotation.RequiresApi;
import com.jd.dynamic.DYConstants;
import com.jingdong.canvas.JDCanvasJNI;
import com.jingdong.canvas.surface.JDTextureView;
import com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.u;
import com.jingdong.sdk.platform.business.personal.R2;
import java.nio.ByteBuffer;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class e {
    private int a;
    private String b;

    /* renamed from: c */
    private Context f13799c = com.jingdong.a.g();
    private JDTextureView d;

    /* renamed from: e */
    private com.jingdong.manto.f f13800e;

    /* renamed from: f */
    private MantoLifecycleLisener f13801f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class a implements MantoLifecycleLisener {
        a() {
            e.this = r1;
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener
        public void onBackground() {
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener
        public void onDestroy() {
            e.this.b();
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener
        public void onForeground() {
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener
        public void onPause() {
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener
        public void onReady() {
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener
        public boolean onRemove() {
            return false;
        }
    }

    public e(com.jingdong.manto.e eVar) {
        this.f13800e = eVar.getLatestRuntime();
    }

    public Bitmap a(int i2, int i3, int i4, int i5) {
        try {
            return Bitmap.createBitmap(u.a(Base64.decode(JDCanvasJNI.callNative(536870913, this.b, "R" + i2 + DYConstants.DY_REGEX_COMMA + i3 + DYConstants.DY_REGEX_COMMA + i4 + DYConstants.DY_REGEX_COMMA + i5 + ";"), 0)), i4, i5, Bitmap.Config.ARGB_8888);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public View a(boolean z, String str, String str2) {
        if (this.a == 0) {
            return null;
        }
        if (this.d == null) {
            JDTextureView jDTextureView = new JDTextureView(this.f13799c, this.b, !z ? 1 : 0);
            this.d = jDTextureView;
            jDTextureView.d("transparent");
            this.d.setOnTouchListener(new com.jingdong.manto.m.u1.a(this.f13800e.f13014f.getFirstPage().i(), this.a, z, str, z, str2, false));
        }
        return this.d;
    }

    public MantoLifecycleLisener a() {
        if (this.f13801f == null) {
            this.f13801f = new a();
        }
        return this.f13801f;
    }

    public String a(int i2, String str) {
        return JDCanvasJNI.callNative(i2, this.b, str);
    }

    public void a(int i2) {
        this.a = i2;
        this.b = String.valueOf(i2);
    }

    @RequiresApi(api = 15)
    public void a(int i2, int i3) {
        JDTextureView jDTextureView = this.d;
        if (jDTextureView != null) {
            jDTextureView.e(MantoDensityUtils.dip2pixel(this.f13799c, i2), MantoDensityUtils.dip2pixel(this.f13799c, i3));
        }
    }

    public void a(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, String str) {
        ByteBuffer nativeBuffer;
        if (str == null || !str.startsWith("NativeBuffer:")) {
            JDCanvasJNI.bindTexture9(this.b, com.jingdong.manto.utils.b.a().a(this.f13800e, str), MantoDensityUtils.dip2pixel(com.jingdong.a.g(), i2), MantoDensityUtils.dip2pixel(com.jingdong.a.g(), i3), i4, 0, i5, i6, i7, i8, i9);
            return;
        }
        int intValue = Integer.valueOf(str.replace("NativeBuffer:", "")).intValue();
        com.jingdong.manto.jsengine.a aVar = (com.jingdong.manto.jsengine.a) this.f13800e.f13015g.g().getInterface(com.jingdong.manto.jsengine.a.class);
        if (aVar == null || !aVar.canUseNativeBuffer() || (nativeBuffer = aVar.getNativeBuffer(intValue)) == null) {
            return;
        }
        byte[] array = nativeBuffer.array();
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(array.length);
        allocateDirect.put(array);
        JDCanvasJNI.glTexImage2D2B(this.b, allocateDirect, 1, i2, i3, i4, i5, i6, i7, i8, i9);
    }

    public void a(int i2, int i3, int i4, int i5, int i6, String str) {
        Bitmap a2;
        if (TextUtils.isEmpty(str) || (a2 = com.jingdong.manto.utils.b.a().a(this.f13800e, str)) == null) {
            return;
        }
        JDCanvasJNI.bindTexture(this.b, a2, 0, i2, i3, i4, i5, i6);
    }

    public void a(String str, int i2) {
        Bitmap a2;
        if (TextUtils.isEmpty(str) || (a2 = com.jingdong.manto.utils.b.a().a(this.f13800e, str)) == null) {
            return;
        }
        JDCanvasJNI.bindTexture(this.b, a2, i2, R2.color.c_FF0017, 0, R2.dimen.jdreact_base_ui_jd_tip_page_button_paddingLeft, R2.dimen.jdreact_base_ui_jd_tip_page_button_paddingLeft, R2.dimen.HugersTextSize);
    }

    public void a(String str, int i2, int i3, int i4, int i5, int i6, int i7, String str2) {
        Bitmap a2;
        if (TextUtils.isEmpty(str2) || (a2 = com.jingdong.manto.utils.b.a().a(this.f13800e, str2)) == null) {
            return;
        }
        JDCanvasJNI.texSubImage2D(this.b, a2, 0, i2, i3, i4, i5, i6, i7);
    }

    public void a(boolean z) {
    }

    public String b(String str, int i2) {
        JSONObject jSONObject = new JSONObject();
        Bitmap a2 = com.jingdong.manto.utils.b.a().a(this.f13800e, str);
        try {
            if (a2 != null) {
                int width = a2.getWidth();
                int height = a2.getHeight();
                jSONObject.put("width", width);
                jSONObject.put("height", height);
            } else {
                jSONObject.put("error", "preload bitmap error");
            }
        } catch (Throwable unused) {
        }
        return jSONObject.toString();
    }

    public void b() {
        this.f13801f = null;
        JDTextureView jDTextureView = this.d;
        if (jDTextureView != null) {
            jDTextureView.b();
            this.d = null;
        }
    }

    public void b(int i2) {
        JDCanvasJNI.setContextType(this.b, i2);
        JDCanvasJNI.setDevicePixelRatio(this.b, MantoDensityUtils.getDensity(this.f13799c));
    }

    public void b(int i2, int i3, int i4, int i5) {
    }

    public void b(boolean z) {
    }
}
