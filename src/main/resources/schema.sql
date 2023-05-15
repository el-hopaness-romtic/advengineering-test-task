CREATE TABLE role (
  role_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  authority VARCHAR(20) NOT NULL
);

CREATE TABLE users (
  user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(20) NOT NULL,
  password VARCHAR(60) NOT NULL
);

CREATE TABLE users_roles (
  user_user_id BIGINT NOT NULL,
  role_role_id BIGINT NOT NULL
);

CREATE TABLE project (
  project_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  project_name VARCHAR(256) NOT NULL,
  parent_project_id BIGINT REFERENCES project
);

