package trade.juniu.pda;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * @author lyd
 * @date 2019/2/28 0028 9:34
 * @desription
 */
public class PDAScanner {

    private Handler mHandler;

    private Context mContext;

    private Barcode mBarcode;

    private OnScanBarcodeListener onScanBarcodeListener;

    public PDAScanner(Context context) {
        this.mContext = context;
        initDevice();
        setHandler(new ScanHandler());
    }

    private void initDevice() {
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
            default:
                break;
        }
    }

    /**
     * 设置结果回掉用的Handler
     * @param handler
     */
    public void setHandler(Handler handler) {
        this.mHandler = handler;
        if (mBarcode != null) {
            mBarcode.setHandler(mHandler);
        }
    }

    public void setOnScanBarcodeListener(OnScanBarcodeListener onScanBarcodeListener) {
        this.onScanBarcodeListener = onScanBarcodeListener;
    }

    /**
     * 扫码结果回到
     */
    class ScanHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            if (PDAConfig.BARCODE_READ == msg.what) {
                String barcode = msg.obj.toString();
                if (onScanBarcodeListener != null) {
                    onScanBarcodeListener.onScan(barcode);
                }
            }
        }
    }
}
