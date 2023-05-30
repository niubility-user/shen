package com.jingdong.common.jdreactFramework.views.pulltorefresh;

/* loaded from: classes5.dex */
public class JDReactPullToRefreshUIHandlerHolder implements JDReactPullToRefreshUIHandler {
    private JDReactPullToRefreshUIHandler mHandler;
    private JDReactPullToRefreshUIHandlerHolder mNext;

    private JDReactPullToRefreshUIHandlerHolder() {
    }

    public static void addHandler(JDReactPullToRefreshUIHandlerHolder jDReactPullToRefreshUIHandlerHolder, JDReactPullToRefreshUIHandler jDReactPullToRefreshUIHandler) {
        if (jDReactPullToRefreshUIHandler == null || jDReactPullToRefreshUIHandlerHolder == null) {
            return;
        }
        if (jDReactPullToRefreshUIHandlerHolder.mHandler == null) {
            jDReactPullToRefreshUIHandlerHolder.mHandler = jDReactPullToRefreshUIHandler;
            return;
        }
        while (!jDReactPullToRefreshUIHandlerHolder.contains(jDReactPullToRefreshUIHandler)) {
            JDReactPullToRefreshUIHandlerHolder jDReactPullToRefreshUIHandlerHolder2 = jDReactPullToRefreshUIHandlerHolder.mNext;
            if (jDReactPullToRefreshUIHandlerHolder2 == null) {
                JDReactPullToRefreshUIHandlerHolder jDReactPullToRefreshUIHandlerHolder3 = new JDReactPullToRefreshUIHandlerHolder();
                jDReactPullToRefreshUIHandlerHolder3.mHandler = jDReactPullToRefreshUIHandler;
                jDReactPullToRefreshUIHandlerHolder.mNext = jDReactPullToRefreshUIHandlerHolder3;
                return;
            }
            jDReactPullToRefreshUIHandlerHolder = jDReactPullToRefreshUIHandlerHolder2;
        }
    }

    private boolean contains(JDReactPullToRefreshUIHandler jDReactPullToRefreshUIHandler) {
        JDReactPullToRefreshUIHandler jDReactPullToRefreshUIHandler2 = this.mHandler;
        return jDReactPullToRefreshUIHandler2 != null && jDReactPullToRefreshUIHandler2 == jDReactPullToRefreshUIHandler;
    }

    public static JDReactPullToRefreshUIHandlerHolder create() {
        return new JDReactPullToRefreshUIHandlerHolder();
    }

    private JDReactPullToRefreshUIHandler getHandler() {
        return this.mHandler;
    }

    public static JDReactPullToRefreshUIHandlerHolder removeHandler(JDReactPullToRefreshUIHandlerHolder jDReactPullToRefreshUIHandlerHolder, JDReactPullToRefreshUIHandler jDReactPullToRefreshUIHandler) {
        if (jDReactPullToRefreshUIHandlerHolder == null || jDReactPullToRefreshUIHandler == null || jDReactPullToRefreshUIHandlerHolder.mHandler == null) {
            return jDReactPullToRefreshUIHandlerHolder;
        }
        JDReactPullToRefreshUIHandlerHolder jDReactPullToRefreshUIHandlerHolder2 = jDReactPullToRefreshUIHandlerHolder;
        JDReactPullToRefreshUIHandlerHolder jDReactPullToRefreshUIHandlerHolder3 = null;
        do {
            if (!jDReactPullToRefreshUIHandlerHolder.contains(jDReactPullToRefreshUIHandler)) {
                jDReactPullToRefreshUIHandlerHolder3 = jDReactPullToRefreshUIHandlerHolder;
                jDReactPullToRefreshUIHandlerHolder = jDReactPullToRefreshUIHandlerHolder.mNext;
            } else if (jDReactPullToRefreshUIHandlerHolder3 == null) {
                jDReactPullToRefreshUIHandlerHolder2 = jDReactPullToRefreshUIHandlerHolder.mNext;
                jDReactPullToRefreshUIHandlerHolder.mNext = null;
                jDReactPullToRefreshUIHandlerHolder = jDReactPullToRefreshUIHandlerHolder2;
            } else {
                jDReactPullToRefreshUIHandlerHolder3.mNext = jDReactPullToRefreshUIHandlerHolder.mNext;
                jDReactPullToRefreshUIHandlerHolder.mNext = null;
                jDReactPullToRefreshUIHandlerHolder = jDReactPullToRefreshUIHandlerHolder3.mNext;
            }
        } while (jDReactPullToRefreshUIHandlerHolder != null);
        return jDReactPullToRefreshUIHandlerHolder2 == null ? new JDReactPullToRefreshUIHandlerHolder() : jDReactPullToRefreshUIHandlerHolder2;
    }

