package androidx.recyclerview.widget;

import androidx.recyclerview.widget.AdapterHelper;
import java.util.List;

/* loaded from: classes.dex */
public class OpReorderer {
    final Callback mCallback;

    /* loaded from: classes.dex */
    public interface Callback {
        AdapterHelper.UpdateOp obtainUpdateOp(int i2, int i3, int i4, Object obj);

        void recycleUpdateOp(AdapterHelper.UpdateOp updateOp);
    }

    public OpReorderer(Callback callback) {
        this.mCallback = callback;
    }

    private int getLastMoveOutOfOrder(List<AdapterHelper.UpdateOp> list) {
        boolean z = false;
        for (int size = list.size() - 1; size >= 0; size--) {
            if (list.get(size).cmd != 8) {
                z = true;
            } else if (z) {
                return size;
            }
        }
        return -1;
    }

    private void swapMoveAdd(List<AdapterHelper.UpdateOp> list, int i2, AdapterHelper.UpdateOp updateOp, int i3, AdapterHelper.UpdateOp updateOp2) {
        int i4 = updateOp.itemCount;
        int i5 = updateOp2.positionStart;
        int i6 = i4 < i5 ? -1 : 0;
        int i7 = updateOp.positionStart;
        if (i7 < i5) {
            i6++;
        }
        if (i5 <= i7) {
            updateOp.positionStart = i7 + updateOp2.itemCount;
        }
        int i8 = updateOp2.positionStart;
        if (i8 <= i4) {
            updateOp.itemCount = i4 + updateOp2.itemCount;
        }
        updateOp2.positionStart = i8 + i6;
        list.set(i2, updateOp2);
        list.set(i3, updateOp);
    }

    private void swapMoveOp(List<AdapterHelper.UpdateOp> list, int i2, int i3) {
        AdapterHelper.UpdateOp updateOp = list.get(i2);
        AdapterHelper.UpdateOp updateOp2 = list.get(i3);
        int i4 = updateOp2.cmd;
        if (i4 == 1) {
            swapMoveAdd(list, i2, updateOp, i3, updateOp2);
        } else if (i4 == 2) {
            swapMoveRemove(list, i2, updateOp, i3, updateOp2);
        } else if (i4 != 4) {
        } else {
            swapMoveUpdate(list, i2, updateOp, i3, updateOp2);
        }
    }

    public void reorderOps(List<AdapterHelper.UpdateOp> list) {
        while (true) {
            int lastMoveOutOfOrder = getLastMoveOutOfOrder(list);
            if (lastMoveOutOfOrder == -1) {
                return;
            }
            swapMoveOp(list, lastMoveOutOfOrder, lastMoveOutOfOrder + 1);
        }
    }

