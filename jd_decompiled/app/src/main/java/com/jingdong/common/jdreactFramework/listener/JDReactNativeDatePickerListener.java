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
import com.jingdong.common.model.datetime.JDDateTimePickerDialog;
import java.util.ArrayList;
import java.util.Calendar;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void showDatePicker(java.lang.String r34, java.util.HashMap r35, final com.jingdong.common.jdreactFramework.JDCallback r36, final com.jingdong.common.jdreactFramework.JDCallback r37) {
        /*
            Method dump skipped, instructions count: 1677
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.jdreactFramework.listener.JDReactNativeDatePickerListener.showDatePicker(java.lang.String, java.util.HashMap, com.jingdong.common.jdreactFramework.JDCallback, com.jingdong.common.jdreactFramework.JDCallback):void");
    }
}
