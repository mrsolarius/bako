package app.bako

import android.app.Application
import android.content.Context

/**
 * Bako
 * Cette Classe permet d'acéder au context d'ou que l'on soit
 */
class Bako : Application() {
    override fun onCreate() {
        instance = this
        super.onCreate()
    }

    companion object {
        //permet de récupérer une instance
        var instance: Bako? = null
            private set
        //permet de récupérer un context
        val context: Context?
            get() = instance
    }
}