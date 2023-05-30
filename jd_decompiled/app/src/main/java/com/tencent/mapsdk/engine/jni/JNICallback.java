package com.tencent.mapsdk.engine.jni;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.os.AsyncTask;
import androidx.annotation.Keep;
import com.tencent.map.lib.JNIInterfaceCallback;
import com.tencent.map.lib.models.MapTileID;
import com.tencent.mapsdk.engine.jni.models.IconImageInfo;
import com.tencent.mapsdk.engine.jni.models.TextBitmapInfo;
import com.tencent.mapsdk.internal.ae;
import com.tencent.mapsdk.internal.de;
import com.tencent.mapsdk.internal.e7;
import com.tencent.mapsdk.internal.fe;
import com.tencent.mapsdk.internal.gb;
import com.tencent.mapsdk.internal.ge;
import com.tencent.mapsdk.internal.ke;
import com.tencent.mapsdk.internal.la;
import com.tencent.mapsdk.internal.le;
import com.tencent.mapsdk.internal.ma;
import com.tencent.mapsdk.internal.mc;
import com.tencent.mapsdk.internal.me;
import com.tencent.mapsdk.internal.ne;
import com.tencent.mapsdk.internal.oe;
import com.tencent.mapsdk.internal.pe;
import com.tencent.mapsdk.internal.qa;
import com.tencent.mapsdk.internal.r1;
import com.tencent.mapsdk.internal.w;
import java.util.Hashtable;

@Keep
/* loaded from: classes9.dex */
public class JNICallback implements JNIInterfaceCallback {
    private static final int CB_TYPE_CAL_TEXT_SIZE = 2;
    private static final int CB_TYPE_CANCEL_DOWNLOAD = 10;
    private static final int CB_TYPE_DOWNLOAD = 3;
    private static final int CB_TYPE_DRAW_TEXT = 1;
    private static final int CB_TYPE_INDOOR_BUILDING_CHANGED = 8;
    private static final int CB_TYPE_LOAD_RES = 4;
    private static final int CB_TYPE_NOTIFY_SET_CENTER_AND_SCALE_ANIM_FINISHED = 9;
    private static final int CB_TYPE_REPORT_ENGINE_CRASH_INFO = 7;
    private static final int CB_TYPE_UPDATE_MAP_PARAM = 6;
    private static final int CB_TYPE_WRITE_FILE = 5;
    private static final int IMG_TYPE_SAT = 1;
    private fe mCancelDownloadCallback;
    private le mCbkGetGLContext;
    private ge mDownloadCallback;
    private r1 mEngineCrashInfoRecorder;
    private ke mIndoorBuildingChangeCallback;
    private ne mMapAnimCallback;
    private me mMapCameraChangeCallback;
    private de mMapLayerClickResultCallback;
    private oe mMapLoadFinishedCallback;
    private pe mMapParamChangeCallback;
    private ae mRender;
    private w mResources;
    private Hashtable<Long, Paint> mTextPaints = new Hashtable<>();
    private Hashtable<Long, PointF> mTextSizeBuffers = new Hashtable<>();
    private Bitmap textCanvas;

    /* loaded from: classes9.dex */
    public static final class a extends AsyncTask<Void, Void, Void> {
        private String a;
        private byte[] b;

        public a(String str, byte[] bArr) {
            this.a = str;
            this.b = bArr;
        }

