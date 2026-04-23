# Finance Manager

Personal budget Android app — one `MainActivity` hosting two fragments. Input financial data on screen A, view a styled report on screen B.

## Features

- **Fragment A (Input)** — Material outlined text fields for salary, rent, food; gradient hero card; rounded Calculate button
- **Fragment B (Result)** — Status pill (HEALTHY / DEFICIT), large remaining-amount headline, stacked horizontal breakdown bar (Expenses / Savings / Free), identity footer
- **Red / Green validation** — header + text flip red when salary < expenses, green otherwise
- **Slide animations** between fragments
- **Back navigation** via FloatingActionButton

## Architecture

| Layer | File |
|-------|------|
| Activity host | `MainActivity.kt` |
| Input fragment | `fragments/FragmentA.kt` + `res/layout/fragment_a.xml` |
| Result fragment | `fragments/FragmentB.kt` + `res/layout/fragment_b.xml` |
| Data class | `model/FinanceModel.kt` |
| Business logic | `logic/FinanceManager.kt` |

### Data flow

```
FragmentA ──Bundle──▶ setFragmentResult ──▶ MainActivity
                                             │
                                             ▼
                     FragmentB.newInstance(bundle) with slide animation
```

## Individual Formula

```
savingsPercent = lastNameLetters + birthMonth
               = 8 (Duchidze) + 4 (April)
               = 12%
```

Constants live in `FinanceManager.Companion` and `FragmentB.Companion` — change them in one place and the UI updates.

## Naming Convention

All view IDs prefixed with `gd_` (Giorgi Duchidze):

- `gd_fragment_container`, `gd_edit_salary`, `gd_edit_rent`, `gd_edit_food`
- `gd_btn_calculate`, `gd_btn_back`
- `gd_tv_title`, `gd_tv_status`, `gd_tv_remaining_big`, `gd_tv_total_expenses`, `gd_tv_savings`, `gd_tv_free`, `gd_tv_identity`
- `gd_card_hero`, `gd_card_header`, `gd_card_breakdown`, `gd_card_inputs`
- `gd_bar`, `gd_bar_expenses`, `gd_bar_savings`, `gd_bar_remaining`

## Tech Stack

- Kotlin 2.0.21
- AGP 8.5.2, Gradle
- AndroidX AppCompat + Material Components
- ConstraintLayout throughout
- ViewBinding

## Build

```bash
./gradlew assembleDebug
./gradlew installDebug
```

Run on emulator or device — min SDK 24, target 35.

## Test Cases

| Input (salary / rent / food) | Expected |
|------------------------------|----------|
| 2000 / 500 / 300 | Green HEALTHY · Remaining 1200 · Savings 240 |
| 500 / 600 / 200 | Red DEFICIT · Remaining −300 · Savings 60 |
| blank / blank / blank | All 0, no crash |

## Evaluation Mapping

| Criterion | Where |
|-----------|-------|
| Fragment management (transactions + data passing) | `MainActivity.setFragmentResultListener` + `FragmentA.setFragmentResult` |
| Kotlin & OOP (logic correctness, formula) | `FinanceManager` calculations + `SAVINGS_PERCENT` |
| UI & Constraints (layout, color validation, ID naming) | `fragment_*.xml` + `FragmentB` color swap |
