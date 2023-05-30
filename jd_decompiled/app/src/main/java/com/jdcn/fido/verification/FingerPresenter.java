package com.jdcn.fido.verification;

import android.app.Activity;
import cn.com.union.fido.bean.authenticator.command.BaseResponse;
import cn.com.union.fido.bean.uafclient.Operation;
import cn.com.union.fido.ui.FIDOUISDK;
import cn.com.union.fido.ui.IActivityPresenter;
import cn.com.union.fido.ui.IAuthPresenter;
import cn.com.union.fido.ui.IAuthSensorService;
import cn.com.union.fido.ui.IFidoView;
import com.jdcn.fido.constant.BasicInformation;
import com.jdcn.fido.sdk.IFidoCallback;
import com.jdcn.fido.utils.FidoServiceUtil;
import com.jdcn.fido.utils.TrackerUtil;
import java.lang.ref.WeakReference;

/* loaded from: classes18.dex */
public class FingerPresenter implements IActivityPresenter, IAuthPresenter {
    private WeakReference<IFidoView> fidoView;
    private IAuthSensorService sensorService;
    private volatile boolean isAuthViewDisplay = false;
    private volatile boolean isAuthActivityFinish = false;
    private volatile boolean isAuthSensorFinish = false;
    private volatile boolean isAuthFinish = false;
    private IFingerModel fingerModel = new FingerModel();

    private void handlerResult(int i2) {
        IFidoView iFidoView;
        if (!this.isAuthFinish) {
            this.isAuthFinish = true;
            i2 = FidoServiceUtil.toStatusCode(i2);
            this.fingerModel.setResponseCoded(i2);
            notifyAuthService();
        }
        if (!this.isAuthActivityFinish && (iFidoView = this.fidoView.get()) != null) {
            this.isAuthActivityFinish = true;
            if (i2 == 16) {
                iFidoView.onAuthenticationSucceeded(getOpType());
            } else {
                iFidoView.onAuthenticationError(getOpType());
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
        if (callback != null) {
            callback.response(500, null);
            IFidoView iFidoView = this.fidoView.get();
            if (iFidoView != null) {
                iFidoView.authViewDisplay(Operation.Reg.name());
            }
            TrackerUtil.append(BasicInformation.SCENE_REG_UI_DISPLAY);
            return;
        }
        this.isAuthFinish = true;
        IFidoView iFidoView2 = this.fidoView.get();
        if (iFidoView2 != null) {
            iFidoView2.onAuthenticationError(getOpType());
        }
    }

    @Override // cn.com.union.fido.ui.IAuthPresenter
    public byte[] getFidoCmd() {
        return null;
    }

    public IFingerModel getFingerModel() {
        return this.fingerModel;
    }

    @Override // cn.com.union.fido.ui.IAuthPresenter
    public String getOpType() {
        return null;
    }

    @Override // cn.com.union.fido.ui.IActivityPresenter
    public void handlerActivityResult(int i2) {
        if (this.isAuthActivityFinish) {
            return;
        }
        this.isAuthActivityFinish = true;
        handlerResult(i2);
    }

    @Override // cn.com.union.fido.ui.IAuthPresenter
    public void handlerAuthResult(int i2, BaseResponse baseResponse) {
        if (this.isAuthSensorFinish) {
            return;
        }
        this.isAuthSensorFinish = true;
        handlerResult(i2);
    }

    @Override // cn.com.union.fido.ui.IAuthPresenter
    public void onAuthenticationFailed() {
        IFidoView iFidoView = this.fidoView.get();
        if (iFidoView != null) {
            iFidoView.onAuthenticationFailed(Operation.Reg.name());
        }
    }

    @Override // cn.com.union.fido.ui.IAuthPresenter
    public void onAuthenticationHelp(CharSequence charSequence) {
        IFidoView iFidoView = this.fidoView.get();
        if (iFidoView != null) {
            iFidoView.onAuthenticationHelp(Operation.Reg.name(), charSequence);
        }
    }

    @Override // cn.com.union.fido.ui.IActivityPresenter
    public void setFidoView(IFidoView iFidoView) {
        this.fidoView = new WeakReference<>(iFidoView);
    }

    @Override // cn.com.union.fido.ui.IActivityPresenter
    public void startAuthenticator(Activity activity) {
        if (this.sensorService == null) {
            this.sensorService = new FingerService();
        }
        this.sensorService.startAuthenticator(activity, this);
    }
}
