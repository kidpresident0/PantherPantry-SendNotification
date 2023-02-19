USE "234a_Null"

CREATE TABLE TEMPLATES (
    ID INTEGER NOT NULL IDENTITY PRIMARY KEY,
    UserID smallint FOREIGN KEY REFERENCES USERS(userID),
    TemplateName NVARCHAR(256),
    Subject NVARCHAR(256),
    TemplateText NVARCHAR(MAX),
    );

GO

INSERT INTO TEMPLATES
("UserID", "TemplateName", "Subject", "TemplateText")
VALUES (1, N'Healthy Habits', N'Healthy Habits', N'Our mission to make you healthy')

INSERT INTO TEMPLATES
("UserID", "TemplateName", "Subject", "TemplateText")
VALUES (2, N'Winter Treats', N'Winter Treats', N'Winter is the time for holidays and big feasts');

INSERT INTO TEMPLATES
("UserID", "TemplateName", "Subject", "TemplateText")
VALUES (3, N'Eliminating bad eating habits', N'Giving up bad eating habits', N'How do you prevent yourself from low quality of food?');

INSERT INTO TEMPLATES
("UserID", "TemplateName", "Subject", "TemplateText")
VALUES (4, N'Poor nutrition', N'Poor nutrition',
N'While skipping a burger and having a salad may not seem that life-changing at first, it could protect your cognitive function for years to come.');

INSERT INTO TEMPLATES
("UserID", "TemplateName", "Subject", "TemplateText")
VALUES (5, N'Healthy Sugar', N'Healthy Sugar', N'Which Sugars Are Good for You');

INSERT INTO TEMPLATES
("UserID", "TemplateName", "Subject", "TemplateText")
VALUES (3, N'Building Vegan Habits', N'Building Vegan Habits', N'How to Maintain a Balanced Diet as a Vegetarian or Vegan');

SELECT * FROM TEMPLATES;