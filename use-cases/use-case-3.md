# USE CASE: 3 Produce a Report on the Salary of Employees of a Given department

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *department manager* I want *to produce a report on the salary of employees in my department* so that *I can support financial reporting of the organisation.*

### Scope

Company.

### Level

Primary task.

### Preconditions

We know the department.  Database contains current employee salary data.

### Success End Condition

A report is available for the deparmtment manager to provide to finance.

### Failed End Condition

No report is produced.

### Primary Actor

Department manager.

### Trigger

A request for finance information is sent to the department manager.

## MAIN SUCCESS SCENARIO

1. Finance request salary information for a given department from the department manager.
2. The department manager captures name of the department to get salary information for.
3. The department manager extracts current salary information of all employees of the given department.
4. The department manager  provides report to finance.

## EXTENSIONS

None.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.3