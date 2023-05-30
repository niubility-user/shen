package com.jingdong.common.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.frame.IMainActivity;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.jdsdk.utils.cache.GlobalImageCache;
import com.jingdong.sdk.oklog.OKLog;
import com.novoda.imageloader.core.bitmap.BitmapUtil;
import java.io.File;
import java.io.InputStream;

/* loaded from: classes6.dex */
public class ImageUtil {
    private static final String TAG = "ImageUtil";

    /* loaded from: classes6.dex */
    public interface ImageLoadListener {
        void onError(GlobalImageCache.BitmapDigest bitmapDigest);

        void onProgress(GlobalImageCache.BitmapDigest bitmapDigest, int i2, int i3);

        void onStart(GlobalImageCache.BitmapDigest bitmapDigest);

        void onSuccess(GlobalImageCache.BitmapDigest bitmapDigest, Bitmap bitmap);
    }

    /* loaded from: classes6.dex */
    public static class InputWay {
        private byte[] byteArray;
        private File file;
        private InputStream inputStream;
        private int resourceId;

        public static InputWay createInputWay(HttpResponse httpResponse) {
            InputWay inputWay = new InputWay();
            if (httpResponse != null) {
                inputWay.setByteArray(httpResponse.getInputData());
                inputWay.setFile(httpResponse.getSaveFile());
            }
            return inputWay;
        }

        public byte[] getByteArray() {
            return this.byteArray;
        }

        public File getFile() {
            return this.file;
        }

        public InputStream getInputStream() {
            return this.inputStream;
        }

        public int getResourceId() {
            return this.resourceId;
        }

        public void setByteArray(byte[] bArr) {
            this.byteArray = bArr;
        }

        public void setFile(File file) {
            this.file = file;
        }

        public void setInputStream(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        public void setResourceId(int i2) {
            this.resourceId = i2;
        }
    }

    public static Bitmap createBitmap(InputWay inputWay, GlobalImageCache.BitmapDigest bitmapDigest) {
        if (bitmapDigest.isLarge()) {
            if (OKLog.D) {
                OKLog.d(ImageUtil.class.getName(), "createBitmap() bitmapDigest isLarge let cleanMost  -->> ");
            }
            GlobalImageCache.getLruBitmapCache().cleanMost();
        }
        Bitmap createBitmap = createBitmap(inputWay, bitmapDigest.getWidth(), bitmapDigest.getHeight());
        if (createBitmap == null) {
            return null;
        }
        return bitmapDigest.getRound() != 0 ? toRoundCorner(createBitmap, bitmapDigest.getRound()) : createBitmap;
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        drawable.draw(canvas);
        return createBitmap;
    }

    public static Drawable drawableToDrawable16(Drawable drawable) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        drawable.draw(canvas);
        return new BitmapDrawable(createBitmap);
    }

    private static LayoutInflater getLayoutInflater() {
        IMyActivity currentMyActivity = BaseFrameUtil.getInstance().getCurrentMyActivity();
        IMainActivity mainFrameActivity = BaseFrameUtil.getInstance().getMainFrameActivity();
        if (currentMyActivity != null) {
            return getLayoutInflater(currentMyActivity.getThisActivity());
        }
        if (mainFrameActivity != null) {
            return getLayoutInflater(mainFrameActivity.getThisActivity());
        }
        return getLayoutInflater(JdSdk.getInstance().getApplication());
    }

    public static Bitmap getRoundedCornerBitmap(Drawable drawable, float f2) {
        return getRoundedCornerBitmap(drawableToBitmap(drawable), f2);
    }

    public static Intent getSelectFileIntent() {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        return intent;
    }

    public static View inflate(int i2, ViewGroup viewGroup) {
        try {
            return getLayoutInflater().inflate(i2, viewGroup);
        } catch (Throwable unused) {
            GlobalImageCache.getLruBitmapCache().clean();
            return getLayoutInflater().inflate(i2, viewGroup);
        }
    }

    public static boolean isBitmapCanUse(Bitmap bitmap) {
        return (bitmap == null || bitmap.isRecycled()) ? false : true;
    }

