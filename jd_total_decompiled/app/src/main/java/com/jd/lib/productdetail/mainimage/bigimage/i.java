package com.jd.lib.productdetail.mainimage.bigimage;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import cn.com.union.fido.common.MIMEType;
import com.jd.lib.productdetail.core.R;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.core.utils.PdMd5Encoder;
import com.jd.lib.productdetail.core.utils.PdWaterMark;
import com.jd.lib.productdetail.mainimage.bigimage.PdBigImageActivity;
import com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener;
import com.jingdong.common.ui.JDBottomDialog;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.sdk.threadpool.ThreadManager;
import com.jingdong.sdk.threadpool.callback.RunnerTaskCallback;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes15.dex */
public class i implements View.OnClickListener {

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ JDBottomDialog f4600g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ PdBigImageActivity.ImageFragment f4601h;

    /* loaded from: classes15.dex */
    public class a extends JDSimpleImageLoadingListener {

        /* renamed from: g  reason: collision with root package name */
        public final /* synthetic */ String f4602g;

        /* renamed from: h  reason: collision with root package name */
        public final /* synthetic */ File f4603h;

        /* renamed from: i  reason: collision with root package name */
        public final /* synthetic */ String f4604i;

        /* renamed from: com.jd.lib.productdetail.mainimage.bigimage.i$a$a  reason: collision with other inner class name */
        /* loaded from: classes15.dex */
        public class RunnableC0153a implements Runnable {

            /* renamed from: g  reason: collision with root package name */
            public final /* synthetic */ Bitmap f4606g;

            public RunnableC0153a(Bitmap bitmap) {
                this.f4606g = bitmap;
            }

            /* JADX WARN: Code restructure failed: missing block: B:34:0x00e4, code lost:
                if (r2 != null) goto L59;
             */
            /* JADX WARN: Code restructure failed: missing block: B:44:0x00f3, code lost:
                if (r2 == null) goto L49;
             */
            /* JADX WARN: Code restructure failed: missing block: B:45:0x00f5, code lost:
                r2.close();
             */
            /* JADX WARN: Code restructure failed: missing block: B:47:0x00f9, code lost:
                r0 = move-exception;
             */
            /* JADX WARN: Code restructure failed: missing block: B:48:0x00fa, code lost:
                com.jingdong.jdsdk.network.toolbox.ExceptionReporter.reportExceptionToBugly(r0);
             */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void run() {
                Bitmap bitmap;
                int i2;
                boolean z;
                Uri insert;
                PdBigImageActivity pdBigImageActivity = i.this.f4601h.q;
                if (pdBigImageActivity == null || pdBigImageActivity.isFinishing() || (bitmap = JDImageUtils.getBitmap(i.this.f4601h.q, R.drawable.lib_pd_core_bg_water_mark)) == null) {
                    return;
                }
                OutputStream outputStream = null;
                Bitmap createWaterBitmap = PdWaterMark.createWaterBitmap(this.f4606g, bitmap, null);
                if (createWaterBitmap == null || (i2 = Build.VERSION.SDK_INT) < 29) {
                    return;
                }
                a aVar = a.this;
                PdBigImageActivity.ImageFragment imageFragment = i.this.f4601h;
                String str = aVar.f4602g;
                if (imageFragment.getContext() != null && !TextUtils.isEmpty(str)) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("_display_name", str);
                    if (i2 >= 29) {
                        contentValues.put("relative_path", Environment.DIRECTORY_PICTURES + "/JDImage");
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append(Environment.getExternalStorageDirectory().getPath());
                        String str2 = File.separator;
                        sb.append(str2);
                        sb.append(Environment.DIRECTORY_DCIM);
                        sb.append(str2);
                        sb.append(System.currentTimeMillis());
                        sb.append(".png");
                        contentValues.put("_data", sb.toString());
                    }
                    contentValues.put("description", "");
                    contentValues.put("mime_type", MIMEType.MIME_TYPE_JPEG);
                    if (imageFragment.getContext().getContentResolver() != null && (insert = imageFragment.getContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)) != null) {
                        try {
                            try {
                                OutputStream openOutputStream = imageFragment.getContext().getContentResolver().openOutputStream(insert);
                                try {
                                    byte[] compressBitmap2Bytes = PDUtils.compressBitmap2Bytes(createWaterBitmap, 10485760, 100);
                                    if (openOutputStream != null) {
                                        openOutputStream.write(compressBitmap2Bytes);
                                        openOutputStream.close();
                                    } else {
                                        outputStream = openOutputStream;
                                    }
                                } catch (Exception e2) {
                                    e = e2;
                                    outputStream = openOutputStream;
                                    ExceptionReporter.reportExceptionToBugly(e);
                                } catch (Throwable th) {
                                    th = th;
                                    outputStream = openOutputStream;
                                    if (outputStream != null) {
                                        try {
                                            outputStream.close();
                                        } catch (IOException e3) {
                                            ExceptionReporter.reportExceptionToBugly(e3);
                                        }
                                    }
                                    throw th;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                            }
                        } catch (Exception e4) {
                            e = e4;
                        }
                    }
                }
                z = false;
                imageFragment.V = z;
                z = true;
                imageFragment.V = z;
            }
        }

