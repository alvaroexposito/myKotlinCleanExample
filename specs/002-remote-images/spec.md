# Spec 002: Remote images from picsum.photos

Status: Implemented
Created: 2026-10-28
Branch: `spec/002-remote-images`
Related: [001-modernize-stack](../001-modernize-stack/spec.md) (changes AC-4 and decision D-2), [constitution](../../docs/constitution.md) (Article III)

## Summary

Every sport currently shows a bundled placeholder icon (spec 001, AC-4 / D-2). This spec replaces those icons with photos loaded from the image server `https://picsum.photos/`, so the grid and detail screens show photographs instead of icons. The server is named because it is the requirement. Note that picsum.photos serves generic stock photos, not sport-specific ones.

## User stories

- **US-1**: As a user, I want each sport tile to show a photo instead of a placeholder icon, so the grid looks like a real gallery.
- **US-2**: As a user, I want the detail screen to show a larger version of the same photo, so the detail feels connected to the tile I tapped.
- **US-3**: As a user without a working connection, I still want to browse sports and read their names, so the app stays usable.

## Acceptance criteria

### Photos (US-1, US-2)

- **AC-1** (US-1): **Given** the app is launched with network access, **when** the sport list is shown, **then** each tile displays a photo loaded from `https://picsum.photos/`, cropped to fill the tile.
- **AC-2** (US-1): **Given** the sport list, **then** each sport's photo is chosen by a stable seed derived from the sport (see D-1), so the same sport shows the same photo on every launch and different sports get different photos.
- **AC-3** (US-2): **Given** the user taps a tile, **when** the detail screen opens, **then** it shows the same photo as that sport's tile, at a resolution suited to the larger detail area.
- **AC-4** (US-1, US-2): **Given** a photo is still loading, **then** the sport's bundled placeholder icon from spec 001 is shown in its place until the photo arrives.

### Offline and errors (US-3)

- **AC-5** (US-3): **Given** a photo fails to load (no network, server error or timeout), **then** the sport's bundled placeholder icon from spec 001 is shown instead, and the sport's name and navigation keep working.
- **AC-6** (US-3): **Given** no network, **when** the app is launched, **then** the list still shows all 8 sports and tapping one opens its detail (spec 001 AC-1, AC-5 to AC-7 still hold).

## Non-functional requirements

- Only HTTPS requests; no cleartext traffic.
- Photos keep the sport name as their content description (spec 001 accessibility rule).
- Scrolling the grid must not re-download photos that were already shown in the same session.
- Requires amending constitution Article III ("Images: Bundled local drawables (no network images)") to allow remote images over HTTPS, done on this spec's branch (see D-4).

## Out of scope

- Sport-specific photos (picsum.photos has no sport categories).
- A real backend for the sport list or names; the list stays local.
- Photo attribution or author display.
- Image effects offered by the server (grayscale, blur).

## Decisions (resolved questions)

- **D-1**: Each sport gets a stable seeded photo, e.g. `https://picsum.photos/seed/<sport>/<width>/<height>`. The same sport always shows the same photo; no picsum ids need maintaining.
- **D-2**: While a photo loads, the sport's existing bundled icon is shown.
- **D-3**: When a photo fails to load, the sport's existing bundled icon is shown. The bundled icons therefore stay in the app.
- **D-4**: Constitution Article III is amended in this spec's branch (version 1.0.0 → 1.1.0) to allow remote images over HTTPS, with the bundled icons as placeholder and fallback.

## Open questions

None.

## Approval

- [x] Approved by: Alvaro Exposito Guerra on 2026-10-28
