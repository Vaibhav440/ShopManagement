✨ Overview

The Online Shopping Management System is a console-based project implemented in Java. It simulates an online shopping experience with features like product management, customer registration, order processing, and payment handling. This project uses MySQL as the database for data persistence and Log4j for logging activities, ensuring a robust and well-maintained system.

🔧 Technologies Used

Programming Language: Java

Database: MySQL

Logging Framework: Log4j

Build Tool: Maven

🚀 Features

User-friendly console interface.

Product catalog management.

Customer registration and login system.

Order placement and tracking.

Admin functionalities for managing users and products.

Logging of activities for debugging and auditing purposes.

✅ Prerequisites

To run this project, ensure the following are installed:

Java Development Kit (JDK) 8 or higher

MySQL server

Maven

🔄 How to Set Up and Run

1. Clone the Repository

git clone https://github.com/your-username/online-shopping-management.git
cd online-shopping-management

2. Configure the Database

Create a MySQL database and import the required schema:

CREATE DATABASE online_shopping;
USE online_shopping;
+------------------------------+
| Tables_in_onlineshoppingmang |
+------------------------------+
| bill                         |
| cart                         |
| order_items                  |
| orders                       |
| payment                      |
| product                      |
| product_category             |
| stock                        |
| user                         |
+------------------------------+


Update database credentials in the project configuration file (e.g., src/main/resources/database.properties):

db.url=jdbc:mysql://localhost:3306/online_shopping
db.username=your_username
db.password=your_password



📁 Project Structure

OnlineShopping/
├── src/
│   ├── main/
│   │   ├── java/        # Java source code
│   │   └── resources/   # Configuration files
├── target/              # Compiled classes and JAR file
├── pom.xml              # Maven configuration file
└── README.md            # Project documentation

🔗 Contributing

Contributions are welcome! Feel free to fork the repository, make changes, and submit a pull request. Please ensure your changes are well-documented and follow the project’s coding standards.


📧 Contact

Author: Vaibhav Shete

Email: Vaibhavshete440@gmail.com

GitHub: https://github.com/Vaibhav440
