-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 11, 2020 at 05:13 AM
-- Server version: 10.4.8-MariaDB
-- PHP Version: 7.2.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `clinicmanagement`
--

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

CREATE TABLE `booking` (
  `booking_id` int(4) NOT NULL,
  `user_id` int(4) NOT NULL,
  `date` date NOT NULL,
  `time` text NOT NULL,
  `symptoms` varchar(100) NOT NULL,
  `doctor` int(4) NOT NULL,
  `status` varchar(15) NOT NULL,
  `prescription_status` varchar(10) NOT NULL,
  `rejecting_remarks` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `booking`
--

INSERT INTO `booking` (`booking_id`, `user_id`, `date`, `time`, `symptoms`, `doctor`, `status`, `prescription_status`, `rejecting_remarks`) VALUES
(54, 2, '2019-12-25', '13:00', 'fever', 4, 'Approved', 'Pending', ''),
(56, 2, '2020-01-08', '13:00', 'back pain', 3, 'Approved', 'Pending', ''),
(57, 8, '2019-12-31', '13:00', 'fever', 3, 'Completed', 'Completed', ''),
(58, 8, '2019-12-31', '13:00', 'flu', 3, 'Rejected', 'Pending', 'I got an emergency leave'),
(59, 8, '2019-12-31', '11:00', 'headache', 3, 'Approved', 'Pending', ''),
(61, 8, '2020-01-01', '13:00', 'rashes', 3, 'Completed', 'Completed', ''),
(62, 8, '2020-01-02', '13:00', 'sick', 4, 'Approved', 'Pending', ''),
(63, 8, '2020-01-02', '15:00', 'cough', 4, 'Pending', 'Pending', ''),
(65, 8, '2020-01-02', '20:00', 'sick', 3, 'Pending', 'Pending', ''),
(66, 10, '2020-01-01', '10:00', 'cough', 3, 'Completed', 'Completed', '');

-- --------------------------------------------------------

--
-- Table structure for table `inventory`
--

CREATE TABLE `inventory` (
  `product_name` varchar(20) NOT NULL,
  `product_id` varchar(5) NOT NULL,
  `supplier_name` varchar(30) NOT NULL,
  `stock` int(255) NOT NULL,
  `price` int(255) NOT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `inventory`
--

INSERT INTO `inventory` (`product_name`, `product_id`, `supplier_name`, `stock`, `price`, `status`) VALUES
('Paracetamol', 'P3210', 'Razi Sdn Bhd', 0, 8, 'inactive'),
('Iodine', 'R231', 'Razi Sdn Bhd', 7, 11, 'inactive'),
('Cough Syrup', 'R2312', 'Razi Sdn Bhd', 70, 2, 'active'),
('Antibiotic', 'R2348', 'Razi Sdn Bhd', 55, 10, 'active'),
('Aspirin', 'S2101', 'Razi Sdn Bhd', 54, 10, 'active');

-- --------------------------------------------------------

--
-- Table structure for table `prescription`
--

CREATE TABLE `prescription` (
  `prescription_id` int(4) NOT NULL,
  `user_id` int(4) NOT NULL,
  `booking_id` int(4) NOT NULL,
  `doctor` int(4) NOT NULL,
  `dosage` int(4) NOT NULL,
  `dosage_type` varchar(20) NOT NULL,
  `frequency` varchar(50) NOT NULL,
  `medicine` varchar(20) NOT NULL,
  `intake` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `prescription`
--

INSERT INTO `prescription` (`prescription_id`, `user_id`, `booking_id`, `doctor`, `dosage`, `dosage_type`, `frequency`, `medicine`, `intake`) VALUES
(55, 8, 57, 3, 1, 'tablet', 'twice a day', 'Iodine', 'orally'),
(56, 8, 57, 3, 1, 'tablet', 'twice a day', 'Aspirin', 'orally'),
(57, 8, 61, 3, 3, 'tablet', 'thrice a day', 'Iodine', 'orally'),
(58, 10, 66, 3, 3, 'tablet', 'twice a day', 'Aspirin', 'orally'),
(59, 10, 66, 3, 2, 'tablet', 'twice a day', 'Iodine', 'orally');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `ID` int(4) NOT NULL,
  `name` varchar(120) NOT NULL,
  `email` varchar(120) NOT NULL,
  `password` varchar(50) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `DOB` varchar(15) NOT NULL,
  `ic` varchar(15) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `role` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`ID`, `name`, `email`, `password`, `phone`, `DOB`, `ic`, `gender`, `role`) VALUES
(1, 'Nik Adam Danish', 'nikadamdanish@gmail.com', 'nikadam99', '019-6604766', '31/05/1999', '990531035145', 'male', 'admin'),
(2, 'Bellarina Chew', 'bellarinachew1118@gmail.com', 'billy1118', '014-3580620', '18/11/1998', '981118126452', 'female', 'patient'),
(3, 'Dr. Ran', 'ran@hotmail.com', '123456', '019-696969', '19/11/1999', '991119125179', 'male', 'doctor'),
(4, 'Dr. Dom', 'dom@hotmail.com', '123456', '019-4766660', '20/11/1999', '991120019127', 'male', 'doctor'),
(5, 'Huzaifah Jep', 'huzaifah@gmail.com', '123456', '017-6969696', '31/03/1998', '980331055433', 'male', 'patient'),
(6, 'Nik Arman ', 'arman@hotmail.com', '123456', '019-4476660', '21/07/2002', '020721125777', 'female', 'pharmacist'),
(8, 'Nurhafizah', 'hafizah@gmail.com', '123456', '011-8947348', '10/10/1998', '980330106810', 'female', 'patient'),
(10, 'Husna', 'husna@gmail.com', '123456', '010-5678978', '10/10/1998', '980235156781', 'female', 'patient');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`booking_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `doctor` (`doctor`);

--
-- Indexes for table `inventory`
--
ALTER TABLE `inventory`
  ADD PRIMARY KEY (`product_id`);

--
-- Indexes for table `prescription`
--
ALTER TABLE `prescription`
  ADD PRIMARY KEY (`prescription_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `doctor` (`doctor`),
  ADD KEY `booking_id` (`booking_id`) USING BTREE;

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `booking`
--
ALTER TABLE `booking`
  MODIFY `booking_id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=67;

--
-- AUTO_INCREMENT for table `prescription`
--
ALTER TABLE `prescription`
  MODIFY `prescription_id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=60;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `ID` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `booking`
--
ALTER TABLE `booking`
  ADD CONSTRAINT `booking_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`ID`),
  ADD CONSTRAINT `booking_ibfk_2` FOREIGN KEY (`doctor`) REFERENCES `user` (`ID`);

--
-- Constraints for table `prescription`
--
ALTER TABLE `prescription`
  ADD CONSTRAINT `prescription_ibfk_1` FOREIGN KEY (`booking_id`) REFERENCES `booking` (`booking_id`),
  ADD CONSTRAINT `prescription_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`ID`),
  ADD CONSTRAINT `prescription_ibfk_3` FOREIGN KEY (`doctor`) REFERENCES `user` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
