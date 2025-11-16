# 🚀 MiniRevenueCatSDK  
### A Lightweight Kotlin Multiplatform (KMP) Subscription & Entitlement SDK  
### Architected in the style of RevenueCat's Purchases SDK

MiniRevenueCatSDK is a fully functional **Kotlin Multiplatform SDK** that demonstrates:

- Shared business logic across Android & iOS  
- Clean SDK API surface  
- Thread-safe caching via TTLCache  
- Retry logic with exponential backoff  
- Platform-specific implementations via `expect/actual`  
- Concurrency handling (Mutex, Atomic primitives)  
- Sample Android app consuming the SDK  
- Support for Android, iOS, and Compose Multiplatform  

This project mirrors how real commercial subscription SDKs (like **RevenueCat Purchases**) are architected internally.

---

## 📦 Modules Overview

### 1. `mini-sdk-core` (SDK Module - Kotlin Multiplatform)
- Production-style SDK module
- Shared code for Android & iOS
- Contains:
  - API layer (`PurchasesAPI`)
  - Business logic (`MiniPurchases`)
  - Models (`CustomerInfo`, `Entitlement`)
  - Caching (`TTLCache`)
  - Retry logic (`ExponentialBackoff`)
  - Concurrency helpers (`AtomicBoolean`, `AtomicLong`)
  - Platform abstractions (`expect/actual Logger`)

### 2. `composeApp` (Sample App - KMP)
- Jetpack Compose Multiplatform sample
- Demonstrates integrating and calling the SDK
- Includes UI, ViewModel, and real usage flows

### 3. `iosApp` (Native SwiftUI Sample App)
- Native iOS app consuming the KMP SDK framework
- Demonstrates Swift → Kotlin SDK usage


---

## 🧱 Architecture Overview

The SDK follows a clean, layered architecture:

- **Sample Apps (composeApp / iosApp)**
  - Demonstrate how to consume the SDK
  - Provide UI to test SDK features

- **MiniPurchases (Public SDK API)**
  - Main entry point for developers
  - Manages configuration, user sessions, customer info fetching

- **PurchasesAPI (Networking Layer)**
  - Fetches customer information
  - Applies retry logic
  - Handles backend responses (mocked or real)

- **Infrastructure Layer**
  - **TTLCache** → Caches responses with time-based invalidation  
  - **ExponentialBackoff** → Retry strategy for network calls  
  - **Concurrency utilities** → Atomic types, Mutex wrappers  

- **Platform Layer (expect/actual)**
  - Logging (Android/iOS)
  - Environment helpers
  - Platform-specific APIs


---

# ✨ **Features**

- ✔ **Kotlin Multiplatform (Android + iOS)**
- ✔ **Thread-safe TTL cache**
- ✔ **Exponential backoff with jitter**
- ✔ **Shared API client abstraction**
- ✔ **expect/actual platform loggers**
- ✔ **Sample Android app (Jetpack Compose)**
- ✔ **Sample iOS app (SwiftUI)**
- ✔ **Coroutines support**
- ✔ **AtomicBoolean & AtomicLong concurrency patterns**
- ✔ **Production-grade SDK structure**

---

# 🚀 Getting Started

## **1. Installation**

Inside your project:

```kotlin
implementation(project(":mini-sdk-core"))
```

## **2. Configure the SDK

```kotlin
val purchases = MiniPurchases.configure(
    apiKey = "test_api_key",
    appUserId = "user123"
)
```
## **3. Fetch Customer Info
```kotlin
val info = purchases.getCustomerInfo()

if (info != null) {
    println("Active entitlements: ${info.entitlements}")
} else {
    println("Failed to fetch.")
}
```
## **📱 Android (Jetpack Compose) Usage
```kotlin

val vm = viewModel<MiniViewModel>()
val info by vm.customerInfo.collectAsState()

Button(onClick = { vm.loadCustomerInfo() }) {
    Text("Fetch Customer Info")
}

info?.let {
    Text("Active Subscriptions: ${it.activeSubscriptions}")
}
```
The sample Android app is located under:

composeApp/
## 🍎 iOS Usage (Swift)
SwiftUI integration (via KMP framework):

The native iOS project lives under:

iosApp/
## 🧠 Project Structure Explained
mini-sdk-core (SDK module)
kotlin

api/          → Network layer abstraction
backoff/      → Retry logic
caches/       → TTL cache, Memory cache
concurrency/  → AtomicBoolean, AtomicLong
models/       → CustomerInfo, Entitlement, Config
platform/     → expect/actual Logger + Environment
purchases/    → MiniPurchases public API
composeApp (Sample App)
Jetpack Compose UI

ViewModel integration

Real SDK usage

iosApp (Sample iOS app)
SwiftUI UI

Demonstrates KMP framework usage

## 🧪 Testing
Run SDK tests:
```ruby
./gradlew :mini-sdk-core:check
```
Includes:

TTLCache tests

Backoff tests

MiniPurchases tests

## 🛣 Roadmap
 Real backend integration example

 SK2 + BillingClient wrapper

 Entitlement computation logic

 Offline mode improvements

 More sample apps

## 🤝 Contributing
Open an Issue or PR!
Issues for ideas, bugs, or enhancements are welcome.

## 📄 License
MIT License

## 🙌 Acknowledgements
This project takes inspiration from:

RevenueCat’s Purchases SDK

Kotlin Multiplatform Architecture

Jetpack Compose / SwiftUI shared code patterns
