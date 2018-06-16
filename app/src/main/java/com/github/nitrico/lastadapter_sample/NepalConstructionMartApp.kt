package com.github.nitrico.lastadapter_sample

import android.app.Application
import com.aadira.library.Enums.SigningMethod
import com.aadira.library.Main.WCBuilder
import com.aadira.library.Main.WooCommerce

// set android:name for application in AndroidManifest.xml
// that will intantiate this class and intantiate the single ton
// for WooCommerge object with the details defined in this class
class NepalConstructionMartApp: Application() {

    override fun onCreate() {
        super.onCreate()

        val builder = WCBuilder()
        builder.setIsHttps(false)
        builder.baseUrl = "www.nepalconstructionmart.com"
        builder.signing_method = SigningMethod.HMACSHA1
        builder.wc_key = "ck_b3b26bf14e1193829ec21ca2c8ae355be3855fc6"
        builder.wc_secret = "cs_69ddf520c834a509c41557c4ab145b8c24e0754e"
        WooCommerce.getInstance().initialize(builder)

        // LeakCanary.install(this);

        println(WooCommerce.getInstance().toString())
    }
}