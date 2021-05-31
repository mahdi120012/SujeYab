 package ir.e.sujeyab.Profile

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
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.squareup.picasso.Picasso
import ir.e.sujeyab.CustomClasses.SharedPrefClass
import ir.e.sujeyab.CustomClasses.TimeKononi
import ir.e.sujeyab.LoadData
import ir.e.sujeyab.R
import ir.e.sujeyab.SabtSuje.snackbar
import ir.e.sujeyab.models.TasavirSujeModel
import kotlinx.android.synthetic.main.comment_fr.view.*
import kotlinx.android.synthetic.main.moarefi_fr.*
import kotlinx.android.synthetic.main.moarefi_fr.view.*
import kotlinx.android.synthetic.main.moarefi_fr.view.clcl
import kotlinx.android.synthetic.main.net_connection.*
import kotlinx.android.synthetic.main.slider_ba_hashiye.*
import kotlinx.android.synthetic.main.slider_ba_hashiye.view.*
import java.util.*
import kotlin.collections.ArrayList


 class MoarefiFr : Fragment() {
    var inflatedview: View? = null

    private val ImgArray = ArrayList<TasavirSujeModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {

        inflatedview = inflater.inflate(R.layout.moarefi_profile_fr, container, false)

        var userId:String = activity!!.intent.extras!!.getString("id").toString()

        /*LoadData.LoadTasavirSujeBaVolley(activity, clWifiState,
                sujeId, inflatedview!!.viewPager1, inflatedview!!.indicator, ImgArray)*/
        //var username = SharedPrefClass.getUserId(activity, "user")
        return inflatedview
    }
}