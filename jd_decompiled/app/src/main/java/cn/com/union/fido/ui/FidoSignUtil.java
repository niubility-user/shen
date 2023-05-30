package cn.com.union.fido.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import cn.com.union.fido.bean.SecCheckResult;
import cn.com.union.fido.bean.authenticator.RawKeyHandle;
import cn.com.union.fido.bean.authenticator.command.RegisterCommand;
import cn.com.union.fido.bean.authenticator.command.RegisterResponse;
import cn.com.union.fido.bean.authenticator.command.SignCommand;
import cn.com.union.fido.bean.authenticator.command.SignResponse;
import cn.com.union.fido.bean.authenticator.tag.TAG_ATTESTATION_BASIC_FULL;
import cn.com.union.fido.bean.authenticator.tag.TAG_ATTESTATION_BASIC_SURROGATE;
import cn.com.union.fido.bean.authenticator.tag.TAG_EXTENSION;
import cn.com.union.fido.bean.authenticator.tag.TAG_UAFV1_AUTH_ASSERTION;
import cn.com.union.fido.bean.authenticator.tag.TAG_UAFV1_KRD;
import cn.com.union.fido.bean.authenticator.tag.TAG_UAFV1_REG_ASSERTION;
import cn.com.union.fido.bean.authenticator.tag.TAG_UAFV1_SIGNED_DATA;
import cn.com.union.fido.bean.authenticator.tag.TAG_USERNAME_AND_KEYHANDLE;
import cn.com.union.fido.bean.uafclient.Operation;
import cn.com.union.fido.common.FingerAuthenticatorInfo;
import cn.com.union.fido.db.AuthenticationManager;
import cn.com.union.fido.db.SignCounterManager;
import cn.com.union.fido.util.CryptoTools;
import cn.com.union.fido.util.StringTools;
import cn.com.union.fido.util.UAFTools;
import cn.com.union.fido.util.Utility;
import cn.com.union.fido.util.asn1.ASN1Set;
import cn.com.union.fido.util.asn1.x509.X509Name;
import cn.com.union.fido.util.p10.PKCS10CertificationRequest;
import com.jd.dynamic.DYConstants;
import com.jdcn.fido.constant.BasicInformation;
import com.jdcn.fido.utils.TrackerUtil;
import com.jdjr.mobilecert.MobileCertConstants;
import com.jingdong.jdsdk.network.dependency.IStatInfoConfig;
import com.wangyin.platform.CryptoUtils;
import com.wangyin.platform.FidoSecCheckCert;
import com.wangyin.platform.FidoSecCheckResult;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PSSParameterSpec;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.codec.CharEncoding;

/* loaded from: classes.dex */
public class FidoSignUtil {
    private SignCommand signCommand = new SignCommand();
    private RegisterCommand registerCommand = new RegisterCommand();
    private List<RawKeyHandle> remainingKeyhandleList = new ArrayList();

    private void getKeyHandle(Activity activity, byte[] bArr) {
        this.signCommand.deserialize(bArr);
        this.remainingKeyhandleList = new ArrayList();
        Iterator<String> it = this.signCommand.keyHandles.iterator();
        while (it.hasNext()) {
            RawKeyHandle generateDecryptedKeyHandle = UAFTools.generateDecryptedKeyHandle(activity, it.next());
            if (generateDecryptedKeyHandle != null && generateDecryptedKeyHandle.KHAccessToken.equals(this.signCommand.khAccessToken)) {
                this.remainingKeyhandleList.add(generateDecryptedKeyHandle);
            }
        }
    }

