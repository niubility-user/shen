package com.facebook.react.views.image;

import android.graphics.Bitmap;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.MultiCacheKey;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.request.Postprocessor;
import com.jd.dynamic.DYConstants;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes12.dex */
public class MultiPostprocessor implements Postprocessor {
    private final List<Postprocessor> mPostprocessors;

    private MultiPostprocessor(List<Postprocessor> list) {
        this.mPostprocessors = new LinkedList(list);
    }

    public static Postprocessor from(List<Postprocessor> list) {
        int size = list.size();
        if (size != 0) {
            if (size != 1) {
                return new MultiPostprocessor(list);
            }
            return list.get(0);
        }
        return null;
    }

    @Override // com.facebook.imagepipeline.request.Postprocessor
    public String getName() {
        StringBuilder sb = new StringBuilder();
        for (Postprocessor postprocessor : this.mPostprocessors) {
            if (sb.length() > 0) {
                sb.append(DYConstants.DY_REGEX_COMMA);
            }
            sb.append(postprocessor.getName());
        }
        sb.insert(0, "MultiPostProcessor (");
        sb.append(")");
        return sb.toString();
    }

    @Override // com.facebook.imagepipeline.request.Postprocessor
    public CacheKey getPostprocessorCacheKey() {
        LinkedList linkedList = new LinkedList();
        Iterator<Postprocessor> it = this.mPostprocessors.iterator();
        while (it.hasNext()) {
            linkedList.push(it.next().getPostprocessorCacheKey());
        }
        return new MultiCacheKey(linkedList);
    }

    @Override // com.facebook.imagepipeline.request.Postprocessor
    public CloseableReference<Bitmap> process(Bitmap bitmap, PlatformBitmapFactory platformBitmapFactory) {
        CloseableReference<Bitmap> closeableReference = null;
        try {
            Iterator<Postprocessor> it = this.mPostprocessors.iterator();
            CloseableReference<Bitmap> closeableReference2 = null;
            while (it.hasNext()) {
                closeableReference = it.next().process(closeableReference2 != null ? closeableReference2.get() : bitmap, platformBitmapFactory);
                CloseableReference.closeSafely(closeableReference2);
                closeableReference2 = closeableReference.mo9clone();
            }
            return closeableReference.mo9clone();
        } finally {
            CloseableReference.closeSafely(closeableReference);
        }
    }
}
