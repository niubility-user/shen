package cn.com.union.fido.service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import cn.com.union.fido.bean.authenticator.AuthenticatorStatusCode;
import cn.com.union.fido.bean.authenticator.command.DeregisterCommand;
import cn.com.union.fido.bean.authenticator.command.DeregisterResponse;
import cn.com.union.fido.bean.authenticator.command.GetInfoResponse;
import cn.com.union.fido.bean.authenticator.command.OpenSettingsCommand;
import cn.com.union.fido.bean.authenticator.command.OpenSettingsResponse;
import cn.com.union.fido.bean.authenticator.command.RegisterCommand;
import cn.com.union.fido.bean.authenticator.command.RegisterResponse;
import cn.com.union.fido.bean.authenticator.command.SignCommand;
import cn.com.union.fido.bean.authenticator.command.SignResponse;
import cn.com.union.fido.bean.authenticator.tag.TAG_AUTHENTICATOR_INFO;
import cn.com.union.fido.bean.authenticator.tag.TAG_AUTHENTICATOR_METADATA;
import cn.com.union.fido.bean.uafclient.Operation;
import cn.com.union.fido.common.FingerAuthenticatorInfo;
import cn.com.union.fido.ui.FIDOUISDK;
import cn.com.union.fido.ui.FidoPresenter;
import cn.com.union.fido.ui.finger.FingerActivity;
import cn.com.union.fido.util.CryptoTools;
import cn.com.union.fido.util.StringTools;
import cn.com.union.fido.util.UAFTools;
import com.jdcn.fido.constant.BasicInformation;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class AuthenticatorService implements AuthenticatorStatusCode {
    private static final String TAG = "AuthenticatorService";
    Context mContext;

    public AuthenticatorService() {
    }

    public AuthenticatorService(Context context) {
        this.mContext = context;
    }

    private byte[] deregisterCommand(byte[] bArr) {
        DeregisterResponse deregisterResponse = new DeregisterResponse();
        DeregisterCommand deregisterCommand = new DeregisterCommand();
        deregisterCommand.deserialize(bArr);
        String str = deregisterCommand.khAccessToken;
        String str2 = deregisterCommand.appID;
        if (deregisterCommand.authenticatorIndex == 0) {
            if (StringTools.isValidateString(str2)) {
                deregisterCommand.khAccessToken = UAFTools.mixKHATokenWithAppID(str, str2);
            }
            CryptoTools.delKeyInTee(deregisterCommand.userName + "004B#0001");
            deregisterResponse.statusCode = (short) 6;
        }
        return deregisterResponse.serialize();
    }

    private byte[] getInfo() {
        GetInfoResponse getInfoResponse = new GetInfoResponse();
        getInfoResponse.statusCode = (short) 0;
        getInfoResponse.apiVersion = (byte) 1;
        ArrayList arrayList = new ArrayList();
        if (Build.VERSION.SDK_INT >= 23) {
            TAG_AUTHENTICATOR_INFO tag_authenticator_info = new TAG_AUTHENTICATOR_INFO();
            tag_authenticator_info.authenticatorIndex = (byte) 0;
            tag_authenticator_info.aaid = "004B#0001";
            TAG_AUTHENTICATOR_METADATA tag_authenticator_metadata = new TAG_AUTHENTICATOR_METADATA();
            tag_authenticator_metadata.authenticatorType = (short) 0;
            tag_authenticator_metadata.maxKeyHandles = (byte) 8;
            tag_authenticator_metadata.userVerification = 2;
            tag_authenticator_metadata.keyProtection = (short) 1;
            tag_authenticator_metadata.matcherProtection = (short) 1;
            tag_authenticator_metadata.tcDisplay = (short) 1;
            tag_authenticator_metadata.authenticationAlg = FingerAuthenticatorInfo.authenticationAlg;
            tag_authenticator_info.authenticatorMetadata = tag_authenticator_metadata;
            tag_authenticator_info.tcDisplayContentType = "text/plain";
            tag_authenticator_info.tcDisplayPNGCharacteristics = FingerAuthenticatorInfo.tag_tc_display_png_characteristics;
            tag_authenticator_info.assertionScheme = "UAFV1TLV";
            tag_authenticator_info.attestationType = FingerAuthenticatorInfo.attestationType;
            tag_authenticator_info.supportedExtensionID = FingerAuthenticatorInfo.supportedExtensionID;
            arrayList.add(tag_authenticator_info);
        }
        getInfoResponse.authenticatorInfo = arrayList;
        return getInfoResponse.serialize();
    }

    private byte[] openSettingsCommand(byte[] bArr) {
        OpenSettingsResponse openSettingsResponse = new OpenSettingsResponse();
        OpenSettingsCommand openSettingsCommand = new OpenSettingsCommand();
        openSettingsCommand.deserialize(bArr);
        if (openSettingsCommand.authenticatorIndex == 0) {
            openSettingsResponse.statusCode = (short) 6;
        }
        return openSettingsResponse.serialize();
    }

    private void processRegisterCommand(RegisterResponse registerResponse, RegisterCommand registerCommand) {
        char c2;
        String str = registerCommand.khAccessToken;
        String str2 = registerCommand.appID;
        if (registerCommand.authenticatorIndex == 0) {
            if (StringTools.isValidateString(str2)) {
                registerCommand.khAccessToken = UAFTools.mixKHATokenWithAppID(str, str2);
            }
            if (TextUtils.equals(Thread.currentThread().getName(), BasicInformation.THREAD_NAME)) {
                registerResponse.statusCode = (short) 33;
                return;
            }
            c2 = 0;
        } else {
            c2 = '\uffff';
        }
        try {
            if (c2 != '\uffff') {
                FidoPresenter fidoPresenter = new FidoPresenter(0, Operation.Reg.name(), registerCommand.serialize());
                FIDOUISDK.setFidoPresenter(fidoPresenter);
                synchronized (fidoPresenter) {
                    startActivity(0);
                    while (fidoPresenter.getFidoModel().getResponseCoded() == -1) {
                        fidoPresenter.wait();
                    }
                }
                if (fidoPresenter.getFidoModel().getResponseCoded() == 0) {
                    RegisterResponse registerResponse2 = (RegisterResponse) fidoPresenter.getFidoModel().getBaseResponse();
                    registerResponse.statusCode = registerResponse2.statusCode;
                    registerResponse.assertion = registerResponse2.assertion;
                    registerResponse.keyHandle = registerResponse2.keyHandle;
                } else {
                    registerResponse.statusCode = (short) fidoPresenter.getFidoModel().getResponseCoded();
                }
            } else {
                registerResponse.statusCode = (short) 2;
            }
        } catch (InterruptedException unused) {
            ((Activity) this.mContext).finishActivity(R2.drawable.author_has_followed_img);
            registerResponse.statusCode = (short) 33;
        } finally {
            FIDOUISDK.setFidoPresenter(null);
        }
    }

    private void processSignCommand(SignResponse signResponse, SignCommand signCommand) {
        char c2;
        String str = signCommand.khAccessToken;
        String str2 = signCommand.appID;
        if (signCommand.authenticatorIndex == 0) {
            if (StringTools.isValidateString(str2)) {
                signCommand.khAccessToken = UAFTools.mixKHATokenWithAppID(str, str2);
            }
            if (TextUtils.equals(Thread.currentThread().getName(), BasicInformation.THREAD_NAME)) {
                ((Activity) this.mContext).finishActivity(R2.drawable.author_has_followed_img);
                signResponse.statusCode = (short) 33;
                return;
            }
            c2 = 0;
        } else {
            c2 = '\uffff';
        }
        try {
            if (c2 != '\uffff') {
                FidoPresenter fidoPresenter = new FidoPresenter(0, Operation.Auth.name(), signCommand.serialize());
                FIDOUISDK.setFidoPresenter(fidoPresenter);
                synchronized (fidoPresenter) {
                    startActivity(0);
                    while (fidoPresenter.getFidoModel().getResponseCoded() == -1) {
                        fidoPresenter.wait();
                    }
                }
                if (fidoPresenter.getFidoModel().getResponseCoded() == 0) {
                    SignResponse signResponse2 = (SignResponse) fidoPresenter.getFidoModel().getBaseResponse();
                    signResponse.statusCode = signResponse2.statusCode;
                    signResponse.assertion = signResponse2.assertion;
                    signResponse.userNameAndKeyHandle = signResponse2.userNameAndKeyHandle;
                } else {
                    signResponse.statusCode = (short) fidoPresenter.getFidoModel().getResponseCoded();
                }
            } else {
                signResponse.statusCode = (short) 2;
            }
        } catch (InterruptedException unused) {
            ((Activity) this.mContext).finishActivity(R2.drawable.author_has_followed_img);
            signResponse.statusCode = (short) 33;
        } finally {
            FIDOUISDK.setFidoPresenter(null);
        }
    }

    private byte[] registerCommand(byte[] bArr) {
        RegisterResponse registerResponse = new RegisterResponse();
        RegisterCommand registerCommand = new RegisterCommand();
        registerCommand.deserialize(bArr);
        processRegisterCommand(registerResponse, registerCommand);
        return registerResponse.serialize();
    }

    private byte[] signCommand(byte[] bArr) {
        SignResponse signResponse = new SignResponse();
        SignCommand signCommand = new SignCommand();
        signCommand.deserialize(bArr);
        processSignCommand(signResponse, signCommand);
        return signResponse.serialize();
    }

    private void startActivity(int i2) {
        Intent intent = new Intent();
        if (i2 == 0) {
            intent.setClass(this.mContext, FingerActivity.class);
        }
        ((Activity) this.mContext).startActivityForResult(intent, R2.drawable.author_has_followed_img);
    }

    public byte[] process(byte[] bArr, int i2) {
        switch (i2) {
            case R2.id.fl_close /* 13313 */:
                return getInfo();
            case R2.id.fl_content /* 13314 */:
                return registerCommand(bArr);
            case R2.id.fl_device_item /* 13315 */:
                return signCommand(bArr);
            case R2.id.fl_inner /* 13316 */:
                return deregisterCommand(bArr);
            case R2.id.fl_input /* 13317 */:
            default:
                return null;
            case R2.id.fl_iv_left_container /* 13318 */:
                return openSettingsCommand(bArr);
        }
    }

    public void setmContext(Context context) {
        this.mContext = context;
    }
}
