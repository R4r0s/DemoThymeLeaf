create table characters(
    id bigint not null;
    name varchar(255) not null,
    uuid varchar(255) not null,
    class varchar(255) not null,
    alignment varchar(255) not null
);

create sequence CHARACTERS_SEQ start with 1 increment by 1;