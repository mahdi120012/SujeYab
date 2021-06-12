package ir.e.sujeyab.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ir.e.sujeyab.R
import ir.e.sujeyab.SabtSuje.snackbar
import kotlinx.android.synthetic.main.karbar_haghighi.view.*
import kotlinx.android.synthetic.main.login.*


class ShomareTelephoneForgetPassword : Fragment() {
    var inflatedview: View? = null
    var username: String? = null;
    var randomNumber: String? = null;


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        inflatedview = inflater.inflate(
            R.layout.shomare_telephone_forget_password,
            container,
            false
        )

        val tabLayout = (activity as Login).tabLayout
        tabLayout.visibility = View.GONE

        val viewPager = (activity as Login).viewPager
        viewPager.setUserInputEnabled(false);


        val txEdame = (activity as Login).txEdame

            txEdame!!.setOnClickListener {

                if (inflatedview!!.etUsername.text.toString().length<10){

                    clcl.snackbar("شماره تلفن همراه خود را به درستی وارد فرمایید")

                }else{
                    val random_int = Math.floor(Math.random() * (9999 - 1000 + 1) + 1000).toInt()

                    val bundle = Bundle()
                    bundle.putString("random_number", random_int.toString())
                    bundle.putString("username", "0"+inflatedview!!.etUsername.text.toString())

                    val myFragment: Fragment = TaeidShomareTelepoheForgetPassword()
                    myFragment.arguments = bundle
                    (activity as Login).supportFragmentManager.beginTransaction()
                        .replace(R.id.clcl, myFragment).addToBackStack(null).commit()

                }
            }


        return inflatedview
    }

}