[README.md](https://github.com/user-attachments/files/32389739/README.md)
# Item Collections Demo

**DEPI – Software Testing Track | Part 01: Java Fundamentals**
Instructor: Mina Younan — Session 08, Task 3

A small Java Maven project that demonstrates how the same `Item` data
(id, name, price) can be **stored, accessed, searched, modified, and
displayed** using different Java data structures and collection types.

## Collections covered

| # | Type | Used as | Why this one |
|---|------|---------|--------------|
| 1 | One-dimensional array | List of item names | Fixed-size, known-in-advance data with fast index access |
| 2 | Two-dimensional array | ID/Price table | Fixed-shape tabular numeric data |
| 3 | `List` / `ArrayList` | Main item catalog | Resizable, ordered, fast random access by index |
| 4 | `LinkedList` | Processing queue | Cheap insert/remove from both ends (`addFirst`/`addLast`/`removeFirst`) |
| 5 | `Set` / `HashSet` | Scanned item IDs | Automatic rejection of duplicate items |
| 6 | `HashMap` | Item catalog by ID | O(1) lookup by key instead of a linear search |

Each collection type is not just used, but paired with a task where its
strengths actually matter — the project avoids applying every type to
the same generic job.

## Project structure

```
item-collections-demo/
├── pom.xml
└── src/
    └── main/
        └── java/
            └── com/
                └── depi/
                    └── items/
                        ├── Item.java   # shared POJO used across all demos
                        └── Main.java   # one method per collection type
```

- **`Item.java`** — a simple POJO with `id`, `name`, `price`.
  `equals()`/`hashCode()` are overridden on `id` only, which is what
  lets `HashSet` correctly detect and reject duplicate items.
- **`Main.java`** — six self-contained demo methods, each printing its
  store/access/search/modify/display steps to the console.

## Requirements

- Java 11+
- Maven 3.6+

## Build & run

```bash
# Compile
mvn compile

# Run
mvn exec:java -Dexec.mainClass="com.depi.items.Main"

# Or package into a runnable jar
mvn package
java -jar target/item-collections-demo.jar
```

Alternatively, open the folder in any IDE (IntelliJ, Eclipse, VS Code)
as a Maven project and run `Main.java` directly.

## Sample output

```
=== 1) One-Dimensional Array ===
Access index 2: Monitor
Search 'Monitor' found at index: 2
Display all: [Keyboard, Wireless Mouse, Monitor, Headset]

=== 2) Two-Dimensional Array ===
Access row 2 -> ID=103, Price=25000.0
Most expensive item -> ID=103, Price=25000.0
Display all rows:
  ID=101, Price=500.0
  ID=102, Price=630.0
  ID=103, Price=25000.0
  ID=104, Price=7000.0
...
```

(Full output covers all six sections when run.)

## License

Coursework project for the DEPI Software Testing Track — free to use
for learning and reference.
