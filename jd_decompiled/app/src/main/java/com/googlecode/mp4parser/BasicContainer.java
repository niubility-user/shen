package com.googlecode.mp4parser;

import com.coremedia.iso.BoxParser;
import com.coremedia.iso.boxes.Box;
import com.coremedia.iso.boxes.Container;
import com.googlecode.mp4parser.util.CastUtils;
import com.googlecode.mp4parser.util.LazyList;
import com.googlecode.mp4parser.util.Logger;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/* loaded from: classes12.dex */
public class BasicContainer implements Container, Iterator<Box>, Closeable {
    private static final Box EOF = new AbstractBox("eof ") { // from class: com.googlecode.mp4parser.BasicContainer.1
        @Override // com.googlecode.mp4parser.AbstractBox
        protected void _parseDetails(ByteBuffer byteBuffer) {
        }

        @Override // com.googlecode.mp4parser.AbstractBox
        protected void getContent(ByteBuffer byteBuffer) {
        }

        @Override // com.googlecode.mp4parser.AbstractBox
        protected long getContentSize() {
            return 0L;
        }
    };
    private static Logger LOG = Logger.getLogger(BasicContainer.class);
    protected BoxParser boxParser;
    protected DataSource dataSource;
    Box lookahead = null;
    long parsePosition = 0;
    long startPosition = 0;
    long endPosition = 0;
    private List<Box> boxes = new ArrayList();

    public void addBox(Box box) {
        if (box != null) {
            this.boxes = new ArrayList(getBoxes());
            box.setParent(this);
            this.boxes.add(box);
        }
    }

    public void close() throws IOException {
        this.dataSource.close();
    }

    @Override // com.coremedia.iso.boxes.Container
    public List<Box> getBoxes() {
        if (this.dataSource != null && this.lookahead != EOF) {
            return new LazyList(this.boxes, this);
        }
        return this.boxes;
    }

    @Override // com.coremedia.iso.boxes.Container
    public ByteBuffer getByteBuffer(long j2, long j3) throws IOException {
        ByteBuffer map;
        DataSource dataSource = this.dataSource;
        if (dataSource != null) {
            synchronized (dataSource) {
                map = this.dataSource.map(this.startPosition + j2, j3);
            }
            return map;
        }
        ByteBuffer allocate = ByteBuffer.allocate(CastUtils.l2i(j3));
        long j4 = j2 + j3;
        long j5 = 0;
        for (Box box : this.boxes) {
            long size = box.getSize() + j5;
            if (size > j2 && j5 < j4) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                WritableByteChannel newChannel = Channels.newChannel(byteArrayOutputStream);
                box.getBox(newChannel);
                newChannel.close();
                if (j5 >= j2 && size <= j4) {
                    allocate.put(byteArrayOutputStream.toByteArray());
                } else if (j5 < j2 && size > j4) {
                    long j6 = j2 - j5;
                    allocate.put(byteArrayOutputStream.toByteArray(), CastUtils.l2i(j6), CastUtils.l2i((box.getSize() - j6) - (size - j4)));
                } else if (j5 < j2 && size <= j4) {
                    long j7 = j2 - j5;
                    allocate.put(byteArrayOutputStream.toByteArray(), CastUtils.l2i(j7), CastUtils.l2i(box.getSize() - j7));
                } else if (j5 >= j2 && size > j4) {
                    allocate.put(byteArrayOutputStream.toByteArray(), 0, CastUtils.l2i(box.getSize() - (size - j4)));
                }
            }
            j5 = size;
        }
        return (ByteBuffer) allocate.rewind();
    }

    public long getContainerSize() {
        long j2 = 0;
        for (int i2 = 0; i2 < getBoxes().size(); i2++) {
            j2 += this.boxes.get(i2).getSize();
        }
        return j2;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        Box box = this.lookahead;
        if (box == EOF) {
            return false;
        }
        if (box != null) {
            return true;
        }
        try {
            this.lookahead = next();
            return true;
        } catch (NoSuchElementException unused) {
            this.lookahead = EOF;
            return false;
        }
    }

    public void initContainer(DataSource dataSource, long j2, BoxParser boxParser) throws IOException {
        this.dataSource = dataSource;
        long position = dataSource.position();
        this.startPosition = position;
        this.parsePosition = position;
        dataSource.position(dataSource.position() + j2);
        this.endPosition = dataSource.position();
        this.boxParser = boxParser;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override // com.coremedia.iso.boxes.Container
    public void setBoxes(List<Box> list) {
        this.boxes = new ArrayList(list);
        this.lookahead = EOF;
        this.dataSource = null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("[");
        for (int i2 = 0; i2 < this.boxes.size(); i2++) {
            if (i2 > 0) {
                sb.append(";");
            }
            sb.append(this.boxes.get(i2).toString());
        }
        sb.append("]");
        return sb.toString();
    }

    @Override // com.coremedia.iso.boxes.Container
    public final void writeContainer(WritableByteChannel writableByteChannel) throws IOException {
        Iterator<Box> it = getBoxes().iterator();
        while (it.hasNext()) {
            it.next().getBox(writableByteChannel);
        }
    }

    @Override // java.util.Iterator
    public Box next() {
        Box parseBox;
        Box box = this.lookahead;
        if (box != null && box != EOF) {
            this.lookahead = null;
            return box;
        }
        DataSource dataSource = this.dataSource;
        if (dataSource != null && this.parsePosition < this.endPosition) {
            try {
                synchronized (dataSource) {
                    this.dataSource.position(this.parsePosition);
                    parseBox = this.boxParser.parseBox(this.dataSource, this);
                    this.parsePosition = this.dataSource.position();
                }
                return parseBox;
            } catch (EOFException unused) {
                throw new NoSuchElementException();
            } catch (IOException unused2) {
                throw new NoSuchElementException();
            }
        }
        this.lookahead = EOF;
        throw new NoSuchElementException();
    }

    @Override // com.coremedia.iso.boxes.Container
    public <T extends Box> List<T> getBoxes(Class<T> cls) {
        List<Box> boxes = getBoxes();
        ArrayList arrayList = null;
        Box box = null;
        for (int i2 = 0; i2 < boxes.size(); i2++) {
            Box box2 = boxes.get(i2);
            if (cls.isInstance(box2)) {
                if (box == null) {
                    box = box2;
                } else {
                    if (arrayList == null) {
                        arrayList = new ArrayList(2);
                        arrayList.add(box);
                    }
                    arrayList.add(box2);
                }
            }
        }
        if (arrayList != null) {
            return arrayList;
        }
        if (box != null) {
            return Collections.singletonList(box);
        }
        return Collections.emptyList();
    }

    @Override // com.coremedia.iso.boxes.Container
    public <T extends Box> List<T> getBoxes(Class<T> cls, boolean z) {
        ArrayList arrayList = new ArrayList(2);
        List<Box> boxes = getBoxes();
        for (int i2 = 0; i2 < boxes.size(); i2++) {
            Box box = boxes.get(i2);
            if (cls.isInstance(box)) {
                arrayList.add(box);
            }
            if (z && (box instanceof Container)) {
                arrayList.addAll(((Container) box).getBoxes(cls, z));
            }
        }
        return arrayList;
    }
}
