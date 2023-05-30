package com.jingdong.jdexreport.record;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.jdexreport.broadcast.UserChangedListener;
import com.jingdong.jdexreport.e.b;
import com.jingdong.jdexreport.e.d;
import com.jingdong.jdexreport.e.e;
import com.jingdong.jdexreport.einterface.InitCommonInfo;
import java.util.HashMap;
import java.util.Vector;

/* loaded from: classes.dex */
public class JDExReportDbImpl {
    public static final int MSG_GONE = 1001;
    public static final int MSG_REPORT_DATA = 1002;
    private static JDExReportDbImpl mInstance;
    private boolean destroyFlag;
    private Context mContext;
    private com.jingdong.jdexreport.b.a mDBManager;
    private InitCommonInfo mInitCommonInfo;
    private a mRecordDemons;
    private Thread mRecordThread;
    com.jingdong.jdexreport.f.a mReportDemon;
    private Thread mReportDemonThread;
    private Handler mReportHandler;
    private e mStrategyModel;
    public Vector<d> recordCacheVec;
    private UserChangedListener userChangedListener;
    private final String LOG_TAG = JDExReportDbImpl.class.getName();
    private Long mRecordNum = 0L;
    private int CACHE_LIST_SIZE = 256;
    private Long mLastReportTime = 0L;
    private Long mLastAlignTime = 0L;
    private boolean mLastReportFailed = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class ExReportDemon extends com.jingdong.jdexreport.f.a {
        public ExReportDemon(Context context, InitCommonInfo initCommonInfo) {
            super(context, initCommonInfo);
        }

        @Override // com.jingdong.jdexreport.f.a
        public void aligningCount() {
            if (JDExReportDbImpl.this.mStrategyModel == null) {
                return;
            }
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - JDExReportDbImpl.this.mLastAlignTime.longValue() < JDExReportDbImpl.this.mStrategyModel.f12612h) {
                JDExReportDbImpl.this.updateRecordNumFromDB();
            }
            JDExReportDbImpl.this.mLastAlignTime = Long.valueOf(currentTimeMillis);
        }

        @Override // com.jingdong.jdexreport.f.a
        public void onDealFail(int i2) {
            if (JDExReportDbImpl.this.mStrategyModel == null) {
                return;
            }
            JDExReportDbImpl.this.mLastReportFailed = true;
            if (JDExReportDbImpl.this.mStrategyModel != null) {
                JDExReportDbImpl.this.decreaseRecordNum(i2);
                if (JDExReportDbImpl.this.mReportHandler != null) {
                    JDExReportDbImpl.this.mReportHandler.removeMessages(1002);
                    JDExReportDbImpl.this.mReportHandler.sendEmptyMessageDelayed(1002, 300000L);
                }
            }
        }

        @Override // com.jingdong.jdexreport.f.a
        public void onDealSuccess(int i2) {
            if (JDExReportDbImpl.this.mStrategyModel == null) {
                return;
            }
            JDExReportDbImpl.this.mLastReportFailed = false;
            if (JDExReportDbImpl.this.mStrategyModel != null) {
                if (!JDExReportDbImpl.this.mStrategyModel.g()) {
                    synchronized (JDExReportDbImpl.this.mRecordNum) {
                        JDExReportDbImpl.this.mRecordNum = 0L;
                    }
                    if (JDExReportDbImpl.this.mReportHandler != null) {
                        JDExReportDbImpl.this.mReportHandler.removeMessages(1002);
                        return;
                    }
                    return;
                }
                JDExReportDbImpl.this.decreaseRecordNum(i2);
                if ((com.jingdong.jdexreport.a.a.d.c(this.mContext) ? JDExReportDbImpl.this.judgeLimitAndSendMessage() : false) || JDExReportDbImpl.this.mReportHandler == null) {
                    return;
                }
                JDExReportDbImpl.this.mReportHandler.removeMessages(1002);
                JDExReportDbImpl.this.mReportHandler.sendEmptyMessageDelayed(1002, JDExReportDbImpl.this.mStrategyModel.c(com.jingdong.jdexreport.a.a.d.b(this.mContext)) * 1000);
            }
        }

