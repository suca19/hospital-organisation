# Hospital Organisation – Assessment Plan (Algorithms & Constructs)

> **How to use this plan**
> - You write the code. This plan explains concepts, gives *small generic examples* (not the solution), and lists checkpoints.
> - Work through the phases in order. Tick `[x]` each item only when it compiles, runs, and you can explain it out loud.
> - At the end of each phase, ask Claude: **"use the phase-reviewer agent to review Phase N"**. The agent checks your code against this file and gives hints, not answers.
> - Stuck? Paste the error or describe what you expected vs. what happened. Debugging is part of learning.

---

## 0. Requirements summary (from `.req/.requirement.txt`)

Chosen organisation: **Hospital**

| # | Section | Marks | Must have |
|---|---------|-------|-----------|
| R1 | Sort a dummy list | 25 | Read `Applicants_Form.txt`, sort alphabetically using a **RECURSIVE** sort, display **first 20** names, **written justification** (why this algorithm vs. others, *not* how it works) |
| R2 | Search the list | 25 | Search by name in the **sorted** list using a **RECURSIVE** search, return **Manager Type** and **Department**, **written justification** |
| R3 | Add new user input | 20 | Input Name, choose Manager Type, choose Department; **validate** both exist; add to the list in memory; **display all newly added records** |
| R4 | Employee hierarchy, binary tree | 20 | Each node stores **Name, Designation, Department**; **≥ 20 records**; **max 2 children per node**; hierarchy makes sense (higher ranks near the top); display with **level-order traversal**; show **tree height** and **total node count** |
| R5 | Enums for menus | (required everywhere) | Use **enums** for the menu options and iterate over them to print menus. This applies to all sections, so Manager Type and Department should be enums too. |

The menu from Example 1 that your program should copy:
```
Please enter the filename to read:
Applicants_Form.txt
File read successfully
Do You wish to SORT or SEARCH:
1. SORT
2. SEARCH
3. ADD RECORDS
4. Create Binary Tree
5. EXIT
```

---

## Current state of the repo (review done on 2026-10-30)

Fix these in Phase 1. Each one teaches something.

1. **`src/app/Enums.java` is a class with `int` fields, not an enum.** The brief says *Enums*. The fields are also instance fields (not `static`), so you can't even reach them without `new Enums()`. → Phase 1.
2. **Package names don't match folders.** `.vscode` sets the source root to `src`, so a file at `src/app/model/Worker.java` must declare `package app.model;`, not `package model;`. `Main.java` has no package at all. → Pick one convention (below) and apply it everywhere.
3. **`.vscode/seetings.json` is misspelled.** VS Code only reads `settings.json`, so this file is currently ignored.
4. **`Doctor`, `Nurse`, `Director` don't extend `Worker`**, and they're empty. Decide whether you need subclasses at all (see Phase 1 design note).
5. **`Applicants_Form.txt` is not in the project.** Get it from Moodle/your lecturer. If you can't, create your own dummy file (≥ 30 names, random order).
6. `Worker` uses the field name `position`, while the brief says "Manager Type" and "Designation". Pick one name and use it consistently. `designation` or `managerType` both work.

---

## Phase 0 – Setup and understanding (no algorithms yet)

**Goal:** a project that compiles and runs from the terminal.

- [ ] Rename `.vscode/seetings.json` → `.vscode/settings.json`
- [ ] Decide on packages. Recommended layout:
  ```
  src/app/Main.java                 package app;
  src/app/menu/MainMenuOption.java  package app.menu;
  src/app/model/Employee.java       package app.model;
  src/app/model/ManagerType.java    package app.model;
  src/app/model/Department.java     package app.model;
  src/app/algorithms/Sorter.java    package app.algorithms;
  src/app/algorithms/Searcher.java  package app.algorithms;
  src/app/tree/EmployeeTree.java    package app.tree;
  src/app/tree/TreeNode.java        package app.tree;
  src/app/io/FileLoader.java        package app.io;
  ```
- [ ] Put `Hospital_Applicants_Form.txt` in the project root. Open it and **write down its format**: just names, or `name,type,dept`? This decides how R2 gets Manager Type/Department.
- [ ] Compile and run from the project root:
  ```bash
  javac -d bin $(find src -name "*.java") && java -cp bin app.Main
  ```
- [ ] Add `bin/` to `.gitignore`

