package com.vivo.identifier;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import com.jd.dynamic.DYConstants;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* loaded from: classes11.dex */
public class IdentifierIdClient {
    private static final String ID_APPID = "appid";
    private static final String ID_TYPE = "type";
    private static final String OAIDSTATUS_FLAG = "OAIDSTATUS";
    private static final String OAID_FLAG = "OAID";
    public static final String SYS_IDENTIFIERID = "persist.sys.identifierid";
    private static final String SYS_IDENTIFIERID_SUPPORTED = "persist.sys.identifierid.supported";
    private static final String TAG = "VMS_SDK_Client";
    private static final int TIME_FOR_QUERY = 2000;
    private static final int TYPE_AAID = 2;
    private static final int TYPE_AAID_VMS = 10;
    private static final int TYPE_GUID = 5;
    private static final int TYPE_OAID = 0;
    private static final int TYPE_OAIDBLACK = 6;
    private static final int TYPE_OAIDSTATUS = 4;
    private static final int TYPE_OAID_VMS = 8;
    private static final int TYPE_QUERY = 11;
    private static final int TYPE_REPORT_STATISTICS = 7;
    private static final int TYPE_UDID = 3;
    private static final int TYPE_VAID = 1;
    private static final int TYPE_VAID_VMS = 9;
    private static final String URI_BASE = "content://com.vivo.vms.IdProvider/IdentifierId";
    private static final String VAID_FLAG = "VAID";
    private static final String VMS_PACKAGE_NAME = "com.vivo.vms";
    private static int VMS_SUPPORT_OAID_BALCKLIST_MIN_VERSION = 13;
    private static String mAAID;
    private static IdentifierIdObserver mAAIDObserver;
    private static int mAaidFail;
    private static int mAaidSuc;
    private static Context mContext;
    private static volatile DataBaseOperation mDatabase;
    private static String mGUID;
    private static volatile IdentifierIdClient mInstance;
    private static boolean mIsSupported;
    private static Object mLock = new Object();
    private static String mOAID;
    private static IdentifierIdObserver mOAIDBLACKObserver;
    private static String mOAIDBlack;
    private static IdentifierIdObserver mOAIDObserver;
    private static String mOAIDStatus;
    private static int mOaidFail;
    private static int mOaidSuc;
    private static Handler mSqlHandler;
    private static HandlerThread mSqlThread;
    private static String mUDID;
    private static String mVAID;
    private static IdentifierIdObserver mVAIDObserver;
    private static int mVaidFail;
    private static int mVaidSuc;
    private static int mVmsAaidFail;
    private static int mVmsAaidSuc;
    private static int mVmsOaidFail;
    private static int mVmsOaidSuc;
    private static int mVmsVaidFail;
    private static int mVmsVaidSuc;
    private final int mVMSVersion;

    private IdentifierIdClient() {
        initSqlThread();
        mDatabase = new DataBaseOperation(mContext);
        this.mVMSVersion = getVMSVersion(mContext);
    }

    private static void checkSupported() {
        mIsSupported = "1".equals(getProperty(SYS_IDENTIFIERID_SUPPORTED, "0")) || "1".equals(getProperty(SYS_IDENTIFIERID, "0"));
    }

    public static IdentifierIdClient getInstance(Context context) {
        if (isSupported()) {
            return getInstanceCore(context);
        }
        return null;
    }

    public static IdentifierIdClient getInstanceCore(Context context) {
        if (mContext == null) {
            if (context == null) {
                return null;
            }
            Context applicationContext = context.getApplicationContext();
            if (applicationContext != null) {
                context = applicationContext;
            }
            mContext = context;
        }
        if (mInstance == null) {
            synchronized (IdentifierIdClient.class) {
                if (mInstance == null) {
                    IdentifierIdClient identifierIdClient = new IdentifierIdClient();
                    mInstance = identifierIdClient;
                    identifierIdClient.startTimingTask();
                }
            }
        }
        return mInstance;
    }

