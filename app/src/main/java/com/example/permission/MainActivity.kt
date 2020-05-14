package com.example.permission

import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.security.Permission
import android.util.Log.d as d1

class MainActivity : AppCompatActivity() {
   private val TAG="Permission"
    private val REQUEST_RECORD_CODE=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setpermission()
    }

    private fun setpermission() {
        val permission =
            ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECORD_AUDIO)

        if (permission!=PackageManager.PERMISSION_GRANTED)
        {
            Log.d(TAG,"Permission denied")
        }

        if (ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.RECORD_AUDIO))
        {
            val builder=AlertDialog.Builder(this)
            builder.setMessage("Permission to access the microphone is required for this app to record audio")
            builder.setTitle("Permission Required")
            builder.setPositiveButton("OK")
            {
                dialog, which ->
                Log.d(TAG,"Clicked")
                makeRequest()
            }
            val dialog=builder.create()
            dialog.show()
        }
        else
        {
           makeRequest()
        }
    }
          private fun makeRequest()
          {
              ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.RECORD_AUDIO),
              REQUEST_RECORD_CODE)
          }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode)
        {
            REQUEST_RECORD_CODE ->{
                if (grantResults.isEmpty()|| grantResults[0]!=PackageManager.PERMISSION_GRANTED)
                {
                    Log.d(TAG,"Permission have been denied by user")
                }
                else
                {
                    Log.d(TAG,"Permission have been granted by user")
                }
            }
        }

    }
}