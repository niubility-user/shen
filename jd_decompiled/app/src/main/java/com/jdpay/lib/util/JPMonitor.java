package com.jdpay.lib.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;

/* loaded from: classes18.dex */
public class JPMonitor {
    public static boolean DEBUG = false;
    public static final int DEFAULT_INVOKER_STACK_INDEX = 4;
    protected Handler handler;

    /* loaded from: classes18.dex */
    public enum Event {
        I { // from class: com.jdpay.lib.util.JPMonitor.Event.1
            @Override // com.jdpay.lib.util.JPMonitor.Event
            boolean isSupportHandler() {
                return true;
            }
        },
        D { // from class: com.jdpay.lib.util.JPMonitor.Event.2
            @Override // com.jdpay.lib.util.JPMonitor.Event
            boolean isSupportHandler() {
                return false;
            }
        },
        V { // from class: com.jdpay.lib.util.JPMonitor.Event.3
            @Override // com.jdpay.lib.util.JPMonitor.Event
            boolean isSupportHandler() {
                return true;
            }
        },
        W { // from class: com.jdpay.lib.util.JPMonitor.Event.4
            @Override // com.jdpay.lib.util.JPMonitor.Event
            boolean isSupportHandler() {
                return true;
            }
        },
        E { // from class: com.jdpay.lib.util.JPMonitor.Event.5
            @Override // com.jdpay.lib.util.JPMonitor.Event
            boolean isSupportHandler() {
                return true;
            }
        };

        abstract boolean isSupportHandler();
    }

    /* loaded from: classes18.dex */
    public interface Handler {
        void onEvent(@NonNull Event event, @NonNull String str, @NonNull String str2);
    }

    public JPMonitor(@Nullable Handler handler) {
        this.handler = handler;
    }

    protected String buildKey(@NonNull Event event, @NonNull StackTraceElement stackTraceElement) {
        return "[" + event.name() + "]" + stackTraceElement.getClassName() + OrderISVUtil.MONEY_DECIMAL + stackTraceElement.getMethodName() + "(#" + stackTraceElement.getLineNumber() + ")";
    }

    public void d(String str, boolean z, int i2) {
        Handler handler;
        Event event = Event.D;
        StackTraceElement invoker = getInvoker(i2);
        if (!z && DEBUG) {
            String str2 = "\u3010" + invoker.getMethodName() + ":" + invoker.getLineNumber() + "\u3011" + str;
            invoker.getClassName();
        } else if (!event.isSupportHandler() || (handler = this.handler) == null) {
        } else {
            handler.onEvent(event, buildKey(event, invoker), str);
        }
    }

    public void e(String str, boolean z, int i2) {
        Handler handler;
        Event event = Event.E;
        StackTraceElement invoker = getInvoker(i2);
        if (!z && DEBUG) {
            String str2 = "\u3010" + invoker.getMethodName() + ":" + invoker.getLineNumber() + "\u3011" + str;
            invoker.getClassName();
        } else if (!event.isSupportHandler() || (handler = this.handler) == null) {
        } else {
            handler.onEvent(event, buildKey(event, invoker), str);
        }
    }

    public Handler getHandler() {
        return this.handler;
    }

    protected StackTraceElement getInvoker(int i2) {
        return Thread.currentThread().getStackTrace()[i2];
    }

    public void i(String str, boolean z, int i2) {
        Handler handler;
        Event event = Event.I;
        StackTraceElement invoker = getInvoker(i2);
        if (!z && DEBUG) {
            String str2 = "\u3010" + invoker.getMethodName() + ":" + invoker.getLineNumber() + "\u3011" + str;
            invoker.getClassName();
        } else if (!event.isSupportHandler() || (handler = this.handler) == null) {
        } else {
            handler.onEvent(event, buildKey(event, invoker), str);
        }
    }

    public void setHandler(@Nullable Handler handler) {
        this.handler = handler;
    }

    public void v(String str, boolean z, int i2) {
        Handler handler;
        Event event = Event.V;
        StackTraceElement invoker = getInvoker(i2);
        if (!z && DEBUG) {
            String str2 = "\u3010" + invoker.getMethodName() + ":" + invoker.getLineNumber() + "\u3011" + str;
            invoker.getClassName();
        } else if (!event.isSupportHandler() || (handler = this.handler) == null) {
        } else {
            handler.onEvent(event, buildKey(event, invoker), str);
        }
    }

    public void w(String str, boolean z, int i2) {
        Handler handler;
        Event event = Event.W;
        StackTraceElement invoker = getInvoker(i2);
        if (!z && DEBUG) {
            String str2 = "\u3010" + invoker.getMethodName() + ":" + invoker.getLineNumber() + "\u3011" + str;
            invoker.getClassName();
        } else if (!event.isSupportHandler() || (handler = this.handler) == null) {
        } else {
            handler.onEvent(event, buildKey(event, invoker), str);
        }
    }
}
