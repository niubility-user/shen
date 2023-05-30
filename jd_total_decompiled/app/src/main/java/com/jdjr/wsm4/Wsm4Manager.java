package com.jdjr.wsm4;

import android.annotation.TargetApi;
import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.jdjr.tools.JDJRSecureUtils;
import com.wangyin.platform.CryptoUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/* loaded from: classes18.dex */
public class Wsm4Manager {
    public static int SM4_CBC = 1;
    public static int SM4_ECB = 0;
    public static int WBSMBOX_LEN = 269184;
    private static Wsm4Manager instance;
    private static final Object lock = new Object();
    private Context mContext;
    private CryptoUtils mCryptoUtils;
    byte[] mDeviceId;

    private Wsm4Manager(Context context) {
        this.mContext = context;
        this.mCryptoUtils = CryptoUtils.newInstance(context.getApplicationContext());
        wsm4init(context);
    }

    public static Wsm4Manager newInstance(Context context) {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new Wsm4Manager(context);
                }
            }
        }
        return instance;
    }

    /* JADX WARN: Removed duplicated region for block: B:177:0x0073 A[Catch: IOException -> 0x0077, all -> 0x0081, TRY_LEAVE, TryCatch #1 {all -> 0x0081, blocks: (B:163:0x005b, B:165:0x0060, B:175:0x006e, B:177:0x0073, B:181:0x0079, B:184:0x007d, B:188:0x0085), top: B:197:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:198:0x006e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private synchronized int setStaticWsmbox() {
        InputStream inputStream;
        FileOutputStream fileOutputStream;
        Throwable th;
        InputStream inputStream2 = null;
        try {
            try {
                String str = this.mContext.getFilesDir() + "/wbxstatic";
                if (new File(str).length() <= WBSMBOX_LEN) {
                    inputStream = this.mContext.getResources().getAssets().open("wbx");
                    try {
                        fileOutputStream = new FileOutputStream(str);
                    } catch (Exception unused) {
                        fileOutputStream = null;
                    } catch (Throwable th2) {
                        th = th2;
                        fileOutputStream = null;
                        th = th;
                        if (inputStream != null) {
                        }
                        if (fileOutputStream != null) {
                        }
                        throw th;
                    }
                    try {
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = inputStream.read(bArr);
                            if (read <= 0) {
                                break;
                            }
                            fileOutputStream.write(bArr, 0, read);
                        }
                        inputStream2 = inputStream;
                    } catch (Exception unused2) {
                        inputStream2 = inputStream;
                        if (inputStream2 != null) {
                            try {
                                inputStream2.close();
                            } catch (IOException unused3) {
                                return -1;
                            }
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        return -1;
                    } catch (Throwable th3) {
                        th = th3;
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException unused4) {
                                return -1;
                            }
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        throw th;
                    }
                } else {
                    fileOutputStream = null;
                }
                if (inputStream2 != null) {
                    try {
                        inputStream2.close();
                    } catch (IOException unused5) {
                        return -1;
                    }
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                return 0;
            } catch (Exception unused6) {
                fileOutputStream = null;
            } catch (Throwable th4) {
                th = th4;
                inputStream = null;
                fileOutputStream = null;
            }
        } catch (Throwable th5) {
            throw th5;
        }
    }

    private void wsm4init(Context context) {
        try {
            File file = new File(context.getFilesDir(), "wbx");
            File file2 = new File(context.getFilesDir(), "wbxstatic");
            if (!file.exists()) {
                file.createNewFile();
            }
            this.mCryptoUtils.Wsm4_init(file.getAbsolutePath().getBytes("UTF-8"), file2.getAbsolutePath().getBytes("UTF-8"));
            setStaticWsmbox();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Deprecated
    public byte[] wsm4CryptData(int i2, int i3, int i4, byte[] bArr) throws Exception {
        byte[] wsm4_crypt_ecb_padding;
        CryptoUtils cryptoUtils = this.mCryptoUtils;
        if (cryptoUtils != null && i2 >= 0 && i2 <= 4 && ((i3 == 0 || i3 == 1) && ((i4 == SM4_CBC || i4 == SM4_ECB) && bArr != null))) {
            if (i2 == Wsm4PaddingType.NO_PADDING) {
                if (i4 == SM4_CBC) {
                    wsm4_crypt_ecb_padding = cryptoUtils.wsm4_crypt_cbc_nopadding(i3, bArr.length, bArr);
                } else {
                    if (i4 == SM4_ECB) {
                        wsm4_crypt_ecb_padding = cryptoUtils.wsm4_crypt_ecb_nopadding(i3, bArr.length, bArr);
                    }
                    wsm4_crypt_ecb_padding = null;
                }
            } else if (i4 == SM4_CBC) {
                wsm4_crypt_ecb_padding = cryptoUtils.wsm4_crypt_cbc_padding(i2, i3, bArr.length, bArr);
            } else {
                if (i4 == SM4_ECB) {
                    wsm4_crypt_ecb_padding = cryptoUtils.wsm4_crypt_ecb_padding(i2, i3, bArr.length, bArr);
                }
                wsm4_crypt_ecb_padding = null;
            }
            String str = new String(JDJRSecureUtils.getErrorCode(wsm4_crypt_ecb_padding), "UTF-8");
            byte[] retData = JDJRSecureUtils.getRetData(wsm4_crypt_ecb_padding);
            if (str.equals("00000")) {
                return retData;
            }
            throw new Exception("wsm4CryptData ERROR: " + str);
        }
        throw new Exception("wsm4CryptData ERROR: PARAMETERERROR");
    }

    @TargetApi(8)
    public String wsm4Decrypt(String str) throws Wsm4Exception {
        String str2;
        if (!TextUtils.isEmpty(str)) {
            byte[] decode = Base64.decode(str, 2);
            byte[] wsm4_crypt_cbc_padding = this.mCryptoUtils.wsm4_crypt_cbc_padding(Wsm4PaddingType.PADDING_PKCS5, 0, decode.length, decode);
            try {
                str2 = new String(JDJRSecureUtils.getErrorCode(wsm4_crypt_cbc_padding), "UTF-8");
            } catch (UnsupportedEncodingException unused) {
                str2 = null;
            }
            byte[] retData = JDJRSecureUtils.getRetData(wsm4_crypt_cbc_padding);
            if ("00000".equals(str2)) {
                return new String(retData);
            }
            throw new Wsm4Exception("error_code:" + str2);
        }
        throw new Wsm4Exception("PARAMETER ERROR");
    }

    @TargetApi(8)
    public String wsm4Encrypt(String str) throws Wsm4Exception {
        String str2;
        if (!TextUtils.isEmpty(str)) {
            byte[] wsm4_crypt_cbc_padding = this.mCryptoUtils.wsm4_crypt_cbc_padding(Wsm4PaddingType.PADDING_PKCS5, 1, str.getBytes().length, str.getBytes());
            try {
                str2 = new String(JDJRSecureUtils.getErrorCode(wsm4_crypt_cbc_padding), "UTF-8");
            } catch (UnsupportedEncodingException unused) {
                str2 = null;
            }
            byte[] retData = JDJRSecureUtils.getRetData(wsm4_crypt_cbc_padding);
            if ("00000".equals(str2)) {
                return Base64.encodeToString(retData, 2);
            }
            throw new Wsm4Exception("error_code:" + str2);
        }
        throw new Wsm4Exception("PARAMETER ERROR");
    }
}
