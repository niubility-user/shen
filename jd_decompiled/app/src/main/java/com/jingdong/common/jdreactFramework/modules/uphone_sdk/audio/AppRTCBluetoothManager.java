package com.jingdong.common.jdreactFramework.modules.uphone_sdk.audio;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import androidx.annotation.Nullable;
import com.jingdong.sdk.oklog.OKLog;
import java.util.List;
import java.util.Set;

/* loaded from: classes5.dex */
public class AppRTCBluetoothManager {
    private static final int BLUETOOTH_SCO_TIMEOUT_MS = 4000;
    private static final int MAX_SCO_CONNECTION_ATTEMPTS = 2;
    private static final String TAG = "AppRTCBluetoothManager";
    private final AppRTCAudioManager apprtcAudioManager;
    private final Context apprtcContext;
    @Nullable
    private final AudioManager audioManager;
    @Nullable
    private BluetoothAdapter bluetoothAdapter;
    @Nullable
    private BluetoothDevice bluetoothDevice;
    @Nullable
    private BluetoothHeadset bluetoothHeadset;
    private final BroadcastReceiver bluetoothHeadsetReceiver;
    private final BluetoothProfile.ServiceListener bluetoothServiceListener;
    private State bluetoothState;
    private final Runnable bluetoothTimeoutRunnable = new Runnable() { // from class: com.jingdong.common.jdreactFramework.modules.uphone_sdk.audio.AppRTCBluetoothManager.1
        {
            AppRTCBluetoothManager.this = this;
        }

        @Override // java.lang.Runnable
        public void run() {
            AppRTCBluetoothManager.this.bluetoothTimeout();
        }
    };
    private final Handler handler;
    int scoConnectionAttempts;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public class BluetoothHeadsetBroadcastReceiver extends BroadcastReceiver {
        private BluetoothHeadsetBroadcastReceiver() {
            AppRTCBluetoothManager.this = r1;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (AppRTCBluetoothManager.this.bluetoothState == State.UNINITIALIZED) {
                return;
            }
            String action = intent.getAction();
            if (action.equals("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED")) {
                int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", 0);
                OKLog.d(AppRTCBluetoothManager.TAG, "BluetoothHeadsetBroadcastReceiver.onReceive: a=ACTION_CONNECTION_STATE_CHANGED, s=" + AppRTCBluetoothManager.this.stateToString(intExtra) + ", sb=" + isInitialStickyBroadcast() + ", BT state: " + AppRTCBluetoothManager.this.bluetoothState);
                if (intExtra == 2) {
                    AppRTCBluetoothManager appRTCBluetoothManager = AppRTCBluetoothManager.this;
                    appRTCBluetoothManager.scoConnectionAttempts = 0;
                    appRTCBluetoothManager.updateAudioDeviceState();
                } else if (intExtra != 1 && intExtra != 3 && intExtra == 0) {
                    AppRTCBluetoothManager.this.stopScoAudio();
                    AppRTCBluetoothManager.this.updateAudioDeviceState();
                }
            } else if (action.equals("android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED")) {
                int intExtra2 = intent.getIntExtra("android.bluetooth.profile.extra.STATE", 10);
                OKLog.d(AppRTCBluetoothManager.TAG, "BluetoothHeadsetBroadcastReceiver.onReceive: a=ACTION_AUDIO_STATE_CHANGED, s=" + AppRTCBluetoothManager.this.stateToString(intExtra2) + ", sb=" + isInitialStickyBroadcast() + ", BT state: " + AppRTCBluetoothManager.this.bluetoothState);
                if (intExtra2 == 12) {
                    AppRTCBluetoothManager.this.cancelTimer();
                    if (AppRTCBluetoothManager.this.bluetoothState == State.SCO_CONNECTING) {
                        OKLog.d(AppRTCBluetoothManager.TAG, "+++ Bluetooth audio SCO is now connected");
                        AppRTCBluetoothManager.this.bluetoothState = State.SCO_CONNECTED;
                        AppRTCBluetoothManager appRTCBluetoothManager2 = AppRTCBluetoothManager.this;
                        appRTCBluetoothManager2.scoConnectionAttempts = 0;
                        appRTCBluetoothManager2.updateAudioDeviceState();
                    } else {
                        OKLog.w(AppRTCBluetoothManager.TAG, "Unexpected state BluetoothHeadset.STATE_AUDIO_CONNECTED");
                    }
                } else if (intExtra2 == 11) {
                    OKLog.d(AppRTCBluetoothManager.TAG, "+++ Bluetooth audio SCO is now connecting...");
                } else if (intExtra2 == 10) {
                    OKLog.d(AppRTCBluetoothManager.TAG, "+++ Bluetooth audio SCO is now disconnected");
                    if (!isInitialStickyBroadcast()) {
                        AppRTCBluetoothManager.this.updateAudioDeviceState();
                    } else {
                        OKLog.d(AppRTCBluetoothManager.TAG, "Ignore STATE_AUDIO_DISCONNECTED initial sticky broadcast.");
                        return;
                    }
                }
            }
            OKLog.d(AppRTCBluetoothManager.TAG, "onReceive done: BT state=" + AppRTCBluetoothManager.this.bluetoothState);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public class BluetoothServiceListener implements BluetoothProfile.ServiceListener {
        private BluetoothServiceListener() {
            AppRTCBluetoothManager.this = r1;
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int i2, BluetoothProfile bluetoothProfile) {
            if (i2 != 1 || AppRTCBluetoothManager.this.bluetoothState == State.UNINITIALIZED) {
                return;
            }
            OKLog.d(AppRTCBluetoothManager.TAG, "BluetoothServiceListener.onServiceConnected: BT state=" + AppRTCBluetoothManager.this.bluetoothState);
            AppRTCBluetoothManager.this.bluetoothHeadset = (BluetoothHeadset) bluetoothProfile;
            AppRTCBluetoothManager.this.updateAudioDeviceState();
            OKLog.d(AppRTCBluetoothManager.TAG, "onServiceConnected done: BT state=" + AppRTCBluetoothManager.this.bluetoothState);
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int i2) {
            if (i2 != 1 || AppRTCBluetoothManager.this.bluetoothState == State.UNINITIALIZED) {
                return;
            }
            OKLog.d(AppRTCBluetoothManager.TAG, "BluetoothServiceListener.onServiceDisconnected: BT state=" + AppRTCBluetoothManager.this.bluetoothState);
            AppRTCBluetoothManager.this.stopScoAudio();
            AppRTCBluetoothManager.this.bluetoothHeadset = null;
            AppRTCBluetoothManager.this.bluetoothDevice = null;
            AppRTCBluetoothManager.this.bluetoothState = State.HEADSET_UNAVAILABLE;
            AppRTCBluetoothManager.this.updateAudioDeviceState();
            OKLog.d(AppRTCBluetoothManager.TAG, "onServiceDisconnected done: BT state=" + AppRTCBluetoothManager.this.bluetoothState);
        }
    }

    /* loaded from: classes5.dex */
    public enum State {
        UNINITIALIZED,
        ERROR,
        HEADSET_UNAVAILABLE,
        HEADSET_AVAILABLE,
        SCO_DISCONNECTING,
        SCO_CONNECTING,
        SCO_CONNECTED
    }

    protected AppRTCBluetoothManager(Context context, AppRTCAudioManager appRTCAudioManager) {
        OKLog.d(TAG, "ctor");
        ThreadUtils.checkIsOnMainThread();
        this.apprtcContext = context;
        this.apprtcAudioManager = appRTCAudioManager;
        this.audioManager = getAudioManager(context);
        this.bluetoothState = State.UNINITIALIZED;
        this.bluetoothServiceListener = new BluetoothServiceListener();
        this.bluetoothHeadsetReceiver = new BluetoothHeadsetBroadcastReceiver();
        this.handler = new Handler(Looper.getMainLooper());
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00a1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void bluetoothTimeout() {
        /*
            r4 = this;
            com.jingdong.common.jdreactFramework.modules.uphone_sdk.audio.ThreadUtils.checkIsOnMainThread()
            com.jingdong.common.jdreactFramework.modules.uphone_sdk.audio.AppRTCBluetoothManager$State r0 = r4.bluetoothState
            com.jingdong.common.jdreactFramework.modules.uphone_sdk.audio.AppRTCBluetoothManager$State r1 = com.jingdong.common.jdreactFramework.modules.uphone_sdk.audio.AppRTCBluetoothManager.State.UNINITIALIZED
            if (r0 == r1) goto Lc2
            android.bluetooth.BluetoothHeadset r0 = r4.bluetoothHeadset
            if (r0 != 0) goto Lf
            goto Lc2
        Lf:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "bluetoothTimeout: BT state="
            r0.append(r1)
            com.jingdong.common.jdreactFramework.modules.uphone_sdk.audio.AppRTCBluetoothManager$State r1 = r4.bluetoothState
            r0.append(r1)
            java.lang.String r1 = ", attempts: "
            r0.append(r1)
            int r1 = r4.scoConnectionAttempts
            r0.append(r1)
            java.lang.String r1 = ", SCO is on: "
            r0.append(r1)
            boolean r1 = r4.isScoOn()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "AppRTCBluetoothManager"
            com.jingdong.sdk.oklog.OKLog.d(r1, r0)
            com.jingdong.common.jdreactFramework.modules.uphone_sdk.audio.AppRTCBluetoothManager$State r0 = r4.bluetoothState
            com.jingdong.common.jdreactFramework.modules.uphone_sdk.audio.AppRTCBluetoothManager$State r2 = com.jingdong.common.jdreactFramework.modules.uphone_sdk.audio.AppRTCBluetoothManager.State.SCO_CONNECTING
            if (r0 == r2) goto L44
            return
        L44:
            android.bluetooth.BluetoothHeadset r0 = r4.bluetoothHeadset
            java.util.List r0 = r0.getConnectedDevices()
            int r2 = r0.size()
            r3 = 0
            if (r2 <= 0) goto L97
            java.lang.Object r0 = r0.get(r3)
            android.bluetooth.BluetoothDevice r0 = (android.bluetooth.BluetoothDevice) r0
            r4.bluetoothDevice = r0
            android.bluetooth.BluetoothHeadset r2 = r4.bluetoothHeadset
            boolean r0 = r2.isAudioConnected(r0)
            if (r0 == 0) goto L7d
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "SCO connected with "
            r0.append(r2)
            android.bluetooth.BluetoothDevice r2 = r4.bluetoothDevice
            java.lang.String r2 = r2.getName()
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            com.jingdong.sdk.oklog.OKLog.d(r1, r0)
            r0 = 1
            goto L98
        L7d:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "SCO is not connected with "
            r0.append(r2)
            android.bluetooth.BluetoothDevice r2 = r4.bluetoothDevice
            java.lang.String r2 = r2.getName()
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            com.jingdong.sdk.oklog.OKLog.d(r1, r0)
        L97:
            r0 = 0
        L98:
            if (r0 == 0) goto La1
            com.jingdong.common.jdreactFramework.modules.uphone_sdk.audio.AppRTCBluetoothManager$State r0 = com.jingdong.common.jdreactFramework.modules.uphone_sdk.audio.AppRTCBluetoothManager.State.SCO_CONNECTED
            r4.bluetoothState = r0
            r4.scoConnectionAttempts = r3
            goto La9
        La1:
            java.lang.String r0 = "BT failed to connect after timeout"
            com.jingdong.sdk.oklog.OKLog.w(r1, r0)
            r4.stopScoAudio()
        La9:
            r4.updateAudioDeviceState()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "bluetoothTimeout done: BT state="
            r0.append(r2)
            com.jingdong.common.jdreactFramework.modules.uphone_sdk.audio.AppRTCBluetoothManager$State r2 = r4.bluetoothState
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            com.jingdong.sdk.oklog.OKLog.d(r1, r0)
        Lc2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.jdreactFramework.modules.uphone_sdk.audio.AppRTCBluetoothManager.bluetoothTimeout():void");
    }

    public void cancelTimer() {
        ThreadUtils.checkIsOnMainThread();
        OKLog.d(TAG, "cancelTimer");
        this.handler.removeCallbacks(this.bluetoothTimeoutRunnable);
    }

    public static AppRTCBluetoothManager create(Context context, AppRTCAudioManager appRTCAudioManager) {
        OKLog.d(TAG, "create" + AppRTCUtils.getThreadInfo());
        return new AppRTCBluetoothManager(context, appRTCAudioManager);
    }

    private boolean isScoOn() {
        return this.audioManager.isBluetoothScoOn();
    }

    private void startTimer() {
        ThreadUtils.checkIsOnMainThread();
        OKLog.d(TAG, "startTimer");
        this.handler.postDelayed(this.bluetoothTimeoutRunnable, 4000L);
    }

    public String stateToString(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        switch (i2) {
                            case 10:
                                return "OFF";
                            case 11:
                                return "TURNING_ON";
                            case 12:
                                return "ON";
                            case 13:
                                return "TURNING_OFF";
                            default:
                                return "INVALID";
                        }
                    }
                    return "DISCONNECTING";
                }
                return "CONNECTED";
            }
            return "CONNECTING";
        }
        return "DISCONNECTED";
    }

    public void updateAudioDeviceState() {
        ThreadUtils.checkIsOnMainThread();
        OKLog.d(TAG, "updateAudioDeviceState");
        this.apprtcAudioManager.updateAudioDeviceState();
    }

    @Nullable
    protected AudioManager getAudioManager(Context context) {
        return (AudioManager) context.getSystemService("audio");
    }

    protected boolean getBluetoothProfileProxy(Context context, BluetoothProfile.ServiceListener serviceListener, int i2) {
        return this.bluetoothAdapter.getProfileProxy(context, serviceListener, i2);
    }

    public State getState() {
        ThreadUtils.checkIsOnMainThread();
        return this.bluetoothState;
    }

    protected boolean hasPermission(Context context, String str) {
        return this.apprtcContext.checkPermission(str, Process.myPid(), Process.myUid()) == 0;
    }

    @SuppressLint({"HardwareIds"})
    protected void logBluetoothAdapterInfo(BluetoothAdapter bluetoothAdapter) {
        OKLog.d(TAG, "BluetoothAdapter: enabled=" + bluetoothAdapter.isEnabled() + ", state=" + stateToString(bluetoothAdapter.getState()) + ", name=" + bluetoothAdapter.getName());
        Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();
        if (bondedDevices.isEmpty()) {
            return;
        }
        OKLog.d(TAG, "paired devices:");
        for (BluetoothDevice bluetoothDevice : bondedDevices) {
            OKLog.d(TAG, " name=" + bluetoothDevice.getName() + ", address=" + bluetoothDevice.getAddress());
        }
    }

    protected void registerReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        this.apprtcContext.registerReceiver(broadcastReceiver, intentFilter);
    }

    public void start() {
        ThreadUtils.checkIsOnMainThread();
        OKLog.d(TAG, "start");
        if (Build.VERSION.SDK_INT >= 31) {
            if (!hasPermission(this.apprtcContext, "android.permission.BLUETOOTH_CONNECT")) {
                OKLog.w(TAG, "Process (pid=" + Process.myPid() + ") lacks BLUETOOTH_CONNECT permission");
                return;
            }
        } else if (!hasPermission(this.apprtcContext, "android.permission.BLUETOOTH")) {
            OKLog.w(TAG, "Process (pid=" + Process.myPid() + ") lacks BLUETOOTH permission");
            return;
        }
        if (this.bluetoothState != State.UNINITIALIZED) {
            OKLog.w(TAG, "Invalid BT state");
            return;
        }
        this.bluetoothHeadset = null;
        this.bluetoothDevice = null;
        this.scoConnectionAttempts = 0;
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        this.bluetoothAdapter = defaultAdapter;
        if (defaultAdapter == null) {
            OKLog.w(TAG, "Device does not support Bluetooth");
        } else if (!this.audioManager.isBluetoothScoAvailableOffCall()) {
            OKLog.e(TAG, "Bluetooth SCO audio is not available off call");
        } else {
            logBluetoothAdapterInfo(this.bluetoothAdapter);
            if (!getBluetoothProfileProxy(this.apprtcContext, this.bluetoothServiceListener, 1)) {
                OKLog.e(TAG, "BluetoothAdapter.getProfileProxy(HEADSET) failed");
                return;
            }
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
            intentFilter.addAction("android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED");
            registerReceiver(this.bluetoothHeadsetReceiver, intentFilter);
            OKLog.d(TAG, "HEADSET profile state: " + stateToString(this.bluetoothAdapter.getProfileConnectionState(1)));
            OKLog.d(TAG, "Bluetooth proxy for headset profile has started");
            this.bluetoothState = State.HEADSET_UNAVAILABLE;
            OKLog.d(TAG, "start done: BT state=" + this.bluetoothState);
        }
    }

    public boolean startScoAudio() {
        ThreadUtils.checkIsOnMainThread();
        OKLog.d(TAG, "startSco: BT state=" + this.bluetoothState + ", attempts: " + this.scoConnectionAttempts + ", SCO is on: " + isScoOn());
        if (this.scoConnectionAttempts >= 2) {
            OKLog.e(TAG, "BT SCO connection fails - no more attempts");
            return false;
        } else if (this.bluetoothState != State.HEADSET_AVAILABLE) {
            OKLog.e(TAG, "BT SCO connection fails - no headset available");
            return false;
        } else {
            OKLog.d(TAG, "Starting Bluetooth SCO and waits for ACTION_AUDIO_STATE_CHANGED...");
            this.bluetoothState = State.SCO_CONNECTING;
            try {
                this.audioManager.startBluetoothSco();
                this.audioManager.setBluetoothScoOn(true);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.scoConnectionAttempts++;
            startTimer();
            OKLog.d(TAG, "startScoAudio done: BT state=" + this.bluetoothState + ", SCO is on: " + isScoOn());
            return true;
        }
    }

    public void stop() {
        ThreadUtils.checkIsOnMainThread();
        OKLog.d(TAG, "stop: BT state=" + this.bluetoothState);
        if (this.bluetoothAdapter == null) {
            return;
        }
        stopScoAudio();
        State state = this.bluetoothState;
        State state2 = State.UNINITIALIZED;
        if (state == state2) {
            return;
        }
        unregisterReceiver(this.bluetoothHeadsetReceiver);
        cancelTimer();
        BluetoothHeadset bluetoothHeadset = this.bluetoothHeadset;
        if (bluetoothHeadset != null) {
            this.bluetoothAdapter.closeProfileProxy(1, bluetoothHeadset);
            this.bluetoothHeadset = null;
        }
        this.bluetoothAdapter = null;
        this.bluetoothDevice = null;
        this.bluetoothState = state2;
        OKLog.d(TAG, "stop done: BT state=" + this.bluetoothState);
    }

    public void stopScoAudio() {
        ThreadUtils.checkIsOnMainThread();
        OKLog.d(TAG, "stopScoAudio: BT state=" + this.bluetoothState + ", SCO is on: " + isScoOn());
        State state = this.bluetoothState;
        if (state == State.SCO_CONNECTING || state == State.SCO_CONNECTED) {
            cancelTimer();
            try {
                this.audioManager.stopBluetoothSco();
                this.audioManager.setBluetoothScoOn(false);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.bluetoothState = State.SCO_DISCONNECTING;
            OKLog.d(TAG, "stopScoAudio done: BT state=" + this.bluetoothState + ", SCO is on: " + isScoOn());
        }
    }

    protected void unregisterReceiver(BroadcastReceiver broadcastReceiver) {
        this.apprtcContext.unregisterReceiver(broadcastReceiver);
    }

    public void updateDevice() {
        if (this.bluetoothState == State.UNINITIALIZED || this.bluetoothHeadset == null) {
            return;
        }
        OKLog.d(TAG, "updateDevice");
        List<BluetoothDevice> connectedDevices = this.bluetoothHeadset.getConnectedDevices();
        if (connectedDevices.isEmpty()) {
            this.bluetoothDevice = null;
            this.bluetoothState = State.HEADSET_UNAVAILABLE;
            OKLog.d(TAG, "No connected bluetooth headset");
        } else {
            this.bluetoothDevice = connectedDevices.get(0);
            this.bluetoothState = State.HEADSET_AVAILABLE;
            OKLog.d(TAG, "Connected bluetooth headset: name=" + this.bluetoothDevice.getName() + ", state=" + stateToString(this.bluetoothHeadset.getConnectionState(this.bluetoothDevice)) + ", SCO audio=" + this.bluetoothHeadset.isAudioConnected(this.bluetoothDevice));
        }
        OKLog.d(TAG, "updateDevice done: BT state=" + this.bluetoothState);
    }
}
