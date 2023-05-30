package com.google.zxing.aztec;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.aztec.decoder.Decoder;
import com.google.zxing.aztec.detector.Detector;
import com.google.zxing.common.DecoderResult;
import java.util.List;
import java.util.Map;

/* loaded from: classes12.dex */
public final class AztecReader implements Reader {
    @Override // com.google.zxing.Reader
    public Result decode(BinaryBitmap binaryBitmap) throws NotFoundException, FormatException {
        return decode(binaryBitmap, null);
    }

    @Override // com.google.zxing.Reader
    public void reset() {
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x005d A[LOOP:0: B:32:0x005b->B:33:0x005d, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0085  */
    @Override // com.google.zxing.Reader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Result decode(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException {
        ResultPoint[] resultPointArr;
        ResultPoint[] resultPointArr2;
        FormatException formatException;
        List<byte[]> byteSegments;
        String eCLevel;
        ResultPointCallback resultPointCallback;
        AztecDetectorResult detect;
        Detector detector = new Detector(binaryBitmap.getBlackMatrix());
        DecoderResult decoderResult = null;
        try {
            detect = detector.detect(false);
            resultPointArr = detect.getPoints();
        } catch (FormatException e2) {
            e = e2;
            resultPointArr = null;
        } catch (NotFoundException e3) {
            e = e3;
            resultPointArr = null;
        }
        try {
            resultPointArr2 = resultPointArr;
            formatException = null;
            decoderResult = new Decoder().decode(detect);
            e = null;
        } catch (FormatException e4) {
            e = e4;
            resultPointArr2 = resultPointArr;
            formatException = e;
            e = null;
            if (decoderResult == null) {
            }
            if (map != null) {
                while (r6 < r0) {
                }
            }
            Result result = new Result(decoderResult.getText(), decoderResult.getRawBytes(), resultPointArr2, BarcodeFormat.AZTEC);
            byteSegments = decoderResult.getByteSegments();
            if (byteSegments != null) {
            }
            eCLevel = decoderResult.getECLevel();
            if (eCLevel != null) {
            }
            return result;
        } catch (NotFoundException e5) {
            e = e5;
            resultPointArr2 = resultPointArr;
            formatException = null;
            if (decoderResult == null) {
            }
            if (map != null) {
            }
            Result result2 = new Result(decoderResult.getText(), decoderResult.getRawBytes(), resultPointArr2, BarcodeFormat.AZTEC);
            byteSegments = decoderResult.getByteSegments();
            if (byteSegments != null) {
            }
            eCLevel = decoderResult.getECLevel();
            if (eCLevel != null) {
            }
            return result2;
        }
        if (decoderResult == null) {
            try {
                AztecDetectorResult detect2 = detector.detect(true);
                resultPointArr2 = detect2.getPoints();
                decoderResult = new Decoder().decode(detect2);
            } catch (FormatException | NotFoundException e6) {
                if (e == null) {
                    if (formatException != null) {
                        throw formatException;
                    }
                    throw e6;
                }
                throw e;
            }
        }
        if (map != null && (resultPointCallback = (ResultPointCallback) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK)) != null) {
            for (ResultPoint resultPoint : resultPointArr2) {
                resultPointCallback.foundPossibleResultPoint(resultPoint);
            }
        }
        Result result22 = new Result(decoderResult.getText(), decoderResult.getRawBytes(), resultPointArr2, BarcodeFormat.AZTEC);
        byteSegments = decoderResult.getByteSegments();
        if (byteSegments != null) {
            result22.putMetadata(ResultMetadataType.BYTE_SEGMENTS, byteSegments);
        }
        eCLevel = decoderResult.getECLevel();
        if (eCLevel != null) {
            result22.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, eCLevel);
        }
        return result22;
    }
}
