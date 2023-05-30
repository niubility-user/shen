package com.facebook.react.uimanager;

import android.util.SparseBooleanArray;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public class NativeViewHierarchyOptimizer {
    private static final boolean ENABLED = true;
    private final ShadowNodeRegistry mShadowNodeRegistry;
    private final SparseBooleanArray mTagsWithLayoutVisited = new SparseBooleanArray();
    private final UIViewOperationQueue mUIViewOperationQueue;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class NodeIndexPair {
        public final int index;
        public final ReactShadowNode node;

        NodeIndexPair(ReactShadowNode reactShadowNode, int i2) {
            this.node = reactShadowNode;
            this.index = i2;
        }
    }

    public NativeViewHierarchyOptimizer(UIViewOperationQueue uIViewOperationQueue, ShadowNodeRegistry shadowNodeRegistry) {
        this.mUIViewOperationQueue = uIViewOperationQueue;
        this.mShadowNodeRegistry = shadowNodeRegistry;
    }

    private void addGrandchildren(ReactShadowNode reactShadowNode, ReactShadowNode reactShadowNode2, int i2) {
        Assertions.assertCondition(!reactShadowNode.isLayoutOnly());
        for (int i3 = 0; i3 < reactShadowNode2.getChildCount(); i3++) {
            ReactShadowNode childAt = reactShadowNode2.getChildAt(i3);
            Assertions.assertCondition(childAt.getNativeParent() == null);
            if (childAt.isLayoutOnly()) {
                int nativeChildCount = reactShadowNode.getNativeChildCount();
                addLayoutOnlyNode(reactShadowNode, childAt, i2);
                i2 += reactShadowNode.getNativeChildCount() - nativeChildCount;
            } else {
                addNonLayoutNode(reactShadowNode, childAt, i2);
                i2++;
            }
        }
    }

    private void addLayoutOnlyNode(ReactShadowNode reactShadowNode, ReactShadowNode reactShadowNode2, int i2) {
        addGrandchildren(reactShadowNode, reactShadowNode2, i2);
    }

    private void addNodeToNode(ReactShadowNode reactShadowNode, ReactShadowNode reactShadowNode2, int i2) {
        int nativeOffsetForChild = reactShadowNode.getNativeOffsetForChild(reactShadowNode.getChildAt(i2));
        if (reactShadowNode.isLayoutOnly()) {
            NodeIndexPair walkUpUntilNonLayoutOnly = walkUpUntilNonLayoutOnly(reactShadowNode, nativeOffsetForChild);
            if (walkUpUntilNonLayoutOnly == null) {
                return;
            }
            ReactShadowNode reactShadowNode3 = walkUpUntilNonLayoutOnly.node;
            nativeOffsetForChild = walkUpUntilNonLayoutOnly.index;
            reactShadowNode = reactShadowNode3;
        }
        if (!reactShadowNode2.isLayoutOnly()) {
            addNonLayoutNode(reactShadowNode, reactShadowNode2, nativeOffsetForChild);
        } else {
            addLayoutOnlyNode(reactShadowNode, reactShadowNode2, nativeOffsetForChild);
        }
    }

    private void addNonLayoutNode(ReactShadowNode reactShadowNode, ReactShadowNode reactShadowNode2, int i2) {
        reactShadowNode.addNativeChildAt(reactShadowNode2, i2);
        this.mUIViewOperationQueue.enqueueManageChildren(reactShadowNode.getReactTag(), null, new ViewAtIndex[]{new ViewAtIndex(reactShadowNode2.getReactTag(), i2)}, null);
    }

    private void applyLayoutBase(ReactShadowNode reactShadowNode) {
        int reactTag = reactShadowNode.getReactTag();
        if (this.mTagsWithLayoutVisited.get(reactTag)) {
            return;
        }
        this.mTagsWithLayoutVisited.put(reactTag, true);
        int screenX = reactShadowNode.getScreenX();
        int screenY = reactShadowNode.getScreenY();
        for (ReactShadowNode parent = reactShadowNode.getParent(); parent != null && parent.isLayoutOnly(); parent = parent.getParent()) {
            screenX += Math.round(parent.getLayoutX());
            screenY += Math.round(parent.getLayoutY());
        }
        applyLayoutRecursive(reactShadowNode, screenX, screenY);
    }

    private void applyLayoutRecursive(ReactShadowNode reactShadowNode, int i2, int i3) {
        if (!reactShadowNode.isLayoutOnly() && reactShadowNode.getNativeParent() != null) {
            this.mUIViewOperationQueue.enqueueUpdateLayout(reactShadowNode.getNativeParent().getReactTag(), reactShadowNode.getReactTag(), i2, i3, reactShadowNode.getScreenWidth(), reactShadowNode.getScreenHeight());
            return;
        }
        for (int i4 = 0; i4 < reactShadowNode.getChildCount(); i4++) {
            ReactShadowNode childAt = reactShadowNode.getChildAt(i4);
            int reactTag = childAt.getReactTag();
            if (!this.mTagsWithLayoutVisited.get(reactTag)) {
                this.mTagsWithLayoutVisited.put(reactTag, true);
                applyLayoutRecursive(childAt, childAt.getScreenX() + i2, childAt.getScreenY() + i3);
            }
        }
    }

    public static void handleRemoveNode(ReactShadowNode reactShadowNode) {
        reactShadowNode.removeAllNativeChildren();
    }

    private static boolean isLayoutOnlyAndCollapsable(@Nullable ReactStylesDiffMap reactStylesDiffMap) {
        if (reactStylesDiffMap == null) {
            return true;
        }
        if (!reactStylesDiffMap.hasKey(ViewProps.COLLAPSABLE) || reactStylesDiffMap.getBoolean(ViewProps.COLLAPSABLE, true)) {
            ReadableMapKeySetIterator keySetIterator = reactStylesDiffMap.mBackingMap.keySetIterator();
            while (keySetIterator.hasNextKey()) {
                if (!ViewProps.isLayoutOnly(reactStylesDiffMap.mBackingMap, keySetIterator.nextKey())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private void removeNodeFromParent(ReactShadowNode reactShadowNode, boolean z) {
        ReactShadowNode nativeParent = reactShadowNode.getNativeParent();
        if (nativeParent != null) {
            int indexOfNativeChild = nativeParent.indexOfNativeChild(reactShadowNode);
            nativeParent.removeNativeChildAt(indexOfNativeChild);
            this.mUIViewOperationQueue.enqueueManageChildren(nativeParent.getReactTag(), new int[]{indexOfNativeChild}, null, z ? new int[]{reactShadowNode.getReactTag()} : null);
            return;
        }
        for (int childCount = reactShadowNode.getChildCount() - 1; childCount >= 0; childCount--) {
            removeNodeFromParent(reactShadowNode.getChildAt(childCount), z);
        }
    }

    private void transitionLayoutOnlyViewToNativeView(ReactShadowNode reactShadowNode, @Nullable ReactStylesDiffMap reactStylesDiffMap) {
        ReactShadowNode parent = reactShadowNode.getParent();
        if (parent == null) {
            reactShadowNode.setIsLayoutOnly(false);
            return;
        }
        int indexOf = parent.indexOf(reactShadowNode);
        parent.removeChildAt(indexOf);
        removeNodeFromParent(reactShadowNode, false);
        reactShadowNode.setIsLayoutOnly(false);
        this.mUIViewOperationQueue.enqueueCreateView(reactShadowNode.getThemedContext(), reactShadowNode.getReactTag(), reactShadowNode.getViewClass(), reactStylesDiffMap);
        parent.addChildAt(reactShadowNode, indexOf);
        addNodeToNode(parent, reactShadowNode, indexOf);
        for (int i2 = 0; i2 < reactShadowNode.getChildCount(); i2++) {
            addNodeToNode(reactShadowNode, reactShadowNode.getChildAt(i2), i2);
        }
        Assertions.assertCondition(this.mTagsWithLayoutVisited.size() == 0);
        applyLayoutBase(reactShadowNode);
        for (int i3 = 0; i3 < reactShadowNode.getChildCount(); i3++) {
            applyLayoutBase(reactShadowNode.getChildAt(i3));
        }
        this.mTagsWithLayoutVisited.clear();
    }

    private NodeIndexPair walkUpUntilNonLayoutOnly(ReactShadowNode reactShadowNode, int i2) {
        while (reactShadowNode.isLayoutOnly()) {
            ReactShadowNode parent = reactShadowNode.getParent();
            if (parent == null) {
                return null;
            }
            i2 += parent.getNativeOffsetForChild(reactShadowNode);
            reactShadowNode = parent;
        }
        return new NodeIndexPair(reactShadowNode, i2);
    }

    public void handleCreateView(ReactShadowNode reactShadowNode, ThemedReactContext themedReactContext, @Nullable ReactStylesDiffMap reactStylesDiffMap) {
        boolean z = reactShadowNode.getViewClass().equals("RCTView") && isLayoutOnlyAndCollapsable(reactStylesDiffMap);
        reactShadowNode.setIsLayoutOnly(z);
        if (z) {
            return;
        }
        this.mUIViewOperationQueue.enqueueCreateView(themedReactContext, reactShadowNode.getReactTag(), reactShadowNode.getViewClass(), reactStylesDiffMap);
    }

    public void handleManageChildren(ReactShadowNode reactShadowNode, int[] iArr, int[] iArr2, ViewAtIndex[] viewAtIndexArr, int[] iArr3) {
        boolean z;
        for (int i2 : iArr2) {
            int i3 = 0;
            while (true) {
                if (i3 >= iArr3.length) {
                    z = false;
                    break;
                } else if (iArr3[i3] == i2) {
                    z = true;
                    break;
                } else {
                    i3++;
                }
            }
            removeNodeFromParent(this.mShadowNodeRegistry.getNode(i2), z);
        }
        for (ViewAtIndex viewAtIndex : viewAtIndexArr) {
            addNodeToNode(reactShadowNode, this.mShadowNodeRegistry.getNode(viewAtIndex.mTag), viewAtIndex.mIndex);
        }
    }

    public void handleSetChildren(ReactShadowNode reactShadowNode, ReadableArray readableArray) {
        for (int i2 = 0; i2 < readableArray.size(); i2++) {
            addNodeToNode(reactShadowNode, this.mShadowNodeRegistry.getNode(readableArray.getInt(i2)), i2);
        }
    }

    public void handleUpdateLayout(ReactShadowNode reactShadowNode) {
        applyLayoutBase(reactShadowNode);
    }

    public void handleUpdateView(ReactShadowNode reactShadowNode, String str, ReactStylesDiffMap reactStylesDiffMap) {
        if (reactShadowNode.isLayoutOnly() && !isLayoutOnlyAndCollapsable(reactStylesDiffMap)) {
            transitionLayoutOnlyViewToNativeView(reactShadowNode, reactStylesDiffMap);
        } else if (reactShadowNode.isLayoutOnly()) {
        } else {
            this.mUIViewOperationQueue.enqueueUpdateProperties(reactShadowNode.getReactTag(), str, reactStylesDiffMap);
        }
    }

    public void onBatchComplete() {
        this.mTagsWithLayoutVisited.clear();
    }
}
