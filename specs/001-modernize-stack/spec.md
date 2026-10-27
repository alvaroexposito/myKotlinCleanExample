# Spec 001: Modernize stack

Status: Draft
Created: 2026-10-27
Branch: `spec/001-modernize-stack`
Related: [000-baseline](../000-baseline/spec.md), [constitution](../../docs/constitution.md)

## Summary

The app no longer builds and its images no longer load. This spec rebuilds it on the stack defined in the constitution (Article III) and the architecture in `docs/architecture.md`, keeping the baseline's user-facing behavior. It also fixes the repository layout so the Gradle project lives at the repo root.

Technology is named here because the migration itself is the requirement.

## User stories

- **US-1**: As a user, I want to browse a grid of sports.
- **US-2**: As a user, I want to tap a sport to see its detail.
- **US-3**: As a developer, I want the project to build and test on current Android Studio, so I can keep evolving it with SDD.

## Acceptance criteria

### Browsing (US-1)

- **AC-1**: **Given** the app is launched, **when** the sport list finishes loading, **then** a 2-column grid shows one image tile per sport.
- **AC-2**: **Given** the list is loading, **then** a loading indicator is shown instead of the grid.
- **AC-3**: **Given** the repository fails, **when** the list screen loads, **then** an error message with a "Retry" action is shown, and tapping Retry reloads the list.
- **AC-4**: Each tile's image comes from a bundled local drawable, so the app works with no network connection.

### Detail (US-2)

- **AC-5**: **Given** the sport list, **when** the user taps a tile, **then** the detail screen opens for that sport's id.
- **AC-6**: **Given** the detail screen for a known id, **then** it shows that sport's image at the top and its name below, using the names from baseline B-4 (with "Athletics" replacing "Athletism").
- **AC-7**: **Given** the detail screen for an unknown id, **then** it shows "No info".
- **AC-8**: **Given** the detail screen, **when** the user presses system Back, **then** they return to the list at the same scroll position.

### Developer (US-3)

- **AC-9**: Running `./gradlew assembleDebug testDebugUnitTest lint ktlintCheck detekt` from the **repository root** succeeds on JDK 17.
- **AC-10**: The codebase follows the layer and package layout in `docs/architecture.md`. `domain` has no Android imports.
- **AC-11**: `.idea/` is no longer tracked by git, and `.gitignore` covers standard Android/IDE files.
- **AC-12**: All legacy code (`usercase/`, `view/`, Picasso, `kotlin-android-extensions`, Java template tests) is removed.

## Non-functional requirements

- Works fully offline; the `INTERNET` permission is removed.
- Dark theme support through Material 3 dynamic or default color schemes.
- Tiles and the detail image have content descriptions (the sport name) for accessibility.

## Out of scope

- Real network API or remote images (possible future spec).
- Persistence or caching.
- Multi-module split.
- New features beyond baseline behavior.

## Open questions

- [NEEDS CLARIFICATION: Keep the list duplicated three times (24 items), as in baseline B-2, or show each sport once (8 items)? Proposed: once.]
- [NEEDS CLARIFICATION: Which images to bundle: your own photos, or royalty-free photos you'll supply? Proposed: placeholder Material vector icons per sport until assets are provided.]
- [NEEDS CLARIFICATION: Keep the app name "MyKotlinCleanExample" and the package `alvaro.exposito.mykotlincleanexample`? Proposed: keep both.]

## Approval

- [ ] Approved by: ____ on ____
