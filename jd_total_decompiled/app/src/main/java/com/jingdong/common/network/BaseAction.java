package com.jingdong.common.network;

import android.text.TextUtils;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.impl.UtilFactory;
import com.jingdong.common.network.BaseEntity;
import com.jingdong.common.network.BaseParam;
import com.jingdong.common.protocol.eventbus.IEventBus;
import com.jingdong.common.protocol.http.HttpListener;
import com.jingdong.common.protocol.http.IHttpConfig;
import com.jingdong.common.protocol.http.IHttpSetting;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.auraSetting.AuraBundleConfig;
import com.jingdong.jdsdk.config.HostConfig;
import com.jingdong.jdsdk.config.HostConstants;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import java.util.PriorityQueue;
import java.util.Queue;

/* loaded from: classes5.dex */
public abstract class BaseAction<T extends BaseParam, E extends BaseEntity> implements HttpListener {
    private static final String TAG = "BaseAction";
    private CommandCallBack<E> mCallBack;
    private int mId;
    private Queue<AbstractTask<E>> mTaskInterceptor;

    public BaseAction() {
    }

    protected final void addTask(AbstractTask<E> abstractTask) {
        if (abstractTask != null) {
            if (this.mTaskInterceptor == null) {
                this.mTaskInterceptor = new PriorityQueue(1);
            }
            this.mTaskInterceptor.add(abstractTask);
        }
    }

    public abstract void cancleIO();

    public abstract void clearState(int i2);

    public abstract void configHttp(IHttpSetting iHttpSetting, T t);

    protected abstract E createErrorEntity();

    public abstract void doRequest(T t);

    protected final void executeErrorCommand(String str) {
        if (this.mCallBack == null) {
            return;
        }
        E createErrorEntity = createErrorEntity();
        if (createErrorEntity != null) {
            createErrorEntity.netMessage = str;
        }
        this.mCallBack.executeErrorCommand(createErrorEntity);
        if (Log.D) {
            Log.d(TAG, "on executing error command");
        }
    }

    protected final void executeSucCommand(String str) {
        E parseDataToEntity = parseDataToEntity(str);
        if (parseDataToEntity != null) {
            while (true) {
                Queue<AbstractTask<E>> queue = this.mTaskInterceptor;
                if (queue == null || queue.isEmpty()) {
                    break;
                }
                AbstractTask<E> poll = this.mTaskInterceptor.poll();
                if (poll != null) {
                    poll.executeTask(parseDataToEntity);
                }
            }
            this.mCallBack.executeSucCommand(parseDataToEntity);
        } else {
            executeErrorCommand(null);
        }
        if (Log.D) {
            Log.d(TAG, "on executing suc command");
        }
    }

    public CommandCallBack<E> getCallBack() {
        return this.mCallBack;
    }

    public final void getData(T t) {
        IHttpSetting httpSetting = UtilFactory.getInstance().getHttpSetting();
        if (httpSetting != null) {
            httpSetting.setHost(HostConfig.getInstance().getHost(HostConstants.PERSONAL_HOST));
            httpSetting.putJsonParam("plugin_version", Long.valueOf(AuraBundleConfig.getInstance().getBundleVersionCode("com.jd.lib.setting")));
            httpSetting.setListener(this);
            configHttp(httpSetting, t);
            BaseActivity activity = t != null ? t.getActivity() : null;
            if (activity == null) {
                httpSetting.addHttpSetting();
            } else {
                httpSetting.addHttpSetting(activity);
            }
        }
    }

    public int getId() {
        return this.mId;
    }

    @Override // com.jingdong.common.protocol.http.HttpListener
    public void onCancel() {
    }

    @Override // com.jingdong.common.protocol.http.HttpListener
    public void onEnd(String str) {
        try {
            if (this.mCallBack == null) {
                return;
            }
            if (TextUtils.isEmpty(str)) {
                executeErrorCommand(null);
            } else {
                executeSucCommand(str);
            }
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
    }

    @Override // com.jingdong.common.protocol.http.HttpListener
    public void onError(String str) {
        try {
            executeErrorCommand(str);
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
    }

    @Override // com.jingdong.common.protocol.http.HttpListener
    public void onReady(IHttpConfig iHttpConfig) {
        if (Log.D) {
            Log.d(TAG, "current networkAction id = " + getId());
        }
    }

    public abstract E parseDataToEntity(String str);

    public void postEvent(Object obj) {
        IEventBus eventBus = UtilFactory.getInstance().getEventBus();
        if (eventBus != null) {
            eventBus.postEvent(obj);
        }
    }

    protected final void removeTask(AbstractTask<E> abstractTask) {
        Queue<AbstractTask<E>> queue;
        if (abstractTask == null || (queue = this.mTaskInterceptor) == null) {
            return;
        }
        queue.remove(abstractTask);
    }

    public void setCommandCallBack(CommandCallBack<E> commandCallBack) {
        this.mCallBack = commandCallBack;
    }

    public void setId(int i2) {
        this.mId = i2;
    }

    public BaseAction(int i2, CommandCallBack<E> commandCallBack) {
        this();
        this.mId = i2;
        this.mCallBack = commandCallBack;
    }
}
