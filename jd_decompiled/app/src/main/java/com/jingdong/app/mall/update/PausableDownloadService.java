package com.jingdong.app.mall.update;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.text.TextUtils;
import android.view.View;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.framework.network.filedown.JDFileService;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.update.view.InstallApkActivity;
import com.jingdong.common.ActivityNumController;
import com.jingdong.common.BaseApplication;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.database.table.VersionUpdataTable;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.DexAsyncUtil;
import com.jingdong.common.utils.FileUtils;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.common.utils.ProcessUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.FileGuider;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpRequest;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.jdupgrade.VersionEntity;
import com.jingdong.sdk.oklog.OKLog;
import de.greenrobot.event.EventBus;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class PausableDownloadService extends Service {
    private static final ArrayList<String> w = new ArrayList<>();

    /* renamed from: k  reason: collision with root package name */
    private HttpRequest f11698k;

    /* renamed from: m  reason: collision with root package name */
    private VersionEntity f11700m;

    /* renamed from: n  reason: collision with root package name */
    private String f11701n;
    private String o;
    private String p;
    private String q;
    private int r;
    private int s;
    private BroadcastReceiver t;
    private JDDialog u;

    /* renamed from: g  reason: collision with root package name */
    private File f11694g = null;

    /* renamed from: h  reason: collision with root package name */
    private NotificationManager f11695h = null;

    /* renamed from: i  reason: collision with root package name */
    private NotificationCompat.Builder f11696i = null;

    /* renamed from: j  reason: collision with root package name */
    private long f11697j = -1;

    /* renamed from: l  reason: collision with root package name */
    private boolean f11699l = false;
    private HttpGroup.OnAllAndPauseListener v = new c();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends BroadcastReceiver {
        a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            int intExtra = intent.getIntExtra("APP_UPDATE_COMMAND", 0);
            if (Log.D) {
                Log.d("PausableDownloadService", "onClickReceiver-command: " + intExtra);
                Log.d("PausableDownloadService", "onClickReceiver-intent.getAction: " + intent.getAction());
                Log.d("PausableDownloadService", "onClickReceiver-is failed: " + CommonBase.getBooleanFromPreference(com.jingdong.common.utils.ApplicationUpgradeHelper.APP_UPDATE_FAILED, Boolean.FALSE));
            }
            if (intent.getAction().equals("PausableDownloadService.Pause")) {
                if (Log.D) {
                    Log.d("PausableDownloadService", "stop updateBelt service");
                }
                PausableDownloadService.this.f11699l = true;
                PausableDownloadService.this.J();
            }
            if (intent.getAction().equals("PausableDownloadService.Download") || (intExtra == 1 && CommonBase.getBooleanFromPreference(com.jingdong.common.utils.ApplicationUpgradeHelper.APP_UPDATE_FAILED, Boolean.FALSE).booleanValue())) {
                if (Log.D) {
                    Log.d("PausableDownloadService", "start updateBelt service");
                }
                PausableDownloadService.this.f11699l = false;
                NotificationCompat.Builder builder = PausableDownloadService.this.f11696i;
                PausableDownloadService pausableDownloadService = PausableDownloadService.this;
                builder.setContentTitle(pausableDownloadService.B(pausableDownloadService.s)).setContentText(PausableDownloadService.this.getResources().getString(R.string.install_app_download_connectting));
                PausableDownloadService.this.f11695h.notify(1000, PausableDownloadService.this.f11696i.build());
                PausableDownloadService.this.z(HttpGroupUtils.getHttpGroupaAsynPool());
            }
            if (intent.getAction().equals("PausableDownloadService.Install")) {
                if (Log.D) {
                    Log.d("PausableDownloadService", "calling install apk activity");
                }
                PausableDownloadService.y(JdSdk.getInstance().getApplication());
                PausableDownloadService.this.I();
            }
            if (intExtra == 2) {
                PausableDownloadService.this.f11695h.cancel(1000);
                PausableDownloadService.this.stopSelf();
            }
            if (!PausableDownloadService.this.f11699l || intent.getAction().equals("PausableDownloadService.Pause")) {
                return;
            }
            PausableDownloadService.w.remove(PausableDownloadService.this.o);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ int f11702g;

        b(int i2) {
            this.f11702g = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            int i2 = Build.VERSION.SDK_INT >= 31 ? 67108864 : 0;
            int i3 = this.f11702g;
            if (i3 >= 0) {
                if (i3 == 100) {
                    NotificationCompat.Builder contentText = PausableDownloadService.this.f11696i.setContentText(PausableDownloadService.this.getResources().getString(R.string.upgrade_btn_finish_text));
                    PausableDownloadService pausableDownloadService = PausableDownloadService.this;
                    contentText.setContentIntent(PendingIntent.getActivity(pausableDownloadService, 1, pausableDownloadService.D(), i2));
                }
                PausableDownloadService.this.f11696i.setContentTitle(PausableDownloadService.this.B(this.f11702g)).setProgress(100, this.f11702g, false);
                PausableDownloadService.this.f11695h.notify(1000, PausableDownloadService.this.f11696i.build());
            }
        }
    }

    /* loaded from: classes4.dex */
    class c implements HttpGroup.OnAllAndPauseListener {

        /* loaded from: classes4.dex */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                ToastUtils.longToast(JdSdk.getInstance().getApplication().getApplicationContext(), PausableDownloadService.this.getResources().getString(R.string.install_app_download_no_wifi_text));
            }
        }

        c() {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            if (Log.D) {
                Log.d("PausableDownloadService", "downloadListener-onEnd()");
            }
            if (!TextUtils.isEmpty(PausableDownloadService.this.o)) {
                PausableDownloadService.w.remove(PausableDownloadService.this.o);
            }
            if (PausableDownloadService.this.f11700m != null && PausableDownloadService.this.f11700m.state == 303) {
                EventBus.getDefault().post(new com.jingdong.app.mall.update.a(2));
            }
            PausableDownloadService.this.K(100);
            File saveFile = httpResponse.getSaveFile();
            if (saveFile != null && saveFile.isFile()) {
                String absolutePath = saveFile.getAbsolutePath();
                PausableDownloadService.this.f11694g = new File(absolutePath);
                if (Log.D) {
                    Log.d("PausableDownloadService", "onEnd() mApkFilePath -->> " + absolutePath);
                }
                String currentMicrosecond = ExceptionReporter.getCurrentMicrosecond();
                CommonBase.putStringToPreference(com.jingdong.common.utils.ApplicationUpgradeHelper.APP_INSTALL_FILE, absolutePath);
                CommonBase.putStringToPreference("APP_INSTALL_CLICK_TS", currentMicrosecond);
                CommonBase.putStringToPreference(com.jingdong.common.utils.ApplicationUpgradeHelper.APP_UPDATE_CLICK_TS, PausableDownloadService.this.q);
                CommonBase.putStringToPreference(com.jingdong.common.utils.ApplicationUpgradeHelper.APP_URL, PausableDownloadService.this.o);
                CommonBase.putLongToPreference(com.jingdong.common.utils.ApplicationUpgradeHelper.APP_APK_SIZE, PausableDownloadService.this.f11697j);
                String fileMD5 = ApplicationUpgradeHelper.getFileMD5(saveFile);
                if (!TextUtils.isEmpty(fileMD5)) {
                    try {
                        VersionUpdataTable.insertVersion(PausableDownloadService.this.f11701n, fileMD5);
                    } catch (Exception e2) {
                        if (Log.D) {
                            e2.printStackTrace();
                        }
                    }
                }
                PausableDownloadService pausableDownloadService = PausableDownloadService.this;
                JDMtaUtils.onClick(pausableDownloadService, "WifiUpdate_DownloadSuccess", pausableDownloadService.getClass().getName());
                if (Log.D) {
                    Log.d("PausableDownloadService", "isAtLeastQ()=" + PausableDownloadService.this.F());
                }
                if (!PausableDownloadService.this.F()) {
                    PausableDownloadService.this.I();
                } else if (ProcessUtil.isForeground()) {
                    PausableDownloadService.this.I();
                }
            } else {
                PausableDownloadService pausableDownloadService2 = PausableDownloadService.this;
                JDMtaUtils.onClick(pausableDownloadService2, "WifiUpdate_DownloadFail", pausableDownloadService2.getClass().getName());
                ExceptionReporter.reportApplicationUpgradeEvent(PausableDownloadService.this.o, PausableDownloadService.this.q, ExceptionReporter.getCurrentMicrosecond(), "" + PausableDownloadService.this.f11697j, "0", "", "download success,but file not found");
            }
            CommonBase.putIntToPreference("jd_app_breakpoint_transmission", 0);
            CommonBase.putBooleanToPreference(com.jingdong.common.utils.ApplicationUpgradeHelper.APP_UPDATE_FAILED, Boolean.FALSE);
            CommonBase.putStringToPreference("jd_app_update_url", PausableDownloadService.this.o);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            int i2 = Build.VERSION.SDK_INT >= 31 ? 201326592 : 134217728;
            NotificationCompat.Builder builder = PausableDownloadService.this.f11696i;
            PausableDownloadService pausableDownloadService = PausableDownloadService.this;
            builder.setContentTitle(pausableDownloadService.B(pausableDownloadService.s)).setContentText(PausableDownloadService.this.getResources().getString(R.string.upgrade_btn_error_text)).setProgress(100, PausableDownloadService.this.s, false).setContentIntent(PendingIntent.getBroadcast(JdSdk.getInstance().getApplication(), 1, new Intent("PausableDownloadService.Download"), i2));
            PausableDownloadService.this.f11695h.notify(1000, PausableDownloadService.this.f11696i.build());
            if (Log.D) {
                Log.d("PausableDownloadService", "downloadListener-onError=" + httpError.toString());
            }
            if (!TextUtils.isEmpty(PausableDownloadService.this.o)) {
                PausableDownloadService.w.remove(PausableDownloadService.this.o);
            }
            CommonBase.putBooleanToPreference(com.jingdong.common.utils.ApplicationUpgradeHelper.APP_UPDATE_FAILED, Boolean.TRUE);
            PausableDownloadService.this.J();
            if (PausableDownloadService.this.f11700m == null || PausableDownloadService.this.f11700m.state != 303) {
                return;
            }
            EventBus.getDefault().post(new com.jingdong.app.mall.update.a(-1));
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnPauseListener
        public void onPause() {
            if (Log.D) {
                Log.d("PausableDownloadService", "downloadListener-onPause()");
            }
            int i2 = Build.VERSION.SDK_INT >= 31 ? 201326592 : 134217728;
            NotificationCompat.Builder builder = PausableDownloadService.this.f11696i;
            PausableDownloadService pausableDownloadService = PausableDownloadService.this;
            builder.setContentTitle(pausableDownloadService.B(pausableDownloadService.s)).setContentText(PausableDownloadService.this.getResources().getString(R.string.upgrade_btn_resume_text)).setProgress(100, PausableDownloadService.this.s, false).setContentIntent(PendingIntent.getBroadcast(JdSdk.getInstance().getApplication(), 1, new Intent("PausableDownloadService.Download"), i2));
            PausableDownloadService.this.f11695h.notify(1000, PausableDownloadService.this.f11696i.build());
            CommonBase.putBooleanToPreference(com.jingdong.common.utils.ApplicationUpgradeHelper.APP_UPDATE_FAILED, Boolean.FALSE);
            CommonBase.putIntToPreference("jd_app_breakpoint_transmission", PausableDownloadService.this.r);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
        public void onProgress(int i2, int i3) {
            if (Log.D) {
                Log.d("PausableDownloadService", "downloadListener-onProgress() max=" + i2 + " progress=" + i3 + " mApkSize=" + PausableDownloadService.this.f11697j);
            }
            PausableDownloadService.this.r = i3;
            CommonBase.putIntToPreference("jd_app_breakpoint_transmission", PausableDownloadService.this.r);
            int i4 = (int) ((i3 / ((float) PausableDownloadService.this.f11697j)) * 100.0f);
            if (Log.D) {
                Log.d("PausableDownloadService", "downloadListener-onProgress() calculate=" + i4);
            }
            if (i3 >= PausableDownloadService.this.f11697j) {
                i4 = 99;
            }
            if (i4 - PausableDownloadService.this.s > 0) {
                PausableDownloadService.this.s = i4;
                CommonBase.putIntToPreference("app_update_percent", PausableDownloadService.this.s);
                PausableDownloadService pausableDownloadService = PausableDownloadService.this;
                pausableDownloadService.K(pausableDownloadService.s);
            }
            if (PausableDownloadService.this.f11700m == null || PausableDownloadService.this.f11700m.state != 303) {
                return;
            }
            EventBus.getDefault().post(new com.jingdong.app.mall.update.a(1, 100.0f, PausableDownloadService.this.s));
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
        public void onStart() {
            if (Log.D) {
                Log.d("PausableDownloadService", "downloadListener-onStart()");
            }
            if (PausableDownloadService.this.f11700m != null && PausableDownloadService.this.f11700m.state == 303) {
                EventBus.getDefault().post(new com.jingdong.app.mall.update.a(1, 100.0f, PausableDownloadService.this.s));
            }
            int i2 = Build.VERSION.SDK_INT >= 31 ? 201326592 : 134217728;
            NotificationCompat.Builder builder = PausableDownloadService.this.f11696i;
            PausableDownloadService pausableDownloadService = PausableDownloadService.this;
            builder.setContentTitle(pausableDownloadService.B(pausableDownloadService.s)).setContentText(PausableDownloadService.this.getResources().getString(R.string.upgrade_btn_pause_text)).setProgress(100, PausableDownloadService.this.s, false).setContentIntent(PendingIntent.getBroadcast(JdSdk.getInstance().getApplication(), 1, new Intent("PausableDownloadService.Pause"), i2));
            PausableDownloadService.this.f11695h.notify(1000, PausableDownloadService.this.f11696i.build());
            if (NetUtils.isWifi()) {
                return;
            }
            BaseApplication.getHandler().post(new a());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class d implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ IMyActivity f11706g;

        /* loaded from: classes4.dex */
        class a implements View.OnClickListener {
            a() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PausableDownloadService.this.u.dismiss();
                d dVar = d.this;
                PausableDownloadService.this.A(dVar.f11706g);
            }
        }

        /* loaded from: classes4.dex */
        class b implements View.OnClickListener {
            b() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PausableDownloadService.this.u.dismiss();
                d dVar = d.this;
                PausableDownloadService.this.A(dVar.f11706g);
            }
        }

        d(IMyActivity iMyActivity) {
            this.f11706g = iMyActivity;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (PausableDownloadService.this.u == null || !PausableDownloadService.this.u.isShowing()) {
                PausableDownloadService.this.u = JDDialog.newJDDialog(this.f11706g.getThisActivity());
                PausableDownloadService.this.u.setContentView(R.layout.dialog_download_error);
                if (PausableDownloadService.this.u != null) {
                    PausableDownloadService.this.u.findViewById(R.id.download_error_close).setOnClickListener(new a());
                    PausableDownloadService.this.u.findViewById(R.id.download_error_button).setOnClickListener(new b());
                    PausableDownloadService.this.u.show();
                }
            }
        }
    }

    /* loaded from: classes4.dex */
    public class e extends Binder {
        public e(PausableDownloadService pausableDownloadService) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void A(IMyActivity iMyActivity) {
        VersionEntity versionEntity = this.f11700m;
        if (versionEntity == null || versionEntity.state != 303 || iMyActivity == null) {
            return;
        }
        iMyActivity.finish();
        ActivityNumController.exitActivityWithoutTop();
        BaseFrameUtil.exit(iMyActivity);
        com.jingdong.app.mall.n.c.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String B(int i2) {
        return getResources().getString(R.string.install_app_download_pre_text) + "(" + i2 + "%)";
    }

    @RequiresApi(api = 26)
    private String C() {
        NotificationChannel notificationChannel = new NotificationChannel("3", "UpdateNotificationChannel", 2);
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(-1);
        notificationChannel.setShowBadge(true);
        notificationChannel.enableVibration(false);
        notificationChannel.setSound(null, null);
        this.f11695h.createNotificationChannel(notificationChannel);
        return notificationChannel.getId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Intent D() {
        Intent intent = new Intent(this, InstallApkActivity.class);
        intent.putExtra("UpgradeEntity", this.f11700m);
        intent.putExtra("app_upgrade", this.p);
        return intent;
    }

    private void E() {
        this.f11695h = (NotificationManager) getSystemService(RemoteMessageConst.NOTIFICATION);
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 26) {
            this.f11696i = new NotificationCompat.Builder(JdSdk.getInstance().getApplicationContext(), C());
        } else {
            this.f11696i = new NotificationCompat.Builder(JdSdk.getInstance().getApplicationContext());
        }
        int intFromPreference = CommonBase.getIntFromPreference("app_update_percent", 0);
        this.f11695h.notify(1000, this.f11696i.setContentTitle(B(intFromPreference)).setContentText(getResources().getString(R.string.install_app_download_connectting)).setProgress(100, intFromPreference, false).setSmallIcon(R.drawable.android_jd_notification_new).setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.jd_buy_icon)).setContentIntent(PendingIntent.getActivity(this, 1, DexAsyncUtil.getMainFrameActivityIntent(this), i2 >= 31 ? 67108864 : 0)).build());
        this.t = new a();
        IntentFilter intentFilter = new IntentFilter("PausableDownloadService.Pause");
        intentFilter.addAction("PausableDownloadService.Download");
        intentFilter.addAction("PausableDownloadService.Install");
        intentFilter.addAction("com.jingdong.app.mall.update.PausableDownloadService");
        registerReceiver(this.t, intentFilter, Configuration.SLEF_BROADCAST_PERMISSION, null);
    }

    private void G() {
        if ((!this.f11699l && !CommonBase.getBooleanFromPreference(com.jingdong.common.utils.ApplicationUpgradeHelper.APP_UPDATE_FAILED, Boolean.FALSE).booleanValue()) || this.f11696i == null || this.f11695h == null) {
            return;
        }
        this.f11699l = false;
        int intFromPreference = CommonBase.getIntFromPreference("app_update_percent", 0);
        this.s = 0;
        if (Log.D) {
            Log.d("PausableDownloadService", "resumeDownload()-percent=" + intFromPreference);
        }
        this.f11696i.setContentTitle(B(intFromPreference)).setContentText(getResources().getString(R.string.install_app_download_connectting));
        this.f11695h.notify(1000, this.f11696i.build());
        z(HttpGroupUtils.getHttpGroupaAsynPool());
    }

    private void H() {
        IMyActivity currentMyActivity = BaseFrameUtil.getInstance().getCurrentMyActivity();
        if (currentMyActivity == null) {
            return;
        }
        currentMyActivity.post(new d(currentMyActivity));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void I() {
        Intent D = D();
        D.addFlags(268435456);
        startActivity(D);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void K(int i2) {
        if (Log.D) {
            Log.d("PausableDownloadService", "updateNoticeUI progress -->> " + i2);
        }
        BaseApplication.getHandler().post(new b(i2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void y(Context context) {
        Method method;
        try {
            Object systemService = context.getSystemService("statusbar");
            if (Build.VERSION.SDK_INT <= 16) {
                method = systemService.getClass().getMethod("collapse", new Class[0]);
            } else {
                method = systemService.getClass().getMethod("collapsePanels", new Class[0]);
            }
            method.invoke(systemService, new Object[0]);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z(HttpGroup httpGroup) {
        if (Log.D) {
            Log.d("PausableDownloadService", "download() -->> ");
        }
        try {
            ArrayList<String> arrayList = w;
            if (arrayList.size() == 0 && !TextUtils.isEmpty(this.o)) {
                arrayList.add(this.o);
            }
            FileGuider fileGuider = new FileGuider();
            fileGuider.setSpace(2);
            fileGuider.setImmutable(true);
            fileGuider.setFileName("jingdong_" + this.f11701n + ".apk");
            fileGuider.setMode(1);
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setUrl(this.o);
            httpSetting.setReferer("Download_apkUpgrade");
            httpSetting.setListener(this.v);
            httpSetting.setType(500);
            httpSetting.setSavePath(fileGuider);
            httpSetting.setBreakpointTransmission(true);
            int i2 = 0;
            httpSetting.setAttempts(0);
            int intFromPreference = CommonBase.getIntFromPreference("jd_app_breakpoint_transmission", 0);
            File filePath = JDFileService.getFilePath(fileGuider.getSpace(), JdSdk.getInstance().getApplicationContext(), fileGuider.getChildDirName(), "", fileGuider.getFileName());
            Log.i("PausableDownloadService", "fileSavePath=" + filePath.getAbsolutePath() + " length=" + filePath.length());
            if (filePath.length() < intFromPreference) {
                intFromPreference = 0;
            }
            Boolean bool = Boolean.FALSE;
            if (CommonBase.getBooleanFromPreference(com.jingdong.common.utils.ApplicationUpgradeHelper.APP_UPDATE_FAILED, bool).booleanValue() && CommonBase.getStringFromPreference(com.jingdong.common.utils.ApplicationUpgradeHelper.APP_URL, this.o).equals(this.o)) {
                this.s = 0;
            } else {
                i2 = intFromPreference;
            }
            Log.i("PausableDownloadService", "get break point when download: " + i2);
            httpSetting.setStartPosBreakpointTransmission(i2);
            this.f11698k = httpGroup.add(httpSetting);
            CommonBase.putBooleanToPreference(com.jingdong.common.utils.ApplicationUpgradeHelper.APP_UPDATE_FAILED, bool);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e("PausableDownloadService", e2);
            }
            try {
                ExceptionReporter.reportExceptionToBugly(new Exception("PausableDownloadService-downloadApk-" + e2));
                ArrayList<String> arrayList2 = w;
                if (arrayList2 != null) {
                    arrayList2.clear();
                }
                ApplicationUpgradeHelper.dismissUpdateDialog();
                ApplicationUpgradeHelper.dismissUpdateProgressDialog();
                CommonBase.putBooleanToPreference(com.jingdong.common.utils.ApplicationUpgradeHelper.APP_UPDATE_FAILED, Boolean.TRUE);
                H();
            } catch (Exception e3) {
                if (OKLog.E) {
                    OKLog.e("PausableDownloadService", e3);
                }
            }
        }
    }

    public boolean F() {
        String str = Build.VERSION.CODENAME;
        return (str.length() == 1 && str.charAt(0) >= 'Q' && str.charAt(0) <= 'Z') || Build.VERSION.SDK_INT > 28;
    }

    protected void J() {
        this.f11698k.stop();
        if (Log.D) {
            Log.d("PausableDownloadService", "stop() mProgress===" + this.r);
            Log.d("PausableDownloadService", "stop() mPercent===" + this.s);
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return new e(this);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        BroadcastReceiver broadcastReceiver = this.t;
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
    }

    @Override // android.app.Service
    @SuppressLint({"NewApi"})
    public int onStartCommand(Intent intent, int i2, int i3) {
        if (Log.D) {
            Log.d("PausableDownloadService", "onStartCommand-startId=" + i3);
        }
        if (intent == null) {
            return 0;
        }
        if (intent.getSerializableExtra("UpgradeEntity") instanceof VersionEntity) {
            VersionEntity versionEntity = (VersionEntity) intent.getSerializableExtra("UpgradeEntity");
            this.f11700m = versionEntity;
            if (versionEntity == null) {
                return 0;
            }
            String str = versionEntity.url;
            this.o = str;
            if (TextUtils.isEmpty(str)) {
                return 0;
            }
            String stringFromPreference = CommonBase.getStringFromPreference(com.jingdong.common.utils.ApplicationUpgradeHelper.APP_INSTALL_FILE, "");
            if (this.f11694g != null && FileUtils.fileIsExists(stringFromPreference)) {
                return 0;
            }
            ArrayList<String> arrayList = w;
            synchronized (arrayList) {
                if (!TextUtils.isEmpty(this.o)) {
                    if (Log.D) {
                        Log.d("PausableDownloadService", "onStartCommand-downLoadArray=" + arrayList.toString() + LangUtils.SINGLE_SPACE + arrayList.indexOf(this.o));
                    }
                    if (arrayList.indexOf(this.o) != -1) {
                        G();
                        return 0;
                    }
                    arrayList.add(this.o);
                }
                this.f11701n = this.f11700m.version;
                this.p = this.f11700m.state + "";
                this.f11697j = this.f11700m.size;
                CommonBase.getJdSharedPreferences().edit().putLong(com.jingdong.common.utils.ApplicationUpgradeHelper.APP_APK_SIZE, this.f11697j).putString("app_upgrade", this.p).putString(com.jingdong.common.utils.ApplicationUpgradeHelper.APP_URL, this.o).putString(com.jingdong.common.utils.ApplicationUpgradeHelper.APP_VERSION, this.f11701n).apply();
                this.q = intent.getStringExtra(com.jingdong.common.utils.ApplicationUpgradeHelper.APP_UPDATE_CLICK_TS);
                E();
                z(HttpGroupUtils.getHttpGroupaAsynPool());
            }
        }
        return super.onStartCommand(intent, i2, i3);
    }
}
