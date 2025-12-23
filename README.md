# JaCoCo + Payara Micro + Arquillian Example

This project is a **working example** demonstrating how to:

* Build a **Jakarta EE 10** application
* Run it on **Payara Micro**
* Test it using **JUnit 5 + Arquillian**
* Collect **code coverage** using **JaCoCo** for:

  * Unit tests
  * Integration tests running inside Payara Micro
* Generate a **merged coverage report**

The repository is intended as a **reference project** for setting up real-world test coverage when using Payara Micro.

---

## 🧱 Tech Stack

* **Java:** 17
* **Jakarta EE:** 10
* **Application Server:** Payara Micro 6
* **Build Tool:** Maven
* **Testing:**

  * JUnit 5 (Jupiter)
  * Arquillian
* **Code Coverage:** JaCoCo

---

## 📦 Project Structure

```
jacoco-payara-micro-example
├── pom.xml
├── mvnw / mvnw.cmd
├── src
│   ├── main
│   │   └── java
│   │       └── com.example
│   └── test
│       ├── java
│       │   ├── *Test.java     # Unit tests
│       │   └── *IT.java       # Integration tests (Arquillian)
│       └── resources
│           └── arquillian.xml
└── target
    └── jacoco
        ├── jacoco-ut.exec
        ├── jacoco-payara-it.exec
        ├── jacoco-merged.exec
        └── coverage-report/
```

---

## ⚙️ Maven Configuration Highlights

### Packaging

* The project is packaged as a **WAR**
* A **Payara Micro bundle** is created during the `package` phase

### Testing Strategy

| Test Type         | Tool                        | JVM              |
| ----------------- | --------------------------- | ---------------- |
| Unit tests        | Maven Surefire              | Local JVM        |
| Integration tests | Maven Failsafe + Arquillian | Payara Micro JVM |

### Code Coverage

* JaCoCo agent is attached to:

  * Unit test JVM
  * Payara Micro JVM (integration tests)
* Execution data is **merged**
* A single HTML report is generated

---

## 🚀 How to Build and Run

### 1️⃣ Prerequisites

Make sure you have:

* Java 17 installed
* Maven 3.9+

Verify:

```bash
java -version
mvn -version
```

---

### 2️⃣ Build the Project

```bash
 mvn clean package -Pbuild-test-microbundle -DskipTests
```

This will:

* Compile the application
* Create a Payara Micro bundle

---

### 3️⃣ Run Tests with Coverage

Use the provided Maven profile:

```bash
mvn verify -Ptests-with-coverage-report
```

This will:

1. Run unit tests with JaCoCo
2. Start Payara Micro
3. Deploy the application
4. Run Arquillian integration tests
5. Collect JaCoCo data from both JVMs
6. Merge coverage data
7. Generate an HTML report

---

## 📊 Coverage Report

After the build completes, open:

```
target/jacoco/coverage-report/index.html
```

This report contains **merged coverage** from:

* Unit tests
* Integration tests running inside Payara Micro

---

---

## 🧠 Key Lessons from This Project
* Payara Micro runs in a **separate JVM**, so JaCoCo must use `tcpserver` mode
* JaCoCo execution data must be **merged** manually

---

## ❗ Common Pitfalls

* Missing `junit-jupiter-engine` dependency → tests will not be discovered
* Forgetting to pass JaCoCo agent to Payara JVM → missing coverage

---

## 📝 License

This project is provided as-is for educational and reference purposes.

Feel free to fork, adapt, and improve it.
