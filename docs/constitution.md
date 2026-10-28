# Project Constitution

Version: 1.1.0 · Ratified: 2026-10-27 · Amended: 2026-10-28

These rules govern every spec, plan and line of code. Amending them requires a PR that bumps the version above and explains why.

## Article I: Specs are the source of truth

1. All behavior is described in `specs/NNN-*/spec.md` before it is implemented.
2. A spec describes **what** and **why** (user value, acceptance criteria), never **how**. Technology choices belong in `plan.md`.
3. Only a human approves a spec, plan or task list (`Status: Approved`).
4. If code and spec disagree, that is a bug: fix the code, or amend and re-approve the spec.

## Article II: Architecture

1. **Clean Architecture** with three layers and one-way dependencies: `presentation → domain ← data`.
2. `domain` is pure Kotlin: no Android, no framework imports. It holds models, repository interfaces and use cases.
3. `data` implements domain repository interfaces. Its data sources are hidden behind those interfaces.
4. `presentation` uses **MVVM**: a `ViewModel` exposes a single immutable `UiState` via `StateFlow`; UI events go in as function calls.
5. One use case per user action, named `VerbNounUseCase` with `operator fun invoke`.
6. Single Activity; screens are composables wired with Navigation Compose.

See `docs/architecture.md` for the package layout.

## Article III: Technology stack

| Concern | Choice |
|---|---|
| Language | Kotlin 2.x (K2), JVM target 17 |
| Build | AGP 8.x, Gradle Kotlin DSL, `gradle/libs.versions.toml` version catalog |
| SDK | `minSdk 24`, `targetSdk`/`compileSdk 35` |
| UI | Jetpack Compose + Material 3 |
| Navigation | Navigation Compose (type-safe routes) |
| DI | Hilt |
| Async | Kotlin Coroutines + Flow |
| Images | Remote images over HTTPS loaded with Coil 3; bundled drawables are the placeholder and fallback |
| Static analysis | Android Lint, ktlint, detekt; all must pass in CI |

Adding a new library requires a line in the relevant `plan.md` saying why.

## Article IV: Testing (test-first)

1. Every acceptance criterion `AC-x` in a spec has at least one automated test that names it (e.g. ``fun `AC-2 tapping a sport opens its detail`()``).
2. Tests are written from the spec **before** the implementation and must fail first.
3. Required coverage per layer:
   - **domain**: JUnit 5 unit tests for every use case.
   - **data**: unit tests for repository implementations.
   - **presentation**: ViewModel tests (MockK + Turbine + `kotlinx-coroutines-test`); Compose UI tests for every screen-level AC.
4. A task is done only when its tests pass and `./gradlew lint ktlintCheck detekt testDebugUnitTest` is green.

## Article V: Simplicity

1. Build only what an approved spec asks for. No speculative abstractions.
2. Prefer the platform and the libraries above over custom frameworks.
3. Delete dead code instead of commenting it out.

## Article VI: Code style

1. English everywhere: identifiers, comments, docs.
2. ktlint official style. No wildcard imports.
3. Immutable data (`val`, `List` rather than `ArrayList`) unless mutation is required.
4. KDoc on public domain APIs; comments explain *why*, not *what*.

## Article VII: Workflow hygiene

1. One branch per spec: `spec/NNN-short-name`.
2. Conventional Commits that reference the spec: `feat(001): …`, `test(001): …`.
3. Trivial changes (typos, formatting, comments, dependency patch bumps that change no behavior) are exempt from specs and use `chore:` / `docs:`.

## Amendments

- **1.1.0** (2026-10-28, [spec 002](../specs/002-remote-images/spec.md)): allow remote images over HTTPS so sports can show photos from picsum.photos; the bundled icons stay as placeholder and offline fallback.
