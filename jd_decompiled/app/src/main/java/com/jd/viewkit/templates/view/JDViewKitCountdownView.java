package com.jd.viewkit.templates.view;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel;
import com.jd.viewkit.global.GlobalManage;
import com.jd.viewkit.helper.JDViewKitChannelModel;
import com.jd.viewkit.jdviewkit.R;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.model.event.JDViewKitVirtualEvent;
import com.jd.viewkit.templates.model.jdviewkitvirtualcountdownview.JDViewKitVirtualCountdownGapConfigView;
import com.jd.viewkit.templates.model.jdviewkitvirtualcountdownview.JDViewKitVirtualCountdownView;
import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import com.jd.viewkit.tool.DateTool;
import com.jd.viewkit.tool.ExpressionParserTool;
import com.jd.viewkit.tool.NumberTool;
import com.jd.viewkit.tool.StringTool;
import com.jd.viewkit.tool.countdown.CountDownTask;
import com.jd.viewkit.tool.countdown.CountdownTimerUtils;
import com.jingdong.common.utils.LangUtils;
import com.tencent.smtt.sdk.TbsDownloadConfig;

/* loaded from: classes18.dex */
public class JDViewKitCountdownView extends JDViewKitAbsoluteLayout {
    public static String useTimeOutKey = "useTimeOut";
    private long countdownSed;
    private JDViewKitTextView gapTextViewOne;
    private JDViewKitTextView gapTextViewThree;
    private JDViewKitTextView gapTextViewTwo;
    private int handleType;
    private boolean isPlay;
    private CountDownTask mCountDownTask;
    private JDViewKitTextView textViewFive;
    private JDViewKitTextView textViewFour;
    private JDViewKitTextView textViewOne;
    private JDViewKitTextView textViewSix;
    private JDViewKitTextView textViewThree;
    private JDViewKitTextView textViewTwo;
    public JDViewKitVirtualCountdownView virtualCountdownView;

    public JDViewKitCountdownView(@NonNull Context context) {
        super(context);
        this.isPlay = false;
        this.handleType = 1;
        this.countdownSed = 0L;
        View inflate = LayoutInflater.from(((JDViewKitAbsoluteLayout) this).mContext).inflate(R.layout.countdownview, this);
        this.textViewSix = (JDViewKitTextView) inflate.findViewById(R.id.countdownview_text_six);
        this.textViewFive = (JDViewKitTextView) inflate.findViewById(R.id.countdownview_text_five);
        this.textViewFour = (JDViewKitTextView) inflate.findViewById(R.id.countdownview_text_four);
        this.textViewThree = (JDViewKitTextView) inflate.findViewById(R.id.countdownview_text_three);
        this.textViewTwo = (JDViewKitTextView) inflate.findViewById(R.id.countdownview_text_two);
        this.textViewOne = (JDViewKitTextView) inflate.findViewById(R.id.countdownview_text_one);
        this.gapTextViewThree = (JDViewKitTextView) inflate.findViewById(R.id.countdownview_gap_three);
        this.gapTextViewTwo = (JDViewKitTextView) inflate.findViewById(R.id.countdownview_gap_two);
        this.gapTextViewOne = (JDViewKitTextView) inflate.findViewById(R.id.countdownview_gap_one);
    }

    private void beginCountDown() {
        JDViewKitVirtualCountdownView jDViewKitVirtualCountdownView;
        JDViewKitDataSourceModel jDViewKitDataSourceModel = this.dataSourceModel;
        if (jDViewKitDataSourceModel == null) {
            return;
        }
        CountDownTask countDownTask = this.mCountDownTask;
        if (countDownTask != null) {
            CountdownTimerUtils.removeCountDownTask(countDownTask);
        }
        if (jDViewKitDataSourceModel == null || (jDViewKitVirtualCountdownView = this.virtualCountdownView) == null) {
            return;
        }
        String stringValueRef = ExpressionParserTool.getStringValueRef(jDViewKitVirtualCountdownView.getValueRefer(), jDViewKitDataSourceModel.getDataMap());
        long longValue = NumberTool.valueOfLong(stringValueRef).longValue();
        if (longValue >= 0 && !StringTool.isEmpty(stringValueRef)) {
            setVisibility(0);
            long longValue2 = DateTool.getTrueTime(jDViewKitDataSourceModel.getTimeStame().longValue(), longValue).longValue();
            this.countdownSed = longValue2;
            handleCountdownTask(longValue2);
            if (longValue2 < 0 && this.virtualCountdownView.getIsEndHide() == 1) {
                setVisibility(8);
                return;
            } else {
                setVisibility(0);
                return;
            }
        }
        setVisibility(8);
    }

