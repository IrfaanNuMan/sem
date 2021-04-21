As an *Census Evaluator* I want *to generate a report on the top N populated cities in a continent* where N is provided by the user* so that, so that *I can support the reporting of the top N populated cities.*
### Scope

Organisation.

### Level

Primary task.

### Preconditions

The Database contains census data.

### Success End Condition

A report is generated for CE on the top N populated cities in a continent.

### Failed End Condition

No report is produced.

### Primary Actor

Census Evaluator.

### Trigger

A request for population information of all employees is sent to CE.

## MAIN SUCCESS SCENARIO

1. Census Evaluator request population information for N cities in a continent.
2. Census Evaluator captures population information for N cities in a continent.
3. Census Evaluator extracts population information for N cities in a continent.
4. Census Evaluator provides report to organisation.

## EXTENSIONS

None

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0