package com.jingdong.common.unification.uniconfig;

import com.jd.lib.un.business.widget.a;
import com.jd.lib.un.utils.UnSharedPreferencesUtils;

/* loaded from: classes6.dex */
public class UnDownLoadFinishImpl implements OnDownloadFinishListener {
    @Override // com.jingdong.common.unification.uniconfig.OnDownloadFinishListener
    public void onEnd(IconConfigModel iconConfigModel) {
        if (DataBaseController.getController().update(iconConfigModel) && DataBaseController.getController().checkDownloadFinish()) {
            UnIconReport.getInstance().endDownload();
            DefaultDataController.getController().setFirstInit(false);
            UnSharedPreferencesUtils.putLong(a.g().d(), UnIconConfigConstants.SHARED_UNI_CONFIG_DOWNLOAD_FINISH_DATA_VERSION, UnIconConfigHelper.getUniConfigDataVersion());
        }
    }

    @Override // com.jingdong.common.unification.uniconfig.OnDownloadFinishListener
    public void onError() {
    }
}
