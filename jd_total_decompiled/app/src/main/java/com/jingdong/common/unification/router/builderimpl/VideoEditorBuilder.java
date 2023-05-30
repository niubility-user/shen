package com.jingdong.common.unification.router.builderimpl;

import com.jingdong.common.unification.filter.FilterTools;
import com.jingdong.common.unification.router.builder.RouterBuilder;
import com.jingdong.common.unification.router.builder.RouterEntry;
import com.jingdong.common.unification.video.VideoConstant;
import com.jingdong.common.unification.video.VideoParam;
import java.util.List;

/* loaded from: classes6.dex */
public class VideoEditorBuilder extends RouterBuilder<VideoEditorBuilder, VideoEditorRouterEntry> {

    /* loaded from: classes6.dex */
    public class VideoEditorRouterEntry extends RouterEntry<VideoEditorRouterEntry> {
        public VideoParam videoParam;

        public VideoEditorRouterEntry() {
        }
    }

    public VideoEditorBuilder() {
        super("JDVideoEditorModule", "show");
    }

    public VideoEditorBuilder cutMaxTime(long j2) {
        putLong(VideoConstant.CUT_MAX_TIME, j2);
        return this;
    }

    public VideoEditorBuilder cutMinTime(long j2) {
        putLong(VideoConstant.CUT_MIN_TIME, j2);
        return this;
    }

    public VideoEditorBuilder editorFunctionControl(int i2) {
        putInt(VideoConstant.EDITOR_FUNCTION_CONTROL, i2);
        return this;
    }

    public VideoEditorBuilder editorVideoPath(String str) {
        putString(VideoConstant.EDITOR_VIDEO_PATH, str);
        return this;
    }

    public VideoEditorBuilder filterTypes(List<FilterTools.FilterType> list) {
        extraObject(VideoConstant.FILTER_TYPES, list);
        return this;
    }

    public VideoEditorBuilder videoParam(VideoParam videoParam) {
        ((VideoEditorRouterEntry) this.mRouterEntry).videoParam = videoParam;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.unification.router.builder.RouterBuilder
    public VideoEditorRouterEntry initRouterEntry() {
        return new VideoEditorRouterEntry();
    }
}
