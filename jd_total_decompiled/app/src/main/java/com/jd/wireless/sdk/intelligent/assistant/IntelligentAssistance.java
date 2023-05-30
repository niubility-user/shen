package com.jd.wireless.sdk.intelligent.assistant;

import android.content.Context;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.Surface;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.un.voice.asr.OnSpeechListener;
import com.jd.lib.un.voice.asr.UnAsrHelper;
import com.jd.lib.un.voice.asr.UnAsrType;
import com.jd.lib.un.voice.tts.OnSpeechSynthesizeListener;
import com.jd.lib.un.voice.tts.UnTtsConfig;
import com.jd.lib.un.voice.tts.UnTtsHelper;
import com.jd.wireless.sdk.intelligent.assistant.audio.record.IAudioRecordCallBack;
import com.jd.wireless.sdk.intelligent.assistant.audio.record.RecordHandler;
import com.jd.wireless.sdk.intelligent.assistant.runnable.NotificationSdkInitComplete;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.app.mall.voice.JDVoiceConstant;
import com.jingdong.cleanmvp.engine.HttpGroupUtil;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.common.runTimeConfig.ConfigUtil;
import com.jingdong.common.unification.voice.UnVoiceConfigHelper;
import com.jingdong.common.utils.StatisticsReportUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.auraSetting.AuraBundleConfig;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.FileService;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.secure.Base64;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.sdk.oklog.OKLog;
import g.f.a.b.b;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.UUID;
import org.json.JSONException;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
import tv.danmaku.ijk.media.player.misc.IMediaFormat;

/* loaded from: classes18.dex */
public class IntelligentAssistance {
    public static final String AMR_WB_FILE_HEAD = "#!AMR-WB\n";
    private static final String TAG = "IntelligentAssistance";
    private static final String VERSION_NAME = "2.0.0";
    private static final String VOICE_FILE_EXTENSION_ARM = ".amr";
    private static final String VOICE_FORMAT_ARM = "amr";
    public static final String VOICE_INPUT_SAMPLE_FILE_NAME = "voiceInputSample";
    private static IntelligentAssistance instance;
    private static Handler mainThreadhandler;
    private UnAsrHelper asrHelper;
    private IAudioRecordCallBack audioRecordCallBack;
    private JDJSONObject businessConfig;
    private String businessNumber;
    private ExtendCallProxy callProxy;
    private Context context;
    private long endTimestamp;
    private String fileExtensionName;
    private IntelligentAssistanceCallBack intelligentAssistanceCallBack;
    private boolean isRecognition;
    private boolean isSingleVoice;
    private boolean isUseAiVoiceAsr;
    private boolean isUseAiVoiceTts;
    private OnSpeechListener listener = new OnSpeechListener() { // from class: com.jd.wireless.sdk.intelligent.assistant.IntelligentAssistance.1
        {
            IntelligentAssistance.this = this;
        }

        @Override // com.jd.lib.un.voice.asr.OnSpeechListener
        public void onEnd() {
            if (!IntelligentAssistance.this.isSingleVoice) {
                if (IntelligentAssistance.this.intelligentAssistanceCallBack != null) {
                    IntelligentAssistance.this.intelligentAssistanceCallBack.phoneticRecognitionResult("", true);
                }
            } else if (IntelligentAssistance.this.intelligentAssistanceCallBack != null) {
                IntelligentAssistance.this.intelligentAssistanceCallBack.speakStop();
            }
        }

        @Override // com.jd.lib.un.voice.asr.OnSpeechListener
        public void onError(int i2, String str) {
            if (IntelligentAssistance.this.intelligentAssistanceCallBack != null) {
                IntelligentAssistance.this.intelligentAssistanceCallBack.recognitionError((byte) -127);
            }
        }

        @Override // com.jd.lib.un.voice.asr.OnSpeechListener
        public void onPrepared() {
        }

        @Override // com.jd.lib.un.voice.asr.OnSpeechListener
        public void onResult(String str) {
            if (TextUtils.isEmpty(str) || IntelligentAssistance.this.intelligentAssistanceCallBack == null) {
                return;
            }
            IntelligentAssistance.this.intelligentAssistanceCallBack.phoneticRecognitionResult(str, IntelligentAssistance.this.isSingleVoice);
            if (IntelligentAssistance.this.speechRecognitionResults != null) {
                IntelligentAssistance.this.speechRecognitionResults.append(str);
            }
        }

        @Override // com.jd.lib.un.voice.asr.OnSpeechListener
        public void onStartSpeech() {
        }

        @Override // com.jd.lib.un.voice.asr.OnSpeechListener
        public void onTemp(String str) {
            if (TextUtils.isEmpty(str) || IntelligentAssistance.this.intelligentAssistanceCallBack == null) {
                return;
            }
            IntelligentAssistance.this.intelligentAssistanceCallBack.phoneticRecognitionResult(str, false);
            if (IntelligentAssistance.this.speechRecognitionResults != null) {
                IntelligentAssistance.this.speechRecognitionResults.append(str);
            }
        }

        @Override // com.jd.lib.un.voice.asr.OnSpeechListener
        public void onVolume(int i2) {
            if (IntelligentAssistance.this.intelligentAssistanceCallBack != null) {
                IntelligentAssistance.this.intelligentAssistanceCallBack.changVolum((byte) i2);
            }
        }
    };
    private RecordHandler recordHandler;
    private StringBuffer speechRecognitionResults;
    private long startTimestamp;
    private ThirdPartySpeechRecognitionConfig thirdPartySpeechRecognitionConfig;
    private UnTtsHelper ttsHelper;
    private String voiceInputSampleSaveDirectoryPath;

