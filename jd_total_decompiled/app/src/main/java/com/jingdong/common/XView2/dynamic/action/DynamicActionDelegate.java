package com.jingdong.common.XView2.dynamic.action;

import android.content.Context;
import android.view.View;
import android.widget.Toast;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.XView2.utils.log.XViewLogPrint;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class DynamicActionDelegate implements IDynamicActionDelegate {
    private Context mContext;

    /* loaded from: classes5.dex */
    public interface IDynamicDataCallBack<T> {
        void fail(String str);

        void success(T t);
    }

    public DynamicActionDelegate(Context context) {
        this.mContext = context;
    }

    @Override // com.jingdong.common.XView2.dynamic.action.IDynamicActionDelegate
    public void requestFunc(String str, JSONObject jSONObject, JSONObject jSONObject2) {
    }

    @Override // com.jingdong.common.XView2.dynamic.action.IDynamicActionDelegate
    public void requestUrl(String str, JDJSONObject jDJSONObject, JDJSONObject jDJSONObject2) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFinalUrl(str);
        httpSetting.setUrl(str);
        httpSetting.setPost(false);
        httpSetting.setEffect(0);
        httpSetting.setLocalFileCache(true);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.XView2.dynamic.action.DynamicActionDelegate.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                if (httpResponse.getFastJsonObject() == null) {
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                XViewLogPrint.JDXLog.e(XView2Constants.TAG, "\u83b7\u53d6json\u6587\u4ef6\u63a5\u53e3 onError");
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    @Override // com.jingdong.common.XView2.dynamic.action.IDynamicActionDelegate
    public void showDialogTitle(String str, String str2, String str3, String str4, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
    }

    @Override // com.jingdong.common.XView2.dynamic.action.IDynamicActionDelegate
    public void showToastTitle(Context context, String str, String str2, int i2) {
        Toast.makeText(context, str, 1).show();
    }
}
