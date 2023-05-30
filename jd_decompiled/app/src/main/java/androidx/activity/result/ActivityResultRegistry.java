package androidx.activity.result;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

/* loaded from: classes.dex */
public abstract class ActivityResultRegistry {
    private static final int INITIAL_REQUEST_CODE_VALUE = 65536;
    private static final String KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS = "KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS";
    private static final String KEY_COMPONENT_ACTIVITY_PENDING_RESULTS = "KEY_COMPONENT_ACTIVITY_PENDING_RESULT";
    private static final String KEY_COMPONENT_ACTIVITY_RANDOM_OBJECT = "KEY_COMPONENT_ACTIVITY_RANDOM_OBJECT";
    private static final String KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS = "KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS";
    private static final String KEY_COMPONENT_ACTIVITY_REGISTERED_RCS = "KEY_COMPONENT_ACTIVITY_REGISTERED_RCS";
    private static final String LOG_TAG = "ActivityResultRegistry";
    private Random mRandom = new Random();
    private final Map<Integer, String> mRcToKey = new HashMap();
    final Map<String, Integer> mKeyToRc = new HashMap();
    private final Map<String, LifecycleContainer> mKeyToLifecycleContainers = new HashMap();
    ArrayList<String> mLaunchedKeys = new ArrayList<>();
    final transient Map<String, CallbackAndContract<?>> mKeyToCallback = new HashMap();
    final Map<String, Object> mParsedPendingResults = new HashMap();
    final Bundle mPendingResults = new Bundle();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class CallbackAndContract<O> {
        final ActivityResultCallback<O> mCallback;
        final ActivityResultContract<?, O> mContract;

        CallbackAndContract(ActivityResultCallback<O> activityResultCallback, ActivityResultContract<?, O> activityResultContract) {
            this.mCallback = activityResultCallback;
            this.mContract = activityResultContract;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class LifecycleContainer {
        final Lifecycle mLifecycle;
        private final ArrayList<LifecycleEventObserver> mObservers = new ArrayList<>();

        LifecycleContainer(@NonNull Lifecycle lifecycle) {
            this.mLifecycle = lifecycle;
        }

        void addObserver(@NonNull LifecycleEventObserver lifecycleEventObserver) {
            this.mLifecycle.addObserver(lifecycleEventObserver);
            this.mObservers.add(lifecycleEventObserver);
        }

        void clearObservers() {
            Iterator<LifecycleEventObserver> it = this.mObservers.iterator();
            while (it.hasNext()) {
                this.mLifecycle.removeObserver(it.next());
            }
            this.mObservers.clear();
        }
    }

    private void bindRcKey(int i2, String str) {
        this.mRcToKey.put(Integer.valueOf(i2), str);
        this.mKeyToRc.put(str, Integer.valueOf(i2));
    }

    private <O> void doDispatch(String str, int i2, @Nullable Intent intent, @Nullable CallbackAndContract<O> callbackAndContract) {
        ActivityResultCallback<O> activityResultCallback;
        if (callbackAndContract != null && (activityResultCallback = callbackAndContract.mCallback) != null) {
            activityResultCallback.onActivityResult(callbackAndContract.mContract.parseResult(i2, intent));
            return;
        }
        this.mParsedPendingResults.remove(str);
        this.mPendingResults.putParcelable(str, new ActivityResult(i2, intent));
    }

    private int generateRandomNumber() {
        int nextInt = this.mRandom.nextInt(2147418112);
        while (true) {
            int i2 = nextInt + 65536;
            if (!this.mRcToKey.containsKey(Integer.valueOf(i2))) {
                return i2;
            }
            nextInt = this.mRandom.nextInt(2147418112);
        }
    }

    private int registerKey(String str) {
        Integer num = this.mKeyToRc.get(str);
        if (num != null) {
            return num.intValue();
        }
        int generateRandomNumber = generateRandomNumber();
        bindRcKey(generateRandomNumber, str);
        return generateRandomNumber;
    }

    @MainThread
    public final boolean dispatchResult(int i2, int i3, @Nullable Intent intent) {
        String str = this.mRcToKey.get(Integer.valueOf(i2));
        if (str == null) {
            return false;
        }
        this.mLaunchedKeys.remove(str);
        doDispatch(str, i3, intent, this.mKeyToCallback.get(str));
        return true;
    }

    @MainThread
    public abstract <I, O> void onLaunch(int i2, @NonNull ActivityResultContract<I, O> activityResultContract, @SuppressLint({"UnknownNullness"}) I i3, @Nullable ActivityOptionsCompat activityOptionsCompat);

    public final void onRestoreInstanceState(@Nullable Bundle bundle) {
        if (bundle == null) {
            return;
        }
        ArrayList<Integer> integerArrayList = bundle.getIntegerArrayList(KEY_COMPONENT_ACTIVITY_REGISTERED_RCS);
        ArrayList<String> stringArrayList = bundle.getStringArrayList(KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS);
        if (stringArrayList == null || integerArrayList == null) {
            return;
        }
        this.mLaunchedKeys = bundle.getStringArrayList(KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS);
        this.mRandom = (Random) bundle.getSerializable(KEY_COMPONENT_ACTIVITY_RANDOM_OBJECT);
        this.mPendingResults.putAll(bundle.getBundle(KEY_COMPONENT_ACTIVITY_PENDING_RESULTS));
        for (int i2 = 0; i2 < stringArrayList.size(); i2++) {
            String str = stringArrayList.get(i2);
            if (this.mKeyToRc.containsKey(str)) {
                Integer remove = this.mKeyToRc.remove(str);
                if (!this.mPendingResults.containsKey(str)) {
                    this.mRcToKey.remove(remove);
                }
            }
            bindRcKey(integerArrayList.get(i2).intValue(), stringArrayList.get(i2));
        }
    }

    public final void onSaveInstanceState(@NonNull Bundle bundle) {
        bundle.putIntegerArrayList(KEY_COMPONENT_ACTIVITY_REGISTERED_RCS, new ArrayList<>(this.mKeyToRc.values()));
        bundle.putStringArrayList(KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS, new ArrayList<>(this.mKeyToRc.keySet()));
        bundle.putStringArrayList(KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS, new ArrayList<>(this.mLaunchedKeys));
        bundle.putBundle(KEY_COMPONENT_ACTIVITY_PENDING_RESULTS, (Bundle) this.mPendingResults.clone());
        bundle.putSerializable(KEY_COMPONENT_ACTIVITY_RANDOM_OBJECT, this.mRandom);
    }

    @NonNull
    public final <I, O> ActivityResultLauncher<I> register(@NonNull final String str, @NonNull LifecycleOwner lifecycleOwner, @NonNull final ActivityResultContract<I, O> activityResultContract, @NonNull final ActivityResultCallback<O> activityResultCallback) {
        Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        if (!lifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            registerKey(str);
            LifecycleContainer lifecycleContainer = this.mKeyToLifecycleContainers.get(str);
            if (lifecycleContainer == null) {
                lifecycleContainer = new LifecycleContainer(lifecycle);
            }
            lifecycleContainer.addObserver(new LifecycleEventObserver() { // from class: androidx.activity.result.ActivityResultRegistry.1
                @Override // androidx.lifecycle.LifecycleEventObserver
                public void onStateChanged(@NonNull LifecycleOwner lifecycleOwner2, @NonNull Lifecycle.Event event) {
                    if (Lifecycle.Event.ON_START.equals(event)) {
                        ActivityResultRegistry.this.mKeyToCallback.put(str, new CallbackAndContract<>(activityResultCallback, activityResultContract));
                        if (ActivityResultRegistry.this.mParsedPendingResults.containsKey(str)) {
                            Object obj = ActivityResultRegistry.this.mParsedPendingResults.get(str);
                            ActivityResultRegistry.this.mParsedPendingResults.remove(str);
                            activityResultCallback.onActivityResult(obj);
                        }
                        ActivityResult activityResult = (ActivityResult) ActivityResultRegistry.this.mPendingResults.getParcelable(str);
                        if (activityResult != null) {
                            ActivityResultRegistry.this.mPendingResults.remove(str);
                            activityResultCallback.onActivityResult(activityResultContract.parseResult(activityResult.getResultCode(), activityResult.getData()));
                        }
                    } else if (Lifecycle.Event.ON_STOP.equals(event)) {
                        ActivityResultRegistry.this.mKeyToCallback.remove(str);
                    } else if (Lifecycle.Event.ON_DESTROY.equals(event)) {
                        ActivityResultRegistry.this.unregister(str);
                    }
                }
            });
            this.mKeyToLifecycleContainers.put(str, lifecycleContainer);
            return new ActivityResultLauncher<I>
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0035: RETURN 
                  (wrap: androidx.activity.result.ActivityResultLauncher<I> : 0x0032: CONSTRUCTOR 
                  (r3v0 'this' androidx.activity.result.ActivityResultRegistry A[IMMUTABLE_TYPE, THIS])
                  (r4v0 'str' java.lang.String A[DONT_INLINE])
                  (r5 I:int A[DONT_GENERATE, DONT_INLINE, REMOVE])
                  (r6v0 'activityResultContract' androidx.activity.result.contract.ActivityResultContract<I, O> A[DONT_INLINE])
                 A[GenericInfoAttr{[I], explicit=false}, MD:(androidx.activity.result.ActivityResultRegistry, java.lang.String, int, androidx.activity.result.contract.ActivityResultContract):void (m), WRAPPED] (LINE:9) call: androidx.activity.result.ActivityResultRegistry.2.<init>(androidx.activity.result.ActivityResultRegistry, java.lang.String, int, androidx.activity.result.contract.ActivityResultContract):void type: CONSTRUCTOR)
                 (LINE:9) in method: androidx.activity.result.ActivityResultRegistry.register(java.lang.String, androidx.lifecycle.LifecycleOwner, androidx.activity.result.contract.ActivityResultContract<I, O>, androidx.activity.result.ActivityResultCallback<O>):androidx.activity.result.ActivityResultLauncher<I>, file: classes.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
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
                androidx.lifecycle.Lifecycle r0 = r5.getLifecycle()
                androidx.lifecycle.Lifecycle$State r1 = r0.getCurrentState()
                androidx.lifecycle.Lifecycle$State r2 = androidx.lifecycle.Lifecycle.State.STARTED
                boolean r1 = r1.isAtLeast(r2)
                if (r1 != 0) goto L36
                int r5 = r3.registerKey(r4)
                java.util.Map<java.lang.String, androidx.activity.result.ActivityResultRegistry$LifecycleContainer> r1 = r3.mKeyToLifecycleContainers
                java.lang.Object r1 = r1.get(r4)
                androidx.activity.result.ActivityResultRegistry$LifecycleContainer r1 = (androidx.activity.result.ActivityResultRegistry.LifecycleContainer) r1
                if (r1 != 0) goto L23
                androidx.activity.result.ActivityResultRegistry$LifecycleContainer r1 = new androidx.activity.result.ActivityResultRegistry$LifecycleContainer
                r1.<init>(r0)
            L23:
                androidx.activity.result.ActivityResultRegistry$1 r0 = new androidx.activity.result.ActivityResultRegistry$1
                r0.<init>()
                r1.addObserver(r0)
                java.util.Map<java.lang.String, androidx.activity.result.ActivityResultRegistry$LifecycleContainer> r7 = r3.mKeyToLifecycleContainers
                r7.put(r4, r1)
                androidx.activity.result.ActivityResultRegistry$2 r7 = new androidx.activity.result.ActivityResultRegistry$2
                r7.<init>()
                return r7
            L36:
                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                java.lang.StringBuilder r6 = new java.lang.StringBuilder
                r6.<init>()
                java.lang.String r7 = "LifecycleOwner "
                r6.append(r7)
                r6.append(r5)
                java.lang.String r5 = " is attempting to register while current state is "
                r6.append(r5)
                androidx.lifecycle.Lifecycle$State r5 = r0.getCurrentState()
                r6.append(r5)
                java.lang.String r5 = ". LifecycleOwners must call register before they are STARTED."
                r6.append(r5)
                java.lang.String r5 = r6.toString()
                r4.<init>(r5)
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.activity.result.ActivityResultRegistry.register(java.lang.String, androidx.lifecycle.LifecycleOwner, androidx.activity.result.contract.ActivityResultContract, androidx.activity.result.ActivityResultCallback):androidx.activity.result.ActivityResultLauncher");
        }

        @MainThread
        final void unregister(@NonNull String str) {
            Integer remove;
            if (!this.mLaunchedKeys.contains(str) && (remove = this.mKeyToRc.remove(str)) != null) {
                this.mRcToKey.remove(remove);
            }
            this.mKeyToCallback.remove(str);
            if (this.mParsedPendingResults.containsKey(str)) {
                String str2 = "Dropping pending result for request " + str + ": " + this.mParsedPendingResults.get(str);
                this.mParsedPendingResults.remove(str);
            }
            if (this.mPendingResults.containsKey(str)) {
                String str3 = "Dropping pending result for request " + str + ": " + this.mPendingResults.getParcelable(str);
                this.mPendingResults.remove(str);
            }
            LifecycleContainer lifecycleContainer = this.mKeyToLifecycleContainers.get(str);
            if (lifecycleContainer != null) {
                lifecycleContainer.clearObservers();
                this.mKeyToLifecycleContainers.remove(str);
            }
        }

        @MainThread
        public final <O> boolean dispatchResult(int i2, @SuppressLint({"UnknownNullness"}) O o) {
            ActivityResultCallback<?> activityResultCallback;
            String str = this.mRcToKey.get(Integer.valueOf(i2));
            if (str == null) {
                return false;
            }
            this.mLaunchedKeys.remove(str);
            CallbackAndContract<?> callbackAndContract = this.mKeyToCallback.get(str);
            if (callbackAndContract != null && (activityResultCallback = callbackAndContract.mCallback) != null) {
                activityResultCallback.onActivityResult(o);
                return true;
            }
            this.mPendingResults.remove(str);
            this.mParsedPendingResults.put(str, o);
            return true;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @NonNull
        public final <I, O> ActivityResultLauncher<I> register(@NonNull final String str, @NonNull final ActivityResultContract<I, O> activityResultContract, @NonNull ActivityResultCallback<O> activityResultCallback) {
            registerKey(str);
            this.mKeyToCallback.put(str, new CallbackAndContract<>(activityResultCallback, activityResultContract));
            if (this.mParsedPendingResults.containsKey(str)) {
                Object obj = this.mParsedPendingResults.get(str);
                this.mParsedPendingResults.remove(str);
                activityResultCallback.onActivityResult(obj);
            }
            ActivityResult activityResult = (ActivityResult) this.mPendingResults.getParcelable(str);
            if (activityResult != null) {
                this.mPendingResults.remove(str);
                activityResultCallback.onActivityResult(activityResultContract.parseResult(activityResult.getResultCode(), activityResult.getData()));
            }
            return new ActivityResultLauncher<I>
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0047: RETURN 
                  (wrap: androidx.activity.result.ActivityResultLauncher<I> : 0x0044: CONSTRUCTOR 
                  (r3v0 'this' androidx.activity.result.ActivityResultRegistry A[IMMUTABLE_TYPE, THIS])
                  (r4v0 'str' java.lang.String A[DONT_INLINE])
                  (r0 I:int A[DONT_GENERATE, DONT_INLINE, REMOVE])
                  (r5v0 'activityResultContract' androidx.activity.result.contract.ActivityResultContract<I, O> A[DONT_INLINE])
                 A[GenericInfoAttr{[I], explicit=false}, MD:(androidx.activity.result.ActivityResultRegistry, java.lang.String, int, androidx.activity.result.contract.ActivityResultContract):void (m), WRAPPED] (LINE:23) call: androidx.activity.result.ActivityResultRegistry.3.<init>(androidx.activity.result.ActivityResultRegistry, java.lang.String, int, androidx.activity.result.contract.ActivityResultContract):void type: CONSTRUCTOR)
                 (LINE:23) in method: androidx.activity.result.ActivityResultRegistry.register(java.lang.String, androidx.activity.result.contract.ActivityResultContract<I, O>, androidx.activity.result.ActivityResultCallback<O>):androidx.activity.result.ActivityResultLauncher<I>, file: classes.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
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
                int r0 = r3.registerKey(r4)
                java.util.Map<java.lang.String, androidx.activity.result.ActivityResultRegistry$CallbackAndContract<?>> r1 = r3.mKeyToCallback
                androidx.activity.result.ActivityResultRegistry$CallbackAndContract r2 = new androidx.activity.result.ActivityResultRegistry$CallbackAndContract
                r2.<init>(r6, r5)
                r1.put(r4, r2)
                java.util.Map<java.lang.String, java.lang.Object> r1 = r3.mParsedPendingResults
                boolean r1 = r1.containsKey(r4)
                if (r1 == 0) goto L24
                java.util.Map<java.lang.String, java.lang.Object> r1 = r3.mParsedPendingResults
                java.lang.Object r1 = r1.get(r4)
                java.util.Map<java.lang.String, java.lang.Object> r2 = r3.mParsedPendingResults
                r2.remove(r4)
                r6.onActivityResult(r1)
            L24:
                android.os.Bundle r1 = r3.mPendingResults
                android.os.Parcelable r1 = r1.getParcelable(r4)
                androidx.activity.result.ActivityResult r1 = (androidx.activity.result.ActivityResult) r1
                if (r1 == 0) goto L42
                android.os.Bundle r2 = r3.mPendingResults
                r2.remove(r4)
                int r2 = r1.getResultCode()
                android.content.Intent r1 = r1.getData()
                java.lang.Object r1 = r5.parseResult(r2, r1)
                r6.onActivityResult(r1)
            L42:
                androidx.activity.result.ActivityResultRegistry$3 r6 = new androidx.activity.result.ActivityResultRegistry$3
                r6.<init>()
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.activity.result.ActivityResultRegistry.register(java.lang.String, androidx.activity.result.contract.ActivityResultContract, androidx.activity.result.ActivityResultCallback):androidx.activity.result.ActivityResultLauncher");
        }
    }
