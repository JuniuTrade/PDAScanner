package trade.juniu.pda;

import android.os.Handler;

/**
 * @author lyd
 * @date 2019/2/28 0028 10:29
 * @desription
 */
public abstract class Barcode {

    protected OnScanBarcodeListener onScanBarcodeListener;

    public abstract void init();

    public void setOnScanBarcodeListener(OnScanBarcodeListener onScanBarcodeListener) {
        this.onScanBarcodeListener = onScanBarcodeListener;
    }
}
