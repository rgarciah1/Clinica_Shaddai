-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: dbclinica
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cita`
--

DROP TABLE IF EXISTS `cita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cita` (
  `idCita` int NOT NULL AUTO_INCREMENT,
  `idPaciente` int NOT NULL,
  `idDoctor` int NOT NULL,
  `titulo` varchar(50) NOT NULL,
  `fechaCita` datetime NOT NULL,
  `idEstado` int NOT NULL,
  `idUsuario` int NOT NULL,
  PRIMARY KEY (`idCita`),
  KEY `fk_cita_usuario1_idx` (`idUsuario`),
  KEY `fk_cita_usuario2` (`idPaciente`),
  KEY `fk_cita_usuario3` (`idDoctor`),
  CONSTRAINT `fk_cita_usuario1` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`),
  CONSTRAINT `fk_cita_usuario2` FOREIGN KEY (`idPaciente`) REFERENCES `usuario` (`idUsuario`),
  CONSTRAINT `fk_cita_usuario3` FOREIGN KEY (`idDoctor`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cita`
--

LOCK TABLES `cita` WRITE;
/*!40000 ALTER TABLE `cita` DISABLE KEYS */;
INSERT INTO `cita` VALUES (1,1,1,'Inicial','2021-10-30 10:00:00',1,1),(2,1,1,'Segunda','2021-11-05 10:00:00',1,1),(3,1,1,'Cita de diagnostico','2021-10-08 23:33:00',1,1),(4,24,1,'Cita de diagnostico','2021-10-08 23:33:00',1,24);
/*!40000 ALTER TABLE `cita` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estadocita`
--

DROP TABLE IF EXISTS `estadocita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estadocita` (
  `idEstado` int NOT NULL AUTO_INCREMENT,
  `estado` varchar(25) NOT NULL,
  PRIMARY KEY (`idEstado`,`estado`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estadocita`
--

LOCK TABLES `estadocita` WRITE;
/*!40000 ALTER TABLE `estadocita` DISABLE KEYS */;
INSERT INTO `estadocita` VALUES (1,'Nueva'),(2,'En Progreso'),(3,'Cancelada'),(4,'Finalizada');
/*!40000 ALTER TABLE `estadocita` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expedientepaciente`
--

DROP TABLE IF EXISTS `expedientepaciente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `expedientepaciente` (
  `idExpediente` int NOT NULL AUTO_INCREMENT,
  `idPaciente` int NOT NULL,
  `idDoctor` int NOT NULL,
  `titulo` varchar(50) NOT NULL,
  PRIMARY KEY (`idExpediente`),
  KEY `idPaciente` (`idPaciente`),
  KEY `expedientepaciente_ibfk_2` (`idDoctor`),
  CONSTRAINT `expedientepaciente_ibfk_1` FOREIGN KEY (`idPaciente`) REFERENCES `usuario` (`idUsuario`),
  CONSTRAINT `expedientepaciente_ibfk_2` FOREIGN KEY (`idDoctor`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expedientepaciente`
--

LOCK TABLES `expedientepaciente` WRITE;
/*!40000 ALTER TABLE `expedientepaciente` DISABLE KEYS */;
/*!40000 ALTER TABLE `expedientepaciente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persona` (
  `idPersona` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) DEFAULT NULL,
  `apellido` varchar(30) DEFAULT NULL,
  `edad` int DEFAULT NULL,
  `correo` varchar(45) DEFAULT NULL,
  `direccion` varchar(30) DEFAULT NULL,
  `departamento` varchar(30) DEFAULT NULL,
  `habilitado` int DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  PRIMARY KEY (`idPersona`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES (29,'ottoniel','barillas cruz ',23,'ottomansketa@gmail.com','ciudad','palin ',0,'1990-06-05'),(31,'Jose','Parada',28,'joseangelpl293@gmail.com','ciudad','guatemala',1,'1993-04-02'),(32,'admin','admin',28,'admin@admin.com','ciudad','guatemala',1,'1990-01-01');
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seguimientopaciente`
--

DROP TABLE IF EXISTS `seguimientopaciente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seguimientopaciente` (
  `idSeguimiento` int NOT NULL AUTO_INCREMENT,
  `idExpediente` int NOT NULL,
  `seguimiento` varchar(100) NOT NULL,
  `detalle` varchar(1000) NOT NULL,
  `fechaSeguimiento` datetime NOT NULL,
  `archivo` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idSeguimiento`),
  KEY `idExpediente` (`idExpediente`),
  CONSTRAINT `seguimientopaciente_ibfk_1` FOREIGN KEY (`idExpediente`) REFERENCES `expedientepaciente` (`idExpediente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seguimientopaciente`
--

LOCK TABLES `seguimientopaciente` WRITE;
/*!40000 ALTER TABLE `seguimientopaciente` DISABLE KEYS */;
/*!40000 ALTER TABLE `seguimientopaciente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipousuario`
--

DROP TABLE IF EXISTS `tipousuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipousuario` (
  `idTipoUsuario` int NOT NULL AUTO_INCREMENT,
  `tipo` varchar(25) NOT NULL,
  PRIMARY KEY (`idTipoUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipousuario`
--

LOCK TABLES `tipousuario` WRITE;
/*!40000 ALTER TABLE `tipousuario` DISABLE KEYS */;
INSERT INTO `tipousuario` VALUES (1,'Paciente'),(2,'Doctor');
/*!40000 ALTER TABLE `tipousuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `idUsuario` int NOT NULL AUTO_INCREMENT,
  `idPersona` int DEFAULT NULL,
  `idTipoUsuario` int DEFAULT NULL,
  `usuario` varchar(15) DEFAULT NULL,
  `clave` varchar(15) DEFAULT NULL,
  `habilitado` varchar(1) DEFAULT NULL,
  `fecha_usaurio` datetime DEFAULT NULL,
  PRIMARY KEY (`idUsuario`),
  KEY `idTipoUsuario` (`idTipoUsuario`),
  KEY `idPersona` (`idPersona`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,32,2,'admin','admin','1','2021-10-04 21:46:30'),(22,29,2,'ottonielbc','ottonielbc','1','2021-10-04 21:46:30'),(23,31,1,'joseparada','jose123','1','2021-10-04 21:46:30'),(24,32,1,'robertogh','beto','1',NULL);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-09  1:11:05
