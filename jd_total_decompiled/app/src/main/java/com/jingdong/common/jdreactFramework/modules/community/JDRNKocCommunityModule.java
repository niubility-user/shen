package com.jingdong.common.jdreactFramework.modules.community;

import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.WritableMap;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.jdreactFramework.modules.community.upload.UploadMedia;
import com.jingdong.common.jdreactFramework.modules.community.upload.task.BaseUploadTask;
import com.jingdong.common.jdreactFramework.modules.community.upload.task.VideoCompressTask;
import com.jingdong.common.jdreactFramework.modules.community.upload.task.VideoUploadTask;
import com.jingdong.common.jdreactFramework.modules.community.upload.utils.Utils;
import com.jingdong.common.unification.uniutil.CRCUtils;
import com.jingdong.common.utils.ExifUtil;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.SimpleFileUploader;
import com.jingdong.jdsdk.secure.Base64;
import com.jingdong.jdsdk.utils.cache.GlobalImageCache;
import com.jingdong.sdk.platform.business.personal.R2;
import com.jingdong.sdk.threadpool.ThreadManager;
import com.tencent.smtt.sdk.TbsReaderView;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import org.apache.commons.codec.CharEncoding;
import tv.danmaku.ijk.media.ext.report.ReportConstant;

/* loaded from: classes5.dex */
public class JDRNKocCommunityModule extends ReactContextBaseJavaModule {
    private static final String TAG = "JDRNKocCommunityModule";
    private final String AU_CODE;
    private final String IMG_UPLOAD_URL;
    VideoCompressTask compressTask;
    Queue<Media> queue;
    volatile boolean running;
    private VideoUploadTask uploadTask;

    /* loaded from: classes5.dex */
    public class Media {
        Callback errorCallback;
        ReadableMap path;
        Callback successCallback;
        int type;

        public Media(ReadableMap readableMap, Callback callback, Callback callback2, int i2) {
            JDRNKocCommunityModule.this = r1;
            this.path = readableMap;
            this.successCallback = callback;
            this.errorCallback = callback2;
            this.type = i2;
        }
    }

