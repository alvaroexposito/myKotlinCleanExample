# SDD Workflow

Every non-trivial change follows four phases. Each phase produces one file in `specs/NNN-short-name/` and ends with a human approval.

```
idea ──/spec──▶ spec.md ──approve──▶ /plan ──▶ plan.md ──approve──▶ /tasks ──▶ tasks.md ──approve──▶ /implement ──▶ PR
```

## 1. Specify: `spec.md` (what and why)

- Copy `specs/_templates/spec-template.md`. Number specs sequentially with three digits.
- Write user stories and **testable** acceptance criteria (`AC-1`, `AC-2`, …) in Given/When/Then form.
- Mark every unknown with `[NEEDS CLARIFICATION: question]`. A spec with open markers can't be approved.
- No technology names (no "Compose", "Room", …) unless the technology *is* the requirement, as in spec 001.

## 2. Plan: `plan.md` (how)

- Only start once `spec.md` is `Status: Approved`.
- Run the **constitution check**: list each article that applies and state how the plan complies, or why it needs an exception.
- Describe the affected layers, new or changed types, data flow, the test strategy per AC, and risks.

## 3. Tasks: `tasks.md` (in what order)

- Only start once `plan.md` is `Status: Approved`.
- Use small, ordered, checkable tasks (`- [ ] T001 …`). Mark tasks that can run in parallel with `[P]`.
- Test tasks come **before** their implementation tasks and reference ACs.
- The last task is always "Verify all ACs and run the full check suite".

## 4. Implement

- Work task by task and tick each box when it is done.
- If reality forces a deviation, stop, update `spec.md`/`plan.md`, and ask for re-approval.
- Open a PR titled `NNN: <feature>` that links the spec folder.

## Status values

Every spec, plan and tasks file starts with a `Status:` line:

| Status | Meaning |
|---|---|
| `Draft` | Being written; may contain `[NEEDS CLARIFICATION]` |
| `In Review` | Complete; waiting for a human |
| `Approved` | A human signed off; the next phase may start |
| `Implemented` | Merged; spec describes shipped behavior |
| `Superseded by NNN` | Replaced by a later spec |

## Changing shipped behavior

Write a new spec that references the old one. Set the old spec to `Superseded by NNN` only if it is entirely replaced; otherwise leave it as is and let the new spec describe the delta.
