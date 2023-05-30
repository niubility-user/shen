package cn.com.union.fido.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.text.TextUtils;
import cn.com.union.fido.bean.SecCheckResult;
import cn.com.union.fido.util.asn1.ASN1Encodable;
import cn.com.union.fido.util.asn1.ASN1EncodableVector;
import cn.com.union.fido.util.asn1.DERInteger;
import cn.com.union.fido.util.asn1.DERSequence;
import com.jdcn.fido.constant.BasicInformation;
import com.jdcn.fido.utils.TrackerUtil;
import com.jingdong.common.jdreactFramework.utils.RSAUtils;
import com.jingdong.common.utils.security.JDKeyStore;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.RSAKeyGenParameterSpec;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes.dex */
public class CryptoTools {
    public static byte[] decrypt(String str, String str2) {
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                SecretKey keyInTee = getKeyInTee(str);
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
                cipher.init(2, keyInTee, new IvParameterSpec(str.substring(0, 16).getBytes()));
                return cipher.doFinal(StringTools.urlSafeBase64Dec(str2));
            }
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static void delKeyInTee(String str) {
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            keyStore.deleteEntry(str);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void delKeyInTee(String str, String str2) {
        try {
            KeyStore keyStore = KeyStore.getInstance(str2);
            keyStore.load(null);
            if (keyStore.containsAlias(str)) {
                keyStore.deleteEntry(str);
            }
        } catch (Throwable unused) {
        }
    }

    public static byte[] doHash(byte[] bArr, String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str);
            messageDigest.update(bArr);
            return messageDigest.digest();
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String encrypt(String str, byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        byte[] bArr2 = null;
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                SecretKey keyInTee = getKeyInTee(str);
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
                cipher.init(1, keyInTee, new IvParameterSpec(str.substring(0, 16).getBytes()));
                bArr2 = cipher.doFinal(bArr);
            }
            stringBuffer.append(StringTools.urlSafeBase64Enc(bArr2));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return stringBuffer.toString();
    }

