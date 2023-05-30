package com.jingdong.manto.pkg.util;

import com.jingdong.manto.pkg.db.entity.PkgCollectEntity;
import java.util.Comparator;

/* loaded from: classes16.dex */
public class PkgRecentCompartor implements Comparator<PkgCollectEntity> {
    @Override // java.util.Comparator
    public int compare(PkgCollectEntity pkgCollectEntity, PkgCollectEntity pkgCollectEntity2) {
        if (pkgCollectEntity.favorite) {
            if (!pkgCollectEntity2.favorite) {
                return -1;
            }
        } else if (pkgCollectEntity2.favorite) {
            return 1;
        }
        return (int) (pkgCollectEntity2.lastOpenTime - pkgCollectEntity.lastOpenTime);
    }
}
