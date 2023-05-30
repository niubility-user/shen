package com.facebook.jni;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public class DestructorThread {
    private static Thread sThread;
    private static DestructorStack sDestructorStack = new DestructorStack();
    private static ReferenceQueue sReferenceQueue = new ReferenceQueue();
    private static DestructorList sDestructorList = new DestructorList();

    /* loaded from: classes.dex */
    public static abstract class Destructor extends PhantomReference<Object> {
        private Destructor next;
        private Destructor previous;

        abstract void destruct();

        /* JADX INFO: Access modifiers changed from: package-private */
        public Destructor(Object obj) {
            super(obj, DestructorThread.sReferenceQueue);
            DestructorThread.sDestructorStack.push(this);
        }

        private Destructor() {
            super(null, DestructorThread.sReferenceQueue);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class DestructorList {
        private Destructor mHead;

        public DestructorList() {
            Terminus terminus = new Terminus();
            this.mHead = terminus;
            ((Destructor) terminus).next = new Terminus();
            this.mHead.next.previous = this.mHead;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void drop(Destructor destructor) {
            destructor.next.previous = destructor.previous;
            destructor.previous.next = destructor.next;
        }

        public void enqueue(Destructor destructor) {
            destructor.next = this.mHead.next;
            this.mHead.next = destructor;
            destructor.next.previous = destructor;
            destructor.previous = this.mHead;
        }
    }

    /* loaded from: classes.dex */
    private static class DestructorStack {
        private AtomicReference<Destructor> mHead;

        private DestructorStack() {
            this.mHead = new AtomicReference<>();
        }

        public void push(Destructor destructor) {
            Destructor destructor2;
            do {
                destructor2 = this.mHead.get();
                destructor.next = destructor2;
            } while (!this.mHead.compareAndSet(destructor2, destructor));
        }

        public void transferAllToList() {
            Destructor andSet = this.mHead.getAndSet(null);
            while (andSet != null) {
                Destructor destructor = andSet.next;
                DestructorThread.sDestructorList.enqueue(andSet);
                andSet = destructor;
            }
        }
    }

    /* loaded from: classes.dex */
    private static class Terminus extends Destructor {
        private Terminus() {
            super();
        }

        @Override // com.facebook.jni.DestructorThread.Destructor
        void destruct() {
            throw new IllegalStateException("Cannot destroy Terminus Destructor.");
        }
    }

    static {
        Thread thread = new Thread("HybridData DestructorThread") { // from class: com.facebook.jni.DestructorThread.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                while (true) {
                    try {
                        Destructor destructor = (Destructor) DestructorThread.sReferenceQueue.remove();
                        destructor.destruct();
                        if (destructor.previous == null) {
                            DestructorThread.sDestructorStack.transferAllToList();
                        }
                        DestructorList.drop(destructor);
                    } catch (InterruptedException unused) {
                    }
                }
            }
        };
        sThread = thread;
        thread.start();
    }
}
