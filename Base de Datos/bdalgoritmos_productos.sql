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
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productos` (
  `id_producto` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `precio` double NOT NULL,
  `id_categoria` int(11) NOT NULL,
  `id_provedor` int(11) NOT NULL,
  `stockMinimo` int(11) NOT NULL,
  `existencia` int(11) NOT NULL,
  PRIMARY KEY (`id_producto`,`id_categoria`),
  UNIQUE KEY `id_producto_UNIQUE` (`id_producto`),
  KEY `fk_productos_categoria_prod1_idx` (`id_categoria`),
  KEY `fk_productos_provedores1_idx` (`id_provedor`),
  CONSTRAINT `fk_productos_categoria_prod1` FOREIGN KEY (`id_categoria`) REFERENCES `categoria_prod` (`id_categoria`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_productos_provedores1` FOREIGN KEY (`id_provedor`) REFERENCES `provedores` (`id_provedor`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,'Javon en polvo',10000,7,11,8,29),(2,'pan tajado',3000,5,5,1,27),(3,'Atun',2500,9,12,10,29),(4,'Gaseosa pepsi',2000,2,2,20,31),(5,'Gaseosa cocacola',2300,2,1,20,41),(6,'Jamon de pierna',5000,1,13,9,22),(7,'Sardina',2600,9,12,5,24),(8,'Galletas de taco',3000,5,14,3,31),(9,'Galletas oreo',600,5,15,15,32),(10,'Leche litro',1500,10,6,6,26),(11,'cerveza',15000,6,16,10,26),(12,'ron',35000,6,16,10,27),(13,'aguardiente',40000,6,16,10,28),(14,'vino',25541,6,16,10,25),(15,'ron blanco',2236,6,16,10,30),(16,'tequila',2241,6,16,10,24),(17,'cocoanis',1500,6,16,10,34),(18,'old par',20000,6,16,10,27),(19,'manzana roja',2000,8,12,15,36),(20,'manzana verde',3000,8,12,15,33),(21,'bananas',1500,8,12,15,34),(22,'mango verde',3500,8,12,15,32),(23,'mango maduro',1000,8,12,15,32),(24,'fresas',2500,8,12,15,32),(25,'mora',5000,8,12,15,42),(26,'tomate de arbol',500,8,12,15,34),(27,'maracullada',2365,8,12,15,34),(28,'tamarindo',100,8,12,15,26),(29,'papas margaritas',3665,3,9,5,20),(30,'cheetos picantes',2200,3,9,5,17),(31,'quelos',1125,3,9,5,16),(32,'cheestres',112,3,9,5,19),(33,'yupis',2366,3,9,5,25),(34,'yupis picantes',2212,3,9,5,18),(35,'caro paos',1598,3,9,5,27),(36,'papas azules',2987,3,9,5,15);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
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
