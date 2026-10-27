---
description: Implement an approved spec by executing its tasks test-first
argument-hint: <spec number, e.g. 002>
---

Implement spec $ARGUMENTS.

1. Open `specs/$ARGUMENTS-*/`. **`spec.md`, `plan.md` and `tasks.md` must all be `Approved`**; otherwise stop and tell the user.
2. Make sure you're on branch `spec/$ARGUMENTS-<name>`. Create it if needed.
3. Execute the unchecked tasks in order:
   - For test tasks, write the test that names its AC and confirm it fails.
   - For implementation tasks, write the minimum code that makes the tests pass, following `docs/architecture.md`.
   - Tick the box in `tasks.md` right after each task is done.
4. If something can't be done as specified, **stop**, propose the spec/plan change, and wait for re-approval.
5. Finish with the verification tasks and report each AC's status to the user.
6. Commit using Conventional Commits that reference the spec (`feat($ARGUMENTS): …`). Don't push or open a PR unless the user asks.
