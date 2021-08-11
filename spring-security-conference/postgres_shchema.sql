create table users(
	username VARCHAR(50) NOT NULL,
	password VARCHAR(100) NOT NULL,
	nickname VARCHAR(100) NOT NULL,
	enabled BOOLEAN NOT NULL DEFAULT TRUE,
	PRIMARY KEY(username)
);

create table authorities(
	username VARCHAR(50) NOT NULL,
	authority VARCHAR(50) NOT NULL,
	constraint fk_users
		foreign key(username)
			references users(username)
);

create unique index ix_auth_username
  on authorities(username,authority);

create table persistent_logins(
	username VARCHAR(50) NOT NULL,
	series VARCHAR(64) NOT NULL,
	token VARCHAR(64) NOT NULL,
	last_used TIMESTAMP NOT NULL,
	PRIMARY KEY(series),
	constraint fk_persistent_logins
		foreign key(username)
			references users(username)
);

create table accounts(
	username VARCHAR(50) NOT NULL,
	password VARCHAR(100) NOT NULL,
	email VARCHAR(100) NOT NULL,
	firstname VARCHAR(50) NOT NULL,
	lastname VARCHAR(50) NOT NULL,
	PRIMARY KEY(username)
);

create table verification_tokens(
	username VARCHAR(50) NOT NULL,
	token VARCHAR(100) NOT NULL,
	expiry_date DATE NOT NULL,
	PRIMARY KEY(username, token)
);

create table reset_tokens(
	email VARCHAR(100) NOT NULL,
	username VARCHAR(50) NOT NULL,
	token VARCHAR(100) NOT NULL,
	expiry_date DATE NOT NULL,
	PRIMARY KEY(email, token)
);
  
insert into users(username, password, enabled)
  values('yakir', '$2a$10$GVER/WZdPLIKYzb1QaR72.OYCCrDoFWq3yQOzVCJVEa0LvcjNOAPu', TRUE);
  
insert into authorities(username, authority)
  values('yakir', 'ROLE_USER');
  
  
-- update users set password = '$2a$10$GVER/WZdPLIKYzb1QaR72.OYCCrDoFWq3yQOzVCJVEa0LvcjNOAPu';
update users set nickname = 'pita';
select * from users;
select * from authorities;
select * from persistent_logins;
select * from accounts;
-- delete from users where username = 'yakir2';
-- delete from accounts where username = 'bla';

-- alter table users
-- rename column passwd to password;

commit;