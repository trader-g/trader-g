CREATE TABLE tdb.Card (
    CardId INT IDENTITY(1,1) PRIMARY KEY,
    Type VARCHAR(60) NOT NULL,
    Size VARCHAR(60) NOT NULL
);
GO

CREATE TABLE tdb.Inventory (
    InventoryId INT IDENTITY(1,1) PRIMARY KEY,
    CardId INT NOT NULL,
    PlayerId INT NOT NULL,
    Quantity INT NOT NULL,
    FOREIGN KEY (CardId) REFERENCES tdb.Card(CardId),
    FOREIGN KEY (PlayerId) REFERENCES tdb.Player(PlayerId)
);
Go

CREATE TABLE tdb.Offer (
    OfferId INT IDENTITY(1,1) PRIMARY KEY,
    SellerId INT NOT NULL,
    FOREIGN KEY (SellerId) REFERENCES tdb.Player(PlayerId)
);
Go

CREATE TABLE tdb.Give (
    GiveId INT IDENTITY(1,1) PRIMARY KEY,
    OfferId INT NOT NULL,
    CardId INT NOT NULL,
    Quantity INT NOT NULL,
    FOREIGN KEY (OfferId) REFERENCES tdb.Offer(OfferId),
    FOREIGN KEY (CardId) REFERENCES tdb.Card(CardId)
);

CREATE TABLE tdb.Receive (
    ReceiveId INT IDENTITY(1,1) PRIMARY KEY,
    OfferId INT NOT NULL,
    CardId INT NOT NULL,
    Quantity INT NOT NULL,
    FOREIGN KEY (OfferId) REFERENCES tdb.Offer(OfferId),
    FOREIGN KEY (CardId) REFERENCES tdb.Card(CardId)
);
Go

CREATE TABLE tdb.Trade (
    TradeId INT IDENTITY(1,1) PRIMARY KEY,
    BuyerId INT NOT NULL,
    OfferId INT NOT NULL,
    FOREIGN KEY (OfferId) REFERENCES tdb.Offer(OfferId),
    FOREIGN KEY (BuyerId) REFERENCES tdb.Player(PlayerId)
);
Go
