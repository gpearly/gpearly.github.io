# drop existing tables
DROP TABLE Defects;
DROP TABLE Employees;
DROP TABLE Products;

# create tables
CREATE TABLE Employees (
  EmployeeId int(11) NOT NULL,
  LastName varchar(32) NOT NULL,
  FirstName varchar(32) NOT NULL,
  Email varchar(255) DEFAULT NULL,
  PRIMARY KEY (EmployeeId)
);

CREATE TABLE Products (
  Name varchar(32) DEFAULT NULL,
  Status enum('OPEN', 'CLOSED') NOT NULL DEFAULT 'OPEN',
  UNIQUE (Name)
);

CREATE TABLE Defects (
DefectId int(11) NOT NULL AUTO_INCREMENT,
Product varchar(132) NOT NULL,
Submitter varchar(32) NOT NULL,
Submit_Date date NOT NULL,
Title varchar(132) NOT NULL,
Description varchar(4000) NOT NULL,
Due_Date date DEFAULT NULL,
Priority enum('LOW','MEDIUM','HIGH') NOT NULL DEFAULT 'LOW',
State enum('ASSIGNED','ANALYZED','APPROVED','CLOSED') NOT NULL DEFAULT 'ASSIGNED',
Assignee varchar(32),
Solution varchar(4000),
PRIMARY KEY (DefectId)
);


/* Insert default data into bug tracking schema */

/* Employees table first */
INSERT INTO `Employees`(`EmployeeId`, `LastName`, `FirstName`, `Email`)
VALUES (543897,'Lee','Brock','blee@email.com');
INSERT INTO `Employees`(`EmployeeId`, `LastName`, `FirstName`, `Email`)
VALUES (542789,'Age','Sue','sage@email.com');
INSERT INTO `Employees`(`EmployeeId`, `LastName`, `FirstName`, `Email`)
VALUES (798734,'Tai','Parh','ptai@email.com');
INSERT INTO `Employees`(`EmployeeId`, `LastName`, `FirstName`, `Email`)
VALUES (945897,'Waacko','Wendy','wwaacko@email.com');

/* Products table second */
INSERT INTO `Products`(`Name`, `Status`)
VALUES ('Product_1','OPEN');
INSERT INTO `Products`(`Name`, `Status`)
VALUES ('Product_2','CLOSED');
INSERT INTO `Products`(`Name`, `Status`)
VALUES ('Product_3','CLOSED');
INSERT INTO `Products`(`Name`, `Status`)
VALUES ('Product_4','OPEN');
INSERT INTO `Products`(`Name`, `Status`)
VALUES ('Product_5','OPEN');
INSERT INTO `Products`(`Name`, `Status`)
VALUES ('Product_6','OPEN');
INSERT INTO `Products`(`Name`, `Status`)
VALUES ('Product_7','CLOSED');

/* Defects table last - since it references the other two */
/* 1 - OPEN */
INSERT INTO `Defects`
(`DefectId`, `Product`, `Submitter`, `Submit_Date`, `Title`,
`Description`, `Due_Date`, `Priority`, `State`, `Assignee`, `Solution`)
VALUES ('1','Product_1','Waacko','2016-01-05','Bug for Product_1',
'Missing its rainbow.','2016-06-11','MEDIUM','ASSIGNED',
'Tai','Found skittles.');
/* 2 - CLOSED */
INSERT INTO `Defects`
(`DefectId`, `Product`, `Submitter`, `Submit_Date`, `Title`,
`Description`, `Due_Date`, `Priority`, `State`, `Assignee`, `Solution`)
VALUES ('2','Product_2','Lee','2016-02-06','Bug for Product_2',
'Seven ate nine.','2016-06-11','LOW','CLOSED',
'Lee','Found some food for Seven.');
/* 3 - CLOSED */
INSERT INTO `Defects`
(`DefectId`, `Product`, `Submitter`, `Submit_Date`, `Title`,
`Description`, `Due_Date`, `Priority`, `State`, `Assignee`, `Solution`)
VALUES ('3','Product_3','Age','2016-03-05','Bug for Product_3',
'It is raining cats and dogs.','2016-03-10','LOW','CLOSED',
'Waacko','Contacted the local animal shelter to find homes for our new fury friends.');
/* 4 - OPEN */
INSERT INTO `Defects`
(`DefectId`, `Product`, `Submitter`, `Submit_Date`, `Title`,
`Description`, `Due_Date`, `Priority`, `State`, `Assignee`, `Solution`)
VALUES ('4','Product_4','Tai','2016-01-05','Bug for Product_4',
'Donald Duck is missing his pants.','2016-06-11','HIGH','ASSIGNED',
'Tai', NULL);
/* 5 - OPEN */
INSERT INTO `Defects`
(`DefectId`, `Product`, `Submitter`, `Submit_Date`, `Title`,
`Description`, `Due_Date`, `Priority`, `State`, `Assignee`, `Solution`)
VALUES ('5','Product_5','Lee','2016-02-15','Bug for Product_5',
'A bugga boo.','2016-04-12','MEDIUM','ANALYZED',
'Tai', NULL);
/* 6 - OPEN */
INSERT INTO `Defects`
(`DefectId`, `Product`, `Submitter`, `Submit_Date`, `Title`,
`Description`, `Due_Date`, `Priority`, `State`, `Assignee`, `Solution`)
VALUES ('6','Product_6','Age','2016-01-05','Bug for Product_6',
'Holes in logic.','2016-06-11','HIGH','ANALYZED',
'Age', NULL);
/* 7 - CLOSED */
INSERT INTO `Defects`
(`DefectId`, `Product`, `Submitter`, `Submit_Date`, `Title`,
`Description`, `Due_Date`, `Priority`, `State`, `Assignee`, `Solution`)
VALUES ('7','Product_7','Waacko','2016-01-17','Bug for Product_7',
'e^(i*pi) + 1','2016-01-19','HIGH','CLOSED',
'Tai','Answer is 0');

