
    drop table if exists Contact;

    create table Contact (
        contactId integer not null auto_increment,
        address1 varchar(255),
        address2 varchar(255),
        businessEmailAddress varchar(255),
        businessPhone varchar(255),
        businessPhone2 varchar(255),
        cellPhone varchar(255),
        city varchar(255),
        contactType integer,
        dateCreated datetime,
        faxPhone varchar(255),
        name varchar(255),
        organization varchar(255),
        otherEmailAddress varchar(255),
        state varchar(255),
        userId integer not null,
        zipCode varchar(255),
        primary key (contactId)
    );
