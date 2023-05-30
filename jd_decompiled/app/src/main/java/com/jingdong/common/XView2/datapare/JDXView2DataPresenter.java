package com.jingdong.common.XView2.datapare;

import android.content.Context;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.XView2.entity.XViewConfigEntity;
import com.jingdong.common.XView2.utils.HttpUtils;
import com.jingdong.common.listui.Observable;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes5.dex */
public class JDXView2DataPresenter {
    private Context mContext;
    private AtomicBoolean mIsFetching = new AtomicBoolean(false);
    private Observable mObservable;

    public JDXView2DataPresenter(Context context) {
        this.mContext = context;
    }

    public void getXViewConfigData(final IXView2DataCallBack iXView2DataCallBack) {
        if (this.mObservable == null) {
            this.mObservable = new Observable().subscribe("error", new Observable.Action<String>() { // from class: com.jingdong.common.XView2.datapare.JDXView2DataPresenter.2
                @Override // com.jingdong.common.listui.Observable.Action
                public void call(String str) {
                    if (iXView2DataCallBack != null) {
                        JDXView2DataPresenter.this.mIsFetching.set(false);
                        iXView2DataCallBack.fail(str);
                    }
                }
            }).subscribe("data", new Observable.Action<XViewConfigEntity>() { // from class: com.jingdong.common.XView2.datapare.JDXView2DataPresenter.1
                @Override // com.jingdong.common.listui.Observable.Action
                public void call(XViewConfigEntity xViewConfigEntity) {
                    if (iXView2DataCallBack != null) {
                        JDXView2DataPresenter.this.mIsFetching.set(false);
                        iXView2DataCallBack.success(xViewConfigEntity);
                    }
                }
            });
        }
        if (this.mIsFetching.get()) {
            return;
        }
        this.mIsFetching.set(true);
        HashMap hashMap = new HashMap();
        hashMap.put("api-version", "1.1.0");
        HttpUtils.getXViewDataWithObservable(this.mContext, XView2Constants.XVIEW2_FUNCTION_CONFIG, hashMap, this.mObservable);
    }
}
