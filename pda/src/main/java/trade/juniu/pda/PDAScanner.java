package trade.juniu.pda;

import android.content.Context;
import android.os.Build;
import android.util.Log;

/**
 * @author lyd
 * @date 2019/2/28 0028 9:34
 * @desription
 */
public class PDAScanner {

    private Context mContext;

    private Barcode mBarcode;

    public PDAScanner(Context context) {
        this.mContext = context;
        initDevice();
    }

    private void initDevice() {
        Log.e("lyd", " model " + Build.MODEL);
        switch (Build.MODEL) {
            case PDAConfig.DEVICE_A3X:
                break;
            case PDAConfig.DEVICE_C50:
                mBarcode = new BarcodeC50();
                break;
            case PDAConfig.DEVICE_C76:
                mBarcode = new BarcodeC76(mContext);
                break;
            case PDAConfig.DEVICE_SIMPHONE:
                mBarcode = new BarcodeSimphone();
            default:
                break;
        }
    }

    public void setOnScanBarcodeListener(OnScanBarcodeListener onScanBarcodeListener) {
        mBarcode.setOnScanBarcodeListener(onScanBarcodeListener);
    }

}
