package com.travelpayouts.travel.app

import android.widget.Toast
import com.onesignal.OneSignal
import com.travelpayouts.travel.sdk.Config
import com.travelpayouts.travel.sdk.Config.Tab
import com.travelpayouts.travel.sdk.TravelApp
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.YandexMetricaConfig

class App : TravelApp() {
    override val config: Config = Config(
        tabs = setOf(
            Tab.Flights,
            Tab.Hotels,
            Tab.RentalCars("https://tp.media/r?marker=220738&p=3555&u=https%3A%2F%2Fwww.discovercars.com%2F"),
            Tab.AppInfo
        )
    )

    override fun onCreate() {
        super.onCreate()
        val config = YandexMetricaConfig.newConfigBuilder(getString(R.string.yandex_metrica_api))
            .build() // Creating an extended library configuration
        YandexMetrica.activate(applicationContext, config) // Initializing the AppMetrica SDK
        YandexMetrica.enableActivityAutoTracking(this) // Automatic tracking of user activity

        // OneSignal Initialization
        OneSignal.startInit(this)
            .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
            .unsubscribeWhenNotificationsAreDisabled(true)
            .init()
    }
}
