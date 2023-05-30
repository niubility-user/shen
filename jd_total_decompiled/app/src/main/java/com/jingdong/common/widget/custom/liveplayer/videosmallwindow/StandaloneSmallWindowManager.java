package com.jingdong.common.widget.custom.liveplayer.videosmallwindow;

import android.content.Context;
import android.graphics.Point;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import com.jingdong.common.widget.custom.liveplayer.LiveDataEntity;
import com.jingdong.common.widget.custom.liveplayer.LivePlayer;
import com.jingdong.common.widget.custom.liveplayer.LiveUIConfigBean;
import com.jingdong.common.widget.custom.liveplayer.videosmallwindow.ISmallWindowManager;
import com.jingdong.sdk.utils.DPIUtil;
import java.lang.ref.WeakReference;

/* loaded from: classes12.dex */
public class StandaloneSmallWindowManager extends SmallWindowManager {
    public static final int DEFAULT_LANDSCAPE_HEIGHT = 90;
    public static final int DEFAULT_LANDSCAPE_WIDTH = 160;
    public static final int DEFAULT_PORTRAIT_HEIGHT = 180;
    public static final int DEFAULT_PORTRAIT_WIDTH = 102;
    private static boolean isLandScape;
    private static boolean isMateX;
    private static LivePlayer livePlayer;
    private static FrameLayout mPlayerHolder;
    private static ISmallWindowManager.ISizeChanger mSizeChanger;
    private Context mContext;
    private LiveDataEntity mLiveDataEntity;
    private boolean mNeedIgnoreLoadingStatusWhenStart;
    private ISmallWindowManager.IPlayerChange mPlayerChangeListener;
    private boolean playerDestroyed = false;
    private boolean inited = false;

    /* renamed from: com.jingdong.common.widget.custom.liveplayer.videosmallwindow.StandaloneSmallWindowManager$2 */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$jingdong$common$widget$custom$liveplayer$LivePlayer$PlayerStatus;

