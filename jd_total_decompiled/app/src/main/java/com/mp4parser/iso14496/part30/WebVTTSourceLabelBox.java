package com.mp4parser.iso14496.part30;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.Utf8;
import com.googlecode.mp4parser.AbstractBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: classes14.dex */
public class WebVTTSourceLabelBox extends AbstractBox {
    public static final String TYPE = "vlab";
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final /* synthetic */ JoinPoint.StaticPart ajc$tjp_1 = null;
    String sourceLabel;

    static {
        ajc$preClinit();
    }

    public WebVTTSourceLabelBox() {
        super(TYPE);
        this.sourceLabel = "";
    }

    private static /* synthetic */ void ajc$preClinit() {
        Factory factory = new Factory("WebVTTSourceLabelBox.java", WebVTTSourceLabelBox.class);
        ajc$tjp_0 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "getSourceLabel", "com.mp4parser.iso14496.part30.WebVTTSourceLabelBox", "", "", "", "java.lang.String"), 37);
        ajc$tjp_1 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig("1", "setSourceLabel", "com.mp4parser.iso14496.part30.WebVTTSourceLabelBox", "java.lang.String", "sourceLabel", "", "void"), 41);
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    protected void _parseDetails(ByteBuffer byteBuffer) {
        this.sourceLabel = IsoTypeReader.readString(byteBuffer, byteBuffer.remaining());
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    protected void getContent(ByteBuffer byteBuffer) {
        byteBuffer.put(Utf8.convert(this.sourceLabel));
    }

    @Override // com.googlecode.mp4parser.AbstractBox
    protected long getContentSize() {
        return Utf8.utf8StringLengthInBytes(this.sourceLabel);
    }

    public String getSourceLabel() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.sourceLabel;
    }

    public void setSourceLabel(String str) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, str));
        this.sourceLabel = str;
    }
}
