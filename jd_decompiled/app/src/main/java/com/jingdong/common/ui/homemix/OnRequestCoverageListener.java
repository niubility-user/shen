package com.jingdong.common.ui.homemix;

import com.jingdong.common.ui.homemix.entity.Coverage;
import java.util.List;

/* loaded from: classes6.dex */
public interface OnRequestCoverageListener {
    void onError();

    void onResult(List<Coverage> list);
}
