
    drop table if exists Contact;

    create table Contact (
        id integer not null auto_increment,
        birthDate datetime,
        email varchar(255),
        firstName varchar(255),
        lastName varchar(255),
        phoneNumber varchar(255),
        primary key (id)
    );