    private IntelligentAssistance(Context context) {
        mainThreadhandler = new Handler(Looper.getMainLooper());
        StringBuilder sb = new StringBuilder();
        sb.append(FileService.getExternalFilesDir(null).getAbsolutePath());
        String str = File.separator;
        sb.append(str);
        sb.append(VOICE_INPUT_SAMPLE_FILE_NAME);
        sb.append(str);
        this.voiceInputSampleSaveDirectoryPath = sb.toString();
        File file = new File(this.voiceInputSampleSaveDirectoryPath);
        if (file.exists()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    file2.delete();
                }
            }
        } else {
            file.mkdirs();
        }
        this.context = context;
    }

    public static final String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    private void getConfig() {
        JDJSONObject parseObject = JDJSON.parseObject(ConfigUtil.getStringFromPreference(ConfigUtil.KEY_VOICE_SDK_CONFIG));
        if (parseObject != null && parseObject.getJSONObject(this.businessNumber) != null) {
            this.businessConfig = parseObject.getJSONObject(this.businessNumber);
        } else {
            this.businessConfig = new JDJSONObject();
        }
    }

    public static IntelligentAssistance getInstance() {
        IntelligentAssistance intelligentAssistance;
        IntelligentAssistance intelligentAssistance2 = instance;
        if (intelligentAssistance2 != null) {
            return intelligentAssistance2;
        }
        synchronized (IntelligentAssistance.class) {
            if (instance == null) {
                AuraBundleConfig.getInstance().loadBundle(AuraBundleInfos.getBundleNameFromBundleId(57));
                instance = new IntelligentAssistance(JdSdk.getInstance().getApplicationContext());
            }
            intelligentAssistance = instance;
        }
        return intelligentAssistance;
    }

    private void initJdRecord() {
        this.audioRecordCallBack = new AudioRecordCallBack(instance, this.intelligentAssistanceCallBack);
        try {
            RecordHandler recordHandler = new RecordHandler(this.businessConfig.toJSONString(), this.voiceInputSampleSaveDirectoryPath + VOICE_INPUT_SAMPLE_FILE_NAME, this.audioRecordCallBack);
            this.recordHandler = recordHandler;
            if (recordHandler != null) {
                recordHandler.prepare();
                IntelligentAssistanceCallBack intelligentAssistanceCallBack = this.intelligentAssistanceCallBack;
                if (intelligentAssistanceCallBack != null) {
                    intelligentAssistanceCallBack.initComplete((byte) 0);
                }
            }
        } catch (JSONException e2) {
            OKLog.e(TAG, e2);
            IntelligentAssistanceCallBack intelligentAssistanceCallBack2 = this.intelligentAssistanceCallBack;
            if (intelligentAssistanceCallBack2 != null) {
                intelligentAssistanceCallBack2.initComplete((byte) -127);
            }
        }
    }

    private void initJdTranscoding() {
    }

    private void initThirdPartySpeechLibrary() {
        this.thirdPartySpeechRecognitionConfig = ThirdPartySpeechRecognitionConfig.parseConfig(this.businessConfig.toJSONString());
        this.callProxy = ExtendCallProxy.getInstance();
        initThirdPartySpeechRecognition();
        initThirdPartySpeechSynthesis();
        JDMtaUtils.onClick(JdSdk.getInstance().getApplicationContext(), "VoiceSDKiFlytek", "");
    }

    private void initThirdPartySpeechRecognition() {
        this.callProxy.initThirdPartySpeechRecognitionEngine(this.thirdPartySpeechRecognitionConfig);
        if (this.thirdPartySpeechRecognitionConfig.isUploadAudioData()) {
            if (!TextUtils.isEmpty(this.callProxy.getVoiceSampleFormat())) {
                this.fileExtensionName = OrderISVUtil.MONEY_DECIMAL + this.callProxy.getVoiceSampleFormat();
                this.callProxy.setVoiceSavePath(this.voiceInputSampleSaveDirectoryPath + VOICE_INPUT_SAMPLE_FILE_NAME + this.fileExtensionName);
            }
        } else {
            this.callProxy.setVoiceSavePath(null);
        }
        this.callProxy.setThirdPartySpeechRecognizerListener(new ThirdPartySpeechRecognizerListener() { // from class: com.jd.wireless.sdk.intelligent.assistant.IntelligentAssistance.2
            {
                IntelligentAssistance.this = this;
            }

            @Override // com.jd.wireless.sdk.intelligent.assistant.ThirdPartySpeechRecognizerListener
            public void onEndOfSpeech() {
                if (IntelligentAssistance.this.intelligentAssistanceCallBack != null) {
                    IntelligentAssistance.this.intelligentAssistanceCallBack.speakStop();
                }
            }

            @Override // com.jd.wireless.sdk.intelligent.assistant.ThirdPartySpeechRecognizerListener
            public void onError(byte b) {
                if (IntelligentAssistance.this.intelligentAssistanceCallBack != null) {
                    IntelligentAssistance.this.intelligentAssistanceCallBack.recognitionError((byte) -127);
                }
            }

            @Override // com.jd.wireless.sdk.intelligent.assistant.ThirdPartySpeechRecognizerListener
            public void onResult(String str, boolean z) {
                JDMtaUtils.onClick(JdSdk.getInstance().getApplicationContext(), "VoiceSDKiFlytek", "");
                if (IntelligentAssistance.this.intelligentAssistanceCallBack != null) {
                    IntelligentAssistance.this.intelligentAssistanceCallBack.phoneticRecognitionResult(str, z);
                    IntelligentAssistance.this.speechRecognitionResults.append(str);
                }
                if (z) {
                    IntelligentAssistance intelligentAssistance = IntelligentAssistance.this;
                    intelligentAssistance.uploadVoiceSample(intelligentAssistance.processAudioSample(intelligentAssistance.speechRecognitionResults.toString()));
                    IntelligentAssistance.this.speechRecognitionResults.setLength(0);
                }
            }

            @Override // com.jd.wireless.sdk.intelligent.assistant.ThirdPartySpeechRecognizerListener
            public void onRmsChanged(double d) {
                if (IntelligentAssistance.this.intelligentAssistanceCallBack != null) {
                    IntelligentAssistance.this.intelligentAssistanceCallBack.changVolum((byte) d);
                }
            }

            @Override // com.jd.wireless.sdk.intelligent.assistant.ThirdPartySpeechRecognizerListener
            public void onStart() {
                if (IntelligentAssistance.this.intelligentAssistanceCallBack != null) {
                    IntelligentAssistance.this.intelligentAssistanceCallBack.phoneticRecognitionStart();
                }
            }
        });
        this.speechRecognitionResults = new StringBuffer();
    }

    private void initThirdPartySpeechSynthesis() {
        this.callProxy.initThirdPartySpeechSynthesisEngine();
        this.callProxy.setThirdPartySpeechSynthesizerListener(new ThirdPartySpeechSynthesizerListener() { // from class: com.jd.wireless.sdk.intelligent.assistant.IntelligentAssistance.4
            {
                IntelligentAssistance.this = this;
            }

            @Override // com.jd.wireless.sdk.intelligent.assistant.ThirdPartySpeechSynthesizerListener
            public void onBufferProgress(int i2, int i3, int i4, String str) {
            }

            @Override // com.jd.wireless.sdk.intelligent.assistant.ThirdPartySpeechSynthesizerListener
            public void onCompleted(int i2) {
                if (IntelligentAssistance.this.intelligentAssistanceCallBack != null) {
                    IntelligentAssistance.this.intelligentAssistanceCallBack.speechSynthesisStop(0);
                }
            }

            @Override // com.jd.wireless.sdk.intelligent.assistant.ThirdPartySpeechSynthesizerListener
            public void onEvent(int i2, int i3, int i4) {
            }

            @Override // com.jd.wireless.sdk.intelligent.assistant.ThirdPartySpeechSynthesizerListener
            public void onSpeakBegin() {
            }

            @Override // com.jd.wireless.sdk.intelligent.assistant.ThirdPartySpeechSynthesizerListener
            public void onSpeakPaused() {
            }

            @Override // com.jd.wireless.sdk.intelligent.assistant.ThirdPartySpeechSynthesizerListener
            public void onSpeakProgress(int i2, int i3, int i4) {
            }

            @Override // com.jd.wireless.sdk.intelligent.assistant.ThirdPartySpeechSynthesizerListener
            public void onSpeakResumed() {
            }
        });
    }

    private void initTtsListener() {
        UnTtsHelper unTtsHelper = this.ttsHelper;
        if (unTtsHelper != null) {
            unTtsHelper.setOnSpeechSynthesizeListener(new OnSpeechSynthesizeListener() { // from class: com.jd.wireless.sdk.intelligent.assistant.IntelligentAssistance.3
                {
                    IntelligentAssistance.this = this;
                }

                @Override // com.jd.lib.un.voice.tts.OnSpeechSynthesizeListener
                public void onError(String str, String str2) {
                    IntelligentAssistance.mainThreadhandler.post(new Runnable() { // from class: com.jd.wireless.sdk.intelligent.assistant.IntelligentAssistance.3.2
                        {
                            AnonymousClass3.this = this;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            if (IntelligentAssistance.this.intelligentAssistanceCallBack != null) {
                                IntelligentAssistance.this.intelligentAssistanceCallBack.speechSynthesisStop(0);
                            }
                        }
                    });
                }

                @Override // com.jd.lib.un.voice.tts.OnSpeechSynthesizeListener
                public void onPlayFinish(String str) {
                    IntelligentAssistance.mainThreadhandler.post(new Runnable() { // from class: com.jd.wireless.sdk.intelligent.assistant.IntelligentAssistance.3.1
                        {
                            AnonymousClass3.this = this;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            if (IntelligentAssistance.this.intelligentAssistanceCallBack != null) {
                                IntelligentAssistance.this.intelligentAssistanceCallBack.speechSynthesisStop(0);
                            }
                        }
                    });
                }

                @Override // com.jd.lib.un.voice.tts.OnSpeechSynthesizeListener
                public void onPlayPause(String str) {
                }

                @Override // com.jd.lib.un.voice.tts.OnSpeechSynthesizeListener
                public void onPlayResume(String str) {
                }

                @Override // com.jd.lib.un.voice.tts.OnSpeechSynthesizeListener
                public void onPlayStart(String str) {
                }

                @Override // com.jd.lib.un.voice.tts.OnSpeechSynthesizeListener
                public void onSynthesizeFinish(String str) {
                }

                @Override // com.jd.lib.un.voice.tts.OnSpeechSynthesizeListener
                public void onSynthesizeStart(String str) {
                }
            });
        }
    }

    private boolean isUserThirdPartySpeechRecognition() {
        JDJSONObject jDJSONObject = this.businessConfig;
        return jDJSONObject == null || jDJSONObject.getIntValue("type") != 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:260:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x0086 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:266:0x0096 A[Catch: IllegalStateException -> 0x01e9, IOException -> 0x01f0, TRY_LEAVE, TryCatch #13 {IOException -> 0x01f0, IllegalStateException -> 0x01e9, blocks: (B:264:0x0088, B:266:0x0096, B:272:0x00c1, B:275:0x0118, B:277:0x011e, B:279:0x012f, B:271:0x00be), top: B:339:0x0088 }] */
    /* JADX WARN: Removed duplicated region for block: B:287:0x0187 A[Catch: IllegalStateException -> 0x01e2, IOException -> 0x01e4, TryCatch #8 {IllegalStateException -> 0x01e2, blocks: (B:281:0x014a, B:285:0x0181, B:287:0x0187, B:289:0x018b, B:290:0x01a4, B:294:0x01aa, B:304:0x01bc, B:306:0x01c5, B:309:0x01d4, B:282:0x0152), top: B:330:0x014a }] */
    /* JADX WARN: Removed duplicated region for block: B:307:0x01d0  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x01e6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public JDJSONObject processAudioSample(String str) {
        MediaCodec mediaCodec;
        MediaFormat mediaFormat;
        MediaExtractor mediaExtractor;
        File file;
        FileInputStream fileInputStream;
        boolean z;
        long j2;
        int dequeueOutputBuffer;
        Object obj;
        File file2 = new File(this.voiceInputSampleSaveDirectoryPath + VOICE_INPUT_SAMPLE_FILE_NAME + this.fileExtensionName);
        if (file2.exists()) {
            String generateUUID = generateUUID();
            File file3 = new File(this.voiceInputSampleSaveDirectoryPath + generateUUID + this.fileExtensionName);
            file2.renameTo(file3);
            if (Build.VERSION.SDK_INT >= 16) {
                try {
                    mediaCodec = MediaCodec.createEncoderByType("audio/amr-wb");
                    try {
                        mediaFormat = new MediaFormat();
                        try {
                            mediaFormat.setString(IMediaFormat.KEY_MIME, "audio/amr-wb");
                        } catch (IOException e2) {
                            e = e2;
                            OKLog.e(TAG, e);
                            if (mediaCodec != null) {
                            }
                            if (mediaCodec != null) {
                                try {
                                    mediaExtractor.setDataSource(file3.getAbsolutePath());
                                    if (mediaExtractor.getTrackCount() != 1) {
                                    }
                                } catch (IOException e3) {
                                    e = e3;
                                    file = file3;
                                } catch (IllegalStateException e4) {
                                    e = e4;
                                    file = file3;
                                }
                                JDJSONObject jDJSONObject = new JDJSONObject();
                                jDJSONObject.put("fileName", (Object) file3.getName());
                                jDJSONObject.put("text", (Object) str);
                                jDJSONObject.put("serviceProvider", (Object) this.businessConfig.getString("type"));
                                return jDJSONObject;
                            }
                            file = file3;
                            file3 = file;
                            JDJSONObject jDJSONObject2 = new JDJSONObject();
                            jDJSONObject2.put("fileName", (Object) file3.getName());
                            jDJSONObject2.put("text", (Object) str);
                            jDJSONObject2.put("serviceProvider", (Object) this.businessConfig.getString("type"));
                            return jDJSONObject2;
                        } catch (IllegalArgumentException e5) {
                            e = e5;
                            OKLog.e(TAG, e);
                            if (mediaCodec != null) {
                            }
                            if (mediaCodec != null) {
                            }
                            file = file3;
                            file3 = file;
                            JDJSONObject jDJSONObject22 = new JDJSONObject();
                            jDJSONObject22.put("fileName", (Object) file3.getName());
                            jDJSONObject22.put("text", (Object) str);
                            jDJSONObject22.put("serviceProvider", (Object) this.businessConfig.getString("type"));
                            return jDJSONObject22;
                        }
                    } catch (IOException e6) {
                        e = e6;
                        mediaFormat = null;
                    } catch (IllegalArgumentException e7) {
                        e = e7;
                        mediaFormat = null;
                    }
                } catch (IOException e8) {
                    e = e8;
                    mediaCodec = null;
                    mediaFormat = null;
                } catch (IllegalArgumentException e9) {
                    e = e9;
                    mediaCodec = null;
                    mediaFormat = null;
                }
                mediaExtractor = mediaCodec != null ? new MediaExtractor() : null;
                if (mediaCodec != null && mediaExtractor != null) {
                    mediaExtractor.setDataSource(file3.getAbsolutePath());
                    if (mediaExtractor.getTrackCount() != 1) {
                        int i2 = 0;
                        mediaExtractor.selectTrack(0);
                        MediaFormat trackFormat = mediaExtractor.getTrackFormat(0);
                        mediaExtractor.release();
                        mediaFormat.setInteger("sample-rate", trackFormat.getInteger("sample-rate"));
                        mediaFormat.setInteger("channel-count", 1);
                        mediaFormat.setInteger(IjkMediaMeta.IJKM_KEY_BITRATE, mediaFormat.getInteger("sample-rate") * 16);
                        try {
                            mediaCodec.configure(mediaFormat, (Surface) null, (MediaCrypto) null, 1);
                        } catch (Exception e10) {
                            OKLog.e(TAG, e10);
                        }
                        mediaCodec.start();
                        File file4 = new File(file3.getParentFile().getAbsolutePath() + "/" + generateUUID + VOICE_FILE_EXTENSION_ARM);
                        file4.createNewFile();
                        FileOutputStream fileOutputStream = new FileOutputStream(file4);
                        fileOutputStream.write(AMR_WB_FILE_HEAD.getBytes());
                        ByteBuffer[] inputBuffers = mediaCodec.getInputBuffers();
                        ByteBuffer[] outputBuffers = mediaCodec.getOutputBuffers();
                        MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
                        FileInputStream fileInputStream2 = new FileInputStream(file3);
                        fileInputStream2.skip(44L);
                        boolean z2 = false;
                        while (true) {
                            if (!z2) {
                                int dequeueInputBuffer = mediaCodec.dequeueInputBuffer(Final.FIVE_SECOND);
                                if (dequeueInputBuffer >= 0) {
                                    ByteBuffer byteBuffer = inputBuffers[dequeueInputBuffer];
                                    int capacity = byteBuffer.capacity();
                                    byteBuffer.clear();
                                    byte[] bArr = new byte[capacity];
                                    int read = fileInputStream2.read(bArr, i2, capacity);
                                    if (read < 0) {
                                        file = file3;
                                        fileInputStream = fileInputStream2;
                                        z = false;
                                        try {
                                            try {
                                                mediaCodec.queueInputBuffer(dequeueInputBuffer, 0, 0, System.nanoTime(), 4);
                                                j2 = Final.FIVE_SECOND;
                                                z2 = true;
                                                dequeueOutputBuffer = mediaCodec.dequeueOutputBuffer(bufferInfo, j2);
                                                if (dequeueOutputBuffer >= 0) {
                                                    if (bufferInfo.size != 0) {
                                                        ByteBuffer byteBuffer2 = outputBuffers[dequeueOutputBuffer];
                                                        byteBuffer2.position(bufferInfo.offset);
                                                        byteBuffer2.limit(bufferInfo.offset + bufferInfo.size);
                                                        byte[] bArr2 = new byte[bufferInfo.size];
                                                        byteBuffer2.get(bArr2);
                                                        byteBuffer2.clear();
                                                        try {
                                                            fileOutputStream.write(bArr2);
                                                        } catch (IOException e11) {
                                                            OKLog.e(TAG, e11);
                                                            try {
                                                                fileOutputStream.close();
                                                                return null;
                                                            } catch (IOException e12) {
                                                                try {
                                                                    e12.printStackTrace();
                                                                    return null;
                                                                } catch (Throwable unused) {
                                                                    return null;
                                                                }
                                                            } catch (Throwable unused2) {
                                                                return null;
                                                            }
                                                        }
                                                    }
                                                    obj = null;
                                                    mediaCodec.releaseOutputBuffer(dequeueOutputBuffer, z);
                                                    if ((bufferInfo.flags & 4) != 0) {
                                                        break;
                                                    }
                                                } else {
                                                    obj = null;
                                                    if (dequeueOutputBuffer == -3) {
                                                        outputBuffers = mediaCodec.getOutputBuffers();
                                                    }
                                                }
                                                fileInputStream2 = fileInputStream;
                                                file3 = file;
                                                i2 = 0;
                                            } catch (IOException e13) {
                                                e = e13;
                                                OKLog.e(TAG, e);
                                                file3 = file;
                                                JDJSONObject jDJSONObject222 = new JDJSONObject();
                                                jDJSONObject222.put("fileName", (Object) file3.getName());
                                                jDJSONObject222.put("text", (Object) str);
                                                jDJSONObject222.put("serviceProvider", (Object) this.businessConfig.getString("type"));
                                                return jDJSONObject222;
                                            }
                                        } catch (IllegalStateException e14) {
                                            e = e14;
                                            OKLog.e(TAG, e);
                                            file3 = file;
                                            JDJSONObject jDJSONObject2222 = new JDJSONObject();
                                            jDJSONObject2222.put("fileName", (Object) file3.getName());
                                            jDJSONObject2222.put("text", (Object) str);
                                            jDJSONObject2222.put("serviceProvider", (Object) this.businessConfig.getString("type"));
                                            return jDJSONObject2222;
                                        }
                                    } else {
                                        file = file3;
                                        fileInputStream = fileInputStream2;
                                        z = false;
                                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                        byteArrayOutputStream.write(bArr, 0, read);
                                        byteBuffer.put(byteArrayOutputStream.toByteArray());
                                        mediaCodec.queueInputBuffer(dequeueInputBuffer, 0, read, System.nanoTime(), 0);
                                        j2 = Final.FIVE_SECOND;
                                        dequeueOutputBuffer = mediaCodec.dequeueOutputBuffer(bufferInfo, j2);
                                        if (dequeueOutputBuffer >= 0) {
                                        }
                                        fileInputStream2 = fileInputStream;
                                        file3 = file;
                                        i2 = 0;
                                    }
                                }
                            }
                            file = file3;
                            fileInputStream = fileInputStream2;
                            z = false;
                            j2 = Final.FIVE_SECOND;
                            dequeueOutputBuffer = mediaCodec.dequeueOutputBuffer(bufferInfo, j2);
                            if (dequeueOutputBuffer >= 0) {
                            }
                            fileInputStream2 = fileInputStream;
                            file3 = file;
                            i2 = 0;
                        }
                        mediaCodec.release();
                        fileOutputStream.close();
                        file.delete();
                        file3 = file4;
                    }
                    JDJSONObject jDJSONObject22222 = new JDJSONObject();
                    jDJSONObject22222.put("fileName", (Object) file3.getName());
                    jDJSONObject22222.put("text", (Object) str);
                    jDJSONObject22222.put("serviceProvider", (Object) this.businessConfig.getString("type"));
                    return jDJSONObject22222;
                }
            }
            file = file3;
            file3 = file;
            JDJSONObject jDJSONObject222222 = new JDJSONObject();
            jDJSONObject222222.put("fileName", (Object) file3.getName());
            jDJSONObject222222.put("text", (Object) str);
            jDJSONObject222222.put("serviceProvider", (Object) this.businessConfig.getString("type"));
            return jDJSONObject222222;
        }
        return null;
    }

    public void uploadVoiceSample(JDJSONObject jDJSONObject) {
        if (!NetUtils.isWifi() || jDJSONObject == null) {
            return;
        }
        try {
            final File file = new File(this.voiceInputSampleSaveDirectoryPath + jDJSONObject.getString("fileName"));
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setHost(Configuration.getNgwHost());
            httpSetting.setFunctionId("voiceUpload");
            httpSetting.setEffect(0);
            String name = file.getName();
            httpSetting.putJsonParam("speech", Base64.encodeFromFile(file.getAbsolutePath()));
            httpSetting.putJsonParam("format", name.substring(name.indexOf(OrderISVUtil.MONEY_DECIMAL) + 1, name.length()));
            httpSetting.putJsonParam("voiceSdkVersion", VERSION_NAME);
            httpSetting.putJsonParam("businessId", this.businessNumber);
            httpSetting.putJsonParam("text", jDJSONObject.getString("text"));
            httpSetting.putJsonParam("supplier", this.businessConfig.getString("type"));
            httpSetting.putJsonParam("dnsType", "1");
            httpSetting.putJsonParam("did", StatisticsReportUtil.genarateDeviceUUID(this.context));
            httpSetting.setListener(new HttpGroup.OnEndListener() { // from class: com.jd.wireless.sdk.intelligent.assistant.IntelligentAssistance.5
                {
                    IntelligentAssistance.this = this;
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                public void onEnd(HttpResponse httpResponse) {
                    file.delete();
                }
            });
            new HttpGroupUtil().getHttpGroupaAsynPool(null, null).add(httpSetting);
        } catch (IOException e2) {
            OKLog.e(TAG, e2);
        }
    }

    private int voiceNameChange(String str) {
        if (TextUtils.equals(str, "xiaoyan")) {
            return 6;
        }
        if (TextUtils.equals(str, "xiaofeng")) {
            return 4;
        }
        return TextUtils.equals(str, "aisduoxu") ? 1 : 0;
    }

    public void cancelSpeak() {
        if (this.isUseAiVoiceAsr) {
            UnAsrHelper unAsrHelper = this.asrHelper;
            if (unAsrHelper != null) {
                unAsrHelper.cancel();
            }
        } else if (isUserThirdPartySpeechRecognition()) {
            ExtendCallProxy extendCallProxy = this.callProxy;
            if (extendCallProxy != null) {
                extendCallProxy.cancelSpeak();
            }
        } else {
            RecordHandler recordHandler = this.recordHandler;
            if (recordHandler != null) {
                this.isRecognition = false;
                recordHandler.stopRecord();
            }
        }
    }

    public void cancelSpeechSynthesizer() {
        UnTtsHelper unTtsHelper;
        if (this.isUseAiVoiceTts && (unTtsHelper = this.ttsHelper) != null) {
            unTtsHelper.cancle();
        }
        ExtendCallProxy extendCallProxy = this.callProxy;
        if (extendCallProxy != null) {
            extendCallProxy.cancelSpeechSynthesizer();
        }
    }

    public void deleteVoiceFile(String str) {
        if (TextUtils.isEmpty(str) && VOICE_FORMAT_ARM.equals(this.recordHandler.getFormat())) {
            File file = new File(this.voiceInputSampleSaveDirectoryPath + VOICE_INPUT_SAMPLE_FILE_NAME + VOICE_FILE_EXTENSION_ARM);
            if (file.exists()) {
                file.delete();
                return;
            }
            return;
        }
        File file2 = new File(this.voiceInputSampleSaveDirectoryPath + str);
        if (file2.exists()) {
            file2.delete();
        }
    }

    public void initSdk(String str, IntelligentAssistanceCallBack intelligentAssistanceCallBack) {
        this.businessNumber = str;
        this.intelligentAssistanceCallBack = intelligentAssistanceCallBack;
        this.isUseAiVoiceAsr = UnVoiceConfigHelper.getInstance().useNewVoiceByNusinessNo(this.businessNumber);
        this.isUseAiVoiceTts = UnVoiceConfigHelper.getInstance().useNewVoiceTts();
        if (this.isUseAiVoiceAsr) {
            if (OKLog.D) {
                b.d(2);
            }
            this.asrHelper = new UnAsrHelper(JdSdk.getInstance().getApplication());
            this.speechRecognitionResults = new StringBuffer();
            if (TextUtils.equals(this.businessNumber, JDVoiceConstant.SEARCH)) {
                this.asrHelper.setArsType(UnAsrType.TOUCH);
                this.isSingleVoice = true;
            } else if (TextUtils.equals(this.businessNumber, JDVoiceConstant.ADDRESS)) {
                this.asrHelper.setArsType(UnAsrType.SINGLE);
                this.isSingleVoice = true;
            } else if (TextUtils.equals(this.businessNumber, "6c44102d")) {
                this.asrHelper.setArsType(UnAsrType.SINGLE);
                this.isSingleVoice = true;
                if (this.isUseAiVoiceTts) {
                    this.ttsHelper = new UnTtsHelper(JdSdk.getInstance().getApplicationContext());
                    initTtsListener();
                }
            } else {
                this.asrHelper.setArsType(UnAsrType.LONG);
                this.isSingleVoice = false;
            }
            this.asrHelper.setOnSpeechListener(this.listener);
        }
        if (!this.isUseAiVoiceTts || !this.isUseAiVoiceAsr) {
            getConfig();
            if (isUserThirdPartySpeechRecognition()) {
                initThirdPartySpeechLibrary();
            } else {
                initJdRecord();
            }
        }
        mainThreadhandler.post(new NotificationSdkInitComplete(intelligentAssistanceCallBack, (byte) 0));
    }

    public boolean isListening() {
        ExtendCallProxy extendCallProxy = this.callProxy;
        if (extendCallProxy != null) {
            return extendCallProxy.isListening();
        }
        return false;
    }

    public boolean isUseAiVoiceAsr() {
        return this.isUseAiVoiceAsr;
    }

    public void setVoiceName(String str) {
        if (this.isUseAiVoiceTts && this.ttsHelper != null) {
            UnTtsConfig unTtsConfig = new UnTtsConfig();
            unTtsConfig.getTtsParam().b("tim", String.valueOf(voiceNameChange(str)));
            this.ttsHelper.setConfig(unTtsConfig);
        }
        ExtendCallProxy extendCallProxy = this.callProxy;
        if (extendCallProxy != null) {
            extendCallProxy.setVoiceName(str);
        }
    }

    public void startSpeak() {
        if (this.isUseAiVoiceAsr) {
            this.startTimestamp = System.currentTimeMillis();
            UnAsrHelper unAsrHelper = this.asrHelper;
            if (unAsrHelper != null) {
                unAsrHelper.start();
            }
            IntelligentAssistanceCallBack intelligentAssistanceCallBack = this.intelligentAssistanceCallBack;
            if (intelligentAssistanceCallBack != null) {
                intelligentAssistanceCallBack.phoneticRecognitionStart();
            }
        } else if (isUserThirdPartySpeechRecognition()) {
            ExtendCallProxy extendCallProxy = this.callProxy;
            if (extendCallProxy != null) {
                extendCallProxy.startSpeak();
            }
            this.startTimestamp = System.currentTimeMillis();
        } else {
            RecordHandler recordHandler = this.recordHandler;
            if (recordHandler != null) {
                recordHandler.startRecord();
                this.isRecognition = true;
            }
        }
    }

    public void startSpeechSynthesizer(String str) {
        if (this.isUseAiVoiceTts) {
            UnTtsHelper unTtsHelper = this.ttsHelper;
            if (unTtsHelper != null) {
                unTtsHelper.start(str);
                return;
            }
            return;
        }
        ExtendCallProxy extendCallProxy = this.callProxy;
        if (extendCallProxy != null) {
            extendCallProxy.startSpeechSynthesizer(str);
        }
    }

    public void stopSpeak() {
        if (this.isUseAiVoiceAsr) {
            if (this.isSingleVoice) {
                long currentTimeMillis = System.currentTimeMillis();
                this.endTimestamp = currentTimeMillis;
                if (currentTimeMillis - this.startTimestamp < 1000) {
                    UnAsrHelper unAsrHelper = this.asrHelper;
                    if (unAsrHelper != null) {
                        unAsrHelper.cancel();
                    }
                    IntelligentAssistanceCallBack intelligentAssistanceCallBack = this.intelligentAssistanceCallBack;
                    if (intelligentAssistanceCallBack != null) {
                        intelligentAssistanceCallBack.recognitionError((byte) 3);
                        return;
                    }
                    return;
                }
            }
            UnAsrHelper unAsrHelper2 = this.asrHelper;
            if (unAsrHelper2 != null) {
                unAsrHelper2.stop();
            }
        } else if (isUserThirdPartySpeechRecognition()) {
            long currentTimeMillis2 = System.currentTimeMillis();
            this.endTimestamp = currentTimeMillis2;
            if (currentTimeMillis2 - this.startTimestamp < 1000) {
                ExtendCallProxy extendCallProxy = this.callProxy;
                if (extendCallProxy != null) {
                    extendCallProxy.cancelSpeak();
                }
                IntelligentAssistanceCallBack intelligentAssistanceCallBack2 = this.intelligentAssistanceCallBack;
                if (intelligentAssistanceCallBack2 != null) {
                    intelligentAssistanceCallBack2.recognitionError((byte) 3);
                    return;
                }
                return;
            }
            ExtendCallProxy extendCallProxy2 = this.callProxy;
            if (extendCallProxy2 != null) {
                extendCallProxy2.stopSpeak();
            }
        } else {
            RecordHandler recordHandler = this.recordHandler;
            if (recordHandler != null) {
                this.isRecognition = true;
                recordHandler.stopRecord();
            }
        }
    }

    public void submitVoiceProceedRecognition() {
        if (this.isRecognition) {
            try {
                IntelligentAssistanceCallBack intelligentAssistanceCallBack = this.intelligentAssistanceCallBack;
                if (intelligentAssistanceCallBack != null) {
                    intelligentAssistanceCallBack.phoneticRecognitionStart();
                }
                File file = new File(this.voiceInputSampleSaveDirectoryPath + VOICE_INPUT_SAMPLE_FILE_NAME + OrderISVUtil.MONEY_DECIMAL + this.recordHandler.getFormat());
                HttpSetting httpSetting = new HttpSetting();
                httpSetting.setHost(Configuration.getNgwHost());
                httpSetting.setFunctionId("voiceProcess");
                httpSetting.setEffect(0);
                httpSetting.putJsonParam("format", this.recordHandler.getFormat());
                httpSetting.putJsonParam("businessId", this.businessNumber);
                httpSetting.putJsonParam("speech", Base64.encodeFromFile(file.getAbsolutePath()));
                httpSetting.putJsonParam("did", StatisticsReportUtil.genarateDeviceUUID(this.context));
                httpSetting.putJsonParam("len", Long.valueOf(file.length()));
                httpSetting.putJsonParam("function", "2");
                httpSetting.putJsonParam("dnsType", "1");
                httpSetting.putJsonParam("voiceSdkVersion", VERSION_NAME);
                httpSetting.setListener(new VoiceProcessCallListener(instance, this.intelligentAssistanceCallBack));
                new HttpGroupUtil().getHttpGroupaAsynPool(null, null).add(httpSetting);
            } catch (IOException e2) {
                OKLog.e(TAG, e2);
            }
        }
    }
}
