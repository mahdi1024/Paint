CREATE DATABASE  IF NOT EXISTS `paint` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `paint`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: paint
-- ------------------------------------------------------
-- Server version	5.7.14

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `login_tb`
--

DROP TABLE IF EXISTS `login_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login_tb` (
  `idlogin_tb` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`idlogin_tb`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `idlogin_tb_UNIQUE` (`idlogin_tb`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_tb`
--

LOCK TABLES `login_tb` WRITE;
/*!40000 ALTER TABLE `login_tb` DISABLE KEYS */;
INSERT INTO `login_tb` VALUES (1,'mahdi','8f8a33be7f11f4c5aa922aee2aac44b4'),(2,'nafis','0facfd564cf1d4e089b17d5e82f96393'),(6,'zahra','08d5231c6703b3fa998ba21d85242fe6');
/*!40000 ALTER TABLE `login_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shape_tb`
--

DROP TABLE IF EXISTS `shape_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shape_tb` (
  `shape_ID` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  `color` varchar(45) NOT NULL,
  `startX` int(11) NOT NULL,
  `startY` int(11) NOT NULL,
  `curX` int(11) NOT NULL,
  `curY` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  PRIMARY KEY (`shape_ID`),
  KEY `userID_idx` (`userID`),
  CONSTRAINT `userID` FOREIGN KEY (`userID`) REFERENCES `login_tb` (`idlogin_tb`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shape_tb`
--

LOCK TABLES `shape_tb` WRITE;
/*!40000 ALTER TABLE `shape_tb` DISABLE KEYS */;
INSERT INTO `shape_tb` VALUES (2,'rectangle','red',279,122,405,344,1),(3,'rectangle','blue',130,256,379,374,1),(9,'line','green',266,443,449,351,1),(10,'line','blue',71,248,286,353,1),(96,'rectangle','black',248,98,383,237,1),(97,'circle','black',202,95,381,303,1),(98,'circle','green',251,81,380,254,1),(99,'line','red',117,215,267,350,1),(100,'circle','red',284,118,428,263,2),(101,'rectangle','red',17,17,147,175,1),(102,'rectangle','blue',175,191,407,378,2),(103,'line','black',150,47,353,127,2),(104,'line','green',58,213,162,487,2),(105,'rectangle','red',58,227,188,340,1),(106,'circle','blue',54,50,174,187,2),(107,'line','red',250,315,450,310,2),(108,'rectangle','black',262,334,442,464,2),(112,'circle','red',206,29,356,173,1),(113,'rectangle','black',58,399,210,490,1),(114,'rectangle','green',194,5,466,151,1),(115,'rectangle','black',213,70,437,361,6),(116,'rectangle','black',147,189,257,454,6),(117,'circle','green',55,43,349,223,6),(118,'circle','green',131,239,462,459,6),(119,'circle','blue',322,103,418,333,6),(120,'circle','red',47,191,379,331,6),(121,'circle','red',72,355,422,488,6),(122,'circle','red',105,25,332,169,6),(123,'line','red',38,381,363,63,6),(124,'line','green',17,183,349,373,6),(125,'circle','blue',324,277,480,424,1),(126,'rectangle','black',51,67,207,283,1),(127,'circle','black',310,65,435,246,1),(128,'circle','red',273,206,454,423,1),(129,'line','red',92,195,362,219,1);
/*!40000 ALTER TABLE `shape_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'paint'
--

--
-- Dumping routines for database 'paint'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-02-15 15:56:08
