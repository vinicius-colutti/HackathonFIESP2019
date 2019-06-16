-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: db-fiesphack.mysql.uhserver.com    Database: db_fiesphack
-- ------------------------------------------------------
-- Server version	5.6.22

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
-- Table structure for table `tbl_acesso`
--

DROP TABLE IF EXISTS `tbl_acesso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_acesso` (
  `id_acesso` int(11) NOT NULL AUTO_INCREMENT,
  `nome_acesso` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_acesso`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_acesso`
--

LOCK TABLES `tbl_acesso` WRITE;
/*!40000 ALTER TABLE `tbl_acesso` DISABLE KEYS */;
INSERT INTO `tbl_acesso` VALUES (1,'paramedicos');
/*!40000 ALTER TABLE `tbl_acesso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_consultas_medicamentos`
--

DROP TABLE IF EXISTS `tbl_consultas_medicamentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_consultas_medicamentos` (
  `id_consultas_medicamentos` int(11) NOT NULL,
  `nome_paciente` varchar(45) DEFAULT NULL,
  `doc_paciente` varchar(45) DEFAULT NULL,
  `endereco_da_consulta` text,
  `id_farmacia` varchar(45) DEFAULT NULL,
  `finalizado` tinyint(4) DEFAULT NULL,
  `observacoes` text,
  `identificador` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_consultas_medicamentos`),
  KEY `fk_id_farmacia_consultas_medicamentos_idx` (`id_farmacia`),
  CONSTRAINT `fk_id_farmacia_consultas_medicamentos` FOREIGN KEY (`id_farmacia`) REFERENCES `tbl_farmacia` (`id_farmacia`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_consultas_medicamentos`
--

LOCK TABLES `tbl_consultas_medicamentos` WRITE;
/*!40000 ALTER TABLE `tbl_consultas_medicamentos` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_consultas_medicamentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_especialidades`
--

DROP TABLE IF EXISTS `tbl_especialidades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_especialidades` (
  `id_especialidade` int(11) NOT NULL AUTO_INCREMENT,
  `nome_especialidade` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_especialidade`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_especialidades`
--

LOCK TABLES `tbl_especialidades` WRITE;
/*!40000 ALTER TABLE `tbl_especialidades` DISABLE KEYS */;
INSERT INTO `tbl_especialidades` VALUES (1,'Cardiologia'),(2,'Pediatria'),(3,'Ortopedia');
/*!40000 ALTER TABLE `tbl_especialidades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_especialidades_hospitais`
--

DROP TABLE IF EXISTS `tbl_especialidades_hospitais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_especialidades_hospitais` (
  `id_especialidade_hospital` int(11) NOT NULL AUTO_INCREMENT,
  `id_hospital` int(11) DEFAULT NULL,
  `id_especialidade` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_especialidade_hospital`),
  KEY `fk_id_especialidade_idx` (`id_especialidade`),
  KEY `fk_id_hospital_idx` (`id_hospital`),
  CONSTRAINT `fk_id_especialidade` FOREIGN KEY (`id_especialidade`) REFERENCES `tbl_especialidades` (`id_especialidade`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_id_hospital` FOREIGN KEY (`id_hospital`) REFERENCES `tbl_hospitais` (`id_hospital`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_especialidades_hospitais`
--

LOCK TABLES `tbl_especialidades_hospitais` WRITE;
/*!40000 ALTER TABLE `tbl_especialidades_hospitais` DISABLE KEYS */;
INSERT INTO `tbl_especialidades_hospitais` VALUES (1,7479387,1),(2,7479387,3),(3,7638698,2),(4,7638698,3);
/*!40000 ALTER TABLE `tbl_especialidades_hospitais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_especialidades_medicos_hospitais`
--

DROP TABLE IF EXISTS `tbl_especialidades_medicos_hospitais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_especialidades_medicos_hospitais` (
  `id_especialidades_medicos_hospitais` int(11) NOT NULL AUTO_INCREMENT,
  `id_especialidade` int(11) DEFAULT NULL,
  `id_medico` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_especialidades_medicos_hospitais`),
  KEY `fk_id_especialides_medicos_hospitais_espe_idx` (`id_especialidade`),
  KEY `fk_id_especialides_medicos_hospitais_med_idx` (`id_medico`),
  CONSTRAINT `fk_id_especialides_medicos_hospitais_espe` FOREIGN KEY (`id_especialidade`) REFERENCES `tbl_especialidades` (`id_especialidade`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_id_especialides_medicos_hospitais_med` FOREIGN KEY (`id_medico`) REFERENCES `tbl_medicos_hospitais` (`id_medicos_hospitais`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_especialidades_medicos_hospitais`
--

LOCK TABLES `tbl_especialidades_medicos_hospitais` WRITE;
/*!40000 ALTER TABLE `tbl_especialidades_medicos_hospitais` DISABLE KEYS */;
INSERT INTO `tbl_especialidades_medicos_hospitais` VALUES (1,1,1),(2,2,2),(3,3,3),(4,2,4);
/*!40000 ALTER TABLE `tbl_especialidades_medicos_hospitais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_farmacia`
--

DROP TABLE IF EXISTS `tbl_farmacia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_farmacia` (
  `id_farmacia` varchar(45) NOT NULL,
  PRIMARY KEY (`id_farmacia`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_farmacia`
--

LOCK TABLES `tbl_farmacia` WRITE;
/*!40000 ALTER TABLE `tbl_farmacia` DISABLE KEYS */;
INSERT INTO `tbl_farmacia` VALUES ('61.585.865/0878-42'),('61.585.865/1147-50');
/*!40000 ALTER TABLE `tbl_farmacia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_gravidade`
--

DROP TABLE IF EXISTS `tbl_gravidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_gravidade` (
  `id_gravidade` int(11) NOT NULL AUTO_INCREMENT,
  `nome_gravidade` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_gravidade`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_gravidade`
--

LOCK TABLES `tbl_gravidade` WRITE;
/*!40000 ALTER TABLE `tbl_gravidade` DISABLE KEYS */;
INSERT INTO `tbl_gravidade` VALUES (1,'Baixa'),(2,'Media'),(3,'Alta');
/*!40000 ALTER TABLE `tbl_gravidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_hospitais`
--

DROP TABLE IF EXISTS `tbl_hospitais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_hospitais` (
  `id_hospital` int(11) NOT NULL,
  PRIMARY KEY (`id_hospital`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_hospitais`
--

LOCK TABLES `tbl_hospitais` WRITE;
/*!40000 ALTER TABLE `tbl_hospitais` DISABLE KEYS */;
INSERT INTO `tbl_hospitais` VALUES (7479387),(7638698);
/*!40000 ALTER TABLE `tbl_hospitais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_leito_hospital`
--

DROP TABLE IF EXISTS `tbl_leito_hospital`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_leito_hospital` (
  `id_leito_hospital` int(11) NOT NULL AUTO_INCREMENT,
  `id_leito` int(11) DEFAULT NULL,
  `id_hospital` int(11) DEFAULT NULL,
  `qtd_total` varchar(45) DEFAULT NULL,
  `qtd_ocupado` varchar(45) DEFAULT NULL,
  `disponivel` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_leito_hospital`),
  KEY `fk_id_leito_idx` (`id_leito`),
  KEY `fk_id_leito_hospital_idx` (`id_hospital`),
  CONSTRAINT `fk_id_leito` FOREIGN KEY (`id_leito`) REFERENCES `tbl_leitos` (`id_leitos`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_id_leito_hospital` FOREIGN KEY (`id_hospital`) REFERENCES `tbl_hospitais` (`id_hospital`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_leito_hospital`
--

LOCK TABLES `tbl_leito_hospital` WRITE;
/*!40000 ALTER TABLE `tbl_leito_hospital` DISABLE KEYS */;
INSERT INTO `tbl_leito_hospital` VALUES (2,1,7479387,'10','3',1),(3,2,7479387,'6','2',1),(4,3,7638698,'8','4',1),(5,4,7638698,'14','9',1);
/*!40000 ALTER TABLE `tbl_leito_hospital` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_leitos`
--

DROP TABLE IF EXISTS `tbl_leitos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_leitos` (
  `id_leitos` int(11) NOT NULL AUTO_INCREMENT,
  `nome_leito` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_leitos`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_leitos`
--

LOCK TABLES `tbl_leitos` WRITE;
/*!40000 ALTER TABLE `tbl_leitos` DISABLE KEYS */;
INSERT INTO `tbl_leitos` VALUES (1,'UTI'),(2,'Cirúrgico'),(3,'Clínico'),(4,'Pediátricos');
/*!40000 ALTER TABLE `tbl_leitos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_medicamentos`
--

DROP TABLE IF EXISTS `tbl_medicamentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_medicamentos` (
  `id_medicamentos` int(11) NOT NULL AUTO_INCREMENT,
  `nome_medicamento` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_medicamentos`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_medicamentos`
--

LOCK TABLES `tbl_medicamentos` WRITE;
/*!40000 ALTER TABLE `tbl_medicamentos` DISABLE KEYS */;
INSERT INTO `tbl_medicamentos` VALUES (1,'Amoxicilina'),(2,'Dipirona'),(3,'Adenosina 50mg'),(4,'Alteplase 100mg');
/*!40000 ALTER TABLE `tbl_medicamentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_medicamentos_consulta`
--

DROP TABLE IF EXISTS `tbl_medicamentos_consulta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_medicamentos_consulta` (
  `id_medicamentos_consulta` int(11) NOT NULL AUTO_INCREMENT,
  `id_medicamentos` int(11) DEFAULT NULL,
  `id_consulta` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_medicamentos_consulta`),
  KEY `fk_id_medicamento_medicamentos_consulta_idx` (`id_medicamentos`),
  KEY `fk_id_consulta_medicamentos_consulta_idx` (`id_consulta`),
  CONSTRAINT `fk_id_consulta_medicamentos_consulta` FOREIGN KEY (`id_consulta`) REFERENCES `tbl_consultas_medicamentos` (`id_consultas_medicamentos`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_id_medicamento_medicamentos_consulta` FOREIGN KEY (`id_medicamentos`) REFERENCES `tbl_medicamentos` (`id_medicamentos`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_medicamentos_consulta`
--

LOCK TABLES `tbl_medicamentos_consulta` WRITE;
/*!40000 ALTER TABLE `tbl_medicamentos_consulta` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_medicamentos_consulta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_medicamentos_farmacia`
--

DROP TABLE IF EXISTS `tbl_medicamentos_farmacia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_medicamentos_farmacia` (
  `id_medicamentos_farmacia` int(11) NOT NULL,
  `id_medicamento` int(11) DEFAULT NULL,
  `id_farmacia` varchar(45) DEFAULT NULL,
  `disponivel` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id_medicamentos_farmacia`),
  KEY `fk_medicamento_medicamentos_farmacia_idx` (`id_medicamento`),
  KEY `fk_farmacia_medicamentos_farmacia_idx` (`id_farmacia`),
  CONSTRAINT `fk_farmacia_medicamentos_farmacia` FOREIGN KEY (`id_farmacia`) REFERENCES `tbl_farmacia` (`id_farmacia`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_medicamento_medicamentos_farmacia` FOREIGN KEY (`id_medicamento`) REFERENCES `tbl_medicamentos` (`id_medicamentos`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_medicamentos_farmacia`
--

LOCK TABLES `tbl_medicamentos_farmacia` WRITE;
/*!40000 ALTER TABLE `tbl_medicamentos_farmacia` DISABLE KEYS */;
INSERT INTO `tbl_medicamentos_farmacia` VALUES (1,1,'61.585.865/0878-42',1),(2,2,'61.585.865/0878-42',1),(3,3,'61.585.865/0878-42',1),(4,4,'61.585.865/1147-50',1),(5,1,'61.585.865/1147-50',1);
/*!40000 ALTER TABLE `tbl_medicamentos_farmacia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_medicos_hospitais`
--

DROP TABLE IF EXISTS `tbl_medicos_hospitais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_medicos_hospitais` (
  `id_medicos_hospitais` int(11) NOT NULL AUTO_INCREMENT,
  `nome_medico` varchar(45) DEFAULT NULL,
  `disponivel` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id_medicos_hospitais`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_medicos_hospitais`
--

LOCK TABLES `tbl_medicos_hospitais` WRITE;
/*!40000 ALTER TABLE `tbl_medicos_hospitais` DISABLE KEYS */;
INSERT INTO `tbl_medicos_hospitais` VALUES (1,'Joao da Silva',1),(2,'Marina de Souza',1),(3,'Luiz Carlos',1),(4,'Antonia de Jesus',1);
/*!40000 ALTER TABLE `tbl_medicos_hospitais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_pedidos_emergencias`
--

DROP TABLE IF EXISTS `tbl_pedidos_emergencias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_pedidos_emergencias` (
  `id_pedidos_emergencias` int(11) NOT NULL AUTO_INCREMENT,
  `id_paramedico` int(11) DEFAULT NULL,
  `id_hospital` int(11) DEFAULT NULL,
  `situacao` tinyint(4) DEFAULT NULL,
  `idade_paciente` varchar(45) DEFAULT NULL,
  `genero_paciente` varchar(45) DEFAULT NULL,
  `traumas` text,
  `identificacao_paciente` text,
  `id_gravidade` int(11) DEFAULT NULL,
  `data_insercao` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_pedidos_emergencias`),
  KEY `fk_id_paramedico_ped_emergencia_idx` (`id_paramedico`),
  KEY `fk_id_hospital_pedidos_emergencias_idx` (`id_hospital`),
  KEY `fk_id_gravidade_pedidos_emergencias_idx` (`id_gravidade`),
  CONSTRAINT `fk_id_gravidade_pedidos_emergencias` FOREIGN KEY (`id_gravidade`) REFERENCES `tbl_gravidade` (`id_gravidade`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_id_hospital_pedidos_emergencias` FOREIGN KEY (`id_hospital`) REFERENCES `tbl_hospitais` (`id_hospital`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_id_paramedico_ped_emergencia` FOREIGN KEY (`id_paramedico`) REFERENCES `tbl_usuarios_acesso` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_pedidos_emergencias`
--

LOCK TABLES `tbl_pedidos_emergencias` WRITE;
/*!40000 ALTER TABLE `tbl_pedidos_emergencias` DISABLE KEYS */;
INSERT INTO `tbl_pedidos_emergencias` VALUES (9,1,7638698,0,'13','Masculino','Dores no coração e peito','Vinicius Colutti',2,NULL),(10,2,7479387,0,'18','Masculino','Dores na barriga','Gulherme Tiburcio',3,NULL),(11,3,7479387,0,'26','Feminino','Dores no braço','Bianca Pimentel',1,'Sun Jun 16 02:29:56 BRT 2019');
/*!40000 ALTER TABLE `tbl_pedidos_emergencias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_usuarios_acesso`
--

DROP TABLE IF EXISTS `tbl_usuarios_acesso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_usuarios_acesso` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `CPF` varchar(45) DEFAULT NULL,
  `senha` varchar(45) DEFAULT NULL,
  `id_acesso` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_usuario`),
  KEY `fk_id_acesso_idx` (`id_acesso`),
  CONSTRAINT `fk_id_acesso` FOREIGN KEY (`id_acesso`) REFERENCES `tbl_acesso` (`id_acesso`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_usuarios_acesso`
--

LOCK TABLES `tbl_usuarios_acesso` WRITE;
/*!40000 ALTER TABLE `tbl_usuarios_acesso` DISABLE KEYS */;
INSERT INTO `tbl_usuarios_acesso` VALUES (1,'50382435842','123',1),(2,'48981763801','123',1),(3,'09731525408','123',1);
/*!40000 ALTER TABLE `tbl_usuarios_acesso` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-16  4:34:44
