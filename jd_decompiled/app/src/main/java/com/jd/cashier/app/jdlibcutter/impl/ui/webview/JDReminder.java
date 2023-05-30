package com.jd.cashier.app.jdlibcutter.impl.ui.webview;

import android.text.TextUtils;
import android.view.View;
import com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IReminder;
import com.jingdong.common.entity.JDReminderNewEntity;
import com.jingdong.common.messagepop.JDMessageNoticeManager;
import com.jingdong.common.utils.JDReminderNewUtils;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.jdsdk.constant.JshopConst;
import java.util.ArrayList;
import java.util.Collection;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class JDReminder implements IReminder {
    private void sendDataToM(View view, String str, String str2) {
        try {
            if (view instanceof JDWebView) {
                ((JDWebView) view).injectJs("javascript:" + str + "(" + str2 + ");");
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IReminder
    public void addReminder(View view, String str) {
        Exception exc;
        String str2;
        String str3;
        String str4 = "0";
        String str5 = "";
        int i2 = 0;
        i2 = 0;
        if (TextUtils.isEmpty(str)) {
            str3 = "";
        } else {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String optString = jSONObject.optString("businessType", "");
                String optString2 = jSONObject.optString("showTag", "");
                String optString3 = jSONObject.optString("remindTitle", "");
                String optString4 = jSONObject.optString(JshopConst.JSKEY_COUPON_BEGIN_TIME, "0");
                String optString5 = jSONObject.optString("uniqueId", "");
                String optString6 = jSONObject.optString("jumpStr", "");
                String optString7 = jSONObject.optString("imgUrl", "");
                String optString8 = jSONObject.optString("notifyTime", "0");
                String optString9 = jSONObject.optString("extraStr1", "");
                String optString10 = jSONObject.optString("extraStr2", "");
                String optString11 = jSONObject.optString("callBackName", "");
                try {
                    str5 = jSONObject.optString("callBackId", "");
                    if (TextUtils.isEmpty(optString4)) {
                        optString4 = "0";
                    }
                    if (!TextUtils.isEmpty(optString8)) {
                        str4 = optString8;
                    }
                    boolean reminder = JDReminderNewUtils.setReminder(new JDReminderNewEntity.ReminderBuilder(optString, optString2, optString5, optString3, Long.parseLong(optString4), optString6).extra(optString9).more(optString10).reminderImgUrl(optString7).notificationTimeMillis(Long.parseLong(str4)).build());
                    str3 = optString11;
                    i2 = reminder;
                } catch (Exception e2) {
                    exc = e2;
                    str2 = str5;
                    str5 = optString11;
                    exc.printStackTrace();
                    String str6 = str5;
                    str5 = str2;
                    str3 = str6;
                    sendDataToM(view, str3, i2 + ",'" + str5 + "'");
                }
            } catch (Exception e3) {
                exc = e3;
                str2 = "";
            }
        }
        sendDataToM(view, str3, i2 + ",'" + str5 + "'");
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IReminder
    public void checkNotificationEnable(View view, String str) {
        String str2;
        int i2 = 0;
        i2 = 0;
        if (TextUtils.isEmpty(str)) {
            str2 = "";
        } else {
            try {
                str2 = new JSONObject(str).optString("callBackName", "");
                try {
                    i2 = JDMessageNoticeManager.getInstance().isNotificationEnable();
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                    sendDataToM(view, str2, i2 + "");
                }
            } catch (Exception e3) {
                e = e3;
                str2 = "";
            }
        }
        sendDataToM(view, str2, i2 + "");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IReminder
    public void checkReminder(View view, String str) {
        String str2;
        String str3;
        JSONObject jSONObject;
        String optString;
        String optString2;
        String optString3;
        String str4 = "0";
        String str5 = "";
        int i2 = 0;
        i2 = 0;
        if (TextUtils.isEmpty(str)) {
            str3 = "";
        } else {
            try {
                jSONObject = new JSONObject(str);
                optString = jSONObject.optString("businessType", "");
                optString2 = jSONObject.optString(JshopConst.JSKEY_COUPON_BEGIN_TIME, "0");
                optString3 = jSONObject.optString("uniqueId", "");
                str3 = jSONObject.optString("callBackName", "");
            } catch (Exception e2) {
                e = e2;
                str2 = "";
            }
            try {
                str5 = jSONObject.optString("callBackId", "");
                if (!TextUtils.isEmpty(optString2)) {
                    str4 = optString2;
                }
                i2 = JDReminderNewUtils.checkReminder(optString, optString3, Long.parseLong(str4));
            } catch (Exception e3) {
                e = e3;
                str2 = str5;
                str5 = str3;
                e.printStackTrace();
                str3 = str5;
                str5 = str2;
                sendDataToM(view, str3, i2 + ",'" + str5 + "'");
            }
        }
        sendDataToM(view, str3, i2 + ",'" + str5 + "'");
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IReminder
    public void getAllRemindersWithTimeSpanAndBusinessType(View view, String str) {
        String str2;
        String str3;
        String str4 = "0";
        String str5 = "";
        if (TextUtils.isEmpty(str)) {
            str2 = "";
            str3 = str2;
        } else {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String optString = jSONObject.optString("businessType", "");
                String optString2 = jSONObject.optString("fromTime", "0");
                String optString3 = jSONObject.optString("toTime", "0");
                str3 = jSONObject.optString("callBackName", "");
                try {
                    str2 = jSONObject.optString("callBackId", "");
                    try {
                        if (TextUtils.isEmpty(optString2)) {
                            optString2 = "0";
                        }
                        if (!TextUtils.isEmpty(optString3)) {
                            str4 = optString3;
                        }
                        ArrayList<JDReminderNewEntity> allRemindersByBusinessTypeDuringTimePeriod = JDReminderNewUtils.getAllRemindersByBusinessTypeDuringTimePeriod(optString, Long.parseLong(optString2), Long.parseLong(str4));
                        if (allRemindersByBusinessTypeDuringTimePeriod != null && allRemindersByBusinessTypeDuringTimePeriod.size() > 0) {
                            ArrayList arrayList = new ArrayList();
                            for (JDReminderNewEntity jDReminderNewEntity : allRemindersByBusinessTypeDuringTimePeriod) {
                                if (jDReminderNewEntity != null) {
                                    arrayList.add(jDReminderNewEntity.getIdentificationId());
                                }
                            }
                            str5 = new JSONArray((Collection) arrayList).toString();
                        }
                    } catch (Exception e2) {
                        e = e2;
                        e.printStackTrace();
                        sendDataToM(view, str3, "'" + str5 + "','" + str2 + "'");
                    }
                } catch (Exception e3) {
                    e = e3;
                    str2 = "";
                }
            } catch (Exception e4) {
                e = e4;
                str2 = "";
                str3 = str2;
            }
        }
        sendDataToM(view, str3, "'" + str5 + "','" + str2 + "'");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IReminder
    public void removeReminder(View view, String str) {
        String str2;
        String str3;
        JSONObject jSONObject;
        String optString;
        String optString2;
        String optString3;
        String str4 = "0";
        String str5 = "";
        int i2 = 0;
        i2 = 0;
        if (TextUtils.isEmpty(str)) {
            str3 = "";
        } else {
            try {
                jSONObject = new JSONObject(str);
                optString = jSONObject.optString("businessType", "");
                optString2 = jSONObject.optString(JshopConst.JSKEY_COUPON_BEGIN_TIME, "0");
                optString3 = jSONObject.optString("uniqueId", "");
                str3 = jSONObject.optString("callBackName", "");
            } catch (Exception e2) {
                e = e2;
                str2 = "";
            }
            try {
                str5 = jSONObject.optString("callBackId", "");
                if (!TextUtils.isEmpty(optString2)) {
                    str4 = optString2;
                }
                i2 = JDReminderNewUtils.cancelReminder(optString, optString3, Long.parseLong(str4));
            } catch (Exception e3) {
                e = e3;
                str2 = str5;
                str5 = str3;
                e.printStackTrace();
                str3 = str5;
                str5 = str2;
                sendDataToM(view, str3, i2 + ",'" + str5 + "'");
            }
        }
        sendDataToM(view, str3, i2 + ",'" + str5 + "'");
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IReminder
    public void showPushOpenGuide(View view, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            String optString = new JSONObject(str).optString("scenesId", "");
            if (TextUtils.isEmpty(optString)) {
                return;
            }
            JDMessageNoticeManager.getInstance().showPushOpenGuide(optString);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
