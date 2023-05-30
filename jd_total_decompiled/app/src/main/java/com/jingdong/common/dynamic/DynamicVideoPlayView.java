package com.jingdong.common.dynamic;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.Keep;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.base.DynamicSdk;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;
import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;
import tv.danmaku.ijk.media.widget.JDPlayerView;
import tv.danmaku.ijk.media.widget.controller.JDControllerOptions;

@Keep
/* loaded from: classes5.dex */
public class DynamicVideoPlayView extends FrameLayout {
    private HashMap<String, String> attributes;
    private Context context;
    private JDControllerOptions.Builder controlOptBuilder;
    private String imgUrl;
    private float lb;
    private float lt;
    private Paint paint;
    private Path path;
    private SimpleDraweeView placeholderView;
    private float rb;
    private float rt;
    private VideoPlayStateChanged videoPlayStateChanged;
    private String videoPlayUrl;
    private JDPlayerView videoPlayView;

    @Keep
    /* loaded from: classes5.dex */
    public interface VideoPlayStateChanged {
        void onCompletion();

        void onError();

        void onInfo(int i2, int i3);

        void onNoPlayUrl();

        void onPrepared();

        void onSeekComplete();
    }

    public DynamicVideoPlayView(Context context) {
        super(context);
        this.paint = new Paint();
        init();
        this.context = context;
    }

