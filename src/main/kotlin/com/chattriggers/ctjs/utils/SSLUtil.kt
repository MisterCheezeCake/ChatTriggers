package com.chattriggers.ctjs.utils

import java.security.KeyStore
import javax.net.ssl.KeyManagerFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory
object SSLUtil {
    private var ctx: SSLContext? = null
    fun getSSLContext(): SSLContext? {
        return ctx
    }
    init {
        try {
            val myKeyStore = KeyStore.getInstance("JKS")
            myKeyStore.load(SSLUtil::class.java.getResourceAsStream("/ctjskeystore.jks"), "changeit".toCharArray())
            val kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm())
            val tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
            kmf.init(myKeyStore, null)
            tmf.init(myKeyStore)
            ctx = SSLContext.getInstance("TLS")
            ctx?.init(kmf.keyManagers, tmf.trustManagers, null)
        } catch (e: Exception) {
            println("CTJS failed to load keystore. Web requests may fail on older Java versions")
            e.printStackTrace()
        }
    }
}