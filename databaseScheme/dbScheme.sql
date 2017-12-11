-- -----------------------------------------------------
-- Scheme
-- -----------------------------------------------------

DROP SCHEMA IF EXISTS public CASCADE;
CREATE SCHEMA IF NOT EXISTS public;

-- -----------------------------------------------------
-- Create hibernate_sequence
-- -----------------------------------------------------

DROP SEQUENCE IF EXISTS hibernate_sequence;
CREATE SEQUENCE hibernate_sequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 20
  CACHE 1;
ALTER TABLE hibernate_sequence
  OWNER TO viktor_kulygin;

-- -----------------------------------------------------
-- Table Orders
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Bookings (
  id INT NOT NULL DEFAULT nextval('hibernate_sequence'),
  workTime VARCHAR(45) NULL,
  date TIMESTAMP(0) NULL,
  employee VARCHAR(45) NULL,
  bookingDate TIMESTAMP(0) NULL,
  bookingTime INT NULL,
  PRIMARY KEY (id))
;