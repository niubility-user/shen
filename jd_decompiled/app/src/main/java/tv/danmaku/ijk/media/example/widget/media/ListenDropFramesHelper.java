package tv.danmaku.ijk.media.example.widget.media;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/* loaded from: classes11.dex */
public class ListenDropFramesHelper {
    private static final int FFP_PROP_INT64_DROP_FRAMES_AUDIO = 21001;
    private static final int FFP_PROP_INT64_DROP_FRAMES_VIDEO = 21002;
    public static final int MEDIA_INFO_DROP_FRAMES = 10303;

    /* loaded from: classes11.dex */
    public static class DropFramesEntity {
        public int audioDroppedDuration;
        public int audioDuration;
        public int videoDroppedDuration;
        public int videoDuration;
    }

    public static DropFramesEntity getDropFramesDataFromPlayer(IjkMediaPlayer ijkMediaPlayer) {
        if (ijkMediaPlayer == null) {
            return null;
        }
        long propertyLong = ijkMediaPlayer.getPropertyLong(21001);
        long propertyLong2 = ijkMediaPlayer.getPropertyLong(21002);
        if (propertyLong == 0 && propertyLong2 == 0) {
            return null;
        }
        DropFramesEntity dropFramesEntity = new DropFramesEntity();
        dropFramesEntity.audioDuration = (int) (propertyLong & 1048575);
        dropFramesEntity.videoDuration = (int) (1048575 & propertyLong2);
        dropFramesEntity.audioDroppedDuration = (int) (propertyLong >> 20);
        dropFramesEntity.videoDroppedDuration = (int) (propertyLong2 >> 20);
        return dropFramesEntity;
    }
}