**Check yourself:** Why must the `package` line match the folder path? (Answer: the compiler and JVM use the package name to find the `.class` file inside `bin/`.)

---

## Phase 1 – Enums and the data model (R5 + foundation)

### Concept: what an enum is
An enum is a fixed set of named constants, and Java checks it at compile time. It can hold fields, constructors and methods. `values()` returns every constant in order, which makes it easy to *iterate* over menu options. The brief asks for exactly that.

### Generic example (coffee shop, not your menu)
```java
public enum CoffeeSize {
    SMALL(1, "Small 250ml"),
    MEDIUM(2, "Medium 350ml"),
    LARGE(3, "Large 450ml");

    private final int code;
    private final String label;

    CoffeeSize(int code, String label) {   // enum constructors are always private
        this.code = code;
        this.label = label;
    }

    public int getCode()     { return code; }
    public String getLabel() { return label; }

    public static CoffeeSize fromCode(int code) {
        for (CoffeeSize s : values()) {
            if (s.code == code) return s;
        }
        return null;   // or throw IllegalArgumentException, your choice
    }
}

// printing a menu by iterating:
for (CoffeeSize s : CoffeeSize.values()) {
    System.out.println(s.getCode() + ". " + s.getLabel());
}
```

### Your tasks
- [ ] Replace `Enums.java` with a real enum for the main menu: `SORT`, `SEARCH`, `ADD_RECORDS`, `CREATE_BINARY_TREE`, `EXIT`
- [ ] Create a `ManagerType` enum with hospital roles. Give each one a **rank** number; you'll need it for the tree in Phase 5. Example ranks: Medical Director (1) → Head of Department (2) → Consultant / Senior Nurse Manager (3) → Team Lead / Ward Manager (4) → Staff (5)
- [ ] Create a `Department` enum: e.g. Emergency, Cardiology, Paediatrics, Radiology, Surgery, HR, Administration
- [ ] Create an `Employee` class (or fix `Worker`) with `name`, `managerType` (type `ManagerType`, **not** String), `department` (type `Department`), plus a `toString()`
- [ ] Main menu printed by **looping over `values()`**, with no hard-coded `println("1. SORT")`

### Design note: subclasses (`Doctor`, `Nurse`, `Director`)
Ask yourself: does a Doctor *behave* differently from a Nurse in this program? Here the only difference is the data (type + department), so an enum field is simpler than inheritance. You may keep subclasses to show OOP, but don't let them complicate the sort, search and tree code, which should all work with `Employee`.

**Check yourself:** What does `ManagerType.valueOf("CONSULTANT")` do, and what happens with `valueOf("consultant")`?

➡️ **Review: "use the phase-reviewer agent to review Phase 1"**

---

## Phase 2 – Menu loop, file reading and input validation

### Concept: a robust input loop
```java
Scanner sc = new Scanner(System.in);

int readInt(Scanner sc, int min, int max) {
    while (true) {
        String line = sc.nextLine().trim();
        try {
            int n = Integer.parseInt(line);
            if (n >= min && n <= max) return n;
        } catch (NumberFormatException e) { /* fall through */ }
        System.out.println("Please enter a number between " + min + " and " + max + ":");
    }
}
```
Prefer `nextLine()` + `parseInt`. Mixing `nextInt()` with `nextLine()` is the most common Scanner bug: `nextInt()` leaves the newline in the buffer, so the next `nextLine()` comes back empty.

### Concept: reading a file
Use `java.nio.file.Files.readAllLines(Path.of(filename))` or a `BufferedReader`. Catch `IOException` and ask for the filename again; don't crash.

### Your tasks
- [ ] Ask "Please enter the filename to read:", load it, print "File read successfully" (or a friendly error and ask again)
- [ ] Parse each line into an `Employee`. **If the file only has names**, decide how they get a ManagerType and Department, e.g. assign them randomly from the enum `values()`, and write that assumption in your report
- [ ] Skip blank lines and trim whitespace
- [ ] Main menu loops until `EXIT` is chosen, using a `switch` on the enum constant (not on raw ints)
- [ ] Invalid input (letters, 0, 99, empty) never crashes the program

**Check yourself:** Can you `switch` on an enum? What do the case labels look like?

➡️ **Review: "use the phase-reviewer agent to review Phase 2"**

---

## Phase 3 – R1: Recursive sort, first 20 names (25 marks)

