# drop existing tables
DROP TABLE Defects;
DROP TABLE Employees;
DROP TABLE Products;

# create tables
CREATE TABLE Defects (
  DefectId int(11) NOT NULL AUTO_INCREMENT,
  Product varchar(132) NOT NULL,
  Submitter varchar(32) NOT NULL,
  Submit_Date date NOT NULL,
  Title varchar(132) NOT NULL,
  Description varchar(4000) NOT NULL,
  Due_Date date DEFAULT NULL,
  Priority enum('LOW', 'MEDIUM', 'HIGH') NOT NULL DEFAULT 'LOW',
  State enum('ASSIGNED', 'ANALYZED', 'APPROVED', 'CLOSED' NOT NULL DEFAULT 'ASSIGNED'),
  Assignee varchar(32) DEFAULT NULL,
  Solution varchar(4000) DEFAULT NULL,
  PRIMARY KEY (DefectId)
)

CREATE TABLE Employees (
  EmployeeId int(11) NOT NULL,
  LastName varchar(32) NOT NULL,
  FirstName varchar(32) NOT NULL,
  Email varchar(255) DEFAULT NULL,
  PRIMARY KEY (EmployeeId)
)

CREATE TABLE Products (
  Name varchar(32) DEFAULT NULL,
  Status enum('OPEN', 'CLOSED') NOT NULL DEFAULT 'OPEN',
  UNIQUE (Name)
)