    private void dealScaleType(IPlayerControl.PlayerOptions playerOptions, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            playerOptions.setAspectRatio(Integer.parseInt(str));
        } catch (Exception unused) {
        }
    }

    private int handleRadius(String str) {
        try {
            return DPIUtil.dip2px(this.context, Float.parseFloat(str));
        } catch (Exception unused) {
            return 0;
        }
    }

    private void init() {
        this.path = new Path();
        setWillNotDraw(false);
    }

    private void initPlayerController() {
        JDPlayerView jDPlayerView;
        this.controlOptBuilder = new JDControllerOptions.Builder();
        if (getContext() instanceof Activity) {
            this.controlOptBuilder.activity((Activity) getContext());
        }
        HashMap<String, String> hashMap = this.attributes;
        if (hashMap != null) {
            for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                String str = this.attributes.get(entry.getKey());
                String key = entry.getKey();
                key.hashCode();
                char c2 = '\uffff';
                switch (key.hashCode()) {
                    case -1571314776:
                        if (key.equals("placeholderImage")) {
                            c2 = 0;
                            break;
                        }
                        break;
                    case -163977556:
                        if (key.equals("needControlBar")) {
                            c2 = 1;
                            break;
                        }
                        break;
                    case 38990929:
                        if (key.equals("needShowVoiceButton")) {
                            c2 = 2;
                            break;
                        }
                        break;
                    case 73410554:
                        if (key.equals("userInteractionEnabled")) {
                            c2 = 3;
                            break;
                        }
                        break;
                    case 296192188:
                        if (key.equals("needPlayButton")) {
                            c2 = 4;
                            break;
                        }
                        break;
                }
                switch (c2) {
                    case 0:
                        this.imgUrl = str;
                        JDPlayerView jDPlayerView2 = this.videoPlayView;
                        if (jDPlayerView2 != null) {
                            jDPlayerView2.setImgCover(str);
                            break;
                        } else {
                            break;
                        }
                    case 1:
                        this.controlOptBuilder.manualControlVisible(TextUtils.equals(str, "0"));
                        break;
                    case 2:
                        this.controlOptBuilder.enableMuteSwitch(TextUtils.equals(this.attributes.get("needShowVoiceButton"), "1"));
                        break;
                    case 3:
                        if (TextUtils.equals(this.attributes.get("userInteractionEnabled"), "0") && (jDPlayerView = this.videoPlayView) != null) {
                            jDPlayerView.setOnClickListener(null);
                            this.videoPlayView.setClickable(false);
                            this.videoPlayView.setLongClickable(false);
                            break;
                        }
                        break;
                    case 4:
                        JDPlayerView jDPlayerView3 = this.videoPlayView;
                        if (jDPlayerView3 == null) {
                            break;
                        } else {
                            jDPlayerView3.changeStartIconVisible(TextUtils.equals(str, "1") ? 0 : 8);
                            break;
                        }
                }
            }
        }
        this.controlOptBuilder.uiMode(JDControllerOptions.UIMode.UI_ITEM_SMALL);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void initVideoPlayerView() {
        VideoPlayStateChanged videoPlayStateChanged;
        char c2;
        SimpleDraweeView simpleDraweeView = this.placeholderView;
        if (simpleDraweeView != null) {
            simpleDraweeView.setVisibility(8);
        }
        if (this.videoPlayView == null) {
            this.videoPlayView = new JDPlayerView(this.context);
            initPlayerController();
            IPlayerControl.PlayerOptions playerOptions = new IPlayerControl.PlayerOptions(false);
            HashMap<String, String> hashMap = this.attributes;
            if (hashMap != null) {
                for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                    String str = this.attributes.get(entry.getKey());
                    String key = entry.getKey();
                    key.hashCode();
                    switch (key.hashCode()) {
                        case -1877911644:
                            if (key.equals("scaleType")) {
                                c2 = 0;
                                break;
                            }
                            c2 = '\uffff';
                            break;
                        case -1180332746:
                            if (key.equals("isLive")) {
                                c2 = 1;
                                break;
                            }
                            c2 = '\uffff';
                            break;
                        case -425209226:
                            if (key.equals("autoReplay")) {
                                c2 = 2;
                                break;
                            }
                            c2 = '\uffff';
                            break;
                        case 3363353:
                            if (key.equals("mute")) {
                                c2 = 3;
                                break;
                            }
                            c2 = '\uffff';
                            break;
                        case 1151378164:
                            if (key.equals("videoUrl")) {
                                c2 = 4;
                                break;
                            }
                            c2 = '\uffff';
                            break;
                        default:
                            c2 = '\uffff';
                            break;
                    }
                    switch (c2) {
                        case 0:
                            dealScaleType(playerOptions, str);
                            continue;
                        case 2:
                            playerOptions.setLoop(TextUtils.equals(str, "1"));
                            continue;
                        case 3:
                            playerOptions.setVolume("1".equals(str) ? 0.0f : 1.0f);
                            continue;
                        case 4:
                            this.videoPlayUrl = str;
                            break;
                    }
                    playerOptions.setLive("1".equals(str));
                }
            }
            playerOptions.setPlayTypeId("152");
            playerOptions.setUseCache(true);
            playerOptions.setIsRequestAudioFocus(false);
            this.videoPlayView.setPlayerOptions(playerOptions, this.controlOptBuilder.build());
            if (!TextUtils.isEmpty(this.videoPlayUrl)) {
                this.videoPlayView.setVideoPath(this.videoPlayUrl, JDPlayerView.PlayMode.CLICK_PLAY);
            }
            this.videoPlayView.setPlayerStateListener(new IPlayerControl.OnPlayerStateListener() { // from class: com.jingdong.common.dynamic.DynamicVideoPlayView.1
                @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
                public void onCompletion() {
                    if (DynamicVideoPlayView.this.videoPlayView != null) {
                        DynamicVideoPlayView.this.videoPlayView.setImgCover(DynamicVideoPlayView.this.imgUrl);
                        DynamicVideoPlayView.this.videoFinished();
                    }
                }

                @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
                public void onCreatePlayer() {
                }

                @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
                public boolean onError(int i2, int i3) {
                    if (DynamicVideoPlayView.this.videoPlayStateChanged != null) {
                        DynamicVideoPlayView.this.videoPlayStateChanged.onError();
                        return false;
                    }
                    return false;
                }

                @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
                public boolean onInfo(int i2, int i3) {
                    if (DynamicVideoPlayView.this.videoPlayStateChanged != null) {
                        DynamicVideoPlayView.this.videoPlayStateChanged.onInfo(i2, i3);
                        return false;
                    }
                    return false;
                }

                @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
                public void onPrepared(long j2) {
                    if (DynamicVideoPlayView.this.videoPlayStateChanged != null) {
                        DynamicVideoPlayView.this.videoPlayStateChanged.onPrepared();
                    }
                }

                @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
                public void onSeekComplete() {
                    if (DynamicVideoPlayView.this.videoPlayStateChanged != null) {
                        DynamicVideoPlayView.this.videoPlayStateChanged.onSeekComplete();
                    }
                }
            });
            addView(this.videoPlayView);
            if (this.videoPlayView != null) {
                this.videoPlayView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
            }
            if (!TextUtils.isEmpty(this.videoPlayUrl) || (videoPlayStateChanged = this.videoPlayStateChanged) == null) {
                return;
            }
            videoPlayStateChanged.onNoPlayUrl();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void setupViewAttributes() {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        String str;
        char c2;
        HashMap<String, String> hashMap = this.attributes;
        if (hashMap != null) {
            Iterator<Map.Entry<String, String>> it = hashMap.entrySet().iterator();
            i2 = 0;
            i3 = 0;
            i4 = 0;
            i5 = 0;
            i6 = 0;
            str = null;
            while (it.hasNext()) {
                String key = it.next().getKey();
                key.hashCode();
                switch (key.hashCode()) {
                    case -2039200304:
                        if (key.equals(DYConstants.DY_BORDER_RADIUS_B_L)) {
                            c2 = 0;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case -1919362822:
                        if (key.equals(DYConstants.DY_BORDER_RADIUS_T_L)) {
                            c2 = 1;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case -1571314776:
                        if (key.equals("placeholderImage")) {
                            c2 = 2;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case -734233787:
                        if (key.equals(DYConstants.DY_BORDER_RADIUS_T_R)) {
                            c2 = 3;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case -154228433:
                        if (key.equals(DYConstants.DY_BORDER_RADIUS_B_R)) {
                            c2 = 4;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case 1349188574:
                        if (key.equals("borderRadius")) {
                            c2 = 5;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    default:
                        c2 = '\uffff';
                        break;
                }
                switch (c2) {
                    case 0:
                        i4 = handleRadius(this.attributes.get(DYConstants.DY_BORDER_RADIUS_B_L));
                        break;
                    case 1:
                        i2 = handleRadius(this.attributes.get(DYConstants.DY_BORDER_RADIUS_T_L));
                        break;
                    case 2:
                        str = this.attributes.get("placeholderImage");
                        break;
                    case 3:
                        i3 = handleRadius(this.attributes.get(DYConstants.DY_BORDER_RADIUS_T_R));
                        break;
                    case 4:
                        i5 = handleRadius(this.attributes.get(DYConstants.DY_BORDER_RADIUS_B_R));
                        break;
                    case 5:
                        i6 = handleRadius(this.attributes.get("borderRadius"));
                        break;
                }
            }
        } else {
            i2 = 0;
            i3 = 0;
            i4 = 0;
            i5 = 0;
            i6 = 0;
            str = null;
        }
        if (i2 != 0 || i3 != 0 || i4 != 0 || i5 != 0) {
            setCorner(i2, i3, i4, i5);
        } else if (i6 != 0) {
            setCorner(i6, i6, i6, i6);
        }
        if (!TextUtils.isEmpty(str)) {
            if (this.placeholderView == null) {
                SimpleDraweeView simpleDraweeView = new SimpleDraweeView(this.context);
                this.placeholderView = simpleDraweeView;
                simpleDraweeView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                addView(this.placeholderView);
                this.placeholderView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
            }
            DynamicSdk.getEngine().getImageLoader().displayImage(this.placeholderView, str, 0, null);
            this.placeholderView.setVisibility(0);
            return;
        }
        SimpleDraweeView simpleDraweeView2 = this.placeholderView;
        if (simpleDraweeView2 != null) {
            simpleDraweeView2.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void videoFinished() {
        destroyPlayer(null);
        VideoPlayStateChanged videoPlayStateChanged = this.videoPlayStateChanged;
        if (videoPlayStateChanged != null) {
            videoPlayStateChanged.onCompletion();
        }
    }

    public void destroyPlayer(JSONObject jSONObject) {
        JDPlayerView jDPlayerView = this.videoPlayView;
        if (jDPlayerView != null) {
            if (jDPlayerView.isPlaying()) {
                this.videoPlayView.pause();
            }
            this.videoPlayView.release();
            removeView(this.videoPlayView);
            this.videoPlayView = null;
            SimpleDraweeView simpleDraweeView = this.placeholderView;
            if (simpleDraweeView != null) {
                simpleDraweeView.setVisibility(0);
            }
        }
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        if (this.lt == 0.0f && this.rt == 0.0f && this.lb == 0.0f && this.rb == 0.0f) {
            super.draw(canvas);
        } else if (Build.VERSION.SDK_INT > 26) {
            canvas.clipPath(this.path);
            super.draw(canvas);
        } else {
            canvas.saveLayer(0.0f, 0.0f, getWidth(), getHeight(), this.paint, 31);
            canvas.setDrawFilter(new PaintFlagsDrawFilter(0, 3));
            super.draw(canvas);
            this.paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            canvas.drawPath(this.path, this.paint);
            this.paint.setXfermode(null);
            canvas.restore();
        }
    }

    public JDPlayerView getPlayerView() {
        JDPlayerView jDPlayerView = this.videoPlayView;
        if (jDPlayerView != null) {
            return jDPlayerView;
        }
        return null;
    }

    public String getVideoPlayUrl() {
        return this.videoPlayUrl;
    }

    public JDPlayerView getVideoPlayView() {
        return this.videoPlayView;
    }

    public boolean isPlaying(JSONObject jSONObject) {
        JDPlayerView jDPlayerView = this.videoPlayView;
        if (jDPlayerView != null) {
            return jDPlayerView.isPlaying();
        }
        return false;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        this.path.reset();
        Path path = this.path;
        RectF rectF = new RectF(0.0f, 0.0f, getWidth(), getHeight());
        float f2 = this.lt;
        float f3 = this.rt;
        float f4 = this.rb;
        float f5 = this.lb;
        path.addRoundRect(rectF, new float[]{f2, f2, f3, f3, f4, f4, f5, f5}, Path.Direction.CCW);
    }

    public void pauseVideo(JSONObject jSONObject) {
        JDPlayerView jDPlayerView = this.videoPlayView;
        if (jDPlayerView != null && jDPlayerView.isPlaying()) {
            this.videoPlayView.pause();
        }
    }

    public void seekToTime(JSONObject jSONObject) {
        if (jSONObject == null || this.videoPlayView == null) {
            return;
        }
        this.videoPlayView.seekTo(jSONObject.optInt("timeStamp"));
    }

    public void setAttributes(HashMap<String, String> hashMap) {
        this.attributes = hashMap;
        setupViewAttributes();
    }

    public void setCorner(int i2, int i3, int i4, int i5) {
        float f2 = i2;
        this.lt = f2;
        float f3 = i3;
        this.rt = f3;
        float f4 = i4;
        this.lb = f4;
        float f5 = i5;
        this.rb = f5;
        this.path.reset();
        this.path.addRoundRect(new RectF(0.0f, 0.0f, getWidth(), getHeight()), new float[]{f2, f2, f3, f3, f4, f4, f5, f5}, Path.Direction.CCW);
        invalidate();
    }

    public void setVideoPlayStateChanged(VideoPlayStateChanged videoPlayStateChanged) {
        this.videoPlayStateChanged = videoPlayStateChanged;
    }

    public void startPlay(JSONObject jSONObject) {
        if (this.videoPlayView == null) {
            initVideoPlayerView();
        }
        JDPlayerView jDPlayerView = this.videoPlayView;
        if (jDPlayerView == null || jDPlayerView.isPlaying()) {
            return;
        }
        this.videoPlayView.doClickPlay();
    }

    public void stopVideo(JSONObject jSONObject) {
        JDPlayerView jDPlayerView = this.videoPlayView;
        if (jDPlayerView == null) {
            return;
        }
        jDPlayerView.stop();
    }

    public DynamicVideoPlayView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.paint = new Paint();
        init();
        this.context = context;
    }
}
