package com.wangyin.platform;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.jdjr.tools.EnvConfig;
import com.jdjr.tools.EnvSwitchUtils;
import com.jdjr.tools.JDJRLog;
import com.jdjr.tools.JDJRSecureUtils;
import com.jdjr.wsm4.Wsm4Manager;
import java.io.UnsupportedEncodingException;
import kotlin.text.Typography;

/* loaded from: classes11.dex */
public class CryptoUtils {
    static final int SO_LOAD_FAILED = 22222;
    static final String SO_LOAD_FAILED_STR = "22222";
    private static final String TAG = "CryptoUtils";
    private static CryptoUtils instance = null;
    static boolean isLoadLibrary = true;
    private static final Object lock;
    private final Context context;
    private final NativeCryptoUtils nativeCryptoUtils;

    static {
        try {
            System.loadLibrary("WangyinCryptoLib");
        } catch (Throwable unused) {
            isLoadLibrary = false;
        }
        try {
            System.loadLibrary("AntiCheat");
        } catch (Throwable unused2) {
            isLoadLibrary = false;
        }
        lock = new Object();
    }

    public CryptoUtils(Context context) {
        this.context = context;
        this.nativeCryptoUtils = new NativeCryptoUtils(context);
        mobileCertInit(context);
        mobileCertSmInit(context, context.getFilesDir() + "/wbxstatic");
    }

    public static FidoSecCheckResult fidoSecCheck(Context context, String str, byte[] bArr) throws NativeLibNotLoadException, FidoSecCheckException, FidoSecEnvException {
        if (isLoadLibrary) {
            Wsm4Manager.newInstance(context);
            return NativeCryptoUtils.NativeFidoSecCheck(context, null, null, str, Base64.decode(FidoSecCheckCert.getEncKey(), 0), null, bArr);
        }
        throw new NativeLibNotLoadException();
    }

    private byte[] getCurrentBytes(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return str.getBytes("UTF-8");
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public static CryptoUtils newInstance(Context context) {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new CryptoUtils(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    public byte[] CheckPwdEqual(long j2, long j3) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeCheckPwdEqual(j2, j3);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public int CheckRegexMatch(long j2, String str, String str2) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeCheckRegexMatch(j2, str, str2);
        }
        return 22222;
    }

    public byte[] GenPayCode(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, long j2) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeGenPayCode(bArr, bArr2, bArr3, bArr4, j2);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public byte[] GenPayCode_16(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, long j2) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeGenPayCode_16(bArr, bArr2, bArr3, bArr4, j2);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public byte[] GenPayCode_ChinaUnionPay_15(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, long j2) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeGenPayCode_ChinaUnionPay_15(bArr, bArr2, bArr3, bArr4, j2);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public byte[] GenPayCode_JDJR_16(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, long j2) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeGenPayCodeJDJR(bArr, bArr2, bArr3, bArr4, j2);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public byte[] GetCertSerialNumber(byte[] bArr) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeGetCertSerialNumber(bArr);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public String GetLibVersion() {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeGetLibVersion();
        }
        return String.valueOf(22222);
    }

