-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 09, 2019 at 08:10 AM
-- Server version: 10.1.34-MariaDB
-- PHP Version: 7.2.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hotel`
--

-- --------------------------------------------------------

--
-- Table structure for table `amenities`
--

CREATE TABLE `amenities` (
  `amenityID` char(10) NOT NULL,
  `amenityName` varchar(20) DEFAULT NULL,
  `amenityDescription` varchar(100) DEFAULT NULL,
  `amenityPrice` double(7,2) DEFAULT NULL,
  `amenityOperationStartTime` time DEFAULT NULL,
  `amenityOperationEndTime` time DEFAULT NULL,
  `amenityRules` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `amenities`
--

INSERT INTO `amenities` (`amenityID`, `amenityName`, `amenityDescription`, `amenityPrice`, `amenityOperationStartTime`, `amenityOperationEndTime`, `amenityRules`) VALUES
('A1', 'Tennis Court', 'Enjoy a game of tennis with your loved ones', 25.00, '09:00:00', '23:30:00', '-'),
('A2', 'Sauna Baths', 'Take a relaxing bath to melt you stress away', 5.00, '09:00:00', '23:30:00', '-'),
('A3', 'Horse Stables', 'Enjoy stables with a covered horse back trail and enjoy the scenery', 45.00, '10:00:00', '19:00:00', '-'),
('A4', 'Rent a Boat', 'Enjoy a drift away in the waters', 25.00, '09:00:00', '16:30:00', '-'),
('A5', 'Ball Room', 'Rent the entire ball room for your special occasions', 150.00, '07:00:00', '01:00:00', '-'),
('A6', 'Dining Room', '', 15.00, '09:00:00', '02:30:00', '-'),
('A7', 'Indoor Swimming Pool', 'Enjoy indoor pool with covered shade to swim in any weather', 5.00, '09:00:00', '23:30:00', '-'),
('A8', 'Outdoor Swimming Poo', 'Enjoy the view alongside the pool', 0.00, '09:00:00', '23:30:00', '-');

-- --------------------------------------------------------

--
-- Table structure for table `amenities_usage_log`
--

CREATE TABLE `amenities_usage_log` (
  `amenityID` char(10) NOT NULL,
  `roomID` char(10) NOT NULL,
  `guestID` char(10) NOT NULL,
  `usageStartTime` time DEFAULT NULL,
  `usageEndTime` time DEFAULT NULL,
  `amenityTotalPrice` double(7,2) DEFAULT NULL,
  `remarks` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `amenities_usage_log`
--

INSERT INTO `amenities_usage_log` (`amenityID`, `roomID`, `guestID`, `usageStartTime`, `usageEndTime`, `amenityTotalPrice`, `remarks`) VALUES
('A1', 'HR1', 'G1', '13:00:00', '15:30:00', 62.50, '-');

-- --------------------------------------------------------

--
-- Table structure for table `cash`
--

CREATE TABLE `cash` (
  `paymentMethodID` char(10) NOT NULL,
  `paymentMethodName` char(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `check_in`
--

CREATE TABLE `check_in` (
  `checkInID` char(10) NOT NULL,
  `checkInFormID` char(10) NOT NULL,
  `depositAmount` double(7,2) DEFAULT NULL,
  `paymentStatus` char(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `check_in`
--

INSERT INTO `check_in` (`checkInID`, `checkInFormID`, `depositAmount`, `paymentStatus`) VALUES
('CI1', 'CIF1', 4050.00, 'Sucessful');

-- --------------------------------------------------------

--
-- Table structure for table `check_in_form`
--

CREATE TABLE `check_in_form` (
  `checkInFormID` char(10) NOT NULL,
  `reservationID` char(10) NOT NULL,
  `signature` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `check_in_form`
--

INSERT INTO `check_in_form` (`checkInFormID`, `reservationID`, `signature`) VALUES
('CIF1', 'R1', 1);

-- --------------------------------------------------------

--
-- Table structure for table `cheque`
--

CREATE TABLE `cheque` (
  `paymentMethodID` char(10) NOT NULL,
  `paymentMethodName` char(30) DEFAULT NULL,
  `chequeHolderName` varchar(25) DEFAULT NULL,
  `chequeBankName` varchar(25) DEFAULT NULL,
  `chequeAmount` double(7,2) DEFAULT NULL,
  `chequeDate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `credit_card`
