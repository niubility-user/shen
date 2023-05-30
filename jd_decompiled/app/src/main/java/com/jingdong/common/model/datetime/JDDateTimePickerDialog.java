package com.jingdong.common.model.datetime;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import com.jingdong.app.mall.R;
import com.jingdong.common.model.datetime.dateview.NumericWheelAdapter;
import com.jingdong.common.model.datetime.dateview.OnWheelChangedListener;
import com.jingdong.common.model.datetime.dateview.WheelView;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/* loaded from: classes5.dex */
public class JDDateTimePickerDialog extends Dialog {
    public static final int MAX_END_YEAR = 2100;
    public static final int MIN_START_YEAR = 1900;
    public static final String SELECT_DATE_MODE = "date";
    public static final String SELECT_TIME_MODE = "time";
    public static final String TWO_DIGIT_FORMAT = "%02d";
    private int curr_day;
    private int curr_hour;
    private int curr_minute;
    private int curr_month;
    private int curr_year;
    private List<String> list_big;
    private List<String> list_little;
    private final OnDateTimeSetListener mCallBack;
    private int mStartYear;
    String[] months_big;
    String[] months_little;
    final WheelView wv_day;
    final WheelView wv_hours;
    final WheelView wv_mins;
    final WheelView wv_month;
    final WheelView wv_year;

