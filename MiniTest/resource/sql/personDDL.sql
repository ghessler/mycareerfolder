
    drop table if exists Person;

    create table Person (
        id integer not null auto_increment,
        age integer not null,
        name varchar(255),
        primary key (id)
    );
