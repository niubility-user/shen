package com.jingdong.common.messagecenter.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.utils.ui.view.JDMultiTextView;
import com.jingdong.common.R;
import com.jingdong.common.messagecenter.view.AutoScrollTextView;
import com.jingdong.common.widget.RedPointTextView;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes5.dex */
public class RedPointNumView extends RelativeLayout {
    private int curNum;
    private CopyOnWriteArrayList<Integer> curlist;
    private boolean isViewGone;
    private boolean mBackGround;
    private int mBackGroundColor;
    private final Context mContext;
    private JDMultiTextView plus_icon;
    private CopyOnWriteArrayList<Integer> preList;
    private int pre_Num;
    private AutoScrollTextView redpoint_ge;
    private AutoScrollTextView redpoint_shi;

    public RedPointNumView(Context context) {
        this(context, null);
    }

    private void decreaseNum(int i2, final int i3) {
        if (this.preList.get(0).intValue() > 0) {
            show99Plus();
            if (this.curlist.get(0).intValue() > 0) {
                return;
            }
        } else if (this.preList.get(1).intValue() > 0) {
            show10Plus(this.preList);
        } else if (this.preList.get(2).intValue() > 0) {
            showNumPlus(this.preList);
        } else {
            showNone();
        }
        this.redpoint_shi.setAutoNumChangeListener(new AutoScrollTextView.RedNumChangeListener() { // from class: com.jingdong.common.messagecenter.view.RedPointNumView.1
            @Override // com.jingdong.common.messagecenter.view.AutoScrollTextView.RedNumChangeListener
            public void OnRedNumMore() {
            }

            @Override // com.jingdong.common.messagecenter.view.AutoScrollTextView.RedNumChangeListener
            public void OnRedNumless() {
            }

            @Override // com.jingdong.common.messagecenter.view.AutoScrollTextView.RedNumChangeListener
            public void onFinish() {
                RedPointNumView.this.redpoint_shi.onRemoveTask();
            }
        });
        this.redpoint_ge.setAutoNumChangeListener(new AutoScrollTextView.RedNumChangeListener() { // from class: com.jingdong.common.messagecenter.view.RedPointNumView.2
            @Override // com.jingdong.common.messagecenter.view.AutoScrollTextView.RedNumChangeListener
            public void OnRedNumMore() {
            }

            @Override // com.jingdong.common.messagecenter.view.AutoScrollTextView.RedNumChangeListener
            public void OnRedNumless() {
                int intValue;
                if (RedPointNumView.this.preList.size() == 3 && RedPointNumView.this.curlist.size() == 3 && (intValue = ((Integer) RedPointNumView.this.preList.get(1)).intValue()) > ((Integer) RedPointNumView.this.curlist.get(1)).intValue()) {
                    AutoScrollTextView autoScrollTextView = RedPointNumView.this.redpoint_shi;
                    AutoScrollTextView.Mode mode = AutoScrollTextView.Mode.DOWN;
                    autoScrollTextView.setMode(mode);
                    int i4 = intValue - 1;
                    if (i4 > 0) {
                        RedPointNumView.this.redpoint_shi.setTargetNumber(i4);
                    } else {
                        RedPointNumView.this.redpoint_shi.setNumber(0);
                        RedPointNumView.this.redpoint_shi.setVisibility(8);
                        RedPointNumView.this.setViewBackGround(true);
                    }
                    RedPointNumView.this.preList.set(1, Integer.valueOf(i4));
                    if (RedPointNumView.this.curlist.get(1) == RedPointNumView.this.preList.get(1)) {
                        RedPointNumView.this.redpoint_ge.setMode(mode);
                        RedPointNumView.this.redpoint_ge.setTargetNumber(((Integer) RedPointNumView.this.curlist.get(2)).intValue());
                    }
                }
            }

            @Override // com.jingdong.common.messagecenter.view.AutoScrollTextView.RedNumChangeListener
            public void onFinish() {
                if (i3 <= 0) {
                    RedPointNumView.this.redpoint_ge.setVisibility(8);
                    RedPointNumView.this.setBackgroundColor(0);
                }
                RedPointNumView.this.redpoint_ge.onRemoveTask();
            }
        });
        this.redpoint_ge.setMode(AutoScrollTextView.Mode.DOWN);
        if (i3 < 100) {
            this.plus_icon.setVisibility(8);
        }
        if (this.preList.get(1) == this.curlist.get(1)) {
            this.redpoint_ge.setTargetNumber(this.curlist.get(2).intValue());
        } else {
            this.redpoint_ge.setTargetNumber(-2);
        }
    }

