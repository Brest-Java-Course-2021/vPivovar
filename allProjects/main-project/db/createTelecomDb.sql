
USE telecom;


DROP TABLE telecom.region;
-- TABLE telecom.region:
CREATE TABLE IF NOT EXISTS telecom.region(
  id int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  short_region_code tinyint(2) UNSIGNED,
  name varchar(255) NOT NULL,
  PRIMARY KEY (id) 
)
ENGINE = INNODB;


DROP TABLE telecom.user;
-- TABLE telecom.user:
CREATE TABLE IF NOT EXISTS telecom.user (
  id int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  role_id tinyint(1) NOT NULL,
  permission_id int(11) NOT NULL,
  area_reach varchar(255) NOT NULL,
  login varchar(255) NOT NULL,
  first_name varchar(255) NOT NULL,
  last_name varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  encrypted_password varchar(255) NOT NULL,
  incoming_message varchar(255) DEFAULT NULL,
  last_logon datetime DEFAULT NULL,
  active tinyint(1) NOT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB,
CHECKSUM = 0;

DROP TABLE telecom.role;
-- TABLE telecom.role:
CREATE TABLE IF NOT EXISTS telecom.role (
  id int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  name_role varchar(255) NOT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB,
CHECKSUM = 0;

DROP TABLE telecom.user_role;
-- TABLE for mapping user and roles: telecom.role:
CREATE TABLE IF NOT EXISTS telecom.user_role (
  user_id int(11) UNSIGNED NOT NULL,
  role_id int(11) UNSIGNED NOT NULL,

  UNIQUE (user_id, role_id)

)
ENGINE = INNODB,
CHECKSUM = 0;


-- TABLE telecom.client
CREATE TABLE client (
  id int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id)
)
ENGINE = INNODB,
CHECKSUM = 0;


-- TABLE telecom.using_service
CREATE TABLE using_service (
  id int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id)
)
ENGINE = INNODB,
CHECKSUM = 0;


-- TABLE telecom.billing
CREATE TABLE billing (
  id int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id)
)
ENGINE = INNODB,
CHECKSUM = 0;


-- TABLE telecom.billing_history
CREATE TABLE billing_history (
  id int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id)
)
ENGINE = INNODB,
CHECKSUM = 0;

