DROP SEQUENCE IF EXISTS sq_user;
CREATE SEQUENCE sq_user;

DROP TABLE IF EXISTS T_USER;
CREATE TABLE T_USER (
pkid_user BIGINT  NOT NULL DEFAULT nextval('sq_user') PRIMARY KEY,
nom varchar not null,
prenom varchar not null,
login varchar not null,
password varchar not null,
email_contact varchar null
);
