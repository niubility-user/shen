package com.jingdong.common.unification.router.builderimpl;

import com.jingdong.common.unification.filter.FilterTools;
import com.jingdong.common.unification.router.builder.RouterBuilder;
import com.jingdong.common.unification.router.builder.RouterEntry;
import com.jingdong.common.unification.video.VideoConstant;
import com.jingdong.common.unification.video.VideoParam;
import java.util.List;

/* loaded from: classes6.dex */
public class VideoRecorderBuilder extends RouterBuilder<VideoRecorderBuilder, VideoRecorderRouterEntry> {

    /* loaded from: classes6.dex */
    public class VideoRecorderRouterEntry extends RouterEntry<VideoRecorderRouterEntry> {
        public VideoParam videoParam;

        public VideoRecorderRouterEntry() {
        }
    }

    public VideoRecorderBuilder() {
        super("JDVideoRecorderModule", "show");
    }

    public VideoRecorderBuilder cutMaxTime(int i2) {
        putInt(VideoConstant.CUT_MAX_TIME, i2);
        return this;
    }

    public VideoRecorderBuilder cutMinTime(int i2) {
        putInt(VideoConstant.CUT_MIN_TIME, i2);
        return this;
    }

    public VideoRecorderBuilder editorFunctionControl(int i2) {
        putInt(VideoConstant.EDITOR_FUNCTION_CONTROL, i2);
        return this;
    }

    public VideoRecorderBuilder editorVideoPath(String str) {
        putString(VideoConstant.EDITOR_VIDEO_PATH, str);
        return this;
    }

    public VideoRecorderBuilder filterTypes(List<FilterTools.FilterType> list) {
        extraObject(VideoConstant.FILTER_TYPES, list);
        return this;
    }

    public VideoRecorderBuilder isSquarePhoto(boolean z) {
        putBoolean(VideoConstant.IS_SQUARE_PHOTO, z);
        return this;
    }

    public VideoRecorderBuilder needEditor(boolean z) {
        putBoolean(VideoConstant.NEED_EDITOR, z);
        return this;
    }

    public VideoRecorderBuilder needEditorPic(boolean z) {
        putBoolean("needEditorPic", z);
        return this;
    }

    public VideoRecorderBuilder picFilterTypes(List<FilterTools.FilterType> list) {
        extraObject("picFilterTypes", list);
        return this;
    }

    public VideoRecorderBuilder recordCurrentState(int i2) {
        putInt(VideoConstant.RECORD_CURRENT_STASTE, i2);
        return this;
    }

    public VideoRecorderBuilder recordFunctionControl(int i2) {
        putInt(VideoConstant.RECORD_FUNCTION_CONTROL, i2);
        return this;
    }

    public VideoRecorderBuilder recordMaxTime(int i2) {
        putInt(VideoConstant.RECORD_MAX_TIME, i2);
        return this;
    }

    public VideoRecorderBuilder recordMinTime(int i2) {
        putInt(VideoConstant.RECORD_MIN_TIME, i2);
        return this;
    }

    public VideoRecorderBuilder setRequestCode(int i2) {
        requestCode(i2);
        return this;
    }

    public VideoRecorderBuilder squarePhotoWidth(boolean z) {
        putBoolean(VideoConstant.SQUARE_PHOTO_WIDTH, z);
        return this;
    }

    public VideoRecorderBuilder videoParam(VideoParam videoParam) {
        ((VideoRecorderRouterEntry) this.mRouterEntry).videoParam = videoParam;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.unification.router.builder.RouterBuilder
    public VideoRecorderRouterEntry initRouterEntry() {
        return new VideoRecorderRouterEntry();
    }
}
