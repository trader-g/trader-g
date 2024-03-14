CREATE TABLE tdb.Status (
    StatusId INT IDENTITY(1,1) PRIMARY KEY,
    Status VARCHAR(60) NOT NULL
);
Go

ALTER TABLE tdb.Offer
ADD StatusId INT NOT NULL;

ALTER TABLE tdb.Offer
ADD FOREIGN KEY (StatusId) REFERENCES tdb.Status(StatusId);
Go

INSERT INTO tdb.Status (Status) VALUES
    ('Open'),
    ('Cancelled'),
    ('Accepted');
