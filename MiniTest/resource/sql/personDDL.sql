
    drop table if exists Document;

    drop table if exists User;

    create table Document (
        id integer not null auto_increment,
        description varchar(255),
        fileName varchar(255),
        fileType varchar(255),
        title varchar(255),
        primary key (id)
    );

    create table User (
        id integer not null auto_increment,
        age integer not null,
        name varchar(255),
        primary key (id)
    );
