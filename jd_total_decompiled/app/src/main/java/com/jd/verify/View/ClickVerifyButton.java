package com.jd.verify.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jd.verify.CallBack;
import com.jd.verify.View.gif.GifView;
import com.jd.verify.model.IninVerifyInfo;
import com.meizu.cloud.pushsdk.notification.model.AdvanceSetting;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import verify.jd.com.myverify.R;

/* loaded from: classes18.dex */
public class ClickVerifyButton extends LinearLayout implements IView {
    private final int DEFAULT_COLOR;
    private View.OnClickListener checkListener;
    private e dialog;
    private View frame;
    private GifView gifView;
    private boolean isVerifying;
    private long lastTouchTime;
    private LinearLayout llCheck;
    private CallBack mCallback;
    private Context mContext;
    private IninVerifyInfo mInfo;
    private com.jd.verify.model.b mPoint;
    private com.jd.verify.common.a notifyListener;
    private List<com.jd.verify.model.b> touchHistory;
    private List<JSONObject> touchHistoryArray;
    private TextView tvCheck;
    private TextView tvLoad;
    private com.jd.verify.common.b webView;

    /* loaded from: classes18.dex */
    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (view != ClickVerifyButton.this.llCheck) {
                if (view != ClickVerifyButton.this.tvLoad || ClickVerifyButton.this.webView == null) {
                    return;
                }
                ClickVerifyButton.this.webView.loadUrl(com.jd.verify.j.c.a().c());
                return;
            }
            ClickVerifyButton.this.check();
        }
    }

    public ClickVerifyButton(Context context) {
        super(context);
        this.DEFAULT_COLOR = Color.parseColor("#6495ED");
        this.isVerifying = false;
        this.lastTouchTime = 0L;
        this.checkListener = new a();
        init(context, null);
    }

    private void addHistoryTouch(com.jd.verify.model.b bVar) {
        if (this.touchHistory.size() >= 200) {
            this.touchHistory.remove(0);
        }
        this.touchHistory.add(bVar);
    }

    private JSONArray getHistoryArray() {
        JSONArray jSONArray = new JSONArray();
        Iterator<JSONObject> it = this.touchHistoryArray.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next());
        }
        return jSONArray;
    }

    private JSONObject getTouchJsonString(List<com.jd.verify.model.b> list) {
        int size = list.size();
        JSONArray jSONArray = new JSONArray();
        for (int i2 = 0; i2 < size; i2++) {
            jSONArray.put(list.get(i2).c());
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("eid", "touch");
            jSONObject.put("did", "android_img");
            jSONObject.put(AdvanceSetting.CLEAR_NOTIFICATION, "android_click_track");
            jSONObject.put("time", System.currentTimeMillis());
            jSONObject.put("pt", jSONArray);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    private void init(Context context, AttributeSet attributeSet) {
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.click_verify_button, (ViewGroup) this, true);
        this.frame = findViewById(R.id.frame);
        this.llCheck = (LinearLayout) findViewById(R.id.ll_load);
        this.gifView = (GifView) findViewById(R.id.gif);
        this.tvCheck = (TextView) findViewById(R.id.tv_click);
        this.tvLoad = (TextView) findViewById(R.id.tv_load);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.VerifyClickButton);
        obtainStyledAttributes.getIndexCount();
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.VerifyClickButton_verifyAnimSize, com.jd.verify.j.b.a(context, 22.0f));
        int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.VerifyClickButton_verifyTextSize, com.jd.verify.j.b.a(context, 14.0f));
        int color = obtainStyledAttributes.getColor(R.styleable.VerifyClickButton_verifyTextColor, this.DEFAULT_COLOR);
        this.gifView.a(dimensionPixelSize, dimensionPixelSize);
        this.tvCheck.setTextColor(color);
        float f2 = dimensionPixelSize2;
        this.tvCheck.setTextSize(com.jd.verify.j.b.b(context, f2));
        this.tvLoad.setTextColor(color);
        this.tvLoad.setTextSize(com.jd.verify.j.b.b(context, f2));
        obtainStyledAttributes.recycle();
        this.gifView.setGifImageType(GifView.d.COVER);
        this.gifView.a(dimensionPixelSize, dimensionPixelSize);
        this.gifView.setLayoutParams(new LinearLayout.LayoutParams(dimensionPixelSize, dimensionPixelSize));
        this.llCheck.setOnClickListener(this.checkListener);
        this.tvLoad.setOnClickListener(this.checkListener);
        this.touchHistoryArray = new ArrayList();
        this.touchHistory = new ArrayList();
    }

    public void changeBgCircle() {
        View view = this.frame;
        if (view != null) {
            view.setBackgroundResource(R.drawable.click_verify_bg_circle);
            this.frame.setPadding(com.jd.verify.j.b.a(this.mContext, 15.0f), 0, com.jd.verify.j.b.a(this.mContext, 15.0f), 0);
        }
    }

    public void check() {
        this.gifView.setGifImage(R.raw.verify_load);
        if (this.webView != null) {
            this.isVerifying = true;
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("touchList", getHistoryArray());
                jSONObject.put("params", "android");
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            this.touchHistoryArray.clear();
            this.webView.loadUrl("javascript:appCheck('" + jSONObject.toString() + "')");
        }
    }

    @Override // com.jd.verify.View.IView
    public void checkFinish(int i2, String str) {
        if (1 == i2) {
            this.isVerifying = false;
            this.llCheck.setEnabled(false);
            this.gifView.setGifImageOnce(R.raw.verify_finish);
            this.tvCheck.setText("\u9a8c\u8bc1\u5b8c\u6210");
        } else if (2 == i2) {
            startAnimation();
            this.isVerifying = false;
            CallBack callBack = this.mCallback;
            if (callBack != null) {
                callBack.onFail("");
            }
        } else if (3 == i2) {
            startAnimation();
        } else if (4 != i2) {
            if (6 == i2) {
                startAnimation();
            }
        } else {
            this.llCheck.setVisibility(8);
            this.tvLoad.setVisibility(0);
            this.tvLoad.setEnabled(true);
            this.tvLoad.setText(str);
        }
    }

    public void enableClick(boolean z) {
        this.llCheck.setEnabled(z);
        this.tvLoad.setEnabled(z);
    }

    public void initButton() {
        this.tvLoad.setText("\u52a0\u8f7d\u4e2d");
        this.llCheck.setVisibility(8);
        this.tvLoad.setVisibility(0);
        this.tvLoad.setEnabled(false);
        this.llCheck.setEnabled(false);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        long currentTimeMillis = System.currentTimeMillis();
        if (0 == this.lastTouchTime) {
            this.mPoint = new com.jd.verify.model.b((int) motionEvent.getRawX(), (int) motionEvent.getRawY(), (int) motionEvent.getX(), (int) motionEvent.getY(), 0);
        } else {
            this.mPoint = new com.jd.verify.model.b((int) motionEvent.getRawX(), (int) motionEvent.getRawY(), (int) motionEvent.getX(), (int) motionEvent.getY(), (int) (currentTimeMillis - this.lastTouchTime));
        }
        this.lastTouchTime = currentTimeMillis;
        addHistoryTouch(this.mPoint);
        if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
            addHistoryTouch(this.touchHistory);
            this.lastTouchTime = 0L;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void resetAndLoad() {
        com.jd.verify.common.b bVar = this.webView;
        if (bVar != null) {
            bVar.loadUrl(com.jd.verify.j.c.a().c());
        }
        startAnimation();
    }

    public void resetOut() {
        startAnimation();
    }

    @Override // com.jd.verify.View.IView
    public void setCurrentType(int i2) {
    }

    @Override // com.jd.verify.View.IView
    public void setDialg(e eVar) {
        this.dialog = eVar;
        this.webView = eVar.g();
    }

    @Override // com.jd.verify.View.IView
    public void setFinishListener(CallBack callBack) {
        this.mCallback = callBack;
    }

    @Override // com.jd.verify.View.IView
    public void setInfo(IninVerifyInfo ininVerifyInfo) {
        this.mInfo = ininVerifyInfo;
    }

    @Override // com.jd.verify.View.IView
    public void setNotifyListener(com.jd.verify.common.a aVar) {
        this.notifyListener = aVar;
    }

    public void setVerifyClick(View.OnClickListener onClickListener) {
        this.llCheck.setOnClickListener(onClickListener);
    }

    public void startAnimation() {
        this.llCheck.setVisibility(0);
        this.tvLoad.setVisibility(8);
        this.gifView.setGifImage(R.raw.verify_start);
        this.tvCheck.setText("\u70b9\u51fb\u6309\u94ae\u8fdb\u884c\u9a8c\u8bc1");
        this.llCheck.setEnabled(true);
        this.tvLoad.setEnabled(false);
    }

    public void stopAnimation() {
        this.gifView.a();
    }

    private void addHistoryTouch(List<com.jd.verify.model.b> list) {
        if (this.touchHistoryArray.size() >= 10) {
            this.touchHistoryArray.remove(0);
        }
        this.touchHistoryArray.add(getTouchJsonString(list));
        this.touchHistory.clear();
    }

    public ClickVerifyButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.DEFAULT_COLOR = Color.parseColor("#6495ED");
        this.isVerifying = false;
        this.lastTouchTime = 0L;
        this.checkListener = new a();
        init(context, attributeSet);
    }

    public ClickVerifyButton(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.DEFAULT_COLOR = Color.parseColor("#6495ED");
        this.isVerifying = false;
        this.lastTouchTime = 0L;
        this.checkListener = new a();
        init(context, attributeSet);
    }
}
