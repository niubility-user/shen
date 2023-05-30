package com.wangyin.platform;

import android.content.Context;
import java.security.Signature;

/* loaded from: classes11.dex */
public class NativeCryptoUtils {
    private final Context context;

    public NativeCryptoUtils(Context context) {
        this.context = context;
    }

    public static native byte[] GetUserIDByPayCode_16(byte[] bArr, byte[] bArr2);

    public static native byte[] NativeAppendChar(long j2, int i2, String str, int i3);

    public static native byte[] NativeCheckPwdEqual(long j2, long j3);

    public static native int NativeCheckRegexMatch(long j2, String str, String str2);

    public static native int NativeCommunicationSelfTest_gm();

    public static native byte[] NativeCreateP10Request(String str, String str2, String str3, int i2);

    public static native byte[] NativeCreateP10SmRequest(String str, String str2, String str3, int i2);

    public static native byte[] NativeDecodeDataFromServer(String str, String str2, String str3, int i2, String str4);

    public static native byte[] NativeDecodeDataFromServer_gm(String str, String str2, String str3, int i2, String str4);

    public static native byte[] NativeDeleteAllCertificate();

    public static native byte[] NativeDeleteAllChar(long j2);

    public static native byte[] NativeDeleteCertificate(String str);

    public static native byte[] NativeDeleteCertificateSm(String str);

    public static native byte[] NativeDeleteChar(long j2, int i2, int i3);

    public static native byte[] NativeEncodeDataToServer(String str, long j2, String str2, String str3, String str4, String str5, String str6, int i2);

    public static native byte[] NativeEncodeDataToServer_gm(String str, long j2, String str2, String str3, String str4, String str5, String str6, int i2);

    public static native FidoSecCheckResult NativeFidoSecCheck(Context context, String str, Signature signature, String str2, byte[] bArr, String str3, byte[] bArr2) throws FidoSecCheckException;

    public static native byte[] NativeFileCrypto(Context context, String str, String str2, int i2, int i3, String str3, String str4);

    public static native byte[] NativeGenPayCode(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, long j2);

    public static native byte[] NativeGenPayCodeJDJR(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, long j2);

    public static native byte[] NativeGenPayCode_16(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, long j2);

    public static native byte[] NativeGenPayCode_ChinaUnionPay_15(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, long j2);

    public static native byte[] NativeGenerateOTP(byte[] bArr, byte[] bArr2, long j2, int i2);

    public static native byte[] NativeGetCertSerialNumber(byte[] bArr);

    public static native byte[] NativeGetCryptoInputData(long j2, byte[] bArr);

    public static native byte[] NativeGetCryptoInputDataDegrade(long j2, byte[] bArr);

    public static native byte[] NativeGetDeviceGUID();

    public static native char NativeGetHandshakeStatus();

    public static native char NativeGetHandshakeStatus_gm();

    public static native int NativeGetInputDataLen(long j2);

    public static native String NativeGetLibVersion();

    public static native byte[] NativeGetSourceData(long j2, byte[] bArr, byte[] bArr2);

    public static native byte[] NativeGetTempInputData(long j2);

    public static native byte[] NativeGetUserIDByPayCode(byte[] bArr, byte[] bArr2);

    public static native byte[] NativeGetUserIDByPayCodeJDJR(byte[] bArr, byte[] bArr2);

    public static native byte[] NativeGetUserIDByPayCode_ChinaUnionPay_15(byte[] bArr, byte[] bArr2);

    public static native byte[] NativeImportCert(byte[] bArr, byte[] bArr2);

    public static native byte[] NativeImportSmCert(byte[] bArr, byte[] bArr2);

    public static native long NativeInitializeKeyBoardCrypto();

    public static native byte[] NativeIsCertExists(String str, int i2);

    public static native byte[] NativeIsCertExistsSm(String str, int i2);

    public static native byte[] NativeMobileCertInit(Context context);

    public static native byte[] NativeMobileCertSmInit(Context context, String str);

    public static native byte[] NativeP1Sign(byte[] bArr, byte[] bArr2);

    public static native byte[] NativeP7Envelop(String str, byte[] bArr);

    public static native byte[] NativeP7Envelope(String str, byte[] bArr);

    public static native byte[] NativeP7Sign(byte[] bArr, String str, byte[] bArr2);

    public static native byte[] NativeP7_SM_Envelope(String str, byte[] bArr);

    public static native int NativeSetCryptoAlgorithm(long j2, int i2);

    public static native void NativeSetHandshakeStatus(char c2);

    public static native void NativeSetHandshakeStatus_gm(char c2);

    public static native int NativeSetMD5Attach(long j2, int i2);

    public static native int NativeSetServerTime(long j2, long j3);

    public static native byte[] NativeSignP7AndEnvelopMsg(String str, String str2, String str3, byte[] bArr);

    public static native byte[] NativeSignP7AndEnvelopMsgSm(String str, String str2, String str3, byte[] bArr);

    public static native byte[] NativeSm3(String str);

    public static native byte[] NativeSm4Decrypt(String str, String str2);

    public static native byte[] NativeSm4Encrypt(String str, String str2);

    public static native void NativeStartAutoHandshake(String str, int i2, String str2);

    public static native void NativeStartAutoHandshake_gm(String str, int i2, String str2);

    public static native byte[] NativeSymmetricCrypto(Context context, String str, String str2, byte[] bArr, int i2, int i3);

    public static native byte[] NativeUPChkPayCode(String str, int i2, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, long j2);

    public static native byte[] NativeUPGenPayCode(long j2, int i2, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, long j3);

    public static native byte[] NativeUninitializeKeyBoardcrypto(long j2);

    public static native byte[] NativeVerifyP1SignMsg(byte[] bArr, int i2, byte[] bArr2, byte[] bArr3);

    public static native byte[] NativeVerifyP1SignMsgSm(byte[] bArr, int i2, byte[] bArr2, byte[] bArr3);

    public static native byte[] NativeWYP7Envelope(String str, byte[] bArr);

    public static native int NativeWsm4_init(byte[] bArr, byte[] bArr2);

    public static native int NativeWsm4_init_sig(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4);

    public static native byte[] Nativesm4_crypt_cbc_nopadding(int i2, int i3, byte[] bArr, int i4);

    public static native byte[] Nativesm4_crypt_cbc_padding(int i2, int i3, int i4, byte[] bArr, int i5);

    public static native byte[] Nativesm4_crypt_ecb_nopadding(int i2, int i3, byte[] bArr, int i4);

    public static native byte[] Nativesm4_crypt_ecb_padding(int i2, int i3, int i4, byte[] bArr, int i5);

    public static native byte[] verifyP7SignMsgSm(byte[] bArr);

    public static native byte[] verifySignMsg(byte[] bArr);

    public native byte[] NativeAddRootCert(String str);
}