    /* loaded from: classes5.dex */
    public interface OnDateTimeSetListener {
        void onDateTimeSet(Calendar calendar);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JDDateTimePickerDialog(Context context, String str, int i2, int i3, Calendar calendar, OnDateTimeSetListener onDateTimeSetListener) {
        super(context, R.style.f6);
        OnDateTimeSetListener onDateTimeSetListener2;
        String str2 = str;
        this.months_big = new String[]{"1", "3", "5", "7", "8", "10", "12"};
        this.months_little = new String[]{"4", "6", "9", "11"};
        setCanceledOnTouchOutside(true);
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.jd_date_time_picker_dialog_layout, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(R.id.bv);
        WheelView wheelView = (WheelView) inflate.findViewById(R.id.a7f);
        this.wv_year = wheelView;
        WheelView wheelView2 = (WheelView) inflate.findViewById(R.id.a7g);
        this.wv_month = wheelView2;
        WheelView wheelView3 = (WheelView) inflate.findViewById(R.id.a7h);
        this.wv_day = wheelView3;
        WheelView wheelView4 = (WheelView) inflate.findViewById(R.id.a7i);
        this.wv_hours = wheelView4;
        WheelView wheelView5 = (WheelView) inflate.findViewById(R.id.mins);
        this.wv_mins = wheelView5;
        if (TextUtils.isEmpty(str) || !(SELECT_DATE_MODE.equals(str2) || "time".equals(str2))) {
            onDateTimeSetListener2 = onDateTimeSetListener;
            str2 = SELECT_DATE_MODE;
        } else {
            onDateTimeSetListener2 = onDateTimeSetListener;
        }
        this.mCallBack = onDateTimeSetListener2;
        this.mStartYear = i2;
        Calendar calendar2 = calendar == null ? Calendar.getInstance() : calendar;
        int adjustFontSize = adjustFontSize(getWindow().getWindowManager());
        if (SELECT_DATE_MODE.equals(str2)) {
            if (i2 < 1900) {
                this.mStartYear = 1900;
            }
            int i4 = 2100;
            int i5 = i3;
            i5 = i5 > 2100 ? 2100 : i5;
            if (i2 > i5) {
                this.mStartYear = 1900;
            } else {
                i4 = i5;
            }
            textView.setText("\u65e5\u671f\u9009\u62e9");
            int i6 = calendar2.get(1);
            int i7 = calendar2.get(2);
            int i8 = calendar2.get(5);
            this.list_big = Arrays.asList(this.months_big);
            this.list_little = Arrays.asList(this.months_little);
            wheelView.setAdapter(new NumericWheelAdapter(i2, i4));
            wheelView.setCyclic(true);
            wheelView.setLabel("\u5e74");
            wheelView.setCurrentItem(i6 - i2);
            wheelView2.setAdapter(new NumericWheelAdapter(1, 12, TWO_DIGIT_FORMAT));
            wheelView2.setCyclic(true);
            wheelView2.setLabel("\u6708");
            wheelView2.setCurrentItem(i7);
            wheelView3.setCyclic(true);
            int i9 = i7 + 1;
            if (this.list_big.contains(String.valueOf(i9))) {
                wheelView3.setAdapter(new NumericWheelAdapter(1, 31, TWO_DIGIT_FORMAT));
            } else if (this.list_little.contains(String.valueOf(i9))) {
                wheelView3.setAdapter(new NumericWheelAdapter(1, 30, TWO_DIGIT_FORMAT));
            } else if ((i6 % 4 == 0 && i6 % 100 != 0) || i6 % 400 == 0) {
                wheelView3.setAdapter(new NumericWheelAdapter(1, 29, TWO_DIGIT_FORMAT));
            } else {
                wheelView3.setAdapter(new NumericWheelAdapter(1, 28, TWO_DIGIT_FORMAT));
            }
            wheelView3.setLabel("\u65e5");
            wheelView3.setCurrentItem(i8 - 1);
            OnWheelChangedListener onWheelChangedListener = new OnWheelChangedListener() { // from class: com.jingdong.common.model.datetime.JDDateTimePickerDialog.1
                @Override // com.jingdong.common.model.datetime.dateview.OnWheelChangedListener
                public void onChanged(WheelView wheelView6, int i10, int i11) {
                    int i12 = i11 + JDDateTimePickerDialog.this.mStartYear;
                    if (!JDDateTimePickerDialog.this.list_big.contains(String.valueOf(JDDateTimePickerDialog.this.wv_month.currentItem + 1))) {
                        if (JDDateTimePickerDialog.this.list_little.contains(String.valueOf(JDDateTimePickerDialog.this.wv_month.currentItem + 1))) {
                            JDDateTimePickerDialog.this.wv_day.setAdapter(new NumericWheelAdapter(1, 30, JDDateTimePickerDialog.TWO_DIGIT_FORMAT));
                            WheelView wheelView7 = JDDateTimePickerDialog.this.wv_day;
                            if (wheelView7.currentItem > 29) {
                                wheelView7.setCurrentItem(0);
                                return;
                            }
                            return;
                        } else if ((i12 % 4 == 0 && i12 % 100 != 0) || i12 % 400 == 0) {
                            JDDateTimePickerDialog.this.wv_day.setAdapter(new NumericWheelAdapter(1, 29, JDDateTimePickerDialog.TWO_DIGIT_FORMAT));
                            WheelView wheelView8 = JDDateTimePickerDialog.this.wv_day;
                            if (wheelView8.currentItem > 28) {
                                wheelView8.setCurrentItem(0);
                                return;
                            }
                            return;
                        } else {
                            JDDateTimePickerDialog.this.wv_day.setAdapter(new NumericWheelAdapter(1, 28, JDDateTimePickerDialog.TWO_DIGIT_FORMAT));
                            WheelView wheelView9 = JDDateTimePickerDialog.this.wv_day;
                            if (wheelView9.currentItem > 27) {
                                wheelView9.setCurrentItem(0);
                                return;
                            }
                            return;
                        }
                    }
                    JDDateTimePickerDialog.this.wv_day.setAdapter(new NumericWheelAdapter(1, 31, JDDateTimePickerDialog.TWO_DIGIT_FORMAT));
                }
            };
            OnWheelChangedListener onWheelChangedListener2 = new OnWheelChangedListener() { // from class: com.jingdong.common.model.datetime.JDDateTimePickerDialog.2
                /* JADX WARN: Code restructure failed: missing block: B:13:0x006f, code lost:
                    if (((r2.wv_year.currentItem + r2.mStartYear) % 100) == 0) goto L14;
                 */
                @Override // com.jingdong.common.model.datetime.dateview.OnWheelChangedListener
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public void onChanged(com.jingdong.common.model.datetime.dateview.WheelView r5, int r6, int r7) {
                    /*
                        r4 = this;
                        r5 = 1
                        int r7 = r7 + r5
                        com.jingdong.common.model.datetime.JDDateTimePickerDialog r6 = com.jingdong.common.model.datetime.JDDateTimePickerDialog.this
                        java.util.List r6 = com.jingdong.common.model.datetime.JDDateTimePickerDialog.access$100(r6)
                        java.lang.String r0 = java.lang.String.valueOf(r7)
                        boolean r6 = r6.contains(r0)
                        java.lang.String r0 = "%02d"
                        if (r6 == 0) goto L24
                        com.jingdong.common.model.datetime.JDDateTimePickerDialog r6 = com.jingdong.common.model.datetime.JDDateTimePickerDialog.this
                        com.jingdong.common.model.datetime.dateview.WheelView r6 = r6.wv_day
                        com.jingdong.common.model.datetime.dateview.NumericWheelAdapter r7 = new com.jingdong.common.model.datetime.dateview.NumericWheelAdapter
                        r1 = 31
                        r7.<init>(r5, r1, r0)
                        r6.setAdapter(r7)
                        goto Lb1
                    L24:
                        com.jingdong.common.model.datetime.JDDateTimePickerDialog r6 = com.jingdong.common.model.datetime.JDDateTimePickerDialog.this
                        java.util.List r6 = com.jingdong.common.model.datetime.JDDateTimePickerDialog.access$200(r6)
                        java.lang.String r7 = java.lang.String.valueOf(r7)
                        boolean r6 = r6.contains(r7)
                        r7 = 29
                        r1 = 0
                        if (r6 == 0) goto L51
                        com.jingdong.common.model.datetime.JDDateTimePickerDialog r6 = com.jingdong.common.model.datetime.JDDateTimePickerDialog.this
                        com.jingdong.common.model.datetime.dateview.WheelView r6 = r6.wv_day
                        com.jingdong.common.model.datetime.dateview.NumericWheelAdapter r2 = new com.jingdong.common.model.datetime.dateview.NumericWheelAdapter
                        r3 = 30
                        r2.<init>(r5, r3, r0)
                        r6.setAdapter(r2)
                        com.jingdong.common.model.datetime.JDDateTimePickerDialog r5 = com.jingdong.common.model.datetime.JDDateTimePickerDialog.this
                        com.jingdong.common.model.datetime.dateview.WheelView r5 = r5.wv_day
                        int r6 = r5.currentItem
                        if (r6 <= r7) goto Lb1
                        r5.setCurrentItem(r1)
                        goto Lb1
                    L51:
                        com.jingdong.common.model.datetime.JDDateTimePickerDialog r6 = com.jingdong.common.model.datetime.JDDateTimePickerDialog.this
                        com.jingdong.common.model.datetime.dateview.WheelView r2 = r6.wv_year
                        int r2 = r2.currentItem
                        int r6 = com.jingdong.common.model.datetime.JDDateTimePickerDialog.access$000(r6)
                        int r2 = r2 + r6
                        int r2 = r2 % 4
                        r6 = 28
                        if (r2 != 0) goto L71
                        com.jingdong.common.model.datetime.JDDateTimePickerDialog r2 = com.jingdong.common.model.datetime.JDDateTimePickerDialog.this
                        com.jingdong.common.model.datetime.dateview.WheelView r3 = r2.wv_year
                        int r3 = r3.currentItem
                        int r2 = com.jingdong.common.model.datetime.JDDateTimePickerDialog.access$000(r2)
                        int r3 = r3 + r2
                        int r3 = r3 % 100
                        if (r3 != 0) goto L80
                    L71:
                        com.jingdong.common.model.datetime.JDDateTimePickerDialog r2 = com.jingdong.common.model.datetime.JDDateTimePickerDialog.this
                        com.jingdong.common.model.datetime.dateview.WheelView r3 = r2.wv_year
                        int r3 = r3.currentItem
                        int r2 = com.jingdong.common.model.datetime.JDDateTimePickerDialog.access$000(r2)
                        int r3 = r3 + r2
                        int r3 = r3 % 400
                        if (r3 != 0) goto L98
                    L80:
                        com.jingdong.common.model.datetime.JDDateTimePickerDialog r2 = com.jingdong.common.model.datetime.JDDateTimePickerDialog.this
                        com.jingdong.common.model.datetime.dateview.WheelView r2 = r2.wv_day
                        com.jingdong.common.model.datetime.dateview.NumericWheelAdapter r3 = new com.jingdong.common.model.datetime.dateview.NumericWheelAdapter
                        r3.<init>(r5, r7, r0)
                        r2.setAdapter(r3)
                        com.jingdong.common.model.datetime.JDDateTimePickerDialog r5 = com.jingdong.common.model.datetime.JDDateTimePickerDialog.this
                        com.jingdong.common.model.datetime.dateview.WheelView r5 = r5.wv_day
                        int r7 = r5.currentItem
                        if (r7 <= r6) goto Lb1
                        r5.setCurrentItem(r1)
                        goto Lb1
                    L98:
                        com.jingdong.common.model.datetime.JDDateTimePickerDialog r7 = com.jingdong.common.model.datetime.JDDateTimePickerDialog.this
                        com.jingdong.common.model.datetime.dateview.WheelView r7 = r7.wv_day
                        com.jingdong.common.model.datetime.dateview.NumericWheelAdapter r2 = new com.jingdong.common.model.datetime.dateview.NumericWheelAdapter
                        r2.<init>(r5, r6, r0)
                        r7.setAdapter(r2)
                        com.jingdong.common.model.datetime.JDDateTimePickerDialog r5 = com.jingdong.common.model.datetime.JDDateTimePickerDialog.this
                        com.jingdong.common.model.datetime.dateview.WheelView r5 = r5.wv_day
                        int r6 = r5.currentItem
                        r7 = 27
                        if (r6 <= r7) goto Lb1
                        r5.setCurrentItem(r1)
                    Lb1:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.model.datetime.JDDateTimePickerDialog.AnonymousClass2.onChanged(com.jingdong.common.model.datetime.dateview.WheelView, int, int):void");
                }
            };
            wheelView.addChangingListener(onWheelChangedListener);
            wheelView2.addChangingListener(onWheelChangedListener2);
            wheelView3.TEXT_SIZE = adjustFontSize;
            wheelView2.TEXT_SIZE = adjustFontSize;
            wheelView.TEXT_SIZE = adjustFontSize;
            wheelView3.setVisibility(0);
            wheelView2.setVisibility(0);
            wheelView.setVisibility(0);
            wheelView4.setVisibility(8);
            wheelView5.setVisibility(8);
            inflate.findViewById(R.id.br).setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.model.datetime.JDDateTimePickerDialog.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    JDDateTimePickerDialog jDDateTimePickerDialog = JDDateTimePickerDialog.this;
                    jDDateTimePickerDialog.curr_year = jDDateTimePickerDialog.wv_year.currentItem + jDDateTimePickerDialog.mStartYear;
                    JDDateTimePickerDialog jDDateTimePickerDialog2 = JDDateTimePickerDialog.this;
                    jDDateTimePickerDialog2.curr_month = jDDateTimePickerDialog2.wv_month.currentItem;
                    JDDateTimePickerDialog jDDateTimePickerDialog3 = JDDateTimePickerDialog.this;
                    jDDateTimePickerDialog3.curr_day = jDDateTimePickerDialog3.wv_day.currentItem + 1;
                    if (JDDateTimePickerDialog.this.mCallBack != null) {
                        Calendar calendar3 = Calendar.getInstance();
                        calendar3.set(1, JDDateTimePickerDialog.this.curr_year);
                        calendar3.set(2, JDDateTimePickerDialog.this.curr_month);
                        calendar3.set(5, JDDateTimePickerDialog.this.curr_day);
                        JDDateTimePickerDialog.this.mCallBack.onDateTimeSet(calendar3);
                    }
                    JDDateTimePickerDialog.this.dismiss();
                }
            });
        } else if ("time".equals(str2)) {
            textView.setText("\u65f6\u95f4\u9009\u62e9");
            int i10 = calendar2.get(11);
            int i11 = calendar2.get(12);
            wheelView4.setAdapter(new NumericWheelAdapter(0, 23, TWO_DIGIT_FORMAT));
            wheelView4.setCyclic(true);
            wheelView4.setLabel("\u65f6");
            wheelView4.setCurrentItem(i10);
            wheelView5.setAdapter(new NumericWheelAdapter(0, 59, TWO_DIGIT_FORMAT));
            wheelView5.setCyclic(true);
            wheelView5.setLabel("\u5206");
            wheelView5.setCurrentItem(i11);
            wheelView4.TEXT_SIZE = adjustFontSize;
            wheelView5.TEXT_SIZE = adjustFontSize;
            wheelView3.setVisibility(8);
            wheelView2.setVisibility(8);
            wheelView.setVisibility(8);
            wheelView4.setVisibility(0);
            wheelView5.setVisibility(0);
            inflate.findViewById(R.id.br).setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.model.datetime.JDDateTimePickerDialog.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    JDDateTimePickerDialog jDDateTimePickerDialog = JDDateTimePickerDialog.this;
                    jDDateTimePickerDialog.curr_hour = jDDateTimePickerDialog.wv_hours.currentItem;
                    JDDateTimePickerDialog jDDateTimePickerDialog2 = JDDateTimePickerDialog.this;
                    jDDateTimePickerDialog2.curr_minute = jDDateTimePickerDialog2.wv_mins.currentItem;
                    if (JDDateTimePickerDialog.this.mCallBack != null) {
                        Calendar calendar3 = Calendar.getInstance();
                        calendar3.set(11, JDDateTimePickerDialog.this.curr_hour);
                        calendar3.set(12, JDDateTimePickerDialog.this.curr_minute);
                        JDDateTimePickerDialog.this.mCallBack.onDateTimeSet(calendar3);
                    }
                    JDDateTimePickerDialog.this.dismiss();
                }
            });
        }
        setContentView(inflate);
    }

    public static int adjustFontSize(WindowManager windowManager) {
        int width = windowManager.getDefaultDisplay().getWidth();
        if (width <= 240) {
            return 20;
        }
        if (width <= 320) {
            return 24;
        }
        if (width <= 480) {
            return 34;
        }
        if (width <= 540) {
            return 36;
        }
        if (width <= 800) {
        }
        return 45;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
    }
}
