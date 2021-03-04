package ir.e.sujeyab.SabtSuje

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Xml
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.e.sujeyab.Controller.ApiForUpload
import ir.e.sujeyab.R
import kotlinx.android.synthetic.main.vijegiha_fr.*
import kotlinx.android.synthetic.main.vijegiha_fr.view.*
import kotlinx.android.synthetic.main.vijegiha_fr.view.clcl
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream


class VijegiHaFr : Fragment(), UploadRequestBody.UploadCallback {
    var inflatedview: View? = null
    private var selectedImageUri: Uri? = null
    companion object {
        const val REQUEST_CODE_PICK_IMAGE = 101
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        inflatedview = inflater.inflate(R.layout.vijegiha_fr, container, false)

        /*var t = inflatedview!!.txEdame
        t.setOnClickListener {
            activity!!.viewPager.setCurrentItem(1)
            //LoadData.addSujeJadid(activity,clWifiState,etOnvan,etMozo,etTozihat)
        }*/

        inflatedview!!.clEntakhabTasvirSuje.setOnClickListener {
            //کد زیر برای دسترسی دادن به حافظست
            /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(
                    activity!!,
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
                val intent = Intent(
                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.parse("package:packageName"))
                activity!!.finish()
                startActivity(intent)
                return@setOnClickListener
            }else{
                openImageChooser()
            }*/
            openImageChooser()
        }

        inflatedview!!.button_upload.setOnClickListener {
            inflatedview!!.progress_bar.visibility = View.VISIBLE
            uploadImage()
        }


        return inflatedview
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
            inflatedview!!.clTasvirSuje.visibility = View.VISIBLE
            inflatedview!!. button_upload.visibility = View.VISIBLE
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
        ApiForUpload().uploadImage(MultipartBody.Part.createFormData("image",file.name, body),
                                   RequestBody.create(MediaType.parse("multipart/form-data"), "json"),
                                   RequestBody.create(MediaType.parse("multipart/form-data"), "متن تست"))
            .enqueue(object : Callback<UploadResponse> {

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