    public JDRNKocCommunityModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.running = false;
        this.AU_CODE = "f14fad7f095190488b0a4050c5a94db0";
        this.IMG_UPLOAD_URL = "https://upload-appimg.jd.com/appImageUpload.action";
        this.queue = new LinkedList();
        this.compressTask = null;
    }

    public void callWithParam(Callback callback, WritableMap writableMap) {
        if (callback != null) {
            callback.invoke(writableMap);
        }
    }

    public void callWithoutParam(Callback callback) {
        if (callback != null) {
            callback.invoke(new Object[0]);
        }
    }

    private UploadMedia createUploadMedia(@NonNull String str) {
        Uri parse;
        if (!str.startsWith("file://")) {
            parse = Uri.parse("file://" + str);
        } else {
            parse = Uri.parse(str);
        }
        if (parse != null) {
            return new UploadMedia(parse);
        }
        return null;
    }

    private void uploadImg(@NonNull Media media) {
        Utils.log(TAG, "\u5f00\u59cb\u4e0a\u4f20\u56fe\u7247");
        final ReadableMap readableMap = media.path;
        final Callback callback = media.successCallback;
        final Callback callback2 = media.errorCallback;
        readableMap.getString("index");
        String string = readableMap.getString("path");
        uploadImg("https://upload-appimg.jd.com/appImageUpload.action", readableMap.hasKey("code") ? readableMap.getString("code") : "f14fad7f095190488b0a4050c5a94db0", newBitmapToByte(string, ExifUtil.readPictureDegree(string), R2.attr.switchBackDrawable, 90, readableMap.hasKey("quality") ? Utils.parse(readableMap.getString("quality"), 50) : 50, readableMap.hasKey("maxLength") ? Utils.parse(readableMap.getString("maxLength"), 1572864L) : 1572864L), new HttpGroup.OnAllListener() { // from class: com.jingdong.common.jdreactFramework.modules.community.JDRNKocCommunityModule.3
            {
                JDRNKocCommunityModule.this = this;
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                JDJSONObject parseUploadImage = JDRNKocCommunityModule.this.parseUploadImage(httpResponse.getFastJsonArray());
                if (parseUploadImage != null) {
                    if (!parseUploadImage.getString("id").equals("1")) {
                        Callback callback3 = callback2;
                        if (callback3 != null) {
                            callback3.invoke(new Object[0]);
                        }
                    } else {
                        WritableMap createMap = Arguments.createMap();
                        Iterator<String> it = parseUploadImage.keySet().iterator();
                        while (it != null && it.hasNext()) {
                            String obj = it.next().toString();
                            createMap.putString(obj, parseUploadImage.getString(obj));
                        }
                        WritableMap createMap2 = Arguments.createMap();
                        ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
                        while (keySetIterator != null && keySetIterator.hasNextKey()) {
                            String nextKey = keySetIterator.nextKey();
                            createMap2.putString(nextKey, readableMap.getString(nextKey));
                        }
                        Callback callback4 = callback;
                        if (callback4 != null) {
                            callback4.invoke(createMap, createMap2);
                        }
                    }
                } else {
                    Callback callback5 = callback2;
                    if (callback5 != null) {
                        callback5.invoke(new Object[0]);
                    }
                }
                JDRNKocCommunityModule jDRNKocCommunityModule = JDRNKocCommunityModule.this;
                jDRNKocCommunityModule.startNext(jDRNKocCommunityModule.queue.peek());
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                Callback callback3 = callback2;
                if (callback3 != null) {
                    callback3.invoke(new Object[0]);
                }
                JDRNKocCommunityModule jDRNKocCommunityModule = JDRNKocCommunityModule.this;
                jDRNKocCommunityModule.startNext(jDRNKocCommunityModule.queue.peek());
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
                JDRNKocCommunityModule.this.queue.poll();
            }
        });
    }

    @ReactMethod
    public void compressVideo(ReadableMap readableMap, Callback callback, Callback callback2) {
        this.queue.offer(new Media(readableMap, callback, callback2, 3));
        if (this.running) {
            return;
        }
        startNext(this.queue.peek());
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return TAG;
    }

    public byte[] newBitmapToByte(String str, int i2, int i3, int i4, int i5, long j2) {
        if (str == null) {
            return null;
        }
        try {
            return CompressUtils.compress(str, i2, i3, i4, i5, j2);
        } catch (Exception e2) {
            e2.printStackTrace();
            GlobalImageCache.getLruBitmapCache().cleanMost();
            return null;
        }
    }

    public JDJSONObject parseUploadImage(JDJSONArray jDJSONArray) {
        if (jDJSONArray == null) {
            return null;
        }
        return jDJSONArray.getJSONObject(0);
    }

    void startNext(Media media) {
        if (media == null) {
            this.running = false;
            return;
        }
        this.running = true;
        int i2 = media.type;
        if (i2 == 1) {
            uploadImg(media);
        } else if (i2 == 2) {
            uploadVideo(media);
        } else if (i2 != 3) {
            this.queue.poll();
            startNext(this.queue.peek());
        } else {
            compressVideo(media);
        }
    }

    @ReactMethod
    public void uploadImage(ReadableMap readableMap, Callback callback, Callback callback2) {
        this.queue.offer(new Media(readableMap, callback, callback2, 1));
        if (this.running) {
            return;
        }
        startNext(this.queue.peek());
    }

    @ReactMethod
    public void uploadVideo(ReadableMap readableMap, Callback callback, Callback callback2) {
        this.queue.offer(new Media(readableMap, callback, callback2, 2));
        if (this.running) {
            return;
        }
        startNext(this.queue.peek());
    }

    private void compressVideo(Media media) {
        String str = TAG;
        Utils.log(str, "\u5f00\u59cb\u538b\u7f29");
        ReadableMap readableMap = media.path;
        final Callback callback = media.successCallback;
        final Callback callback2 = media.errorCallback;
        String string = readableMap.getString("path");
        if (TextUtils.isEmpty(string)) {
            Utils.log("compress", "\u89c6\u9891\u8def\u5f84\u83b7\u53d6\u5931\u8d25");
            callWithoutParam(callback2);
            this.queue.poll();
            startNext(this.queue.peek());
            return;
        }
        final UploadMedia createUploadMedia = createUploadMedia(string);
        Utils.log(str, "\u521b\u5efa\u538b\u7f29\u5bf9\u8c61");
        if (createUploadMedia == null) {
            Utils.log("compress", "\u5bf9\u8c61\u521b\u5efa\u5931\u8d25");
            callWithoutParam(callback2);
            this.queue.poll();
            startNext(this.queue.peek());
            return;
        }
        if (readableMap.hasKey("compressScale")) {
            createUploadMedia.setCompressScaleIndex(readableMap.getString("compressScale"));
        }
        VideoCompressTask videoCompressTask = new VideoCompressTask(null);
        this.compressTask = videoCompressTask;
        videoCompressTask.setCallback(new BaseUploadTask.Listener() { // from class: com.jingdong.common.jdreactFramework.modules.community.JDRNKocCommunityModule.1
            {
                JDRNKocCommunityModule.this = this;
            }

            @Override // com.jingdong.common.jdreactFramework.modules.community.upload.task.BaseUploadTask.Listener
            public void onExecuted() {
                JDRNKocCommunityModule.this.compressTask = null;
                Utils.log(JDRNKocCommunityModule.TAG, "\u538b\u7f29\u5b8c\u6210");
                if (createUploadMedia.isError()) {
                    Utils.log(JDRNKocCommunityModule.TAG, "\u5931\u8d25\u56de\u8c03");
                    JDRNKocCommunityModule.this.callWithoutParam(callback2);
                } else {
                    Utils.log(JDRNKocCommunityModule.TAG, "\u6210\u529f: " + createUploadMedia.getVideoPath());
                    Utils.log(JDRNKocCommunityModule.TAG, "\u538b\u7f29\u540e\u4f53\u79ef: " + createUploadMedia.getVideoSize());
                    WritableMap createMap = Arguments.createMap();
                    createMap.putString(TbsReaderView.KEY_FILE_PATH, createUploadMedia.getVideoPath());
                    createMap.putString("fileSize", String.valueOf(createUploadMedia.getVideoSize()));
                    JDRNKocCommunityModule.this.callWithParam(callback, createMap);
                }
                JDRNKocCommunityModule.this.queue.poll();
                JDRNKocCommunityModule jDRNKocCommunityModule = JDRNKocCommunityModule.this;
                jDRNKocCommunityModule.startNext(jDRNKocCommunityModule.queue.peek());
            }
        });
        this.compressTask.exec(createUploadMedia);
    }

    private void uploadVideo(@NonNull Media media) {
        Utils.log(TAG, "\u5f00\u59cb\u4e0a\u4f20\u89c6\u9891");
        ReadableMap readableMap = media.path;
        final Callback callback = media.successCallback;
        final Callback callback2 = media.errorCallback;
        String string = readableMap.getString("videoId");
        String string2 = readableMap.getString(TbsReaderView.KEY_FILE_PATH);
        String string3 = readableMap.getString("url");
        final String string4 = readableMap.hasKey(ReportConstant.CommonInfo.PLAY_URL) ? readableMap.getString(ReportConstant.CommonInfo.PLAY_URL) : "";
        if (TextUtils.isEmpty(string2)) {
            Utils.log("uploadVideo", "\u89c6\u9891\u5730\u5740\u4e3a\u7a7a");
            WritableMap createMap = Arguments.createMap();
            createMap.putString("videoId", string);
            callWithParam(callback2, createMap);
            this.queue.poll();
            startNext(this.queue.peek());
            return;
        }
        final UploadMedia uploadMedia = new UploadMedia();
        uploadMedia.setUploadFile(new File(string2));
        uploadMedia.setVideoUploadUrl(string3);
        uploadMedia.setVideoId(string);
        VideoUploadTask videoUploadTask = new VideoUploadTask(null);
        this.uploadTask = videoUploadTask;
        videoUploadTask.setCallback(new BaseUploadTask.Listener() { // from class: com.jingdong.common.jdreactFramework.modules.community.JDRNKocCommunityModule.2
            {
                JDRNKocCommunityModule.this = this;
            }

            @Override // com.jingdong.common.jdreactFramework.modules.community.upload.task.BaseUploadTask.Listener
            public void onExecuted() {
                JDRNKocCommunityModule.this.uploadTask = null;
                Utils.log("uploadVideo", "\u4e0a\u4f20\u7ed3\u675f");
                WritableMap createMap2 = Arguments.createMap();
                createMap2.putString("videoId", uploadMedia.getVideoId());
                if (uploadMedia.isError()) {
                    Utils.log("uploadVideo", "\u5931\u8d25\u56de\u8c03");
                    JDRNKocCommunityModule.this.callWithParam(callback2, createMap2);
                } else {
                    Utils.log("uploadVideo", "\u6210\u529f: " + string4);
                    JDRNKocCommunityModule.this.callWithParam(callback, createMap2);
                }
                JDRNKocCommunityModule.this.queue.poll();
                JDRNKocCommunityModule jDRNKocCommunityModule = JDRNKocCommunityModule.this;
                jDRNKocCommunityModule.startNext(jDRNKocCommunityModule.queue.peek());
            }
        });
        this.uploadTask.exec(uploadMedia);
    }

    void uploadImg(final String str, final String str2, final byte[] bArr, final HttpGroup.OnAllListener onAllListener) {
        ThreadManager.heavy().post(new Runnable() { // from class: com.jingdong.common.jdreactFramework.modules.community.JDRNKocCommunityModule.4
            {
                JDRNKocCommunityModule.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                byte[] bArr2 = bArr;
                if (bArr2 == null) {
                    HttpGroup.OnAllListener onAllListener2 = onAllListener;
                    if (onAllListener2 != null) {
                        onAllListener2.onStart();
                        onAllListener.onError(new HttpError());
                        return;
                    }
                    return;
                }
                String str3 = Long.toHexString(CRCUtils.checksumBytes(bArr2)) + "" + bArr.length + CartConstant.KEY_YB_INFO_LINK;
                byte[] bArr3 = null;
                try {
                    bArr3 = Base64.encodeBytes(bArr).getBytes(CharEncoding.US_ASCII);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                SimpleFileUploader.getInstance().upload(SimpleFileUploader.UploadRequest.Builder.newInstance().addHeader("aucode", str2).addHeader("keycode", str3).addHeader("type", "0").addHeader("client", "Android").byteSource(bArr3).url(str).build(), onAllListener);
            }
        });
    }
}
