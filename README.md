# Finance Manager

Android app with MainActivity + 2 Fragments for finance input and result display.

## Architecture

- **FragmentA (Input)**: salary, rent, food + Calculate button
- **FragmentB (Result)**: totals, savings, status, identity
- Data passed via `setFragmentResult` + `Bundle`

## Individual Formula

`savingsPercent = lastNameLetters + birthMonth = 8 + 4 = 12%`

## ID Naming

All IDs prefixed with `gd_`.

## Build

```
./gradlew assembleDebug
./gradlew installDebug
```
