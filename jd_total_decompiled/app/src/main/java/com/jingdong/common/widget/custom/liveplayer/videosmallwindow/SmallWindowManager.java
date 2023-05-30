package com.jingdong.common.widget.custom.liveplayer.videosmallwindow;

import android.view.View;
import android.view.ViewGroup;
import com.jingdong.common.utils.BackForegroundWatcher;
import com.jingdong.common.widget.custom.liveplayer.LiveUIConfigBean;
import com.jingdong.common.widget.custom.liveplayer.callback.SmallWindowUICallback;
import com.jingdong.common.widget.custom.liveplayer.decoration.IDecorator;
import com.jingdong.common.widget.custom.liveplayer.videosmallwindow.ISmallWindowManager;
import com.jingdong.common.widget.custom.liveplayer.videosmallwindow.SmallWindow;
import tv.danmaku.ijk.media.example.widget.media.IjkVideoView;
import tv.danmaku.ijk.media.example.widget.media.JDVideoView;

/* loaded from: classes12.dex */
public class SmallWindowManager implements ISmallWindowManager {
    private BackWatcher backWatcher;
    private View entityView;
    private ISmallWindowManager.IPlayerChange iPlayerChange;
    private boolean isSmallShowing;
    protected ISmallWindowManager.ISizeChanger sizeChanger;
    protected SmallWindow smallWindow;
    protected boolean autoCloseBackground = true;
    private boolean isManualClosed = false;

    /* loaded from: classes12.dex */
    public class BackWatcher implements BackForegroundWatcher.BackForegroundListener {
        private BackWatcher() {
            SmallWindowManager.this = r1;
        }

        @Override // com.jingdong.common.utils.BackForegroundWatcher.BackForegroundListener
        public void onBackToForeground() {
        }

        @Override // com.jingdong.common.utils.BackForegroundWatcher.BackForegroundListener
        public void onForeToBackground() {
            if (SmallWindowManager.this.isSmallShowing) {
                SmallWindowManager smallWindowManager = SmallWindowManager.this;
                if (smallWindowManager.autoCloseBackground) {
                    smallWindowManager.close();
                }
            }
        }
    }

    /* loaded from: classes12.dex */
    public static class Instance {
        private static SmallWindowManager smallWindowManager = new SmallWindowManager();

        private Instance() {
        }
    }

    /* loaded from: classes12.dex */
    public class OnCloseClick implements View.OnClickListener {
        private OnCloseClick() {
            SmallWindowManager.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SmallWindowManager.this.isManualClosed = true;
            SmallWindowManager.this.close();
        }
    }

    public void checkSmallwindowShow() {
        SmallWindow smallWindow = this.smallWindow;
        if (smallWindow != null) {
            this.isSmallShowing = smallWindow.isSmallWindowShow();
        }
    }

    public static SmallWindowManager getInstance() {
        return Instance.smallWindowManager;
    }

