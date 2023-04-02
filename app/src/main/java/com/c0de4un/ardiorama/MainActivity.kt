package com.c0de4un.ardiorama

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.SurfaceTexture
import android.hardware.camera2.CameraDevice
import android.hardware.camera2.CameraManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.view.Surface
import android.view.TextureView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.c0de4un.ardiorama.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var cameraDevice: CameraDevice
    private lateinit var handler: Handler
    private lateinit var binding: ActivityMainBinding
    private lateinit var textureView: TextureView
    private lateinit var cameraManager: CameraManager

    private val cameraPermissionCode = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getPermission()

        val handlerThread = HandlerThread("videoThread")
        handler = Handler(handlerThread.looper)

        cameraManager = getSystemService(CAMERA_SERVICE) as CameraManager

        textureView = findViewById(R.id.textureView)
        textureView.surfaceTextureListener = object:TextureView.SurfaceTextureListener {
            override fun onSurfaceTextureAvailable(
                surface: SurfaceTexture,
                width: Int,
                height: Int
            ) {
                openCamera()
            }

            override fun onSurfaceTextureSizeChanged(
                surface: SurfaceTexture,
                width: Int,
                height: Int
            ) {
            }

            override fun onSurfaceTextureDestroyed(surface: SurfaceTexture): Boolean {
                return false
            }

            override fun onSurfaceTextureUpdated(surface: SurfaceTexture) {
            }

        }

        // Example of a call to a native method
        //binding.sampleText.text = stringFromJNI()
    }

    /**
     * A native method that is implemented by the 'ardiorama' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'ardiorama' library on application startup.
        init {
            System.loadLibrary("ardiorama")
        }
    }

    private fun getPermission()
    {
        if ( ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ) {
            requestPermissions(arrayOf(android.Manifest.permission.CAMERA), cameraPermissionCode)
        }
    }

    @SuppressLint("MissingPermission")
    private fun openCamera() {
        cameraManager.openCamera(
            cameraManager.cameraIdList[0],
            object:CameraDevice.StateCallback() {
                override fun onOpened(camera: CameraDevice) {
                    cameraDevice = camera

                    val surfaceTexture = textureView.surfaceTexture
                    val surface = Surface(surfaceTexture)
                }

                override fun onDisconnected(camera: CameraDevice) {
                }

                override fun onError(camera: CameraDevice, error: Int) {
                }
            },
            handler
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == cameraPermissionCode) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                getPermission()
            }
        }
    }
}