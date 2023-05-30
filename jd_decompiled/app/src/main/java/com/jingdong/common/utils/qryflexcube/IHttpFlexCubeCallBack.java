package com.jingdong.common.utils.qryflexcube;

import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import java.util.List;

/* loaded from: classes6.dex */
public interface IHttpFlexCubeCallBack {
    void onEnd(List<JDJSONObject> list);

    void onError(HttpError httpError);
}
