# repro-kover-verification

## Issue

The verification rule declared just under `koverReport` is not applied to `koverVerify*` tasks of any Android build variant.

## Confirmed Kover version

- 0.7.3

## How to reproduce it with this project

1. Clone this repository
2. Run following verification tasks
   - `:app:koverVerifyDebug`
   - `:app:koverVerifyRelease`
   - `:app:koverVerify`
3. Check task outputs

Kover configuration is [here](README.md#project-details)

## Expectation

All verification tasks fail with the following message:

```
Rule violated: lines covered percentage is 6.451600, but expected minimum is 10
```

## Actual result

- `./gradlew :app:koverVerifyDebug`: verification passed and there is no error message ❌
- `./gradlew :app:koverVerifyRelease`: verification failed as expected ✅
- `./gradlew :app:koverVerify`: verification failed as expected ✅

## Project details

### Requirement

- JDK 17+
- Android Studio Giraffe Patch 1 or later (if you run this project on Android Studio)

### Structure

- `:app`: application module
  - build variants: `debug` and `release`

### Kover configuration

From [app/build.gradle.kts](app/build.gradle.kts)

```kotlin
koverReport {
    verify {
        rule {
            minBound(10)
        }
    }
    defaults {
        mergeWith("debug")
    }
    androidReports("release") {
        // note: `koverVerifyRelease` will also pass when removing following rule,
        //       which means the above rule is not applied to the variant `release` too
        verify {
            rule {
                minBound(10)
            }
        }
    }
}
```
