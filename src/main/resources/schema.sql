-- drop table if exists authors;
-- drop table if exists books;
-- drop table if exists genres;

CREATE TABLE genres
(
    id         bigserial,
    genre_name varchar(36),
    PRIMARY KEY (id)
);

CREATE TABLE authors
(
    id          bigserial,
    author_name varchar(36),
    PRIMARY KEY (id)
);

CREATE TABLE books
(
    id        bigserial,
    book_name varchar(36),
    author_id bigint NOT NULL,
    genre_id  bigint NOT NULL,
    PRIMARY KEY (id),
    foreign key (genre_id) references genres (id),
    foreign key (author_id) references authors (id)
);
