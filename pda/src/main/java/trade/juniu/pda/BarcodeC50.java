package trade.juniu.pda;

import android.os.Build;
import android.os.Handler;
import android.os.Message;

/**
 * @author lyd
 * @date 2019/2/28 0028 10:17
 * @desription
 */
public class BarcodeC50 extends Barcode {

    public native void open();

    public native void scan();

    public native void stopScan();

    public native void close();

    /**
     * 设置扫码模式 true：连续
     *
     * @param isContinue
     */
    public native void setScanMode(boolean isContinue);

    /**
     * 当前模式,是否是连扫
     *
     * @return 1:连扫
     */
    public native int isContinueModel();

    /**
     * 获取设备唯一ID
     *
     * @return
     */
    public native String getMachineCode();

    public BarcodeC50(){
        init();
    }

    @Override
    public void init() {
        open();
        //判断是否为连续扫码模式，不是把设备设置为连续扫描模式
        if (isContinueModel() != 1) {
            setScanMode(true);
            stopScan();
        }
    }

    public void setScanResults(String result) {
        if (result == null) {
            return;
        }
        if (onScanBarcodeListener == null) {
            return;
        }
        onScanBarcodeListener.onScan(result);
    }

    static {
        if (PDAConfig.DEVICE_C50.equals(Build.MODEL)) {
            try {
                System.loadLibrary("Barcode");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
