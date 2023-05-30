package com.jd.aips.tools.linker.elf;

import com.jd.aips.tools.linker.elf.Elf;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes12.dex */
public class Program32Header extends Elf.ProgramHeader {
    public Program32Header(ElfParser elfParser, Elf.Header header, long j2) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.order(header.bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j3 = header.phoff + (j2 * header.phentsize);
        this.type = elfParser.c(allocate, j3);
        this.offset = elfParser.c(allocate, 4 + j3);
        this.vaddr = elfParser.c(allocate, 8 + j3);
        this.memsz = elfParser.c(allocate, j3 + 20);
    }
}
