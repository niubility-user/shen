package com.jingdong.common.utils;

import com.jd.jdsdk.security.DesCbcCrypto;
import com.jingdong.common.entity.DesCommonUtils;
import com.jingdong.common.utils.security.JDKeyStore;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.utils.JsonEncryptUtil;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011J!\u0010\u0006\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0007\u00a2\u0006\u0004\b\u0006\u0010\u0007J-\u0010\u0006\u001a\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u0002H\u0007\u00a2\u0006\u0004\b\u0006\u0010\tJ#\u0010\n\u001a\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0007\u00a2\u0006\u0004\b\n\u0010\u0007J-\u0010\n\u001a\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u0002H\u0007\u00a2\u0006\u0004\b\n\u0010\tJ\u001b\u0010\u000b\u001a\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0007\u00a2\u0006\u0004\b\u000b\u0010\fJ\u001b\u0010\r\u001a\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0007\u00a2\u0006\u0004\b\r\u0010\fJ\u000f\u0010\u000e\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0019\u0010\u000e\u001a\u00020\u00022\b\u0010\b\u001a\u0004\u0018\u00010\u0002H\u0002\u00a2\u0006\u0004\b\u000e\u0010\f\u00a8\u0006\u0012"}, d2 = {"Lcom/jingdong/common/utils/PersonalDesCommonUtils;", "Lcom/jd/jdsdk/security/DesCbcCrypto;", "", "originalValue", "", JsonEncryptUtil.ENC_KEY, "commonEncrypt", "(Ljava/lang/String;I)Ljava/lang/String;", "secretKey", "(Ljava/lang/String;ILjava/lang/String;)Ljava/lang/String;", "commonDecrypt", "newEncrypt", "(Ljava/lang/String;)Ljava/lang/String;", "newDecrypt", "getSecretKey", "()Ljava/lang/String;", "<init>", "()V", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class PersonalDesCommonUtils extends DesCbcCrypto {
    public static final PersonalDesCommonUtils INSTANCE = new PersonalDesCommonUtils();

    private PersonalDesCommonUtils() {
    }

    @JvmStatic
    @Nullable
    public static final String commonDecrypt(@Nullable String originalValue, int r2) {
        return commonDecrypt(originalValue, r2, null);
    }

    @JvmStatic
    @Nullable
    public static final String commonEncrypt(@NotNull String originalValue, int r2) {
        return commonEncrypt(originalValue, r2, null);
    }

    private final String getSecretKey() {
        return getSecretKey(null);
    }

    @JvmStatic
    @Nullable
    public static final String newDecrypt(@Nullable String originalValue) {
        if (originalValue == null || originalValue.length() == 0) {
            return null;
        }
        try {
            return DesCbcCrypto.decrypt(originalValue, INSTANCE.getSecretKey(), (byte[]) null);
        } catch (Throwable th) {
            ExceptionReporter.reportExceptionToBugly(new IllegalArgumentException("PersonalDesCommonUtils newDecrypt error massage:" + th));
            return null;
        }
    }

    @JvmStatic
    @Nullable
    public static final String newEncrypt(@Nullable String originalValue) {
        if (originalValue == null || originalValue.length() == 0) {
            return null;
        }
        try {
            return DesCbcCrypto.encrypt(originalValue, INSTANCE.getSecretKey(), (byte[]) null);
        } catch (Throwable th) {
            ExceptionReporter.reportExceptionToBugly(new IllegalArgumentException("PersonalDesCommonUtils newEncrypt error massage:" + th));
            return null;
        }
    }

    @JvmStatic
    @Nullable
    public static final String commonDecrypt(@Nullable String originalValue, int r5, @Nullable String secretKey) {
        if (originalValue == null || originalValue.length() == 0) {
            return null;
        }
        if (r5 == 1) {
            try {
                return DesCommonUtils.decryptThreeDESECB(originalValue, INSTANCE.getSecretKey(secretKey));
            } catch (Throwable th) {
                ExceptionReporter.reportExceptionToBugly(new IllegalArgumentException("PersonalDesCommonUtils commonDecrypt enc:" + r5 + " error massage:" + th));
            }
        } else if (r5 != 2) {
            return originalValue;
        } else {
            try {
                return DesCbcCrypto.decrypt(originalValue, INSTANCE.getSecretKey(), (byte[]) null);
            } catch (Throwable th2) {
                ExceptionReporter.reportExceptionToBugly(new IllegalArgumentException("PersonalDesCommonUtils commonDecrypt enc:" + r5 + " error massage:" + th2));
            }
        }
        return null;
    }

    @JvmStatic
    @Nullable
    public static final String commonEncrypt(@Nullable String originalValue, int r5, @Nullable String secretKey) {
        if (originalValue == null || originalValue.length() == 0) {
            return null;
        }
        if (r5 == 1) {
            try {
                return DesCommonUtils.encryptThreeDESECB(originalValue, INSTANCE.getSecretKey(secretKey));
            } catch (Throwable th) {
                ExceptionReporter.reportExceptionToBugly(new IllegalArgumentException("PersonalDesCommonUtils commonEncrypt enc:" + r5 + " error massage:" + th));
            }
        } else if (r5 != 2) {
            return originalValue;
        } else {
            try {
                return DesCbcCrypto.encrypt(originalValue, INSTANCE.getSecretKey(), (byte[]) null);
            } catch (Throwable th2) {
                ExceptionReporter.reportExceptionToBugly(new IllegalArgumentException("PersonalDesCommonUtils commonEncrypt enc:" + r5 + " error massage:" + th2));
            }
        }
        return null;
    }

    private final String getSecretKey(String secretKey) {
        if (secretKey == null || secretKey.length() == 0) {
            String generateKey = JDKeyStore.getInstance().generateKey(JDKeyStore.KEY_TYPE_3DES);
            Intrinsics.checkExpressionValueIsNotNull(generateKey, "JDKeyStore.getInstance()\u2026enerateKey(KEY_TYPE_3DES)");
            return generateKey;
        } else if (secretKey == null) {
            Intrinsics.throwNpe();
            return secretKey;
        } else {
            return secretKey;
        }
    }
}
