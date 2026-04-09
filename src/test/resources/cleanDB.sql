-- MySQL dump 10.13
-- Host: 127.0.0.1    Database: travel_journal
-- ------------------------------------------------------

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
-- 1. Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `first_name` varchar(50) DEFAULT NULL,
                        `last_name` varchar(50) DEFAULT NULL,
                        `user_name` varchar(100) DEFAULT NULL,
                        `password` varchar(100) DEFAULT NULL,
                        `cognito_id` varchar(100) DEFAULT NULL, -- Cognito 'sub' 매핑용 컬럼
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `users_user_name_uindex` (`user_name`),
                        UNIQUE KEY `users_cognito_id_uindex` (`cognito_id`) -- Cognito ID 중복 방지 및 검색 최적화
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 2. Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
CREATE TABLE `location` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `name` varchar(255) NOT NULL,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `name_unique` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 3. Table structure for table `journal`
--

DROP TABLE IF EXISTS `journal`;
CREATE TABLE `journal` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `user_id` int NOT NULL,
                           `location_id` int NOT NULL,
                           `title` varchar(255) DEFAULT NULL,
                           `content` text,
                           `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                           `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           `weather` varchar(255) DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           KEY `user_id` (`user_id`),
                           KEY `location_id` (`location_id`),
                           CONSTRAINT `journal_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
                           CONSTRAINT `journal_location_id_fk` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Data Dumping
--

-- Users (초기 데이터는 cognito_id가 NULL일 수 있음)
LOCK TABLES `user` WRITE;
INSERT INTO `user` (`id`, `first_name`, `last_name`, `user_name`, `password`, `cognito_id`) VALUES
                                                                                                (1,'Admin','Admin','admin','admin', 'admin-dummy-id'),
                                                                                                (2,'Joe','Coyne','jcoyne','supersecret1', NULL),
                                                                                                (3,'Fred','Hensen','fhensen','supersecret2', NULL),
                                                                                                (4,'Barney','Curry','bcurry','supersecret3', NULL),
                                                                                                (5,'Karen','Mack','kmack','supersecret4', NULL),
                                                                                                (6,'Dianne','Klein','dklein','supersecret5', NULL),
                                                                                                (7,'Dawn','Tillman','dtillman','supersecret6', NULL);
UNLOCK TABLES;

-- Locations
LOCK TABLES `location` WRITE;
INSERT INTO `location` (`id`, `name`) VALUES (1,'Paris'),(2,'London'),(3,'Amsterdam'),(4,'Madison');
UNLOCK TABLES;

-- Journals
LOCK TABLES `journal` WRITE;
INSERT INTO `journal` (`id`, `user_id`, `location_id`, `title`, `content`, `created_at`, `updated_at`, `weather`) VALUES
                                                                                                                      (1,1,1,'Administrator 1','','2026-02-05 00:25:57','2026-02-05 00:25:57','Cold'),
                                                                                                                      (3,1,2,'Administrator 2','null test','2026-02-05 00:27:21','2026-02-05 00:27:21','Cold'),
                                                                                                                      (4,2,3,'Journal Test 1','null test','2026-02-05 00:28:01','2026-02-05 00:28:01','Cold'),
                                                                                                                      (5,3,4,'Journal Test 2','','2026-02-04 00:00:00','2026-02-04 00:00:00','Cold');
UNLOCK TABLES;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;