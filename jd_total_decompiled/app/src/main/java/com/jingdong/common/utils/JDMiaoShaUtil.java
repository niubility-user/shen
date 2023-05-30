package com.jingdong.common.utils;

import android.text.TextUtils;
import com.jingdong.common.entity.JDReminderNewEntity;
import com.jingdong.common.web.IRouterParams;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JDReminderConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.network.toolbox.FileService;
import com.jingdong.jdsdk.utils.MyCountdownTimer;
import com.jingdong.sdk.oklog.OKLog;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JDMiaoShaUtil {
    public static final int MIAOSHA_BEGINING = 2;
    public static final int MIAOSHA_FINISH = 3;
    public static final int MIAOSHA_WILLBEGIN = 1;
    public static final String TAG = "JDMiaoSha";
    private MyCountdownTimer myCountdownTimer;
    private int what = 2;
    private boolean isStop = true;

    /* loaded from: classes6.dex */
    public interface CountDownListener {
        void changed(MyCountdownTimer myCountdownTimer, long j2, long[] jArr, int i2);

        boolean finish(MyCountdownTimer myCountdownTimer, long j2, int i2);
    }

    public static void checkReminder(IRouterParams iRouterParams) {
        if (iRouterParams != null) {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            JSONArray jSONArray2 = new JSONArray();
            ArrayList<JDReminderNewEntity> allRemindersByBusinessTypeAndTime = JDReminderNewUtils.getAllRemindersByBusinessTypeAndTime(JDReminderConstant.BUSINESS_TYPE_SECKILL, getTwoDaysAgoTimeMillis());
            ArrayList<JDReminderNewEntity> allRemindersByBusinessTypeAndTime2 = JDReminderNewUtils.getAllRemindersByBusinessTypeAndTime(JDReminderConstant.BUSUNESS_TYPE_SECKILL_BRAND, getTwoDaysAgoTimeMillis());
            for (int i2 = 0; i2 < allRemindersByBusinessTypeAndTime.size(); i2++) {
                try {
                    jSONArray.put(allRemindersByBusinessTypeAndTime.get(i2).getIdentificationId() + CartConstant.KEY_YB_INFO_LINK + allRemindersByBusinessTypeAndTime.get(i2).getStartTimeMillis());
                } catch (Exception e2) {
                    e2.printStackTrace();
                    iRouterParams.onCallBack("1");
                    return;
                }
            }
            for (int i3 = 0; i3 < allRemindersByBusinessTypeAndTime2.size(); i3++) {
                jSONArray2.put(allRemindersByBusinessTypeAndTime2.get(i3).getIdentificationId());
            }
            jSONObject.put("wareId", jSONArray);
            jSONObject.put(JshopConst.JSHOP_ACTIVITY_IDS, jSONArray2);
            iRouterParams.onCallBack(jSONObject);
            return;
        }
        iRouterParams.onCallBack("1");
    }

    public static long getTwoDaysAgoTimeMillis() {
        return System.currentTimeMillis() - 172800000;
    }

    public static String getWareInfoStyle() {
        return readFileData(FileService.getDirectory(6).getDir().getAbsolutePath() + "/WareInfoStyle");
    }

    public static boolean isExistWareInfoStyle() {
        StringBuilder sb = new StringBuilder();
        sb.append(FileService.getDirectory(6).getDir().getAbsolutePath());
        sb.append("/WareInfoStyle");
        return !TextUtils.isEmpty(readFileData(sb.toString()));
    }

    private static String readFileData(String str) {
        FileInputStream fileInputStream = null;
        try {
            try {
                try {
                    if (TextUtils.isEmpty(str)) {
                        return "";
                    }
                    FileInputStream fileInputStream2 = new FileInputStream(str);
                    try {
                        String str2 = new String(readInputStream(fileInputStream2));
                        try {
                            fileInputStream2.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                        return str2;
                    } catch (FileNotFoundException e3) {
                        e = e3;
                        fileInputStream = fileInputStream2;
                        e.printStackTrace();
                        if (fileInputStream != null) {
                            fileInputStream.close();
                            return "";
                        }
                        return "";
                    } catch (Exception e4) {
                        e = e4;
                        fileInputStream = fileInputStream2;
                        e.printStackTrace();
                        if (fileInputStream != null) {
                            fileInputStream.close();
                            return "";
                        }
                        return "";
                    } catch (Throwable th) {
                        th = th;
                        fileInputStream = fileInputStream2;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (FileNotFoundException e6) {
                    e = e6;
                } catch (Exception e7) {
                    e = e7;
                }
            } catch (IOException e8) {
                e8.printStackTrace();
                return "";
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static byte[] readInputStream(FileInputStream fileInputStream) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    fileInputStream.close();
                    byteArrayOutputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void countdownCancel() {
        MyCountdownTimer myCountdownTimer = this.myCountdownTimer;
        if (myCountdownTimer != null) {
            this.isStop = true;
            myCountdownTimer.cancel(2);
            this.myCountdownTimer.cancel(1);
            this.myCountdownTimer.cancel(3);
        }
    }

    public long getCountdownTime(long j2, long j3) {
        if (j2 > 0) {
            this.what = 1;
            return j2;
        } else if (j3 > 0 && j2 < 0) {
            this.what = 2;
            return j3;
        } else if (j3 >= 0 || j2 >= 0) {
            return 0L;
        } else {
            this.what = 3;
            return 1L;
        }
    }

    public boolean isStop() {
        return this.isStop;
    }

    public void resetTime(long j2) {
        MyCountdownTimer myCountdownTimer = this.myCountdownTimer;
        if (myCountdownTimer == null || j2 <= 0) {
            return;
        }
        this.isStop = false;
        myCountdownTimer.reset(j2, 1000L, this.what);
    }

    public void setCountdown(long j2, final long j3, final CountDownListener countDownListener) {
        long countdownTime = getCountdownTime(j2, j3);
        OKLog.d("JDMiaoSha", " -->>setCountdown countdownTime=" + countdownTime);
        MyCountdownTimer myCountdownTimer = this.myCountdownTimer;
        if (myCountdownTimer == null) {
            this.myCountdownTimer = new MyCountdownTimer(countdownTime, 1000L, this.what) { // from class: com.jingdong.common.utils.JDMiaoShaUtil.1
                @Override // com.jingdong.jdsdk.utils.MyCountdownTimer
                public void onFinish(int i2) {
                    CountDownListener countDownListener2 = countDownListener;
                    if (countDownListener2 != null) {
                        countDownListener2.finish(this, j3, i2);
                    }
                    JDMiaoShaUtil.this.countdownCancel();
                }

                @Override // com.jingdong.jdsdk.utils.MyCountdownTimer
                public void onTick(long j4, int i2) {
                    long[] hms = JDMiaoShaUtil.this.toHMS(j4);
                    CountDownListener countDownListener2 = countDownListener;
                    if (countDownListener2 != null) {
                        countDownListener2.changed(this, j4, hms, i2);
                    }
                }
            }.start();
        } else {
            myCountdownTimer.reset(countdownTime, 1000L, this.what);
        }
        this.isStop = false;
    }

    public void setHMS(long j2) {
        MyCountdownTimer myCountdownTimer = this.myCountdownTimer;
        if (myCountdownTimer != null) {
            myCountdownTimer.reset(j2, 1000L, this.what);
        }
    }

    public long[] toHMS(long j2) {
        long j3 = j2 / 1000;
        long j4 = (j3 / 60) / 60;
        long j5 = j4 * 60 * 60;
        long j6 = ((j2 - (j5 * 1000)) / 1000) / 60;
        long j7 = (j3 - j5) - (60 * j6);
        if (j4 < 0) {
            j4 = 0;
        }
        if (j6 < 0) {
            j6 = 0;
        }
        if (j7 < 0) {
            j7 = 0;
        }
        return new long[]{j4, j6, j7};
    }
}
