package com.google.zxing.datamatrix.encoder;

import androidx.core.view.InputDeviceCompat;
import com.jingdong.sdk.platform.business.personal.R2;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public final class Base256Encoder implements Encoder {
    private static char randomize255State(char c2, int i2) {
        int i3 = c2 + ((i2 * R2.anim.mtrl_bottom_sheet_slide_out) % 255) + 1;
        return i3 <= 255 ? (char) i3 : (char) (i3 + InputDeviceCompat.SOURCE_ANY);
    }

    @Override // com.google.zxing.datamatrix.encoder.Encoder
    public void encode(EncoderContext encoderContext) {
        StringBuilder sb = new StringBuilder();
        sb.append((char) 0);
        while (true) {
            if (!encoderContext.hasMoreCharacters()) {
                break;
            }
            sb.append(encoderContext.getCurrentChar());
            encoderContext.pos++;
            int lookAheadTest = HighLevelEncoder.lookAheadTest(encoderContext.getMessage(), encoderContext.pos, getEncodingMode());
            if (lookAheadTest != getEncodingMode()) {
                encoderContext.signalEncoderChange(lookAheadTest);
                break;
            }
        }
        int length = sb.length() - 1;
        int codewordCount = encoderContext.getCodewordCount() + length + 1;
        encoderContext.updateSymbolInfo(codewordCount);
        boolean z = encoderContext.getSymbolInfo().getDataCapacity() - codewordCount > 0;
        if (encoderContext.hasMoreCharacters() || z) {
            if (length <= 249) {
                sb.setCharAt(0, (char) length);
            } else if (length > 249 && length <= 1555) {
                sb.setCharAt(0, (char) ((length / 250) + 249));
                sb.insert(1, (char) (length % 250));
            } else {
                throw new IllegalStateException("Message length not in valid ranges: " + length);
            }
        }
        int length2 = sb.length();
        for (int i2 = 0; i2 < length2; i2++) {
            encoderContext.writeCodeword(randomize255State(sb.charAt(i2), encoderContext.getCodewordCount() + 1));
        }
    }

    @Override // com.google.zxing.datamatrix.encoder.Encoder
    public int getEncodingMode() {
        return 5;
    }
}
