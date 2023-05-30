package com.android.volley.toolbox;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.error.ParseError;
import com.jd.framework.network.request.JDRequest;

/* loaded from: classes.dex */
public class ImageRequest extends Request<Bitmap> {
    public static final float DEFAULT_IMAGE_BACKOFF_MULT = 2.0f;
    public static final int DEFAULT_IMAGE_MAX_RETRIES = 2;
    public static final int DEFAULT_IMAGE_TIMEOUT_MS = 1000;
    private static final Object sDecodeLock = new Object();
    private final Bitmap.Config mDecodeConfig;
    private Response.Listener<Bitmap> mListener;
    private final int mMaxHeight;
    private final int mMaxWidth;
    private ImageView.ScaleType mScaleType;

    public ImageRequest(String str, Response.Listener<Bitmap> listener, int i2, int i3, ImageView.ScaleType scaleType, Bitmap.Config config, Response.ErrorListener errorListener) {
        super(0, str, errorListener);
        setRetryPolicy(new DefaultRetryPolicy(1000, 2, 2.0f));
        this.mListener = listener;
        this.mDecodeConfig = config;
        this.mMaxWidth = i2;
        this.mMaxHeight = i3;
        this.mScaleType = scaleType;
    }

    private Response<Bitmap> doParse(NetworkResponse networkResponse) {
        Bitmap decodeByteArray;
        byte[] bArr = networkResponse.data;
        BitmapFactory.Options options = new BitmapFactory.Options();
        if (this.mMaxWidth == 0 && this.mMaxHeight == 0) {
            options.inPreferredConfig = this.mDecodeConfig;
            decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        } else {
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            int i2 = options.outWidth;
            int i3 = options.outHeight;
            int resizedDimension = getResizedDimension(this.mMaxWidth, this.mMaxHeight, i2, i3, this.mScaleType);
            int resizedDimension2 = getResizedDimension(this.mMaxHeight, this.mMaxWidth, i3, i2, this.mScaleType);
            options.inJustDecodeBounds = false;
            options.inSampleSize = findBestSampleSize(i2, i3, resizedDimension, resizedDimension2);
            decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            if (decodeByteArray != null && (decodeByteArray.getWidth() > resizedDimension || decodeByteArray.getHeight() > resizedDimension2)) {
                Bitmap createScaledBitmap = Bitmap.createScaledBitmap(decodeByteArray, resizedDimension, resizedDimension2, true);
                decodeByteArray.recycle();
                decodeByteArray = createScaledBitmap;
            }
        }
        if (decodeByteArray == null) {
            return Response.error(new ParseError(networkResponse));
        }
        return Response.success(networkResponse.statusCode, decodeByteArray, HttpHeaderParser.parseCacheHeaders(this, networkResponse));
    }

    static int findBestSampleSize(int i2, int i3, int i4, int i5) {
        double d = i2;
        double d2 = i4;
        Double.isNaN(d);
        Double.isNaN(d2);
        double d3 = i3;
        double d4 = i5;
        Double.isNaN(d3);
        Double.isNaN(d4);
        double min = Math.min(d / d2, d3 / d4);
        float f2 = 1.0f;
        while (true) {
            float f3 = 2.0f * f2;
            if (f3 > min) {
                return (int) f2;
            }
            f2 = f3;
        }
    }

    private static int getResizedDimension(int i2, int i3, int i4, int i5, ImageView.ScaleType scaleType) {
        if (i2 == 0 && i3 == 0) {
            return i4;
        }
        if (scaleType == ImageView.ScaleType.FIT_XY) {
            return i2 == 0 ? i4 : i2;
        } else if (i2 == 0) {
            double d = i3;
            double d2 = i5;
            Double.isNaN(d);
            Double.isNaN(d2);
            double d3 = i4;
            Double.isNaN(d3);
            return (int) (d3 * (d / d2));
        } else if (i3 == 0) {
            return i2;
        } else {
            double d4 = i5;
            double d5 = i4;
            Double.isNaN(d4);
            Double.isNaN(d5);
            double d6 = d4 / d5;
            if (scaleType == ImageView.ScaleType.CENTER_CROP) {
                double d7 = i2;
                Double.isNaN(d7);
                double d8 = i3;
                if (d7 * d6 < d8) {
                    Double.isNaN(d8);
                    return (int) (d8 / d6);
                }
                return i2;
            }
            double d9 = i2;
            Double.isNaN(d9);
            double d10 = i3;
            if (d9 * d6 > d10) {
                Double.isNaN(d10);
                return (int) (d10 / d6);
            }
            return i2;
        }
    }

    @Override // com.android.volley.Request
    public void deliverResponse(Response<Bitmap> response) {
        this.mListener.onResponse(response);
    }

    @Override // com.android.volley.Request
    public JDRequest.Priority getPriority() {
        return JDRequest.Priority.LOW;
    }

    @Override // com.android.volley.Request
    public void onFinish() {
        super.onFinish();
        this.mListener = null;
    }

    @Override // com.android.volley.Request
    public Response<Bitmap> parseNetworkResponse(NetworkResponse networkResponse) {
        Response<Bitmap> doParse;
        synchronized (sDecodeLock) {
            try {
                try {
                    doParse = doParse(networkResponse);
                } catch (OutOfMemoryError e2) {
                    VolleyLog.e("Caught OOM for %d byte image, url=%s", Integer.valueOf(networkResponse.data.length), getUrl());
                    return Response.error(new ParseError(e2));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return doParse;
    }

    @Deprecated
    public ImageRequest(String str, Response.Listener<Bitmap> listener, int i2, int i3, Bitmap.Config config, Response.ErrorListener errorListener) {
        this(str, listener, i2, i3, ImageView.ScaleType.CENTER_INSIDE, config, errorListener);
    }
}
