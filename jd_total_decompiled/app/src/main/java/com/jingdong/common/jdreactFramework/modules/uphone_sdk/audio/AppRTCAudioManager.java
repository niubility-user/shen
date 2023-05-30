package com.jingdong.common.jdreactFramework.modules.uphone_sdk.audio;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.os.Build;
import androidx.annotation.Nullable;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.jdreactFramework.modules.uphone_sdk.audio.AppRTCBluetoothManager;
import com.jingdong.sdk.oklog.OKLog;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes5.dex */
public class AppRTCAudioManager {
    private static final String SPEAKERPHONE_AUTO = "auto";
    private static final String SPEAKERPHONE_FALSE = "false";
    private static final String SPEAKERPHONE_TRUE = "true";
    private static final String TAG = "AppRTCAudioManager";
    private AudioManagerState amState;
    private final Context apprtcContext;
    @Nullable
    private AudioManager.OnAudioFocusChangeListener audioFocusChangeListener;
    @Nullable
    private AudioManager audioManager;
    @Nullable
    private AudioManagerEvents audioManagerEvents;
    private final AppRTCBluetoothManager bluetoothManager;
    private AudioDevice defaultAudioDevice;
    private boolean hasWiredHeadset;
    @Nullable
    private AppRTCProximitySensor proximitySensor;
    private boolean savedIsMicrophoneMute;
    private boolean savedIsSpeakerPhoneOn;
    private AudioDevice selectedAudioDevice;
    private final String useSpeakerphone;
    private AudioDevice userSelectedAudioDevice;
    private BroadcastReceiver wiredHeadsetReceiver;
    private int savedAudioMode = -2;
    private Set<AudioDevice> audioDevices = new HashSet();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.common.jdreactFramework.modules.uphone_sdk.audio.AppRTCAudioManager$3  reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$jingdong$common$jdreactFramework$modules$uphone_sdk$audio$AppRTCAudioManager$AudioDevice;

