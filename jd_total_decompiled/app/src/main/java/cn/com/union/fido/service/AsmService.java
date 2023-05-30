package cn.com.union.fido.service;

import android.content.Context;
import android.os.Binder;
import cn.com.union.fido.bean.Extension;
import cn.com.union.fido.bean.Transaction;
import cn.com.union.fido.bean.asm.ASMRequest;
import cn.com.union.fido.bean.asm.ASMResponse;
import cn.com.union.fido.bean.asm.AppRegistration;
import cn.com.union.fido.bean.asm.AsmStatusCode;
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
import cn.com.union.fido.bean.authenticator.DisplayPNGCharacteristicsDescriptor;
import cn.com.union.fido.bean.authenticator.command.DeregisterCommand;
import cn.com.union.fido.bean.authenticator.command.DeregisterResponse;
import cn.com.union.fido.bean.authenticator.command.GetInfoCommand;
import cn.com.union.fido.bean.authenticator.command.GetInfoResponse;
import cn.com.union.fido.bean.authenticator.command.OpenSettingsCommand;
import cn.com.union.fido.bean.authenticator.command.OpenSettingsResponse;
import cn.com.union.fido.bean.authenticator.command.RegisterCommand;
import cn.com.union.fido.bean.authenticator.command.RegisterResponse;
import cn.com.union.fido.bean.authenticator.command.SignCommand;
import cn.com.union.fido.bean.authenticator.command.SignResponse;
import cn.com.union.fido.bean.authenticator.tag.TAG_AUTHENTICATOR_INFO;
import cn.com.union.fido.bean.authenticator.tag.TAG_AUTHENTICATOR_METADATA;
import cn.com.union.fido.bean.authenticator.tag.TAG_EXTENSION;
import cn.com.union.fido.bean.authenticator.tag.TAG_TC_DISPLAY_PNG_CHARACTERISTICS;
import cn.com.union.fido.bean.authenticator.tag.TAG_UAFV1_REG_ASSERTION;
import cn.com.union.fido.bean.authenticator.tag.TAG_USERNAME_AND_KEYHANDLE;
import cn.com.union.fido.bean.db.AuthenticationEntity;
import cn.com.union.fido.common.GlobalConfiguration;
import cn.com.union.fido.db.AuthenticationManager;
import cn.com.union.fido.util.CommonTools;
import cn.com.union.fido.util.CryptoTools;
import cn.com.union.fido.util.DateTools;
import cn.com.union.fido.util.StringTools;
import cn.com.union.fido.util.UAFTools;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class AsmService implements AsmStatusCode {
    static final int MSG_SAY_HELLO = 1;
    private static final String TAG = "AsmService";
    private AuthenticationManager authDBManager;
    private AuthenticatorService mAuthenticateService;
    Context mContext;

    /* renamed from: cn.com.union.fido.service.AsmService$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$cn$com$union$fido$bean$asm$Request;

        static {
            int[] iArr = new int[Request.values().length];
            $SwitchMap$cn$com$union$fido$bean$asm$Request = iArr;
            try {
                iArr[Request.GetInfo.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$cn$com$union$fido$bean$asm$Request[Request.Register.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$cn$com$union$fido$bean$asm$Request[Request.Authenticate.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$cn$com$union$fido$bean$asm$Request[Request.Deregister.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$cn$com$union$fido$bean$asm$Request[Request.GetRegistrations.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$cn$com$union$fido$bean$asm$Request[Request.OpenSettings.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$cn$com$union$fido$bean$asm$Request[Request.Commit.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public AsmService() {
        this.mAuthenticateService = new AuthenticatorService();
    }

    public AsmService(Context context) {
        this.mContext = context;
        this.mAuthenticateService = new AuthenticatorService(context);
        this.authDBManager = new AuthenticationManager(context.getApplicationContext());
    }

    /* JADX WARN: Code restructure failed: missing block: B:182:0x003c, code lost:
        if (r1.isSecondFactorOnly != false) goto L246;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private ASMResponse authenticateRequest(ASMRequest aSMRequest) {
        short s;
        ASMResponse aSMResponse = new ASMResponse();
        AuthenticatorInfo locateAuthenticator = GlobalConfiguration.locateAuthenticator(aSMRequest.authenticatorIndex.shortValue());
        short s2 = 1;
        if (locateAuthenticator != null) {
            AuthenticateIn authenticateIn = (AuthenticateIn) aSMRequest.getArgs();
            List<Extension> exts = aSMRequest.getExts();
            String str = authenticateIn.appID;
            String genKHAccessToken = UAFTools.genKHAccessToken(str, null, null, UAFTools.getCallerID(this.mContext, Binder.getCallingUid()), true);
            List<String> list = authenticateIn.keyIDs;
            if (CommonTools.isValidateList(list)) {
                ArrayList arrayList = new ArrayList();
                if (locateAuthenticator.isRoamingAuthenticator) {
                    for (String str2 : list) {
                        if (StringTools.isValidateString(str2)) {
                            arrayList.add(str2);
                        }
                    }
                } else {
                    Iterator<String> it = list.iterator();
                    while (it.hasNext()) {
                        AuthenticationEntity entity = this.authDBManager.getEntity(str, it.next());
                        if (entity != null) {
                            String keyHandle = entity.getKeyHandle();
                            if (StringTools.isValidateString(keyHandle)) {
                                arrayList.add(keyHandle);
                            }
                        }
                    }
                }
                if (CommonTools.isValidateList(arrayList)) {
                    AuthenticateOut authenticateOut = new AuthenticateOut();
                    authenticateOut.assertionScheme = locateAuthenticator.assertionScheme;
                    SignResponse triggerSignCommand = triggerSignCommand(locateAuthenticator, authenticateIn, genKHAccessToken, arrayList, null, exts);
                    short s3 = triggerSignCommand.statusCode;
                    short s4 = 5;
                    if (s3 == 0) {
                        List<TAG_USERNAME_AND_KEYHANDLE> list2 = triggerSignCommand.userNameAndKeyHandle;
                        if (locateAuthenticator.isSecondFactorOnly || !CommonTools.isValidateList(list2)) {
                            authenticateOut.assertion = StringTools.urlSafeBase64Enc(triggerSignCommand.assertion);
                            aSMResponse.statusCode = (short) 0;
                            aSMResponse.responseData = authenticateOut;
                        }
                        aSMResponse.statusCode = s4;
                    } else if (s3 == 5) {
                        s = 3;
                    } else if (s3 == 8) {
                        s = 4;
                    } else {
                        s = 16;
                        if (s3 == 16) {
                            s = 6;
                        } else if (s3 == 19) {
                            s = 9;
                        } else {
                            s2 = 20;
                            if (s3 != 20) {
                                s = 21;
                                if (s3 == 21) {
                                    s = 17;
                                } else {
                                    s4 = 22;
                                    if (s3 == 22) {
                                        s = 18;
                                    } else if (s3 == 23) {
                                        aSMResponse.statusCode = (short) 19;
                                    } else if (s3 != 24) {
                                        if (s3 != 25) {
                                            if (s3 != 32) {
                                                if (s3 == 33) {
                                                    aSMResponse.statusCode = (short) 23;
                                                } else {
                                                    aSMResponse.statusCode = s3;
                                                }
                                            }
                                            aSMResponse.statusCode = s4;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return aSMResponse;
                }
                s = 2;
            }
            aSMResponse.statusCode = s;
            return aSMResponse;
        }
        aSMResponse.statusCode = s2;
        return aSMResponse;
    }

    private void commitInRequest(ASMRequest aSMRequest) {
        CommitIn commitIn = (CommitIn) aSMRequest.getArgs();
        AuthenticationEntity authenticationEntity = new AuthenticationEntity(commitIn.aaid, commitIn.keyID, "ready");
        if (commitIn.commit) {
            this.authDBManager.updateStatus(authenticationEntity);
        } else {
            this.authDBManager.delEntityByAaid(commitIn.aaid, commitIn.keyID);
        }
    }

    private ASMResponse deregisterRequest(ASMRequest aSMRequest) {
        ASMResponse aSMResponse = new ASMResponse();
        short shortValue = aSMRequest.authenticatorIndex.shortValue();
        AuthenticatorInfo locateAuthenticator = GlobalConfiguration.locateAuthenticator(shortValue);
        if (locateAuthenticator != null) {
            DeregisterIn deregisterIn = (DeregisterIn) aSMRequest.getArgs();
            String str = deregisterIn.appID;
            String genKHAccessToken = UAFTools.genKHAccessToken(str, null, null, UAFTools.getCallerID(this.mContext, Binder.getCallingUid()), true);
            if (!locateAuthenticator.isRoamingAuthenticator) {
                AuthenticationEntity entity = this.authDBManager.getEntity(deregisterIn.appID, deregisterIn.keyID);
                r6 = entity != null ? entity.getUserName() : null;
                this.authDBManager.delEntityByAppID(deregisterIn.appID, deregisterIn.keyID);
            }
            DeregisterCommand deregisterCommand = new DeregisterCommand();
            deregisterCommand.authenticatorIndex = (byte) shortValue;
            deregisterCommand.appID = str;
            deregisterCommand.khAccessToken = genKHAccessToken;
            deregisterCommand.keyID = deregisterIn.keyID;
            if (r6 != null) {
                deregisterCommand.userName = r6;
            }
            byte[] process = this.mAuthenticateService.process(deregisterCommand.serialize(), R2.id.fl_inner);
            DeregisterResponse deregisterResponse = new DeregisterResponse();
            if (CommonTools.isValidateByteArray(process)) {
                deregisterResponse.deserialize(process);
                if (deregisterResponse.statusCode == 6) {
                    aSMResponse.statusCode = (short) 0;
                    return aSMResponse;
                }
            }
        }
        aSMResponse.statusCode = (short) 1;
        return aSMResponse;
    }

    private ASMResponse getInfoRequest() {
        ASMResponse aSMResponse = new ASMResponse();
        byte[] process = this.mAuthenticateService.process(new GetInfoCommand().serialize(), R2.id.fl_close);
        GetInfoResponse getInfoResponse = new GetInfoResponse();
        getInfoResponse.deserialize(process);
        if (getInfoResponse.statusCode == 0) {
            List<TAG_AUTHENTICATOR_INFO> list = getInfoResponse.authenticatorInfo;
            ArrayList arrayList = new ArrayList();
            arrayList.add(UAFTools.getAsmVersion());
            ArrayList arrayList2 = new ArrayList();
            for (TAG_AUTHENTICATOR_INFO tag_authenticator_info : list) {
                AuthenticatorInfo authenticatorInfo = new AuthenticatorInfo();
                authenticatorInfo.authenticatorIndex = tag_authenticator_info.authenticatorIndex;
                authenticatorInfo.asmVersions = arrayList;
                authenticatorInfo.isUserEnrolled = true;
                authenticatorInfo.hasSettings = false;
                authenticatorInfo.aaid = tag_authenticator_info.aaid;
                authenticatorInfo.assertionScheme = tag_authenticator_info.assertionScheme;
                TAG_AUTHENTICATOR_METADATA tag_authenticator_metadata = tag_authenticator_info.authenticatorMetadata;
                authenticatorInfo.authenticationAlgorithm = tag_authenticator_metadata.authenticationAlg;
                authenticatorInfo.attestationTypes = tag_authenticator_info.attestationType;
                authenticatorInfo.userVerification = tag_authenticator_metadata.userVerification;
                authenticatorInfo.keyProtection = tag_authenticator_metadata.keyProtection;
                authenticatorInfo.matcherProtection = tag_authenticator_metadata.matcherProtection;
                authenticatorInfo.attachmentHint = 1L;
                authenticatorInfo.isSecondFactorOnly = false;
                authenticatorInfo.isRoamingAuthenticator = false;
                authenticatorInfo.supportedExtensionIDs = tag_authenticator_info.supportedExtensionID;
                authenticatorInfo.tcDisplay = tag_authenticator_metadata.tcDisplay;
                authenticatorInfo.tcDisplayContentType = tag_authenticator_info.tcDisplayContentType;
                ArrayList arrayList3 = new ArrayList();
                for (TAG_TC_DISPLAY_PNG_CHARACTERISTICS tag_tc_display_png_characteristics : tag_authenticator_info.tcDisplayPNGCharacteristics) {
                    DisplayPNGCharacteristicsDescriptor displayPNGCharacteristicsDescriptor = new DisplayPNGCharacteristicsDescriptor();
                    displayPNGCharacteristicsDescriptor.width = tag_tc_display_png_characteristics.width;
                    displayPNGCharacteristicsDescriptor.height = tag_tc_display_png_characteristics.height;
                    displayPNGCharacteristicsDescriptor.bitDepth = tag_tc_display_png_characteristics.bitDepth;
                    byte b = tag_tc_display_png_characteristics.colorType;
                    displayPNGCharacteristicsDescriptor.colorType = b;
                    displayPNGCharacteristicsDescriptor.compression = b;
                    displayPNGCharacteristicsDescriptor.filter = tag_tc_display_png_characteristics.filter;
                    displayPNGCharacteristicsDescriptor.interlace = tag_tc_display_png_characteristics.interlace;
                    arrayList3.add(displayPNGCharacteristicsDescriptor);
                }
                authenticatorInfo.tcDisplayPNGCharacteristics = arrayList3;
                authenticatorInfo.title = "FIDO UAF1.0 Authenticator (ECDSA) for Android";
                authenticatorInfo.description = "FIDO UAF1.0 Authenticator (ECDSA) for Android";
                authenticatorInfo.icon = "data:image/png;base64,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";
                arrayList2.add(authenticatorInfo);
            }
            GetInfoOut getInfoOut = new GetInfoOut();
            getInfoOut.Authenticators = arrayList2;
            aSMResponse.responseData = getInfoOut;
            aSMResponse.statusCode = (short) 0;
        } else {
            aSMResponse.statusCode = (short) 1;
        }
        return aSMResponse;
    }

    private ASMResponse getRegistrationsRequest(ASMRequest aSMRequest) {
        short s;
        ASMResponse aSMResponse = new ASMResponse();
        AuthenticatorInfo locateAuthenticator = GlobalConfiguration.locateAuthenticator(aSMRequest.authenticatorIndex.shortValue());
        if (locateAuthenticator != null) {
            if (!locateAuthenticator.isRoamingAuthenticator) {
                String callerID = UAFTools.getCallerID(this.mContext, Binder.getCallingUid());
                ArrayList arrayList = new ArrayList();
                Map<String, List<String>> registrations = this.authDBManager.getRegistrations(callerID, locateAuthenticator.aaid);
                for (String str : registrations.keySet()) {
                    AppRegistration appRegistration = new AppRegistration();
                    appRegistration.appID = str;
                    appRegistration.keyIDs = registrations.get(str);
                    arrayList.add(appRegistration);
                }
                GetRegistrationsOut getRegistrationsOut = new GetRegistrationsOut();
                getRegistrationsOut.appRegs = arrayList;
                aSMResponse.responseData = getRegistrationsOut;
                s = 0;
            }
            return aSMResponse;
        }
        s = 1;
        aSMResponse.statusCode = s;
        return aSMResponse;
    }

    private ASMResponse openSettingsRequest(ASMRequest aSMRequest) {
        ASMResponse aSMResponse = new ASMResponse();
        short shortValue = aSMRequest.authenticatorIndex.shortValue();
        AuthenticatorInfo locateAuthenticator = GlobalConfiguration.locateAuthenticator(shortValue);
        if (locateAuthenticator != null && locateAuthenticator.hasSettings) {
            OpenSettingsCommand openSettingsCommand = new OpenSettingsCommand();
            openSettingsCommand.authenticatorIndex = (byte) shortValue;
            byte[] process = this.mAuthenticateService.process(openSettingsCommand.serialize(), R2.id.fl_iv_left_container);
            OpenSettingsResponse openSettingsResponse = new OpenSettingsResponse();
            if (CommonTools.isValidateByteArray(process)) {
                openSettingsResponse.deserialize(process);
                if (openSettingsResponse.statusCode == 6) {
                    aSMResponse.statusCode = (short) 0;
                    return aSMResponse;
                }
            }
        }
        aSMResponse.statusCode = (short) 1;
        return aSMResponse;
    }

    private ASMResponse registerRequest(ASMRequest aSMRequest) {
        ASMResponse aSMResponse = new ASMResponse();
        short shortValue = aSMRequest.authenticatorIndex.shortValue();
        AuthenticatorInfo locateAuthenticator = GlobalConfiguration.locateAuthenticator(shortValue);
        short s = 1;
        if (locateAuthenticator != null) {
            RegisterIn registerIn = (RegisterIn) aSMRequest.getArgs();
            String str = registerIn.appID;
            String callerID = UAFTools.getCallerID(this.mContext, Binder.getCallingUid());
            String genKHAccessToken = UAFTools.genKHAccessToken(str, null, null, callerID, true);
            RegisterCommand registerCommand = new RegisterCommand();
            registerCommand.authenticatorIndex = (byte) shortValue;
            registerCommand.appID = str;
            registerCommand.finalChallenge = CryptoTools.doHash(registerIn.finalChallenge.getBytes(), "SHA256");
            registerCommand.userName = registerIn.username;
            registerCommand.attestationType = registerIn.attestationType;
            registerCommand.khAccessToken = genKHAccessToken;
            List<Extension> exts = aSMRequest.getExts();
            if (exts != null) {
                ArrayList arrayList = new ArrayList();
                for (Extension extension : exts) {
                    TAG_EXTENSION tag_extension = new TAG_EXTENSION();
                    tag_extension.id = extension.getId();
                    tag_extension.data = extension.getData();
                    arrayList.add(tag_extension);
                }
                registerCommand.extensions = arrayList;
            }
            byte[] process = this.mAuthenticateService.process(registerCommand.serialize(), R2.id.fl_content);
            RegisterResponse registerResponse = new RegisterResponse();
            if (CommonTools.isValidateByteArray(process)) {
                registerResponse.deserialize(process);
            } else {
                registerResponse.statusCode = (short) 1;
            }
            short s2 = registerResponse.statusCode;
            if (s2 == 0) {
                byte[] bArr = registerResponse.assertion;
                TAG_UAFV1_REG_ASSERTION tag_uafv1_reg_assertion = new TAG_UAFV1_REG_ASSERTION();
                tag_uafv1_reg_assertion.deserialize(bArr);
                if (!locateAuthenticator.isRoamingAuthenticator) {
                    AuthenticationEntity authenticationEntity = new AuthenticationEntity();
                    authenticationEntity.setCallerID(callerID);
                    authenticationEntity.setAppID(str);
                    authenticationEntity.setKeyHandle(registerResponse.keyHandle);
                    authenticationEntity.setKeyID(StringTools.urlSafeBase64Enc(tag_uafv1_reg_assertion.uafv1Krd.keyID));
                    authenticationEntity.setCurrentTimestamp(DateTools.getCurrentTimeStamp());
                    authenticationEntity.setStatus("ready");
                    authenticationEntity.setAaid(locateAuthenticator.aaid);
                    authenticationEntity.setUserName(registerIn.username);
                    this.authDBManager.add(authenticationEntity);
                }
                RegisterOut registerOut = new RegisterOut();
                registerOut.assertionScheme = locateAuthenticator.assertionScheme;
                registerOut.assertion = StringTools.urlSafeBase64Enc(bArr);
                aSMResponse.statusCode = (short) 0;
                aSMResponse.responseData = registerOut;
            } else {
                if (s2 == 5) {
                    s2 = 3;
                } else if (s2 == 8) {
                    s2 = 4;
                } else {
                    short s3 = 16;
                    if (s2 == 16) {
                        s2 = 6;
                    } else if (s2 == 19) {
                        s2 = 9;
                    } else {
                        s = 20;
                        if (s2 != 20) {
                            s3 = 21;
                            if (s2 == 21) {
                                s2 = 17;
                            } else if (s2 == 22) {
                                s2 = 18;
                            } else if (s2 == 23) {
                                aSMResponse.statusCode = (short) 19;
                            } else if (s2 != 24) {
                                if (s2 != 25) {
                                    if (s2 == 32) {
                                        aSMResponse.statusCode = (short) 22;
                                    } else if (s2 == 33) {
                                        aSMResponse.statusCode = (short) 23;
                                    }
                                }
                            }
                        }
                        aSMResponse.statusCode = s3;
                    }
                }
                aSMResponse.statusCode = s2;
            }
            return aSMResponse;
        }
        aSMResponse.statusCode = s;
        return aSMResponse;
    }

    private SignResponse triggerSignCommand(AuthenticatorInfo authenticatorInfo, AuthenticateIn authenticateIn, String str, List<String> list, String str2, List<Extension> list2) {
        String str3;
        SignCommand signCommand = new SignCommand();
        signCommand.authenticatorIndex = (byte) authenticatorInfo.authenticatorIndex;
        signCommand.appID = authenticateIn.appID;
        signCommand.finalChallenge = CryptoTools.hash(authenticateIn.finalChallenge, "SHA256");
        List<Transaction> list3 = authenticateIn.transaction;
        if (CommonTools.isValidateList(list3)) {
            if (list3.size() == 1) {
                str3 = new String(StringTools.urlSafeBase64Dec(list3.get(0).getContent()));
            } else {
                CommonTools.isScreenOriatationPortrait(this.mContext);
                Iterator<Transaction> it = list3.iterator();
                while (it.hasNext()) {
                    it.next().getTcDisplayPNGCharacteristics();
                }
                str3 = new String(StringTools.urlSafeBase64Dec(list3.get(0).getContent()));
            }
            signCommand.transactionContent = str3;
        }
        signCommand.khAccessToken = str;
        signCommand.keyHandles = list;
        signCommand.userName = str2;
        if (list2 != null) {
            ArrayList arrayList = new ArrayList();
            for (Extension extension : list2) {
                TAG_EXTENSION tag_extension = new TAG_EXTENSION();
                tag_extension.id = extension.getId();
                tag_extension.data = extension.getData();
                arrayList.add(tag_extension);
            }
            signCommand.extensions = arrayList;
        }
        byte[] process = this.mAuthenticateService.process(signCommand.serialize(), R2.id.fl_device_item);
        SignResponse signResponse = new SignResponse();
        if (CommonTools.isValidateByteArray(process)) {
            signResponse.deserialize(process);
        } else {
            signResponse.statusCode = (short) 1;
        }
        return signResponse;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public ASMResponse process(ASMRequest aSMRequest) {
        switch (AnonymousClass1.$SwitchMap$cn$com$union$fido$bean$asm$Request[aSMRequest.requestType.ordinal()]) {
            case 1:
                return getInfoRequest();
            case 2:
                return registerRequest(aSMRequest);
            case 3:
                return authenticateRequest(aSMRequest);
            case 4:
                return deregisterRequest(aSMRequest);
            case 5:
                return getRegistrationsRequest(aSMRequest);
            case 6:
                return openSettingsRequest(aSMRequest);
            case 7:
                commitInRequest(aSMRequest);
                break;
        }
        return null;
    }

    public void setmContext(Context context) {
        this.mContext = context;
        this.mAuthenticateService.setmContext(context);
    }
}
