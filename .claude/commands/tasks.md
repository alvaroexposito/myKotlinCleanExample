---
description: Break an approved plan into ordered, test-first tasks
argument-hint: <spec number, e.g. 002>
---

Create the task list for spec $ARGUMENTS.

1. Open `specs/$ARGUMENTS-*/plan.md`. **If its status isn't `Approved`, stop** and tell the user.
2. Create `tasks.md` from `specs/_templates/tasks-template.md`, with `Status: Draft`.
3. Write small, ordered tasks with ids `T0NN`. Each test task references its AC and comes before the matching implementation task. Mark independent tasks `[P]`.
4. End with the verification phase from the template.
5. Set `Status: In Review` and ask the user to approve. Don't write code.
