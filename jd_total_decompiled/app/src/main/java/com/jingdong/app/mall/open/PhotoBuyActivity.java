package com.jingdong.app.mall.open;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.R;
import com.jingdong.common.deeplinkhelper.DeepLinkScanHelper;
import com.jingdong.common.utils.ImageCompressUtils;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.threadpool.ThreadManager;
import java.io.IOException;

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
    */
    public ImageCompressUtils.TargetImageInfo F(Activity activity, Uri uri) {
        Exception exc;
        ImageCompressUtils.TargetImageInfo targetImageInfo;
        ParcelFileDescriptor parcelFileDescriptor = null;
        try {
            targetImageInfo = ImageCompressUtils.compressImage(this, uri);
            exc = null;
        } catch (Exception e2) {
            exc = e2;
            targetImageInfo = null;
        }
        if (targetImageInfo == null && uri != null && activity != null && Build.VERSION.SDK_INT >= 24) {
            ExceptionReporter.reportOpenAppJumpException("Openapp_SendImgToPhotoBuy_New", uri.toString(), null);
            try {
                parcelFileDescriptor = activity.getContentResolver().openFileDescriptor(uri, "r");
                if (parcelFileDescriptor != null) {
                    targetImageInfo = ImageCompressUtils.compressImage(parcelFileDescriptor.getFileDescriptor());
                }
            } catch (Exception e3) {
                exc = e3;
            } catch (Throwable th) {
                if (parcelFileDescriptor != null) {
                    try {
                        parcelFileDescriptor.close();
                    } catch (IOException unused) {
                    }
                }
                throw th;
            }
        }
        if (targetImageInfo == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Uri = ");
            sb.append(uri != null ? uri.toString() : "");
            sb.append(",  Path = ");
            sb.append(uri.toString());
            ExceptionReporter.reportOpenAppJumpException("Openapp_SendImgToPhotoBuy_Err", sb.toString(), ExceptionReporter.getStackStringFromException(exc));
        }
        return targetImageInfo;
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
