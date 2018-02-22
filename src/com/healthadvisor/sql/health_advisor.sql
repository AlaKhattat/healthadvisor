-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Jeu 22 Février 2018 à 05:00
-- Version du serveur :  5.7.14
-- Version de PHP :  5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `health_advisor`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

CREATE TABLE `admin` (
  `LOGIN_ADMIN` varchar(255) COLLATE utf8_bin NOT NULL,
  `PASSWORD_ADMIN` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `aliment`
--

CREATE TABLE `aliment` (
  `NOM_ALIMENT` varchar(255) COLLATE utf8_bin NOT NULL,
  `QUANTITE` float NOT NULL,
  `VALEUR_ENERGETIQUE` float DEFAULT NULL,
  `TYPE_ALIMENT` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Contenu de la table `aliment`
--

INSERT INTO `aliment` (`NOM_ALIMENT`, `QUANTITE`, `VALEUR_ENERGETIQUE`, `TYPE_ALIMENT`) VALUES
('Boeuf_steak', 85, 67, 'viandes ouefs matiereGrasse '),
('Pain_vegan', 55, 22, 'cereales pains legumes '),
('beurre', 1, NULL, 'laitier'),
('café', 1, NULL, 'cacao'),
('cake', 55, 26, 'cereales noix '),
('chocolat', 1, NULL, 'cacao'),
('escaloppe', 55, 26, 'volailles ouefs '),
('fromage blanc', 1, NULL, 'laitier'),
('fruits de mer', 1, NULL, 'fruits'),
('fruits secs', 1, NULL, 'fruits'),
('haricots vert', 1, NULL, 'legumes'),
('noix', 1, NULL, 'noix'),
('oeuf', 1, NULL, 'oeufs'),
('oranges', 1, NULL, 'fruits'),
('pain', 1, NULL, 'pains'),
('pates', 1, NULL, 'cereales'),
('poisson', 1, NULL, 'poissons'),
('poulet', 1, NULL, 'volailles'),
('riz', 1, NULL, 'cereales'),
('thé', 1, NULL, 'thes'),
('viande maigre', 1, NULL, 'viandes'),
('viande rouges', 1, NULL, 'viandes');

-- --------------------------------------------------------

--
-- Structure de la table `aliment_regime`
--

CREATE TABLE `aliment_regime` (
  `NOM_ALIMENT` varchar(255) COLLATE utf8_bin NOT NULL,
  `ID_REGIME` varchar(255) COLLATE utf8_bin NOT NULL,
  `allergiesTypeAliment` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Contenu de la table `aliment_regime`
--

INSERT INTO `aliment_regime` (`NOM_ALIMENT`, `ID_REGIME`, `allergiesTypeAliment`) VALUES
('beurre', 'micronutrition', NULL),
('café', 'micronutrition', NULL),
('fromage blanc', 'micronutrition', NULL),
('fruits de mer', 'micronutrition', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `article`
--

CREATE TABLE `article` (
  `REFERENCE` int(11) NOT NULL,
  `NOM` varchar(255) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(255) COLLATE utf8_bin NOT NULL,
  `CONTENU` varchar(255) COLLATE utf8_bin NOT NULL,
  `ID_MEDECIN` varchar(255) COLLATE utf8_bin NOT NULL,
  `URL_IMAGE` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `body_parts`
--

CREATE TABLE `body_parts` (
  `ID_BODY_PART` int(11) NOT NULL,
  `NOM_BODY_PART` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `body_parts`
--

INSERT INTO `body_parts` (`ID_BODY_PART`, `NOM_BODY_PART`) VALUES
(6, 'Tête, gorge et cou'),
(7, 'Bras et épaule'),
(10, 'Jambes'),
(15, 'Poitrine et dos'),
(16, 'Abdomen, bassin et fesses'),
(17, 'Peau, articulations et général');

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE `commande` (
  `NUMERO_COMMANDE` int(11) NOT NULL,
  `DATE_PAYEMENT` date NOT NULL,
  `MODE_PAYEMENT` varchar(255) COLLATE utf8_bin NOT NULL,
  `ID_CLIENT` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Contenu de la table `commande`
--

INSERT INTO `commande` (`NUMERO_COMMANDE`, `DATE_PAYEMENT`, `MODE_PAYEMENT`, `ID_CLIENT`) VALUES
(1, '2018-02-07', 'dqsdq', 'sdqqsd');

-- --------------------------------------------------------

--
-- Structure de la table `evennement`
--

CREATE TABLE `evennement` (
  `ID` int(11) NOT NULL,
  `NOM` varchar(255) COLLATE utf8_bin NOT NULL,
  `DATE` date NOT NULL,
  `LOCATION` varchar(255) COLLATE utf8_bin NOT NULL,
  `TYPE` varchar(255) COLLATE utf8_bin NOT NULL,
  `MAX_PARTICIPANTS` int(11) NOT NULL,
  `URL_IMAGE` varchar(255) COLLATE utf8_bin NOT NULL,
  `HEURE` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `evennement_participants`
--

CREATE TABLE `evennement_participants` (
  `ID_EVENT` int(11) NOT NULL,
  `ID_USER` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `information_sante`
--

CREATE TABLE `information_sante` (
  `taille` float DEFAULT NULL,
  `poids` float DEFAULT NULL,
  `age` int(11) NOT NULL,
  `allergies` text COLLATE utf8_bin,
  `maladies` text COLLATE utf8_bin,
  `login` varchar(255) COLLATE utf8_bin NOT NULL,
  `sexe` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `ligne_commande`
--

CREATE TABLE `ligne_commande` (
  `ID_PRODUIT` int(11) NOT NULL,
  `ID_COMMANDE` int(11) NOT NULL,
  `PRIX` float NOT NULL,
  `QUANTITE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `medecin`
--

CREATE TABLE `medecin` (
  `LOGIN` varchar(255) COLLATE utf8_bin NOT NULL,
  `SPECIALITE` varchar(255) COLLATE utf8_bin NOT NULL,
  `ADRESSE` varchar(255) COLLATE utf8_bin NOT NULL,
  `DIPLOME` varchar(255) COLLATE utf8_bin NOT NULL,
  `RATING` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `patient`
--

CREATE TABLE `patient` (
  `LOGIN` varchar(255) COLLATE utf8_bin NOT NULL,
  `PASSWORD` varchar(255) COLLATE utf8_bin NOT NULL,
  `CIN_USER` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Contenu de la table `patient`
--

INSERT INTO `patient` (`LOGIN`, `PASSWORD`, `CIN_USER`) VALUES
('sdqqsd', 'qsdq', '05545a');

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE `produit` (
  `ID_PRODUIT` int(11) NOT NULL,
  `REFERENCE` varchar(255) COLLATE utf8_bin NOT NULL,
  `NOM` varchar(255) COLLATE utf8_bin NOT NULL,
  `PRIX_VENTE` float NOT NULL,
  `URL_IMAGE` varchar(255) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(255) COLLATE utf8_bin NOT NULL,
  `TYPE` varchar(255) COLLATE utf8_bin NOT NULL,
  `DATE_MISE` date NOT NULL,
  `PROMOTION` float NOT NULL DEFAULT '0',
  `ID_USER` varchar(255) COLLATE utf8_bin NOT NULL,
  `QUANTITE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Contenu de la table `produit`
--

INSERT INTO `produit` (`ID_PRODUIT`, `REFERENCE`, `NOM`, `PRIX_VENTE`, `URL_IMAGE`, `DESCRIPTION`, `TYPE`, `DATE_MISE`, `PROMOTION`, `ID_USER`, `QUANTITE`) VALUES
(10, '44455', 'produit', 400, 'C:\\wamp64\\www\\HealthAdvisorImages\\para.jpg', '', 'Bien etre', '2018-02-21', 0, '50', 15);

-- --------------------------------------------------------

--
-- Structure de la table `question`
--

CREATE TABLE `question` (
  `ID` int(11) NOT NULL,
  `QUESTION` varchar(255) COLLATE utf8_bin NOT NULL,
  `ID_PATIENT` varchar(255) COLLATE utf8_bin NOT NULL,
  `date_publication` timestamp NOT NULL,
  `modification_etat` tinyint(1) NOT NULL,
  `specialite` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SIGNALISATION_ETAT` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `regime`
--

CREATE TABLE `regime` (
  `ID_REGIME` varchar(255) COLLATE utf8_bin NOT NULL,
  `TYPE` varchar(255) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION_REGIME` text COLLATE utf8_bin NOT NULL,
  `DUREE` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Contenu de la table `regime`
--

INSERT INTO `regime` (`ID_REGIME`, `TYPE`, `DESCRIPTION_REGIME`, `DUREE`) VALUES
('le jeune', 'fasting', 'Le fasting est une pratique qui vient des USA Il s’agit d’un modèle nutritionnel qui permet de mincir et d’avoir de l’énergie au quotidien.\r\nElle consiste a ne rien manger pendant quelques heures.Généralement entre 16 et 24 heures', NULL),
('micronutrition', 'maigreur', 'La micronutrition se concentre sur \\n l\'équilibre des apports énergétiques de chaque personne pour pouvoir personnaliser le régime alimentaire. Elle permet de combler des microcarences et de corriger les excès en apports nutritionnels.', NULL),
('regime hypocalorique', 'maigreur diabetique ', 'le regime hypocalorique est un régime qui vous permet de mangez du tout mais en réduisant les quantités consommée.\r\nle principe de ce régime est de ne pas dépasser 1400 kCalories par jour.', NULL),
('régime dissocié', 'maigreur', 'Le régime dissocié permet de perdre du poids en mangeant uniquement une famille d\'aliments par jour.\r\nLe principe est de manger uniquement une famille d\'aliment  tout en évitant l\'association de certains aliments.', NULL),
('régime hyperprotéiné', 'diabétique maigreur', 'Le régime hyperprotéiné a pour principal effet de provoquer une perte de poids rapide. Il consiste à réduire le taux de glucide et de graisse ce qui va préserver la masse musculaire.', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `regime_sport`
--

CREATE TABLE `regime_sport` (
  `ID_REGIME` varchar(255) COLLATE utf8_bin NOT NULL,
  `NOM_SPORT` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Contenu de la table `regime_sport`
--

INSERT INTO `regime_sport` (`ID_REGIME`, `NOM_SPORT`) VALUES
('micronutrition', 'Exercices Biceps'),
('micronutrition', 'Exercices des Abdominaux');

-- --------------------------------------------------------

--
-- Structure de la table `regime_user`
--

CREATE TABLE `regime_user` (
  `ID_USER` varchar(255) COLLATE utf8_bin NOT NULL,
  `ID_REGIME` varchar(255) COLLATE utf8_bin NOT NULL,
  `ID_SPORT` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DATE_DEBUT` date DEFAULT NULL,
  `DUREE` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `rendez_vous`
--

CREATE TABLE `rendez_vous` (
  `ID` int(11) NOT NULL,
  `DATE_HEURE` datetime NOT NULL,
  `USER_ID` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `reponse`
--

CREATE TABLE `reponse` (
  `ID_REPONSE` int(11) NOT NULL,
  `REPONSE` varchar(255) COLLATE utf8_bin NOT NULL,
  `ID_MEDECIN` varchar(255) COLLATE utf8_bin NOT NULL,
  `ID_QUESTION` int(11) NOT NULL,
  `date_publication` timestamp NOT NULL,
  `modification_etat` tinyint(1) NOT NULL,
  `SIGNALISATION_ETAT` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `reponses_possibles`
--

CREATE TABLE `reponses_possibles` (
  `ID_REPONSE` int(11) NOT NULL,
  `REPONSE` varchar(255) COLLATE utf8_bin NOT NULL,
  `ID_SONDAGE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `sondage`
--

CREATE TABLE `sondage` (
  `ID_SONDAGE` int(11) NOT NULL,
  `NOM_SONDAGE` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `sport`
--

CREATE TABLE `sport` (
  `NOM_SPORT` varchar(255) COLLATE utf8_bin NOT NULL,
  `TYPE` varchar(255) COLLATE utf8_bin NOT NULL,
  `DEPENSE_ENERGETIQUE` float NOT NULL,
  `lienSport` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Contenu de la table `sport`
--

INSERT INTO `sport` (`NOM_SPORT`, `TYPE`, `DEPENSE_ENERGETIQUE`, `lienSport`) VALUES
('Exercices Biceps', 'biceps', 7.19, NULL),
('Exercices des Abdominaux', 'abdo', 98, NULL),
('Exercices des Fessiers', 'fessier', 187.5, NULL),
('Exercices des Poitrine', 'poitrine', 30.9, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `sub_body_parts`
--

CREATE TABLE `sub_body_parts` (
  `ID_SUB_BODY` int(11) NOT NULL,
  `NOM_SUB_BODY` varchar(255) NOT NULL,
  `ID_BODY_PART` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `sub_body_parts`
--

INSERT INTO `sub_body_parts` (`ID_SUB_BODY`, `NOM_SUB_BODY`, `ID_BODY_PART`) VALUES
(21, 'Cheveux et cuir chevelu', 6),
(22, 'Front et tête en général', 6),
(23, 'Visage et yeux', 6),
(24, 'Nez, oreilles, gorge et cou', 6),
(25, 'Bouche et mâchoire', 6),
(26, 'Bras et épaule', 7),
(28, 'Avant-bras et coude', 7),
(29, 'Main et poignet', 7),
(30, 'Doigt', 7),
(31, 'Poitrine', 15),
(32, 'Poitrine latérale', 15),
(33, 'Dos', 15),
(36, 'Abdomen', 16),
(37, 'Bassin', 16),
(38, 'Genitals et aine', 16),
(39, 'Hanches et articulation de la hanche', 16),
(40, 'Fesses et rectum', 16),
(41, 'Cuisse et genou', 10),
(43, 'Jambe inférieure et cheville', 10),
(44, 'Pied', 10),
(45, 'Orteils', 10),
(46, 'Peau', 17),
(47, 'Général, articulations et autres', 17),
(48, 'Bras en général', 7),
(49, 'Jambes en général', 10);

-- --------------------------------------------------------

--
-- Structure de la table `sub_body_parts_symptome`
--

CREATE TABLE `sub_body_parts_symptome` (
  `ID_sub_body_parts_symptome` int(11) NOT NULL,
  `ID_SUB_BODY` int(11) NOT NULL,
  `ID_SYMPTOME` int(11) NOT NULL,
  `gender` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `sub_body_parts_symptome`
--

INSERT INTO `sub_body_parts_symptome` (`ID_sub_body_parts_symptome`, `ID_SUB_BODY`, `ID_SYMPTOME`, `gender`) VALUES
(1, 21, 152, 'man'),
(2, 21, 239, 'man'),
(3, 21, 245, 'man'),
(4, 21, 247, 'man'),
(5, 21, 269, 'man'),
(6, 22, 9, 'man'),
(7, 22, 11, 'man'),
(8, 22, 16, 'man'),
(9, 22, 43, 'man'),
(10, 22, 44, 'man'),
(11, 22, 52, 'man'),
(12, 22, 53, 'man'),
(13, 22, 57, 'man'),
(14, 22, 85, 'man'),
(15, 22, 114, 'man'),
(16, 22, 120, 'man'),
(17, 22, 125, 'man'),
(18, 22, 126, 'man'),
(19, 22, 128, 'man'),
(20, 22, 144, 'man'),
(21, 22, 207, 'man'),
(22, 22, 235, 'man'),
(23, 22, 238, 'man'),
(24, 22, 241, 'man'),
(25, 22, 242, 'man'),
(26, 22, 243, 'man'),
(27, 22, 244, 'man'),
(28, 22, 970, 'man'),
(29, 22, 974, 'man'),
(30, 22, 975, 'man'),
(31, 22, 976, 'man'),
(32, 22, 982, 'man'),
(33, 22, 984, 'man'),
(34, 22, 988, 'man'),
(35, 22, 1007, 'man'),
(36, 23, 24, 'man'),
(37, 23, 33, 'man'),
(38, 23, 57, 'man'),
(39, 23, 66, 'man'),
(40, 23, 68, 'man'),
(41, 23, 69, 'man'),
(42, 23, 70, 'man'),
(43, 23, 71, 'man'),
(44, 23, 72, 'man'),
(45, 23, 73, 'man'),
(46, 23, 75, 'man'),
(47, 23, 76, 'man'),
(48, 23, 77, 'man'),
(49, 23, 106, 'man'),
(50, 23, 129, 'man'),
(51, 23, 137, 'man'),
(52, 23, 166, 'man'),
(53, 23, 170, 'man'),
(54, 23, 176, 'man'),
(55, 23, 208, 'man'),
(56, 23, 209, 'man'),
(57, 23, 210, 'man'),
(58, 23, 211, 'man'),
(59, 23, 213, 'man'),
(60, 23, 219, 'man'),
(61, 23, 242, 'man'),
(62, 23, 243, 'man'),
(63, 23, 244, 'man'),
(64, 23, 246, 'man'),
(65, 23, 258, 'man'),
(66, 23, 270, 'man'),
(67, 23, 273, 'man'),
(68, 23, 286, 'man'),
(69, 23, 287, 'man'),
(70, 23, 288, 'man'),
(71, 23, 970, 'man'),
(72, 24, 13, 'man'),
(73, 24, 14, 'man'),
(74, 24, 15, 'man'),
(75, 24, 28, 'man'),
(76, 24, 30, 'man'),
(77, 24, 38, 'man'),
(78, 24, 40, 'man'),
(79, 24, 45, 'man'),
(80, 24, 46, 'man'),
(81, 24, 78, 'man'),
(82, 24, 86, 'man'),
(83, 24, 87, 'man'),
(84, 24, 88, 'man'),
(85, 24, 90, 'man'),
(86, 24, 93, 'man'),
(87, 24, 95, 'man'),
(88, 24, 96, 'man'),
(89, 24, 98, 'man'),
(90, 24, 102, 'man'),
(91, 24, 121, 'man'),
(92, 24, 122, 'man'),
(93, 24, 133, 'man'),
(94, 24, 136, 'man'),
(95, 24, 153, 'man'),
(96, 24, 169, 'man'),
(97, 24, 203, 'man'),
(98, 24, 234, 'man'),
(99, 24, 288, 'man'),
(100, 24, 984, 'man'),
(101, 24, 988, 'man'),
(102, 25, 15, 'man'),
(103, 25, 35, 'man'),
(104, 25, 40, 'man'),
(105, 25, 49, 'man'),
(106, 25, 54, 'man'),
(107, 25, 62, 'man'),
(108, 25, 64, 'man'),
(109, 25, 93, 'man'),
(110, 25, 97, 'man'),
(111, 25, 98, 'man'),
(112, 25, 101, 'man'),
(113, 25, 122, 'man'),
(114, 25, 126, 'man'),
(115, 25, 131, 'man'),
(116, 25, 133, 'man'),
(117, 25, 135, 'man'),
(118, 25, 153, 'man'),
(119, 25, 181, 'man'),
(120, 25, 203, 'man'),
(121, 25, 204, 'man'),
(122, 25, 205, 'man'),
(123, 25, 233, 'man'),
(124, 25, 243, 'man'),
(125, 25, 272, 'man'),
(126, 25, 286, 'man'),
(127, 25, 970, 'man'),
(128, 25, 973, 'man'),
(129, 25, 977, 'man'),
(130, 25, 980, 'man'),
(131, 25, 991, 'man'),
(132, 25, 1008, 'man'),
(133, 26, 971, 'man'),
(134, 28, 186, 'man'),
(135, 28, 971, 'man'),
(136, 29, 115, 'man'),
(137, 29, 132, 'man'),
(138, 29, 148, 'man'),
(139, 29, 186, 'man'),
(140, 29, 200, 'man'),
(141, 29, 201, 'man'),
(142, 29, 216, 'man'),
(143, 29, 257, 'man'),
(144, 29, 978, 'man'),
(145, 29, 979, 'man'),
(146, 29, 981, 'man'),
(147, 29, 984, 'man'),
(148, 30, 91, 'man'),
(149, 30, 178, 'man'),
(150, 30, 201, 'man'),
(151, 30, 991, 'man'),
(152, 30, 995, 'man'),
(153, 48, 12, 'man'),
(154, 48, 27, 'man'),
(155, 48, 94, 'man'),
(156, 48, 119, 'man'),
(157, 48, 140, 'man'),
(158, 48, 155, 'man'),
(159, 48, 156, 'man'),
(160, 48, 168, 'man'),
(161, 48, 177, 'man'),
(162, 48, 193, 'man'),
(163, 48, 194, 'man'),
(164, 48, 197, 'man'),
(165, 48, 198, 'man'),
(166, 48, 202, 'man'),
(167, 48, 221, 'man'),
(168, 48, 230, 'man'),
(169, 48, 248, 'man'),
(170, 48, 251, 'man'),
(171, 48, 971, 'man'),
(172, 48, 972, 'man'),
(173, 48, 981, 'man'),
(174, 48, 987, 'man'),
(175, 41, 145, 'man'),
(176, 41, 146, 'man'),
(177, 41, 256, 'man'),
(178, 41, 984, 'man'),
(179, 43, 142, 'man'),
(180, 43, 143, 'man'),
(181, 43, 145, 'man'),
(182, 43, 146, 'man'),
(183, 43, 147, 'man'),
(184, 43, 232, 'man'),
(185, 43, 992, 'man'),
(186, 43, 994, 'man'),
(187, 43, 996, 'man'),
(188, 43, 1002, 'man'),
(189, 43, 1006, 'man'),
(190, 44, 89, 'man'),
(191, 44, 91, 'man'),
(192, 44, 115, 'man'),
(193, 44, 130, 'man'),
(194, 44, 132, 'man'),
(195, 44, 146, 'man'),
(196, 44, 201, 'man'),
(197, 44, 216, 'man'),
(198, 44, 255, 'man'),
(199, 44, 257, 'man'),
(200, 44, 979, 'man'),
(201, 44, 981, 'man'),
(202, 44, 992, 'man'),
(203, 44, 1002, 'man'),
(204, 44, 1003, 'man'),
(205, 45, 27, 'man'),
(206, 45, 91, 'man'),
(207, 45, 201, 'man'),
(208, 45, 216, 'man'),
(209, 45, 230, 'man'),
(210, 45, 979, 'man'),
(211, 45, 997, 'man'),
(212, 45, 1003, 'man'),
(213, 49, 12, 'man'),
(214, 49, 27, 'man'),
(215, 49, 94, 'man'),
(216, 49, 103, 'man'),
(217, 49, 118, 'man'),
(218, 49, 119, 'man'),
(219, 49, 140, 'man'),
(220, 49, 145, 'man'),
(221, 49, 146, 'man'),
(222, 49, 155, 'man'),
(223, 49, 156, 'man'),
(224, 49, 177, 'man'),
(225, 49, 185, 'man'),
(226, 49, 193, 'man'),
(227, 49, 194, 'man'),
(228, 49, 195, 'man'),
(229, 49, 197, 'man'),
(230, 49, 198, 'man'),
(231, 49, 230, 'man'),
(232, 49, 231, 'man'),
(233, 49, 252, 'man'),
(234, 49, 253, 'man'),
(235, 49, 254, 'man'),
(236, 49, 257, 'man'),
(237, 49, 972, 'man'),
(238, 49, 981, 'man'),
(239, 49, 987, 'man'),
(240, 49, 1005, 'man'),
(241, 49, 1006, 'man'),
(242, 31, 15, 'man'),
(243, 31, 17, 'man'),
(244, 31, 29, 'man'),
(245, 31, 30, 'man'),
(246, 31, 31, 'man'),
(247, 31, 37, 'man'),
(248, 31, 44, 'man'),
(249, 31, 45, 'man'),
(250, 31, 64, 'man'),
(251, 31, 133, 'man'),
(252, 31, 153, 'man'),
(253, 31, 233, 'man'),
(254, 31, 250, 'man'),
(255, 31, 985, 'man'),
(256, 31, 986, 'man'),
(257, 32, 183, 'man'),
(258, 32, 248, 'man'),
(259, 33, 103, 'man'),
(260, 33, 104, 'man'),
(261, 33, 167, 'man'),
(262, 33, 251, 'man'),
(263, 33, 260, 'man'),
(264, 33, 263, 'man'),
(265, 33, 998, 'man'),
(266, 36, 10, 'man'),
(267, 36, 44, 'man'),
(268, 36, 45, 'man'),
(269, 36, 48, 'man'),
(270, 36, 49, 'man'),
(271, 36, 50, 'man'),
(272, 36, 54, 'man'),
(273, 36, 92, 'man'),
(274, 36, 101, 'man'),
(275, 36, 131, 'man'),
(276, 36, 153, 'man'),
(277, 36, 154, 'man'),
(278, 36, 179, 'man'),
(279, 36, 181, 'man'),
(280, 36, 188, 'man'),
(281, 36, 191, 'man'),
(282, 36, 192, 'man'),
(283, 36, 989, 'man'),
(284, 37, 39, 'man'),
(285, 37, 59, 'man'),
(286, 37, 107, 'man'),
(287, 37, 108, 'man'),
(288, 37, 109, 'man'),
(289, 37, 110, 'man'),
(290, 37, 160, 'man'),
(291, 37, 161, 'man'),
(292, 37, 162, 'man'),
(293, 37, 163, 'man'),
(294, 37, 164, 'man'),
(295, 37, 165, 'man'),
(296, 37, 236, 'man'),
(297, 37, 268, 'man'),
(298, 38, 39, 'man'),
(299, 38, 59, 'man'),
(300, 38, 107, 'man'),
(301, 38, 108, 'man'),
(302, 38, 109, 'man'),
(303, 38, 110, 'man'),
(304, 38, 113, 'man'),
(305, 38, 160, 'man'),
(306, 38, 161, 'man'),
(307, 38, 162, 'man'),
(308, 38, 163, 'man'),
(309, 38, 164, 'man'),
(310, 38, 165, 'man'),
(311, 38, 172, 'man'),
(312, 38, 222, 'man'),
(313, 38, 236, 'man'),
(314, 38, 249, 'man'),
(315, 38, 266, 'man'),
(316, 38, 267, 'man'),
(317, 38, 268, 'man'),
(318, 38, 984, 'man'),
(319, 39, 118, 'man'),
(320, 39, 155, 'man'),
(321, 39, 156, 'man'),
(322, 39, 195, 'man'),
(323, 39, 196, 'man'),
(324, 39, 993, 'man'),
(325, 39, 1000, 'man'),
(326, 39, 1005, 'man'),
(327, 40, 50, 'man'),
(328, 40, 79, 'man'),
(329, 40, 80, 'man'),
(330, 40, 81, 'man'),
(331, 40, 82, 'man'),
(332, 40, 83, 'man'),
(333, 40, 84, 'man'),
(334, 40, 103, 'man'),
(335, 40, 180, 'man'),
(336, 40, 189, 'man'),
(337, 40, 190, 'man'),
(338, 40, 265, 'man'),
(339, 40, 990, 'man'),
(340, 40, 999, 'man'),
(341, 46, 21, 'man'),
(342, 46, 24, 'man'),
(343, 46, 25, 'man'),
(344, 46, 26, 'man'),
(345, 46, 34, 'man'),
(346, 46, 61, 'man'),
(347, 46, 62, 'man'),
(348, 46, 63, 'man'),
(349, 46, 65, 'man'),
(350, 46, 105, 'man'),
(351, 46, 124, 'man'),
(352, 46, 134, 'man'),
(353, 46, 138, 'man'),
(354, 46, 139, 'man'),
(355, 46, 149, 'man'),
(356, 46, 150, 'man'),
(357, 46, 151, 'man'),
(358, 46, 177, 'man'),
(359, 46, 184, 'man'),
(360, 46, 187, 'man'),
(361, 46, 214, 'man'),
(362, 46, 215, 'man'),
(363, 46, 217, 'man'),
(364, 46, 218, 'man'),
(365, 46, 220, 'man'),
(366, 46, 240, 'man'),
(367, 46, 987, 'man'),
(368, 46, 991, 'man'),
(369, 46, 1001, 'man'),
(370, 46, 1004, 'man'),
(371, 47, 11, 'man'),
(372, 47, 12, 'man'),
(373, 47, 16, 'man'),
(374, 47, 22, 'man'),
(375, 47, 23, 'man'),
(376, 47, 27, 'man'),
(377, 47, 43, 'man'),
(378, 47, 44, 'man'),
(379, 47, 47, 'man'),
(380, 47, 52, 'man'),
(381, 47, 53, 'man'),
(382, 47, 54, 'man'),
(383, 47, 60, 'man'),
(384, 47, 85, 'man'),
(385, 47, 114, 'man'),
(386, 47, 115, 'man'),
(387, 47, 116, 'man'),
(388, 47, 118, 'man'),
(389, 47, 120, 'man'),
(390, 47, 128, 'man'),
(391, 47, 131, 'man'),
(392, 47, 132, 'man'),
(393, 47, 138, 'man'),
(394, 47, 139, 'man'),
(395, 47, 140, 'man'),
(396, 47, 144, 'man'),
(397, 47, 149, 'man'),
(398, 47, 156, 'man'),
(399, 47, 157, 'man'),
(400, 47, 175, 'man'),
(401, 47, 177, 'man'),
(402, 47, 193, 'man'),
(403, 47, 194, 'man'),
(404, 47, 195, 'man'),
(405, 47, 197, 'man'),
(406, 47, 198, 'man'),
(407, 47, 207, 'man'),
(408, 47, 230, 'man'),
(409, 47, 238, 'man'),
(410, 47, 241, 'man'),
(411, 47, 257, 'man'),
(412, 47, 262, 'man'),
(413, 47, 972, 'man'),
(414, 47, 974, 'man'),
(415, 47, 975, 'man'),
(416, 47, 976, 'man'),
(417, 47, 981, 'man'),
(418, 47, 982, 'man'),
(419, 47, 983, 'man'),
(420, 47, 987, 'man'),
(421, 47, 1004, 'man'),
(422, 47, 1005, 'man'),
(423, 47, 1007, 'man'),
(424, 21, 152, 'woman'),
(425, 21, 239, 'woman'),
(426, 21, 245, 'woman'),
(427, 21, 247, 'woman'),
(428, 21, 269, 'woman'),
(429, 22, 9, 'woman'),
(430, 22, 11, 'woman'),
(431, 22, 16, 'woman'),
(432, 22, 43, 'woman'),
(433, 22, 44, 'woman'),
(434, 22, 52, 'woman'),
(435, 22, 53, 'woman'),
(436, 22, 57, 'woman'),
(437, 22, 85, 'woman'),
(438, 22, 114, 'woman'),
(439, 22, 120, 'woman'),
(440, 22, 125, 'woman'),
(441, 22, 126, 'woman'),
(442, 22, 128, 'woman'),
(443, 22, 144, 'woman'),
(444, 22, 207, 'woman'),
(445, 22, 235, 'woman'),
(446, 22, 238, 'woman'),
(447, 22, 241, 'woman'),
(448, 22, 242, 'woman'),
(449, 22, 243, 'woman'),
(450, 22, 244, 'woman'),
(451, 22, 970, 'woman'),
(452, 22, 974, 'woman'),
(453, 22, 975, 'woman'),
(454, 22, 976, 'woman'),
(455, 22, 982, 'woman'),
(456, 22, 984, 'woman'),
(457, 22, 988, 'woman'),
(458, 22, 1007, 'woman'),
(459, 23, 24, 'woman'),
(460, 23, 33, 'woman'),
(461, 23, 57, 'woman'),
(462, 23, 66, 'woman'),
(463, 23, 68, 'woman'),
(464, 23, 69, 'woman'),
(465, 23, 70, 'woman'),
(466, 23, 71, 'woman'),
(467, 23, 72, 'woman'),
(468, 23, 73, 'woman'),
(469, 23, 75, 'woman'),
(470, 23, 76, 'woman'),
(471, 23, 77, 'woman'),
(472, 23, 106, 'woman'),
(473, 23, 129, 'woman'),
(474, 23, 137, 'woman'),
(475, 23, 166, 'woman'),
(476, 23, 170, 'woman'),
(477, 23, 176, 'woman'),
(478, 23, 208, 'woman'),
(479, 23, 209, 'woman'),
(480, 23, 210, 'woman'),
(481, 23, 211, 'woman'),
(482, 23, 213, 'woman'),
(483, 23, 219, 'woman'),
(484, 23, 242, 'woman'),
(485, 23, 243, 'woman'),
(486, 23, 244, 'woman'),
(487, 23, 246, 'woman'),
(488, 23, 258, 'woman'),
(489, 23, 270, 'woman'),
(490, 23, 273, 'woman'),
(491, 23, 286, 'woman'),
(492, 23, 287, 'woman'),
(493, 23, 288, 'woman'),
(494, 23, 970, 'woman'),
(495, 24, 13, 'woman'),
(496, 24, 14, 'woman'),
(497, 24, 15, 'woman'),
(498, 24, 28, 'woman'),
(499, 24, 30, 'woman'),
(500, 24, 38, 'woman'),
(501, 24, 40, 'woman'),
(502, 24, 45, 'woman'),
(503, 24, 46, 'woman'),
(504, 24, 78, 'woman'),
(505, 24, 86, 'woman'),
(506, 24, 87, 'woman'),
(507, 24, 88, 'woman'),
(508, 24, 90, 'woman'),
(509, 24, 93, 'woman'),
(510, 24, 95, 'woman'),
(511, 24, 96, 'woman'),
(512, 24, 98, 'woman'),
(513, 24, 102, 'woman'),
(514, 24, 121, 'woman'),
(515, 24, 122, 'woman'),
(516, 24, 133, 'woman'),
(517, 24, 136, 'woman'),
(518, 24, 153, 'woman'),
(519, 24, 169, 'woman'),
(520, 24, 203, 'woman'),
(521, 24, 234, 'woman'),
(522, 24, 288, 'woman'),
(523, 24, 984, 'woman'),
(524, 24, 988, 'woman'),
(525, 25, 15, 'woman'),
(526, 25, 35, 'woman'),
(527, 25, 40, 'woman'),
(528, 25, 49, 'woman'),
(529, 25, 54, 'woman'),
(530, 25, 62, 'woman'),
(531, 25, 64, 'woman'),
(532, 25, 93, 'woman'),
(533, 25, 97, 'woman'),
(534, 25, 98, 'woman'),
(535, 25, 101, 'woman'),
(536, 25, 122, 'woman'),
(537, 25, 126, 'woman'),
(538, 25, 131, 'woman'),
(539, 25, 133, 'woman'),
(540, 25, 135, 'woman'),
(541, 25, 153, 'woman'),
(542, 25, 181, 'woman'),
(543, 25, 203, 'woman'),
(544, 25, 204, 'woman'),
(545, 25, 205, 'woman'),
(546, 25, 233, 'woman'),
(547, 25, 243, 'woman'),
(548, 25, 272, 'woman'),
(549, 25, 286, 'woman'),
(550, 25, 970, 'woman'),
(551, 25, 973, 'woman'),
(552, 25, 977, 'woman'),
(553, 25, 980, 'woman'),
(554, 25, 991, 'woman'),
(555, 25, 1008, 'woman'),
(556, 26, 971, 'woman'),
(557, 28, 186, 'woman'),
(558, 28, 971, 'woman'),
(559, 29, 115, 'woman'),
(560, 29, 132, 'woman'),
(561, 29, 148, 'woman'),
(562, 29, 186, 'woman'),
(563, 29, 200, 'woman'),
(564, 29, 201, 'woman'),
(565, 29, 216, 'woman'),
(566, 29, 257, 'woman'),
(567, 29, 978, 'woman'),
(568, 29, 979, 'woman'),
(569, 29, 981, 'woman'),
(570, 29, 984, 'woman'),
(571, 30, 91, 'woman'),
(572, 30, 178, 'woman'),
(573, 30, 201, 'woman'),
(574, 30, 991, 'woman'),
(575, 30, 995, 'woman'),
(576, 48, 12, 'woman'),
(577, 48, 27, 'woman'),
(578, 48, 94, 'woman'),
(579, 48, 119, 'woman'),
(580, 48, 140, 'woman'),
(581, 48, 155, 'woman'),
(582, 48, 156, 'woman'),
(583, 48, 168, 'woman'),
(584, 48, 177, 'woman'),
(585, 48, 193, 'woman'),
(586, 48, 194, 'woman'),
(587, 48, 197, 'woman'),
(588, 48, 198, 'woman'),
(589, 48, 202, 'woman'),
(590, 48, 221, 'woman'),
(591, 48, 230, 'woman'),
(592, 48, 248, 'woman'),
(593, 48, 251, 'woman'),
(594, 48, 971, 'woman'),
(595, 48, 972, 'woman'),
(596, 48, 981, 'woman'),
(597, 48, 987, 'woman'),
(598, 41, 145, 'woman'),
(599, 41, 146, 'woman'),
(600, 41, 256, 'woman'),
(601, 41, 984, 'woman'),
(602, 43, 142, 'woman'),
(603, 43, 143, 'woman'),
(604, 43, 145, 'woman'),
(605, 43, 146, 'woman'),
(606, 43, 147, 'woman'),
(607, 43, 232, 'woman'),
(608, 43, 992, 'woman'),
(609, 43, 994, 'woman'),
(610, 43, 996, 'woman'),
(611, 43, 1002, 'woman'),
(612, 43, 1006, 'woman'),
(613, 44, 89, 'woman'),
(614, 44, 91, 'woman'),
(615, 44, 115, 'woman'),
(616, 44, 130, 'woman'),
(617, 44, 132, 'woman'),
(618, 44, 146, 'woman'),
(619, 44, 201, 'woman'),
(620, 44, 216, 'woman'),
(621, 44, 255, 'woman'),
(622, 44, 257, 'woman'),
(623, 44, 979, 'woman'),
(624, 44, 981, 'woman'),
(625, 44, 992, 'woman'),
(626, 44, 1002, 'woman'),
(627, 44, 1003, 'woman'),
(628, 45, 27, 'woman'),
(629, 45, 91, 'woman'),
(630, 45, 201, 'woman'),
(631, 45, 216, 'woman'),
(632, 45, 230, 'woman'),
(633, 45, 979, 'woman'),
(634, 45, 997, 'woman'),
(635, 45, 1003, 'woman'),
(636, 49, 12, 'woman'),
(637, 49, 27, 'woman'),
(638, 49, 94, 'woman'),
(639, 49, 103, 'woman'),
(640, 49, 118, 'woman'),
(641, 49, 119, 'woman'),
(642, 49, 140, 'woman'),
(643, 49, 145, 'woman'),
(644, 49, 146, 'woman'),
(645, 49, 155, 'woman'),
(646, 49, 156, 'woman'),
(647, 49, 177, 'woman'),
(648, 49, 185, 'woman'),
(649, 49, 193, 'woman'),
(650, 49, 194, 'woman'),
(651, 49, 195, 'woman'),
(652, 49, 197, 'woman'),
(653, 49, 198, 'woman'),
(654, 49, 230, 'woman'),
(655, 49, 231, 'woman'),
(656, 49, 252, 'woman'),
(657, 49, 253, 'woman'),
(658, 49, 254, 'woman'),
(659, 49, 257, 'woman'),
(660, 49, 972, 'woman'),
(661, 49, 981, 'woman'),
(662, 49, 987, 'woman'),
(663, 49, 1005, 'woman'),
(664, 49, 1006, 'woman'),
(665, 31, 15, 'woman'),
(666, 31, 17, 'woman'),
(667, 31, 29, 'woman'),
(668, 31, 30, 'woman'),
(669, 31, 31, 'woman'),
(670, 31, 37, 'woman'),
(671, 31, 44, 'woman'),
(672, 31, 45, 'woman'),
(673, 31, 64, 'woman'),
(674, 31, 133, 'woman'),
(675, 31, 153, 'woman'),
(676, 31, 233, 'woman'),
(677, 31, 250, 'woman'),
(678, 31, 261, 'woman'),
(679, 31, 985, 'woman'),
(680, 31, 986, 'woman'),
(681, 32, 183, 'woman'),
(682, 32, 248, 'woman'),
(683, 33, 103, 'woman'),
(684, 33, 104, 'woman'),
(685, 33, 167, 'woman'),
(686, 33, 251, 'woman'),
(687, 33, 260, 'woman'),
(688, 33, 263, 'woman'),
(689, 33, 998, 'woman'),
(690, 36, 10, 'woman'),
(691, 36, 44, 'woman'),
(692, 36, 45, 'woman'),
(693, 36, 48, 'woman'),
(694, 36, 49, 'woman'),
(695, 36, 50, 'woman'),
(696, 36, 54, 'woman'),
(697, 36, 92, 'woman'),
(698, 36, 101, 'woman'),
(699, 36, 131, 'woman'),
(700, 36, 153, 'woman'),
(701, 36, 154, 'woman'),
(702, 36, 179, 'woman'),
(703, 36, 181, 'woman'),
(704, 36, 188, 'woman'),
(705, 36, 191, 'woman'),
(706, 36, 192, 'woman'),
(707, 36, 223, 'woman'),
(708, 36, 284, 'woman'),
(709, 36, 989, 'woman'),
(710, 37, 39, 'woman'),
(711, 37, 59, 'woman'),
(712, 37, 107, 'woman'),
(713, 37, 108, 'woman'),
(714, 37, 109, 'woman'),
(715, 37, 110, 'woman'),
(716, 37, 160, 'woman'),
(717, 37, 161, 'woman'),
(718, 37, 163, 'woman'),
(719, 37, 164, 'woman'),
(720, 37, 165, 'woman'),
(721, 37, 236, 'woman'),
(722, 37, 268, 'woman'),
(723, 38, 39, 'woman'),
(724, 38, 59, 'woman'),
(725, 38, 107, 'woman'),
(726, 38, 108, 'woman'),
(727, 38, 109, 'woman'),
(728, 38, 110, 'woman'),
(729, 38, 112, 'woman'),
(730, 38, 123, 'woman'),
(731, 38, 160, 'woman'),
(732, 38, 161, 'woman'),
(733, 38, 163, 'woman'),
(734, 38, 164, 'woman'),
(735, 38, 165, 'woman'),
(736, 38, 173, 'woman'),
(737, 38, 236, 'woman'),
(738, 38, 249, 'woman'),
(739, 38, 268, 'woman'),
(740, 38, 284, 'woman'),
(741, 38, 984, 'woman'),
(742, 39, 118, 'woman'),
(743, 39, 155, 'woman'),
(744, 39, 156, 'woman'),
(745, 39, 195, 'woman'),
(746, 39, 196, 'woman'),
(747, 39, 993, 'woman'),
(748, 39, 1000, 'woman'),
(749, 39, 1005, 'woman'),
(750, 40, 50, 'woman'),
(751, 40, 79, 'woman'),
(752, 40, 80, 'woman'),
(753, 40, 81, 'woman'),
(754, 40, 82, 'woman'),
(755, 40, 83, 'woman'),
(756, 40, 84, 'woman'),
(757, 40, 103, 'woman'),
(758, 40, 180, 'woman'),
(759, 40, 189, 'woman'),
(760, 40, 190, 'woman'),
(761, 40, 265, 'woman'),
(762, 40, 990, 'woman'),
(763, 40, 999, 'woman'),
(764, 46, 21, 'woman'),
(765, 46, 24, 'woman'),
(766, 46, 25, 'woman'),
(767, 46, 26, 'woman'),
(768, 46, 34, 'woman'),
(769, 46, 61, 'woman'),
(770, 46, 62, 'woman'),
(771, 46, 63, 'woman'),
(772, 46, 65, 'woman'),
(773, 46, 105, 'woman'),
(774, 46, 124, 'woman'),
(775, 46, 134, 'woman'),
(776, 46, 138, 'woman'),
(777, 46, 139, 'woman'),
(778, 46, 149, 'woman'),
(779, 46, 150, 'woman'),
(780, 46, 151, 'woman'),
(781, 46, 177, 'woman'),
(782, 46, 184, 'woman'),
(783, 46, 187, 'woman'),
(784, 46, 214, 'woman'),
(785, 46, 215, 'woman'),
(786, 46, 217, 'woman'),
(787, 46, 218, 'woman'),
(788, 46, 220, 'woman'),
(789, 46, 240, 'woman'),
(790, 46, 987, 'woman'),
(791, 46, 991, 'woman'),
(792, 46, 1001, 'woman'),
(793, 46, 1004, 'woman'),
(794, 47, 11, 'woman'),
(795, 47, 12, 'woman'),
(796, 47, 16, 'woman'),
(797, 47, 22, 'woman'),
(798, 47, 23, 'woman'),
(799, 47, 27, 'woman'),
(800, 47, 43, 'woman'),
(801, 47, 44, 'woman'),
(802, 47, 47, 'woman'),
(803, 47, 52, 'woman'),
(804, 47, 53, 'woman'),
(805, 47, 54, 'woman'),
(806, 47, 60, 'woman'),
(807, 47, 85, 'woman'),
(808, 47, 114, 'woman'),
(809, 47, 115, 'woman'),
(810, 47, 116, 'woman'),
(811, 47, 118, 'woman'),
(812, 47, 120, 'woman'),
(813, 47, 128, 'woman'),
(814, 47, 131, 'woman'),
(815, 47, 132, 'woman'),
(816, 47, 138, 'woman'),
(817, 47, 139, 'woman'),
(818, 47, 140, 'woman'),
(819, 47, 144, 'woman'),
(820, 47, 149, 'woman'),
(821, 47, 156, 'woman'),
(822, 47, 157, 'woman'),
(823, 47, 175, 'woman'),
(824, 47, 177, 'woman'),
(825, 47, 193, 'woman'),
(826, 47, 194, 'woman'),
(827, 47, 195, 'woman'),
(828, 47, 197, 'woman'),
(829, 47, 198, 'woman'),
(830, 47, 207, 'woman'),
(831, 47, 230, 'woman'),
(832, 47, 238, 'woman'),
(833, 47, 241, 'woman'),
(834, 47, 257, 'woman'),
(835, 47, 262, 'woman'),
(836, 47, 972, 'woman'),
(837, 47, 974, 'woman'),
(838, 47, 975, 'woman'),
(839, 47, 976, 'woman'),
(840, 47, 981, 'woman'),
(841, 47, 982, 'woman'),
(842, 47, 983, 'woman'),
(843, 47, 987, 'woman'),
(844, 47, 1004, 'woman'),
(845, 47, 1005, 'woman'),
(846, 47, 1007, 'woman'),
(847, 21, 152, 'boy'),
(848, 21, 239, 'boy'),
(849, 21, 245, 'boy'),
(850, 21, 247, 'boy'),
(851, 21, 269, 'boy'),
(852, 22, 9, 'boy'),
(853, 22, 11, 'boy'),
(854, 22, 16, 'boy'),
(855, 22, 43, 'boy'),
(856, 22, 44, 'boy'),
(857, 22, 52, 'boy'),
(858, 22, 53, 'boy'),
(859, 22, 57, 'boy'),
(860, 22, 85, 'boy'),
(861, 22, 114, 'boy'),
(862, 22, 120, 'boy'),
(863, 22, 125, 'boy'),
(864, 22, 126, 'boy'),
(865, 22, 128, 'boy'),
(866, 22, 144, 'boy'),
(867, 22, 207, 'boy'),
(868, 22, 235, 'boy'),
(869, 22, 238, 'boy'),
(870, 22, 241, 'boy'),
(871, 22, 242, 'boy'),
(872, 22, 243, 'boy'),
(873, 22, 244, 'boy'),
(874, 22, 970, 'boy'),
(875, 22, 974, 'boy'),
(876, 22, 975, 'boy'),
(877, 22, 976, 'boy'),
(878, 22, 982, 'boy'),
(879, 22, 984, 'boy'),
(880, 22, 988, 'boy'),
(881, 22, 1007, 'boy'),
(882, 23, 24, 'boy'),
(883, 23, 33, 'boy'),
(884, 23, 57, 'boy'),
(885, 23, 66, 'boy'),
(886, 23, 68, 'boy'),
(887, 23, 69, 'boy'),
(888, 23, 70, 'boy'),
(889, 23, 71, 'boy'),
(890, 23, 72, 'boy'),
(891, 23, 73, 'boy'),
(892, 23, 75, 'boy'),
(893, 23, 76, 'boy'),
(894, 23, 77, 'boy'),
(895, 23, 106, 'boy'),
(896, 23, 129, 'boy'),
(897, 23, 137, 'boy'),
(898, 23, 166, 'boy'),
(899, 23, 170, 'boy'),
(900, 23, 176, 'boy'),
(901, 23, 208, 'boy'),
(902, 23, 209, 'boy'),
(903, 23, 210, 'boy'),
(904, 23, 211, 'boy'),
(905, 23, 213, 'boy'),
(906, 23, 219, 'boy'),
(907, 23, 242, 'boy'),
(908, 23, 243, 'boy'),
(909, 23, 244, 'boy'),
(910, 23, 246, 'boy'),
(911, 23, 258, 'boy'),
(912, 23, 270, 'boy'),
(913, 23, 273, 'boy'),
(914, 23, 287, 'boy'),
(915, 23, 288, 'boy'),
(916, 23, 970, 'boy'),
(917, 24, 13, 'boy'),
(918, 24, 14, 'boy'),
(919, 24, 15, 'boy'),
(920, 24, 28, 'boy'),
(921, 24, 30, 'boy'),
(922, 24, 38, 'boy'),
(923, 24, 40, 'boy'),
(924, 24, 45, 'boy'),
(925, 24, 46, 'boy'),
(926, 24, 78, 'boy'),
(927, 24, 86, 'boy'),
(928, 24, 87, 'boy'),
(929, 24, 88, 'boy'),
(930, 24, 90, 'boy'),
(931, 24, 93, 'boy'),
(932, 24, 95, 'boy'),
(933, 24, 96, 'boy'),
(934, 24, 98, 'boy'),
(935, 24, 102, 'boy'),
(936, 24, 121, 'boy'),
(937, 24, 122, 'boy'),
(938, 24, 133, 'boy'),
(939, 24, 136, 'boy'),
(940, 24, 153, 'boy'),
(941, 24, 169, 'boy'),
(942, 24, 203, 'boy'),
(943, 24, 234, 'boy'),
(944, 24, 288, 'boy'),
(945, 24, 984, 'boy'),
(946, 24, 988, 'boy'),
(947, 25, 15, 'boy'),
(948, 25, 35, 'boy'),
(949, 25, 40, 'boy'),
(950, 25, 49, 'boy'),
(951, 25, 54, 'boy'),
(952, 25, 62, 'boy'),
(953, 25, 64, 'boy'),
(954, 25, 93, 'boy'),
(955, 25, 97, 'boy'),
(956, 25, 98, 'boy'),
(957, 25, 101, 'boy'),
(958, 25, 122, 'boy'),
(959, 25, 126, 'boy'),
(960, 25, 131, 'boy'),
(961, 25, 133, 'boy'),
(962, 25, 135, 'boy'),
(963, 25, 153, 'boy'),
(964, 25, 181, 'boy'),
(965, 25, 203, 'boy'),
(966, 25, 204, 'boy'),
(967, 25, 205, 'boy'),
(968, 25, 233, 'boy'),
(969, 25, 243, 'boy'),
(970, 25, 264, 'boy'),
(971, 25, 272, 'boy'),
(972, 25, 970, 'boy'),
(973, 25, 973, 'boy'),
(974, 25, 977, 'boy'),
(975, 25, 980, 'boy'),
(976, 25, 991, 'boy'),
(977, 25, 1008, 'boy'),
(978, 26, 971, 'boy'),
(979, 28, 186, 'boy'),
(980, 28, 971, 'boy'),
(981, 29, 115, 'boy'),
(982, 29, 132, 'boy'),
(983, 29, 148, 'boy'),
(984, 29, 186, 'boy'),
(985, 29, 200, 'boy'),
(986, 29, 201, 'boy'),
(987, 29, 216, 'boy'),
(988, 29, 257, 'boy'),
(989, 29, 978, 'boy'),
(990, 29, 979, 'boy'),
(991, 29, 981, 'boy'),
(992, 29, 984, 'boy'),
(993, 30, 91, 'boy'),
(994, 30, 178, 'boy'),
(995, 30, 201, 'boy'),
(996, 30, 991, 'boy'),
(997, 30, 995, 'boy'),
(998, 48, 12, 'boy'),
(999, 48, 27, 'boy'),
(1000, 48, 94, 'boy'),
(1001, 48, 119, 'boy'),
(1002, 48, 140, 'boy'),
(1003, 48, 155, 'boy'),
(1004, 48, 156, 'boy'),
(1005, 48, 168, 'boy'),
(1006, 48, 177, 'boy'),
(1007, 48, 193, 'boy'),
(1008, 48, 194, 'boy'),
(1009, 48, 197, 'boy'),
(1010, 48, 198, 'boy'),
(1011, 48, 202, 'boy'),
(1012, 48, 221, 'boy'),
(1013, 48, 230, 'boy'),
(1014, 48, 248, 'boy'),
(1015, 48, 251, 'boy'),
(1016, 48, 971, 'boy'),
(1017, 48, 972, 'boy'),
(1018, 48, 981, 'boy'),
(1019, 48, 987, 'boy'),
(1020, 41, 145, 'boy'),
(1021, 41, 146, 'boy'),
(1022, 41, 256, 'boy'),
(1023, 41, 984, 'boy'),
(1024, 43, 142, 'boy'),
(1025, 43, 143, 'boy'),
(1026, 43, 145, 'boy'),
(1027, 43, 146, 'boy'),
(1028, 43, 147, 'boy'),
(1029, 43, 232, 'boy'),
(1030, 43, 992, 'boy'),
(1031, 43, 994, 'boy'),
(1032, 43, 996, 'boy'),
(1033, 43, 1002, 'boy'),
(1034, 43, 1006, 'boy'),
(1035, 44, 89, 'boy'),
(1036, 44, 91, 'boy'),
(1037, 44, 115, 'boy'),
(1038, 44, 130, 'boy'),
(1039, 44, 132, 'boy'),
(1040, 44, 146, 'boy'),
(1041, 44, 201, 'boy'),
(1042, 44, 216, 'boy'),
(1043, 44, 255, 'boy'),
(1044, 44, 257, 'boy'),
(1045, 44, 979, 'boy'),
(1046, 44, 981, 'boy'),
(1047, 44, 992, 'boy'),
(1048, 44, 1002, 'boy'),
(1049, 44, 1003, 'boy'),
(1050, 45, 27, 'boy'),
(1051, 45, 91, 'boy'),
(1052, 45, 201, 'boy'),
(1053, 45, 216, 'boy'),
(1054, 45, 230, 'boy'),
(1055, 45, 979, 'boy'),
(1056, 45, 997, 'boy'),
(1057, 45, 1003, 'boy'),
(1058, 49, 12, 'boy'),
(1059, 49, 27, 'boy'),
(1060, 49, 94, 'boy'),
(1061, 49, 103, 'boy'),
(1062, 49, 118, 'boy'),
(1063, 49, 119, 'boy'),
(1064, 49, 140, 'boy'),
(1065, 49, 145, 'boy'),
(1066, 49, 146, 'boy'),
(1067, 49, 155, 'boy'),
(1068, 49, 156, 'boy'),
(1069, 49, 177, 'boy'),
(1070, 49, 185, 'boy'),
(1071, 49, 193, 'boy'),
(1072, 49, 194, 'boy'),
(1073, 49, 195, 'boy'),
(1074, 49, 197, 'boy'),
(1075, 49, 198, 'boy'),
(1076, 49, 230, 'boy'),
(1077, 49, 231, 'boy'),
(1078, 49, 252, 'boy'),
(1079, 49, 253, 'boy'),
(1080, 49, 254, 'boy'),
(1081, 49, 257, 'boy'),
(1082, 49, 972, 'boy'),
(1083, 49, 981, 'boy'),
(1084, 49, 987, 'boy'),
(1085, 49, 1005, 'boy'),
(1086, 49, 1006, 'boy'),
(1087, 31, 15, 'boy'),
(1088, 31, 17, 'boy'),
(1089, 31, 29, 'boy'),
(1090, 31, 30, 'boy'),
(1091, 31, 31, 'boy'),
(1092, 31, 37, 'boy'),
(1093, 31, 44, 'boy'),
(1094, 31, 45, 'boy'),
(1095, 31, 64, 'boy'),
(1096, 31, 133, 'boy'),
(1097, 31, 153, 'boy'),
(1098, 31, 233, 'boy'),
(1099, 31, 250, 'boy'),
(1100, 31, 985, 'boy'),
(1101, 31, 986, 'boy'),
(1102, 32, 183, 'boy'),
(1103, 32, 248, 'boy'),
(1104, 33, 103, 'boy'),
(1105, 33, 104, 'boy'),
(1106, 33, 167, 'boy'),
(1107, 33, 251, 'boy'),
(1108, 33, 260, 'boy'),
(1109, 33, 263, 'boy'),
(1110, 33, 998, 'boy'),
(1111, 36, 10, 'boy'),
(1112, 36, 44, 'boy'),
(1113, 36, 45, 'boy'),
(1114, 36, 48, 'boy'),
(1115, 36, 49, 'boy'),
(1116, 36, 50, 'boy'),
(1117, 36, 54, 'boy'),
(1118, 36, 92, 'boy'),
(1119, 36, 101, 'boy'),
(1120, 36, 131, 'boy'),
(1121, 36, 153, 'boy'),
(1122, 36, 154, 'boy'),
(1123, 36, 179, 'boy'),
(1124, 36, 181, 'boy'),
(1125, 36, 188, 'boy'),
(1126, 36, 191, 'boy'),
(1127, 36, 192, 'boy'),
(1128, 36, 989, 'boy'),
(1129, 37, 39, 'boy'),
(1130, 37, 59, 'boy'),
(1131, 37, 107, 'boy'),
(1132, 37, 108, 'boy'),
(1133, 37, 109, 'boy'),
(1134, 37, 110, 'boy'),
(1135, 37, 160, 'boy'),
(1136, 37, 161, 'boy'),
(1137, 37, 163, 'boy'),
(1138, 37, 164, 'boy'),
(1139, 37, 165, 'boy'),
(1140, 37, 236, 'boy'),
(1141, 37, 268, 'boy'),
(1142, 38, 39, 'boy'),
(1143, 38, 59, 'boy'),
(1144, 38, 107, 'boy'),
(1145, 38, 108, 'boy'),
(1146, 38, 109, 'boy'),
(1147, 38, 110, 'boy'),
(1148, 38, 160, 'boy'),
(1149, 38, 161, 'boy'),
(1150, 38, 163, 'boy'),
(1151, 38, 164, 'boy'),
(1152, 38, 165, 'boy'),
(1153, 38, 172, 'boy'),
(1154, 38, 222, 'boy'),
(1155, 38, 236, 'boy'),
(1156, 38, 249, 'boy'),
(1157, 38, 266, 'boy'),
(1158, 38, 267, 'boy'),
(1159, 38, 268, 'boy'),
(1160, 38, 984, 'boy'),
(1161, 39, 118, 'boy'),
(1162, 39, 155, 'boy'),
(1163, 39, 156, 'boy'),
(1164, 39, 195, 'boy'),
(1165, 39, 196, 'boy'),
(1166, 39, 993, 'boy'),
(1167, 39, 1000, 'boy'),
(1168, 39, 1005, 'boy'),
(1169, 40, 50, 'boy'),
(1170, 40, 79, 'boy'),
(1171, 40, 80, 'boy'),
(1172, 40, 81, 'boy'),
(1173, 40, 82, 'boy'),
(1174, 40, 83, 'boy'),
(1175, 40, 84, 'boy'),
(1176, 40, 103, 'boy'),
(1177, 40, 180, 'boy'),
(1178, 40, 189, 'boy'),
(1179, 40, 190, 'boy'),
(1180, 40, 265, 'boy'),
(1181, 40, 990, 'boy'),
(1182, 40, 999, 'boy'),
(1183, 46, 21, 'boy'),
(1184, 46, 24, 'boy'),
(1185, 46, 25, 'boy'),
(1186, 46, 26, 'boy'),
(1187, 46, 34, 'boy'),
(1188, 46, 61, 'boy'),
(1189, 46, 62, 'boy'),
(1190, 46, 63, 'boy'),
(1191, 46, 65, 'boy'),
(1192, 46, 105, 'boy'),
(1193, 46, 124, 'boy'),
(1194, 46, 134, 'boy'),
(1195, 46, 138, 'boy'),
(1196, 46, 139, 'boy'),
(1197, 46, 149, 'boy'),
(1198, 46, 150, 'boy'),
(1199, 46, 151, 'boy'),
(1200, 46, 177, 'boy'),
(1201, 46, 184, 'boy'),
(1202, 46, 187, 'boy'),
(1203, 46, 214, 'boy'),
(1204, 46, 215, 'boy'),
(1205, 46, 217, 'boy'),
(1206, 46, 218, 'boy'),
(1207, 46, 220, 'boy'),
(1208, 46, 240, 'boy'),
(1209, 46, 987, 'boy'),
(1210, 46, 991, 'boy'),
(1211, 46, 1001, 'boy'),
(1212, 46, 1004, 'boy'),
(1213, 47, 11, 'boy'),
(1214, 47, 12, 'boy'),
(1215, 47, 16, 'boy'),
(1216, 47, 22, 'boy'),
(1217, 47, 23, 'boy'),
(1218, 47, 27, 'boy'),
(1219, 47, 43, 'boy'),
(1220, 47, 44, 'boy'),
(1221, 47, 47, 'boy'),
(1222, 47, 52, 'boy'),
(1223, 47, 53, 'boy'),
(1224, 47, 54, 'boy'),
(1225, 47, 60, 'boy'),
(1226, 47, 85, 'boy'),
(1227, 47, 114, 'boy'),
(1228, 47, 115, 'boy'),
(1229, 47, 116, 'boy'),
(1230, 47, 118, 'boy'),
(1231, 47, 120, 'boy'),
(1232, 47, 128, 'boy'),
(1233, 47, 131, 'boy'),
(1234, 47, 132, 'boy'),
(1235, 47, 138, 'boy'),
(1236, 47, 139, 'boy'),
(1237, 47, 140, 'boy'),
(1238, 47, 144, 'boy'),
(1239, 47, 149, 'boy'),
(1240, 47, 156, 'boy'),
(1241, 47, 157, 'boy'),
(1242, 47, 175, 'boy'),
(1243, 47, 177, 'boy'),
(1244, 47, 193, 'boy'),
(1245, 47, 194, 'boy'),
(1246, 47, 195, 'boy'),
(1247, 47, 197, 'boy'),
(1248, 47, 198, 'boy'),
(1249, 47, 207, 'boy'),
(1250, 47, 230, 'boy'),
(1251, 47, 238, 'boy'),
(1252, 47, 241, 'boy'),
(1253, 47, 257, 'boy'),
(1254, 47, 262, 'boy'),
(1255, 47, 972, 'boy'),
(1256, 47, 974, 'boy'),
(1257, 47, 975, 'boy'),
(1258, 47, 976, 'boy'),
(1259, 47, 981, 'boy'),
(1260, 47, 982, 'boy'),
(1261, 47, 983, 'boy'),
(1262, 47, 987, 'boy'),
(1263, 47, 1004, 'boy'),
(1264, 47, 1005, 'boy'),
(1265, 47, 1007, 'boy'),
(1266, 21, 152, 'girl'),
(1267, 21, 239, 'girl'),
(1268, 21, 245, 'girl'),
(1269, 21, 247, 'girl'),
(1270, 21, 269, 'girl'),
(1271, 22, 9, 'girl'),
(1272, 22, 11, 'girl'),
(1273, 22, 16, 'girl'),
(1274, 22, 43, 'girl'),
(1275, 22, 44, 'girl'),
(1276, 22, 52, 'girl'),
(1277, 22, 53, 'girl'),
(1278, 22, 57, 'girl'),
(1279, 22, 85, 'girl'),
(1280, 22, 114, 'girl'),
(1281, 22, 120, 'girl'),
(1282, 22, 125, 'girl'),
(1283, 22, 126, 'girl'),
(1284, 22, 128, 'girl'),
(1285, 22, 144, 'girl'),
(1286, 22, 207, 'girl'),
(1287, 22, 235, 'girl'),
(1288, 22, 238, 'girl'),
(1289, 22, 241, 'girl'),
(1290, 22, 242, 'girl'),
(1291, 22, 243, 'girl'),
(1292, 22, 244, 'girl'),
(1293, 22, 970, 'girl'),
(1294, 22, 974, 'girl'),
(1295, 22, 975, 'girl'),
(1296, 22, 976, 'girl'),
(1297, 22, 982, 'girl'),
(1298, 22, 984, 'girl'),
(1299, 22, 988, 'girl'),
(1300, 22, 1007, 'girl'),
(1301, 23, 24, 'girl'),
(1302, 23, 33, 'girl'),
(1303, 23, 57, 'girl'),
(1304, 23, 66, 'girl'),
(1305, 23, 68, 'girl'),
(1306, 23, 69, 'girl'),
(1307, 23, 70, 'girl'),
(1308, 23, 71, 'girl'),
(1309, 23, 72, 'girl'),
(1310, 23, 73, 'girl'),
(1311, 23, 75, 'girl'),
(1312, 23, 76, 'girl'),
(1313, 23, 77, 'girl'),
(1314, 23, 106, 'girl'),
(1315, 23, 129, 'girl'),
(1316, 23, 137, 'girl'),
(1317, 23, 166, 'girl'),
(1318, 23, 170, 'girl'),
(1319, 23, 176, 'girl'),
(1320, 23, 208, 'girl'),
(1321, 23, 209, 'girl'),
(1322, 23, 210, 'girl'),
(1323, 23, 211, 'girl'),
(1324, 23, 213, 'girl'),
(1325, 23, 219, 'girl'),
(1326, 23, 242, 'girl'),
(1327, 23, 243, 'girl'),
(1328, 23, 244, 'girl'),
(1329, 23, 246, 'girl'),
(1330, 23, 258, 'girl'),
(1331, 23, 270, 'girl'),
(1332, 23, 273, 'girl'),
(1333, 23, 287, 'girl'),
(1334, 23, 288, 'girl'),
(1335, 23, 970, 'girl'),
(1336, 24, 13, 'girl'),
(1337, 24, 14, 'girl'),
(1338, 24, 15, 'girl'),
(1339, 24, 28, 'girl'),
(1340, 24, 30, 'girl'),
(1341, 24, 38, 'girl'),
(1342, 24, 40, 'girl'),
(1343, 24, 45, 'girl'),
(1344, 24, 46, 'girl'),
(1345, 24, 78, 'girl'),
(1346, 24, 86, 'girl'),
(1347, 24, 87, 'girl'),
(1348, 24, 88, 'girl'),
(1349, 24, 90, 'girl'),
(1350, 24, 93, 'girl'),
(1351, 24, 95, 'girl'),
(1352, 24, 96, 'girl'),
(1353, 24, 98, 'girl'),
(1354, 24, 102, 'girl'),
(1355, 24, 121, 'girl'),
(1356, 24, 122, 'girl'),
(1357, 24, 133, 'girl'),
(1358, 24, 136, 'girl'),
(1359, 24, 153, 'girl'),
(1360, 24, 169, 'girl'),
(1361, 24, 203, 'girl'),
(1362, 24, 234, 'girl'),
(1363, 24, 288, 'girl'),
(1364, 24, 984, 'girl'),
(1365, 24, 988, 'girl'),
(1366, 25, 15, 'girl'),
(1367, 25, 35, 'girl'),
(1368, 25, 40, 'girl'),
(1369, 25, 49, 'girl'),
(1370, 25, 54, 'girl'),
(1371, 25, 62, 'girl'),
(1372, 25, 64, 'girl'),
(1373, 25, 93, 'girl'),
(1374, 25, 97, 'girl'),
(1375, 25, 98, 'girl'),
(1376, 25, 101, 'girl'),
(1377, 25, 122, 'girl'),
(1378, 25, 126, 'girl'),
(1379, 25, 131, 'girl'),
(1380, 25, 133, 'girl'),
(1381, 25, 135, 'girl'),
(1382, 25, 153, 'girl'),
(1383, 25, 181, 'girl'),
(1384, 25, 203, 'girl'),
(1385, 25, 204, 'girl'),
(1386, 25, 205, 'girl'),
(1387, 25, 233, 'girl'),
(1388, 25, 243, 'girl'),
(1389, 25, 264, 'girl'),
(1390, 25, 272, 'girl'),
(1391, 25, 970, 'girl'),
(1392, 25, 973, 'girl'),
(1393, 25, 977, 'girl'),
(1394, 25, 980, 'girl'),
(1395, 25, 991, 'girl'),
(1396, 25, 1008, 'girl'),
(1397, 26, 971, 'girl'),
(1398, 28, 186, 'girl'),
(1399, 28, 971, 'girl'),
(1400, 29, 115, 'girl'),
(1401, 29, 132, 'girl'),
(1402, 29, 148, 'girl'),
(1403, 29, 186, 'girl'),
(1404, 29, 200, 'girl'),
(1405, 29, 201, 'girl'),
(1406, 29, 216, 'girl'),
(1407, 29, 257, 'girl'),
(1408, 29, 978, 'girl'),
(1409, 29, 979, 'girl'),
(1410, 29, 981, 'girl'),
(1411, 29, 984, 'girl'),
(1412, 30, 91, 'girl'),
(1413, 30, 178, 'girl'),
(1414, 30, 201, 'girl'),
(1415, 30, 991, 'girl'),
(1416, 30, 995, 'girl'),
(1417, 48, 12, 'girl'),
(1418, 48, 27, 'girl'),
(1419, 48, 94, 'girl'),
(1420, 48, 119, 'girl'),
(1421, 48, 140, 'girl'),
(1422, 48, 155, 'girl'),
(1423, 48, 156, 'girl'),
(1424, 48, 168, 'girl'),
(1425, 48, 177, 'girl'),
(1426, 48, 193, 'girl'),
(1427, 48, 194, 'girl'),
(1428, 48, 197, 'girl'),
(1429, 48, 198, 'girl'),
(1430, 48, 202, 'girl'),
(1431, 48, 221, 'girl'),
(1432, 48, 230, 'girl'),
(1433, 48, 248, 'girl'),
(1434, 48, 251, 'girl'),
(1435, 48, 971, 'girl'),
(1436, 48, 972, 'girl'),
(1437, 48, 981, 'girl'),
(1438, 48, 987, 'girl'),
(1439, 41, 145, 'girl'),
(1440, 41, 146, 'girl'),
(1441, 41, 256, 'girl'),
(1442, 41, 984, 'girl'),
(1443, 43, 142, 'girl'),
(1444, 43, 143, 'girl'),
(1445, 43, 145, 'girl'),
(1446, 43, 146, 'girl'),
(1447, 43, 147, 'girl'),
(1448, 43, 232, 'girl'),
(1449, 43, 992, 'girl'),
(1450, 43, 994, 'girl'),
(1451, 43, 996, 'girl'),
(1452, 43, 1002, 'girl'),
(1453, 43, 1006, 'girl'),
(1454, 44, 89, 'girl'),
(1455, 44, 91, 'girl'),
(1456, 44, 115, 'girl'),
(1457, 44, 130, 'girl'),
(1458, 44, 132, 'girl'),
(1459, 44, 146, 'girl'),
(1460, 44, 201, 'girl'),
(1461, 44, 216, 'girl'),
(1462, 44, 255, 'girl'),
(1463, 44, 257, 'girl'),
(1464, 44, 979, 'girl'),
(1465, 44, 981, 'girl'),
(1466, 44, 992, 'girl'),
(1467, 44, 1002, 'girl'),
(1468, 44, 1003, 'girl'),
(1469, 45, 27, 'girl'),
(1470, 45, 91, 'girl'),
(1471, 45, 201, 'girl'),
(1472, 45, 216, 'girl'),
(1473, 45, 230, 'girl'),
(1474, 45, 979, 'girl'),
(1475, 45, 997, 'girl'),
(1476, 45, 1003, 'girl'),
(1477, 49, 12, 'girl'),
(1478, 49, 27, 'girl'),
(1479, 49, 94, 'girl'),
(1480, 49, 103, 'girl'),
(1481, 49, 118, 'girl'),
(1482, 49, 119, 'girl'),
(1483, 49, 140, 'girl'),
(1484, 49, 145, 'girl'),
(1485, 49, 146, 'girl'),
(1486, 49, 155, 'girl'),
(1487, 49, 156, 'girl'),
(1488, 49, 177, 'girl'),
(1489, 49, 185, 'girl'),
(1490, 49, 193, 'girl'),
(1491, 49, 194, 'girl'),
(1492, 49, 195, 'girl'),
(1493, 49, 197, 'girl'),
(1494, 49, 198, 'girl'),
(1495, 49, 230, 'girl'),
(1496, 49, 231, 'girl'),
(1497, 49, 252, 'girl'),
(1498, 49, 253, 'girl'),
(1499, 49, 254, 'girl'),
(1500, 49, 257, 'girl'),
(1501, 49, 972, 'girl'),
(1502, 49, 981, 'girl'),
(1503, 49, 987, 'girl'),
(1504, 49, 1005, 'girl'),
(1505, 49, 1006, 'girl'),
(1506, 31, 15, 'girl'),
(1507, 31, 17, 'girl'),
(1508, 31, 29, 'girl'),
(1509, 31, 30, 'girl'),
(1510, 31, 31, 'girl'),
(1511, 31, 37, 'girl'),
(1512, 31, 44, 'girl'),
(1513, 31, 45, 'girl'),
(1514, 31, 64, 'girl'),
(1515, 31, 133, 'girl'),
(1516, 31, 153, 'girl'),
(1517, 31, 233, 'girl'),
(1518, 31, 250, 'girl'),
(1519, 31, 985, 'girl'),
(1520, 31, 986, 'girl'),
(1521, 32, 183, 'girl'),
(1522, 32, 248, 'girl'),
(1523, 33, 103, 'girl'),
(1524, 33, 104, 'girl'),
(1525, 33, 167, 'girl'),
(1526, 33, 251, 'girl'),
(1527, 33, 260, 'girl'),
(1528, 33, 263, 'girl'),
(1529, 33, 998, 'girl'),
(1530, 36, 10, 'girl'),
(1531, 36, 44, 'girl'),
(1532, 36, 45, 'girl'),
(1533, 36, 48, 'girl'),
(1534, 36, 49, 'girl'),
(1535, 36, 50, 'girl'),
(1536, 36, 54, 'girl'),
(1537, 36, 92, 'girl'),
(1538, 36, 101, 'girl'),
(1539, 36, 131, 'girl'),
(1540, 36, 153, 'girl'),
(1541, 36, 154, 'girl'),
(1542, 36, 179, 'girl'),
(1543, 36, 181, 'girl'),
(1544, 36, 188, 'girl'),
(1545, 36, 191, 'girl'),
(1546, 36, 192, 'girl'),
(1547, 36, 989, 'girl'),
(1548, 37, 39, 'girl'),
(1549, 37, 59, 'girl'),
(1550, 37, 107, 'girl'),
(1551, 37, 108, 'girl'),
(1552, 37, 109, 'girl'),
(1553, 37, 110, 'girl'),
(1554, 37, 160, 'girl'),
(1555, 37, 161, 'girl'),
(1556, 37, 163, 'girl'),
(1557, 37, 164, 'girl'),
(1558, 37, 165, 'girl'),
(1559, 37, 236, 'girl'),
(1560, 37, 268, 'girl'),
(1561, 38, 39, 'girl'),
(1562, 38, 59, 'girl'),
(1563, 38, 107, 'girl'),
(1564, 38, 108, 'girl'),
(1565, 38, 109, 'girl'),
(1566, 38, 110, 'girl'),
(1567, 38, 160, 'girl'),
(1568, 38, 161, 'girl'),
(1569, 38, 163, 'girl'),
(1570, 38, 164, 'girl'),
(1571, 38, 165, 'girl'),
(1572, 38, 173, 'girl'),
(1573, 38, 236, 'girl'),
(1574, 38, 249, 'girl'),
(1575, 38, 268, 'girl'),
(1576, 38, 984, 'girl'),
(1577, 39, 118, 'girl'),
(1578, 39, 155, 'girl'),
(1579, 39, 156, 'girl'),
(1580, 39, 195, 'girl'),
(1581, 39, 196, 'girl'),
(1582, 39, 993, 'girl'),
(1583, 39, 1000, 'girl'),
(1584, 39, 1005, 'girl'),
(1585, 40, 50, 'girl'),
(1586, 40, 79, 'girl'),
(1587, 40, 80, 'girl'),
(1588, 40, 81, 'girl'),
(1589, 40, 82, 'girl'),
(1590, 40, 83, 'girl'),
(1591, 40, 84, 'girl'),
(1592, 40, 103, 'girl'),
(1593, 40, 180, 'girl'),
(1594, 40, 189, 'girl'),
(1595, 40, 190, 'girl'),
(1596, 40, 265, 'girl'),
(1597, 40, 990, 'girl'),
(1598, 40, 999, 'girl'),
(1599, 46, 21, 'girl'),
(1600, 46, 24, 'girl'),
(1601, 46, 25, 'girl'),
(1602, 46, 26, 'girl'),
(1603, 46, 34, 'girl'),
(1604, 46, 61, 'girl'),
(1605, 46, 62, 'girl'),
(1606, 46, 63, 'girl'),
(1607, 46, 65, 'girl'),
(1608, 46, 105, 'girl'),
(1609, 46, 124, 'girl'),
(1610, 46, 134, 'girl'),
(1611, 46, 138, 'girl'),
(1612, 46, 139, 'girl'),
(1613, 46, 149, 'girl'),
(1614, 46, 150, 'girl'),
(1615, 46, 151, 'girl'),
(1616, 46, 177, 'girl'),
(1617, 46, 184, 'girl'),
(1618, 46, 187, 'girl'),
(1619, 46, 214, 'girl'),
(1620, 46, 215, 'girl'),
(1621, 46, 217, 'girl'),
(1622, 46, 218, 'girl'),
(1623, 46, 220, 'girl'),
(1624, 46, 240, 'girl'),
(1625, 46, 987, 'girl'),
(1626, 46, 991, 'girl'),
(1627, 46, 1001, 'girl'),
(1628, 46, 1004, 'girl'),
(1629, 47, 11, 'girl'),
(1630, 47, 12, 'girl'),
(1631, 47, 16, 'girl'),
(1632, 47, 22, 'girl'),
(1633, 47, 23, 'girl'),
(1634, 47, 27, 'girl'),
(1635, 47, 43, 'girl'),
(1636, 47, 44, 'girl'),
(1637, 47, 47, 'girl'),
(1638, 47, 52, 'girl'),
(1639, 47, 53, 'girl'),
(1640, 47, 54, 'girl'),
(1641, 47, 60, 'girl'),
(1642, 47, 85, 'girl'),
(1643, 47, 114, 'girl'),
(1644, 47, 115, 'girl'),
(1645, 47, 116, 'girl'),
(1646, 47, 118, 'girl'),
(1647, 47, 120, 'girl'),
(1648, 47, 128, 'girl'),
(1649, 47, 131, 'girl'),
(1650, 47, 132, 'girl'),
(1651, 47, 138, 'girl'),
(1652, 47, 139, 'girl'),
(1653, 47, 140, 'girl'),
(1654, 47, 144, 'girl'),
(1655, 47, 149, 'girl'),
(1656, 47, 156, 'girl'),
(1657, 47, 157, 'girl'),
(1658, 47, 175, 'girl'),
(1659, 47, 177, 'girl'),
(1660, 47, 193, 'girl'),
(1661, 47, 194, 'girl'),
(1662, 47, 195, 'girl'),
(1663, 47, 197, 'girl'),
(1664, 47, 198, 'girl'),
(1665, 47, 207, 'girl'),
(1666, 47, 230, 'girl'),
(1667, 47, 238, 'girl'),
(1668, 47, 241, 'girl'),
(1669, 47, 257, 'girl'),
(1670, 47, 262, 'girl'),
(1671, 47, 972, 'girl'),
(1672, 47, 974, 'girl'),
(1673, 47, 975, 'girl'),
(1674, 47, 976, 'girl'),
(1675, 47, 981, 'girl'),
(1676, 47, 982, 'girl'),
(1677, 47, 983, 'girl'),
(1678, 47, 987, 'girl'),
(1679, 47, 1004, 'girl'),
(1680, 47, 1005, 'girl'),
(1681, 47, 1007, 'girl');

-- --------------------------------------------------------

--
-- Structure de la table `symptome`
--

CREATE TABLE `symptome` (
  `ID_SYMPTOME` int(11) NOT NULL,
  `NOM_SYMPTOME` varchar(255) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Contenu de la table `symptome`
--

INSERT INTO `symptome` (`ID_SYMPTOME`, `NOM_SYMPTOME`, `DESCRIPTION`) VALUES
(9, 'Mal de tête', ''),
(10, 'Mal dans l\'estomac', ''),
(11, 'Fièvre', ''),
(12, 'Mal aux membres', ''),
(13, 'Mal à la gorge', ''),
(14, 'Écoulement nasal', ''),
(15, 'Toux', ''),
(16, 'Fatigue', ''),
(17, 'Douleur dans la poitrine', ''),
(21, 'Démangeaison de la peau', ''),
(22, 'Perte de poids', ''),
(23, 'Prise de poids', ''),
(24, 'Comédon', ''),
(25, 'Bouton', ''),
(26, 'Pustule', ''),
(27, 'Mal aux joints', ''),
(28, 'Nez bouché', ''),
(29, 'Manque d\'air', ''),
(30, 'Sifflement', ''),
(31, 'Opression dans la poitrine', ''),
(33, 'Yeux rouges', ''),
(34, 'Éruption cutanée', ''),
(35, 'Gonflement des lèvres', ''),
(37, 'Palpitation', ''),
(38, 'Saignement du nez', ''),
(39, 'Augmentation du volume urinaire', ''),
(40, 'Soif excessive', ''),
(43, 'Stupeur', ''),
(44, 'Nausée', ''),
(45, 'Brûlures d\'estomac', ''),
(46, 'Brûlure dans la gorge', ''),
(47, 'Tristesse', ''),
(48, 'Ventre gonflé', ''),
(49, 'Désir insatiable de manger', ''),
(50, 'Diarrhée ', ''),
(52, 'Insomnie', ''),
(53, 'Difficulté de concentration', ''),
(54, 'Diminution de l\'appétit', ''),
(57, 'Évanouissement', ''),
(59, 'Mictions fréquentes', ''),
(60, 'Sensibilité au froid', ''),
(61, 'Peau rouge', ''),
(62, 'Cloques sur la peau', ''),
(63, 'Non-cicatrisation des plaies de la peau', ''),
(64, 'Expectorations', ''),
(65, 'Grain de beauté irrégulier', ''),
(66, 'Perte du champ visuel', ''),
(68, 'Troubles de la vision', ''),
(69, 'Sensibilité à la lumière', ''),
(70, 'Difficulté d\'adaptation clair-obscur', ''),
(71, 'Vision double', ''),
(72, 'Halo', ''),
(73, 'Démangeaison des yeux', ''),
(75, 'Brûlure des yeux', ''),
(76, 'Sensation de corps étranger dans l\'oeil', ''),
(77, 'Vision trouble', ''),
(78, 'Bruit dans l\'oreille', ''),
(79, 'Défécation difficile', ''),
(80, 'Défécation difficile', ''),
(81, 'Défécation incomplète', ''),
(82, 'Moins de 3 défécations par semaine', ''),
(83, 'Selles grasses', ''),
(84, 'Défécation malodorante', ''),
(85, 'Sautes d\'humeur', ''),
(86, 'Sensation de pression dans l\'oreille', ''),
(87, 'Mal à l\'oreille', ''),
(88, 'Démangeaison dans l\'oreille', ''),
(89, 'Pieds froids', ''),
(90, 'Malentendants', ''),
(91, 'Changements dans les ongles', ''),
(92, 'Satiété précoce', ''),
(93, 'Difficulté à avaler', ''),
(94, 'Crampes', ''),
(95, 'Éternuements', ''),
(96, 'Démangeaison dans le nez', ''),
(97, 'Aphtes', ''),
(98, 'Difficulté à parler', ''),
(101, 'Vomissement', ''),
(102, 'Sensibilité au bruit', ''),
(103, 'Douleur irradiant à la jambe', ''),
(104, 'Mal au dos', ''),
(105, 'Peau de couleur jaune', ''),
(106, 'Coloration jaunâtre de la partie blanche de l\'oeil', ''),
(107, 'Sensation de brûlure au moment d\'uriner', ''),
(108, 'Urine foncée', ''),
(109, 'Miction douloureuse', ''),
(110, 'Verrues génitales', ''),
(112, 'Trouble de la menstruation', ''),
(113, 'Trouble de l\'érection', ''),
(114, 'Nervosité', ''),
(115, 'Tremblement au repos', ''),
(116, 'Maigreur', ''),
(118, 'Inactivité physique', ''),
(119, 'Raideur musculaire', ''),
(120, 'Troubles de l\'équilibre', ''),
(121, 'Enrouement', ''),
(122, 'Hoquet', ''),
(123, 'Absence des règles', ''),
(124, 'Eruption cutanée', ''),
(125, 'Manque de mémoire', ''),
(126, 'Trouble à trouver les mots', ''),
(128, 'Désorientation temporelle ou locale', ''),
(129, 'Problèmes avec le sens du toucher dans le visage', ''),
(130, 'Problèmes avec le sens du toucher dans les pieds', ''),
(131, 'Augmentation de l\'appétit', ''),
(132, 'Tremblement au mouvement', ''),
(133, 'Toux nocturne', ''),
(134, 'Croûtes', ''),
(135, 'Mal à  la bouche', ''),
(136, 'Mal au cou', ''),
(137, 'Hypersensibilité à la lumière', ''),
(138, 'Transpiration', ''),
(139, 'Sueurs froides', ''),
(140, 'Paralysie', ''),
(142, 'Mal aux mollets', ''),
(143, 'Ulcère de jambe', ''),
(144, 'Inconscience courte', ''),
(145, 'Sensation de tension dans les jambes', ''),
(146, 'Crampes dans les jambes', ''),
(147, 'Gonflement des chevilles', ''),
(148, 'Gonflement de la main', ''),
(149, 'Bouffées de chaleur', ''),
(150, 'Pâleur', ''),
(151, 'Peau sèche', ''),
(152, 'Perte des cheveux', ''),
(153, 'Respiration rapide et approfondi', ''),
(154, 'Flatulence', ''),
(155, 'Mal aux os', ''),
(156, 'Fracture osseuse', ''),
(157, 'Surpoids', ''),
(160, 'Urgence d\'uriner', ''),
(161, 'Uriner pendant la nuit', ''),
(162, 'Perte d\'urine après uriner', ''),
(163, 'Diminution du jet urinaire', ''),
(164, 'Sensation de vidange incomplète', ''),
(165, 'Difficulté à uriner lié à un début retardé', ''),
(166, 'Faible vue pour les objets proches', ''),
(167, 'Mobilité réduite de la colonne vertébrale', ''),
(168, 'Faiblesse musculaire dans le bras', ''),
(169, 'Gonflement des ganglions dans le cou', ''),
(170, 'Gonflement des joues', ''),
(172, 'Écoulement purulent par l\'urètre', ''),
(173, 'Écoulement purulent par le vagin', ''),
(174, 'Douleurs abdominales basses', ''),
(175, 'Frissons', ''),
(176, 'Saignement dans l\'oeil', ''),
(177, 'Douleur musculaire', ''),
(178, 'Mobilité réduite des doigts', ''),
(179, 'Douleur brûlante de l\'estomac', ''),
(180, 'Selles noires', ''),
(181, 'Vomissements de sang', ''),
(183, 'Douleur au flanc', ''),
(184, 'Durcissement de la peau', ''),
(185, 'Douleur à la jambe liée au stress', ''),
(186, 'Mal à la main', ''),
(187, 'Blessure', ''),
(188, 'Défense abdominale', ''),
(189, 'Défécation douloureuse', ''),
(190, 'Sang dans les selles', ''),
(191, 'Douleur à la décompression', ''),
(192, 'Bombé à la paroi abdominale', ''),
(193, 'Gonflement des articulations', ''),
(194, 'Épanchement articulaire', ''),
(195, 'Mobilité réduite de la jambe', ''),
(196, 'Douleur à la hanche', ''),
(197, 'Immobilisation', ''),
(198, 'Instabilité de l\'articulation', ''),
(200, 'Engourdissement des mains', ''),
(201, 'Picotements', ''),
(202, 'Atrophie musculaire du bras', ''),
(203, 'Mal à avaler', ''),
(204, 'Augmentation de la salivation', ''),
(205, 'Machoire bloquée', ''),
(206, 'Perte auditive', ''),
(207, 'Étourdissement', ''),
(208, 'Gonflement des paupières', ''),
(209, 'Paupières collées', ''),
(210, 'Perte de cils', ''),
(211, 'Larmes', ''),
(213, 'Faible vue pour les objets lointains', ''),
(214, 'Desquamation de la peau', ''),
(215, 'Peau humide, ramolli', ''),
(216, 'Décoloration des ongles', ''),
(217, 'Épaississement de la peau', ''),
(218, 'Grossissement de la structure de la peau', ''),
(219, 'Mal au visage', ''),
(220, 'Augmentation de la sensibilité tactile', ''),
(221, 'Engourdissement dans le bras', ''),
(222, 'Douleurs testiculaires', ''),
(223, 'Douleurs abdominales associées aux menstruations', ''),
(228, 'Toux grasse', ''),
(230, 'Joints rouges', ''),
(231, 'Gonflement des jambes', ''),
(232, 'Veines marquées', ''),
(233, 'Toux sanglante', ''),
(234, 'Raideur dans le cou', ''),
(235, 'Trou de mémoire', ''),
(236, 'Gonflement dans la région génitale', ''),
(238, 'Anxiété', ''),
(239, 'Chute des cheveux', ''),
(240, 'Tache bleue sur la peau', ''),
(241, 'Somnolence avec endormissement spontané', ''),
(242, 'Clignotement des yeux', ''),
(243, 'Tique', ''),
(244, 'Paupière tombantes', ''),
(245, 'Desquamation de la peau sur la tête', ''),
(246, 'Paralysie faciale', ''),
(247, 'Démangeaison sur la tête', ''),
(248, 'Gonflement des ganglions au niveau des aisselles', ''),
(249, 'Gonflement des ganglions dans l\'aine', ''),
(250, 'Douleurs à la respiration', ''),
(251, 'Douleur irradiant au bras', ''),
(252, 'Atrophie musculaire dans la jambe', ''),
(253, 'Faiblesse musculaire dans la jambe', ''),
(254, 'Engourdissement dans le jambe', ''),
(255, 'Mal au pied', ''),
(256, 'Mal au genou', ''),
(257, 'Mouvements involontaires', ''),
(258, 'Protrusion des yeux', ''),
(260, 'Courbure de la colonne vertébrale', ''),
(261, 'Boule dans le sein', ''),
(262, 'Augmentation des pulsions', ''),
(263, 'Douleurs lombaires', ''),
(264, 'Langue rouge brillant', ''),
(265, 'Défécation incontrôlée', ''),
(266, 'Malposition des testicules', ''),
(267, 'Gonflement des testicules', ''),
(268, 'Démangeaison ou brûlure dans la région génitale', ''),
(269, 'Scalp rouge', ''),
(270, 'Vision double, aiguë', ''),
(272, 'Bouche sèche', ''),
(273, 'Yeux secs', ''),
(284, 'Saignement de vagin', ''),
(286, 'Mal à mâcher', ''),
(287, 'Mal aux yeux', ''),
(288, 'Brûlures dans le nez', ''),
(970, 'Gonflement du visage', ''),
(971, 'Gonflement du bras', ''),
(972, 'Faiblesse ou engourdissement du côté droite ou gauche du corps', ''),
(973, 'Démangeaison dans la gueule ou gorge', ''),
(974, 'Agressivité', ''),
(975, 'Tristesse', ''),
(976, 'Hallucination', ''),
(977, 'Gonflement de la langue', ''),
(978, 'Mains froides', ''),
(979, 'Fragilité des ongles', ''),
(980, 'Brûlure à la langue', ''),
(981, 'Agitation', ''),
(982, 'Sensation de défaillance', ''),
(983, 'Raideur matinale', ''),
(984, 'Absence de pouls', ''),
(985, 'Souffle au coeur', ''),
(986, 'Rythme cardiaque irrégulier', ''),
(987, 'Faiblesse musculaire', ''),
(988, 'Difficulté à comprendre le discours', ''),
(989, 'Estomac dilaté', ''),
(990, 'Mal à l\'anus', ''),
(991, 'Peau de couleur bleue', ''),
(992, 'Mobilité reduite de la cheville', ''),
(993, 'Déformation de la hanche', ''),
(994, 'Déformation du genou', ''),
(995, 'Déformation du doigt', ''),
(996, 'Déformation de la cheville', ''),
(997, 'Déformation de l\'orteil', ''),
(998, 'Déformation du dos', ''),
(999, 'Démangeaison de l\'anus', ''),
(1000, 'Mobilité réduite de la hanche', ''),
(1001, 'Cicatrice', ''),
(1002, 'Gonflement du pied', ''),
(1003, 'Gonflement de l\'orteil', ''),
(1004, 'Sueurs nocturnes', ''),
(1005, 'Difficulté avec la démarche', ''),
(1006, 'Mollet agrandi', ''),
(1007, 'Difficulté d\'apprentissage', ''),
(1008, 'Mal aux dents', '');

-- --------------------------------------------------------

--
-- Structure de la table `user_reponse`
--

CREATE TABLE `user_reponse` (
  `ID_USER` varchar(255) COLLATE utf8_bin NOT NULL,
  `ID_REPONSE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `CIN` varchar(255) COLLATE utf8_bin NOT NULL,
  `NOM` varchar(255) COLLATE utf8_bin NOT NULL,
  `PRENOM` varchar(255) COLLATE utf8_bin NOT NULL,
  `EMAIL` varchar(255) COLLATE utf8_bin NOT NULL,
  `DATE_NAISS` date NOT NULL,
  `SEXE` varchar(255) COLLATE utf8_bin NOT NULL,
  `PAYS` varchar(255) COLLATE utf8_bin NOT NULL,
  `VILLE` varchar(255) COLLATE utf8_bin NOT NULL,
  `NUM_TEL` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Contenu de la table `utilisateur`
--

INSERT INTO `utilisateur` (`CIN`, `NOM`, `PRENOM`, `EMAIL`, `DATE_NAISS`, `SEXE`, `PAYS`, `VILLE`, `NUM_TEL`) VALUES
('05545a', 'aaza', 'kjhjdfsd', 'hjh', '2018-02-13', 'azaz', 'qsqdq', 'qsdq', 0);

--
-- Index pour les tables exportées
--

--
-- Index pour la table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`LOGIN_ADMIN`);

--
-- Index pour la table `aliment`
--
ALTER TABLE `aliment`
  ADD PRIMARY KEY (`NOM_ALIMENT`);

--
-- Index pour la table `aliment_regime`
--
ALTER TABLE `aliment_regime`
  ADD PRIMARY KEY (`NOM_ALIMENT`,`ID_REGIME`),
  ADD KEY `fk_aliment_regimealiment` (`ID_REGIME`);

--
-- Index pour la table `article`
--
ALTER TABLE `article`
  ADD PRIMARY KEY (`REFERENCE`),
  ADD KEY `fk_ARTICLE_MEDECIN` (`ID_MEDECIN`);

--
-- Index pour la table `body_parts`
--
ALTER TABLE `body_parts`
  ADD PRIMARY KEY (`ID_BODY_PART`);

--
-- Index pour la table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`NUMERO_COMMANDE`),
  ADD KEY `fk_COMMANDE_CLIENT` (`ID_CLIENT`);

--
-- Index pour la table `evennement`
--
ALTER TABLE `evennement`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `evennement_participants`
--
ALTER TABLE `evennement_participants`
  ADD PRIMARY KEY (`ID_EVENT`,`ID_USER`),
  ADD KEY `fk_USER_EVENTPARTICIPANTS` (`ID_USER`);

--
-- Index pour la table `information_sante`
--
ALTER TABLE `information_sante`
  ADD PRIMARY KEY (`login`);

--
-- Index pour la table `ligne_commande`
--
ALTER TABLE `ligne_commande`
  ADD PRIMARY KEY (`ID_PRODUIT`,`ID_COMMANDE`),
  ADD KEY `fk_COMMANDE_LIGNECOMMANDE` (`ID_COMMANDE`);

--
-- Index pour la table `medecin`
--
ALTER TABLE `medecin`
  ADD PRIMARY KEY (`LOGIN`);

--
-- Index pour la table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`LOGIN`),
  ADD KEY `fk_PATIENT_UTILISATEUR` (`CIN_USER`);

--
-- Index pour la table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`ID_PRODUIT`),
  ADD UNIQUE KEY `REFERENCE` (`REFERENCE`);

--
-- Index pour la table `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `fk_QUESTION_PATIENT` (`ID_PATIENT`);

--
-- Index pour la table `regime`
--
ALTER TABLE `regime`
  ADD PRIMARY KEY (`ID_REGIME`);

--
-- Index pour la table `regime_sport`
--
ALTER TABLE `regime_sport`
  ADD PRIMARY KEY (`ID_REGIME`,`NOM_SPORT`),
  ADD KEY `FK_sport_sportregime` (`NOM_SPORT`);

--
-- Index pour la table `regime_user`
--
ALTER TABLE `regime_user`
  ADD PRIMARY KEY (`ID_USER`,`ID_REGIME`),
  ADD KEY `fk_regime_userregime` (`ID_REGIME`);

--
-- Index pour la table `rendez_vous`
--
ALTER TABLE `rendez_vous`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `fk_PATIENT_RENDEZVOUS` (`USER_ID`);

--
-- Index pour la table `reponse`
--
ALTER TABLE `reponse`
  ADD PRIMARY KEY (`ID_REPONSE`),
  ADD KEY `fk_REPONSE_MEDECIN` (`ID_MEDECIN`),
  ADD KEY `fk_REPONSE_QUESTION` (`ID_QUESTION`);

--
-- Index pour la table `reponses_possibles`
--
ALTER TABLE `reponses_possibles`
  ADD PRIMARY KEY (`ID_REPONSE`),
  ADD KEY `reponses_possibles_ibfk_1` (`ID_SONDAGE`);

--
-- Index pour la table `sondage`
--
ALTER TABLE `sondage`
  ADD PRIMARY KEY (`ID_SONDAGE`);

--
-- Index pour la table `sport`
--
ALTER TABLE `sport`
  ADD PRIMARY KEY (`NOM_SPORT`);

--
-- Index pour la table `sub_body_parts`
--
ALTER TABLE `sub_body_parts`
  ADD PRIMARY KEY (`ID_SUB_BODY`),
  ADD KEY `ID_BODY_PART` (`ID_BODY_PART`);

--
-- Index pour la table `sub_body_parts_symptome`
--
ALTER TABLE `sub_body_parts_symptome`
  ADD PRIMARY KEY (`ID_sub_body_parts_symptome`,`ID_SUB_BODY`,`ID_SYMPTOME`),
  ADD KEY `ID_SUB_BODY` (`ID_SUB_BODY`),
  ADD KEY `FK_SYMPTOME` (`ID_SYMPTOME`);

--
-- Index pour la table `symptome`
--
ALTER TABLE `symptome`
  ADD PRIMARY KEY (`ID_SYMPTOME`);

--
-- Index pour la table `user_reponse`
--
ALTER TABLE `user_reponse`
  ADD PRIMARY KEY (`ID_USER`,`ID_REPONSE`),
  ADD KEY `fk_userreponse_reponsespossibles` (`ID_REPONSE`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`CIN`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `evennement`
--
ALTER TABLE `evennement`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `produit`
--
ALTER TABLE `produit`
  MODIFY `ID_PRODUIT` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT pour la table `question`
--
ALTER TABLE `question`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `rendez_vous`
--
ALTER TABLE `rendez_vous`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `reponse`
--
ALTER TABLE `reponse`
  MODIFY `ID_REPONSE` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `reponses_possibles`
--
ALTER TABLE `reponses_possibles`
  MODIFY `ID_REPONSE` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `sondage`
--
ALTER TABLE `sondage`
  MODIFY `ID_SONDAGE` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `sub_body_parts_symptome`
--
ALTER TABLE `sub_body_parts_symptome`
  MODIFY `ID_sub_body_parts_symptome` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1682;
--
-- AUTO_INCREMENT pour la table `symptome`
--
ALTER TABLE `symptome`
  MODIFY `ID_SYMPTOME` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1009;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `aliment_regime`
--
ALTER TABLE `aliment_regime`
  ADD CONSTRAINT `fk_aliment_regimealiment` FOREIGN KEY (`NOM_ALIMENT`) REFERENCES `aliment` (`NOM_ALIMENT`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_aliment_regimeregime` FOREIGN KEY (`ID_REGIME`) REFERENCES `regime` (`ID_REGIME`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `article`
--
ALTER TABLE `article`
  ADD CONSTRAINT `fk_ARTICLE_MEDECIN` FOREIGN KEY (`ID_MEDECIN`) REFERENCES `medecin` (`LOGIN`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `fk_COMMANDE_CLIENT` FOREIGN KEY (`ID_CLIENT`) REFERENCES `patient` (`LOGIN`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `evennement_participants`
--
ALTER TABLE `evennement_participants`
  ADD CONSTRAINT `fk_EVENT_EVENTPARTICIPANTS` FOREIGN KEY (`ID_EVENT`) REFERENCES `evennement` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_USER_EVENTPARTICIPANTS` FOREIGN KEY (`ID_USER`) REFERENCES `patient` (`LOGIN`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `information_sante`
--
ALTER TABLE `information_sante`
  ADD CONSTRAINT `fk_login_informationSante` FOREIGN KEY (`login`) REFERENCES `patient` (`LOGIN`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `ligne_commande`
--
ALTER TABLE `ligne_commande`
  ADD CONSTRAINT `fk_COMMANDE_LIGNECOMMANDE` FOREIGN KEY (`ID_COMMANDE`) REFERENCES `commande` (`NUMERO_COMMANDE`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_PRODUITT_LIGNECOMMANDE` FOREIGN KEY (`ID_PRODUIT`) REFERENCES `produit` (`ID_PRODUIT`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `medecin`
--
ALTER TABLE `medecin`
  ADD CONSTRAINT `fk_PATIENT_MEDECIN` FOREIGN KEY (`LOGIN`) REFERENCES `patient` (`LOGIN`);

--
-- Contraintes pour la table `patient`
--
ALTER TABLE `patient`
  ADD CONSTRAINT `fk_PATIENT_UTILISATEUR` FOREIGN KEY (`CIN_USER`) REFERENCES `utilisateur` (`CIN`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `question`
--
ALTER TABLE `question`
  ADD CONSTRAINT `fk_QUESTION_PATIENT` FOREIGN KEY (`ID_PATIENT`) REFERENCES `patient` (`LOGIN`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `regime_sport`
--
ALTER TABLE `regime_sport`
  ADD CONSTRAINT `fk_regime_sportregime` FOREIGN KEY (`ID_REGIME`) REFERENCES `regime` (`ID_REGIME`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_sport_sportregime` FOREIGN KEY (`NOM_SPORT`) REFERENCES `sport` (`NOM_SPORT`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `regime_user`
--
ALTER TABLE `regime_user`
  ADD CONSTRAINT `fk_regime_userregime` FOREIGN KEY (`ID_REGIME`) REFERENCES `regime` (`ID_REGIME`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_user_userregime` FOREIGN KEY (`ID_USER`) REFERENCES `patient` (`LOGIN`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `rendez_vous`
--
ALTER TABLE `rendez_vous`
  ADD CONSTRAINT `fk_PATIENT_RENDEZVOUS` FOREIGN KEY (`USER_ID`) REFERENCES `patient` (`LOGIN`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `reponse`
--
ALTER TABLE `reponse`
  ADD CONSTRAINT `fk_REPONSE_MEDECIN` FOREIGN KEY (`ID_MEDECIN`) REFERENCES `medecin` (`LOGIN`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_REPONSE_QUESTION` FOREIGN KEY (`ID_QUESTION`) REFERENCES `question` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `reponses_possibles`
--
ALTER TABLE `reponses_possibles`
  ADD CONSTRAINT `reponses_possibles_ibfk_1` FOREIGN KEY (`ID_SONDAGE`) REFERENCES `sondage` (`ID_SONDAGE`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `sub_body_parts`
--
ALTER TABLE `sub_body_parts`
  ADD CONSTRAINT `FK_ID_BODY_PART` FOREIGN KEY (`ID_BODY_PART`) REFERENCES `body_parts` (`ID_BODY_PART`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `sub_body_parts_symptome`
--
ALTER TABLE `sub_body_parts_symptome`
  ADD CONSTRAINT `FK_SUB_BODY` FOREIGN KEY (`ID_SUB_BODY`) REFERENCES `sub_body_parts` (`ID_SUB_BODY`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_SYMPTOME` FOREIGN KEY (`ID_SYMPTOME`) REFERENCES `symptome` (`ID_SYMPTOME`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `user_reponse`
--
ALTER TABLE `user_reponse`
  ADD CONSTRAINT `fk_userreponse_patient` FOREIGN KEY (`ID_USER`) REFERENCES `patient` (`LOGIN`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_userreponse_reponsespossibles` FOREIGN KEY (`ID_REPONSE`) REFERENCES `reponses_possibles` (`ID_REPONSE`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
