---
description: Draft a new SDD spec from a feature description
argument-hint: <feature description>
---

Draft a new feature spec for: $ARGUMENTS

1. Read `docs/constitution.md` and `docs/sdd-workflow.md`.
2. List `specs/` and choose the next free three-digit number `NNN`. Derive a short kebab-case name.
3. Create `specs/NNN-name/spec.md` from `specs/_templates/spec-template.md`, with `Status: Draft` and today's date.
4. Write user stories and testable Given/When/Then acceptance criteria (`AC-n`). Describe *what* and *why* only, not technology.
5. Put every unknown into "Open questions" as `[NEEDS CLARIFICATION: …]`, then ask the user about them with multiple-choice questions and update the spec with their answers.
6. When nothing is left open, set `Status: In Review` and ask the user to approve. Never set `Approved` yourself.

Don't write a plan, tasks or code in this step.
