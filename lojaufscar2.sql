-- phpMyAdmin SQL Dump
-- version 4.6.3deb1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Nov 28, 2016 at 06:41 PM
-- Server version: 5.6.30-1
-- PHP Version: 5.6.17-3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `lojaufscar`
--

-- --------------------------------------------------------

--
-- Table structure for table `categoria`
--

CREATE TABLE `categoria` (
  `id` int(11) NOT NULL,
  `descricao` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `categoria`
--

INSERT INTO `categoria` (`id`, `descricao`) VALUES
(1, 'Tablet'),
(2, 'Celular'),
(3, 'Notebook'),
(4, 'Computador');

-- --------------------------------------------------------

--
-- Table structure for table `cliente`
--

CREATE TABLE `cliente` (
  `senha` varchar(100) NOT NULL,
  `nome` varchar(80) NOT NULL,
  `cpf` int(11) NOT NULL,
  `data_nascimento` varchar(12) NOT NULL,
  `email` varchar(80) NOT NULL,
  `telefone1` varchar(15) NOT NULL,
  `telefone2` varchar(15) NOT NULL,
  `telefone3` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cliente`
--

INSERT INTO `cliente` (`senha`, `nome`, `cpf`, `data_nascimento`, `email`, `telefone1`, `telefone2`, `telefone3`) VALUES
('202cb962ac59075b964b07152d234b70', 'teste', 123, '123', '123@mail.com', '123', '123', '123');

-- --------------------------------------------------------

--
-- Table structure for table `endereco`
--

CREATE TABLE `endereco` (
  `id` int(11) NOT NULL,
  `cliente_cpf` int(11) NOT NULL,
  `descricao` varchar(20) NOT NULL,
  `logradouro` varchar(80) NOT NULL,
  `numero` varchar(6) NOT NULL,
  `complemento` varchar(20) NOT NULL,
  `bairro` varchar(20) NOT NULL,
  `cidade` varchar(30) NOT NULL,
  `uf` char(2) NOT NULL,
  `cep` varchar(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `endereco`
--

INSERT INTO `endereco` (`id`, `cliente_cpf`, `descricao`, `logradouro`, `numero`, `complemento`, `bairro`, `cidade`, `uf`, `cep`) VALUES
(1, 123, '123', '123', '123', '123', '123', '123', '12', '123');

-- --------------------------------------------------------

--
-- Table structure for table `forma_pagamento`
--

CREATE TABLE `forma_pagamento` (
  `id` int(11) NOT NULL,
  `descricao` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `forma_pagamento`
--

INSERT INTO `forma_pagamento` (`id`, `descricao`) VALUES
(1, 'boleto'),
(2, 'Crédito 2x'),
(3, 'Crédito 3x'),
(4, 'Crédito 4x'),
(5, 'Crédito 5x'),
(6, 'Crédito 6x'),
(7, 'Teste');

-- --------------------------------------------------------

--
-- Table structure for table `funcionario`
--

CREATE TABLE `funcionario` (
  `login` varchar(20) NOT NULL,
  `nome` varchar(80) NOT NULL,
  `senha` varchar(100) NOT NULL,
  `email` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `funcionario`
--

INSERT INTO `funcionario` (`login`, `nome`, `senha`, `email`) VALUES
('admin', 'admin', '21232f297a57a5a743894a0e4a801fc3', 'admin@mail.com');

-- --------------------------------------------------------

--
-- Table structure for table `loja`
--

CREATE TABLE `loja` (
  `nome` varchar(80) NOT NULL,
  `cnpj` int(11) NOT NULL,
  `logo` varchar(100) NOT NULL,
  `cores` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='cores terão apenas 4 padrões(azul, verde, laranja, vermelho)';

-- --------------------------------------------------------

--
-- Table structure for table `pedido`
--

CREATE TABLE `pedido` (
  `id` int(11) NOT NULL,
  `data_pedido` date NOT NULL,
  `cliente_cpf` int(11) NOT NULL,
  `endereco_entrega_id` int(11) NOT NULL,
  `forma_pagamento_id` int(11) NOT NULL,
  `frete` decimal(10,2) DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  `status` char(1) NOT NULL COMMENT '0 - pendente, 1 - liberado, 2 - Cancelado'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pedido`
--

INSERT INTO `pedido` (`id`, `data_pedido`, `cliente_cpf`, `endereco_entrega_id`, `forma_pagamento_id`, `frete`, `total`, `status`) VALUES
(1, '2016-09-19', 123, 1, 1, 0.00, 2998.00, '0'),
(2, '2016-09-19', 123, 1, 1, 0.00, 1499.00, '0'),
(3, '2016-09-19', 123, 1, 1, 0.00, 1499.00, '0'),
(4, '2016-09-19', 123, 1, 1, 0.00, 1299.00, '0'),
(5, '2016-09-19', 123, 1, 1, 0.00, 3897.00, '0'),
(6, '2016-09-19', 123, 1, 1, 0.00, 850.00, '0'),
(7, '2016-09-19', 123, 1, 1, 0.00, 2500.00, '0'),
(8, '2016-09-19', 123, 1, 1, 0.00, 1398.00, '0'),
(9, '2016-09-19', 123, 1, 1, 0.00, 850.00, '0'),
(10, '2016-09-19', 123, 1, 1, 0.00, 850.00, '0'),
(11, '2016-09-19', 123, 1, 1, 0.00, 850.00, '0'),
(12, '2016-09-19', 123, 1, 1, 0.00, 850.00, '0'),
(13, '2016-09-19', 123, 1, 1, 0.00, 1398.00, '0'),
(14, '2016-09-19', 123, 1, 1, 0.00, 1499.00, '0'),
(15, '2016-09-19', 123, 1, 1, 0.00, 1499.00, '0'),
(16, '2016-09-19', 123, 1, 1, 0.00, 1398.00, '0'),
(17, '2016-09-19', 123, 1, 1, 0.00, 850.00, '0'),
(18, '2016-09-19', 123, 1, 1, 0.00, 1299.00, '0'),
(19, '2016-09-19', 123, 1, 1, 0.00, 1299.00, '0'),
(20, '2016-09-19', 123, 1, 1, 0.00, 1499.00, '0'),
(21, '2016-09-19', 123, 1, 1, 0.00, 850.00, '0'),
(22, '2016-09-19', 123, 1, 1, 0.00, 1499.00, '0');

-- --------------------------------------------------------

--
-- Table structure for table `pedido_produto`
--

CREATE TABLE `pedido_produto` (
  `produto_id` int(11) NOT NULL,
  `pedido_id` int(11) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `valor_unitario` decimal(10,2) DEFAULT NULL,
  `valor_total` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pedido_produto`
--

INSERT INTO `pedido_produto` (`produto_id`, `pedido_id`, `quantidade`, `valor_unitario`, `valor_total`) VALUES
(1, 22, 1, NULL, 1499.00);

-- --------------------------------------------------------

--
-- Table structure for table `produto`
--

CREATE TABLE `produto` (
  `id` int(11) NOT NULL,
  `descricao` varchar(80) NOT NULL,
  `caracteristicas` text NOT NULL,
  `categoria_id` int(11) NOT NULL,
  `quantidade_estoque` int(11) NOT NULL,
  `preco_custo` decimal(10,0) NOT NULL,
  `preco_venda` decimal(10,0) NOT NULL,
  `status` tinyint(1) NOT NULL COMMENT 'controla se o produto está liberado pra venda'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `produto`
--

INSERT INTO `produto` (`id`, `descricao`, `caracteristicas`, `categoria_id`, `quantidade_estoque`, `preco_custo`, `preco_venda`, `status`) VALUES
(1, 'Smartphone Moto G 4 Plus Dual Chip Android 6.0 Tela 5.5 32GB', 'Primeiro eu queria cumprimentar os internautas. -Oi Internautas! Depois dizer que o meio ambiente é sem dúvida nenhuma uma ameaça ao desenvolvimento sustentável. E isso significa que é uma ameaça pro futuro do nosso planeta e dos nossos países. O desemprego beira 20%, ou seja, 1 em cada 4 portugueses.', 2, 10, 400, 1499, 1),
(2, 'Smartphone Moto G 4 Dual Chip Android 6.0 Tela 5.5\'\' 16GB Câmera 13MP - Preto', 'Eu dou dinheiro pra minha filha. Eu dou dinheiro pra ela viajar, então é... é... Já vivi muito sem dinheiro, já vivi muito com dinheiro. -Jornalista: Coloca esse dinheiro na poupança que a senhora ganha R$10 mil por mês. -Dilma: O que que é R$10 mil?\r\n\r\nSe hoje é o dia das crianças... Ontem eu disse: o dia da criança é o dia da mãe, dos pais, das professoras, mas também é o dia dos animais, sempre que você olha uma criança, há sempre uma figura oculta, que é um cachorro atrás. O que é algo muito importante!', 2, 100, 300, 1299, 1),
(3, 'Tablet Samsung Galaxy Tab E T560 8GB Wi-Fi Tela 9.6" Android 4.4 Quad-Core', 'Primeiro eu queria cumprimentar os internautas. -Oi Internautas! Depois dizer que o meio ambiente é sem dúvida nenhuma uma ameaça ao desenvolvimento sustentável. E isso significa que é uma ameaça pro futuro do nosso planeta e dos nossos países. O desemprego beira 20%, ou seja, 1 em cada 4 portugueses.\r\n', 1, 5, 200, 850, 1),
(4, 'Tablet Samsung Galaxy Tab A P550 16GB Wi-Fi Tela 9.7" Android 5.0 Quad-Core', 'Primeiro eu queria cumprimentar os internautas. -Oi Internautas! Depois dizer que o meio ambiente é sem dúvida nenhuma uma ameaça ao desenvolvimento sustentável. E isso significa que é uma ameaça pro futuro do nosso planeta e dos nossos países. O desemprego beira 20%, ou seja, 1 em cada 4 portugueses.\r\n', 1, 5, 300, 1398, 1),
(5, 'Notebook 2x1 HP Pavilion x360 11-n225br Pentium Quad Core 4GB 500GB LED 11.6', 'No meu xinélo da humildade eu gostaria muito de ver o Neymar e o Ganso. Por que eu acho que.... 11 entre 10 brasileiros gostariam. Você veja, eu já vi, parei de ver. Voltei a ver, e acho que o Neymar e o Ganso têm essa capacidade de fazer a gente olhar.\r\n\r\nA única área que eu acho, que vai exigir muita atenção nossa, e aí eu já aventei a hipótese de até criar um ministério. É na área de... Na área... Eu diria assim, como uma espécie de analogia com o que acontece na área agrícola.', 3, 50, 1000, 2500, 1);

-- --------------------------------------------------------

--
-- Table structure for table `produtos_destaque`
--

CREATE TABLE `produtos_destaque` (
  `id` int(11) NOT NULL,
  `id_produto1` int(11) NOT NULL,
  `id_produto2` int(11) NOT NULL,
  `id_produto3` int(11) NOT NULL,
  `id_produto4` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Armazena as id''s de até quatro produtos para o destaque';

--
-- Dumping data for table `produtos_destaque`
--

INSERT INTO `produtos_destaque` (`id`, `id_produto1`, `id_produto2`, `id_produto3`, `id_produto4`) VALUES
(1, 0, 0, 0, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`cpf`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `endereco`
--
ALTER TABLE `endereco`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_cliente_cpf` (`cliente_cpf`);

--
-- Indexes for table `forma_pagamento`
--
ALTER TABLE `forma_pagamento`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `funcionario`
--
ALTER TABLE `funcionario`
  ADD PRIMARY KEY (`login`);

--
-- Indexes for table `loja`
--
ALTER TABLE `loja`
  ADD PRIMARY KEY (`cnpj`);

--
-- Indexes for table `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_forma_pagamento_id` (`forma_pagamento_id`),
  ADD KEY `fk_endereco_entrega_id` (`endereco_entrega_id`),
  ADD KEY `cliente_cpf_fk` (`cliente_cpf`);

--
-- Indexes for table `pedido_produto`
--
ALTER TABLE `pedido_produto`
  ADD PRIMARY KEY (`pedido_id`,`produto_id`),
  ADD KEY `fk_produto_id` (`produto_id`);

--
-- Indexes for table `produto`
--
ALTER TABLE `produto`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_categoria_id` (`categoria_id`);

--
-- Indexes for table `produtos_destaque`
--
ALTER TABLE `produtos_destaque`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `endereco`
--
ALTER TABLE `endereco`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `forma_pagamento`
--
ALTER TABLE `forma_pagamento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;
--
-- AUTO_INCREMENT for table `produto`
--
ALTER TABLE `produto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `produtos_destaque`
--
ALTER TABLE `produtos_destaque`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `endereco`
--
ALTER TABLE `endereco`
  ADD CONSTRAINT `fk_cliente_cpf` FOREIGN KEY (`cliente_cpf`) REFERENCES `cliente` (`cpf`);

--
-- Constraints for table `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `cliente_cpf_fk` FOREIGN KEY (`cliente_cpf`) REFERENCES `cliente` (`cpf`),
  ADD CONSTRAINT `fk_endereco_entrega_id` FOREIGN KEY (`endereco_entrega_id`) REFERENCES `endereco` (`id`),
  ADD CONSTRAINT `fk_forma_pagamento_id` FOREIGN KEY (`forma_pagamento_id`) REFERENCES `forma_pagamento` (`id`);

--
-- Constraints for table `pedido_produto`
--
ALTER TABLE `pedido_produto`
  ADD CONSTRAINT `fk_pedido_id` FOREIGN KEY (`pedido_id`) REFERENCES `pedido` (`id`),
  ADD CONSTRAINT `fk_produto_id` FOREIGN KEY (`produto_id`) REFERENCES `produto` (`id`);

--
-- Constraints for table `produto`
--
ALTER TABLE `produto`
  ADD CONSTRAINT `fk_categoria_id` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
