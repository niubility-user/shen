package cn.com.union.fido.bean.uafclient;

import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class TrustApps {
    List<TrustedFacets> trustedFacets;

    public boolean checkOrigin(String str) {
        Iterator<TrustedFacets> it = this.trustedFacets.iterator();
        boolean z = false;
        while (it.hasNext()) {
            List<String> ids = it.next().getIds();
            if (ids != null && ids.size() > 0 && ids.contains(str)) {
                z = true;
            }
        }
        return z;
    }

    public List<TrustedFacets> getTrustedFacets() {
        return this.trustedFacets;
    }

    public void setTrustedFacets(List<TrustedFacets> list) {
        this.trustedFacets = list;
    }
}
