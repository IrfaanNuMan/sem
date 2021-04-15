# USE CASE: 12 Produce a Report on the Salary of all Employees 

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *Census Evaluator* I want *to produce a report on the top N populated cities in the world where N is provided by the user* so that *I can provide statistics for the organisation.*

### Scope

Company.

### Level

Primary task.

### Preconditions

The Database contains all the cities in the world and their population.

### Success End Condition

A report is available on all the the top N cities in the world.

### Failed End Condition

No report is produced.

### Primary Actor

Census Advisor.

### Trigger

A request for population the top N cities in the world is requested by the Census Advisor.

## MAIN SUCCESS SCENARIO

1. Census Advisor requests a report on the population of the top N cities in the world.
2. Census Evaluator inputs N cities to be extracted.
3. Census Evaluator extracts N cities in all the world.

## EXTENSIONS

None

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0