-- phpMyAdmin SQL Dump
-- version 4.8.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  mer. 12 sep. 2018 à 17:34
-- Version du serveur :  10.1.33-MariaDB
-- Version de PHP :  7.2.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `base_loncoto`
--

-- --------------------------------------------------------

--
-- Structure de la table `article`
--

CREATE TABLE `article` (
  `id` int(11) NOT NULL,
  `date_creation` date DEFAULT NULL,
  `marque` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `nom` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `catalogue_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Déchargement des données de la table `article`
--

INSERT INTO `article` (`id`, `date_creation`, `marque`, `nom`, `catalogue_id`) VALUES
(1, '2018-06-05', 'Xmark', 'Servers 4P 128GORam', 1),
(2, '2018-05-25', 'Xmark', 'NetScreen Lux', 1),
(3, '2018-05-02', 'Xmark', 'OlUt9 DatBase', 1),
(4, '2018-09-04', 'Parrot', 'MS 45 WideScreen', 1),
(5, '2018-06-06', 'Parrot', 'HZ Serv', 1),
(6, '2017-07-18', 'Appeul', 'Imprimante DST78 2015', 1),
(7, '2018-09-05', 'Amazen', 'Bloc de coloriage pour enfants', 1);

-- --------------------------------------------------------

--
-- Structure de la table `catalogue`
--

CREATE TABLE `catalogue` (
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Déchargement des données de la table `catalogue`
--

INSERT INTO `catalogue` (`id`) VALUES
(1);

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `id` int(11) NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `nbsites` int(11) NOT NULL,
  `nom` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id`, `email`, `nbsites`, `nom`) VALUES
(1, 'contact@atol-software.com', 2, 'Atole-Software'),
(2, 'support@techno-manag.fr', 0, 'Le Management techno'),
(3, 'louis.dubois@sfr.fr', 1, 'Louis Dubois SARL'),
(4, 'contact.maintenance@kpjm.com', 11, 'KPJM'),
(5, 'hardware-valley@gmail.com', 6, 'Hardware Valley'),
(6, 'gaston@lagaffe.org', 2, 'Gaston Lagaffe');

-- --------------------------------------------------------

--
-- Structure de la table `client_sites`
--

