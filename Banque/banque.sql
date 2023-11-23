-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  ven. 11 sep. 2020 à 21:48
-- Version du serveur :  10.1.34-MariaDB
-- Version de PHP :  7.2.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `banque`
--

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

CREATE TABLE `compte` (
  `id` int(11) NOT NULL,
  `libelle` varchar(250) DEFAULT NULL,
  `solde` decimal(10,2) DEFAULT NULL,
  `decouvert` decimal(10,2) DEFAULT NULL,
  `taux` decimal(5,2) DEFAULT NULL,
  `utilisateurId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `compte`
--

INSERT INTO `compte` (`id`, `libelle`, `solde`, `decouvert`, `taux`, `utilisateurId`) VALUES
(12, 'Compte Courant', '250.00', NULL, NULL, 1),
(13, 'Compte Courant', '1490.00', '100.00', NULL, 2),
(14, 'Compte Courant', '350.00', '50.00', NULL, 3),
(15, 'Livret A', '9950.00', NULL, '0.10', 1),
(16, 'Compte Remunéré', '510.00', '0.00', '0.30', 2);

-- --------------------------------------------------------

--
-- Structure de la table `operation`
--

CREATE TABLE `operation` (
  `id` int(11) NOT NULL,
  `libelle` varchar(250) DEFAULT NULL,
  `montant` double DEFAULT NULL,
  `date` timestamp NULL DEFAULT NULL,
  `compteId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `operation`
--

INSERT INTO `operation` (`id`, `libelle`, `montant`, `date`, `compteId`) VALUES
(1, 'Virement', 500, '2014-12-31 23:00:00', 15),
(2, 'Retrait', -500, '2014-12-31 23:00:00', 12),
(3, 'Transaction avec le comte 12', -50, '2015-02-13 13:10:52', 15),
(4, 'Transaction avec le comte 15', 50, '2015-02-13 13:10:52', 12),
(5, 'Transaction avec le comte 16', -10, '2019-10-20 16:51:43', 13),
(6, 'Transaction avec le comte 13', 10, '2019-10-20 16:51:43', 16);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id` int(11) NOT NULL,
  `login` varchar(120) NOT NULL,
  `password` varchar(120) NOT NULL,
  `nom` varchar(120) DEFAULT NULL,
  `prenom` varchar(120) DEFAULT NULL,
  `sex` tinyint(1) DEFAULT NULL,
  `derniereConnection` timestamp NULL DEFAULT NULL,
  `dateDeNaissance` date DEFAULT NULL,
  `adresse` varchar(500) DEFAULT NULL,
  `codePostal` int(11) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `login`, `password`, `nom`, `prenom`, `sex`, `derniereConnection`, `dateDeNaissance`, `adresse`, `codePostal`, `telephone`) VALUES
(1, 'df', 'df', 'Fargis', 'Denis', 0, NULL, '1975-09-19', NULL, 78140, NULL),
(2, 'dj', 'dj', 'Fanael', 'Julie', 1, NULL, NULL, NULL, NULL, NULL),
(3, 'ip', 'ip', 'Iaris', 'Paul', 0, NULL, NULL, NULL, NULL, NULL);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `compte`
--
ALTER TABLE `compte`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_compte_utilisateur_idx` (`utilisateurId`);

--
-- Index pour la table `operation`
--
ALTER TABLE `operation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_operation_compte1_idx` (`compteId`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `compte`
--
ALTER TABLE `compte`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT pour la table `operation`
--
ALTER TABLE `operation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `compte`
--
ALTER TABLE `compte`
  ADD CONSTRAINT `fk_compte_utilisateur` FOREIGN KEY (`utilisateurId`) REFERENCES `utilisateur` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `operation`
--
ALTER TABLE `operation`
  ADD CONSTRAINT `fk_operation_compte1` FOREIGN KEY (`compteId`) REFERENCES `compte` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