    private PrivateKey getPrivateHandle(Activity activity, IAuthPresenter iAuthPresenter, int i2, String str, String str2) {
        PrivateKey privateKey;
        if (iAuthPresenter.getOpType().equals(Operation.Reg.name())) {
            this.registerCommand.deserialize(iAuthPresenter.getFidoCmd());
            privateKey = UAFTools.genUAuthKeyPair(activity, i2, str, str2, StringTools.urlSafeBase64Enc(CryptoTools.hash(this.registerCommand.userName + "004B#0001" + this.registerCommand.extensions.get(1).data, "SHA256")), this.registerCommand.extensions.get(2).data).getPrivate();
        } else {
            privateKey = null;
        }
        if (iAuthPresenter.getOpType().equals(Operation.Auth.name())) {
            getKeyHandle(activity, iAuthPresenter.getFidoCmd());
            if (this.remainingKeyhandleList.size() != 1) {
                deregister(activity, "004B#0001");
                return privateKey;
            }
            RawKeyHandle rawKeyHandle = this.remainingKeyhandleList.get(0);
            return UAFTools.getUAuthPrivateKeyTEE(257, rawKeyHandle.PrivateKey, StringTools.urlSafeBase64Enc(CryptoTools.hash(rawKeyHandle.Username + "004B#0001" + this.signCommand.extensions.get(1).data, "SHA256")), str);
        }
        return privateKey;
    }

    public void deregister(Context context, String str) {
        try {
            new AuthenticationManager(context).delEntityByAaid(str);
        } catch (Throwable unused) {
        }
    }

