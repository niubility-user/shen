package tv.danmaku.ijk.media.ext.pool;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import tv.danmaku.ijk.media.example.widget.media.TextureRenderView;
import tv.danmaku.ijk.media.player.IJDVideoPlayer;

/* loaded from: classes11.dex */
public class JDPlayStateInfo implements Serializable {
    private int keycode;
    private String originUrl;
    private TextureRenderView textureRenderView;
    private WeakReference<IJDVideoPlayer> videoViewRef;

    public JDPlayStateInfo(String str, int i2, WeakReference<IJDVideoPlayer> weakReference, TextureRenderView textureRenderView) {
        this.originUrl = str;
        this.keycode = i2;
        this.videoViewRef = weakReference;
        this.textureRenderView = textureRenderView;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.originUrl.equals(((JDPlayStateInfo) obj).originUrl);
    }

    public int getKeycode() {
        return this.keycode;
    }

    public IJDVideoPlayer getMediaPlayer() {
        WeakReference<IJDVideoPlayer> weakReference = this.videoViewRef;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public String getOriginUrl() {
        return this.originUrl;
    }

    public TextureRenderView getSurfaceTexture() {
        return this.textureRenderView;
    }

    public int hashCode() {
        return this.originUrl.hashCode();
    }

    public void setSurfaceTexture(TextureRenderView textureRenderView) {
        this.textureRenderView = textureRenderView;
    }

    public String toString() {
        return "JDPlayStateInfo{originUrl='" + this.originUrl + "', videoViewRef=" + this.videoViewRef + ", surfaceTexture=" + this.textureRenderView + ", keycode=" + this.keycode + '}';
    }

    public JDPlayStateInfo(String str) {
        this.originUrl = str;
    }
}
