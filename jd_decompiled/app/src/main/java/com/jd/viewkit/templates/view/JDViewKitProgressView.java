package com.jd.viewkit.templates.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.core.internal.view.SupportMenu;
import com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel;
import com.jd.viewkit.global.GlobalManage;
import com.jd.viewkit.helper.JDViewKitChannelModel;
import com.jd.viewkit.templates.JDViewKitBaseLayout;
import com.jd.viewkit.templates.model.JDViewKitVirtualProgressView;
import com.jd.viewkit.templates.model.event.JDViewKitVirtualEvent;
import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import com.jd.viewkit.tool.ExpressionParserTool;
import com.jd.viewkit.tool.NumberTool;
import com.jd.viewkit.tool.StringTool;
import java.util.Map;

/* loaded from: classes18.dex */
public class JDViewKitProgressView extends JDViewKitBaseLayout<JDViewKitVirtualProgressView> implements View.OnClickListener {
    private static final int VIEW_CHANGE_INTERVAL = 50;
    public static String useTimeOutKey = "useTimeOut_";
    private float dy;
    private Handler handler;
    private boolean isPause;
    private JDViewKitDataSourceModel mDataSourceModel;
    private boolean needPlay;
    private float pressInt;
    private long token;
    private JDViewKitVirtualProgressView virtualProgressView;

    /* loaded from: classes18.dex */
    public class ProgressMessage {
        public float press;
        public long token;

        public ProgressMessage(long j2, float f2) {
            JDViewKitProgressView.this = r1;
            this.token = j2;
            this.press = f2;
        }
    }

    public JDViewKitProgressView(Context context) {
        super(context);
        this.token = -1L;
        this.handler = new Handler(Looper.getMainLooper()) { // from class: com.jd.viewkit.templates.view.JDViewKitProgressView.1
            {
                JDViewKitProgressView.this = this;
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (!JDViewKitProgressView.this.isPause && JDViewKitProgressView.this.needPlay) {
                    Object obj = message.obj;
                    if (obj instanceof ProgressMessage) {
                        ProgressMessage progressMessage = (ProgressMessage) obj;
                        if (JDViewKitProgressView.this.token - progressMessage.token != 0) {
                            return;
                        }
                        JDViewKitProgressView.this.pressInt = progressMessage.press;
                        JDViewKitProgressView.this.invalidate();
                        JDViewKitProgressView.this.autoPlay(50);
                    }
                }
            }
        };
        setWillNotDraw(false);
    }

    public synchronized void autoPlay(int i2) {
        if (this.needPlay) {
            float f2 = this.pressInt;
            float f3 = this.dy + f2;
            if (f2 >= 0.0f && f2 <= 100.0f) {
                this.token = System.currentTimeMillis();
                Message obtain = Message.obtain();
                obtain.obj = new ProgressMessage(this.token, f3);
                this.handler.sendMessageDelayed(obtain, i2);
                return;
            }
            timeOut();
        }
    }

    private void beginCountDown() {
        JDViewKitVirtualProgressView jDViewKitVirtualProgressView;
        JDViewKitDataSourceModel jDViewKitDataSourceModel = this.mDataSourceModel;
        if (jDViewKitDataSourceModel == null || (jDViewKitVirtualProgressView = this.virtualProgressView) == null) {
            return;
        }
        this.needPlay = true;
        String stringValueRef = ExpressionParserTool.getStringValueRef(jDViewKitVirtualProgressView.getValueRefer(), jDViewKitDataSourceModel.getDataMap());
        long valueOfFloat = NumberTool.valueOfFloat(stringValueRef) * 1000.0f;
        if (valueOfFloat > 0 && !StringTool.isEmpty(stringValueRef)) {
            setVisibility(0);
            long currentTimeMillis = System.currentTimeMillis() - jDViewKitDataSourceModel.getLongTimeStame().longValue();
            if (this.virtualProgressView.isLeftStart()) {
                float f2 = 5200.0f / ((float) valueOfFloat);
                this.dy = f2;
                float f3 = (((float) currentTimeMillis) * f2) / 50.0f;
                this.pressInt = f3;
                this.pressInt = f3 <= 100.0f ? f3 : 100.0f;
            } else {
                float f4 = 0.0f - (5200.0f / ((float) valueOfFloat));
                this.dy = f4;
                float f5 = ((((float) currentTimeMillis) * f4) / 50.0f) + 100.0f;
                this.pressInt = f5;
                this.pressInt = f5 >= 0.0f ? f5 : 0.0f;
            }
            autoPlay(50);
            return;
        }
        setVisibility(8);
    }

