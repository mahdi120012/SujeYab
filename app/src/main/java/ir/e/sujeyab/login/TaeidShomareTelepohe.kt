package ir.e.sujeyab.login

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import ir.e.sujeyab.R
import kotlinx.android.synthetic.main.karbar_haghighi.*
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.taeid_shomare_telephone.*


class TaeidShomareTelepohe : Fragment() {
    var inflatedview: View? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        inflatedview = inflater.inflate(R.layout.taeid_shomare_telephone, container, false)


        /*object :CountDownTimer(90000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                txTimeRemain.setText("" + millisUntilFinished / 1000)
                //here you can have your logic to set text to edittext
            }

            override fun onFinish() {
                txTimeRemain.setText("تمام!")
            }

        }.start()*/

        val tabLayout = (activity as Login).tabLayout
        tabLayout.visibility = View.GONE

        val viewPager = (activity as Login).viewPager
        viewPager.setUserInputEnabled(false);


        val txEdame = (activity as Login).txEdame

            txEdame!!.setOnClickListener {

                var codeFaalSazi = etCodeFaalSazi.text.toString()
                if (codeFaalSazi == "1"){

                    val fr: Fragment = TakmilEtelaat()
                    //fr.arguments = args
                    val fm = fragmentManager
                    val fragmentTransaction = fm!!.beginTransaction()
                    fragmentTransaction.replace(ir.e.sujeyab.R.id.clcl, fr)
                    fragmentTransaction.commit()

                    }else{
                    Toast.makeText(activity,"کد فعالسازی را به درستی وارد نمایید",Toast.LENGTH_SHORT).show()
                }
            }


        return inflatedview
    }

}