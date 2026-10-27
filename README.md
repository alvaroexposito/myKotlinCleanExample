# myKotlinCleanExample

Android sample app (sports grid + detail) built with Clean Architecture, MVVM and Jetpack Compose, developed with **Spec-Driven Development**.

## Documentation

| Document | What it covers |
|---|---|
| [Technical documentation](docs/TECHNICAL.md) | Full technical reference with diagrams: Clean Architecture, MVVM, Compose, navigation, Hilt, coroutines, testing and build setup. |
| [Architecture](docs/architecture.md) | Short summary of the layers, the `presentation → domain ← data` rule, package layout and conventions. |
| [Constitution](docs/constitution.md) | The binding project rules: architecture, tech stack, test-first testing, simplicity, code style and git workflow. Wins over any other doc. |
| [SDD workflow](docs/sdd-workflow.md) | How a change goes from idea to merged code: spec → plan → tasks → implement, with human approval at each step. |

## Specs

- [specs/](specs/): one folder per feature, each with `spec.md`, `plan.md` and `tasks.md`.
- [000-baseline](specs/000-baseline/spec.md): behavior of the legacy app.
- [001-modernize-stack](specs/001-modernize-stack/spec.md): migration to the current stack.

## AI agents

- [AGENTS.md](AGENTS.md): instructions for any AI coding agent.
- [CLAUDE.md](CLAUDE.md): Claude Code specifics.
