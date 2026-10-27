# Tasks 001: Modernize stack

Status: Approved
Plan: [plan.md](plan.md) (must be Approved)

Legend: `[P]` = parallelizable · tests come before implementation.

## Phase 1: Repository & build setup

- [x] T001 Create branch `spec/001-modernize-stack`
- [x] T002 `git mv MyKotlinCleanExample/* .` to the repo root (own commit, no content changes)
- [x] T003 `git rm -r --cached .idea` and replace `.gitignore` with the standard Android template (AC-11)
- [x] T004 Replace the Gradle wrapper with the latest stable version; convert to `settings.gradle.kts` / `build.gradle.kts`
- [x] T005 Add `gradle/libs.versions.toml` with all plan dependencies
- [x] T006 Configure `:app`: AGP 8, Kotlin 2, Compose, KSP, Hilt, `minSdk 24`, `compile/targetSdk 35`, JVM 17
- [x] T007 [P] Add ktlint + detekt configuration
- [x] T008 Confirm an empty Compose `MainActivity` builds (`./gradlew assembleDebug`)

## Phase 2: Domain (test-first)

- [x] T010 Create `domain/model/Sport`, `domain/repository/SportRepository`
- [x] T011 [P] Test `GetSportsUseCaseTest` (AC-1 data)
- [x] T012 [P] Test `GetSportDetailUseCaseTest` (AC-6, AC-7)
- [x] T013 Implement `GetSportsUseCase`, `GetSportDetailUseCase`

## Phase 3: Data (test-first)

- [ ] T020 Add 8 placeholder vector icons to `res/drawable`, one per sport (D-2)
- [ ] T021 Test `LocalSportDataSourceTest`: exactly 8 unique sports (AC-1, D-1), drawables set (AC-4), names per AC-6
- [ ] T022 [P] Test `SportRepositoryImplTest` (success and failure paths)
- [ ] T023 Implement `LocalSportDataSource`, `SportRepositoryImpl`, `RepositoryModule`, `SportsApp`

## Phase 4: Presentation (test-first)

- [ ] T030 [P] Test `SportListViewModelTest` (AC-1, AC-2, AC-3)
- [ ] T031 [P] Test `SportDetailViewModelTest` (AC-6, AC-7)
- [ ] T032 Implement `SportListViewModel`, `SportDetailViewModel` + `UiState`s
- [ ] T033 [P] Test `SportListScreenTest` (AC-1, AC-2, AC-3)
- [ ] T034 [P] Test `SportDetailScreenTest` (AC-6, AC-7)
- [ ] T035 Implement Material 3 theme, `SportListScreen`, `SportDetailScreen`
- [ ] T036 Test `AppNavigationTest` (AC-5, AC-8)
- [ ] T037 Implement `AppNavHost` + routes; wire into `MainActivity`

## Phase 5: Cleanup

- [x] T040 Delete legacy `usercase/`, `view/`, `model/`, `utils/`, XML layouts, Java template tests (AC-12) (done during Phase 1: legacy sources can't compile with the new build, so T008 required removing them)
- [x] T041 Remove the `INTERNET` permission from the manifest (done during Phase 1, when the manifest was rewritten)
- [ ] T042 Add the Konsist layer test: `domain` has no `android.*` imports (AC-10)

## Phase 6: Verification

- [ ] T090 Every AC-1…AC-12 has a passing test or checked review item
- [ ] T091 `./gradlew assembleDebug testDebugUnitTest lint ktlintCheck detekt` is green from the repo root (AC-9)
- [ ] T092 Run on a device or emulator in airplane mode: list, detail, back
- [ ] T093 Update `AGENTS.md` (repo layout, current state) and set spec/plan/tasks status to `Implemented`

## Approval

- [x] Approved by: Alvaro Exposito Guerra on 2026-10-27
