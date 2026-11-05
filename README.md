# Hospital Organisation – Algorithms and Constructs

A Java terminal application that manages the staff of a hospital (Riverside General Hospital).
It loads staff records from a file, sorts and searches them with recursive algorithms, lets the user
add new records with validation, and (Phase 6) builds the staff hierarchy as a binary tree.
All menus are driven by Java enums.

## How to run

Requires **Java 21**. From the project root:

```
javac -d bin $(find src -name "*.java")
java -cp bin app.Main
```

When asked for the file name, type:

```
Hospital_Applicants_Form.txt
```

## Data file

We use the next file -> `Hospital_Applicants_Form.txt`
(50 hospital staff records)

The file is comma-separated (CSV) and the first line is a header:

| Column | Field | Used as |
|---|---|---|
| 0 | First name | name |
| 1 | Last name | name (joined with the first name: "First Last") |
| 2 | Gender | not used |
| 3 | Email | not used |
| 4 | Salary | not used |
| 5 | Department | `Department` enum |
| 6 | Profession | `Profession` enum → `Doctor` / `Nurse` / `Technician` |
| 7 | Job title | `ManagerType` enum |
| 8 | Hospital | not used |

## Main menu

```
1. Sort Employees
2. Search Records
3. Add Records
4. Create Binary Tree
5. Exit
```

The menu is printed by iterating `Option.values()`, so it is never hard-coded.

## Features

| Requirement | Feature | Where |
|---|---|---|
| R1 – Sort | Recursive **Merge Sort** by name (case-insensitive); displays the first 20 employees | `algorithms/Sorter.java`, `Menu.sortEmployees()` |
| R2 – Search | Recursive **Binary Search** by full name (case-insensitive) on the sorted list; shows Manager Type and Department | `algorithms/Searcher.java`, `Menu.searchRecords()` |
| R3 – Add records | Name, Profession, Manager Type and Department entered by the user; the three choices come from enum menus, so only existing values are accepted; all newly added records are displayed | `ui/AddRecordForm.java` |
| R4 – Binary tree | *In progress* | – |
| R5 – Enums | `Option` (main menu), `Profession`, `ManagerType`, `Department` (add-record menus and data validation) | `Option.java`, `model/` |
