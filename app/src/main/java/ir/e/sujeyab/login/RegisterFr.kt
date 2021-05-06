package ir.e.sujeyab.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.e.sujeyab.LoadData
import ir.e.sujeyab.SabtSuje.snackbar
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.login.clcl
import kotlinx.android.synthetic.main.net_connection.*
import kotlinx.android.synthetic.main.register.*
import kotlinx.android.synthetic.main.register.view.*


class RegisterFr : Fragment() {

    var inflatedview: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        inflatedview = inflater.inflate(ir.e.sujeyab.R.layout.register, container, false)


        val tabLayout = (activity as Login).tabLayout
        tabLayout.visibility = View.GONE

        val viewPager = (activity as Login).viewPager
        viewPager.setUserInputEnabled(false);


        val txEdame = (activity as Login).txEdame

        txEdame!!.setOnClickListener {

            var username = etUsername.text.toString()
            var password = etPassword.text.toString()

            if (username == ""){
                clcl.snackbar("شماره تلفن همراه خود را وارد کنید")

            }else if (username.length < 10) {
                clcl.snackbar("شماره تلفن همراه خود را به درستی وارد کنید")

            }else if (password.length < 6){
                clcl.snackbar("رمز عبور می بایست حداقل ۶ حرف باشد")

            }else{


/*
                val fr: Fragment = TakmilEtelaat()
                //fr.arguments = args
                val fm = fragmentManager
                val fragmentTransaction = fm!!.beginTransaction()
                fragmentTransaction.replace(ir.e.sujeyab.R.id.clcl, fr)
                fragmentTransaction.commit()*/

                LoadData.registerBaRetrifit(activity,clcl,clWifiState,"0"+username,password)

            }




        }


        inflatedview!!.clVorodBaNamKarbari.setOnClickListener {

            val i = Intent(activity, Login::class.java)
            /*i.putExtra("PersonID", personID)*/
            startActivity(i)
            activity!!.finish()

        }

        return inflatedview


    }

}