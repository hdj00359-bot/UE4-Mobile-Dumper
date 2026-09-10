package com.bizzra.dumper

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings

class MainActivity : Activity() {

    private var dumperStarted = false
    private var permissionScreenOpen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestOverlayPermissionOrStart()
    }

    override fun onResume() {
        super.onResume()
        requestOverlayPermissionOrStart()
    }

    private fun requestOverlayPermissionOrStart() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M &&
            !Settings.canDrawOverlays(this)
        ) {
            if (permissionScreenOpen) return
            permissionScreenOpen = true
            startActivity(
                Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:$packageName")
                )
            )
            return
        }

        permissionScreenOpen = false
        if (!dumperStarted) {
            if (DumperCore.Start(this)) {
                dumperStarted = true
                startService(Intent(this, FloatingService::class.java))
            }
        }
    }
}