package com.jd.aips.tools.linker.elf;

import com.jd.aips.tools.linker.elf.Elf;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes12.dex */
public class ElfParser implements Closeable, Elf {
    private final FileChannel a;

    public ElfParser(File file) throws FileNotFoundException {
        if (file != null && file.exists()) {
            this.a = new FileInputStream(file).getChannel();
            return;
        }
        throw new IllegalArgumentException("File is null or does not exist");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int a(ByteBuffer byteBuffer, long j2) throws IOException {
        a(byteBuffer, j2, 2);
        return byteBuffer.getShort() & 65535;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long b(ByteBuffer byteBuffer, long j2) throws IOException {
        a(byteBuffer, j2, 8);
        return byteBuffer.getLong();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long c(ByteBuffer byteBuffer, long j2) throws IOException {
        a(byteBuffer, j2, 4);
        return byteBuffer.getInt() & 4294967295L;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.a.close();
    }

    public Elf.Header parseHeader() throws IOException {
        this.a.position(0L);
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        a(allocate, 0L, 4);
        if ((allocate.getInt() & 4294967295L) == 1179403647) {
            a(allocate, 4L, 1);
            short s = (short) (allocate.get() & 255);
            a(allocate, 5L, 1);
            boolean z = ((short) (allocate.get() & 255)) == 2;
            if (s == 1) {
                return new Elf32Header(z, this);
            }
            if (s == 2) {
                return new Elf64Header(z, this);
            }
            throw new IllegalStateException("Invalid class type!");
        }
        throw new IllegalArgumentException("Invalid ELF Magic!");
    }

    public List<String> parseNeededDependencies() throws IOException {
        long j2;
        long j3;
        this.a.position(0L);
        ArrayList arrayList = new ArrayList();
        Elf.Header parseHeader = parseHeader();
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(parseHeader.bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j4 = parseHeader.phnum;
        int i2 = 0;
        if (j4 == 65535) {
            j4 = parseHeader.getSectionHeader(0).info;
        }
        long j5 = 0;
        while (true) {
            j2 = 1;
            if (j5 >= j4) {
                j3 = 0;
                break;
            }
            Elf.ProgramHeader programHeader = parseHeader.getProgramHeader(j5);
            if (programHeader.type == 2) {
                j3 = programHeader.offset;
                break;
            }
            j5++;
        }
        if (j3 == 0) {
            return Collections.unmodifiableList(arrayList);
        }
        ArrayList arrayList2 = new ArrayList();
        long j6 = 0;
        while (true) {
            Elf.DynamicStructure dynamicStructure = parseHeader.getDynamicStructure(j3, i2);
            long j7 = j3;
            long j8 = dynamicStructure.tag;
            if (j8 == 1) {
                arrayList2.add(Long.valueOf(dynamicStructure.val));
            } else if (j8 == 5) {
                j6 = dynamicStructure.val;
            }
            i2++;
            if (dynamicStructure.tag == 0) {
                break;
            }
            j3 = j7;
        }
        if (j6 != 0) {
            for (long j9 = 0; j9 < j4; j9 += j2) {
                Elf.ProgramHeader programHeader2 = parseHeader.getProgramHeader(j9);
                if (programHeader2.type == j2) {
                    long j10 = programHeader2.vaddr;
                    if (j10 > j6) {
                        continue;
                    } else if (j6 <= programHeader2.memsz + j10) {
                        long j11 = (j6 - j10) + programHeader2.offset;
                        Iterator it = arrayList2.iterator();
                        while (it.hasNext()) {
                            long longValue = ((Long) it.next()).longValue() + j11;
                            StringBuilder sb = new StringBuilder();
                            while (true) {
                                long j12 = longValue + 1;
                                a(allocate, longValue, 1);
                                short s = (short) (allocate.get() & 255);
                                if (s != 0) {
                                    sb.append((char) s);
                                    longValue = j12;
                                }
                            }
                            arrayList.add(sb.toString());
                        }
                        return arrayList;
                    } else {
                        j2 = 1;
                    }
                }
            }
            throw new IllegalStateException("Could not map vma to file offset!");
        }
        throw new IllegalStateException("String table offset not found!");
    }

    protected void a(ByteBuffer byteBuffer, long j2, int i2) throws IOException {
        byteBuffer.position(0);
        byteBuffer.limit(i2);
        long j3 = 0;
        while (j3 < i2) {
            int read = this.a.read(byteBuffer, j2 + j3);
            if (read == -1) {
                throw new EOFException();
            }
            j3 += read;
        }
        byteBuffer.position(0);
    }
}
