 package ir.e.sujeyab.Profile

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import ir.e.sujeyab.CustomClasses.Recyclerview
import ir.e.sujeyab.CustomClasses.SharedPrefClass
import ir.e.sujeyab.LoadData
import ir.e.sujeyab.R
import ir.e.sujeyab.SabtSuje.snackbar
import ir.e.sujeyab.adapters.RecyclerAdapterComments
import ir.e.sujeyab.login.Login
import ir.e.sujeyab.models.CommentsModel
import kotlinx.android.synthetic.main.comment_fr.*
import kotlinx.android.synthetic.main.comment_fr.view.*
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.net_connection.view.*

 class SujeHaFr : Fragment() {
    var inflatedview: View? = null
    private var rAdapter: RecyclerAdapterComments? = null
    private var rModels: ArrayList<CommentsModel>? = null
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        inflatedview = inflater.inflate(R.layout.comment_fr, container, false)
        var post_id:String = activity!!.intent.extras!!.getString("id").toString()

        rModels = ArrayList()
        rAdapter = RecyclerAdapterComments("comments", activity, rModels, rAdapter)
        Recyclerview.defineRecyclerViewVerticalComment(activity, inflatedview!!.rv1, rAdapter, rModels)
        LoadData.loadCommentsBaRetrofit(activity,inflatedview!!.clWifiState,rModels,rAdapter, post_id)
        var username = SharedPrefClass.getUserId(activity,"user")

        inflatedview!!.llsend.setOnClickListener {

            if (username == "" || username == null){
                inflatedview!!.clcl.snackbar("برای ارسال نظر ابتدا وارد شوید")
            }else{

                if (etMatnComment.text.toString() == ""){
                    inflatedview!!.clcl.snackbar("طول نظر خیلی کوتاه است")
                }else{
                    LoadData.sendCommentsBaRetrofit(activity,inflatedview!!.clWifiState,rModels,rAdapter, username, post_id, etMatnComment, inflatedview!!.rv1)
                }
            }
        }

        return inflatedview
}

 }