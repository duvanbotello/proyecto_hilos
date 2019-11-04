-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: bdalgoritmos
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `empleados`
--

DROP TABLE IF EXISTS `empleados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `empleados` (
  `num_empleado` int(11) NOT NULL AUTO_INCREMENT,
  `doc_empleado` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `telefono` int(11) DEFAULT NULL,
  `edad` int(11) NOT NULL,
  `tipo_empleado` int(11) NOT NULL,
  PRIMARY KEY (`doc_empleado`),
  UNIQUE KEY `doc_empleado_UNIQUE` (`doc_empleado`),
  UNIQUE KEY `num_empleado_UNIQUE` (`num_empleado`),
  KEY `fk_empleados_tipo_empleado1_idx` (`tipo_empleado`),
  CONSTRAINT `fk_empleados_tipo_empleado1` FOREIGN KEY (`tipo_empleado`) REFERENCES `tipo_empleado` (`id_tipoEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleados`
--

LOCK TABLES `empleados` WRITE;
/*!40000 ALTER TABLE `empleados` DISABLE KEYS */;
INSERT INTO `empleados` VALUES (1,123,'Cheo','Botello',3233,89,2),(2,992348,'Pedro','Ariza',323424,120,2),(3,12748374,'Papa','Arenales',832333,35,2),(4,31239412,'Jose','Chacho',423432,89,2),(5,78723948,'Maria','Ufemia',423423,64,2),(6,80239238,'AnaLubia','Lubianis',397232,37,2),(7,95838345,'Jhoel','Villamizar',43422,43,2),(8,656556568,'Chaden','galvis',655665,212,2),(9,912312412,'Camilo','Garcia',808989,23,2),(10,923184823,'Darwin','dasddasd',645456,56,2),(11,1090499082,'duvan','botello',663237,21,2);
/*!40000 ALTER TABLE `empleados` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-15 21:18:11
