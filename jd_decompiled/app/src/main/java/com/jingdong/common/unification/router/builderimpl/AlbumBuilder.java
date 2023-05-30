package com.jingdong.common.unification.router.builderimpl;

import com.jingdong.common.unification.album.AlbumConstant;
import com.jingdong.common.unification.album.AlbumParam;
import com.jingdong.common.unification.album.LocalMedia;
import com.jingdong.common.unification.router.builder.RouterBuilder;
import com.jingdong.common.unification.router.builder.RouterEntry;
import java.util.List;

/* loaded from: classes6.dex */
public class AlbumBuilder extends RouterBuilder<AlbumBuilder, AlbumRouterEntry> {

    /* loaded from: classes6.dex */
    public class AlbumRouterEntry extends RouterEntry<AlbumRouterEntry> {
        public AlbumParam albumParam;

        public AlbumRouterEntry() {
        }
    }

    public AlbumBuilder() {
        super("JDAlbumModule", "show");
    }

    public AlbumBuilder albumParam(AlbumParam albumParam) {
        ((AlbumRouterEntry) this.mRouterEntry).albumParam = albumParam;
        return this;
    }

    public AlbumBuilder cameraOrVideoAction(int i2) {
        putInt(AlbumConstant.CAMERA_OR_VIDEO_ACTION, i2);
        return this;
    }

    public AlbumBuilder canSelectMediaCount(int i2) {
        putInt(AlbumConstant.CAN_SELECT_MEDIA_COUNT, i2);
        return this;
    }

    public AlbumBuilder cutShape(boolean z) {
        putBoolean(AlbumConstant.CUT_SHAPE, z);
        return this;
    }

    public AlbumBuilder fromSource(String str) {
        putString("fromSource", str);
        return this;
    }

    public AlbumBuilder loadCameraOrVideo(int i2) {
        putInt(AlbumConstant.LOAD_CAMERA_OR_VIDEO, i2);
        return this;
    }

    public AlbumBuilder needCutPic(boolean z) {
        putBoolean(AlbumConstant.NEED_CUT_PIC, z);
        return this;
    }

    public AlbumBuilder needEditorPic(boolean z) {
        putBoolean("needEditorPic", z);
        return this;
    }

    public AlbumBuilder selectedMedia(List<LocalMedia> list) {
        extraObject(AlbumConstant.SELECT_MEDIAS, list);
        return this;
    }

    public AlbumBuilder setRequestCode(int i2) {
        requestCode(i2);
        return this;
    }

    public AlbumBuilder showAnimatePic(boolean z) {
        putBoolean(AlbumConstant.PIC_BEAUTIFY_ANIMATE_SWITCH, z);
        return this;
    }

    public AlbumBuilder videoEditorAction(int i2) {
        putInt(AlbumConstant.VIDEO_EDITOR_ACTION, i2);
        return this;
    }

    public AlbumBuilder videoMaxTime(int i2) {
        putInt(AlbumConstant.VIDEO_MAX_DURATION, i2);
        return this;
    }

    public AlbumBuilder videoMinTime(int i2) {
        putInt(AlbumConstant.VIDEO_MIN_DURATION, i2);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.unification.router.builder.RouterBuilder
    public AlbumRouterEntry initRouterEntry() {
        return new AlbumRouterEntry();
    }
}
