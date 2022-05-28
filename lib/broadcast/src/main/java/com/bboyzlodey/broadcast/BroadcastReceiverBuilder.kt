package com.bboyzlodey.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

internal class BroadcastReceiverBuilder {

    private val payloadMap: MutableMap<PayloadKey, PayloadConsumer> = mutableMapOf()

    fun addPayload(key: PayloadKey, consumer: PayloadConsumer) {
        payloadMap[key] = consumer
    }

    fun removePayload(key: PayloadKey) {
        payloadMap.remove(key)
    }

    fun build(): BroadcastReceiver {
        return object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                payloadMap.forEach { (key, consumer) ->
                    intent.getStringExtra(key)?.let { consumer.invoke(it) }
                        ?: throw IllegalStateException("Data of type String missing for extra key: $key")
                }
            }
        }
    }
}

typealias PayloadKey = String
typealias PayloadConsumer = (String) -> Unit