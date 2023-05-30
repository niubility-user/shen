package tv.danmaku.ijk.media.alpha.mix;

import android.util.Pair;
import android.view.MotionEvent;
import java.util.Iterator;
import tv.danmaku.ijk.media.alpha.AlphaConfig;

/* loaded from: classes11.dex */
public class MixTouch {
    private static final String TAG = "MixTouch";
    private MixAlphaPlugin mixAlphaPlugin;

    public MixTouch(MixAlphaPlugin mixAlphaPlugin) {
        this.mixAlphaPlugin = mixAlphaPlugin;
    }

    private Boolean calClick(int i2, int i3, AlphaConfig.PointRect pointRect) {
        int i4;
        int i5 = pointRect.x;
        return Boolean.valueOf(i2 >= i5 && i2 <= i5 + pointRect.w && i3 >= (i4 = pointRect.y) && i3 <= i4 + pointRect.f20500h);
    }

    public Resource onTouchEvent(MotionEvent motionEvent) {
        MixAlphaPlugin mixAlphaPlugin = this.mixAlphaPlugin;
        if (mixAlphaPlugin != null && mixAlphaPlugin.getPlayer() != null && this.mixAlphaPlugin.getPlayer().getAlphaPlayerView() != null && this.mixAlphaPlugin.getPlayer().configManager != null && this.mixAlphaPlugin.getPlayer().configManager.config != null) {
            Pair<Integer, Integer> realSize = this.mixAlphaPlugin.getPlayer().getAlphaPlayerView().getRealSize();
            int i2 = this.mixAlphaPlugin.getPlayer().configManager.config.width;
            int i3 = this.mixAlphaPlugin.getPlayer().configManager.config.height;
            if (i2 != 0 && i3 != 0 && ((Integer) realSize.first).intValue() != 0 && ((Integer) realSize.second).intValue() != 0 && motionEvent.getAction() == 1) {
                float x = (motionEvent.getX() * i2) / ((Integer) realSize.first).intValue();
                float y = (motionEvent.getY() * i3) / ((Integer) realSize.second).intValue();
                if (this.mixAlphaPlugin.getFrameAll() != null && this.mixAlphaPlugin.getFrameAll().getMap() != null && this.mixAlphaPlugin.getFrameAll().getMap().get(this.mixAlphaPlugin.getCurFrameIndex()) != null) {
                    Iterator<Frame> it = this.mixAlphaPlugin.getFrameAll().getMap().get(this.mixAlphaPlugin.getCurFrameIndex()).getList().iterator();
                    while (it.hasNext()) {
                        Frame next = it.next();
                        Src src = this.mixAlphaPlugin.getSrcMap().getMap().get(next.getSrcId());
                        if (calClick((int) x, (int) y, next.getFrame()).booleanValue()) {
                            Resource resource = new Resource(src);
                            resource.setCurPoint(next.getFrame());
                            return resource;
                        }
                    }
                }
            }
        }
        return null;
    }
}