        /* JADX WARN: Code restructure failed: missing block: B:18:0x003b, code lost:
            if (r2 == null) goto L28;
         */
        /* JADX WARN: Code restructure failed: missing block: B:24:0x0047, code lost:
            if (r2 == null) goto L28;
         */
        /* JADX WARN: Removed duplicated region for block: B:43:0x005f A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // android.os.AsyncTask
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.Void doInBackground(java.lang.Void... r5) {
            /*
                r4 = this;
                java.io.File r5 = new java.io.File
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r1 = r4.a
                r0.append(r1)
                java.lang.String r1 = ".tmp"
                r0.append(r1)
                java.lang.String r0 = r0.toString()
                r5.<init>(r0)
                r0 = 0
                r1 = 0
                boolean r2 = r5.exists()     // Catch: java.lang.Throwable -> L33 java.io.IOException -> L35 java.io.FileNotFoundException -> L41
                if (r2 != 0) goto L23
                com.tencent.mapsdk.internal.fa.b(r5)     // Catch: java.lang.Throwable -> L33 java.io.IOException -> L35 java.io.FileNotFoundException -> L41
            L23:
                java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L33 java.io.IOException -> L35 java.io.FileNotFoundException -> L41
                r2.<init>(r5)     // Catch: java.lang.Throwable -> L33 java.io.IOException -> L35 java.io.FileNotFoundException -> L41
                byte[] r3 = r4.b     // Catch: java.io.IOException -> L2f java.io.FileNotFoundException -> L31 java.lang.Throwable -> L5b
                r2.write(r3)     // Catch: java.io.IOException -> L2f java.io.FileNotFoundException -> L31 java.lang.Throwable -> L5b
                r1 = 1
                goto L3d
            L2f:
                r3 = move-exception
                goto L38
            L31:
                r3 = move-exception
                goto L44
            L33:
                r5 = move-exception
                goto L5d
            L35:
                r2 = move-exception
                r3 = r2
                r2 = r0
            L38:
                r3.printStackTrace()     // Catch: java.lang.Throwable -> L5b
                if (r2 == 0) goto L4e
            L3d:
                r2.close()     // Catch: java.io.IOException -> L4a
                goto L4e
            L41:
                r2 = move-exception
                r3 = r2
                r2 = r0
            L44:
                r3.printStackTrace()     // Catch: java.lang.Throwable -> L5b
                if (r2 == 0) goto L4e
                goto L3d
            L4a:
                r2 = move-exception
                r2.printStackTrace()
            L4e:
                if (r1 == 0) goto L5a
                java.io.File r1 = new java.io.File
                java.lang.String r2 = r4.a
                r1.<init>(r2)
                r5.renameTo(r1)
            L5a:
                return r0
            L5b:
                r5 = move-exception
                r0 = r2
            L5d:
                if (r0 == 0) goto L67
                r0.close()     // Catch: java.io.IOException -> L63
                goto L67
            L63:
                r0 = move-exception
                r0.printStackTrace()
            L67:
                goto L69
            L68:
                throw r5
            L69:
                goto L68
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.mapsdk.engine.jni.JNICallback.a.doInBackground(java.lang.Void[]):java.lang.Void");
        }
    }

    public JNICallback(ae aeVar, w wVar, ge geVar, fe feVar, oe oeVar, ke keVar, ne neVar, r1 r1Var, pe peVar, me meVar, de deVar) {
        this.mRender = aeVar;
        this.mResources = wVar;
        this.mDownloadCallback = geVar;
        this.mCancelDownloadCallback = feVar;
        this.mMapLoadFinishedCallback = oeVar;
        this.mMapParamChangeCallback = peVar;
        this.mIndoorBuildingChangeCallback = keVar;
        this.mMapCameraChangeCallback = meVar;
        this.mMapAnimCallback = neVar;
        this.mEngineCrashInfoRecorder = r1Var;
        this.mMapLayerClickResultCallback = deVar;
    }

    private void cacheTextPaint(Paint paint) {
        Hashtable<Long, Paint> hashtable = this.mTextPaints;
        if (hashtable != null) {
            hashtable.put(Long.valueOf(Thread.currentThread().getId()), paint);
        }
    }

    private void cacheTextSize(PointF pointF) {
        Hashtable<Long, PointF> hashtable = this.mTextSizeBuffers;
        if (hashtable != null) {
            hashtable.put(Long.valueOf(Thread.currentThread().getId()), pointF);
        }
    }

    private PointF calTextSize(String str, int i2) {
        float measureText = initTextPaint(i2).measureText(str) + 1.0f;
        int i3 = i2 + 2;
        PointF textSize = getTextSize();
        if (textSize == null) {
            textSize = new PointF();
            cacheTextSize(textSize);
        }
        textSize.x = measureText;
        textSize.y = i3;
        return textSize;
    }

    private void cancelDownload(String str, gb gbVar) {
        if (this.mCancelDownloadCallback != null) {
            ma.c("Engine callback cancel download:" + str);
            this.mCancelDownloadCallback.a(str, gbVar);
        }
    }

    private void download(String str, gb gbVar) {
        if (this.mDownloadCallback != null) {
            ma.c("Engine callback download:" + str + ":" + gbVar);
            this.mDownloadCallback.b(str, gbVar);
        }
    }

    private Bitmap drawText(int i2, String str, byte[] bArr) {
        TextBitmapInfo textBitmapInfo = new TextBitmapInfo();
        textBitmapInfo.fill(bArr);
        if (textBitmapInfo.width == 0 || textBitmapInfo.height == 0) {
            return null;
        }
        if (this.textCanvas == null) {
            this.textCanvas = Bitmap.createBitmap(400, 400, Bitmap.Config.ALPHA_8);
        }
        if (this.textCanvas == null) {
            return null;
        }
        Paint initTextPaint = initTextPaint(i2);
        this.textCanvas.eraseColor(0);
        initTextPaint.setFakeBoldText(textBitmapInfo.bold);
        new Canvas(this.textCanvas).drawText(str, 200.0f, 200.0f - ((initTextPaint.descent() + initTextPaint.ascent()) / 2.0f), initTextPaint);
        return this.textCanvas;
    }

    private Paint getTextPaint() {
        Hashtable<Long, Paint> hashtable = this.mTextPaints;
        if (hashtable == null) {
            return null;
        }
        return hashtable.get(Long.valueOf(Thread.currentThread().getId()));
    }

    private PointF getTextSize() {
        Hashtable<Long, PointF> hashtable = this.mTextSizeBuffers;
        if (hashtable == null) {
            return null;
        }
        return hashtable.get(Long.valueOf(Thread.currentThread().getId()));
    }

    private Paint initTextPaint(int i2) {
        Paint textPaint = getTextPaint();
        if (textPaint == null) {
            textPaint = new mc(this.mResources.a());
            textPaint.setTypeface(Typeface.DEFAULT);
            textPaint.setAntiAlias(true);
            textPaint.setStyle(Paint.Style.FILL);
            textPaint.setTextAlign(Paint.Align.CENTER);
            textPaint.setLinearText(true);
            cacheTextPaint(textPaint);
        }
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(i2);
        return textPaint;
    }

    private IconImageInfo loadImage(int i2, byte[] bArr) {
        if (bArr != null && bArr.length != 0) {
            try {
                String str = new String(bArr);
                return i2 == 1 ? this.mResources.c(str) : this.mResources.b(str);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    private void writeFile(String str, byte[] bArr) {
        if (bArr == null) {
            return;
        }
        new a(str, bArr).execute(new Void[0]);
    }

    @Override // com.tencent.map.lib.JNIInterfaceCallback
    public Object callback(int i2, int i3, String str, byte[] bArr, Object obj) {
        JNIEvent jNIEvent = new JNIEvent();
        jNIEvent.id = i3;
        jNIEvent.name = str;
        jNIEvent.data = bArr;
        jNIEvent.extra = obj;
        return callback(i2, jNIEvent);
    }

    public Object callback(int i2, JNIEvent jNIEvent) {
        switch (i2) {
            case 1:
                return drawText(jNIEvent.id, jNIEvent.name, jNIEvent.data);
            case 2:
                return calTextSize(jNIEvent.name, jNIEvent.id);
            case 3:
                if (!e7.b(jNIEvent.name)) {
                    gb gbVar = new gb();
                    gbVar.a = jNIEvent.id;
                    Object obj = jNIEvent.extra;
                    if (obj instanceof MapTileID) {
                        MapTileID mapTileID = (MapTileID) obj;
                        gbVar.b = mapTileID.getDataSource().getValue();
                        gbVar.f16603c = mapTileID.getPriority().getValue();
                    }
                    gbVar.d = jNIEvent.extra;
                    download(jNIEvent.name, gbVar);
                    break;
                }
                break;
            case 4:
                IconImageInfo loadImage = loadImage(jNIEvent.id, jNIEvent.data);
                if (jNIEvent.data != null) {
                    new String(jNIEvent.data);
                    return loadImage;
                }
                return loadImage;
            case 5:
                ma.a(la.f16819f, "CB_TYPE_WRITE_FILE:" + jNIEvent.name);
                writeFile(jNIEvent.name, jNIEvent.data);
                break;
            case 6:
                pe peVar = this.mMapParamChangeCallback;
                if (peVar != null) {
                    peVar.a(jNIEvent.id);
                    break;
                }
                break;
            case 7:
                r1 r1Var = this.mEngineCrashInfoRecorder;
                if (r1Var != null) {
                    r1Var.a(jNIEvent.name);
                    break;
                }
                break;
            case 8:
                ke keVar = this.mIndoorBuildingChangeCallback;
                if (keVar != null) {
                    keVar.d();
                    break;
                }
                break;
            case 9:
                ne neVar = this.mMapAnimCallback;
                if (neVar != null) {
                    neVar.a(jNIEvent.id > 0);
                    break;
                }
                break;
            case 10:
                qa.a("CB_TYPE_CANCEL_DOWNLOAD", jNIEvent);
                if (!e7.b(jNIEvent.name)) {
                    gb gbVar2 = new gb();
                    gbVar2.a = jNIEvent.id;
                    Object obj2 = jNIEvent.extra;
                    if (obj2 instanceof MapTileID) {
                        MapTileID mapTileID2 = (MapTileID) obj2;
                        gbVar2.b = mapTileID2.getDataSource().getValue();
                        gbVar2.f16603c = mapTileID2.getPriority().getValue();
                    }
                    gbVar2.d = jNIEvent.extra;
                    cancelDownload(jNIEvent.name, gbVar2);
                    break;
                }
                break;
        }
        return null;
    }

    @Override // com.tencent.map.lib.JNIInterfaceCallback
    public int callbackGetGLContext() {
        le leVar = this.mCbkGetGLContext;
        if (leVar != null) {
            return leVar.getEGLContextHash();
        }
        return 0;
    }

    public void destory() {
        Hashtable<Long, Paint> hashtable = this.mTextPaints;
        if (hashtable != null) {
            hashtable.clear();
            this.mTextPaints = null;
        }
        Hashtable<Long, PointF> hashtable2 = this.mTextSizeBuffers;
        if (hashtable2 != null) {
            hashtable2.clear();
            this.mTextSizeBuffers = null;
        }
        this.mResources = null;
        this.mDownloadCallback = null;
        this.mCancelDownloadCallback = null;
        this.mMapParamChangeCallback = null;
        this.mIndoorBuildingChangeCallback = null;
        this.mMapCameraChangeCallback = null;
        this.mRender = null;
    }

    @Override // com.tencent.map.lib.JNIInterfaceCallback
    public boolean onJniCallbackRenderMapFrame(int i2) {
        ae aeVar = this.mRender;
        if (aeVar != null) {
            return aeVar.a(i2);
        }
        return false;
    }

    @Override // com.tencent.map.lib.JNIInterfaceCallback
    public void onMapCameraChangeStopped() {
        me meVar = this.mMapCameraChangeCallback;
        if (meVar != null) {
            meVar.onMapCameraChangeStopped();
        }
    }

    @Override // com.tencent.map.lib.JNIInterfaceCallback
    public void onMapCameraChanged() {
        me meVar = this.mMapCameraChangeCallback;
        if (meVar != null) {
            meVar.onMapCameraChanged();
        }
    }

    @Override // com.tencent.map.lib.JNIInterfaceCallback
    public void onMapLoaded() {
        oe oeVar = this.mMapLoadFinishedCallback;
        if (oeVar != null) {
            oeVar.onMapLoaded();
        }
    }

    @Override // com.tencent.map.lib.JNIInterfaceCallback
    public void onVisualLayerClickResult(float f2, float f3, long j2, String str, String str2) {
        de deVar = this.mMapLayerClickResultCallback;
        if (deVar != null) {
            deVar.a(f2, f3, j2, str, str2);
        }
    }

    public void setMapCallbackGetGLContext(le leVar) {
        this.mCbkGetGLContext = leVar;
    }
}
