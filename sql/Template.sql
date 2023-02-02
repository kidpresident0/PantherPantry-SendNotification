USE "234a_Null"

CREATE TABLE Templates (
    ID INTEGER NOT NULL IDENTITY PRIMARY KEY,
    Subject NVARCHAR(256),
    TemplateText NVARCHAR(MAX),
    Campus NVARCHAR(256),
    FoodItems NVARCHAR(256),
    StartDate DATE,
    EndDate DATE,
    Term NCHAR(10),
    StaffName NVARCHAR(256)
    );

GO

INSERT INTO Templates
("Subject", "TemplateText", "Campus", "FoodItems", "StartDate", "EndDate", "Term", "StaffName")
VALUES (N'Healthy Habits', N'Our mission to make you healthy', N'Sylvania Campus', N'Vegetables and Fruits',
N'2022-11-13', N'2022-11-17', N'Fall', N'Karla Wise');

INSERT INTO Templates
("Subject", "TemplateText", "Campus", "FoodItems", "StartDate", "EndDate", "Term", "StaffName")
VALUES (N'Giving up bad eating habits', N'How do you prevent yourself from low quality of food?',
N'Southeast Campus', N'Homemade whole grain bread', '2022-04-27', N'2022-04-29', N'Spring', N'Ernest Carlson');

INSERT INTO Templates
("Subject", "TemplateText", "Campus", "FoodItems", "StartDate", "EndDate", "Term", "StaffName")
VALUES (N'Poor nutrition',
N'While skipping a burger and having a salad may not seem that life-changing at first, it could protect your cognitive function for years to come. ',
N'Sylvania Campus', N'Fish, eggs and beans', N'2022-06-06', N'2022-06-13', N'Summer', N'Anna Lowe');

INSERT INTO Templates
("Subject", "TemplateText", "Campus", "FoodItems", "StartDate", "EndDate", "Term", "StaffName")
VALUES (N'Healthy Sugar', N'Which Sugars Are Good for You', N'Rock Creek Campus', N'Sugar free pastry',
N'2022-02-15', N'2022-02-18', N'Winter', N'Jeff Tucker');

INSERT INTO Templates
("Subject", "TemplateText", "Campus", "FoodItems", "StartDate", "EndDate", "Term", "StaffName")
VALUES (N'Building Vegan Habits', N'OHow to Maintain a Balanced Diet as a Vegetarian or Vegan',
N'Rock Creek Campus', N'Vegan snacks and different nuts and seeds',
N'2022-08-01', N'2022-08-05', N'Summer', N'Luis Maxwell');

SELECT * FROM Templates;