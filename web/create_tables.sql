-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 11, 2019 at 03:27 AM
-- Server version: 10.1.35-MariaDB
-- PHP Version: 7.2.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `inventorydb`
--

-- --------------------------------------------------------

--
-- Table structure for table `inventory`
--
DROP TABLE IF EXISTS INVENTORY;
CREATE TABLE `inventory` (
  `id` int(11) NOT NULL,
  `email` varchar(100) COLLATE utf8_bin NOT NULL,
  `artist` varchar(100) COLLATE utf8_bin NOT NULL,
  `album` varchar(100) COLLATE utf8_bin NOT NULL,
  `year` int(11) NOT NULL,
  `state` varchar(20) COLLATE utf8_bin NOT NULL,
  `state_detailed` varchar(255) COLLATE utf8_bin NOT NULL,
  `upc` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `notes` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--
DROP TABLE IF EXISTS USERS;
CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `email` varchar(100) COLLATE utf8_bin NOT NULL,
  `firstname` varchar(255) COLLATE utf8_bin NOT NULL,
  `lastname` varchar(255) COLLATE utf8_bin NOT NULL,
  `street` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `city` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `province` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `country` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `postal_code` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(100) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `inventory`
--
ALTER TABLE `inventory`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `inventory`
--
ALTER TABLE `inventory`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

ALTER TABLE `users` ADD UNIQUE(`email`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

INSERT INTO `inventory` (`id`, `email`, `artist`, `album`, `year`, `state`, `state_detailed`, `upc`, `notes`) 
VALUES (NULL, 'me@me', 'E. Presley', 'The moon', '1955', 'G', 'Few sctraches', '', NULL);

INSERT INTO `inventory` (`id`, `email`, `artist`, `album`, `year`, `state`, `state_detailed`, `upc`, `notes`) 
VALUES (NULL, 'you@you', 'E. Piath', 'Rien', '1976', 'VG', 'like new', '', NULL);

INSERT INTO `users` (`id`, `email`, `firstname`, `lastname`, `street`, `city`, `province`, `country`, `postal_code`, `password`) VALUES (NULL, 'me@me', 'Evgeniya', 
'Korchagina', '3333-Jean Talon', 'Montreal', 'QC', 'Canada', 'h3r2g1', 'evgeniya');

INSERT INTO `users` (`id`, `email`, `firstname`, `lastname`, `street`, `city`, `province`, `country`, `postal_code`, `password`) VALUES (NULL, 'you@you', 'Alex', 
'Brown', NULL, NULL, NULL, NULL, NULL, 'alex');