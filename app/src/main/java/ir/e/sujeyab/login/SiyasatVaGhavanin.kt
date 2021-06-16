package ir.e.sujeyab.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ir.e.sujeyab.CustomClasses.SharedPrefClass
import ir.e.sujeyab.R
import kotlinx.android.synthetic.main.setting.*

class SiyasatVaGhavanin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.siyasat_va_ghavanin)

        imgBack.setOnClickListener {
            finish()
        }


    }
}