package com.example.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.example.data.TuitionRepository

object ExternalIntents {

    fun dialPhoneNumber(context: Context, phoneNumber: String): Boolean {
        val sanitized = phoneNumber.filter { it.isDigit() || it == '+' }
        if (sanitized.isEmpty()) return false
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$sanitized")
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        return launchSafely(context, intent)
    }

    fun openWhatsAppEnquiry(
        context: Context,
        message: String = TuitionRepository.DEFAULT_WHATSAPP_MESSAGE,
    ): Boolean {
        val encoded = Uri.encode(message)
        val url = "https://wa.me/${TuitionRepository.WHATSAPP_NUMBER_INTL}?text=$encoded"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        return launchSafely(context, intent)
    }

    fun openGoogleMapsLocation(context: Context): Boolean {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(TuitionRepository.GOOGLE_MAPS_URL)).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        return launchSafely(context, intent)
    }

    private fun launchSafely(context: Context, intent: Intent): Boolean {
        return try {
            context.startActivity(intent)
            true
        } catch (_: ActivityNotFoundException) {
            false
        } catch (_: SecurityException) {
            false
        }
    }
}
