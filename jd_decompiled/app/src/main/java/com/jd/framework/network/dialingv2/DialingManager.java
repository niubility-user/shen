package com.jd.framework.network.dialingv2;

import android.text.TextUtils;
import com.jd.framework.network.dialing.ConnectivityChangeObserver;
import com.jd.framework.network.dialing.NetworkExceptionFilter;
import com.jd.framework.network.dialingv2.BaseDialingTask;
import com.jingdong.common.network.IpModel;
import com.jingdong.jdsdk.network.toolbox.GlobalExecutorService;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes13.dex */
public class DialingManager implements ConnectivityChangeObserver.Event {
    public static final String TAG = "DialingTask";
    private List<DialingModel> mAvailableModel;
    private BuildInIPDialingTask mBuildInDialingTask;
    private Set<String> mDialingIPSet;
    private Set<String> mFailingSet;
    private HttpDnsDialingTask mHttpDnsDialingTask;
    private BaseDialingTask.EventListener mLocalDnsDialListener;
    private LocalDnsDialingTask mLocalDnsDialingTask;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class SingletonHolder {
        public static DialingManager instance = new DialingManager();

        private SingletonHolder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doBuildInDialing() {
        GlobalExecutorService.lightExecutorService().execute(this.mBuildInDialingTask);
    }

    private void doLocalDnsDialing() {
        this.mLocalDnsDialingTask.setEventListener(this.mLocalDnsDialListener);
        GlobalExecutorService.lightExecutorService().execute(this.mLocalDnsDialingTask);
    }

    private DialingModel findIpFromAvailableList() {
        int i2 = 0;
        while (true) {
            if (i2 >= this.mAvailableModel.size()) {
                i2 = -1;
                break;
            } else if (!this.mFailingSet.contains(this.mAvailableModel.get(i2))) {
                break;
            } else {
                i2++;
            }
        }
        if (i2 > 0) {
            return this.mAvailableModel.get(i2);
        }
        return null;
    }

    public static DialingManager getInstance() {
        return SingletonHolder.instance;
    }

    public void add2AvailableList(List<DialingModel> list) {
        this.mAvailableModel.addAll(list);
    }

    public void addFailedIP(String str, Exception exc) {
        if (TextUtils.isEmpty(str) || exc == null) {
            return;
        }
        if (OKLog.D) {
            OKLog.d("DialingTask", "\u68c0\u6d4b\u5230ip\u5730\u5740 " + str + " \u53d1\u751f\u5f02\u5e38 " + exc);
        }
        if (!this.mFailingSet.contains(str) && NetworkExceptionFilter.filter(exc)) {
            if (OKLog.D) {
                OKLog.d("DialingTask", "\u5c5e\u4e8e\u7279\u5b9a\u7684\u5f02\u5e38\u7c7b\u578b\uff0c\u5c06\u6545\u969cip " + str + " \u52a0\u5165\u5230\u9ed1\u540d\u5355\u4e2d");
            }
            this.mFailingSet.add(str);
        }
        if (this.mFailingSet.contains(str) || DialingMethodHelper.isHealthIp(str)) {
            return;
        }
        if (OKLog.D) {
            OKLog.d("DialingTask", "\u4e0d\u5c5e\u4e8e\u7279\u5b9a\u7684\u5f02\u5e38\u7c7b\u578b\uff0c\u5bf9 " + str + " \u8fdb\u884c\u63a2\u6d4b\u540e\u4e0d\u53ef\u7528\uff0c\u5c06\u5176\u52a0\u5165\u5230\u9ed1\u540d\u5355\u4e2d");
        }
        this.mFailingSet.add(str);
    }

    public void clear() {
        try {
            this.mLocalDnsDialingTask.clear();
            this.mHttpDnsDialingTask.clear();
            this.mBuildInDialingTask.clear();
            this.mFailingSet.clear();
            this.mDialingIPSet.clear();
            this.mAvailableModel.clear();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public synchronized DialingModel getAvailableIP() {
        DialingModel cachedModel;
        cachedModel = this.mHttpDnsDialingTask.getMasterIPDialingTask().getCachedModel();
        if (cachedModel == null) {
            cachedModel = this.mLocalDnsDialingTask.getCachedModel();
        }
        if (cachedModel == null) {
            cachedModel = this.mHttpDnsDialingTask.getBackupIPDialingTask().getCachedModel();
        }
        if (cachedModel == null) {
            cachedModel = this.mBuildInDialingTask.getCachedModel();
        }
        if (cachedModel == null) {
            cachedModel = findIpFromAvailableList();
        }
        if (OKLog.D) {
            OKLog.d("DialingTask", "DialingManager \u83b7\u53d6\u53ef\u7528IP\u5730\u5740: " + cachedModel);
        }
        return cachedModel;
    }

    public Set<String> getDialingIpSet() {
        return this.mDialingIPSet;
    }

    public Set<String> getFailingSet() {
        return this.mFailingSet;
    }

    public void initialize() {
        this.mBuildInDialingTask = BuildInIPDialingTask.createTask();
        this.mLocalDnsDialingTask = LocalDnsDialingTask.createTask();
        this.mHttpDnsDialingTask = HttpDnsDialingTask.createTask();
        doLocalDnsDialing();
    }

    public void onHttpDnsReceived(IpModel ipModel) {
        this.mHttpDnsDialingTask.onHttpDnsReceived(ipModel);
    }

    @Override // com.jd.framework.network.dialing.ConnectivityChangeObserver.Event
    public void onNetworkChange() {
        if (OKLog.D) {
            OKLog.d("DialingTask", "\u68c0\u6d4b\u5230\u7f51\u7edc\u53d1\u751f\u53d8\u5316\uff0c\u6e05\u7a7a\u4e0a\u6b21\u63a2\u6d4b\u8bb0\u5f55\uff0c\u5373\u5c06\u91cd\u65b0\u63a2\u6d4b");
        }
        clear();
        if (NetUtils.isNetworkAvailable()) {
            doLocalDnsDialing();
        }
    }

    private DialingManager() {
        this.mFailingSet = new HashSet();
        this.mDialingIPSet = new HashSet();
        this.mAvailableModel = new ArrayList();
        this.mLocalDnsDialListener = new BaseDialingTask.EventListener() { // from class: com.jd.framework.network.dialingv2.DialingManager.1
            @Override // com.jd.framework.network.dialingv2.BaseDialingTask.EventListener
            public void onComplete() {
                if (DialingManager.this.mLocalDnsDialingTask.getCachedModel() != null) {
                    if (OKLog.D) {
                        OKLog.d("DialingTask", "LocalDns\u62e8\u6d4b\u5b8c\u6210");
                        return;
                    }
                    return;
                }
                if (OKLog.D) {
                    OKLog.d("DialingTask", "LocalDns\u62e8\u6d4b\u5b8c\u6210\uff0c\u65e0\u53ef\u7528IP\u5730\u5740\uff0c\u8fdb\u884c\u5907\u9009IP\u63a2\u6d4b");
                }
                DialingManager.this.doBuildInDialing();
            }

            @Override // com.jd.framework.network.dialingv2.BaseDialingTask.EventListener
            public void onInitial() {
            }

            @Override // com.jd.framework.network.dialingv2.BaseDialingTask.EventListener
            public void onStart() {
                if (OKLog.D) {
                    OKLog.d("DialingTask", "LocalDns\u5f00\u59cb\u62e8\u6d4b");
                }
            }
        };
    }
}
