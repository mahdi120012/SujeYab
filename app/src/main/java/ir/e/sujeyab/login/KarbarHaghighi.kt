package ir.e.sujeyab.login

import android.os.Bundle
import android.text.InputFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import ir.e.sujeyab.CustomClasses.InputFilterMinMax
import ir.e.sujeyab.LoadData
import ir.e.sujeyab.R
import ir.e.sujeyab.SabtSuje.snackbar
import kotlinx.android.synthetic.main.karbar_haghighi.*
import kotlinx.android.synthetic.main.karbar_haghighi.clcl
import kotlinx.android.synthetic.main.karbar_haghighi.view.*
import kotlinx.android.synthetic.main.karbar_hoghoghi.view.*
import kotlinx.android.synthetic.main.karbar_hoghoghi.view.clVorodBaGoogle
import kotlinx.android.synthetic.main.karbar_hoghoghi.view.etPassword
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.net_connection.*
import kotlinx.android.synthetic.main.takmil_etelaat.view.*


class KarbarHaghighi : Fragment() {
    var inflatedview: View? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        inflatedview = inflater.inflate(R.layout.karbar_haghighi, container, false)

        inflatedview!!.clVorodBaGoogle.setOnClickListener {
            Toast.makeText(activity,"به زودی راه اندازی می شود",Toast.LENGTH_SHORT).show()
        }

        inflatedview!!.clForgetPassword.setOnClickListener {
            val fr: Fragment = ShomareTelephoneForgetPassword()
            //fr.arguments = args
            val fm = fragmentManager
            val fragmentTransaction = fm!!.beginTransaction()
            fragmentTransaction.replace(R.id.clcl, fr)
            fragmentTransaction.commit()
        }

        val clVorodBaNamKarbari = inflatedview!!.findViewById<View>(R.id.clVorodBaNamKarbari) as ConstraintLayout
        val etUsername = inflatedview!!.findViewById<View>(R.id.etUsername) as EditText



        val txEdame = (activity as Login).txEdame

        txEdame.setOnClickListener {
            //Toast.makeText(activity,"کاربر حقیقی",Toast.LENGTH_SHORT).show()
            /*val fr: Fragment = TaeidShomareTelepohe()
            //fr.arguments = args
            val fm = fragmentManager
            val fragmentTransaction =
                    fm!!.beginTransaction()
            fragmentTransaction.replace(R.id.clcl, fr)
            fragmentTransaction.commit()*/
            if (etUsername.text.toString().length<10 || etUsername.text.toString().length == 0){
                clcl.snackbar("شماره تلفن همراه یا رمز عبور خود را به درستی وارد فرمایید")
            }else {
                if (etUsername.text.toString().length<10){
                    clcl.snackbar("شماره تلفن همراه خود را به درستی وارد فرمایید")
                }else if (etPassword.text.toString().length == 0){
                    clcl.snackbar("رمز عبور خود را به درستی وارد فرمایید")
                }else{
                    LoadData.checUsernameExsist(activity,(activity as Login).clWifiState,etUsername, inflatedview!!.etPassword)
                }
            }

        }
        //inflatedview!!.etUsername.setFilters(arrayOf<InputFilter>(InputFilterMinMax(1, 9)))


        clVorodBaNamKarbari.setOnClickListener {
            val fr: Fragment = RegisterFr()
            //fr.arguments = args
            val fm = fragmentManager
            val fragmentTransaction = fm!!.beginTransaction()
            fragmentTransaction.replace(R.id.clcl, fr)
            fragmentTransaction.commit()
        }

        return inflatedview
    }

}