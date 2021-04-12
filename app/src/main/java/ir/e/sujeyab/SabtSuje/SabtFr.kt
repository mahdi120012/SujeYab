package ir.e.sujeyab.SabtSuje

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import ir.e.sujeyab.R
import ir.e.sujeyab.pishkhan.Pishkhan
import kotlinx.android.synthetic.main.button_sabt_fori_suje.view.*
import kotlinx.android.synthetic.main.sabt_fori_suje.*
import kotlinx.android.synthetic.main.sabt_fr.view.*


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
        ;
        //inflatedview!!.txShenaseRahgiri.setText(getActivity()!!.getIntent().getExtras()!!.getString("shenase_rahgiri"))
        inflatedview!!.txBazgashtBePishkhan.setOnClickListener {

            val intent = Intent(activity, Pishkhan::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)

        }


        inflatedview!!.clEdame.setOnClickListener {
            /*if (inflatedview!!.etOnvan.text.toString() == "" || inflatedview!!.etMozo.text.toString() == "" || inflatedview!!.etTozihat.text.toString() == ""){
                clcl.snackbar("لطفا همه فیلد ها را تکمیل نمایید")

            }else{
                activity!!.viewPager.setCurrentItem(2)
            }*/

            Toast.makeText(activity, "ثبت", Toast.LENGTH_LONG).show()
        }


        inflatedview!!.clBazgasht.setOnClickListener {
            if (((activity)!!.tabLayout.getTabAt(1)!!.view as LinearLayout).visibility == View.GONE) {
                activity!!.viewPager.setCurrentItem(2)
            } else {
                activity!!.viewPager.setCurrentItem(1)
            }
        }

        return inflatedview
    }
}