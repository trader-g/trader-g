CREATE TABLE tdb.PlayerContact (
    PlayerContactId INT IDENTITY(1,1) PRIMARY KEY,
    ContactTypeId INT NOT NULL,
    ContactValue VARCHAR(320) NOT NULL,
    PlayerId INT NOT NULL,
    CONSTRAINT FK_PlayerContact_PlayerId FOREIGN KEY (PlayerId) REFERENCES tdb.Players(PlayerId),
    CONSTRAINT FK_PlayerContact_ContactTypeId FOREIGN KEY (ContactTypeId) REFERENCES tdb.ContactType(ContactTypeId)
);