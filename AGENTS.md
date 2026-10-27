# AGENTS.md

Instructions for any AI coding agent (Claude Code, Codex, Cursor, Copilot, …) working in this repository.

## What this project is

`myKotlinCleanExample` is a small Android sample app that shows a two-column grid of sports. Tapping a sport opens a detail screen with a larger image and the sport's name.

The project uses **Spec-Driven Development (SDD)**: specs are the source of truth, and code is an implementation of an approved spec.

## Read these first

1. [`docs/constitution.md`](docs/constitution.md): the non-negotiable rules for architecture, stack, testing and style.
2. [`docs/sdd-workflow.md`](docs/sdd-workflow.md): how a change goes from idea to merged code.
3. [`docs/architecture.md`](docs/architecture.md): target layering and package layout.
4. [`specs/`](specs/): one folder per feature. Each folder holds `spec.md`, `plan.md` and `tasks.md`.

If the constitution and any other document disagree, **the constitution wins**.

## Golden rules (strict SDD)

- **No production code without an approved spec and plan.** Before you edit anything under `app/src/main`, there must be a `specs/NNN-*/` folder whose `spec.md` **and** `plan.md` both have `Status: Approved`. If there isn't one, stop and propose a spec.
- **Only a human approves.** An agent may draft specs, plans and tasks, but it never sets `Status: Approved` itself.
- **Write tests from the spec, before the implementation.** Every acceptance criterion (`AC-x`) maps to at least one test, and the test name or KDoc references the AC id.
- **Work through `tasks.md` in order.** Tick each task (`- [x]`) as it is completed. Don't do work that no task covers; add a task first, and tell the user.
- **Behavior changed means the spec changed.** If the implementation has to differ from the spec, update `spec.md` (and `plan.md`) first, then ask the user to re-approve.
- **Trivial-change exemption.** Typos, comments, formatting, and dependency patch bumps that change no behavior may skip a spec. Say so in the commit message (`chore:` / `docs:`).

## Repository layout

```
AGENTS.md                 ← you are here (tool-neutral)
CLAUDE.md                 ← Claude Code entry point (imports this file)
docs/
  constitution.md         ← project rules
  sdd-workflow.md         ← process
  architecture.md         ← target architecture
specs/
  _templates/             ← spec / plan / tasks templates
  000-baseline/           ← current behavior of the legacy app (reference)
  001-modernize-stack/    ← first SDD feature: migrate to the modern stack
MyKotlinCleanExample/     ← legacy Gradle project (moved to repo root by spec 001)
.claude/commands/         ← /spec, /plan, /tasks, /implement slash commands
```

## Current state vs target

| | Current (legacy, 2017) | Target (see constitution) |
|---|---|---|
| Language / build | Kotlin 1.1, AGP 2.3, Gradle 3.3, Groovy DSL | Kotlin 2.x, AGP 8.x, Gradle Kotlin DSL + version catalog |
| UI | Activities, XML, `kotlin-android-extensions` | Single Activity, Jetpack Compose, Navigation Compose |
| Architecture | Activities call "use cases" directly | Clean Architecture (domain / data / presentation) + MVVM |
| Async / DI | Callbacks / none | Coroutines + Flow / Hilt |
| Images | Picasso + lorempixel.com (offline) | Bundled local drawables |
| Tests | Template examples only | JUnit5, MockK, Turbine, Compose UI tests |

**The legacy project does not build with modern tooling.** Until spec 001 lands, don't try to fix the legacy build piecemeal. Follow spec 001's tasks.

## Commands (valid once spec 001 is implemented)

```bash
./gradlew assembleDebug          # build
./gradlew testDebugUnitTest      # unit tests
./gradlew connectedDebugAndroidTest  # instrumented / Compose UI tests (device needed)
./gradlew lint ktlintCheck detekt  # static analysis
```

## Conventions

- Language for code, docs and specs: **English**.
- Commits use Conventional Commits and reference the spec, e.g. `feat(001): add SportRepository`.
- Branch per spec: `spec/NNN-short-name`.
