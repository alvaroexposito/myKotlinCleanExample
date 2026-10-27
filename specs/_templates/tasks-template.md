# Tasks NNN: <Feature name>

Status: Draft
Plan: [plan.md](plan.md) (must be Approved)

Legend: `[P]` = can run in parallel with the previous task · tests come before implementation.

## Phase 1: Setup

- [ ] T001 <task>

## Phase 2: Tests first (must fail)

- [ ] T010 [P] Write test for AC-1 in `<TestClass>`

## Phase 3: Implementation

- [ ] T020 <task> (makes AC-1 pass)

## Phase 4: Verification

- [ ] T090 All ACs have passing tests; run `./gradlew lint ktlintCheck detekt testDebugUnitTest`
- [ ] T091 Update spec/plan status to `Implemented`

## Approval

- [ ] Approved by: ____ on ____
