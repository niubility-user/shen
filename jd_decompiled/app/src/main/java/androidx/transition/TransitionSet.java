package androidx.transition;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.content.res.TypedArrayUtils;
import androidx.transition.Transition;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class TransitionSet extends Transition {
    private static final int FLAG_CHANGE_EPICENTER = 8;
    private static final int FLAG_CHANGE_INTERPOLATOR = 1;
    private static final int FLAG_CHANGE_PATH_MOTION = 4;
    private static final int FLAG_CHANGE_PROPAGATION = 2;
    public static final int ORDERING_SEQUENTIAL = 1;
    public static final int ORDERING_TOGETHER = 0;
    private int mChangeFlags;
    int mCurrentListeners;
    private boolean mPlayTogether;
    boolean mStarted;
    private ArrayList<Transition> mTransitions;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class TransitionSetListener extends TransitionListenerAdapter {
        TransitionSet mTransitionSet;

        TransitionSetListener(TransitionSet transitionSet) {
            this.mTransitionSet = transitionSet;
        }

        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
        public void onTransitionEnd(@NonNull Transition transition) {
            TransitionSet transitionSet = this.mTransitionSet;
            int i2 = transitionSet.mCurrentListeners - 1;
            transitionSet.mCurrentListeners = i2;
            if (i2 == 0) {
                transitionSet.mStarted = false;
                transitionSet.end();
            }
            transition.removeListener(this);
        }

        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
        public void onTransitionStart(@NonNull Transition transition) {
            TransitionSet transitionSet = this.mTransitionSet;
            if (transitionSet.mStarted) {
                return;
            }
            transitionSet.start();
            this.mTransitionSet.mStarted = true;
        }
    }

    public TransitionSet() {
        this.mTransitions = new ArrayList<>();
        this.mPlayTogether = true;
        this.mStarted = false;
        this.mChangeFlags = 0;
    }

    private void setupStartEndListeners() {
        TransitionSetListener transitionSetListener = new TransitionSetListener(this);
        Iterator<Transition> it = this.mTransitions.iterator();
        while (it.hasNext()) {
            it.next().addListener(transitionSetListener);
        }
        this.mCurrentListeners = this.mTransitions.size();
    }

    @NonNull
    public TransitionSet addTransition(@NonNull Transition transition) {
        this.mTransitions.add(transition);
        transition.mParent = this;
        long j2 = this.mDuration;
        if (j2 >= 0) {
            transition.setDuration(j2);
        }
        if ((this.mChangeFlags & 1) != 0) {
            transition.setInterpolator(getInterpolator());
        }
        if ((this.mChangeFlags & 2) != 0) {
            transition.setPropagation(getPropagation());
        }
        if ((this.mChangeFlags & 4) != 0) {
            transition.setPathMotion(getPathMotion());
        }
        if ((this.mChangeFlags & 8) != 0) {
            transition.setEpicenterCallback(getEpicenterCallback());
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.transition.Transition
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void cancel() {
        super.cancel();
        int size = this.mTransitions.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mTransitions.get(i2).cancel();
        }
    }

    @Override // androidx.transition.Transition
    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        if (isValidTarget(transitionValues.view)) {
            Iterator<Transition> it = this.mTransitions.iterator();
            while (it.hasNext()) {
                Transition next = it.next();
                if (next.isValidTarget(transitionValues.view)) {
                    next.captureEndValues(transitionValues);
                    transitionValues.mTargetedTransitions.add(next);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.transition.Transition
    public void capturePropagationValues(TransitionValues transitionValues) {
        super.capturePropagationValues(transitionValues);
        int size = this.mTransitions.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mTransitions.get(i2).capturePropagationValues(transitionValues);
        }
    }

    @Override // androidx.transition.Transition
    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        if (isValidTarget(transitionValues.view)) {
            Iterator<Transition> it = this.mTransitions.iterator();
            while (it.hasNext()) {
                Transition next = it.next();
                if (next.isValidTarget(transitionValues.view)) {
                    next.captureStartValues(transitionValues);
                    transitionValues.mTargetedTransitions.add(next);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.transition.Transition
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void createAnimators(ViewGroup viewGroup, TransitionValuesMaps transitionValuesMaps, TransitionValuesMaps transitionValuesMaps2, ArrayList<TransitionValues> arrayList, ArrayList<TransitionValues> arrayList2) {
        long startDelay = getStartDelay();
        int size = this.mTransitions.size();
        for (int i2 = 0; i2 < size; i2++) {
            Transition transition = this.mTransitions.get(i2);
            if (startDelay > 0 && (this.mPlayTogether || i2 == 0)) {
                long startDelay2 = transition.getStartDelay();
                if (startDelay2 > 0) {
                    transition.setStartDelay(startDelay2 + startDelay);
                } else {
                    transition.setStartDelay(startDelay);
                }
            }
            transition.createAnimators(viewGroup, transitionValuesMaps, transitionValuesMaps2, arrayList, arrayList2);
        }
    }

    @Override // androidx.transition.Transition
    @NonNull
    public Transition excludeTarget(@NonNull View view, boolean z) {
        for (int i2 = 0; i2 < this.mTransitions.size(); i2++) {
            this.mTransitions.get(i2).excludeTarget(view, z);
        }
        return super.excludeTarget(view, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.transition.Transition
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void forceToEnd(ViewGroup viewGroup) {
        super.forceToEnd(viewGroup);
        int size = this.mTransitions.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mTransitions.get(i2).forceToEnd(viewGroup);
        }
    }

    public int getOrdering() {
        return !this.mPlayTogether ? 1 : 0;
    }

    public Transition getTransitionAt(int i2) {
        if (i2 < 0 || i2 >= this.mTransitions.size()) {
            return null;
        }
        return this.mTransitions.get(i2);
    }

    public int getTransitionCount() {
        return this.mTransitions.size();
    }

    @Override // androidx.transition.Transition
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void pause(View view) {
        super.pause(view);
        int size = this.mTransitions.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mTransitions.get(i2).pause(view);
        }
    }

    @NonNull
    public TransitionSet removeTransition(@NonNull Transition transition) {
        this.mTransitions.remove(transition);
        transition.mParent = null;
        return this;
    }

    @Override // androidx.transition.Transition
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void resume(View view) {
        super.resume(view);
        int size = this.mTransitions.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mTransitions.get(i2).resume(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.transition.Transition
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void runAnimators() {
        if (this.mTransitions.isEmpty()) {
            start();
            end();
            return;
        }
        setupStartEndListeners();
        if (!this.mPlayTogether) {
            for (int i2 = 1; i2 < this.mTransitions.size(); i2++) {
                this.mTransitions.get(i2);
                this.mTransitions.get(i2 - 1).addListener(new TransitionListenerAdapter
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0036: INVOKE 
                      (wrap: androidx.transition.Transition : 0x0023: INVOKE 
                      (wrap: java.util.ArrayList<androidx.transition.Transition> : 0x001f: IGET (r4v0 'this' androidx.transition.TransitionSet A[IMMUTABLE_TYPE, THIS]) A[WRAPPED] (LINE:7) androidx.transition.TransitionSet.mTransitions java.util.ArrayList)
                      (wrap: ?? : ?: ARITH (r0v6 'i2' int) - (1 int) A[WRAPPED])
                     type: VIRTUAL call: java.util.ArrayList.get(int):java.lang.Object A[MD:(int):E (c), WRAPPED] (LINE:7))
                      (wrap: androidx.transition.TransitionListenerAdapter : 0x0033: CONSTRUCTOR 
                      (r4v0 'this' androidx.transition.TransitionSet A[IMMUTABLE_TYPE, THIS])
                      (r2 I:androidx.transition.Transition A[DONT_GENERATE, DONT_INLINE, REMOVE])
                     A[MD:(androidx.transition.TransitionSet, androidx.transition.Transition):void (m), WRAPPED] (LINE:9) call: androidx.transition.TransitionSet.1.<init>(androidx.transition.TransitionSet, androidx.transition.Transition):void type: CONSTRUCTOR)
                     type: VIRTUAL call: androidx.transition.Transition.addListener(androidx.transition.Transition$TransitionListener):androidx.transition.Transition A[MD:(androidx.transition.Transition$TransitionListener):androidx.transition.Transition (m)] (LINE:9) in method: androidx.transition.TransitionSet.runAnimators():void, file: classes.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                    	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                    	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:195)
                    	at jadx.core.dex.regions.loops.LoopRegion.generate(LoopRegion.java:171)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                    	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    Caused by: java.lang.NullPointerException
                    */
                /*
                    this = this;
                    java.util.ArrayList<androidx.transition.Transition> r0 = r4.mTransitions
                    boolean r0 = r0.isEmpty()
                    if (r0 == 0) goto Lf
                    r4.start()
                    r4.end()
                    return
                Lf:
                    r4.setupStartEndListeners()
                    boolean r0 = r4.mPlayTogether
                    if (r0 != 0) goto L4b
                    r0 = 1
                L17:
                    java.util.ArrayList<androidx.transition.Transition> r1 = r4.mTransitions
                    int r1 = r1.size()
                    if (r0 >= r1) goto L3c
                    java.util.ArrayList<androidx.transition.Transition> r1 = r4.mTransitions
                    int r2 = r0 + (-1)
                    java.lang.Object r1 = r1.get(r2)
                    androidx.transition.Transition r1 = (androidx.transition.Transition) r1
                    java.util.ArrayList<androidx.transition.Transition> r2 = r4.mTransitions
                    java.lang.Object r2 = r2.get(r0)
                    androidx.transition.Transition r2 = (androidx.transition.Transition) r2
                    androidx.transition.TransitionSet$1 r3 = new androidx.transition.TransitionSet$1
                    r3.<init>()
                    r1.addListener(r3)
                    int r0 = r0 + 1
                    goto L17
                L3c:
                    java.util.ArrayList<androidx.transition.Transition> r0 = r4.mTransitions
                    r1 = 0
                    java.lang.Object r0 = r0.get(r1)
                    androidx.transition.Transition r0 = (androidx.transition.Transition) r0
                    if (r0 == 0) goto L61
                    r0.runAnimators()
                    goto L61
                L4b:
                    java.util.ArrayList<androidx.transition.Transition> r0 = r4.mTransitions
                    java.util.Iterator r0 = r0.iterator()
                L51:
                    boolean r1 = r0.hasNext()
                    if (r1 == 0) goto L61
                    java.lang.Object r1 = r0.next()
                    androidx.transition.Transition r1 = (androidx.transition.Transition) r1
                    r1.runAnimators()
                    goto L51
                L61:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.transition.TransitionSet.runAnimators():void");
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // androidx.transition.Transition
            public void setCanRemoveViews(boolean z) {
                super.setCanRemoveViews(z);
                int size = this.mTransitions.size();
                for (int i2 = 0; i2 < size; i2++) {
                    this.mTransitions.get(i2).setCanRemoveViews(z);
                }
            }

            @Override // androidx.transition.Transition
            public void setEpicenterCallback(Transition.EpicenterCallback epicenterCallback) {
                super.setEpicenterCallback(epicenterCallback);
                this.mChangeFlags |= 8;
                int size = this.mTransitions.size();
                for (int i2 = 0; i2 < size; i2++) {
                    this.mTransitions.get(i2).setEpicenterCallback(epicenterCallback);
                }
            }

            @NonNull
            public TransitionSet setOrdering(int i2) {
                if (i2 == 0) {
                    this.mPlayTogether = true;
                } else if (i2 == 1) {
                    this.mPlayTogether = false;
                } else {
                    throw new AndroidRuntimeException("Invalid parameter for TransitionSet ordering: " + i2);
                }
                return this;
            }

            @Override // androidx.transition.Transition
            public void setPathMotion(PathMotion pathMotion) {
                super.setPathMotion(pathMotion);
                this.mChangeFlags |= 4;
                for (int i2 = 0; i2 < this.mTransitions.size(); i2++) {
                    this.mTransitions.get(i2).setPathMotion(pathMotion);
                }
            }

            @Override // androidx.transition.Transition
            public void setPropagation(TransitionPropagation transitionPropagation) {
                super.setPropagation(transitionPropagation);
                this.mChangeFlags |= 2;
                int size = this.mTransitions.size();
                for (int i2 = 0; i2 < size; i2++) {
                    this.mTransitions.get(i2).setPropagation(transitionPropagation);
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // androidx.transition.Transition
            public String toString(String str) {
                String transition = super.toString(str);
                for (int i2 = 0; i2 < this.mTransitions.size(); i2++) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(transition);
                    sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                    sb.append(this.mTransitions.get(i2).toString(str + "  "));
                    transition = sb.toString();
                }
                return transition;
            }

            @Override // androidx.transition.Transition
            @NonNull
            public TransitionSet addListener(@NonNull Transition.TransitionListener transitionListener) {
                return (TransitionSet) super.addListener(transitionListener);
            }

            @Override // androidx.transition.Transition
            /* renamed from: clone */
            public Transition mo6clone() {
                TransitionSet transitionSet = (TransitionSet) super.mo6clone();
                transitionSet.mTransitions = new ArrayList<>();
                int size = this.mTransitions.size();
                for (int i2 = 0; i2 < size; i2++) {
                    transitionSet.addTransition(this.mTransitions.get(i2).mo6clone());
                }
                return transitionSet;
            }

            @Override // androidx.transition.Transition
            @NonNull
            public TransitionSet removeListener(@NonNull Transition.TransitionListener transitionListener) {
                return (TransitionSet) super.removeListener(transitionListener);
            }

            @Override // androidx.transition.Transition
            @NonNull
            public TransitionSet setDuration(long j2) {
                super.setDuration(j2);
                if (this.mDuration >= 0) {
                    int size = this.mTransitions.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        this.mTransitions.get(i2).setDuration(j2);
                    }
                }
                return this;
            }

            @Override // androidx.transition.Transition
            @NonNull
            public TransitionSet setInterpolator(@Nullable TimeInterpolator timeInterpolator) {
                this.mChangeFlags |= 1;
                ArrayList<Transition> arrayList = this.mTransitions;
                if (arrayList != null) {
                    int size = arrayList.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        this.mTransitions.get(i2).setInterpolator(timeInterpolator);
                    }
                }
                return (TransitionSet) super.setInterpolator(timeInterpolator);
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // androidx.transition.Transition
            public TransitionSet setSceneRoot(ViewGroup viewGroup) {
                super.setSceneRoot(viewGroup);
                int size = this.mTransitions.size();
                for (int i2 = 0; i2 < size; i2++) {
                    this.mTransitions.get(i2).setSceneRoot(viewGroup);
                }
                return this;
            }

            @Override // androidx.transition.Transition
            @NonNull
            public TransitionSet setStartDelay(long j2) {
                return (TransitionSet) super.setStartDelay(j2);
            }

            @Override // androidx.transition.Transition
            @NonNull
            public Transition excludeTarget(@NonNull String str, boolean z) {
                for (int i2 = 0; i2 < this.mTransitions.size(); i2++) {
                    this.mTransitions.get(i2).excludeTarget(str, z);
                }
                return super.excludeTarget(str, z);
            }

            @Override // androidx.transition.Transition
            @NonNull
            public TransitionSet addTarget(@NonNull View view) {
                for (int i2 = 0; i2 < this.mTransitions.size(); i2++) {
                    this.mTransitions.get(i2).addTarget(view);
                }
                return (TransitionSet) super.addTarget(view);
            }

            @Override // androidx.transition.Transition
            @NonNull
            public TransitionSet removeTarget(@IdRes int i2) {
                for (int i3 = 0; i3 < this.mTransitions.size(); i3++) {
                    this.mTransitions.get(i3).removeTarget(i2);
                }
                return (TransitionSet) super.removeTarget(i2);
            }

            public TransitionSet(Context context, AttributeSet attributeSet) {
                super(context, attributeSet);
                this.mTransitions = new ArrayList<>();
                this.mPlayTogether = true;
                this.mStarted = false;
                this.mChangeFlags = 0;
                TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, Styleable.TRANSITION_SET);
                setOrdering(TypedArrayUtils.getNamedInt(obtainStyledAttributes, (XmlResourceParser) attributeSet, "transitionOrdering", 0, 0));
                obtainStyledAttributes.recycle();
            }

            @Override // androidx.transition.Transition
            @NonNull
            public Transition excludeTarget(int i2, boolean z) {
                for (int i3 = 0; i3 < this.mTransitions.size(); i3++) {
                    this.mTransitions.get(i3).excludeTarget(i2, z);
                }
                return super.excludeTarget(i2, z);
            }

            @Override // androidx.transition.Transition
            @NonNull
            public TransitionSet addTarget(@IdRes int i2) {
                for (int i3 = 0; i3 < this.mTransitions.size(); i3++) {
                    this.mTransitions.get(i3).addTarget(i2);
                }
                return (TransitionSet) super.addTarget(i2);
            }

            @Override // androidx.transition.Transition
            @NonNull
            public TransitionSet removeTarget(@NonNull View view) {
                for (int i2 = 0; i2 < this.mTransitions.size(); i2++) {
                    this.mTransitions.get(i2).removeTarget(view);
                }
                return (TransitionSet) super.removeTarget(view);
            }

            @Override // androidx.transition.Transition
            @NonNull
            public Transition excludeTarget(@NonNull Class cls, boolean z) {
                for (int i2 = 0; i2 < this.mTransitions.size(); i2++) {
                    this.mTransitions.get(i2).excludeTarget(cls, z);
                }
                return super.excludeTarget(cls, z);
            }

            @Override // androidx.transition.Transition
            @NonNull
            public TransitionSet addTarget(@NonNull String str) {
                for (int i2 = 0; i2 < this.mTransitions.size(); i2++) {
                    this.mTransitions.get(i2).addTarget(str);
                }
                return (TransitionSet) super.addTarget(str);
            }

            @Override // androidx.transition.Transition
            @NonNull
            public TransitionSet removeTarget(@NonNull Class cls) {
                for (int i2 = 0; i2 < this.mTransitions.size(); i2++) {
                    this.mTransitions.get(i2).removeTarget(cls);
                }
                return (TransitionSet) super.removeTarget(cls);
            }

            @Override // androidx.transition.Transition
            @NonNull
            public TransitionSet addTarget(@NonNull Class cls) {
                for (int i2 = 0; i2 < this.mTransitions.size(); i2++) {
                    this.mTransitions.get(i2).addTarget(cls);
                }
                return (TransitionSet) super.addTarget(cls);
            }

            @Override // androidx.transition.Transition
            @NonNull
            public TransitionSet removeTarget(@NonNull String str) {
                for (int i2 = 0; i2 < this.mTransitions.size(); i2++) {
                    this.mTransitions.get(i2).removeTarget(str);
                }
                return (TransitionSet) super.removeTarget(str);
            }
        }