    private JDVideoView traversalNewVideoView(ViewGroup viewGroup) {
        JDVideoView traversalNewVideoView;
        for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if (childAt instanceof JDVideoView) {
                return (JDVideoView) childAt;
            }
            if ((childAt instanceof ViewGroup) && (traversalNewVideoView = traversalNewVideoView((ViewGroup) childAt)) != null) {
                return traversalNewVideoView;
            }
        }
        return null;
    }

    private IjkVideoView traversalView(ViewGroup viewGroup) {
        IjkVideoView traversalView;
        for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if (childAt instanceof IjkVideoView) {
                return (IjkVideoView) childAt;
            }
            if ((childAt instanceof ViewGroup) && (traversalView = traversalView((ViewGroup) childAt)) != null) {
                return traversalView;
            }
        }
        return null;
    }

    public void addSmallWindowUICallBack(SmallWindowUICallback smallWindowUICallback) {
        SmallWindow smallWindow = this.smallWindow;
        if (smallWindow != null) {
            smallWindow.addSmallWindowUICallBack(smallWindowUICallback);
        }
    }

    @Override // com.jingdong.common.widget.custom.liveplayer.videosmallwindow.ISmallWindowManager
    public void close() {
        if (isNotEmpty()) {
            this.isSmallShowing = false;
            this.iPlayerChange.onPause();
            this.smallWindow.close(null, this.isManualClosed);
        }
    }

    @Override // com.jingdong.common.widget.custom.liveplayer.videosmallwindow.ISmallWindowManager
    public void destory() {
        if (isNotEmpty()) {
            if (this.isSmallShowing) {
                this.isSmallShowing = false;
                this.smallWindow.close(null, this.isManualClosed);
            }
            BackForegroundWatcher.getInstance().unRegisterListener(this.backWatcher);
            ISmallWindowManager.IPlayerChange iPlayerChange = this.iPlayerChange;
            if (iPlayerChange != null) {
                iPlayerChange.onDestory();
            }
            this.iPlayerChange = null;
            this.entityView = null;
            this.smallWindow = null;
            this.backWatcher = null;
            this.sizeChanger = null;
        }
    }

    public void exposeSmallWindow() {
        SmallWindow smallWindow = this.smallWindow;
        if (smallWindow == null || !this.isSmallShowing) {
            return;
        }
        smallWindow.exposeSmallWindow();
    }

    public SmallWindow.Holder getUIHolder() {
        SmallWindow smallWindow = this.smallWindow;
        if (smallWindow != null) {
            return smallWindow.getUIHoder();
        }
        return null;
    }

    public boolean hasSmallWindow() {
        return this.smallWindow != null;
    }

    public void hideSmallWindow() {
        SmallWindow smallWindow = this.smallWindow;
        if (smallWindow == null || !this.isSmallShowing) {
            return;
        }
        smallWindow.hideSmallWindow();
    }

    @Override // com.jingdong.common.widget.custom.liveplayer.videosmallwindow.ISmallWindowManager
    public void init(View view, boolean z, ISmallWindowManager.IPlayerChange iPlayerChange) {
        init(view, z, iPlayerChange, null, true, 12.0f, 2, SmallWindow.DEFAULT_BORDER_COLOR);
    }

    public boolean isInPostponeSmallWindowStatus() {
        SmallWindow smallWindow = this.smallWindow;
        if (smallWindow != null) {
            return smallWindow.isInPostponeSmallWindowStatus();
        }
        return false;
    }

    public boolean isLandScape() {
        SmallWindow smallWindow = this.smallWindow;
        if (smallWindow != null) {
            return smallWindow.isLandScape();
        }
        return false;
    }

    public boolean isManualClosed() {
        return this.isManualClosed;
    }

    public boolean isNotEmpty() {
        return (this.iPlayerChange == null || this.entityView == null || this.smallWindow == null || this.backWatcher == null) ? false : true;
    }

    public boolean isPlaying() {
        View view = this.entityView;
        if (view != null) {
            if (view instanceof IjkVideoView) {
                return ((IjkVideoView) view).isPlaying();
            }
            if (view instanceof JDVideoView) {
                return ((JDVideoView) view).isPlaying();
            }
            if (view instanceof ViewGroup) {
                IjkVideoView traversalView = traversalView((ViewGroup) view);
                if (traversalView != null) {
                    return traversalView.isPlaying();
                }
                JDVideoView traversalNewVideoView = traversalNewVideoView((ViewGroup) this.entityView);
                if (traversalNewVideoView != null) {
                    return traversalNewVideoView.isPlaying();
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public boolean isSmallShowing() {
        return this.isSmallShowing;
    }

    public void notifyDecorationChange() {
        SmallWindow smallWindow = this.smallWindow;
        if (smallWindow != null) {
            smallWindow.notifyDecorationChange();
        }
    }

    @Override // com.jingdong.common.widget.custom.liveplayer.videosmallwindow.ISmallWindowManager
    public void onResume() {
        if (isNotEmpty() && this.isSmallShowing) {
            showBig();
        }
    }

    @Override // com.jingdong.common.widget.custom.liveplayer.videosmallwindow.ISmallWindowManager
    public void onStop() {
        if (!isNotEmpty() || this.isSmallShowing) {
            return;
        }
        this.iPlayerChange.onPause();
    }

    public void postoneDisplaySmallwindow() {
        SmallWindow smallWindow = this.smallWindow;
        if (smallWindow != null) {
            smallWindow.postoneDisplaySmallwindow();
        }
    }

    public void removeSmallWindowUICallBack(SmallWindowUICallback smallWindowUICallback) {
        SmallWindow smallWindow = this.smallWindow;
        if (smallWindow != null) {
            smallWindow.removeSmallWindowUICallBack(smallWindowUICallback);
        }
    }

    public void setAutoCloseBackground(boolean z) {
        this.autoCloseBackground = z;
    }

    public void setBlurImageURL(String str, boolean z) {
        SmallWindow smallWindow = this.smallWindow;
        if (smallWindow != null) {
            try {
                smallWindow.setBlurImageURL(str, z);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void setBottomSafeDistance(int i2) {
        SmallWindow smallWindow = this.smallWindow;
        if (smallWindow != null) {
            smallWindow.setBottomSafeDistance(i2);
        }
    }

    public void setDecoration(IDecorator iDecorator) {
        SmallWindow smallWindow = this.smallWindow;
        if (smallWindow != null) {
            smallWindow.setDecoration(iDecorator);
        }
    }

    public void setLeftRightSafeDistance(int i2) {
        SmallWindow smallWindow = this.smallWindow;
        if (smallWindow != null) {
            smallWindow.setLeftRightSafeDistance(i2);
        }
    }

    public void setNeedAttach(boolean z) {
        SmallWindow smallWindow = this.smallWindow;
        if (smallWindow != null) {
            smallWindow.setmNeedAttach(z);
        }
    }

    public void setShowMuteIcon(boolean z) {
        SmallWindow smallWindow = this.smallWindow;
        if (smallWindow != null) {
            smallWindow.setShowMuteIcon(z);
        }
    }

    public void setSmallWindowPlayStatus(int i2) {
        SmallWindow smallWindow = this.smallWindow;
        if (smallWindow != null) {
            smallWindow.setSmallWindowPlayStatus(i2);
        }
    }

    public void setSmallWindowUICallBack(SmallWindowUICallback smallWindowUICallback) {
        SmallWindow smallWindow = this.smallWindow;
        if (smallWindow != null) {
            smallWindow.setSmallWindowUICallBack(smallWindowUICallback);
        }
    }

    public void setTopSafeDistance(int i2) {
        SmallWindow smallWindow = this.smallWindow;
        if (smallWindow != null) {
            smallWindow.setTopSafeDistance(i2);
        }
    }

    public void setWindowPos(int i2, int i3) {
        SmallWindow smallWindow = this.smallWindow;
        if (smallWindow != null) {
            smallWindow.setPos(i2, i3);
        }
    }

    @Override // com.jingdong.common.widget.custom.liveplayer.videosmallwindow.ISmallWindowManager
    public void showBig() {
        if (isNotEmpty()) {
            this.isSmallShowing = false;
            this.smallWindow.close(new SmallWindow.IOpenOrClose() { // from class: com.jingdong.common.widget.custom.liveplayer.videosmallwindow.SmallWindowManager.1
                {
                    SmallWindowManager.this = this;
                }

                @Override // com.jingdong.common.widget.custom.liveplayer.videosmallwindow.SmallWindow.IOpenOrClose
                public void complete() {
                    if (SmallWindowManager.this.isNotEmpty()) {
                        SmallWindowManager.this.iPlayerChange.showAtBig();
                    }
                }
            }, this.isManualClosed);
        }
    }

    @Override // com.jingdong.common.widget.custom.liveplayer.videosmallwindow.ISmallWindowManager
    public void showSmall() {
        this.isSmallShowing = true;
        this.isManualClosed = false;
        if (isNotEmpty()) {
            this.iPlayerChange.showAtSmall();
            SmallWindow smallWindow = this.smallWindow;
            ISmallWindowManager.ISizeChanger iSizeChanger = this.sizeChanger;
            smallWindow.setSize(iSizeChanger == null ? null : iSizeChanger.getSize());
            this.smallWindow.open(this.entityView, new SmallWindow.IOpenOrClose() { // from class: com.jingdong.common.widget.custom.liveplayer.videosmallwindow.SmallWindowManager.2
                {
                    SmallWindowManager.this = this;
                }

                @Override // com.jingdong.common.widget.custom.liveplayer.videosmallwindow.SmallWindow.IOpenOrClose
                public void complete() {
                    SmallWindowManager.this.checkSmallwindowShow();
                    if (SmallWindowManager.this.isNotEmpty()) {
                        SmallWindowManager.this.iPlayerChange.onPause();
                    }
                }
            });
        }
    }

    public void startDisplaySmallwindow() {
        SmallWindow smallWindow = this.smallWindow;
        if (smallWindow != null) {
            smallWindow.startDisplaySmallwindow();
        }
    }

    @Override // com.jingdong.common.widget.custom.liveplayer.videosmallwindow.ISmallWindowManager
    public void init(View view, boolean z, ISmallWindowManager.IPlayerChange iPlayerChange, ISmallWindowManager.ISizeChanger iSizeChanger, boolean z2, float f2, int i2, String str) {
        init(view, z, iPlayerChange, iSizeChanger, z2, f2, i2, str, null);
    }

    @Override // com.jingdong.common.widget.custom.liveplayer.videosmallwindow.ISmallWindowManager
    public void init(View view, boolean z, ISmallWindowManager.IPlayerChange iPlayerChange, ISmallWindowManager.ISizeChanger iSizeChanger, boolean z2, float f2, int i2, String str, LiveUIConfigBean liveUIConfigBean) {
        this.iPlayerChange = iPlayerChange;
        this.entityView = view;
        this.isSmallShowing = false;
        this.smallWindow = new SmallWindow(view.getContext(), iPlayerChange, z, new OnCloseClick(), z2, f2, i2, str, liveUIConfigBean);
        this.backWatcher = new BackWatcher();
        BackForegroundWatcher.getInstance().registerListener(this.backWatcher);
        this.sizeChanger = iSizeChanger;
    }

    @Override // com.jingdong.common.widget.custom.liveplayer.videosmallwindow.ISmallWindowManager
    public void showSmall(boolean z) {
        showSmall(z, 0);
    }

    public void init(View view, boolean z, ISmallWindowManager.IPlayerChange iPlayerChange, ISmallWindowManager.ISizeChanger iSizeChanger) {
        init(view, z, iPlayerChange);
        this.sizeChanger = iSizeChanger;
    }

    @Override // com.jingdong.common.widget.custom.liveplayer.videosmallwindow.ISmallWindowManager
    public void showSmall(boolean z, int i2) {
        this.isSmallShowing = true;
        this.isManualClosed = false;
        if (isNotEmpty()) {
            this.iPlayerChange.showAtSmall();
            SmallWindow smallWindow = this.smallWindow;
            ISmallWindowManager.ISizeChanger iSizeChanger = this.sizeChanger;
            smallWindow.setSize(iSizeChanger == null ? null : iSizeChanger.getSize());
            this.smallWindow.open(this.entityView, new SmallWindow.IOpenOrClose() { // from class: com.jingdong.common.widget.custom.liveplayer.videosmallwindow.SmallWindowManager.3
                {
                    SmallWindowManager.this = this;
                }

                @Override // com.jingdong.common.widget.custom.liveplayer.videosmallwindow.SmallWindow.IOpenOrClose
                public void complete() {
                    SmallWindowManager.this.checkSmallwindowShow();
                    if (SmallWindowManager.this.isNotEmpty()) {
                        SmallWindowManager.this.iPlayerChange.onPause();
                    }
                }
            }, z);
        }
    }
}