        static {
            int[] iArr = new int[LivePlayer.PlayerStatus.values().length];
            $SwitchMap$com$jingdong$common$widget$custom$liveplayer$LivePlayer$PlayerStatus = iArr;
            try {
                iArr[LivePlayer.PlayerStatus.STATE_FINISHED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    /* loaded from: classes12.dex */
    public static class Instance {
        private static StandaloneSmallWindowManager smallWindowManager = new StandaloneSmallWindowManager();

        private Instance() {
        }
    }

    /* loaded from: classes12.dex */
    public static class SmallWindowPlayerChange implements ISmallWindowManager.IPlayerChange {
        private WeakReference<FrameLayout> containerReference;
        public boolean isClose = false;
        public boolean isDestroy = false;

        public void addPlayerToContainer(FrameLayout frameLayout) {
            int dip2px;
            int dip2px2;
            if (frameLayout == null) {
                return;
            }
            removePlayerFromParent(StandaloneSmallWindowManager.livePlayer);
            if (StandaloneSmallWindowManager.isLandScape) {
                dip2px = DPIUtil.dip2px(160.0f);
                dip2px2 = DPIUtil.dip2px(90.0f);
            } else {
                dip2px = DPIUtil.dip2px(102.0f);
                dip2px2 = DPIUtil.dip2px(180.0f);
            }
            if (StandaloneSmallWindowManager.mSizeChanger != null && StandaloneSmallWindowManager.mSizeChanger.getSize() != null) {
                dip2px = StandaloneSmallWindowManager.mSizeChanger.getSize().x;
                dip2px2 = StandaloneSmallWindowManager.mSizeChanger.getSize().y;
            }
            frameLayout.addView(StandaloneSmallWindowManager.livePlayer, dip2px, dip2px2);
            setPlayerContainer(frameLayout);
        }

        public FrameLayout getPlayerContainer() {
            WeakReference<FrameLayout> weakReference = this.containerReference;
            if (weakReference == null) {
                return null;
            }
            return weakReference.get();
        }

        public void isClose(boolean z) {
            this.isClose = z;
        }

        public boolean isPlaying() {
            return StandaloneSmallWindowManager.livePlayer != null && StandaloneSmallWindowManager.livePlayer.isPlaying();
        }

        public boolean isShowSmall() {
            if (StandaloneSmallWindowManager.livePlayer != null) {
                return StandaloneSmallWindowManager.livePlayer.isShowInSmall();
            }
            return false;
        }

        @Override // com.jingdong.common.widget.custom.liveplayer.videosmallwindow.ISmallWindowManager.IPlayerChange
        public void onDestory() {
            if (StandaloneSmallWindowManager.livePlayer != null) {
                StandaloneSmallWindowManager.livePlayer.releaseInThread();
            }
        }

        @Override // com.jingdong.common.widget.custom.liveplayer.videosmallwindow.ISmallWindowManager.IPlayerChange
        public void onMuteClick() {
            if (StandaloneSmallWindowManager.livePlayer == null || this.isDestroy) {
                return;
            }
            StandaloneSmallWindowManager.livePlayer.changeVoiceState();
        }

        @Override // com.jingdong.common.widget.custom.liveplayer.videosmallwindow.ISmallWindowManager.IPlayerChange
        public void onPause() {
            if (!isShowSmall() || this.isDestroy) {
                return;
            }
            isClose(true);
            if (StandaloneSmallWindowManager.livePlayer != null) {
                StandaloneSmallWindowManager.livePlayer.restoreVideoView();
                StandaloneSmallWindowManager.livePlayer.setShowInSmall(false);
            }
        }

        @Override // com.jingdong.common.widget.custom.liveplayer.videosmallwindow.ISmallWindowManager.IPlayerChange
        public void onSmallClick() {
            if (StandaloneSmallWindowManager.livePlayer == null || this.isDestroy || StandaloneSmallWindowManager.isMateX) {
                return;
            }
            if (StandaloneSmallWindowManager.livePlayer.isJumpOut()) {
                StandaloneSmallWindowManager.getInstance().showBig();
            }
            if (StandaloneSmallWindowManager.livePlayer != null) {
                StandaloneSmallWindowManager.livePlayer.onSmallStarClick();
            }
        }

        public void removePlayerFromParent(LivePlayer livePlayer) {
            if (livePlayer == null) {
                return;
            }
            ViewParent parent = livePlayer.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(livePlayer);
            }
        }

        public void setPlayerContainer(FrameLayout frameLayout) {
            WeakReference<FrameLayout> weakReference = this.containerReference;
            if (weakReference != null) {
                weakReference.clear();
            }
            this.containerReference = new WeakReference<>(frameLayout);
        }

        @Override // com.jingdong.common.widget.custom.liveplayer.videosmallwindow.ISmallWindowManager.IPlayerChange
        public void showAtBig() {
            if (StandaloneSmallWindowManager.livePlayer == null || this.isDestroy || StandaloneSmallWindowManager.isMateX) {
                return;
            }
            addPlayerToContainer(getPlayerContainer());
            StandaloneSmallWindowManager.livePlayer.suspend();
        }

        @Override // com.jingdong.common.widget.custom.liveplayer.videosmallwindow.ISmallWindowManager.IPlayerChange
        public void showAtSmall() {
            Point point2;
            if (StandaloneSmallWindowManager.livePlayer == null || this.isDestroy || StandaloneSmallWindowManager.isMateX) {
                return;
            }
            removePlayerFromParent(StandaloneSmallWindowManager.livePlayer);
            StandaloneSmallWindowManager.livePlayer.setAspectRatio(1);
            if (StandaloneSmallWindowManager.isLandScape) {
                point2 = new Point(DPIUtil.dip2px(160.0f), DPIUtil.dip2px(90.0f));
            } else {
                point2 = new Point(DPIUtil.dip2px(102.0f), DPIUtil.dip2px(180.0f));
            }
            if (StandaloneSmallWindowManager.mSizeChanger != null && StandaloneSmallWindowManager.mSizeChanger.getSize() != null) {
                point2 = StandaloneSmallWindowManager.mSizeChanger.getSize();
            }
            StandaloneSmallWindowManager.livePlayer.resizeVideoView(point2);
            StandaloneSmallWindowManager.livePlayer.setShowInSmall(true);
        }
    }

    /* loaded from: classes12.dex */
    public static class SmallWindowSizeChanger implements ISmallWindowManager.ISizeChanger {
        @Override // com.jingdong.common.widget.custom.liveplayer.videosmallwindow.ISmallWindowManager.ISizeChanger
        public Point getSize() {
            if (StandaloneSmallWindowManager.livePlayer == null || StandaloneSmallWindowManager.isMateX) {
                return null;
            }
            if (StandaloneSmallWindowManager.isLandScape) {
                return new Point(DPIUtil.dip2px(160.0f), DPIUtil.dip2px(90.0f));
            }
            return new Point(DPIUtil.dip2px(102.0f), DPIUtil.dip2px(180.0f));
        }
    }

    private void createPlayer() {
        LivePlayer livePlayer2 = new LivePlayer(this.mContext);
        livePlayer = livePlayer2;
        this.playerDestroyed = false;
        livePlayer2.setStatusChangedListener(new LivePlayer.StatusChangedListener() { // from class: com.jingdong.common.widget.custom.liveplayer.videosmallwindow.StandaloneSmallWindowManager.1
            {
                StandaloneSmallWindowManager.this = this;
            }

            @Override // com.jingdong.common.widget.custom.liveplayer.LivePlayer.StatusChangedListener
            public void onStatusChanged(LivePlayer.PlayerStatus playerStatus) {
                if (AnonymousClass2.$SwitchMap$com$jingdong$common$widget$custom$liveplayer$LivePlayer$PlayerStatus[playerStatus.ordinal()] != 1) {
                    return;
                }
                StandaloneSmallWindowManager.getInstance().destroy();
            }
        });
    }

    public static StandaloneSmallWindowManager getInstance() {
        return Instance.smallWindowManager;
    }

    private void reportPlayerExpo() {
        LivePlayer livePlayer2;
        if (!this.inited || (livePlayer2 = livePlayer) == null) {
            return;
        }
        livePlayer2.reportExpo();
    }

    public static void setIsMateX(boolean z) {
        isMateX = z;
    }

    @Override // com.jingdong.common.widget.custom.liveplayer.videosmallwindow.SmallWindowManager, com.jingdong.common.widget.custom.liveplayer.videosmallwindow.ISmallWindowManager
    public void close() {
        reportPlayerExpo();
        this.inited = false;
        super.close();
    }

    public void destroy() {
        this.inited = false;
        super.destory();
        ISmallWindowManager.IPlayerChange iPlayerChange = this.mPlayerChangeListener;
        if (iPlayerChange instanceof SmallWindowPlayerChange) {
            ((SmallWindowPlayerChange) iPlayerChange).removePlayerFromParent(livePlayer);
        }
        this.mPlayerChangeListener = null;
        LivePlayer livePlayer2 = livePlayer;
        if (livePlayer2 != null) {
            livePlayer2.destroy();
            livePlayer = null;
        }
        mPlayerHolder = null;
        this.playerDestroyed = true;
        mSizeChanger = null;
    }

    public LiveDataEntity getLiveDataEntity() {
        return this.mLiveDataEntity;
    }

    public void init(Context context, boolean z, ISmallWindowManager.IPlayerChange iPlayerChange, ISmallWindowManager.ISizeChanger iSizeChanger) {
        init(context, z, iPlayerChange, iSizeChanger, false, 0.0f, 2, SmallWindow.DEFAULT_BORDER_COLOR);
    }

    public void initStandaloneSmallWindow() {
    }

    public boolean isInited() {
        return this.inited;
    }

    public boolean isPlayerDestroyed() {
        return this.playerDestroyed;
    }

    public LivePlayer playerInstance() {
        return livePlayer;
    }

    public void reInit(Context context, boolean z, ISmallWindowManager.IPlayerChange iPlayerChange, ISmallWindowManager.ISizeChanger iSizeChanger) {
        if (isMateX) {
            return;
        }
        this.inited = false;
        close();
        init(context, z, iPlayerChange, iSizeChanger, false, 0.0f, 2, SmallWindow.DEFAULT_BORDER_COLOR);
    }

    public void setData(LiveDataEntity liveDataEntity) {
        this.mLiveDataEntity = liveDataEntity;
        LivePlayer livePlayer2 = livePlayer;
        if (livePlayer2 != null) {
            livePlayer2.setData(liveDataEntity);
            if (liveDataEntity != null && "2".equals(liveDataEntity.liveType)) {
                setAutoCloseBackground(false);
            }
        }
        if (liveDataEntity != null) {
            setSmallWindowPlayStatus(liveDataEntity.liveStreamTag);
        }
        if (liveDataEntity == null || TextUtils.isEmpty(liveDataEntity.indexImage)) {
            return;
        }
        setBlurImageURL(liveDataEntity.indexImage, true);
    }

    public void setIgnoreLoadingStatusWhenStart(boolean z) {
        this.mNeedIgnoreLoadingStatusWhenStart = z;
    }

    public void setStatusChangedListener(LivePlayer.StatusChangedListener statusChangedListener) {
        livePlayer.setStatusChangedListener(statusChangedListener);
    }

    @Override // com.jingdong.common.widget.custom.liveplayer.videosmallwindow.SmallWindowManager, com.jingdong.common.widget.custom.liveplayer.videosmallwindow.ISmallWindowManager
    public void showSmall() {
        if (isMateX) {
            return;
        }
        if (this.mNeedIgnoreLoadingStatusWhenStart) {
            postoneDisplaySmallwindow();
        }
        LivePlayer livePlayer2 = livePlayer;
        if (livePlayer2 != null) {
            livePlayer2.play();
            super.showSmall();
        }
    }

    public void init(Context context, boolean z, ISmallWindowManager.IPlayerChange iPlayerChange, ISmallWindowManager.ISizeChanger iSizeChanger, boolean z2, float f2, int i2, String str) {
        init(context, z, iPlayerChange, iSizeChanger, z2, f2, i2, str, (LiveUIConfigBean) null);
    }

    public void init(Context context, boolean z, ISmallWindowManager.IPlayerChange iPlayerChange, ISmallWindowManager.ISizeChanger iSizeChanger, boolean z2, float f2, int i2, String str, LiveUIConfigBean liveUIConfigBean) {
        mSizeChanger = iSizeChanger;
        if (isMateX || this.inited) {
            return;
        }
        this.mContext = context;
        if (livePlayer == null) {
            createPlayer();
        }
        if (mPlayerHolder == null) {
            mPlayerHolder = new FrameLayout(this.mContext);
        }
        isLandScape = z;
        this.mPlayerChangeListener = iPlayerChange;
        if (iPlayerChange instanceof SmallWindowPlayerChange) {
            ((SmallWindowPlayerChange) iPlayerChange).setPlayerContainer(mPlayerHolder);
        }
        super.init(livePlayer, z, iPlayerChange, iSizeChanger, z2, f2, i2, str, liveUIConfigBean);
        this.inited = true;
    }

    public void reInit(Context context, boolean z, ISmallWindowManager.IPlayerChange iPlayerChange, ISmallWindowManager.ISizeChanger iSizeChanger, boolean z2, float f2, int i2, String str) {
        if (isMateX) {
            return;
        }
        this.inited = false;
        close();
        init(context, z, iPlayerChange, iSizeChanger, z2, f2, i2, str);
    }

    @Override // com.jingdong.common.widget.custom.liveplayer.videosmallwindow.SmallWindowManager, com.jingdong.common.widget.custom.liveplayer.videosmallwindow.ISmallWindowManager
    public void showSmall(boolean z) {
        showSmall(z, 0);
    }

    @Override // com.jingdong.common.widget.custom.liveplayer.videosmallwindow.SmallWindowManager, com.jingdong.common.widget.custom.liveplayer.videosmallwindow.ISmallWindowManager
    public void showSmall(boolean z, int i2) {
        if (isMateX) {
            return;
        }
        if (this.mNeedIgnoreLoadingStatusWhenStart) {
            postoneDisplaySmallwindow();
        }
        LivePlayer livePlayer2 = livePlayer;
        if (livePlayer2 != null) {
            livePlayer2.play(z, i2);
            super.showSmall(z, i2);
        }
    }

    public void reInit(Context context, boolean z, ISmallWindowManager.IPlayerChange iPlayerChange, ISmallWindowManager.ISizeChanger iSizeChanger, boolean z2, float f2, int i2, String str, LiveUIConfigBean liveUIConfigBean) {
        if (isMateX) {
            return;
        }
        this.inited = false;
        close();
        init(context, z, iPlayerChange, iSizeChanger, z2, f2, i2, str, liveUIConfigBean);
    }
}
