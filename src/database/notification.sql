--CREATE TABLE USERS (
--    ID INT PRIMARY KEY AUTO_INCREMENT,
--    USERNAME VARCHAR(255) NOT NULL,
--    FIRST_NAME VARCHAR(255) NOT NULL,
--    LAST_NAME VARCHAR(255) NOT NULL,
--    PASSWORD VARCHAR(255)) NOT NULL,
--    EMAIL VARCHAR(255) NOT NULL UNIQUE,
--    ROLE VARCHAR(255) NOT NULL
--);
--INSERT INTO USERS (ID, USERNAME, FIRST_NAME, LAST_NAME, EMAIL, ROLE) VALUES (6,'FirstSubscriber','Hungry'
--                    , 'Joe,', 'P@ssw0rd', 'ImhungryJoe@whensdinner.com', 'SUBSCRIBER');

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