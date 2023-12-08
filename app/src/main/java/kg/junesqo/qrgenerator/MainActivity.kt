package kg.junesqo.qrgenerator

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    var qrImage: ImageView? = null
    var generateButton: Button? = null
    var scannerButton: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        qrImage = findViewById(R.id.qrImage)
        generateButton = findViewById(R.id.generateButton)
        generateButton?.setOnClickListener {
            generatorQrCode("Image Processing")
        }

        scannerButton = findViewById(R.id.scannerButton)
        scannerButton?.setOnClickListener {
            checkCameraPermission()
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

    private fun checkCameraPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 12)

        } else {
            startActivity(Intent(this, ScannerActivity::class.java))
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 12){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                startActivity(Intent(this,ScannerActivity::class.java))
            }
        }
    }
}