README.md
🚀 MiniRevenueCatSDK
A Lightweight Kotlin Multiplatform (KMP) Subscription & Entitlement SDK

Architected in the style of RevenueCat’s production SDKs.

MiniRevenueCatSDK is a Kotlin Multiplatform SDK demonstrating:

Clean SDK API surface design

Cross-platform logic (Android/iOS)

Thread-safe caching (TTL cache)

Exponential backoff with jitter

expect/actual platform code

Shared business logic

Logging abstraction

Simple sample apps (Android, iOS)

It is built as a portfolio-quality SDK representing how real-world subscription SDKs (like RevenueCat Purchases) are architected internally.

📦 Modules
MiniRevenueCatSDK/
│
├── mini-sdk-core/           → Core KMP SDK module
├── mini-sdk-sample-android/ → Sample Android app (Compose)
└── mini-sdk-tests/          → Common + platform tests

🧱 Architecture Overview
┌──────────────────────┐
│     Sample App       │  (Android / iOS)
└───────────┬──────────┘
│ uses
┌───────────▼──────────┐
│   MiniPurchases API  │  ← Public entry point
└───────────┬──────────┘
│ delegates
┌───────────▼──────────┐
│     PurchasesAPI     │  ← Networking (mock)
└───────────┬──────────┘
│
┌───────────▼──────────┐
│  TTLCache & Backoff  │  ← Infra layer
└───────────┬──────────┘
│
┌───────────▼──────────┐
│  expect/actual code  │  ← Platform (Logger, HttpClient)
└──────────────────────┘

✨ Features
✔ Kotlin Multiplatform shared business logic
✔ Platform-specific networking + logging
✔ Clean SDK-style interface
✔ Thread-safe TTL cache
✔ Exponential backoff for retry logic
✔ Sample Android Compose app
✔ Full test suite
✔ Modern Gradle KMP configuration
✔ Industry-standard SDK project structure
🚀 Getting Started
1. Installation

Add the core module to your Gradle project:

implementation(project(":mini-sdk-core"))


(This repo includes the sample app; for external usage, publish via MavenLocal or a remote artifact repository.)

2. Configure the SDK
   val purchases = MiniPurchases.configure(
   apiKey = "test_api_key",
   appUserId = "user_123"
   )

3. Fetch Customer Info
   val customerInfo = purchases.getCustomerInfo()

if (customerInfo != null) {
println("Active entitlements: ${customerInfo.entitlements}")
} else {
println("Failed to fetch")
}

📱 Android Usage (Jetpack Compose Sample)
val viewModel: MiniViewModel = viewModel()
val info by viewModel.customerInfo.collectAsState()

Button(onClick = { viewModel.loadCustomerInfo() }) {
Text("Fetch Customer Info")
}

info?.let {
Text("Active Subscriptions: ${it.activeSubscriptions}")
}


The sample Android app is inside:

mini-sdk-sample-android/app/

🍎 iOS Usage (Swift)

The SDK builds automatically via KMP.
SwiftUI sample coming soon (see roadmap).

let purchases = MiniPurchases.companion.configure(
apiKey: "test_api_key",
appUserId: "user_123"
)

purchases.getCustomerInfo { info in
print(info)
}

🧠 Core SDK Concepts
1. MiniPurchases (public SDK entry point)
   MiniPurchases.configure(apiKey, userId)


This mirrors the Purchases.sharedInstance pattern from RevenueCat.

2. TTL Cache

Prevents unnecessary backend calls:

val cache = TTLCache<String, CustomerInfo>(ttl = 10.minutes)

3. Exponential Backoff

Retry logic:

ExponentialBackoff(
initialDelay = 100.ms,
maxDelay = 5.seconds,
maxRetries = 5
)

4. expect/actual Platform Code
   commonMain
   expect object Logger {
   fun d(message: String)
   fun e(message: String)
   }

androidMain
actual object Logger {
override fun d(message: String) = Log.d("MiniSDK", message)
}

iosMain
actual object Logger {
override fun d(message: String) = NSLog("%@", message)
}

🔍 Project Structure Explained
mini-sdk-core

Contains the entire SDK:

api/ → network client, response models

purchases/ → main public API

models/ → CustomerInfo, Entitlement

cache/ → in-memory TTL cache

backoff/ → retry logic

concurrency/ → atomic primitives (expect/actual)

platform/ → Logger + Environment (expect/actual)

mini-sdk-sample-android

Shows real integration using:

ViewModel

Compose

StateFlow

Logging

mini-sdk-tests

Unit tests for:

cache

backoff

purchases

🧪 Testing

Run all tests:

./gradlew :mini-sdk-core:check

🛣 Roadmap

iOS SwiftUI sample app

Mock backend server

In-app purchase integration (StoreKit + Google Billing)

Entitlement verification

Offline mode

More advanced caching strategies

🤝 Contributing

PRs welcome!
Open an issue if you want to propose new features or improvements.

📄 License

MIT License.