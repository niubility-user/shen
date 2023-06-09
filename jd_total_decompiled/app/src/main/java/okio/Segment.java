package okio;

import javax.annotation.Nullable;

/* loaded from: classes11.dex */
public final class Segment {
    static final int SHARE_MINIMUM = 1024;
    static final int SIZE = 8192;
    final byte[] data;
    int limit;
    Segment next;
    boolean owner;
    int pos;
    Segment prev;
    boolean shared;

    public Segment() {
        this.data = new byte[8192];
        this.owner = true;
        this.shared = false;
    }

    public final void compact() {
        Segment segment = this.prev;
        if (segment != this) {
            if (segment.owner) {
                int i2 = this.limit - this.pos;
                if (i2 > (8192 - segment.limit) + (segment.shared ? 0 : segment.pos)) {
                    return;
                }
                writeTo(segment, i2);
                pop();
                SegmentPool.recycle(this);
                return;
            }
            return;
        }
        throw new IllegalStateException();
    }

    @Nullable
    public final Segment pop() {
        Segment segment = this.next;
        Segment segment2 = segment != this ? segment : null;
        Segment segment3 = this.prev;
        segment3.next = segment;
        this.next.prev = segment3;
        this.next = null;
        this.prev = null;
        return segment2;
    }

    public final Segment push(Segment segment) {
        segment.prev = this;
        segment.next = this.next;
        this.next.prev = segment;
        this.next = segment;
        return segment;
    }

    public final Segment sharedCopy() {
        this.shared = true;
        return new Segment(this.data, this.pos, this.limit, true, false);
    }

    public final Segment split(int i2) {
        Segment take;
        if (i2 > 0 && i2 <= this.limit - this.pos) {
            if (i2 >= 1024) {
                take = sharedCopy();
            } else {
                take = SegmentPool.take();
                System.arraycopy(this.data, this.pos, take.data, 0, i2);
            }
            take.limit = take.pos + i2;
            this.pos += i2;
            this.prev.push(take);
            return take;
        }
        throw new IllegalArgumentException();
    }

    public final Segment unsharedCopy() {
        return new Segment((byte[]) this.data.clone(), this.pos, this.limit, false, true);
    }

    public final void writeTo(Segment segment, int i2) {
        if (segment.owner) {
            int i3 = segment.limit;
            if (i3 + i2 > 8192) {
                if (!segment.shared) {
                    int i4 = segment.pos;
                    if ((i3 + i2) - i4 <= 8192) {
                        byte[] bArr = segment.data;
                        System.arraycopy(bArr, i4, bArr, 0, i3 - i4);
                        segment.limit -= segment.pos;
                        segment.pos = 0;
                    } else {
                        throw new IllegalArgumentException();
                    }
                } else {
                    throw new IllegalArgumentException();
                }
            }
            System.arraycopy(this.data, this.pos, segment.data, segment.limit, i2);
            segment.limit += i2;
            this.pos += i2;
            return;
        }
        throw new IllegalArgumentException();
    }

    public Segment(byte[] bArr, int i2, int i3, boolean z, boolean z2) {
        this.data = bArr;
        this.pos = i2;
        this.limit = i3;
        this.shared = z;
        this.owner = z2;
    }
}
