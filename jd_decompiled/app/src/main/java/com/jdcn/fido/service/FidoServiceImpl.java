package com.jdcn.fido.service;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import com.jdcn.fido.constant.BasicInformation;
import com.jdcn.fido.sdk.IFidoCallback;
import com.jdcn.fido.utils.EnvUtil;
import com.jdcn.fido.utils.TrackerUtil;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes18.dex */
public class FidoServiceImpl {
    private static AtomicReference<Thread> atomicThread = new AtomicReference<>();

    /* JADX INFO: Access modifiers changed from: private */
    public static void errorCallback(Activity activity, String str, Throwable th, String str2, final IFidoCallback iFidoCallback) {
        final Bundle bundle = new Bundle();
        bundle.putString("message", str);
        TrackerUtil.appendResultException(BasicInformation.GET_DEVICEID_RESULT, th);
        TrackerUtil.tracker(activity, str2);
        activity.runOnUiThread(new Runnable() { // from class: com.jdcn.fido.service.FidoServiceImpl.6
            @Override // java.lang.Runnable
            public final void run() {
                IFidoCallback.this.response(401, bundle);
            }
        });
    }

    public static void getDeviceId(final Activity activity, final Bundle bundle, final IFidoCallback iFidoCallback) {
        if (iFidoCallback != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(System.currentTimeMillis());
            sb.append((int) (Math.random() * 1.0d * 100000.0d));
            String sb2 = sb.toString();
            try {
                TrackerUtil.tracker(activity, bundle, sb2, "getDeviceId_run_001");
                bundle.putString("serialNumber", sb2);
                if (activity == null) {
                    TrackerUtil.tracker(activity, bundle, sb2, "getDeviceId_end_400");
                    iFidoCallback.response(400, null);
                    return;
                }
                EnvUtil.getEnv(activity);
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    new Thread(new Runnable() { // from class: com.jdcn.fido.service.FidoServiceImpl.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            try {
                                TrackerUtil.threadLocal.set(bundle);
                                TrackerUtil.tracker(activity, null, "", "getDeviceId_run_002");
                                StringBuilder sb3 = new StringBuilder();
                                sb3.append(System.currentTimeMillis());
                                sb3.append((int) (Math.random() * 1.0d * 100000.0d));
                                TrackerUtil.serialNumber = sb3.toString();
                                TrackerUtil.append(BasicInformation.GET_DEVICEID_CALL);
                                GetDeviceIdService.getDeviceId(activity, bundle, iFidoCallback);
                            } catch (Throwable th) {
                                FidoServiceImpl.errorCallback(activity, "FidoServiceImpl->getDeviceId", th, BasicInformation.GET_DEVICEID, iFidoCallback);
                            }
                        }
                    }).start();
                    return;
                }
                TrackerUtil.threadLocal.set(bundle);
                TrackerUtil.tracker(activity, null, "", "getDeviceId_run_002");
                StringBuilder sb3 = new StringBuilder();
                sb3.append(System.currentTimeMillis());
                sb3.append((int) (Math.random() * 1.0d * 100000.0d));
                TrackerUtil.serialNumber = sb3.toString();
                TrackerUtil.append(BasicInformation.GET_DEVICEID_CALL);
                GetDeviceIdService.getDeviceId(activity, bundle, iFidoCallback);
            } catch (Throwable th) {
                TrackerUtil.tracker(activity, bundle, sb2, "getDeviceId_end_401");
                errorCallback(activity, "FidoServiceImpl->getDeviceId", th, BasicInformation.GET_DEVICEID, iFidoCallback);
            }
        }
    }

    private static Thread getRegist(final Activity activity, final Bundle bundle, final IFidoCallback iFidoCallback) {
        return new Thread(new Runnable() { // from class: com.jdcn.fido.service.FidoServiceImpl.3
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    EnvUtil.getEnv(activity);
                    TrackerUtil.threadLocal.set(bundle);
                    TrackerUtil.tracker(activity, null, "", "regist_run_002");
                    TrackerUtil.append(BasicInformation.SCENE_REG_CALL);
                    RegisterService.regist(activity, bundle, iFidoCallback, false);
                    FidoServiceImpl.atomicThread.set(null);
                } catch (Throwable th) {
                    TrackerUtil.tracker(activity, null, "", "regist_end_401");
                    FidoServiceImpl.errorCallback(activity, "FidoServiceImpl->register", th, BasicInformation.SCENE_REG, iFidoCallback);
                    FidoServiceImpl.atomicThread.set(null);
                }
            }
        });
    }

    private static Thread getTransport(final Activity activity, final Bundle bundle, final IFidoCallback iFidoCallback) {
        return new Thread(new Runnable() { // from class: com.jdcn.fido.service.FidoServiceImpl.4
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    EnvUtil.getEnv(activity);
                    TrackerUtil.threadLocal.set(bundle);
                    TrackerUtil.tracker(activity, null, "", "transport_run_002");
                    TrackerUtil.append(BasicInformation.SCENE_TRANS_CALL);
                    TransportService.transport(activity, bundle, iFidoCallback);
                    FidoServiceImpl.atomicThread.set(null);
                } catch (Throwable th) {
                    TrackerUtil.tracker(activity, null, "", "transport_end_401");
                    FidoServiceImpl.errorCallback(activity, "FidoServiceImpl->transport", th, BasicInformation.SCENE_TRANS, iFidoCallback);
                    FidoServiceImpl.atomicThread.set(null);
                }
            }
        });
    }

    private static Thread getUnregister(final Activity activity, final Bundle bundle, final IFidoCallback iFidoCallback) {
        return new Thread(new Runnable() { // from class: com.jdcn.fido.service.FidoServiceImpl.5
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    EnvUtil.getEnv(activity);
                    TrackerUtil.append(BasicInformation.SCENE_UNREG_CALL);
                    UnregisterService.unregister(activity, bundle, iFidoCallback);
                    FidoServiceImpl.atomicThread.set(null);
                } catch (Throwable th) {
                    FidoServiceImpl.errorCallback(activity, "FidoServiceImpl->unregister", th, BasicInformation.SCENE_UNREG, iFidoCallback);
                    FidoServiceImpl.atomicThread.set(null);
                }
            }
        });
    }

    public static void interrupt() {
        Thread thread = atomicThread.get();
        if (thread != null) {
            thread.setName(BasicInformation.THREAD_NAME);
            thread.interrupt();
            TrackerUtil.append(BasicInformation.SCENE_USER_INTERRUPT);
        }
    }

    public static void isFingerPayOpen(final Activity activity, final Bundle bundle, final IFidoCallback iFidoCallback) {
        if (iFidoCallback != null) {
            try {
                if (activity == null) {
                    iFidoCallback.response(400, null);
                    return;
                }
                EnvUtil.getEnv(activity);
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    new Thread(new Runnable() { // from class: com.jdcn.fido.service.FidoServiceImpl.2
                        @Override // java.lang.Runnable
                        public final void run() {
                            try {
                                StringBuilder sb = new StringBuilder();
                                sb.append(System.currentTimeMillis());
                                sb.append((int) (Math.random() * 1.0d * 100000.0d));
                                TrackerUtil.serialNumber = sb.toString();
                                TrackerUtil.append(BasicInformation.GET_OPENSTATE_CALL);
                                FidoStatusService.getOpenState(activity, bundle, iFidoCallback);
                            } catch (Throwable th) {
                                FidoServiceImpl.errorCallback(activity, "FidoServiceImpl->isFingerPayOpen", th, BasicInformation.GET_OPENSTATE, iFidoCallback);
                            }
                        }
                    }).start();
                    return;
                }
                StringBuilder sb = new StringBuilder();
                sb.append(System.currentTimeMillis());
                sb.append((int) (Math.random() * 1.0d * 100000.0d));
                TrackerUtil.serialNumber = sb.toString();
                TrackerUtil.append(BasicInformation.GET_OPENSTATE_CALL);
                FidoStatusService.getOpenState(activity, bundle, iFidoCallback);
            } catch (Throwable th) {
                errorCallback(activity, "FidoServiceImpl->isFingerPayOpen", th, BasicInformation.GET_OPENSTATE, iFidoCallback);
            }
        }
    }

    public static void regist(Activity activity, Bundle bundle, IFidoCallback iFidoCallback) {
        if (iFidoCallback != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(System.currentTimeMillis());
            sb.append((int) (Math.random() * 1.0d * 100000.0d));
            String sb2 = sb.toString();
            try {
                TrackerUtil.tracker(activity, bundle, sb2, "regist_run_001");
                if (activity == null) {
                    TrackerUtil.tracker(activity, bundle, sb2, "regist_end_400");
                    iFidoCallback.response(400, null);
                } else if (atomicThread.get() != null) {
                    TrackerUtil.tracker(activity, bundle, sb2, "regist_end_207");
                    iFidoCallback.response(207, null);
                } else if (atomicThread.compareAndSet(null, getRegist(activity, bundle, iFidoCallback))) {
                    bundle.putString("serialNumber", sb2);
                    atomicThread.get().start();
                } else {
                    TrackerUtil.tracker(activity, bundle, sb2, "regist_end_207");
                    iFidoCallback.response(207, null);
                }
            } catch (Throwable th) {
                TrackerUtil.tracker(activity, bundle, sb2, "regist_end_401");
                errorCallback(activity, "FidoServiceImpl->register", th, BasicInformation.SCENE_REG, iFidoCallback);
            }
        }
    }

    public static void transport(Activity activity, Bundle bundle, IFidoCallback iFidoCallback) {
        if (iFidoCallback != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(System.currentTimeMillis());
            sb.append((int) (Math.random() * 1.0d * 100000.0d));
            String sb2 = sb.toString();
            try {
                TrackerUtil.tracker(activity, bundle, sb2, "transport_run_001");
                if (activity == null) {
                    TrackerUtil.tracker(activity, bundle, sb2, "transport_end_400");
                    iFidoCallback.response(400, null);
                } else if (atomicThread.get() != null) {
                    TrackerUtil.tracker(activity, bundle, sb2, "transport_end_207");
                    iFidoCallback.response(207, null);
                } else if (atomicThread.compareAndSet(null, getTransport(activity, bundle, iFidoCallback))) {
                    bundle.putString("serialNumber", sb2);
                    atomicThread.get().start();
                } else {
                    TrackerUtil.tracker(activity, bundle, sb2, "transport_end_207");
                    iFidoCallback.response(207, null);
                }
            } catch (Throwable th) {
                TrackerUtil.tracker(activity, bundle, sb2, "transport_end_401");
                errorCallback(activity, "FidoServiceImpl->transport", th, BasicInformation.SCENE_TRANS, iFidoCallback);
            }
        }
    }

    public static void unregister(Activity activity, Bundle bundle, IFidoCallback iFidoCallback) {
        if (iFidoCallback != null) {
            try {
                if (activity == null) {
                    iFidoCallback.response(400, null);
                } else if (atomicThread.get() != null) {
                    iFidoCallback.response(207, null);
                } else if (atomicThread.compareAndSet(null, getUnregister(activity, bundle, iFidoCallback))) {
                    atomicThread.get().start();
                } else {
                    iFidoCallback.response(207, null);
                }
            } catch (Throwable th) {
                errorCallback(activity, "FidoServiceImpl->unregister", th, BasicInformation.SCENE_UNREG, iFidoCallback);
            }
        }
    }
}
