# Plan 002: Remote images from picsum.photos

Status: Approved
Spec: [spec.md](spec.md) (Approved 2026-10-28)

## Approach

- The **data layer** builds two picsum URLs per sport from a stable seed (the lowercase sport name, D-1): a tile-sized one and a detail-sized one. Both use the same seed, so they show the same photo (AC-3).
- The **domain model** `Sport` gains those two URLs as plain `String`s. It keeps `imageRes`, which is now the placeholder and fallback icon (D-2, D-3).
- The **presentation layer** loads the URLs with **Coil 3** (`AsyncImage`). The bundled icon is used as both `placeholder` and `error`. A small shared `SportImage` composable wraps this, so the grid and detail screen behave the same way.
- Coil's default singleton `ImageLoader` gives an in-memory and disk cache, which covers the "no re-download while scrolling" requirement. No custom loader or Hilt wiring is needed.
- `AndroidManifest.xml` gets the `INTERNET` permission back and explicitly sets `android:usesCleartextTraffic="false"`.
- The constitution is amended on this branch (D-4).

URL shapes (sizes in pixels, matching the current layouts):

| Use | URL | Why this size |
|---|---|---|
| Grid tile | `https://picsum.photos/seed/<seed>/600/300` | Tile is half the screen width at a 2:1 aspect ratio |
| Detail | `https://picsum.photos/seed/<seed>/1200/800` | Full width, top third of the screen, cropped to fill |

## Constitution check

| Article | Applies? | How the plan complies / justified exception |
|---|---|---|
| I Specs | Yes | Spec 002 is approved; this plan only covers its ACs. Spec 001 stays `Implemented`. Spec 002 describes the change to 001's AC-4, D-2 and its "offline / no INTERNET permission" requirement. |
| II Architecture | Yes | `domain` adds only `String` fields (no Android or Coil imports; `LayerRulesTest` still enforces this). URL building lives in `data`. Coil is used only in `presentation`. No new use case: no new user action. |
| III Stack | **Exception → amendment** | Article III says "Bundled local drawables (no network images)". Per D-4, this branch amends it to: *"Remote images over HTTPS loaded with Coil 3; bundled drawables are the placeholder and fallback."* Version bumps 1.0.0 → 1.1.0 with a short rationale, as Article III requires for a new library. |
| IV Testing | Yes | Every AC maps to tests below and they are written first. Image states are checked through a semantics property, so UI tests don't depend on the real network. |
| V Simplicity | Yes | One small composable, one URL helper, two fields. Default Coil singleton, with no custom `ImageLoader`, interceptors or DI module. |
| VI Style | Yes | KDoc on the new `Sport` fields; ktlint and detekt stay green. |

## Affected layers & types

| Layer | New / changed | Type | Notes |
|---|---|---|---|
| domain | changed | `Sport` | Adds `tileImageUrl: String` and `detailImageUrl: String`. `imageRes` KDoc now says "placeholder and fallback". |
| data | new | `data/image/PicsumImageUrls` | `object` with `tile(seed)` and `detail(seed)`. The only place that knows the picsum host and sizes. |
| data | changed | `LocalSportDataSource` | Fills both URLs using the seed `name.lowercase()`. |
| presentation | new | `presentation/common/SportImage` | Wraps `AsyncImage` with `placeholder = error = painterResource(imageRes)`, `ContentScale.Crop` and `contentDescription = name`. Exposes the load state (`Loading` / `Success` / `Error`) as a semantics property for tests. |
| presentation | new | `presentation/common/SportImageState` | Semantics key and enum used by `SportImage` and by tests. |
| presentation | changed | `SportListScreen` | The tile `Image` becomes `SportImage(sport.tileImageUrl, …)`. |
| presentation | changed | `SportDetailScreen` | The detail `Image` becomes `SportImage(sport.detailImageUrl, …)`. |
| app | changed | `AndroidManifest.xml` | Adds `INTERNET` and `usesCleartextTraffic="false"`. |
| docs | changed | `docs/constitution.md` | Article III amendment, version 1.1.0. |
| docs | changed | `docs/architecture.md`, `docs/TECHNICAL.md`, `AGENTS.md` | Image loading described; "images are bundled placeholder icons" corrected. |

## Data flow

1. `SportListViewModel` → `GetSportsUseCase` → `SportRepositoryImpl` → `LocalSportDataSource`, as today. Each `Sport` now carries `tileImageUrl` and `detailImageUrl`.
2. `SportListScreen` renders `SportImage(url = tileImageUrl, placeholderRes = imageRes, name)` for each tile.
3. Coil checks the memory cache, then the disk cache, then fetches from picsum over HTTPS. Picsum redirects `/seed/...` to a fixed image, and Coil's OkHttp client follows the redirect.
4. While loading, the icon is shown (AC-4). On failure the icon stays (AC-5). On success the photo replaces it (AC-1).
5. Tap → navigate with the id (unchanged) → `SportDetailScreen` renders `SportImage(url = detailImageUrl, …)` (AC-3).

