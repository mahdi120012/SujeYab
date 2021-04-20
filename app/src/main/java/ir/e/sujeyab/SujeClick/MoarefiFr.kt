 package ir.e.sujeyab.SujeClick

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import ir.e.sujeyab.CustomClasses.SharedPrefClass
import ir.e.sujeyab.CustomClasses.TimeKononi
import ir.e.sujeyab.LoadData
import ir.e.sujeyab.R
import ir.e.sujeyab.adapters.TasavirSujeAdapter
import ir.e.sujeyab.models.TasavirSujeModel
import kotlinx.android.synthetic.main.moarefi_fr.view.*
import kotlinx.android.synthetic.main.moarefi_fr.view.indicator
import kotlinx.android.synthetic.main.net_connection.*
import kotlinx.android.synthetic.main.pishkhan_fr.view.*
import java.util.*
import kotlin.collections.ArrayList


 class MoarefiFr : Fragment() {
    var inflatedview: View? = null

    private val ImgArray = ArrayList<TasavirSujeModel>()
    //private lateinit var tasavirSujeAdapter:TasavirSujeAdapter
    private var currentPage = 0
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        inflatedview = inflater.inflate(R.layout.moarefi_fr, container, false)


        var sujeId:String = activity!!.intent.extras!!.getString("id").toString()
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

        //loadImage(inflatedview!!.imgPicture,picture)
        loadImage(inflatedview!!.imgAxFerestande,axFerestande)

        //line zir baraye load tasavir safhe mahsole
        LoadData.LoadTasavirSujeBaRetrofit(activity, clWifiState,
            sujeId, inflatedview!!.viewPager1, inflatedview!!.indicator, ImgArray)

        //init()

        var username = SharedPrefClass.getUserId(activity,"user")

        inflatedview!!.imgLike.setOnClickListener {
            LoadData.LikePost(activity, clWifiState,username,
                sujeId, inflatedview!!.imgLike)
        }



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

     private fun init() {
         val handler = Handler()
         val Update = Runnable {
             if (currentPage === 9) {
                 currentPage = 0
             }
             inflatedview!!.viewPager1.setCurrentItem(currentPage++, true)
         }
         //Auto start
         val swipeTimer = Timer()
         swipeTimer.schedule(object : TimerTask() {
             override fun run() {
                 handler.post(Update)
             }
         }, 3500, 3500)
     }
}