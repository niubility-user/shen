package com.jd.jdcache.util;

import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001f\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a7\u0010\u0006\u001a\u00028\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\u0010\b\u0001\u0010\u0003*\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0002*\u00028\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lcom/jd/jdcache/util/IUsefulCheck;", "T", "", "R", "", "needUseful", "useful", "(Ljava/util/Collection;Z)Ljava/util/Collection;", "JDCache_release"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public final class IUsefulCheckKt {
    public static final <T extends IUsefulCheck, R extends Collection<T>> R useful(R r, boolean z) {
        if (r != null) {
            Iterator it = r.iterator();
            while (it.hasNext()) {
                if ((z && !((IUsefulCheck) it.next()).useful()) || (!z && ((IUsefulCheck) it.next()).useful())) {
                    it.remove();
                }
            }
        }
        return r;
    }

    public static /* synthetic */ Collection useful$default(Collection collection, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z = true;
        }
        return useful(collection, z);
    }
}
