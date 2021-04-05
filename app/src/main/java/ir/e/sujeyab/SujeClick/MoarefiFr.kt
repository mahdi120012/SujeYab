 package ir.e.sujeyab.SujeClick

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import ir.e.sujeyab.CustomClasses.TimeKononi
import ir.e.sujeyab.R
import kotlinx.android.synthetic.main.button_sabt_fori_suje.*
import kotlinx.android.synthetic.main.button_sabt_fori_suje.view.*
import kotlinx.android.synthetic.main.moarefi_fr.*
import kotlinx.android.synthetic.main.moarefi_fr.view.*
import kotlinx.android.synthetic.main.sabt_fori_suje.*
import kotlinx.android.synthetic.main.sabt_fori_suje.clcl
import kotlinx.android.synthetic.main.tarh_suje_fr.*
import kotlinx.android.synthetic.main.tarh_suje_fr.view.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.greenrobot.eventbus.EventBus
import java.io.File


class MoarefiFr : Fragment() {
    var inflatedview: View? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        inflatedview = inflater.inflate(R.layout.moarefi_fr, container, false)

        var onvan:String = activity!!.intent.extras!!.getString("onvan").toString()
        var matn:String = activity!!.intent.extras!!.getString("matn").toString()
        var picture:String = activity!!.intent.extras!!.getString("picture").toString()
        var motavali:String = activity!!.intent.extras!!.getString("motavali").toString()
        var modat_baghi_mande:String = activity!!.intent.extras!!.getString("modat_baghi_mande").toString()
        var axFerestande:String = activity!!.intent.extras!!.getString("axFerestande").toString()
        var dateCreate:String = activity!!.intent.extras!!.getString("date_create").toString()
        var name:String = activity!!.intent.extras!!.getString("name").toString()
        var semat_shoghli:String = activity!!.intent.extras!!.getString("semat_shoghli").toString()
        inflatedview!!.txOnvan.setText(onvan)
        inflatedview!!.txMatn.setText(matn)

        //Toast.makeText(activity,dateCreate,Toast.LENGTH_SHORT).show()
        inflatedview!!.txTarikh.setText(TimeKononi().changeGregorianToPersian(dateCreate))
        inflatedview!!.txNameFerestande.setText(name)
        inflatedview!!.txTakhasosFerestande.setText(semat_shoghli)

        //inflatedview!!.txMatnKamel.setText(matn)

        loadImage(inflatedview!!.imgPicture,picture)
        loadImage(inflatedview!!.imgAxFerestande,axFerestande)

        return inflatedview
}

 fun loadImage(img: ImageView, pictureLink:String){

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