package com.jd.verify.View;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.verify.CallBack;
import com.jd.verify.model.IninVerifyInfo;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import verify.jd.com.myverify.R;

/* loaded from: classes18.dex */
public class SlideVerifyButton extends FrameLayout implements IView {
    public static final int STATE_FAIL = 3;
    public static final int STATE_SUCCESS = 2;
    public static final int STATE_VERIFYING = 1;
    public static final int STATE_WAIT_VERIFY = 0;
    private final String TAG;
    private ImageView button;
    private int currentState;
    private e dialog;
    private boolean enableMove;
    private int errorTime;
    private boolean isInit;
    private boolean isReady;
    private JSONObject jo;

    /* renamed from: k  reason: collision with root package name */
    private boolean f7115k;
    private long lastTouchTime;
    private CallBack mCallback;
    private Context mContext;
    private Paint mPaint;
    private com.jd.verify.model.b mPoint;
    private Paint mSlidePaint;
    private SlideStateListener mSlideStateListener;
    private Paint mSlideStrokePaint;
    private com.jd.verify.common.a notifyListener;
    private int textFinishColor;
    private int textInitColor;
    private TextView textView;
    private Bitmap thumb;
    private int thumbW;
    private List<com.jd.verify.model.b> touch;
    private List<com.jd.verify.model.b> touchHistory;
    private List<JSONObject> touchHistoryArray;
    private int touch_x;
    private int viewH;
    private int viewW;
    private com.jd.verify.common.b webView;
    private int x;

    /* loaded from: classes18.dex */
    public interface SlideStateListener {
        void slideDown();

