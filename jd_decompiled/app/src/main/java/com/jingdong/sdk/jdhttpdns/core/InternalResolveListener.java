package com.jingdong.sdk.jdhttpdns.core;

import com.jingdong.sdk.jdhttpdns.JDHttpDnsToolkit;
import com.jingdong.sdk.jdhttpdns.config.Constant;
import com.jingdong.sdk.jdhttpdns.listener.IFailureController;
import com.jingdong.sdk.jdhttpdns.listener.IReporter;
import com.jingdong.sdk.jdhttpdns.pojo.FailEvent;
import com.jingdong.sdk.jdhttpdns.pojo.HttpDnsEvent;
import com.jingdong.sdk.jdhttpdns.utils.StatisticTool;
import java.util.HashMap;

/* loaded from: classes7.dex */
public class InternalResolveListener {
    private void saveNetworkStatData() {
        if (StatisticTool.isSendLastResult() && JDHttpDnsToolkit.getInstance().getStatReporter() != null) {
            HashMap<String, Integer> hashMap = new HashMap<>();
            hashMap.put(Constant.KEY_TOTAL_HTTPDNS_REQUEST_COUNT, Integer.valueOf(StatisticTool.getTotalCount()));
            hashMap.put(Constant.KEY_SUCCEED_HTTPDNS_REQUEST_COUNT, Integer.valueOf(StatisticTool.getSuccessCount()));
            hashMap.put(Constant.KEY_SUCCEED_HTTPDNS_DOMAIN_REQUEST_COUNT, Integer.valueOf(StatisticTool.getDomainSuccessCount()));
            JDHttpDnsToolkit.getInstance().getStatReporter().saveNetworkStatistic(hashMap);
        }
    }

    public void onFailure(HttpDnsEvent httpDnsEvent, boolean z) {
        saveNetworkStatData();
        IFailureController controller = JDHttpDnsToolkit.getInstance().getController();
        if (z) {
            controller.onHttpDnsFailure(httpDnsEvent.getException());
        }
        if (controller != null) {
            if (WorkRunnable.getFailureCount() >= (controller.getFailureCountLimit() <= 0 ? 3 : controller.getFailureCountLimit())) {
                controller.reachFailureLimit();
            }
        }
        IReporter reporter = JDHttpDnsToolkit.getInstance().getReporter();
        if (reporter != null) {
            reporter.httpDnsMta(new FailEvent(httpDnsEvent.getUrl(), httpDnsEvent.getException()));
        }
    }

    public void onSuccess(HttpDnsEvent httpDnsEvent) {
        saveNetworkStatData();
    }
}
