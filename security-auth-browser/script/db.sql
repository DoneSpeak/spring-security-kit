use security_kit_browser;

-- remember me的表
create table persistent_logins (
   username varchar(64) not null,
   series varchar(64) primary key,
   token varchar(64) not null,
   last_used timestamp not null
);