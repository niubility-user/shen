package com.facebook.react.animated;

import android.util.SparseArray;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.UIImplementation;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.EventDispatcherListener;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.recommend.RecommendMtaUtils;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import javax.annotation.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class NativeAnimatedNodesManager implements EventDispatcherListener {
    private final UIManagerModule.CustomEventNamesResolver mCustomEventNamesResolver;
    private final UIImplementation mUIImplementation;
    private final SparseArray<AnimatedNode> mAnimatedNodes = new SparseArray<>();
    private final SparseArray<AnimationDriver> mActiveAnimations = new SparseArray<>();
    private final SparseArray<AnimatedNode> mUpdatedNodes = new SparseArray<>();
    private final Map<String, List<EventAnimationDriver>> mEventDrivers = new HashMap();
    private int mAnimatedGraphBFSColor = 0;
    private final List<AnimatedNode> mRunUpdateNodeList = new LinkedList();

    public NativeAnimatedNodesManager(UIManagerModule uIManagerModule) {
        this.mUIImplementation = uIManagerModule.getUIImplementation();
        uIManagerModule.getEventDispatcher().addListener(this);
        this.mCustomEventNamesResolver = uIManagerModule.getDirectEventNamesResolver();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleEvent(Event event) {
        if (this.mEventDrivers.isEmpty()) {
            return;
        }
        String resolveCustomEventName = this.mCustomEventNamesResolver.resolveCustomEventName(event.getEventName());
        List<EventAnimationDriver> list = this.mEventDrivers.get(event.getViewTag() + resolveCustomEventName);
        if (list != null) {
            for (EventAnimationDriver eventAnimationDriver : list) {
                stopAnimationsForNode(eventAnimationDriver.mValueNode);
                event.dispatch(eventAnimationDriver);
                this.mRunUpdateNodeList.add(eventAnimationDriver.mValueNode);
            }
            updateNodes(this.mRunUpdateNodeList);
            this.mRunUpdateNodeList.clear();
        }
    }

    private void stopAnimationsForNode(AnimatedNode animatedNode) {
        int i2 = 0;
        while (i2 < this.mActiveAnimations.size()) {
            AnimationDriver valueAt = this.mActiveAnimations.valueAt(i2);
            if (animatedNode.equals(valueAt.mAnimatedValue)) {
                if (valueAt.mEndCallback != null) {
                    WritableMap createMap = Arguments.createMap();
                    createMap.putBoolean("finished", false);
                    valueAt.mEndCallback.invoke(createMap);
                }
                this.mActiveAnimations.removeAt(i2);
                i2--;
            }
            i2++;
        }
    }

    private void updateNodes(List<AnimatedNode> list) {
        int i2 = this.mAnimatedGraphBFSColor + 1;
        this.mAnimatedGraphBFSColor = i2;
        if (i2 == 0) {
            this.mAnimatedGraphBFSColor = i2 + 1;
        }
        ArrayDeque arrayDeque = new ArrayDeque();
        int i3 = 0;
        for (AnimatedNode animatedNode : list) {
            int i4 = animatedNode.mBFSColor;
            int i5 = this.mAnimatedGraphBFSColor;
            if (i4 != i5) {
                animatedNode.mBFSColor = i5;
                i3++;
                arrayDeque.add(animatedNode);
            }
        }
        while (!arrayDeque.isEmpty()) {
            AnimatedNode animatedNode2 = (AnimatedNode) arrayDeque.poll();
            if (animatedNode2.mChildren != null) {
                for (int i6 = 0; i6 < animatedNode2.mChildren.size(); i6++) {
                    AnimatedNode animatedNode3 = animatedNode2.mChildren.get(i6);
                    animatedNode3.mActiveIncomingNodes++;
                    int i7 = animatedNode3.mBFSColor;
                    int i8 = this.mAnimatedGraphBFSColor;
                    if (i7 != i8) {
                        animatedNode3.mBFSColor = i8;
                        i3++;
                        arrayDeque.add(animatedNode3);
                    }
                }
            }
        }
        int i9 = this.mAnimatedGraphBFSColor + 1;
        this.mAnimatedGraphBFSColor = i9;
        if (i9 == 0) {
            this.mAnimatedGraphBFSColor = i9 + 1;
        }
        int i10 = 0;
        for (AnimatedNode animatedNode4 : list) {
            if (animatedNode4.mActiveIncomingNodes == 0) {
                int i11 = animatedNode4.mBFSColor;
                int i12 = this.mAnimatedGraphBFSColor;
                if (i11 != i12) {
                    animatedNode4.mBFSColor = i12;
                    i10++;
                    arrayDeque.add(animatedNode4);
                }
            }
        }
        while (!arrayDeque.isEmpty()) {
            AnimatedNode animatedNode5 = (AnimatedNode) arrayDeque.poll();
            animatedNode5.update();
            if (animatedNode5 instanceof PropsAnimatedNode) {
                try {
                    ((PropsAnimatedNode) animatedNode5).updateView();
                } catch (IllegalViewOperationException e2) {
                    FLog.e(ReactConstants.TAG, "Native animation workaround, frame lost as result of race condition", e2);
                }
            }
            if (animatedNode5 instanceof ValueAnimatedNode) {
                ((ValueAnimatedNode) animatedNode5).onValueUpdate();
            }
            if (animatedNode5.mChildren != null) {
                for (int i13 = 0; i13 < animatedNode5.mChildren.size(); i13++) {
                    AnimatedNode animatedNode6 = animatedNode5.mChildren.get(i13);
                    int i14 = animatedNode6.mActiveIncomingNodes - 1;
                    animatedNode6.mActiveIncomingNodes = i14;
                    int i15 = animatedNode6.mBFSColor;
                    int i16 = this.mAnimatedGraphBFSColor;
                    if (i15 != i16 && i14 == 0) {
                        animatedNode6.mBFSColor = i16;
                        i10++;
                        arrayDeque.add(animatedNode6);
                    }
                }
            }
        }
        if (i3 == i10) {
            return;
        }
        throw new IllegalStateException("Looks like animated nodes graph has cycles, there are " + i3 + " but toposort visited only " + i10);
    }

    public void addAnimatedEventToView(int i2, String str, ReadableMap readableMap) {
        int i3 = readableMap.getInt("animatedValueTag");
        AnimatedNode animatedNode = this.mAnimatedNodes.get(i3);
        if (animatedNode != null) {
            if (animatedNode instanceof ValueAnimatedNode) {
                ReadableArray array = readableMap.getArray("nativeEventPath");
                ArrayList arrayList = new ArrayList(array.size());
                for (int i4 = 0; i4 < array.size(); i4++) {
                    arrayList.add(array.getString(i4));
                }
                EventAnimationDriver eventAnimationDriver = new EventAnimationDriver(arrayList, (ValueAnimatedNode) animatedNode);
                String str2 = i2 + str;
                if (this.mEventDrivers.containsKey(str2)) {
                    this.mEventDrivers.get(str2).add(eventAnimationDriver);
                    return;
                }
                ArrayList arrayList2 = new ArrayList(1);
                arrayList2.add(eventAnimationDriver);
                this.mEventDrivers.put(str2, arrayList2);
                return;
            }
            throw new JSApplicationIllegalArgumentException("Animated node connected to event should beof type " + ValueAnimatedNode.class.getName());
        }
        throw new JSApplicationIllegalArgumentException("Animated node with tag " + i3 + " does not exists");
    }

    public void connectAnimatedNodeToView(int i2, int i3) {
        AnimatedNode animatedNode = this.mAnimatedNodes.get(i2);
        if (animatedNode != null) {
            if (animatedNode instanceof PropsAnimatedNode) {
                ((PropsAnimatedNode) animatedNode).connectToView(i3);
                this.mUpdatedNodes.put(i2, animatedNode);
                return;
            }
            throw new JSApplicationIllegalArgumentException("Animated node connected to view should beof type " + PropsAnimatedNode.class.getName());
        }
        throw new JSApplicationIllegalArgumentException("Animated node with tag " + i2 + " does not exists");
    }

    public void connectAnimatedNodes(int i2, int i3) {
        AnimatedNode animatedNode = this.mAnimatedNodes.get(i2);
        if (animatedNode != null) {
            AnimatedNode animatedNode2 = this.mAnimatedNodes.get(i3);
            if (animatedNode2 != null) {
                animatedNode.addChild(animatedNode2);
                this.mUpdatedNodes.put(i3, animatedNode2);
                return;
            }
            throw new JSApplicationIllegalArgumentException("Animated node with tag " + i3 + " does not exists");
        }
        throw new JSApplicationIllegalArgumentException("Animated node with tag " + i2 + " does not exists");
    }

    public void createAnimatedNode(int i2, ReadableMap readableMap) {
        AnimatedNode trackingAnimatedNode;
        if (this.mAnimatedNodes.get(i2) == null) {
            String string = readableMap.getString("type");
            if (DeeplinkProductDetailHelper.LAYER_STYLE.equals(string)) {
                trackingAnimatedNode = new StyleAnimatedNode(readableMap, this);
            } else if ("value".equals(string)) {
                trackingAnimatedNode = new ValueAnimatedNode(readableMap);
            } else if ("props".equals(string)) {
                trackingAnimatedNode = new PropsAnimatedNode(readableMap, this, this.mUIImplementation);
            } else if ("interpolation".equals(string)) {
                trackingAnimatedNode = new InterpolationAnimatedNode(readableMap);
            } else if ("addition".equals(string)) {
                trackingAnimatedNode = new AdditionAnimatedNode(readableMap, this);
            } else if ("subtraction".equals(string)) {
                trackingAnimatedNode = new SubtractionAnimatedNode(readableMap, this);
            } else if ("division".equals(string)) {
                trackingAnimatedNode = new DivisionAnimatedNode(readableMap, this);
            } else if ("multiplication".equals(string)) {
                trackingAnimatedNode = new MultiplicationAnimatedNode(readableMap, this);
            } else if ("modulus".equals(string)) {
                trackingAnimatedNode = new ModulusAnimatedNode(readableMap, this);
            } else if ("diffclamp".equals(string)) {
                trackingAnimatedNode = new DiffClampAnimatedNode(readableMap, this);
            } else if ("transform".equals(string)) {
                trackingAnimatedNode = new TransformAnimatedNode(readableMap, this);
            } else if (RecommendMtaUtils.TRACKING.equals(string)) {
                trackingAnimatedNode = new TrackingAnimatedNode(readableMap, this);
            } else {
                throw new JSApplicationIllegalArgumentException("Unsupported node type: " + string);
            }
            trackingAnimatedNode.mTag = i2;
            this.mAnimatedNodes.put(i2, trackingAnimatedNode);
            this.mUpdatedNodes.put(i2, trackingAnimatedNode);
            return;
        }
        throw new JSApplicationIllegalArgumentException("Animated node with tag " + i2 + " already exists");
    }

    public void disconnectAnimatedNodeFromView(int i2, int i3) {
        AnimatedNode animatedNode = this.mAnimatedNodes.get(i2);
        if (animatedNode != null) {
            if (animatedNode instanceof PropsAnimatedNode) {
                ((PropsAnimatedNode) animatedNode).disconnectFromView(i3);
                return;
            }
            throw new JSApplicationIllegalArgumentException("Animated node connected to view should beof type " + PropsAnimatedNode.class.getName());
        }
        throw new JSApplicationIllegalArgumentException("Animated node with tag " + i2 + " does not exists");
    }

    public void disconnectAnimatedNodes(int i2, int i3) {
        AnimatedNode animatedNode = this.mAnimatedNodes.get(i2);
        if (animatedNode != null) {
            AnimatedNode animatedNode2 = this.mAnimatedNodes.get(i3);
            if (animatedNode2 != null) {
                animatedNode.removeChild(animatedNode2);
                this.mUpdatedNodes.put(i3, animatedNode2);
                return;
            }
            throw new JSApplicationIllegalArgumentException("Animated node with tag " + i3 + " does not exists");
        }
        throw new JSApplicationIllegalArgumentException("Animated node with tag " + i2 + " does not exists");
    }

    public void dropAnimatedNode(int i2) {
        this.mAnimatedNodes.remove(i2);
        this.mUpdatedNodes.remove(i2);
    }

    public void extractAnimatedNodeOffset(int i2) {
        AnimatedNode animatedNode = this.mAnimatedNodes.get(i2);
        if (animatedNode != null && (animatedNode instanceof ValueAnimatedNode)) {
            ((ValueAnimatedNode) animatedNode).extractOffset();
            return;
        }
        throw new JSApplicationIllegalArgumentException("Animated node with tag " + i2 + " does not exists or is not a 'value' node");
    }

    public void flattenAnimatedNodeOffset(int i2) {
        AnimatedNode animatedNode = this.mAnimatedNodes.get(i2);
        if (animatedNode != null && (animatedNode instanceof ValueAnimatedNode)) {
            ((ValueAnimatedNode) animatedNode).flattenOffset();
            return;
        }
        throw new JSApplicationIllegalArgumentException("Animated node with tag " + i2 + " does not exists or is not a 'value' node");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public AnimatedNode getNodeById(int i2) {
        return this.mAnimatedNodes.get(i2);
    }

    public boolean hasActiveAnimations() {
        return this.mActiveAnimations.size() > 0 || this.mUpdatedNodes.size() > 0;
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcherListener
    public void onEventDispatch(final Event event) {
        if (UiThreadUtil.isOnUiThread()) {
            handleEvent(event);
        } else {
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.animated.NativeAnimatedNodesManager.1
                @Override // java.lang.Runnable
                public void run() {
                    NativeAnimatedNodesManager.this.handleEvent(event);
                }
            });
        }
    }

    public void removeAnimatedEventFromView(int i2, String str, int i3) {
        String str2 = i2 + str;
        if (this.mEventDrivers.containsKey(str2)) {
            List<EventAnimationDriver> list = this.mEventDrivers.get(str2);
            if (list.size() == 1) {
                this.mEventDrivers.remove(i2 + str);
                return;
            }
            ListIterator<EventAnimationDriver> listIterator = list.listIterator();
            while (listIterator.hasNext()) {
                if (listIterator.next().mValueNode.mTag == i3) {
                    listIterator.remove();
                    return;
                }
            }
        }
    }

    public void restoreDefaultValues(int i2, int i3) {
        AnimatedNode animatedNode = this.mAnimatedNodes.get(i2);
        if (animatedNode == null) {
            return;
        }
        if (animatedNode instanceof PropsAnimatedNode) {
            ((PropsAnimatedNode) animatedNode).restoreDefaultValues();
            return;
        }
        throw new JSApplicationIllegalArgumentException("Animated node connected to view should beof type " + PropsAnimatedNode.class.getName());
    }

    public void runUpdates(long j2) {
        UiThreadUtil.assertOnUiThread();
        for (int i2 = 0; i2 < this.mUpdatedNodes.size(); i2++) {
            this.mRunUpdateNodeList.add(this.mUpdatedNodes.valueAt(i2));
        }
        this.mUpdatedNodes.clear();
        boolean z = false;
        for (int i3 = 0; i3 < this.mActiveAnimations.size(); i3++) {
            AnimationDriver valueAt = this.mActiveAnimations.valueAt(i3);
            valueAt.runAnimationStep(j2);
            this.mRunUpdateNodeList.add(valueAt.mAnimatedValue);
            if (valueAt.mHasFinished) {
                z = true;
            }
        }
        updateNodes(this.mRunUpdateNodeList);
        this.mRunUpdateNodeList.clear();
        if (z) {
            for (int size = this.mActiveAnimations.size() - 1; size >= 0; size--) {
                AnimationDriver valueAt2 = this.mActiveAnimations.valueAt(size);
                if (valueAt2.mHasFinished) {
                    if (valueAt2.mEndCallback != null) {
                        WritableMap createMap = Arguments.createMap();
                        createMap.putBoolean("finished", true);
                        valueAt2.mEndCallback.invoke(createMap);
                    }
                    this.mActiveAnimations.removeAt(size);
                }
            }
        }
    }

    public void setAnimatedNodeOffset(int i2, double d) {
        AnimatedNode animatedNode = this.mAnimatedNodes.get(i2);
        if (animatedNode != null && (animatedNode instanceof ValueAnimatedNode)) {
            ((ValueAnimatedNode) animatedNode).mOffset = d;
            this.mUpdatedNodes.put(i2, animatedNode);
            return;
        }
        throw new JSApplicationIllegalArgumentException("Animated node with tag " + i2 + " does not exists or is not a 'value' node");
    }

    public void setAnimatedNodeValue(int i2, double d) {
        AnimatedNode animatedNode = this.mAnimatedNodes.get(i2);
        if (animatedNode != null && (animatedNode instanceof ValueAnimatedNode)) {
            stopAnimationsForNode(animatedNode);
            ((ValueAnimatedNode) animatedNode).mValue = d;
            this.mUpdatedNodes.put(i2, animatedNode);
            return;
        }
        throw new JSApplicationIllegalArgumentException("Animated node with tag " + i2 + " does not exists or is not a 'value' node");
    }

    public void startAnimatingNode(int i2, int i3, ReadableMap readableMap, Callback callback) {
        AnimationDriver decayAnimation;
        AnimatedNode animatedNode = this.mAnimatedNodes.get(i3);
        if (animatedNode != null) {
            if (animatedNode instanceof ValueAnimatedNode) {
                AnimationDriver animationDriver = this.mActiveAnimations.get(i2);
                if (animationDriver != null) {
                    animationDriver.resetConfig(readableMap);
                    return;
                }
                String string = readableMap.getString("type");
                if ("frames".equals(string)) {
                    decayAnimation = new FrameBasedAnimationDriver(readableMap);
                } else if ("spring".equals(string)) {
                    decayAnimation = new SpringAnimation(readableMap);
                } else if ("decay".equals(string)) {
                    decayAnimation = new DecayAnimation(readableMap);
                } else {
                    throw new JSApplicationIllegalArgumentException("Unsupported animation type: " + string);
                }
                decayAnimation.mId = i2;
                decayAnimation.mEndCallback = callback;
                decayAnimation.mAnimatedValue = (ValueAnimatedNode) animatedNode;
                this.mActiveAnimations.put(i2, decayAnimation);
                return;
            }
            throw new JSApplicationIllegalArgumentException("Animated node should be of type " + ValueAnimatedNode.class.getName());
        }
        throw new JSApplicationIllegalArgumentException("Animated node with tag " + i3 + " does not exists");
    }

    public void startListeningToAnimatedNodeValue(int i2, AnimatedNodeValueListener animatedNodeValueListener) {
        AnimatedNode animatedNode = this.mAnimatedNodes.get(i2);
        if (animatedNode != null && (animatedNode instanceof ValueAnimatedNode)) {
            ((ValueAnimatedNode) animatedNode).setValueListener(animatedNodeValueListener);
            return;
        }
        throw new JSApplicationIllegalArgumentException("Animated node with tag " + i2 + " does not exists or is not a 'value' node");
    }

    public void stopAnimation(int i2) {
        for (int i3 = 0; i3 < this.mActiveAnimations.size(); i3++) {
            AnimationDriver valueAt = this.mActiveAnimations.valueAt(i3);
            if (valueAt.mId == i2) {
                if (valueAt.mEndCallback != null) {
                    WritableMap createMap = Arguments.createMap();
                    createMap.putBoolean("finished", false);
                    valueAt.mEndCallback.invoke(createMap);
                }
                this.mActiveAnimations.removeAt(i3);
                return;
            }
        }
    }

    public void stopListeningToAnimatedNodeValue(int i2) {
        AnimatedNode animatedNode = this.mAnimatedNodes.get(i2);
        if (animatedNode != null && (animatedNode instanceof ValueAnimatedNode)) {
            ((ValueAnimatedNode) animatedNode).setValueListener(null);
            return;
        }
        throw new JSApplicationIllegalArgumentException("Animated node with tag " + i2 + " does not exists or is not a 'value' node");
    }
}
