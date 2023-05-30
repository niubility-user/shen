package com.jd.framework.network.dialingv2;

import com.jd.framework.network.dialingv2.BaseDialingTask;
import com.jd.framework.network.dialingv2.DialingModel;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes13.dex */
public class BuildInIPDialingTask extends BaseDialingTask {
    public BuildInIPDialingTask(String[] strArr, DialingModel.Source source) {
        super(strArr, source);
    }

    public static BuildInIPDialingTask createTask() {
        return new BuildInIPDialingTask(DialingConfig.UNIFORM_BUILD_IN_IP_LIST, DialingModel.Source.SOURCE_FROM_BUILD_IN);
    }

    private DialingModel dialing() {
        List<DialingModel> dialing = DialingMethodHelper.dialing(filter(), 2000);
        if (dialing != null) {
            DialingManager.getInstance().add2AvailableList(dialing);
        }
        return selectModelWithStrategy(dialing);
    }

    @Override // com.jd.framework.network.dialingv2.BaseDialingTask
    public void clear() {
        synchronized (BuildInIPDialingTask.class) {
            try {
                super.clear();
            }
        }
    }

    @Override // com.jd.framework.network.dialingv2.BaseDialingTask
    protected List<DialingModel> filter() {
        ArrayList arrayList = new ArrayList();
        for (DialingModel dialingModel : this.data) {
            if (!DialingManager.getInstance().getDialingIpSet().contains(dialingModel.ipAddress)) {
                DialingManager.getInstance().getDialingIpSet().add(dialingModel.ipAddress);
                arrayList.add(dialingModel);
            }
        }
        return arrayList;
    }

    @Override // com.jd.framework.network.dialingv2.BaseDialingTask
    public DialingModel getCachedModel() {
        DialingModel dialingModel;
        synchronized (BuildInIPDialingTask.class) {
            if (this.status == BaseDialingTask.RunningStatus.INITIAL) {
                startDialing();
            }
            if (this.available != null && DialingManager.getInstance().getFailingSet().contains(this.available.ipAddress)) {
                this.available = null;
            }
            dialingModel = this.available;
        }
        return dialingModel;
    }

    @Override // com.jd.framework.network.dialingv2.BaseDialingTask
    protected DialingModel selectModelWithStrategy(List<DialingModel> list) {
        return DialingMethodHelper.randomSelect(list);
    }

    @Override // com.jd.framework.network.dialingv2.BaseDialingTask
    public void startDialing() {
        synchronized (BuildInIPDialingTask.class) {
            if (this.status != BaseDialingTask.RunningStatus.INITIAL) {
                if (OKLog.D) {
                    OKLog.d("DialingTask", "\u5185\u7f6e\u515c\u5e95VIP\u63a2\u6d4b\u5df2\u7ecf\u5f00\u59cb\uff0c\u65e0\u9700\u91cd\u590d\u63a2\u6d4b");
                }
                return;
            }
            if (OKLog.D) {
                OKLog.d("DialingTask", "\u5185\u7f6e\u515c\u5e95VIP\u63a2\u6d4b\u5f00\u59cb....");
            }
            setStatus(BaseDialingTask.RunningStatus.START);
            long currentTimeMillis = System.currentTimeMillis();
            this.available = dialing();
            if (OKLog.D) {
                OKLog.d("DialingTask", "\u5185\u7f6eIP\u5217\u8868\u63a2\u6d4b\u7ed3\u675f\uff0c\u8017\u65f6 : " + (System.currentTimeMillis() - currentTimeMillis) + "\u6beb\u79d2\uff0c\u62e8\u6d4b\u7ed3\u679c\u4e3a : " + this.available);
            }
            setStatus(BaseDialingTask.RunningStatus.COMPLETED);
        }
    }
}
