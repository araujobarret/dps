-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 20-Set-2016 às 03:56
-- Versão do servidor: 10.1.9-MariaDB
-- PHP Version: 5.6.15

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
-- Estrutura da tabela `categoria`
--

CREATE TABLE `categoria` (
  `id` int(11) NOT NULL,
  `descricao` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `categoria`
--

INSERT INTO `categoria` (`id`, `descricao`) VALUES
(1, 'Tablet'),
(2, 'Celular'),
(3, 'Notebook'),
(4, 'Computador');

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
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
-- Extraindo dados da tabela `cliente`
--

INSERT INTO `cliente` (`senha`, `nome`, `cpf`, `data_nascimento`, `email`, `telefone1`, `telefone2`, `telefone3`) VALUES
('698dc19d489c4e4db73e28a713eab07b', 'teste', 12, 'teste', 'teste@mail.com', 'teste', 'teste', 'teste'),
('37693cfc748049e45d87b8c7d8b9aacd', 'alfredo', 123, '23', 'alfredo@mail.com', '12312', '23', '23');

-- --------------------------------------------------------

--
-- Estrutura da tabela `endereco`
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
-- Extraindo dados da tabela `endereco`
--

INSERT INTO `endereco` (`id`, `cliente_cpf`, `descricao`, `logradouro`, `numero`, `complemento`, `bairro`, `cidade`, `uf`, `cep`) VALUES
(1, 123, '23', '23', '23', '23', '23', '23', '23', '23'),
(2, 12, 'Teste2', 'teste', 'vteste', 'teste', 'teste', 'teste', 'te', 'teste'),
(3, 12, 'Residencial 2', 'teste', 'teste', 'teste', 'teste', 'teste', 'te', 'teste');

-- --------------------------------------------------------

--
-- Estrutura da tabela `forma_pagamento`
--

CREATE TABLE `forma_pagamento` (
  `id` int(11) NOT NULL,
  `descricao` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `forma_pagamento`
--

INSERT INTO `forma_pagamento` (`id`, `descricao`) VALUES
(1, 'boleto'),
(2, 'Crédito 1x');

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcionario`
--

CREATE TABLE `funcionario` (
  `login` varchar(20) NOT NULL,
  `nome` varchar(80) NOT NULL,
  `senha` varchar(100) NOT NULL,
  `email` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `funcionario`
--

INSERT INTO `funcionario` (`login`, `nome`, `senha`, `email`) VALUES
('admin', 'admin', '21232f297a57a5a743894a0e4a801fc3', 'admin@mail.com'),
('teste2', 'teste2', '38851536d87701d2191990e24a7f8d4e', 'teste@mail.com');

-- --------------------------------------------------------

--
-- Estrutura da tabela `loja`
--

CREATE TABLE `loja` (
  `nome` varchar(80) NOT NULL,
  `cnpj` int(11) NOT NULL,
  `logo` varchar(100) NOT NULL,
  `cores` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='cores terão apenas 4 padrões(azul, verde, laranja, vermelho)';

-- --------------------------------------------------------

--
-- Estrutura da tabela `pedido`
--

CREATE TABLE `pedido` (
  `id` int(11) NOT NULL,
  `data_pedido` date NOT NULL,
  `cliente_cpf` int(11) NOT NULL,
  `endereco_entrega_id` int(11) NOT NULL,
  `forma_pagamento_id` int(11) NOT NULL,
  `frete` decimal(10,2) NOT NULL,
  `total` decimal(10,2) NOT NULL,
  `status` char(1) NOT NULL COMMENT '0 - pendente, 1 - liberado, 2 - Cancelado'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `pedido`
--

INSERT INTO `pedido` (`id`, `data_pedido`, `cliente_cpf`, `endereco_entrega_id`, `forma_pagamento_id`, `frete`, `total`, `status`) VALUES
(1, '2016-09-19', 12, 3, 1, '0.00', '3898.00', '0'),
(2, '2016-09-19', 12, 2, 1, '0.00', '2149.00', '0');

-- --------------------------------------------------------

--
-- Estrutura da tabela `pedido_produto`
--

CREATE TABLE `pedido_produto` (
  `produto_id` int(11) NOT NULL,
  `pedido_id` int(11) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `valor_unitario` decimal(10,2) NOT NULL,
  `valor_total` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `produto`
--

CREATE TABLE `produto` (
  `id` int(11) NOT NULL,
  `descricao` varchar(80) NOT NULL,
  `caracteristicas` text NOT NULL,
  `categoria_id` int(11) NOT NULL,
  `quantidade_estoque` int(11) NOT NULL,
  `preco_custo` decimal(10,2) NOT NULL,
  `preco_venda` decimal(10,2) NOT NULL,
  `status` tinyint(1) NOT NULL COMMENT 'controla se o produto está liberado pra venda'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `produto`
--

INSERT INTO `produto` (`id`, `descricao`, `caracteristicas`, `categoria_id`, `quantidade_estoque`, `preco_custo`, `preco_venda`, `status`) VALUES
(1, 'Smartphone Moto G 4 Plus Dual Chip Android 6.0 Tela 5.5 32GB', 'Primeiro eu queria cumprimentar os internautas. -Oi Internautas! Depois dizer que o meio ambiente é sem dúvida nenhuma uma ameaça ao desenvolvimento sustentável. E isso significa que é uma ameaça pro futuro do nosso planeta e dos nossos países. O desemprego beira 20%, ou seja, 1 em cada 4 portugueses.', 2, 10, '400.00', '1499.00', 1),
(2, 'Smartphone Moto G 4 Dual Chip Android 6.0 Tela 5.5'''' 16GB Câmera 13MP - Preto', 'Eu dou dinheiro pra minha filha. Eu dou dinheiro pra ela viajar, então é... é... Já vivi muito sem dinheiro, já vivi muito com dinheiro. -Jornalista: Coloca esse dinheiro na poupança que a senhora ganha R$10 mil por mês. -Dilma: O que que é R$10 mil?\r\n\r\nSe hoje é o dia das crianças... Ontem eu disse: o dia da criança é o dia da mãe, dos pais, das professoras, mas também é o dia dos animais, sempre que você olha uma criança, há sempre uma figura oculta, que é um cachorro atrás. O que é algo muito importante!', 2, 100, '300.00', '1299.00', 1),
(3, 'Tablet Samsung Galaxy Tab E T560 8GB Wi-Fi Tela 9.6" Android 4.4 Quad-Core', 'Primeiro eu queria cumprimentar os internautas. -Oi Internautas! Depois dizer que o meio ambiente é sem dúvida nenhuma uma ameaça ao desenvolvimento sustentável. E isso significa que é uma ameaça pro futuro do nosso planeta e dos nossos países. O desemprego beira 20%, ou seja, 1 em cada 4 portugueses.\r\n', 1, 5, '200.00', '850.00', 1),
(4, 'Tablet Samsung Galaxy Tab A P550 16GB Wi-Fi Tela 9.7" Android 5.0 Quad-Core', 'Primeiro eu queria cumprimentar os internautas. -Oi Internautas! Depois dizer que o meio ambiente é sem dúvida nenhuma uma ameaça ao desenvolvimento sustentável. E isso significa que é uma ameaça pro futuro do nosso planeta e dos nossos países. O desemprego beira 20%, ou seja, 1 em cada 4 portugueses.\r\n', 1, 5, '300.00', '1398.00', 1),
(5, 'Notebook 2x1 HP Pavilion x360 11-n225br Pentium Quad Core 4GB 500GB LED 11.6', 'No meu xinélo da humildade eu gostaria muito de ver o Neymar e o Ganso. Por que eu acho que.... 11 entre 10 brasileiros gostariam. Você veja, eu já vi, parei de ver. Voltei a ver, e acho que o Neymar e o Ganso têm essa capacidade de fazer a gente olhar.\r\n\r\nA única área que eu acho, que vai exigir muita atenção nossa, e aí eu já aventei a hipótese de até criar um ministério. É na área de... Na área... Eu diria assim, como uma espécie de analogia com o que acontece na área agrícola.', 3, 50, '1000.00', '2500.00', 1);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `forma_pagamento`
--
ALTER TABLE `forma_pagamento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `produto`
--
ALTER TABLE `produto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `endereco`
--
ALTER TABLE `endereco`
  ADD CONSTRAINT `fk_cliente_cpf` FOREIGN KEY (`cliente_cpf`) REFERENCES `cliente` (`cpf`);

--
-- Limitadores para a tabela `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `cliente_cpf_fk` FOREIGN KEY (`cliente_cpf`) REFERENCES `cliente` (`cpf`),
  ADD CONSTRAINT `fk_endereco_entrega_id` FOREIGN KEY (`endereco_entrega_id`) REFERENCES `endereco` (`id`),
  ADD CONSTRAINT `fk_forma_pagamento_id` FOREIGN KEY (`forma_pagamento_id`) REFERENCES `forma_pagamento` (`id`);

--
-- Limitadores para a tabela `pedido_produto`
--
ALTER TABLE `pedido_produto`
  ADD CONSTRAINT `fk_pedido_id` FOREIGN KEY (`pedido_id`) REFERENCES `pedido` (`id`),
  ADD CONSTRAINT `fk_produto_id` FOREIGN KEY (`produto_id`) REFERENCES `produto` (`id`);

--
-- Limitadores para a tabela `produto`
--
ALTER TABLE `produto`
  ADD CONSTRAINT `fk_categoria_id` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
