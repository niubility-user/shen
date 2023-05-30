package c.t.m.g;

import com.facebook.cache.disk.DefaultDiskStorage;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Locale;
import java.util.zip.GZIPOutputStream;

/* loaded from: classes.dex */
public class f4 {
    public static boolean a(File file, File file2, boolean z) {
        byte[] c2 = y0.a().c(2048);
        try {
            try {
                long length = file.length();
                File file3 = new File(file.getAbsolutePath() + DefaultDiskStorage.FileType.TEMP);
                GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(new FileOutputStream(file3));
                FileInputStream fileInputStream = new FileInputStream(file);
                while (true) {
                    int read = fileInputStream.read(c2);
                    if (read == -1) {
                        break;
                    }
                    gZIPOutputStream.write(c2, 0, read);
                }
                fileInputStream.close();
                gZIPOutputStream.close();
                if (z) {
                    file.delete();
                }
                file3.renameTo(file2);
                long length2 = file2.length();
                Locale locale = Locale.ENGLISH;
                Object[] objArr = new Object[5];
                objArr[0] = file.getName();
                objArr[1] = Long.valueOf(length);
                objArr[2] = file2.getName();
                objArr[3] = Long.valueOf(length2);
                double d = length2;
                double d2 = length;
                Double.isNaN(d);
                Double.isNaN(d2);
                objArr[4] = Double.valueOf(d / d2);
                String.format(locale, "compressFileGzip:%s,%d,%s,%d,%.2f", objArr);
                return true;
            } finally {
                y0.a().b(c2);
            }
        } catch (Throwable unused) {
            StringBuilder sb = new StringBuilder("compressFileGzip failed:");
            sb.append(file.getName());
            sb.append(DYConstants.DY_REGEX_COMMA);
            sb.append(file.length());
            sb.append(OrderISVUtil.MONEY_DECIMAL);
            return false;
        }
    }

    public static byte[] b(byte[] bArr) {
        l2.b(bArr);
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bArr.length);
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return byteArray;
        } catch (Throwable unused) {
            return e2.a;
        }
    }
}
