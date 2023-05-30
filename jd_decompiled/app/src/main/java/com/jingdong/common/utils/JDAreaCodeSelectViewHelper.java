package com.jingdong.common.utils;

import androidx.fragment.app.FragmentActivity;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.frame.JDHandler;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.ui.JDAreaCodeSelectView;
import com.jingdong.common.ui.address.entity.AreaBeanVO;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.platform.lib.utils.HostUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class JDAreaCodeSelectViewHelper extends FragmentActivity {
    private static final String TAG = JDAreaCodeSelectViewHelper.class.getSimpleName();
    private JDAreaCodeSelectView jdAreaCodeSelectView;
    private JDAreaCodeSelectView.OnAreaCodeLoadCompletedListener listener;
    private FragmentActivity mMyActivity;
    private OnAreaCodeListener mOnAreaCodeListener;
    private boolean mIsDestroy = false;
    private JDAreaCodeSelectView.AreaCodeHelper areaCodeHelper = new JDAreaCodeSelectView.AreaCodeHelper() { // from class: com.jingdong.common.utils.JDAreaCodeSelectViewHelper.1
        @Override // com.jingdong.common.ui.JDAreaCodeSelectView.AreaCodeHelper
        public void close() {
            if (JDAreaCodeSelectViewHelper.this.mOnAreaCodeListener != null) {
                JDAreaCodeSelectViewHelper.this.mOnAreaCodeListener.onClose();
            }
        }

        @Override // com.jingdong.common.ui.JDAreaCodeSelectView.AreaCodeHelper
        public void loadAreaCode(JDAreaCodeSelectView.OnAreaCodeLoadCompletedListener onAreaCodeLoadCompletedListener) {
            JDAreaCodeSelectViewHelper.this.listener = onAreaCodeLoadCompletedListener;
            JDAreaCodeSelectViewHelper.this.queryAreaCodeData("action");
        }

        @Override // com.jingdong.common.ui.JDAreaCodeSelectView.AreaCodeHelper
        public void onAreaCodeSelected(AreaBeanVO areaBeanVO) {
            if (JDAreaCodeSelectViewHelper.this.mOnAreaCodeListener != null) {
                JDAreaCodeSelectViewHelper.this.mOnAreaCodeListener.onAreaCodeSelected(areaBeanVO);
            }
        }

        @Override // com.jingdong.common.ui.JDAreaCodeSelectView.AreaCodeHelper
        public void reloadData() {
        }
    };
    private JDHandler mJDHandler = new JDHandler();

    /* loaded from: classes6.dex */
    public interface OnAreaCodeListener {
        void onAreaCodeSelected(AreaBeanVO areaBeanVO);

        void onClose();

        void onLoadAreaCode(boolean z);
    }

    public JDAreaCodeSelectViewHelper(FragmentActivity fragmentActivity) {
        this.mMyActivity = fragmentActivity;
        JDAreaCodeSelectView jDAreaCodeSelectView = new JDAreaCodeSelectView(this.mMyActivity);
        this.jdAreaCodeSelectView = jDAreaCodeSelectView;
        jDAreaCodeSelectView.initDataNew(this.areaCodeHelper);
        this.jdAreaCodeSelectView.hideErrorView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showData(ArrayList<AreaBeanVO> arrayList, ArrayList<AreaBeanVO> arrayList2) {
        if (arrayList != null && arrayList2 != null) {
            JDAreaCodeSelectView.OnAreaCodeLoadCompletedListener onAreaCodeLoadCompletedListener = this.listener;
            if (onAreaCodeLoadCompletedListener != null) {
                onAreaCodeLoadCompletedListener.onAreaCodeLoadCompleted(true, arrayList, arrayList2);
            }
            OnAreaCodeListener onAreaCodeListener = this.mOnAreaCodeListener;
            if (onAreaCodeListener != null) {
                onAreaCodeListener.onLoadAreaCode(true);
                return;
            }
            return;
        }
        OnAreaCodeListener onAreaCodeListener2 = this.mOnAreaCodeListener;
        if (onAreaCodeListener2 != null) {
            onAreaCodeListener2.onLoadAreaCode(false);
        }
        JDAreaCodeSelectView jDAreaCodeSelectView = this.jdAreaCodeSelectView;
        if (jDAreaCodeSelectView != null) {
            jDAreaCodeSelectView.showErrorView();
        }
    }

    public void destroy() {
        this.mIsDestroy = true;
        JDAreaCodeSelectView jDAreaCodeSelectView = this.jdAreaCodeSelectView;
        if (jDAreaCodeSelectView != null) {
            jDAreaCodeSelectView.destroy();
        }
        this.listener = null;
        this.mOnAreaCodeListener = null;
        this.mJDHandler = null;
    }

    public JDAreaCodeSelectView getView() {
        return this.jdAreaCodeSelectView;
    }

    public void queryAreaCodeData(String str) {
        HttpSetting httpSetting = new HttpSetting();
        HttpGroup.OnAllListener onAllListener = new HttpGroup.OnAllListener() { // from class: com.jingdong.common.utils.JDAreaCodeSelectViewHelper.2
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                if (Log.D) {
                    Log.d(JDAreaCodeSelectViewHelper.TAG, "httpsetting-onend");
                }
                if (JDAreaCodeSelectViewHelper.this.mIsDestroy) {
                    return;
                }
                JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                if (fastJsonObject == null) {
                    if (JDAreaCodeSelectViewHelper.this.jdAreaCodeSelectView != null) {
                        JDAreaCodeSelectViewHelper.this.jdAreaCodeSelectView.showErrorView();
                        return;
                    }
                    return;
                }
                try {
                    JDJSONObject jDJSONObject = (JDJSONObject) JDJSON.parse(fastJsonObject.getString("areaInfoResultVO"));
                    if (jDJSONObject == null) {
                        if (JDAreaCodeSelectViewHelper.this.jdAreaCodeSelectView != null) {
                            JDAreaCodeSelectViewHelper.this.jdAreaCodeSelectView.showErrorView();
                            return;
                        }
                        return;
                    }
                    String string = jDJSONObject.getString("areaCodes");
                    String string2 = jDJSONObject.getString("commonAreaCodes");
                    if (Log.D) {
                        Log.d(JDAreaCodeSelectViewHelper.TAG, "areaCodes = " + string);
                        Log.d(JDAreaCodeSelectViewHelper.TAG, "commonAreaCodes = " + string2);
                    }
                    if (string != null && string2 != null) {
                        final List parseArray = JDJSON.parseArray(string, AreaBeanVO.class);
                        final List parseArray2 = JDJSON.parseArray(string2, AreaBeanVO.class);
                        if (Log.D) {
                            Log.d(JDAreaCodeSelectViewHelper.TAG, "areaCodesSize = " + parseArray.size());
                            Log.d(JDAreaCodeSelectViewHelper.TAG, "commonAreaCodesSize = " + parseArray2.size());
                        }
                        JDAreaCodeSelectViewHelper.this.mJDHandler.post(new Runnable() { // from class: com.jingdong.common.utils.JDAreaCodeSelectViewHelper.2.1
                            @Override // java.lang.Runnable
                            public void run() {
                                if (JDAreaCodeSelectViewHelper.this.mIsDestroy) {
                                    return;
                                }
                                JDAreaCodeSelectViewHelper.this.showData(new ArrayList(parseArray), new ArrayList(parseArray2));
                            }
                        });
                        return;
                    }
                    if (JDAreaCodeSelectViewHelper.this.jdAreaCodeSelectView != null) {
                        JDAreaCodeSelectViewHelper.this.jdAreaCodeSelectView.showErrorView();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                if (JDAreaCodeSelectViewHelper.this.mIsDestroy || JDAreaCodeSelectViewHelper.this.mOnAreaCodeListener == null) {
                    return;
                }
                JDAreaCodeSelectViewHelper.this.mOnAreaCodeListener.onLoadAreaCode(false);
                if (JDAreaCodeSelectViewHelper.this.jdAreaCodeSelectView != null) {
                    JDAreaCodeSelectViewHelper.this.jdAreaCodeSelectView.showErrorView();
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        };
        httpSetting.setFunctionId("getAreaCodeList");
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setListener(onAllListener);
        httpSetting.setHost(HostUtils.getOrderHost());
        httpSetting.setNotifyUser(true);
        FragmentActivity fragmentActivity = this.mMyActivity;
        if (fragmentActivity instanceof IMyActivity) {
            HttpGroup httpGroupaAsynPool = ((IMyActivity) fragmentActivity).getHttpGroupaAsynPool();
            if (httpGroupaAsynPool != null) {
                httpGroupaAsynPool.add(httpSetting);
                return;
            }
            return;
        }
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public void setData(String str) {
    }

    public void setOnAreaCodeListener(OnAreaCodeListener onAreaCodeListener) {
        this.mOnAreaCodeListener = onAreaCodeListener;
    }

    public void showAreaCode() {
        JDAreaCodeSelectView jDAreaCodeSelectView = this.jdAreaCodeSelectView;
        if (jDAreaCodeSelectView != null) {
            jDAreaCodeSelectView.showAreaCode();
        }
    }
}
