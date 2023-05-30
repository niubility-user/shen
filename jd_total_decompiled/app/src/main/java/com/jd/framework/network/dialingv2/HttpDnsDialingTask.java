package com.jd.framework.network.dialingv2;

import android.text.TextUtils;
import com.android.volley.utils.TimeUtils;
import com.jd.framework.network.dialingv2.BaseDialingTask;
import com.jd.framework.network.dialingv2.DialingModel;
import com.jingdong.common.network.IpModel;
import com.jingdong.jdsdk.network.toolbox.GlobalExecutorService;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.conn.util.InetAddressUtils;

/* loaded from: classes13.dex */
public class HttpDnsDialingTask {
    private MasterIPDialingTask masterIPDialingTask = new MasterIPDialingTask();
    private BackupIPDialingTask backupIPDialingTask = new BackupIPDialingTask();

    /* loaded from: classes13.dex */
    public static class BackupIPDialingTask {
        private DialingModel cachedModel;
        private List<DialingModel> data = new ArrayList(2);
        private Object dataLock = new Object();
        protected BaseDialingTask.RunningStatus status;

        private DialingModel selectModelWithStrategy(List<DialingModel> list) {
            return DialingMethodHelper.selectWithHappyEyeball(list, 150);
        }

        public void clear() {
            synchronized (BackupIPDialingTask.class) {
                this.cachedModel = null;
            }
            synchronized (this.dataLock) {
                this.data.clear();
            }
            this.status = BaseDialingTask.RunningStatus.INITIAL;
        }

        public void doDialing() {
            synchronized (this.dataLock) {
                if (this.data.isEmpty()) {
                    if (OKLog.D) {
                        OKLog.d("DialingTask", "HttpDns\u5907\u9009IP\u672a\u83b7\u53d6\u5230\uff0c\u65e0\u6cd5\u8fdb\u884c\u62e8\u6d4b");
                    }
                } else if (this.status != BaseDialingTask.RunningStatus.INITIAL) {
                    if (OKLog.D) {
                        OKLog.d("DialingTask", "BackupIPDialingTask HttpDns\u5907\u9009\u62e8\u6d4b\u5de5\u4f5c\u5df2\u7ecf\u7ed3\u675f");
                    }
                } else {
                    this.status = BaseDialingTask.RunningStatus.START;
                    if (OKLog.D) {
                        OKLog.d("DialingTask", "BackupIPDialingTask \u5907\u9009ip\u5f00\u59cb\u62e8\u6d4b");
                    }
                    long currentTimeMillis = System.currentTimeMillis();
                    List<DialingModel> dialing = DialingMethodHelper.dialing(filter(), 2000);
                    if (dialing != null) {
                        DialingManager.getInstance().add2AvailableList(dialing);
                    }
                    DialingModel selectModelWithStrategy = selectModelWithStrategy(dialing);
                    synchronized (BackupIPDialingTask.class) {
                        this.cachedModel = selectModelWithStrategy;
                        if (OKLog.D) {
                            OKLog.d("DialingTask", "BackupIPDialingTask \u5907\u9009HttpDns\u63a2\u6d4b\u5b8c\u6bd5\uff0c\u8017\u65f6 : " + (System.currentTimeMillis() - currentTimeMillis) + "\u6beb\u79d2, \u62e8\u6d4b\u7ed3\u679c\u4e3a : " + this.cachedModel);
                        }
                    }
                    this.status = BaseDialingTask.RunningStatus.COMPLETED;
                }
            }
        }

        protected List<DialingModel> filter() {
            ArrayList arrayList = new ArrayList();
            synchronized (this.dataLock) {
                for (DialingModel dialingModel : this.data) {
                    if (!DialingManager.getInstance().getDialingIpSet().contains(dialingModel.ipAddress)) {
                        DialingManager.getInstance().getDialingIpSet().add(dialingModel.ipAddress);
                        arrayList.add(dialingModel);
                    }
                }
            }
            return arrayList;
        }

