package com.meizu.cloud.pushsdk.e.i;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.NetworkOnMainThreadException;
import android.widget.ImageView;
import com.meizu.cloud.pushsdk.e.b.c;
import com.meizu.cloud.pushsdk.e.d.k;
import com.meizu.cloud.pushsdk.e.h.g;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

/* loaded from: classes14.dex */
public class b {
    public static int a(int i2, int i3, int i4, int i5) {
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

    private static int b(int i2, int i3, int i4, int i5, ImageView.ScaleType scaleType) {
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

    public static c<Bitmap> c(k kVar, int i2, int i3, Bitmap.Config config, ImageView.ScaleType scaleType) {
        Bitmap bitmap;
        byte[] bArr = new byte[0];
        try {
            bArr = g.b(kVar.a().g()).b();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        if (i2 == 0 && i3 == 0) {
            options.inPreferredConfig = config;
            bitmap = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        } else {
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            int i4 = options.outWidth;
            int i5 = options.outHeight;
            int b = b(i2, i3, i4, i5, scaleType);
            int b2 = b(i3, i2, i5, i4, scaleType);
            options.inJustDecodeBounds = false;
            options.inSampleSize = a(i4, i5, b, b2);
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            if (i(decodeByteArray, b, b2)) {
                bitmap = Bitmap.createScaledBitmap(decodeByteArray, b, b2, true);
                decodeByteArray.recycle();
            } else {
                bitmap = decodeByteArray;
            }
        }
        if (bitmap == null) {
            com.meizu.cloud.pushsdk.e.c.a aVar = new com.meizu.cloud.pushsdk.e.c.a(kVar);
            j(aVar);
            return c.a(aVar);
        }
        return c.b(bitmap);
    }

    public static com.meizu.cloud.pushsdk.e.c.a d(com.meizu.cloud.pushsdk.e.c.a aVar) {
        aVar.b("connectionError");
        aVar.a(0);
        aVar.a(aVar.getMessage());
        return aVar;
    }

    public static com.meizu.cloud.pushsdk.e.c.a e(com.meizu.cloud.pushsdk.e.c.a aVar, com.meizu.cloud.pushsdk.e.b.b bVar, int i2) {
        bVar.d(aVar);
        aVar.a(i2);
        aVar.b("responseFromServerError");
        return aVar;
    }

    public static com.meizu.cloud.pushsdk.e.c.a f(Exception exc) {
        com.meizu.cloud.pushsdk.e.c.a aVar = new com.meizu.cloud.pushsdk.e.c.a(exc);
        aVar.b((Build.VERSION.SDK_INT < 11 || !(exc instanceof NetworkOnMainThreadException)) ? "connectionError" : "networkOnMainThreadError");
        aVar.a(0);
        return aVar;
    }

    public static String g(String str) {
        String contentTypeFor = URLConnection.getFileNameMap().getContentTypeFor(str);
        return contentTypeFor == null ? "application/octet-stream" : contentTypeFor;
    }

    public static void h(k kVar, String str, String str2) throws IOException {
        InputStream inputStream;
        byte[] bArr = new byte[2048];
        FileOutputStream fileOutputStream = null;
        try {
            inputStream = kVar.a().f();
            try {
                File file = new File(str);
                if (!file.exists()) {
                    file.mkdirs();
                }
                FileOutputStream fileOutputStream2 = new FileOutputStream(new File(file, str2));
                while (true) {
                    try {
                        int read = inputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        fileOutputStream2.write(bArr, 0, read);
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
                fileOutputStream2.flush();
                try {
                    inputStream.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
                try {
                    fileOutputStream2.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            inputStream = null;
        }
    }

    private static boolean i(Bitmap bitmap, int i2, int i3) {
        if (bitmap == null) {
            return false;
        }
        return bitmap.getWidth() > i2 || bitmap.getHeight() > i3;
    }

    public static com.meizu.cloud.pushsdk.e.c.a j(com.meizu.cloud.pushsdk.e.c.a aVar) {
        aVar.a(0);
        aVar.b("parseError");
        aVar.a(aVar.getMessage());
        return aVar;
    }
}
