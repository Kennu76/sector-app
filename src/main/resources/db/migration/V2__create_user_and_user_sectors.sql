create table USER (
    user_id int not null,
    NAME varchar(100) not null,
    agreeToTerms boolean,
    PRIMARY KEY (user_id)
);

create table USER_SECTOR (
    user_sector_id int not null,
    user_id int,
    sector_id int,
    sector_level int,
    expandable boolean,
    PRIMARY KEY (user_sector_id),
    CONSTRAINT FK_User FOREIGN KEY (user_id)
    REFERENCES USER(user_id)
);