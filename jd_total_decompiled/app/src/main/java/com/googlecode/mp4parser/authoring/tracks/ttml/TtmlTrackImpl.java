package com.googlecode.mp4parser.authoring.tracks.ttml;

import cn.com.union.fido.common.MIMEType;
import com.coremedia.iso.boxes.SampleDescriptionBox;
import com.coremedia.iso.boxes.SubSampleInformationBox;
import com.googlecode.mp4parser.authoring.AbstractTrack;
import com.googlecode.mp4parser.authoring.Sample;
import com.googlecode.mp4parser.authoring.TrackMetaData;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.sdk.platform.business.personal.R2;
import com.mp4parser.iso14496.part30.XMLSubtitleSampleEntry;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/* loaded from: classes12.dex */
public class TtmlTrackImpl extends AbstractTrack {
    SampleDescriptionBox sampleDescriptionBox;
    private long[] sampleDurations;
    List<Sample> samples;
    SubSampleInformationBox subSampleInformationBox;
    TrackMetaData trackMetaData;
    XMLSubtitleSampleEntry xmlSubtitleSampleEntry;

    public TtmlTrackImpl(String str, List<Document> list) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException, URISyntaxException {
        super(str);
        this.trackMetaData = new TrackMetaData();
        this.sampleDescriptionBox = new SampleDescriptionBox();
        this.xmlSubtitleSampleEntry = new XMLSubtitleSampleEntry();
        this.samples = new ArrayList();
        this.subSampleInformationBox = new SubSampleInformationBox();
        extractLanguage(list);
        HashSet hashSet = new HashSet();
        this.sampleDurations = new long[list.size()];
        XPathFactory.newInstance().newXPath().setNamespaceContext(TtmlHelpers.NAMESPACE_CONTEXT);
        for (int i2 = 0; i2 < list.size(); i2++) {
            Document document = list.get(i2);
            SubSampleInformationBox.SubSampleEntry subSampleEntry = new SubSampleInformationBox.SubSampleEntry();
            this.subSampleInformationBox.getEntries().add(subSampleEntry);
            subSampleEntry.setSampleDelta(1L);
            this.sampleDurations[i2] = extractDuration(document);
            List<byte[]> extractImages = extractImages(document);
            hashSet.addAll(extractMimeTypes(document));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            TtmlHelpers.pretty(document, byteArrayOutputStream, 4);
            SubSampleInformationBox.SubSampleEntry.SubsampleEntry subsampleEntry = new SubSampleInformationBox.SubSampleEntry.SubsampleEntry();
            subsampleEntry.setSubsampleSize(byteArrayOutputStream.size());
            subSampleEntry.getSubsampleEntries().add(subsampleEntry);
            Iterator<byte[]> it = extractImages.iterator();
            while (it.hasNext()) {
                byteArrayOutputStream.write(it.next());
                SubSampleInformationBox.SubSampleEntry.SubsampleEntry subsampleEntry2 = new SubSampleInformationBox.SubSampleEntry.SubsampleEntry();
                subsampleEntry2.setSubsampleSize(r4.length);
                subSampleEntry.getSubsampleEntries().add(subsampleEntry2);
            }
            byteArrayOutputStream.toByteArray();
            this.samples.add(new Sample
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x00f6: INVOKE 
                  (wrap: java.util.List<com.googlecode.mp4parser.authoring.Sample> : 0x00ef: IGET (r9v0 'this' com.googlecode.mp4parser.authoring.tracks.ttml.TtmlTrackImpl A[IMMUTABLE_TYPE, THIS]) A[WRAPPED] (LINE:34) com.googlecode.mp4parser.authoring.tracks.ttml.TtmlTrackImpl.samples java.util.List)
                  (wrap: com.googlecode.mp4parser.authoring.Sample : 0x00f3: CONSTRUCTOR 
                  (r9v0 'this' com.googlecode.mp4parser.authoring.tracks.ttml.TtmlTrackImpl A[IMMUTABLE_TYPE, THIS])
                  (r2 I:byte[] A[DONT_GENERATE, DONT_INLINE, REMOVE])
                 A[MD:(com.googlecode.mp4parser.authoring.tracks.ttml.TtmlTrackImpl, byte[]):void (m), WRAPPED] call: com.googlecode.mp4parser.authoring.tracks.ttml.TtmlTrackImpl.1.<init>(com.googlecode.mp4parser.authoring.tracks.ttml.TtmlTrackImpl, byte[]):void type: CONSTRUCTOR)
                 type: INTERFACE call: java.util.List.add(java.lang.Object):boolean A[MD:(E):boolean (c)] (LINE:34) in method: com.googlecode.mp4parser.authoring.tracks.ttml.TtmlTrackImpl.<init>(java.lang.String, java.util.List<org.w3c.dom.Document>):void, file: classes12.dex
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
                	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1105)
                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
                	... 21 more
                */
            /*
                Method dump skipped, instructions count: 280
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.googlecode.mp4parser.authoring.tracks.ttml.TtmlTrackImpl.<init>(java.lang.String, java.util.List):void");
        }

        protected static List<byte[]> extractImages(Document document) throws XPathExpressionException, URISyntaxException, IOException {
            NodeList nodeList = (NodeList) XPathFactory.newInstance().newXPath().compile("//*/@backgroundImage").evaluate(document, XPathConstants.NODESET);
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            int i2 = 1;
            for (int i3 = 0; i3 < nodeList.getLength(); i3++) {
                Node item = nodeList.item(i3);
                String nodeValue = item.getNodeValue();
                String substring = nodeValue.substring(nodeValue.lastIndexOf(OrderISVUtil.MONEY_DECIMAL));
                String str = (String) linkedHashMap.get(nodeValue);
                if (str == null) {
                    str = "urn:mp4parser:" + i2 + substring;
                    linkedHashMap.put(str, nodeValue);
                    i2++;
                }
                item.setNodeValue(str);
            }
            ArrayList arrayList = new ArrayList();
            if (!linkedHashMap.isEmpty()) {
                Iterator it = linkedHashMap.entrySet().iterator();
                while (it.hasNext()) {
                    arrayList.add(streamToByteArray(new URI(document.getDocumentURI()).resolve((String) ((Map.Entry) it.next()).getValue()).toURL().openStream()));
                }
            }
            return arrayList;
        }

        public static String getLanguage(Document document) {
            return document.getDocumentElement().getAttribute("xml:lang");
        }

        private static String join(String str, String[] strArr) {
            StringBuilder sb = new StringBuilder();
            for (String str2 : strArr) {
                sb.append(str2);
                sb.append(str);
            }
            sb.setLength(sb.length() > 0 ? sb.length() - 1 : 0);
            return sb.toString();
        }

        private static long latestTimestamp(Document document) {
            XPath newXPath = XPathFactory.newInstance().newXPath();
            newXPath.setNamespaceContext(TtmlHelpers.NAMESPACE_CONTEXT);
            try {
                NodeList nodeList = (NodeList) newXPath.compile("//*[name()='p']").evaluate(document, XPathConstants.NODESET);
                long j2 = 0;
                for (int i2 = 0; i2 < nodeList.getLength(); i2++) {
                    j2 = Math.max(TtmlHelpers.getEndTime(nodeList.item(i2)), j2);
                }
                return j2;
            } catch (XPathExpressionException e2) {
                throw new RuntimeException(e2);
            }
        }

        private static byte[] streamToByteArray(InputStream inputStream) throws IOException {
            byte[] bArr = new byte[R2.drawable.button_i_a_01];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int read = inputStream.read(bArr);
                if (-1 == read) {
                    return byteArrayOutputStream.toByteArray();
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        long extractDuration(Document document) {
            return lastTimestamp(document) - firstTimestamp(document);
        }

        protected void extractLanguage(List<Document> list) {
            Iterator<Document> it = list.iterator();
            String str = null;
            while (it.hasNext()) {
                String language = getLanguage(it.next());
                if (str == null) {
                    this.trackMetaData.setLanguage(Locale.forLanguageTag(language).getISO3Language());
                    str = language;
                } else if (!str.equals(language)) {
                    throw new RuntimeException("Within one Track all sample documents need to have the same language");
                }
            }
        }

        protected List<String> extractMimeTypes(Document document) throws XPathExpressionException {
            NodeList nodeList = (NodeList) XPathFactory.newInstance().newXPath().compile("//*/@smpte:backgroundImage").evaluate(document, XPathConstants.NODESET);
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            for (int i2 = 0; i2 < nodeList.getLength(); i2++) {
                String nodeValue = nodeList.item(i2).getNodeValue();
                String substring = nodeValue.substring(nodeValue.lastIndexOf(OrderISVUtil.MONEY_DECIMAL));
                if (!substring.contains("jpg") && !substring.contains("jpeg")) {
                    if (substring.contains("png")) {
                        linkedHashSet.add(MIMEType.MIME_TYPE_PNG);
                    }
                } else {
                    linkedHashSet.add(MIMEType.MIME_TYPE_JPEG);
                }
            }
            return new ArrayList(linkedHashSet);
        }

        protected long firstTimestamp(Document document) {
            XPath newXPath = XPathFactory.newInstance().newXPath();
            newXPath.setNamespaceContext(TtmlHelpers.NAMESPACE_CONTEXT);
            try {
                NodeList nodeList = (NodeList) newXPath.compile("//*[@begin]").evaluate(document, XPathConstants.NODESET);
                long j2 = Long.MAX_VALUE;
                for (int i2 = 0; i2 < nodeList.getLength(); i2++) {
                    j2 = Math.min(TtmlHelpers.getStartTime(nodeList.item(i2)), j2);
                }
                return j2;
            } catch (XPathExpressionException e2) {
                throw new RuntimeException(e2);
            }
        }

        @Override // com.googlecode.mp4parser.authoring.Track
        public String getHandler() {
            return "subt";
        }

        @Override // com.googlecode.mp4parser.authoring.Track
        public SampleDescriptionBox getSampleDescriptionBox() {
            return this.sampleDescriptionBox;
        }

        @Override // com.googlecode.mp4parser.authoring.Track
        public long[] getSampleDurations() {
            int length = this.sampleDurations.length;
            long[] jArr = new long[length];
            for (int i2 = 0; i2 < length; i2++) {
                jArr[i2] = (this.sampleDurations[i2] * this.trackMetaData.getTimescale()) / 1000;
            }
            return jArr;
        }

        @Override // com.googlecode.mp4parser.authoring.Track
        public List<Sample> getSamples() {
            return this.samples;
        }

        @Override // com.googlecode.mp4parser.authoring.AbstractTrack, com.googlecode.mp4parser.authoring.Track
        public SubSampleInformationBox getSubsampleInformationBox() {
            return this.subSampleInformationBox;
        }

        @Override // com.googlecode.mp4parser.authoring.Track
        public TrackMetaData getTrackMetaData() {
            return this.trackMetaData;
        }

        protected long lastTimestamp(Document document) {
            XPath newXPath = XPathFactory.newInstance().newXPath();
            newXPath.setNamespaceContext(TtmlHelpers.NAMESPACE_CONTEXT);
            try {
                NodeList nodeList = (NodeList) newXPath.compile("//*[@end]").evaluate(document, XPathConstants.NODESET);
                long j2 = 0;
                for (int i2 = 0; i2 < nodeList.getLength(); i2++) {
                    j2 = Math.max(TtmlHelpers.getEndTime(nodeList.item(i2)), j2);
                }
                return j2;
            } catch (XPathExpressionException e2) {
                throw new RuntimeException(e2);
            }
        }
    }
