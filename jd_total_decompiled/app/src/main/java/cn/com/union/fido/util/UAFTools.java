package cn.com.union.fido.util;

import android.content.Context;
import android.os.Build;
import android.util.Base64;
import cn.com.union.fido.bean.Version;
import cn.com.union.fido.bean.authenticator.RawKeyHandle;
import cn.com.union.fido.bean.uafclient.FinalChallengeParams;
import cn.com.union.fido.bean.uafclient.tls.ChannelBinding;
import cn.com.union.fido.common.GlobalConfiguration;
import com.jdjr.risk.jdcn.common.utils.FsGsonUtil;
import java.io.ByteArrayInputStream;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

/* loaded from: classes.dex */
public class UAFTools {
    private static final String TAG = "UAFTools";

    public static String genFinalChallengeParams(String str, String str2, String str3, ChannelBinding channelBinding) {
        try {
            return new String(Base64.encode(FsGsonUtil.gsonString(new FinalChallengeParams(str, str2, str3, channelBinding)).getBytes(), 10), "utf-8");
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String genKHAccessToken(String str, String str2, String str3, String str4, boolean z) {
        String str5;
        if (z) {
            try {
                StringBuffer stringBuffer = new StringBuffer(str);
                stringBuffer.append(str2);
                stringBuffer.append(str3);
                stringBuffer.append(str4);
                str = stringBuffer.toString();
            } catch (Exception unused) {
                str5 = "";
            }
        }
        str5 = StringTools.urlSafeBase64Enc(CryptoTools.hash(str, "SHA256"));
        return StringTools.subStringByByte(str5, 32);
    }

    public static KeyPair genUAuthKeyPair(Context context, int i2, String str, String str2, String str3, String str4) {
        if (Build.VERSION.SDK_INT >= 18) {
            CryptoTools.delKeyInTee(str3, str);
            return genUAuthKeyPairTEE(context, i2, str2, str3, str4);
        }
        return null;
    }

    private static KeyPair genUAuthKeyPairTEE(Context context, int i2, String str, String str2, String str3) {
        String str4;
        String str5;
        KeyPair keyPair = null;
        try {
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (i2 != 99) {
            switch (i2) {
                case 1:
                case 2:
                    str5 = "SECP256R1";
                    keyPair = CryptoTools.genECKeyTee(str5, str, str2, str3);
                    break;
                case 3:
                case 4:
                    str4 = "PSS";
                    break;
                case 5:
                case 6:
                    str5 = "SECP256K1";
                    keyPair = CryptoTools.genECKeyTee(str5, str, str2, str3);
                    break;
                case 7:
                    keyPair = CryptoTools.genAsymmetricKey("SM2", 256);
                    break;
            }
            return keyPair;
        }
        str4 = "PKCS1";
        keyPair = CryptoTools.genRSAKeyTee(2048, str4, str, str2, str3);
        return keyPair;
    }

    public static String genUVI(String str, String str2) {
        if (StringTools.isValidateString(str) && StringTools.isValidateString(str2)) {
            return CryptoTools.hash2Hex(str + CryptoTools.hash2Hex(str2));
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0031 A[Catch: Exception -> 0x003e, TRY_LEAVE, TryCatch #0 {Exception -> 0x003e, blocks: (B:3:0x0001, B:5:0x0009, B:6:0x000b, B:19:0x0031, B:7:0x0010, B:9:0x0016, B:11:0x001c, B:12:0x0021, B:14:0x0027), top: B:27:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static RawKeyHandle generateDecryptedKeyHandle(Context context, String str) {
        String seriNumSerial;
        byte[] bArr;
        RawKeyHandle rawKeyHandle = null;
        try {
            if (!CryptoTools.secretKeyDetection(GlobalConfiguration.SERI_NUM)) {
                String seriNumFingerprint = GlobalConfiguration.getSeriNumFingerprint(context);
                if (seriNumFingerprint == null || !CryptoTools.secretKeyDetection(seriNumFingerprint)) {
                    seriNumSerial = GlobalConfiguration.getSeriNumSerial(context);
                    if (seriNumSerial == null || !CryptoTools.secretKeyDetection(seriNumSerial)) {
                        bArr = null;
                    }
                } else {
                    bArr = CryptoTools.decrypt(seriNumFingerprint, str);
                }
                if (bArr == null) {
                    RawKeyHandle rawKeyHandle2 = new RawKeyHandle();
                    try {
                        rawKeyHandle2.deserialize(bArr);
                        return rawKeyHandle2;
                    } catch (Exception e2) {
                        e = e2;
                        rawKeyHandle = rawKeyHandle2;
                        e.printStackTrace();
                        return rawKeyHandle;
                    }
                }
                return null;
            }
            seriNumSerial = GlobalConfiguration.SERI_NUM;
            bArr = CryptoTools.decrypt(seriNumSerial, str);
            if (bArr == null) {
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    public static String generateEncryptedKeyHandleAhth(Context context, RawKeyHandle rawKeyHandle) {
        String str = null;
        try {
            byte[] serialize = rawKeyHandle.serialize();
            if (CryptoTools.secretKeyDetection(GlobalConfiguration.SERI_NUM)) {
                str = CryptoTools.encrypt(GlobalConfiguration.SERI_NUM, serialize);
            } else {
                String seriNumFingerprint = GlobalConfiguration.getSeriNumFingerprint(context);
                if (seriNumFingerprint == null || !CryptoTools.secretKeyDetection(seriNumFingerprint)) {
                    String seriNumSerial = GlobalConfiguration.getSeriNumSerial(context);
                    if (seriNumSerial != null && CryptoTools.secretKeyDetection(seriNumSerial)) {
                        str = CryptoTools.encrypt(seriNumSerial, serialize);
                    }
                } else {
                    str = CryptoTools.encrypt(seriNumFingerprint, serialize);
                }
            }
        } catch (Throwable unused) {
        }
        return str;
    }

    public static String generateEncryptedKeyHandleReg(RawKeyHandle rawKeyHandle) {
        try {
            return CryptoTools.encrypt(GlobalConfiguration.SERI_NUM, rawKeyHandle.serialize());
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static Version getAsmVersion() {
        Version version = new Version();
        version.major = 1;
        version.minor = 0;
        return version;
    }

    public static String getCallerID(Context context, int i2) {
        String[] packagesForUid = context.getPackageManager().getPackagesForUid(i2);
        if (packagesForUid == null) {
            return null;
        }
        try {
            return Base64.encodeToString(MessageDigest.getInstance("SHA1").digest(((X509Certificate) CertificateFactory.getInstance("X509").generateCertificate(new ByteArrayInputStream(context.getPackageManager().getPackageInfo(packagesForUid[0], 64).signatures[0].toByteArray()))).getEncoded()), 3);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static Version getClientVersion() {
        Version version = new Version();
        version.major = 1;
        version.minor = 0;
        return version;
    }

    public static Version getUAFVersion() {
        Version version = new Version();
        version.major = 1;
        version.minor = 0;
        return version;
    }

    public static KeyPair getUAuthKeyPairTEE(int i2, String str, String str2, String str3) {
        if (i2 != 355) {
            switch (i2) {
                case 256:
                case 257:
                case 258:
                case 259:
                    break;
                default:
                    return null;
            }
        }
        try {
            return CryptoTools.getPrivateKeyPairInTee(str2, str3);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static PrivateKey getUAuthPrivateKeyTEE(int i2, String str, String str2, String str3) {
        if (i2 != 355) {
            switch (i2) {
                case 256:
                case 257:
                case 258:
                case 259:
                    break;
                default:
                    return null;
            }
        }
        try {
            return CryptoTools.getPrivateKeyInTee(str2, str3);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static byte[] getUAuthPublicKey(int i2, KeyPair keyPair) {
        PublicKey publicKey;
        try {
            if (i2 != 355) {
                switch (i2) {
                    case 256:
                        return CryptoTools.getRAWECPublicKey(keyPair);
                    case 257:
                        publicKey = keyPair.getPublic();
                        break;
                    case 258:
                        return CryptoTools.getRAWRSAPublicKey(keyPair);
                    case 259:
                        return CryptoTools.getDERRSAPublicKey(keyPair);
                    case 260:
                        publicKey = keyPair.getPublic();
                        break;
                    default:
                        return null;
                }
            } else {
                publicKey = keyPair.getPublic();
            }
            return publicKey.getEncoded();
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String getVendorIDFromAAID(String str) {
        if (StringTools.isValidateString(str)) {
            return StringTools.splitStrFlag(str, "#")[0];
        }
        return null;
    }

    public static String mixKHATokenWithAppID(String str, String str2) {
        return StringTools.subStringByByte(StringTools.urlSafeBase64Enc(CryptoTools.hash(str + str2, "SHA256")), 32);
    }
}