    void swapMoveRemove(List<AdapterHelper.UpdateOp> list, int i2, AdapterHelper.UpdateOp updateOp, int i3, AdapterHelper.UpdateOp updateOp2) {
        boolean z;
        int i4 = updateOp.positionStart;
        int i5 = updateOp.itemCount;
        boolean z2 = false;
        if (i4 < i5) {
            if (updateOp2.positionStart == i4 && updateOp2.itemCount == i5 - i4) {
                z = false;
                z2 = true;
            } else {
                z = false;
            }
        } else if (updateOp2.positionStart == i5 + 1 && updateOp2.itemCount == i4 - i5) {
            z = true;
            z2 = true;
        } else {
            z = true;
        }
        int i6 = updateOp2.positionStart;
        if (i5 < i6) {
            updateOp2.positionStart = i6 - 1;
        } else {
            int i7 = updateOp2.itemCount;
            if (i5 < i6 + i7) {
                updateOp2.itemCount = i7 - 1;
                updateOp.cmd = 2;
                updateOp.itemCount = 1;
                if (updateOp2.itemCount == 0) {
                    list.remove(i3);
                    this.mCallback.recycleUpdateOp(updateOp2);
                    return;
                }
                return;
            }
        }
        int i8 = updateOp.positionStart;
        int i9 = updateOp2.positionStart;
        AdapterHelper.UpdateOp updateOp3 = null;
        if (i8 <= i9) {
            updateOp2.positionStart = i9 + 1;
        } else {
            int i10 = updateOp2.itemCount;
            if (i8 < i9 + i10) {
                updateOp3 = this.mCallback.obtainUpdateOp(2, i8 + 1, (i9 + i10) - i8, null);
                updateOp2.itemCount = updateOp.positionStart - updateOp2.positionStart;
            }
        }
        if (z2) {
            list.set(i2, updateOp2);
            list.remove(i3);
            this.mCallback.recycleUpdateOp(updateOp);
            return;
        }
        if (z) {
            if (updateOp3 != null) {
                int i11 = updateOp.positionStart;
                if (i11 > updateOp3.positionStart) {
                    updateOp.positionStart = i11 - updateOp3.itemCount;
                }
                int i12 = updateOp.itemCount;
                if (i12 > updateOp3.positionStart) {
                    updateOp.itemCount = i12 - updateOp3.itemCount;
                }
            }
            int i13 = updateOp.positionStart;
            if (i13 > updateOp2.positionStart) {
                updateOp.positionStart = i13 - updateOp2.itemCount;
            }
            int i14 = updateOp.itemCount;
            if (i14 > updateOp2.positionStart) {
                updateOp.itemCount = i14 - updateOp2.itemCount;
            }
        } else {
            if (updateOp3 != null) {
                int i15 = updateOp.positionStart;
                if (i15 >= updateOp3.positionStart) {
                    updateOp.positionStart = i15 - updateOp3.itemCount;
                }
                int i16 = updateOp.itemCount;
                if (i16 >= updateOp3.positionStart) {
                    updateOp.itemCount = i16 - updateOp3.itemCount;
                }
            }
            int i17 = updateOp.positionStart;
            if (i17 >= updateOp2.positionStart) {
                updateOp.positionStart = i17 - updateOp2.itemCount;
            }
            int i18 = updateOp.itemCount;
            if (i18 >= updateOp2.positionStart) {
                updateOp.itemCount = i18 - updateOp2.itemCount;
            }
        }
        list.set(i2, updateOp2);
        if (updateOp.positionStart != updateOp.itemCount) {
            list.set(i3, updateOp);
        } else {
            list.remove(i3);
        }
        if (updateOp3 != null) {
            list.add(i2, updateOp3);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:49:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void swapMoveUpdate(java.util.List<androidx.recyclerview.widget.AdapterHelper.UpdateOp> r9, int r10, androidx.recyclerview.widget.AdapterHelper.UpdateOp r11, int r12, androidx.recyclerview.widget.AdapterHelper.UpdateOp r13) {
        /*
            r8 = this;
            int r0 = r11.itemCount
            int r1 = r13.positionStart
            r2 = 4
            r3 = 0
            r4 = 1
            if (r0 >= r1) goto Ld
            int r1 = r1 - r4
            r13.positionStart = r1
            goto L20
        Ld:
            int r5 = r13.itemCount
            int r1 = r1 + r5
            if (r0 >= r1) goto L20
            int r5 = r5 - r4
            r13.itemCount = r5
            androidx.recyclerview.widget.OpReorderer$Callback r0 = r8.mCallback
            int r1 = r11.positionStart
            java.lang.Object r5 = r13.payload
            androidx.recyclerview.widget.AdapterHelper$UpdateOp r0 = r0.obtainUpdateOp(r2, r1, r4, r5)
            goto L21
        L20:
            r0 = r3
        L21:
            int r1 = r11.positionStart
            int r5 = r13.positionStart
            if (r1 > r5) goto L2b
            int r5 = r5 + r4
            r13.positionStart = r5
            goto L41
        L2b:
            int r6 = r13.itemCount
            int r7 = r5 + r6
            if (r1 >= r7) goto L41
            int r5 = r5 + r6
            int r5 = r5 - r1
            androidx.recyclerview.widget.OpReorderer$Callback r3 = r8.mCallback
            int r1 = r1 + r4
            java.lang.Object r4 = r13.payload
            androidx.recyclerview.widget.AdapterHelper$UpdateOp r3 = r3.obtainUpdateOp(r2, r1, r5, r4)
            int r1 = r13.itemCount
            int r1 = r1 - r5
            r13.itemCount = r1
        L41:
            r9.set(r12, r11)
            int r11 = r13.itemCount
            if (r11 <= 0) goto L4c
            r9.set(r10, r13)
            goto L54
        L4c:
            r9.remove(r10)
            androidx.recyclerview.widget.OpReorderer$Callback r11 = r8.mCallback
            r11.recycleUpdateOp(r13)
        L54:
            if (r0 == 0) goto L59
            r9.add(r10, r0)
        L59:
            if (r3 == 0) goto L5e
            r9.add(r10, r3)
        L5e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.OpReorderer.swapMoveUpdate(java.util.List, int, androidx.recyclerview.widget.AdapterHelper$UpdateOp, int, androidx.recyclerview.widget.AdapterHelper$UpdateOp):void");
    }
}
