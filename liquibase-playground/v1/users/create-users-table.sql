CREATE TABLE USERS (
	GUID VARCHAR PRIMARY KEY NOT NULL,
	LOGIN VARCHAR NOT NULL,
	PASSWORD VARCHAR NOT NULL,
	FIRST_NAME VARCHAR NOT NULL,
	LAST_NAME VARCHAR NOT NULL,
	SECOND_NAME VARCHAR,
	ROLE VARCHAR NOT NULL,
	LOGIN_ATTEMPTS INTEGER(4),
	EMAIL VARCHAR,
	CREATED_TS TIMESTAMP,
	CREATED_UID VARCHAR
);

CREATE INDEX IDX_USERS_GUID ON USERS(GUID);

CREATE INDEX IDX_USERS_LOGIN ON USERS(LOGIN);
