package ir.e.sujeyab.CustomClasses;

import android.app.ProgressDialog;
import android.content.Context;

public class ProgressDialogClass {

    private ProgressDialog progressDialog = null;

    public void showProgress(Context c){
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(c);
        }
        progressDialog = new ProgressDialog(c);

        progressDialog.setMessage("درحال بارگزاری...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void dismissProgress(){
        progressDialog.dismiss();
    }
}