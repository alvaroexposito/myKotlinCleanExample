---
description: Draft the technical plan for an approved spec
argument-hint: <spec number, e.g. 002>
---

Create the plan for spec $ARGUMENTS.

1. Open `specs/$ARGUMENTS-*/spec.md`. **If its status isn't `Approved`, stop** and tell the user.
2. Read `docs/constitution.md` and `docs/architecture.md`, and the relevant existing code.
3. Create `plan.md` in the same folder from `specs/_templates/plan-template.md`, with `Status: Draft`.
4. Fill in the constitution check honestly. Flag any exception and explain why it's needed.
5. Map every AC to at least one test in the test strategy table.
6. Justify any new dependency.
7. Set `Status: In Review` and ask the user to approve. Don't write tasks or code.