CREATE TABLE `client_sites` (
  `clients_id` int(11) NOT NULL,
  `sites_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Déchargement des données de la table `client_sites`
--

INSERT INTO `client_sites` (`clients_id`, `sites_id`) VALUES
(1, 3),
(1, 4),
(3, 1),
(4, 2),
(4, 3),
(4, 5),
(5, 3),
(5, 4),
(6, 1),
(6, 4),
(6, 5);

-- --------------------------------------------------------

--
-- Structure de la table `groupe_intervenant`
--

CREATE TABLE `groupe_intervenant` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Déchargement des données de la table `groupe_intervenant`
--

INSERT INTO `groupe_intervenant` (`id`, `nom`) VALUES
(1, 'A'),
(2, 'B'),
(3, 'C'),
(4, 'D');

-- --------------------------------------------------------

--
-- Structure de la table `intervenant`
--

CREATE TABLE `intervenant` (
  `id` int(11) NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `nom` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `prenom` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Déchargement des données de la table `intervenant`
--

INSERT INTO `intervenant` (`id`, `email`, `nom`, `prenom`) VALUES
(1, 'emilien.gentil@loncoto.fr', 'Gentil', 'Emilien'),
(2, 'cedric.kelly@loncoto.fr', 'Kelly', 'Cédric'),
(3, 'cara.stevens@loncoto.fr', 'Stevens', 'Cara'),
(4, 'fiona.green@loncoto.fr', 'Green', 'Fiona'),
(5, 'garett.winter@loncoto.fr', 'Winter', 'Garett'),
(6, 'sakura.yamamoto@loncoto.fr', 'Yamamoto', 'Sakura'),
(7, 'jackson.bradshaw@loncoto.fr', 'Jackson', 'Bradshaw'),
(8, 'steve.chikh@loncoto.fr', 'Chikh', 'Steve');

-- --------------------------------------------------------

--
-- Structure de la table `intervenant_groupe_intervenants`
--

CREATE TABLE `intervenant_groupe_intervenants` (
  `intervenants_id` int(11) NOT NULL,
  `groupe_intervenants_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Déchargement des données de la table `intervenant_groupe_intervenants`
--

INSERT INTO `intervenant_groupe_intervenants` (`intervenants_id`, `groupe_intervenants_id`) VALUES
(1, 1),
(1, 3),
(1, 4),
(2, 4),
(3, 1),
(3, 2),
(4, 1),
(5, 2),
(5, 3),
(6, 2),
(6, 3),
(6, 4),
(7, 1),
(7, 2);

-- --------------------------------------------------------

--
-- Structure de la table `intervention`
--

CREATE TABLE `intervention` (
  `id` int(11) NOT NULL,
  `commentaire` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `statut` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `intervenant_id` int(11) DEFAULT NULL,
  `materiel_id` int(11) DEFAULT NULL,
  `end` date DEFAULT NULL,
  `start` date DEFAULT NULL,
  `title` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `color` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Déchargement des données de la table `intervention`
--

INSERT INTO `intervention` (`id`, `commentaire`, `statut`, `intervenant_id`, `materiel_id`, `end`, `start`, `title`, `color`) VALUES
(1, 'R A S', 'Terminée', 8, 23, '2018-09-06', '2018-09-06', 'intervention1', 'green'),
(2, 'Fil endommagé', 'Terminée', 6, 7, '2018-09-08', '2018-09-07', 'event2', 'green'),
(3, 'Ecran cassé', 'En attente d\'intervention', 1, 2, '2018-09-30', '2018-09-29', 'un long moment', 'red'),
(4, 'Fausse alerte', 'Terminée', 7, 18, '2018-09-02', '2018-09-02', 'oui d\'office', 'green'),
(6, '', 'En Attente', 5, 18, '2018-09-30', '2018-09-30', 'srtyhsery', 'red'),
(7, 'xfguxfgu', 'Reprogrammée', 3, 7, '2018-09-14', '2018-09-14', 'Intervention de CaraStevens sur Appeul- Site : Complexe Spatiale', 'orange'),
(8, '', 'En Attente', 6, 19, '2018-09-16', '2018-09-16', 'Intervention de SakuraYamamoto sur Amazen- Site : D.E.G Adventura', 'red'),
(63, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 2, 1, '2018-09-11', '2018-09-11', 'Intervention Preventive surOlUt9 DatBase', '#b67fc7'),
(64, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 2, 2, '2018-09-28', '2018-09-28', 'Intervention Preventive surOlUt9 DatBase', '#b67fc7'),
(65, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 8, 3, '2018-09-19', '2018-09-19', 'Intervention Preventive surOlUt9 DatBase', '#b67fc7'),
(66, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 4, 4, '2018-09-01', '2018-09-01', 'Intervention Preventive surOlUt9 DatBase', '#b67fc7'),
(67, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 4, 6, '2018-09-11', '2018-09-11', 'Intervention Preventive surImprimante DST78 2015', '#b67fc7'),
(68, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 6, 5, '2018-09-23', '2018-09-23', 'Intervention Preventive surMS 45 WideScreen', '#b67fc7'),
(69, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 8, 7, '2018-09-18', '2018-09-18', 'Intervention Preventive surImprimante DST78 2015', '#b67fc7'),
(70, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 5, 8, '2018-09-17', '2018-09-17', 'Intervention Preventive surServers 4P 128GORam', '#b67fc7'),
(71, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 2, 10, '2018-09-19', '2018-09-19', 'Intervention Preventive surServers 4P 128GORam', '#b67fc7'),
(72, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 7, 9, '2018-09-12', '2018-09-12', 'Intervention Preventive surNetScreen Lux', '#b67fc7'),
(73, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 1, 12, '2018-09-14', '2018-09-14', 'Intervention Preventive surNetScreen Lux', '#b67fc7'),
(74, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 1, 11, '2018-09-01', '2018-09-01', 'Intervention Preventive surBloc de coloriage pour enfants', '#b67fc7'),
(75, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 2, 13, '2018-09-12', '2018-09-12', 'Intervention Preventive surOlUt9 DatBase', '#b67fc7'),
(76, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 7, 14, '2018-09-09', '2018-09-09', 'Intervention Preventive surServers 4P 128GORam', '#b67fc7'),
(77, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 8, 15, '2018-09-17', '2018-09-17', 'Intervention Preventive surImprimante DST78 2015', '#b67fc7'),
(78, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 6, 16, '2018-09-07', '2018-09-07', 'Intervention Preventive surServers 4P 128GORam', '#b67fc7'),
(79, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 4, 17, '2018-09-29', '2018-09-29', 'Intervention Preventive surHZ Serv', '#b67fc7'),
(80, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 8, 18, '2018-09-11', '2018-09-11', 'Intervention Preventive surServers 4P 128GORam', '#b67fc7'),
(81, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 3, 19, '2018-09-28', '2018-09-28', 'Intervention Preventive surBloc de coloriage pour enfants', '#b67fc7'),
(82, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 5, 20, '2018-09-02', '2018-09-02', 'Intervention Preventive surNetScreen Lux', '#b67fc7'),
(83, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 2, 21, '2018-09-14', '2018-09-14', 'Intervention Preventive surOlUt9 DatBase', '#b67fc7'),
(84, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 3, 22, '2018-09-12', '2018-09-12', 'Intervention Preventive surHZ Serv', '#b67fc7'),
(85, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 1, 25, '2018-09-29', '2018-09-29', 'Intervention Preventive surMS 45 WideScreen', '#b67fc7'),
(86, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 6, 23, '2018-09-29', '2018-09-29', 'Intervention Preventive surHZ Serv', '#b67fc7'),
(87, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 6, 24, '2018-09-10', '2018-09-10', 'Intervention Preventive surMS 45 WideScreen', '#b67fc7'),
(88, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 3, 26, '2018-09-08', '2018-09-08', 'Intervention Preventive surServers 4P 128GORam', '#b67fc7'),
(89, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 1, 27, '2018-09-13', '2018-09-13', 'Intervention Preventive surServers 4P 128GORam', '#b67fc7'),
(90, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 8, 4, '2019-03-07', '2019-03-07', 'Intervention Preventive surOlUt9 DatBase', '#b67fc7'),
(91, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 8, 3, '2019-02-13', '2019-02-13', 'Intervention Preventive surOlUt9 DatBase', '#b67fc7'),
(92, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 2, 1, '2019-04-09', '2019-04-09', 'Intervention Preventive surOlUt9 DatBase', '#b67fc7'),
(93, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 2, 2, '2019-03-10', '2019-03-10', 'Intervention Preventive surOlUt9 DatBase', '#b67fc7'),
(94, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 3, 5, '2019-02-09', '2019-02-09', 'Intervention Preventive surMS 45 WideScreen', '#b67fc7'),
(95, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 2, 6, '2019-02-21', '2019-02-21', 'Intervention Preventive surImprimante DST78 2015', '#b67fc7'),
(96, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 7, 9, '2019-02-22', '2019-02-22', 'Intervention Preventive surNetScreen Lux', '#b67fc7'),
(97, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 7, 10, '2019-04-09', '2019-04-09', 'Intervention Preventive surServers 4P 128GORam', '#b67fc7'),
(98, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 2, 8, '2019-02-16', '2019-02-16', 'Intervention Preventive surServers 4P 128GORam', '#b67fc7'),
(99, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 5, 7, '2019-02-04', '2019-02-04', 'Intervention Preventive surImprimante DST78 2015', '#b67fc7'),
(100, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 4, 12, '2019-03-12', '2019-03-12', 'Intervention Preventive surNetScreen Lux', '#b67fc7'),
(101, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 1, 11, '2019-02-03', '2019-02-03', 'Intervention Preventive surBloc de coloriage pour enfants', '#b67fc7'),
(102, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 2, 14, '2019-03-10', '2019-03-10', 'Intervention Preventive surServers 4P 128GORam', '#b67fc7'),
(103, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 4, 15, '2019-03-20', '2019-03-20', 'Intervention Preventive surImprimante DST78 2015', '#b67fc7'),
(104, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 5, 13, '2019-01-13', '2019-01-13', 'Intervention Preventive surOlUt9 DatBase', '#b67fc7'),
(105, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 5, 17, '2019-01-19', '2019-01-19', 'Intervention Preventive surHZ Serv', '#b67fc7'),
(106, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 3, 16, '2019-02-08', '2019-02-08', 'Intervention Preventive surServers 4P 128GORam', '#b67fc7'),
(107, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 5, 19, '2019-02-23', '2019-02-23', 'Intervention Preventive surBloc de coloriage pour enfants', '#b67fc7'),
(108, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 4, 18, '2019-04-04', '2019-04-04', 'Intervention Preventive surServers 4P 128GORam', '#b67fc7'),
(109, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 1, 20, '2019-02-09', '2019-02-09', 'Intervention Preventive surNetScreen Lux', '#b67fc7'),
(110, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 1, 21, '2019-03-07', '2019-03-07', 'Intervention Preventive surOlUt9 DatBase', '#b67fc7'),
(111, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 4, 24, '2019-01-23', '2019-01-23', 'Intervention Preventive surMS 45 WideScreen', '#b67fc7'),
(112, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 1, 22, '2019-03-09', '2019-03-09', 'Intervention Preventive surHZ Serv', '#b67fc7'),
(113, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 4, 23, '2019-03-27', '2019-03-27', 'Intervention Preventive surHZ Serv', '#b67fc7'),
(114, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 5, 25, '2019-02-22', '2019-02-22', 'Intervention Preventive surMS 45 WideScreen', '#b67fc7'),
(115, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 3, 26, '2019-01-17', '2019-01-17', 'Intervention Preventive surServers 4P 128GORam', '#b67fc7'),
(116, 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi, iusto est nemo earum velit abVoluptatibus incidunt architecto eius sapiente laboriosam aut dolores velit repellendus assumendatenetur! Hic, nisi necessitatibus!', 'Préventive', 1, 27, '2019-01-18', '2019-01-18', 'Intervention Preventive surServers 4P 128GORam', '#b67fc7');

-- --------------------------------------------------------

--
-- Structure de la table `materiel`
--

CREATE TABLE `materiel` (
  `id` int(11) NOT NULL,
  `serialid` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `etat` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `article_id` int(11) DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  `lesite_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Déchargement des données de la table `materiel`
--

INSERT INTO `materiel` (`id`, `serialid`, `etat`, `article_id`, `client_id`, `lesite_id`) VALUES
(1, '20000001541', 'bon', 3, 3, 1),
(2, '20000001542', 'bon', 3, 3, 1),
(3, '20000001543', 'bon', 3, 3, 1),
(4, '200000015414', 'bon', 3, 6, 1),
(5, '200000015415', 'bon', 4, 6, 1),
(6, '200000015445', 'bon', 6, 4, 2),
(7, '20000001578', 'bon', 6, 4, 2),
(8, '20000001565', 'bon', 1, 4, 2),
(9, '20000001533', 'bon', 2, 4, 2),
(10, '20000001511', 'bon', 1, 4, 2),
(11, '456000012443', 'bon', 7, 1, 3),
(12, '4560000124556', 'bon', 2, 1, 3),
(13, '456000012251', 'bon', 3, 2, 3),
(14, '856000012446', 'bon', 1, 2, 3),
(15, '4875yh12543', 'bon', 6, 4, 3),
(16, '4560000145l6', 'bon', 1, 5, 3),
(17, '02102114', 'bon', 5, 4, 3),
(18, '02102138', 'bon', 1, 4, 3),
(19, '02102137', 'bon', 7, 4, 3),
(20, '02102139', 'bon', 2, 4, 3),
(21, '02102165', 'bon', 3, 4, 3),
(22, '02102136', 'bon', 5, 4, 3),
(23, '916480LLHN', 'bon', 5, 1, 4),
(24, '516480253po', 'bon', 4, 5, 4),
(25, '916480253de', 'bon', 4, 6, 4),
(26, '96659KJH78', 'bon', 1, 6, 5),
(27, 'POI4548cl', 'bon', 1, 4, 5);

-- --------------------------------------------------------

--
-- Structure de la table `site`
--

CREATE TABLE `site` (
  `id` int(11) NOT NULL,
  `adresse` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `nom` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Déchargement des données de la table `site`
--

INSERT INTO `site` (`id`, `adresse`, `nom`) VALUES
(1, '3 rue pomme 45100 Chalons', 'Le site du Soleil'),
(2, '845 avenue de Longfroid, Bâtiment B, 05210 St-Raphael', 'Complexe Spatiale'),
(3, 'Lieu-dit \"les sables mouvants\" , 78150 Jouets-ville', 'D.E.G Adventura'),
(4, '97 rue des minimes, 31400 Toulouse', 'Tech Zone'),
(5, '142 avenue des jardins, 23000 Nice', 'P.M.E.F');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `article`
--
ALTER TABLE `article`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKheecxhpykc6qo7fvrynw6vbxr` (`catalogue_id`);

--
-- Index pour la table `catalogue`
--
ALTER TABLE `catalogue`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `client_sites`
--
ALTER TABLE `client_sites`
  ADD PRIMARY KEY (`clients_id`,`sites_id`),
  ADD KEY `FKhih6wi4y999yc4s81m0i2yfok` (`sites_id`);

--
-- Index pour la table `groupe_intervenant`
--
ALTER TABLE `groupe_intervenant`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `intervenant`
--
ALTER TABLE `intervenant`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `intervenant_groupe_intervenants`
--
ALTER TABLE `intervenant_groupe_intervenants`
  ADD PRIMARY KEY (`intervenants_id`,`groupe_intervenants_id`),
  ADD KEY `FK88pdg3bsatqbqmk4d8uolpn6j` (`groupe_intervenants_id`);

--
-- Index pour la table `intervention`
--
ALTER TABLE `intervention`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1gq45xrtd7j688juiuto7vs3x` (`intervenant_id`),
  ADD KEY `FK9kenlchr3krdac5pd53nre1ia` (`materiel_id`);

--
-- Index pour la table `materiel`
--
ALTER TABLE `materiel`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKn4syf6r9mesx5gngvx85jqr5i` (`article_id`),
  ADD KEY `FKe1pyrhny75x6wvyjh05i39bm9` (`client_id`),
  ADD KEY `FK7cjjns29k6va3ltf77ntqafjt` (`lesite_id`);

--
-- Index pour la table `site`
--
ALTER TABLE `site`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `article`
--
ALTER TABLE `article`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `catalogue`
--
ALTER TABLE `catalogue`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `groupe_intervenant`
--
ALTER TABLE `groupe_intervenant`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `intervenant`
--
ALTER TABLE `intervenant`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `intervention`
--
ALTER TABLE `intervention`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=117;

--
-- AUTO_INCREMENT pour la table `materiel`
--
ALTER TABLE `materiel`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT pour la table `site`
--
ALTER TABLE `site`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `article`
--
ALTER TABLE `article`
  ADD CONSTRAINT `FKheecxhpykc6qo7fvrynw6vbxr` FOREIGN KEY (`catalogue_id`) REFERENCES `catalogue` (`id`);

--
-- Contraintes pour la table `client_sites`
--
ALTER TABLE `client_sites`
  ADD CONSTRAINT `FK2mimln7fyonw9iqm0nbhqd783` FOREIGN KEY (`clients_id`) REFERENCES `client` (`id`),
  ADD CONSTRAINT `FKhih6wi4y999yc4s81m0i2yfok` FOREIGN KEY (`sites_id`) REFERENCES `site` (`id`);

--
-- Contraintes pour la table `intervenant_groupe_intervenants`
--
ALTER TABLE `intervenant_groupe_intervenants`
  ADD CONSTRAINT `FK88pdg3bsatqbqmk4d8uolpn6j` FOREIGN KEY (`groupe_intervenants_id`) REFERENCES `groupe_intervenant` (`id`),
  ADD CONSTRAINT `FKapllyfjvj6feg1mhn84lpqdih` FOREIGN KEY (`intervenants_id`) REFERENCES `intervenant` (`id`);

--
-- Contraintes pour la table `intervention`
--
ALTER TABLE `intervention`
  ADD CONSTRAINT `FK1gq45xrtd7j688juiuto7vs3x` FOREIGN KEY (`intervenant_id`) REFERENCES `intervenant` (`id`),
  ADD CONSTRAINT `FK9kenlchr3krdac5pd53nre1ia` FOREIGN KEY (`materiel_id`) REFERENCES `materiel` (`id`);

--
-- Contraintes pour la table `materiel`
--
ALTER TABLE `materiel`
  ADD CONSTRAINT `FK7cjjns29k6va3ltf77ntqafjt` FOREIGN KEY (`lesite_id`) REFERENCES `site` (`id`),
  ADD CONSTRAINT `FKe1pyrhny75x6wvyjh05i39bm9` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`),
  ADD CONSTRAINT `FKn4syf6r9mesx5gngvx85jqr5i` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
