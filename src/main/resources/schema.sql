Create Table if Not Exists beer
(
    id                 integer not null primary key auto_increment,
    beer_name          varchar(255),
    beer_style         varchar(255),
    upc                varchar(25),
    quantity_on_hand   integer,
    price              decimal(10, 2),
    created_date       timestamp,
    last_modified_date timestamp
);

Create Table if Not Exists customer
(
    id                 integer not null primary key auto_increment,
    customer_name      varchar(255),
    created_date       timestamp,
    last_modified_date timestamp
);