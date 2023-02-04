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

SELECT TOP (20) [userID]
      ,[username]
      ,[firstName]
      ,[lastName]
      ,[userEmail]
      ,[userPassword]
      ,[salt]
      ,[userRole]
  FROM [234a_Null].[dbo].[USERS]