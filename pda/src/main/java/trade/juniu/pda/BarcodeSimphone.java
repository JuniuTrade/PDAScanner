package trade.juniu.pda;

import com.zltd.industry.ScannerManager;

import java.io.UnsupportedEncodingException;

/**
 * @author lyd
 * @date 2019/2/28 17:28
 * @desription
 */
public class BarcodeSimphone extends Barcode {

    private ScannerManager mScannerManager;

    public BarcodeSimphone(){
        init();
    }

    @Override
    public void init() {
        mScannerManager = ScannerManager.getInstance();
        mScannerManager.scannerEnable(true);// 扫描可用
        mScannerManager.setScanMode(ScannerManager.SCAN_SINGLE_MODE);// 单扫
        mScannerManager.setDataTransferType(ScannerManager.TRANSFER_BY_API);// API获取扫描数据
        mScannerManager.addScannerStatusListener(new ScannerStatusListener());
    }

    /**
     * 扫码监听
     */
    class ScannerStatusListener implements ScannerManager.IScannerStatusListener {
        @Override
        public void onScannerStatusChanage(int i) {

        }

        @Override
        public void onScannerResultChanage(byte[] bytes) {
            try {
                String barcode = new String(bytes, "UTF-8");
                if (barcode != null && onScanBarcodeListener != null) {
                    onScanBarcodeListener.onScan(barcode);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }
}
