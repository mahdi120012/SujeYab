package ir.e.sujeyab

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ir.e.sujeyab.CustomClasses.SharedPrefClass
import ir.e.sujeyab.SabtSuje.snackbar
import ir.e.sujeyab.login.Login
import ir.e.sujeyab.upload.Poshtibani
import kotlinx.android.synthetic.main.setting.*

class Setting : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting)
        //val typeface = ResourcesCompat.getFont(applicationContext, R.font.fa_regular)
        //txMoshakhasatManIcon.setTypeface(typeface);
        //txMoshakhasatManIcon.setText("\uf5d0");

        var username:String = SharedPrefClass.getUserId(this,"user")

        if (username == ""){
            clExit.visibility = View.GONE
            txMoshakhasatMan.setText("ورود به حساب کاربری")
        }

        imgBack.setOnClickListener {
            finish()
        }

        clMoshakhasatMan.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            //intent.putExtra("keyIdentifier", value)
            startActivity(intent)
        }

        clTansimatAsli.setOnClickListener {
            Toast.makeText(this, "بزودی راه اندازی می شود", Toast.LENGTH_SHORT).show()
        }

        clElanat.setOnClickListener {
            Toast.makeText(this, "بزودی راه اندازی می شود", Toast.LENGTH_SHORT).show()
        }


        clMoarefiSuje.setOnClickListener {
            Toast.makeText(this, "بزودی راه اندازی می شود", Toast.LENGTH_SHORT).show()
        }


        clDostanManDarSuje.setOnClickListener {
            Toast.makeText(this, "بزودی راه اندازی می شود", Toast.LENGTH_SHORT).show()
        }


        clPoshtibani.setOnClickListener {
            val intent = Intent(this, Poshtibani::class.java)
            //myIntent.putExtra("key", value) //Optional parameters
            startActivity(intent)

        }

        clMoarefiBeDostan.setOnClickListener {
            Toast.makeText(this, "بزودی راه اندازی می شود", Toast.LENGTH_SHORT).show()
        }

        clExit.setOnClickListener {
            SharedPrefClass.clearData(this);
            finish()
        }

        imgAparat.setOnClickListener {
            val uri: Uri = Uri.parse("https://www.aparat.com/samarqand")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        imgTwitter.setOnClickListener {
            clcl.snackbar("به زودی راه اندازی می شود")
            /*val uri: Uri = Uri.parse("https://www.twitter.com")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)*/
        }

        imgInstagram.setOnClickListener {
            val uri: Uri = Uri.parse("https://www.instagram.com/coronado.ir")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        imgEitta.setOnClickListener {
            val uri: Uri = Uri.parse("https://eitaa.com/samarqand")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        imgGap.setOnClickListener {
            val uri: Uri = Uri.parse("https://gap.im/samarqand")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }


        imgIgap.setOnClickListener {
            val uri: Uri = Uri.parse("https://igap.net/samarqand")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

    }
}