package com.googlecode.mp4parser.boxes.mp4.objectdescriptors;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Descriptor(tags = {3})
/* loaded from: classes12.dex */
public class ESDescriptor extends BaseDescriptor {
    private static Logger log = Logger.getLogger(ESDescriptor.class.getName());
    int URLFlag;
    String URLString;
    DecoderConfigDescriptor decoderConfigDescriptor;
    int dependsOnEsId;
    int esId;
    int oCREsId;
    int oCRstreamFlag;
    int remoteODFlag;
    SLConfigDescriptor slConfigDescriptor;
    int streamDependenceFlag;
    int streamPriority;
    int URLLength = 0;
    List<BaseDescriptor> otherDescriptors = new ArrayList();

    public ESDescriptor() {
        this.tag = 3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ESDescriptor eSDescriptor = (ESDescriptor) obj;
        if (this.URLFlag == eSDescriptor.URLFlag && this.URLLength == eSDescriptor.URLLength && this.dependsOnEsId == eSDescriptor.dependsOnEsId && this.esId == eSDescriptor.esId && this.oCREsId == eSDescriptor.oCREsId && this.oCRstreamFlag == eSDescriptor.oCRstreamFlag && this.remoteODFlag == eSDescriptor.remoteODFlag && this.streamDependenceFlag == eSDescriptor.streamDependenceFlag && this.streamPriority == eSDescriptor.streamPriority) {
            String str = this.URLString;
            if (str == null ? eSDescriptor.URLString == null : str.equals(eSDescriptor.URLString)) {
                DecoderConfigDescriptor decoderConfigDescriptor = this.decoderConfigDescriptor;
                if (decoderConfigDescriptor == null ? eSDescriptor.decoderConfigDescriptor == null : decoderConfigDescriptor.equals(eSDescriptor.decoderConfigDescriptor)) {
                    List<BaseDescriptor> list = this.otherDescriptors;
                    if (list == null ? eSDescriptor.otherDescriptors == null : list.equals(eSDescriptor.otherDescriptors)) {
                        SLConfigDescriptor sLConfigDescriptor = this.slConfigDescriptor;
                        SLConfigDescriptor sLConfigDescriptor2 = eSDescriptor.slConfigDescriptor;
                        return sLConfigDescriptor == null ? sLConfigDescriptor2 == null : sLConfigDescriptor.equals(sLConfigDescriptor2);
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    @Override // com.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor
    int getContentSize() {
        int i2 = this.streamDependenceFlag > 0 ? 5 : 3;
        if (this.URLFlag > 0) {
            i2 += this.URLLength + 1;
        }
        if (this.oCRstreamFlag > 0) {
            i2 += 2;
        }
        int size = i2 + this.decoderConfigDescriptor.getSize() + this.slConfigDescriptor.getSize();
        if (this.otherDescriptors.size() <= 0) {
            return size;
        }
        throw new RuntimeException(" Doesn't handle other descriptors yet");
    }

    public DecoderConfigDescriptor getDecoderConfigDescriptor() {
        return this.decoderConfigDescriptor;
    }

    public int getDependsOnEsId() {
        return this.dependsOnEsId;
    }

    public int getEsId() {
        return this.esId;
    }

    public List<BaseDescriptor> getOtherDescriptors() {
        return this.otherDescriptors;
    }

    public int getRemoteODFlag() {
        return this.remoteODFlag;
    }

    public SLConfigDescriptor getSlConfigDescriptor() {
        return this.slConfigDescriptor;
    }

    public int getStreamDependenceFlag() {
        return this.streamDependenceFlag;
    }

    public int getStreamPriority() {
        return this.streamPriority;
    }

    public int getURLFlag() {
        return this.URLFlag;
    }

    public int getURLLength() {
        return this.URLLength;
    }

    public String getURLString() {
        return this.URLString;
    }

    public int getoCREsId() {
        return this.oCREsId;
    }

    public int getoCRstreamFlag() {
        return this.oCRstreamFlag;
    }

    public int hashCode() {
        int i2 = ((((((((((this.esId * 31) + this.streamDependenceFlag) * 31) + this.URLFlag) * 31) + this.oCRstreamFlag) * 31) + this.streamPriority) * 31) + this.URLLength) * 31;
        String str = this.URLString;
        int hashCode = (((((((i2 + (str != null ? str.hashCode() : 0)) * 31) + this.remoteODFlag) * 31) + this.dependsOnEsId) * 31) + this.oCREsId) * 31;
        DecoderConfigDescriptor decoderConfigDescriptor = this.decoderConfigDescriptor;
        int hashCode2 = (hashCode + (decoderConfigDescriptor != null ? decoderConfigDescriptor.hashCode() : 0)) * 31;
        SLConfigDescriptor sLConfigDescriptor = this.slConfigDescriptor;
        int hashCode3 = (hashCode2 + (sLConfigDescriptor != null ? sLConfigDescriptor.hashCode() : 0)) * 31;
        List<BaseDescriptor> list = this.otherDescriptors;
        return hashCode3 + (list != null ? list.hashCode() : 0);
    }

    @Override // com.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor
    public void parseDetail(ByteBuffer byteBuffer) throws IOException {
        this.esId = IsoTypeReader.readUInt16(byteBuffer);
        int readUInt8 = IsoTypeReader.readUInt8(byteBuffer);
        int i2 = readUInt8 >>> 7;
        this.streamDependenceFlag = i2;
        this.URLFlag = (readUInt8 >>> 6) & 1;
        this.oCRstreamFlag = (readUInt8 >>> 5) & 1;
        this.streamPriority = readUInt8 & 31;
        if (i2 == 1) {
            this.dependsOnEsId = IsoTypeReader.readUInt16(byteBuffer);
        }
        if (this.URLFlag == 1) {
            int readUInt82 = IsoTypeReader.readUInt8(byteBuffer);
            this.URLLength = readUInt82;
            this.URLString = IsoTypeReader.readString(byteBuffer, readUInt82);
        }
        if (this.oCRstreamFlag == 1) {
            this.oCREsId = IsoTypeReader.readUInt16(byteBuffer);
        }
        while (byteBuffer.remaining() > 1) {
            BaseDescriptor createFrom = ObjectDescriptorFactory.createFrom(-1, byteBuffer);
            if (createFrom instanceof DecoderConfigDescriptor) {
                this.decoderConfigDescriptor = (DecoderConfigDescriptor) createFrom;
            } else if (createFrom instanceof SLConfigDescriptor) {
                this.slConfigDescriptor = (SLConfigDescriptor) createFrom;
            } else {
                this.otherDescriptors.add(createFrom);
            }
        }
    }

    @Override // com.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor
    public ByteBuffer serialize() {
        ByteBuffer wrap = ByteBuffer.wrap(new byte[getSize()]);
        IsoTypeWriter.writeUInt8(wrap, 3);
        writeSize(wrap, getContentSize());
        IsoTypeWriter.writeUInt16(wrap, this.esId);
        IsoTypeWriter.writeUInt8(wrap, (this.streamDependenceFlag << 7) | (this.URLFlag << 6) | (this.oCRstreamFlag << 5) | (this.streamPriority & 31));
        if (this.streamDependenceFlag > 0) {
            IsoTypeWriter.writeUInt16(wrap, this.dependsOnEsId);
        }
        if (this.URLFlag > 0) {
            IsoTypeWriter.writeUInt8(wrap, this.URLLength);
            IsoTypeWriter.writeUtf8String(wrap, this.URLString);
        }
        if (this.oCRstreamFlag > 0) {
            IsoTypeWriter.writeUInt16(wrap, this.oCREsId);
        }
        ByteBuffer serialize = this.decoderConfigDescriptor.serialize();
        ByteBuffer serialize2 = this.slConfigDescriptor.serialize();
        wrap.put(serialize.array());
        wrap.put(serialize2.array());
        return wrap;
    }

    public void setDecoderConfigDescriptor(DecoderConfigDescriptor decoderConfigDescriptor) {
        this.decoderConfigDescriptor = decoderConfigDescriptor;
    }

    public void setDependsOnEsId(int i2) {
        this.dependsOnEsId = i2;
    }

    public void setEsId(int i2) {
        this.esId = i2;
    }

    public void setRemoteODFlag(int i2) {
        this.remoteODFlag = i2;
    }

    public void setSlConfigDescriptor(SLConfigDescriptor sLConfigDescriptor) {
        this.slConfigDescriptor = sLConfigDescriptor;
    }

    public void setStreamDependenceFlag(int i2) {
        this.streamDependenceFlag = i2;
    }

    public void setStreamPriority(int i2) {
        this.streamPriority = i2;
    }

    public void setURLFlag(int i2) {
        this.URLFlag = i2;
    }

    public void setURLLength(int i2) {
        this.URLLength = i2;
    }

    public void setURLString(String str) {
        this.URLString = str;
    }

    public void setoCREsId(int i2) {
        this.oCREsId = i2;
    }

    public void setoCRstreamFlag(int i2) {
        this.oCRstreamFlag = i2;
    }

    @Override // com.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor
    public String toString() {
        return "ESDescriptor{esId=" + this.esId + ", streamDependenceFlag=" + this.streamDependenceFlag + ", URLFlag=" + this.URLFlag + ", oCRstreamFlag=" + this.oCRstreamFlag + ", streamPriority=" + this.streamPriority + ", URLLength=" + this.URLLength + ", URLString='" + this.URLString + "', remoteODFlag=" + this.remoteODFlag + ", dependsOnEsId=" + this.dependsOnEsId + ", oCREsId=" + this.oCREsId + ", decoderConfigDescriptor=" + this.decoderConfigDescriptor + ", slConfigDescriptor=" + this.slConfigDescriptor + '}';
    }
}
