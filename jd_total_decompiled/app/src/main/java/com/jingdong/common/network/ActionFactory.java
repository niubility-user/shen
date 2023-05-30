package com.jingdong.common.network;

import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.novoda.imageloader.core.cache.util.LruCache;

/* loaded from: classes5.dex */
public class ActionFactory {
    private static final String TAG = "ActionFactory";
    private static volatile ActionFactory mInstance;
    private LruCache<Integer, BaseAction> mCaches = new LruCache<>(15);

    private ActionFactory() {
    }

    public static ActionFactory getInstance() {
        if (mInstance == null) {
            synchronized (ActionFactory.class) {
                if (mInstance == null) {
                    mInstance = new ActionFactory();
                }
            }
        }
        return mInstance;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v4, types: [com.jingdong.common.network.BaseAction] */
    public <Action extends BaseAction> Action getAction(int i2, Class<Action> cls) {
        Action action = null;
        try {
            BaseAction baseAction = this.mCaches.get(Integer.valueOf(i2));
            if (baseAction != 0) {
                action = baseAction;
            }
        } catch (Exception unused) {
        }
        if (action == null) {
            try {
                try {
                    Action newInstance = cls.newInstance();
                    if (newInstance != null) {
                        newInstance.setId(i2);
                        this.mCaches.put(Integer.valueOf(i2), newInstance);
                    }
                    action = newInstance;
                } catch (Exception e2) {
                    if (Log.D) {
                        Log.d(TAG, "new instance class in exception \n" + e2.getMessage());
                    }
                    ExceptionReporter.reportExceptionToBugly(e2);
                    if (action != null) {
                        action.setId(i2);
                        this.mCaches.put(Integer.valueOf(i2), action);
                    }
                }
            } catch (Throwable th) {
                if (action != null) {
                    action.setId(i2);
                    this.mCaches.put(Integer.valueOf(i2), action);
                }
                throw th;
            }
        }
        if (Log.D) {
            StringBuilder sb = new StringBuilder();
            sb.append("cache size is ");
            LruCache<Integer, BaseAction> lruCache = this.mCaches;
            sb.append(lruCache == null ? 0L : lruCache.size());
            Log.d(TAG, sb.toString());
        }
        return action;
    }

    public void put(BaseAction baseAction) {
        LruCache<Integer, BaseAction> lruCache;
        if (baseAction == null || (lruCache = this.mCaches) == null) {
            return;
        }
        lruCache.put(Integer.valueOf(baseAction.getId()), baseAction);
    }

    public void remove(int i2) {
        LruCache<Integer, BaseAction> lruCache = this.mCaches;
        if (lruCache != null) {
            lruCache.remove(Integer.valueOf(i2));
        }
    }

    public void remove(BaseAction baseAction) {
        LruCache<Integer, BaseAction> lruCache = this.mCaches;
        if (lruCache == null || baseAction == null) {
            return;
        }
        lruCache.remove(Integer.valueOf(baseAction.getId()));
    }
}
