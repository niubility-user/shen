package com.jingdong.common.utils.personal.platform.floorframe.isv;

import android.content.res.Configuration;
import com.jingdong.common.cart.clean.CartCleanConstants;
import com.jingdong.sdk.platform.floor.entity.BaseFloorData;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tv.danmaku.ijk.media.player.IMediaPlayer;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0019\u001a\u00020\u0018\u0012\u0006\u0010\u001a\u001a\u00020\u0018\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\u0007\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u0007\u0010\u0006J\u0019\u0010\n\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0016\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0019\u0010\r\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\fH\u0016\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0019\u0010\u000f\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\fH\u0016\u00a2\u0006\u0004\b\u000f\u0010\u000eJ\u0017\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0010H\u0016\u00a2\u0006\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0016\u00a8\u0006\u001d"}, d2 = {"Lcom/jingdong/common/utils/personal/platform/floorframe/isv/ISVFloorData;", "Lcom/jingdong/sdk/platform/floor/entity/BaseFloorData;", "Lcom/jingdong/common/utils/personal/platform/floorframe/isv/OnConfigurationChangeListener;", CartCleanConstants.CART_CLEAN_DIALOG_LISTENER, "", "addOnConfigurationChangeListener", "(Lcom/jingdong/common/utils/personal/platform/floorframe/isv/OnConfigurationChangeListener;)V", "removeOnConfigurationChangeListener", "Landroid/content/res/Configuration;", "newConfig", "notifyOnConfigurationChanged", "(Landroid/content/res/Configuration;)V", "Lcom/jingdong/common/utils/personal/platform/floorframe/isv/OnScrollChangeListener;", "addOnScrollChangeListener", "(Lcom/jingdong/common/utils/personal/platform/floorframe/isv/OnScrollChangeListener;)V", "removeOnScrollChangeListener", "", IMediaPlayer.OnNativeInvokeListener.ARG_OFFSET, "notifyOnScrollChanged", "(I)V", "Ljava/util/concurrent/CopyOnWriteArrayList;", "onScrollChangeListeners", "Ljava/util/concurrent/CopyOnWriteArrayList;", "onISVConfigurationChangeListeners", "", "key", "moduleName", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public class ISVFloorData extends BaseFloorData {
    private CopyOnWriteArrayList<OnConfigurationChangeListener> onISVConfigurationChangeListeners;
    private CopyOnWriteArrayList<OnScrollChangeListener> onScrollChangeListeners;

    public ISVFloorData(@NotNull String str, @NotNull String str2) {
        super(str, str2);
    }

    public final void addOnConfigurationChangeListener(@Nullable OnConfigurationChangeListener listener) {
        if (listener != null) {
            if (this.onISVConfigurationChangeListeners == null) {
                this.onISVConfigurationChangeListeners = new CopyOnWriteArrayList<>();
            }
            CopyOnWriteArrayList<OnConfigurationChangeListener> copyOnWriteArrayList = this.onISVConfigurationChangeListeners;
            if (copyOnWriteArrayList != null) {
                copyOnWriteArrayList.add(listener);
            }
        }
    }

    public void addOnScrollChangeListener(@Nullable OnScrollChangeListener listener) {
        if (listener != null) {
            if (this.onScrollChangeListeners == null) {
                this.onScrollChangeListeners = new CopyOnWriteArrayList<>();
            }
            CopyOnWriteArrayList<OnScrollChangeListener> copyOnWriteArrayList = this.onScrollChangeListeners;
            if (copyOnWriteArrayList != null) {
                copyOnWriteArrayList.add(listener);
            }
        }
    }

    public void notifyOnConfigurationChanged(@Nullable Configuration newConfig) {
        CopyOnWriteArrayList<OnConfigurationChangeListener> copyOnWriteArrayList = this.onISVConfigurationChangeListeners;
        if (copyOnWriteArrayList != null) {
            Iterator<OnConfigurationChangeListener> it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                it.next().onConfigurationChanged(newConfig);
            }
        }
    }

    public void notifyOnScrollChanged(int offset) {
        CopyOnWriteArrayList<OnScrollChangeListener> copyOnWriteArrayList = this.onScrollChangeListeners;
        if (copyOnWriteArrayList != null) {
            Iterator<T> it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                ((OnScrollChangeListener) it.next()).onScrollChanged(offset);
            }
        }
    }

    public final void removeOnConfigurationChangeListener(@Nullable OnConfigurationChangeListener listener) {
        CopyOnWriteArrayList<OnConfigurationChangeListener> copyOnWriteArrayList;
        if (listener == null || (copyOnWriteArrayList = this.onISVConfigurationChangeListeners) == null) {
            return;
        }
        copyOnWriteArrayList.remove(listener);
    }

    public void removeOnScrollChangeListener(@Nullable OnScrollChangeListener listener) {
        CopyOnWriteArrayList<OnScrollChangeListener> copyOnWriteArrayList;
        if (listener == null || (copyOnWriteArrayList = this.onScrollChangeListeners) == null) {
            return;
        }
        copyOnWriteArrayList.remove(listener);
    }
}
