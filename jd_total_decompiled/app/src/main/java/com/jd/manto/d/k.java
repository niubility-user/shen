package com.jd.manto.d;

import android.content.Context;
import android.os.Bundle;
import com.googlecode.mp4parser.boxes.mp4.samplegrouping.RollRecoveryEntry;
import com.jingdong.common.face.ARConfigInfo;
import com.jingdong.common.face.ApiCallback;
import com.jingdong.common.face.FaceHelper;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.manto.sdk.api.IFaceDetect;
import java.util.Observable;
import java.util.Observer;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class k implements IFaceDetect {

    /* loaded from: classes17.dex */
    class a extends ApiCallback<ARConfigInfo> {
        final /* synthetic */ IFaceDetect.FaceInitCallback a;
        final /* synthetic */ Context b;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.jd.manto.d.k$a$a  reason: collision with other inner class name */
        /* loaded from: classes17.dex */
        public class C0191a implements Observer {
            C0191a() {
            }

            @Override // java.util.Observer
            public void update(Observable observable, Object obj) {
                double doubleValue = ((Double) obj).doubleValue();
                if (doubleValue < 0.0d) {
                    a.this.a.onFailed("\u4e0b\u8f7d\u6a21\u578b\u5f02\u5e38");
                } else if (doubleValue >= 1.0d) {
                    a aVar = a.this;
                    k.this.b(aVar.b, aVar.a);
                }
            }
        }

        a(IFaceDetect.FaceInitCallback faceInitCallback, Context context) {
            this.a = faceInitCallback;
            this.b = context;
        }

        @Override // com.jingdong.common.face.ApiCallback
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void onResponse(ARConfigInfo aRConfigInfo) {
            try {
                FaceHelper.download(this.b, aRConfigInfo.getJd_url_android(), aRConfigInfo.getJd_md5_android(), new C0191a());
            } catch (Exception e2) {
                this.a.onFailed("error");
                e2.printStackTrace();
            }
        }

        @Override // com.jingdong.common.face.ApiCallback
        public void onFailure(Exception exc) {
            this.a.onFailed("\u83b7\u53d6\u4eba\u8138\u914d\u7f6e\u5931\u8d25");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class b implements Observer {
        final /* synthetic */ IFaceDetect.FaceInitCallback a;

        b(k kVar, IFaceDetect.FaceInitCallback faceInitCallback) {
            this.a = faceInitCallback;
        }

        @Override // java.util.Observer
        public void update(Observable observable, Object obj) {
            if (obj instanceof Boolean) {
                String str = "init result:" + obj;
                if (((Boolean) obj).booleanValue()) {
                    this.a.onSuccess();
                    return;
                } else {
                    this.a.onFailed("\u521d\u59cb\u5316\u5931\u8d25");
                    return;
                }
            }
            this.a.onFailed("\u521d\u59cb\u5316\u5f02\u5e38");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Context context, IFaceDetect.FaceInitCallback faceInitCallback) {
        FaceHelper.init(context, new b(this, faceInitCallback));
    }

    @Override // com.jingdong.manto.sdk.api.IFaceDetect
    public void getJfaceTracker(byte[] bArr, int i2, int i3, IFaceDetect.FaceDetectCallback faceDetectCallback) {
        Bundle bundle;
        IFaceDetect.FaceDetectCallback faceDetectCallback2 = faceDetectCallback;
        try {
            JSONObject jSONObject = new JSONObject(FaceHelper.getJFaceRGBTracker(bArr, i2, i3));
            if (jSONObject.optInt("stateCode") != 0) {
                faceDetectCallback2.onFailed(null);
            } else if (jSONObject.optInt("faceNumber") <= 0) {
                faceDetectCallback2.onFailed(null);
            } else {
                JSONObject jSONObject2 = jSONObject.optJSONArray("faceInfo").getJSONObject(0);
                int optInt = jSONObject2.optInt(JshopConst.JSHOP_PROMOTIO_X);
                int optInt2 = jSONObject2.optInt(JshopConst.JSHOP_PROMOTIO_Y);
                int optInt3 = jSONObject2.optInt("width");
                int optInt4 = jSONObject2.optInt("height");
                JSONArray optJSONArray = jSONObject2.optJSONArray("point");
                double optDouble = jSONObject2.optDouble(RollRecoveryEntry.TYPE);
                try {
                    double optDouble2 = jSONObject2.optDouble("yaw");
                    String str = JshopConst.JSHOP_PROMOTIO_Y;
                    String str2 = JshopConst.JSHOP_PROMOTIO_X;
                    double optDouble3 = jSONObject2.optDouble("pch");
                    JSONObject jSONObject3 = new JSONObject();
                    try {
                        jSONObject3.put("originX", optInt);
                        jSONObject3.put("originY", optInt2);
                        jSONObject3.put(CartConstant.KEY_SKU_WEIGHT, optInt3);
                        jSONObject3.put("height", optInt4);
                        JSONObject jSONObject4 = new JSONObject();
                        jSONObject4.put("pitch", optDouble3);
                        jSONObject4.put("yaw", optDouble2);
                        jSONObject4.put(RollRecoveryEntry.TYPE, optDouble);
                        JSONArray jSONArray = new JSONArray();
                        if (optJSONArray.length() == 812) {
                            int i4 = 600;
                            while (i4 < 812) {
                                JSONObject jSONObject5 = new JSONObject();
                                String str3 = str2;
                                jSONObject5.put(str3, optJSONArray.get(i4));
                                String str4 = str;
                                jSONObject5.put(str4, optJSONArray.get(i4 + 1));
                                jSONArray.put(jSONObject5);
                                i4 += 2;
                                str2 = str3;
                                str = str4;
                            }
                        }
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("detectRect", jSONObject3.toString());
                        bundle2.putString("angleArray", jSONObject4.toString());
                        bundle2.putString("pointArray", jSONArray.toString());
                        faceDetectCallback2 = faceDetectCallback;
                        faceDetectCallback2.onResult(bundle2);
                    } catch (Exception unused) {
                        faceDetectCallback2 = faceDetectCallback;
                        bundle = null;
                        faceDetectCallback2.onFailed(bundle);
                    }
                } catch (Exception unused2) {
                }
            }
        } catch (Exception unused3) {
            bundle = null;
        }
    }

    @Override // com.jingdong.manto.sdk.api.IFaceDetect
    public void init(Context context, IFaceDetect.FaceInitCallback faceInitCallback) {
        FaceHelper.getArConfig(new a(faceInitCallback, context));
    }

    @Override // com.jingdong.manto.sdk.api.IFaceDetect
    public void release() {
        FaceHelper.release();
    }
}