    public boolean hasHandler() {
        return this.mHandler != null;
    }

    @Override // com.jingdong.common.jdreactFramework.views.pulltorefresh.JDReactPullToRefreshUIHandler
    public void onUIPositionChange(JDReactPullToRefreshBasicFrameLayout jDReactPullToRefreshBasicFrameLayout, boolean z, byte b, JDReactPullToRefreshIndicator jDReactPullToRefreshIndicator) {
        JDReactPullToRefreshUIHandlerHolder jDReactPullToRefreshUIHandlerHolder = this;
        do {
            JDReactPullToRefreshUIHandler handler = jDReactPullToRefreshUIHandlerHolder.getHandler();
            if (handler != null) {
                handler.onUIPositionChange(jDReactPullToRefreshBasicFrameLayout, z, b, jDReactPullToRefreshIndicator);
            }
            jDReactPullToRefreshUIHandlerHolder = jDReactPullToRefreshUIHandlerHolder.mNext;
        } while (jDReactPullToRefreshUIHandlerHolder != null);
    }

    @Override // com.jingdong.common.jdreactFramework.views.pulltorefresh.JDReactPullToRefreshUIHandler
    public void onUIRefreshBegin(JDReactPullToRefreshBasicFrameLayout jDReactPullToRefreshBasicFrameLayout) {
        JDReactPullToRefreshUIHandlerHolder jDReactPullToRefreshUIHandlerHolder = this;
        do {
            JDReactPullToRefreshUIHandler handler = jDReactPullToRefreshUIHandlerHolder.getHandler();
            if (handler != null) {
                handler.onUIRefreshBegin(jDReactPullToRefreshBasicFrameLayout);
            }
            jDReactPullToRefreshUIHandlerHolder = jDReactPullToRefreshUIHandlerHolder.mNext;
        } while (jDReactPullToRefreshUIHandlerHolder != null);
    }

    @Override // com.jingdong.common.jdreactFramework.views.pulltorefresh.JDReactPullToRefreshUIHandler
    public void onUIRefreshComplete(JDReactPullToRefreshBasicFrameLayout jDReactPullToRefreshBasicFrameLayout) {
        JDReactPullToRefreshUIHandlerHolder jDReactPullToRefreshUIHandlerHolder = this;
        do {
            JDReactPullToRefreshUIHandler handler = jDReactPullToRefreshUIHandlerHolder.getHandler();
            if (handler != null) {
                handler.onUIRefreshComplete(jDReactPullToRefreshBasicFrameLayout);
            }
            jDReactPullToRefreshUIHandlerHolder = jDReactPullToRefreshUIHandlerHolder.mNext;
        } while (jDReactPullToRefreshUIHandlerHolder != null);
    }

    @Override // com.jingdong.common.jdreactFramework.views.pulltorefresh.JDReactPullToRefreshUIHandler
    public void onUIRefreshPrepare(JDReactPullToRefreshBasicFrameLayout jDReactPullToRefreshBasicFrameLayout) {
        if (hasHandler()) {
            JDReactPullToRefreshUIHandlerHolder jDReactPullToRefreshUIHandlerHolder = this;
            do {
                JDReactPullToRefreshUIHandler handler = jDReactPullToRefreshUIHandlerHolder.getHandler();
                if (handler != null) {
                    handler.onUIRefreshPrepare(jDReactPullToRefreshBasicFrameLayout);
                }
                jDReactPullToRefreshUIHandlerHolder = jDReactPullToRefreshUIHandlerHolder.mNext;
            } while (jDReactPullToRefreshUIHandlerHolder != null);
        }
    }

    @Override // com.jingdong.common.jdreactFramework.views.pulltorefresh.JDReactPullToRefreshUIHandler
    public void onUIReset(JDReactPullToRefreshBasicFrameLayout jDReactPullToRefreshBasicFrameLayout) {
        JDReactPullToRefreshUIHandlerHolder jDReactPullToRefreshUIHandlerHolder = this;
        do {
            JDReactPullToRefreshUIHandler handler = jDReactPullToRefreshUIHandlerHolder.getHandler();
            if (handler != null) {
                handler.onUIReset(jDReactPullToRefreshBasicFrameLayout);
            }
            jDReactPullToRefreshUIHandlerHolder = jDReactPullToRefreshUIHandlerHolder.mNext;
        } while (jDReactPullToRefreshUIHandlerHolder != null);
    }
}
