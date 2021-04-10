package ir.e.sujeyab.login

import android.R
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import ir.e.sujeyab.LoadData
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.login.clcl
import kotlinx.android.synthetic.main.login.tabLayout
import kotlinx.android.synthetic.main.login.viewPager
import kotlinx.android.synthetic.main.net_connection.*
import kotlinx.android.synthetic.main.register.*


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
                Toast.makeText(activity,"شماره تلفن همراه خود را وارد کنید",Toast.LENGTH_SHORT).show()
            }else if (username.length < 11) {
                Toast.makeText(activity,"شماره تلفن همراه خود را به درستی وارد کنید",Toast.LENGTH_SHORT).show()
            }else if (password.length < 6){
                Toast.makeText(activity,"رمز عبور می بایست حداقل 6 حرف باشد",Toast.LENGTH_SHORT).show()
            }else{


/*
                val fr: Fragment = TakmilEtelaat()
                //fr.arguments = args
                val fm = fragmentManager
                val fragmentTransaction = fm!!.beginTransaction()
                fragmentTransaction.replace(ir.e.sujeyab.R.id.clcl, fr)
                fragmentTransaction.commit()*/

                LoadData.registerBaRetrifit(activity,clcl,clWifiState,username,password)

            }




        }

        return inflatedview


    }

}