package com.oracle.dv

import android.content.Context
import android.media.AudioManager
import android.media.ToneGenerator
import android.media.ToneGenerator.TONE_CDMA_PIP
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    lateinit var appContext: Context
    lateinit var device: UiDevice

    @Before
    fun setup() {
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    }


    @Test
    fun otherTest() {
        val user = "Mendoza"
        device.pressHome()
        device.findObject(By.text("WhatsApp")).clickAndWait(Until.newWindow(), 300)
        Thread.sleep(1000)
        device.findObject(By.desc("New chat")).clickAndWait(Until.newWindow(), 300)
        Thread.sleep(1000)
        UiScrollable(UiSelector().className("android.widget.ListView")).getChildByText(
            UiSelector(),
            user
        )
        Thread.sleep(1000)
        val userItem = device.findObject(By.text(user))
        userItem.clickAndWait(Until.newWindow(), 200)
        while (true) {
            if (device.findObject(UiSelector().textContains("online")).exists()) {
                beep()
                Thread.sleep(3000)
            }
            Thread.sleep(400)
        }
    }

}

val toneGen1 = ToneGenerator(AudioManager.STREAM_MUSIC, 85)
fun beep() {
    toneGen1.startTone(TONE_CDMA_PIP, 200)
}