### Concept: recursion
Every recursive method needs:
1. a **base case**, where it stops without calling itself, and
2. a **recursive case** that moves *closer* to the base case.

If either is missing you get a `StackOverflowError`.

### Warm-up exercise (do this first, in a scratch file)
Find the maximum of an array **recursively by splitting it in half**:
```java
static int max(int[] a, int lo, int hi) {
    if (lo == hi) return a[lo];               // base case: one element
    int mid = (lo + hi) / 2;
    int left  = max(a, lo, mid);              // solve left half
    int right = max(a, mid + 1, hi);          // solve right half
    return Math.max(left, right);             // combine
}
```
This *split → solve each half → combine* pattern is called **divide and conquer**. Two of the recursive sorts work this way.

### Choosing the algorithm
Recursive candidates: **Merge Sort**, **Quick Sort**, (recursive Insertion/Selection/Bubble also exist).
Compare them on these questions and pick one. Your justification must answer them **without describing how the algorithm works**:
- Worst-case time complexity? Does it stay fast when the data is *already sorted*? (This matters: after a sort you add records and sort again.)
- Is it **stable**, i.e. do two people with the same name keep their original order?
- Memory cost: does it need extra space?
- How does it behave on Strings and on a list of ~hundreds/thousands of names?
- Why not the simple O(n²) ones?

### Your tasks
- [ ] Implement your chosen sort **recursively** on `List<Employee>` (or an array), comparing by name with `compareToIgnoreCase`
- [ ] Don't use `Collections.sort`, `List.sort`, `Arrays.sort` or streams `.sorted()`; those don't count
- [ ] Display only the **first 20** names (and handle a list with fewer than 20 without crashing)
- [ ] Test: empty list, 1 item, 2 items, duplicates, names with different capitalisation ("alice" vs "Bob")
- [ ] Write the **justification** paragraph in your own words (put a draft in `report.md` or your report document)

**Check yourself:** Why does `"bob".compareTo("Alice")` give a different result from `compareToIgnoreCase`?

➡️ **Review: "use the phase-reviewer agent to review Phase 3"**

---

## Phase 4 – R2: Recursive search (25 marks)

### Choosing the algorithm
Candidates: **Linear search** (can be written recursively) vs **Binary search** (naturally recursive).
For the justification, think about:
- What does each one need from the data? (You already sorted it in Phase 3, so use that.)
- Time complexity for 1,000 names, and for 1,000,000?
- What happens if the list is **not** sorted when the user searches? Your program must make sure it is.

### Hints (try first, then ask)
- Your recursive method needs the list, the target name, and the current range (`low`, `high`).
- What's the base case when the name is **not found**?
- Watch out for off-by-one bugs: `mid - 1` / `mid + 1`, and `low > high` vs `low >= high`.

### Your tasks
- [ ] Recursive search by name (case-insensitive) on the **sorted** list
- [ ] On success print Name, **Manager Type** and **Department**
- [ ] On failure print a friendly "not found" message
- [ ] If the list was changed (records added) since the last sort, sort it before searching
- [ ] Test: first name, last name, middle name, a name not in the list, lowercase input
- [ ] Optional extra: duplicate names. Binary search finds *one* match; can you show all of them?
- [ ] Write the **justification** paragraph

➡️ **Review: "use the phase-reviewer agent to review Phase 4"**

---

## Phase 5 – R3: Add new records with validation (20 marks)

Follow Command line Example 2 from the brief:
```
Please input the Employee Name:
Please select from the following Management Staff:   ← printed by looping ManagerType.values()
Please select the Department:                         ← printed by looping Department.values()
"John Joe" has been added as "Assistant Manager" to "Foreign Exchange" successfully!
```

### Your tasks
- [ ] Name: reject empty or blank input (optional: reject digits)
- [ ] Manager Type and Department chosen **from the enum menus**. Invalid numbers are rejected and the user is asked again. This is the "must exist" validation.
- [ ] Add the new `Employee` to the main list in memory
- [ ] Keep a separate list of **newly added records** and print **all** of them after each add
- [ ] Mark the main list as "unsorted" so Phase 4's search re-sorts it
- [ ] Optional: allow adding several in a row ("Add another? Y/N")

**Check yourself:** Why is choosing from an enum menu stronger validation than letting the user type the department name?

➡️ **Review: "use the phase-reviewer agent to review Phase 5"**

---

