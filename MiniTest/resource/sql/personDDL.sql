
    drop table if exists Document;
<<<<<<< HEAD

    drop table if exists User;
=======

    drop table if exists Person;

    drop table if exists Task;
>>>>>>> 21975f1... all in

    create table Document (
        id integer not null auto_increment,
        description varchar(255),
        fileName varchar(255),
        fileType varchar(255),
        title varchar(255),
        primary key (id)
    );

<<<<<<< HEAD
    create table User (
=======
    create table Person (
>>>>>>> 21975f1... all in
        id integer not null auto_increment,
        age integer not null,
        name varchar(255),
        primary key (id)
    );

    create table Task (
        id integer not null auto_increment,
        contact varchar(255),
        description varchar(255),
        endDate varchar(255),
        startDate varchar(255),
        title varchar(255),
        primary key (id)
    );