    public static JDViewKitProgressView getProgressView(JDViewKitBaseLayout jDViewKitBaseLayout) {
        if (jDViewKitBaseLayout == null) {
            return null;
        }
        if (jDViewKitBaseLayout instanceof JDViewKitProgressView) {
            return (JDViewKitProgressView) jDViewKitBaseLayout;
        }
        if (jDViewKitBaseLayout.getChildCount() > 0) {
            for (int i2 = 0; i2 < jDViewKitBaseLayout.getChildCount(); i2++) {
                View childAt = jDViewKitBaseLayout.getChildAt(i2);
                if (childAt != null) {
                    if (childAt instanceof JDViewKitProgressView) {
                        return (JDViewKitProgressView) childAt;
                    }
                    if (childAt instanceof JDViewKitAbsoluteLayout) {
                        return getProgressView((JDViewKitBaseLayout) childAt);
                    }
                }
            }
        }
        return null;
    }

    private Bitmap getRoundedCornerBitmap(Bitmap bitmap, float f2) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(SupportMenu.CATEGORY_MASK);
        canvas.drawRoundRect(rectF, f2, f2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return createBitmap;
    }

    private void viewAppear() {
        this.isPause = false;
        if (this.needPlay) {
            beginCountDown();
        }
    }

    private void viewDisAppear() {
        this.token = System.currentTimeMillis();
        this.isPause = true;
    }

