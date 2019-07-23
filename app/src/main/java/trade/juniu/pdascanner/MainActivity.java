package trade.juniu.pdascanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import trade.juniu.pda.OnScanBarcodeListener;
import trade.juniu.pda.PDAScanner;
import trade.juniu.pda.PDASingle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = findViewById(R.id.text);
        PDAScanner scanner = PDASingle.getScanner(this);
        scanner.setOnScanBarcodeListener(new OnScanBarcodeListener() {
            @Override
            public void onScan(final String barcode) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(barcode);
                    }
                });
            }
        });
    }
}
