package ir.e.sujeyab.SujeClick

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ir.e.sujeyab.R
import kotlinx.android.synthetic.main.suje_click.*

class SujeClick : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.suje_click)

        var onvan:String = intent.extras!!.getString("onvan").toString()
        var matn:String = intent.extras!!.getString("matn").toString()
        var picture:String = intent.extras!!.getString("picture").toString()
        var motavali:String = intent.extras!!.getString("motavali").toString()
        var modat_baghi_mande:String = intent.extras!!.getString("modat_baghi_mande").toString()

        txOnvan.setText(onvan)
    }
}