# AGENTS.md

Instructions for any AI coding agent (Claude Code, Codex, Cursor, Copilot, …) working in this repository.

## What this project is

`myKotlinCleanExample` is a small Android sample app that shows a two-column grid of sports. Tapping a sport opens a detail screen with a larger image and the sport's name.

The project uses **Spec-Driven Development (SDD)**: specs are the source of truth, and code is an implementation of an approved spec.

## Read these first

1. [`docs/constitution.md`](docs/constitution.md): the non-negotiable rules for architecture, stack, testing and style.
2. [`docs/sdd-workflow.md`](docs/sdd-workflow.md): how a change goes from idea to merged code.
3. [`docs/architecture.md`](docs/architecture.md): layering and package layout.
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
  architecture.md         ← architecture and package layout
specs/
  _templates/             ← spec / plan / tasks templates
  000-baseline/           ← current behavior of the legacy app (reference)
  001-modernize-stack/    ← first SDD feature: migrate to the modern stack
app/                      ← Android app module (domain / data / presentation)
gradle/libs.versions.toml ← dependency versions
config/detekt/            ← detekt rules
.claude/commands/         ← /spec, /plan, /tasks, /implement slash commands
```

## Current state

Spec 001 is implemented. The app runs on the stack in constitution Article III: Kotlin 2.1, AGP 8.7, Gradle 8.11, Compose + Material 3, Hilt, Coroutines/Flow and Navigation Compose. All data is local, and the images are bundled placeholder icons.

Build with **JDK 17 or 21** (for example `export JAVA_HOME=$(/usr/libexec/java_home -v 21)`). UI tests need a device or emulator; Espresso 3.7+ is required for Android 16+ images.

## Commands

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
