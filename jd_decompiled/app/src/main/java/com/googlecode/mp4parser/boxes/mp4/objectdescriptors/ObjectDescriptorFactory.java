package com.googlecode.mp4parser.boxes.mp4.objectdescriptors;

import com.coremedia.iso.IsoTypeReader;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes12.dex */
public class ObjectDescriptorFactory {
    protected static Logger log = Logger.getLogger(ObjectDescriptorFactory.class.getName());
    protected static Map<Integer, Map<Integer, Class<? extends BaseDescriptor>>> descriptorRegistry = new HashMap();

    static {
        HashSet<Class<? extends BaseDescriptor>> hashSet = new HashSet();
        hashSet.add(DecoderSpecificInfo.class);
        hashSet.add(SLConfigDescriptor.class);
        hashSet.add(BaseDescriptor.class);
        hashSet.add(ExtensionDescriptor.class);
        hashSet.add(ObjectDescriptorBase.class);
        hashSet.add(ProfileLevelIndicationDescriptor.class);
        hashSet.add(AudioSpecificConfig.class);
        hashSet.add(ExtensionProfileLevelDescriptor.class);
        hashSet.add(ESDescriptor.class);
        hashSet.add(DecoderConfigDescriptor.class);
        for (Class<? extends BaseDescriptor> cls : hashSet) {
            Descriptor descriptor = (Descriptor) cls.getAnnotation(Descriptor.class);
            int[] tags = descriptor.tags();
            int objectTypeIndication = descriptor.objectTypeIndication();
            Map<Integer, Class<? extends BaseDescriptor>> map = descriptorRegistry.get(Integer.valueOf(objectTypeIndication));
            if (map == null) {
                map = new HashMap<>();
            }
            for (int i2 : tags) {
                map.put(Integer.valueOf(i2), cls);
            }
            descriptorRegistry.put(Integer.valueOf(objectTypeIndication), map);
        }
    }

    public static BaseDescriptor createFrom(int i2, ByteBuffer byteBuffer) throws IOException {
        BaseDescriptor unknownDescriptor;
        int readUInt8 = IsoTypeReader.readUInt8(byteBuffer);
        Map<Integer, Class<? extends BaseDescriptor>> map = descriptorRegistry.get(Integer.valueOf(i2));
        if (map == null) {
            map = descriptorRegistry.get(-1);
        }
        Class<? extends BaseDescriptor> cls = map.get(Integer.valueOf(readUInt8));
        if (cls != null && !cls.isInterface() && !Modifier.isAbstract(cls.getModifiers())) {
            try {
                unknownDescriptor = cls.newInstance();
            } catch (Exception e2) {
                log.log(Level.SEVERE, "Couldn't instantiate BaseDescriptor class " + cls + " for objectTypeIndication " + i2 + " and tag " + readUInt8, (Throwable) e2);
                throw new RuntimeException(e2);
            }
        } else {
            log.warning("No ObjectDescriptor found for objectTypeIndication " + Integer.toHexString(i2) + " and tag " + Integer.toHexString(readUInt8) + " found: " + cls);
            unknownDescriptor = new UnknownDescriptor();
        }
        unknownDescriptor.parse(readUInt8, byteBuffer);
        return unknownDescriptor;
    }
}
