--SELECT userID , username , firstName , lastName ,  userPassword, userEmail , userPhone , userRole FROM 234a_Null.dbo.USERS";

--CREATE TABLE USERS (
--    userID INT PRIMARY KEY AUTO_INCREMENT,
--    username VARCHAR(255) NOT NULL,
--    firstName VARCHAR(255) NOT NULL,
--    lastName VARCHAR(255) NOT NULL,
--    userPassword VARCHAR(255)) NOT NULL,
--    userEmail VARCHAR(255) NOT NULL UNIQUE,
--    userPhone VARCHAR(10) NOT NULL UNIQUE
--    userRole VARCHAR(255) NOT NULL
--);
--INSERT INTO USERS (userID, username, firstName, lastName, userPassword, userEmail, userPhone, userRole) VALUES (6,'FirstSubscriber','Hungry'
--                    , 'Joe,', 'P@ssw0rd', 'ImhungryJoe@whensdinner.com', '9712223456 'SUBSCRIBER');

-- Delete Table --
  DROP TABLE NOTIFICATIONS;
-- Create Table --
  CREATE TABLE NOTIFICATIONS (
  notificationID int NOT NULL IDENTITY(1,1) PRIMARY KEY,
  subject NVARCHAR(255),
  messageBody NVARCHAR(255),
  sentBy NVARCHAR(255),
  sentDateTime DATETIME,
  subscriberCount int);