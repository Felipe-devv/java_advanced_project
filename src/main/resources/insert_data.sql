-- MySQL dump 10.13  Distrib 8.4.2, for Linux (x86_64)
--
-- Host: localhost    Database: kebabownia
-- ------------------------------------------------------
-- Server version	8.4.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `Kebab`
--

LOCK TABLES `Kebab` WRITE;
/*!40000 ALTER TABLE `Kebab` DISABLE KEYS */;
INSERT INTO `Kebab` VALUES (1,'Pita','XL',23.99,'Kurczak','Pikantny',1),(2,'Falafel','Duzy',21,'Kotleciki z ciecierzycy','Mieszany',1);
/*!40000 ALTER TABLE `Kebab` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Paragon`
--

LOCK TABLES `Paragon` WRITE;
/*!40000 ALTER TABLE `Paragon` DISABLE KEYS */;
INSERT INTO `Paragon` VALUES (1,1,1,'Warszawa','16-124',29),(2,2,2,'Lublin','20-234',22.59);
/*!40000 ALTER TABLE `Paragon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Turek`
--

LOCK TABLES `Turek` WRITE;
/*!40000 ALTER TABLE `Turek` DISABLE KEYS */;
INSERT INTO `Turek` VALUES (1,'Ahmet','Ylmaz','Kucharz','25.50',1),(2,'Mehmet','Demir','Kelner','25.00',1);
/*!40000 ALTER TABLE `Turek` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-10 12:45:26
