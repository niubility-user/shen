package com.coremedia.iso.boxes;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeReaderVariable;
import com.coremedia.iso.IsoTypeWriter;
import com.coremedia.iso.IsoTypeWriterVariable;
import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.platform.business.personal.R2;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.runtime.internal.Conversions;
import org.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: classes.dex */
public class ItemLocationBox extends AbstractFullBox {
    public static final String TYPE = "iloc";
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_10 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_11 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_5 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_6 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_7 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_8 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_9 = null;
    public int baseOffsetSize;
    public int indexSize;
    public List<Item> items;
    public int lengthSize;
    public int offsetSize;

    static {
        ajc$preClinit();
    }

    public ItemLocationBox() {
        super(TYPE);
        this.offsetSize = 8;
        this.lengthSize = 8;
        this.baseOffsetSize = 8;
        this.indexSize = 0;
        this.items = new LinkedList();
    }

    private static /* synthetic */ void ajc$preClinit() {
        Factory factory = new Factory("ItemLocationBox.java", ItemLocationBox.class);
        ajc$tjp_0 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "getOffsetSize", "com.coremedia.iso.boxes.ItemLocationBox", "", "", "", "int"), 119);
        ajc$tjp_1 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "setOffsetSize", "com.coremedia.iso.boxes.ItemLocationBox", "int", "offsetSize", "", "void"), 123);
        ajc$tjp_10 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "createItem", "com.coremedia.iso.boxes.ItemLocationBox", "int:int:int:long:java.util.List", "itemId:constructionMethod:dataReferenceIndex:baseOffset:extents", "", "com.coremedia.iso.boxes.ItemLocationBox$Item"), 160);
        ajc$tjp_11 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "createExtent", "com.coremedia.iso.boxes.ItemLocationBox", "long:long:long", "extentOffset:extentLength:extentIndex", "", "com.coremedia.iso.boxes.ItemLocationBox$Extent"), 285);
        ajc$tjp_2 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "getLengthSize", "com.coremedia.iso.boxes.ItemLocationBox", "", "", "", "int"), 127);
        ajc$tjp_3 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "setLengthSize", "com.coremedia.iso.boxes.ItemLocationBox", "int", "lengthSize", "", "void"), 131);
        ajc$tjp_4 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "getBaseOffsetSize", "com.coremedia.iso.boxes.ItemLocationBox", "", "", "", "int"), 135);
        ajc$tjp_5 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "setBaseOffsetSize", "com.coremedia.iso.boxes.ItemLocationBox", "int", "baseOffsetSize", "", "void"), R2.anim.live_pop_rotate_amin);
        ajc$tjp_6 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "getIndexSize", "com.coremedia.iso.boxes.ItemLocationBox", "", "", "", "int"), R2.anim.manto_translate_dialog_out);
        ajc$tjp_7 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "setIndexSize", "com.coremedia.iso.boxes.ItemLocationBox", "int", "indexSize", "", "void"), R2.anim.miaosha_dropdown_out);
        ajc$tjp_8 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "getItems", "com.coremedia.iso.boxes.ItemLocationBox", "", "", "", "java.util.List"), 151);
        ajc$tjp_9 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "setItems", "com.coremedia.iso.boxes.ItemLocationBox", "java.util.List", CartConstant.KEY_ITEMS, "", "void"), 155);
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        parseVersionAndFlags(byteBuffer);
        int readUInt8 = IsoTypeReader.readUInt8(byteBuffer);
        this.offsetSize = readUInt8 >>> 4;
        this.lengthSize = readUInt8 & 15;
        int readUInt82 = IsoTypeReader.readUInt8(byteBuffer);
        this.baseOffsetSize = readUInt82 >>> 4;
        if (getVersion() == 1) {
            this.indexSize = readUInt82 & 15;
        }
        int readUInt16 = IsoTypeReader.readUInt16(byteBuffer);
        for (int i2 = 0; i2 < readUInt16; i2++) {
            this.items.add(new Item(byteBuffer));
        }
    }

    public Extent createExtent(long j2, long j3, long j4) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_11, (Object) this, (Object) this, new Object[]{Conversions.longObject(j2), Conversions.longObject(j3), Conversions.longObject(j4)}));
        return new Extent(j2, j3, j4);
    }

    public Item createItem(int i2, int i3, int i4, long j2, List<Extent> list) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_10, (Object) this, (Object) this, new Object[]{Conversions.intObject(i2), Conversions.intObject(i3), Conversions.intObject(i4), Conversions.longObject(j2), list}));
        return new Item(i2, i3, i4, j2, list);
    }

    public int getBaseOffsetSize() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this));
        return this.baseOffsetSize;
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    protected void getContent(ByteBuffer byteBuffer) {
        writeVersionAndFlags(byteBuffer);
        IsoTypeWriter.writeUInt8(byteBuffer, (this.offsetSize << 4) | this.lengthSize);
        if (getVersion() == 1) {
            IsoTypeWriter.writeUInt8(byteBuffer, (this.baseOffsetSize << 4) | this.indexSize);
        } else {
            IsoTypeWriter.writeUInt8(byteBuffer, this.baseOffsetSize << 4);
        }
        IsoTypeWriter.writeUInt16(byteBuffer, this.items.size());
        Iterator<Item> it = this.items.iterator();
        while (it.hasNext()) {
            it.next().getContent(byteBuffer);
        }
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    protected long getContentSize() {
        long j2 = 8;
        while (this.items.iterator().hasNext()) {
            j2 += r0.next().getSize();
        }
        return j2;
    }

    public int getIndexSize() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_6, this, this));
        return this.indexSize;
    }

    public List<Item> getItems() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_8, this, this));
        return this.items;
    }

    public int getLengthSize() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.lengthSize;
    }

    public int getOffsetSize() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.offsetSize;
    }

    public void setBaseOffsetSize(int i2) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_5, this, this, Conversions.intObject(i2)));
        this.baseOffsetSize = i2;
    }

    public void setIndexSize(int i2) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_7, this, this, Conversions.intObject(i2)));
        this.indexSize = i2;
    }

    public void setItems(List<Item> list) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_9, this, this, list));
        this.items = list;
    }

    public void setLengthSize(int i2) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this, Conversions.intObject(i2)));
        this.lengthSize = i2;
    }

    public void setOffsetSize(int i2) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, Conversions.intObject(i2)));
        this.offsetSize = i2;
    }

    Extent createExtent(ByteBuffer byteBuffer) {
        return new Extent(byteBuffer);
    }

    Item createItem(ByteBuffer byteBuffer) {
        return new Item(byteBuffer);
    }

    /* loaded from: classes.dex */
    public class Extent {
        public long extentIndex;
        public long extentLength;
        public long extentOffset;

        public Extent(long j2, long j3, long j4) {
            this.extentOffset = j2;
            this.extentLength = j3;
            this.extentIndex = j4;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Extent extent = (Extent) obj;
            return this.extentIndex == extent.extentIndex && this.extentLength == extent.extentLength && this.extentOffset == extent.extentOffset;
        }

        public void getContent(ByteBuffer byteBuffer) {
            int i2;
            if (ItemLocationBox.this.getVersion() == 1 && (i2 = ItemLocationBox.this.indexSize) > 0) {
                IsoTypeWriterVariable.write(this.extentIndex, byteBuffer, i2);
            }
            IsoTypeWriterVariable.write(this.extentOffset, byteBuffer, ItemLocationBox.this.offsetSize);
            IsoTypeWriterVariable.write(this.extentLength, byteBuffer, ItemLocationBox.this.lengthSize);
        }

        public int getSize() {
            ItemLocationBox itemLocationBox = ItemLocationBox.this;
            int i2 = itemLocationBox.indexSize;
            if (i2 <= 0) {
                i2 = 0;
            }
            return i2 + itemLocationBox.offsetSize + itemLocationBox.lengthSize;
        }

        public int hashCode() {
            long j2 = this.extentOffset;
            long j3 = this.extentLength;
            long j4 = this.extentIndex;
            return (((((int) (j2 ^ (j2 >>> 32))) * 31) + ((int) (j3 ^ (j3 >>> 32)))) * 31) + ((int) (j4 ^ (j4 >>> 32)));
        }

        public String toString() {
            return "Extent{extentOffset=" + this.extentOffset + ", extentLength=" + this.extentLength + ", extentIndex=" + this.extentIndex + '}';
        }

        public Extent(ByteBuffer byteBuffer) {
            int i2;
            if (ItemLocationBox.this.getVersion() == 1 && (i2 = ItemLocationBox.this.indexSize) > 0) {
                this.extentIndex = IsoTypeReaderVariable.read(byteBuffer, i2);
            }
            this.extentOffset = IsoTypeReaderVariable.read(byteBuffer, ItemLocationBox.this.offsetSize);
            this.extentLength = IsoTypeReaderVariable.read(byteBuffer, ItemLocationBox.this.lengthSize);
        }
    }

    /* loaded from: classes.dex */
    public class Item {
        public long baseOffset;
        public int constructionMethod;
        public int dataReferenceIndex;
        public List<Extent> extents;
        public int itemId;

        public Item(ByteBuffer byteBuffer) {
            this.extents = new LinkedList();
            this.itemId = IsoTypeReader.readUInt16(byteBuffer);
            if (ItemLocationBox.this.getVersion() == 1) {
                this.constructionMethod = IsoTypeReader.readUInt16(byteBuffer) & 15;
            }
            this.dataReferenceIndex = IsoTypeReader.readUInt16(byteBuffer);
            int i2 = ItemLocationBox.this.baseOffsetSize;
            if (i2 > 0) {
                this.baseOffset = IsoTypeReaderVariable.read(byteBuffer, i2);
            } else {
                this.baseOffset = 0L;
            }
            int readUInt16 = IsoTypeReader.readUInt16(byteBuffer);
            for (int i3 = 0; i3 < readUInt16; i3++) {
                this.extents.add(new Extent(byteBuffer));
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Item item = (Item) obj;
            if (this.baseOffset == item.baseOffset && this.constructionMethod == item.constructionMethod && this.dataReferenceIndex == item.dataReferenceIndex && this.itemId == item.itemId) {
                List<Extent> list = this.extents;
                List<Extent> list2 = item.extents;
                return list == null ? list2 == null : list.equals(list2);
            }
            return false;
        }

        public void getContent(ByteBuffer byteBuffer) {
            IsoTypeWriter.writeUInt16(byteBuffer, this.itemId);
            if (ItemLocationBox.this.getVersion() == 1) {
                IsoTypeWriter.writeUInt16(byteBuffer, this.constructionMethod);
            }
            IsoTypeWriter.writeUInt16(byteBuffer, this.dataReferenceIndex);
            int i2 = ItemLocationBox.this.baseOffsetSize;
            if (i2 > 0) {
                IsoTypeWriterVariable.write(this.baseOffset, byteBuffer, i2);
            }
            IsoTypeWriter.writeUInt16(byteBuffer, this.extents.size());
            Iterator<Extent> it = this.extents.iterator();
            while (it.hasNext()) {
                it.next().getContent(byteBuffer);
            }
        }

        public int getSize() {
            int i2 = (ItemLocationBox.this.getVersion() == 1 ? 4 : 2) + 2 + ItemLocationBox.this.baseOffsetSize + 2;
            Iterator<Extent> it = this.extents.iterator();
            while (it.hasNext()) {
                i2 += it.next().getSize();
            }
            return i2;
        }

        public int hashCode() {
            long j2 = this.baseOffset;
            int i2 = ((((((this.itemId * 31) + this.constructionMethod) * 31) + this.dataReferenceIndex) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31;
            List<Extent> list = this.extents;
            return i2 + (list != null ? list.hashCode() : 0);
        }

        public void setBaseOffset(long j2) {
            this.baseOffset = j2;
        }

        public String toString() {
            return "Item{baseOffset=" + this.baseOffset + ", itemId=" + this.itemId + ", constructionMethod=" + this.constructionMethod + ", dataReferenceIndex=" + this.dataReferenceIndex + ", extents=" + this.extents + '}';
        }

        public Item(int i2, int i3, int i4, long j2, List<Extent> list) {
            this.extents = new LinkedList();
            this.itemId = i2;
            this.constructionMethod = i3;
            this.dataReferenceIndex = i4;
            this.baseOffset = j2;
            this.extents = list;
        }
    }
}