        boolean slideFinished();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes18.dex */
    public class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (SlideVerifyButton.this.x < 20) {
                SlideVerifyButton.this.x = 0;
            } else {
                SlideVerifyButton.this.x -= 20;
            }
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) SlideVerifyButton.this.button.getLayoutParams();
            layoutParams.setMargins(SlideVerifyButton.this.x > 2 ? SlideVerifyButton.this.x : 2, 2, 0, 2);
            SlideVerifyButton.this.button.setLayoutParams(layoutParams);
            SlideVerifyButton.this.button.setBackgroundResource(SlideVerifyButton.this.getThumbColor());
            SlideVerifyButton.this.button.setImageResource(SlideVerifyButton.this.getThumbSrc());
            SlideVerifyButton.this.invalidate();
        }
    }

    /* loaded from: classes18.dex */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SlideVerifyButton.this.reset();
        }
    }

    /* loaded from: classes18.dex */
    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SlideVerifyButton.this.reset();
        }
    }

    public SlideVerifyButton(Context context) {
        super(context);
        this.TAG = "SlideVerifyButton";
        this.errorTime = 1;
        this.textInitColor = Color.parseColor(JDDarkUtil.COLOR_999999);
        this.textFinishColor = Color.parseColor("#1aa863");
        this.currentState = 0;
        this.enableMove = true;
        this.isReady = false;
        this.isInit = true;
        this.x = 0;
        this.f7115k = true;
        this.touch_x = 0;
        this.lastTouchTime = 0L;
        init(context);
    }

    private void addHistoryTouch(com.jd.verify.model.b bVar) {
        if (this.touchHistory.size() >= 200) {
            this.touchHistory.remove(0);
        }
        this.touchHistory.add(bVar);
    }

    private void drawSlideArea(Canvas canvas) {
        int i2 = this.x;
        RectF rectF = new RectF(2.0f, 2.0f, i2 + r2, (float) (this.viewH - 2));
        this.mSlidePaint.setColor(getRectColor());
        float f2 = this.viewH / 2;
        canvas.drawRoundRect(rectF, f2, f2, this.mSlidePaint);
        int i3 = this.x;
        RectF rectF2 = new RectF(2.0f, 2.0f, i3 + r2, this.viewH);
        this.mSlideStrokePaint.setColor(getBoundColor());
        float f3 = this.viewH / 2;
        canvas.drawRoundRect(rectF2, f3, f3, this.mSlideStrokePaint);
    }

    private int getBoundColor() {
        int i2 = this.currentState;
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        return -1;
                    }
                    return getColor(R.color.verify_red_ffb0b0);
                }
                return getColor(R.color.verify_green_94d4b5);
            }
            return getColor(R.color.verify_blue_91c7ff);
        }
        return -1;
    }

    private int getColor(int i2) {
        Context context = this.mContext;
        if (context == null) {
            return -1;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            return context.getColor(i2);
        }
        return context.getResources().getColor(i2);
    }

    private JSONObject getHisTouchJsonString(List<com.jd.verify.model.b> list) {
        int size = list.size();
        if (size == 0) {
            return new JSONObject();
        }
        com.jd.verify.model.b bVar = list.get(0);
        JSONArray jSONArray = new JSONArray();
        for (int i2 = 0; i2 < size; i2++) {
            jSONArray.put(list.get(i2).c());
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(JshopConst.JSHOP_PROMOTIO_X, bVar.a());
            jSONObject.put(JshopConst.JSHOP_PROMOTIO_Y, bVar.b());
            jSONObject.put("ht", getHeight());
            jSONObject.put("wt", getWidth());
            jSONObject.put(ThemeTitleConstant.TITLE_LIST_DRAWABLE_ID, jSONArray);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    private JSONArray getHistoryArray() {
        JSONArray jSONArray = new JSONArray();
        Iterator<JSONObject> it = this.touchHistoryArray.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next());
        }
        return jSONArray;
    }

    private int getRectColor() {
        int color;
        int i2 = this.currentState;
        if (i2 == 1) {
            color = getColor(R.color.verify_blue_deeeff);
        } else if (i2 != 2) {
            color = i2 != 3 ? -1 : getColor(R.color.verify_red_ffebeb);
        } else {
            color = getColor(R.color.verify_green_e8fcf2);
        }
        com.jd.verify.j.d.a("SlideVerifyButton", "currentState:" + this.currentState);
        return color;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getThumbColor() {
        int i2 = R.drawable.verify_oval_white;
        int i3 = this.currentState;
        if (i3 == 1) {
            i2 = R.drawable.verify_oval_blue;
        } else if (i3 == 2) {
            i2 = R.drawable.verify_oval_green;
        } else if (i3 == 3) {
            i2 = R.drawable.verify_oval_red;
        }
        com.jd.verify.j.d.a("SlideVerifyButton", "currentState:" + this.currentState);
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getThumbSrc() {
        int i2 = R.drawable.verify_slide_right_black;
        int i3 = this.currentState;
        if (i3 == 1) {
            i2 = R.drawable.verify_slide_right_white;
        } else if (i3 == 2) {
            i2 = R.drawable.verify_slide_sucess;
        } else if (i3 == 3) {
            i2 = R.drawable.verify_slide_error;
        }
        com.jd.verify.j.d.a("SlideVerifyButton", "currentState:" + this.currentState);
        return i2;
    }

    private JSONObject getTouchJsonString(List<com.jd.verify.model.b> list) {
        int size = list.size();
        if (size == 0) {
            return new JSONObject();
        }
        com.jd.verify.model.b bVar = list.get(0);
        JSONArray jSONArray = new JSONArray();
        for (int i2 = 0; i2 < size; i2++) {
            jSONArray.put(list.get(i2).d());
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(JshopConst.JSHOP_PROMOTIO_X, bVar.a());
            jSONObject.put(JshopConst.JSHOP_PROMOTIO_Y, bVar.b());
            jSONObject.put("ht", getHeight());
            jSONObject.put("wt", getWidth());
            jSONObject.put(ThemeTitleConstant.TITLE_LIST_DRAWABLE_ID, jSONArray);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    private void init(Context context) {
        this.mContext = context;
        initTextView();
        setBackgroundResource(R.drawable.verify_slide_verify_button_bg);
        this.mPaint = new Paint();
        Paint paint = new Paint();
        this.mSlidePaint = paint;
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        Paint paint2 = new Paint();
        this.mSlideStrokePaint = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        this.mSlideStrokePaint.setStrokeWidth(1.0f);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        Resources resources = this.mContext.getResources();
        int i2 = R.drawable.verify_slide_right_black;
        this.thumb = BitmapFactory.decodeResource(resources, i2, options);
        this.touch = new ArrayList();
        this.touchHistoryArray = new ArrayList();
        this.touchHistory = new ArrayList();
        ImageView imageView = new ImageView(context);
        this.button = imageView;
        imageView.setImageResource(i2);
        this.button.setBackgroundResource(R.drawable.verify_oval_white);
        this.button.setScaleType(ImageView.ScaleType.CENTER);
        this.button.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        addView(this.button);
    }

    private void initTextView() {
        TextView textView = new TextView(this.mContext);
        this.textView = textView;
        textView.setText("\u62d6\u52a8\u6ed1\u5757\u5411\u53f3\u6ed1\u52a8");
        this.textView.setTextSize(1, 14.0f);
        this.textView.setTextColor(this.textInitColor);
        this.textView.setGravity(17);
        addView(this.textView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reset() {
        this.currentState = 0;
        com.jd.verify.j.d.a("SlideVerifyButton", "reset:" + this.currentState);
        this.textView.setText("\u62d6\u52a8\u6ed1\u5757\u5411\u53f3\u6ed1\u52a8");
        this.textView.setVisibility(0);
        int i2 = this.x;
        for (int i3 = 0; i3 < (i2 / 20) + 1; i3++) {
            postDelayed(new a(), i3 * 5);
        }
    }

    private void slideFinish() {
        this.textView.setText("\u9a8c\u8bc1\u4e2d");
        this.textView.setVisibility(0);
        com.jd.verify.common.b bVar = this.webView;
        if (bVar != null) {
            if (!bVar.a()) {
                if (!this.isReady) {
                    reset();
                    return;
                }
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("touchList", getHistoryArray());
                    jSONObject.put("touch", getTouchJsonString(this.touch));
                    jSONObject.put("params", "android");
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                this.webView.loadUrl("javascript:appCheck('" + jSONObject.toString() + "')");
                com.jd.verify.j.d.a(jSONObject.toString());
            } else {
                reset();
                Toast.makeText(this.mContext, "\u52a0\u8f7d\u5931\u8d25\uff0c\u8bf7\u9000\u51fa\u91cd\u8bd5", 0).show();
            }
            this.touchHistoryArray.clear();
            this.touch.clear();
            return;
        }
        reset();
        Toast.makeText(this.mContext, "\u52a0\u8f7d\u5931\u8d25\uff0c\u8bf7\u9000\u51fa\u91cd\u8bd5", 0).show();
    }

    private void sliding() {
    }

    @Override // com.jd.verify.View.IView
    public void checkFinish(int i2, String str) {
        if (1 == i2) {
            this.currentState = 2;
            this.textView.setText("\u6ed1\u52a8\u9a8c\u8bc1\u6210\u529f");
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.button.getLayoutParams();
            layoutParams.setMargins(this.x, 2, 0, 2);
            this.button.setLayoutParams(layoutParams);
            this.button.setBackgroundResource(getThumbColor());
            this.button.setImageResource(getThumbSrc());
            invalidate();
        } else if (2 == i2) {
            this.currentState = 3;
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) this.button.getLayoutParams();
            layoutParams2.setMargins(this.x, 2, 0, 2);
            this.button.setLayoutParams(layoutParams2);
            this.button.setBackgroundResource(getThumbColor());
            this.button.setImageResource(getThumbSrc());
            invalidate();
            postDelayed(new b(), 1000L);
            CallBack callBack = this.mCallback;
            if (callBack != null) {
                callBack.onFail(str);
            }
        } else if (3 == i2) {
            reset();
        } else if (4 == i2) {
            reset();
            com.jd.verify.common.b bVar = this.webView;
            if (bVar != null) {
                bVar.loadUrl(com.jd.verify.j.c.a().c());
            }
        } else if (5 != i2) {
            if (6 == i2) {
                this.isReady = true;
            }
        } else {
            this.currentState = 3;
            FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) this.button.getLayoutParams();
            layoutParams3.setMargins(this.x, 2, 0, 2);
            this.button.setLayoutParams(layoutParams3);
            this.button.setBackgroundResource(getThumbColor());
            this.button.setImageResource(getThumbSrc());
            invalidate();
            postDelayed(new c(), 1000L);
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.viewW = getWidth();
        this.viewH = getHeight();
        this.thumbW = getHeight();
        if (this.isInit) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.button.getLayoutParams();
            int i2 = this.viewH - 4;
            layoutParams.height = i2;
            layoutParams.width = i2;
            layoutParams.setMargins(2, 2, 0, 2);
            this.isInit = false;
            this.button.setLayoutParams(layoutParams);
            this.button.setBackgroundResource(getThumbColor());
            this.button.setImageResource(getThumbSrc());
        }
        drawSlideArea(canvas);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int i2 = this.currentState;
        if (i2 != 3 && i2 != 2) {
            long currentTimeMillis = System.currentTimeMillis();
            if (0 == this.lastTouchTime) {
                this.mPoint = new com.jd.verify.model.b((int) motionEvent.getRawX(), (int) motionEvent.getRawY(), (int) motionEvent.getX(), (int) motionEvent.getY(), 0);
            } else {
                this.mPoint = new com.jd.verify.model.b((int) motionEvent.getRawX(), (int) motionEvent.getRawY(), (int) motionEvent.getX(), (int) motionEvent.getY(), (int) (currentTimeMillis - this.lastTouchTime));
            }
            this.lastTouchTime = currentTimeMillis;
            if (motionEvent.getAction() == 0) {
                this.touch.clear();
                addHistoryTouch(this.mPoint);
                int x = (int) motionEvent.getX();
                this.touch_x = x;
                this.f7115k = true;
                if (x > this.thumbW) {
                    this.f7115k = false;
                } else {
                    SlideStateListener slideStateListener = this.mSlideStateListener;
                    if (slideStateListener != null) {
                        slideStateListener.slideDown();
                    }
                }
            }
            if (!this.enableMove) {
                return true;
            }
            if (motionEvent.getAction() == 2) {
                addHistoryTouch(this.mPoint);
                if (!this.f7115k) {
                    return true;
                }
                this.currentState = 1;
                com.jd.verify.j.d.a("SlideVerifyButton", "verifying");
                this.touch.add(this.mPoint);
                int x2 = ((int) motionEvent.getX()) - this.touch_x;
                if (x2 < 0) {
                    this.x = 0;
                } else {
                    int i3 = this.thumbW;
                    int i4 = x2 + i3;
                    int i5 = this.viewW;
                    if (i4 > i5) {
                        this.x = i5 - i3;
                    } else {
                        this.x = x2;
                    }
                }
                sliding();
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.button.getLayoutParams();
                layoutParams.setMargins(this.x, 2, 0, 2);
                this.button.setLayoutParams(layoutParams);
                this.button.setBackgroundResource(getThumbColor());
                this.button.setImageResource(getThumbSrc());
                invalidate();
            }
            if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
                addHistoryTouch(this.mPoint);
                addHistoryTouch(this.touchHistory);
                if ((((int) motionEvent.getX()) - this.touch_x) + this.thumbW < this.viewW) {
                    reset();
                } else {
                    com.jd.verify.j.d.a("SlideVerifyButton", "verify success");
                    SlideStateListener slideStateListener2 = this.mSlideStateListener;
                    if (slideStateListener2 != null ? slideStateListener2.slideFinished() : false) {
                        slideFinish();
                    }
                }
                this.lastTouchTime = 0L;
            }
        }
        return true;
    }

    public void resetAndLoad() {
        com.jd.verify.common.b bVar = this.webView;
        if (bVar != null) {
            bVar.loadUrl(com.jd.verify.j.c.a().c());
        }
        reset();
    }

    public void resetOut() {
        reset();
    }

    @Override // com.jd.verify.View.IView
    public void setCurrentType(int i2) {
    }

    @Override // com.jd.verify.View.IView
    public void setDialg(e eVar) {
        this.dialog = eVar;
        this.webView = eVar.g();
    }

    public void setEnableMove(boolean z) {
        this.enableMove = z;
    }

    public void setErrorTime(int i2) {
        this.errorTime = i2;
    }

    @Override // com.jd.verify.View.IView
    public void setFinishListener(CallBack callBack) {
        this.mCallback = callBack;
    }

    public void setFinishedText(String str) {
        this.textView.setText(str);
        this.textView.setTextColor(this.textFinishColor);
    }

    @Override // com.jd.verify.View.IView
    public void setInfo(IninVerifyInfo ininVerifyInfo) {
    }

    @Override // com.jd.verify.View.IView
    public void setNotifyListener(com.jd.verify.common.a aVar) {
        this.notifyListener = aVar;
    }

    public void setText(String str) {
        this.textView.setText(str);
    }

    public void setmSlideStateListener(SlideStateListener slideStateListener) {
        this.mSlideStateListener = slideStateListener;
    }

    private void addHistoryTouch(List<com.jd.verify.model.b> list) {
        if (this.touchHistoryArray.size() >= 10) {
            this.touchHistoryArray.remove(0);
        }
        this.touchHistoryArray.add(getHisTouchJsonString(list));
        this.touchHistory.clear();
    }

    public SlideVerifyButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = "SlideVerifyButton";
        this.errorTime = 1;
        this.textInitColor = Color.parseColor(JDDarkUtil.COLOR_999999);
        this.textFinishColor = Color.parseColor("#1aa863");
        this.currentState = 0;
        this.enableMove = true;
        this.isReady = false;
        this.isInit = true;
        this.x = 0;
        this.f7115k = true;
        this.touch_x = 0;
        this.lastTouchTime = 0L;
        init(context);
    }

    public SlideVerifyButton(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.TAG = "SlideVerifyButton";
        this.errorTime = 1;
        this.textInitColor = Color.parseColor(JDDarkUtil.COLOR_999999);
        this.textFinishColor = Color.parseColor("#1aa863");
        this.currentState = 0;
        this.enableMove = true;
        this.isReady = false;
        this.isInit = true;
        this.x = 0;
        this.f7115k = true;
        this.touch_x = 0;
        this.lastTouchTime = 0L;
        init(context);
    }
}
