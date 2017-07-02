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

-- Password is pass
INSERT INTO `user` VALUES (1,'normal1','$2a$12$4Skgek/GmW3DTJnwtGNK0eREIAyldSjgva3ybtz7i11pLphviAoh6',NULL,1,'1B','9342123456','normal1@something.com','KA43Y2743',0);
INSERT INTO `user` VALUES (2,'normal2','$2a$12$4Skgek/GmW3DTJnwtGNK0eREIAyldSjgva3ybtz7i11pLphviAoh6',NULL,2,'2A','8197564323','normal2@something.com','KA43Y4633',0);
INSERT INTO `user` VALUES (3,'ajith','$2a$12$4Skgek/GmW3DTJnwtGNK0eREIAyldSjgva3ybtz7i11pLphviAoh6',NULL,1,'121A','9483123434','contact@pandel.in','KA19L1234',0);
INSERT INTO `user` VALUES (4,'mukund','$2a$12$4Skgek/GmW3DTJnwtGNK0eREIAyldSjgva3ybtz7i11pLphviAoh6',NULL,1,'12B','9234567832','mukund.ananthu@gmail.com','KA20L7536',0);
INSERT INTO `user` VALUES (5,'charan','$2a$12$4Skgek/GmW3DTJnwtGNK0eREIAyldSjgva3ybtz7i11pLphviAoh6',NULL,2,'1B','8763638565','charan@gmail.com','KA34F6532',0);
INSERT INTO `user` VALUES (6,'deekshith','$2a$12$4Skgek/GmW3DTJnwtGNK0eREIAyldSjgva3ybtz7i11pLphviAoh6',NULL,2,'2A','8763634632','deekshith@gmail.com','KA34F6543',0);
CREATE TABLE admin (
`adminId` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`adminName` VARCHAR(100) NOT NULL,
`password` VARCHAR(100) NOT NULL,
`token` VARCHAR(100),
`locationId` INT NOT NULL
);

-- Password is pass
INSERT INTO admin (adminName, password, locationId) VALUES ("admin1", "$2a$12$4Skgek/GmW3DTJnwtGNK0eREIAyldSjgva3ybtz7i11pLphviAoh6", 1);
INSERT INTO admin (adminName, password, locationId) VALUES ("admin2", "$2a$12$4Skgek/GmW3DTJnwtGNK0eREIAyldSjgva3ybtz7i11pLphviAoh6", 2);
INSERT INTO admin (adminName, password, locationId) VALUES ("admin3", "$2a$12$4Skgek/GmW3DTJnwtGNK0eREIAyldSjgva3ybtz7i11pLphviAoh6", 1);
INSERT INTO admin (adminName, password, locationId) VALUES ("admin4", "$2a$12$4Skgek/GmW3DTJnwtGNK0eREIAyldSjgva3ybtz7i11pLphviAoh6", 2);

CREATE TABLE location (
`locationId` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`locationName` VARCHAR(100) NOT NULL,
`locationType` INT NOT NULL,
`distance` INT NOT NULL
);

INSERT INTO location (locationName, locationType, distance) VALUES ("Ivory Heights", 1, 1);
INSERT INTO location (locationName, locationType, distance) VALUES ("Durga Petals", 1, 5);
INSERT INTO location (locationName, locationType, distance) VALUES ("Bhagmane", 0, 3);
INSERT INTO location (locationName, locationType, distance) VALUES ("RMZ Eco World", 0, 6);
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
	`takerUserId` INT NOT NULL,
	`techParkId` INT NOT NULL
);