## Phase 6 – R4: Employee hierarchy binary tree (20 marks)

### Concept: binary tree vs. binary *search* tree
A **Binary Search Tree** orders nodes by a key (left < root < right). That's **not** an org chart.
Here you want a **hierarchy**: the most senior person at the root and lower ranks further down. Each node has at most 2 children (`left`, `right`).

A clean approach:
1. Take ≥ 20 employees and **sort them by `ManagerType` rank** (you can reuse your recursive sort with a different comparison).
2. Insert them **level by level**, filling each level left to right (a *complete* binary tree). The Director becomes the root, Heads of Department go on level 1, and so on.
3. Level-by-level insertion uses a **queue**: the first node in the queue that has a free child slot gets the new node.

### Pseudocode (you turn this into Java)
```
insert(employee):
    newNode = Node(employee)
    if root is null: root = newNode; return
    queue = [root]
    while queue not empty:
        current = queue.removeFirst()
        if current.left is null:  current.left = newNode;  return
        else queue.add(current.left)
        if current.right is null: current.right = newNode; return
        else queue.add(current.right)

levelOrder():
    queue = [root]
    while queue not empty:
        print every node currently in the queue as one level   (tip: use queue.size() at the start of the level)
        add their non-null children to the queue

height(node):   base case → node is null → return 0 (or -1, pick a definition and state it)
                otherwise → 1 + max(height(left), height(right))
count(node):    base case → null → 0
                otherwise → 1 + count(left) + count(right)
```
Java tip: `Queue<TreeNode> q = new LinkedList<>();` then `offer()`, `poll()`, `isEmpty()`.

### Your tasks
- [ ] `TreeNode` with `Employee` data (Name, Designation, Department), `left`, `right`
- [ ] `EmployeeTree` with `insert`, `levelOrder`, `height` (recursive), `countNodes` (recursive)
- [ ] Insert **≥ 20** records, using the file data and/or a built-in dummy list of hospital staff
- [ ] Higher ranks appear on the higher levels of the output
- [ ] Print the level-order traversal grouped by level, e.g. `Level 0: Dr. X (Medical Director, Administration)`
- [ ] Print **tree height** and **total number of nodes**
- [ ] Sanity-check with maths: a complete binary tree with 20 nodes has 5 levels (0–4). Does your height match the definition you chose?

**Check yourself:** Explain in two sentences why a complete binary tree can't perfectly represent "a Head of Cardiology manages 4 consultants", and how you'd mention that limitation in the report.

➡️ **Review: "use the phase-reviewer agent to review Phase 6"**

---

## Phase 7 – Polish, report and final check

- [ ] Every menu in the program comes from an enum's `values()`
- [ ] No crash on any bad input (do a "monkey test": type rubbish everywhere)
- [ ] Code is readable: meaningful names, short methods, comments only where the *why* isn't obvious
- [ ] Report contains: organisation analysis (hospital structure, roles, departments, challenges), sort justification, search justification, the assumptions you made (file format, how the tree is ordered), and screenshots of each menu option working
- [ ] Commit regularly with clear messages (`git commit -m "Add recursive merge sort for employee names"`)
- [ ] The whole program compiles from a clean `bin/` with the command from Phase 0

➡️ **Final review: "use the phase-reviewer agent to do a FINAL review of all phases"**

---

## Debugging cheat-sheet

| Symptom | Likely cause |
|---|---|
| `error: package model does not exist` / class not found | `package` line doesn't match the folder, or you're running from the wrong folder / with the wrong `-cp` |
| `StackOverflowError` | Recursive method has no base case, or the recursive call doesn't shrink the problem |
| Next `nextLine()` returns `""` | You used `nextInt()` before it; read whole lines instead |
| `NoSuchFileException` | Path is relative to the folder you run `java` from, not to the `.java` file |
| Binary search never finds a name that's in the list | List isn't sorted, a case mismatch between sort and search comparisons, or an off-by-one in `mid ± 1` |
| `NullPointerException` in tree code | Accessing `node.left.data` without checking `node.left != null` |
| `IndexOutOfBoundsException` when printing 20 names | The list has fewer than 20 items; use `Math.min(20, list.size())` |

Debugging habit: before changing code, **say what you expect to happen and what actually happened**. Add a temporary `System.out.println` with `low`, `mid`, `high` (or whatever variables matter) on each recursive call and watch the values.
