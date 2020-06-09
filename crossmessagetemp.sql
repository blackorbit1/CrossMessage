-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  sam. 28 avr. 2018 à 00:52
-- Version du serveur :  10.1.30-MariaDB
-- Version de PHP :  7.2.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `gomez`
--

-- --------------------------------------------------------

--
-- Structure de la table `friend`
--

CREATE TABLE `friend` (
  `userID` int(11) NOT NULL,
  `friendID` int(11) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Déchargement des données de la table `friend`
--

INSERT INTO `friend` (`userID`, `friendID`, `date`) VALUES
(2, 3, '2018-02-27 21:45:20'),
(4, 3, '2018-04-27 22:08:08');

-- --------------------------------------------------------

--
-- Structure de la table `session`
--

CREATE TABLE `session` (
  `id` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `debut` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fin` timestamp NULL DEFAULT NULL,
  `isRoot` tinyint(1) NOT NULL,
  `cle` varchar(32) COLLATE latin1_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Déchargement des données de la table `session`
--

INSERT INTO `session` (`id`, `userID`, `debut`, `fin`, `isRoot`, `cle`) VALUES
(2, 4, '2018-04-27 22:07:01', NULL, 0, 'eyR17BD9u3NbiEruFvEx0UMS3u4ps4hn'),
(4, 5, '2018-04-27 22:38:54', NULL, 0, 'V9ZRXKPgfkND2vbAI2y0VPZk3KDTLwuX');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `userID` int(11) NOT NULL,
  `login` varchar(64) COLLATE latin1_bin NOT NULL,
  `prenom` varchar(255) COLLATE latin1_bin NOT NULL,
  `nom` varchar(255) COLLATE latin1_bin NOT NULL,
  `password` blob NOT NULL,
  `isRoot` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`userID`, `login`, `prenom`, `nom`, `password`, `isRoot`) VALUES
(1, 'DV', 'Dark', 'Vador', '', 0),
(2, 'jane', 'randomtruc', 'Jane', 0x2a46324538344433454231343939303130334532374639323531334242383534454341413843373237, 0),
(3, 'JD', 'Jean', 'Dupond', 0x2a41344236313537333139303338373234453335363038393446374639333243383838364542464346, 0),
(4, 'EH', 'Hawk', 'Ethan', 0x2a45424339434430344432374236354145414143313245424546463742303645464230414645463732, 0),
(5, 'sparrow', 'Depp', 'Johnny', 0x2a45424339434430344432374236354145414143313245424546463742303645464230414645463732, 0);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `friend`
--
ALTER TABLE `friend`
  ADD PRIMARY KEY (`userID`,`friendID`);

--
-- Index pour la table `session`
--
ALTER TABLE `session`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UNIQUE` (`userID`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userID`),
  ADD UNIQUE KEY `UNIQUE` (`login`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `session`
--
ALTER TABLE `session`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
