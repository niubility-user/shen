package com.jingdong.common.jdreactFramework.modules.uphone_sdk.audio;

import android.content.Context;
import com.jingdong.common.jdreactFramework.modules.uphone_sdk.audio.AppRTCAudioManager;
import com.jingdong.sdk.oklog.OKLog;
import java.util.Set;

/* loaded from: classes5.dex */
public class AutoAudioManager {
    private static String TAG = "AutoAudioManager";
    private AppRTCAudioManager audioManager;
    private AutoAudioManagerObserver autoAudioManagerObserver;
    private Context context;
    private AppRTCAudioManager.AudioDevice lastDevice = AppRTCAudioManager.AudioDevice.NONE;

    public AutoAudioManager(Context context, AutoAudioManagerObserver autoAudioManagerObserver) {
        this.context = context.getApplicationContext();
        this.autoAudioManagerObserver = autoAudioManagerObserver;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean hasExternalDevices() {
        AppRTCAudioManager appRTCAudioManager = this.audioManager;
        if (appRTCAudioManager == null) {
            return false;
        }
        Set<AppRTCAudioManager.AudioDevice> audioDevices = appRTCAudioManager.getAudioDevices();
        return audioDevices.contains(AppRTCAudioManager.AudioDevice.WIRED_HEADSET) || audioDevices.contains(AppRTCAudioManager.AudioDevice.BLUETOOTH);
    }

    public void bluetoothStart() {
        AppRTCAudioManager appRTCAudioManager = this.audioManager;
        if (appRTCAudioManager != null) {
            appRTCAudioManager.bluetoothStart();
        }
    }

    public AppRTCAudioManager.AudioDevice getExternalDevice() {
        AppRTCAudioManager appRTCAudioManager = this.audioManager;
        if (appRTCAudioManager != null) {
            Set<AppRTCAudioManager.AudioDevice> audioDevices = appRTCAudioManager.getAudioDevices();
            AppRTCAudioManager.AudioDevice audioDevice = AppRTCAudioManager.AudioDevice.BLUETOOTH;
            if (audioDevices.contains(audioDevice)) {
                return audioDevice;
            }
            AppRTCAudioManager.AudioDevice audioDevice2 = AppRTCAudioManager.AudioDevice.WIRED_HEADSET;
            if (audioDevices.contains(audioDevice2)) {
                return audioDevice2;
            }
            return null;
        }
        return null;
    }

    public boolean isSpeakerOn() {
        AppRTCAudioManager appRTCAudioManager = this.audioManager;
        return appRTCAudioManager != null && appRTCAudioManager.getSelectedAudioDevice() == AppRTCAudioManager.AudioDevice.SPEAKER_PHONE;
    }

    public void speaker(boolean z) {
        try {
            if (this.audioManager == null) {
                return;
            }
            if (z) {
                OKLog.i(TAG, "to loud speaker");
                this.lastDevice = AppRTCAudioManager.AudioDevice.SPEAKER_PHONE;
            } else {
                OKLog.i(TAG, "to quiet speaker");
                AppRTCAudioManager.AudioDevice externalDevice = getExternalDevice();
                if (externalDevice != null) {
                    AppRTCAudioManager.AudioDevice audioDevice = AppRTCAudioManager.AudioDevice.BLUETOOTH;
                    if (externalDevice == audioDevice) {
                        this.lastDevice = audioDevice;
                    } else {
                        AppRTCAudioManager.AudioDevice audioDevice2 = AppRTCAudioManager.AudioDevice.WIRED_HEADSET;
                        if (externalDevice == audioDevice2) {
                            this.lastDevice = audioDevice2;
                        } else {
                            this.lastDevice = AppRTCAudioManager.AudioDevice.EARPIECE;
                        }
                    }
                } else {
                    this.lastDevice = AppRTCAudioManager.AudioDevice.EARPIECE;
                }
            }
            this.audioManager.selectAudioDevice(this.lastDevice);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void start(boolean z) {
        AppRTCAudioManager.AudioDevice audioDevice;
        AppRTCAudioManager.SpeakerPhoneType speakerPhoneType;
        if (this.audioManager == null) {
            if (z) {
                audioDevice = AppRTCAudioManager.AudioDevice.SPEAKER_PHONE;
            } else {
                audioDevice = AppRTCAudioManager.AudioDevice.EARPIECE;
            }
            this.lastDevice = audioDevice;
            Context context = this.context;
            if (z) {
                speakerPhoneType = AppRTCAudioManager.SpeakerPhoneType.TRUE;
            } else {
                speakerPhoneType = AppRTCAudioManager.SpeakerPhoneType.FALSE;
            }
            AppRTCAudioManager create = AppRTCAudioManager.create(context, speakerPhoneType);
            this.audioManager = create;
            create.start(new AppRTCAudioManager.AudioManagerEvents() { // from class: com.jingdong.common.jdreactFramework.modules.uphone_sdk.audio.AutoAudioManager.1
                @Override // com.jingdong.common.jdreactFramework.modules.uphone_sdk.audio.AppRTCAudioManager.AudioManagerEvents
                public void onAudioDeviceChanged(AppRTCAudioManager.AudioDevice audioDevice2, Set<AppRTCAudioManager.AudioDevice> set) {
                    AppRTCAudioManager.AudioDevice audioDevice3;
                    OKLog.d(AutoAudioManager.TAG, "audio device change: " + set + ", selected: " + audioDevice2);
                    if (AutoAudioManager.this.autoAudioManagerObserver != null) {
                        boolean hasExternalDevices = AutoAudioManager.this.hasExternalDevices();
                        AutoAudioManager.this.autoAudioManagerObserver.onDeviceChange(hasExternalDevices);
                        if (hasExternalDevices && audioDevice2 == (audioDevice3 = AppRTCAudioManager.AudioDevice.BLUETOOTH)) {
                            if (AutoAudioManager.this.lastDevice == AppRTCAudioManager.AudioDevice.EARPIECE) {
                                OKLog.d(AutoAudioManager.TAG, "new external device: auto select bluetooth 1");
                                AutoAudioManager.this.audioManager.selectAudioDevice(audioDevice3);
                            }
                        } else if (!hasExternalDevices && audioDevice2 == AppRTCAudioManager.AudioDevice.SPEAKER_PHONE) {
                            AppRTCAudioManager.AudioDevice audioDevice4 = AutoAudioManager.this.lastDevice;
                            AppRTCAudioManager.AudioDevice audioDevice5 = AppRTCAudioManager.AudioDevice.EARPIECE;
                            if (audioDevice4 == audioDevice5) {
                                OKLog.d(AutoAudioManager.TAG, "remove external device: auto recovery EARPIECE");
                                AutoAudioManager.this.audioManager.selectAudioDevice(audioDevice5);
                            }
                        } else if (hasExternalDevices || audioDevice2 != AppRTCAudioManager.AudioDevice.EARPIECE) {
                        } else {
                            AppRTCAudioManager.AudioDevice audioDevice6 = AutoAudioManager.this.lastDevice;
                            AppRTCAudioManager.AudioDevice audioDevice7 = AppRTCAudioManager.AudioDevice.SPEAKER_PHONE;
                            if (audioDevice6 == audioDevice7) {
                                OKLog.d(AutoAudioManager.TAG, "remove external device: auto recovery SPEAKER_PHONE");
                                AutoAudioManager.this.audioManager.selectAudioDevice(audioDevice7);
                            }
                        }
                    }
                }
            });
        }
    }

    public void stop() {
        AppRTCAudioManager appRTCAudioManager = this.audioManager;
        if (appRTCAudioManager != null) {
            appRTCAudioManager.stop();
            this.audioManager = null;
        }
        this.lastDevice = AppRTCAudioManager.AudioDevice.NONE;
    }
}
