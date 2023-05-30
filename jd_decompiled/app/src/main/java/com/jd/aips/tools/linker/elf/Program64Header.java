package com.jd.aips.tools.linker.elf;

import com.jd.aips.tools.linker.elf.Elf;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes12.dex */
public class Program64Header extends Elf.ProgramHeader {
    public Program64Header(ElfParser elfParser, Elf.Header header, long j2) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(header.bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j3 = header.phoff + (j2 * header.phentsize);
        this.type = elfParser.c(allocate, j3);
        this.offset = elfParser.b(allocate, 8 + j3);
        this.vaddr = elfParser.b(allocate, 16 + j3);
        this.memsz = elfParser.b(allocate, j3 + 40);
    }
}
