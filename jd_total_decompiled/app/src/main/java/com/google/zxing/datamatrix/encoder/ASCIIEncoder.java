package com.google.zxing.datamatrix.encoder;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public final class ASCIIEncoder implements Encoder {
    private static char encodeASCIIDigits(char c2, char c3) {
        if (HighLevelEncoder.isDigit(c2) && HighLevelEncoder.isDigit(c3)) {
            return (char) (((c2 - '0') * 10) + (c3 - '0') + 130);
        }
        throw new IllegalArgumentException("not digits: " + c2 + c3);
    }

    @Override // com.google.zxing.datamatrix.encoder.Encoder
    public void encode(EncoderContext encoderContext) {
        if (HighLevelEncoder.determineConsecutiveDigitCount(encoderContext.getMessage(), encoderContext.pos) >= 2) {
            encoderContext.writeCodeword(encodeASCIIDigits(encoderContext.getMessage().charAt(encoderContext.pos), encoderContext.getMessage().charAt(encoderContext.pos + 1)));
            encoderContext.pos += 2;
            return;
        }
        char currentChar = encoderContext.getCurrentChar();
        int lookAheadTest = HighLevelEncoder.lookAheadTest(encoderContext.getMessage(), encoderContext.pos, getEncodingMode());
        if (lookAheadTest == getEncodingMode()) {
            if (HighLevelEncoder.isExtendedASCII(currentChar)) {
                encoderContext.writeCodeword('\u00eb');
                encoderContext.writeCodeword((char) ((currentChar - '\u0080') + 1));
                encoderContext.pos++;
                return;
            }
            encoderContext.writeCodeword((char) (currentChar + 1));
            encoderContext.pos++;
        } else if (lookAheadTest == 1) {
            encoderContext.writeCodeword('\u00e6');
            encoderContext.signalEncoderChange(1);
        } else if (lookAheadTest == 2) {
            encoderContext.writeCodeword('\u00ef');
            encoderContext.signalEncoderChange(2);
        } else if (lookAheadTest == 3) {
            encoderContext.writeCodeword('\u00ee');
            encoderContext.signalEncoderChange(3);
        } else if (lookAheadTest == 4) {
            encoderContext.writeCodeword('\u00f0');
            encoderContext.signalEncoderChange(4);
        } else if (lookAheadTest == 5) {
            encoderContext.writeCodeword('\u00e7');
            encoderContext.signalEncoderChange(5);
        } else {
            throw new IllegalStateException("Illegal mode: " + lookAheadTest);
        }
    }

    @Override // com.google.zxing.datamatrix.encoder.Encoder
    public int getEncodingMode() {
        return 0;
    }
}
