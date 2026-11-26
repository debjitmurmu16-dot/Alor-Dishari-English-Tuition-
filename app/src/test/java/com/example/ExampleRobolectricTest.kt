package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.TuitionRepository
import com.example.ui.AlorDishariViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

    @Test
    fun `app name matches Alor Dishari`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("Alor Dishari", appName)
    }

    @Test
    fun `enquiry form validates required fields and builds WhatsApp message`() {
        val viewModel = AlorDishariViewModel()
        assertNull(viewModel.validateAndBuildEnquiryMessage())
        assertTrue(viewModel.uiState.value.enquiryForm.nameError)
        assertTrue(viewModel.uiState.value.enquiryForm.phoneError)

        viewModel.updateStudentName("Aarav Sen")
        viewModel.updateSelectedClass("Class X")
        viewModel.updatePhoneNumber("8145614076")
        viewModel.updateNotes("Interested in morning batch")

        val message = viewModel.validateAndBuildEnquiryMessage()
        assertNotNull(message)
        assertTrue(message!!.contains("Aarav Sen"))
        assertTrue(message.contains("Class X"))
        assertTrue(message.contains("8145614076"))
        assertTrue(message.contains("Alor Dishari English Tuition"))
    }

    @Test
    fun `repository exposes all three required class tracks and verified contact links`() {
        assertEquals(3, TuitionRepository.courseTracks.size)
        assertEquals("8145614076", TuitionRepository.PHONE_PRIMARY)
        assertEquals("7908523487", TuitionRepository.PHONE_SECONDARY)
        assertEquals("https://maps.app.goo.gl/ySxSMN26ySBZPNnK6", TuitionRepository.GOOGLE_MAPS_URL)
    }
}
