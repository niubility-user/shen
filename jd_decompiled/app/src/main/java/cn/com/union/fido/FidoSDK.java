package cn.com.union.fido;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import cn.com.union.fido.bean.Extension;
import cn.com.union.fido.bean.asm.ASMRequest;
import cn.com.union.fido.bean.asm.ASMResponse;
import cn.com.union.fido.bean.asm.AppRegistration;
import cn.com.union.fido.bean.asm.AuthenticateIn;
import cn.com.union.fido.bean.asm.AuthenticateOut;
import cn.com.union.fido.bean.asm.AuthenticatorInfo;
import cn.com.union.fido.bean.asm.CommitIn;
import cn.com.union.fido.bean.asm.DeregisterIn;
import cn.com.union.fido.bean.asm.GetInfoOut;
import cn.com.union.fido.bean.asm.GetRegistrationsOut;
import cn.com.union.fido.bean.asm.RegisterIn;
import cn.com.union.fido.bean.asm.RegisterOut;
import cn.com.union.fido.bean.asm.Request;
import cn.com.union.fido.bean.uafclient.AuthenticationRequest;
import cn.com.union.fido.bean.uafclient.AuthenticationResponse;
import cn.com.union.fido.bean.uafclient.Authenticator;
import cn.com.union.fido.bean.uafclient.AuthenticatorRegistrationAssertion;
import cn.com.union.fido.bean.uafclient.AuthenticatorSignAssertion;
import cn.com.union.fido.bean.uafclient.AuthenticatorsSucBean;
import cn.com.union.fido.bean.uafclient.DeregisterAuthenticator;
import cn.com.union.fido.bean.uafclient.DeregisterRequest;
import cn.com.union.fido.bean.uafclient.DescriptionBean;
import cn.com.union.fido.bean.uafclient.DiscoveryData;
import cn.com.union.fido.bean.uafclient.ErrorCode;
import cn.com.union.fido.bean.uafclient.Operation;
import cn.com.union.fido.bean.uafclient.OperationHeader;
import cn.com.union.fido.bean.uafclient.OperationRequest;
import cn.com.union.fido.bean.uafclient.RegistrationRequest;
import cn.com.union.fido.bean.uafclient.RegistrationResponse;
import cn.com.union.fido.bean.uafclient.TrustApps;
import cn.com.union.fido.bean.uafclient.TrustedFacets;
import cn.com.union.fido.bean.uafclient.UAFMessage;
import cn.com.union.fido.bean.uafclient.policy.Policy;
import cn.com.union.fido.bean.uafclient.tls.ChannelBinding;
import cn.com.union.fido.common.Constance;
import cn.com.union.fido.common.GlobalConfiguration;
import cn.com.union.fido.service.AsmService;
import cn.com.union.fido.util.CommonTools;
import cn.com.union.fido.util.CryptoTools;
import cn.com.union.fido.util.StringTools;
import cn.com.union.fido.util.UAFTools;
import cn.com.union.fido.util.policy.PolicyHandler;
import cn.com.union.fido.util.uaf.HeaderValid;
import cn.com.union.fido.util.uaf.RequestValid;
import com.coremedia.iso.boxes.AuthorBox;
import com.google.gson.JsonArray;
import com.jd.dynamic.DYConstants;
import com.jdcn.fido.constant.BasicInformation;
import com.jdcn.fido.http.HttpUtil;
import com.jdjr.risk.jdcn.common.utils.FsGsonUtil;
import com.wangyin.platform.CryptoUtils;
import java.io.ByteArrayInputStream;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.ShortCompanionObject;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class FidoSDK implements ErrorCode {
    private static final String TAG = "FidoSDK";
    private static volatile FidoSDK fidoSDK;
    private AsmService mAsmService;
    Context mContext;
    private TrustApps trustApps;
    private String appID = null;
    private String facet_id = null;
    int sampleAuthIndex = 0;

    private FidoSDK(Context context) {
        this.mContext = context;
        this.mAsmService = new AsmService(context);
        genFacetID();
        CryptoUtils.newInstance(context).startAutoHandshake();
    }

    private List<Extension> genExts(OperationRequest operationRequest) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject(new String(Base64.decode(operationRequest.getHeader().getServerData(), 0)));
            String optString = jSONObject.optString("downloadCer");
            Extension extension = new Extension();
            extension.setId("downloadCer");
            extension.setData(DYConstants.DY_FALSE);
            if (!TextUtils.isEmpty(optString)) {
                extension.setData(optString);
            }
            arrayList.add(extension);
            String optString2 = jSONObject.optString("authType");
            Extension extension2 = new Extension();
            extension2.setId("authType");
            extension2.setData("");
            if (!TextUtils.isEmpty(optString)) {
                extension2.setData(optString2);
            }
            arrayList.add(extension2);
            arrayList.addAll(operationRequest.getHeader().getExts());
        } catch (Throwable unused) {
        }
        return arrayList;
    }

    private String genFacetID() {
        try {
            Activity activity = (Activity) this.mContext;
            String packageName = activity.getPackageName();
            if (packageName != null) {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(activity.getPackageManager().getPackageInfo(packageName, 64).signatures[0].toByteArray());
                this.facet_id = "android:apk-key-hash:" + Base64.encodeToString(MessageDigest.getInstance("SHA1").digest(((X509Certificate) CertificateFactory.getInstance("X509").generateCertificate(byteArrayInputStream)).getEncoded()), 3);
            }
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
        } catch (NoSuchAlgorithmException e3) {
            e3.printStackTrace();
        } catch (CertificateException e4) {
            e4.printStackTrace();
        }
        return this.facet_id;
    }

    public static final FidoSDK getInstance(Context context) {
        if (fidoSDK == null) {
            synchronized (FidoSDK.class) {
                if (fidoSDK == null) {
                    fidoSDK = new FidoSDK(context);
                    GlobalConfiguration.getAuthenticatorInfo(context);
                    GlobalConfiguration.getSeriNumEID(context);
                }
            }
        }
        fidoSDK.mContext = context;
        fidoSDK.mAsmService.setmContext(context);
        return fidoSDK;
    }

    private List<String> getRegKeyIDs(AuthenticatorInfo authenticatorInfo, Policy policy) {
        new ArrayList();
        return authenticatorInfo.isRoamingAuthenticator ? PolicyHandler.getAcceptedKeyIDs(policy, authenticatorInfo.aaid) : getRegistrationInfo(authenticatorInfo.authenticatorIndex, this.appID);
    }

    private List<String> getRegistrationInfo(short s, String str) {
        ASMRequest aSMRequest = new ASMRequest();
        aSMRequest.requestType = Request.GetRegistrations;
        aSMRequest.asmVersion = UAFTools.getAsmVersion();
        aSMRequest.authenticatorIndex = Short.valueOf(s);
        ASMResponse process = this.mAsmService.process(aSMRequest);
        List<String> arrayList = new ArrayList<>();
        Object responseData = process.getResponseData();
        if (responseData != null) {
            for (AppRegistration appRegistration : ((GetRegistrationsOut) responseData).appRegs) {
                String str2 = appRegistration.appID;
                if (str2 != null && str2.equals(str)) {
                    arrayList = appRegistration.keyIDs;
                }
            }
        }
        return arrayList;
    }

    private void getTrustApps() {
        try {
            String httpGet = HttpUtil.httpGet(this.appID);
            if (httpGet == null || httpGet.length() <= 0) {
                return;
            }
            this.trustApps = (TrustApps) FsGsonUtil.gsonToBean(httpGet, TrustApps.class);
        } catch (Exception unused) {
            this.trustApps = null;
        }
    }

    public static boolean isFingersChange(String str, String str2) {
        String urlSafeBase64Enc = StringTools.urlSafeBase64Enc(CryptoTools.hash(str + "004B#0001" + str2, "SHA256"));
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            Signature.getInstance("SHA256withRSA").initSign((PrivateKey) keyStore.getKey(urlSafeBase64Enc, null));
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return true;
        }
    }

    private short processingAuthenticationReq(String str, String str2, UAFMessage uAFMessage, UAFMessage uAFMessage2, Handler handler, Handler handler2) {
        AuthenticationRequest authenticationRequest = (AuthenticationRequest) FsGsonUtil.gsonToBean(str, AuthenticationRequest.class);
        short validOPRequest = RequestValid.validOPRequest(authenticationRequest);
        if (validOPRequest != 0) {
            return validOPRequest;
        }
        OperationHeader header = authenticationRequest.getHeader();
        String appID = header.getAppID();
        this.appID = appID;
        if (!StringTools.isValidateString(appID) || this.appID.equalsIgnoreCase(this.facet_id)) {
            this.appID = this.facet_id;
            this.trustApps = new TrustApps();
            ArrayList arrayList = new ArrayList();
            arrayList.add(this.appID);
            TrustedFacets trustedFacets = new TrustedFacets();
            trustedFacets.setIds(arrayList);
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(trustedFacets);
            this.trustApps.setTrustedFacets(arrayList2);
        } else {
            getTrustApps();
        }
        if (TextUtils.equals(Thread.currentThread().getName(), BasicInformation.THREAD_NAME)) {
            return (short) 24;
        }
        TrustApps trustApps = this.trustApps;
        if (trustApps != null) {
            if (trustApps.checkOrigin(this.facet_id)) {
                Policy policy = authenticationRequest.getPolicy();
                List<AuthenticatorInfo> list = GlobalConfiguration.authenticators;
                ArrayList arrayList3 = new ArrayList();
                if (list != null) {
                    for (AuthenticatorInfo authenticatorInfo : list) {
                        AuthenticatorInfo allowedAuthenticator = PolicyHandler.getAllowedAuthenticator(policy, authenticatorInfo, getRegKeyIDs(authenticatorInfo, authenticationRequest.getPolicy()), true);
                        if (allowedAuthenticator != null) {
                            arrayList3.add(allowedAuthenticator);
                        }
                    }
                }
                ArrayList arrayList4 = new ArrayList();
                int size = arrayList3.size();
                if (size > 0 && size <= 1) {
                    this.sampleAuthIndex = 0;
                    ChannelBinding channelBinding = new ChannelBinding();
                    channelBinding.fromJSON("{}");
                    String genFinalChallengeParams = UAFTools.genFinalChallengeParams(this.appID, authenticationRequest.getChallenge(), this.facet_id, channelBinding);
                    ASMResponse sendAuthentication2ASM = sendAuthentication2ASM((AuthenticatorInfo) arrayList3.get(this.sampleAuthIndex), authenticationRequest, genFinalChallengeParams);
                    short s = sendAuthentication2ASM.statusCode;
                    if (s == 0) {
                        Object responseData = sendAuthentication2ASM.getResponseData();
                        if (responseData != null) {
                            AuthenticateOut authenticateOut = (AuthenticateOut) responseData;
                            AuthenticatorSignAssertion authenticatorSignAssertion = new AuthenticatorSignAssertion();
                            authenticatorSignAssertion.setAssertion(authenticateOut.assertion);
                            authenticatorSignAssertion.setAssertionScheme(authenticateOut.assertionScheme);
                            arrayList4.add(authenticatorSignAssertion);
                            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
                            authenticationResponse.setHeader(header);
                            authenticationResponse.setFcParams(genFinalChallengeParams);
                            authenticationResponse.setAssertions(arrayList4);
                            ArrayList arrayList5 = new ArrayList();
                            arrayList5.add(authenticationResponse);
                            uAFMessage.uafProtocolMessage = FsGsonUtil.gsonString(arrayList5);
                            return (short) 0;
                        }
                        return ErrorCode.UNKNOWN;
                    } else if (s == 2) {
                        return (short) 8;
                    } else {
                        if (s == 3) {
                            return (short) 3;
                        }
                        if (s == 4) {
                            return (short) 16;
                        }
                        if (s == 5) {
                            return (short) 17;
                        }
                        if (s == 6) {
                            return (short) 18;
                        }
                        if (s == 9) {
                            return (short) 19;
                        }
                        if (s == 16) {
                            return (short) 22;
                        }
                        if (s == 17) {
                            return (short) 32;
                        }
                        if (s == 18) {
                            return (short) 33;
                        }
                        if (s == 19) {
                            return (short) 34;
                        }
                        if (s == 20) {
                            return (short) 35;
                        }
                        if (s == 21) {
                            return (short) 36;
                        }
                        if (s == 22) {
                            return (short) 23;
                        }
                        if (s == 23) {
                            return (short) 24;
                        }
                        return ErrorCode.UNKNOWN;
                    }
                }
                return (short) 5;
            }
            return (short) 7;
        }
        return (short) 9;
    }

    private short processingDeregistrationReq(String str, String str2, UAFMessage uAFMessage, UAFMessage uAFMessage2, Handler handler, Handler handler2) {
        DeregisterRequest deregisterRequest = (DeregisterRequest) FsGsonUtil.gsonToBean(str, DeregisterRequest.class);
        this.appID = deregisterRequest.getHeader().getAppID();
        short validOPRequest = RequestValid.validOPRequest(deregisterRequest);
        if (validOPRequest != 0) {
            return validOPRequest;
        }
        if (!StringTools.isValidateString(this.appID) || this.appID.equalsIgnoreCase(this.facet_id)) {
            this.appID = this.facet_id;
            this.trustApps = new TrustApps();
            ArrayList arrayList = new ArrayList();
            arrayList.add(this.appID);
            TrustedFacets trustedFacets = new TrustedFacets();
            trustedFacets.setIds(arrayList);
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(trustedFacets);
            this.trustApps.setTrustedFacets(arrayList2);
        } else {
            getTrustApps();
        }
        TrustApps trustApps = this.trustApps;
        if (trustApps != null) {
            if (trustApps.checkOrigin(this.facet_id)) {
                Iterator<DeregisterAuthenticator> it = deregisterRequest.getAuthenticators().iterator();
                if (it.hasNext()) {
                    DeregisterAuthenticator next = it.next();
                    if (sendDeregister2ASM(GlobalConfiguration.locateAuthenticator(next.getAaid()).authenticatorIndex, this.appID, next.getKeyID()).statusCode == 0) {
                        return (short) 0;
                    }
                    return ErrorCode.UNKNOWN;
                }
                return (short) 5;
            }
            return (short) 7;
        }
        return (short) 9;
    }

    private short processingRegistrationReq(String str, String str2, UAFMessage uAFMessage, UAFMessage uAFMessage2, Handler handler, Handler handler2) {
        RegistrationRequest registrationRequest = (RegistrationRequest) FsGsonUtil.gsonToBean(str, RegistrationRequest.class);
        short validOPRequest = RequestValid.validOPRequest(registrationRequest);
        if (validOPRequest != 0) {
            return validOPRequest;
        }
        OperationHeader header = registrationRequest.getHeader();
        String appID = header.getAppID();
        this.appID = appID;
        if (!StringTools.isValidateString(appID) || this.appID.equalsIgnoreCase(this.facet_id)) {
            this.appID = this.facet_id;
            this.trustApps = new TrustApps();
            ArrayList arrayList = new ArrayList();
            arrayList.add(this.appID);
            TrustedFacets trustedFacets = new TrustedFacets();
            trustedFacets.setIds(arrayList);
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(trustedFacets);
            this.trustApps.setTrustedFacets(arrayList2);
        } else {
            getTrustApps();
        }
        if (TextUtils.equals(Thread.currentThread().getName(), BasicInformation.THREAD_NAME)) {
            return (short) 24;
        }
        TrustApps trustApps = this.trustApps;
        if (trustApps != null) {
            if (trustApps.checkOrigin(this.facet_id)) {
                Policy policy = registrationRequest.getPolicy();
                List<AuthenticatorInfo> list = GlobalConfiguration.authenticators;
                ArrayList arrayList3 = new ArrayList();
                if (list != null) {
                    for (AuthenticatorInfo authenticatorInfo : list) {
                        AuthenticatorInfo allowedAuthenticator = PolicyHandler.getAllowedAuthenticator(policy, authenticatorInfo, getRegKeyIDs(authenticatorInfo, registrationRequest.getPolicy()), false);
                        if (allowedAuthenticator != null) {
                            arrayList3.add(allowedAuthenticator);
                        }
                    }
                }
                ArrayList arrayList4 = new ArrayList();
                int size = arrayList3.size();
                if (size > 0 && size <= 1) {
                    AuthenticatorInfo authenticatorInfo2 = (AuthenticatorInfo) arrayList3.get(this.sampleAuthIndex);
                    this.sampleAuthIndex = 0;
                    ChannelBinding channelBinding = new ChannelBinding();
                    channelBinding.fromJSON("{}");
                    String genFinalChallengeParams = UAFTools.genFinalChallengeParams(this.appID, registrationRequest.getChallenge(), this.facet_id, channelBinding);
                    ASMResponse sendRegister2ASM = sendRegister2ASM(authenticatorInfo2, registrationRequest, genFinalChallengeParams);
                    short s = sendRegister2ASM.statusCode;
                    if (s == 0) {
                        Object responseData = sendRegister2ASM.getResponseData();
                        if (responseData != null) {
                            RegisterOut registerOut = (RegisterOut) responseData;
                            AuthenticatorRegistrationAssertion authenticatorRegistrationAssertion = new AuthenticatorRegistrationAssertion();
                            authenticatorRegistrationAssertion.setAssertion(registerOut.assertion);
                            authenticatorRegistrationAssertion.setAssertionScheme(registerOut.assertionScheme);
                            authenticatorRegistrationAssertion.setTcDisplayPNGCharacteristics(authenticatorInfo2.tcDisplayPNGCharacteristics);
                            arrayList4.add(authenticatorRegistrationAssertion);
                            RegistrationResponse registrationResponse = new RegistrationResponse();
                            registrationResponse.setHeader(header);
                            registrationResponse.setFcParams(genFinalChallengeParams);
                            registrationResponse.setAssertions(arrayList4);
                            ArrayList arrayList5 = new ArrayList();
                            arrayList5.add(registrationResponse);
                            uAFMessage.uafProtocolMessage = FsGsonUtil.gsonString(arrayList5);
                            return (short) 0;
                        }
                        return ErrorCode.UNKNOWN;
                    } else if (s == 2) {
                        return (short) 8;
                    } else {
                        if (s == 3) {
                            return (short) 3;
                        }
                        if (s == 4) {
                            return (short) 16;
                        }
                        if (s == 5) {
                            return (short) 17;
                        }
                        if (s == 6) {
                            return (short) 18;
                        }
                        if (s == 9) {
                            return (short) 19;
                        }
                        if (s == 16) {
                            return (short) 22;
                        }
                        if (s == 17) {
                            return (short) 32;
                        }
                        if (s == 18) {
                            return (short) 33;
                        }
                        if (s == 19) {
                            return (short) 34;
                        }
                        if (s == 20) {
                            return (short) 35;
                        }
                        if (s == 21) {
                            return (short) 36;
                        }
                        if (s == 22) {
                            return (short) 23;
                        }
                        if (s == 23) {
                            return (short) 24;
                        }
                        return ErrorCode.UNKNOWN;
                    }
                }
                return (short) 5;
            }
            return (short) 7;
        }
        return (short) 9;
    }

    private ASMResponse sendAuthentication2ASM(AuthenticatorInfo authenticatorInfo, AuthenticationRequest authenticationRequest, String str) {
        ASMRequest aSMRequest = new ASMRequest();
        aSMRequest.requestType = Request.Authenticate;
        aSMRequest.asmVersion = UAFTools.getAsmVersion();
        aSMRequest.authenticatorIndex = Short.valueOf(authenticatorInfo.authenticatorIndex);
        List<Extension> genExts = genExts(authenticationRequest);
        if (genExts != null) {
            aSMRequest.exts = genExts;
        }
        List<String> acceptedKeyIDs = PolicyHandler.getAcceptedKeyIDs(authenticationRequest.getPolicy(), authenticatorInfo.aaid);
        AuthenticateIn authenticateIn = new AuthenticateIn();
        authenticateIn.appID = this.appID;
        authenticateIn.keyIDs = acceptedKeyIDs;
        authenticateIn.transaction = authenticationRequest.getTransaction();
        authenticateIn.finalChallenge = str;
        aSMRequest.args = authenticateIn;
        if (TextUtils.equals(Thread.currentThread().getName(), BasicInformation.THREAD_NAME)) {
            ASMResponse aSMResponse = new ASMResponse();
            aSMResponse.statusCode = (short) 23;
            return aSMResponse;
        }
        return this.mAsmService.process(aSMRequest);
    }

    private ASMResponse sendDeregister2ASM(short s, String str, String str2) {
        ASMRequest aSMRequest = new ASMRequest();
        aSMRequest.requestType = Request.Deregister;
        aSMRequest.asmVersion = UAFTools.getAsmVersion();
        aSMRequest.authenticatorIndex = Short.valueOf(s);
        DeregisterIn deregisterIn = new DeregisterIn();
        deregisterIn.appID = str;
        deregisterIn.keyID = str2;
        aSMRequest.args = deregisterIn;
        return this.mAsmService.process(aSMRequest);
    }

    private ASMResponse sendRegister2ASM(AuthenticatorInfo authenticatorInfo, RegistrationRequest registrationRequest, String str) {
        ASMRequest aSMRequest = new ASMRequest();
        aSMRequest.requestType = Request.Register;
        aSMRequest.asmVersion = UAFTools.getAsmVersion();
        aSMRequest.authenticatorIndex = Short.valueOf(authenticatorInfo.authenticatorIndex);
        List<Extension> genExts = genExts(registrationRequest);
        if (genExts != null) {
            aSMRequest.exts = genExts;
        }
        RegisterIn registerIn = new RegisterIn();
        registerIn.appID = this.appID;
        registerIn.username = registrationRequest.getUsername();
        registerIn.finalChallenge = str;
        registerIn.attestationType = authenticatorInfo.attestationTypes.get(0).shortValue();
        aSMRequest.args = registerIn;
        if (TextUtils.equals(Thread.currentThread().getName(), BasicInformation.THREAD_NAME)) {
            ASMResponse aSMResponse = new ASMResponse();
            aSMResponse.statusCode = (short) 23;
            return aSMResponse;
        }
        return this.mAsmService.process(aSMRequest);
    }

    public void checkPolicy(UAFMessage uAFMessage, Handler handler) {
        String jsonElement;
        OperationRequest parseOPRequestMessage;
        String str = uAFMessage.uafProtocolMessage;
        if (StringTools.isValidateString(str)) {
            ArrayList arrayList = new ArrayList();
            JsonArray jsonToJsonArray = FsGsonUtil.jsonToJsonArray(str);
            short s = 5;
            if (jsonToJsonArray.size() != 1 || (parseOPRequestMessage = RequestValid.parseOPRequestMessage((jsonElement = jsonToJsonArray.get(0).getAsJsonObject().toString()))) == null) {
                s = 6;
            } else {
                OperationHeader header = parseOPRequestMessage.getHeader();
                short validOPHeader = HeaderValid.validOPHeader(header, this.facet_id);
                if (validOPHeader == 0) {
                    String op = header.getOp();
                    if (op.equals(Operation.Reg.name()) && (validOPHeader = RequestValid.validOPRequest((RegistrationRequest) FsGsonUtil.gsonToBean(jsonElement, RegistrationRequest.class))) == 0) {
                        List<AuthenticatorInfo> list = GlobalConfiguration.authenticators;
                        Policy policy = parseOPRequestMessage.getPolicy();
                        String appID = header.getAppID();
                        this.appID = appID;
                        if (!StringTools.isValidateString(appID)) {
                            this.appID = genFacetID();
                        }
                        if (list != null) {
                            for (AuthenticatorInfo authenticatorInfo : list) {
                                AuthenticatorInfo allowedAuthenticator = PolicyHandler.getAllowedAuthenticator(policy, authenticatorInfo, getRegKeyIDs(authenticatorInfo, policy), false);
                                if (allowedAuthenticator != null) {
                                    arrayList.add(allowedAuthenticator);
                                }
                            }
                        }
                        if (CommonTools.isValidateList(arrayList)) {
                            validOPHeader = ShortCompanionObject.MIN_VALUE;
                            while (arrayList.iterator().hasNext()) {
                                validOPHeader = (short) (validOPHeader | ((short) ((AuthenticatorInfo) r8.next()).userVerification));
                            }
                        } else {
                            validOPHeader = 5;
                        }
                    }
                    if (op.equals(Operation.Auth.name())) {
                        short validOPRequest = RequestValid.validOPRequest((AuthenticationRequest) FsGsonUtil.gsonToBean(jsonElement, AuthenticationRequest.class));
                        if (validOPRequest == 0) {
                            List<AuthenticatorInfo> list2 = GlobalConfiguration.authenticators;
                            Policy policy2 = parseOPRequestMessage.getPolicy();
                            String appID2 = header.getAppID();
                            this.appID = appID2;
                            if (!StringTools.isValidateString(appID2)) {
                                this.appID = genFacetID();
                            }
                            ArrayList arrayList2 = new ArrayList();
                            if (list2 != null) {
                                for (AuthenticatorInfo authenticatorInfo2 : list2) {
                                    AuthenticatorInfo allowedAuthenticator2 = PolicyHandler.getAllowedAuthenticator(policy2, authenticatorInfo2, getRegKeyIDs(authenticatorInfo2, policy2), true, arrayList2);
                                    if (allowedAuthenticator2 != null) {
                                        arrayList.add(allowedAuthenticator2);
                                    }
                                }
                            }
                            if (CommonTools.isValidateList(arrayList)) {
                                s = arrayList2.size() == 2 ? (short) 48 : validOPRequest;
                                if (arrayList2.size() == 1) {
                                    if (((Extension) arrayList2.get(0)).getData().equals("")) {
                                        s = 0;
                                    }
                                    if (((Extension) arrayList2.get(0)).getData().equals(AuthorBox.TYPE)) {
                                        s = 49;
                                    }
                                    if (((Extension) arrayList2.get(0)).getData().equals("transaction")) {
                                        s = 50;
                                    }
                                }
                            }
                        } else {
                            s = validOPRequest;
                        }
                    }
                }
                s = validOPHeader;
            }
            Message message = new Message();
            Bundle bundle = new Bundle();
            bundle.putShort("ERROR", s);
            message.setData(bundle);
            handler.sendMessage(message);
        }
    }

    public void discover(Handler handler, Handler handler2) {
        DiscoveryData discoveryData = new DiscoveryData();
        ArrayList arrayList = new ArrayList();
        arrayList.add(UAFTools.getUAFVersion());
        discoveryData.supportedUAFVersions = arrayList;
        discoveryData.clientVendor = Constance.CLIENT_VENDOR;
        discoveryData.clientVersion = UAFTools.getClientVersion();
        ASMRequest aSMRequest = new ASMRequest();
        aSMRequest.requestType = Request.GetInfo;
        Object responseData = this.mAsmService.process(aSMRequest).getResponseData();
        List<AuthenticatorInfo> list = responseData != null ? ((GetInfoOut) responseData).Authenticators : null;
        ArrayList arrayList2 = new ArrayList();
        for (AuthenticatorInfo authenticatorInfo : list) {
            Authenticator authenticator = new Authenticator();
            authenticator.aaid = authenticatorInfo.aaid;
            authenticator.assertionScheme = authenticatorInfo.assertionScheme;
            authenticator.attachmentHint = authenticatorInfo.attachmentHint;
            authenticator.attestationTypes = authenticatorInfo.attestationTypes;
            authenticator.description = authenticatorInfo.description;
            authenticator.icon = authenticatorInfo.icon;
            authenticator.isSecondFactorOnly = authenticatorInfo.isSecondFactorOnly;
            authenticator.keyProtection = authenticatorInfo.keyProtection;
            authenticator.matcherProtection = authenticatorInfo.matcherProtection;
            authenticator.supportedExtensionIDs = authenticatorInfo.supportedExtensionIDs;
            authenticator.supportedUAFVersions = arrayList;
            authenticator.tcDisplay = authenticatorInfo.tcDisplay;
            authenticator.tcDisplayContentType = authenticatorInfo.tcDisplayContentType;
            authenticator.tcDisplayPNGCharacteristics = authenticatorInfo.tcDisplayPNGCharacteristics;
            authenticator.title = authenticatorInfo.title;
            authenticator.userVerification = authenticatorInfo.userVerification;
            arrayList2.add(authenticator);
        }
        discoveryData.availableAuthenticators = arrayList2;
        handler.obtainMessage(0, discoveryData).sendToTarget();
    }

    public int notifyUAFResult(UAFMessage uAFMessage) {
        String str = uAFMessage.uafProtocolMessage;
        if (StringTools.isValidateString(str)) {
            AuthenticatorsSucBean authenticatorsSucBean = ((DescriptionBean) FsGsonUtil.gsonToBean(str, DescriptionBean.class)).authenticatorsSucceeded;
            ASMRequest aSMRequest = new ASMRequest();
            aSMRequest.asmVersion = UAFTools.getAsmVersion();
            aSMRequest.requestType = Request.Commit;
            CommitIn commitIn = new CommitIn();
            commitIn.aaid = authenticatorsSucBean.getAaid();
            commitIn.keyID = authenticatorsSucBean.getKeyID();
            commitIn.commit = true;
            aSMRequest.args = commitIn;
            this.mAsmService.process(aSMRequest);
            return 0;
        }
        return 6;
    }

    public void processUAFOperation(UAFMessage uAFMessage, Handler handler, Handler handler2) {
        UAFMessage uAFMessage2 = new UAFMessage();
        short sendUAFMessage = sendUAFMessage(uAFMessage, uAFMessage2, handler, handler2);
        if (sendUAFMessage == 0) {
            handler.obtainMessage(0, uAFMessage2).sendToTarget();
            return;
        }
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putShort("ERROR", sendUAFMessage);
        message.setData(bundle);
        handler2.sendMessage(message);
    }

    public void release() {
        this.mContext = null;
        this.mAsmService.setmContext(null);
    }

    public short sendUAFMessage(UAFMessage uAFMessage, UAFMessage uAFMessage2, Handler handler, Handler handler2) {
        String jsonElement;
        OperationRequest parseOPRequestMessage;
        if (TextUtils.equals(Thread.currentThread().getName(), BasicInformation.THREAD_NAME)) {
            return (short) 24;
        }
        String str = uAFMessage.uafProtocolMessage;
        String str2 = uAFMessage.additionalData;
        if (StringTools.isValidateString(str)) {
            JsonArray jsonToJsonArray = FsGsonUtil.jsonToJsonArray(str);
            if (jsonToJsonArray.size() != 1 || (parseOPRequestMessage = RequestValid.parseOPRequestMessage((jsonElement = jsonToJsonArray.get(0).getAsJsonObject().toString()))) == null) {
                return (short) 6;
            }
            OperationHeader header = parseOPRequestMessage.getHeader();
            short validOPHeader = HeaderValid.validOPHeader(header, this.facet_id);
            if (validOPHeader == 0) {
                String op = header.getOp();
                if (op.equals(Operation.Reg.name())) {
                    return processingRegistrationReq(jsonElement, str2, uAFMessage2, uAFMessage, handler, handler2);
                }
                if (op.equals(Operation.Auth.name())) {
                    return processingAuthenticationReq(jsonElement, str2, uAFMessage2, uAFMessage, handler, handler2);
                }
                if (op.equals(Operation.Dereg.name())) {
                    return processingDeregistrationReq(jsonElement, str2, uAFMessage2, uAFMessage, handler, handler2);
                }
            }
            return validOPHeader;
        }
        return (short) 6;
    }
}
