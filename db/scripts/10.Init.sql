SET SESSION SQL_MODE='ANSI';

CREATE SEQUENCE sequenceId START 5000;

CREATE TABLE users (
	"id" INTEGER NOT NULL,
	"login" VARCHAR(255) NOT NULL,
	"timezone" VARCHAR(100),
	"locale" VARCHAR(50),
	"password" VARCHAR(1024),
	
	"firstname" VARCHAR(255),
	"lastname" VARCHAR(255),
	"email" VARCHAR(255),
	
	"creator" INTEGER,
    "created" TIMESTAMP,
    "editor" INTEGER,
    "edited" TIMESTAMP,
    "isdeleted" BOOLEAN DEFAULT FALSE,	
    "version" INTEGER DEFAULT 0,

	CONSTRAINT pk_user PRIMARY KEY("id"),
	CONSTRAINT unq_user_login UNIQUE ("login"),
	CONSTRAINT fk_user_creator FOREIGN KEY ("creator") REFERENCES users("ID"),
    CONSTRAINT fk_user_editor FOREIGN KEY ("editor") REFERENCES users("ID")
);

CREATE TABLE roles (
	"id" INTEGER NOT NULL,
	"name" VARCHAR(255),
	
	"creator" INTEGER,
    "created" TIMESTAMP,
    "editor" INTEGER,
    "edited" TIMESTAMP,
    "isdeleted" BOOLEAN DEFAULT FALSE,	
    "version" INTEGER DEFAULT 0,

	CONSTRAINT pk_role PRIMARY KEY("id"),
	CONSTRAINT unq_role_name UNIQUE ("name"),	
    CONSTRAINT fk_role_creator FOREIGN KEY ("creator") REFERENCES users("id"),
    CONSTRAINT fk_role_editor FOREIGN KEY ("editor") REFERENCES users("id")
);

CREATE TABLE role_permissions (
	"roleid" INTEGER NOT NULL,
	"permission" VARCHAR(255),

	CONSTRAINT pk_role_permission PRIMARY KEY("roleid", "permission"),
	CONSTRAINT fk_role_permission_roleid FOREIGN KEY ("roleid") REFERENCES roles("id")
);

CREATE TABLE user_roles (
	"userid" INTEGER NOT NULL,
	"roleid" INTEGER NOT NULL,

	CONSTRAINT pk_user_role PRIMARY KEY("userid", "roleid"),
	CONSTRAINT fk_user_role_userid FOREIGN KEY ("userid") REFERENCES users("id"),
	CONSTRAINT fk_user_role_roleid FOREIGN KEY ("roleid") REFERENCES roles("id")
);

CREATE VIEW user_access AS SELECT u.login, u.password, r.name AS rolename, p.permission FROM user_roles rel INNER JOIN users u ON u.id = rel.userid AND u.isdeleted = false INNER JOIN roles r ON r.id = rel.roleid AND r.isdeleted = false INNER JOIN role_permissions p ON r.id = p.roleid;

-- password is abcd1234
INSERT INTO users(id, login, timezone, locale, password, firstname, lastname, email, creator, created, editor, edited, isdeleted, version) values (1, 'admin', 'UTC', 'en-US', 'e9cee71ab932fde863338d08be4de9dfe39ea049bdafb342ce659ec5450b69ae', 'Root', 'Admin', 'admin@localhost', 1, NOW(), 1, NOW(), 0, 1);
INSERT INTO roles(id, name, creator, created, editor, edited, isdeleted, version) values (1, 'Administrators', 1, NOW(), 1, NOW(), 0, 1);
INSERT INTO roles(id, name, creator, created, editor, edited, isdeleted, version) values (2, 'PowerUsers', 1, NOW(), 1, NOW(), 0, 1);
INSERT INTO roles(id, name, creator, created, editor, edited, isdeleted, version) values (3, 'Users', 1, NOW(), 1, NOW(), 0, 1);
INSERT INTO roles(id, name, creator, created, editor, edited, isdeleted, version) values (4, 'Guests', 1, NOW(), 1, NOW(), 0, 1);

insert into role_permissions(roleid, permission) values (1, 'LOGIN');
insert into role_permissions(roleid, permission) values (1, 'LIST_USERS');
insert into role_permissions(roleid, permission) values (1, 'ADD_USER');
insert into role_permissions(roleid, permission) values (1, 'EDIT_USER');
insert into role_permissions(roleid, permission) values (1, 'DELETE_USER');

insert into role_permissions(roleid, permission) values (2, 'LOGIN');
insert into role_permissions(roleid, permission) values (2, 'LIST_USERS');

insert into role_permissions(roleid, permission) values (3, 'LOGIN');

insert into role_permissions(roleid, permission) values (4, 'LOGIN');

insert into user_roles(userid, roleid) values (1, 1);

