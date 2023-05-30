package com.jingdong.sdk.platform.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import com.airbnb.lottie.LottieAnimationView;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener;
import com.jingdong.app.util.image.placeholder.JDPlaceholderDrawable;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.utils.FileUtils;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.RxUtil;
import com.jingdong.jdsdk.network.toolbox.FileGuider;
import com.jingdong.jdsdk.network.toolbox.FileService;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.Md5Encrypt;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.business.personal.R;
import com.jingdong.sdk.platform.business.personal.entity.CommonMultiIconEntity;
import java.io.File;
import org.json.JSONObject;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/* loaded from: classes10.dex */
public class PersonalLottieView extends LottieAnimationView implements DownLottieZipFileCallback {
    private static final String LOTTIE_FILENAME_KEY = "PersonalLottie";
    private static final Handler MAIN_HANDLER = new Handler(Looper.getMainLooper());
    private static final String TAG = "PersonalLottieView";
    private static Subscription subscription;
    private DownLottieZipFileCallback downLottieZipFileCallback;
    private boolean needPlaceholder;
    private String safeImage;

    public PersonalLottieView(Context context) {
        this(context, null);
    }

    public void clearPlaceholder() {
        if (Build.VERSION.SDK_INT >= 16) {
            setBackground(null);
        } else {
            setBackgroundDrawable(null);
        }
    }

