# CLAUDE.md

@AGENTS.md

## Claude Code specifics

- Use the SDD slash commands in `.claude/commands/`:
  - `/spec <feature description>`: drafts `specs/NNN-name/spec.md` from the template.
  - `/plan <NNN>`: drafts `plan.md` for an **approved** spec.
  - `/tasks <NNN>`: breaks an approved plan into `tasks.md`.
  - `/implement <NNN>`: executes the tasks in order, test-first.
- Before any edit under `app/src/main` (or `MyKotlinCleanExample/app/src/main` before flattening), check the gate: the active spec's `spec.md` and `plan.md` both say `Status: Approved`. If not, stop and tell the user.
- Never change a `Status:` line to `Approved` yourself. Ask the user.
- When a spec has open questions (`[NEEDS CLARIFICATION]`), ask the user with a multiple-choice question rather than guessing.
- Keep `tasks.md` checkboxes current as you work, so the user can see progress there.
