-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 22, 2018 at 06:45 PM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 7.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `nsbm`
--

-- --------------------------------------------------------

--
-- Table structure for table `bachelorcourse`
--

CREATE TABLE `bachelorcourse` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `duration` int(1) NOT NULL,
  `credits` int(2) NOT NULL,
  `fac_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bachelorcourse`
--

INSERT INTO `bachelorcourse` (`id`, `name`, `duration`, `credits`, `fac_id`) VALUES
(1, 'Bachelor of Computer Science', 3, 30, 2),
(2, 'Bachelor of Business Studies', 3, 25, 3),
(3, 'Bachelor of Auto Mobile Engineering', 3, 20, 1);

-- --------------------------------------------------------

--
-- Table structure for table `bachelorsub`
--

CREATE TABLE `bachelorsub` (
  `bachCourse_id` int(11) NOT NULL,
  `sub_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bachelorsub`
--

INSERT INTO `bachelorsub` (`bachCourse_id`, `sub_id`) VALUES
(1, 1),
(1, 3),
(3, 2),
(3, 3),
(3, 6);

-- --------------------------------------------------------

--
-- Table structure for table `faculty`
--

CREATE TABLE `faculty` (
  `id` int(2) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `faculty`
--

INSERT INTO `faculty` (`id`, `name`) VALUES
(1, 'School of Engineering'),
(2, 'School of Computing'),
(3, 'School of Business');

-- --------------------------------------------------------

--
-- Table structure for table `instructer`
--

CREATE TABLE `instructer` (
  `id` int(11) NOT NULL,
  `fname` varchar(50) NOT NULL,
  `mname` varchar(50) NOT NULL,
  `lname` varchar(50) NOT NULL,
  `gender` char(1) NOT NULL,
  `dob` date NOT NULL,
  `home_no` varchar(20) NOT NULL,
  `add_1` varchar(50) NOT NULL,
  `add_2` varchar(50) NOT NULL,
  `add_3` varchar(50) NOT NULL,
  `t_no` char(10) NOT NULL,
  `email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `instructer`
--

INSERT INTO `instructer` (`id`, `fname`, `mname`, `lname`, `gender`, `dob`, `home_no`, `add_1`, `add_2`, `add_3`, `t_no`, `email`) VALUES
(1, 'Kasun', '', 'Pathirana', 'M', '1987-09-02', '120', 'Galle Road', '', 'Moratuwa', '071123457', 'kasun@123.com');

-- --------------------------------------------------------

--
-- Table structure for table `lab`
--

CREATE TABLE `lab` (
  `id` int(10) NOT NULL,
  `capacity` tinyint(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lab`
--

INSERT INTO `lab` (`id`, `capacity`) VALUES
(1, 30);

-- --------------------------------------------------------

--
-- Table structure for table `labsession`
--

CREATE TABLE `labsession` (
  `ins_id` int(10) NOT NULL,
  `sub_id` int(10) NOT NULL,
  `lab_id` int(10) NOT NULL,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `labsession`
--

INSERT INTO `labsession` (`ins_id`, `sub_id`, `lab_id`, `start_time`, `end_time`) VALUES
(1, 2, 1, '22:54:00', '23:54:00'),
(1, 3, 1, '12:42:00', '13:42:00');

-- --------------------------------------------------------

--
-- Table structure for table `lecturer`
--

CREATE TABLE `lecturer` (
  `id` int(10) NOT NULL,
  `fname` varchar(20) NOT NULL,
  `mname` varchar(20) NOT NULL,
  `lname` varchar(20) NOT NULL,
  `gender` char(1) NOT NULL,
  `dob` date NOT NULL,
  `home_no` varchar(10) NOT NULL,
  `add_1` varchar(50) NOT NULL,
  `add_2` varchar(50) NOT NULL,
  `add_3` varchar(50) NOT NULL,
  `t_no` char(10) NOT NULL,
  `email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lecturer`
--

INSERT INTO `lecturer` (`id`, `fname`, `mname`, `lname`, `gender`, `dob`, `home_no`, `add_1`, `add_2`, `add_3`, `t_no`, `email`) VALUES
(1, 'David', '', 'Perera', 'F', '1975-05-02', '32', 'Horton Place', '', 'Nuwara Eliya', '0718523745', 'david@gmail.com'),
(3, 'Kasun', 'Priyashan', '', 'M', '2018-08-01', '12', 'Ward Place', '', 'Colombo 2', '0759846321', 'kasun@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `mastercourse`
--

CREATE TABLE `mastercourse` (
  `id` int(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `credits` int(2) NOT NULL,
  `fac_id` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mastercourse`
--

INSERT INTO `mastercourse` (`id`, `name`, `credits`, `fac_id`) VALUES
(1, 'Master of Information Technology', 24, 2),
(2, 'Master of Science', 21, 1),
(3, 'Master of Business Management', 35, 3);

-- --------------------------------------------------------

--
-- Table structure for table `mastersub`
--

CREATE TABLE `mastersub` (
  `masterCourse_id` int(11) NOT NULL,
  `sub_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mastersub`
--

INSERT INTO `mastersub` (`masterCourse_id`, `sub_id`) VALUES
(1, 2),
(2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `postgraduate`
--

CREATE TABLE `postgraduate` (
  `id` int(10) NOT NULL,
  `fname` varchar(50) NOT NULL,
  `mname` varchar(50) DEFAULT NULL,
  `lname` varchar(50) NOT NULL,
  `gender` char(1) NOT NULL,
  `dob` date NOT NULL,
  `home_no` varchar(20) DEFAULT NULL,
  `add_1` varchar(50) NOT NULL,
  `add_2` varchar(50) NOT NULL,
  `add_3` varchar(50) NOT NULL,
  `t_no` int(10) NOT NULL,
  `email` varchar(50) NOT NULL,
  `type` varchar(50) NOT NULL,
  `institute` varchar(50) NOT NULL,
  `grad_year` int(4) NOT NULL,
  `r_year` int(4) NOT NULL,
  `r_semester` int(1) NOT NULL,
  `fac_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `postgraduate`
--

INSERT INTO `postgraduate` (`id`, `fname`, `mname`, `lname`, `gender`, `dob`, `home_no`, `add_1`, `add_2`, `add_3`, `t_no`, `email`, `type`, `institute`, `grad_year`, `r_year`, `r_semester`, `fac_id`, `course_id`) VALUES
(3, 'Gamith', '', 'Ishara', 'M', '2018-06-13', '20/D', 'Temple Road', '', 'Katukurunda', 0769875321, 'gishara@gmail.com', 'hfgh', 'ESOFT Metro Campus', 2001, 2018, 1, 2, 1),
(4, 'gfhdf', 'hfgdhgd', 'hdfhgdg', 'F', '2018-08-15', 'dfhdgd', 'ghdghd', 'hdhgdsfh', 'dfhgdh', 1111, '111', '111', '111', 111, 2018, 1, 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `postresult`
--

CREATE TABLE `postresult` (
  `post_id` int(11) NOT NULL,
  `sub_id` int(11) NOT NULL,
  `result` char(2) NOT NULL,
  `payment` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `subject`
--

CREATE TABLE `subject` (
  `id` int(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `credits` int(2) NOT NULL,
  `fee` int(11) NOT NULL,
  `lec_id` int(10) DEFAULT NULL,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `subject`
--

INSERT INTO `subject` (`id`, `name`, `credits`, `fee`, `lec_id`, `start_time`, `end_time`) VALUES
(1, 'Programming', 3, 3000, 1, '09:00:00', '11:00:00'),
(2, 'Mathematical Methods', 2, 4000, 1, '00:00:00', '00:00:00'),
(3, 'Pure Maths', 2, 4500, 1, '12:00:00', '14:00:00'),
(6, 'Applied Maths', 2, 5600, 3, '12:00:00', '13:00:00'),
(12, 'Management Process', 3, 4500, 3, '08:00:00', '10:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `undergraduate`
--

CREATE TABLE `undergraduate` (
  `id` int(10) NOT NULL,
  `fname` varchar(50) NOT NULL,
  `mname` varchar(50) DEFAULT NULL,
  `lname` varchar(50) NOT NULL,
  `gender` char(1) NOT NULL,
  `dob` date NOT NULL,
  `home_no` varchar(20) DEFAULT NULL,
  `add_1` varchar(50) NOT NULL,
  `add_2` varchar(50) NOT NULL,
  `add_3` varchar(50) NOT NULL,
  `t_no` char(10) NOT NULL,
  `email` varchar(100) NOT NULL,
  `island_rank` smallint(10) NOT NULL,
  `district_rank` smallint(10) NOT NULL,
  `sub1` char(1) NOT NULL,
  `sub2` char(1) NOT NULL,
  `sub3` char(1) NOT NULL,
  `r_year` int(4) NOT NULL,
  `r_semester` int(1) NOT NULL,
  `fac_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `undergraduate`
--

INSERT INTO `undergraduate` (`id`, `fname`, `mname`, `lname`, `gender`, `dob`, `home_no`, `add_1`, `add_2`, `add_3`, `t_no`, `email`, `island_rank`, `district_rank`, `sub1`, `sub2`, `sub3`, `r_year`, `r_semester`, `fac_id`, `course_id`) VALUES
(18, 'Hashan', 'Tilakaratne', '', 'M', '2018-08-22', '12', 'Bay Brooke Place', '', 'Colombo 3', '0710265479', 'hashantil@gmail.com', 5210, 241, 'C', 'B', 'B', 2018, 2, 1, 3),
(19, 'Reno', '', 'Silva', 'M', '1988-08-02', '5/A', 'ABC Avenue', 'Bokandara', 'Piliyandala', '0785614951', 'rsilva@gmail.com', 987, 112, 'A', 'A', 'S', 2018, 2, 3, 2);

-- --------------------------------------------------------

--
-- Table structure for table `undresult`
--

CREATE TABLE `undresult` (
  `und_id` int(11) NOT NULL,
  `sub_id` int(11) NOT NULL,
  `result` char(2) DEFAULT NULL,
  `payment` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Stand-in structure for view `und_sub_details`
-- (See below for the actual view)
--
CREATE TABLE `und_sub_details` (
`und_id` int(11)
,`sub_id` int(11)
,`name` varchar(50)
,`credits` int(2)
,`fee` int(11)
,`result` char(2)
);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`) VALUES
(1, 'admin', 'admin');

-- --------------------------------------------------------

--
-- Structure for view `und_sub_details`
--
DROP TABLE IF EXISTS `und_sub_details`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `und_sub_details`  AS  select `undresult`.`und_id` AS `und_id`,`undresult`.`sub_id` AS `sub_id`,`subject`.`name` AS `name`,`subject`.`credits` AS `credits`,`subject`.`fee` AS `fee`,`undresult`.`result` AS `result` from (`subject` join `undresult` on((`subject`.`id` = `undresult`.`sub_id`))) ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bachelorcourse`
--
ALTER TABLE `bachelorcourse`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_Faculty_Bachelor` (`fac_id`);

--
-- Indexes for table `bachelorsub`
--
ALTER TABLE `bachelorsub`
  ADD PRIMARY KEY (`bachCourse_id`,`sub_id`),
  ADD KEY `FK_BachSub_Sub` (`sub_id`);

--
-- Indexes for table `faculty`
--
ALTER TABLE `faculty`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `instructer`
--
ALTER TABLE `instructer`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `t_no` (`t_no`);

--
-- Indexes for table `lab`
--
ALTER TABLE `lab`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `labsession`
--
ALTER TABLE `labsession`
  ADD PRIMARY KEY (`ins_id`,`sub_id`,`lab_id`),
  ADD KEY `FK_LabSession_ins` (`ins_id`),
  ADD KEY `FK_LabSession_lab` (`lab_id`),
  ADD KEY `FK_LabSession_sub` (`sub_id`);

--
-- Indexes for table `lecturer`
--
ALTER TABLE `lecturer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `mastercourse`
--
ALTER TABLE `mastercourse`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_Faculty_Master` (`fac_id`);

--
-- Indexes for table `mastersub`
--
ALTER TABLE `mastersub`
  ADD PRIMARY KEY (`masterCourse_id`,`sub_id`),
  ADD KEY `FK_MasterSub_Sub` (`sub_id`);

--
-- Indexes for table `postgraduate`
--
ALTER TABLE `postgraduate`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_Post_fac` (`fac_id`),
  ADD KEY `FK_Post_Course` (`course_id`);

--
-- Indexes for table `postresult`
--
ALTER TABLE `postresult`
  ADD PRIMARY KEY (`post_id`,`sub_id`),
  ADD KEY `FK_PostResult_sub` (`sub_id`);

--
-- Indexes for table `subject`
--
ALTER TABLE `subject`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_Subject_Lec` (`lec_id`);

--
-- Indexes for table `undergraduate`
--
ALTER TABLE `undergraduate`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_UND_Fac` (`fac_id`),
  ADD KEY `FK_UND_Course` (`course_id`);

--
-- Indexes for table `undresult`
--
ALTER TABLE `undresult`
  ADD PRIMARY KEY (`und_id`,`sub_id`),
  ADD KEY `FK_UndResult_sub` (`sub_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bachelorcourse`
--
ALTER TABLE `bachelorcourse`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `faculty`
--
ALTER TABLE `faculty`
  MODIFY `id` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `instructer`
--
ALTER TABLE `instructer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `lab`
--
ALTER TABLE `lab`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `lecturer`
--
ALTER TABLE `lecturer`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `mastercourse`
--
ALTER TABLE `mastercourse`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `postgraduate`
--
ALTER TABLE `postgraduate`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `subject`
--
ALTER TABLE `subject`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `undergraduate`
--
ALTER TABLE `undergraduate`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `bachelorcourse`
--
ALTER TABLE `bachelorcourse`
  ADD CONSTRAINT `FK_Faculty_Bachelor` FOREIGN KEY (`fac_id`) REFERENCES `faculty` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `bachelorsub`
--
ALTER TABLE `bachelorsub`
  ADD CONSTRAINT `FK_BachSub_BachelorCourseId` FOREIGN KEY (`bachCourse_id`) REFERENCES `bachelorcourse` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_BachSub_Sub` FOREIGN KEY (`sub_id`) REFERENCES `subject` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `labsession`
--
ALTER TABLE `labsession`
  ADD CONSTRAINT `FK_LabSession_ins` FOREIGN KEY (`ins_id`) REFERENCES `instructer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_LabSession_lab` FOREIGN KEY (`lab_id`) REFERENCES `lab` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_LabSession_sub` FOREIGN KEY (`sub_id`) REFERENCES `subject` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `mastercourse`
--
ALTER TABLE `mastercourse`
  ADD CONSTRAINT `FK_Faculty_Master` FOREIGN KEY (`fac_id`) REFERENCES `faculty` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `mastersub`
--
ALTER TABLE `mastersub`
  ADD CONSTRAINT `FK_MasterSub_MasterCourseId` FOREIGN KEY (`masterCourse_id`) REFERENCES `mastercourse` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_MasterSub_Sub` FOREIGN KEY (`sub_id`) REFERENCES `subject` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `postgraduate`
--
ALTER TABLE `postgraduate`
  ADD CONSTRAINT `FK_Post_Course` FOREIGN KEY (`course_id`) REFERENCES `mastercourse` (`id`),
  ADD CONSTRAINT `FK_Post_fac` FOREIGN KEY (`fac_id`) REFERENCES `faculty` (`id`);

--
-- Constraints for table `postresult`
--
ALTER TABLE `postresult`
  ADD CONSTRAINT `FK_PostResult_post` FOREIGN KEY (`post_id`) REFERENCES `postgraduate` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_PostResult_sub` FOREIGN KEY (`sub_id`) REFERENCES `subject` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `subject`
--
ALTER TABLE `subject`
  ADD CONSTRAINT `FK_sub_lec` FOREIGN KEY (`lec_id`) REFERENCES `lecturer` (`id`) ON DELETE SET NULL ON UPDATE SET NULL;

--
-- Constraints for table `undergraduate`
--
ALTER TABLE `undergraduate`
  ADD CONSTRAINT `FK_UND_Course` FOREIGN KEY (`course_id`) REFERENCES `bachelorcourse` (`id`),
  ADD CONSTRAINT `FK_UND_Fac` FOREIGN KEY (`fac_id`) REFERENCES `faculty` (`id`);

--
-- Constraints for table `undresult`
--
ALTER TABLE `undresult`
  ADD CONSTRAINT `FK_UndResult_Und` FOREIGN KEY (`und_id`) REFERENCES `undergraduate` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_UndResult_sub` FOREIGN KEY (`sub_id`) REFERENCES `subject` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