    private String getDownloadedLottieLocalFilePath(String str) {
        String lottieLocalFilePathName;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            lottieLocalFilePathName = getLottieLocalFilePathName(Md5Encrypt.md5(str));
        } catch (Exception unused) {
        }
        if (TextUtils.isEmpty(lottieLocalFilePathName)) {
            return null;
        }
        if (FileUtils.fileIsExists(lottieLocalFilePathName)) {
            return lottieLocalFilePathName;
        }
        return null;
    }

    public String getLottieLocalFilePathName(String str) {
        FileService.Directory directory = FileService.getDirectory(4);
        if (directory == null) {
            return null;
        }
        return String.format("%s/%s_%s", directory.getPath(), LOTTIE_FILENAME_KEY, str);
    }

    public void handleLoadSafeImage() {
        if (TextUtils.isEmpty(this.safeImage)) {
            setPlaceholder(true);
        } else {
            JDImageUtils.loadImage(this.safeImage, new JDSimpleImageLoadingListener() { // from class: com.jingdong.sdk.platform.widget.PersonalLottieView.5
                {
                    PersonalLottieView.this = this;
                }

                @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingCancelled(String str, View view) {
                    onLoadingFailed(str, view, null);
                }

                @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                    PersonalLottieView.this.clearPlaceholder();
                    PersonalLottieView.this.setImageBitmap(bitmap);
                }

                @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
                    PersonalLottieView.this.setPlaceholder(true);
                }
            });
        }
    }

    public void loadSafeImage() {
        if (this.needPlaceholder) {
            MAIN_HANDLER.post(new Runnable() { // from class: com.jingdong.sdk.platform.widget.PersonalLottieView.4
                {
                    PersonalLottieView.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    PersonalLottieView.this.handleLoadSafeImage();
                }
            });
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(15:8|9|10|12|13|14|15|16|(6:(2:18|(1:20)(0))|23|24|25|26|(1:28)(3:29|30|31))(0)|22|23|24|25|26|(0)(0)) */
    /* JADX WARN: Removed duplicated region for block: B:134:0x004f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x004e A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.json.JSONObject readZipFile(java.lang.String r11) {
        /*
            r10 = this;
            r0 = 0
            java.lang.String r11 = com.jingdong.jdsdk.utils.Md5Encrypt.md5(r11)     // Catch: java.lang.Throwable -> L84
            java.lang.String r11 = r10.getLottieLocalFilePathName(r11)     // Catch: java.lang.Throwable -> L84
            boolean r1 = android.text.TextUtils.isEmpty(r11)
            if (r1 == 0) goto L10
            return r0
        L10:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L65 java.lang.Exception -> L76
            r2.<init>(r11)     // Catch: java.lang.Throwable -> L65 java.lang.Exception -> L76
            java.util.zip.ZipInputStream r11 = new java.util.zip.ZipInputStream     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L63
            r11.<init>(r2)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L63
            r3 = 69
            byte[] r4 = new byte[r3]     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5c
            java.util.zip.ZipEntry r5 = r11.getNextEntry()     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5c
            if (r5 == 0) goto L40
        L29:
            r5 = 0
            int r6 = r11.read(r4, r5, r3)     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5c
            r7 = -1
            if (r6 == r7) goto L40
            java.lang.String r7 = new java.lang.String     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5c
            java.lang.String r8 = "UTF-8"
            java.nio.charset.Charset r8 = java.nio.charset.Charset.forName(r8)     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5c
            r7.<init>(r4, r5, r6, r8)     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5c
            r1.append(r7)     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5c
            goto L29
        L40:
            r11.close()     // Catch: java.io.IOException -> L43
        L43:
            r2.close()     // Catch: java.io.IOException -> L47
            goto L48
        L47:
        L48:
            int r11 = r1.length()
            if (r11 != 0) goto L4f
            return r0
        L4f:
            org.json.JSONObject r11 = new org.json.JSONObject     // Catch: org.json.JSONException -> L59
            java.lang.String r1 = r1.toString()     // Catch: org.json.JSONException -> L59
            r11.<init>(r1)     // Catch: org.json.JSONException -> L59
            return r11
        L59:
            return r0
        L5a:
            r0 = move-exception
            goto L69
        L5c:
            goto L78
        L5e:
            r11 = move-exception
            r9 = r0
            r0 = r11
            r11 = r9
            goto L69
        L63:
            r11 = r0
            goto L78
        L65:
            r11 = move-exception
            r2 = r0
            r0 = r11
            r11 = r2
        L69:
            if (r11 == 0) goto L70
            r11.close()     // Catch: java.io.IOException -> L6f
            goto L70
        L6f:
        L70:
            if (r2 == 0) goto L75
            r2.close()     // Catch: java.io.IOException -> L75
        L75:
            throw r0
        L76:
            r11 = r0
            r2 = r11
        L78:
            if (r11 == 0) goto L7f
            r11.close()     // Catch: java.io.IOException -> L7e
            goto L7f
        L7e:
        L7f:
            if (r2 == 0) goto L84
            r2.close()
        L84:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.sdk.platform.widget.PersonalLottieView.readZipFile(java.lang.String):org.json.JSONObject");
    }

    public void setPlaceholder(boolean z) {
        if (this.needPlaceholder) {
            JDPlaceholderDrawable jDPlaceholderDrawable = z ? new JDPlaceholderDrawable(18) : null;
            if (Build.VERSION.SDK_INT >= 16) {
                setBackground(jDPlaceholderDrawable);
            } else {
                setBackgroundDrawable(jDPlaceholderDrawable);
            }
        }
    }

    public void display(CommonMultiIconEntity.IconElement iconElement) {
        if (iconElement == null) {
            loadSafeImage();
            return;
        }
        this.safeImage = iconElement.safeImage;
        if (TextUtils.isEmpty(iconElement.lottieContent)) {
            loadSafeImage();
            return;
        }
        loop(iconElement.playTimes != 1);
        String str = iconElement.lottieContent;
        if (lottieLocalFileExists(str)) {
            play(iconElement.lottieContent);
        } else {
            setDownLottieZipFileCallback(this);
            downLottieZipFile(str);
        }
        if (OKLog.D) {
            OKLog.d(TAG, String.format("localFilePath exists: %s", Boolean.valueOf(lottieLocalFileExists(str))));
        }
    }

    public void downLottieZipFile(final String str) {
        final String md5 = Md5Encrypt.md5(str);
        FileGuider fileGuider = new FileGuider();
        fileGuider.setSpace(1);
        fileGuider.setChildDirName("lottie");
        fileGuider.setImmutable(false);
        fileGuider.setFileName(md5);
        fileGuider.setMode(1);
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUrl(str);
        httpSetting.setSavePath(fileGuider);
        httpSetting.setLocalFileCache(true);
        httpSetting.setOnTouchEvent(true);
        httpSetting.setEffect(0);
        httpSetting.setType(500);
        httpSetting.setIsExclusiveTask(true);
        httpSetting.setListener(new HttpGroup.OnCommonListener() { // from class: com.jingdong.sdk.platform.widget.PersonalLottieView.1
            {
                PersonalLottieView.this = this;
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                if (httpResponse == null || httpResponse.getSaveFile() == null) {
                    PersonalLottieView.this.loadSafeImage();
                    if (PersonalLottieView.this.downLottieZipFileCallback != null) {
                        PersonalLottieView.this.downLottieZipFileCallback.onDownFailed(new IllegalArgumentException("downLottieZipFile get error httpResponse or httpResponse.getSaveFile() is null!"));
                        return;
                    }
                    return;
                }
                File saveFile = httpResponse.getSaveFile();
                String lottieLocalFilePathName = PersonalLottieView.this.getLottieLocalFilePathName(md5);
                if (TextUtils.isEmpty(lottieLocalFilePathName)) {
                    PersonalLottieView.this.loadSafeImage();
                    if (PersonalLottieView.this.downLottieZipFileCallback != null) {
                        PersonalLottieView.this.downLottieZipFileCallback.onDownFailed(new IllegalArgumentException("downLottieZipFile get error strLottieFilePathName is null!"));
                        return;
                    }
                    return;
                }
                FileUtils.saveToFile(lottieLocalFilePathName, saveFile.getAbsolutePath());
                if (PersonalLottieView.this.downLottieZipFileCallback != null) {
                    PersonalLottieView.this.downLottieZipFileCallback.onDownSuccess(str);
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                PersonalLottieView.this.loadSafeImage();
                if (OKLog.D) {
                    OKLog.d(PersonalLottieView.TAG, "httpSetting onError exception: " + httpError.getMessage());
                }
                if (PersonalLottieView.this.downLottieZipFileCallback != null) {
                    PersonalLottieView.this.downLottieZipFileCallback.onDownFailed(httpError);
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
                if (OKLog.D) {
                    OKLog.d(PersonalLottieView.TAG, "httpSetting onReady exception: " + httpSettingParams.toString());
                }
            }
        });
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        try {
            super.draw(canvas);
        } catch (Exception e2) {
            if (OKLog.D) {
                OKLog.d(TAG, "draw exception: " + e2.getMessage());
            }
        }
    }

    public boolean lottieLocalFileExists(String str) {
        return !TextUtils.isEmpty(getDownloadedLottieLocalFilePath(str));
    }

    @Override // com.jingdong.sdk.platform.widget.DownLottieZipFileCallback
    public void onDownFailed(Throwable th) {
        if (OKLog.D) {
            OKLog.d(TAG, "onDownFailed: " + th.getMessage());
        }
    }

    @Override // com.jingdong.sdk.platform.widget.DownLottieZipFileCallback
    public void onDownSuccess(String str) {
        play(str);
        if (OKLog.D) {
            OKLog.d(TAG, "onDownSuccess");
        }
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        setPlaceholder(true);
    }

    public void play(final String str) {
        subscription = Observable.create(new Observable.OnSubscribe<JSONObject>() { // from class: com.jingdong.sdk.platform.widget.PersonalLottieView.3
            {
                PersonalLottieView.this = this;
            }

            @Override // rx.functions.Action1
            public void call(Subscriber<? super JSONObject> subscriber) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                if (OKLog.D) {
                    OKLog.d(PersonalLottieView.TAG, String.format("play current thread:%s ", Thread.currentThread()));
                }
                JSONObject readZipFile = PersonalLottieView.this.readZipFile(str);
                if (readZipFile != null) {
                    subscriber.onNext(readZipFile);
                } else {
                    subscriber.onError(new Throwable("Lottie json file parse failed"));
                }
                subscriber.onCompleted();
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe((Subscriber) new Subscriber<JSONObject>() { // from class: com.jingdong.sdk.platform.widget.PersonalLottieView.2
            {
                PersonalLottieView.this = this;
            }

            @Override // rx.Observer
            public void onCompleted() {
                PersonalLottieView.this.playAnimation();
                if (OKLog.D) {
                    OKLog.d(PersonalLottieView.TAG, "play onCompleted");
                }
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                PersonalLottieView.this.loadSafeImage();
                if (OKLog.D) {
                    OKLog.d(PersonalLottieView.TAG, String.format("play onError:%s ", th));
                }
            }

            @Override // rx.Observer
            public void onNext(JSONObject jSONObject) {
                PersonalLottieView.this.setAnimationFromJson(jSONObject.toString(), str);
                PersonalLottieView.this.setPlaceholder(false);
                if (OKLog.D) {
                    OKLog.d(PersonalLottieView.TAG, String.format("play onNext current thread:%s ", Thread.currentThread()));
                }
            }
        });
    }

    public void release() {
        RxUtil.unSubscribeSafely(subscription);
        if (OKLog.D) {
            OKLog.d(TAG, "release");
        }
    }

    public void setDownLottieZipFileCallback(DownLottieZipFileCallback downLottieZipFileCallback) {
        this.downLottieZipFileCallback = downLottieZipFileCallback;
    }

    public void simplePlay(String str) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            loadSafeImage();
            return;
        }
        try {
            setAnimationFromJson(readZipFile(str).toString(), str);
            setPlaceholder(false);
            playAnimation();
        } catch (Throwable unused) {
            loadSafeImage();
        }
    }

    public PersonalLottieView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PersonalLottieView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.needPlaceholder = true;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PersonalLottieView);
            this.needPlaceholder = obtainStyledAttributes.getBoolean(R.styleable.PersonalLottieView_needPlaceholder, true);
            obtainStyledAttributes.recycle();
        }
    }
}
