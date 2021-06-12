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
import kotlinx.android.synthetic.main.change_password.*
import kotlinx.android.synthetic.main.karbar_haghighi.*
import kotlinx.android.synthetic.main.karbar_haghighi.clcl
import kotlinx.android.synthetic.main.karbar_haghighi.etPassword
import kotlinx.android.synthetic.main.karbar_haghighi.view.*
import kotlinx.android.synthetic.main.karbar_hoghoghi.view.*
import kotlinx.android.synthetic.main.karbar_hoghoghi.view.clVorodBaGoogle
import kotlinx.android.synthetic.main.karbar_hoghoghi.view.etPassword
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.net_connection.*
import kotlinx.android.synthetic.main.takmil_etelaat.view.*


class ChangePassword : Fragment() {
    var inflatedview: View? = null
    var username: String? = null;

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        inflatedview = inflater.inflate(R.layout.change_password, container, false)

        val bundle = this.arguments

        if (bundle != null) {
            username = bundle.getString("username")
        }

        val txEdame = (activity as Login).txEdame

        txEdame.setOnClickListener {

            if (etPassword.text.toString().length < 6){
                clcl.snackbar("کلمه عبور جدید میبایست حداقل ۶ حرف باشد")
            }else if (etPassword.text.toString() != etRePassword.text.toString()){
                    clcl.snackbar("کلمه عبور و تکرار آن یکسان نیستند")
                }else{
                LoadData.editPassword(activity, username, etPassword.text.toString())
                }
        }

        return inflatedview
    }

}