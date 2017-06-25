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
`email` VARCHAR(100) NOT NULL,
`vehicleNumber` VARCHAR(100) NOT NULL,
`pendingStatus` INT NOT NULL DEFAULT 1
);

-- Password is passw
INSERT INTO `user` VALUES (1,'normal1','$2a$12$.j4ViduPIClhj1ul7zVfduOS8n4luaw3dzLoLvr9IN7OP8B/qQHPS',NULL,1,'B','23812251','a@b.c','someVehicleNumber',1);

INSERT INTO `user` VALUES (2,'normal2','$2a$12$.j4ViduPIClhj1ul7zVfduOS8n4luaw3dzLoLvr9IN7OP8B/qQHPS',NULL,1,'B','23812251','a@b.c','someVehicleNumber',0);
CREATE TABLE admin (
`adminId` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`adminName` VARCHAR(100) NOT NULL,
`password` VARCHAR(100) NOT NULL,
`token` VARCHAR(100),
`locationId` INT NOT NULL
);

-- Password is password123
INSERT INTO admin (adminName, password, locationId) VALUES ("admin1", "$2a$12$cQWa6z0gbZmx0h69e22Fn.BY6UtyeUaak2vSiXUZNtJSin0Zbrgne", 1);
INSERT INTO admin (adminName, password, locationId) VALUES ("admin2", "$2a$12$cQWa6z0gbZmx0h69e22Fn.BY6UtyeUaak2vSiXUZNtJSin0Zbrgne", 2);
INSERT INTO admin (adminName, password, locationId) VALUES ("admin3", "$2a$12$cQWa6z0gbZmx0h69e22Fn.BY6UtyeUaak2vSiXUZNtJSin0Zbrgne", 3);
INSERT INTO admin (adminName, password, locationId) VALUES ("admin4", "$2a$12$cQWa6z0gbZmx0h69e22Fn.BY6UtyeUaak2vSiXUZNtJSin0Zbrgne", 4);

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
	`numberOfSeats` INT NOT NULL,
	`finishedRide` INT NOT NULL DEFAULT 0
);

CREATE TABLE `rideTaker`
(
	`rideId` INT NOT NULL,
	`takerUserId` INT NOT NULL
);

