package ir.e.sujeyab.SabtSuje

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.e.sujeyab.R
import kotlinx.android.synthetic.main.sabt_fori_suje.*
import kotlinx.android.synthetic.main.sabt_fori_suje.clcl
import kotlinx.android.synthetic.main.tarh_suje_fr.*
import kotlinx.android.synthetic.main.tarh_suje_fr.view.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.greenrobot.eventbus.EventBus
import java.io.File


class TarhSujeFr : Fragment() {
    var inflatedview: View? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {



        inflatedview = inflater.inflate(R.layout.tarh_suje_fr, container, false)

        val event = ir.e.sujeyab.SabtSuje.MessageEvent()
        event.etOnvan = inflatedview!!.etOnvan
        EventBus.getDefault().post(event)


        (activity!!.txEdame)!!.setText("ادامه")
        (activity!!.clEdame)!!.setOnClickListener {
            if (activity!!.viewPager.currentItem == 3){
                if (etOnvan.text.toString() == "" || etMozo.text.toString() == "" || etTozihat.text.toString() == ""){
                    clcl.snackbar("لطفا همه فیلد ها را تکمیل نمایید")

                }else{
                activity!!.viewPager.setCurrentItem(2)
                }
                //LoadData.addSujeJadid(activity,clWifiState,etOnvan,etMozo,etTozihat)
            }else if (activity!!.viewPager.currentItem == 2){
                activity!!.viewPager.setCurrentItem(1)
                activity!!.txEdame.setText("ثبت سوژه")
            }else if (activity!!.viewPager.currentItem == 1){
                activity!!.viewPager.setCurrentItem(0)
            }
        }

        (activity!!.clBazgasht)!!.setOnClickListener {
            if (activity!!.viewPager.currentItem == 0){
                activity!!.viewPager.setCurrentItem(1)
                //LoadData.addSujeJadid(activity,clWifiState,etOnvan,etMozo,etTozihat)
            }else if (activity!!.viewPager.currentItem == 1){
                activity!!.viewPager.setCurrentItem(2)
            }else if (activity!!.viewPager.currentItem == 2){
                activity!!.viewPager.setCurrentItem(3)
            }
        }





        return inflatedview
    }

/*    public fun SelectImage() {
        ImagePicker.with(this)
                .setFolderMode(true)
                .setFolderTitle("Album")
                .setRootDirectoryName(Config.ROOT_DIR_DCIM)
                .setDirectoryName("Image Picker")
                .setMultipleMode(true)
                .setShowNumberIndicator(true)
                .setMaxSize(10)
                .setLimitMessage("You can select up to 10 images")
                .setRequestCode(100)
                .start();
    }*/

/*     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == Activity.RESULT_OK && data != null) {
            //the image URI
            val selectedImage: Uri? = data.data
            LoadData.uploadFile(activity,selectedImage, "My Image")
        }
    }*/


/*    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == Config.RC_PICK_IMAGES && resultCode == Activity.RESULT_OK && data != null){
            val img: ArrayList<Image> = data.getParcelableArrayListExtra(Config.EXTRA_IMAGES)!!

            for (image in img) {
                Picasso.get()
                        .load(image.uri)
                        .centerInside()
                        .fit()
                        .error(R.drawable.suje_icon)
                        .placeholder(R.drawable.suje_icon)
                        .into(inflatedview!!.imgImage)
                }

            //uploadImage(File(img.get(0).path))
        }
        super.onActivityResult(requestCode, resultCode, data)
    }*/


    private fun uploadImage1(image: File) {
            var requestBody:RequestBody =  RequestBody.create(MediaType.get("image/*"),image)
            var image:MultipartBody.Part = MultipartBody.Part.createFormData("img",image.name,requestBody)
            var user:RequestBody = RequestBody.create(MediaType.parse("text/plain"),"GrayMind")

    }



}