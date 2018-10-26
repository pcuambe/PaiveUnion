-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 25-Out-2018 às 20:04
-- Versão do servidor: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `paivel`
--
CREATE DATABASE IF NOT EXISTS `paivel` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `paivel`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `aluguer`
--
-- Criação: 23-Out-2018 às 11:10
--

DROP TABLE IF EXISTS `aluguer`;
CREATE TABLE IF NOT EXISTS `aluguer` (
`aluguerID` int(11) NOT NULL,
  `apagado` bit(1) NOT NULL,
  `data` date DEFAULT NULL,
  `valor` double NOT NULL,
  `valorDoPrejuizo` double NOT NULL,
  `cliente_clienteID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `aluguer`:
--   `cliente_clienteID`
--       `cliente` -> `clienteID`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `aluguer_material`
--
-- Criação: 23-Out-2018 às 11:10
--

DROP TABLE IF EXISTS `aluguer_material`;
CREATE TABLE IF NOT EXISTS `aluguer_material` (
  `aluguerID` int(11) NOT NULL,
  `materialID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `aluguer_material`:
--   `aluguerID`
--       `aluguer` -> `aluguerID`
--   `materialID`
--       `material` -> `materialID`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--
-- Criação: 23-Out-2018 às 11:10
--

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE IF NOT EXISTS `cliente` (
`clienteID` int(11) NOT NULL,
  `apagado` bit(1) NOT NULL,
  `categoria` varchar(255) DEFAULT NULL,
  `contato1` varchar(255) DEFAULT NULL,
  `contato2` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `taxa` double NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `cliente`
--

INSERT INTO `cliente` (`clienteID`, `apagado`, `categoria`, `contato1`, `contato2`, `nome`, `taxa`) VALUES
(1, b'0', 'Individual', '845678901', '825678901', 'Jose Cossa', 0),
(2, b'0', 'Empresarial', '825678901', '845678901', 'Gosen Youth', 0),
(8, b'0', 'Individual', 'nnb', 'nn', 'nn', 100),
(9, b'0', 'Individual', '9919', '7881', 'Pedro Macanzo', 100);

-- --------------------------------------------------------

--
-- Estrutura da tabela `evento`
--
-- Criação: 23-Out-2018 às 11:10
--

DROP TABLE IF EXISTS `evento`;
CREATE TABLE IF NOT EXISTS `evento` (
`eventoID` int(11) NOT NULL,
  `activo` bit(1) NOT NULL,
  `apagado` bit(1) NOT NULL,
  `codigoEvento` varchar(255) DEFAULT NULL,
  `dataDeRealizacao` date DEFAULT NULL,
  `duracao` int(11) NOT NULL,
  `nrPessoas` int(11) DEFAULT NULL,
  `preco` double NOT NULL,
  `taxaAdiamento` double NOT NULL,
  `terimando` bit(1) NOT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `cliente_clienteID` int(11) DEFAULT NULL,
  `salao_salaoID` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `evento`:
--   `salao_salaoID`
--       `salao` -> `salaoID`
--   `cliente_clienteID`
--       `cliente` -> `clienteID`
--

--
-- Extraindo dados da tabela `evento`
--

INSERT INTO `evento` (`eventoID`, `activo`, `apagado`, `codigoEvento`, `dataDeRealizacao`, `duracao`, `nrPessoas`, `preco`, `taxaAdiamento`, `terimando`, `titulo`, `cliente_clienteID`, `salao_salaoID`) VALUES
(1, b'1', b'0', 'EVT20407463', '2018-10-09', 23, 400, 560000, 0, b'0', 'Casamento de Rui e Ricardina', 1, 2),
(2, b'1', b'0', 'EVT15585368', '2018-10-26', 23, 300, 360000, 0, b'0', 'Gosen Celebration', 2, 1),
(8, b'1', b'0', 'EVT28826223', '2018-12-06', 23, 100, 140100, 0, b'0', 'nnn', 8, 2),
(9, b'1', b'0', 'EVT19869898', '2018-11-30', 23, 300, 420100, 0, b'0', 'Casamento Luiois', 9, 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `ev_func`
--
-- Criação: 23-Out-2018 às 11:10
--

DROP TABLE IF EXISTS `ev_func`;
CREATE TABLE IF NOT EXISTS `ev_func` (
  `codigoEvento` int(11) NOT NULL,
  `codigoFuncionario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `ev_func`:
--   `codigoEvento`
--       `evento` -> `eventoID`
--   `codigoFuncionario`
--       `funcionario` -> `funcionarioID`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `ev_matuso`
--
-- Criação: 23-Out-2018 às 11:11
--

DROP TABLE IF EXISTS `ev_matuso`;
CREATE TABLE IF NOT EXISTS `ev_matuso` (
  `codigoEvento` int(11) NOT NULL,
  `codigoMaterial` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `ev_matuso`:
--   `codigoEvento`
--       `evento` -> `eventoID`
--   `codigoMaterial`
--       `material_uso` -> `materialUsoID`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcionario`
--
-- Criação: 23-Out-2018 às 11:10
--

DROP TABLE IF EXISTS `funcionario`;
CREATE TABLE IF NOT EXISTS `funcionario` (
`funcionarioID` int(11) NOT NULL,
  `apagado` bit(1) NOT NULL,
  `categoria` varchar(255) DEFAULT NULL,
  `codigoFuncionario` varchar(255) DEFAULT NULL,
  `dataNascimento` date DEFAULT NULL,
  `demitido` bit(1) NOT NULL,
  `disponivel` bit(1) NOT NULL,
  `nome` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `material`
--
-- Criação: 23-Out-2018 às 11:11
--

DROP TABLE IF EXISTS `material`;
CREATE TABLE IF NOT EXISTS `material` (
`materialID` int(11) NOT NULL,
  `apagado` bit(1) NOT NULL,
  `categoria` varchar(255) DEFAULT NULL,
  `codigoMaterial` varchar(255) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `disponivel` bit(1) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `pessoasMesa` int(11) NOT NULL,
  `precoDeAluguer` double NOT NULL,
  `quantidade` int(11) NOT NULL,
  `quantidadeMinima` int(11) NOT NULL,
  `salao_salaoID` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `material`:
--   `salao_salaoID`
--       `salao` -> `salaoID`
--

--
-- Extraindo dados da tabela `material`
--

INSERT INTO `material` (`materialID`, `apagado`, `categoria`, `codigoMaterial`, `descricao`, `disponivel`, `nome`, `pessoasMesa`, `precoDeAluguer`, `quantidade`, `quantidadeMinima`, `salao_salaoID`) VALUES
(1, b'0', 'Mesa', 'MAT12977687', 'Mesas para 8 pessoas.', b'0', 'Mesa de Vidro Para 8', 8, 220, 120, 5, 2),
(2, b'0', 'Mesa', 'MAT6932222', 'Bla bla bla bla bla', b'0', 'Mesa de Vidro Para 10', 10, 300, 105, 5, 1),
(3, b'0', 'Cadeira', 'MAT12024020', 'bla bla bla bla', b'0', 'Cadeira de Madeira Italiana', 0, 100, 1110, 20, 1),
(4, b'0', 'Cadeira', 'MAT17410254', 'bla bla bla bla', b'0', 'Cadeira de Madeira Italiana', 0, 100, 1200, 20, 2),
(5, b'0', 'Guardanapo', 'MAT8226928', 'bla bla bla bla', b'0', 'Guardanapos Azuis', 0, 10, 1700, 100, 1),
(6, b'0', 'Guardanapo', 'MAT3655379', 'bla bla bla bla', b'0', 'Guardanapos Azuis', 0, 120, 2000, 30, 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `material_uso`
--
-- Criação: 23-Out-2018 às 11:11
--

DROP TABLE IF EXISTS `material_uso`;
CREATE TABLE IF NOT EXISTS `material_uso` (
`materialUsoID` int(11) NOT NULL,
  `categoria` varchar(255) DEFAULT NULL,
  `codigoMaterial` varchar(255) DEFAULT NULL,
  `dataDeUso` datetime DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `pessoasMesa` int(11) NOT NULL,
  `precoDeAluguer` double NOT NULL,
  `quantidade` int(11) NOT NULL,
  `quantidadeMinima` int(11) NOT NULL,
  `quantidadePrejuizo` int(11) NOT NULL,
  `material_materialID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `material_uso`:
--   `material_materialID`
--       `material` -> `materialID`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `parcela`
--
-- Criação: 23-Out-2018 às 11:11
--

DROP TABLE IF EXISTS `parcela`;
CREATE TABLE IF NOT EXISTS `parcela` (
`parcelaID` int(11) NOT NULL,
  `apagado` bit(1) NOT NULL,
  `dataPagamento` datetime DEFAULT NULL,
  `valor` double NOT NULL,
  `cliente_clienteID` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `parcela`:
--   `cliente_clienteID`
--       `cliente` -> `clienteID`
--

--
-- Extraindo dados da tabela `parcela`
--

INSERT INTO `parcela` (`parcelaID`, `apagado`, `dataPagamento`, `valor`, `cliente_clienteID`) VALUES
(1, b'0', '2018-10-25 11:23:38', 4000, 1),
(2, b'0', '2018-10-25 11:29:22', 30000, 8),
(3, b'0', '2018-10-25 11:30:03', 30000, 1),
(4, b'0', '2018-10-25 11:59:09', 120000, 1),
(5, b'0', '2018-10-25 12:00:18', 120000, 1),
(6, b'0', '2018-10-25 12:00:50', 12000, 2),
(7, b'0', '2018-10-25 12:09:17', 40000, 2),
(8, b'0', '2018-10-25 17:50:57', 130000, 9),
(9, b'0', '2018-10-25 17:51:14', 130000, 9),
(10, b'0', '2018-10-25 17:57:20', 3000, 9),
(11, b'0', '2018-10-25 18:25:56', 3000, 8),
(12, b'0', '2018-10-25 18:45:59', 1000, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `salao`
--
-- Criação: 23-Out-2018 às 11:11
--

DROP TABLE IF EXISTS `salao`;
CREATE TABLE IF NOT EXISTS `salao` (
`salaoID` int(11) NOT NULL,
  `apagado` bit(1) NOT NULL,
  `capacidade` int(11) NOT NULL,
  `codigoSalao` varchar(255) DEFAULT NULL,
  `disponivel` bit(1) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `preco` double NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `salao`
--

INSERT INTO `salao` (`salaoID`, `apagado`, `capacidade`, `codigoSalao`, `disponivel`, `nome`, `preco`) VALUES
(1, b'0', 1500, 'SA31629677', b'1', 'Go Saloon', 1200),
(2, b'0', 2000, 'SA31798582', b'1', 'Mega Lona', 1400);

-- --------------------------------------------------------

--
-- Estrutura da tabela `taxas`
--
-- Criação: 23-Out-2018 às 11:11
--

DROP TABLE IF EXISTS `taxas`;
CREATE TABLE IF NOT EXISTS `taxas` (
`id` int(11) NOT NULL,
  `clienteEmpresarial` double NOT NULL,
  `clienteNormal` double NOT NULL,
  `taxaAdiamento` double NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `taxas`
--

INSERT INTO `taxas` (`id`, `clienteEmpresarial`, `clienteNormal`, `taxaAdiamento`) VALUES
(2, 5000, 100, 30000);

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--
-- Criação: 24-Out-2018 às 20:21
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
`id` int(11) NOT NULL,
  `apelido` varchar(255) DEFAULT NULL,
  `nivelAcesso` varchar(40) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `apagado` bit(1) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`id`, `apelido`, `nivelAcesso`, `nome`, `password`, `userName`, `apagado`) VALUES
(2, 'Matavele', 'Gestor', 'Ivo', '1234', 'matavela', b'0'),
(6, 'Amosse', 'Administrador', 'Paulo', 'holy', 'pcuambe', b'0');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `aluguer`
--
ALTER TABLE `aluguer`
 ADD PRIMARY KEY (`aluguerID`), ADD KEY `FK_4bl7c1adpyn6x31vq7fvvp62x` (`cliente_clienteID`);

--
-- Indexes for table `aluguer_material`
--
ALTER TABLE `aluguer_material`
 ADD PRIMARY KEY (`aluguerID`,`materialID`), ADD KEY `FK_bjtvb0ce8ja33jgn5itojo660` (`materialID`);

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
 ADD PRIMARY KEY (`clienteID`);

--
-- Indexes for table `evento`
--
ALTER TABLE `evento`
 ADD PRIMARY KEY (`eventoID`), ADD KEY `FK_nmxp4vbl5u9h6plp5vvf3vc57` (`cliente_clienteID`), ADD KEY `FK_a1ulvowhdtovf0vk01g4idxta` (`salao_salaoID`);

--
-- Indexes for table `ev_func`
--
ALTER TABLE `ev_func`
 ADD PRIMARY KEY (`codigoEvento`,`codigoFuncionario`), ADD KEY `FK_ki4ht3tj032kosnst1ylcg9vu` (`codigoFuncionario`);

--
-- Indexes for table `ev_matuso`
--
ALTER TABLE `ev_matuso`
 ADD PRIMARY KEY (`codigoEvento`,`codigoMaterial`), ADD UNIQUE KEY `UK_8s0bxdr8bm16h9hmxkjwh1x3j` (`codigoMaterial`);

--
-- Indexes for table `funcionario`
--
ALTER TABLE `funcionario`
 ADD PRIMARY KEY (`funcionarioID`);

--
-- Indexes for table `material`
--
ALTER TABLE `material`
 ADD PRIMARY KEY (`materialID`), ADD KEY `FK_3u3ths94fxaeixy7p5ij9haq9` (`salao_salaoID`);

--
-- Indexes for table `material_uso`
--
ALTER TABLE `material_uso`
 ADD PRIMARY KEY (`materialUsoID`), ADD KEY `FK_hm7mmvee9d9hms61j8fsdx6w8` (`material_materialID`);

--
-- Indexes for table `parcela`
--
ALTER TABLE `parcela`
 ADD PRIMARY KEY (`parcelaID`), ADD KEY `FK_9tw977o97pxq5tsjjpuv884lw` (`cliente_clienteID`);

--
-- Indexes for table `salao`
--
ALTER TABLE `salao`
 ADD PRIMARY KEY (`salaoID`);

--
-- Indexes for table `taxas`
--
ALTER TABLE `taxas`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `aluguer`
--
ALTER TABLE `aluguer`
MODIFY `aluguerID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `cliente`
--
ALTER TABLE `cliente`
MODIFY `clienteID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `evento`
--
ALTER TABLE `evento`
MODIFY `eventoID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `funcionario`
--
ALTER TABLE `funcionario`
MODIFY `funcionarioID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `material`
--
ALTER TABLE `material`
MODIFY `materialID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `material_uso`
--
ALTER TABLE `material_uso`
MODIFY `materialUsoID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `parcela`
--
ALTER TABLE `parcela`
MODIFY `parcelaID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `salao`
--
ALTER TABLE `salao`
MODIFY `salaoID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `taxas`
--
ALTER TABLE `taxas`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `aluguer`
--
ALTER TABLE `aluguer`
ADD CONSTRAINT `FK_4bl7c1adpyn6x31vq7fvvp62x` FOREIGN KEY (`cliente_clienteID`) REFERENCES `cliente` (`clienteID`);

--
-- Limitadores para a tabela `aluguer_material`
--
ALTER TABLE `aluguer_material`
ADD CONSTRAINT `FK_bdk39384q85v372ne7veg7e6t` FOREIGN KEY (`aluguerID`) REFERENCES `aluguer` (`aluguerID`),
ADD CONSTRAINT `FK_bjtvb0ce8ja33jgn5itojo660` FOREIGN KEY (`materialID`) REFERENCES `material` (`materialID`);

--
-- Limitadores para a tabela `evento`
--
ALTER TABLE `evento`
ADD CONSTRAINT `FK_a1ulvowhdtovf0vk01g4idxta` FOREIGN KEY (`salao_salaoID`) REFERENCES `salao` (`salaoID`),
ADD CONSTRAINT `FK_nmxp4vbl5u9h6plp5vvf3vc57` FOREIGN KEY (`cliente_clienteID`) REFERENCES `cliente` (`clienteID`);

--
-- Limitadores para a tabela `ev_func`
--
ALTER TABLE `ev_func`
ADD CONSTRAINT `FK_hiwpecqq9jmt2p9812jqpdmoo` FOREIGN KEY (`codigoEvento`) REFERENCES `evento` (`eventoID`),
ADD CONSTRAINT `FK_ki4ht3tj032kosnst1ylcg9vu` FOREIGN KEY (`codigoFuncionario`) REFERENCES `funcionario` (`funcionarioID`);

--
-- Limitadores para a tabela `ev_matuso`
--
ALTER TABLE `ev_matuso`
ADD CONSTRAINT `FK_510kg77ohudioyqxnx1robuma` FOREIGN KEY (`codigoEvento`) REFERENCES `evento` (`eventoID`),
ADD CONSTRAINT `FK_8s0bxdr8bm16h9hmxkjwh1x3j` FOREIGN KEY (`codigoMaterial`) REFERENCES `material_uso` (`materialUsoID`);

--
-- Limitadores para a tabela `material`
--
ALTER TABLE `material`
ADD CONSTRAINT `FK_3u3ths94fxaeixy7p5ij9haq9` FOREIGN KEY (`salao_salaoID`) REFERENCES `salao` (`salaoID`);

--
-- Limitadores para a tabela `material_uso`
--
ALTER TABLE `material_uso`
ADD CONSTRAINT `FK_hm7mmvee9d9hms61j8fsdx6w8` FOREIGN KEY (`material_materialID`) REFERENCES `material` (`materialID`);

--
-- Limitadores para a tabela `parcela`
--
ALTER TABLE `parcela`
ADD CONSTRAINT `FK_9tw977o97pxq5tsjjpuv884lw` FOREIGN KEY (`cliente_clienteID`) REFERENCES `cliente` (`clienteID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
