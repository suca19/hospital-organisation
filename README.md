# Hospital Organisation – Algorithms and Constructs

A Java terminal application that manages the staff of a hospital (Riverside General Hospital).
It loads staff records from a file, sorts and searches them with recursive algorithms, lets the user
add new records with validation, and builds the staff hierarchy as a binary tree.
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
| 8 | Hospital | not used (every record is from the same hospital, Riverside General Hospital) |

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
| R4 – Binary tree | Hospital hierarchy: employees sorted by rank (Director first) and inserted level by level, at most 2 children per node; shows the level-order traversal, the tree height and the number of nodes | `tree/EmployeeTree.java`, `tree/TreeNode.java`, `Menu.createBinaryTree()` |
| R5 – Enums | `Option` (main menu), `Profession`, `ManagerType`, `Department` (add-record menus and data validation) | `Option.java`, `model/` |

## Project structure

```
src/app/
├── Main.java                 starts the program
├── Option.java               main menu options (enum)
├── ui/                       user interaction
│   ├── Menu.java             main loop, sort and search screens
│   └── AddRecordForm.java    add-record screen and validation
├── model/                    hospital domain
│   ├── Employee.java         abstract base class (name, department, manager type)
│   ├── EmployeeFactory.java  creates the right subclass for a profession
│   ├── Profession.java       DOCTOR, NURSE, TECHNICIAN
│   ├── ManagerType.java      HOSPITAL_DIRECTOR … STAFF, each with a rank
│   ├── Department.java       EMERGENCY, CARDIOLOGY, …
│   ├── types/                Doctor, Nurse, Technician (extend Employee)
│   ├── department/           HospitalDepartment (abstract) → Clinical, Diagnostic, Support + DepartmentFactory
│   └── position/             Position (abstract) → Executive, Management, Staff + PositionFactory
├── data/
│   └── EmployeeReader.java   reads and validates the data file
├── algorithms/
│   ├── Sorter.java           recursive merge sort (by name or by rank)
│   └── Searcher.java         recursive binary search
└── tree/
    ├── TreeNode.java         one employee + left and right child
    └── EmployeeTree.java     insert, level-order display, height, node count
```

## Design decisions

- **Packages by responsibility:** `ui` (user interaction), `model` (domain), `data` (file loading),
  `algorithms` (sorting and searching). Each class has one job.
- **Enums instead of free text:** Department, Manager Type and Profession are enums, so invalid values
  cannot exist in the program. The user chooses them from numbered menus generated from `values()`.
- **Inheritance:** `Employee` is abstract; `Doctor`, `Nurse` and `Technician` extend it and override
  `getProfession()`. Sorting, searching and display all work with `Employee`, so they don't depend on
  the subclasses.
- **Three parent classes, three types each:** `Employee` (what the person does: Doctor, Nurse,
  Technician), `HospitalDepartment` (where they work: Clinical, Diagnostic, Support) and `Position`
  (their level: Executive, Management, Staff). The enums hold the fixed values used in the menus, and the
  classes add behaviour through polymorphism, e.g. `getCategory()`, `isOpen24Hours()` and
  `getResponsibility()`, which are shown in the search results.
- **Factory:** `EmployeeFactory` is the only class that knows the subclasses. Both the file reader and
  the add-record form use it, so adding a new profession only changes the enum and the factory.
  `DepartmentFactory` and `PositionFactory` do the same for departments and positions.
- **One shared `Scanner`:** `Menu` creates the only `Scanner` on `System.in` and passes it to
  `AddRecordForm`, which avoids two scanners competing for the same input.

## Assumptions and validation

- The first line of the data file is a header and is skipped. Blank lines are ignored.
- Lines with missing columns, or an unknown department, profession or job title, are skipped with a message.
- If the file cannot be read or contains no valid records, the user is asked for the file name again.
- Menu input must be a number in the menu range; anything else is rejected and asked again.
- Names cannot be empty. The search needs the full name ("First Last") and ignores capital letters.
- The list is sorted again before every search, so newly added employees are found.
- New records are stored in the program's memory (as required); they are not written back to the file.

## Algorithm justifications

### Why Merge Sort (R1)

I chose Merge Sort because it is the safest recursive sort for a staff list that keeps growing.

- **It is always fast.** Merge Sort takes O(n log n) time in every case, including the best, average
  and worst case. Quick Sort is also O(n log n) on average, but it can drop to O(n²) when the data is
  already sorted or nearly sorted. That is a real risk in this program: after the first sort the list stays
  sorted, and new employees are only added at the end before it is sorted again. Recursive Insertion,
  Selection and Bubble Sort are O(n²) all the time, so they get slow very quickly as the list grows.
- **Its recursion stays shallow.** Merge Sort only goes about log₂ n calls deep: around 6 levels for
  our 50 employees, and only about 20 for a million. Recursive Insertion, Selection and Bubble Sort, and
  Quick Sort in its worst case, go n calls deep. I tested the default Java call stack on my machine, and
  it crashed with a `StackOverflowError` after about 15,700 nested calls. Those sorts would crash on a
  hospital with around 16,000 staff, while Merge Sort would not come close to the limit.
- **It is stable.** Employees that compare as equal keep their original order. I used this when building
  the hierarchy tree: I sort by name first and then by rank, so inside each rank the names stay in
  alphabetical order without writing any extra code. Quick Sort is not stable, so it could not do this.
- **Its one downside is not a problem here.** Merge Sort needs O(n) extra memory for the temporary
  lists, while Quick Sort sorts in place. But a hospital has thousands of staff, not billions, so the extra
  memory is very small, and I think a guaranteed running time and no risk of a crash are worth more.

### Why Binary Search (R2)

I chose Binary Search because the list is already sorted, and that is exactly what Binary Search needs.

- **It is much faster than a linear search.** Binary Search is O(log n), so it needs at most about 6
  comparisons to find a name among our 50 employees, and only about 20 among a million. A linear
  search may have to check every employee, which means up to 50 checks, or a million.
- **We already pay for sorting.** The list must be sorted for Requirement 1 anyway, so the main condition
  of Binary Search comes for free. Linear search does not use the sorted order at all, so it would waste
  the work the sort has already done.
- **It is safe as a recursive method.** Each call only searches half of the remaining list, so the
  recursion is about log n deep, around 6 calls for 50 employees. A recursive linear search would go one
  call per employee, and on a large list it would hit the same `StackOverflowError` limit as the O(n²) sorts.
- **The results are consistent with the sort.** The search compares names in the same way as the sort
  (alphabetical and ignoring capital letters). So the search always moves in the right direction, and
  "conor burke" finds "Conor Burke".
