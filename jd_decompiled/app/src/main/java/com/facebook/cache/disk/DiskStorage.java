package com.facebook.cache.disk;

import com.facebook.binaryresource.BinaryResource;
import com.facebook.cache.common.WriterCallback;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public interface DiskStorage {

    /* loaded from: classes.dex */
    public static class DiskDumpInfo {
        public List<DiskDumpInfoEntry> entries = new ArrayList();
        public Map<String, Integer> typeCounts = new HashMap();
    }

    /* loaded from: classes.dex */
    public static class DiskDumpInfoEntry {
        public final String firstBits;
        public final String id;
        public final String path;
        public final float size;
        public final String type;

        /* JADX INFO: Access modifiers changed from: protected */
        public DiskDumpInfoEntry(String str, String str2, String str3, float f2, String str4) {
            this.id = str;
            this.path = str2;
            this.type = str3;
            this.size = f2;
            this.firstBits = str4;
        }
    }

    /* loaded from: classes.dex */
    public interface Entry {
        String getId();

        BinaryResource getResource();

        long getSize();

        long getTimestamp();
    }

    /* loaded from: classes.dex */
    public interface Inserter {
        boolean cleanUp();

        BinaryResource commit(Object obj);

        void writeData(WriterCallback writerCallback, Object obj);
    }

    void clearAll();

    boolean contains(String str, Object obj);

    DiskDumpInfo getDumpInfo();

    Collection<Entry> getEntries();

    BinaryResource getResource(String str, Object obj);

    String getStorageName();

    Inserter insert(String str, Object obj);

    boolean isEnabled();

    boolean isExternal();

    void purgeUnexpectedResources();

    long remove(Entry entry);

    long remove(String str);

    boolean touch(String str, Object obj);
}
