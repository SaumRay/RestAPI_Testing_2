# \# 📚 Library Management API Test Suite

# 

# !\[Java](https://img.shields.io/badge/Java-15%2B-orange?style=flat\&logo=java)

# !\[REST Assured](https://img.shields.io/badge/REST%20Assured-5.4.0-green?style=flat)

# !\[TestNG](https://img.shields.io/badge/TestNG-7.9.0-red?style=flat)

# !\[Maven](https://img.shields.io/badge/Maven-3.x-blue?style=flat\&logo=apachemaven)

# !\[API](https://img.shields.io/badge/API-Swagger%20Petstore-yellow?style=flat)

# 

# A REST API automation test suite built with \*\*REST Assured\*\* and \*\*TestNG\*\*, simulating a \*\*Library Management System\*\* (Add Book, Borrow, Return, Delete) using the \[Swagger Petstore API](https://petstore.swagger.io/).

# 

# \---

# 

# \## 📁 Project Structure

# 

# ```

# LibraryAPITests/

# ├── src/

# │   ├── main/

# │   │   └── java/

# │   └── test/

# │       ├── java/

# │       │   └── com/apitest/

# │       │       ├── base/

# │       │       │   └── BaseTest.java          # Reusable base class (setup/teardown)

# │       │       └── library/

# │       │           └── LibraryTest.java        # Main test class

# │       └── resources/

# │           ├── testng.xml                      # TestNG XML suite runner

# │           ├── logback.xml                     # Logging configuration

# │           └── logs/

# │               └── restassured.log             # Auto-generated request/response logs

# ├── .gitignore

# ├── pom.xml

# └── README.md

# ```

# 

# \---

# 

# \## 🧪 Test Cases

# 

# | # | Test Method | HTTP Method | Endpoint | Description |

# |---|---|---|---|---|

# | 1 | `testGetAllAvailableBooks` | GET | `/pet/findByStatus` | Fetch all available books |

# | 2 | `testAddNewBookToLibrary` | POST | `/pet` | Add a new book |

# | 3 | `testGetBookById` | GET | `/pet/{id}` | Fetch book by chained ID |

# | 4 | `testBorrowBook` | PUT | `/pet` | Borrow a book (status → pending) |

# | 5 | `testReturnBook` | POST | `/pet/{id}` | Return a book (status → available) |

# | 6 | `testDeleteBook` | DELETE | `/pet/{id}` | Delete a book from library |

# | 7 | `testVerifyBookDeleted` | GET | `/pet/{id}` | Negative — verify 404 after delete |

# | 8 | `testGetBookInvalidId` | GET | `/pet/abc123` | Negative — invalid ID returns 404 |

# 

# \---

# 

# \## 🔑 Key Concepts Covered

# 

# \- ✅ \*\*Reusable Base Class\*\* — Shared `@BeforeClass` / `@AfterClass` setup via `BaseTest.java`

# \- ✅ \*\*Request Chaining\*\* — POST response ID captured and reused in GET, PUT, DELETE

# \- ✅ \*\*File Logging\*\* — All requests and responses auto-logged to `restassured.log`

# \- ✅ \*\*TestNG XML Suite Runner\*\* — All tests orchestrated via `testng.xml`

# \- ✅ \*\*Negative Test Cases\*\* — 404 validation for deleted and invalid book IDs

# \- ✅ \*\*Java Text Blocks\*\* — Clean JSON payloads using `"""` syntax (Java 15+)

# \- ✅ \*\*RequestSpecBuilder\*\* — Centralized base URI, headers, and filters

# 

# \---

# 

# \## 🛠️ Tech Stack

# 

# | Tool | Version | Purpose |

# |---|---|---|

# | Java | 15+ | Programming language |

# | REST Assured | 5.4.0 | API testing library |

# | TestNG | 7.9.0 | Test framework \& runner |

# | Maven | 3.x | Build \& dependency management |

# | Logback | 1.4.11 | Request/Response file logging |

# | Swagger Petstore | v2 | Demo API (mapped to Library) |

# 

# \---

# 

# \## ⚙️ Prerequisites

# 

# \- Java 15 or higher installed

# \- Maven 3.x installed

# \- IntelliJ IDEA (recommended)

# \- Internet connection (tests hit live Petstore API)

# 

# \---

# 

# \## 🚀 How to Run

# 

# \### Option 1 — Run via IntelliJ

# 1\. Right-click `testng.xml` → \*\*Run\*\*

# 2\. Or right-click `LibraryTest.java` → \*\*Run\*\*

# 

# \### Option 2 — Run via Maven Terminal

# ```bash

# mvn clean test

# ```

# 

# \### Option 3 — Run via TestNG XML with Maven

# ```bash

# mvn test -DsuiteXmlFile=src/test/resources/testng.xml

# ```

# 

# \---

# 

# \## 📊 Expected Test Results

# 

# ```

# ========================================

# Library Management API Suite

# ========================================

# Total Tests  : 8

# Passed       : 8

# Failed       : 0

# Skipped      : 0

# ========================================

# ```

# 

# \---

# 

# \## 📄 Log Output

# 

# After each run, full request/response logs are saved to:

# ```

# src/test/resources/logs/restassured.log

# ```

# 

# Sample log entry:

# ```

# Request method:  GET

# Request URI:     https://petstore.swagger.io/v2/pet/findByStatus?status=available

# Headers:         Content-Type=application/json

# 

# Response status: 200 OK

# Response body:   \[ { "id": 998877, "name": "The Alchemist", "status": "available" } ]

# ```

# 

# \---

# 

# \## 🗺️ API Mapping (Library → Petstore)

# 

# | Library Concept | Petstore Mapping |

# |---|---|

# | Book | Pet |

# | Add Book | POST `/pet` |

# | Available Books | GET `/pet/findByStatus?status=available` |

# | Borrow Book | PUT `/pet` with `status: pending` |

# | Return Book | POST `/pet/{id}` with `status: available` |

# | Remove Book | DELETE `/pet/{id}` |

# 

# \---

# 

# \## 👨‍💻 Author

# 

# \*\*Saumarghya Ray\*\*

# \- GitHub: \[@SaumRay](https://github.com/SaumRay)

# 

# \---

# 

# \## 📜 License

# 

# This project is for learning and practice purposes only.

