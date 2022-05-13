# Project 1 - Employee Reimbursment System (ERS)

## Executive Summary
The Expense Reimbursement System (ERS) will manage the process of reimbursing employees for expenses incurred while on company time. All employees in the company can login and submit requests for reimbursement and view their past tickets and pending requests. Finance managers can log in and view all reimbursement requests and past history for all employees in the company. Finance managers are authorized to approve and deny requests for expense reimbursement. The reimbursement types should have the following options: LODGING, FOOD, TRAVEL


# Tech Stack
 - Java 8
 - Apache Maven
 - PostgreSQL
 - AWS RDS
 - Java Servlets
 - JDBC

# Program Functions
 - Domain objects persisted in 3NF relational database
 - CRUD functionality for all domain objects accessible via RESTful API
 - Workflows to complete all user stories
  - As a guest, I can register for a new account
  - As a guest, I can log into my account
  - As a user, I can submit a request for reimbursement
  - As a user, I can cancel a pending request for reimbursement
  - As a user, I can view my pending and completed past requests for reimbursement
  - As a user, I can edit my pending requests for reimbursement
  - As a user, I can approve expense reimbursements
  - As a user, I can deny expense reimbursements
  - As a user, I can filter requests by status
 - Validate all user input
