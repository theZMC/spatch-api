DROP DATABASE `spatch` IF EXISTS;

CREATE DATABASE `spatch`;

USE `spatch`;

CREATE TABLE `spatch`.`dispatch` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `placeId` INT NOT NULL,
  `tripId` INT NULL DEFAULT NULL,
  `scheduledTime` TIMESTAMP NULL DEFAULT NULL,
  `startTime` TIMESTAMP NULL DEFAULT NULL,
  `endTime` TIMESTAMP NULL DEFAULT NULL,
  `estTimeOnSite` INT NOT NULL DEFAULT '0',
  `isComplete` BOOLEAN NOT NULL DEFAULT FALSE,
  `priority` ENUM('emergency', 'standard', 'preventative', '') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE `spatch`.`trip` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  `technicianId` INT NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE `spatch`.`technician` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(128) NOT NULL,
  `lastName` VARCHAR(128) NOT NULL,
  `avatar` JSON NULL DEFAULT NULL,
  `phone` BIGINT NOT NULL,
  `email` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE `spatch`.`place` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `primaryContactId` INT NOT NULL,
  `address` VARCHAR(256) NOT NULL,
  `gPlaceId` VARCHAR(64) NULL DEFAULT NULL,
  `displayName` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE `spatch`.`note` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `dispatchId` INT NOT NULL,
  `time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `message` TEXT NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE `spatch`.`contact` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(128) NOT NULL,
  `lastName` VARCHAR(128) NOT NULL,
  `phone` BIGINT NOT NULL,
  `email` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;

/* Add foreign key constraints */
ALTER TABLE
  `dispatch`
ADD
  FOREIGN KEY (`tripId`) REFERENCES `trip`(`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE
  `dispatch`
ADD
  FOREIGN KEY (`placeId`) REFERENCES `place`(`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE
  `trip`
ADD
  FOREIGN KEY (`technicianId`) REFERENCES `technician`(`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE
  `place`
ADD
  FOREIGN KEY (`primaryContactId`) REFERENCES `contact`(`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE
  `note`
ADD
  FOREIGN KEY (`dispatchId`) REFERENCES `dispatch`(`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;