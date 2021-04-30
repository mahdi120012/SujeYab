package ir.e.sujeyab.SabtSuje

import android.content.ContentResolver
import android.net.Uri
import android.os.Build
import android.provider.OpenableColumns
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.snackbar.Snackbar
import ir.e.sujeyab.R


fun View.snackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snackbar ->

        snackbar.setAction("باشه") {
            snackbar.dismiss()
        }

        val snackbarActionTextView =
            snackbar.view.findViewById(R.id.snackbar_action) as TextView
        snackbarActionTextView.textSize = 13f
        snackbarActionTextView.setTypeface(ResourcesCompat.getFont(context, R.font.iran_sans_bold));

        val mView: View = snackbar.getView()
        val mTextView =
            mView.findViewById<View>(R.id.snackbar_text) as TextView
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) mTextView.textAlignment =
            View.TEXT_ALIGNMENT_CENTER else mTextView.gravity =
            Gravity.CENTER_HORIZONTAL
        mTextView.textSize = 12f
        mTextView.setTypeface(ResourcesCompat.getFont(context, R.font.iran_sans));

    }.show()

}

fun ContentResolver.getFileName(fileUri: Uri): String {
    var name = ""
    val returnCursor = this.query(fileUri, null, null, null, null)
    if (returnCursor != null) {
        val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        returnCursor.moveToFirst()
        name = returnCursor.getString(nameIndex)
        returnCursor.close()
    }
    return name
}