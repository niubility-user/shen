package com.jd.skin.lib.Utils;

import android.content.Context;
import android.text.TextUtils;
import com.jd.lib.un.utils.UnLog;
import com.jd.skin.lib.JDSkinSDK;
import com.jingdong.sdk.oklog.OKLog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes18.dex */
public class FileUtils {
    public static String creatFileName(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            return str.substring(str.lastIndexOf("/") + 1);
        } catch (Exception unused) {
            return null;
        }
    }

    public static String creatFilePath(String str) {
        try {
            Context context = JDSkinSDK.getInstance().getContext();
            if (context == null) {
                return null;
            }
            File file = new File(context.getFilesDir().getPath() + ConstancesUtils.SAVE_DIR + "/" + str);
            if (!file.exists()) {
                file.mkdirs();
            }
            return file.getAbsolutePath();
        } catch (Exception unused) {
            return null;
        }
    }

    public static String creatLocalFile(String str, String str2) {
        try {
            String creatFilePath = creatFilePath(str2);
            String creatFileName = creatFileName(str);
            if (creatFilePath != null && creatFileName != null && !"".equals(creatFileName)) {
                return creatFilePath + "/" + creatFileName;
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public static void deletePics(List<File> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        try {
            for (File file : list) {
                if (file.exists()) {
                    file.delete();
                }
            }
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
            }
        }
    }

    public static String getJson(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open(str), "utf-8"));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return sb.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r11v1, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r11v5 */
    public static synchronized String getMD5(String str) {
        FileInputStream fileInputStream;
        synchronized (FileUtils.class) {
            try {
                try {
                    MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
                    File file = new File((String) str);
                    if (file.exists() && file.isFile()) {
                        fileInputStream = new FileInputStream(file);
                        try {
                            messageDigest.update(fileInputStream.getChannel().map(FileChannel.MapMode.READ_ONLY, 0L, file.length()));
                            byte[] digest = messageDigest.digest();
                            StringBuilder sb = new StringBuilder(digest.length * 2);
                            for (byte b : digest) {
                                sb.append("0123456789abcdef".charAt((b >> 4) & 15));
                                sb.append("0123456789abcdef".charAt(b & 15));
                            }
                            String sb2 = sb.toString();
                            try {
                                fileInputStream.close();
                            } catch (Exception e2) {
                                if (OKLog.D) {
                                    e2.toString();
                                }
                            }
                            return sb2;
                        } catch (FileNotFoundException e3) {
                            e = e3;
                            UnLog.e("FileUtils", e.toString());
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (Exception e4) {
                                    if (OKLog.D) {
                                        e4.toString();
                                    }
                                }
                            }
                            return null;
                        } catch (IOException unused) {
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (Exception e5) {
                                    if (OKLog.D) {
                                        e5.toString();
                                    }
                                }
                            }
                            return null;
                        } catch (NoSuchAlgorithmException e6) {
                            e = e6;
                            UnLog.e("FileUtils", e.toString());
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (Exception e7) {
                                    if (OKLog.D) {
                                        e7.toString();
                                    }
                                }
                            }
                            return null;
                        }
                    }
                    return null;
                } catch (FileNotFoundException e8) {
                    e = e8;
                    fileInputStream = null;
                } catch (IOException unused2) {
                    fileInputStream = null;
                } catch (NoSuchAlgorithmException e9) {
                    e = e9;
                    fileInputStream = null;
                } catch (Throwable th) {
                    th = th;
                    str = 0;
                    if (str != 0) {
                        try {
                            str.close();
                        } catch (Exception e10) {
                            if (OKLog.D) {
                                e10.toString();
                            }
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    public static List<File> readLocalFile(String str) {
        try {
            Context context = JDSkinSDK.getInstance().getContext();
            if (context == null) {
                return null;
            }
            File file = new File(context.getFilesDir().getPath() + "/" + ConstancesUtils.SAVE_DIR + "/" + str);
            File[] listFiles = file.isDirectory() ? file.listFiles() : null;
            if (listFiles == null || listFiles.length <= 0) {
                return null;
            }
            return new ArrayList(Arrays.asList(listFiles));
        } catch (Exception unused) {
            return null;
        }
    }
}
