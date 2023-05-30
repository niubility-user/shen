package com.jingdong.manto.jsapi.camera;

import android.os.Build;
import android.text.TextUtils;
import com.jingdong.common.apkcenter.ApkDownloadTable;
import com.jingdong.manto.R;
import com.jingdong.manto.jsapi.camera.record.MantoCameraView;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.utils.MantoStringUtils;
import com.jingdong.manto.utils.m;
import java.nio.ByteBuffer;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public final class c extends d0 {

    /* loaded from: classes15.dex */
    class a implements Runnable {
        final /* synthetic */ JSONObject a;
        final /* synthetic */ com.jingdong.manto.h b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ int f13143c;
        final /* synthetic */ String d;

        /* renamed from: com.jingdong.manto.jsapi.camera.c$a$a  reason: collision with other inner class name */
        /* loaded from: classes15.dex */
        class C0523a implements com.jingdong.manto.jsapi.camera.a {
            C0523a() {
            }

            @Override // com.jingdong.manto.jsapi.camera.a
            public void a(int i2, String str) {
                if (i2 == 0) {
                    a aVar = a.this;
                    aVar.b.a(aVar.f13143c, c.this.putErrMsg(IMantoBaseModule.SUCCESS, null, aVar.d));
                    return;
                }
                a aVar2 = a.this;
                aVar2.b.a(aVar2.f13143c, c.this.putErrMsg("fail:" + str, null, a.this.d));
            }

            @Override // com.jingdong.manto.jsapi.camera.a
            public final void a(int i2, String str, String str2, int i3, int i4) {
                if (i2 == 0) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("tempImagePath", str);
                    hashMap.put("width", Integer.valueOf(i3));
                    hashMap.put("height", Integer.valueOf(i4));
                    a aVar = a.this;
                    aVar.b.a(aVar.f13143c, c.this.putErrMsg(IMantoBaseModule.SUCCESS, hashMap, aVar.d));
                    return;
                }
                a aVar2 = a.this;
                aVar2.b.a(aVar2.f13143c, c.this.putErrMsg("fail:" + str2, null, a.this.d));
            }

            @Override // com.jingdong.manto.jsapi.camera.a
            public void a(int i2, String str, String str2, String str3) {
                if (i2 == 0 && !TextUtils.isEmpty(str2)) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("tempThumbPath", str);
                    hashMap.put("tempVideoPath", str2);
                    a aVar = a.this;
                    aVar.b.a(aVar.f13143c, c.this.putErrMsg(IMantoBaseModule.SUCCESS, hashMap, aVar.d));
                    return;
                }
                a aVar2 = a.this;
                aVar2.b.a(aVar2.f13143c, c.this.putErrMsg("fail: " + str3, null, a.this.d));
            }

            @Override // com.jingdong.manto.jsapi.camera.a
            public void a(String str, byte[] bArr, int i2, int i3) {
                if (!a.this.b.d || !MantoStringUtils.isEmpty(str) || bArr == null) {
                    a aVar = a.this;
                    aVar.b.a(aVar.f13143c, c.this.putErrMsg("fail:" + str, null, a.this.d));
                    return;
                }
                JSONObject jSONObject = new JSONObject();
                f fVar = new f();
                try {
                    jSONObject.put("width", i2);
                    jSONObject.put("height", i3);
                } catch (Exception unused) {
                }
                int i4 = i2 * i3 * 4;
                ((com.jingdong.manto.jsengine.a) a.this.b.g().getInterface(com.jingdong.manto.jsengine.a.class)).setNativeBuffer(i4, ByteBuffer.wrap(bArr));
                jSONObject.put("bufferId", i4);
                fVar.a(a.this.b).f13315c = jSONObject.toString();
                fVar.a();
            }
        }

        a(JSONObject jSONObject, com.jingdong.manto.h hVar, int i2, String str) {
            this.a = jSONObject;
            this.b = hVar;
            this.f13143c = i2;
            this.d = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            int optInt = this.a.optInt("cameraId");
            String optString = this.a.optString("type");
            j a = j.a();
            Integer valueOf = Integer.valueOf(optInt);
            MantoCameraViewContainer mantoCameraViewContainer = a.d.containsKey(valueOf) ? a.d.get(valueOf) : null;
            if (mantoCameraViewContainer == null) {
                this.b.a(this.f13143c, c.this.putErrMsg("fail:no such camera", null, this.d));
            } else if (mantoCameraViewContainer.h() && !TextUtils.equals(optString, "stopRecord")) {
                this.b.a(this.f13143c, c.this.putErrMsg("fail:" + mantoCameraViewContainer.getContext().getString(R.string.manto_not_allow_capture_hide), null, this.d));
            } else {
                mantoCameraViewContainer.setOperateCallback(new C0523a());
                if (optString.equals("closeFrameChange")) {
                    mantoCameraViewContainer.n();
                    this.b.a(this.f13143c, c.this.putErrMsg(IMantoBaseModule.SUCCESS, null, this.d));
                } else if (optString.equals("listenFrameChange")) {
                    if (Build.VERSION.SDK_INT < 17) {
                        this.b.a(this.f13143c, c.this.putErrMsg("fail:system too old.", null, this.d));
                        return;
                    }
                    com.jingdong.manto.jsengine.a aVar = (com.jingdong.manto.jsengine.a) this.b.g().getInterface(com.jingdong.manto.jsengine.a.class);
                    if (aVar == null || !aVar.canUseNativeBuffer()) {
                        HashMap hashMap = new HashMap(2);
                        hashMap.put("message", "can not use nativeBuffer in this phone.");
                        this.b.a(this.f13143c, c.this.putErrMsg("fail:can not use nativeBuffer.", hashMap, this.d));
                        return;
                    }
                    this.a.optString(ApkDownloadTable.FIELD_SIZE);
                    this.b.a(this.f13143c, c.this.putErrMsg(IMantoBaseModule.SUCCESS, new HashMap(3), this.d));
                    mantoCameraViewContainer.m();
                } else if (optString.equals("startRecord")) {
                    if (!TextUtils.equals("1", m.a("cameraRecord", "1"))) {
                        this.b.a(this.f13143c, c.this.putErrMsg("fail:startRecord not enable", null, this.d));
                    } else if (mantoCameraViewContainer.f13138m == null) {
                        this.b.a(this.f13143c, c.this.putErrMsg("fail:camera is not ready", null, this.d));
                    } else {
                        mantoCameraViewContainer.a();
                        this.b.a(this.f13143c, c.this.putErrMsg(IMantoBaseModule.SUCCESS, null, this.d));
                    }
                } else if (optString.equals("stopRecord")) {
                    if (!TextUtils.equals("1", m.a("cameraRecord", "1"))) {
                        this.b.a(this.f13143c, c.this.putErrMsg("fail:stopRecord not enable", null, this.d));
                    } else if (mantoCameraViewContainer.f13138m == null) {
                        this.b.a(this.f13143c, c.this.putErrMsg("fail:camera is not ready.", null, this.d));
                    } else {
                        mantoCameraViewContainer.b();
                        this.b.a(this.f13143c, c.this.putErrMsg(IMantoBaseModule.SUCCESS, null, this.d));
                    }
                } else if (optString.equals("takePhoto")) {
                    mantoCameraViewContainer.setQuality(this.a.optString("quality", "high"));
                    MantoCameraView mantoCameraView = mantoCameraViewContainer.f13138m;
                    if (mantoCameraView != null) {
                        mantoCameraView.b();
                    } else {
                        this.b.a(this.f13143c, c.this.putErrMsg("fail:camera is not ready..", null, this.d));
                    }
                } else if (!optString.equals("setZoom")) {
                    this.b.a(this.f13143c, c.this.putErrMsg("fail:operateType not supported", null, this.d));
                } else {
                    float optDouble = (float) this.a.optDouble("zoom");
                    if (optDouble < 1.0f) {
                        this.b.a(this.f13143c, c.this.putErrMsg("fail: zoom multiple not support", null, this.d));
                        return;
                    }
                    if (mantoCameraViewContainer.f13138m != null) {
                        mantoCameraViewContainer.setZoom(optDouble);
                    }
                    HashMap hashMap2 = new HashMap(1);
                    hashMap2.put("zoom", Float.valueOf(optDouble));
                    this.b.a(this.f13143c, c.this.putErrMsg(IMantoBaseModule.SUCCESS, hashMap2, this.d));
                }
            }
        }
    }

    @Override // com.jingdong.manto.m.d0
    public void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        super.exec(hVar, jSONObject, i2, str);
        if (jSONObject == null) {
            hVar.a(i2, putErrMsg("fail:data is null or nil", null, str));
        } else {
            com.jingdong.manto.b.d().mainThread().execute(new a(jSONObject, hVar, i2, str));
        }
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "operateCamera";
    }
}
