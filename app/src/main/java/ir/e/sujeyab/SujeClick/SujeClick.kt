package ir.e.sujeyab.SujeClick

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import ir.e.sujeyab.R
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.suje_click.*
import kotlinx.android.synthetic.main.suje_click.txOnvan

class SujeClick : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.suje_click)

        var onvan:String = intent.extras!!.getString("onvan").toString()
        var matn:String = intent.extras!!.getString("matn").toString()
        var picture:String = intent.extras!!.getString("picture").toString()
        var motavali:String = intent.extras!!.getString("motavali").toString()
        var modat_baghi_mande:String = intent.extras!!.getString("modat_baghi_mande").toString()
        var axFerestande:String = intent.extras!!.getString("axFerestande").toString()

        txOnvan.setText(onvan)
        txMatn.setText(matn)
        txMatnKamel.setText(matn)

        loadImage(imgPicture,picture)
        loadImage(imgAxFerestande,axFerestande)

    }


    fun loadImage(img:ImageView, pictureLink:String){

        //line zir baraye circle kardane imageView ee
        //.transform(CropCircleTransformation())

        if (pictureLink.isEmpty()) {
            Picasso.get()
                    .load(R.drawable.logo)
                    .centerInside()
                    .fit()
                    .error(R.drawable.logo)
                    .placeholder(R.drawable.logo)
                    .into(img)
        } else {
            Picasso.get()
                    .load(pictureLink)
                    .centerInside()
                    .fit()
                    .error(R.drawable.logo)
                    .placeholder(R.drawable.logo)
                    .into(img)
        }

    }
}