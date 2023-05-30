package com.jd.viewkit.templates.manager;

import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.model.model.JDViewKitVirtualServiceModel;
import java.util.Map;

/* loaded from: classes18.dex */
public interface JDViewKitVirtualViewManager {
    void dslMap2Virtual(String str, JDViewKitVirtualServiceModel jDViewKitVirtualServiceModel);

    JDViewKitVirtualView dslRoot2Virtaul(String str, JDViewKitVirtualServiceModel jDViewKitVirtualServiceModel);

    Map<String, JDViewKitVirtualView> getDslsMap();

    JDViewKitVirtualView getRootVirtaul(String str);

    void saveRootVirtaul(String str, JDViewKitVirtualView jDViewKitVirtualView);
}
