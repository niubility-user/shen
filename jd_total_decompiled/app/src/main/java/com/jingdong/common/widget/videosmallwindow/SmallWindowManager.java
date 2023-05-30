package com.jingdong.common.widget.videosmallwindow;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.jingdong.common.utils.BackForegroundWatcher;
import com.jingdong.common.widget.videosmallwindow.ISmallWindowManager;
import com.jingdong.common.widget.videosmallwindow.SmallWindow;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import tv.danmaku.ijk.media.example.widget.media.IjkVideoView;
import tv.danmaku.ijk.media.example.widget.media.JDVideoView;

/* loaded from: classes12.dex */
public class SmallWindowManager implements ISmallWindowManager {
    private BackWatcher backWatcher;
    private View entityView;
    private ISmallWindowManager.IPlayerChange iPlayerChange;
    private boolean isSmallShowing;
    private boolean isSmallShowingEx;
    private List<SmallWindowCallback> mCallbacks;
    private ISmallWindowManager.ISizeChanger sizeChanger;
    private SmallWindow smallWindow;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class BackWatcher implements BackForegroundWatcher.BackForegroundListener {
        private BackWatcher() {
        }

        @Override // com.jingdong.common.utils.BackForegroundWatcher.BackForegroundListener
        public void onBackToForeground() {
        }

        @Override // com.jingdong.common.utils.BackForegroundWatcher.BackForegroundListener
        public void onForeToBackground() {
            if (SmallWindowManager.this.isNotEmpty() && SmallWindowManager.this.isSmallShowing) {
                SmallWindowManager.this.iPlayerChange.onPause();
                SmallWindowManager.this.smallWindow.close(null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class Instance {
        private static SmallWindowManager smallWindowManager = new SmallWindowManager();

        private Instance() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class OnCloseClick implements View.OnClickListener {
        private OnCloseClick() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SmallWindowManager.this.close();
        }
    }

    /* loaded from: classes12.dex */
    public interface SmallWindowCallback {
        void onWindowClose();
    }

    public static SmallWindowManager getInstance() {
        return Instance.smallWindowManager;
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

    public void addCallback(SmallWindowCallback smallWindowCallback) {
        if (this.mCallbacks == null) {
            this.mCallbacks = new ArrayList();
        }
        this.mCallbacks.add(smallWindowCallback);
    }

    @Override // com.jingdong.common.widget.videosmallwindow.ISmallWindowManager
    public void close() {
        if (isNotEmpty()) {
            this.iPlayerChange.onPause();
            this.smallWindow.close(null);
        }
        List<SmallWindowCallback> list = this.mCallbacks;
        if (list != null && list.size() > 0) {
            Iterator<SmallWindowCallback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onWindowClose();
            }
        }
        this.mCallbacks = null;
        this.isSmallShowingEx = false;
    }

    @Override // com.jingdong.common.widget.videosmallwindow.ISmallWindowManager
    public void destory() {
        if (isNotEmpty()) {
            if (this.isSmallShowing) {
                this.smallWindow.close(null);
            }
            BackForegroundWatcher.getInstance().unRegisterListener(this.backWatcher);
            this.iPlayerChange.onDestory();
            this.iPlayerChange = null;
            this.entityView = null;
            this.smallWindow = null;
            this.backWatcher = null;
            this.sizeChanger = null;
        }
    }

    @Override // com.jingdong.common.widget.videosmallwindow.ISmallWindowManager
    public void init(View view, boolean z, ISmallWindowManager.IPlayerChange iPlayerChange) {
        init(view, (SmallWindowConfig) null, z, iPlayerChange);
    }

    public boolean isNotEmpty() {
        return (this.iPlayerChange == null || this.entityView == null || this.smallWindow == null || this.backWatcher == null) ? false : true;
    }

    public boolean isPlaying() {
        JDVideoView traversalNewVideoView;
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
                SmallWindow smallWindow = this.smallWindow;
                if (smallWindow == null || (traversalNewVideoView = smallWindow.traversalNewVideoView((ViewGroup) this.entityView)) == null) {
                    return false;
                }
                return traversalNewVideoView.isPlaying();
            }
            return false;
        }
        return false;
    }

    public boolean isSmallShowing() {
        return this.isSmallShowing;
    }

    public boolean isSmallShowingEx() {
        return this.isSmallShowingEx;
    }

    @Override // com.jingdong.common.widget.videosmallwindow.ISmallWindowManager
    public void onResume() {
        if (isNotEmpty() && this.isSmallShowing) {
            showBig();
        }
    }

    @Override // com.jingdong.common.widget.videosmallwindow.ISmallWindowManager
    public void onStop() {
        if (!isNotEmpty() || this.isSmallShowing) {
            return;
        }
        this.iPlayerChange.onPause();
    }

    @Override // com.jingdong.common.widget.videosmallwindow.ISmallWindowManager
    public void showBig() {
        if (isNotEmpty()) {
            this.isSmallShowing = false;
            this.isSmallShowingEx = false;
            this.smallWindow.close(new SmallWindow.IOpenOrClose() { // from class: com.jingdong.common.widget.videosmallwindow.SmallWindowManager.1
                @Override // com.jingdong.common.widget.videosmallwindow.SmallWindow.IOpenOrClose
                public void complete() {
                    if (SmallWindowManager.this.isNotEmpty()) {
                        SmallWindowManager.this.iPlayerChange.showAtBig();
                    }
                }
            });
        }
    }

    @Override // com.jingdong.common.widget.videosmallwindow.ISmallWindowManager
    public void showSmall() {
        this.isSmallShowing = true;
        this.isSmallShowingEx = true;
        if (isNotEmpty()) {
            this.iPlayerChange.showAtSmall();
            SmallWindow smallWindow = this.smallWindow;
            ISmallWindowManager.ISizeChanger iSizeChanger = this.sizeChanger;
            smallWindow.setSize(iSizeChanger == null ? null : iSizeChanger.getSize());
            this.smallWindow.open(this.entityView, new SmallWindow.IOpenOrClose() { // from class: com.jingdong.common.widget.videosmallwindow.SmallWindowManager.2
                @Override // com.jingdong.common.widget.videosmallwindow.SmallWindow.IOpenOrClose
                public void complete() {
                    if (SmallWindowManager.this.isNotEmpty()) {
                        SmallWindowManager.this.iPlayerChange.onPause();
                    }
                }
            });
        }
    }

    private SmallWindowManager() {
    }

    public void init(View view, SmallWindowConfig smallWindowConfig, boolean z, ISmallWindowManager.IPlayerChange iPlayerChange) {
        this.iPlayerChange = iPlayerChange;
        this.entityView = view;
        this.isSmallShowing = false;
        this.isSmallShowingEx = false;
        Context context = view.getContext();
        this.smallWindow = new SmallWindow(context, smallWindowConfig, iPlayerChange, z, new OnCloseClick());
        this.backWatcher = new BackWatcher();
        BackForegroundWatcher.getInstance().registerListener(this.backWatcher);
        this.sizeChanger = null;
    }

    public void init(View view, SmallWindowConfig smallWindowConfig, boolean z, ISmallWindowManager.IPlayerChange iPlayerChange, ISmallWindowManager.ISizeChanger iSizeChanger) {
        init(view, smallWindowConfig, z, iPlayerChange);
        this.sizeChanger = iSizeChanger;
    }

    public void init(View view, boolean z, ISmallWindowManager.IPlayerChange iPlayerChange, ISmallWindowManager.ISizeChanger iSizeChanger) {
        init(view, null, z, iPlayerChange, iSizeChanger);
    }
}