    private CopyOnWriteArrayList<Integer> getList(int i2) {
        CopyOnWriteArrayList<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        if (i2 >= 100) {
            copyOnWriteArrayList.add(0, 1);
            copyOnWriteArrayList.add(1, 9);
            copyOnWriteArrayList.add(2, 9);
        } else {
            copyOnWriteArrayList.add(0, 0);
            copyOnWriteArrayList.add(1, Integer.valueOf((i2 / 10) % 10));
            copyOnWriteArrayList.add(2, Integer.valueOf(i2 % 10));
        }
        return copyOnWriteArrayList;
    }

    private void increaseNum(int i2, final int i3) {
        if (this.preList.get(0).intValue() > 0) {
            show99Plus();
            return;
        }
        if (this.preList.get(1).intValue() > 0) {
            if (i2 == 99 && i3 > 99) {
                showCurNum(100);
                return;
            }
            show10Plus(this.preList);
        } else if (this.preList.get(2).intValue() > 0) {
            showNumPlus(this.preList);
        } else {
            showZero();
        }
        this.redpoint_shi.setAutoNumChangeListener(new AutoScrollTextView.RedNumChangeListener() { // from class: com.jingdong.common.messagecenter.view.RedPointNumView.3
            @Override // com.jingdong.common.messagecenter.view.AutoScrollTextView.RedNumChangeListener
            public void OnRedNumMore() {
            }

            @Override // com.jingdong.common.messagecenter.view.AutoScrollTextView.RedNumChangeListener
            public void OnRedNumless() {
            }

            @Override // com.jingdong.common.messagecenter.view.AutoScrollTextView.RedNumChangeListener
            public void onFinish() {
                RedPointNumView.this.redpoint_shi.onRemoveTask();
            }
        });
        this.redpoint_ge.setAutoNumChangeListener(new AutoScrollTextView.RedNumChangeListener() { // from class: com.jingdong.common.messagecenter.view.RedPointNumView.4
            @Override // com.jingdong.common.messagecenter.view.AutoScrollTextView.RedNumChangeListener
            public void OnRedNumMore() {
                if (RedPointNumView.this.preList.size() == 3 && RedPointNumView.this.curlist.size() == 3) {
                    int intValue = ((Integer) RedPointNumView.this.preList.get(1)).intValue();
                    int i4 = intValue + 1;
                    RedPointNumView.this.preList.set(1, Integer.valueOf(i4));
                    if (((Integer) RedPointNumView.this.preList.get(1)).intValue() >= 10) {
                        RedPointNumView.this.show99Plus();
                    } else if (((Integer) RedPointNumView.this.curlist.get(1)).intValue() > intValue) {
                        RedPointNumView.this.redpoint_shi.setVisibility(0);
                        RedPointNumView.this.setViewBackGround(false);
                        String str = "shi_num:" + intValue;
                        if (RedPointNumView.this.curlist.get(1) == RedPointNumView.this.preList.get(1)) {
                            RedPointNumView.this.redpoint_ge.setNumber(0);
                            RedPointNumView.this.redpoint_ge.setMode(AutoScrollTextView.Mode.UP);
                            RedPointNumView.this.redpoint_ge.setTargetNumber(((Integer) RedPointNumView.this.curlist.get(2)).intValue());
                        }
                        if (intValue != 0) {
                            RedPointNumView.this.redpoint_shi.setMode(AutoScrollTextView.Mode.UP);
                            RedPointNumView.this.redpoint_shi.setTargetNumber(i4);
                            return;
                        }
                        RedPointNumView.this.redpoint_shi.setMode(AutoScrollTextView.Mode.KEEP);
                        RedPointNumView.this.redpoint_shi.setNumber(i4);
                    }
                }
            }

            @Override // com.jingdong.common.messagecenter.view.AutoScrollTextView.RedNumChangeListener
            public void OnRedNumless() {
            }

            @Override // com.jingdong.common.messagecenter.view.AutoScrollTextView.RedNumChangeListener
            public void onFinish() {
                if (i3 > 99) {
                    RedPointNumView.this.plus_icon.setVisibility(0);
                }
                RedPointNumView.this.redpoint_ge.onRemoveTask();
            }
        });
        this.redpoint_ge.setMode(AutoScrollTextView.Mode.UP);
        if (this.preList.get(1) == this.curlist.get(1)) {
            this.redpoint_ge.setTargetNumber(this.curlist.get(2).intValue());
        } else {
            this.redpoint_ge.setTargetNumber(i3);
        }
    }

