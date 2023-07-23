CREATE TABLE if not exists `person` (
  `id` bigint NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) NOT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `person` WRITE;
UNLOCK TABLES;
