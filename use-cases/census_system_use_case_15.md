# USE CASE: 15 Produce a Report for The top N populated cities in a country

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *Census Evaluator* I want *to generate a report on the top N populated cities in a country,* where N is provided by the user so that *I can support the reporting of the top N populated cities.*

### Scope

Organisation.

### Level

Primary task.

### Preconditions

The Database contains census data.

### Success End Condition

A report is generated for CE on the top N populated cities in a country.

### Failed End Condition

No report is produced.

### Primary Actor

Census Evaluator.

### Trigger

A request for population information of all employees is sent to CE.

## MAIN SUCCESS SCENARIO

1. Census Evaluator request population information for N cities in a country.
2. Census Evaluator captures population information for N cities in a country.
3. Census Evaluator extracts population information for N cities in a country.
4. Census Evaluator provides report to organisation.

## EXTENSIONS

None

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0