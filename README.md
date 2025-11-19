# EventosUy — Multi-Platform Event Management System

A full event-management platform built as a distributed system with desktop, web, and mobile interfaces. It supports user management, events, editions, registrations, sponsorships, authentication, media handling, search, and more.

**Repository:**  
https://github.com/andresgonzalezarbildi/EventosUy

---

## How It's Made

**Tech used:**  
Java SE, Swing, JSP/Servlets, HTML5, CSS, JavaScript, Bootstrap, SOAP Web Services (JAX-WS), Maven.

The system is composed of multiple independent modules that communicate through SOAP Web Services:

- **Central Server (Java)**  
  Business logic, automated testing, persistence, and remote service publishing.

- **Desktop Application (Swing)**  
  Administrative interface for managing users, events, and editions.

- **Web Server (JSP/Servlets)**  
  Dynamic web front-end with login, event browsing, registrations, media display, and PDF generation.

- **Mobile Web App (Responsive Design)**  
  A mobile-friendly UI built with Bootstrap, consuming the same remote services.

- **Maven Build System**  
  Automated generation of `.jar` and `.war` artifacts and full dependency management.

---

## Optimizations

- Refactored business logic to reduce coupling and comply with PMD + CheckStyle rules.  
- Clear separation between client-side, server-side, and service layers.  
- External `.properties` configuration files to allow environment changes without recompiling.

---

## Lessons Learned

- Integration of multiple Java technologies in a consistent architecture.  
- Publishing and consuming SOAP Web Services and how they shape distributed design.  
- Building interfaces ranging from Swing GUIs to responsive web layouts.  
- Automating the full build pipeline with Maven and packaging for deployment.  
- Modular organization enabling independent evolution of each component.
