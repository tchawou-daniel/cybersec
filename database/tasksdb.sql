-- phpMyAdmin SQL Dump
-- version 4.6.6deb4+deb9u2
-- https://www.phpmyadmin.net/
--
-- Client :  localhost
-- Généré le :  Ven 12 Février 2021 à 17:04
-- Version du serveur :  10.1.47-MariaDB-0+deb9u1
-- Version de PHP :  7.3.25-1+0~20201130.73+debian9~1.gbp042074

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `tasksdb`
--

-- --------------------------------------------------------

--
-- Structure de la table `t_tasks`
--

CREATE TABLE `t_tasks` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `finished` bit(1) DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `t_tasks`
--

INSERT INTO `t_tasks` (`id`, `name`, `description`, `date_created`, `finished`) VALUES
(14, 'Anglais', '‘ or 1=1;–', '2021-02-12 15:28:20', b'0'),
(16, 'Maths', 'maths   ', '2021-02-12 15:05:38', b'0'),
(17, 'Prog fonctionnelle', 'programmation   fonctionnelle', '2021-02-12 15:09:37', b'0'),
(18, 'cybersécurité  ', 'cybersécuritéeraerazerzaer', '2021-02-12 15:36:04', b'0'),
(19, 'Programmation avancée', 'maths   ', '2021-02-10 14:38:58', b'0'),
(20, 'DevOPs', 'DevOPs   ', '2021-02-12 15:06:04', b'0'),
(21, 'Prog fonctionnelle', 'projet‘    table t_tasks', '2021-02-12 15:10:59', b'0');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `t_tasks`
--
ALTER TABLE `t_tasks`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `t_tasks`
--
ALTER TABLE `t_tasks`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