    public void clearUseTimeOut(String str) {
        JDViewKitDataSourceModel jDViewKitDataSourceModel = this.mDataSourceModel;
        if (jDViewKitDataSourceModel == null || jDViewKitDataSourceModel.getDataMap() == null) {
            return;
        }
        this.mDataSourceModel.getDataMap().put(str, 0);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchVisibilityChanged(View view, int i2) {
        super.dispatchVisibilityChanged(view, i2);
        if (i2 == 0) {
            viewAppear();
        } else {
            viewDisAppear();
        }
    }

    @Override // com.jd.viewkit.templates.JDViewKitBaseLayout, android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.virtualProgressView == null || this.mDataSourceModel == null) {
            return;
        }
        int realPx = GlobalManage.getInstance().getRealPx(this.virtualProgressView.getWidth(), getChannelModel());
        int realPx2 = GlobalManage.getInstance().getRealPx(this.virtualProgressView.getHeigh(), getChannelModel());
        int realPx3 = GlobalManage.getInstance().getRealPx(this.virtualProgressView.getBorderWidth(), getChannelModel());
        int realPx4 = GlobalManage.getInstance().getRealPx(this.virtualProgressView.getBorderRadius(), getChannelModel());
        float f2 = realPx;
        int i2 = (int) ((this.pressInt / 100.0f) * f2);
        if (i2 < 0) {
            i2 = 0;
        }
        if (i2 > realPx) {
            i2 = realPx;
        }
        int i3 = realPx3 * 2;
        Bitmap createBitmap = Bitmap.createBitmap(realPx - i3, realPx2 - i3, Bitmap.Config.ARGB_8888);
        Canvas canvas2 = new Canvas(createBitmap);
        int progressColorInt = this.virtualProgressView.getProgressColorInt();
        Paint paint = new Paint();
        RectF rectF = new RectF(new Rect(0, 0, i2, createBitmap.getHeight()));
        paint.setAntiAlias(true);
        paint.setColor(progressColorInt);
        canvas2.drawRect(rectF, paint);
        canvas2.save();
        paint.setColor(this.virtualProgressView.getBackgroundColorInt());
        canvas2.drawRect(new RectF(i2, 0.0f, f2, createBitmap.getHeight()), paint);
        float f3 = realPx3;
        canvas.drawBitmap(getRoundedCornerBitmap(createBitmap, realPx4 - realPx3), f3, f3, (Paint) null);
    }

    public int getUseTimeOut(String str) {
        Object obj;
        JDViewKitDataSourceModel jDViewKitDataSourceModel = this.mDataSourceModel;
        if (jDViewKitDataSourceModel == null || jDViewKitDataSourceModel.getDataMap() == null || (obj = this.mDataSourceModel.getDataMap().get(str)) == null || !(obj instanceof Integer)) {
            return 0;
        }
        return ((Integer) obj).intValue();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        viewAppear();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        JDViewKitEventHelper.click(this.virtualProgressView, this.mDataSourceModel, this, getChannelModel());
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        viewDisAppear();
    }

    @Override // com.jd.viewkit.templates.JDViewKitBaseLayout
    public void setBgColorAndBorder(View view) {
    }

    public void setCountdownLifeCycle(int i2) {
        if (i2 == 2) {
            viewDisAppear();
        } else if (i2 == 1) {
            viewAppear();
        }
    }

    @Override // com.jd.viewkit.templates.JDViewKitBaseLayout
    public void setDataSourceMap(Map map, boolean z) {
        super.setDataSourceMap(map, z);
    }

    public void setDataSourceModel(JDViewKitDataSourceModel jDViewKitDataSourceModel, boolean z) {
        this.mDataSourceModel = jDViewKitDataSourceModel;
        if (this.virtualProgressView != null && jDViewKitDataSourceModel != null && jDViewKitDataSourceModel.getDataMap() != null) {
            boolean filterValue = JDViewKitBaseLayout.getFilterValue(this.virtualProgressView.isFilter(), jDViewKitDataSourceModel.getDataMap());
            this.isFilter = filterValue;
            if (filterValue) {
                return;
            }
        }
        this.needPlay = false;
        JDViewKitVirtualProgressView jDViewKitVirtualProgressView = this.virtualProgressView;
        if (jDViewKitVirtualProgressView != null) {
            if (jDViewKitVirtualProgressView.isCountDownProgress()) {
                if (jDViewKitDataSourceModel.getLongDataState() == 0) {
                    jDViewKitDataSourceModel.setLongTimeStame(Long.valueOf(System.currentTimeMillis()));
                    jDViewKitDataSourceModel.setLongDataState(1);
                }
                beginCountDown();
            } else {
                float intValueRef = ExpressionParserTool.getIntValueRef(this.virtualProgressView.getValueRefer(), this.mDataSourceModel.getDataMap());
                this.pressInt = intValueRef;
                if (intValueRef < 0.0f) {
                    intValueRef = 0.0f;
                }
                this.pressInt = intValueRef;
                if (intValueRef > 100.0f) {
                    intValueRef = 100.0f;
                }
                this.pressInt = intValueRef;
            }
        }
        requestLayout();
    }

    public void setUseTimeOut(String str) {
        JDViewKitDataSourceModel jDViewKitDataSourceModel = this.mDataSourceModel;
        if (jDViewKitDataSourceModel == null || jDViewKitDataSourceModel.getDataMap() == null) {
            return;
        }
        this.mDataSourceModel.getDataMap().put(str, 1);
    }

    public synchronized void timeOut() {
        if (this.virtualProgressView.getVirtualEventByType(JDViewKitVirtualEvent.typeTimeOutEvent) == null) {
            return;
        }
        String str = useTimeOutKey + this.virtualProgressView.getDslId();
        if (getUseTimeOut(str) > 0) {
            return;
        }
        setUseTimeOut(str);
        JDViewKitEventHelper.timeOut(this.virtualProgressView, this.mDataSourceModel, this, getChannelModel());
    }

    @Override // com.jd.viewkit.templates.JDViewKitBaseLayout
    public void setVirtualView(JDViewKitVirtualProgressView jDViewKitVirtualProgressView) {
        super.setVirtualView((JDViewKitProgressView) jDViewKitVirtualProgressView);
        this.virtualProgressView = jDViewKitVirtualProgressView;
        if (jDViewKitVirtualProgressView == null || jDViewKitVirtualProgressView.getVirtualEventByType(JDViewKitVirtualEvent.typeClick) == null) {
            return;
        }
        setOnClickListener(this);
    }

    public JDViewKitProgressView(Context context, JDViewKitChannelModel jDViewKitChannelModel) {
        this(context);
        this.channelModel = jDViewKitChannelModel;
    }

    public JDViewKitProgressView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.token = -1L;
        this.handler = new Handler(Looper.getMainLooper()) { // from class: com.jd.viewkit.templates.view.JDViewKitProgressView.1
            {
                JDViewKitProgressView.this = this;
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (!JDViewKitProgressView.this.isPause && JDViewKitProgressView.this.needPlay) {
                    Object obj = message.obj;
                    if (obj instanceof ProgressMessage) {
                        ProgressMessage progressMessage = (ProgressMessage) obj;
                        if (JDViewKitProgressView.this.token - progressMessage.token != 0) {
                            return;
                        }
                        JDViewKitProgressView.this.pressInt = progressMessage.press;
                        JDViewKitProgressView.this.invalidate();
                        JDViewKitProgressView.this.autoPlay(50);
                    }
                }
            }
        };
    }
}
