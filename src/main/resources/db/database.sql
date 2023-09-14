-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: aerolinea_db
-- ------------------------------------------------------
-- Server version	8.0.28

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

CREATE DATABASE aerolinea_db;

--
-- Table structure for table `aerolinea`
--

DROP TABLE IF EXISTS `aerolinea`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aerolinea` (
  `id` int NOT NULL AUTO_INCREMENT,
  `aerolinea` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aerolinea`
--

LOCK TABLES `aerolinea` WRITE;
/*!40000 ALTER TABLE `aerolinea` DISABLE KEYS */;
INSERT INTO `aerolinea` VALUES (1,'American Airlines'),(2,'Delta Air Lines'),(3,'United Airlines'),(4,'Emirates'),(5,'Lufthansa'),(6,'British Airways'),(7,'Air France'),(8,'Qatar Airways'),(9,'Singapore Airlines'),(10,'Cathay Pacific'),(11,'ANA All Nippon Airways'),(12,'KLM Royal Dutch Airlines'),(13,'Virgin Atlantic'),(14,'Turkish Airlines'),(15,'Etihad Airways'),(16,'Southwest Airlines'),(17,'JetBlue Airways'),(18,'Ryanair'),(19,'Qantas'),(20,'Finnair'),(21,'Latam Airlines');
/*!40000 ALTER TABLE `aerolinea` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `boleto`
--

DROP TABLE IF EXISTS `boleto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `boleto` (
  `id` binary(16) NOT NULL,
  `fecha_compra` datetime(6) DEFAULT NULL,
  `usuario_id` binary(16) DEFAULT NULL,
  `vuelo_id` binary(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4sg3hgpy9cvfxwvtbj3mjrtaa` (`usuario_id`),
  KEY `FKaj3e0s0sfxl2y0o0fy9nnpr33` (`vuelo_id`),
  CONSTRAINT `FK4sg3hgpy9cvfxwvtbj3mjrtaa` FOREIGN KEY (`usuario_id`) REFERENCES `user` (`uuid`),
  CONSTRAINT `FKaj3e0s0sfxl2y0o0fy9nnpr33` FOREIGN KEY (`vuelo_id`) REFERENCES `vuelo` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `boleto`
--

LOCK TABLES `boleto` WRITE;
/*!40000 ALTER TABLE `boleto` DISABLE KEYS */;
INSERT INTO `boleto` VALUES (_binary 'S\�n\�\�FƔ[N�\�6`\�','2023-09-09 03:09:49.125000',_binary 'x*`�\�\�J\��ZW.Q\'',_binary '2	\�=\�O��\�\�\'\��'),(_binary '^\�ly�K\�5�N��W','2023-09-09 03:09:49.125000',_binary 'fx\"o~A9��\�hѤ',_binary '\���Yu�D읋���B\�2');
/*!40000 ALTER TABLE `boleto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ciudad`
--

DROP TABLE IF EXISTS `ciudad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ciudad` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ciudad_name` varchar(50) NOT NULL,
  `pais_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_atcm3qkl8vjojhllugihlxi7` (`ciudad_name`),
  KEY `FK479gu8gh6s75va0hxbne1kjlm` (`pais_id`),
  CONSTRAINT `FK479gu8gh6s75va0hxbne1kjlm` FOREIGN KEY (`pais_id`) REFERENCES `pais` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ciudad`
--

LOCK TABLES `ciudad` WRITE;
/*!40000 ALTER TABLE `ciudad` DISABLE KEYS */;
INSERT INTO `ciudad` VALUES (1,'Buenos Aires',1),(2,'Córdoba',1),(3,'Rosario',1),(4,'Rio de Janeiro',2),(5,'Sao Paulo',2),(6,'Salvador',2),(7,'Toronto',3),(8,'Vancouver',3),(9,'Montreal',3),(10,'Beijing',4),(11,'Shanghai',4),(12,'Guangzhou',4),(13,'Paris',5),(14,'Marseille',5),(15,'Lyon',5),(16,'Berlin',6),(17,'Munich',6),(18,'Hamburg',6),(19,'Mumbai',7),(20,'Delhi',7),(21,'Bangalore',7),(22,'Rome',8),(23,'Milan',8),(24,'Naples',8),(25,'Tokyo',9),(26,'Osaka',9),(27,'Kyoto',9),(28,'Mexico City',10),(29,'Guadalajara',10),(30,'Monterrey',10),(31,'Amsterdam',11),(32,'Rotterdam',11),(33,'Utrecht',11),(34,'Moscow',12),(35,'Saint Petersburg',12),(36,'Novosibirsk',12),(37,'Seoul',13),(38,'Busan',13),(39,'Incheon',13),(40,'Madrid',14),(41,'Barcelona',14),(42,'Valencia',14),(43,'Zurich',15),(44,'Geneva',15),(45,'Bern',15),(46,'London',16),(47,'Manchester',16),(48,'Edinburgh',16),(49,'New York City',17),(50,'Los Angeles',17),(51,'Chicago',17),(52,'Sydney',18),(53,'Melbourne',18),(54,'Brisbane',18),(55,'Auckland',19),(56,'Wellington',19),(57,'Christchurch',19),(58,'Johannesburg',20),(59,'Cape Town',20),(60,'Durban',20);
/*!40000 ALTER TABLE `ciudad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pais`
--

DROP TABLE IF EXISTS `pais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pais` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pais_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pais`
--

LOCK TABLES `pais` WRITE;
/*!40000 ALTER TABLE `pais` DISABLE KEYS */;
INSERT INTO `pais` VALUES (1,'Argentina'),(2,'Brazil'),(3,'Canada'),(4,'China'),(5,'France'),(6,'Germany'),(7,'India'),(8,'Italy'),(9,'Japan'),(10,'Mexico'),(11,'Netherlands'),(12,'Russia'),(13,'South Korea'),(14,'Spain'),(15,'Switzerland'),(16,'United Kingdom'),(17,'United States'),(18,'Australia'),(19,'New Zealand'),(20,'South Africa');
/*!40000 ALTER TABLE `pais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tarjeta_credito`
--

DROP TABLE IF EXISTS `tarjeta_credito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tarjeta_credito` (
  `id` binary(16) NOT NULL,
  `cvv` int DEFAULT NULL,
  `nombre_tarjeta` varchar(255) DEFAULT NULL,
  `numero_tarjeta` varchar(255) DEFAULT NULL,
  `usuario_id` binary(16) DEFAULT NULL,
  `anio_expiracion` int DEFAULT NULL,
  `mes_expiracion` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKe75a49njhm3snqm21dhohuxlr` (`usuario_id`),
  KEY `IDX7q5l0pbsuyvgomynffaw1h1wj` (`numero_tarjeta`),
  CONSTRAINT `FKe75a49njhm3snqm21dhohuxlr` FOREIGN KEY (`usuario_id`) REFERENCES `user` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarjeta_credito`
--

LOCK TABLES `tarjeta_credito` WRITE;
/*!40000 ALTER TABLE `tarjeta_credito` DISABLE KEYS */;
INSERT INTO `tarjeta_credito` VALUES (_binary '\�j\�J\�`\�8|/��',222,'PABLO GUERRA C.','4343556677889900',_binary 'x*`�\�\�J\��ZW.Q\'',11,2027),(_binary 'G�$��oEK�b�p[\�',123,'CREDIMAS CLASICA CHIP','1111222233334444',_binary 'x*`�\�\�J\��ZW.Q\'',11,2028);
/*!40000 ALTER TABLE `tarjeta_credito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `uuid` binary(16) NOT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `contrasenia` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `role` enum('ADMIN','USER') DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (_binary 'T�2=7�G9��c�=?v','Pérez','$2a$10$MQvnLhnRCEikjyyfkCrCj.GzeT7NYd6mszLq06X/QScG/uU.h6h8O','juan@example.com','Juan','123456789','USER'),(_binary 'fx\"o~A9��\�hѤ','Guerra','$2a$10$vATuVoQz86DaJdikiYP5z.qbLNHTTv6CSfzjqrD2XiDZ8cUnsOyUW','admin@gmail.com','Pablo','987654321','ADMIN'),(_binary 'x*`�\�\�J\��ZW.Q\'','Guerra','$2a$10$WuGwNYypeiULTDHglXXSY.Pl9nhKMhTI3wxGlVrgW/6E/Je5itDyO','pablo@gmail.com','Pablo','9579875524','USER'),(_binary '�\�;�)I\�� v]\��\�','González','$2a$10$unN85GWoyEEfY2yH/4ZWiOd/OTCLtAKMo4IEc1QQI59EhnL.9fER2','maria@example.com','María','9876543210','USER');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vuelo`
--

DROP TABLE IF EXISTS `vuelo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vuelo` (
  `uuid` binary(16) NOT NULL,
  `fecha_ida` datetime(6) DEFAULT NULL,
  `fecha_vuelta` datetime(6) DEFAULT NULL,
  `precio` float DEFAULT NULL,
  `aerolinea_id` int DEFAULT NULL,
  `ciudad_destino_id` int DEFAULT NULL,
  `ciudad_origen_id` int DEFAULT NULL,
  `cantidad_pasajes` int DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `FK5se3mkqwwtymed3najmh8bmej` (`aerolinea_id`),
  KEY `FK2r00di8p9v30s6l7fu5p3vmmg` (`ciudad_destino_id`),
  KEY `FK1edhjeq3v0e7bdvy1jwjube8n` (`ciudad_origen_id`),
  CONSTRAINT `FK1edhjeq3v0e7bdvy1jwjube8n` FOREIGN KEY (`ciudad_origen_id`) REFERENCES `ciudad` (`id`),
  CONSTRAINT `FK2r00di8p9v30s6l7fu5p3vmmg` FOREIGN KEY (`ciudad_destino_id`) REFERENCES `ciudad` (`id`),
  CONSTRAINT `FK5se3mkqwwtymed3najmh8bmej` FOREIGN KEY (`aerolinea_id`) REFERENCES `aerolinea` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vuelo`
--

LOCK TABLES `vuelo` WRITE;
/*!40000 ALTER TABLE `vuelo` DISABLE KEYS */;
INSERT INTO `vuelo` VALUES (_binary '2	\�=\�O��\�\�\'\��','2023-09-10 09:45:00.000000','2023-09-20 17:45:00.000000',600.9,4,5,4,80),(_binary 'T�2=7�G9��c�=?v','2023-09-15 08:00:00.000000','2023-09-25 15:00:00.000000',500.5,1,2,1,150),(_binary 'iS:7��N ��~�yP\�','2023-09-14 08:20:00.000000','2023-09-30 08:00:00.000000',500,15,37,4,120),(_binary 'l\�\�\�ǐO��\�\�~e^}\�','2023-09-10 09:45:00.000000','2023-09-20 17:45:00.000000',600.9,1,11,10,90),(_binary '�\�œ~�Ke�2R��\�','2023-10-01 12:00:00.000000','2023-10-15 20:00:00.000000',800.2,3,4,3,120),(_binary '�o(�B:�K�{2\n\�','2023-09-14 08:30:00.000000','2023-09-27 17:15:00.000000',380.45,10,17,16,130),(_binary '��ۘ\�Oz������]\n','2023-09-17 11:00:00.000000','2023-09-29 19:30:00.000000',620.6,11,19,18,160),(_binary '�\�T?\�Iن�g,�k\�','2023-09-25 10:30:00.000000','2023-10-12 18:00:00.000000',600.5,8,13,12,150),(_binary '�\�>)&�C��m��i\�','2023-09-14 14:30:00.000000','2023-09-28 23:15:00.000000',680.4,15,26,13,200),(_binary '�\�����L����Z��.','2023-09-12 07:30:00.000000','2023-09-28 14:15:00.000000',700.3,5,7,6,180),(_binary '\�<�|\�K$��̟\Z�}]','2023-09-22 09:15:00.000000','2023-10-08 16:45:00.000000',450.8,7,11,10,100),(_binary '\�\�\�/F��Ǜb�\�	','2023-09-28 12:45:00.000000','2023-10-11 21:00:00.000000',590.5,14,25,24,90),(_binary '\�[\"\nJhGߘ�[p��M','2023-09-30 11:45:00.000000','2023-10-13 20:00:00.000000',520.9,19,9,8,140),(_binary '\�\�K��;H.K\�\�~','2023-10-04 09:00:00.000000','2023-10-17 17:30:00.000000',470.75,20,11,10,170),(_binary '\�j�\nH�G$���j�\�@�','2023-09-18 10:15:00.000000','2023-10-01 18:45:00.000000',550.6,16,2,3,150),(_binary '\�\�vlS\�L�mÌ\���\�','2023-09-20 10:30:00.000000','2023-09-30 18:30:00.000000',450.75,2,3,2,200),(_binary '\�複�C\�Pm#��W\�','2023-09-30 12:45:00.000000','2023-10-18 21:00:00.000000',750.75,9,15,14,180),(_binary 'ᰜ|h J!�Y�H\�','2023-09-20 09:15:00.000000','2023-10-02 16:45:00.000000',510.7,12,21,20,140),(_binary '\�\�z�;�FׯDI�T*o','2023-09-22 09:30:00.000000','2023-10-05 18:15:00.000000',450.7,17,1,2,180),(_binary '\��5��A.�\�Q\�cY','2023-09-24 10:30:00.000000','2023-10-07 18:00:00.000000',420.3,13,23,22,110),(_binary '\�M���YC\Z���\��+=','2023-09-26 12:15:00.000000','2023-10-09 21:30:00.000000',610.8,18,7,6,220),(_binary '\���Yu�D읋���B\�2','2023-09-18 11:00:00.000000','2023-10-02 19:30:00.000000',550.25,6,9,8,220),(_binary '\�\�0\�\�DɌ߲	�\�}�','2023-09-10 09:45:00.000000','2023-09-20 17:45:00.000000',600,1,20,11,100);
/*!40000 ALTER TABLE `vuelo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-13 13:52:37
