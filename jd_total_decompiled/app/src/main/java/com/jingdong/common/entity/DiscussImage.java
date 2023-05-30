package com.jingdong.common.entity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.text.TextUtils;
import com.jingdong.common.utils.ExifUtil;
import com.jingdong.common.utils.JdWebViewFunctionUtil;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.jdsdk.utils.cache.GlobalImageCache;
import com.jingdong.sdk.oklog.OKLog;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes5.dex */
public class DiscussImage {
    static final long LIMIT_IMAGE_SIZE = 524288;
    private static final String TAG = "DiscussImage";
    private Uri path;
    private BitmapDrawable picture;
    public int rotate;

    public static DiscussImage createDiscussImage(Context context, Uri uri) {
        return createDiscussImage(context, uri, true);
    }

    private static int getFileSampleSize(String str) {
        if (TextUtils.isEmpty(str)) {
            return 4;
        }
        File file = new File(str);
        if (file.isFile() && file.exists()) {
            return (int) (file.length() / LIMIT_IMAGE_SIZE);
        }
        return 4;
    }

    public Uri getPath() {
        return this.path;
    }

    public BitmapDrawable getPicture() {
        return this.picture;
    }

    public void setPath(Uri uri) {
        this.path = uri;
    }

    public void setPicture(BitmapDrawable bitmapDrawable) {
        this.picture = bitmapDrawable;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0127 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0140 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:111:0x011b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0114 A[Catch: all -> 0x0157, TRY_LEAVE, TryCatch #11 {all -> 0x0157, blocks: (B:53:0x0110, B:55:0x0114, B:69:0x0135, B:71:0x0139), top: B:108:0x0061 }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0139 A[Catch: all -> 0x0157, TRY_LEAVE, TryCatch #11 {all -> 0x0157, blocks: (B:53:0x0110, B:55:0x0114, B:69:0x0135, B:71:0x0139), top: B:108:0x0061 }] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x014c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r13v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r13v13 */
    /* JADX WARN: Type inference failed for: r13v15 */
    /* JADX WARN: Type inference failed for: r13v16 */
    /* JADX WARN: Type inference failed for: r13v3 */
    /* JADX WARN: Type inference failed for: r1v0, types: [android.content.ContentResolver, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r3v0, types: [boolean, java.io.InputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static DiscussImage createDiscussImage(Context context, Uri uri, boolean z) {
        InputStream inputStream;
        InputStream inputStream2;
        if (OKLog.D && uri != null) {
            String str = TAG;
            OKLog.d(str, "uri path-->> " + uri.getPath());
            OKLog.d(str, "uri string-->> " + uri.toString());
        }
        if (uri == null) {
            return null;
        }
        ?? contentResolver = context.getContentResolver();
        BitmapFactory.Options options = new BitmapFactory.Options();
        String pathFromUri = JdWebViewFunctionUtil.getPathFromUri(context, uri);
        ?? isEmpty = TextUtils.isEmpty(pathFromUri);
        if (isEmpty != 0) {
            pathFromUri = uri.getPath();
        }
        options.inSampleSize = getFileSampleSize(pathFromUri);
        int readPictureDegree = ExifUtil.readPictureDegree(uri.getPath());
        try {
            try {
                inputStream2 = contentResolver.openInputStream(uri);
                try {
                    try {
                        if (z != 0) {
                            z = Bitmap.createScaledBitmap(BitmapFactory.decodeStream(inputStream2, null, options), DPIUtil.dip2px(100.0f), DPIUtil.dip2px(100.0f), false);
                        } else {
                            z = BitmapFactory.decodeStream(inputStream2, null, options);
                        }
                    } catch (Exception e2) {
                        e = e2;
                        inputStream = null;
                        if (OKLog.E) {
                            OKLog.e(TAG, e);
                        }
                        if (inputStream2 != null) {
                            try {
                                inputStream2.close();
                            } catch (IOException e3) {
                                OKLog.e(TAG, e3);
                            }
                        }
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e4) {
                                OKLog.e(TAG, e4);
                            }
                        }
                        return null;
                    } catch (Throwable th) {
                        th = th;
                        inputStream = null;
                        if (OKLog.E) {
                            OKLog.e(TAG, th);
                        }
                        if (inputStream2 != null) {
                            try {
                                inputStream2.close();
                            } catch (IOException e5) {
                                OKLog.e(TAG, e5);
                            }
                        }
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e6) {
                                OKLog.e(TAG, e6);
                            }
                        }
                        return null;
                    }
                } catch (Throwable unused) {
                    GlobalImageCache.getLruBitmapCache().cleanMost();
                    if (z != 0) {
                        z = Bitmap.createScaledBitmap(BitmapFactory.decodeStream(inputStream2, null, options), DPIUtil.dip2px(100.0f), DPIUtil.dip2px(100.0f), false);
                    } else {
                        z = BitmapFactory.decodeStream(inputStream2, null, options);
                    }
                }
                inputStream = contentResolver.openInputStream(uri);
                if (readPictureDegree != 0) {
                    try {
                        Matrix matrix = new Matrix();
                        matrix.setRotate(readPictureDegree);
                        try {
                            z = Bitmap.createBitmap(z, 0, 0, z.getWidth(), z.getHeight(), matrix, true);
                        } catch (Throwable unused2) {
                            GlobalImageCache.getLruBitmapCache().cleanMost();
                            z = Bitmap.createBitmap(z, 0, 0, z.getWidth(), z.getHeight(), matrix, true);
                        }
                    } catch (Exception e7) {
                        e = e7;
                        if (OKLog.E) {
                        }
                        if (inputStream2 != null) {
                        }
                        if (inputStream != null) {
                        }
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        if (OKLog.E) {
                        }
                        if (inputStream2 != null) {
                        }
                        if (inputStream != null) {
                        }
                        return null;
                    }
                }
                if (inputStream2 != null) {
                    try {
                        inputStream2.close();
                    } catch (IOException e8) {
                        OKLog.e(TAG, e8);
                    }
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e9) {
                        OKLog.e(TAG, e9);
                    }
                }
                DiscussImage discussImage = new DiscussImage();
                discussImage.setPicture(new BitmapDrawable(z));
                discussImage.setPath(uri);
                discussImage.rotate = readPictureDegree;
                return discussImage;
            } catch (Exception e10) {
                e = e10;
                inputStream = null;
                inputStream2 = null;
            } catch (Throwable th3) {
                th = th3;
                inputStream = null;
                inputStream2 = null;
            }
        } catch (Throwable th4) {
            if (isEmpty != 0) {
                try {
                    isEmpty.close();
                } catch (IOException e11) {
                    OKLog.e(TAG, e11);
                }
            }
            if (contentResolver != 0) {
                try {
                    contentResolver.close();
                } catch (IOException e12) {
                    OKLog.e(TAG, e12);
                }
            }
            throw th4;
        }
    }
}