## Test strategy

UI tests install a Coil `FakeImageLoaderEngine` (from `coil-test`) as the singleton image loader, so success, error and "never finishes" are deterministic and need no network.

| AC | Test level | Test class / name |
|---|---|---|
| AC-1 | data unit | `PicsumImageUrlsTest`: `AC-1 tile URL is https picsum seed URL` |
| AC-1 | data unit | `LocalSportDataSourceTest`: `AC-1 every sport has a picsum tile URL` |
| AC-1 | Compose UI | `SportListScreenTest.ac1_tileShowsRemotePhotoWhenLoaded` (fake engine → success; state `Success`, content description = name) |
| AC-2 | data unit | `LocalSportDataSourceTest`: `AC-2 image URLs are stable across calls`, `AC-2 each sport has a different seed` |
| AC-3 | data unit | `PicsumImageUrlsTest`: `AC-3 tile and detail URLs share the same seed`, `AC-3 detail URL is larger than tile URL` |
| AC-3 | Compose UI | `SportDetailScreenTest.ac3_detailLoadsDetailUrl` (fake engine answers only `detailImageUrl` → state `Success`) |
| AC-4 | Compose UI | `SportListScreenTest.ac4_showsIconWhileLoading`, `SportDetailScreenTest.ac4_showsIconWhileLoading` (fake engine never completes → state `Loading`, placeholder painter shown) |
| AC-5 | Compose UI | `SportListScreenTest.ac5_showsIconWhenLoadFails` (fake engine errors → state `Error`; name still in semantics; tap still calls `onSportClick`) |
| AC-5 | Compose UI | `SportDetailScreenTest.ac5_showsIconAndNameWhenLoadFails` |
| AC-6 | Compose UI (Hilt) | `AppNavigationTest.ac6_offlineListAndDetailStillWork` (fake engine fails every request → 8 tiles, tap opens detail with the name) |
| Arch | unit | Existing `LayerRulesTest` (domain has no Coil/Android imports), plus `AC-1 Coil is used only in presentation` |

Non-functional requirements:

- **HTTPS only:** `PicsumImageUrlsTest` asserts the `https://` scheme; the manifest sets `usesCleartextTraffic="false"`.
- **Content description:** covered by the AC-1 and AC-5 UI tests (lookup by name).
- **No re-download while scrolling:** comes from Coil's default memory cache. It's checked manually in the final verification task (scroll the grid with network logging; no repeat requests), not with an automated test.

Existing spec 001 tests are updated for the new `Sport` fields. `LocalSportDataSourceTest`'s `AC-4 every sport has a bundled drawable` stays, because the icon is still required as the fallback.

## New dependencies

| Library | Version | Why |
|---|---|---|
| `io.coil-kt.coil3:coil-compose` | 3.0.4 | Standard Compose image loader: async loading, placeholder/error painters, memory and disk cache. Needed for AC-1, AC-4, AC-5 and the caching requirement. |
| `io.coil-kt.coil3:coil-network-okhttp` | 3.0.4 | Coil 3 needs a separate network module for HTTP(S) URLs. OkHttp follows picsum's seed redirect. |
| `io.coil-kt.coil3:coil-test` | 3.0.4 (androidTest only) | `FakeImageLoaderEngine` makes image states deterministic in UI tests without network. |

Version 3.0.4 is chosen to match the Compose BOM `2024.12.01` and Kotlin 2.x already in the catalog.

## Risks & mitigations

- **Picsum is a free third-party service; it may be slow or down:** the icon fallback (AC-5) keeps the app usable. Tests never hit the real server.
- **A seed at two sizes might crop to a visibly different region:** picsum maps a seed to one source image and then resizes and crops it, so it's the same photo. The final verification task confirms this visually on a device.
- **Photos aren't sport-related:** accepted in the spec (out of scope).
- **Renaming a sport changes its seed and so its photo:** acceptable; names are fixed data today. If ids are preferred, it's a one-line change in `LocalSportDataSource`.
- **Coil 3 / OkHttp adds APK size (~1 MB):** acceptable for a sample app; minify is off anyway.
- **Network semantics in UI tests:** solved by the fake engine; the `SportImageState` semantics property avoids pixel comparisons.

## Approval

- [x] Approved by: Alvaro Exposito Guerra on 2026-10-28
