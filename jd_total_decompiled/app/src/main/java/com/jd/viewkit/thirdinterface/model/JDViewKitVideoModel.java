package com.jd.viewkit.thirdinterface.model;

import com.jd.viewkit.tool.NumberTool;
import java.util.Map;

/* loaded from: classes18.dex */
public class JDViewKitVideoModel {
    public static String durationKey = "duration";
    public static String imageUrlKey = "imageUrl";
    public static String playUrlKey = "playUrl";
    public static String videoIdKey = "videoId";
    public String defaultVoiceType;
    private long duration;
    private String imageUrl;
    public String isCircularPlay;
    private String playUrl;
    public String showControl;
    private String videoId;

    public JDViewKitVideoModel(Map<String, Object> map) {
        if (map == null || !(map instanceof Map)) {
            return;
        }
        setPlayUrl(map.get(playUrlKey) == null ? "" : map.get(playUrlKey).toString());
        setImageUrl(map.get(imageUrlKey) == null ? "" : map.get(imageUrlKey).toString());
        setVideoId(map.get(videoIdKey) != null ? map.get(videoIdKey).toString() : "");
        Object obj = map.get(durationKey);
        if (obj != null) {
            setDuration(NumberTool.valueOfLong(obj.toString()).longValue());
        }
    }

    public String getDefaultVoiceType() {
        return this.defaultVoiceType;
    }

    public long getDuration() {
        return this.duration;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public String getIsCircularPlay() {
        return this.isCircularPlay;
    }

    public String getPlayUrl() {
        return this.playUrl;
    }

    public String getShowControl() {
        return this.showControl;
    }

    public String getVideoId() {
        return this.videoId;
    }

    public void setDefaultVoiceType(String str) {
        this.defaultVoiceType = str;
    }

    public void setDuration(long j2) {
        this.duration = j2;
    }

    public void setImageUrl(String str) {
        this.imageUrl = str;
    }

    public void setIsCircularPlay(String str) {
        this.isCircularPlay = str;
    }

    public void setPlayUrl(String str) {
        this.playUrl = str;
    }

    public void setShowControl(String str) {
        this.showControl = str;
    }

    public void setVideoId(String str) {
        this.videoId = str;
    }
}