        /* loaded from: classes15.dex */
        public class b implements RunnerTaskCallback {

            /* renamed from: com.jd.lib.productdetail.mainimage.bigimage.i$a$b$a  reason: collision with other inner class name */
            /* loaded from: classes15.dex */
            public class RunnableC0154a implements Runnable {

                /* renamed from: g  reason: collision with root package name */
                public final /* synthetic */ String f4608g;

                public RunnableC0154a(String str) {
                    this.f4608g = str;
                }

                @Override // java.lang.Runnable
                public void run() {
                    PdBigImageActivity.ImageFragment imageFragment = i.this.f4601h;
                    PDUtils.showToastCenterIcon(imageFragment.q, imageFragment.V ? (byte) 2 : (byte) 1, this.f4608g);
                }
            }

            /* renamed from: com.jd.lib.productdetail.mainimage.bigimage.i$a$b$b  reason: collision with other inner class name */
            /* loaded from: classes15.dex */
            public class RunnableC0155b implements Runnable {
                public RunnableC0155b(String str) {
                }

                @Override // java.lang.Runnable
                public void run() {
                    PdBigImageActivity.ImageFragment imageFragment = i.this.f4601h;
                    PDUtils.showToastCenterIcon(imageFragment.q, imageFragment.V ? (byte) 2 : (byte) 1, "\u50a8\u5b58\u7a7a\u95f4\u4e0d\u8db3,\u4fdd\u5b58\u5931\u8d25");
                }
            }

            public b(Bitmap bitmap, Bitmap bitmap2) {
            }

            @Override // com.jingdong.sdk.threadpool.callback.RunnerTaskCallback
            public void onFailed(String str, Throwable th) {
                PdBigImageActivity pdBigImageActivity = i.this.f4601h.q;
                if (pdBigImageActivity == null || pdBigImageActivity.isFinishing()) {
                    return;
                }
                i.this.f4601h.q.runOnUiThread(new RunnableC0155b("\u50a8\u5b58\u7a7a\u95f4\u4e0d\u8db3,\u4fdd\u5b58\u5931\u8d25"));
            }

            @Override // com.jingdong.sdk.threadpool.callback.RunnerTaskCallback
            public void onStart(String str) {
            }

            @Override // com.jingdong.sdk.threadpool.callback.RunnerTaskCallback
            public void onSuccess(String str, Object obj) {
                PdBigImageActivity pdBigImageActivity = i.this.f4601h.q;
                if (pdBigImageActivity == null || pdBigImageActivity.isFinishing()) {
                    return;
                }
                PdBigImageActivity.ImageFragment imageFragment = i.this.f4601h;
                if (imageFragment.V) {
                    imageFragment.q.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.parse("file://" + a.this.f4603h.getAbsolutePath())));
                }
                a aVar = a.this;
                i.this.f4601h.q.runOnUiThread(new RunnableC0154a(i.this.f4601h.V ? "\u56fe\u7247\u5df2\u4fdd\u5b58\u81f3".concat(aVar.f4604i) : "\u50a8\u5b58\u7a7a\u95f4\u4e0d\u8db3,\u4fdd\u5b58\u5931\u8d25"));
            }
        }

        public a(String str, File file, String str2) {
            this.f4602g = str;
            this.f4603h = file;
            this.f4604i = str2;
        }

        @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
            PdBigImageActivity pdBigImageActivity = i.this.f4601h.q;
            if (pdBigImageActivity == null || pdBigImageActivity.isFinishing()) {
                return;
            }
            ThreadManager.light().post(new RunnableC0153a(bitmap), "", new b(null, null));
        }
    }

    public i(PdBigImageActivity.ImageFragment imageFragment, JDBottomDialog jDBottomDialog) {
        this.f4601h = imageFragment;
        this.f4600g = jDBottomDialog;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        String str = PdMd5Encoder.encode(this.f4601h.f4558g, "") + ".jpg";
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory());
        String str2 = File.separator;
        sb.append(str2);
        sb.append(Environment.DIRECTORY_PICTURES.concat(str2 + "JDImage"));
        String sb2 = sb.toString();
        File file = new File(sb2);
        if (!file.exists()) {
            file.mkdirs();
        }
        JDImageUtils.loadImage(this.f4601h.f4558g, new a("" + str, new File(sb2.concat(str2 + str)), sb2));
        if (this.f4600g.isShowing()) {
            this.f4600g.dismiss();
        }
    }
}
