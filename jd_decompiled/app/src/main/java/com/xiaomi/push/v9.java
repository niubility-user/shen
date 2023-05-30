package com.xiaomi.push;

import java.io.File;
import java.io.FileFilter;

/* loaded from: classes11.dex */
class v9 implements FileFilter {
    @Override // java.io.FileFilter
    public boolean accept(File file) {
        return file.isDirectory();
    }
}
