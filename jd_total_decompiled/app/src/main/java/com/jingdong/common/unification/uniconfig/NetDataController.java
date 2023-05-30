package com.jingdong.common.unification.uniconfig;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.lib.un.business.widget.a;
import com.jd.lib.un.utils.UnSharedPreferencesUtils;
import com.jingdong.common.UnLog;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.unification.navigationbar.db.NavigationDbConstants;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.network.toolbox.FileGuider;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpRequest;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class NetDataController {
    private static final String SP_BETA_HOST = "unicon_beta_host";
    private static final String TAG = "NetDataController";
    private static NetDataController controller;
    private List<HttpRequest> downloadRequests;
    private Object syncObject = new Object();

    private NetDataController() {
    }

    public static synchronized NetDataController getController() {
        NetDataController netDataController;
        synchronized (NetDataController.class) {
            NetDataController netDataController2 = controller;
            if (netDataController2 != null) {
                return netDataController2;
            }
            synchronized (NetDataController.class) {
                if (controller == null) {
                    controller = new NetDataController();
                }
                netDataController = controller;
            }
            return netDataController;
        }
    }

    private void optionBeta(String str) {
        if (Log.D) {
            String string = UnSharedPreferencesUtils.getString(a.g().d(), SP_BETA_HOST, "");
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(string) || TextUtils.equals(str, string)) {
                return;
            }
            if (!str.startsWith("beta")) {
                str.endsWith("care");
            }
            DataBaseController.getController().deleteAllData("uni_icon_config");
            SharedPreferencesUtil.putLong(UnIconConfigConstants.SHARED_UNI_CONFIG_DOWNLOAD_FINISH_DATA_VERSION, 0L);
            SharedPreferencesUtil.putLong(UnIconConfigConstants.SHARED_UNI_CONFIG_DATA_VERSION, 0L);
            UnSharedPreferencesUtils.putString(a.g().d(), SP_BETA_HOST, str);
        }
    }

    public HttpRequest downLoadIcon(final IconConfigModel iconConfigModel, final OnDownloadFinishListener onDownloadFinishListener) {
        if (iconConfigModel == null || TextUtils.isEmpty(iconConfigModel.id) || TextUtils.isEmpty(iconConfigModel.url)) {
            return null;
        }
        FileGuider fileGuider = new FileGuider();
        fileGuider.setSpace(1);
        fileGuider.setChildDirName(UnIconConfigConstants.ICON_DIR);
        fileGuider.setImmutable(false);
        fileGuider.setFileName(iconConfigModel.id + ".png");
        fileGuider.setMode(1);
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUrl(iconConfigModel.url);
        httpSetting.setCacheMode(2);
        httpSetting.setType(500);
        httpSetting.setSavePath(fileGuider);
        httpSetting.setBreakpointTransmission(false);
        httpSetting.setAttempts(1);
        httpSetting.setReferer("Download_unitIcon");
        httpSetting.setListener(new HttpGroup.OnAllAndPauseListener() { // from class: com.jingdong.common.unification.uniconfig.NetDataController.2
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                if (httpResponse != null) {
                    iconConfigModel.path = httpResponse.getSaveFile().getAbsolutePath();
                    UnLog.d("iconConfig", iconConfigModel.url);
                }
                OnDownloadFinishListener onDownloadFinishListener2 = onDownloadFinishListener;
                if (onDownloadFinishListener2 != null) {
                    onDownloadFinishListener2.onEnd(iconConfigModel);
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                onDownloadFinishListener.onError();
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnPauseListener
            public void onPause() {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
        return HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public void multiDownload(List<IconConfigModel> list) {
        synchronized (this.syncObject) {
            if (list != null) {
                if (list.size() > 0) {
                    UnIconReport.getInstance().startDownload(list.size());
                    List<HttpRequest> list2 = this.downloadRequests;
                    if (list2 != null && list2.size() > 0) {
                        stopDownloads();
                        this.downloadRequests.clear();
                    } else {
                        this.downloadRequests = new ArrayList();
                    }
                    UnDownLoadFinishImpl unDownLoadFinishImpl = new UnDownLoadFinishImpl();
                    Iterator<IconConfigModel> it = list.iterator();
                    while (it.hasNext()) {
                        this.downloadRequests.add(downLoadIcon(it.next(), unDownLoadFinishImpl));
                    }
                }
            }
        }
    }

    public void requestToJson(final OnUnIconResponseListener onUnIconResponseListener) {
        if (onUnIconResponseListener == null) {
            return;
        }
        final long uniConfigDataVersion = UnIconConfigHelper.getUniConfigDataVersion();
        HttpSetting httpSetting = new HttpSetting();
        if (TextUtils.isEmpty(a.g().m())) {
            if (!TextUtils.isEmpty(a.g().l())) {
                httpSetting.setFunctionId(a.g().l());
            } else {
                httpSetting.setFunctionId("widget");
            }
            if (!TextUtils.isEmpty(a.g().k())) {
                httpSetting.setHost(a.g().k());
            }
        } else {
            httpSetting.setUrl(a.g().m());
        }
        if (!TextUtils.isEmpty(a.g().a())) {
            httpSetting.putJsonParam("appid", a.g().a());
        }
        httpSetting.setCacheMode(2);
        httpSetting.setAttempts(2);
        httpSetting.putJsonParam(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_DATAVERSION, String.valueOf(uniConfigDataVersion));
        httpSetting.putJsonParam("tsExpIds", UnIconConfigController.getController().getTestIds());
        httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.unification.uniconfig.NetDataController.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                IconConfigJsonModel iconConfigJsonModel;
                try {
                    iconConfigJsonModel = (IconConfigJsonModel) JDJSON.parseObject(httpResponse.getString(), IconConfigJsonModel.class);
                } catch (Exception e2) {
                    UnLog.e(NetDataController.TAG, e2.getMessage());
                    iconConfigJsonModel = null;
                }
                if (iconConfigJsonModel != null) {
                    onUnIconResponseListener.onEnd(iconConfigJsonModel, iconConfigJsonModel.dataVersion);
                } else {
                    onUnIconResponseListener.onEnd(null, uniConfigDataVersion);
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
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

    public synchronized void stopDownloads() {
        for (HttpRequest httpRequest : this.downloadRequests) {
            if (!httpRequest.isStop()) {
                httpRequest.stop();
            }
        }
    }
}