        public DialingModel getCachedModel() {
            DialingModel dialingModel;
            if (this.status == BaseDialingTask.RunningStatus.INITIAL) {
                doDialing();
            }
            synchronized (BackupIPDialingTask.class) {
                if (this.cachedModel != null && DialingManager.getInstance().getFailingSet().contains(this.cachedModel.ipAddress)) {
                    this.cachedModel = null;
                }
                dialingModel = this.cachedModel;
            }
            return dialingModel;
        }

        public void saveBackupIP(IpModel ipModel) {
            if (OKLog.D) {
                OKLog.d("DialingTask", "BackupIPDialingTask saveBackupIP \u7f13\u5b58\u5907\u9009IP\u5730\u5740\u8bb0\u5f55");
            }
            synchronized (this.dataLock) {
                if (!this.data.isEmpty()) {
                    if (OKLog.D) {
                        OKLog.d("DialingTask", "BackupIPDialingTask \u5907\u9009IP\u5730\u5740\u5df2\u7ecf\u5b58\u50a8");
                    }
                    return;
                }
                String[] v4Backup = ipModel.getV4Backup();
                int length = v4Backup != null ? v4Backup.length : 0;
                String[] v6Backup = ipModel.getV6Backup();
                int length2 = v6Backup != null ? v6Backup.length : 0;
                int i2 = length + length2;
                if (i2 > 0) {
                    String[] strArr = new String[i2];
                    if (v4Backup != null && length > 0) {
                        System.arraycopy(v4Backup, 0, strArr, 0, length);
                    }
                    if (v6Backup != null && length2 > 0) {
                        System.arraycopy(v6Backup, 0, strArr, length, length2);
                    }
                    synchronized (this.dataLock) {
                        for (int i3 = 0; i3 < i2; i3++) {
                            String str = strArr[i3];
                            DialingModel dialingModel = new DialingModel();
                            dialingModel.from = DialingModel.Source.SOURCE_FROM_HTTPDNS_BACKUP;
                            dialingModel.ipAddress = str;
                            dialingModel.isIPv6 = InetAddressUtils.isIPv6Address(str);
                            this.data.add(dialingModel);
                        }
                        if (OKLog.D) {
                            OKLog.d("DialingTask", "BackupIPDialingTask \u5907\u9009IP\u5730\u5740\u5b58\u50a8\u6210\u529f\uff0c\u5171\u8ba1 " + this.data.size() + " \u4e2a");
                        }
                    }
                    this.status = BaseDialingTask.RunningStatus.INITIAL;
                }
            }
        }
    }

    /* loaded from: classes13.dex */
    public static class MasterIPDialingTask {
        private DialingModel cachedModel;
        private boolean isTaskRunning = false;

        public void clear() {
            synchronized (MasterIPDialingTask.class) {
                this.cachedModel = null;
            }
        }