    public byte[] GetUserIDByPayCode(byte[] bArr, byte[] bArr2) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeGetUserIDByPayCode(bArr, bArr2);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public byte[] GetUserIDByPayCodeJDJR(byte[] bArr, byte[] bArr2) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeGetUserIDByPayCodeJDJR(bArr, bArr2);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public byte[] GetUserIDByPayCode_ChinaUnionPay_15(byte[] bArr, byte[] bArr2) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeGetUserIDByPayCode_ChinaUnionPay_15(bArr, bArr2);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public byte[] P1Sign(byte[] bArr, byte[] bArr2) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeP1Sign(bArr, bArr2);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public byte[] P7Sign(byte[] bArr, String str, byte[] bArr2) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeP7Sign(bArr, str, bArr2);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public int Wsm4_init(byte[] bArr, byte[] bArr2) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeWsm4_init(bArr, bArr2);
        }
        return 22222;
    }

    public byte[] addRootCert(String str) {
        if (isLoadLibrary) {
            return this.nativeCryptoUtils.NativeAddRootCert(str);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public byte[] appenChar(long j2, int i2, String str, int i3) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeAppendChar(j2, i2, str, i3);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public int communicationSelfTest_gm() {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeCommunicationSelfTest_gm();
        }
        return 22222;
    }

    public byte[] createP10Request(String str, String str2, String str3, int i2) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeCreateP10Request(str, str2, str3, i2);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public byte[] createP10RequestSm(String str, String str2, String str3, int i2) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeCreateP10SmRequest(str, str2, str3, i2);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public byte[] decodeDataFromServer(String str) {
        return decodeDataFromServer(str, null, null, 0, null);
    }

    public byte[] decodeDataFromServer_gm(String str) {
        return decodeDataFromServer_gm(str, null, null, 0, null);
    }

    public byte[] deleteAllCertificate() {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeDeleteAllCertificate();
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public byte[] deleteAllChar(long j2) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeDeleteAllChar(j2);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public byte[] deleteCertificate(String str) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeDeleteCertificate(str);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public byte[] deleteCertificateSm(String str) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeDeleteCertificateSm(str);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public byte[] deleteChar(long j2, int i2, int i3) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeDeleteChar(j2, i2, i3);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public byte[] encodeDataToServer(String str, long j2) {
        return encodeDataToServer(str, j2, null, null, null, null, null, 0);
    }

    public byte[] encodeDataToServer_gm(String str, long j2) {
        return encodeDataToServer_gm(str, j2, null, null, null, null, null, 0);
    }

    public byte[] fileCrypto(Context context, String str, String str2, int i2, int i3, String str3, String str4) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeFileCrypto(context, str, str2, i2, i3, str3, str4);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    /* JADX WARN: Code restructure failed: missing block: B:93:0x0065, code lost:
        r0 = "33333".getBytes();
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x0069, code lost:
        r8.close();
        r6.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x006f, code lost:
        if (r24 == null) goto L97;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0071, code lost:
        r24.close();
     */
    /* JADX WARN: Removed duplicated region for block: B:124:0x00aa A[Catch: Exception -> 0x00b2, TryCatch #9 {Exception -> 0x00b2, blocks: (B:122:0x00a5, B:124:0x00aa, B:126:0x00af), top: B:141:0x00a5 }] */
    /* JADX WARN: Removed duplicated region for block: B:126:0x00af A[Catch: Exception -> 0x00b2, TRY_LEAVE, TryCatch #9 {Exception -> 0x00b2, blocks: (B:122:0x00a5, B:124:0x00aa, B:126:0x00af), top: B:141:0x00a5 }] */
    /* JADX WARN: Removed duplicated region for block: B:141:0x00a5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public byte[] fileDecrypt(android.content.Context r19, java.lang.String r20, java.lang.String r21, int r22, java.lang.String r23, java.io.OutputStream r24) {
        /*
            r18 = this;
            r1 = r24
            java.lang.String r0 = "33333"
            boolean r2 = com.wangyin.platform.CryptoUtils.isLoadLibrary
            if (r2 == 0) goto Lb3
            r2 = 16400(0x4010, float:2.2981E-41)
            byte[] r3 = new byte[r2]
            r4 = 5
            byte[] r5 = new byte[r4]
            java.io.File r6 = new java.io.File
            r7 = r23
            r6.<init>(r7)
            r7 = 0
            java.io.FileInputStream r8 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8d
            r8.<init>(r6)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8d
            java.io.BufferedInputStream r6 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L87
            r6.<init>(r8)     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L87
        L21:
            int r7 = r6.read(r3)     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L88
            r9 = -1
            if (r7 == r9) goto L75
            r9 = 0
            if (r7 != r2) goto L2d
            r15 = r3
            goto L35
        L2d:
            if (r7 >= r2) goto L65
            byte[] r10 = new byte[r7]     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L88
            java.lang.System.arraycopy(r3, r9, r10, r9, r7)     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L88
            r15 = r10
        L35:
            r16 = 0
            r11 = r18
            r12 = r19
            r13 = r20
            r14 = r21
            r17 = r22
            byte[] r7 = r11.symmetricCrypto(r12, r13, r14, r15, r16, r17)     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L88
            java.lang.System.arraycopy(r7, r9, r5, r9, r4)     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L88
            java.lang.String r10 = new java.lang.String     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L88
            r10.<init>(r5)     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L88
            java.lang.String r11 = "00000"
            boolean r10 = r10.equals(r11)     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L88
            if (r10 == 0) goto L75
            int r10 = r7.length     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L88
            int r10 = r10 - r4
            byte[] r10 = new byte[r10]     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L88
            int r11 = r7.length     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L88
            int r11 = r11 - r4
            java.lang.System.arraycopy(r7, r4, r10, r9, r11)     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L88
            r1.write(r10)     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L88
            r24.flush()     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L88
            goto L21
        L65:
            byte[] r0 = r0.getBytes()     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L88
            r8.close()     // Catch: java.lang.Exception -> L74
            r6.close()     // Catch: java.lang.Exception -> L74
            if (r1 == 0) goto L74
            r24.close()     // Catch: java.lang.Exception -> L74
        L74:
            return r0
        L75:
            r8.close()     // Catch: java.lang.Exception -> L80
            r6.close()     // Catch: java.lang.Exception -> L80
            if (r1 == 0) goto L80
            r24.close()     // Catch: java.lang.Exception -> L80
        L80:
            return r5
        L81:
            r0 = move-exception
            goto L85
        L83:
            r0 = move-exception
            r6 = r7
        L85:
            r7 = r8
            goto La3
        L87:
            r6 = r7
        L88:
            r7 = r8
            goto L8e
        L8a:
            r0 = move-exception
            r6 = r7
            goto La3
        L8d:
            r6 = r7
        L8e:
            byte[] r0 = r0.getBytes()     // Catch: java.lang.Throwable -> La2
            if (r7 == 0) goto L97
            r7.close()     // Catch: java.lang.Exception -> La1
        L97:
            if (r6 == 0) goto L9c
            r6.close()     // Catch: java.lang.Exception -> La1
        L9c:
            if (r1 == 0) goto La1
            r24.close()     // Catch: java.lang.Exception -> La1
        La1:
            return r0
        La2:
            r0 = move-exception
        La3:
            if (r7 == 0) goto La8
            r7.close()     // Catch: java.lang.Exception -> Lb2
        La8:
            if (r6 == 0) goto Lad
            r6.close()     // Catch: java.lang.Exception -> Lb2
        Lad:
            if (r1 == 0) goto Lb2
            r24.close()     // Catch: java.lang.Exception -> Lb2
        Lb2:
            throw r0
        Lb3:
            java.lang.String r0 = "22222"
            byte[] r0 = r0.getBytes()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wangyin.platform.CryptoUtils.fileDecrypt(android.content.Context, java.lang.String, java.lang.String, int, java.lang.String, java.io.OutputStream):byte[]");
    }

    public byte[] getCryptoInputData(long j2, byte[] bArr) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeGetCryptoInputData(j2, bArr);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public byte[] getCryptoInputDataDegrade(long j2, byte[] bArr) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeGetCryptoInputDataDegrade(j2, bArr);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public byte[] getDeviceGUID() {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeGetDeviceGUID();
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public char getHandshakeStatus() {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeGetHandshakeStatus();
        }
        return '\u56ce';
    }

    public char getHandshakeStatus_gm() {
        return isLoadLibrary ? NativeCryptoUtils.NativeGetHandshakeStatus_gm() : Typography.quote;
    }

    public int getInputDataLen(long j2) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeGetInputDataLen(j2);
        }
        return 22222;
    }

    public String getSm3Data(String str) throws NativeCryptoException {
        if (isLoadLibrary) {
            if (str == null) {
                str = "";
            }
            byte[] NativeSm3 = NativeCryptoUtils.NativeSm3(str);
            byte[] errorCode = JDJRSecureUtils.getErrorCode(NativeSm3);
            if ("00000".equals(new String(errorCode))) {
                return Base64.encodeToString(JDJRSecureUtils.getRetData(NativeSm3), 2);
            }
            throw new NativeCryptoException(new String(errorCode));
        }
        return SO_LOAD_FAILED_STR;
    }

    public byte[] getSourceData(long j2, byte[] bArr, byte[] bArr2) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeGetSourceData(j2, bArr, bArr2);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public byte[] getTempInputData(long j2) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeGetTempInputData(j2);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public byte[] importCert(byte[] bArr, byte[] bArr2) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeImportCert(bArr, bArr2);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public byte[] importSmCert(byte[] bArr, byte[] bArr2) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeImportSmCert(bArr, bArr2);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public long initializeKeyBoardCrypto() {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeInitializeKeyBoardCrypto();
        }
        return 22222L;
    }

    public byte[] isCertExists(String str, int i2) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeIsCertExists(str, i2);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public byte[] isCertExistsSm(String str, int i2) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeIsCertExistsSm(str, i2);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public byte[] mobileCertInit(Context context) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeMobileCertInit(context);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public byte[] mobileCertSmInit(Context context, String str) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeMobileCertSmInit(context, str);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public byte[] p7Envelope(String str, byte[] bArr) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeP7Envelope(str, bArr);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public String p7SMEnvelope(String str, String str2) throws NativeCryptoException {
        if (isLoadLibrary) {
            if (str == null) {
                str = "";
            }
            byte[] NativeP7_SM_Envelope = NativeCryptoUtils.NativeP7_SM_Envelope(str, str2 != null ? str2.getBytes() : "".getBytes());
            byte[] errorCode = JDJRSecureUtils.getErrorCode(NativeP7_SM_Envelope);
            if ("00000".equals(new String(errorCode))) {
                return Base64.encodeToString(JDJRSecureUtils.getRetData(NativeP7_SM_Envelope), 2);
            }
            throw new NativeCryptoException(new String(errorCode));
        }
        return SO_LOAD_FAILED_STR;
    }

    public int setCryptoAlgorithm(long j2, int i2) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeSetCryptoAlgorithm(j2, i2);
        }
        return 22222;
    }

    public int setHandshakeStatus(char c2) {
        if (isLoadLibrary) {
            NativeCryptoUtils.NativeSetHandshakeStatus(c2);
            return 1;
        }
        return 22222;
    }

    public void setHandshakeStatus_gm(char c2) {
        if (isLoadLibrary) {
            NativeCryptoUtils.NativeSetHandshakeStatus_gm(c2);
        }
    }

    public int setMD5Attach(long j2, int i2) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeSetMD5Attach(j2, i2);
        }
        return 22222;
    }

    public int setServerTime(long j2, long j3) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeSetServerTime(j2, j3);
        }
        return 22222;
    }

    public byte[] signP7AndEnvelopMsg(String str, String str2, String str3, byte[] bArr) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeSignP7AndEnvelopMsg(str, str2, str3, bArr);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public byte[] signP7AndEnvelopMsgSm(String str, String str2, String str3, byte[] bArr) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeSignP7AndEnvelopMsgSm(str, str2, str3, bArr);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public String sm4Decrypt(String str, String str2) throws NativeCryptoException {
        if (isLoadLibrary) {
            if (str == null) {
                str = "";
            }
            if (str2 == null) {
                str2 = "";
            }
            byte[] NativeSm4Decrypt = NativeCryptoUtils.NativeSm4Decrypt(str, str2);
            byte[] errorCode = JDJRSecureUtils.getErrorCode(NativeSm4Decrypt);
            if ("00000".equals(new String(errorCode))) {
                return new String(JDJRSecureUtils.getRetData(NativeSm4Decrypt));
            }
            throw new NativeCryptoException(new String(errorCode));
        }
        return SO_LOAD_FAILED_STR;
    }

    public String sm4Encrypt(String str, String str2) throws NativeCryptoException {
        if (isLoadLibrary) {
            if (str == null) {
                str = "";
            }
            if (str2 == null) {
                str2 = "";
            }
            byte[] NativeSm4Encrypt = NativeCryptoUtils.NativeSm4Encrypt(str, str2);
            byte[] errorCode = JDJRSecureUtils.getErrorCode(NativeSm4Encrypt);
            if ("00000".equals(new String(errorCode))) {
                return new String(JDJRSecureUtils.getRetData(NativeSm4Encrypt));
            }
            throw new NativeCryptoException(new String(errorCode));
        }
        return SO_LOAD_FAILED_STR;
    }

    public void startAutoHandshake() {
        startAutoHandshake(null, 0, null);
    }

    public void startAutoHandshake_gm(String str, int i2, String str2) {
        if (isLoadLibrary) {
            if (str == null || str.equals("")) {
                str = EnvConfig.getSecurityCommunicationIpGm(this.context);
                i2 = EnvConfig.getSecurityCommunicationPortGm(this.context);
                str2 = EnvConfig.getSecurityCommunicationCertGm(this.context);
            }
            NativeCryptoUtils.NativeStartAutoHandshake_gm(str, i2, str2);
        }
    }

    public byte[] symmetricCrypto(Context context, String str, String str2, byte[] bArr, int i2, int i3) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeSymmetricCrypto(context, str, str2, bArr, i2, i3);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public byte[] uPChkPayCode(String str, int i2, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, long j2) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeUPChkPayCode(str, i2, bArr, bArr2, bArr3, bArr4, j2);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public byte[] uninitializeKeyBoardcrypto(long j2) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeUninitializeKeyBoardcrypto(j2);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public byte[] upGenPayCode(long j2, int i2, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, long j3) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeUPGenPayCode(j2, i2, bArr, bArr2, bArr3, bArr4, j3);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public byte[] verifyP1SignMsg(byte[] bArr, int i2, byte[] bArr2, byte[] bArr3) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeVerifyP1SignMsg(bArr, i2, bArr2, bArr3);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public byte[] verifyP1SignMsgSm(byte[] bArr, int i2, byte[] bArr2, byte[] bArr3) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeVerifyP1SignMsgSm(bArr, i2, bArr2, bArr3);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public byte[] verifyP7SignMsgSm(byte[] bArr) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.verifyP7SignMsgSm(bArr);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public byte[] verifySignMsg(byte[] bArr) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.verifySignMsg(bArr);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public byte[] wsm4_crypt_cbc_nopadding(int i2, int i3, byte[] bArr) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.Nativesm4_crypt_cbc_nopadding(i2, i3, bArr, 0);
        }
        return getCurrentBytes(SO_LOAD_FAILED_STR);
    }

    public byte[] wsm4_crypt_cbc_padding(int i2, int i3, int i4, byte[] bArr) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.Nativesm4_crypt_cbc_padding(i2, i3, i4, bArr, 0);
        }
        return getCurrentBytes(SO_LOAD_FAILED_STR);
    }

    public byte[] wsm4_crypt_ecb_nopadding(int i2, int i3, byte[] bArr) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.Nativesm4_crypt_ecb_nopadding(i2, i3, bArr, 0);
        }
        return getCurrentBytes(SO_LOAD_FAILED_STR);
    }

    public byte[] wsm4_crypt_ecb_padding(int i2, int i3, int i4, byte[] bArr) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.Nativesm4_crypt_ecb_padding(i2, i3, i4, bArr, 0);
        }
        return getCurrentBytes(SO_LOAD_FAILED_STR);
    }

    public byte[] wyP7Envelope(String str, byte[] bArr) {
        if (isLoadLibrary) {
            return NativeCryptoUtils.NativeWYP7Envelope(str, bArr);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public byte[] decodeDataFromServer(String str, String str2, String str3, int i2, String str4) {
        if (isLoadLibrary) {
            if ((str3 == null || str3.equals("")) && (str3 == null || str3.equals(""))) {
                str3 = EnvConfig.getSecurityCommunicationIp(this.context);
                i2 = EnvConfig.getSecurityCommunicationPort(this.context);
                str4 = EnvConfig.getSecurityCommunicationCert(this.context);
            }
            byte[] NativeDecodeDataFromServer = NativeCryptoUtils.NativeDecodeDataFromServer(str, str2, str3, i2, str4);
            if (EnvSwitchUtils.isTestEnvironment()) {
                JDJRLog.d(TAG, "decodeDataFromServer:" + new String(JDJRSecureUtils.getErrorCode(NativeDecodeDataFromServer)));
            }
            return NativeDecodeDataFromServer;
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public byte[] decodeDataFromServer_gm(String str, String str2, String str3, int i2, String str4) {
        if (isLoadLibrary) {
            if (str3 == null || str3.equals("")) {
                str3 = EnvConfig.getSecurityCommunicationIpGm(this.context);
                i2 = EnvConfig.getSecurityCommunicationPortGm(this.context);
                str4 = EnvConfig.getSecurityCommunicationCertGm(this.context);
            }
            byte[] NativeDecodeDataFromServer_gm = NativeCryptoUtils.NativeDecodeDataFromServer_gm(str, str2, str3, i2, str4);
            if (EnvSwitchUtils.isTestEnvironment()) {
                JDJRLog.d(TAG, "decodeDataFromServer_gm:" + new String(JDJRSecureUtils.getErrorCode(NativeDecodeDataFromServer_gm)));
            }
            return NativeDecodeDataFromServer_gm;
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public byte[] encodeDataToServer(String str, long j2, String str2, String str3, String str4, String str5, String str6, int i2) {
        String securityCommunicationIp;
        int securityCommunicationPort;
        String securityCommunicationCert;
        if (isLoadLibrary) {
            if ((str6 == null || str6.equals("")) && (str6 == null || str6.equals(""))) {
                securityCommunicationIp = EnvConfig.getSecurityCommunicationIp(this.context);
                securityCommunicationPort = EnvConfig.getSecurityCommunicationPort(this.context);
                securityCommunicationCert = EnvConfig.getSecurityCommunicationCert(this.context);
            } else {
                securityCommunicationCert = str5;
                securityCommunicationPort = i2;
                securityCommunicationIp = str6;
            }
            byte[] NativeEncodeDataToServer = NativeCryptoUtils.NativeEncodeDataToServer(str, j2, str2, str3, str4, securityCommunicationCert, securityCommunicationIp, securityCommunicationPort);
            if (EnvSwitchUtils.isTestEnvironment()) {
                JDJRLog.d(TAG, "encodeDataToServer:" + new String(JDJRSecureUtils.getErrorCode(NativeEncodeDataToServer)));
            }
            return NativeEncodeDataToServer;
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public byte[] encodeDataToServer_gm(String str, long j2, String str2, String str3, String str4, String str5, String str6, int i2) {
        String securityCommunicationIpGm;
        int securityCommunicationPortGm;
        String securityCommunicationCertGm;
        if (isLoadLibrary) {
            if (str6 == null || str6.equals("")) {
                securityCommunicationIpGm = EnvConfig.getSecurityCommunicationIpGm(this.context);
                securityCommunicationPortGm = EnvConfig.getSecurityCommunicationPortGm(this.context);
                securityCommunicationCertGm = EnvConfig.getSecurityCommunicationCertGm(this.context);
            } else {
                securityCommunicationCertGm = str5;
                securityCommunicationPortGm = i2;
                securityCommunicationIpGm = str6;
            }
            byte[] NativeEncodeDataToServer_gm = NativeCryptoUtils.NativeEncodeDataToServer_gm(str, j2, str2, str3, str4, securityCommunicationCertGm, securityCommunicationIpGm, securityCommunicationPortGm);
            if (EnvSwitchUtils.isTestEnvironment()) {
                JDJRLog.d(TAG, "encodeDataToServer_gm:" + new String(JDJRSecureUtils.getErrorCode(NativeEncodeDataToServer_gm)));
            }
            return NativeEncodeDataToServer_gm;
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public void startAutoHandshake(String str, int i2, String str2) {
        if (isLoadLibrary) {
            if (str == null || str.equals("")) {
                str = EnvConfig.getSecurityCommunicationIp(this.context);
                i2 = EnvConfig.getSecurityCommunicationPort(this.context);
                str2 = EnvConfig.getSecurityCommunicationCert(this.context);
            }
            NativeCryptoUtils.NativeStartAutoHandshake(str, i2, str2);
        }
    }

    public void startAutoHandshake_gm() {
        startAutoHandshake_gm(null, 0, null);
    }
}
