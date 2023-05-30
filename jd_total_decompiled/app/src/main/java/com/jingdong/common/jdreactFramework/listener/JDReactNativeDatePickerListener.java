package com.jingdong.common.jdreactFramework.listener;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.jdflutter.JDFlutterCall;
import com.jingdong.common.jdflutter.JDFlutterCallResult;
import com.jingdong.common.jdreactFramework.JDCallback;
import com.jingdong.common.jdreactFramework.R;
import com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper;
import com.jingdong.common.jdreactFramework.utils.AresCommonUtils;
import com.jingdong.common.jdreactFramework.utils.JLog;
import com.jingdong.common.jdreactFramework.utils.WheelView;
import com.jingdong.common.jdreactFramework.views.WheelViewLayout;
import com.jingdong.common.model.datetime.JDDateTimePickerDialog;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class JDReactNativeDatePickerListener implements NativeDatePickerListener, JDFlutterCall {
    public static final String DATEPICKERCHANNEL = "com.jd.jdflutter/datePicker";
    private static final String TAG = "JDReactNativeDatePickerListener";
    String firstData;
    String fourthData;
    String month;
    String secondData;
    String thirdData;
    String year;
    int maxMonth = -1;
    int maxYear = -1;
    int maxDay = -1;
    int minMonth = -1;
    int minYear = -1;
    int minDay = -1;
    String mode = JDDateTimePickerDialog.SELECT_DATE_MODE;

    private String getFromList(ArrayList<String> arrayList, int i2) {
        if (arrayList == null || arrayList.isEmpty()) {
            return null;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        if (i2 > arrayList.size() - 1) {
            i2 = arrayList.size() - 1;
        }
        return arrayList.get(i2);
    }

    public static int getMonthLastDay(int i2, int i3) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, i2);
        calendar.set(2, i3 - 1);
        calendar.set(5, 1);
        calendar.roll(5, -1);
        return calendar.get(5);
    }

    public static String getWeekday(int i2, int i3, int i4) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(i2, i3 - 1, i4);
        switch (calendar.get(7)) {
            case 1:
                return "\u5468\u65e5";
            case 2:
                return "\u5468\u4e00";
            case 3:
                return "\u5468\u4e8c";
            case 4:
                return "\u5468\u4e09";
            case 5:
                return "\u5468\u56db";
            case 6:
                return "\u5468\u4e94";
            case 7:
                return "\u5468\u516d";
            default:
                return "";
        }
    }

    public int getDayByMonth(ArrayList<String> arrayList, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        int i11;
        arrayList.clear();
        int monthLastDay = getMonthLastDay(i2, i9);
        if (i2 == i4 && i9 == i6) {
            i11 = i8;
            if (i10 >= i8) {
                i8 = i10;
            }
        } else {
            i8 = i10;
            i11 = 1;
        }
        if (i2 != i3 || i9 != i5) {
            i7 = monthLastDay;
        } else if (i8 > i7) {
            i8 = i7;
        }
        for (int i12 = i11; i12 <= i7; i12++) {
            arrayList.add(i12 + "");
        }
        if (i8 <= monthLastDay) {
            monthLastDay = i8;
        }
        int i13 = monthLastDay - i11;
        if (monthLastDay != -1) {
            int indexOf = arrayList.indexOf("" + monthLastDay);
            if (indexOf != -1) {
                i13 = indexOf;
            }
        }
        this.thirdData = "" + arrayList.get(i13);
        return i13;
    }

    public int[] getMonthDayByYear(ArrayList<String> arrayList, ArrayList<String> arrayList2, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        arrayList.clear();
        arrayList2.clear();
        int i16 = i9;
        if (i2 == i3) {
            if (i16 > i5) {
                i16 = i5;
            }
            i11 = i5;
        } else {
            i11 = 12;
        }
        if (i2 == i4) {
            if (i16 < i6) {
                i16 = i6;
            }
            i12 = i6;
        } else {
            i12 = 1;
        }
        while (i12 <= i11) {
            arrayList.add(i12 + "");
            i12++;
        }
        int indexOf = i16 != -1 ? arrayList.indexOf("" + i16) : 0;
        int monthLastDay = getMonthLastDay(i2, i16);
        if (i2 == i4 && i16 == i6) {
            i14 = i8;
            i13 = i10;
            if (i13 < i14) {
                i13 = i14;
            }
        } else {
            i13 = i10;
            i14 = 1;
        }
        if (i2 == i3 && i16 == i5) {
            i15 = i7;
            if (i13 > i15) {
                i13 = i15;
            }
        } else {
            i15 = monthLastDay;
        }
        for (int i17 = i14; i17 <= i15; i17++) {
            arrayList2.add(i17 + "");
        }
        if (i13 <= monthLastDay) {
            monthLastDay = i13;
        }
        int i18 = monthLastDay - i14;
        if (monthLastDay != -1) {
            int indexOf2 = arrayList2.indexOf("" + monthLastDay);
            if (indexOf2 != -1) {
                i18 = indexOf2;
            }
        }
        this.secondData = "" + arrayList.get(indexOf);
        this.thirdData = "" + arrayList2.get(i18);
        return new int[]{indexOf, i18};
    }

    @Override // com.jingdong.common.jdflutter.JDFlutterCall
    public void onMethodCall(String str, HashMap hashMap, final JDFlutterCallResult jDFlutterCallResult, Activity activity) {
        if (str.equals("showWheelPicker")) {
            showWheelPicker(activity, (ArrayList) hashMap.get("array"), hashMap.containsKey("defaultValue") ? (String) hashMap.get("defaultValue") : "", new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeDatePickerListener.14
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeDatePickerListener.15
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("showTimeWheelPicker")) {
            showTimeWheelPicker(activity, hashMap.containsKey("yearx") ? (String) hashMap.get("yearx") : "", hashMap.containsKey("monthx") ? (String) hashMap.get("monthx") : "", new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeDatePickerListener.16
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeDatePickerListener.17
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("showDatePicker")) {
            showDatePicker(activity, hashMap, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeDatePickerListener.18
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeDatePickerListener.19
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("showDatePicker2")) {
            showDatePicker2(activity, hashMap, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeDatePickerListener.20
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeDatePickerListener.21
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeDatePickerListener
    public void showDatePicker(Activity activity, HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        String str;
        int lastIndexOf;
        if (hashMap.containsKey(JDDateTimePickerDialog.SELECT_DATE_MODE)) {
            str = (String) hashMap.get(JDDateTimePickerDialog.SELECT_DATE_MODE);
            if (!TextUtils.isEmpty(str) && (lastIndexOf = (str = str.replace('T', ' ')).lastIndexOf(OrderISVUtil.MONEY_DECIMAL)) != -1) {
                str = str.substring(0, lastIndexOf);
            }
        } else {
            str = null;
        }
        showDatePicker(str, hashMap, jDCallback, jDCallback2);
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeDatePickerListener
    public void showDatePicker2(Activity activity, HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        String timestampToDateStr;
        if (hashMap.containsKey(JDDateTimePickerDialog.SELECT_DATE_MODE)) {
            try {
                timestampToDateStr = AresCommonUtils.timestampToDateStr(Long.parseLong((String) hashMap.get(JDDateTimePickerDialog.SELECT_DATE_MODE)));
            } catch (Exception unused) {
            }
            showDatePicker(timestampToDateStr, hashMap, jDCallback, jDCallback2);
        }
        timestampToDateStr = null;
        showDatePicker(timestampToDateStr, hashMap, jDCallback, jDCallback2);
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeDatePickerListener
    public void showTimeWheelPicker(Activity activity, String str, String str2, JDCallback jDCallback, JDCallback jDCallback2) {
        showTimeWheelPicker(str, str2, new HashMap(), jDCallback, jDCallback2);
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeDatePickerListener
    public void showTimeWheelPicker2(Activity activity, String str, String str2, HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        showTimeWheelPicker(str, str2, hashMap, jDCallback, jDCallback2);
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeDatePickerListener
    public void showWheelPicker(Activity activity, ArrayList arrayList, String str, JDCallback jDCallback, JDCallback jDCallback2) {
        showWheelPicker(arrayList, str, new HashMap(), jDCallback, jDCallback2);
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeDatePickerListener
    public void showWheelPicker2(Activity activity, ArrayList arrayList, String str, HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        showWheelPicker(arrayList, str, hashMap, jDCallback, jDCallback2);
    }

    private void showTimeWheelPicker(String str, String str2, HashMap hashMap, final JDCallback jDCallback, final JDCallback jDCallback2) {
        int indexOf;
        String str3;
        Activity currentMyActivity = AbstractJDReactInitialHelper.getCurrentMyActivity();
        try {
            final Dialog dialog = new Dialog(currentMyActivity, R.style.JDReactWheelViewDialogStyle);
            View inflate = LayoutInflater.from(currentMyActivity).inflate(R.layout.jdreact_time_picke_wheel_view_dialog_layout, (ViewGroup) null);
            Button button = (Button) inflate.findViewById(R.id.btn_cancel);
            String str4 = hashMap.containsKey("cancelButtonColor") ? (String) hashMap.get("cancelButtonColor") : null;
            if (str4 != null && str4.startsWith("#")) {
                try {
                    button.setTextColor(Color.parseColor(str4));
                } catch (Exception unused) {
                }
            }
            button.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeDatePickerListener.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    JDCallback jDCallback3 = jDCallback2;
                    if (jDCallback3 != null) {
                        jDCallback3.invoke(new Object[0]);
                    }
                    Dialog dialog2 = dialog;
                    if (dialog2 != null) {
                        dialog2.dismiss();
                    }
                }
            });
            Button button2 = (Button) inflate.findViewById(R.id.btn_ok);
            String str5 = hashMap.containsKey("confirmButtonColor") ? (String) hashMap.get("confirmButtonColor") : null;
            if (str5 != null && str5.startsWith("#")) {
                try {
                    button2.setTextColor(Color.parseColor(str5));
                } catch (Exception unused2) {
                }
            }
            ArrayList arrayList = new ArrayList();
            Calendar calendar = Calendar.getInstance();
            int i2 = calendar.get(1);
            int i3 = i2 + 100;
            for (int i4 = 1900; i4 < i3; i4++) {
                arrayList.add(i4 + "");
            }
            WheelView wheelView = (WheelView) inflate.findViewById(R.id.wheelView_year);
            int i5 = -1037525;
            if (hashMap.containsKey("selectedTextColor") && (str3 = (String) hashMap.get("selectedTextColor")) != null && str3.startsWith("#")) {
                String lowerCase = str3.replaceFirst("#", "").toLowerCase();
                if (lowerCase.length() == 6) {
                    lowerCase = "ff" + lowerCase;
                }
                i5 = AresCommonUtils.hexStrToInt(lowerCase, -1037525);
            }
            wheelView.setSelectedColor(i5);
            wheelView.setData(arrayList);
            int i6 = i2 - 1900;
            if (!TextUtils.isEmpty(str) && (indexOf = arrayList.indexOf(str)) != -1) {
                i6 = indexOf;
            }
            wheelView.setDefault(i6);
            this.year = (String) arrayList.get(i6);
            ArrayList arrayList2 = new ArrayList();
            for (int i7 = 1; i7 <= 12; i7++) {
                arrayList2.add("" + i7);
            }
            WheelView wheelView2 = (WheelView) inflate.findViewById(R.id.wheelView_month);
            wheelView2.setSelectedColor(i5);
            wheelView2.setData(arrayList2);
            int i8 = calendar.get(2);
            if (!TextUtils.isEmpty(str2) && (i8 = arrayList2.indexOf(str2)) == -1) {
                i8 = 0;
            }
            wheelView2.setDefault(i8);
            this.month = (String) arrayList2.get(i8);
            wheelView.setOnSelectListener(new WheelView.OnSelectListener() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeDatePickerListener.5
                @Override // com.jingdong.common.jdreactFramework.utils.WheelView.OnSelectListener
                public void endSelect(int i9, String str6) {
                    JDReactNativeDatePickerListener.this.year = str6;
                }

                @Override // com.jingdong.common.jdreactFramework.utils.WheelView.OnSelectListener
                public void selecting(int i9, String str6) {
                }
            });
            wheelView2.setOnSelectListener(new WheelView.OnSelectListener() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeDatePickerListener.6
                @Override // com.jingdong.common.jdreactFramework.utils.WheelView.OnSelectListener
                public void endSelect(int i9, String str6) {
                    JDReactNativeDatePickerListener.this.month = str6;
                }

                @Override // com.jingdong.common.jdreactFramework.utils.WheelView.OnSelectListener
                public void selecting(int i9, String str6) {
                }
            });
            button2.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeDatePickerListener.7
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    String str6 = JDReactNativeDatePickerListener.TAG;
                    JDReactNativeDatePickerListener jDReactNativeDatePickerListener = JDReactNativeDatePickerListener.this;
                    JLog.d(str6, String.format("year = %s, month = %s", jDReactNativeDatePickerListener.year, jDReactNativeDatePickerListener.month));
                    JDCallback jDCallback3 = jDCallback;
                    if (jDCallback3 != null) {
                        JDReactNativeDatePickerListener jDReactNativeDatePickerListener2 = JDReactNativeDatePickerListener.this;
                        jDCallback3.invoke(jDReactNativeDatePickerListener2.year, jDReactNativeDatePickerListener2.month);
                    }
                    Dialog dialog2 = dialog;
                    if (dialog2 != null) {
                        dialog2.dismiss();
                    }
                }
            });
            dialog.setContentView(inflate);
            Window window = dialog.getWindow();
            if (window != null) {
                window.setGravity(80);
                window.setLayout(-1, -2);
            }
            dialog.show();
        } catch (Exception e2) {
            JLog.e(TAG, e2.toString());
        }
    }

    private void showWheelPicker(ArrayList arrayList, String str, HashMap hashMap, final JDCallback jDCallback, final JDCallback jDCallback2) {
        String str2;
        Activity currentMyActivity = AbstractJDReactInitialHelper.getCurrentMyActivity();
        if (arrayList != null && arrayList.size() > 0 && currentMyActivity != null && !currentMyActivity.isFinishing()) {
            try {
                final Dialog dialog = new Dialog(currentMyActivity, R.style.JDReactWheelViewDialogStyle);
                View inflate = LayoutInflater.from(currentMyActivity).inflate(R.layout.jdreact_wheel_view_dialog_layout, (ViewGroup) null);
                dialog.setContentView(inflate);
                final WheelView wheelView = (WheelView) inflate.findViewById(R.id.wheelView);
                int i2 = -16777216;
                if (hashMap.containsKey("selectedTextColor") && (str2 = (String) hashMap.get("selectedTextColor")) != null && str2.startsWith("#")) {
                    String lowerCase = str2.replaceFirst("#", "").toLowerCase();
                    if (lowerCase.length() == 6) {
                        lowerCase = "ff" + lowerCase;
                    }
                    i2 = AresCommonUtils.hexStrToInt(lowerCase, -16777216);
                }
                wheelView.setSelectedColor(i2);
                ArrayList arrayList2 = new ArrayList();
                int i3 = 0;
                for (int i4 = 0; i4 < arrayList.size(); i4++) {
                    String str3 = (String) arrayList.get(i4);
                    if (str3 != null) {
                        arrayList2.add(str3);
                        if (str3.equals(str)) {
                            i3 = i4;
                        }
                    }
                }
                wheelView.setData(arrayList2);
                wheelView.setDefault(i3);
                Button button = (Button) inflate.findViewById(R.id.btn_cancel);
                String str4 = hashMap.containsKey("cancelButtonColor") ? (String) hashMap.get("cancelButtonColor") : null;
                if (str4 != null && str4.startsWith("#")) {
                    try {
                        button.setTextColor(Color.parseColor(str4));
                    } catch (Exception unused) {
                    }
                }
                button.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeDatePickerListener.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        jDCallback2.invoke(new Object[0]);
                        dialog.dismiss();
                    }
                });
                Button button2 = (Button) inflate.findViewById(R.id.btn_ok);
                String str5 = hashMap.containsKey("confirmButtonColor") ? (String) hashMap.get("confirmButtonColor") : null;
                if (str5 != null && str5.startsWith("#")) {
                    try {
                        button2.setTextColor(Color.parseColor(str5));
                    } catch (Exception unused2) {
                    }
                }
                button2.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeDatePickerListener.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        jDCallback.invoke(wheelView.getSelectedText());
                        dialog.dismiss();
                    }
                });
                Window window = dialog.getWindow();
                if (window != null) {
                    window.setGravity(80);
                    window.setLayout(-1, -2);
                }
                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeDatePickerListener.3
                    @Override // android.content.DialogInterface.OnCancelListener
                    public void onCancel(DialogInterface dialogInterface) {
                        JDCallback jDCallback3 = jDCallback2;
                        if (jDCallback3 != null) {
                            jDCallback3.invoke(new Object[0]);
                        }
                    }
                });
                dialog.show();
                return;
            } catch (Exception e2) {
                jDCallback2.invoke(new Object[0]);
                JLog.e(TAG, e2.toString());
                return;
            }
        }
        jDCallback2.invoke(new Object[0]);
    }

    /* JADX WARN: Removed duplicated region for block: B:133:0x0361  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x0509 A[Catch: Exception -> 0x0534, TryCatch #7 {Exception -> 0x0534, blocks: (B:224:0x0541, B:226:0x054e, B:228:0x0556, B:230:0x055e, B:232:0x056d, B:233:0x057e, B:234:0x0582, B:236:0x059a, B:167:0x0419, B:168:0x0422, B:170:0x0426, B:175:0x0439, B:183:0x044e, B:190:0x0481, B:191:0x048a, B:195:0x04a2, B:198:0x04bc, B:199:0x04d5, B:203:0x04e4, B:205:0x04fa, B:210:0x0509, B:212:0x051f), top: B:290:0x0419 }] */
    /* JADX WARN: Removed duplicated region for block: B:215:0x0527  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x052f  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x0530  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x055e A[Catch: Exception -> 0x0534, TryCatch #7 {Exception -> 0x0534, blocks: (B:224:0x0541, B:226:0x054e, B:228:0x0556, B:230:0x055e, B:232:0x056d, B:233:0x057e, B:234:0x0582, B:236:0x059a, B:167:0x0419, B:168:0x0422, B:170:0x0426, B:175:0x0439, B:183:0x044e, B:190:0x0481, B:191:0x048a, B:195:0x04a2, B:198:0x04bc, B:199:0x04d5, B:203:0x04e4, B:205:0x04fa, B:210:0x0509, B:212:0x051f), top: B:290:0x0419 }] */
    /* JADX WARN: Removed duplicated region for block: B:236:0x059a A[Catch: Exception -> 0x0534, TRY_LEAVE, TryCatch #7 {Exception -> 0x0534, blocks: (B:224:0x0541, B:226:0x054e, B:228:0x0556, B:230:0x055e, B:232:0x056d, B:233:0x057e, B:234:0x0582, B:236:0x059a, B:167:0x0419, B:168:0x0422, B:170:0x0426, B:175:0x0439, B:183:0x044e, B:190:0x0481, B:191:0x048a, B:195:0x04a2, B:198:0x04bc, B:199:0x04d5, B:203:0x04e4, B:205:0x04fa, B:210:0x0509, B:212:0x051f), top: B:290:0x0419 }] */
    /* JADX WARN: Removed duplicated region for block: B:242:0x05ac  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x05c4 A[Catch: Exception -> 0x05a6, TRY_ENTER, TRY_LEAVE, TryCatch #3 {Exception -> 0x05a6, blocks: (B:238:0x059f, B:246:0x05c4, B:250:0x05e3, B:253:0x05f2, B:255:0x0607, B:256:0x0610), top: B:282:0x059f }] */
    /* JADX WARN: Removed duplicated region for block: B:250:0x05e3 A[Catch: Exception -> 0x05a6, TRY_ENTER, TRY_LEAVE, TryCatch #3 {Exception -> 0x05a6, blocks: (B:238:0x059f, B:246:0x05c4, B:250:0x05e3, B:253:0x05f2, B:255:0x0607, B:256:0x0610), top: B:282:0x059f }] */
    /* JADX WARN: Removed duplicated region for block: B:253:0x05f2 A[Catch: Exception -> 0x05a6, TRY_ENTER, TryCatch #3 {Exception -> 0x05a6, blocks: (B:238:0x059f, B:246:0x05c4, B:250:0x05e3, B:253:0x05f2, B:255:0x0607, B:256:0x0610), top: B:282:0x059f }] */
    /* JADX WARN: Removed duplicated region for block: B:261:0x0664 A[Catch: Exception -> 0x0672, TryCatch #8 {Exception -> 0x0672, blocks: (B:259:0x062b, B:261:0x0664, B:262:0x066e), top: B:292:0x062b }] */
    /* JADX WARN: Removed duplicated region for block: B:272:0x067d  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01fb A[Catch: Exception -> 0x0025, TRY_ENTER, TRY_LEAVE, TryCatch #5 {Exception -> 0x0025, blocks: (B:5:0x001a, B:7:0x0022, B:14:0x006c, B:19:0x007a, B:24:0x0088, B:28:0x00a1, B:34:0x00c8, B:37:0x00d1, B:67:0x01fb, B:72:0x020b, B:77:0x022f, B:80:0x0239, B:43:0x0102, B:45:0x011a, B:47:0x012c, B:49:0x0130, B:55:0x0168, B:57:0x0184, B:58:0x0188, B:60:0x0192, B:62:0x01ac, B:63:0x01b0, B:50:0x0141), top: B:286:0x001a }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0204  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x022f A[Catch: Exception -> 0x0025, TRY_ENTER, TryCatch #5 {Exception -> 0x0025, blocks: (B:5:0x001a, B:7:0x0022, B:14:0x006c, B:19:0x007a, B:24:0x0088, B:28:0x00a1, B:34:0x00c8, B:37:0x00d1, B:67:0x01fb, B:72:0x020b, B:77:0x022f, B:80:0x0239, B:43:0x0102, B:45:0x011a, B:47:0x012c, B:49:0x0130, B:55:0x0168, B:57:0x0184, B:58:0x0188, B:60:0x0192, B:62:0x01ac, B:63:0x01b0, B:50:0x0141), top: B:286:0x001a }] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0236  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0239 A[Catch: Exception -> 0x0025, TRY_LEAVE, TryCatch #5 {Exception -> 0x0025, blocks: (B:5:0x001a, B:7:0x0022, B:14:0x006c, B:19:0x007a, B:24:0x0088, B:28:0x00a1, B:34:0x00c8, B:37:0x00d1, B:67:0x01fb, B:72:0x020b, B:77:0x022f, B:80:0x0239, B:43:0x0102, B:45:0x011a, B:47:0x012c, B:49:0x0130, B:55:0x0168, B:57:0x0184, B:58:0x0188, B:60:0x0192, B:62:0x01ac, B:63:0x01b0, B:50:0x0141), top: B:286:0x001a }] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0268 A[Catch: Exception -> 0x035b, TRY_ENTER, TryCatch #0 {Exception -> 0x035b, blocks: (B:86:0x0268, B:88:0x0273, B:89:0x028a, B:91:0x028f, B:93:0x02a5, B:94:0x02a7, B:107:0x02ca, B:108:0x02e3, B:110:0x02e8, B:112:0x02fe, B:113:0x0300, B:123:0x031e, B:124:0x0333, B:126:0x0337, B:140:0x037e, B:143:0x0396, B:146:0x03b0, B:148:0x03c6, B:154:0x03d3, B:156:0x03e9, B:158:0x03ee, B:172:0x0432, B:177:0x043d), top: B:277:0x0266 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void showDatePicker(String str, HashMap hashMap, final JDCallback jDCallback, final JDCallback jDCallback2) {
        JDCallback jDCallback3;
        int i2;
        int i3;
        String str2;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        Object obj;
        Object obj2;
        int i13;
        int i14;
        int i15;
        Button button;
        WheelViewLayout wheelViewLayout;
        String str3;
        String str4;
        String str5;
        View view;
        int i16;
        int i17;
        ArrayList<String> arrayList;
        int i18;
        int i19;
        int i20;
        int i21;
        int i22;
        int i23;
        int i24;
        int i25;
        int i26;
        int i27;
        Object obj3;
        JDReactNativeDatePickerListener jDReactNativeDatePickerListener;
        Window window;
        String str6;
        String str7;
        ArrayList<String> arrayList2;
        int i28;
        int i29;
        JDReactNativeDatePickerListener jDReactNativeDatePickerListener2 = this;
        try {
            if (hashMap.containsKey(CustomThemeConstance.NAVI_MODEL)) {
                try {
                    String str8 = (String) hashMap.get(CustomThemeConstance.NAVI_MODEL);
                    if (str8 != null) {
                        jDReactNativeDatePickerListener2.mode = str8;
                    }
                } catch (Exception e2) {
                    e = e2;
                    jDCallback3 = jDCallback2;
                    if (jDCallback3 != null) {
                    }
                    JLog.e(TAG, e.toString());
                    return;
                }
            }
            jDReactNativeDatePickerListener2.firstData = null;
            jDReactNativeDatePickerListener2.secondData = null;
            jDReactNativeDatePickerListener2.thirdData = null;
            jDReactNativeDatePickerListener2.fourthData = null;
            jDReactNativeDatePickerListener2.maxMonth = -1;
            jDReactNativeDatePickerListener2.maxYear = -1;
            jDReactNativeDatePickerListener2.maxDay = -1;
            jDReactNativeDatePickerListener2.minMonth = -1;
            jDReactNativeDatePickerListener2.minYear = -1;
            jDReactNativeDatePickerListener2.minDay = -1;
            Calendar calendar = Calendar.getInstance();
            final int i30 = calendar.get(2) + 1;
            int i31 = calendar.get(1);
            final int i32 = calendar.get(5);
            int i33 = calendar.get(11);
            int i34 = calendar.get(10);
            int i35 = calendar.get(12);
            String str9 = hashMap.containsKey("minDate") ? (String) hashMap.get("minDate") : null;
            String str10 = hashMap.containsKey("maxDate") ? (String) hashMap.get("maxDate") : null;
            if (str9 == null) {
                str9 = "1900-01-01";
            }
            if (str10 == null) {
                str10 = (i31 + 100) + "-12-31";
            }
            if (str == null) {
                i2 = i33;
                i3 = i34;
                str2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            } else {
                i2 = i33;
                i3 = i34;
                str2 = str;
            }
            int i36 = i35;
            int i37 = i31;
            try {
                try {
                    try {
                        if (!jDReactNativeDatePickerListener2.mode.equals("datetime") && !jDReactNativeDatePickerListener2.mode.equals(JDDateTimePickerDialog.SELECT_DATE_MODE)) {
                            if (jDReactNativeDatePickerListener2.mode.equals("time")) {
                                i10 = i3;
                                i11 = i36;
                                i9 = i37;
                                i14 = -1;
                                obj2 = "datetime";
                                i12 = i2;
                                i15 = -1;
                                obj = "selectedTextColor";
                                i13 = -1;
                            } else {
                                i10 = i3;
                                i11 = i36;
                                i9 = i37;
                                i14 = i9;
                                obj2 = "datetime";
                                i15 = i32;
                                i12 = i2;
                                obj = "selectedTextColor";
                                i13 = i30;
                            }
                            Activity currentMyActivity = AbstractJDReactInitialHelper.getCurrentMyActivity();
                            int i38 = i11;
                            int i39 = i10;
                            final Dialog dialog = new Dialog(currentMyActivity, R.style.JDReactWheelViewDialogStyle);
                            View inflate = LayoutInflater.from(currentMyActivity).inflate(R.layout.jdreact_date_time_picker_view_dialog_layout, (ViewGroup) null);
                            WheelViewLayout wheelViewLayout2 = (WheelViewLayout) inflate.findViewById(R.id.container);
                            button = (Button) inflate.findViewById(R.id.btn_cancel);
                            if (hashMap.containsKey("cancelButtonColor")) {
                                wheelViewLayout = wheelViewLayout2;
                                str3 = null;
                            } else {
                                str3 = (String) hashMap.get("cancelButtonColor");
                                wheelViewLayout = wheelViewLayout2;
                            }
                            if (str3 != null && str3.startsWith("#")) {
                                try {
                                    button.setTextColor(Color.parseColor(str3));
                                } catch (Exception unused) {
                                }
                            }
                            button.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeDatePickerListener.8
                                @Override // android.view.View.OnClickListener
                                public void onClick(View view2) {
                                    JDCallback jDCallback4 = jDCallback2;
                                    if (jDCallback4 != null) {
                                        jDCallback4.invoke(new Object[0]);
                                    }
                                    Dialog dialog2 = dialog;
                                    if (dialog2 != null) {
                                        dialog2.dismiss();
                                    }
                                }
                            });
                            Button button2 = (Button) inflate.findViewById(R.id.btn_ok);
                            str4 = !hashMap.containsKey("confirmButtonColor") ? (String) hashMap.get("confirmButtonColor") : null;
                            if (str4 != null) {
                                if (str4.startsWith("#")) {
                                    try {
                                        button2.setTextColor(Color.parseColor(str4));
                                    } catch (Exception unused2) {
                                    }
                                }
                            }
                            ArrayList<String> arrayList3 = new ArrayList<>();
                            final ArrayList<String> arrayList4 = new ArrayList<>();
                            final ArrayList<String> arrayList5 = new ArrayList<>();
                            ArrayList<String> arrayList6 = new ArrayList<>();
                            if (!jDReactNativeDatePickerListener2.mode.equals(JDDateTimePickerDialog.SELECT_DATE_MODE)) {
                                int i40 = jDReactNativeDatePickerListener2.minYear;
                                view = inflate;
                                int i41 = jDReactNativeDatePickerListener2.maxYear;
                                str5 = "#";
                                for (int i42 = i40; i42 <= i41; i42++) {
                                    arrayList3.add(i42 + "");
                                }
                                int i43 = i9 - i40;
                                if (i14 != -1) {
                                    i43 = arrayList3.indexOf("" + i14);
                                    if (i43 == -1) {
                                        i43 = i14 - i40;
                                    }
                                }
                                int i44 = jDReactNativeDatePickerListener2.minMonth;
                                int i45 = i43;
                                int i46 = jDReactNativeDatePickerListener2.maxMonth;
                                if (i14 == i41) {
                                    if (i13 > i46) {
                                        i13 = i46;
                                    }
                                    arrayList2 = arrayList6;
                                    i28 = i46;
                                } else {
                                    arrayList2 = arrayList6;
                                    i28 = 12;
                                }
                                if (i14 == i40) {
                                    if (i13 < i44) {
                                        i13 = i44;
                                    }
                                    i29 = i44;
                                } else {
                                    i29 = 1;
                                }
                                int i47 = i9;
                                int i48 = i29;
                                while (i48 <= i28) {
                                    arrayList4.add(i48 + "");
                                    i48++;
                                    i28 = i28;
                                }
                                int i49 = i30 - i29;
                                if (i14 != -1) {
                                    i49 = arrayList4.indexOf("" + i13);
                                    if (i49 == -1) {
                                        i49 = i13 - i29;
                                    }
                                }
                                int monthLastDay = getMonthLastDay(i14, i13);
                                int i50 = i49;
                                int i51 = jDReactNativeDatePickerListener2.minDay;
                                int i52 = jDReactNativeDatePickerListener2.maxDay;
                                int i53 = (i14 == i40 && i13 == i44) ? i51 : 1;
                                if (i14 == i41 && i13 == i46) {
                                    monthLastDay = i52;
                                }
                                for (int i54 = i53; i54 <= monthLastDay; i54++) {
                                    arrayList5.add(i54 + "");
                                }
                                i19 = i32 - i53;
                                if (i15 != 0) {
                                    i19 = arrayList4.indexOf("" + i15);
                                    if (i19 == -1) {
                                        i19 = i15 - i53;
                                    }
                                }
                                i16 = i45;
                                i25 = i50;
                                arrayList = arrayList2;
                                i17 = i47;
                                i24 = -1;
                            } else {
                                str5 = "#";
                                ArrayList<String> arrayList7 = arrayList6;
                                view = inflate;
                                int i55 = i9;
                                if (jDReactNativeDatePickerListener2.mode.equals("time")) {
                                    for (int i56 = 1; i56 <= 12; i56++) {
                                        arrayList3.add(i56 + "");
                                    }
                                    for (int i57 = 0; i57 < 60; i57++) {
                                        arrayList4.add(i57 + "");
                                    }
                                    if (i39 != -1) {
                                        int indexOf = arrayList3.indexOf("" + i39);
                                        i27 = indexOf == -1 ? i39 - 1 : indexOf;
                                        i26 = i38;
                                    } else {
                                        i26 = i38;
                                        i27 = -1;
                                    }
                                    if (i26 != -1) {
                                        i25 = arrayList4.indexOf("" + i26);
                                        if (i25 == -1) {
                                            i25 = i26 - 1;
                                        }
                                    } else {
                                        i25 = -1;
                                    }
                                    arrayList5.add("\u4e0a\u5348");
                                    arrayList5.add("\u4e0b\u5348");
                                    arrayList = arrayList7;
                                    i17 = i55;
                                    i24 = -1;
                                    i16 = i27;
                                    i19 = i12 > 12 ? 1 : 0;
                                } else {
                                    int i58 = i12;
                                    int i59 = i38;
                                    if (jDReactNativeDatePickerListener2.mode.equals(obj2)) {
                                        try {
                                            int i60 = jDReactNativeDatePickerListener2.minMonth;
                                            i16 = -1;
                                            int i61 = 0;
                                            int i62 = 0;
                                            while (i60 <= jDReactNativeDatePickerListener2.maxMonth) {
                                                int i63 = i55;
                                                int monthLastDay2 = getMonthLastDay(i63, i60);
                                                int i64 = i59;
                                                int i65 = i60 == jDReactNativeDatePickerListener2.minMonth ? jDReactNativeDatePickerListener2.minDay : 1;
                                                int i66 = i60 == jDReactNativeDatePickerListener2.maxMonth ? jDReactNativeDatePickerListener2.maxDay : monthLastDay2;
                                                int i67 = i62;
                                                int i68 = i61;
                                                int i69 = i16;
                                                int i70 = i65;
                                                while (i70 <= i66) {
                                                    int i71 = i66;
                                                    arrayList3.add("" + i60 + "\u6708" + i70 + "\u65e5" + getWeekday(i63, i60, i70));
                                                    if (i60 == i13 && i70 == i15) {
                                                        i69 = i68;
                                                    } else if (i60 == i30 && i70 == i32) {
                                                        i67 = i68;
                                                    }
                                                    i68++;
                                                    i70++;
                                                    i66 = i71;
                                                }
                                                i60++;
                                                jDReactNativeDatePickerListener2 = this;
                                                i16 = i69;
                                                i61 = i68;
                                                i62 = i67;
                                                i59 = i64;
                                                i55 = i63;
                                            }
                                            i17 = i55;
                                            int i72 = i59;
                                            for (int i73 = 1; i73 <= 12; i73++) {
                                                arrayList5.add(i73 + "");
                                            }
                                            int i74 = 0;
                                            while (i74 < 60) {
                                                ArrayList<String> arrayList8 = arrayList7;
                                                arrayList8.add(i74 + "");
                                                i74++;
                                                arrayList7 = arrayList8;
                                            }
                                            arrayList = arrayList7;
                                            arrayList4.add("\u4e0a\u5348");
                                            arrayList4.add("\u4e0b\u5348");
                                            if (i16 == -1) {
                                                i16 = i62;
                                            }
                                            if (i39 != -1) {
                                                int indexOf2 = arrayList5.indexOf("" + i39);
                                                i20 = -1;
                                                if (indexOf2 == -1) {
                                                    i19 = i39 - 1;
                                                    i18 = i72;
                                                } else {
                                                    i19 = indexOf2;
                                                    i18 = i72;
                                                    if (i18 == i20) {
                                                        int indexOf3 = arrayList.indexOf("" + i18);
                                                        if (indexOf3 == -1) {
                                                            i21 = 1;
                                                            i23 = i18 - 1;
                                                        } else {
                                                            i21 = 1;
                                                            i23 = indexOf3;
                                                        }
                                                        i22 = i58;
                                                    } else {
                                                        i21 = 1;
                                                        i22 = i58;
                                                        i23 = -1;
                                                    }
                                                    if (i22 > 12) {
                                                        i21 = 0;
                                                    }
                                                    i24 = i23;
                                                    i25 = i21;
                                                }
                                            } else {
                                                i18 = i72;
                                                i19 = -1;
                                            }
                                            i20 = -1;
                                            if (i18 == i20) {
                                            }
                                            if (i22 > 12) {
                                            }
                                            i24 = i23;
                                            i25 = i21;
                                        } catch (Exception e3) {
                                            e = e3;
                                            jDCallback3 = jDCallback2;
                                            if (jDCallback3 != null) {
                                            }
                                            JLog.e(TAG, e.toString());
                                            return;
                                        }
                                    } else {
                                        arrayList = arrayList7;
                                        i17 = i55;
                                        i19 = -1;
                                        i25 = -1;
                                        i24 = -1;
                                        i16 = -1;
                                    }
                                }
                            }
                            int i75 = -1037525;
                            obj3 = obj;
                            if (hashMap.containsKey(obj3) && (str6 = (String) hashMap.get(obj3)) != null) {
                                str7 = str5;
                                if (str6.startsWith(str7)) {
                                    String lowerCase = str6.replaceFirst(str7, "").toLowerCase();
                                    if (lowerCase.length() == 6) {
                                        lowerCase = "ff" + lowerCase;
                                    }
                                    i75 = AresCommonUtils.hexStrToInt(lowerCase, -1037525);
                                }
                            }
                            View view2 = view;
                            WheelView wheelView = (WheelView) view2.findViewById(R.id.wheelView_first);
                            wheelView.setVisibility(0);
                            wheelView.setSelectedColor(i75);
                            wheelView.setData(arrayList3);
                            if (i16 == -1) {
                                wheelView.setDefault(i16);
                                jDReactNativeDatePickerListener = this;
                                try {
                                    jDReactNativeDatePickerListener.firstData = jDReactNativeDatePickerListener.getFromList(arrayList3, i16);
                                } catch (Exception e4) {
                                    e = e4;
                                    jDCallback3 = jDCallback2;
                                    if (jDCallback3 != null) {
                                        jDCallback3.invoke(new Object[0]);
                                    }
                                    JLog.e(TAG, e.toString());
                                    return;
                                }
                            } else {
                                jDReactNativeDatePickerListener = this;
                            }
                            final WheelView wheelView2 = (WheelView) view2.findViewById(R.id.wheelView_second);
                            wheelView2.setVisibility(0);
                            wheelView2.setSelectedColor(i75);
                            wheelView2.setData(arrayList4);
                            if (i25 != -1) {
                                wheelView2.setDefault(i25);
                                jDReactNativeDatePickerListener.secondData = jDReactNativeDatePickerListener.getFromList(arrayList4, i25);
                            }
                            final WheelView wheelView3 = (WheelView) view2.findViewById(R.id.wheelView_third);
                            wheelView3.setVisibility(0);
                            wheelView3.setSelectedColor(i75);
                            wheelView3.setData(arrayList5);
                            if (i19 != -1) {
                                wheelView3.setDefault(i19);
                                jDReactNativeDatePickerListener.thirdData = jDReactNativeDatePickerListener.getFromList(arrayList5, i19);
                            }
                            if (arrayList.size() != 0) {
                                WheelView wheelView4 = (WheelView) view2.findViewById(R.id.wheelView_fourth);
                                wheelView4.setVisibility(0);
                                wheelView4.setSelectedColor(i75);
                                wheelView4.setData(arrayList);
                                if (i24 != -1) {
                                    wheelView4.setDefault(i24);
                                    jDReactNativeDatePickerListener.fourthData = jDReactNativeDatePickerListener.getFromList(arrayList, i24);
                                }
                                wheelView4.setOnSelectListener(new WheelView.OnSelectListener() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeDatePickerListener.9
                                    @Override // com.jingdong.common.jdreactFramework.utils.WheelView.OnSelectListener
                                    public void endSelect(int i76, String str11) {
                                        JDReactNativeDatePickerListener.this.fourthData = str11;
                                    }

                                    @Override // com.jingdong.common.jdreactFramework.utils.WheelView.OnSelectListener
                                    public void selecting(int i76, String str11) {
                                    }
                                });
                            }
                            final WheelViewLayout wheelViewLayout3 = wheelViewLayout;
                            JDReactNativeDatePickerListener jDReactNativeDatePickerListener3 = jDReactNativeDatePickerListener;
                            wheelView.setOnSelectListener(new WheelView.OnSelectListener() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeDatePickerListener.10
                                @Override // com.jingdong.common.jdreactFramework.utils.WheelView.OnSelectListener
                                public void endSelect(int i76, String str11) {
                                    if (str11 == null || !str11.equals(JDReactNativeDatePickerListener.this.firstData)) {
                                        JDReactNativeDatePickerListener jDReactNativeDatePickerListener4 = JDReactNativeDatePickerListener.this;
                                        jDReactNativeDatePickerListener4.firstData = str11;
                                        if (jDReactNativeDatePickerListener4.mode.equals(JDDateTimePickerDialog.SELECT_DATE_MODE)) {
                                            JDReactNativeDatePickerListener jDReactNativeDatePickerListener5 = JDReactNativeDatePickerListener.this;
                                            ArrayList<String> arrayList9 = arrayList4;
                                            ArrayList<String> arrayList10 = arrayList5;
                                            int intValue = Integer.valueOf(jDReactNativeDatePickerListener5.firstData).intValue();
                                            JDReactNativeDatePickerListener jDReactNativeDatePickerListener6 = JDReactNativeDatePickerListener.this;
                                            int[] monthDayByYear = jDReactNativeDatePickerListener5.getMonthDayByYear(arrayList9, arrayList10, intValue, jDReactNativeDatePickerListener6.maxYear, jDReactNativeDatePickerListener6.minYear, jDReactNativeDatePickerListener6.maxMonth, jDReactNativeDatePickerListener6.minMonth, jDReactNativeDatePickerListener6.maxDay, jDReactNativeDatePickerListener6.minDay, Integer.valueOf(jDReactNativeDatePickerListener6.secondData).intValue(), Integer.valueOf(JDReactNativeDatePickerListener.this.thirdData).intValue());
                                            wheelView2.setData(arrayList4);
                                            wheelView3.setData(arrayList5);
                                            wheelViewLayout3.refreshWheelView(new WheelView[]{wheelView2, wheelView3}, monthDayByYear);
                                        }
                                    }
                                }

                                @Override // com.jingdong.common.jdreactFramework.utils.WheelView.OnSelectListener
                                public void selecting(int i76, String str11) {
                                }
                            });
                            wheelView2.setOnSelectListener(new WheelView.OnSelectListener() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeDatePickerListener.11
                                @Override // com.jingdong.common.jdreactFramework.utils.WheelView.OnSelectListener
                                public void endSelect(int i76, String str11) {
                                    if (str11 == null || !str11.equals(JDReactNativeDatePickerListener.this.secondData)) {
                                        JDReactNativeDatePickerListener jDReactNativeDatePickerListener4 = JDReactNativeDatePickerListener.this;
                                        jDReactNativeDatePickerListener4.secondData = str11;
                                        if (jDReactNativeDatePickerListener4.mode.equals(JDDateTimePickerDialog.SELECT_DATE_MODE)) {
                                            JDReactNativeDatePickerListener jDReactNativeDatePickerListener5 = JDReactNativeDatePickerListener.this;
                                            ArrayList<String> arrayList9 = arrayList5;
                                            int intValue = Integer.valueOf(jDReactNativeDatePickerListener5.firstData).intValue();
                                            JDReactNativeDatePickerListener jDReactNativeDatePickerListener6 = JDReactNativeDatePickerListener.this;
                                            int dayByMonth = jDReactNativeDatePickerListener5.getDayByMonth(arrayList9, intValue, jDReactNativeDatePickerListener6.maxYear, jDReactNativeDatePickerListener6.minYear, jDReactNativeDatePickerListener6.maxMonth, jDReactNativeDatePickerListener6.minMonth, jDReactNativeDatePickerListener6.maxDay, jDReactNativeDatePickerListener6.minDay, Integer.valueOf(jDReactNativeDatePickerListener6.secondData).intValue(), Integer.valueOf(JDReactNativeDatePickerListener.this.thirdData).intValue());
                                            wheelView3.setData(arrayList5);
                                            wheelViewLayout3.refreshWheelView(new WheelView[]{wheelView3}, new int[]{dayByMonth});
                                        }
                                    }
                                }

                                @Override // com.jingdong.common.jdreactFramework.utils.WheelView.OnSelectListener
                                public void selecting(int i76, String str11) {
                                }
                            });
                            wheelView3.setOnSelectListener(new WheelView.OnSelectListener() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeDatePickerListener.12
                                @Override // com.jingdong.common.jdreactFramework.utils.WheelView.OnSelectListener
                                public void endSelect(int i76, String str11) {
                                    JDReactNativeDatePickerListener.this.thirdData = str11;
                                }

                                @Override // com.jingdong.common.jdreactFramework.utils.WheelView.OnSelectListener
                                public void selecting(int i76, String str11) {
                                }
                            });
                            final int i76 = i17;
                            button2.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeDatePickerListener.13
                                @Override // android.view.View.OnClickListener
                                public void onClick(View view3) {
                                    Date date;
                                    if (JDReactNativeDatePickerListener.this.mode.equals("datetime")) {
                                        int indexOf4 = JDReactNativeDatePickerListener.this.firstData.indexOf("\u6708");
                                        int intValue = Integer.valueOf(JDReactNativeDatePickerListener.this.firstData.substring(0, indexOf4)).intValue();
                                        int intValue2 = Integer.valueOf(JDReactNativeDatePickerListener.this.firstData.substring(indexOf4 + 1, JDReactNativeDatePickerListener.this.firstData.indexOf("\u65e5"))).intValue();
                                        int intValue3 = Integer.valueOf(JDReactNativeDatePickerListener.this.thirdData).intValue();
                                        if (JDReactNativeDatePickerListener.this.secondData.equals("\u4e0b\u5348")) {
                                            intValue3 += 12;
                                        }
                                        date = new Date(i76 - 1900, intValue - 1, intValue2, intValue3, Integer.valueOf(JDReactNativeDatePickerListener.this.fourthData).intValue());
                                    } else if (JDReactNativeDatePickerListener.this.mode.equals("time")) {
                                        int intValue4 = Integer.valueOf(JDReactNativeDatePickerListener.this.firstData).intValue();
                                        if (JDReactNativeDatePickerListener.this.thirdData.equals("\u4e0b\u5348")) {
                                            intValue4 += 12;
                                        }
                                        date = new Date(i76 - 1900, i30 - 1, i32, intValue4, Integer.valueOf(JDReactNativeDatePickerListener.this.secondData).intValue());
                                    } else {
                                        date = new Date(Integer.valueOf(JDReactNativeDatePickerListener.this.firstData).intValue() - 1900, Integer.valueOf(JDReactNativeDatePickerListener.this.secondData).intValue() - 1, Integer.valueOf(JDReactNativeDatePickerListener.this.thirdData).intValue());
                                    }
                                    JDCallback jDCallback4 = jDCallback;
                                    if (jDCallback4 != null) {
                                        jDCallback4.invoke("" + date.getTime());
                                    }
                                    Dialog dialog2 = dialog;
                                    if (dialog2 != null) {
                                        dialog2.dismiss();
                                    }
                                }
                            });
                            dialog.setContentView(view2);
                            window = dialog.getWindow();
                            if (window != null) {
                                window.setGravity(80);
                                window.setLayout(-1, -2);
                            }
                            dialog.show();
                            return;
                        }
                        wheelView.setOnSelectListener(new WheelView.OnSelectListener() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeDatePickerListener.10
                            @Override // com.jingdong.common.jdreactFramework.utils.WheelView.OnSelectListener
                            public void endSelect(int i762, String str11) {
                                if (str11 == null || !str11.equals(JDReactNativeDatePickerListener.this.firstData)) {
                                    JDReactNativeDatePickerListener jDReactNativeDatePickerListener4 = JDReactNativeDatePickerListener.this;
                                    jDReactNativeDatePickerListener4.firstData = str11;
                                    if (jDReactNativeDatePickerListener4.mode.equals(JDDateTimePickerDialog.SELECT_DATE_MODE)) {
                                        JDReactNativeDatePickerListener jDReactNativeDatePickerListener5 = JDReactNativeDatePickerListener.this;
                                        ArrayList<String> arrayList9 = arrayList4;
                                        ArrayList<String> arrayList10 = arrayList5;
                                        int intValue = Integer.valueOf(jDReactNativeDatePickerListener5.firstData).intValue();
                                        JDReactNativeDatePickerListener jDReactNativeDatePickerListener6 = JDReactNativeDatePickerListener.this;
                                        int[] monthDayByYear = jDReactNativeDatePickerListener5.getMonthDayByYear(arrayList9, arrayList10, intValue, jDReactNativeDatePickerListener6.maxYear, jDReactNativeDatePickerListener6.minYear, jDReactNativeDatePickerListener6.maxMonth, jDReactNativeDatePickerListener6.minMonth, jDReactNativeDatePickerListener6.maxDay, jDReactNativeDatePickerListener6.minDay, Integer.valueOf(jDReactNativeDatePickerListener6.secondData).intValue(), Integer.valueOf(JDReactNativeDatePickerListener.this.thirdData).intValue());
                                        wheelView2.setData(arrayList4);
                                        wheelView3.setData(arrayList5);
                                        wheelViewLayout3.refreshWheelView(new WheelView[]{wheelView2, wheelView3}, monthDayByYear);
                                    }
                                }
                            }

                            @Override // com.jingdong.common.jdreactFramework.utils.WheelView.OnSelectListener
                            public void selecting(int i762, String str11) {
                            }
                        });
                        wheelView2.setOnSelectListener(new WheelView.OnSelectListener() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeDatePickerListener.11
                            @Override // com.jingdong.common.jdreactFramework.utils.WheelView.OnSelectListener
                            public void endSelect(int i762, String str11) {
                                if (str11 == null || !str11.equals(JDReactNativeDatePickerListener.this.secondData)) {
                                    JDReactNativeDatePickerListener jDReactNativeDatePickerListener4 = JDReactNativeDatePickerListener.this;
                                    jDReactNativeDatePickerListener4.secondData = str11;
                                    if (jDReactNativeDatePickerListener4.mode.equals(JDDateTimePickerDialog.SELECT_DATE_MODE)) {
                                        JDReactNativeDatePickerListener jDReactNativeDatePickerListener5 = JDReactNativeDatePickerListener.this;
                                        ArrayList<String> arrayList9 = arrayList5;
                                        int intValue = Integer.valueOf(jDReactNativeDatePickerListener5.firstData).intValue();
                                        JDReactNativeDatePickerListener jDReactNativeDatePickerListener6 = JDReactNativeDatePickerListener.this;
                                        int dayByMonth = jDReactNativeDatePickerListener5.getDayByMonth(arrayList9, intValue, jDReactNativeDatePickerListener6.maxYear, jDReactNativeDatePickerListener6.minYear, jDReactNativeDatePickerListener6.maxMonth, jDReactNativeDatePickerListener6.minMonth, jDReactNativeDatePickerListener6.maxDay, jDReactNativeDatePickerListener6.minDay, Integer.valueOf(jDReactNativeDatePickerListener6.secondData).intValue(), Integer.valueOf(JDReactNativeDatePickerListener.this.thirdData).intValue());
                                        wheelView3.setData(arrayList5);
                                        wheelViewLayout3.refreshWheelView(new WheelView[]{wheelView3}, new int[]{dayByMonth});
                                    }
                                }
                            }

                            @Override // com.jingdong.common.jdreactFramework.utils.WheelView.OnSelectListener
                            public void selecting(int i762, String str11) {
                            }
                        });
                        wheelView3.setOnSelectListener(new WheelView.OnSelectListener() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeDatePickerListener.12
                            @Override // com.jingdong.common.jdreactFramework.utils.WheelView.OnSelectListener
                            public void endSelect(int i762, String str11) {
                                JDReactNativeDatePickerListener.this.thirdData = str11;
                            }

                            @Override // com.jingdong.common.jdreactFramework.utils.WheelView.OnSelectListener
                            public void selecting(int i762, String str11) {
                            }
                        });
                        final int i762 = i17;
                        button2.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeDatePickerListener.13
                            @Override // android.view.View.OnClickListener
                            public void onClick(View view3) {
                                Date date;
                                if (JDReactNativeDatePickerListener.this.mode.equals("datetime")) {
                                    int indexOf4 = JDReactNativeDatePickerListener.this.firstData.indexOf("\u6708");
                                    int intValue = Integer.valueOf(JDReactNativeDatePickerListener.this.firstData.substring(0, indexOf4)).intValue();
                                    int intValue2 = Integer.valueOf(JDReactNativeDatePickerListener.this.firstData.substring(indexOf4 + 1, JDReactNativeDatePickerListener.this.firstData.indexOf("\u65e5"))).intValue();
                                    int intValue3 = Integer.valueOf(JDReactNativeDatePickerListener.this.thirdData).intValue();
                                    if (JDReactNativeDatePickerListener.this.secondData.equals("\u4e0b\u5348")) {
                                        intValue3 += 12;
                                    }
                                    date = new Date(i762 - 1900, intValue - 1, intValue2, intValue3, Integer.valueOf(JDReactNativeDatePickerListener.this.fourthData).intValue());
                                } else if (JDReactNativeDatePickerListener.this.mode.equals("time")) {
                                    int intValue4 = Integer.valueOf(JDReactNativeDatePickerListener.this.firstData).intValue();
                                    if (JDReactNativeDatePickerListener.this.thirdData.equals("\u4e0b\u5348")) {
                                        intValue4 += 12;
                                    }
                                    date = new Date(i762 - 1900, i30 - 1, i32, intValue4, Integer.valueOf(JDReactNativeDatePickerListener.this.secondData).intValue());
                                } else {
                                    date = new Date(Integer.valueOf(JDReactNativeDatePickerListener.this.firstData).intValue() - 1900, Integer.valueOf(JDReactNativeDatePickerListener.this.secondData).intValue() - 1, Integer.valueOf(JDReactNativeDatePickerListener.this.thirdData).intValue());
                                }
                                JDCallback jDCallback4 = jDCallback;
                                if (jDCallback4 != null) {
                                    jDCallback4.invoke("" + date.getTime());
                                }
                                Dialog dialog2 = dialog;
                                if (dialog2 != null) {
                                    dialog2.dismiss();
                                }
                            }
                        });
                        dialog.setContentView(view2);
                        window = dialog.getWindow();
                        if (window != null) {
                        }
                        dialog.show();
                        return;
                    } catch (Exception e5) {
                        e = e5;
                        jDCallback3 = jDCallback2;
                        if (jDCallback3 != null) {
                        }
                        JLog.e(TAG, e.toString());
                        return;
                    }
                    final WheelView wheelView22 = (WheelView) view2.findViewById(R.id.wheelView_second);
                    wheelView22.setVisibility(0);
                    wheelView22.setSelectedColor(i75);
                    wheelView22.setData(arrayList4);
                    if (i25 != -1) {
                    }
                    final WheelView wheelView32 = (WheelView) view2.findViewById(R.id.wheelView_third);
                    wheelView32.setVisibility(0);
                    wheelView32.setSelectedColor(i75);
                    wheelView32.setData(arrayList5);
                    if (i19 != -1) {
                    }
                    if (arrayList.size() != 0) {
                    }
                    final WheelViewLayout wheelViewLayout32 = wheelViewLayout;
                    JDReactNativeDatePickerListener jDReactNativeDatePickerListener32 = jDReactNativeDatePickerListener;
                } catch (Exception e6) {
                    e = e6;
                }
                if (!jDReactNativeDatePickerListener2.mode.equals(JDDateTimePickerDialog.SELECT_DATE_MODE)) {
                }
                int i752 = -1037525;
                obj3 = obj;
                if (hashMap.containsKey(obj3)) {
                    str7 = str5;
                    if (str6.startsWith(str7)) {
                    }
                }
                View view22 = view;
                WheelView wheelView5 = (WheelView) view22.findViewById(R.id.wheelView_first);
                wheelView5.setVisibility(0);
                wheelView5.setSelectedColor(i752);
                wheelView5.setData(arrayList3);
                if (i16 == -1) {
                }
            } catch (Exception e7) {
                e = e7;
                jDCallback3 = jDCallback2;
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (str2 != null) {
                Date parse = simpleDateFormat.parse(str2);
                int month = parse.getMonth() + 1;
                int date = parse.getDate();
                if (jDReactNativeDatePickerListener2.mode.equals("datetime")) {
                    int hours = parse.getHours();
                    i4 = date;
                    i8 = parse.getYear() + 1900;
                    int i77 = hours > 12 ? hours - 12 : hours;
                    i36 = parse.getMinutes();
                    i37 = -1;
                    int i78 = i77;
                    i5 = month;
                    i6 = hours;
                    i7 = i78;
                } else {
                    i4 = date;
                    i5 = month;
                    i6 = i2;
                    i7 = i3;
                    i8 = i37;
                    i37 = parse.getYear() + 1900;
                }
            } else {
                i4 = i32;
                i5 = i30;
                i6 = i2;
                i7 = i3;
                i8 = i37;
            }
            int i79 = i7;
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
            if (str9 != null) {
                Date parse2 = simpleDateFormat2.parse(str9);
                jDReactNativeDatePickerListener2.minMonth = parse2.getMonth() + 1;
                jDReactNativeDatePickerListener2.minDay = parse2.getDate();
                if (jDReactNativeDatePickerListener2.mode.equals("datetime")) {
                    jDReactNativeDatePickerListener2.minYear = -1;
                } else {
                    jDReactNativeDatePickerListener2.minYear = parse2.getYear() + 1900;
                }
            }
            if (str10 != null) {
                Date parse3 = simpleDateFormat2.parse(str10);
                jDReactNativeDatePickerListener2.maxMonth = parse3.getMonth() + 1;
                jDReactNativeDatePickerListener2.maxDay = parse3.getDate();
                if (jDReactNativeDatePickerListener2.mode.equals("datetime")) {
                    jDReactNativeDatePickerListener2.maxYear = -1;
                } else {
                    jDReactNativeDatePickerListener2.maxYear = parse3.getYear() + 1900;
                }
            }
            i9 = i8;
            i10 = i79;
            i11 = i36;
            i12 = i6;
            obj = "selectedTextColor";
            obj2 = "datetime";
            i13 = i5;
            i14 = i37;
            i15 = i4;
            Activity currentMyActivity2 = AbstractJDReactInitialHelper.getCurrentMyActivity();
            int i382 = i11;
            int i392 = i10;
            final Dialog dialog2 = new Dialog(currentMyActivity2, R.style.JDReactWheelViewDialogStyle);
            View inflate2 = LayoutInflater.from(currentMyActivity2).inflate(R.layout.jdreact_date_time_picker_view_dialog_layout, (ViewGroup) null);
            WheelViewLayout wheelViewLayout22 = (WheelViewLayout) inflate2.findViewById(R.id.container);
            button = (Button) inflate2.findViewById(R.id.btn_cancel);
            if (hashMap.containsKey("cancelButtonColor")) {
            }
            if (str3 != null) {
                button.setTextColor(Color.parseColor(str3));
            }
            button.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeDatePickerListener.8
                @Override // android.view.View.OnClickListener
                public void onClick(View view23) {
                    JDCallback jDCallback4 = jDCallback2;
                    if (jDCallback4 != null) {
                        jDCallback4.invoke(new Object[0]);
                    }
                    Dialog dialog22 = dialog2;
                    if (dialog22 != null) {
                        dialog22.dismiss();
                    }
                }
            });
            Button button22 = (Button) inflate2.findViewById(R.id.btn_ok);
            if (!hashMap.containsKey("confirmButtonColor")) {
            }
            if (str4 != null) {
            }
            ArrayList<String> arrayList32 = new ArrayList<>();
            final ArrayList arrayList42 = new ArrayList<>();
            final ArrayList arrayList52 = new ArrayList<>();
            ArrayList<String> arrayList62 = new ArrayList<>();
        } catch (Exception e8) {
            e = e8;
        }
    }
}