    private void initNum(int i2, int i3) {
        setRedBackground(i2);
        setSpeed(i2, i3);
        setRedPoint(i2, i3);
        if (i2 < i3) {
            increaseNum(i2, i3);
        } else if (i2 > i3) {
            decreaseNum(i2, i3);
        } else {
            showNum(i3);
        }
    }

    private void initView() {
        this.redpoint_shi = (AutoScrollTextView) findViewById(R.id.autoview_shi);
        this.redpoint_ge = (AutoScrollTextView) findViewById(R.id.autoview_ge);
        JDMultiTextView jDMultiTextView = (JDMultiTextView) findViewById(R.id.plus_icon);
        this.plus_icon = jDMultiTextView;
        jDMultiTextView.setMultiTypeFace();
        this.redpoint_shi.setVisibility(8);
        this.redpoint_ge.setVisibility(8);
        this.plus_icon.setVisibility(8);
    }

    private void setBackground(boolean z) {
        this.mBackGround = z;
        if (this.curNum < 10) {
            setViewBackGround(true);
        } else {
            setViewBackGround(false);
        }
    }

    private void setRedBackground(int i2) {
        if (i2 <= 0) {
            this.redpoint_shi.setVisibility(8);
            this.redpoint_ge.setVisibility(8);
            this.plus_icon.setVisibility(8);
            setBackgroundColor(0);
        } else if (i2 < 10) {
            this.redpoint_shi.setVisibility(8);
            this.redpoint_ge.setVisibility(0);
            this.plus_icon.setVisibility(8);
            setViewBackGround(true);
        } else if (i2 < 100) {
            this.redpoint_shi.setVisibility(0);
            this.redpoint_ge.setVisibility(0);
            this.plus_icon.setVisibility(8);
            setViewBackGround(false);
        } else {
            this.redpoint_shi.setVisibility(0);
            this.redpoint_ge.setVisibility(0);
            this.plus_icon.setVisibility(0);
            setViewBackGround(false);
        }
    }

    private void setRedPoint(int i2, int i3) {
        this.preList = getList(i2);
        this.curlist = getList(i3);
    }

