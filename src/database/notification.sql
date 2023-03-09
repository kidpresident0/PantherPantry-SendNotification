--SELECT userID , username , firstName , lastName ,  userPassword, userEmail , userPhone , userRole FROM 234a_Null.dbo.USERS";
DROP TABLE USERS;
CREATE TABLE USERS (
   userID INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
   firstName VARCHAR(255) NOT NULL,
   lastName VARCHAR(255) NOT NULL,
   userPassword VARCHAR(255)) NOT NULL,
   userEmail VARCHAR(255) NOT NULL UNIQUE,
   userPhone VARCHAR(10) NOT NULL UNIQUE
    userRole VARCHAR(255) NOT NULL
);
INSERT INTO USERS (username, firstName, lastName, userPassword, userEmail, userRole, phoneNumber, receiveNotifications, notificationType) VALUES ('coolgal99','Taylor'
                    , 'Swift', 'P@ssw0rd4567', 'grammyWinner@whatever.com', 'subscriber','9718305656','Yes','Both');

INSERT INTO USERS (username, firstName, lastName, userPassword, userEmail, userRole, phoneNumber, receiveNotifications, notificationType) VALUES ('BestTank','D.VA'
                    , 'Mech', 'P@ssw0rd345', 'NerfThis@whatever.com', 'subscriber','9716909876','Yes','SMS');

ALTER TABLE [234a_Null].[dbo].[USERS]
ADD userPhone  VARCHAR(15);

SET IDENTITY_INSERT [234a_Null].[dbo].[USERS]  OFF;

INSERT INTO [234a_Null].[dbo].[USERS] (username, firstName, lastName, userPassword, userEmail, userRole, phoneNumber) VALUES ('foodEater88','Jack'
                    , 'Hungrymen', 'P@ssw0rd', 'hungrydude@whensdinner.com', 'subscriber', '19716667777');

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