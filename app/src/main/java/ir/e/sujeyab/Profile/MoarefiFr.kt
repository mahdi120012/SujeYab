 package ir.e.sujeyab.Profile

import android.app.Activity
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
import kotlinx.android.synthetic.main.moarefi_profile_fr.*
import kotlinx.android.synthetic.main.moarefi_profile_fr.view.*
import kotlinx.android.synthetic.main.net_connection.*
import kotlinx.android.synthetic.main.net_connection.view.*
import kotlinx.android.synthetic.main.suje_main_act.*
import java.util.*
import kotlin.collections.ArrayList


 class MoarefiFr : Fragment() {
    var inflatedview: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {

        inflatedview = inflater.inflate(R.layout.moarefi_profile_fr, container, false)

        var username_ferestande:String = activity!!.intent.extras!!.getString("username_ferestande").toString()
        var userNameFamily:String = activity!!.intent.extras!!.getString("name_family").toString()

        //Toast.makeText(activity,username_ferestande,Toast.LENGTH_SHORT).show()

        (activity as MainActProfile).txTitlePage.setText(userNameFamily)

        LoadData.loadMoshakhasatDarSafheProfile(activity, inflatedview!!.clWifiState,username_ferestande,
            inflatedview!!.imgProfile,inflatedview!!.txNameKarbar,inflatedview!!.txUsername,
            inflatedview!!.txMatnKholaseMoarefi,inflatedview!!.txMadrakTahsili1,inflatedview!!.txMadrakTahsili2,
            inflatedview!!.txTakhasos, inflatedview!!.txEmail, inflatedview!!.txTelephone, inflatedview!!.imgCall)

        return inflatedview
    }
}