        public boolean doDialing(IpModel ipModel) {
            if (ipModel == null || TextUtils.isEmpty(ipModel.getMaster())) {
                return false;
            }
            try {
                if (this.isTaskRunning) {
                    if (OKLog.D) {
                        OKLog.d("DialingTask", String.format("MasterVip %s \u6b63\u5728\u63a2\u6d4b\u6216\u5df2\u7ecf\u5931\u8d25\uff0c \u653e\u5f03\u63a2\u6d4b\u4efb\u52a1", ipModel.getMaster()));
                    }
                    return false;
                }
                this.isTaskRunning = true;
                synchronized (MasterIPDialingTask.class) {
                    DialingModel dialingModel = this.cachedModel;
                    if (dialingModel != null && !dialingModel.isExpire()) {
                        if (TextUtils.equals(ipModel.getMaster(), this.cachedModel.ipAddress)) {
                            if (OKLog.D) {
                                OKLog.d("DialingTask", String.format("MasterVip %s \u5df2\u7ecf\u5b58\u5728\uff0c\u653e\u5f03\u63a2\u6d4b", ipModel.getMaster()));
                            }
                            return false;
                        }
                    } else {
                        this.cachedModel = null;
                    }
                    DialingManager.getInstance().getDialingIpSet().add(ipModel.getMaster());
                    DialingModel dialingModel2 = new DialingModel();
                    dialingModel2.from = DialingModel.Source.SOURCE_FROM_HTTPDNS_MASTER;
                    String master = ipModel.getMaster();
                    dialingModel2.ipAddress = master;
                    if (!TextUtils.isEmpty(master) && dialingModel2.ipAddress.startsWith("[") && dialingModel2.ipAddress.endsWith("]")) {
                        dialingModel2.ipAddress.substring(1, r4.length() - 2);
                    }
                    dialingModel2.isIPv6 = InetAddressUtils.isIPv6Address(dialingModel2.ipAddress);
                    dialingModel2.updateTime = TimeUtils.getCurrentTime();
                    dialingModel2.ttl = ipModel.ttl;
                    if (OKLog.D) {
                        OKLog.d("DialingTask", "\u5f00\u59cb\u5bf9MasterVip\u8fdb\u884c\u63a2\u6d4b masterVip -> " + dialingModel2.ipAddress);
                    }
                    long currentTimeMillis = System.currentTimeMillis();
                    DialingModel singleDialing = DialingMethodHelper.singleDialing(dialingModel2, 2000);
                    if (singleDialing == null && OKLog.D) {
                        OKLog.d("DialingTask", "\u63a2\u6d4b\u5230masterVip\u4e0d\u53ef\u7528 " + dialingModel2.ipAddress);
                    }
                    synchronized (MasterIPDialingTask.class) {
                        this.cachedModel = singleDialing;
                        if (OKLog.D) {
                            OKLog.d("DialingTask", "HttpDns MasterVip\u63a2\u6d4b\u7ed3\u675f, \u8017\u65f6 : " + (System.currentTimeMillis() - currentTimeMillis) + "\u6beb\u79d2, \u62e8\u6d4b\u7ed3\u679c\u4e3a " + this.cachedModel);
                        }
                    }
                    return true;
                }
            } finally {
                this.isTaskRunning = false;
            }
        }

        public DialingModel getCachedModel() {
            DialingModel dialingModel;
            synchronized (MasterIPDialingTask.class) {
                if (this.cachedModel != null) {
                    if (DialingManager.getInstance().getFailingSet().contains(this.cachedModel.ipAddress)) {
                        this.cachedModel = null;
                    }
                    DialingModel dialingModel2 = this.cachedModel;
                    if (dialingModel2 != null && dialingModel2.isExpire()) {
                        this.cachedModel = null;
                    }
                }
                dialingModel = this.cachedModel;
            }
            return dialingModel;
        }
    }

    public static HttpDnsDialingTask createTask() {
        return new HttpDnsDialingTask();
    }

    public void clear() {
        getMasterIPDialingTask().clear();
        getBackupIPDialingTask().clear();
    }

    public BackupIPDialingTask getBackupIPDialingTask() {
        return this.backupIPDialingTask;
    }

    public MasterIPDialingTask getMasterIPDialingTask() {
        return this.masterIPDialingTask;
    }

    public void onHttpDnsReceived(final IpModel ipModel) {
        if (ipModel == null) {
            return;
        }
        if (OKLog.D) {
            OKLog.d("DialingTask", "HttpDnsIPDialingTask \u63a5\u6536\u5230\u65b0\u7684HttpDns\u7684\u8bf7\u6c42\u7ed3\u679c");
        }
        GlobalExecutorService.lightExecutorService().execute(new Runnable() { // from class: com.jd.framework.network.dialingv2.HttpDnsDialingTask.1
            {
                HttpDnsDialingTask.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                HttpDnsDialingTask.this.backupIPDialingTask.saveBackupIP(ipModel);
                if (HttpDnsDialingTask.this.masterIPDialingTask.doDialing(ipModel) && HttpDnsDialingTask.this.masterIPDialingTask.getCachedModel() == null) {
                    if (OKLog.D) {
                        OKLog.d("DialingTask", "HttpDns MasterVip\u62e8\u6d4b\u5230\u4e0d\u53ef\u7528\uff0c\u5373\u5c06\u8fdb\u884c\u5907\u9009IP\u62e8\u6d4b");
                    }
                    HttpDnsDialingTask.this.backupIPDialingTask.doDialing();
                }
            }
        });
    }
}
