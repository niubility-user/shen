package com.jingdong.common.jdreactFramework.activities;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.jdreactFramework.views.webview.JDReactVideoPlayer;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\b\u0016\u0018\u00002\u00020\u0001:\u0001'B\u0007\u00a2\u0006\u0004\b&\u0010\u0010J)\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\b\u0010\tJ\u0019\u0010\r\u001a\u00020\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0014\u00a2\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\fH\u0014\u00a2\u0006\u0004\b\u000f\u0010\u0010R\"\u0010\u0012\u001a\u00020\u00118\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R(\u0010\u0019\u001a\b\u0018\u00010\u0018R\u00020\u00008\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR$\u0010 \u001a\u0004\u0018\u00010\u001f8\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%\u00a8\u0006("}, d2 = {"Lcom/jingdong/common/jdreactFramework/activities/FullScreenVideoActivity;", "Lcom/jingdong/common/BaseActivity;", "", "url", "", "width", "height", "Landroid/graphics/Bitmap;", "createVideoThumbnail", "(Ljava/lang/String;II)Landroid/graphics/Bitmap;", "Landroid/os/Bundle;", "savedInstanceState", "", "onCreate", "(Landroid/os/Bundle;)V", "onDestroy", "()V", "", "mfinished", "Z", "getMfinished$app_debug", "()Z", "setMfinished$app_debug", "(Z)V", "Lcom/jingdong/common/jdreactFramework/activities/FullScreenVideoActivity$LoadingAsyncTask;", "mLoadingAsyncTask", "Lcom/jingdong/common/jdreactFramework/activities/FullScreenVideoActivity$LoadingAsyncTask;", "getMLoadingAsyncTask$app_debug", "()Lcom/jingdong/common/jdreactFramework/activities/FullScreenVideoActivity$LoadingAsyncTask;", "setMLoadingAsyncTask$app_debug", "(Lcom/jingdong/common/jdreactFramework/activities/FullScreenVideoActivity$LoadingAsyncTask;)V", "Lcom/jingdong/common/jdreactFramework/views/webview/JDReactVideoPlayer;", "videoPlayer", "Lcom/jingdong/common/jdreactFramework/views/webview/JDReactVideoPlayer;", "getVideoPlayer$app_debug", "()Lcom/jingdong/common/jdreactFramework/views/webview/JDReactVideoPlayer;", "setVideoPlayer$app_debug", "(Lcom/jingdong/common/jdreactFramework/views/webview/JDReactVideoPlayer;)V", "<init>", "LoadingAsyncTask", "app_debug"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes5.dex */
public class FullScreenVideoActivity extends BaseActivity {
    private HashMap _$_findViewCache;
    @Nullable
    private LoadingAsyncTask mLoadingAsyncTask;
    private boolean mfinished;
    @Nullable
    private JDReactVideoPlayer videoPlayer;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\n\b\u0086\u0004\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001B/\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u001c\u001a\u00020\u0003\u0012\u0006\u0010\u0013\u001a\u00020\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u0010\u0016\u001a\u00020\u0015\u00a2\u0006\u0004\b\u001d\u0010\u001eJ%\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0005\"\u00020\u0002H\u0014\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0019\u0010\u000b\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\u0004H\u0014\u00a2\u0006\u0004\b\u000b\u0010\fR\u0016\u0010\u000e\u001a\u00020\r8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0011\u001a\u00020\u00108\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0013\u001a\u00020\u00038\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\"\u0010\u0016\u001a\u00020\u00158\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0016\u0010\u001c\u001a\u00020\u00038\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u0014\u00a8\u0006\u001f"}, d2 = {"Lcom/jingdong/common/jdreactFramework/activities/FullScreenVideoActivity$LoadingAsyncTask;", "Landroid/os/AsyncTask;", "Ljava/lang/Void;", "", "Landroid/graphics/Bitmap;", "", "params", "doInBackground", "([Ljava/lang/Void;)Landroid/graphics/Bitmap;", "result", "", "onPostExecute", "(Landroid/graphics/Bitmap;)V", "Lcom/jingdong/common/jdreactFramework/views/webview/JDReactVideoPlayer;", "videoPlayer", "Lcom/jingdong/common/jdreactFramework/views/webview/JDReactVideoPlayer;", "", "url", "Ljava/lang/String;", "width", "I", "", "show", "Z", "getShow$app_debug", "()Z", "setShow$app_debug", "(Z)V", "height", "<init>", "(Lcom/jingdong/common/jdreactFramework/activities/FullScreenVideoActivity;Lcom/jingdong/common/jdreactFramework/views/webview/JDReactVideoPlayer;IILjava/lang/String;Z)V", "app_debug"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes5.dex */
    public final class LoadingAsyncTask extends AsyncTask<Void, Integer, Bitmap> {
        private final int height;
        private boolean show;
        private final String url;
        private final JDReactVideoPlayer videoPlayer;
        private final int width;

        public LoadingAsyncTask(@NotNull JDReactVideoPlayer jDReactVideoPlayer, int i2, int i3, @NotNull String str, boolean z) {
            this.videoPlayer = jDReactVideoPlayer;
            this.height = i2;
            this.width = i3;
            this.url = str;
            this.show = z;
        }

        /* renamed from: getShow$app_debug  reason: from getter */
        public final boolean getShow() {
            return this.show;
        }

        public final void setShow$app_debug(boolean z) {
            this.show = z;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        @Nullable
        public Bitmap doInBackground(@NotNull Void... params) {
            return FullScreenVideoActivity.this.createVideoThumbnail(this.url, this.width, this.height);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(@Nullable Bitmap result) {
            this.videoPlayer.setCoverBitmap(result);
            if (!this.show || FullScreenVideoActivity.this.getMfinished()) {
                return;
            }
            this.videoPlayer.start();
            this.videoPlayer.getVideoView().start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Bitmap createVideoThumbnail(String url, int width, int height) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            try {
                if (Build.VERSION.SDK_INT >= 14) {
                    mediaMetadataRetriever.setDataSource(url, new HashMap());
                } else {
                    mediaMetadataRetriever.setDataSource(url);
                }
                Bitmap frameAtTime = mediaMetadataRetriever.getFrameAtTime();
                try {
                    mediaMetadataRetriever.release();
                    return frameAtTime;
                } catch (RuntimeException unused) {
                    return frameAtTime;
                }
            } catch (IllegalArgumentException | RuntimeException unused2) {
                mediaMetadataRetriever.release();
                return null;
            } catch (Throwable th) {
                try {
                    mediaMetadataRetriever.release();
                } catch (RuntimeException unused3) {
                }
                throw th;
            }
        } catch (RuntimeException unused4) {
            return null;
        }
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i2) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i2));
        if (view == null) {
            View findViewById = findViewById(i2);
            this._$_findViewCache.put(Integer.valueOf(i2), findViewById);
            return findViewById;
        }
        return view;
    }

    @Nullable
    /* renamed from: getMLoadingAsyncTask$app_debug  reason: from getter */
    public final LoadingAsyncTask getMLoadingAsyncTask() {
        return this.mLoadingAsyncTask;
    }

    /* renamed from: getMfinished$app_debug  reason: from getter */
    public final boolean getMfinished() {
        return this.mfinished;
    }

    @Nullable
    /* renamed from: getVideoPlayer$app_debug  reason: from getter */
    public final JDReactVideoPlayer getVideoPlayer() {
        return this.videoPlayer;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle savedInstanceState) {
        this.statusBarTintEnable = false;
        getWindow().setFlags(1024, 1024);
        super.onCreate(savedInstanceState);
        setUseBasePV(false);
        JDReactVideoPlayer jDReactVideoPlayer = new JDReactVideoPlayer(this);
        this.videoPlayer = jDReactVideoPlayer;
        setContentView(jDReactVideoPlayer);
        String stringExtra = getIntent().getStringExtra("url");
        JDReactVideoPlayer jDReactVideoPlayer2 = this.videoPlayer;
        if (jDReactVideoPlayer2 == null) {
            Intrinsics.throwNpe();
        }
        jDReactVideoPlayer2.setActivity(this);
        JDReactVideoPlayer jDReactVideoPlayer3 = this.videoPlayer;
        if (jDReactVideoPlayer3 == null) {
            Intrinsics.throwNpe();
        }
        jDReactVideoPlayer3.setfullScreenListener(new JDReactVideoPlayer.HideFullScreenListener() { // from class: com.jingdong.common.jdreactFramework.activities.FullScreenVideoActivity$onCreate$1
            @Override // com.jingdong.common.jdreactFramework.views.webview.JDReactVideoPlayer.HideFullScreenListener
            public final void closeClicked() {
                FullScreenVideoActivity.this.finish();
            }
        });
        Toast.makeText(this, "xxx:" + stringExtra, 1);
        JDReactVideoPlayer jDReactVideoPlayer4 = this.videoPlayer;
        if (jDReactVideoPlayer4 == null) {
            Intrinsics.throwNpe();
        }
        jDReactVideoPlayer4.setVideoUri(Uri.parse(stringExtra));
        JDReactVideoPlayer jDReactVideoPlayer5 = this.videoPlayer;
        if (jDReactVideoPlayer5 == null) {
            Intrinsics.throwNpe();
        }
        jDReactVideoPlayer5.hideFullScreenButton();
        JDReactVideoPlayer jDReactVideoPlayer6 = this.videoPlayer;
        if (jDReactVideoPlayer6 == null) {
            Intrinsics.throwNpe();
        }
        jDReactVideoPlayer6.registerSensor(this);
        if (stringExtra != null) {
            JDReactVideoPlayer jDReactVideoPlayer7 = this.videoPlayer;
            if (jDReactVideoPlayer7 == null) {
                Intrinsics.throwNpe();
            }
            JDReactVideoPlayer jDReactVideoPlayer8 = this.videoPlayer;
            if (jDReactVideoPlayer8 == null) {
                Intrinsics.throwNpe();
            }
            int height = jDReactVideoPlayer8.getHeight();
            JDReactVideoPlayer jDReactVideoPlayer9 = this.videoPlayer;
            if (jDReactVideoPlayer9 == null) {
                Intrinsics.throwNpe();
            }
            LoadingAsyncTask loadingAsyncTask = new LoadingAsyncTask(jDReactVideoPlayer7, height, jDReactVideoPlayer9.getWidth(), stringExtra, true);
            this.mLoadingAsyncTask = loadingAsyncTask;
            if (loadingAsyncTask == null) {
                Intrinsics.throwNpe();
            }
            loadingAsyncTask.execute(new Void[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.mfinished = true;
        JDReactVideoPlayer jDReactVideoPlayer = this.videoPlayer;
        if (jDReactVideoPlayer == null) {
            Intrinsics.throwNpe();
        }
        jDReactVideoPlayer.unregisterSensor();
        LoadingAsyncTask loadingAsyncTask = this.mLoadingAsyncTask;
        if (loadingAsyncTask != null) {
            if (loadingAsyncTask == null) {
                Intrinsics.throwNpe();
            }
            if (loadingAsyncTask.getStatus() == AsyncTask.Status.RUNNING) {
                LoadingAsyncTask loadingAsyncTask2 = this.mLoadingAsyncTask;
                if (loadingAsyncTask2 == null) {
                    Intrinsics.throwNpe();
                }
                loadingAsyncTask2.cancel(true);
                this.mLoadingAsyncTask = null;
            }
        }
        JDReactVideoPlayer jDReactVideoPlayer2 = this.videoPlayer;
        if (jDReactVideoPlayer2 != null) {
            if (jDReactVideoPlayer2 == null) {
                Intrinsics.throwNpe();
            }
            if (jDReactVideoPlayer2.getVideoView() != null) {
                JDReactVideoPlayer jDReactVideoPlayer3 = this.videoPlayer;
                if (jDReactVideoPlayer3 == null) {
                    Intrinsics.throwNpe();
                }
                jDReactVideoPlayer3.getVideoView().stop();
            }
        }
    }

    public final void setMLoadingAsyncTask$app_debug(@Nullable LoadingAsyncTask loadingAsyncTask) {
        this.mLoadingAsyncTask = loadingAsyncTask;
    }

    public final void setMfinished$app_debug(boolean z) {
        this.mfinished = z;
    }

    public final void setVideoPlayer$app_debug(@Nullable JDReactVideoPlayer jDReactVideoPlayer) {
        this.videoPlayer = jDReactVideoPlayer;
    }
}
