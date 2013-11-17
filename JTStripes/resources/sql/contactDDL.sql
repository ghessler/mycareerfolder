
    alter table Contact_Document 
        drop 
        foreign key FK_7spj2i9tk33hy93u46newqd7g;

    alter table Contact_Document 
        drop 
        foreign key FK_19whr7bn3nlp5wym26juft9av;

    drop table if exists Contact;

    drop table if exists Contact_Document;

    drop table if exists Document;

    create table Contact (
        id integer not null auto_increment,
        age integer not null,
        birthDate datetime,
        email varchar(255),
        firstName varchar(255),
        lastName varchar(255),
        phoneNumber varchar(255),
        primary key (id)
    );

    create table Contact_Document (
        contacts_id integer not null,
        documents_id integer not null,
        primary key (contacts_id, documents_id)
    );

    create table Document (
        id integer not null auto_increment,
        context varchar(255),
        fileName varchar(255),
        fileType varchar(255),
        titolo varchar(255),
        primary key (id)
    );

    alter table Contact_Document 
        add index FK_7spj2i9tk33hy93u46newqd7g (documents_id), 
        add constraint FK_7spj2i9tk33hy93u46newqd7g 
        foreign key (documents_id) 
        references Document (id);

    alter table Contact_Document 
        add index FK_19whr7bn3nlp5wym26juft9av (contacts_id), 
        add constraint FK_19whr7bn3nlp5wym26juft9av 
        foreign key (contacts_id) 
        references Contact (id);
