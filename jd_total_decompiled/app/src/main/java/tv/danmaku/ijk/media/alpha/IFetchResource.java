package tv.danmaku.ijk.media.alpha;

import android.graphics.Bitmap;
import java.util.List;
import tv.danmaku.ijk.media.alpha.mix.Resource;

/* loaded from: classes11.dex */
public interface IFetchResource {
    Bitmap fetchImage(Resource resource);

    String fetchText(Resource resource);

    void releaseResource(List<Resource> list);
}
