package com.jingdong.common.widget.custom.comment;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes12.dex */
public class CommentObservableManager {
    private static List<WeakReference<CommentListener>> mObservers;

    /* loaded from: classes12.dex */
    private static class CommentInstance {
        public static final CommentObservableManager INSTANCE = new CommentObservableManager();

        private CommentInstance() {
        }
    }

    /* loaded from: classes12.dex */
    public static abstract class CommentListener {
        public void SyncCommentNum(int i2, String str) {
        }

        public void addComment(CommentEntity commentEntity) {
            changeCommentNum(true, commentEntity);
        }

        public void changeCommentNum(boolean z, CommentEntity commentEntity) {
        }

        public void deleteComment(CommentEntity commentEntity) {
            changeCommentNum(false, commentEntity);
        }

        public void zanComment(CommentEntity commentEntity) {
        }
    }

    public static CommentObservableManager getManager() {
        return CommentInstance.INSTANCE;
    }

    public void deregisterCommentObserver(CommentListener commentListener) {
        if (commentListener == null) {
            return;
        }
        synchronized (mObservers) {
            Iterator<WeakReference<CommentListener>> it = mObservers.iterator();
            while (it.hasNext()) {
                CommentListener commentListener2 = it.next().get();
                if (commentListener2 != null && commentListener2 == commentListener) {
                    it.remove();
                }
            }
        }
    }

    public void notifyAddComment(CommentEntity commentEntity) {
        Iterator<WeakReference<CommentListener>> it = mObservers.iterator();
        while (it.hasNext()) {
            CommentListener commentListener = it.next().get();
            if (commentListener != null) {
                commentListener.addComment(commentEntity);
            }
        }
    }

    public void notifyDeleteComment(CommentEntity commentEntity) {
        Iterator<WeakReference<CommentListener>> it = mObservers.iterator();
        while (it.hasNext()) {
            CommentListener commentListener = it.next().get();
            if (commentListener != null) {
                commentListener.deleteComment(commentEntity);
            }
        }
    }

    public void notifySyncComment(int i2, String str) {
        Iterator<WeakReference<CommentListener>> it = mObservers.iterator();
        while (it.hasNext()) {
            CommentListener commentListener = it.next().get();
            if (commentListener != null) {
                commentListener.SyncCommentNum(i2, str);
            }
        }
    }

    public void notifyZanComment(CommentEntity commentEntity) {
        Iterator<WeakReference<CommentListener>> it = mObservers.iterator();
        while (it.hasNext()) {
            CommentListener commentListener = it.next().get();
            if (commentListener != null) {
                commentListener.zanComment(commentEntity);
            }
        }
    }

    public void registerCommentObserver(CommentListener commentListener) {
        if (commentListener == null) {
            return;
        }
        synchronized (mObservers) {
            Iterator<WeakReference<CommentListener>> it = mObservers.iterator();
            while (it.hasNext()) {
                if (it.next().get() == commentListener) {
                    return;
                }
            }
            mObservers.add(new WeakReference<>(commentListener));
        }
    }

    private CommentObservableManager() {
        mObservers = new ArrayList();
    }
}
