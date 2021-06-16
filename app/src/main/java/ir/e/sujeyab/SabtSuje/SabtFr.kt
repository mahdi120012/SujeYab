package ir.e.sujeyab.SabtSuje

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ir.e.sujeyab.R
import ir.e.sujeyab.login.Login
import ir.e.sujeyab.login.TaeidShomareTelepoheForgetPassword
import ir.e.sujeyab.pishkhan.Pishkhan
import kotlinx.android.synthetic.main.button_sabt_fori_suje.*
import kotlinx.android.synthetic.main.button_sabt_fori_suje.view.*
import kotlinx.android.synthetic.main.karbar_haghighi.view.*
import kotlinx.android.synthetic.main.main_cat1.view.*
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

        inflatedview!!.clBazgasht.visibility = View.GONE



        inflatedview!!.clEdame.setOnClickListener {
            val intent = Intent(activity, Pishkhan::class.java)
            activity!!.startActivity(intent)

            val activity = activity as AppCompatActivity
            activity.finish()
        }


        inflatedview!!.clEdameVaSabtTozihat.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("post_id", "446")

            val myFragment: Fragment = SabtTozihatRoyeAxFilm_fr()
            myFragment.arguments = bundle
            activity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.clcl, myFragment).addToBackStack(null).commit()
        }

        //(activity!!.txEdame)!!.setText("بازگشت به پیشخوان")
        inflatedview!!.txEdame.setText("بازگشت به پیشخوان")

        (activity!!.imgBack).setOnClickListener {
            /*Snackbar snackbar = Snackbar.make(clcl, "مشخصات شما با موفقیت ویرایش شد", Snackbar.LENGTH_LONG);
                snackbar.show();*/
            val intent = Intent(activity, Pishkhan::class.java)
            activity!!.startActivity(intent)

            val activity = activity as AppCompatActivity
            activity.finish()
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