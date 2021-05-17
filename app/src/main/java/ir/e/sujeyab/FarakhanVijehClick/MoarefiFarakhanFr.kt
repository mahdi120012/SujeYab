 package ir.e.sujeyab.SujeClick

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar.OnRatingBarChangeListener
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import ir.e.sujeyab.CustomClasses.EnglishNumberToPersian
import ir.e.sujeyab.CustomClasses.SharedPrefClass
import ir.e.sujeyab.CustomClasses.TimeKononi
import ir.e.sujeyab.LoadData
import ir.e.sujeyab.R
import ir.e.sujeyab.SabtSuje.SabtForiSuje
import ir.e.sujeyab.SabtSuje.snackbar
import ir.e.sujeyab.login.Login
import ir.e.sujeyab.models.TasavirSujeModel
import kotlinx.android.synthetic.main.moarefi_farakhan_fr.view.*
import kotlinx.android.synthetic.main.moarefi_farakhan_fr.view.txTimeRemain
import kotlinx.android.synthetic.main.moarefi_fr.view.clcl
import kotlinx.android.synthetic.main.moarefi_fr.view.imgAxFerestande
import kotlinx.android.synthetic.main.moarefi_fr.view.imgLike
import kotlinx.android.synthetic.main.moarefi_fr.view.indicator
import kotlinx.android.synthetic.main.moarefi_fr.view.ratingBar
import kotlinx.android.synthetic.main.moarefi_fr.view.txMatn
import kotlinx.android.synthetic.main.moarefi_fr.view.txOnvan
import kotlinx.android.synthetic.main.moarefi_fr.view.txRateAvg
import kotlinx.android.synthetic.main.moarefi_fr.view.txTedadLike
import kotlinx.android.synthetic.main.moarefi_fr.view.txTedadRate
import kotlinx.android.synthetic.main.moarefi_fr.view.viewPager1
import kotlinx.android.synthetic.main.net_connection.*
import kotlinx.android.synthetic.main.row_farakhan_vijeh.view.*
import java.util.*
import kotlin.collections.ArrayList


 class MoarefiFarakhanFr : Fragment() {
    var inflatedview: View? = null

    private val ImgArray = ArrayList<TasavirSujeModel>()
    //private lateinit var tasavirSujeAdapter:TasavirSujeAdapter
    private var currentPage = 0
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        inflatedview = inflater.inflate(R.layout.moarefi_farakhan_fr, container, false)


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
        var tedad_like:String = activity!!.intent.extras!!.getString("tedad_like").toString()
        var vaziyat_like:String = activity!!.intent.extras!!.getString("vaziyat_like").toString()
        var tedad_comment:String = activity!!.intent.extras!!.getString("tedad_comment").toString()




        if (modat_baghi_mande.contains("-")) {
            val modatBaghimande: String = modat_baghi_mande.replace("-", "")
            inflatedview!!.txTimeRemain.setText(EnglishNumberToPersian().convert(modatBaghimande) + " روز گذشته")
        } else {
            inflatedview!!.txTimeRemain.setText(EnglishNumberToPersian().convert(modat_baghi_mande) + " روز باقی مانده")
        }

        inflatedview!!.txMotavali.setText(motavali)
        inflatedview!!.txTedadLike.setText(tedad_like)
        //Toast.makeText(activity,tedad_like,Toast.LENGTH_SHORT).show()

        if (vaziyat_like.equals("0")) {
            inflatedview!!.imgLike.setImageDrawable(ContextCompat.getDrawable(activity!!, R.drawable.like))
        } else {
            inflatedview!!.imgLike.setImageDrawable(ContextCompat.getDrawable(activity!!, R.drawable.like_red))
        }


        inflatedview!!.txOnvan.setText(onvan)
        inflatedview!!.txMatn.setText(matn)

        //Toast.makeText(activity,dateCreate,Toast.LENGTH_SHORT).show()
        inflatedview!!.txTarikh.setText(TimeKononi().changeGregorianToPersian(dateCreate))
        //inflatedview!!.txNameFerestande.setText(name)
        inflatedview!!.clAddSuje.setOnClickListener {
            var username = SharedPrefClass.getUserId(activity,"user")

            if (username == ""){
                val intent = Intent(activity, Login::class.java)
                startActivity(intent)
            }else{

                val intent = Intent(activity, SabtForiSuje::class.java)
                startActivity(intent)
            }
        }

        //inflatedview!!.txMatnKamel.setText(matn)

        //loadImage(inflatedview!!.imgPicture,picture)
        loadImage(inflatedview!!.imgAxFerestande,axFerestande)

        //line zir baraye load tasavir safhe mahsole
        LoadData.LoadTasavirSujeBaRetrofit(activity, clWifiState,
            sujeId, inflatedview!!.viewPager1, inflatedview!!.indicator, ImgArray)

        //init()

        var username = SharedPrefClass.getUserId(activity,"user")

            inflatedview!!.imgLike.setOnClickListener {

                if (username == "" || username == null){

                    inflatedview!!.clcl.snackbar("ابتدا وارد شوید")

                }else{
                    LoadData.LikePost(activity, clWifiState,username,
                        sujeId, inflatedview!!.imgLike, inflatedview!!.txTedadLike)
                }

            }


        inflatedview!!.ratingBar.setOnRatingBarChangeListener(OnRatingBarChangeListener { arg0, rateValue, arg2 ->

            if (username == "" || username == null){
                inflatedview!!.clcl.snackbar("ابتدا وارد شوید")
            }else{
                LoadData.sendRateBaRetrofit(activity, clWifiState, username, sujeId, rateValue.toString(), inflatedview!!.txTedadRate,inflatedview!!.txRateAvg)
            }
        })


        //Toast.makeText(activity,sujeId,Toast.LENGTH_SHORT).show()
        LoadData.loadTotalRateAndRateAvgBaRetrofit(activity, clWifiState, sujeId, inflatedview!!.txTedadRate,inflatedview!!.txRateAvg)


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