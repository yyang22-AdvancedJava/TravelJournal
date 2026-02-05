-- MySQL dump 10.13  Distrib 8.0.45, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: travel_journal
-- ------------------------------------------------------
-- Server version	8.0.45-0ubuntu0.22.04.1

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
-- Table structure for table `journal`
--

DROP TABLE IF EXISTS `journal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `journal` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `user_id` int NOT NULL,
                           `title` varchar(255) DEFAULT NULL,
                           `content` text,
                           `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                           `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                           `location` varchar(255) DEFAULT NULL,
                           `weather` varchar(255) DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           KEY `user_id` (`user_id`),
                           CONSTRAINT `journal_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `journal`
--

LOCK TABLES `journal` WRITE;
/*!40000 ALTER TABLE `journal` DISABLE KEYS */;
INSERT INTO `journal` VALUES (1,1,'Administrator 1','','2026-02-05 00:25:57','2026-02-05 00:25:57','Paris','Cold'),(3,1,'Administrator 2','null test','2026-02-05 00:27:21','2026-02-05 00:27:21','London','Cold'),(4,2,'Journal Test 1','null test','2026-02-05 00:28:01','2026-02-05 00:28:01','Amsterdam','Cold'),(5,3,'Journal Test 2','','2026-02-04 00:00:00','2026-02-04 00:00:00','Madison','Cold');
/*!40000 ALTER TABLE `journal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `first_name` varchar(25) DEFAULT NULL,
                        `last_name` varchar(30) DEFAULT NULL,
                        `user_name` varchar(15) DEFAULT NULL,
                        `password` varchar(30) DEFAULT NULL,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `users_user_name_uindex` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Admin','Admin','admin','admin'),(2,'Joe','Coyne','jcoyne','supersecret1'),(3,'Fred','Hensen','fhensen','supersecret2'),(4,'Barney','Curry','bcurry','supersecret3'),(5,'Karen','Mack','kmack','supersecret4'),(6,'Dianne','Klein','dklein','supersecret5'),(7,'Dawn','Tillman','dtillman','supersecret6');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-02-05 13:02:19
