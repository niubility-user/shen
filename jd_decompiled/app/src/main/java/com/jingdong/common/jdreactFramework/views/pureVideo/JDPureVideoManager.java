package com.jingdong.common.jdreactFramework.views.pureVideo;

import android.text.TextUtils;
import android.widget.ImageView;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.jdreactFramework.views.pureVideo.PureVideoPlayView;
import com.jingdong.common.jdreactFramework.views.pureVideo.RCTVideoPlayView;
import com.jingdong.manto.sdk.api.IAudioPlayer;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class JDPureVideoManager extends SimpleViewManager<PureVideoPlayView> {
    public static final String REACT_CLASS = "JDRCTPureVideo";
    public static final String TAG = "JDPureVideoManager";

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    @interface ReceiveCommand {
        public static final int ChangeVoice = 1;
        public static final int Destroy = 4;
        public static final int Pause = 2;
        public static final int Play = 3;
        public static final int Seek = 5;
    }

    /* loaded from: classes5.dex */
    interface SourceKey {
        public static final String AUTO_PLAY = "autoPlay";
        public static final String BOTTOM_LEFT_RADIUS = "bottomLeftRadius";
        public static final String BOTTOM_RIGHT_RADIUS = "bottomRightRadius";
        public static final String CLICKABLE = "clickable";
        public static final String COVER_SCALE_TYPE = "coverScaleType";
        public static final String COVER_URL = "coverUrl";
        public static final String LOOP = "loop";
        public static final String MUTE = "mute";
        public static final String RADIUS = "radius";
        public static final String SCALE_TYPE = "scaleType";
        public static final String TOP_LEFT_RADIUS = "topLeftRadius";
        public static final String TOP_RIGHT_RADIUS = "topRightRadius";
        public static final String VIDEO_URL = "url";
    }

    private ImageView.ScaleType coverImage(String str) {
        if (TextUtils.isEmpty(str)) {
            return ImageView.ScaleType.MATRIX;
        }
        String lowerCase = str.toLowerCase();
        char c2 = '\uffff';
        switch (lowerCase.hashCode()) {
            case -2021672893:
                if (lowerCase.equals("fit_center")) {
                    c2 = 2;
                    break;
                }
                break;
            case -1364013995:
                if (lowerCase.equals(DYConstants.DY_CENTER)) {
                    c2 = 4;
                    break;
                }
                break;
            case -1274273297:
                if (lowerCase.equals("fit_xy")) {
                    c2 = 0;
                    break;
                }
                break;
            case -1081239615:
                if (lowerCase.equals("matrix")) {
                    c2 = 7;
                    break;
                }
                break;
            case -847785043:
                if (lowerCase.equals("fit_end")) {
                    c2 = 3;
                    break;
                }
                break;
            case 225732390:
                if (lowerCase.equals("center_inside")) {
                    c2 = 6;
                    break;
                }
                break;
            case 1335468724:
                if (lowerCase.equals("fit_start")) {
                    c2 = 1;
                    break;
                }
                break;
            case 1671566394:
                if (lowerCase.equals("center_crop")) {
                    c2 = 5;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                return ImageView.ScaleType.FIT_XY;
            case 1:
                return ImageView.ScaleType.FIT_START;
            case 2:
                return ImageView.ScaleType.FIT_CENTER;
            case 3:
                return ImageView.ScaleType.FIT_END;
            case 4:
                return ImageView.ScaleType.CENTER;
            case 5:
                return ImageView.ScaleType.CENTER_CROP;
            case 6:
                return ImageView.ScaleType.CENTER_INSIDE;
            default:
                return ImageView.ScaleType.MATRIX;
        }
    }

    private String coverVideo(String str) {
        if (TextUtils.isEmpty(str)) {
            return "fillParent";
        }
        String lowerCase = str.toLowerCase();
        char c2 = '\uffff';
        switch (lowerCase.hashCode()) {
            case -668181265:
                if (lowerCase.equals("wrapcontent")) {
                    c2 = 1;
                    break;
                }
                break;
            case 51821:
                if (lowerCase.equals("4:3")) {
                    c2 = 4;
                    break;
                }
                break;
            case 1513508:
                if (lowerCase.equals("16:9")) {
                    c2 = 3;
                    break;
                }
                break;
            case 97442514:
                if (lowerCase.equals("fitxy")) {
                    c2 = 2;
                    break;
                }
                break;
            case 236949485:
                if (lowerCase.equals("fillparent")) {
                    c2 = 5;
                    break;
                }
                break;
            case 1805485051:
                if (lowerCase.equals("fitparent")) {
                    c2 = 0;
                    break;
                }
                break;
        }
        return c2 != 0 ? c2 != 1 ? c2 != 2 ? c2 != 3 ? c2 != 4 ? "fillParent" : "4:3" : "16:9" : "fitXY" : "wrapContent" : "fitParent";
    }

    @Override // com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map<String, Integer> getCommandsMap() {
        MapBuilder.Builder builder = MapBuilder.builder();
        builder.put("ChangeVoice", 1);
        builder.put(IAudioPlayer.AUDIO_STATE_PAUSE, 2);
        builder.put(IAudioPlayer.AUDIO_STATE_PLAY, 3);
        builder.put("Destroy", 4);
        builder.put("Seek", 5);
        return builder.build();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    @androidx.annotation.Nullable
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        MapBuilder.Builder builder = MapBuilder.builder();
        for (PureVideoPlayView.VideoEvent videoEvent : PureVideoPlayView.VideoEvent.values()) {
            builder.put(videoEvent.toString(), MapBuilder.of("registrationName", videoEvent.toString()));
        }
        return builder.build();
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    @Nonnull
    public String getName() {
        return REACT_CLASS;
    }

    @ReactProp(name = "source")
    public void setSource(PureVideoPlayView pureVideoPlayView, @androidx.annotation.Nullable ReadableMap readableMap) {
        RCTVideoPlayView videoView = pureVideoPlayView.getVideoView();
        videoView.hideControlPanl(true).setHideRetryBt(true).setNeedJudgeNetOnStart(false).setCoverImageScaleType(coverImage(readableMap.hasKey(SourceKey.COVER_SCALE_TYPE) ? readableMap.getString(SourceKey.COVER_SCALE_TYPE) : null)).setCoverUrl(readableMap.hasKey(SourceKey.COVER_URL) ? readableMap.getString(SourceKey.COVER_URL) : null).setScaleType(coverVideo(readableMap.hasKey("scaleType") ? readableMap.getString("scaleType") : null)).setAutoPlay(false);
        pureVideoPlayView.setVoiceMute(readableMap.hasKey("mute") && readableMap.getBoolean("mute"));
        videoView.setLoop(readableMap.hasKey(SourceKey.LOOP) && readableMap.getBoolean(SourceKey.LOOP));
        pureVideoPlayView.setClickable(readableMap.hasKey(SourceKey.CLICKABLE) && readableMap.getBoolean(SourceKey.CLICKABLE));
        String string = readableMap.hasKey("url") ? readableMap.getString("url") : null;
        String str = "";
        String string2 = readableMap.hasKey("autoPlay") ? readableMap.getString("autoPlay") : "";
        RCTVideoPlayView.AutoPlayEnum autoPlayEnum = RCTVideoPlayView.AutoPlayEnum.NEVER;
        try {
            if (!TextUtils.isEmpty(string2)) {
                str = string2.toUpperCase();
            }
            autoPlayEnum = RCTVideoPlayView.AutoPlayEnum.valueOf(str);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        pureVideoPlayView.setVideoPath(string, autoPlayEnum);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    @Nonnull
    public PureVideoPlayView createViewInstance(@Nonnull ThemedReactContext themedReactContext) {
        return new PureVideoPlayView(themedReactContext);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void onDropViewInstance(PureVideoPlayView pureVideoPlayView) {
        super.onDropViewInstance((JDPureVideoManager) pureVideoPlayView);
        if (pureVideoPlayView != null) {
            pureVideoPlayView.destroy();
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(PureVideoPlayView pureVideoPlayView, int i2, @androidx.annotation.Nullable ReadableArray readableArray) {
        if (pureVideoPlayView == null) {
            return;
        }
        if (i2 == 1) {
            if (readableArray != null) {
                pureVideoPlayView.setVoiceMute(readableArray.getInt(0) == 0);
            }
        } else if (i2 == 2) {
            pureVideoPlayView.pause();
        } else if (i2 == 3) {
            pureVideoPlayView.play();
        } else if (i2 == 4) {
            pureVideoPlayView.destroy();
        } else if (i2 == 5 && readableArray != null) {
            double d = readableArray.getDouble(0);
            double d2 = readableArray.getDouble(1);
            double duration = pureVideoPlayView.getDuration();
            Double.isNaN(duration);
            pureVideoPlayView.seekTo((int) Math.round((duration * d) / d2));
        }
    }
}
