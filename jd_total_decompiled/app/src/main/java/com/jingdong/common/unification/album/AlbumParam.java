package com.jingdong.common.unification.album;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes6.dex */
public class AlbumParam implements Parcelable {
    public static final Parcelable.Creator<AlbumParam> CREATOR = new Parcelable.Creator<AlbumParam>() { // from class: com.jingdong.common.unification.album.AlbumParam.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AlbumParam createFromParcel(Parcel parcel) {
            return new AlbumParam(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AlbumParam[] newArray(int i2) {
            return new AlbumParam[i2];
        }
    };
    public static final String EXTRA_EXPLAIN = "explain";
    public static final String EXTRA_IMAGE_URL = "imageUrl";
    public static final String EXTRA_PIC_GUIDE_LIST = "picGuideList";
    public int cameraOrVideoAction;
    public int canSelectMediaCount;
    public String cate3Id;
    public int cutShape;
    public String extra;
    public boolean isUsePreviewPage;
    public boolean isUseSystemAlbum;
    public int loadCameraOrVideo;
    public boolean needCutPic;
    public boolean needEditorPic;
    public boolean needPicGuide;
    public boolean openFollowTakeUI;
    public boolean savePhotoToAlbum;
    public boolean saveVideoToAlbum;
    public List<LocalMedia> selectedMedia;
    public boolean showAnimatePic;
    public boolean showFollowTake;
    public boolean showMusic;
    public boolean showProps;
    public boolean showVideoCover;
    public String source;
    public int videoEditorAction;
    public String videoMaxTime;
    public String videoMinTime;

    public AlbumParam() {
        this.cameraOrVideoAction = 0;
        this.loadCameraOrVideo = 0;
        this.canSelectMediaCount = 9;
        this.videoMinTime = "3";
        this.videoMaxTime = "10";
        this.savePhotoToAlbum = true;
        this.saveVideoToAlbum = true;
        this.showFollowTake = true;
        this.showMusic = true;
        this.showProps = true;
        this.showVideoCover = true;
        this.isUseSystemAlbum = false;
        this.isUsePreviewPage = true;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.extra);
        parcel.writeString(this.source);
        parcel.writeInt(this.cameraOrVideoAction);
        parcel.writeInt(this.loadCameraOrVideo);
        parcel.writeInt(this.canSelectMediaCount);
        parcel.writeTypedList(this.selectedMedia);
        parcel.writeInt(this.videoEditorAction);
        parcel.writeString(this.videoMinTime);
        parcel.writeString(this.videoMaxTime);
        parcel.writeByte(this.needEditorPic ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.showAnimatePic ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.needCutPic ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.cutShape);
        parcel.writeByte(this.needPicGuide ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.savePhotoToAlbum ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.saveVideoToAlbum ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.showFollowTake ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.openFollowTakeUI ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.showMusic ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.showProps ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.showVideoCover ? (byte) 1 : (byte) 0);
        parcel.writeString(this.cate3Id);
        parcel.writeByte(this.isUseSystemAlbum ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isUsePreviewPage ? (byte) 1 : (byte) 0);
    }

    protected AlbumParam(Parcel parcel) {
        this.cameraOrVideoAction = 0;
        this.loadCameraOrVideo = 0;
        this.canSelectMediaCount = 9;
        this.videoMinTime = "3";
        this.videoMaxTime = "10";
        this.savePhotoToAlbum = true;
        this.saveVideoToAlbum = true;
        this.showFollowTake = true;
        this.showMusic = true;
        this.showProps = true;
        this.showVideoCover = true;
        this.isUseSystemAlbum = false;
        this.isUsePreviewPage = true;
        this.extra = parcel.readString();
        this.source = parcel.readString();
        this.cameraOrVideoAction = parcel.readInt();
        this.loadCameraOrVideo = parcel.readInt();
        this.canSelectMediaCount = parcel.readInt();
        this.selectedMedia = parcel.createTypedArrayList(LocalMedia.CREATOR);
        this.videoEditorAction = parcel.readInt();
        this.videoMinTime = parcel.readString();
        this.videoMaxTime = parcel.readString();
        this.needEditorPic = parcel.readByte() != 0;
        this.showAnimatePic = parcel.readByte() != 0;
        this.needCutPic = parcel.readByte() != 0;
        this.cutShape = parcel.readInt();
        this.needPicGuide = parcel.readByte() != 0;
        this.savePhotoToAlbum = parcel.readByte() != 0;
        this.saveVideoToAlbum = parcel.readByte() != 0;
        this.showFollowTake = parcel.readByte() != 0;
        this.openFollowTakeUI = parcel.readByte() != 0;
        this.showMusic = parcel.readByte() != 0;
        this.showProps = parcel.readByte() != 0;
        this.showVideoCover = parcel.readByte() != 0;
        this.cate3Id = parcel.readString();
        this.isUseSystemAlbum = parcel.readByte() != 0;
        this.isUsePreviewPage = parcel.readByte() != 0;
    }
}
