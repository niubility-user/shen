package com.jd.aips.tools.linker.elf;

import com.jd.aips.tools.linker.elf.Elf;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes12.dex */
public class Elf32Header extends Elf.Header {
    private final ElfParser a;

    public Elf32Header(boolean z, ElfParser elfParser) throws IOException {
        this.bigEndian = z;
        this.a = elfParser;
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.order(z ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        this.type = elfParser.a(allocate, 16L);
        this.phoff = elfParser.c(allocate, 28L);
        this.shoff = elfParser.c(allocate, 32L);
        this.phentsize = elfParser.a(allocate, 42L);
        this.phnum = elfParser.a(allocate, 44L);
        this.shentsize = elfParser.a(allocate, 46L);
        this.shnum = elfParser.a(allocate, 48L);
        this.shstrndx = elfParser.a(allocate, 50L);
    }

    @Override // com.jd.aips.tools.linker.elf.Elf.Header
    public Elf.DynamicStructure getDynamicStructure(long j2, int i2) throws IOException {
        return new Dynamic32Structure(this.a, this, j2, i2);
    }

    @Override // com.jd.aips.tools.linker.elf.Elf.Header
    public Elf.ProgramHeader getProgramHeader(long j2) throws IOException {
        return new Program32Header(this.a, this, j2);
    }

    @Override // com.jd.aips.tools.linker.elf.Elf.Header
    public Elf.SectionHeader getSectionHeader(int i2) throws IOException {
        return new Section32Header(this.a, this, i2);
    }
}
