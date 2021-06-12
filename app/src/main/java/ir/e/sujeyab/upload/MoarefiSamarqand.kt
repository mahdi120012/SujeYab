package ir.e.sujeyab.upload

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ir.e.sujeyab.CustomClasses.EnglishNumberToPersian
import ir.e.sujeyab.CustomClasses.SharedPrefClass
import ir.e.sujeyab.R
import kotlinx.android.synthetic.main.moarefi_samarqand.*
import kotlinx.android.synthetic.main.setting.*
import kotlinx.android.synthetic.main.setting.imgBack

class MoarefiSamarqand : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.moarefi_samarqand)

        //var username:String = SharedPrefClass.getUserId(this,"user")

        txMatnMoarefi.setText(EnglishNumberToPersian().convert(txMatnMoarefi.text.toString()))
        imgBack.setOnClickListener {
            finish()
        }


    }
}