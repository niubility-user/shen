package com.jd.aips.tools.linker.elf;

import com.jd.aips.tools.linker.elf.Elf;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes12.dex */
public class Elf64Header extends Elf.Header {
    private final ElfParser a;

    public Elf64Header(boolean z, ElfParser elfParser) throws IOException {
        this.bigEndian = z;
        this.a = elfParser;
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(z ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        this.type = elfParser.a(allocate, 16L);
        this.phoff = elfParser.b(allocate, 32L);
        this.shoff = elfParser.b(allocate, 40L);
        this.phentsize = elfParser.a(allocate, 54L);
        this.phnum = elfParser.a(allocate, 56L);
        this.shentsize = elfParser.a(allocate, 58L);
        this.shnum = elfParser.a(allocate, 60L);
        this.shstrndx = elfParser.a(allocate, 62L);
    }

    @Override // com.jd.aips.tools.linker.elf.Elf.Header
    public Elf.DynamicStructure getDynamicStructure(long j2, int i2) throws IOException {
        return new Dynamic64Structure(this.a, this, j2, i2);
    }

    @Override // com.jd.aips.tools.linker.elf.Elf.Header
    public Elf.ProgramHeader getProgramHeader(long j2) throws IOException {
        return new Program64Header(this.a, this, j2);
    }

    @Override // com.jd.aips.tools.linker.elf.Elf.Header
    public Elf.SectionHeader getSectionHeader(int i2) throws IOException {
        return new Section64Header(this.a, this, i2);
    }
}