    public void deregisterByName(Context context, String str, String str2) {
        try {
            if (this.remainingKeyhandleList.size() == 1) {
                String str3 = this.remainingKeyhandleList.get(0).Username;
                if (TextUtils.isEmpty(str3)) {
                    return;
                }
                new AuthenticationManager(context).delEntityByUserName(str3);
                CryptoTools.delKeyInTee(StringTools.urlSafeBase64Enc(CryptoTools.hash(str3 + str + this.signCommand.extensions.get(1).data, "SHA256")), str2);
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v13 */
    /* JADX WARN: Type inference failed for: r14v14 */
    /* JADX WARN: Type inference failed for: r14v15 */
    /* JADX WARN: Type inference failed for: r14v7, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r15v11 */
    /* JADX WARN: Type inference failed for: r15v7, types: [short] */
    /* JADX WARN: Type inference failed for: r15v8 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v19, types: [cn.com.union.fido.bean.authenticator.tag.TAG_EXTENSION] */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v41 */
    /* JADX WARN: Type inference failed for: r2v5, types: [cn.com.union.fido.bean.authenticator.tag.TAG_EXTENSION] */
    /* JADX WARN: Type inference failed for: r3v20, types: [byte[]] */
    @TargetApi(23)
    public RegisterResponse doRegProcess(Activity activity, Signature signature, String str, String str2, String str3) {
        TAG_UAFV1_KRD tag_uafv1_krd;
        TAG_UAFV1_KRD tag_uafv1_krd2;
        RawKeyHandle rawKeyHandle;
        TAG_UAFV1_KRD tag_uafv1_krd3;
        RawKeyHandle rawKeyHandle2;
        byte[] bArr;
        TAG_UAFV1_KRD tag_uafv1_krd4;
        ?? r15;
        byte[] bArr2;
        SignCounterManager signCounterManager = new SignCounterManager(activity);
        RegisterResponse registerResponse = new RegisterResponse();
        short s = FingerAuthenticatorInfo.authenticationAlg;
        List<Short> list = FingerAuthenticatorInfo.attestationType;
        if (list == null || list.indexOf(Short.valueOf(this.registerCommand.attestationType)) != -1) {
            KeyPair uAuthKeyPairTEE = UAFTools.getUAuthKeyPairTEE(257, null, StringTools.urlSafeBase64Enc(CryptoTools.hash(this.registerCommand.userName + "004B#0001" + this.registerCommand.extensions.get(1).data, "SHA256")), str2);
            byte[] uAuthPublicKey = UAFTools.getUAuthPublicKey(257, uAuthKeyPairTEE);
            if (uAuthKeyPairTEE == null) {
                return null;
            }
            String keyString = CryptoTools.getKeyString(uAuthKeyPairTEE.getPrivate());
            RawKeyHandle rawKeyHandle3 = new RawKeyHandle();
            RegisterCommand registerCommand = this.registerCommand;
            rawKeyHandle3.KHAccessToken = registerCommand.khAccessToken;
            rawKeyHandle3.PrivateKey = keyString;
            rawKeyHandle3.Username = registerCommand.userName;
            String generateEncryptedKeyHandleReg = UAFTools.generateEncryptedKeyHandleReg(rawKeyHandle3);
            TAG_UAFV1_KRD tag_uafv1_krd5 = new TAG_UAFV1_KRD();
            tag_uafv1_krd5.aaid = "004B#0001";
            tag_uafv1_krd5.authenticatorVersion = (short) 1;
            tag_uafv1_krd5.authenticationMode = (byte) 1;
            tag_uafv1_krd5.signatureAlgAndEncoding = s;
            tag_uafv1_krd5.publicKeyAlgAndEncoding = (short) 257;
            tag_uafv1_krd5.finalChallenge = this.registerCommand.finalChallenge;
            String hash2Hex = CryptoTools.hash2Hex(generateEncryptedKeyHandleReg);
            if (hash2Hex == null) {
                return null;
            }
            String substring = hash2Hex.substring(0, 32);
            tag_uafv1_krd5.keyID = substring;
            int updateOrAddCounter = signCounterManager.updateOrAddCounter("004B#0001", substring, this.registerCommand.userName);
            if (-1 == updateOrAddCounter) {
                return null;
            }
            tag_uafv1_krd5.signCounter = updateOrAddCounter;
            tag_uafv1_krd5.regCounter = 0;
            tag_uafv1_krd5.publicKey = uAuthPublicKey;
            tag_uafv1_krd5.uvi = UAFTools.genUVI(tag_uafv1_krd5.keyID, str);
            byte[] serialize = tag_uafv1_krd5.serialize();
            TAG_UAFV1_REG_ASSERTION tag_uafv1_reg_assertion = new TAG_UAFV1_REG_ASSERTION();
            if (this.registerCommand.attestationType == 15879) {
                ArrayList arrayList = new ArrayList();
                try {
                    if (Build.VERSION.SDK_INT > 29) {
                        Certificate[] certificateChain = CryptoTools.getCertificateChain(StringTools.urlSafeBase64Enc(CryptoTools.hash(this.registerCommand.userName + "004B#0001" + this.registerCommand.extensions.get(1).data, "SHA256")), str2);
                        if (certificateChain == null || certificateChain.length <= 0) {
                            return null;
                        }
                        int length = certificateChain.length;
                        int i2 = 0;
                        while (i2 < length) {
                            arrayList.add(Base64.encodeToString(((X509Certificate) certificateChain[i2]).getEncoded(), 0));
                            i2++;
                            certificateChain = certificateChain;
                        }
                        signature.update(serialize);
                        bArr2 = signature.sign();
                    } else {
                        arrayList.add(FidoSecCheckCert.getCert());
                        FidoSecCheckResult fidoSecCheck = CryptoUtils.fidoSecCheck(activity, StringTools.urlSafeBase64Enc(CryptoTools.hash(this.registerCommand.userName + "004B#0001" + this.registerCommand.extensions.get(1).data, "SHA256")), serialize);
                        bArr2 = fidoSecCheck != null ? fidoSecCheck.signedData : null;
                    }
                    TAG_ATTESTATION_BASIC_FULL tag_attestation_basic_full = new TAG_ATTESTATION_BASIC_FULL();
                    tag_attestation_basic_full.signature = bArr2;
                    tag_attestation_basic_full.certificate = arrayList;
                    tag_attestation_basic_full.keyStoreType = str3;
                    tag_uafv1_reg_assertion.attestationBasicFull = tag_attestation_basic_full;
                } catch (Throwable th) {
                    TrackerUtil.appendException(BasicInformation.SCENE_REG_SIG_EXCEPTION, th);
                    return null;
                }
            }
            if (this.registerCommand.attestationType == 15880) {
                try {
                    String str4 = activity.getFilesDir().getPath() + "/fidoTable";
                    try {
                        if (DYConstants.DY_TRUE.equals(this.registerCommand.extensions.get(0).data)) {
                            StringBuilder sb = new StringBuilder("CN=");
                            sb.append(rawKeyHandle3.Username);
                            sb.append(",O=cfca");
                            tag_uafv1_krd = tag_uafv1_krd5;
                            try {
                                sb.append(System.currentTimeMillis());
                                sb.append(",OU=cfca,C=CN");
                                tag_uafv1_krd5 = null;
                                bArr = 0;
                            } catch (Throwable th2) {
                                th = th2;
                                tag_uafv1_krd5 = null;
                                rawKeyHandle3 = null;
                                tag_uafv1_krd2 = tag_uafv1_krd5;
                                tag_uafv1_krd3 = tag_uafv1_krd5;
                                rawKeyHandle = rawKeyHandle3;
                                th.printStackTrace();
                                bArr = tag_uafv1_krd3;
                                rawKeyHandle2 = rawKeyHandle;
                                TAG_ATTESTATION_BASIC_SURROGATE tag_attestation_basic_surrogate = new TAG_ATTESTATION_BASIC_SURROGATE();
                                tag_attestation_basic_surrogate.signature = bArr;
                                tag_uafv1_reg_assertion.attestationBasicSurrogate = tag_attestation_basic_surrogate;
                                tag_uafv1_reg_assertion.extension = tag_uafv1_krd2;
                                tag_uafv1_krd4 = tag_uafv1_krd;
                                r15 = rawKeyHandle2;
                                tag_uafv1_reg_assertion.uafv1Krd = tag_uafv1_krd4;
                                registerResponse.statusCode = r15;
                                registerResponse.assertion = tag_uafv1_reg_assertion.serialize();
                                registerResponse.keyHandle = generateEncryptedKeyHandleReg;
                                return registerResponse;
                            }
                            try {
                                PKCS10CertificationRequest pKCS10CertificationRequest = new PKCS10CertificationRequest("SHA256withRSA", new X509Name(sb.toString()), uAuthKeyPairTEE.getPublic(), null);
                                rawKeyHandle2 = null;
                                rawKeyHandle2 = null;
                                rawKeyHandle = null;
                                SecCheckResult secCheck = new CryptoTools().secCheck(activity, str4, signature, StringTools.urlSafeBase64Enc(CryptoTools.hash(this.registerCommand.userName + "004B#0001" + this.registerCommand.extensions.get(1).data, "SHA256")), Base64.decode(FingerAuthenticatorInfo.privateKey.getBytes(CharEncoding.ISO_8859_1), 10), pKCS10CertificationRequest.getReqInfoEncoded(), serialize);
                                if (secCheck != null) {
                                    ?? r3 = secCheck.signedData;
                                    try {
                                        pKCS10CertificationRequest.setSigBits(secCheck.extraData);
                                        tag_uafv1_krd2 = new TAG_EXTENSION();
                                    } catch (Throwable th3) {
                                        th = th3;
                                        tag_uafv1_krd2 = null;
                                    }
                                    try {
                                        tag_uafv1_krd2.id = IStatInfoConfig.KEY_ENCRYPT;
                                        tag_uafv1_krd2.data = new String(Base64.encode(pKCS10CertificationRequest.getEncoded(), 0));
                                        tag_uafv1_reg_assertion.extension = tag_uafv1_krd2;
                                        bArr = r3;
                                    } catch (Throwable th4) {
                                        th = th4;
                                        tag_uafv1_krd3 = r3;
                                        tag_uafv1_krd2 = tag_uafv1_krd2;
                                        th.printStackTrace();
                                        bArr = tag_uafv1_krd3;
                                        rawKeyHandle2 = rawKeyHandle;
                                        TAG_ATTESTATION_BASIC_SURROGATE tag_attestation_basic_surrogate2 = new TAG_ATTESTATION_BASIC_SURROGATE();
                                        tag_attestation_basic_surrogate2.signature = bArr;
                                        tag_uafv1_reg_assertion.attestationBasicSurrogate = tag_attestation_basic_surrogate2;
                                        tag_uafv1_reg_assertion.extension = tag_uafv1_krd2;
                                        tag_uafv1_krd4 = tag_uafv1_krd;
                                        r15 = rawKeyHandle2;
                                        tag_uafv1_reg_assertion.uafv1Krd = tag_uafv1_krd4;
                                        registerResponse.statusCode = r15;
                                        registerResponse.assertion = tag_uafv1_reg_assertion.serialize();
                                        registerResponse.keyHandle = generateEncryptedKeyHandleReg;
                                        return registerResponse;
                                    }
                                } else {
                                    tag_uafv1_krd2 = null;
                                }
                            } catch (Throwable th5) {
                                th = th5;
                                rawKeyHandle3 = null;
                                tag_uafv1_krd2 = tag_uafv1_krd5;
                                tag_uafv1_krd3 = tag_uafv1_krd5;
                                rawKeyHandle = rawKeyHandle3;
                                th.printStackTrace();
                                bArr = tag_uafv1_krd3;
                                rawKeyHandle2 = rawKeyHandle;
                                TAG_ATTESTATION_BASIC_SURROGATE tag_attestation_basic_surrogate22 = new TAG_ATTESTATION_BASIC_SURROGATE();
                                tag_attestation_basic_surrogate22.signature = bArr;
                                tag_uafv1_reg_assertion.attestationBasicSurrogate = tag_attestation_basic_surrogate22;
                                tag_uafv1_reg_assertion.extension = tag_uafv1_krd2;
                                tag_uafv1_krd4 = tag_uafv1_krd;
                                r15 = rawKeyHandle2;
                                tag_uafv1_reg_assertion.uafv1Krd = tag_uafv1_krd4;
                                registerResponse.statusCode = r15;
                                registerResponse.assertion = tag_uafv1_reg_assertion.serialize();
                                registerResponse.keyHandle = generateEncryptedKeyHandleReg;
                                return registerResponse;
                            }
                        } else {
                            tag_uafv1_krd = tag_uafv1_krd5;
                            rawKeyHandle2 = null;
                            SecCheckResult secCheck2 = new CryptoTools().secCheck(activity, str4, signature, StringTools.urlSafeBase64Enc(CryptoTools.hash(this.registerCommand.userName + "004B#0001" + this.registerCommand.extensions.get(1).data, "SHA256")), Base64.decode(FingerAuthenticatorInfo.privateKey.getBytes(CharEncoding.ISO_8859_1), 10), null, serialize);
                            tag_uafv1_krd2 = null;
                            bArr = secCheck2 != null ? secCheck2.signedData : null;
                        }
                    } catch (Throwable th6) {
                        th = th6;
                        tag_uafv1_krd2 = tag_uafv1_krd5;
                        tag_uafv1_krd3 = tag_uafv1_krd5;
                        rawKeyHandle = rawKeyHandle3;
                        th.printStackTrace();
                        bArr = tag_uafv1_krd3;
                        rawKeyHandle2 = rawKeyHandle;
                        TAG_ATTESTATION_BASIC_SURROGATE tag_attestation_basic_surrogate222 = new TAG_ATTESTATION_BASIC_SURROGATE();
                        tag_attestation_basic_surrogate222.signature = bArr;
                        tag_uafv1_reg_assertion.attestationBasicSurrogate = tag_attestation_basic_surrogate222;
                        tag_uafv1_reg_assertion.extension = tag_uafv1_krd2;
                        tag_uafv1_krd4 = tag_uafv1_krd;
                        r15 = rawKeyHandle2;
                        tag_uafv1_reg_assertion.uafv1Krd = tag_uafv1_krd4;
                        registerResponse.statusCode = r15;
                        registerResponse.assertion = tag_uafv1_reg_assertion.serialize();
                        registerResponse.keyHandle = generateEncryptedKeyHandleReg;
                        return registerResponse;
                    }
                } catch (Throwable th7) {
                    th = th7;
                    tag_uafv1_krd = tag_uafv1_krd5;
                }
                TAG_ATTESTATION_BASIC_SURROGATE tag_attestation_basic_surrogate2222 = new TAG_ATTESTATION_BASIC_SURROGATE();
                tag_attestation_basic_surrogate2222.signature = bArr;
                tag_uafv1_reg_assertion.attestationBasicSurrogate = tag_attestation_basic_surrogate2222;
                tag_uafv1_reg_assertion.extension = tag_uafv1_krd2;
                tag_uafv1_krd4 = tag_uafv1_krd;
                r15 = rawKeyHandle2;
            } else {
                r15 = 0;
                tag_uafv1_krd4 = tag_uafv1_krd5;
            }
            tag_uafv1_reg_assertion.uafv1Krd = tag_uafv1_krd4;
            registerResponse.statusCode = r15;
            registerResponse.assertion = tag_uafv1_reg_assertion.serialize();
            registerResponse.keyHandle = generateEncryptedKeyHandleReg;
        } else {
            registerResponse.statusCode = (short) 7;
        }
        return registerResponse;
    }

    @TargetApi(23)
    public SignResponse doSignProcess(Activity activity, Signature signature, String str, String str2) {
        TAG_EXTENSION tag_extension;
        SignCounterManager signCounterManager = new SignCounterManager(activity);
        SignResponse signResponse = new SignResponse();
        short s = FingerAuthenticatorInfo.authenticationAlg;
        SignCommand signCommand = this.signCommand;
        String str3 = signCommand.transactionContent;
        List<TAG_EXTENSION> list = signCommand.extensions;
        if (this.remainingKeyhandleList.size() == 0) {
            signResponse.statusCode = (short) 2;
        } else if (this.remainingKeyhandleList.size() > 1) {
            ArrayList arrayList = new ArrayList();
            for (RawKeyHandle rawKeyHandle : this.remainingKeyhandleList) {
                String str4 = rawKeyHandle.Username;
                String generateEncryptedKeyHandleAhth = UAFTools.generateEncryptedKeyHandleAhth(activity, rawKeyHandle);
                TAG_USERNAME_AND_KEYHANDLE tag_username_and_keyhandle = new TAG_USERNAME_AND_KEYHANDLE();
                tag_username_and_keyhandle.keyHandle = generateEncryptedKeyHandleAhth;
                tag_username_and_keyhandle.userName = str4;
                arrayList.add(tag_username_and_keyhandle);
            }
            signResponse.userNameAndKeyHandle = arrayList;
        } else if (this.remainingKeyhandleList.size() == 1) {
            RawKeyHandle rawKeyHandle2 = this.remainingKeyhandleList.get(0);
            String generateEncryptedKeyHandleAhth2 = UAFTools.generateEncryptedKeyHandleAhth(activity, rawKeyHandle2);
            byte[] bArr = null;
            if (generateEncryptedKeyHandleAhth2 == null) {
                return null;
            }
            TAG_UAFV1_SIGNED_DATA tag_uafv1_signed_data = new TAG_UAFV1_SIGNED_DATA();
            tag_uafv1_signed_data.authenticationMode = (byte) 1;
            if (StringTools.isValidateString(str3)) {
                tag_uafv1_signed_data.tcHash = CryptoTools.hash(str3, "SHA256");
                tag_uafv1_signed_data.authenticationMode = (byte) 2;
            }
            TAG_UAFV1_AUTH_ASSERTION tag_uafv1_auth_assertion = new TAG_UAFV1_AUTH_ASSERTION();
            tag_uafv1_signed_data.aaid = "004B#0001";
            tag_uafv1_signed_data.authenticatorVersion = (short) 1;
            tag_uafv1_signed_data.signatureAlgAndEncoding = s;
            tag_uafv1_signed_data.authnrNonce = CryptoTools.genRandom(8);
            tag_uafv1_signed_data.finalChallenge = this.signCommand.finalChallenge;
            if (tag_uafv1_signed_data.authenticationMode == 1) {
                tag_uafv1_signed_data.tcHash = null;
            }
            String hash2Hex = CryptoTools.hash2Hex(generateEncryptedKeyHandleAhth2);
            if (hash2Hex == null) {
                return null;
            }
            String substring = hash2Hex.substring(0, 32);
            tag_uafv1_signed_data.keyID = Utility.strToByte(substring);
            int updateOrAddCounter = signCounterManager.updateOrAddCounter("004B#0001", substring, rawKeyHandle2.Username);
            if (-1 == updateOrAddCounter) {
                return null;
            }
            tag_uafv1_signed_data.signCounter = updateOrAddCounter;
            tag_uafv1_signed_data.uvi = UAFTools.genUVI(substring, str);
            byte[] serialize = tag_uafv1_signed_data.serialize();
            if (list != null) {
                try {
                    if (list.size() == 3) {
                        if (Boolean.parseBoolean(list.get(2).data)) {
                            PKCS10CertificationRequest pKCS10CertificationRequest = new PKCS10CertificationRequest("SHA256withRSA", new X509Name("CN=" + rawKeyHandle2.Username + ",O=cfca" + System.currentTimeMillis() + ",OU=cfca,C=CN"), UAFTools.getUAuthKeyPairTEE(257, rawKeyHandle2.PrivateKey, StringTools.urlSafeBase64Enc(CryptoTools.hash(rawKeyHandle2.Username + "004B#0001" + this.signCommand.extensions.get(1).data, "SHA256")), str2).getPublic(), (ASN1Set) null, signature);
                            tag_extension = new TAG_EXTENSION();
                            tag_extension.id = MobileCertConstants.P10;
                            tag_extension.data = new String(Base64.encode(pKCS10CertificationRequest.getEncoded(), 0));
                            tag_uafv1_auth_assertion.signedData = tag_uafv1_signed_data;
                            tag_uafv1_auth_assertion.signature = bArr;
                            tag_uafv1_auth_assertion.extension = tag_extension;
                            signResponse.assertion = tag_uafv1_auth_assertion.serialize();
                            signResponse.statusCode = (short) 0;
                        } else {
                            signature.update(serialize);
                            bArr = signature.sign();
                            tag_extension = null;
                            tag_uafv1_auth_assertion.signedData = tag_uafv1_signed_data;
                            tag_uafv1_auth_assertion.signature = bArr;
                            tag_uafv1_auth_assertion.extension = tag_extension;
                            signResponse.assertion = tag_uafv1_auth_assertion.serialize();
                            signResponse.statusCode = (short) 0;
                        }
                    }
                } catch (Throwable th) {
                    TrackerUtil.appendException(BasicInformation.SCENE_TRANS_SIG_EXCEPTION, th);
                    return null;
                }
            }
            signature.update(serialize);
            bArr = signature.sign();
            tag_extension = null;
            tag_uafv1_auth_assertion.signedData = tag_uafv1_signed_data;
            tag_uafv1_auth_assertion.signature = bArr;
            tag_uafv1_auth_assertion.extension = tag_extension;
            signResponse.assertion = tag_uafv1_auth_assertion.serialize();
            signResponse.statusCode = (short) 0;
        }
        return signResponse;
    }

    @TargetApi(23)
    public FingerprintManager.CryptoObject genCryptoObject(Activity activity, IAuthPresenter iAuthPresenter, int i2, String str, String str2) {
        Signature signature;
        if (i2 == 2) {
            signature = Signature.getInstance("SHA256withECDSA");
        } else if (i2 != 4) {
            signature = null;
        } else {
            signature = Signature.getInstance("SHA256withRSA/PSS");
            signature.setParameter(new PSSParameterSpec(MGF1ParameterSpec.SHA256.getDigestAlgorithm(), "MGF1", MGF1ParameterSpec.SHA256, 32, 1));
        }
        signature.initSign(getPrivateHandle(activity, iAuthPresenter, i2, str, str2));
        return new FingerprintManager.CryptoObject(signature);
    }
}
