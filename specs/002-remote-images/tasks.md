# Tasks 002: Remote images from picsum.photos

Status: Approved
Plan: [plan.md](plan.md) (Approved 2026-10-28)

Legend: `[P]` = can run in parallel with the previous task · tests come before implementation.

## Phase 1: Setup

- [x] T001 Create branch `spec/002-remote-images` from `master` and bring over the `specs/002-remote-images/` files
- [x] T002 Amend `docs/constitution.md` Article III: images row becomes "Remote images over HTTPS loaded with Coil 3; bundled drawables are the placeholder and fallback". Bump version 1.0.0 → 1.1.0 and add a one-line rationale referencing spec 002 (D-4)
- [ ] T003 Add `coil-compose`, `coil-network-okhttp` (implementation) and `coil-test` (androidTestImplementation), version 3.0.4, to `gradle/libs.versions.toml` and `app/build.gradle.kts`; confirm `./gradlew assembleDebug`
- [ ] T004 Add compile-only skeletons so tests can be written: `Sport.tileImageUrl` / `detailImageUrl` (KDoc; `imageRes` KDoc says "placeholder and fallback"), `data/image/PicsumImageUrls` with `tile(seed)` / `detail(seed)` returning `""`, `presentation/common/SportImageState` (enum + semantics key). Update existing test fixtures (`FakeSportRepository`, screen tests, ViewModel tests) for the new `Sport` constructor

## Phase 2: Tests first (must fail)

### Data

- [ ] T010 [P] `PicsumImageUrlsTest`: `AC-1 tile URL is https picsum seed URL` (also asserts the `https://` scheme), `AC-3 tile and detail URLs share the same seed`, `AC-3 detail URL is larger than tile URL`
- [ ] T011 [P] `LocalSportDataSourceTest`: `AC-1 every sport has a picsum tile URL`, `AC-2 image URLs are stable across calls`, `AC-2 each sport has a different seed`; keep `AC-4 every sport has a bundled drawable` from spec 001 (the fallback still needs it)

### Architecture

- [ ] T012 [P] `LayerRulesTest`: add `AC-1 Coil is used only in presentation` (no `coil3.` imports in `domain` or `data`)

### Presentation (Compose UI, with Coil `FakeImageLoaderEngine` installed as the singleton loader)

- [ ] T013 Add a small androidTest helper that installs a `FakeImageLoaderEngine` for success, error or never-completing responses, and reads the `SportImageState` semantics property
- [ ] T014 [P] `SportListScreenTest`: `ac1_tileShowsRemotePhotoWhenLoaded`, `ac4_showsIconWhileLoading`, `ac5_showsIconWhenLoadFails` (state `Error`, name still in semantics, tap still calls `onSportClick`)
- [ ] T015 [P] `SportDetailScreenTest`: `ac3_detailLoadsDetailUrl`, `ac4_showsIconWhileLoading`, `ac5_showsIconAndNameWhenLoadFails`
- [ ] T016 [P] `AppNavigationTest`: `ac6_offlineListAndDetailStillWork` (every request fails → 8 tiles, tapping one opens the detail with the name)
- [ ] T017 Run `./gradlew testDebugUnitTest` and `connectedDebugAndroidTest`; confirm the new tests fail for the expected reasons (empty URLs, no `SportImage` yet)

## Phase 3: Implementation

- [ ] T020 Implement `PicsumImageUrls.tile(seed)` = `https://picsum.photos/seed/<seed>/600/300` and `detail(seed)` = `https://picsum.photos/seed/<seed>/1200/800` (makes T010 pass)
- [ ] T021 Fill both URLs in `LocalSportDataSource` with the seed `name.lowercase()` (makes T011 pass)
- [ ] T022 Implement `presentation/common/SportImage`: `AsyncImage` with `placeholder = error = painterResource(imageRes)`, `ContentScale.Crop`, `contentDescription = name`, and the load state in the `SportImageState` semantics property
- [ ] T023 Use `SportImage(sport.tileImageUrl, …)` in `SportListScreen` (makes T014 pass)
- [ ] T024 [P] Use `SportImage(sport.detailImageUrl, …)` in `SportDetailScreen` (makes T015 pass)
- [ ] T025 `AndroidManifest.xml`: add the `INTERNET` permission and `android:usesCleartextTraffic="false"` (makes T016 pass on a real launch)

## Phase 4: Docs

- [ ] T030 [P] Update `docs/architecture.md` (data `image/` package, presentation `common/`, image loading line), `docs/TECHNICAL.md` (image loading section) and `AGENTS.md` "Current state" (images now come from picsum.photos with icon fallback)

## Phase 5: Verification

- [ ] T090 All ACs have passing tests; run `./gradlew lint ktlintCheck detekt testDebugUnitTest` and `./gradlew connectedDebugAndroidTest`
- [ ] T091 Manual check on a device or emulator: photos appear in the grid and on detail, tile and detail show the same photo (AC-3 risk), airplane mode shows the icons and navigation still works, scrolling back up doesn't re-request already-shown photos (network logging, caching requirement)
- [ ] T092 Update spec/plan/tasks status to `Implemented`

## Approval

- [x] Approved by: Alvaro Exposito Guerra on 2026-10-28
