# 🩸 BloodSync REST API - Spring Boot Project

This is the backend REST API for the **BloodSync App**, built with Spring Boot. It manages user registrations, donor lookups, and contact requests via email.

---

## 🚀 Features

- 📝 Register new users (donors)
- 🔐 User login with email & password
- 🔍 Search all available donors
- 📩 Send emails to donors in need
- 🔄 Update donor details
- ❌ Delete donor profile

---

## 🔗 API Endpoints

| Method | Endpoint                         | Description                      |
|--------|----------------------------------|----------------------------------|
| POST   | `/register`                      | Register a new user              |
| GET    | `/allusers`                      | Get all users                    |
| GET    | `/login/{email}/{password}`      | Login with credentials           |
| GET    | `/user/{email}`                  | Get user details by email        |
| PUT    | `/update`                        | Update user details              |
| DELETE | `/delete/{email}`                | Delete user                      |
| GET    | `/mailToDonor/{email}`           | Send an email to the donor       |

---

## ⚙️ Tech Stack

- Java 17+
- Spring Boot 3+
- Spring Web
- Spring Data JPA
- MySQL / Railway / FreeDB (for DB)
- JavaMailSender (for email notifications)

---

## 🧪 How to Run Locally

1. **Clone the repository**
   ```bash
   git clone https://github.com/pavanganeshdivi/bloodsync-rest-api.git
