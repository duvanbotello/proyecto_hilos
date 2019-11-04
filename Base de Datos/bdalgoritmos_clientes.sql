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
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clientes` (
  `id_clientes` int(11) NOT NULL AUTO_INCREMENT,
  `doc_cliente` int(11) DEFAULT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `telefono` int(11) DEFAULT NULL,
  `edad` int(11) NOT NULL,
  PRIMARY KEY (`id_clientes`),
  UNIQUE KEY `id_clientes_UNIQUE` (`id_clientes`),
  UNIQUE KEY `doc_cliente_UNIQUE` (`doc_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,123456,'Camilo','Garcia',3455356,22),(2,1231241,'Ricardo','Becerra',998234,15),(3,1273821,'Sara','Camilo',908323,25),(4,3403949,'Andres','Chaparro',92323,27),(5,7346884,'Dayana','Contrareas',123343,37),(6,8323872,'Dexcy','Ramirez',232423,30),(7,9090909,'DrEmpanadas','Ingeros',24235,11),(8,9483472,'Natalia','calsimo',78345345,29),(9,34127978,'Daniel','chacon',23455676,37),(10,77787631,'Karen','Lamentos',9832412,21),(11,85741234,'Cheo','perez',4565647,10),(12,86749204,'yeison','ddddd',55453,58),(13,88123214,'Cleyder','Ortiz',224232,20),(14,91039123,'Katerine','Sarmiento',4353245,45),(15,99127481,'Leidy','Carreño',983273,25),(16,101928347,'hosaw','cilantro',423467,43),(17,109049932,'Carlos','Caceres',5760677,28),(18,128371873,'Guelliermo','santiago',456456,20),(19,234137878,'Pedro','avendaño',34576,34),(20,765438686,'Sebastian','ariza',23466345,36),(21,1264759444,'chiqui','yuyuju',5345436,60),(22,99887,'claudia','lopez',6565,25),(23,5556987,'ivan','duque',65658,85),(24,978845,'Fajardo','verde',322332,54),(25,5966478,'petrosky','morales',3211585,36),(26,897777,'juan','santo',5454,26),(27,9898788,'deysi','nuñez',656556,49),(28,45542659,'jeison','ascanio',56585,100),(29,5115499,'neil','ortiz',25988,100),(30,9999988,'andri','dsmil',15454,100),(31,8888774,'alfredo','gonzales',88898,100),(32,22233664,'lender','beltran',885454,100),(33,55566998,'david','caicedo',15154,100),(34,22145858,'maria','caceres',54988,100),(35,23366974,'angelica','sepulvedad',265655,100),(36,22123665,'cindi','botello',326565,100),(37,99878522,'mayerlin','clasada',2555,100),(38,3364125,'marlos','koaski',989889,255),(39,3232332,'wilian','repre',545454,58),(40,9898747,'leidy','acuña',45454,22),(41,95944452,'karlos','parada',656587,36),(42,5465454,'jorefin','carrasca',6236565,56),(43,656565651,'samuel','perez',5445545,28),(44,4545454,'kacu','villamizar',54544,98),(45,4418892,'fito ','le profe',212121,69);
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
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
