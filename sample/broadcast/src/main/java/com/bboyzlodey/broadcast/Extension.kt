package com.bboyzlodey.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter


fun Context.onBroadcastReceiveString(
    action: String = DEFAULT_ACTION,
    extraKey: String = DEFAULT_EXTRA_KEY,
    consume: (String) -> Unit
) {
    val receiver = createBroadcastReceiver(extraKey to consume)
    val intentFilter = createIntentFilter(action)
    registerReceiver(receiver, intentFilter)
}

private fun createBroadcastReceiver(
    vararg payloads: Pair<PayloadKey, PayloadConsumer>
): BroadcastReceiver {
    return BroadcastReceiverBuilder().apply {
        payloads.forEach {
            addPayload(it.first, it.second)
        }
    }.build()
}

private fun createIntentFilter(action: String): IntentFilter {
    return IntentFilter(action)
}