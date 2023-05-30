package com.jd.dynamic.lib.viewparse;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.Keep;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import com.jd.dynamic.R;
import com.jd.dynamic.b.a.b;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.entity.ViewNode;
import com.jd.dynamic.lib.utils.m;
import com.jd.dynamic.lib.utils.t;
import com.jd.dynamic.yoga.android.YogaLayout;

@Keep
/* loaded from: classes.dex */
public class BaseFrameLayout extends FrameLayout implements LifecycleObserver {
    private static final Handler mHandler = new Handler(Looper.getMainLooper());
    private AsyncTask bindTask;
    private DynamicTemplateEngine engine;
    private boolean isAfterObserver;
    private boolean isCommAttrBinded;
    private boolean isFirstBind;
    private boolean isFromCreateView;
    private boolean isObserverAdded;
    private Handler privateHandler;
    public long unId;

    public BaseFrameLayout(Context context, ViewNode viewNode, DynamicTemplateEngine dynamicTemplateEngine, boolean z) {
        super(context);
        this.privateHandler = new Handler(Looper.getMainLooper());
        this.isCommAttrBinded = false;
        this.isFromCreateView = false;
        this.isFirstBind = true;
        this.isAfterObserver = false;
        boolean A = b.o().A();
        this.isAfterObserver = A;
        if (!A) {
            addLifecycleObserver();
        }
        setId(R.id.dynamic_root_view);
        this.engine = dynamicTemplateEngine;
        this.isFromCreateView = z;
        parse(viewNode);
        if (this.isAfterObserver) {
            addLifecycleObserver();
        }
    }

    public static Handler getMyHandler() {
        return mHandler;
    }

    private void parse(ViewNode viewNode) {
        FrameLayout.LayoutParams layoutParams;
        View view = (View) f.a(viewNode.getViewName(), viewNode.getAttributes(), this.engine, false, false).a(viewNode, getContext());
        if (!(view instanceof YogaLayout)) {
            addView(view);
            return;
        }
        YogaLayout yogaLayout = (YogaLayout) view;
        yogaLayout.isRootLayout = true;
        float numericAttribute = yogaLayout.getYogaLayoutLayoutParams().getNumericAttribute(R.styleable.yoga_yg_marginLeft);
        float numericAttribute2 = yogaLayout.getYogaLayoutLayoutParams().getNumericAttribute(R.styleable.yoga_yg_marginRight);
        float numericAttribute3 = yogaLayout.getYogaLayoutLayoutParams().getNumericAttribute(R.styleable.yoga_yg_marginTop);
        float numericAttribute4 = yogaLayout.getYogaLayoutLayoutParams().getNumericAttribute(R.styleable.yoga_yg_marginBottom);
        if (this.isFromCreateView) {
            layoutParams = new FrameLayout.LayoutParams(((ViewGroup.MarginLayoutParams) yogaLayout.getYogaLayoutLayoutParams()).width > 0 ? ((ViewGroup.MarginLayoutParams) yogaLayout.getYogaLayoutLayoutParams()).width : -1, ((ViewGroup.MarginLayoutParams) yogaLayout.getYogaLayoutLayoutParams()).height > 0 ? ((ViewGroup.MarginLayoutParams) yogaLayout.getYogaLayoutLayoutParams()).height : -1);
        } else {
            layoutParams = new FrameLayout.LayoutParams(((ViewGroup.MarginLayoutParams) yogaLayout.getYogaLayoutLayoutParams()).width, ((ViewGroup.MarginLayoutParams) yogaLayout.getYogaLayoutLayoutParams()).height);
        }
        layoutParams.setMargins((int) numericAttribute, (int) numericAttribute3, (int) numericAttribute2, (int) numericAttribute4);
        addView(yogaLayout, layoutParams);
    }

    public void releaseRes() {
        DynamicTemplateEngine dynamicTemplateEngine = this.engine;
        if (dynamicTemplateEngine != null) {
            dynamicTemplateEngine.release();
        }
        this.privateHandler.removeCallbacksAndMessages(null);
        if (getContext() instanceof LifecycleOwner) {
            ((LifecycleOwner) getContext()).getLifecycle().removeObserver(this);
        }
    }

    public void addLifecycleObserver() {
        if (m.D() && !this.isObserverAdded) {
            if (getContext() instanceof LifecycleOwner) {
                ((LifecycleOwner) getContext()).getLifecycle().addObserver(this);
            }
            this.isObserverAdded = true;
        }
    }

    public AsyncTask getBindTask() {
        return this.bindTask;
    }

    public boolean isCommAttrBinded() {
        return this.isCommAttrBinded;
    }

    public boolean isFirstBind() {
        return this.isFirstBind;
    }

    public boolean isObserverAdded() {
        return this.isObserverAdded;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        t.e("BaseFrameLayout", "onDestroy");
        DynamicSdk.getEngine().removeDarkChangeListener(this.engine);
        Handler handler = mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        if (this.isAfterObserver) {
            releaseRes();
        } else {
            this.privateHandler.postDelayed(new Runnable() { // from class: com.jd.dynamic.lib.viewparse.a
                {
                    BaseFrameLayout.this = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    BaseFrameLayout.this.releaseRes();
                }
            }, 500L);
        }
    }

    public void setBindTask(AsyncTask asyncTask) {
        this.bindTask = asyncTask;
    }

    public void setCommAttrBinded(boolean z) {
        this.isCommAttrBinded = z;
    }

    public void setFirstBind(boolean z) {
        DynamicTemplateEngine dynamicTemplateEngine = this.engine;
        if (dynamicTemplateEngine == null || !dynamicTemplateEngine.isAttached) {
            return;
        }
        this.isFirstBind = z;
    }
}
