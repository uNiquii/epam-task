CREATE TABLE USERS (
    user_id integer NOT NULL AUTO_INCREMENT,
    first_name varchar(80) NOT NULL,
    second_name varchar(80) NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE CONTACTS (
    city varchar(80),
    email varchar(320),
    phone_number integer,
    age integer,
    date_of_birth date,
    address varchar(320),
    user_id integer,
    FOREIGN KEY (`user_id`) REFERENCES `USERS` (`user_id`) ON DELETE CASCADE
);