    public static KeyPair genAsymmetricKey(String str, int i2) {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(str);
            keyPairGenerator.initialize(i2);
            return keyPairGenerator.genKeyPair();
        } catch (Exception e2) {
            throw e2;
        }
    }

    public static KeyPair genECKeyTee(String str, String str2, String str3, String str4) {
        try {
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 23) {
                KeyGenParameterSpec.Builder builder = new KeyGenParameterSpec.Builder(str3, 4);
                builder.setDigests(MessageDigestAlgorithms.SHA_256);
                builder.setAlgorithmParameterSpec(new ECGenParameterSpec(str));
                builder.setUserAuthenticationRequired(true);
                if (i2 > 29) {
                    builder.setAttestationChallenge(str4.getBytes());
                }
                KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", str2);
                keyPairGenerator.initialize(builder.build());
                return keyPairGenerator.generateKeyPair();
            }
            return null;
        } catch (Exception e2) {
            TrackerUtil.appendException(BasicInformation.SCENE_REG_GEN_EXCEPTION, e2);
            throw e2;
        }
    }

    @TargetApi(23)
    public static KeyPair genRSAKeyTee(int i2, String str, String str2, String str3, String str4) {
        try {
            int i3 = Build.VERSION.SDK_INT;
            if (i3 >= 23) {
                KeyGenParameterSpec.Builder builder = new KeyGenParameterSpec.Builder(str3, 4);
                builder.setDigests(MessageDigestAlgorithms.SHA_256);
                builder.setAlgorithmParameterSpec(new RSAKeyGenParameterSpec(i2, null));
                builder.setSignaturePaddings(str);
                builder.setUserAuthenticationRequired(true);
                if (i3 > 29) {
                    builder.setAttestationChallenge(str4.getBytes());
                }
                KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSAUtils.KEY_ALGORITHM, str2);
                keyPairGenerator.initialize(builder.build());
                return keyPairGenerator.generateKeyPair();
            }
            return null;
        } catch (Exception e2) {
            TrackerUtil.appendException(BasicInformation.SCENE_REG_GEN_EXCEPTION, e2);
            throw e2;
        }
    }

    public static byte[] genRandom(int i2) {
        byte[] bArr = null;
        if (i2 > 0) {
            try {
                if (Build.VERSION.SDK_INT >= 23) {
                    SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
                    bArr = secureRandom.generateSeed(i2);
                    secureRandom.setSeed(bArr);
                    secureRandom.nextBytes(new byte[i2]);
                } else {
                    SecureRandom.getInstance("SHA1PRNG").nextBytes(new byte[i2]);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return bArr;
    }

    private static SecretKey genSymmetryKeyTee(String str, boolean z, int i2) {
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                KeyGenerator keyGenerator = KeyGenerator.getInstance(JDKeyStore.KEY_TYPE_AES, "AndroidKeyStore");
                KeyGenParameterSpec.Builder builder = new KeyGenParameterSpec.Builder(str, 3);
                builder.setBlockModes("CBC");
                builder.setRandomizedEncryptionRequired(false);
                builder.setEncryptionPaddings("PKCS7Padding");
                if (z) {
                    builder.setUserAuthenticationRequired(true);
                    if (i2 > 0) {
                        builder.setUserAuthenticationValidityDurationSeconds(i2);
                    }
                }
                keyGenerator.init(builder.build());
                return keyGenerator.generateKey();
            }
            return null;
        } catch (Exception e2) {
            throw e2;
        }
    }

    public static Certificate[] getCertificateChain(String str, String str2) {
        try {
            KeyStore keyStore = KeyStore.getInstance(str2);
            keyStore.load(null);
            return keyStore.getCertificateChain(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static byte[] getDERRSAPublicKey(KeyPair keyPair) {
        try {
            RSAPublicKey rSAPublicKey = (RSAPublicKey) keyPair.getPublic();
            BigInteger modulus = rSAPublicKey.getModulus();
            BigInteger publicExponent = rSAPublicKey.getPublicExponent();
            ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
            aSN1EncodableVector.add(new DERInteger(modulus));
            aSN1EncodableVector.add(new DERInteger(publicExponent));
            return new DERSequence(aSN1EncodableVector).getEncoded(ASN1Encodable.DER);
        } catch (Exception e2) {
            throw e2;
        }
    }

    public static SecretKey getKeyInTee(String str) {
        SecretKey secretKey = null;
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
                keyStore.load(null);
                SecretKey secretKey2 = (SecretKey) keyStore.getKey(str, null);
                if (secretKey2 == null) {
                    try {
                        return genSymmetryKeyTee(str, false, 0);
                    } catch (Exception e2) {
                        e = e2;
                        secretKey = secretKey2;
                        e.printStackTrace();
                        return secretKey;
                    }
                }
                return secretKey2;
            }
            return null;
        } catch (Exception e3) {
            e = e3;
        }
    }

    public static String getKeyString(Key key) {
        byte[] encoded = key.getEncoded();
        if (encoded == null || encoded.length == 0) {
            return "TEEPrivateKey" + Math.random();
        }
        return StringTools.urlSafeBase64Enc(encoded);
    }

    public static PrivateKey getPrivateKeyInTee(String str, String str2) {
        try {
            KeyStore keyStore = KeyStore.getInstance(str2);
            keyStore.load(null);
            keyStore.containsAlias(str);
            return (PrivateKey) keyStore.getKey(str, null);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static KeyPair getPrivateKeyPairInTee(String str, String str2) {
        try {
            KeyStore keyStore = KeyStore.getInstance(str2);
            keyStore.load(null);
            return new KeyPair(keyStore.getCertificate(str).getPublicKey(), (PrivateKey) keyStore.getKey(str, null));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static byte[] getRAWECPublicKey(KeyPair keyPair) {
        try {
            ECPoint w = ((ECPublicKey) keyPair.getPublic()).getW();
            byte[] asUnsigned32ByteArray = BigIntegerUtil.asUnsigned32ByteArray(w.getAffineX());
            byte[] asUnsigned32ByteArray2 = BigIntegerUtil.asUnsigned32ByteArray(w.getAffineY());
            byte[] bArr = new byte[65];
            bArr[0] = 4;
            System.arraycopy(asUnsigned32ByteArray, 0, bArr, 1, 32);
            System.arraycopy(asUnsigned32ByteArray2, 0, bArr, 33, 32);
            return bArr;
        } catch (Exception e2) {
            throw e2;
        }
    }

    public static byte[] getRAWRSAPublicKey(KeyPair keyPair) {
        try {
            RSAPublicKey rSAPublicKey = (RSAPublicKey) keyPair.getPublic();
            byte[] asUnsignedNByteArray = BigIntegerUtil.asUnsignedNByteArray(rSAPublicKey.getModulus(), 256);
            byte[] byteArray = rSAPublicKey.getPublicExponent().toByteArray();
            byte[] copyOf = Arrays.copyOf(asUnsignedNByteArray, asUnsignedNByteArray.length + byteArray.length);
            System.arraycopy(byteArray, 0, copyOf, asUnsignedNByteArray.length, byteArray.length);
            return copyOf;
        } catch (Exception e2) {
            throw e2;
        }
    }

    public static byte[] hash(String str, String str2) {
        if (StringTools.isValidateString(str)) {
            return doHash(str.getBytes(), str2);
        }
        return null;
    }

    public static String hash2Hex(String str) {
        byte[] doHash = StringTools.isValidateString(str) ? doHash(str.getBytes(), "SHA256") : null;
        if (doHash == null || doHash.length <= 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder(doHash.length * 2);
        for (byte b : doHash) {
            int i2 = b & 255;
            if (i2 < 16) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(i2));
        }
        return sb.toString();
    }

    public static boolean secretKeyDetection(String str) {
        try {
            if (TextUtils.isEmpty(str) || Build.VERSION.SDK_INT < 23) {
                return false;
            }
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            return keyStore.containsAlias(str);
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public native SecCheckResult secCheck(Context context, String str, Signature signature, String str2, byte[] bArr, byte[] bArr2, byte[] bArr3);
}