--

CREATE TABLE `credit_card` (
  `paymentMethodID` char(10) NOT NULL,
  `paymentMethodName` char(30) DEFAULT NULL,
  `creditCardHolderName` varchar(25) DEFAULT NULL,
  `creditCardBankName` varchar(25) DEFAULT NULL,
  `creditCardBalance` double(7,2) DEFAULT NULL,
  `creditCardExpiryMonth` int(11) DEFAULT NULL,
  `creditCardExpiryYear` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `credit_card`
--

INSERT INTO `credit_card` (`paymentMethodID`, `paymentMethodName`, `creditCardHolderName`, `creditCardBankName`, `creditCardBalance`, `creditCardExpiryMonth`, `creditCardExpiryYear`) VALUES
('PM3', 'Credit Card', 'John Doe', 'HSBC', 1000.00, 5, 2020);

-- --------------------------------------------------------

--
-- Table structure for table `food_order`
--

CREATE TABLE `food_order` (
  `orderID` char(10) NOT NULL,
  `menuID` char(10) NOT NULL,
  `roomID` char(10) NOT NULL,
  `guestID` char(10) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `orderTotalPrice` double(7,2) DEFAULT NULL,
  `remarks` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `food_order`
--

INSERT INTO `food_order` (`orderID`, `menuID`, `roomID`, `guestID`, `quantity`, `orderTotalPrice`, `remarks`) VALUES
('FO1', 'M1', 'HR1', 'G1', 2, 48.00, '-');

-- --------------------------------------------------------

--
-- Table structure for table `guest`
--

CREATE TABLE `guest` (
  `guestID` char(10) NOT NULL,
  `guestName` varchar(25) NOT NULL,
  `guestAddress` varchar(150) DEFAULT NULL,
  `guestPhoneNumber` varchar(20) DEFAULT NULL,
  `guestEmail` varchar(20) DEFAULT NULL,
  `guestGender` char(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `guest`
--

INSERT INTO `guest` (`guestID`, `guestName`, `guestAddress`, `guestPhoneNumber`, `guestEmail`, `guestGender`) VALUES
('G1', 'Amelia-Grace Brett', '265 Border Avenue Los Angeles, CA 90042', '707-266-7482', 'ameliagb@yahoo.com', 'Female'),
('G2', 'Jamel Franklin', '9026 Pumpkin Hill Road Watsonville, CA 95076', '408-301-3536', 'jamelf@yahoo.com', 'Male');

-- --------------------------------------------------------

--
-- Table structure for table `hoteldetails`
--

CREATE TABLE `hoteldetails` (
  `hotelName` varchar(25) NOT NULL,
  `address` varchar(150) DEFAULT NULL,
  `phoneNumber` varchar(20) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hoteldetails`
--

INSERT INTO `hoteldetails` (`hotelName`, `address`, `phoneNumber`, `email`) VALUES
('Giant Forest Inn Hotel', '7921 South Pin Oak Drive Tracy, CA 95376', '209-200-6070', 'giantforestinn@hotel');

-- --------------------------------------------------------

--
-- Table structure for table `housekeeping`
--

CREATE TABLE `housekeeping` (
  `housekeepingID` char(10) NOT NULL,
  `housekeepingName` char(30) DEFAULT NULL,
  `remarks` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `housekeeping`
--

INSERT INTO `housekeeping` (`housekeepingID`, `housekeepingName`, `remarks`) VALUES
('H1', 'Shampoo', '-');

-- --------------------------------------------------------

--
-- Table structure for table `housekeeping_log`
--

CREATE TABLE `housekeeping_log` (
  `housekeepingID` char(10) NOT NULL,
  `roomID` char(10) NOT NULL,
  `quantityUsed` int(11) DEFAULT NULL,
  `dateOfLog` date DEFAULT NULL,
  `housekeepingStatus` char(15) DEFAULT NULL,
  `remarks` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `housekeeping_log`
--

INSERT INTO `housekeeping_log` (`housekeepingID`, `roomID`, `quantityUsed`, `dateOfLog`, `housekeepingStatus`, `remarks`) VALUES
('H1', 'HR1', 2, '2019-11-12', 'Done', '-');

-- --------------------------------------------------------

--
-- Table structure for table `menu`
--

CREATE TABLE `menu` (
  `menuID` char(10) NOT NULL,
  `menuName` varchar(25) DEFAULT NULL,
  `menuDescription` varchar(25) DEFAULT NULL,
  `menuPrice` double(7,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `menu`
--

INSERT INTO `menu` (`menuID`, `menuName`, `menuDescription`, `menuPrice`) VALUES
('M1', 'Smoked Salmon with Salad', 'Smoked High-Quality Salmo', 24.00);

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `paymentID` char(10) NOT NULL,
  `serviceID` char(10) NOT NULL,
  `reservationID` char(10) NOT NULL,
  `orderID` char(10) NOT NULL,
  `roomID` char(10) NOT NULL,
  `guestID` char(10) NOT NULL,
  `subtotalAmount` double(7,2) DEFAULT NULL,
  `tipsAmount` double(7,2) DEFAULT NULL,
  `totalAmount` double(7,2) DEFAULT NULL,
  `paymentDate` date DEFAULT NULL,
  `paymentStatus` char(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`paymentID`, `serviceID`, `reservationID`, `orderID`, `roomID`, `guestID`, `subtotalAmount`, `tipsAmount`, `totalAmount`, `paymentDate`, `paymentStatus`) VALUES
('P1', 'S1', 'R1', 'FO1', 'HR1', 'G1', 4280.50, 10.00, 4290.50, '2019-11-20', 'Successful');

-- --------------------------------------------------------

--
-- Table structure for table `payment_method`
--

CREATE TABLE `payment_method` (
  `paymentMethodID` char(10) NOT NULL,
  `paymentID` char(10) NOT NULL,
  `guestID` char(10) NOT NULL,
  `paymentAmount` double(7,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `payment_method`
--

INSERT INTO `payment_method` (`paymentMethodID`, `paymentID`, `guestID`, `paymentAmount`) VALUES
('PM3', 'P1', 'G1', 4290.50);

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

CREATE TABLE `reservation` (
  `reservationID` char(10) NOT NULL,
  `roomID` char(10) NOT NULL,
  `guestID` char(10) NOT NULL,
  `checkInDate` date DEFAULT NULL,
  `arrivalTime` time DEFAULT NULL,
  `checkOutDate` date DEFAULT NULL,
  `adultPax` int(11) DEFAULT NULL,
  `childPax` int(11) DEFAULT NULL,
  `reservationEmailStatus` char(15) DEFAULT NULL,
  `reservationTotalPrice` double(7,2) DEFAULT NULL,
  `specialRequest` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`reservationID`, `roomID`, `guestID`, `checkInDate`, `arrivalTime`, `checkOutDate`, `adultPax`, `childPax`, `reservationEmailStatus`, `reservationTotalPrice`, `specialRequest`) VALUES
('R1', 'HR1', 'G1', '2019-11-11', '12:00:00', '2019-11-20', 2, 2, 'Successful', 4050.00, '-');

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE `room` (
  `roomID` char(10) NOT NULL,
  `roomTypeID` char(10) NOT NULL,
  `roomName` varchar(20) DEFAULT NULL,
  `roomAvailability` int(11) DEFAULT NULL,
  `roomAdultCapacity` int(11) DEFAULT NULL,
  `roomChildCapacity` int(11) DEFAULT NULL,
  `bedKingSizeQuantity` int(11) DEFAULT NULL,
  `bedQueenSizeQuantity` int(11) DEFAULT NULL,
  `bedSingleSizeQuantity` int(11) DEFAULT NULL,
  `smoking` char(15) DEFAULT NULL,
  `shower` varchar(20) DEFAULT NULL,
  `wifi` char(15) DEFAULT NULL,
  `aircon` char(15) DEFAULT NULL,
  `leasingPrice` double(7,2) DEFAULT NULL,
  `rentingPrice` double(7,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`roomID`, `roomTypeID`, `roomName`, `roomAvailability`, `roomAdultCapacity`, `roomChildCapacity`, `bedKingSizeQuantity`, `bedQueenSizeQuantity`, `bedSingleSizeQuantity`, `smoking`, `shower`, `wifi`, `aircon`, `leasingPrice`, `rentingPrice`) VALUES
('CR1', 'COTTAGE', 'Jason Mamoa', 5, 2, 3, 1, 1, 1, 'Smoking', 'Shower with Bathtub', 'Wi-Fi Available', 'Air COnditioned', 18000.00, 600.00),
('HR1', 'HOTEL', 'Michael Jackson', 5, 2, 2, 2, 0, 0, 'Non-Smoking', 'Standing Shower', 'Wi-Fi Available', 'Air COnditioned', 13500.00, 450.00);

-- --------------------------------------------------------

--
-- Table structure for table `roomstatus`
--

CREATE TABLE `roomstatus` (
  `roomID` char(10) NOT NULL,
  `roomStatus` char(15) DEFAULT NULL,
  `remarks` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `roomstatus`
--

INSERT INTO `roomstatus` (`roomID`, `roomStatus`, `remarks`) VALUES
('HR1', 'Ready', '-');

-- --------------------------------------------------------

--
-- Table structure for table `roomtype`
--

CREATE TABLE `roomtype` (
  `roomTypeID` char(10) NOT NULL,
  `roomTypeName` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `roomtype`
--

INSERT INTO `roomtype` (`roomTypeID`, `roomTypeName`) VALUES
('COTTAGE', 'Cottage Room'),
('HOTEL', 'Hotel Room');

-- --------------------------------------------------------

--
-- Table structure for table `services`
--

CREATE TABLE `services` (
  `serviceID` char(10) NOT NULL,
  `serviceName` varchar(20) DEFAULT NULL,
  `serviceDescription` varchar(100) DEFAULT NULL,
  `servicePrice` double(7,2) DEFAULT NULL,
  `serviceOperationStartTime` time DEFAULT NULL,
  `serviceOperationEndTime` time DEFAULT NULL,
  `serviceRules` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `services`
--

INSERT INTO `services` (`serviceID`, `serviceName`, `serviceDescription`, `servicePrice`, `serviceOperationStartTime`, `serviceOperationEndTime`, `serviceRules`) VALUES
('S1', 'Thai Massage', 'Enjoy a refreshing massage', 80.00, '09:00:00', '23:30:00', '-');

-- --------------------------------------------------------

--
-- Table structure for table `services_usage_log`
--

CREATE TABLE `services_usage_log` (
  `serviceID` char(10) NOT NULL,
  `roomID` char(10) NOT NULL,
  `guestID` char(10) NOT NULL,
  `usageStartTime` time DEFAULT NULL,
  `usageEndTime` time DEFAULT NULL,
  `servicesTotalPrice` double(7,2) DEFAULT NULL,
  `remarks` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `services_usage_log`
--

INSERT INTO `services_usage_log` (`serviceID`, `roomID`, `guestID`, `usageStartTime`, `usageEndTime`, `servicesTotalPrice`, `remarks`) VALUES
('S1', 'HR1', 'G1', '19:00:00', '20:30:00', 120.00, 'Good Service');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `amenities`
--
ALTER TABLE `amenities`
  ADD PRIMARY KEY (`amenityID`);

--
-- Indexes for table `amenities_usage_log`
--
ALTER TABLE `amenities_usage_log`
  ADD PRIMARY KEY (`amenityID`,`roomID`,`guestID`),
  ADD KEY `roomID` (`roomID`),
  ADD KEY `guestID` (`guestID`);

--
-- Indexes for table `cash`
--
ALTER TABLE `cash`
  ADD PRIMARY KEY (`paymentMethodID`);

--
-- Indexes for table `check_in`
--
ALTER TABLE `check_in`
  ADD PRIMARY KEY (`checkInID`,`checkInFormID`),
  ADD KEY `checkInFormID` (`checkInFormID`);

--
-- Indexes for table `check_in_form`
--
ALTER TABLE `check_in_form`
  ADD PRIMARY KEY (`checkInFormID`,`reservationID`),
  ADD KEY `reservationID` (`reservationID`);

--
-- Indexes for table `cheque`
--
ALTER TABLE `cheque`
  ADD PRIMARY KEY (`paymentMethodID`);

--
-- Indexes for table `credit_card`
--
ALTER TABLE `credit_card`
  ADD PRIMARY KEY (`paymentMethodID`);

--
-- Indexes for table `food_order`
--
ALTER TABLE `food_order`
  ADD PRIMARY KEY (`orderID`,`menuID`,`roomID`,`guestID`),
  ADD KEY `menuID` (`menuID`),
  ADD KEY `roomID` (`roomID`),
  ADD KEY `guestID` (`guestID`);

--
-- Indexes for table `guest`
--
ALTER TABLE `guest`
  ADD PRIMARY KEY (`guestID`);

--
-- Indexes for table `housekeeping`
--
ALTER TABLE `housekeeping`
  ADD PRIMARY KEY (`housekeepingID`);

--
-- Indexes for table `housekeeping_log`
--
ALTER TABLE `housekeeping_log`
  ADD PRIMARY KEY (`housekeepingID`,`roomID`),
  ADD KEY `roomID` (`roomID`);

--
-- Indexes for table `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`menuID`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`paymentID`,`serviceID`,`reservationID`,`orderID`,`roomID`,`guestID`),
  ADD KEY `serviceID` (`serviceID`),
  ADD KEY `reservationID` (`reservationID`),
  ADD KEY `orderID` (`orderID`),
  ADD KEY `roomID` (`roomID`),
  ADD KEY `guestID` (`guestID`);

--
-- Indexes for table `payment_method`
--
ALTER TABLE `payment_method`
  ADD PRIMARY KEY (`paymentMethodID`,`paymentID`),
  ADD KEY `paymentID` (`paymentID`),
  ADD KEY `guestID` (`guestID`);

--
-- Indexes for table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`reservationID`,`roomID`,`guestID`),
  ADD KEY `roomID` (`roomID`),
  ADD KEY `guestID` (`guestID`);

--
-- Indexes for table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`roomID`,`roomTypeID`),
  ADD KEY `roomTypeID` (`roomTypeID`);

--
-- Indexes for table `roomstatus`
--
ALTER TABLE `roomstatus`
  ADD PRIMARY KEY (`roomID`);

--
-- Indexes for table `roomtype`
--
ALTER TABLE `roomtype`
  ADD PRIMARY KEY (`roomTypeID`);

--
-- Indexes for table `services`
--
ALTER TABLE `services`
  ADD PRIMARY KEY (`serviceID`);

--
-- Indexes for table `services_usage_log`
--
ALTER TABLE `services_usage_log`
  ADD PRIMARY KEY (`serviceID`,`roomID`,`guestID`),
  ADD KEY `roomID` (`roomID`),
  ADD KEY `guestID` (`guestID`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `amenities_usage_log`
--
ALTER TABLE `amenities_usage_log`
  ADD CONSTRAINT `amenities_usage_log_ibfk_1` FOREIGN KEY (`amenityID`) REFERENCES `amenities` (`amenityID`),
  ADD CONSTRAINT `amenities_usage_log_ibfk_2` FOREIGN KEY (`roomID`) REFERENCES `room` (`roomID`),
  ADD CONSTRAINT `amenities_usage_log_ibfk_3` FOREIGN KEY (`guestID`) REFERENCES `guest` (`guestID`);

--
-- Constraints for table `cash`
--
ALTER TABLE `cash`
  ADD CONSTRAINT `cash_ibfk_1` FOREIGN KEY (`paymentMethodID`) REFERENCES `payment_method` (`paymentMethodID`);

--
-- Constraints for table `check_in`
--
ALTER TABLE `check_in`
  ADD CONSTRAINT `check_in_ibfk_1` FOREIGN KEY (`checkInFormID`) REFERENCES `check_in_form` (`checkInFormID`);

--
-- Constraints for table `check_in_form`
--
ALTER TABLE `check_in_form`
  ADD CONSTRAINT `check_in_form_ibfk_1` FOREIGN KEY (`reservationID`) REFERENCES `reservation` (`reservationID`);

--
-- Constraints for table `cheque`
--
ALTER TABLE `cheque`
  ADD CONSTRAINT `cheque_ibfk_1` FOREIGN KEY (`paymentMethodID`) REFERENCES `payment_method` (`paymentMethodID`);

--
-- Constraints for table `credit_card`
--
ALTER TABLE `credit_card`
  ADD CONSTRAINT `credit_card_ibfk_1` FOREIGN KEY (`paymentMethodID`) REFERENCES `payment_method` (`paymentMethodID`);

--
-- Constraints for table `food_order`
--
ALTER TABLE `food_order`
  ADD CONSTRAINT `food_order_ibfk_1` FOREIGN KEY (`menuID`) REFERENCES `menu` (`menuID`),
  ADD CONSTRAINT `food_order_ibfk_2` FOREIGN KEY (`roomID`) REFERENCES `room` (`roomID`),
  ADD CONSTRAINT `food_order_ibfk_3` FOREIGN KEY (`guestID`) REFERENCES `guest` (`guestID`);

--
-- Constraints for table `housekeeping_log`
--
ALTER TABLE `housekeeping_log`
  ADD CONSTRAINT `housekeeping_log_ibfk_1` FOREIGN KEY (`roomID`) REFERENCES `room` (`roomID`),
  ADD CONSTRAINT `housekeeping_log_ibfk_2` FOREIGN KEY (`housekeepingID`) REFERENCES `housekeeping` (`housekeepingID`);

--
-- Constraints for table `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`serviceID`) REFERENCES `services` (`serviceID`),
  ADD CONSTRAINT `payment_ibfk_2` FOREIGN KEY (`reservationID`) REFERENCES `reservation` (`reservationID`),
  ADD CONSTRAINT `payment_ibfk_3` FOREIGN KEY (`orderID`) REFERENCES `food_order` (`orderID`),
  ADD CONSTRAINT `payment_ibfk_4` FOREIGN KEY (`roomID`) REFERENCES `room` (`roomID`),
  ADD CONSTRAINT `payment_ibfk_5` FOREIGN KEY (`guestID`) REFERENCES `guest` (`guestID`);

--
-- Constraints for table `payment_method`
--
ALTER TABLE `payment_method`
  ADD CONSTRAINT `payment_method_ibfk_1` FOREIGN KEY (`paymentID`) REFERENCES `payment` (`paymentID`),
  ADD CONSTRAINT `payment_method_ibfk_2` FOREIGN KEY (`guestID`) REFERENCES `guest` (`guestID`);

--
-- Constraints for table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`roomID`) REFERENCES `room` (`roomID`),
  ADD CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`guestID`) REFERENCES `guest` (`guestID`);

--
-- Constraints for table `room`
--
ALTER TABLE `room`
  ADD CONSTRAINT `room_ibfk_1` FOREIGN KEY (`roomTypeID`) REFERENCES `roomtype` (`roomTypeID`);

--
-- Constraints for table `roomstatus`
--
ALTER TABLE `roomstatus`
  ADD CONSTRAINT `roomstatus_ibfk_1` FOREIGN KEY (`roomID`) REFERENCES `room` (`roomID`);

--
-- Constraints for table `services_usage_log`
--
ALTER TABLE `services_usage_log`
  ADD CONSTRAINT `services_usage_log_ibfk_1` FOREIGN KEY (`serviceID`) REFERENCES `services` (`serviceID`),
  ADD CONSTRAINT `services_usage_log_ibfk_2` FOREIGN KEY (`roomID`) REFERENCES `room` (`roomID`),
  ADD CONSTRAINT `services_usage_log_ibfk_3` FOREIGN KEY (`guestID`) REFERENCES `guest` (`guestID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
