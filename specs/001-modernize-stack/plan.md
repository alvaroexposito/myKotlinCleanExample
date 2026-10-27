# Plan 001: Modernize stack

Status: Draft
Spec: [spec.md](spec.md) (must be Approved)

> This is a proposed draft. It becomes actionable only after spec 001 is approved and its open questions are resolved.

## Approach

Rewrite the app rather than upgrading it in place. The legacy code is about 200 lines and every dependency is obsolete, so a clean rebuild is cheaper and lower risk.

1. Move the Gradle project from `MyKotlinCleanExample/` to the repo root with `git mv` (keeps history) and untrack `.idea/`.
2. Replace the build with a fresh AGP 8 / Kotlin 2 / Kotlin DSL / version-catalog setup.
3. Build the layers bottom-up: domain → data → presentation, test-first at each layer.
4. Delete the legacy sources once the new screens cover every AC.

## Constitution check

| Article | Applies? | Compliance |
|---|---|---|
| I Specs | Yes | Behavior is derived from spec 000 and spec 001 |
| II Architecture | Yes | Layers and packages exactly as in `docs/architecture.md` |
| III Stack | Yes | This plan *establishes* the Article III stack |
| IV Testing | Yes | See test strategy; tests precede implementation |
| V Simplicity | Yes | Single module; in-memory data source; no network layer |
| VI Style | Yes | ktlint + detekt added in setup |

## Affected layers & types

| Layer | New / changed | Type | Notes |
|---|---|---|---|
| domain | new | `Sport(id: Int, name: String, imageRes: Int)` | `imageRes` is an `@DrawableRes` Int; no Android import needed |
| domain | new | `SportRepository` | `suspend fun getSports(): Result<List<Sport>>`, `suspend fun getSport(id: Int): Result<Sport?>` |
| domain | new | `GetSportsUseCase`, `GetSportDetailUseCase` | `operator fun invoke` |
| data | new | `LocalSportDataSource` | Hard-coded sports table (baseline B-4) |
| data | new | `SportRepositoryImpl` | Wraps the data source in `Result` |
| di | new | `RepositoryModule` | `@Binds` repository; `@HiltAndroidApp SportsApp` |
| presentation | new | `SportListViewModel` / `SportListUiState` / `SportListScreen` | `LazyVerticalGrid(GridCells.Fixed(2))` |
| presentation | new | `SportDetailViewModel` / `SportDetailUiState` / `SportDetailScreen` | Id read from `SavedStateHandle` (type-safe route) |
| presentation | new | `AppNavHost`, routes `SportList`, `SportDetail(id)` | |
| legacy | removed | `usercase/`, `view/`, `model/`, `utils/`, XML layouts | AC-12 |

## Data flow

Tap a tile → `navController.navigate(SportDetail(id))` → `SportDetailViewModel` reads `id` → `GetSportDetailUseCase(id)` → `SportRepository.getSport(id)` → `UiState.Success(sport)` (or `name = "No info"` when it returns null) → `SportDetailScreen` renders.

## Test strategy

| AC | Level | Test |
|---|---|---|
| AC-1 | VM + UI | `SportListViewModelTest`, `SportListScreenTest` |
| AC-2 | VM + UI | `SportListViewModelTest` (Turbine sees `Loading` first), `SportListScreenTest` |
| AC-3 | VM + UI | Fake repository returning failure, then success after `retry()` |
| AC-4 | unit | `LocalSportDataSourceTest`: every sport has a non-zero `imageRes` |
| AC-5, AC-8 | UI | `AppNavigationTest` (Compose + `TestNavHostController`) |
| AC-6, AC-7 | unit + VM | `GetSportDetailUseCaseTest`, `SportDetailViewModelTest` |
| AC-9 | CI | `./gradlew assembleDebug testDebugUnitTest lint ktlintCheck detekt` |
| AC-10 | static | detekt/Konsist rule: `domain` imports no `android.*` |
| AC-11, AC-12 | review | Checklist in tasks |

## New dependencies

| Library | Why |
|---|---|
| Compose BOM, Material 3, Activity Compose | UI (Article III) |
| Navigation Compose + kotlinx-serialization | Type-safe routes |
| Hilt + hilt-navigation-compose + KSP | DI |
| Lifecycle ViewModel/Runtime Compose | `collectAsStateWithLifecycle` |
| JUnit 5, MockK, Turbine, kotlinx-coroutines-test | Tests (Article IV) |
| Compose UI test, Hilt testing | UI tests |
| ktlint (Gradle plugin), detekt | Style (Article VI) |
| Konsist (test) | Enforce layer rule for AC-10 |

Exact versions are pinned in `gradle/libs.versions.toml` at implementation time (latest stable).

## Risks & mitigations

- **JUnit 5 on Android**: use the `android-junit5` plugin for local unit tests; instrumented tests stay on JUnit 4 runners.
- **Losing git history in the move**: use `git mv` in its own commit, before any content changes.
- **Image licensing**: use placeholder vector icons until real assets are approved (spec open question).

## Approval

- [ ] Approved by: ____ on ____
