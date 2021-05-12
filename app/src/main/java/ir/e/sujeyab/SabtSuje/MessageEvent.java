package ir.e.sujeyab.SabtSuje;

import android.widget.EditText;
import android.widget.TextView;

public class MessageEvent {
    private EditText etOnvan, etMozo, etTozihat;
    private TextView txIdFarakhan;

    public EditText getEtOnvan() {
        return etOnvan;
    }

    public void setEtOnvan(EditText etOnvan) {
        this.etOnvan = etOnvan;
    }

    public EditText getEtMozo() {
        return etMozo;
    }

    public void setEtMozo(EditText etMozo) {
        this.etMozo = etMozo;
    }

    public EditText getEtTozihat() {
        return etTozihat;
    }

    public void setEtTozihat(EditText etTozihat) {
        this.etTozihat = etTozihat;
    }

    public TextView getTxIdFarakhan() {
        return txIdFarakhan;
    }

    public void setTxIdFarakhan(TextView txIdFarakhan) {
        this.txIdFarakhan = txIdFarakhan;
    }
}