    private void setSpeed(int i2, int i3) {
        this.redpoint_ge.setSpeed(Math.abs(Math.min(i2, 99) - Math.min(i3, 99)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setViewBackGround(boolean z) {
        if (z) {
            if (this.mBackGround) {
                setBackgroundResource(R.drawable.red_point_bg1);
            } else {
                setBackgroundResource(R.drawable.red_point_white_bg1);
            }
        } else if (this.mBackGround) {
            setBackgroundResource(R.drawable.red_point_bg2);
        } else {
            setBackgroundResource(R.drawable.red_point_white_bg2);
        }
    }

    private void show10Plus(CopyOnWriteArrayList<Integer> copyOnWriteArrayList) {
        AutoScrollTextView autoScrollTextView = this.redpoint_shi;
        AutoScrollTextView.Mode mode = AutoScrollTextView.Mode.KEEP;
        autoScrollTextView.setMode(mode);
        this.redpoint_ge.setMode(mode);
        this.redpoint_shi.setNumber(copyOnWriteArrayList.get(1).intValue());
        this.redpoint_ge.setNumber(copyOnWriteArrayList.get(2).intValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void show99Plus() {
        AutoScrollTextView autoScrollTextView = this.redpoint_shi;
        AutoScrollTextView.Mode mode = AutoScrollTextView.Mode.KEEP;
        autoScrollTextView.setMode(mode);
        this.redpoint_ge.setMode(mode);
        this.redpoint_shi.setNumber(9);
        this.redpoint_ge.setNumber(9);
    }

    private void show9Plus() {
        this.redpoint_shi.setVisibility(8);
        this.redpoint_ge.setVisibility(0);
        this.plus_icon.setVisibility(0);
        AutoScrollTextView autoScrollTextView = this.redpoint_shi;
        AutoScrollTextView.Mode mode = AutoScrollTextView.Mode.KEEP;
        autoScrollTextView.setMode(mode);
        this.redpoint_ge.setMode(mode);
        this.redpoint_ge.setNumber(9);
    }

    private void showNone() {
        this.redpoint_ge.setVisibility(8);
        this.redpoint_shi.setVisibility(8);
        this.plus_icon.setVisibility(8);
    }

    private void showNum(int i2) {
        setRedBackground(i2);
        setRedPoint(i2, i2);
        if (i2 > 99) {
            show99Plus();
        } else if (i2 > 9) {
            show10Plus(this.curlist);
        } else if (i2 > 0) {
            showNumPlus(this.curlist);
        } else {
            showNone();
        }
    }

    private void showNumPlus(CopyOnWriteArrayList<Integer> copyOnWriteArrayList) {
        AutoScrollTextView autoScrollTextView = this.redpoint_shi;
        AutoScrollTextView.Mode mode = AutoScrollTextView.Mode.KEEP;
        autoScrollTextView.setMode(mode);
        this.redpoint_ge.setMode(mode);
        this.redpoint_shi.setNumber(0);
        this.redpoint_ge.setNumber(copyOnWriteArrayList.get(2).intValue());
    }

    private void showZero() {
        AutoScrollTextView autoScrollTextView = this.redpoint_shi;
        AutoScrollTextView.Mode mode = AutoScrollTextView.Mode.KEEP;
        autoScrollTextView.setMode(mode);
        this.redpoint_ge.setMode(mode);
        this.redpoint_ge.setNumber(0);
        this.redpoint_ge.setVisibility(0);
        setViewBackGround(true);
    }

    public void onDestory() {
        showNum(this.curNum);
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i2) {
        super.onWindowVisibilityChanged(i2);
        if (i2 != 0) {
            this.isViewGone = true;
            onDestory();
        }
        if (this.isViewGone) {
            showNum(this.pre_Num);
        }
    }

    public void setCurNum(int i2) {
        synchronized (RedPointTextView.class) {
            this.curNum = i2;
            initNum(this.pre_Num, i2);
            this.pre_Num = i2;
        }
    }

    public void setPreNum(int i2) {
        this.pre_Num = i2;
    }

    public void setRedPointColor(int i2, boolean z) {
        this.redpoint_ge.setTextColor(i2);
        this.redpoint_shi.setTextColor(i2);
        this.plus_icon.setTextColor(i2);
        setBackground(z);
    }

    public void showCurNum(int i2) {
        synchronized (RedPointTextView.class) {
            this.curNum = i2;
            showNum(i2);
            this.pre_Num = i2;
        }
    }

    public void showNum9Plus() {
        synchronized (RedPointTextView.class) {
            this.curNum = this.curNum;
            show9Plus();
            this.pre_Num = this.curNum;
        }
    }

    public RedPointNumView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mBackGround = true;
        this.mBackGroundColor = -905168;
        this.mContext = context;
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.redpoint_run_view, (ViewGroup) this, true);
        initView();
    }
}