    public static Bitmap loadImageWithCache(GlobalImageCache.BitmapDigest bitmapDigest) {
        Bitmap bitmap = GlobalImageCache.getLruBitmapCache().get(bitmapDigest);
        if (bitmap == null || bitmap.isRecycled()) {
            return null;
        }
        return bitmap;
    }

    public static Drawable scaleDrawable(Drawable drawable, float f2, float f3) {
        try {
            Bitmap drawableToBitmap = drawableToBitmap(drawable);
            int width = drawableToBitmap.getWidth();
            int height = drawableToBitmap.getHeight();
            Matrix matrix = new Matrix();
            matrix.postScale(DPIUtil.getDefaultDisplay().getWidth() / f2, DPIUtil.getDefaultDisplay().getHeight() / f3);
            return new BitmapDrawable(Bitmap.createBitmap(drawableToBitmap, 0, 0, width, height, matrix, true));
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
            return drawable;
        }
    }

    public static Bitmap toRoundBitmap(Bitmap bitmap, int i2, int i3) {
        float f2;
        float f3;
        float f4;
        float f5;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i4 = width > height ? height : width;
        if (OKLog.D) {
            OKLog.d("ImageUile", "toRoundBitmap width-->> " + width);
            OKLog.d("ImageUile", "toRoundBitmap height-->> " + height);
            OKLog.d("ImageUile", "toRoundBitmap dw-->> " + i4);
            OKLog.d("ImageUile", "toRoundBitmap dh-->> " + i4);
        }
        if (i4 < width || i4 < height) {
            try {
                bitmap = Bitmap.createScaledBitmap(bitmap, i4, i4, true);
            } catch (Throwable unused) {
                GlobalImageCache.getLruBitmapCache().clean();
                try {
                    bitmap = Bitmap.createScaledBitmap(bitmap, i4, i4, true);
                } catch (Throwable th) {
                    if (OKLog.E) {
                        OKLog.e(TAG, " -->> ", th);
                    }
                }
            }
        }
        int i5 = width > i4 ? i4 : width;
        if (height <= i4) {
            i4 = height;
        }
        if (width <= height) {
            f2 = i5 / 2;
            f4 = i5;
            f5 = f4;
            f3 = 0.0f;
        } else {
            f2 = i4 / 2;
            f3 = (i5 - i4) / 2;
            f4 = i5 - f3;
            f5 = i4;
        }
        Bitmap createBitmap = Bitmap.createBitmap(i5, i4, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect((int) f3, (int) 0.0f, (int) f4, (int) f5);
        Rect rect2 = new Rect((int) 0.0f, (int) 0.0f, (int) f5, (int) f5);
        RectF rectF = new RectF(rect2);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawRoundRect(rectF, f2, f2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect2, paint);
        return createBitmap;
    }

    public static Bitmap toRoundCorner(Bitmap bitmap, int i2) {
        if (OKLog.D) {
            OKLog.d(ImageUtil.class.getName(), "toRoundCorner() dp -->> " + i2);
        }
        float dip2px = DPIUtil.dip2px(i2);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawRoundRect(rectF, dip2px, dip2px, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        bitmap.recycle();
        if (OKLog.D) {
            OKLog.d(ImageUtil.class.getName(), "toRoundCorner() bitmap -->> " + createBitmap);
        }
        return createBitmap;
    }

    public static Drawable zoomDrawable(Drawable drawable) {
        Bitmap drawableToBitmap = drawableToBitmap(drawable);
        int width = drawableToBitmap.getWidth();
        double height = drawableToBitmap.getHeight();
        Double.isNaN(height);
        return new BitmapDrawable(Bitmap.createBitmap(drawableToBitmap, 0, 0, width, (int) (height * 0.65d)));
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float f2) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawRoundRect(rectF, f2, f2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return createBitmap;
    }

    public static View inflate(Context context, int i2, ViewGroup viewGroup) {
        try {
            return getLayoutInflater(context).inflate(i2, viewGroup);
        } catch (Throwable unused) {
            GlobalImageCache.getLruBitmapCache().clean();
            return getLayoutInflater(context).inflate(i2, viewGroup);
        }
    }

    private static LayoutInflater getLayoutInflater(Context context) {
        return (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public static View inflate(int i2, ViewGroup viewGroup, boolean z) {
        try {
            return getLayoutInflater().inflate(i2, viewGroup, z);
        } catch (Throwable unused) {
            GlobalImageCache.getLruBitmapCache().clean();
            return getLayoutInflater().inflate(i2, viewGroup, z);
        }
    }

    public static Bitmap createBitmap(InputWay inputWay, int i2, int i3) {
        Bitmap bitmap;
        if (OKLog.D) {
            OKLog.d(ImageUtil.class.getName(), "createBitmap() width=" + i2 + " height=" + i3 + " -->> ");
        }
        if (i2 > DPIUtil.dip2px(666.0f)) {
            i2 = DPIUtil.dip2px(666.0f);
        }
        if (i3 > DPIUtil.dip2px(666.0f)) {
            i3 = DPIUtil.dip2px(666.0f);
        }
        if (i2 == 0 && i3 == 0) {
            i2 = DPIUtil.dip2px(666.0f);
            i3 = DPIUtil.dip2px(666.0f);
        }
        BitmapUtil bitmapUtil = new BitmapUtil();
        Bitmap bitmap2 = null;
        for (int i4 = 0; i4 < 2; i4++) {
            if (inputWay.getResourceId() != 0) {
                bitmap2 = bitmapUtil.decodeResourceBitmapAndScale(JdSdk.getInstance().getApplication(), i2, i3, inputWay.getResourceId(), false);
            } else if (inputWay.getFile() != null) {
                bitmap2 = bitmapUtil.decodeFileAndScale(inputWay.getFile(), i2, i3, false);
            } else if (inputWay.getInputStream() == null && inputWay.getByteArray() != null) {
                try {
                    bitmap = BitmapFactory.decodeByteArray(inputWay.getByteArray(), 0, inputWay.getByteArray().length);
                } catch (Throwable unused) {
                    bitmap = null;
                }
                bitmap2 = bitmap == null ? null : bitmapUtil.scaleBitmap(bitmap, i2, i3, false);
            }
            if (bitmap2 != null) {
                break;
            }
            GlobalImageCache.getLruBitmapCache().clean();
        }
        if (OKLog.D && bitmap2 != null) {
            OKLog.d(ImageUtil.class.getName(), "createBitmap() return width=" + bitmap2.getWidth() + " height=" + bitmap2.getHeight() + " -->> ");
        }
        return bitmap2;
    }

    public static View inflate(Context context, int i2, ViewGroup viewGroup, boolean z) {
        try {
            return getLayoutInflater(context).inflate(i2, viewGroup, z);
        } catch (Throwable unused) {
            GlobalImageCache.getLruBitmapCache().clean();
            return getLayoutInflater(context).inflate(i2, viewGroup, z);
        }
    }

    public static Bitmap toRoundBitmap(Bitmap bitmap) {
        float f2;
        float f3;
        float f4;
        float f5;
        try {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            if (width <= height) {
                f5 = width / 2;
                f4 = width;
                f3 = f4;
                f2 = 0.0f;
            } else {
                f2 = (width - height) / 2;
                f3 = height;
                f4 = width - f2;
                width = height;
                f5 = height / 2;
            }
            Bitmap createBitmap = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            Paint paint = new Paint();
            Rect rect = new Rect((int) f2, (int) 0.0f, (int) f4, (int) f3);
            Rect rect2 = new Rect((int) 0.0f, (int) 0.0f, (int) f3, (int) f3);
            RectF rectF = new RectF(rect2);
            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(-12434878);
            canvas.drawRoundRect(rectF, f5, f5, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect, rect2, paint);
            paint.setColor(-1140850689);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(DPIUtil.dip2px(4.0f));
            canvas.drawCircle((rectF.right - rectF.left) / 2.0f, (rectF.bottom - rectF.top) / 2.0f, f5, paint);
            return createBitmap;
        } catch (Throwable th) {
            OKLog.e(TAG, th);
            return bitmap;
        }
    }
}
