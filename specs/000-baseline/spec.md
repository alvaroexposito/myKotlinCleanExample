# Spec 000: Baseline (legacy app behavior)

Status: Implemented (legacy reference)
Created: 2026-10-27
Related: [001-modernize-stack](../001-modernize-stack/spec.md)

## Summary

This spec documents what the 2017 app does today, so that spec 001 can preserve its behavior during the migration. It was written from the code (`MyKotlinCleanExample/app/src/main`). Nothing new is being built here.

## User stories

- **US-1**: As a user, I want to browse a grid of sports images.
- **US-2**: As a user, I want to tap a sport to see it larger, with its name.

## Observed behavior

- **B-1** (US-1): On launch, the main screen shows a **2-column grid** of sport images, cropped to fill each cell, with a 1dp black border.
- **B-2** (US-1): The list has **24 items**: sport ids `1,2,3,5,6,7,8,9` repeated three times. Id `4` is not present.
- **B-3** (US-2): Tapping an item opens a detail screen. The image fills the top third and the sport name, in large centered text, fills the bottom two thirds.
- **B-4** (US-2): Detail names by id:

  | id | Name |
  |---|---|
  | 1 | Baseball |
  | 2 | Windsurfing |
  | 3 | Cycling |
  | 5 | Motorcycle |
  | 6 | Football |
  | 7 | Swimming |
  | 8 | Karate |
  | 9 | Athletism |
  | other | No info |

- **B-5**: Data is hard-coded in `GetListSportsUC` / `GetDetailsSportUC` to simulate a server. It is delivered synchronously through callbacks.
- **B-6**: Images load from `http://lorempixel.com/400/200/sports/<id>/` through Picasso. **That service is offline, so images currently never load.**
- **B-7**: The app has no loading or error states and no empty state.

## Known defects / debt

- Doesn't build with current Android Studio / AGP (Kotlin 1.1, AGP 2.3, jcenter, support libraries, `kotlin-android-extensions`).
- Cleartext HTTP image URLs.
- The duplicated list items look like an artifact of the demo, not a requirement.
- "Athletism" is a typo for "Athletics".
- `SPORT_BUNDLE_EXTRA` is a mutable top-level `var`.
- Only template tests exist.
