package g.f.a.b;

import android.content.Context;
import com.facebook.common.util.UriUtil;
import com.jd.dynamic.DYConstants;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes12.dex */
public class c {
    /* JADX WARN: Removed duplicated region for block: B:89:0x0110 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x011a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String a(String str, Context context) {
        RandomAccessFile randomAccessFile;
        InputStream inputStream;
        InputStream inputStream2 = null;
        if (str == null || str.equals("")) {
            return null;
        }
        if (new File(str).exists()) {
            return str;
        }
        try {
            Matcher matcher = Pattern.compile("(.*?)://(.*)").matcher(str);
            if (matcher.find()) {
                String group = matcher.group(1);
                String group2 = matcher.group(2);
                if (group.equalsIgnoreCase("file")) {
                    inputStream = new FileInputStream(group2);
                } else {
                    if (!group.equalsIgnoreCase(UriUtil.LOCAL_ASSET_SCHEME) && !group.equalsIgnoreCase(DYConstants.DY_ASSETS)) {
                        inputStream = group.equalsIgnoreCase(UriUtil.LOCAL_RESOURCE_SCHEME) ? context.getClass().getResourceAsStream(group2) : null;
                    }
                    String str2 = group2.startsWith("/") ? "" : "/";
                    inputStream = context.getClass().getResourceAsStream("/assets" + str2 + group2);
                }
                try {
                    if (inputStream != null) {
                        byte[] bArr = new byte[1024];
                        File file = new File(context.getFilesDir().getAbsolutePath() + group2);
                        RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "rw");
                        while (true) {
                            try {
                                int read = inputStream.read(bArr);
                                if (read <= 0) {
                                    break;
                                }
                                randomAccessFile2.write(bArr, 0, read);
                            } catch (Exception e2) {
                                randomAccessFile = randomAccessFile2;
                                e = e2;
                                try {
                                    e.printStackTrace();
                                    if (inputStream != null) {
                                        try {
                                            inputStream.close();
                                        } catch (Exception e3) {
                                            e3.printStackTrace();
                                        }
                                    }
                                    if (randomAccessFile != null) {
                                        try {
                                            randomAccessFile.close();
                                        } catch (IOException e4) {
                                            e4.printStackTrace();
                                        }
                                    }
                                    return null;
                                } catch (Throwable th) {
                                    th = th;
                                    inputStream2 = inputStream;
                                    if (inputStream2 != null) {
                                        try {
                                            inputStream2.close();
                                        } catch (Exception e5) {
                                            e5.printStackTrace();
                                        }
                                    }
                                    if (randomAccessFile != null) {
                                        try {
                                            randomAccessFile.close();
                                        } catch (IOException e6) {
                                            e6.printStackTrace();
                                        }
                                    }
                                    throw th;
                                }
                            } catch (Throwable th2) {
                                inputStream2 = inputStream;
                                randomAccessFile = randomAccessFile2;
                                th = th2;
                                if (inputStream2 != null) {
                                }
                                if (randomAccessFile != null) {
                                }
                                throw th;
                            }
                        }
                        String absolutePath = file.getAbsolutePath();
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (Exception e7) {
                                e7.printStackTrace();
                            }
                        }
                        try {
                            randomAccessFile2.close();
                        } catch (IOException e8) {
                            e8.printStackTrace();
                        }
                        return absolutePath;
                    }
                    throw new IOException("bad data source");
                } catch (Exception e9) {
                    e = e9;
                    randomAccessFile = null;
                } catch (Throwable th3) {
                    th = th3;
                    randomAccessFile = null;
                    inputStream2 = inputStream;
                    if (inputStream2 != null) {
                    }
                    if (randomAccessFile != null) {
                    }
                    throw th;
                }
            }
        } catch (Exception e10) {
            e = e10;
            randomAccessFile = null;
            inputStream = null;
        } catch (Throwable th4) {
            th = th4;
            randomAccessFile = null;
        }
        return null;
    }
}
