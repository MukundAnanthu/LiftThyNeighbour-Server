DROP DATABASE IF EXISTS application;
CREATE DATABASE application;
USE application;


CREATE TABLE user (
`userId` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`userName` VARCHAR(100) NOT NULL,
`password` VARCHAR(100) NOT NULL,
`token` VARCHAR(100),
`apartmentId` INT NOT NULL,
`flatNumber` VARCHAR(100) NOT NULL,
`contactNumber` VARCHAR(100) NOT NULL,
`emailId` VARCHAR(100) NOT NULL,
`vehicleNumbers` VARCHAR(100) NOT NULL,
`pendingStatus` INT NOT NULL
);

CREATE TABLE admin (
`adminId` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`adminName` VARCHAR(100) NOT NULL,
`password` VARCHAR(100) NOT NULL,
`token` VARCHAR(100),
`locationId` INT NOT NULL
);

CREATE TABLE location (
`locationId` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`locationName` VARCHAR(100) NOT NULL,
`locationType` INT NOT NULL,
`distance` INT NOT NULL
);

INSERT INTO location (locationName, locationType, distance) VALUES ("Apartment A", 1, 0);
INSERT INTO location (locationName, locationType, distance) VALUES ("Apartment B", 1, 1);
INSERT INTO location (locationName, locationType, distance) VALUES ("TechPark A", 0, 2);
INSERT INTO location (locationName, locationType, distance) VALUES ("TechPark B", 0, 3);
CREATE TABLE `rideOffer`
(
	`rideId` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`driverUserId` INT NOT NULL,
	`sourceType` INT NOT NULL,
	`sourceId` INT NOT NULL,
	`destinationId` INT NOT NULL,
	`departureTime` VARCHAR(10),
	`numberOfSeats` INT NOT NULL
);

CREATE TABLE `rideTaker`
(
	`rideId` INT NOT NULL,
	`takerUserId` INT NOT NULL
);

