package com.jingdong.common.jdreactFramework.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.os.Handler;
import android.os.Message;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.internal.view.SupportMenu;
import com.jingdong.common.jdreactFramework.R;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class WheelView extends View {
    private static final int MOVE_NUMBER = 5;
    private static final int REFRESH_VIEW = 1;
    private static final String TAG = "WheelView";
    private float controlHeight;
    private float controlWidth;
    private List<String> dataList;
    private long downTime;
    private int downY;
    private int goonDistance;
    private long goonTime;
    @SuppressLint({"HandlerLeak"})
    Handler handler;
    private boolean isClearing;
    private boolean isEnable;
    private boolean isScrolling;
    private ArrayList<ItemObject> itemList;
    private int itemNumber;
    private int lineColor;
    private float lineHeight;
    private Paint linePaint;
    private float maskHeight;
    private boolean noEmpty;
    private int normalColor;
    private float normalFont;
    private OnSelectListener onSelectListener;
    private int selectedColor;
    private float selectedFont;
    private int unitHeight;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public class ItemObject {
        private TextPaint textPaint;
        private Rect textRect;
        public int id = 0;
        public String itemText = "";
        public int x = 0;
        public int y = 0;
        public int move = 0;

        public ItemObject() {
        }

        public void drawSelf(Canvas canvas, int i2) {
            if (this.textPaint == null) {
                TextPaint textPaint = new TextPaint();
                this.textPaint = textPaint;
                textPaint.setAntiAlias(true);
            }
            if (this.textRect == null) {
                this.textRect = new Rect();
            }
            if (isSelected()) {
                this.textPaint.setColor(WheelView.this.selectedColor);
                float moveToSelected = moveToSelected();
                if (moveToSelected <= 0.0f) {
                    moveToSelected *= -1.0f;
                }
                this.textPaint.setTextSize(WheelView.this.normalFont + ((WheelView.this.selectedFont - WheelView.this.normalFont) * (1.0f - (moveToSelected / WheelView.this.unitHeight))));
            } else {
                this.textPaint.setColor(WheelView.this.normalColor);
                this.textPaint.setTextSize(WheelView.this.normalFont);
            }
            String str = (String) TextUtils.ellipsize(this.itemText, this.textPaint, i2, TextUtils.TruncateAt.END);
            this.itemText = str;
            this.textPaint.getTextBounds(str, 0, str.length(), this.textRect);
            if (isInView()) {
                canvas.drawText(this.itemText, (this.x + (WheelView.this.controlWidth / 2.0f)) - (this.textRect.width() >> 1), this.y + this.move + ((WheelView.this.unitHeight + this.textRect.height()) >> 1), this.textPaint);
            }
        }

        public boolean isInView() {
            return ((float) (this.y + this.move)) <= WheelView.this.controlHeight && (this.y + this.move) + ((WheelView.this.unitHeight + this.textRect.height()) >> 1) >= 0;
        }

        public boolean isLast() {
            return WheelView.this.dataList != null && WheelView.this.dataList.size() - 1 == this.id;
        }

        public boolean isSelected() {
            if (this.y + this.move < ((WheelView.this.controlHeight / 2.0f) - (WheelView.this.unitHeight >> 1)) + WheelView.this.lineHeight || this.y + this.move > ((WheelView.this.controlHeight / 2.0f) + (WheelView.this.unitHeight >> 1)) - WheelView.this.lineHeight) {
                if (this.y + this.move + WheelView.this.unitHeight < ((WheelView.this.controlHeight / 2.0f) - (WheelView.this.unitHeight >> 1)) + WheelView.this.lineHeight || this.y + this.move + WheelView.this.unitHeight > ((WheelView.this.controlHeight / 2.0f) + (WheelView.this.unitHeight >> 1)) - WheelView.this.lineHeight) {
                    return ((float) (this.y + this.move)) <= ((WheelView.this.controlHeight / 2.0f) - ((float) (WheelView.this.unitHeight >> 1))) + WheelView.this.lineHeight && ((float) ((this.y + this.move) + WheelView.this.unitHeight)) >= ((WheelView.this.controlHeight / 2.0f) + ((float) (WheelView.this.unitHeight >> 1))) - WheelView.this.lineHeight;
                }
                return true;
            }
            return true;
        }

        public boolean isSelectedAllowEmpty() {
            float f2 = ((WheelView.this.controlHeight / 2.0f) - (WheelView.this.unitHeight >> 1)) + WheelView.this.lineHeight;
            float f3 = ((WheelView.this.controlHeight / 2.0f) - (WheelView.this.unitHeight >> 1)) + WheelView.this.lineHeight;
            if (this.id != 0 || this.y + this.move < f2) {
                if (!isLast() || this.y + this.move > f3) {
                    return isSelected();
                }
                return true;
            }
            return true;
        }

        public void move(int i2) {
            this.move = i2;
        }

        public float moveToSelected() {
            return ((WheelView.this.controlHeight / 2.0f) - (WheelView.this.unitHeight >> 1)) - (this.y + this.move);
        }

        public void newY(int i2) {
            this.move = 0;
            this.y += i2;
        }
    }

    /* loaded from: classes5.dex */
    public interface OnSelectListener {
        void endSelect(int i2, String str);

        void selecting(int i2, String str);
    }

    public WheelView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.isScrolling = false;
        this.itemList = new ArrayList<>();
        this.dataList = new ArrayList();
        this.downTime = 0L;
        this.goonTime = 200L;
        this.goonDistance = 100;
        this.lineColor = -16777216;
        this.lineHeight = 2.0f;
        this.normalFont = 14.0f;
        this.selectedFont = 22.0f;
        this.unitHeight = 50;
        this.itemNumber = 7;
        this.normalColor = -16777216;
        this.selectedColor = SupportMenu.CATEGORY_MASK;
        this.maskHeight = 48.0f;
        this.isEnable = true;
        this.noEmpty = true;
        this.isClearing = false;
        this.handler = new Handler() { // from class: com.jingdong.common.jdreactFramework.utils.WheelView.3
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                if (message.what != 1) {
                    return;
                }
                WheelView.this.invalidate();
            }
        };
        init(context, attributeSet);
        initData();
    }

    private void actionMove(int i2) {
        Iterator<ItemObject> it = this.itemList.iterator();
        while (it.hasNext()) {
            it.next().move(i2);
        }
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void actionThreadMove(int i2) {
        Iterator<ItemObject> it = this.itemList.iterator();
        while (it.hasNext()) {
            it.next().move(i2);
        }
        Message message = new Message();
        message.what = 1;
        this.handler.sendMessage(message);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void actionUp(int i2) {
        int moveToSelected;
        if (i2 > 0) {
            for (int i3 = 0; i3 < this.itemList.size(); i3++) {
                if (this.itemList.get(i3).isSelected()) {
                    moveToSelected = (int) this.itemList.get(i3).moveToSelected();
                    OnSelectListener onSelectListener = this.onSelectListener;
                    if (onSelectListener != null) {
                        onSelectListener.endSelect(this.itemList.get(i3).id, this.itemList.get(i3).itemText);
                    }
                }
            }
            moveToSelected = 0;
        } else {
            for (int size = this.itemList.size() - 1; size >= 0; size--) {
                if (this.itemList.get(size).isSelected()) {
                    moveToSelected = (int) this.itemList.get(size).moveToSelected();
                    OnSelectListener onSelectListener2 = this.onSelectListener;
                    if (onSelectListener2 != null) {
                        onSelectListener2.endSelect(this.itemList.get(size).id, this.itemList.get(size).itemText);
                    }
                }
            }
            moveToSelected = 0;
        }
        Iterator<ItemObject> it = this.itemList.iterator();
        while (it.hasNext()) {
            it.next().newY(i2 + 0);
        }
        slowMove(moveToSelected);
        Message message = new Message();
        message.what = 1;
        this.handler.sendMessage(message);
    }

    private void defaultMove(int i2) {
        Iterator<ItemObject> it = this.itemList.iterator();
        while (it.hasNext()) {
            it.next().newY(i2);
        }
        Message message = new Message();
        message.what = 1;
        this.handler.sendMessage(message);
    }

    private void drawLine(Canvas canvas) {
        if (this.linePaint == null) {
            Paint paint = new Paint();
            this.linePaint = paint;
            paint.setColor(this.lineColor);
            this.linePaint.setAntiAlias(true);
            this.linePaint.setStrokeWidth(this.lineHeight);
        }
        float f2 = this.controlHeight;
        int i2 = this.unitHeight;
        float f3 = this.lineHeight;
        canvas.drawLine(0.0f, ((f2 / 2.0f) - (i2 >> 1)) + f3, this.controlWidth, ((f2 / 2.0f) - (i2 >> 1)) + f3, this.linePaint);
        float f4 = this.controlHeight;
        int i3 = this.unitHeight;
        float f5 = this.lineHeight;
        canvas.drawLine(0.0f, ((f4 / 2.0f) + (i3 >> 1)) - f5, this.controlWidth, ((f4 / 2.0f) + (i3 >> 1)) - f5, this.linePaint);
    }

    private synchronized void drawList(Canvas canvas) {
        if (this.isClearing) {
            return;
        }
        try {
            Iterator<ItemObject> it = this.itemList.iterator();
            while (it.hasNext()) {
                it.next().drawSelf(canvas, getMeasuredWidth());
            }
        } catch (Exception unused) {
        }
    }

    private void drawMask(Canvas canvas) {
        LinearGradient linearGradient = new LinearGradient(0.0f, 0.0f, 0.0f, this.maskHeight, 15921906, 15921906, Shader.TileMode.MIRROR);
        Paint paint = new Paint();
        paint.setShader(linearGradient);
        canvas.drawRect(0.0f, 0.0f, this.controlWidth, this.maskHeight, paint);
        float f2 = this.controlHeight;
        LinearGradient linearGradient2 = new LinearGradient(0.0f, f2 - this.maskHeight, 0.0f, f2, 15921906, 15921906, Shader.TileMode.MIRROR);
        Paint paint2 = new Paint();
        paint2.setShader(linearGradient2);
        float f3 = this.controlHeight;
        canvas.drawRect(0.0f, f3 - this.maskHeight, this.controlWidth, f3, paint2);
    }

    private synchronized void goonMove(final int i2) {
        new Thread(new Runnable() { // from class: com.jingdong.common.jdreactFramework.utils.WheelView.1
            @Override // java.lang.Runnable
            public void run() {
                int i3 = 0;
                while (i3 < WheelView.this.unitHeight * 5) {
                    try {
                        Thread.sleep(5L);
                    } catch (InterruptedException e2) {
                        OKLog.e(WheelView.TAG, e2);
                    }
                    WheelView.this.actionThreadMove(i2 > 0 ? i3 : i3 * (-1));
                    i3 += 10;
                }
                WheelView.this.actionUp(i2 > 0 ? i3 - 10 : (i3 * (-1)) + 10);
                WheelView.this.noEmpty();
            }
        }).start();
    }

    private void init(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.JDReactWheelView);
        this.unitHeight = (int) obtainStyledAttributes.getDimension(R.styleable.JDReactWheelView_jdreact_unitHeight, this.unitHeight);
        this.itemNumber = obtainStyledAttributes.getInt(R.styleable.JDReactWheelView_jdreact_itemNumber, this.itemNumber);
        this.normalFont = obtainStyledAttributes.getDimension(R.styleable.JDReactWheelView_jdreact_normalTextSize, this.normalFont);
        this.selectedFont = obtainStyledAttributes.getDimension(R.styleable.JDReactWheelView_jdreact_selectedTextSize, this.selectedFont);
        this.normalColor = obtainStyledAttributes.getColor(R.styleable.JDReactWheelView_jdreact_normalTextColor, this.normalColor);
        this.selectedColor = obtainStyledAttributes.getColor(R.styleable.JDReactWheelView_jdreact_selectedTextColor, this.selectedColor);
        this.lineColor = obtainStyledAttributes.getColor(R.styleable.JDReactWheelView_jdreact_lineColor, this.lineColor);
        this.lineHeight = obtainStyledAttributes.getDimension(R.styleable.JDReactWheelView_jdreact_lineHeight, this.lineHeight);
        this.maskHeight = obtainStyledAttributes.getDimension(R.styleable.JDReactWheelView_jdreact_maskHeight, this.maskHeight);
        this.noEmpty = obtainStyledAttributes.getBoolean(R.styleable.JDReactWheelView_jdreact_noEmpty, true);
        this.isEnable = obtainStyledAttributes.getBoolean(R.styleable.JDReactWheelView_jdreact_isEnable, true);
        obtainStyledAttributes.recycle();
        this.controlHeight = this.itemNumber * this.unitHeight;
    }

    private void initData() {
        this.isClearing = true;
        this.itemList.clear();
        for (int i2 = 0; i2 < this.dataList.size(); i2++) {
            ItemObject itemObject = new ItemObject();
            itemObject.id = i2;
            itemObject.itemText = this.dataList.get(i2);
            itemObject.x = 0;
            itemObject.y = this.unitHeight * i2;
            this.itemList.add(itemObject);
        }
        this.isClearing = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void noEmpty() {
        if (this.noEmpty) {
            Iterator<ItemObject> it = this.itemList.iterator();
            while (it.hasNext()) {
                if (it.next().isSelected()) {
                    return;
                }
            }
            int moveToSelected = (int) this.itemList.get(0).moveToSelected();
            if (moveToSelected < 0) {
                defaultMove(moveToSelected);
            } else {
                defaultMove((int) this.itemList.get(r0.size() - 1).moveToSelected());
            }
            Iterator<ItemObject> it2 = this.itemList.iterator();
            while (it2.hasNext()) {
                ItemObject next = it2.next();
                if (next.isSelected()) {
                    OnSelectListener onSelectListener = this.onSelectListener;
                    if (onSelectListener != null) {
                        onSelectListener.endSelect(next.id, next.itemText);
                        return;
                    }
                    return;
                }
            }
        }
    }

    private void onSelectListener() {
        if (this.onSelectListener == null) {
            return;
        }
        Iterator<ItemObject> it = this.itemList.iterator();
        while (it.hasNext()) {
            ItemObject next = it.next();
            if (next.isSelected()) {
                this.onSelectListener.selecting(next.id, next.itemText);
            }
        }
    }

    private synchronized void slowMove(final int i2) {
        new Thread(new Runnable() { // from class: com.jingdong.common.jdreactFramework.utils.WheelView.2
            @Override // java.lang.Runnable
            public void run() {
                int i3 = i2;
                int i4 = i3 > 0 ? i3 : i3 * (-1);
                int i5 = i3 > 0 ? 1 : -1;
                while (true) {
                    i4--;
                    if (i4 <= 0) {
                        break;
                    }
                    Iterator it = WheelView.this.itemList.iterator();
                    while (it.hasNext()) {
                        ((ItemObject) it.next()).newY(1 * i5);
                    }
                    Message message = new Message();
                    message.what = 1;
                    WheelView.this.handler.sendMessage(message);
                    try {
                        Thread.sleep(2L);
                    } catch (InterruptedException e2) {
                        OKLog.e(WheelView.TAG, e2);
                    }
                }
                Iterator it2 = WheelView.this.itemList.iterator();
                while (it2.hasNext()) {
                    ((ItemObject) it2.next()).newY(i4 * i5);
                }
                Message message2 = new Message();
                message2.what = 1;
                WheelView.this.handler.sendMessage(message2);
                try {
                    Thread.sleep(2L);
                } catch (InterruptedException e3) {
                    OKLog.e(WheelView.TAG, e3);
                }
                Iterator it3 = WheelView.this.itemList.iterator();
                while (it3.hasNext()) {
                    ItemObject itemObject = (ItemObject) it3.next();
                    if (itemObject.isSelected()) {
                        if (WheelView.this.onSelectListener != null) {
                            WheelView.this.onSelectListener.endSelect(itemObject.id, itemObject.itemText);
                            return;
                        }
                        return;
                    }
                }
            }
        }).start();
    }

    public String getItemText(int i2) {
        ArrayList<ItemObject> arrayList = this.itemList;
        return arrayList == null ? "" : arrayList.get(i2).itemText;
    }

    public int getListSize() {
        ArrayList<ItemObject> arrayList = this.itemList;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public int getSelected() {
        Iterator<ItemObject> it = this.itemList.iterator();
        while (it.hasNext()) {
            ItemObject next = it.next();
            if (next.isSelectedAllowEmpty()) {
                return next.id;
            }
        }
        return -1;
    }

    public String getSelectedText() {
        Iterator<ItemObject> it = this.itemList.iterator();
        while (it.hasNext()) {
            ItemObject next = it.next();
            if (next.isSelectedAllowEmpty()) {
                return next.itemText;
            }
        }
        return "";
    }

    public boolean isEnable() {
        return this.isEnable;
    }

    public boolean isScrolling() {
        return this.isScrolling;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawLine(canvas);
        drawList(canvas);
        drawMask(canvas);
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        float measuredWidth = getMeasuredWidth();
        this.controlWidth = measuredWidth;
        if (measuredWidth != 0.0f) {
            setMeasuredDimension(getMeasuredWidth(), this.itemNumber * this.unitHeight);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.isEnable) {
            int y = (int) motionEvent.getY();
            int action = motionEvent.getAction();
            if (action == 0) {
                this.isScrolling = true;
                this.downY = (int) motionEvent.getY();
                this.downTime = System.currentTimeMillis();
            } else if (action == 1) {
                int abs = Math.abs(y - this.downY);
                if (System.currentTimeMillis() - this.downTime < this.goonTime && abs > this.goonDistance) {
                    goonMove(y - this.downY);
                } else {
                    actionUp(y - this.downY);
                    noEmpty();
                    this.isScrolling = false;
                }
            } else if (action == 2) {
                actionMove(y - this.downY);
                onSelectListener();
            }
            return true;
        }
        return true;
    }

    public void refreshData(List<String> list) {
        setData(list);
        invalidate();
    }

    public void setData(List<String> list) {
        this.dataList = list;
        initData();
    }

    public void setDefault(int i2) {
        if (i2 > this.itemList.size() - 1) {
            i2 = this.itemList.size() - 1;
        }
        defaultMove((int) this.itemList.get(i2).moveToSelected());
    }

    public void setEnable(boolean z) {
        this.isEnable = z;
    }

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        this.onSelectListener = onSelectListener;
    }

    public void setSelectedColor(int i2) {
        this.selectedColor = i2;
    }

    public WheelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isScrolling = false;
        this.itemList = new ArrayList<>();
        this.dataList = new ArrayList();
        this.downTime = 0L;
        this.goonTime = 200L;
        this.goonDistance = 100;
        this.lineColor = -16777216;
        this.lineHeight = 2.0f;
        this.normalFont = 14.0f;
        this.selectedFont = 22.0f;
        this.unitHeight = 50;
        this.itemNumber = 7;
        this.normalColor = -16777216;
        this.selectedColor = SupportMenu.CATEGORY_MASK;
        this.maskHeight = 48.0f;
        this.isEnable = true;
        this.noEmpty = true;
        this.isClearing = false;
        this.handler = new Handler() { // from class: com.jingdong.common.jdreactFramework.utils.WheelView.3
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                if (message.what != 1) {
                    return;
                }
                WheelView.this.invalidate();
            }
        };
        init(context, attributeSet);
        initData();
    }

    public WheelView(Context context) {
        super(context);
        this.isScrolling = false;
        this.itemList = new ArrayList<>();
        this.dataList = new ArrayList();
        this.downTime = 0L;
        this.goonTime = 200L;
        this.goonDistance = 100;
        this.lineColor = -16777216;
        this.lineHeight = 2.0f;
        this.normalFont = 14.0f;
        this.selectedFont = 22.0f;
        this.unitHeight = 50;
        this.itemNumber = 7;
        this.normalColor = -16777216;
        this.selectedColor = SupportMenu.CATEGORY_MASK;
        this.maskHeight = 48.0f;
        this.isEnable = true;
        this.noEmpty = true;
        this.isClearing = false;
        this.handler = new Handler() { // from class: com.jingdong.common.jdreactFramework.utils.WheelView.3
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                if (message.what != 1) {
                    return;
                }
                WheelView.this.invalidate();
            }
        };
        initData();
    }
}
