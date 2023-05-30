package com.jingdong.app.mall.open;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.R;
import com.jingdong.common.deeplinkhelper.DeepLinkScanHelper;
import com.jingdong.common.utils.ImageCompressUtils;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.threadpool.ThreadManager;

/* loaded from: classes4.dex */
public class PhotoBuyActivity extends BaseEntryActivity {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Activity f11443g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Uri f11444h;

        /* renamed from: com.jingdong.app.mall.open.PhotoBuyActivity$a$a  reason: collision with other inner class name */
        /* loaded from: classes4.dex */
        class RunnableC0367a implements Runnable {

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ ImageCompressUtils.TargetImageInfo f11446g;

            RunnableC0367a(ImageCompressUtils.TargetImageInfo targetImageInfo) {
                this.f11446g = targetImageInfo;
            }

            @Override // java.lang.Runnable
            public void run() {
                a aVar = a.this;
                PhotoBuyActivity.this.E(aVar.f11443g, this.f11446g);
            }
        }

        a(Activity activity, Uri uri) {
            this.f11443g = activity;
            this.f11444h = uri;
        }

        @Override // java.lang.Runnable
        public void run() {
            ThreadManager.getMainHandler().post(new RunnableC0367a(PhotoBuyActivity.this.F(this.f11443g, this.f11444h)));
        }
    }

    private void G(Activity activity, Uri uri) {
        ThreadManager.light().post(new a(activity, uri));
    }

    public void E(Activity activity, ImageCompressUtils.TargetImageInfo targetImageInfo) {
        if (targetImageInfo != null && targetImageInfo.data != null) {
            Bundle bundle = new Bundle();
            bundle.putByteArray("bmpByte", targetImageInfo.data);
            bundle.putBoolean("isAlbum", true);
            bundle.putInt("srcType", 99);
            bundle.putString("path", "3");
            bundle.putString("mainBodyRectangle", "0,0|" + targetImageInfo.targetWidth + DYConstants.DY_REGEX_COMMA + targetImageInfo.targetHeight);
            DeepLinkScanHelper.startPhotoBuyResultActivity(activity, bundle);
            JDMtaUtils.onClick(activity, "App_PhotoShareAppStartup", activity.getClass().getSimpleName(), "", "");
        } else {
            ToastUtils.shortToast(activity, (int) R.string.m1);
        }
        activity.finish();
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0033, code lost:
        if (r0 != null) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0035, code lost:
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0044, code lost:
        if (r0 == null) goto L28;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.jingdong.common.utils.ImageCompressUtils.TargetImageInfo F(android.app.Activity r6, android.net.Uri r7) {
        /*
            r5 = this;
            r0 = 0
            com.jingdong.common.utils.ImageCompressUtils$TargetImageInfo r1 = com.jingdong.common.utils.ImageCompressUtils.compressImage(r5, r7)     // Catch: java.lang.Exception -> L7
            r2 = r0
            goto La
        L7:
            r1 = move-exception
            r2 = r1
            r1 = r0
        La:
            if (r1 != 0) goto L47
            if (r7 == 0) goto L47
            if (r6 == 0) goto L47
            int r3 = android.os.Build.VERSION.SDK_INT
            r4 = 24
            if (r3 < r4) goto L47
            java.lang.String r3 = r7.toString()
            java.lang.String r4 = "Openapp_SendImgToPhotoBuy_New"
            com.jingdong.jdsdk.network.toolbox.ExceptionReporter.reportOpenAppJumpException(r4, r3, r0)
            android.content.ContentResolver r6 = r6.getContentResolver()     // Catch: java.lang.Throwable -> L3b java.lang.Exception -> L42
            java.lang.String r3 = "r"
            android.os.ParcelFileDescriptor r0 = r6.openFileDescriptor(r7, r3)     // Catch: java.lang.Throwable -> L3b java.lang.Exception -> L42
            if (r0 == 0) goto L33
            java.io.FileDescriptor r6 = r0.getFileDescriptor()     // Catch: java.lang.Throwable -> L3b java.lang.Exception -> L42
            com.jingdong.common.utils.ImageCompressUtils$TargetImageInfo r1 = com.jingdong.common.utils.ImageCompressUtils.compressImage(r6)     // Catch: java.lang.Throwable -> L3b java.lang.Exception -> L42
        L33:
            if (r0 == 0) goto L47
        L35:
            r0.close()     // Catch: java.io.IOException -> L39
            goto L47
        L39:
            goto L47
        L3b:
            r6 = move-exception
            if (r0 == 0) goto L41
            r0.close()     // Catch: java.io.IOException -> L41
        L41:
            throw r6
        L42:
            r6 = move-exception
            r2 = r6
            if (r0 == 0) goto L47
            goto L35
        L47:
            if (r1 != 0) goto L78
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r0 = "Uri = "
            r6.append(r0)
            if (r7 == 0) goto L5a
            java.lang.String r0 = r7.toString()
            goto L5c
        L5a:
            java.lang.String r0 = ""
        L5c:
            r6.append(r0)
            java.lang.String r0 = ",  Path = "
            r6.append(r0)
            java.lang.String r7 = r7.toString()
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            java.lang.String r7 = com.jingdong.jdsdk.network.toolbox.ExceptionReporter.getStackStringFromException(r2)
            java.lang.String r0 = "Openapp_SendImgToPhotoBuy_Err"
            com.jingdong.jdsdk.network.toolbox.ExceptionReporter.reportOpenAppJumpException(r0, r6, r7)
        L78:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.open.PhotoBuyActivity.F(android.app.Activity, android.net.Uri):com.jingdong.common.utils.ImageCompressUtils$TargetImageInfo");
    }

    @Override // com.jingdong.app.mall.open.BaseEntryActivity
    protected void v(Intent intent) {
        ClipData clipData;
        if ("android.intent.action.SEND".equals(intent.getAction())) {
            Uri uri = null;
            Uri uri2 = (Build.VERSION.SDK_INT < 16 || (clipData = intent.getClipData()) == null || clipData.getItemCount() <= 0) ? null : clipData.getItemAt(0).getUri();
            if (uri2 != null || intent.getExtras() == null) {
                uri = uri2;
            } else {
                try {
                    uri = (Uri) intent.getExtras().get("android.intent.extra.STREAM");
                } catch (Exception unused) {
                }
            }
            if (uri != null) {
                G(this, uri);
            } else {
                ToastUtils.shortToast(this, (int) R.string.m1);
            }
        }
    }
}