    public static JDViewKitCountdownView getCountdownView(JDViewKitAbsoluteLayout jDViewKitAbsoluteLayout) {
        if (jDViewKitAbsoluteLayout == null) {
            return null;
        }
        if (jDViewKitAbsoluteLayout instanceof JDViewKitCountdownView) {
            return (JDViewKitCountdownView) jDViewKitAbsoluteLayout;
        }
        if (jDViewKitAbsoluteLayout.getChildCount() > 0) {
            for (int i2 = 0; i2 < jDViewKitAbsoluteLayout.getChildCount(); i2++) {
                View childAt = jDViewKitAbsoluteLayout.getChildAt(i2);
                if (childAt != null) {
                    if (childAt instanceof JDViewKitCountdownView) {
                        return (JDViewKitCountdownView) childAt;
                    }
                    if (childAt instanceof JDViewKitAbsoluteLayout) {
                        return getCountdownView((JDViewKitAbsoluteLayout) childAt);
                    }
                }
            }
        }
        return null;
    }

    private void handleCountdownTask(long j2) {
        CountDownTask countDownTask = this.mCountDownTask;
        if (countDownTask != null) {
            CountdownTimerUtils.removeCountDownTask(countDownTask);
        }
        if (j2 > 0) {
            updateCountDown(j2);
            if (this.isPlay && this.handleType == 2) {
                return;
            }
            CountDownTask countDownTask2 = new CountDownTask(0, (j2 + 1) * 1000, new CountDownTask.CountDownListener() { // from class: com.jd.viewkit.templates.view.JDViewKitCountdownView.1
                {
                    JDViewKitCountdownView.this = this;
                }

                @Override // com.jd.viewkit.tool.countdown.CountDownTask.CountDownListener
                public void changed(long j3) {
                    JDViewKitCountdownView.this.countdownSed = j3 / 1000;
                    JDViewKitCountdownView jDViewKitCountdownView = JDViewKitCountdownView.this;
                    jDViewKitCountdownView.updateCountDown(jDViewKitCountdownView.countdownSed);
                }

                @Override // com.jd.viewkit.tool.countdown.CountDownTask.CountDownListener
                public boolean finish() {
                    JDViewKitCountdownView.this.updateCountDown(-1L);
                    JDViewKitCountdownView.this.countdownSed = -1L;
                    return false;
                }
            });
            this.mCountDownTask = countDownTask2;
            CountdownTimerUtils.addCountDownTask(countDownTask2);
            return;
        }
        updateCountDown(-1L);
        timeOut();
    }

