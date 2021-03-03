package ir.e.sujeyab.SabtSuje

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.e.sujeyab.Controller.ApiForUpload
import ir.e.sujeyab.CustomClasses.SharedPrefClass
import ir.e.sujeyab.LoadData
import ir.e.sujeyab.R
import kotlinx.android.synthetic.main.net_connection.*
import kotlinx.android.synthetic.main.sabt_fori_suje.*
import kotlinx.android.synthetic.main.tarh_suje_fr.*
import kotlinx.android.synthetic.main.tarh_suje_fr.view.*
import kotlinx.android.synthetic.main.vorod_fr.*
import kotlinx.android.synthetic.main.vorod_fr.view.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class VorodFr : Fragment() {
    var inflatedview: View? = null


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        inflatedview = inflater.inflate(R.layout.vorod_fr, container, false)
        /*(activity!!.txEdame)!!.setText("ثبت سوژه")
        (activity!!.txEdame)!!.setOnClickListener {
            activity!!.viewPager.setCurrentItem(0)
        }*/

        var username = SharedPrefClass.getUserId(activity,"user")
        if (username == ""){
            inflatedview!!.txPhoneNumber.setText("09397310149")
        }else{
            inflatedview!!.txPhoneNumber.setText(username)
        }


        return inflatedview
    }


}