        static {
            int[] iArr = new int[AudioDevice.values().length];
            $SwitchMap$com$jingdong$common$jdreactFramework$modules$uphone_sdk$audio$AppRTCAudioManager$AudioDevice = iArr;
            try {
                iArr[AudioDevice.SPEAKER_PHONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$jingdong$common$jdreactFramework$modules$uphone_sdk$audio$AppRTCAudioManager$AudioDevice[AudioDevice.EARPIECE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$jingdong$common$jdreactFramework$modules$uphone_sdk$audio$AppRTCAudioManager$AudioDevice[AudioDevice.WIRED_HEADSET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$jingdong$common$jdreactFramework$modules$uphone_sdk$audio$AppRTCAudioManager$AudioDevice[AudioDevice.BLUETOOTH.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* loaded from: classes5.dex */
    public enum AudioDevice {
        SPEAKER_PHONE,
        WIRED_HEADSET,
        EARPIECE,
        BLUETOOTH,
        NONE
    }

    /* loaded from: classes5.dex */
    public interface AudioManagerEvents {
        void onAudioDeviceChanged(AudioDevice audioDevice, Set<AudioDevice> set);
    }

    /* loaded from: classes5.dex */
    public enum AudioManagerState {
        UNINITIALIZED,
        PREINITIALIZED,
        RUNNING
    }

    /* loaded from: classes5.dex */
    public enum SpeakerPhoneType {
        AUTO,
        TRUE,
        FALSE
    }

    /* loaded from: classes5.dex */
    private class WiredHeadsetReceiver extends BroadcastReceiver {
        private static final int HAS_MIC = 1;
        private static final int HAS_NO_MIC = 0;
        private static final int STATE_PLUGGED = 1;
        private static final int STATE_UNPLUGGED = 0;

        private WiredHeadsetReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            int intExtra = intent.getIntExtra(XView2Constants.STATE, 0);
            int intExtra2 = intent.getIntExtra("microphone", 0);
            String stringExtra = intent.getStringExtra("name");
            StringBuilder sb = new StringBuilder();
            sb.append("WiredHeadsetReceiver.onReceive");
            sb.append(AppRTCUtils.getThreadInfo());
            sb.append(": a=");
            sb.append(intent.getAction());
            sb.append(", s=");
            sb.append(intExtra == 0 ? "unplugged" : "plugged");
            sb.append(", m=");
            sb.append(intExtra2 == 1 ? "mic" : "no mic");
            sb.append(", n=");
            sb.append(stringExtra);
            sb.append(", sb=");
            sb.append(isInitialStickyBroadcast());
            OKLog.d(AppRTCAudioManager.TAG, sb.toString());
            AppRTCAudioManager.this.hasWiredHeadset = intExtra == 1;
            AppRTCAudioManager.this.updateAudioDeviceState();
        }
    }

    private AppRTCAudioManager(Context context, SpeakerPhoneType speakerPhoneType) {
        OKLog.d(TAG, "ctor");
        ThreadUtils.checkIsOnMainThread();
        this.apprtcContext = context;
        this.audioManager = (AudioManager) context.getSystemService("audio");
        this.bluetoothManager = AppRTCBluetoothManager.create(context, this);
        this.wiredHeadsetReceiver = new WiredHeadsetReceiver();
        this.amState = AudioManagerState.UNINITIALIZED;
        if (speakerPhoneType == SpeakerPhoneType.AUTO) {
            this.useSpeakerphone = "auto";
        } else if (speakerPhoneType == SpeakerPhoneType.TRUE) {
            this.useSpeakerphone = "true";
        } else if (speakerPhoneType == SpeakerPhoneType.FALSE) {
            this.useSpeakerphone = "false";
        } else {
            this.useSpeakerphone = "auto";
        }
        OKLog.d(TAG, "useSpeakerphone: " + this.useSpeakerphone);
        if (this.useSpeakerphone.equals("false")) {
            this.defaultAudioDevice = AudioDevice.EARPIECE;
        } else {
            this.defaultAudioDevice = AudioDevice.SPEAKER_PHONE;
        }
        this.proximitySensor = AppRTCProximitySensor.create(context, new Runnable() { // from class: com.jingdong.common.jdreactFramework.modules.uphone_sdk.audio.AppRTCAudioManager.1
            @Override // java.lang.Runnable
            public void run() {
                AppRTCAudioManager.this.onProximitySensorChangedState();
            }
        });
        OKLog.d(TAG, "defaultAudioDevice: " + this.defaultAudioDevice);
        AppRTCUtils.logDeviceInfo(TAG);
    }

    public static AppRTCAudioManager create(Context context, SpeakerPhoneType speakerPhoneType) {
        return new AppRTCAudioManager(context, speakerPhoneType);
    }

    private boolean hasEarpiece() {
        return this.apprtcContext.getPackageManager().hasSystemFeature("android.hardware.telephony");
    }

    @Deprecated
    private boolean hasWiredHeadset() {
        if (Build.VERSION.SDK_INT < 23) {
            return this.audioManager.isWiredHeadsetOn();
        }
        for (AudioDeviceInfo audioDeviceInfo : this.audioManager.getDevices(3)) {
            int type = audioDeviceInfo.getType();
            if (type == 3) {
                OKLog.d(TAG, "hasWiredHeadset: found wired headset");
                return true;
            } else if (type == 11) {
                OKLog.d(TAG, "hasWiredHeadset: found USB audio device");
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onProximitySensorChangedState() {
        if (this.useSpeakerphone.equals("auto") && this.audioDevices.size() == 2) {
            Set<AudioDevice> set = this.audioDevices;
            AudioDevice audioDevice = AudioDevice.EARPIECE;
            if (set.contains(audioDevice)) {
                Set<AudioDevice> set2 = this.audioDevices;
                AudioDevice audioDevice2 = AudioDevice.SPEAKER_PHONE;
                if (set2.contains(audioDevice2)) {
                    if (this.proximitySensor.sensorReportsNearState()) {
                        setAudioDeviceInternal(audioDevice);
                    } else {
                        setAudioDeviceInternal(audioDevice2);
                    }
                }
            }
        }
    }

    private void registerReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        this.apprtcContext.registerReceiver(broadcastReceiver, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAudioDeviceInternal(AudioDevice audioDevice) {
        OKLog.d(TAG, "setAudioDeviceInternal(device=" + audioDevice + ")");
        try {
            AppRTCUtils.assertIsTrue(this.audioDevices.contains(audioDevice));
        } catch (AssertionError e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        int i2 = AnonymousClass3.$SwitchMap$com$jingdong$common$jdreactFramework$modules$uphone_sdk$audio$AppRTCAudioManager$AudioDevice[audioDevice.ordinal()];
        if (i2 == 1) {
            setSpeakerphoneOn(true);
        } else if (i2 == 2 || i2 == 3) {
            setSpeakerphoneOn(false);
        } else if (i2 != 4) {
            OKLog.e(TAG, "Invalid audio device selection");
        } else {
            setSpeakerphoneOn(false);
        }
        this.selectedAudioDevice = audioDevice;
    }

    private void setMicrophoneMute(boolean z) {
        if (this.audioManager.isMicrophoneMute() == z) {
            return;
        }
        this.audioManager.setMicrophoneMute(z);
    }

    private void setSpeakerphoneOn(boolean z) {
        if (this.audioManager.isSpeakerphoneOn() == z) {
            return;
        }
        this.audioManager.setSpeakerphoneOn(z);
    }

    private void unregisterReceiver(BroadcastReceiver broadcastReceiver) {
        this.apprtcContext.unregisterReceiver(broadcastReceiver);
    }

    public void bluetoothStart() {
        try {
            this.bluetoothManager.start();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public Set<AudioDevice> getAudioDevices() {
        ThreadUtils.checkIsOnMainThread();
        return Collections.unmodifiableSet(new HashSet(this.audioDevices));
    }

    public AudioDevice getSelectedAudioDevice() {
        ThreadUtils.checkIsOnMainThread();
        return this.selectedAudioDevice;
    }

    public void selectAudioDevice(AudioDevice audioDevice) {
        ThreadUtils.checkIsOnMainThread();
        if (!this.audioDevices.contains(audioDevice)) {
            OKLog.e(TAG, "Can not select " + audioDevice + " from available " + this.audioDevices);
        }
        this.userSelectedAudioDevice = audioDevice;
        updateAudioDeviceState();
    }

    public void setDefaultAudioDevice(AudioDevice audioDevice) {
        ThreadUtils.checkIsOnMainThread();
        int i2 = AnonymousClass3.$SwitchMap$com$jingdong$common$jdreactFramework$modules$uphone_sdk$audio$AppRTCAudioManager$AudioDevice[audioDevice.ordinal()];
        if (i2 == 1) {
            this.defaultAudioDevice = audioDevice;
        } else if (i2 != 2) {
            OKLog.e(TAG, "Invalid default audio device selection");
        } else if (hasEarpiece()) {
            this.defaultAudioDevice = audioDevice;
        } else {
            this.defaultAudioDevice = AudioDevice.SPEAKER_PHONE;
        }
        OKLog.d(TAG, "setDefaultAudioDevice(device=" + this.defaultAudioDevice + ")");
        updateAudioDeviceState();
    }

    public void start(AudioManagerEvents audioManagerEvents) {
        OKLog.d(TAG, "start");
        ThreadUtils.checkIsOnMainThread();
        AudioManagerState audioManagerState = this.amState;
        AudioManagerState audioManagerState2 = AudioManagerState.RUNNING;
        if (audioManagerState == audioManagerState2) {
            OKLog.e(TAG, "AudioManager is already active");
            return;
        }
        OKLog.d(TAG, "AudioManager starts...");
        this.audioManagerEvents = audioManagerEvents;
        this.amState = audioManagerState2;
        this.savedAudioMode = this.audioManager.getMode();
        this.savedIsSpeakerPhoneOn = this.audioManager.isSpeakerphoneOn();
        this.savedIsMicrophoneMute = this.audioManager.isMicrophoneMute();
        this.hasWiredHeadset = hasWiredHeadset();
        AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() { // from class: com.jingdong.common.jdreactFramework.modules.uphone_sdk.audio.AppRTCAudioManager.2
            @Override // android.media.AudioManager.OnAudioFocusChangeListener
            public void onAudioFocusChange(int i2) {
                String str;
                if (i2 == -3) {
                    str = "AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK";
                } else if (i2 == -2) {
                    str = "AUDIOFOCUS_LOSS_TRANSIENT";
                } else if (i2 == -1) {
                    str = "AUDIOFOCUS_LOSS";
                } else if (i2 != 1) {
                    str = i2 != 2 ? i2 != 3 ? i2 != 4 ? "AUDIOFOCUS_INVALID" : "AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE" : "AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK" : "AUDIOFOCUS_GAIN_TRANSIENT";
                } else {
                    AudioDevice audioDevice = AppRTCAudioManager.this.selectedAudioDevice;
                    AudioDevice audioDevice2 = AudioDevice.EARPIECE;
                    if (audioDevice == audioDevice2 && AppRTCAudioManager.this.audioManager.isSpeakerphoneOn()) {
                        AppRTCAudioManager.this.setAudioDeviceInternal(audioDevice2);
                    }
                    str = "AUDIOFOCUS_GAIN";
                }
                OKLog.d(AppRTCAudioManager.TAG, String.format("onAudioFocusChange[type=%s, device=%s, model=%d, ringModel=%d, streamVolume=%d, speaker=%s]", str, AppRTCAudioManager.this.selectedAudioDevice, Integer.valueOf(AppRTCAudioManager.this.audioManager.getMode()), Integer.valueOf(AppRTCAudioManager.this.audioManager.getRingerMode()), Integer.valueOf(AppRTCAudioManager.this.audioManager.getStreamVolume(0)), Boolean.valueOf(AppRTCAudioManager.this.audioManager.isSpeakerphoneOn())));
            }
        };
        this.audioFocusChangeListener = onAudioFocusChangeListener;
        if (this.audioManager.requestAudioFocus(onAudioFocusChangeListener, 0, 2) == 1) {
            OKLog.d(TAG, "Audio focus request granted for VOICE_CALL streams");
        } else {
            OKLog.e(TAG, "Audio focus request failed");
        }
        this.audioManager.setMode(3);
        setMicrophoneMute(false);
        AudioDevice audioDevice = AudioDevice.NONE;
        this.userSelectedAudioDevice = audioDevice;
        this.selectedAudioDevice = audioDevice;
        this.audioDevices.clear();
        bluetoothStart();
        updateAudioDeviceState();
        registerReceiver(this.wiredHeadsetReceiver, new IntentFilter("android.intent.action.HEADSET_PLUG"));
        OKLog.d(TAG, "AudioManager started");
    }

    public void stop() {
        OKLog.d(TAG, "stop");
        ThreadUtils.checkIsOnMainThread();
        if (this.amState != AudioManagerState.RUNNING) {
            OKLog.e(TAG, "Trying to stop AudioManager in incorrect state: " + this.amState);
            return;
        }
        this.amState = AudioManagerState.UNINITIALIZED;
        unregisterReceiver(this.wiredHeadsetReceiver);
        this.bluetoothManager.stop();
        setSpeakerphoneOn(this.savedIsSpeakerPhoneOn);
        setMicrophoneMute(this.savedIsMicrophoneMute);
        this.audioManager.setMode(this.savedAudioMode);
        this.audioManager.abandonAudioFocus(this.audioFocusChangeListener);
        this.audioFocusChangeListener = null;
        OKLog.d(TAG, "Abandoned audio focus for VOICE_CALL streams");
        AppRTCProximitySensor appRTCProximitySensor = this.proximitySensor;
        if (appRTCProximitySensor != null) {
            appRTCProximitySensor.stop();
            this.proximitySensor = null;
        }
        this.audioManagerEvents = null;
        OKLog.d(TAG, "AudioManager stopped");
    }

    public void updateAudioDeviceState() {
        AudioDevice audioDevice;
        AudioDevice audioDevice2;
        AudioDevice audioDevice3;
        ThreadUtils.checkIsOnMainThread();
        OKLog.d(TAG, "--- updateAudioDeviceState: wired headset=" + this.hasWiredHeadset + ", BT state=" + this.bluetoothManager.getState());
        OKLog.d(TAG, "Device status: available=" + this.audioDevices + ", selected=" + this.selectedAudioDevice + ", user selected=" + this.userSelectedAudioDevice);
        AppRTCBluetoothManager.State state = this.bluetoothManager.getState();
        AppRTCBluetoothManager.State state2 = AppRTCBluetoothManager.State.HEADSET_AVAILABLE;
        if (state == state2 || this.bluetoothManager.getState() == AppRTCBluetoothManager.State.HEADSET_UNAVAILABLE || this.bluetoothManager.getState() == AppRTCBluetoothManager.State.SCO_DISCONNECTING) {
            this.bluetoothManager.updateDevice();
        }
        HashSet hashSet = new HashSet();
        AppRTCBluetoothManager.State state3 = this.bluetoothManager.getState();
        AppRTCBluetoothManager.State state4 = AppRTCBluetoothManager.State.SCO_CONNECTED;
        if (state3 == state4 || this.bluetoothManager.getState() == AppRTCBluetoothManager.State.SCO_CONNECTING || this.bluetoothManager.getState() == state2) {
            hashSet.add(AudioDevice.BLUETOOTH);
        }
        if (this.hasWiredHeadset) {
            hashSet.add(AudioDevice.WIRED_HEADSET);
        } else {
            hashSet.add(AudioDevice.SPEAKER_PHONE);
            if (hasEarpiece()) {
                hashSet.add(AudioDevice.EARPIECE);
            }
        }
        boolean z = true;
        boolean z2 = !this.audioDevices.equals(hashSet);
        this.audioDevices = hashSet;
        if (this.bluetoothManager.getState() == AppRTCBluetoothManager.State.HEADSET_UNAVAILABLE && this.userSelectedAudioDevice == AudioDevice.BLUETOOTH) {
            this.userSelectedAudioDevice = AudioDevice.NONE;
        }
        if (!this.hasWiredHeadset && this.userSelectedAudioDevice == AudioDevice.WIRED_HEADSET) {
            this.userSelectedAudioDevice = AudioDevice.EARPIECE;
        }
        boolean z3 = false;
        boolean z4 = this.bluetoothManager.getState() == state2 && ((audioDevice3 = this.userSelectedAudioDevice) == AudioDevice.NONE || audioDevice3 == AudioDevice.BLUETOOTH);
        if ((this.bluetoothManager.getState() == state4 || this.bluetoothManager.getState() == AppRTCBluetoothManager.State.SCO_CONNECTING) && (audioDevice = this.userSelectedAudioDevice) != AudioDevice.NONE && audioDevice != AudioDevice.BLUETOOTH) {
            z3 = true;
        }
        if (this.bluetoothManager.getState() == state2 || this.bluetoothManager.getState() == AppRTCBluetoothManager.State.SCO_CONNECTING || this.bluetoothManager.getState() == state4) {
            OKLog.d(TAG, "Need BT audio: start=" + z4 + ", stop=" + z3 + ", BT state=" + this.bluetoothManager.getState());
        }
        if (z3) {
            this.bluetoothManager.stopScoAudio();
            this.bluetoothManager.updateDevice();
        }
        if (!z4 || z3 || this.bluetoothManager.startScoAudio()) {
            z = z2;
        } else {
            this.audioDevices.remove(AudioDevice.BLUETOOTH);
        }
        if (this.bluetoothManager.getState() != state4 && this.bluetoothManager.getState() != state2) {
            if (this.hasWiredHeadset) {
                audioDevice2 = AudioDevice.WIRED_HEADSET;
            } else {
                audioDevice2 = this.userSelectedAudioDevice;
                if (audioDevice2 != AudioDevice.SPEAKER_PHONE && audioDevice2 != AudioDevice.EARPIECE) {
                    audioDevice2 = this.defaultAudioDevice;
                }
            }
        } else {
            audioDevice2 = AudioDevice.BLUETOOTH;
        }
        AudioDevice audioDevice4 = this.userSelectedAudioDevice;
        AudioDevice audioDevice5 = AudioDevice.SPEAKER_PHONE;
        if (audioDevice4 == audioDevice5) {
            audioDevice2 = audioDevice5;
        }
        if (audioDevice2 != this.selectedAudioDevice || z) {
            setAudioDeviceInternal(audioDevice2);
            OKLog.d(TAG, "New device status: available=" + this.audioDevices + ", selected=" + audioDevice2);
            AudioManagerEvents audioManagerEvents = this.audioManagerEvents;
            if (audioManagerEvents != null) {
                audioManagerEvents.onAudioDeviceChanged(this.selectedAudioDevice, this.audioDevices);
            }
        }
        OKLog.d(TAG, "--- updateAudioDeviceState done");
    }
}
