package ir.e.sujeyab.upload

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ir.e.sujeyab.CustomClasses.SharedPrefClass
import ir.e.sujeyab.R
import kotlinx.android.synthetic.main.setting.*

class MoarefiSamarqand : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.moarefi_samarqand)

        //var username:String = SharedPrefClass.getUserId(this,"user")


        imgBack.setOnClickListener {
            finish()
        }


    }
}