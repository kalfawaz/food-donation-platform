# Smart Food Donation and Redistribution Platform

A simple web management system that connects **food donors**, **charities** and **volunteers** so surplus food is delivered before it expires.

**Team:** Khalid Alfawaz (Team Leader), Youssef Ali Alkhathami
**Course:** CS6180 Advanced Software Engineering

## Technology

| Part | Technology |
|---|---|
| Frontend | Angular 20 (TypeScript) |
| Backend | Spring Boot 3.3 (Java 17) |
| Database | MySQL 8 with Spring Data JPA |
| Security | Spring Security, JWT, BCrypt |

## Project structure

```
food-donation-platform/
├── frontend/                       Angular application
│   └── src/app/
│       ├── pages/                  login, register, new-donation, my-donations, available-donations
│       ├── services/               auth.service.ts, donation.service.ts
│       ├── guards/                 role.guard.ts (blocks pages by role)
│       └── interceptors/           token.interceptor.ts (adds the login token)
└── backend/                        Spring Boot application
    └── src/main/java/com/fooddonation/
        ├── controller/             REST API (AuthController, DonationController)
        ├── service/                Business rules (AuthService, DonationService)
        ├── repository/             Database access (Spring Data JPA)
        ├── model/                  Entities: User, Donation, DonationRequest, Assignment, StatusHistory
        ├── dto/                    Data sent and received by the API
        ├── security/               JWT and Spring Security setup
        ├── exception/              Error messages returned as JSON
        └── config/                 Creates the default admin account
```

## What you need to install

- Git
- JDK 17 or newer
- Node.js (LTS) and Angular CLI: `npm install -g @angular/cli`
- MySQL Server 8 (and MySQL Workbench)
- IntelliJ IDEA (backend) and VS Code (frontend)
- Postman (to test the API)

## How to run

### 1. Database
Start MySQL. The database `food_donation` and all tables are created automatically on the first run.
The default MySQL login is `root` / `root`. If yours is different, in IntelliJ go to
**Run > Edit Configurations > Environment variables** and add `DB_PASSWORD=yourpassword`.

### 2. Backend
1. In IntelliJ: **File > Open** and choose the `backend` folder. Wait for Maven to download the libraries.
2. Run `FoodDonationApplication`.
3. The API runs at `http://localhost:8080`.

### 3. Frontend
```
cd frontend
npm install
ng serve
```
Open `http://localhost:4200`.

### Default admin account
Created automatically on the first run: **admin@fooddonation.com** / **admin123**

## API (Sprint 1)

| Method | Endpoint | Who | Purpose |
|---|---|---|---|
| POST | /api/auth/register | Anyone | Create an account (Donor, Charity or Volunteer) |
| POST | /api/auth/login | Anyone | Log in and receive a token |
| POST | /api/donations | Donor | Create a donation |
| GET | /api/donations/mine | Donor | My donations |
| GET | /api/donations | Charity, Admin | Available donations (not expired) |

## How we work with Git

1. **Always pull before you start:** `git pull`
2. Commit small pieces with the backlog ID in the message:
   `git commit -m "PB-05: create donation form"`
3. Push when it works: `git push`
4. To avoid conflicts, do not edit the same file at the same time. Tell each other which part you are working on.