    private static String getProperty(String str, String str2) {
        try {
            try {
                Class<?> cls = Class.forName("android.os.SystemProperties");
                return (String) cls.getMethod(IMantoServerRequester.GET, String.class, String.class).invoke(cls, str, "0");
            } catch (Exception e2) {
                String str3 = "getProperty: invoke is error" + e2.getMessage();
                return str2;
            }
        } catch (Throwable unused) {
            return str2;
        }
    }

    public String getStringSplicing(int i2, int i3, int i4, int i5) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(i2);
        stringBuffer.append(DYConstants.DY_REGEX_COMMA);
        stringBuffer.append(i3);
        stringBuffer.append(";");
        stringBuffer.append(i4);
        stringBuffer.append(DYConstants.DY_REGEX_COMMA);
        stringBuffer.append(i5);
        return stringBuffer.toString();
    }

    private static int getVMSVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(VMS_PACKAGE_NAME, 0).versionCode;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    private static synchronized void initObserver(Context context, int i2, String str) {
        synchronized (IdentifierIdClient.class) {
            if (i2 != 0) {
                if (i2 != 1) {
                    if (i2 == 2 && mAAIDObserver == null) {
                        mAAIDObserver = new IdentifierIdObserver(mInstance, 2, str);
                        context.getContentResolver().registerContentObserver(Uri.parse("content://com.vivo.vms.IdProvider/IdentifierId/" + context.getPackageName()), false, mAAIDObserver);
                    }
                } else if (mVAIDObserver == null) {
                    mVAIDObserver = new IdentifierIdObserver(mInstance, 1, str);
                    context.getContentResolver().registerContentObserver(Uri.parse("content://com.vivo.vms.IdProvider/IdentifierId/VAID_".concat(String.valueOf(str))), false, mVAIDObserver);
                }
            } else if (mOAIDObserver == null) {
                mOAIDObserver = new IdentifierIdObserver(mInstance, 0, null);
                context.getContentResolver().registerContentObserver(Uri.parse("content://com.vivo.vms.IdProvider/IdentifierId/OAID"), true, mOAIDObserver);
            }
        }
    }

    private static void initSqlThread() {
        HandlerThread handlerThread = new HandlerThread("SqlWorkThread");
        mSqlThread = handlerThread;
        handlerThread.start();
        mSqlHandler = new Handler(mSqlThread.getLooper()) { // from class: com.vivo.identifier.IdentifierIdClient.2
            /* JADX WARN: Removed duplicated region for block: B:136:0x0080 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            @Override // android.os.Handler
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void handleMessage(Message message) {
                String query;
                int i2;
                String str;
                if (message.what != 11) {
                    return;
                }
                int i3 = message.getData().getInt("type");
                try {
                    query = IdentifierIdClient.mDatabase.query(i3, message.getData().getString("appid"));
                } catch (Exception e2) {
                    String str2 = "readException:" + e2.toString();
                }
                if (i3 == 0) {
                    String unused = IdentifierIdClient.mOAID = query;
                    i2 = 8;
                    str = IdentifierIdClient.mOAID;
                } else if (i3 == 1) {
                    if (query != null) {
                        String unused2 = IdentifierIdClient.mVAID = query;
                    }
                    i2 = 9;
                    str = IdentifierIdClient.mVAID;
                } else if (i3 != 2) {
                    if (i3 != 3) {
                        if (i3 == 4) {
                            String unused3 = IdentifierIdClient.mOAIDStatus = query;
                        } else if (i3 == 5 && query != null) {
                            String unused4 = IdentifierIdClient.mGUID = query;
                        }
                    } else if (query != null) {
                        String unused5 = IdentifierIdClient.mUDID = query;
                    }
                    synchronized (IdentifierIdClient.mLock) {
                        IdentifierIdClient.mLock.notify();
                    }
                    return;
                } else {
                    if (query != null) {
                        String unused6 = IdentifierIdClient.mAAID = query;
                    }
                    i2 = 10;
                    str = IdentifierIdClient.mAAID;
                }
                IdentifierIdClient.setStatistics(i2, str);
                synchronized (IdentifierIdClient.mLock) {
                }
            }
        };
    }

    public static boolean isSupported() {
        if (!mIsSupported) {
            checkSupported();
        }
        return mIsSupported;
    }

    private void queryId(int i2, String str) {
        synchronized (mLock) {
            sendMessageToDataBase(i2, str);
            long uptimeMillis = SystemClock.uptimeMillis();
            try {
                mLock.wait(2000L);
            } catch (InterruptedException unused) {
            }
            int i3 = ((SystemClock.uptimeMillis() - uptimeMillis) > 2000L ? 1 : ((SystemClock.uptimeMillis() - uptimeMillis) == 2000L ? 0 : -1));
        }
    }

    private void queryIdNoDelay(int i2, String str) {
        sendMessageToDataBase(i2, str);
    }

    public static void setStatistics(int i2, String str) {
        if (i2 == 0) {
            if (str == null) {
                mOaidFail++;
            } else {
                mOaidSuc++;
            }
        } else if (i2 == 1) {
            if (str == null) {
                mVaidFail++;
            } else {
                mVaidSuc++;
            }
        } else if (i2 == 2) {
            if (str == null) {
                mAaidFail++;
            } else {
                mAaidSuc++;
            }
        } else {
            switch (i2) {
                case 8:
                    if (str == null) {
                        mVmsOaidFail++;
                        return;
                    } else {
                        mVmsOaidSuc++;
                        return;
                    }
                case 9:
                    if (str == null) {
                        mVmsVaidFail++;
                        return;
                    } else {
                        mVmsVaidSuc++;
                        return;
                    }
                case 10:
                    if (str == null) {
                        mVmsAaidFail++;
                        return;
                    } else {
                        mVmsAaidSuc++;
                        return;
                    }
                default:
                    return;
            }
        }
    }

    private void startTimingTask() {
        Executors.newScheduledThreadPool(1).scheduleWithFixedDelay(new Runnable() { // from class: com.vivo.identifier.IdentifierIdClient.1
            {
                IdentifierIdClient.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (IdentifierIdClient.mOaidSuc + IdentifierIdClient.mOaidFail + IdentifierIdClient.mVmsOaidSuc + IdentifierIdClient.mVmsVaidFail + IdentifierIdClient.mVaidSuc + IdentifierIdClient.mVaidFail + IdentifierIdClient.mVmsVaidSuc + IdentifierIdClient.mVmsVaidFail + IdentifierIdClient.mAaidSuc + IdentifierIdClient.mAaidFail + IdentifierIdClient.mVmsAaidSuc + IdentifierIdClient.mVmsAaidFail > 0) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("oaid", IdentifierIdClient.this.getStringSplicing(IdentifierIdClient.mOaidSuc, IdentifierIdClient.mOaidFail, IdentifierIdClient.mVmsOaidSuc, IdentifierIdClient.mVmsOaidFail));
                    contentValues.put("vaid", IdentifierIdClient.this.getStringSplicing(IdentifierIdClient.mVaidSuc, IdentifierIdClient.mVaidFail, IdentifierIdClient.mVmsVaidSuc, IdentifierIdClient.mVmsVaidFail));
                    contentValues.put("aaid", IdentifierIdClient.this.getStringSplicing(IdentifierIdClient.mAaidSuc, IdentifierIdClient.mAaidFail, IdentifierIdClient.mVmsAaidSuc, IdentifierIdClient.mVmsAaidFail));
                    IdentifierIdClient.mDatabase.insert(7, "vivo", new ContentValues[]{contentValues});
                    int unused = IdentifierIdClient.mOaidSuc = IdentifierIdClient.mOaidFail = IdentifierIdClient.mVaidSuc = IdentifierIdClient.mVaidFail = IdentifierIdClient.mAaidSuc = IdentifierIdClient.mAaidFail = 0;
                    int unused2 = IdentifierIdClient.mVmsOaidSuc = IdentifierIdClient.mVmsOaidFail = IdentifierIdClient.mVmsVaidSuc = IdentifierIdClient.mVmsVaidFail = IdentifierIdClient.mVmsAaidSuc = IdentifierIdClient.mVmsAaidFail = 0;
                }
            }
        }, 600L, 600L, TimeUnit.SECONDS);
    }

    public List deleteOAIDBLACK(List<String> list) {
        if (this.mVMSVersion >= VMS_SUPPORT_OAID_BALCKLIST_MIN_VERSION && list != null && list.size() != 0) {
            try {
                ArrayList arrayList = new ArrayList();
                for (int i2 = 0; i2 < list.size(); i2++) {
                    String[] split = list.get(i2).split(":");
                    if (split.length == 2) {
                        arrayList.add(Boolean.valueOf(mDatabase.delete(6, "vivo", split[0], split[1])));
                    }
                }
                return arrayList;
            } catch (Exception unused) {
            }
        }
        return null;
    }

    public String getAAID() {
        String str = mAAID;
        if (str == null) {
            queryId(2, "vivo");
            if (mAAIDObserver == null) {
                initObserver(mContext, 2, "vivo");
            }
            str = mAAID;
        }
        setStatistics(2, str);
        return mAAID;
    }

    public String getAAIDNoDelay() {
        String str = mAAID;
        if (str == null) {
            queryIdNoDelay(2, "vivo");
            if (mAAIDObserver == null) {
                initObserver(mContext, 2, "vivo");
            }
            str = mAAID;
        }
        setStatistics(2, str);
        return mAAID;
    }

    public String getGUID() {
        String str = mGUID;
        if (str != null) {
            return str;
        }
        queryId(5, "vivo");
        return mGUID;
    }

    public String getGUIDNoDelay() {
        String str = mGUID;
        if (str != null) {
            return str;
        }
        queryIdNoDelay(5, "vivo");
        return mGUID;
    }

    public String getOAID() {
        String str = mOAID;
        if (str == null) {
            queryId(0, null);
            if (mOAIDObserver == null) {
                initObserver(mContext, 0, null);
            }
            str = mOAID;
        }
        setStatistics(0, str);
        return mOAID;
    }

    public String getOAIDNoDelay() {
        String str = mOAID;
        if (str == null) {
            queryIdNoDelay(0, null);
            if (mOAIDObserver == null) {
                initObserver(mContext, 0, null);
            }
            str = mOAID;
        }
        setStatistics(0, str);
        return mOAID;
    }

    public String getOAIDSTATUS() {
        queryId(4, null);
        return mOAIDStatus;
    }

    public String getUDID() {
        String str = mUDID;
        if (str != null) {
            return str;
        }
        queryId(3, null);
        return mUDID;
    }

    public String getVAID() {
        String str = mVAID;
        if (str == null) {
            queryId(1, "vivo");
            if (mVAIDObserver == null) {
                initObserver(mContext, 1, "vivo");
            }
            str = mVAID;
        }
        setStatistics(1, str);
        return mVAID;
    }

    public String getVAIDNoDelay() {
        String str = mVAID;
        if (str == null) {
            queryIdNoDelay(1, "vivo");
            if (mVAIDObserver == null) {
                initObserver(mContext, 1, "vivo");
            }
            str = mVAID;
        }
        setStatistics(1, str);
        return mVAID;
    }

    public boolean insertOAIDBLACK(List<String> list) {
        if (this.mVMSVersion >= VMS_SUPPORT_OAID_BALCKLIST_MIN_VERSION && list != null && list.size() != 0) {
            try {
                ContentValues[] contentValuesArr = new ContentValues[list.size()];
                String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
                for (int i2 = 0; i2 < list.size(); i2++) {
                    ContentValues contentValues = new ContentValues();
                    String[] split = list.get(i2).split(":");
                    if (split.length != 2) {
                        return false;
                    }
                    String str = split[0];
                    String str2 = split[1];
                    contentValues.put("packageName", str);
                    contentValues.put("uid", str2);
                    contentValues.put("value", format);
                    contentValuesArr[i2] = contentValues;
                }
                return mDatabase.insert(6, "vivo", contentValuesArr);
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public void sendMessageToDataBase(int i2, String str) {
        Message obtainMessage = mSqlHandler.obtainMessage();
        obtainMessage.what = 11;
        Bundle bundle = new Bundle();
        bundle.putInt("type", i2);
        if (i2 == 1 || i2 == 2 || i2 == 6) {
            bundle.putString("appid", str);
        }
        obtainMessage.setData(bundle);
        mSqlHandler.sendMessage(obtainMessage);
    }
}
