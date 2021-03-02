package ir.e.sujeyab.SabtSuje

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.e.sujeyab.Controller.ApiForUpload
import ir.e.sujeyab.LoadData
import ir.e.sujeyab.R
import kotlinx.android.synthetic.main.net_connection.*
import kotlinx.android.synthetic.main.sabt_fori_suje.*
import kotlinx.android.synthetic.main.tarh_suje_fr.*
import kotlinx.android.synthetic.main.tarh_suje_fr.view.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class TarhSujeFr : Fragment(), UploadRequestBody.UploadCallback {
    var inflatedview: View? = null
    private var selectedImageUri: Uri? = null
    companion object {
        const val REQUEST_CODE_PICK_IMAGE = 101
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        inflatedview = inflater.inflate(R.layout.tarh_suje_fr, container, false)
        (activity!!.txEdame)!!.setText("ارسال")
        (activity!!.txEdame)!!.setOnClickListener {
            LoadData.addSujeJadid(activity,clWifiState,etOnvan,etMozo,etTozihat)
        }
        inflatedview!!.image_view.setOnClickListener {
            openImageChooser()
        }

        inflatedview!!.button_upload.setOnClickListener {
            uploadImage()
            /*val i = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(i, 100)*/

            //SelectImage()
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



    private fun openImageChooser() {
        Intent(Intent.ACTION_PICK).also {
            it.type = "image/*"
            val mimeTypes = arrayOf("image/jpeg", "image/png")
            it.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
            startActivityForResult(it, REQUEST_CODE_PICK_IMAGE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_PICK_IMAGE -> {
                    selectedImageUri = data?.data
                    image_view.setImageURI(selectedImageUri)
                }
            }
        }
    }

    private fun uploadImage() {
        if (selectedImageUri == null) {
            inflatedview!!.clcl.snackbar("Select an Image First")
            return
        }

        val parcelFileDescriptor =
                activity!!.contentResolver.openFileDescriptor(selectedImageUri!!, "r", null) ?: return

        val inputStream = FileInputStream(parcelFileDescriptor.fileDescriptor)
        val file = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(selectedImageUri!!))
        val outputStream = FileOutputStream(file)
        inputStream.copyTo(outputStream)

        progress_bar.progress = 0
        val body = UploadRequestBody(file, "image", this)
        ApiForUpload().uploadImage(
                MultipartBody.Part.createFormData(
                        "image",
                        file.name,
                        body
                ),
                RequestBody.create(MediaType.parse("multipart/form-data"), "json")
        ).enqueue(object : Callback<UploadResponse> {
            override fun onFailure(call: Call<UploadResponse>, t: Throwable) {
                inflatedview!!.clcl.snackbar(t.message!!)
                progress_bar.progress = 0
            }

            override fun onResponse(
                    call: Call<UploadResponse>,
                    response: Response<UploadResponse>
            ) {
                response.body()?.let {
                    inflatedview!!.clcl.snackbar(it.message)
                    progress_bar.progress = 100
                }
            }
        })

    }

     override fun onProgressUpdate(percentage: Int) {
        progress_bar.progress = percentage
    }




}