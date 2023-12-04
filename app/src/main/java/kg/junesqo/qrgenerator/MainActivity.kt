package kg.junesqo.qrgenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder

class MainActivity : AppCompatActivity() {

    var qrImage: ImageView? = null
    var generateButton: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        qrImage = findViewById(R.id.qrImage)
        generateButton = findViewById(R.id.generateButton)
        generateButton?.setOnClickListener {
            generatorQrCode("Image Processing")
        }

    }

    private fun generatorQrCode(text: String){
        val qrGenerator = QRGEncoder(text, null, QRGContents.Type.TEXT, 500)
        try {
            val bMap = qrGenerator.bitmap
            qrImage?.setImageBitmap(bMap)
        } catch (e: Exception){

        }
    }
}