    private void setGapTextStyle(JDViewKitTextView jDViewKitTextView, JDViewKitVirtualCountdownGapConfigView jDViewKitVirtualCountdownGapConfigView, boolean z) {
        if (jDViewKitTextView == null || jDViewKitVirtualCountdownGapConfigView == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            jDViewKitTextView.setTextAlignment(4);
        }
        jDViewKitTextView.setGravity(48);
        jDViewKitTextView.setTextColor(jDViewKitVirtualCountdownGapConfigView.getColorInt());
        jDViewKitTextView.setLines(1);
        if (StringTool.isNotEmpty(jDViewKitTextView.getText().toString())) {
            int realPx = GlobalManage.getInstance().getRealPx(this.virtualCountdownView.getCardConfigView().getHeigh(), getChannelModel());
            int fontSize = jDViewKitVirtualCountdownGapConfigView.getFontSize();
            if (z) {
                if (fontSize == 0) {
                    fontSize = this.virtualCountdownView.getHeigh();
                }
                double heigh = this.virtualCountdownView.getHeigh();
                Double.isNaN(heigh);
                if (fontSize > heigh * 0.7d) {
                    double heigh2 = this.virtualCountdownView.getHeigh();
                    Double.isNaN(heigh2);
                    fontSize = (int) (heigh2 * 0.7d);
                }
            }
            int realPx2 = GlobalManage.getInstance().getRealPx(fontSize, getChannelModel());
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) jDViewKitTextView.getLayoutParams();
            if (z) {
                double realPx3 = GlobalManage.getInstance().getRealPx(jDViewKitVirtualCountdownGapConfigView.getGap(), getChannelModel());
                Double.isNaN(realPx3);
                marginLayoutParams.width = (int) (realPx3 * 0.7d);
            } else {
                marginLayoutParams.width = GlobalManage.getInstance().getRealPx(jDViewKitVirtualCountdownGapConfigView.getGap(), getChannelModel());
            }
            if (realPx2 > realPx) {
                int i2 = realPx - realPx2;
                double d = i2;
                Double.isNaN(d);
                double abs = Math.abs(i2);
                Double.isNaN(abs);
                marginLayoutParams.topMargin = (int) ((d * 0.5d) + (abs * 0.15d));
            }
            jDViewKitTextView.setLayoutParams(marginLayoutParams);
            jDViewKitTextView.setTextSize(0, realPx2);
        }
    }

    private void viewAppear() {
        if (this.isPlay) {
            beginCountDown();
        } else {
            beginCountDown();
        }
    }

    private void viewDisAppear() {
        CountDownTask countDownTask = this.mCountDownTask;
        if (countDownTask != null) {
            CountdownTimerUtils.removeCountDownTask(countDownTask);
        }
    }

    public void clearUseTimeOut() {
        JDViewKitDataSourceModel jDViewKitDataSourceModel = this.dataSourceModel;
        if (jDViewKitDataSourceModel == null || jDViewKitDataSourceModel.getDataMap() == null) {
            return;
        }
        this.dataSourceModel.getDataMap().put(useTimeOutKey, 0);
    }

    public int getUseTimeOut() {
        Object obj;
        JDViewKitDataSourceModel jDViewKitDataSourceModel = this.dataSourceModel;
        if (jDViewKitDataSourceModel == null || jDViewKitDataSourceModel.getDataMap() == null || (obj = this.dataSourceModel.getDataMap().get(useTimeOutKey)) == null || !(obj instanceof Integer)) {
            return 0;
        }
        return ((Integer) obj).intValue();
    }

    public void handleCountdown(int i2) {
    }

    public void initCountdownParamsByPlay(boolean z) {
        this.isPlay = z;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        viewAppear();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        viewDisAppear();
    }

    public void setCardSpace(JDViewKitTextView jDViewKitTextView, int i2) {
        if (jDViewKitTextView == null || i2 <= 0) {
            return;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) jDViewKitTextView.getLayoutParams();
        marginLayoutParams.leftMargin = GlobalManage.getInstance().getRealPx(i2, getChannelModel());
        jDViewKitTextView.setLayoutParams(marginLayoutParams);
    }

    public void setCountdownLifeCycle(int i2) {
        if (i2 == 2) {
            viewDisAppear();
        } else if (i2 == 1) {
            viewAppear();
        }
    }

    @Override // com.jd.viewkit.templates.view.JDViewKitAbsoluteLayout
    public void setDataSourceModel(JDViewKitDataSourceModel jDViewKitDataSourceModel, boolean z) {
        if (this.dataSourceModel == jDViewKitDataSourceModel) {
            return;
        }
        super.setDataSourceModel(jDViewKitDataSourceModel, z);
        JDViewKitVirtualCountdownView jDViewKitVirtualCountdownView = this.virtualCountdownView;
        if (jDViewKitVirtualCountdownView != null && jDViewKitVirtualCountdownView.getGapConfigView() != null && this.virtualCountdownView.getGapConfigView().getType() == 3 && jDViewKitDataSourceModel != null && jDViewKitDataSourceModel.getDataState() == 0) {
            jDViewKitDataSourceModel.setTimeStame(DateTool.getTime());
            jDViewKitDataSourceModel.setDataState(1);
        }
        beginCountDown();
    }

    public void setUseTimeOut() {
        JDViewKitDataSourceModel jDViewKitDataSourceModel = this.dataSourceModel;
        if (jDViewKitDataSourceModel == null || jDViewKitDataSourceModel.getDataMap() == null) {
            return;
        }
        this.dataSourceModel.getDataMap().put(useTimeOutKey, 1);
    }

    public void setVirtualView(JDViewKitVirtualCountdownView jDViewKitVirtualCountdownView) {
        super.setVirtualView((JDViewKitVirtualView) jDViewKitVirtualCountdownView);
        this.virtualCountdownView = jDViewKitVirtualCountdownView;
        if (jDViewKitVirtualCountdownView != null && jDViewKitVirtualCountdownView.getCardConfigView() != null && jDViewKitVirtualCountdownView.getGapConfigView() != null) {
            if (jDViewKitVirtualCountdownView.getGapConfigView().getType() == 3) {
                int unit = jDViewKitVirtualCountdownView.getGapConfigView().getUnit();
                this.textViewSix.setVisibility(8);
                this.textViewFive.setVisibility(8);
                this.textViewFour.setVisibility(8);
                this.textViewThree.setVisibility(8);
                if (jDViewKitVirtualCountdownView.getCardConfigView().getCardConfigType() != 0 && jDViewKitVirtualCountdownView.getCardConfigView().getCardConfigType() != 2) {
                    if (jDViewKitVirtualCountdownView.getCardConfigView().getCardConfigType() != 1) {
                        jDViewKitVirtualCountdownView.getCardConfigView().setBackgroundColor(null);
                        jDViewKitVirtualCountdownView.getCardConfigView().setBackgroundColorInt(0);
                        jDViewKitVirtualCountdownView.getCardConfigView().setBorderWidth(0);
                    }
                    this.textViewTwo.setVirtualTextView(jDViewKitVirtualCountdownView.getCardConfigView());
                    this.textViewOne.setVirtualTextView(jDViewKitVirtualCountdownView.getCardConfigView());
                    setCardSpace(this.textViewOne, jDViewKitVirtualCountdownView.getCardConfigView().getCardSpace());
                } else {
                    if (jDViewKitVirtualCountdownView.getCardConfigView().getCardConfigType() == 2) {
                        jDViewKitVirtualCountdownView.getCardConfigView().setBorderWidth(0);
                        jDViewKitVirtualCountdownView.getCardConfigView().setBorderRadius(0);
                        jDViewKitVirtualCountdownView.getCardConfigView().setBackgroundColor(null);
                        jDViewKitVirtualCountdownView.getCardConfigView().setBackgroundColorInt(0);
                    }
                    this.textViewTwo.setVisibility(8);
                    this.textViewOne.setVirtualTextView(jDViewKitVirtualCountdownView.getCardConfigView());
                }
                this.gapTextViewThree.setVisibility(8);
                this.gapTextViewTwo.setVisibility(8);
                if (unit == 1) {
                    this.gapTextViewOne.setText("\u79d2");
                } else {
                    this.gapTextViewOne.setText("S");
                }
                if (jDViewKitVirtualCountdownView.getCardConfigView().getCardConfigType() == 2) {
                    setGapTextStyle(this.gapTextViewOne, jDViewKitVirtualCountdownView.getGapConfigView(), true);
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.textViewOne.getLayoutParams();
                    ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) this.gapTextViewOne.getLayoutParams();
                    marginLayoutParams2.topMargin = marginLayoutParams.topMargin;
                    marginLayoutParams2.height = marginLayoutParams.height;
                    this.gapTextViewOne.setLayoutParams(marginLayoutParams2);
                    this.textViewOne.setGravity(80);
                    this.gapTextViewOne.setGravity(80);
                } else {
                    setGapTextStyle(this.gapTextViewOne, jDViewKitVirtualCountdownView.getGapConfigView(), false);
                    ViewGroup.MarginLayoutParams marginLayoutParams3 = (ViewGroup.MarginLayoutParams) this.gapTextViewOne.getLayoutParams();
                    marginLayoutParams3.height = 200;
                    int realPx = GlobalManage.getInstance().getRealPx(this.virtualCountdownView.getHeigh(), getChannelModel());
                    if (realPx > 200) {
                        marginLayoutParams3.height = realPx;
                    }
                    this.gapTextViewOne.setLayoutParams(marginLayoutParams3);
                    this.gapTextViewOne.setGravity(16);
                }
            } else {
                if (jDViewKitVirtualCountdownView.getCardConfigView().getCardConfigType() != 0 && jDViewKitVirtualCountdownView.getCardConfigView().getCardConfigType() != 2) {
                    if (jDViewKitVirtualCountdownView.getCardConfigView().getCardConfigType() != 1) {
                        jDViewKitVirtualCountdownView.getCardConfigView().setBackgroundColor(null);
                        jDViewKitVirtualCountdownView.getCardConfigView().setBackgroundColorInt(0);
                        jDViewKitVirtualCountdownView.getCardConfigView().setBorderWidth(0);
                    }
                    this.textViewSix.setVirtualTextView(jDViewKitVirtualCountdownView.getCardConfigView());
                    this.textViewFive.setVirtualTextView(jDViewKitVirtualCountdownView.getCardConfigView());
                    setCardSpace(this.textViewFive, jDViewKitVirtualCountdownView.getCardConfigView().getCardSpace());
                    this.textViewFour.setVirtualTextView(jDViewKitVirtualCountdownView.getCardConfigView());
                    this.textViewThree.setVirtualTextView(jDViewKitVirtualCountdownView.getCardConfigView());
                    setCardSpace(this.textViewThree, jDViewKitVirtualCountdownView.getCardConfigView().getCardSpace());
                    this.textViewTwo.setVirtualTextView(jDViewKitVirtualCountdownView.getCardConfigView());
                    this.textViewOne.setVirtualTextView(jDViewKitVirtualCountdownView.getCardConfigView());
                    setCardSpace(this.textViewOne, jDViewKitVirtualCountdownView.getCardConfigView().getCardSpace());
                } else {
                    if (jDViewKitVirtualCountdownView.getCardConfigView().getCardConfigType() == 2) {
                        jDViewKitVirtualCountdownView.getCardConfigView().setBorderWidth(0);
                        jDViewKitVirtualCountdownView.getCardConfigView().setBorderRadius(0);
                        jDViewKitVirtualCountdownView.getCardConfigView().setBackgroundColor(null);
                        jDViewKitVirtualCountdownView.getCardConfigView().setBackgroundColorInt(0);
                    }
                    this.textViewSix.setVisibility(8);
                    this.textViewFive.setVirtualTextView(jDViewKitVirtualCountdownView.getCardConfigView());
                    this.textViewFour.setVisibility(8);
                    this.textViewThree.setVirtualTextView(jDViewKitVirtualCountdownView.getCardConfigView());
                    this.textViewTwo.setVisibility(8);
                    this.textViewOne.setVirtualTextView(jDViewKitVirtualCountdownView.getCardConfigView());
                }
                if (jDViewKitVirtualCountdownView.getGapConfigView().getType() == 2) {
                    this.gapTextViewThree.setText("\u65f6");
                    this.gapTextViewTwo.setText("\u5206");
                    this.gapTextViewOne.setText("\u79d2");
                } else {
                    String str = jDViewKitVirtualCountdownView.getGapConfigView().getType() == 1 ? LangUtils.SINGLE_SPACE : ":";
                    this.gapTextViewThree.setText(str);
                    this.gapTextViewTwo.setText(str);
                    this.gapTextViewOne.setText("");
                }
                setGapTextStyle(this.gapTextViewThree, jDViewKitVirtualCountdownView.getGapConfigView(), false);
                setGapTextStyle(this.gapTextViewTwo, jDViewKitVirtualCountdownView.getGapConfigView(), false);
                setGapTextStyle(this.gapTextViewOne, jDViewKitVirtualCountdownView.getGapConfigView(), false);
            }
            this.textViewOne.setClickable(false);
            this.textViewSix.setClickable(false);
            this.textViewFive.setClickable(false);
            this.textViewFour.setClickable(false);
            this.textViewThree.setClickable(false);
            this.textViewTwo.setClickable(false);
            this.textViewOne.setClickable(false);
            return;
        }
        setVisibility(8);
    }

    public void timeOut() {
        if (this.virtualCountdownView.getVirtualEventByType(JDViewKitVirtualEvent.typeTimeOutEvent) != null && getUseTimeOut() <= 0) {
            setUseTimeOut();
            CountdownTimerUtils.removeCountDownTask(this.mCountDownTask);
            JDViewKitEventHelper.timeOut(this.virtualCountdownView, this.dataSourceModel, this, getChannelModel());
        }
    }

    public void updateCountDown(long j2) {
        int i2;
        int i3;
        if (j2 <= 0) {
            if (this.virtualCountdownView.getIsEndHide() == 1) {
                setVisibility(8);
            }
            timeOut();
        }
        long j3 = j2 >= 0 ? j2 : 0L;
        if (this.virtualCountdownView.getCardConfigView() != null) {
            if (this.virtualCountdownView.getGapConfigView() != null && this.virtualCountdownView.getGapConfigView().getType() == 3) {
                int i4 = j3 > 99 ? 99 : (int) j3;
                if (this.virtualCountdownView.getCardConfigView().getCardConfigType() != 0 && this.virtualCountdownView.getCardConfigView().getCardConfigType() != 2) {
                    this.textViewTwo.setText("" + (i4 / 10));
                    this.textViewOne.setText("" + (i4 % 10));
                    return;
                }
                this.textViewOne.setText((i4 / 10) + "" + (i4 % 10));
                return;
            }
            int i5 = 59;
            if (this.virtualCountdownView.getGapConfigView() != null && this.virtualCountdownView.getGapConfigView().getType() == 2 && j3 >= TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC) {
                long j4 = j3 / TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC;
                if (j4 <= 99) {
                    r5 = j4 <= 99 ? j4 : 99L;
                    i3 = (int) ((j3 % TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC) / 3600);
                    i5 = (int) ((j3 % 3600) / 60);
                    r5 = r5;
                } else {
                    i3 = 23;
                }
                if (this.virtualCountdownView.getGapConfigView().getType() == 2) {
                    this.gapTextViewThree.setText("\u5929");
                    this.gapTextViewTwo.setText("\u65f6");
                    this.gapTextViewOne.setText("\u5206");
                }
            } else {
                long j5 = j3 / 3600;
                if (j5 <= 99) {
                    r5 = j5 <= 99 ? j5 : 99L;
                    i5 = (int) ((j3 % 3600) / 60);
                    i2 = (int) (j3 % 60);
                } else {
                    i2 = 59;
                }
                if (this.virtualCountdownView.getGapConfigView().getType() == 2) {
                    this.gapTextViewThree.setText("\u65f6");
                    this.gapTextViewTwo.setText("\u5206");
                    this.gapTextViewOne.setText("\u79d2");
                }
                i3 = i5;
                i5 = i2;
            }
            if (this.virtualCountdownView.getCardConfigView().getCardConfigType() != 0 && this.virtualCountdownView.getCardConfigView().getCardConfigType() != 2) {
                this.textViewSix.setText("" + (r5 / 10));
                this.textViewFive.setText("" + (r5 % 10));
                this.textViewFour.setText("" + (i3 / 10));
                this.textViewThree.setText("" + (i3 % 10));
                this.textViewTwo.setText("" + (i5 / 10));
                this.textViewOne.setText("" + (i5 % 10));
                return;
            }
            this.textViewFive.setText((r5 / 10) + "" + (r5 % 10));
            this.textViewThree.setText((i3 / 10) + "" + (i3 % 10));
            this.textViewOne.setText((i5 / 10) + "" + (i5 % 10));
        }
    }

    public JDViewKitCountdownView(@NonNull Context context, @NonNull JDViewKitChannelModel jDViewKitChannelModel) {
        this(context);
        this.channelModel = jDViewKitChannelModel;
    }
}
