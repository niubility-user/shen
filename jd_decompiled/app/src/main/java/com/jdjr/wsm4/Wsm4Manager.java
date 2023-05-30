package com.jdjr.wsm4;

import android.annotation.TargetApi;
import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.jdjr.tools.JDJRSecureUtils;
import com.wangyin.platform.CryptoUtils;
import java.io.File;
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

    /* JADX WARN: Removed duplicated region for block: B:107:0x0073 A[Catch: IOException -> 0x0077, all -> 0x0081, TRY_LEAVE, TryCatch #1 {all -> 0x0081, blocks: (B:93:0x005b, B:95:0x0060, B:105:0x006e, B:107:0x0073, B:111:0x0079, B:114:0x007d, B:118:0x0085), top: B:127:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:128:0x006e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private synchronized int setStaticWsmbox() {
        /*
            r9 = this;
            monitor-enter(r9)
            r0 = 0
            r1 = -1
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L7a
            r2.<init>()     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L7a
            android.content.Context r3 = r9.mContext     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L7a
            java.io.File r3 = r3.getFilesDir()     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L7a
            r2.append(r3)     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L7a
            java.lang.String r3 = "/wbxstatic"
            r2.append(r3)     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L7a
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L7a
            java.io.File r3 = new java.io.File     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L7a
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L7a
            long r3 = r3.length()     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L7a
            int r5 = com.jdjr.wsm4.Wsm4Manager.WBSMBOX_LEN     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L7a
            long r5 = (long) r5     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L7a
            r7 = 0
            int r8 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r8 > 0) goto L58
            android.content.Context r3 = r9.mContext     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L7a
            android.content.res.Resources r3 = r3.getResources()     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L7a
            android.content.res.AssetManager r3 = r3.getAssets()     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L7a
            java.lang.String r4 = "wbx"
            java.io.InputStream r3 = r3.open(r4)     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L7a
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L55
            r4.<init>(r2)     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L55
            r0 = 1024(0x400, float:1.435E-42)
            byte[] r0 = new byte[r0]     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L56
        L44:
            int r2 = r3.read(r0)     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L56
            if (r2 <= 0) goto L4e
            r4.write(r0, r7, r2)     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L56
            goto L44
        L4e:
            r0 = r3
            goto L59
        L50:
            r0 = move-exception
            goto L6c
        L52:
            r2 = move-exception
            r4 = r0
            goto L6b
        L55:
            r4 = r0
        L56:
            r0 = r3
            goto L7b
        L58:
            r4 = r0
        L59:
            if (r0 == 0) goto L5e
            r0.close()     // Catch: java.io.IOException -> L64 java.lang.Throwable -> L81
        L5e:
            if (r4 == 0) goto L66
            r4.close()     // Catch: java.io.IOException -> L64 java.lang.Throwable -> L81
            goto L66
        L64:
            monitor-exit(r9)
            return r1
        L66:
            monitor-exit(r9)
            return r7
        L68:
            r2 = move-exception
            r3 = r0
            r4 = r3
        L6b:
            r0 = r2
        L6c:
            if (r3 == 0) goto L71
            r3.close()     // Catch: java.io.IOException -> L77 java.lang.Throwable -> L81
        L71:
            if (r4 == 0) goto L79
            r4.close()     // Catch: java.io.IOException -> L77 java.lang.Throwable -> L81
            goto L79
        L77:
            monitor-exit(r9)
            return r1
        L79:
            throw r0     // Catch: java.lang.Throwable -> L81
        L7a:
            r4 = r0
        L7b:
            if (r0 == 0) goto L83
            r0.close()     // Catch: java.lang.Throwable -> L81 java.io.IOException -> L8b
            goto L83
        L81:
            r0 = move-exception
            goto L89
        L83:
            if (r4 == 0) goto L8d
            r4.close()     // Catch: java.lang.Throwable -> L81 java.io.IOException -> L8b
            goto L8d
        L89:
            monitor-exit(r9)
            throw r0
        L8b:
            monitor-exit(r9)
            return r1
        L8d:
            monitor-exit(r9)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jdjr.wsm4.Wsm4Manager.setStaticWsmbox():int");
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
