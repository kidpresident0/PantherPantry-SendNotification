-- ----------------------------------------------------------
-- Reset script for Sprint 1
-- Sevin Webb. CIS 234A. 1/31/2023
-- ----------------------------------------------------------

USE 234a_Null;


-- ----------------------------------------------------------
-- Deletes all tables from the database
-- ----------------------------------------------------------
IF OBJECT_ID('USERS','U') IS NOT NULL
	DROP TABLE USERS;
IF OBJECT_ID('NOTIFICATIONS','U') IS NOT NULL
	DROP TABLE NOTIFICATIONS;
IF OBJECT_ID('TEMPLATES','U') IS NOT NULL
	DROP TABLE TEMPLATES;


-- ----------------------------------------------------------
-- Recreates the tables
-- ----------------------------------------------------------

CREATE TABLE USERS
(
	[userID] [smallint] IDENTITY(1,1) NOT NULL PRIMARY KEY,
	[username] [nvarchar](255), 
	[firstName] [nvarchar](255),
	[lastName] [nvarchar](255),
	[userEmail] [nvarchar](255),
	[userPassword] [nvarchar](255),
	[salt] [nvarchar](255),
	[userRole] [nvarchar](255)
);

CREATE TABLE NOTIFICATIONS
(
	[notificationID] [smallint] IDENTITY(1,1) NOT NULL PRIMARY KEY,
	[userID] [smallint],
	[date] [Date],
	[time] [time],
	[subject] [nvarchar](255),
	[messageBody] [nvarchar](max),
	[subscriberAmount] [smallint]
);

CREATE TABLE TEMPLATES
(
	[templateID] [smallint] IDENTITY(1,1) NOT NULL PRIMARY KEY,
	[userID] [smallint],
	[name] [nvarchar](255),
	[subject] [nvarchar](255),
	[text] [nvarchar](max)
);



-- -------------------------------------------------------
-- Repopulates the Tables
-- -------------------------------------------------------
INSERT INTO USERS (username, firstName,lastName, userEmail, userPassword,salt,userRole) VALUES
	('TeamNull', 'Team', 'Null', 'TeamNull234@gmail.com', 'CIS234A!', 'none', 'subscriber'),
	('user2', 'dude', 'bro', 'dudebro@wow.com', 'Password', '123', 'subscriber'),
	('CerealFan', 'Lain', 'Iwakura', 'wiredworld@where.com', 'Texperimental', '761998', 'subscriber'),
	('BigImpact', 'Gendou', 'Ikari', 'instrumentaldad@nerv.org', 'ThriceUponATime', '3010', 'subscriber'),
	('NeverTakeAnL', 'Light', 'Yagami', 'akira@secret.com', 'StrongNoteTaker', '93312639', 'subscriber');

INSERT INTO NOTIFICATIONS (userID, date, time, subject, messageBody,subscriberAmount) VALUES
	('1', GETDATE(), CURRENT_TIMESTAMP, 'Database Testing', 'This does not actully mean anything. Its just a placeholder
	to have some data that could potentially be used. Have a good day.', '5'),
	('2', GETDATE(), CURRENT_TIMESTAMP, 'test2', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque congue,
	justo quis mollis finibus, felis ex finibus odio, id tincidunt nisl nunc ac ante. Morbi imperdiet sagittis diam, at dictum
	nibh consectetur a. Ut eget libero sed quam dictum suscipit. Donec at scelerisque felis. Nunc eget erat consectetur, elementum
	est scelerisque, cursus velit. Nulla posuere arcu vulputate suscipit malesuada. Nam a dictum sapien, vel finibus justo. Donec non
	erat mauris.', '2'),
	('3', GETDATE(), CURRENT_TIMESTAMP, 'test3', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque congue, justo quis 
	mollis finibus, felis ex finibus odio, id tincidunt nisl nunc ac ante.','15'),
	('4', GETDATE(), CURRENT_TIMESTAMP, 'test4', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque congue, justo quis
	mollis finibus, felis ex finibus odio, id tincidunt nisl nunc ac ante.', '123'),
	('5', GETDATE(), CURRENT_TIMESTAMP, 'test5', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque congue, justo quis
	mollis finibus, felis ex finibus odio, id tincidunt nisl nunc ac ante.', '54');

INSERT INTO TEMPLATES (userID, name, subject,text) VALUES
	('1', 'Test Template', 'Actually just testing', 'This one is actually all just a test. Some would say everything in life is a test. Honestly I prefer quizzes,
	they just seem nicer. But to be fair to tests, some are ok. Like taste tests or test drives. But then again some tests are weird like latest. How can that mean
	the most recent thing when its literally later than the rest. Like cutest means its the most cute thing but latest just doesn''t? Explain that one science.'),
	('2', 'Template 2', 'test2', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque congue, justo quis mollis finibus, felis ex finibus odio, id tincidunt
	nisl nunc ac ante.'),
	('3', 'Template 3', 'test3', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque congue, justo quis mollis finibus, felis ex finibus odio, id tincidunt
	nisl nunc ac ante.'),
	('4', 'Template 4', 'test4', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque congue, justo quis mollis finibus, felis ex finibus odio, id tincidunt 
	nisl nunc ac ante.'),
	('5', 'Template 5', 'test5', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque congue, justo quis mollis finibus, felis ex finibus odio, id tincidunt
	nisl nunc ac ante.');


-- ------------------------------------------------------------
-- Test Query, uncomment to check everything works if you want
-- ------------------------------------------------------------

 -- SELECT * FROM USERS; SELECT * FROM NOTIFICATIONS; SELECT * FROM TEMPLATES;