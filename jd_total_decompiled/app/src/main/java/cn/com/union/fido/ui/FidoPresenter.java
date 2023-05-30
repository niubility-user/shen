package cn.com.union.fido.ui;

import android.app.Activity;
import cn.com.union.fido.bean.authenticator.command.BaseResponse;
import cn.com.union.fido.bean.uafclient.Operation;
import cn.com.union.fido.ui.finger.FingerSensorService;
import com.jdcn.fido.constant.BasicInformation;
import com.jdcn.fido.sdk.IFidoCallback;
import com.jdcn.fido.utils.TrackerUtil;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public class FidoPresenter implements IActivityPresenter, IAuthPresenter {
    private IFidoModel fidoModel;
    private WeakReference<IFidoView> fidoView;
    private IAuthSensorService sensorService;
    private volatile boolean isAuthViewDisplay = false;
    private volatile boolean isAuthActivityFinish = false;
    private volatile boolean isAuthSensorFinish = false;
    private volatile boolean isAuthFinish = false;

    public FidoPresenter(int i2, String str, byte[] bArr) {
        this.fidoModel = new FidoModel(i2, str, bArr);
    }

    private void handlerResult(int i2, BaseResponse baseResponse) {
        int i3;
        switch (i2) {
            case 16:
                i3 = 0;
                break;
            case 17:
                i3 = 2;
                break;
            case 18:
                i3 = 5;
                break;
            case 19:
                i3 = 8;
                break;
            case 20:
            case 22:
            case 23:
            default:
                i3 = 1;
                break;
            case 21:
                i3 = 16;
                break;
            case 24:
                i3 = 19;
                break;
            case 25:
                i3 = 20;
                break;
            case 26:
                i3 = 21;
                break;
            case 27:
                i3 = 22;
                break;
            case 28:
                i3 = 23;
                break;
            case 29:
                i3 = 24;
                break;
            case 30:
                i3 = 25;
                break;
        }
        if (!this.isAuthFinish) {
            this.isAuthFinish = true;
            this.fidoModel.setResponseCoded(i3);
            this.fidoModel.setBaseResponse(baseResponse);
            notifyAuthService();
        }
        if (!this.isAuthActivityFinish) {
            this.isAuthActivityFinish = true;
            IFidoView iFidoView = this.fidoView.get();
            if (iFidoView != null) {
                if (i3 == 0) {
                    iFidoView.onAuthenticationSucceeded(getOpType());
                } else {
                    iFidoView.onAuthenticationError(getOpType());
                }
            }
        }
        if (this.isAuthSensorFinish) {
            return;
        }
        this.isAuthSensorFinish = true;
        this.sensorService.endAuthenticator();
    }

    private void notifyAuthService() {
        try {
            synchronized (this) {
                notifyAll();
            }
        } catch (Throwable unused) {
        }
    }

    @Override // cn.com.union.fido.ui.IAuthPresenter
    public void authViewDisplay() {
        if (this.isAuthViewDisplay) {
            return;
        }
        this.isAuthViewDisplay = true;
        if (this.isAuthFinish) {
            return;
        }
        IFidoCallback callback = FIDOUISDK.getCallback();
        if (callback == null) {
            this.isAuthFinish = true;
            IFidoView iFidoView = this.fidoView.get();
            if (iFidoView != null) {
                iFidoView.onAuthenticationError(getOpType());
                return;
            }
            return;
        }
        callback.response(500, null);
        IFidoView iFidoView2 = this.fidoView.get();
        if (iFidoView2 != null) {
            iFidoView2.authViewDisplay(getOpType());
        }
        if (getOpType().equals(Operation.Reg.name())) {
            TrackerUtil.append(BasicInformation.SCENE_REG_UI_DISPLAY);
        }
        if (getOpType().equals(Operation.Auth.name())) {
            TrackerUtil.append(BasicInformation.SCENE_TRANS_UI_DISPLAY);
        }
    }

    @Override // cn.com.union.fido.ui.IAuthPresenter
    public byte[] getFidoCmd() {
        return this.fidoModel.getFidoCmd();
    }

    public IFidoModel getFidoModel() {
        return this.fidoModel;
    }

    @Override // cn.com.union.fido.ui.IAuthPresenter
    public String getOpType() {
        return this.fidoModel.getOpType();
    }

    @Override // cn.com.union.fido.ui.IActivityPresenter
    public void handlerActivityResult(int i2) {
        if (this.isAuthActivityFinish) {
            return;
        }
        this.isAuthActivityFinish = true;
        handlerResult(i2, null);
    }

    @Override // cn.com.union.fido.ui.IAuthPresenter
    public void handlerAuthResult(int i2, BaseResponse baseResponse) {
        if (this.isAuthSensorFinish) {
            return;
        }
        this.isAuthSensorFinish = true;
        handlerResult(i2, baseResponse);
    }

    @Override // cn.com.union.fido.ui.IAuthPresenter
    public void onAuthenticationFailed() {
        IFidoView iFidoView = this.fidoView.get();
        if (iFidoView != null) {
            iFidoView.onAuthenticationFailed(getOpType());
        }
    }

    @Override // cn.com.union.fido.ui.IAuthPresenter
    public void onAuthenticationHelp(CharSequence charSequence) {
        IFidoView iFidoView = this.fidoView.get();
        if (iFidoView != null) {
            iFidoView.onAuthenticationHelp(getOpType(), charSequence);
        }
    }

    @Override // cn.com.union.fido.ui.IActivityPresenter
    public void setFidoView(IFidoView iFidoView) {
        this.fidoView = new WeakReference<>(iFidoView);
    }

    @Override // cn.com.union.fido.ui.IActivityPresenter
    public void startAuthenticator(Activity activity) {
        if (this.fidoModel.getAuthenticatorIndex() != 0) {
            return;
        }
        if (this.sensorService == null) {
            this.sensorService = new FingerSensorService();
        }
        this.sensorService.startAuthenticator(activity, this);
    }
}
