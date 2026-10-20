# Java CRUD Application (Spring Boot + Vaadin)

A simple CRUD (Create, Read, Update, Delete) application for managing customers, built with **Spring Boot**, **Vaadin**, **Spring Data JPA** and an **H2** in-memory database.

The project follows the official Spring guide [Creating CRUD UI with Vaadin](https://spring.io/guides/gs/crud-with-vaadin/). Its purpose is to practise Java, Git, GitHub and collaboration.

---

## Features

- **Create** a new customer with the *New customer* button
- **Read** all customers in a table (grid)
- **Update** a customer by selecting a row, editing the fields and clicking *Save*
- **Delete** a customer by selecting a row and clicking *Delete*
- **Filter** customers by last name

Sample data (5 customers) is loaded automatically at startup. Because H2 is an in-memory database, all changes are reset when the application restarts.

---

## Technologies

| Tool | Purpose |
|---|---|
| Java 21+ | Programming language |
| Spring Boot | Application framework |
| Vaadin | Web user interface written in Java |
| Spring Data JPA | Database access |
| H2 Database | In-memory database |
| Maven | Build tool and dependency management |
| Git & GitHub | Version control and hosting |
| IntelliJ IDEA | IDE |

---

## Project structure

```
java-crud/
├── .gitignore
├── README.md
├── pom.xml                        # Maven configuration and dependencies
├── mvnw, mvnw.cmd                 # Maven wrapper scripts
├── .mvn/wrapper/                  # Maven wrapper settings
└── src/main/java/com/example/crud_with_vaadin/
    ├── CrudWithVaadinApplication.java   # Main class, loads sample data
    ├── Customer.java                    # Entity (database table)
    ├── CustomerRepository.java          # Interface for database access
    ├── CustomerEditor.java              # Form for create / update / delete
    └── MainView.java                    # Main page with the table and filter
```

---

## How to run

**Requirements:** Git and JDK 21 or newer.

```bash
git clone https://github.com/User74169/java-crud.git
cd java-crud
./mvnw spring-boot:run          # Windows: mvnw.cmd spring-boot:run
```

Open **http://localhost:8080** in your browser.

In IntelliJ IDEA you can instead open `CrudWithVaadinApplication.java` and click the green ▶ next to the `main` method.

---

## How this project was built (step by step)

### Step 1: Create a GitHub account

1. Create a GitHub account dedicated to the course at github.com/signup.
2. Include the Student ID in the username (for example `User74169`).
3. Verify the email address.
4. Enable two-factor authentication under **Settings → Password and authentication**.

### Step 2: Create a public repository

1. On GitHub, click **+ → New repository**.
2. Name: `java-crud`.
3. Visibility: **Public**.
4. Tick **Add a README file**.
5. Under **Add .gitignore**, choose **Maven**.
6. Click **Create repository**.

#### Install the tools

Check that Git and Java are installed:

```bash
git --version
java -version
```

- Git: download from git-scm.com
- JDK 21: for example Eclipse Temurin from adoptium.net, or **Download JDK** inside IntelliJ
- IntelliJ IDEA Community Edition

#### Configure Git (one time only)

```bash
git config --global user.name "Your Name"
git config --global user.email "your-github-email@example.com"
git config --global --list       # check the settings
```

> **Note:** `--global` is required. Without it, Git only saves the setting for the current repository and fails with `fatal: not in a git directory` when run outside one.

#### Clone the repository

In IntelliJ: **File → Close Project**, then on the welcome screen click **Clone Repository** and paste:

```
https://github.com/User74169/java-crud.git
```

Or in a terminal:

```bash
git clone https://github.com/User74169/java-crud.git
cd java-crud
git status        # should say: On branch main, nothing to commit
```

> **Tip:** `git clone` creates a new folder. To clone into an existing empty folder, add a dot at the end: `git clone <url> .`

### Step 3: Create the Java CRUD application

#### 3.1 Generate the Spring Boot project

1. Open [Spring Initializr](https://start.spring.io) with these settings:
   - Project: **Maven**
   - Language: **Java**
   - Java: **21**
   - Artifact: `crud-with-vaadin`
   - Dependencies: **Vaadin**, **Spring Data JPA**, **H2 Database**
2. Click **Generate** and unzip the downloaded file.
3. Copy the **contents** of the unzipped folder (not the folder itself) into the cloned `java-crud` folder.
   - Replace `.gitignore` with the Spring one.
   - Keep the existing `README.md`.
4. Open the folder in IntelliJ and click **Load Maven Project** when the popup appears.
5. Run `CrudWithVaadinApplication` and open http://localhost:8080. A "No views found" page means the setup works.

#### 3.2 Add the code

All classes go in `src/main/java/com/example/crud_with_vaadin/` and start with:

```java
package com.example.crud_with_vaadin;
```

| File | Type | Role |
|---|---|---|
| `Customer` | Class | JPA entity with `id`, `firstName`, `lastName` |
| `CustomerRepository` | **Interface** | Extends `JpaRepository`; Spring generates the implementation |
| `CrudWithVaadinApplication` | Class | Main class; adds sample customers at startup |
| `CustomerEditor` | Class | Form with Save, Cancel and Delete buttons |
| `MainView` | Class | Page at `/` with the filter, grid and editor |

#### 3.3 Run and test

1. Restart the application.
2. Open http://localhost:8080.
3. Test create, read, update, delete and filter.

#### 3.4 Commit and push

```bash
git status                  # check what changed (target/ and .idea/ should NOT appear)
git add .
git commit -m "Add Customer CRUD application with Spring Boot and Vaadin"
git push
```

Then open the repository on GitHub to confirm the files are there.

---

## Everyday Git workflow

After every change:

```bash
git status                       # see what changed
git add .                        # stage the changes
git commit -m "Describe change"  # save a snapshot
git push                         # upload to GitHub
```

To get the latest changes from GitHub (for example after a collaborator pushes):

```bash
git pull
```

---

## Troubleshooting

Problems encountered while setting up this project, and how they were fixed.

### `fatal: not in a git directory`
Occurs when running `git config user.email ...` without `--global` outside a repository. Fix: add `--global`.

### Maven sync fails with `Unable to create injector` / `No implementation for ArtifactHandlerManager was bound`
The Maven wrapper downloaded Maven 4, which this IntelliJ version cannot import. Fix: pin the wrapper to Maven 3.9.

1. Open `.mvn/wrapper/maven-wrapper.properties`.
2. Set:
```
   distributionUrl=https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.9.11/apache-maven-3.9.11-bin.zip
```
3. In the **Maven** tool window, click **Reload All Maven Projects**.

It also helps to set **Settings → Build, Execution, Deployment → Build Tools → Maven → Maven home path** to **Bundled (Maven 3)**. Updating IntelliJ to a newer version also solves it.

### `release version 5 not supported` / `SDK does not support source version 1.5`
A symptom of the failed Maven sync: IntelliJ did not read `pom.xml` and fell back to Java 1.5. Fix the Maven sync first. Do **not** downgrade the SDK. Also make sure `pom.xml` does not contain extra `maven.compiler.source` / `maven.compiler.target` lines that conflict with `<java.version>`.

### `Cannot run program ".../openjdk-27/bin/java"`
IntelliJ was pointing to a JDK that was not fully installed. Fix:

1. **File → Project Structure → Project → SDK → Download JDK** and choose a stable version (21 recommended).
2. Set the same JDK under **Modules → Dependencies → Module SDK**.
3. Remove broken JDK entries under **Platform Settings → SDKs**.
4. Set the same JDK for **Maven → Importing → JDK for importer** and **Maven → Runner → JRE**.
5. Reload Maven and **Build → Rebuild Project**.

### Wrong icon in the browser tab
Chrome caches tab icons per address. If another project ran on `localhost:8080` before, its icon may still appear. Open the page in an Incognito window or clear cached images to fix it.

---

## Author

- GitHub: [User74169](https://github.com/User74169)
- GitHub: [olukorede74363](https://github.com/olukorede74363)
- Course project: Java, Git and GitHub practice
