package com.bizzra.dumper

import android.content.Context
import android.widget.Toast

object DumperCore {
    private var loaded = false

    @JvmStatic
    external fun CheckOverlayPermission(context: Context)

    @JvmStatic
    fun Start(context: Context): Boolean {
        return try {
            if (!loaded) {
                System.loadLibrary("Dumper")
                loaded = true
            }
            CheckOverlayPermission(context)
            true
        } catch (_: UnsatisfiedLinkError) {
            Toast.makeText(context, "当前 ABI 缺少 libDumper.so", Toast.LENGTH_LONG).show()
            false
        } catch (_: SecurityException) {
            Toast.makeText(context, "系统阻止了悬浮窗服务", Toast.LENGTH_LONG).show()
            false
        }
    }
}