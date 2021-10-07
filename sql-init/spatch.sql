-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: mariadb
-- Generation Time: Oct 07, 2021 at 01:38 AM
-- Server version: 10.6.4-MariaDB-1:10.6.4+maria~focal
-- PHP Version: 7.4.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `spatch`
--

-- --------------------------------------------------------

--
-- Table structure for table `contact`
--

CREATE TABLE `contact` (
  `id` int(11) NOT NULL,
  `firstName` varchar(128) NOT NULL,
  `lastName` varchar(128) NOT NULL,
  `phone` bigint(20) NOT NULL,
  `email` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `contact`
--

INSERT INTO `contact` (`id`, `firstName`, `lastName`, `phone`, `email`) VALUES
(1, 'Margaret', 'Houlihan', 5014441234, 'margaret@mash4077.mil'),
(2, 'Charles', 'Winchester', 5014442468, 'charwin@mash4077.mil'),
(3, 'Sherman', 'Potter', 5014441234, 'stpotter@mash4077.mil'),
(4, 'Henry', 'Blake', 5014441234, 'hblake@mash4077.mil');

-- --------------------------------------------------------

--
-- Table structure for table `dispatch`
--

CREATE TABLE `dispatch` (
  `id` int(11) NOT NULL,
  `placeId` int(11) NOT NULL,
  `tripId` int(11) DEFAULT NULL,
  `scheduledTime` bigint(20) DEFAULT NULL,
  `startTime` bigint(20) DEFAULT NULL,
  `endTime` bigint(20) DEFAULT NULL,
  `estTimeOnSite` int(11) NOT NULL DEFAULT 0,
  `isComplete` tinyint(1) NOT NULL DEFAULT 0,
  `priority` enum('emergency','standard','preventative','') NOT NULL DEFAULT 'standard'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `dispatch`
--

INSERT INTO `dispatch` (`id`, `placeId`, `tripId`, `scheduledTime`, `startTime`, `endTime`, `estTimeOnSite`, `isComplete`, `priority`) VALUES
(1, 1, 1, 20220214130000, NULL, NULL, 1, 0, 'standard'),
(2, 14, NULL, NULL, NULL, NULL, 90, 0, 'standard');

-- --------------------------------------------------------

--
-- Table structure for table `note`
--

CREATE TABLE `note` (
  `id` int(11) NOT NULL,
  `dispatchId` int(11) NOT NULL,
  `time` timestamp NOT NULL DEFAULT current_timestamp(),
  `message` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `note`
--

INSERT INTO `note` (`id`, `dispatchId`, `time`, `message`) VALUES
(1, 1, '2021-08-29 23:37:30', 'Charles at Damgoode Pies states their secondary oven is down. Need service to diagnose and repair.'),
(2, 2, '2021-10-07 01:26:38', 'Notes notes notes');

-- --------------------------------------------------------

--
-- Table structure for table `place`
--

CREATE TABLE `place` (
  `id` int(11) NOT NULL,
  `primaryContactId` int(11) DEFAULT NULL,
  `address` varchar(256) NOT NULL,
  `city` varchar(255) NOT NULL,
  `state` varchar(2) NOT NULL,
  `zipcode` varchar(12) NOT NULL,
  `gPlaceId` varchar(64) DEFAULT NULL,
  `displayName` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `place`
--

INSERT INTO `place` (`id`, `primaryContactId`, `address`, `city`, `state`, `zipcode`, `gPlaceId`, `displayName`) VALUES
(1, 2, '6706 Cantrell Rd', 'Little Rock', 'AR', '72207', 'ChIJC4aMj5mj0ocR6Fk2TgVdIsI', 'Damgoode Pies'),
(2, 1, '7410 Cantrell Rd', 'Little Rock', 'AR', '72207', 'ChIJ096Y2Oqj0ocRKekYd-GTGAY', 'Hubcap Burger Company'),
(3, NULL, '6 Oriole Cir', 'Little Rock', 'AR', '72205', 'ChIJ9y2Eqnmk0ocR0mCzfQ3ACio', '6 Oriole Cir'),
(5, 1, '701 Broadway St', 'Little Rock', 'AR', '72201', 'ChIJD7P1j2W70ocRDW7_9-DsOBU', 'McDonald\'s'),
(6, 1, '101 E Markham St', 'Little Rock', 'AR', '72201', 'ChIJPc2YMA270ocRlJGFlnXcf24', 'Margaret Clark Adventure Park'),
(7, 1, '4450 E McCain Blvd', 'North Little Rock', 'AR', '72117', 'ChIJcxRChYS-0ocRsLmMQdlArIs', 'Walmart Supercenter'),
(8, 1, '500 Woodlane St', 'Little Rock', 'AR', '72201', 'ChIJlak53Fu70ocR-0-Av2EQh3c', 'Arkansas State Capitol'),
(9, 1, '23800 Chenal Pkwy', 'Little Rock', 'AR', '72223', 'ChIJu2lRFLpfzYcRiWpaABU2nXs', 'Murphy USA'),
(10, 1, '113 Commons Dr', 'Maumelle', 'AR', '72113', 'ChIJoVOcLGSf0ocRzZyJmacrZ68', 'Chick-fil-A'),
(11, 1, '11525 Cantrell Rd', 'Little Rock', 'AR', '72212', 'ChIJH6Sk7Aqh0ocRBePAHB-Ct6U', 'Chick-fil-A'),
(12, 2, '12500 W Markham St', 'Little Rock', 'AR', '72211', 'ChIJqTt5vy2h0ocRArMELLvvAp8', 'Chick-fil-A'),
(13, 1, '4320 E McCain Blvd', 'North Little Rock', 'AR', '72117', 'ChIJdVSMm4S-0ocRVGvjTw7FZWY', 'Chick-fil-A'),
(14, 1, '13201 Crystal Hill Rd', 'North Little Rock', 'AR', '72113', 'ChIJZ1Pd4vGh0ocRwrAymXLWlgQ', 'Murphy USA');

-- --------------------------------------------------------

--
-- Table structure for table `technician`
--

CREATE TABLE `technician` (
  `id` int(11) NOT NULL,
  `firstName` varchar(128) NOT NULL,
  `lastName` varchar(128) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `phone` bigint(20) NOT NULL,
  `homePlaceId` int(11) NOT NULL,
  `email` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `technician`
--

INSERT INTO `technician` (`id`, `firstName`, `lastName`, `avatar`, `phone`, `homePlaceId`, `email`) VALUES
(1, 'Hawkeye', 'Pierce', '86b51805-5fa8-4196-8c66-0f020288ad1d.jpg', 5015551234, 3, 'hawkeye@spatch.app'),
(2, 'BJ', 'Hunnicutt', '8fee065a-f033-4d79-a0cc-a304afc9f237.jpg', 5015552468, 3, 'bjhun@spatch.app');

-- --------------------------------------------------------

--
-- Table structure for table `trip`
--

CREATE TABLE `trip` (
  `id` int(11) NOT NULL,
  `date` date NOT NULL,
  `startTime` bigint(20) DEFAULT NULL,
  `technicianId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `trip`
--

INSERT INTO `trip` (`id`, `date`, `startTime`, `technicianId`) VALUES
(1, '2022-02-17', 90000000, 1),
(2, '2022-02-14', 84500000, 2),
(3, '2022-02-14', 94980000, 1),
(4, '2021-10-03', 59400000, 1),
(5, '2021-10-03', 97260000, 1),
(6, '2021-10-03', 97500000, 1),
(7, '2021-10-03', 54360000, 1),
(8, '2021-10-03', 99360000, 1),
(9, '2021-10-03', 99900000, 1),
(10, '2021-10-03', 99960000, 1),
(11, '2021-10-03', 100080000, 1),
(12, '2021-10-05', 79200000, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `contact`
--
ALTER TABLE `contact`
  ADD PRIMARY KEY (`id`),
  ADD KEY `firstName` (`firstName`),
  ADD KEY `lastName` (`lastName`);

--
-- Indexes for table `dispatch`
--
ALTER TABLE `dispatch`
  ADD PRIMARY KEY (`id`),
  ADD KEY `tripId` (`tripId`),
  ADD KEY `placeId` (`placeId`);

--
-- Indexes for table `note`
--
ALTER TABLE `note`
  ADD PRIMARY KEY (`id`),
  ADD KEY `dispatchId` (`dispatchId`);

--
-- Indexes for table `place`
--
ALTER TABLE `place`
  ADD PRIMARY KEY (`id`),
  ADD KEY `primaryContactId` (`primaryContactId`),
  ADD KEY `gPlaceId` (`gPlaceId`);

--
-- Indexes for table `technician`
--
ALTER TABLE `technician`
  ADD PRIMARY KEY (`id`),
  ADD KEY `homePlaceId` (`homePlaceId`);

--
-- Indexes for table `trip`
--
ALTER TABLE `trip`
  ADD PRIMARY KEY (`id`),
  ADD KEY `technicianId` (`technicianId`),
  ADD KEY `date` (`date`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `contact`
--
ALTER TABLE `contact`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `dispatch`
--
ALTER TABLE `dispatch`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `note`
--
ALTER TABLE `note`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `place`
--
ALTER TABLE `place`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `technician`
--
ALTER TABLE `technician`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `trip`
--
ALTER TABLE `trip`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `dispatch`
--
ALTER TABLE `dispatch`
  ADD CONSTRAINT `dispatch_ibfk_1` FOREIGN KEY (`tripId`) REFERENCES `trip` (`id`),
  ADD CONSTRAINT `dispatch_ibfk_2` FOREIGN KEY (`placeId`) REFERENCES `place` (`id`);

--
-- Constraints for table `note`
--
ALTER TABLE `note`
  ADD CONSTRAINT `note_ibfk_1` FOREIGN KEY (`dispatchId`) REFERENCES `dispatch` (`id`);

--
-- Constraints for table `place`
--
ALTER TABLE `place`
  ADD CONSTRAINT `place_ibfk_1` FOREIGN KEY (`primaryContactId`) REFERENCES `contact` (`id`);

--
-- Constraints for table `technician`
--
ALTER TABLE `technician`
  ADD CONSTRAINT `technician_ibfk_1` FOREIGN KEY (`homePlaceId`) REFERENCES `place` (`id`);

--
-- Constraints for table `trip`
--
ALTER TABLE `trip`
  ADD CONSTRAINT `trip_ibfk_1` FOREIGN KEY (`technicianId`) REFERENCES `technician` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
