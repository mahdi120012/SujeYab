package ir.e.sujeyab.SabtSuje

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import ir.e.sujeyab.Controller.ApiForUpload
import ir.e.sujeyab.LoadData
import ir.e.sujeyab.R
import kotlinx.android.synthetic.main.button_sabt_fori_suje.*
import kotlinx.android.synthetic.main.button_sabt_fori_suje.view.*
import kotlinx.android.synthetic.main.net_connection.*
import kotlinx.android.synthetic.main.sabt_fori_suje.*
import kotlinx.android.synthetic.main.tarh_suje_fr.*
import kotlinx.android.synthetic.main.tarh_suje_fr.view.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

 class SabtFr : Fragment() {
    var inflatedview: View? = null


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        inflatedview = inflater.inflate(R.layout.sabt_fr, container, false)
        /*(activity!!.txEdame)!!.setOnClickListener {
            LoadData.addSujeJadid(activity,clWifiState,etOnvan,etMozo,etTozihat)
        }*/

        inflatedview!!.clEdame.setOnClickListener {
            /*if (inflatedview!!.etOnvan.text.toString() == "" || inflatedview!!.etMozo.text.toString() == "" || inflatedview!!.etTozihat.text.toString() == ""){
                clcl.snackbar("لطفا همه فیلد ها را تکمیل نمایید")

            }else{
                activity!!.viewPager.setCurrentItem(2)
            }*/

            Toast.makeText(activity,"ثبت", Toast.LENGTH_LONG).show()
        }

        inflatedview!!.clBazgasht.setOnClickListener {
            if(((activity)!!.tabLayout.getTabAt(1)!!.view as LinearLayout).visibility == View.GONE){
                activity!!.viewPager.setCurrentItem(2)
            }else{
                activity!!.viewPager.setCurrentItem(1)
            }
        }

        return inflatedview
    }


}