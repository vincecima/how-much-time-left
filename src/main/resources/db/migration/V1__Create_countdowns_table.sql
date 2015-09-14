CREATE EXTENSION pgcrypto;

CREATE TABLE countdowns (
  id uuid DEFAULT gen_random_uuid(),
  name varchar(255) NOT NULL,
  target_date_time timestamp with time zone NOT NULL
);