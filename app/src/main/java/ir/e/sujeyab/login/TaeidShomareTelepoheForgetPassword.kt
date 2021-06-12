package ir.e.sujeyab.login

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ir.e.sujeyab.LoadData
import ir.e.sujeyab.R
import kotlinx.android.synthetic.main.karbar_haghighi.view.*
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.taeid_shomare_telephone.*
import kotlinx.android.synthetic.main.taeid_shomare_telephone.view.*


class TaeidShomareTelepoheForgetPassword : Fragment() {
    var inflatedview: View? = null
    var username: String? = null;
    var randomNumber: String? = null;


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        inflatedview = inflater.inflate(R.layout.taeid_shomare_telephone, container, false)

        val bundle = this.arguments

        if (bundle != null) {
             username = bundle.getString("username")
             randomNumber = bundle.getString("random_number")
        }

        LoadData.sendActivationCode(activity, username, randomNumber)





        object :CountDownTimer(90000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                inflatedview!!.txTimeRemain.setText("زمان باقی مانده: " + millisUntilFinished / 1000)
                //here you can have your logic to set text to edittext
            }

            override fun onFinish() {
                inflatedview!!.txTimeRemain.setText("تمام!")
                clErsalMojadad.visibility = View.VISIBLE
            }

        }.start()

        val tabLayout = (activity as Login).tabLayout
        tabLayout.visibility = View.GONE

        val viewPager = (activity as Login).viewPager
        viewPager.setUserInputEnabled(false);


        val txEdame = (activity as Login).txEdame

            txEdame!!.setOnClickListener {

                var codeFaalSazi = etCodeFaalSazi.text.toString()
                if (codeFaalSazi == randomNumber){


                    val bundle = Bundle()
                    bundle.putString("username", username)

                    Toast.makeText(activity, username,Toast.LENGTH_SHORT).show()

                    val myFragment: Fragment = ChangePassword()
                    myFragment.arguments = bundle
                    (activity as Login).supportFragmentManager.beginTransaction()
                        .replace(R.id.clcl, myFragment).addToBackStack(null).commit()

                }else{
                    Toast.makeText(
                        activity, "کد فعالسازی را به درستی وارد نمایید", Toast.LENGTH_SHORT
                    ).show()
                }
            }

        inflatedview!!.clErsalMojadad.setOnClickListener {

            randomNumber = Math.floor(Math.random() * (9999 - 1000 + 1) + 1000).toInt().toString()
            LoadData.sendActivationCode(activity, username, randomNumber)
            clErsalMojadad.visibility = View.GONE

            object :CountDownTimer(90000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    inflatedview!!.txTimeRemain.setText("زمان باقی مانده: " + millisUntilFinished / 1000)
                    //here you can have your logic to set text to edittext
                }

                override fun onFinish() {
                    inflatedview!!.txTimeRemain.setText("تمام!")
                    clErsalMojadad.visibility = View.VISIBLE
                }

            }.start()

        }


        return inflatedview
    }

}