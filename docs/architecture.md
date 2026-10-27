# Architecture

Status: Current (implemented by spec 001). For the legacy app this replaced, see `specs/000-baseline/spec.md`.

For diagrams and full technical details (MVVM, Compose, navigation, Hilt, testing), see [`TECHNICAL.md`](TECHNICAL.md).

## Layers

```
┌──────────────── presentation ────────────────┐
│ Composable screens ◀── UiState ── ViewModel  │
└──────────────────────────┬───────────────────┘
                           │ calls
┌──────────────── domain (pure Kotlin) ────────┐
│ UseCases ──▶ Repository interfaces ◀ Models  │
└──────────────────────────▲───────────────────┘
                           │ implements
┌──────────────── data ────────────────────────┐
│ RepositoryImpl ──▶ LocalSportDataSource      │
└──────────────────────────────────────────────┘
```

Dependency rule: `presentation → domain ← data`. `domain` imports nothing from the other two.

## Package layout

Single `:app` module to start; split into Gradle modules only when a spec justifies it.

```
alvaro.exposito.mykotlincleanexample
├── MainActivity.kt              // single Activity, hosts NavHost
├── SportsApp.kt                 // @HiltAndroidApp
├── di/                          // Hilt modules (bind repositories)
├── domain/
│   ├── model/                   // Sport, SportDetail
│   ├── repository/              // SportRepository (interface)
│   └── usecase/                 // GetSportsUseCase, GetSportDetailUseCase
├── data/
│   ├── local/                   // LocalSportDataSource (in-memory + drawables)
│   └── repository/              // SportRepositoryImpl
└── presentation/
    ├── navigation/              // routes, NavGraph
    ├── theme/                   // Material 3 theme
    ├── sportlist/               // SportListScreen, SportListViewModel, SportListUiState
    └── sportdetail/             // SportDetailScreen, SportDetailViewModel, SportDetailUiState
```

## Conventions

- `UiState` is a sealed interface: `Loading`, `Success(...)`, `Error(message)`. A screen may split `Success` into more specific states, as the detail screen does with `Found` and `NotFound`.
- ViewModels expose `val uiState: StateFlow<XUiState>` and never hold a `Context`.
- Repositories return `Result<T>` or `Flow<T>`; they never throw across layer boundaries.
- Navigation arguments are ids only. Screens load their own data through their ViewModel.
- Tests mirror the main package structure under `src/test` and `src/androidTest`.
