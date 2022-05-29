## Description
Tool for debug application. Allows you to update any value dynamically

## How it work?
Based on BroadcastReceiver. Content is sent via adb. For your convenience, a script has already been written to send a broadcast message. You receive the message and extract the data.

## How it use?
1. Register BroadcastReceiver using extension function. [See extension function list](/lib/broadcast/src/main/java/com/bboyzlodey/broadcast/Extension.kt)
#### Example
Code sample below show how to register reciver. It`s easy.

```kotlin
// You must provide Context, because onBroadcastReceiveString method is extension of Context
private fun debugReceivingName(context: Context) : Flow<String> {
  val debugFlow = MutableSharedFlow<String>(replay = 0, extraBufferCapacity = 1)
  // method will called with default arguments
  context.onBroadcastReceiveString { receivedString ->
    // react to received data
    sharedFlow.tryEmit(receivedString)
  }
  return debugFlow
  // collect and update your state in outside this method
}
```

2. Send broadcast message with our script or adb command directly.
* Using our script:<br>
`./sendString DEFAULT_ACTION DEFAULT_PAYLOAD "Hello World!"`<br>

## Caution :warning:
**Don't use in production!** This tool can be applied for debuging and developing android application. All extensions call `registerBroadcastReceiver()`, but **not call** `unregisterBroadcastReceiver()` method! Care about your users in production.

## TODO:
- [ ] Add lifecycle for unregistering receiver
- [ ] Support multiple devices
- [ ] Add ability send broadcast with multiple payloads
- [ ] Add ability send json
- [ ] Create plugin for IDE
- [ ] Simplify script usage