        @Override // com.jingdong.jdexreport.f.a
        public void onNullDataReport() {
            JDExReportDbImpl.this.mLastReportFailed = false;
        }
    }

    public JDExReportDbImpl(Context context, InitCommonInfo initCommonInfo) {
        this.destroyFlag = false;
        this.mStrategyModel = null;
        this.destroyFlag = false;
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        if (applicationContext == null) {
            this.mContext = context;
        }
        this.mInitCommonInfo = initCommonInfo;
        this.mDBManager = com.jingdong.jdexreport.b.a.a(context);
        this.mStrategyModel = e.a(this.mContext);
        clearDBWithTIme();
        this.recordCacheVec = new Vector<>();
        this.mRecordDemons = new a(this.mDBManager, this, this.mContext);
        Thread thread = new Thread(this.mRecordDemons);
        this.mRecordThread = thread;
        thread.start();
        b.a(this.mInitCommonInfo);
        this.mReportHandler = new Handler(context.getMainLooper()) { // from class: com.jingdong.jdexreport.record.JDExReportDbImpl.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                int i2 = message.what;
                if (i2 != 1001) {
                    if (i2 != 1002) {
                        return;
                    }
                    JDExReportDbImpl.this.invokeReport();
                    return;
                }
                com.jingdong.jdexreport.a.a.a.a(JDExReportDbImpl.this.LOG_TAG, "DBCore.destoryInstance");
                com.jingdong.jdexreport.b.a.b();
                e.b();
                b.a();
                JDExReportDbImpl unused = JDExReportDbImpl.mInstance = null;
            }
        };
    }

    private void clearDBWithTIme() {
        new Thread(new Runnable() { // from class: com.jingdong.jdexreport.record.JDExReportDbImpl.2
            @Override // java.lang.Runnable
            public void run() {
                if (JDExReportDbImpl.this.mDBManager != null && JDExReportDbImpl.this.mStrategyModel != null) {
                    long c2 = JDExReportDbImpl.this.mStrategyModel.c();
                    if (c2 > 0) {
                        JDExReportDbImpl.this.mDBManager.b(c2);
                    }
                }
                JDExReportDbImpl.this.updateRecordNumFromDB();
                JDExReportDbImpl.this.startReportDemon();
            }
        }).start();
    }

    private void destroy() {
        com.jingdong.jdexreport.a.a.a.a(this.LOG_TAG, "destroy");
        this.destroyFlag = true;
        a aVar = this.mRecordDemons;
        if (aVar != null) {
            aVar.a();
            synchronized (this.mRecordDemons) {
                try {
                    this.mRecordDemons.notify();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        Vector<d> vector = this.recordCacheVec;
        if (vector != null) {
            vector.clear();
        }
        this.mRecordDemons = null;
        Handler handler = this.mReportHandler;
        if (handler != null) {
            handler.removeMessages(0);
            this.mReportHandler.sendEmptyMessageDelayed(1001, Final.FIVE_SECOND);
        }
        com.jingdong.jdexreport.f.a aVar2 = this.mReportDemon;
        if (aVar2 != null) {
            stopThread(aVar2);
        }
    }

    public static synchronized void destroyInstance() {
        synchronized (JDExReportDbImpl.class) {
            JDExReportDbImpl jDExReportDbImpl = mInstance;
            if (jDExReportDbImpl != null) {
                jDExReportDbImpl.destroy();
            }
        }
    }

    private void destroyReprot() {
        com.jingdong.jdexreport.f.a aVar = this.mReportDemon;
        if (aVar != null) {
            stopThread(aVar);
        }
    }

    public static long getDBDataCount() {
        com.jingdong.jdexreport.b.a aVar;
        JDExReportDbImpl jDExReportDbImpl = mInstance;
        if (jDExReportDbImpl == null || (aVar = jDExReportDbImpl.mDBManager) == null) {
            return 0L;
        }
        return aVar.c();
    }

    public static synchronized JDExReportDbImpl getInstance(Context context, InitCommonInfo initCommonInfo) {
        JDExReportDbImpl jDExReportDbImpl;
        synchronized (JDExReportDbImpl.class) {
            if (mInstance == null) {
                mInstance = new JDExReportDbImpl(context, initCommonInfo);
            }
            jDExReportDbImpl = mInstance;
        }
        return jDExReportDbImpl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invokeReport() {
        com.jingdong.jdexreport.f.a aVar;
        if (this.destroyFlag || (aVar = this.mReportDemon) == null) {
            return;
        }
        if (this.mReportDemonThread == null && this.mStrategyModel == null) {
            return;
        }
        synchronized (aVar) {
            try {
                this.mReportDemon.notify();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        synchronized (this.mLastReportTime) {
            this.mLastReportTime = Long.valueOf(System.currentTimeMillis());
        }
        Handler handler = this.mReportHandler;
        if (handler != null) {
            handler.removeMessages(1002);
            this.mReportHandler.sendEmptyMessageDelayed(1002, this.mStrategyModel.c(com.jingdong.jdexreport.a.a.d.b(this.mContext)) * 1000);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void startReportDemon() {
        if (this.mReportDemon == null) {
            this.mReportDemon = new ExReportDemon(this.mContext, this.mInitCommonInfo);
        }
        if (this.mReportDemonThread == null) {
            this.mReportDemonThread = new Thread(this.mReportDemon);
        }
        this.mReportDemonThread.start();
        Handler handler = this.mReportHandler;
        if (handler != null) {
            handler.sendEmptyMessageDelayed(1002, this.mStrategyModel.c(com.jingdong.jdexreport.a.a.d.b(this.mContext)) * 1000);
        }
    }

    private void stopThread(com.jingdong.jdexreport.f.a aVar) {
        aVar.stopThread();
        synchronized (aVar) {
            try {
                aVar.notify();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long updateRecordNumFromDB() {
        Long valueOf;
        synchronized (this.mRecordNum) {
            valueOf = Long.valueOf(this.mDBManager.c());
            this.mRecordNum = valueOf;
        }
        return valueOf.longValue();
    }

    public void aligningCurrentLocalCount(int i2) {
        this.mRecordNum = Long.valueOf(this.mDBManager.c());
    }

    public void closeUnWifiReport() {
        com.jingdong.jdexreport.f.a.closeUnWifiReport();
    }

    public long decreaseRecordNum(long j2) {
        synchronized (this.mRecordNum) {
            Long valueOf = Long.valueOf(this.mRecordNum.longValue() - j2);
            this.mRecordNum = valueOf;
            if (valueOf.longValue() < 0) {
                updateRecordNumFromDB();
            }
        }
        return this.mRecordNum.longValue();
    }

    public long getCurrentLocalCount() {
        return this.mRecordNum.longValue();
    }

    public long getRecordNum() {
        return this.mRecordNum.longValue();
    }

    public UserChangedListener getUserChangedListener() {
        return this.userChangedListener;
    }

    public long incrementRecordNum() {
        Long valueOf;
        synchronized (this.mRecordNum) {
            valueOf = Long.valueOf(this.mRecordNum.longValue() + 1);
            this.mRecordNum = valueOf;
        }
        return valueOf.longValue();
    }

    public boolean judgeLimitAndSendMessage() {
        try {
            String b = com.jingdong.jdexreport.a.a.d.b(this.mContext);
            if (this.mLastReportFailed) {
                return false;
            }
            long j2 = 0;
            if (this.mStrategyModel.a(b, this.mRecordNum.longValue()) || (this.mRecordNum.longValue() > 0 && 0 == this.mRecordNum.longValue() % 300)) {
                Handler handler = this.mReportHandler;
                if (handler != null) {
                    handler.removeMessages(1002);
                }
                long currentTimeMillis = System.currentTimeMillis();
                long longValue = this.mLastReportTime.longValue();
                Handler handler2 = this.mReportHandler;
                if (handler2 != null) {
                    long j3 = currentTimeMillis - longValue;
                    if ((this.mStrategyModel.c(com.jingdong.jdexreport.a.a.d.b(this.mContext)) * 1000) - j3 > 0) {
                        long c2 = this.mStrategyModel.c(com.jingdong.jdexreport.a.a.d.b(this.mContext));
                        Long.signum(c2);
                        j2 = (c2 * 1000) - j3;
                    }
                    handler2.sendEmptyMessageDelayed(1002, j2);
                }
                return true;
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public void openUnWifiReport() {
        com.jingdong.jdexreport.f.a.openUnWifiReport();
    }

    public void reqRecord(HashMap<String, String> hashMap) {
        if (this.destroyFlag) {
            return;
        }
        e a = e.a(this.mContext);
        if (a == null || a.g()) {
            if (this.mRecordDemons == null) {
                synchronized (this) {
                    if (this.mRecordDemons == null) {
                        this.mRecordDemons = new a(this.mDBManager, this, this.mContext);
                        Thread thread = new Thread(this.mRecordDemons);
                        this.mRecordThread = thread;
                        thread.start();
                    }
                }
            }
            d dVar = new d();
            dVar.b(hashMap);
            synchronized (this.recordCacheVec) {
                if (this.recordCacheVec.size() < this.CACHE_LIST_SIZE) {
                    com.jingdong.jdexreport.a.a.a.a("JDExReport", "[vec]add record:" + hashMap.toString());
                    this.recordCacheVec.add(0, dVar);
                }
                this.recordCacheVec.notifyAll();
            }
        }
    }

    public void setUserChangedListener(UserChangedListener userChangedListener) {
        this.userChangedListener = userChangedListener;
    }

    public void updatePin() {
        UserChangedListener userChangedListener = this.userChangedListener;
        if (userChangedListener != null) {
            b.f12597m = userChangedListener.onUserChanged();
        }
    }
}
