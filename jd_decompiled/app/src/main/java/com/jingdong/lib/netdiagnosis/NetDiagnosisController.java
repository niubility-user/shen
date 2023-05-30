package com.jingdong.lib.netdiagnosis;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.runTimeConfig.ConfigUtil;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.common.utils.StringUtil;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpGroupSetting;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.network.utils.JDDnsUtil;
import com.jingdong.jdsdk.utils.FormatUtils;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.sdk.jdhttpdns.JDHttpDnsToolkit;
import com.jingdong.sdk.jdhttpdns.pojo.IpModel;
import com.jingdong.sdk.oklog.OKLog;
import f.f;
import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class NetDiagnosisController {
    public static final int AUTO_DIAGNOSE_TIMES = 7;
    public static final int AVG_TIME_MAX = 500;
    public static final int CONNECT_TIMES = 4;
    public static final String FUNCTIONID = "networkLog";
    public static final int NET_DIAGNOSE_TYPE_AUTO = 1;
    public static final int NET_DIAGNOSE_TYPE_DEFAULT = 0;
    public static final int NET_REQUEST_FAIL_MAX_TIMES = 5;
    public static final int SAVE_DATA_SIZE = 5;
    public static final String SAVE_FILE = "net_diagnosis_save.txt";
    public static final String SHARED_AUTO_DIAGNOSE_DATE = "shared_auto_diagnose_date";
    public static final String SHARED_AUTO_DIAGNOSE_TIMES = "shared_auto_diagnose_times";
    public static final String SHARED_NET_CONFIG = "pingMonitor";
    public static final String SHARED_NET_REQUEST_FAIL_TIMES = "shared_net_request_fail_times";
    public static final String SHARED_SEARCH_CONFIG = "searchEntry";
    public static final String START_NET_DIAGNOSE_PASSWORD = "@wlzd";
    public static final String TAG = "NetDiagnosis";
    private static NetDiagnosisController controller;
    private static final String[] otherUrls = {"www.baidu.com", "www.taobao.com", "www.tmall.com"};
    private Handler handler;
    private f.f mainTask;
    private f.C0827f mainTcs;
    private String netType;
    private int progress;
    private StringBuilder report;
    public File saveFile;
    private f.f startAnimTask;
    private f.C0827f startAnimTcs;
    private long taskStartTime;
    private String[] jdUrls = {"www.jd.com", Configuration.PERSONAL_CONFIG_HOST, "pay.m.jd.com", "cdngw.m.jd.com", "h5.m.jd.com", "m.360buyimg.com", "api.m.jd.com"};
    private boolean startAnimationFlag = true;
    Message msg = null;
    boolean isWifiPortal = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements Callable<Object> {
        a() {
            NetDiagnosisController.this = r1;
        }

        @Override // java.util.concurrent.Callable
        public Object call() throws Exception {
            for (int i2 = 1; i2 <= 15 && NetDiagnosisController.this.startAnimationFlag && !NetDiagnosisController.this.mainTask.r(); i2++) {
                if (NetDiagnosisController.this.startAnimTask.r()) {
                    NetDiagnosisController.this.sendCancel();
                    return null;
                }
                Thread.sleep(300L);
                NetDiagnosisController.this.progress = i2;
                NetDiagnosisController.this.sendProgress();
            }
            return null;
        }
    }

    /* loaded from: classes14.dex */
    public class b implements f.d<Map<String, List<PingResultEntity>>, JSONObject> {
        b() {
            NetDiagnosisController.this = r1;
        }

        @Override // f.d
        /* renamed from: a */
        public JSONObject then(f.f<Map<String, List<PingResultEntity>>> fVar) throws Exception {
            if (OKLog.D) {
                OKLog.d("net-re", "task 4 " + NetDiagnosisController.this.isWifiPortal + LangUtils.SINGLE_SPACE + NetDiagnosisController.this.mainTask.r());
            }
            NetDiagnosisController netDiagnosisController = NetDiagnosisController.this;
            netDiagnosisController.msg = netDiagnosisController.handler.obtainMessage();
            NetDiagnosisController netDiagnosisController2 = NetDiagnosisController.this;
            netDiagnosisController2.msg.what = 3;
            if (!netDiagnosisController2.mainTask.r() && fVar.p() != null) {
                NetDiagnosisController.this.msg.arg1 = 1;
                ReportNetLogEntity reportNetLogEntity = new ReportNetLogEntity();
                reportNetLogEntity.mode = "1";
                List<PingResultEntity> list = fVar.p().get("jdUrl");
                List<PingResultEntity> list2 = fVar.p().get("otherUrl");
                list.addAll(list2);
                reportNetLogEntity.ping = list;
                new ArrayList().add(reportNetLogEntity);
                NetDiagnosisController netDiagnosisController3 = NetDiagnosisController.this;
                netDiagnosisController3.msg.arg2 = netDiagnosisController3.createReport(list, list2);
            } else {
                NetDiagnosisController.this.msg.arg1 = 0;
            }
            NetDiagnosisController netDiagnosisController4 = NetDiagnosisController.this;
            if (netDiagnosisController4.isWifiPortal) {
                return null;
            }
            netDiagnosisController4.handler.sendMessage(NetDiagnosisController.this.msg);
            return null;
        }
    }

    /* loaded from: classes14.dex */
    public class c implements f.d<List<PingResultEntity>, Map<String, List<PingResultEntity>>> {
        c() {
            NetDiagnosisController.this = r1;
        }

        @Override // f.d
        /* renamed from: a */
        public Map<String, List<PingResultEntity>> then(f.f<List<PingResultEntity>> fVar) throws Exception {
            if (OKLog.D) {
                OKLog.d("net-re", "task 3");
            }
            if (NetDiagnosisController.this.mainTask.r()) {
                return null;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("otherUrl", fVar.p());
            NetDiagnosisController netDiagnosisController = NetDiagnosisController.this;
            hashMap.put("jdUrl", netDiagnosisController.connectList(netDiagnosisController.jdUrls, 4, NetDiagnosisController.this.mainTask, NetDiagnosisController.this.handler));
            return hashMap;
        }
    }

    /* loaded from: classes14.dex */
    public class d implements f.d<Boolean, List<PingResultEntity>> {
        d() {
            NetDiagnosisController.this = r1;
        }

        @Override // f.d
        /* renamed from: a */
        public List<PingResultEntity> then(f.f<Boolean> fVar) throws Exception {
            if (OKLog.D) {
                OKLog.d("net-re", "task 2");
            }
            NetDiagnosisController.this.startAnimationFlag = false;
            if (NetDiagnosisController.this.mainTask.r()) {
                NetDiagnosisController.this.isWifiPortal = false;
                return null;
            }
            NetDiagnosisController.this.isWifiPortal = fVar.p().booleanValue();
            NetDiagnosisController netDiagnosisController = NetDiagnosisController.this;
            if (!netDiagnosisController.isWifiPortal || netDiagnosisController.mainTask.r()) {
                return NetDiagnosisController.this.connectList(NetDiagnosisController.otherUrls, 4, NetDiagnosisController.this.mainTask, NetDiagnosisController.this.handler);
            }
            NetDiagnosisController netDiagnosisController2 = NetDiagnosisController.this;
            netDiagnosisController2.msg = netDiagnosisController2.handler.obtainMessage(3);
            NetDiagnosisController netDiagnosisController3 = NetDiagnosisController.this;
            netDiagnosisController3.msg.arg1 = 3;
            netDiagnosisController3.handler.sendMessage(NetDiagnosisController.this.msg);
            NetDiagnosisController.this.mainTcs.b();
            return null;
        }
    }

    /* loaded from: classes14.dex */
    public class e implements Callable<Boolean> {
        e() {
            NetDiagnosisController.this = r1;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Boolean call() throws Exception {
            if (NetUtils.isWifi()) {
                return Boolean.valueOf(NetDiagnosisController.this.isWifiSetPortal());
            }
            return Boolean.FALSE;
        }
    }

    /* loaded from: classes14.dex */
    public class f implements f.d<List<PingResultEntity>, JSONObject> {
        f(NetDiagnosisController netDiagnosisController) {
        }

        @Override // f.d
        /* renamed from: a */
        public JSONObject then(f.f<List<PingResultEntity>> fVar) throws Exception {
            if (fVar.p() == null) {
                return null;
            }
            ReportNetLogEntity reportNetLogEntity = new ReportNetLogEntity();
            reportNetLogEntity.mode = "0";
            reportNetLogEntity.ping = fVar.p();
            new ArrayList().add(reportNetLogEntity);
            return null;
        }
    }

    /* loaded from: classes14.dex */
    public class g implements Callable<List<PingResultEntity>> {
        g() {
            NetDiagnosisController.this = r1;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public List<PingResultEntity> call() throws Exception {
            if (NetUtils.isWifi() && NetDiagnosisController.this.isWifiSetPortal()) {
                return null;
            }
            NetDiagnosisController.this.taskStartTime = System.currentTimeMillis();
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(NetDiagnosisController.this.connectList(NetDiagnosisController.otherUrls, 4, null, null));
            NetDiagnosisController netDiagnosisController = NetDiagnosisController.this;
            arrayList.addAll(netDiagnosisController.connectList(netDiagnosisController.jdUrls, 4, null, null));
            return arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class h implements Callable<Object> {

        /* loaded from: classes14.dex */
        class a implements HttpGroup.OnAllListener {
            a() {
                h.this = r1;
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                File file = NetDiagnosisController.this.saveFile;
                if (file == null || !file.exists()) {
                    return;
                }
                NetDiagnosisController.this.saveFile.delete();
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
        }

        h() {
            NetDiagnosisController.this = r1;
        }

        /* JADX WARN: Removed duplicated region for block: B:180:0x00c7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // java.util.concurrent.Callable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.Object call() throws java.lang.Exception {
            /*
                Method dump skipped, instructions count: 219
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.lib.netdiagnosis.NetDiagnosisController.h.call():java.lang.Object");
        }
    }

    /* loaded from: classes14.dex */
    class i implements HttpGroup.OnAllListener {

        /* renamed from: g */
        final /* synthetic */ List f12985g;

        i(List list) {
            NetDiagnosisController.this = r1;
            this.f12985g = list;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            if (OKLog.D) {
                OKLog.d(NetDiagnosisController.TAG, "end" + httpResponse.getString());
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            if (OKLog.D) {
                OKLog.d(NetDiagnosisController.TAG, "error-->" + httpError.toString());
            }
            Iterator it = this.f12985g.iterator();
            while (it.hasNext()) {
                NetDiagnosisController.this.saveReport2File(JDJSON.toJSONString((ReportNetLogEntity) it.next()));
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
        public void onProgress(int i2, int i3) {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
        public void onStart() {
            if (OKLog.D) {
                OKLog.d(NetDiagnosisController.TAG, "start");
            }
        }
    }

    /* loaded from: classes14.dex */
    public static class j {
        public String a;
        public String b;

        /* renamed from: c */
        public boolean f12987c;
        public long d;

        j() {
        }
    }

    private NetDiagnosisController() {
    }

    private void addToMap(Map<String, List<j>> map, j jVar) {
        String str = jVar.a + ":" + jVar.b;
        List<j> list = map.get(str);
        if (list == null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(jVar);
            map.put(str, arrayList);
            return;
        }
        list.add(jVar);
    }

    private j connect(String str, int i2) {
        long j2;
        if (i2 == 0) {
            i2 = 80;
        }
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e2) {
            OKLog.e(TAG, e2);
        }
        j jVar = new j();
        jVar.a = str;
        Socket socket = null;
        long j3 = 0;
        try {
            try {
                try {
                    j2 = System.currentTimeMillis();
                    try {
                        Socket socket2 = new Socket();
                        try {
                            socket2.connect(new InetSocketAddress(str, i2), 1000);
                            socket2.sendUrgentData(255);
                            jVar.b = socket2.getInetAddress().getHostAddress();
                            j3 = System.currentTimeMillis();
                            if (!socket2.isClosed()) {
                                socket2.shutdownInput();
                            }
                            socket2.shutdownOutput();
                            socket2.close();
                        } catch (IOException e3) {
                            e = e3;
                            socket = socket2;
                            jVar.b = getIpWithStack(e.getMessage());
                            if (OKLog.D) {
                                OKLog.d(TAG, "---ex--ip--->" + jVar.b);
                            }
                            OKLog.e(TAG, e);
                            if (socket != null && !socket.isClosed()) {
                                socket.shutdownInput();
                            }
                            socket.shutdownOutput();
                            socket.close();
                            jVar.d = j3 - j2;
                            return jVar;
                        } catch (Throwable th) {
                            th = th;
                            socket = socket2;
                            if (socket != null) {
                                try {
                                    if (!socket.isClosed()) {
                                        socket.shutdownInput();
                                    }
                                } catch (IOException e4) {
                                    OKLog.e(TAG, e4);
                                    throw th;
                                }
                            }
                            socket.shutdownOutput();
                            socket.close();
                            throw th;
                        }
                    } catch (IOException e5) {
                        e = e5;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (IOException e6) {
                e = e6;
                j2 = 0;
            }
        } catch (IOException e7) {
            OKLog.e(TAG, e7);
        }
        jVar.d = j3 - j2;
        return jVar;
    }

    public List<PingResultEntity> connectList(String[] strArr, int i2, f.f fVar, Handler handler) {
        j connect;
        List<PingResultEntity> list = null;
        if (fVar != null && fVar.r()) {
            return null;
        }
        if (strArr == null || strArr.length <= 0) {
            return null;
        }
        if (OKLog.D) {
            OKLog.d("net-result", "connectList start");
        }
        HashMap hashMap = new HashMap();
        int i3 = 0;
        while (true) {
            long j2 = -1;
            if (i3 < i2) {
                int length = strArr.length;
                int i4 = 0;
                while (i4 < length) {
                    String str = strArr[i4];
                    if (OKLog.D) {
                        OKLog.d("net-result", "connectList start for " + str);
                    }
                    if (handler != null) {
                        Message obtainMessage = handler.obtainMessage(1);
                        this.msg = obtainMessage;
                        obtainMessage.arg1 = getProgress();
                        handler.sendMessage(this.msg);
                    }
                    if (fVar != null && fVar.r()) {
                        return null;
                    }
                    if (isTimeout()) {
                        connect = new j();
                        connect.a = str;
                        connect.d = j2;
                    } else {
                        IpModel ipModelByHost = JDDnsUtil.getInstance().isNetworkDnsControlEnable() ? JDHttpDnsToolkit.getInstance().getIpModelByHost(str) : null;
                        if (ipModelByHost != null) {
                            j connect2 = connect(ipModelByHost.getIp(), 80);
                            connect2.a = ipModelByHost.host;
                            connect2.f12987c = true;
                            addToMap(hashMap, connect2);
                        }
                        connect = connect(str, 80);
                    }
                    if (OKLog.D) {
                        OKLog.d("net-re", "connection   " + str + LangUtils.SINGLE_SPACE + i3);
                    }
                    addToMap(hashMap, connect);
                    i4++;
                    j2 = -1;
                }
                i3++;
            } else {
                ArrayList arrayList = new ArrayList();
                for (Map.Entry<String, List<j>> entry : hashMap.entrySet()) {
                    if (fVar != null && fVar.r()) {
                        return list;
                    }
                    List<j> value = entry.getValue();
                    PingResultEntity pingResultEntity = new PingResultEntity();
                    pingResultEntity.transmitted = String.valueOf(value.size());
                    boolean z = false;
                    long j3 = -1;
                    int i5 = 0;
                    for (j jVar : value) {
                        if (!TextUtils.isEmpty(jVar.a) && TextUtils.isEmpty(pingResultEntity.domainName)) {
                            pingResultEntity.domainName = jVar.a;
                        }
                        if (jVar.d > Long.parseLong(pingResultEntity.max)) {
                            pingResultEntity.max = String.valueOf(jVar.d);
                        }
                        if (jVar.d > 0 && (Long.parseLong(pingResultEntity.min) < 0 || jVar.d < Long.parseLong(pingResultEntity.min))) {
                            pingResultEntity.min = String.valueOf(jVar.d);
                        }
                        long j4 = jVar.d;
                        if (j4 > 0) {
                            i5++;
                        }
                        pingResultEntity.ip = jVar.b;
                        if (j4 > 0) {
                            j3 += j4;
                        }
                        z = jVar.f12987c;
                    }
                    pingResultEntity.dnsFlag = z ? "1" : "0";
                    Object[] objArr = new Object[1];
                    objArr[0] = Float.valueOf(((float) j3) / (i5 == 0 ? 1 : i5));
                    pingResultEntity.avg = String.format("%.2f", objArr);
                    pingResultEntity.received = String.valueOf(i5);
                    pingResultEntity.packetLoss = String.format("%.2f", Float.valueOf((value.size() - i5) / value.size()));
                    if (i5 > 0) {
                        pingResultEntity.code = "0";
                    }
                    arrayList.add(pingResultEntity);
                    list = null;
                }
                if (fVar == null || !fVar.r()) {
                    return arrayList;
                }
                return null;
            }
        }
    }

    public int createReport(List<PingResultEntity> list, List<PingResultEntity> list2) {
        OKLog.d(TAG, "  report :" + list.size() + "   " + list2.size());
        String networkType = NetUtils.getNetworkType();
        double d2 = 0.0d;
        double d3 = 0.0d;
        double d4 = 0.0d;
        int i2 = 0;
        for (PingResultEntity pingResultEntity : list) {
            Double valueOf = Double.valueOf(Double.parseDouble(pingResultEntity.transmitted));
            Double valueOf2 = Double.valueOf(Double.parseDouble(pingResultEntity.received));
            Double valueOf3 = Double.valueOf(Double.parseDouble(pingResultEntity.avg));
            d2 += valueOf.doubleValue();
            d3 += valueOf2.doubleValue();
            if (valueOf3.doubleValue() > 0.0d) {
                d4 += valueOf3.doubleValue();
                i2++;
            }
        }
        char c2 = ((d2 > 0.0d ? 1 : (d2 == 0.0d ? 0 : -1)) != 0 ? d3 / d2 : 0.0d) <= 0.5d ? (char) 1 : (char) 0;
        if (d3 > 0.0d && i2 != 0) {
            double d5 = i2;
            Double.isNaN(d5);
            if (d4 / d5 > 500.0d) {
                c2 = 2;
            }
        }
        if (OKLog.D) {
            StringBuilder sb = new StringBuilder();
            sb.append("--avg-time-jd-");
            double d6 = i2;
            Double.isNaN(d6);
            sb.append(d4 / d6);
            OKLog.d(TAG, sb.toString());
        }
        double d7 = 0.0d;
        double d8 = 0.0d;
        double d9 = 0.0d;
        int i3 = 0;
        for (PingResultEntity pingResultEntity2 : list2) {
            Double valueOf4 = Double.valueOf(Double.parseDouble(pingResultEntity2.transmitted));
            Double valueOf5 = Double.valueOf(Double.parseDouble(pingResultEntity2.received));
            Double valueOf6 = Double.valueOf(Double.parseDouble(pingResultEntity2.avg));
            d8 += valueOf4.doubleValue();
            d7 += valueOf5.doubleValue();
            if (valueOf6.doubleValue() > 0.0d) {
                d9 += valueOf6.doubleValue();
                i3++;
            }
        }
        char c3 = d7 / d8 <= 0.5d ? (char) 1 : (char) 0;
        if (OKLog.D) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("--avg-time-other-");
            double d10 = i3;
            Double.isNaN(d10);
            sb2.append(d9 / d10);
            OKLog.d(TAG, sb2.toString());
        }
        if (d7 > 0.0d && i3 != 0) {
            double d11 = i3;
            Double.isNaN(d11);
            if (d9 / d11 > 500.0d) {
                c3 = 2;
            }
        }
        if (c2 == 0 && c3 == 0) {
            return 4;
        }
        if ((c2 == 2 || c2 == 1) && c3 == 0) {
            return 1;
        }
        if (c2 == 2 && c3 == 2) {
            return 2;
        }
        return "wifi".equals(networkType) ? (c2 == 1 && c3 == 1) ? 3 : 5 : (c2 == 1 && c3 == 1) ? 2 : 5;
    }

    public static NetDiagnosisController getController() {
        NetDiagnosisController netDiagnosisController;
        NetDiagnosisController netDiagnosisController2 = controller;
        if (netDiagnosisController2 != null) {
            return netDiagnosisController2;
        }
        synchronized (NetDiagnosisController.class) {
            if (controller == null) {
                controller = new NetDiagnosisController();
            }
            netDiagnosisController = controller;
        }
        return netDiagnosisController;
    }

    private String getIpWithStack(String str) {
        if (OKLog.D) {
            OKLog.d(TAG, "---ex-msg->" + str);
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        for (String str2 : str.split(" |/")) {
            if (StringUtil.isIp(str2)) {
                return str2;
            }
        }
        return null;
    }

    private int getProgress() {
        int i2 = this.progress + 1;
        this.progress = i2;
        return i2;
    }

    public static boolean isAllowSearchJump(String str) {
        return searchConfig() && !TextUtils.isEmpty(str) && START_NET_DIAGNOSE_PASSWORD.equals(str);
    }

    private boolean isTimeout() {
        return System.currentTimeMillis() - this.taskStartTime > 120000;
    }

    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0061: MOVE (r1 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:58:0x0061 */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0064  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean isWifiSetPortal() {
        /*
            r6 = this;
            r0 = 0
            r1 = 0
            java.net.URL r2 = new java.net.URL     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            java.lang.String r3 = "http://clients3.google.com/generate_204"
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            java.net.URLConnection r2 = r2.openConnection()     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            r2.setInstanceFollowRedirects(r0)     // Catch: java.io.IOException -> L4d java.lang.Throwable -> L60
            r1 = 5000(0x1388, float:7.006E-42)
            r2.setConnectTimeout(r1)     // Catch: java.io.IOException -> L4d java.lang.Throwable -> L60
            r2.setReadTimeout(r1)     // Catch: java.io.IOException -> L4d java.lang.Throwable -> L60
            r2.setUseCaches(r0)     // Catch: java.io.IOException -> L4d java.lang.Throwable -> L60
            r2.getInputStream()     // Catch: java.io.IOException -> L4d java.lang.Throwable -> L60
            boolean r1 = com.jingdong.sdk.oklog.OKLog.D     // Catch: java.io.IOException -> L4d java.lang.Throwable -> L60
            if (r1 == 0) goto L3e
            java.lang.String r1 = "net-r"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L4d java.lang.Throwable -> L60
            r3.<init>()     // Catch: java.io.IOException -> L4d java.lang.Throwable -> L60
            java.lang.String r4 = "code-->"
            r3.append(r4)     // Catch: java.io.IOException -> L4d java.lang.Throwable -> L60
            int r4 = r2.getResponseCode()     // Catch: java.io.IOException -> L4d java.lang.Throwable -> L60
            r3.append(r4)     // Catch: java.io.IOException -> L4d java.lang.Throwable -> L60
            java.lang.String r3 = r3.toString()     // Catch: java.io.IOException -> L4d java.lang.Throwable -> L60
            com.jingdong.sdk.oklog.OKLog.d(r1, r3)     // Catch: java.io.IOException -> L4d java.lang.Throwable -> L60
        L3e:
            int r1 = r2.getResponseCode()     // Catch: java.io.IOException -> L4d java.lang.Throwable -> L60
            r3 = 204(0xcc, float:2.86E-43)
            if (r1 == r3) goto L47
            r0 = 1
        L47:
            if (r2 == 0) goto L4c
            r2.disconnect()
        L4c:
            return r0
        L4d:
            r1 = move-exception
            goto L55
        L4f:
            r0 = move-exception
            goto L62
        L51:
            r2 = move-exception
            r5 = r2
            r2 = r1
            r1 = r5
        L55:
            java.lang.String r3 = "NetDiagnosis"
            com.jingdong.sdk.oklog.OKLog.e(r3, r1)     // Catch: java.lang.Throwable -> L60
            if (r2 == 0) goto L5f
            r2.disconnect()
        L5f:
            return r0
        L60:
            r0 = move-exception
            r1 = r2
        L62:
            if (r1 == 0) goto L67
            r1.disconnect()
        L67:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.lib.netdiagnosis.NetDiagnosisController.isWifiSetPortal():boolean");
    }

    public static boolean netConfig() {
        return "1".equals(ConfigUtil.getStringFromPreference(SHARED_NET_CONFIG, "0"));
    }

    private void pingTasks() {
        this.netType = NetUtils.getNetworkType();
        f.C0827f l2 = f.f.l();
        this.mainTcs = l2;
        this.mainTask = l2.a();
        this.report = new StringBuilder();
        this.progress = 0;
        this.taskStartTime = System.currentTimeMillis();
        e eVar = new e();
        ExecutorService executorService = f.f.f19368i;
        f.f.c(eVar, executorService).h(new d(), executorService).h(new c(), executorService).h(new b(), executorService);
    }

    public static boolean searchConfig() {
        return "1".equals(ConfigUtil.getStringFromPreference(SHARED_SEARCH_CONFIG, "0"));
    }

    public void sendCancel() {
        Message obtainMessage = this.handler.obtainMessage(3);
        this.msg = obtainMessage;
        obtainMessage.arg1 = 0;
        this.handler.sendMessage(obtainMessage);
    }

    public void sendProgress() {
        Message obtainMessage = this.handler.obtainMessage(1);
        this.msg = obtainMessage;
        obtainMessage.arg1 = this.progress;
        this.handler.sendMessage(obtainMessage);
    }

    public void autoNetDiagnose() {
        if (OKLog.D) {
            OKLog.d(TAG, "netConfig:" + netConfig());
            OKLog.d(TAG, "isAutoDiagnose:" + isAutoDiagnose());
        }
        if (netConfig() && isAutoDiagnose() && NetUtils.isNetworkAvailable()) {
            autoPingTasks();
            setAutoTimes();
        }
    }

    public void autoPingTasks() {
        g gVar = new g();
        ExecutorService executorService = f.f.f19368i;
        f.f.c(gVar, executorService).h(new f(this), executorService);
    }

    public void cancelTasks() {
        f.f fVar;
        if (this.startAnimTcs != null && (fVar = this.startAnimTask) != null && !fVar.r()) {
            this.startAnimTcs.b();
        }
        f.f fVar2 = this.mainTask;
        if (fVar2 == null || fVar2.r()) {
            return;
        }
        this.mainTcs.b();
        if (OKLog.D) {
            OKLog.d("net-result", "t cancel " + this.mainTask.r());
        }
    }

    public File getSaveFile() {
        File file = this.saveFile;
        if (file != null) {
            return file;
        }
        File file2 = new File(JdSdk.getInstance().getApplication().getFilesDir() + "/" + SAVE_FILE);
        if (!file2.exists() || !file2.isFile()) {
            try {
                file2.createNewFile();
            } catch (IOException e2) {
                OKLog.e(TAG, e2);
                return null;
            }
        }
        return file2;
    }

    public synchronized boolean isAutoDiagnose() {
        int intFromPreference = CommonBase.getIntFromPreference(SHARED_NET_REQUEST_FAIL_TIMES, 0);
        CommonBase.putIntToPreference(SHARED_NET_REQUEST_FAIL_TIMES, intFromPreference + 1);
        if (intFromPreference < 5) {
            return false;
        }
        CommonBase.putIntToPreference(SHARED_NET_REQUEST_FAIL_TIMES, 0);
        return !FormatUtils.formatDateWithYMH(new Date()).equals(CommonBase.getStringFromPreference(SHARED_AUTO_DIAGNOSE_DATE, "")) || CommonBase.getIntFromPreference(SHARED_AUTO_DIAGNOSE_TIMES, 0) < 7;
    }

    public void netDiagnose(Handler handler) {
        this.startAnimationFlag = true;
        this.handler = handler;
        if (OKLog.D) {
            OKLog.d("net-r", NetUtils.isNetworkAvailable() + "   \u662f\u5426\u6709\u7f51\u7edc");
        }
        if (!NetUtils.isNetworkAvailable()) {
            Message obtainMessage = handler.obtainMessage(3);
            this.msg = obtainMessage;
            obtainMessage.arg1 = 2;
            handler.sendMessage(obtainMessage);
            return;
        }
        pingTasks();
        if (NetUtils.isWifi()) {
            f.C0827f l2 = f.f.l();
            this.startAnimTcs = l2;
            this.startAnimTask = l2.a();
            f.f.c(new a(), f.f.f19368i);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:148:0x0062, code lost:
        if (r2 == null) goto L150;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:187:0x00f7 A[Catch: all -> 0x010e, TRY_LEAVE, TryCatch #11 {, blocks: (B:189:0x00fe, B:197:0x010c, B:194:0x0105, B:176:0x00e4, B:170:0x00d9, B:173:0x00de, B:185:0x00f3, B:187:0x00f7), top: B:212:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:213:0x00fe A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r2v13, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void saveReport2File(java.lang.String r9) {
        /*
            Method dump skipped, instructions count: 292
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.lib.netdiagnosis.NetDiagnosisController.saveReport2File(java.lang.String):void");
    }

    public synchronized void setAutoTimes() {
        String formatDateWithYMH = FormatUtils.formatDateWithYMH(new Date());
        String stringFromPreference = CommonBase.getStringFromPreference(SHARED_AUTO_DIAGNOSE_DATE, "");
        int i2 = 0;
        int intFromPreference = CommonBase.getIntFromPreference(SHARED_AUTO_DIAGNOSE_TIMES, 0);
        if (formatDateWithYMH.equals(stringFromPreference)) {
            i2 = intFromPreference;
        }
        CommonBase.putStringToPreference(SHARED_AUTO_DIAGNOSE_DATE, formatDateWithYMH);
        CommonBase.putIntToPreference(SHARED_AUTO_DIAGNOSE_TIMES, i2 + 1);
    }

    public void upLocalData() {
        if (SwitchQueryFetcher.isXTime()) {
            OKLog.d(TAG, "networklog X time not report ");
        } else {
            f.f.c(new h(), f.f.f19368i);
        }
    }

    public void uploadData(List<ReportNetLogEntity> list) {
        if (SwitchQueryFetcher.isXTime()) {
            OKLog.d(TAG, "networklog X time not report ");
        } else if (list == null || list.size() == 0) {
        } else {
            HashMap hashMap = new HashMap();
            hashMap.put("logList", list);
            try {
                JSONObject jSONObject = new JSONObject(JDJSON.toJSONString(hashMap));
                if (OKLog.D) {
                    OKLog.d(TAG, " request -->" + jSONObject);
                }
                HttpGroupSetting createNewSettings = HttpGroupUtils.createNewSettings();
                createNewSettings.setPriority(1000);
                createNewSettings.setType(1000);
                HttpGroup httpGroup = HttpGroup.getHttpGroup(createNewSettings);
                HttpSetting httpSetting = new HttpSetting();
                httpSetting.setFunctionId(FUNCTIONID);
                httpSetting.setHost(Configuration.getNgwHost());
                httpSetting.setJsonParams(jSONObject);
                httpSetting.setListener(new i(list));
                httpGroup.add(httpSetting);
            } catch (JSONException e2) {
                OKLog.e(TAG, e2);
            }
        }
    }
}
