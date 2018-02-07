-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Mar 06 Février 2018 à 11:14
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
  `QUANTITE` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `aliment_regime`
--

CREATE TABLE `aliment_regime` (
  `NOM_ALIMENT` varchar(255) COLLATE utf8_bin NOT NULL,
  `ID_REGIME` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `article`
--

CREATE TABLE `article` (
  `REFERENCE` int(11) NOT NULL,
  `NOM` varchar(255) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(255) COLLATE utf8_bin NOT NULL,
  `CONTENU` varchar(255) COLLATE utf8_bin NOT NULL,
  `ID_MEDECIN` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

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

-- --------------------------------------------------------

--
-- Structure de la table `evennement`
--

CREATE TABLE `evennement` (
  `ID` int(11) NOT NULL,
  `NOM` varchar(255) COLLATE utf8_bin NOT NULL,
  `DATE_HEURE` datetime NOT NULL,
  `LOCATION` varchar(255) COLLATE utf8_bin NOT NULL,
  `TYPE` varchar(255) COLLATE utf8_bin NOT NULL,
  `MAX_PARTICIPANTS` int(11) NOT NULL
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
-- Structure de la table `ligne_commande`
--

CREATE TABLE `ligne_commande` (
  `ID_PRODUIT` varchar(255) COLLATE utf8_bin NOT NULL,
  `ID_COMMANDE` int(11) NOT NULL,
  `PRIX` float NOT NULL,
  `QUANTITE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `ligne_promotion`
--

CREATE TABLE `ligne_promotion` (
  `ID_PROMOTION` int(11) NOT NULL,
  `ID_PRODUIT` varchar(255) COLLATE utf8_bin NOT NULL,
  `PRIX_PROMOTION` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `maladie`
--

CREATE TABLE `maladie` (
  `ID_MALADIE` int(11) NOT NULL,
  `NOM_MALADIE` varchar(255) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `maladie_symptome`
--

CREATE TABLE `maladie_symptome` (
  `ID_MALADIE` int(11) NOT NULL,
  `ID_SYMPTOME` int(11) NOT NULL
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

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE `produit` (
  `REFERENCE` varchar(255) COLLATE utf8_bin NOT NULL,
  `NOM` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `promotion`
--

CREATE TABLE `promotion` (
  `ID_PROMOTION` int(11) NOT NULL,
  `DATE_DEBUT` date NOT NULL,
  `DATE_FIN` date NOT NULL,
  `POURCENTAGE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `question`
--

CREATE TABLE `question` (
  `ID` int(11) NOT NULL,
  `QUESTION` varchar(255) COLLATE utf8_bin NOT NULL,
  `ID_PATIENT` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `regime`
--

CREATE TABLE `regime` (
  `ID_REGIME` int(11) NOT NULL,
  `TYPE` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `regime_sport`
--

CREATE TABLE `regime_sport` (
  `ID_REGIME` int(11) NOT NULL,
  `NOM_SPORT` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `regime_user`
--

CREATE TABLE `regime_user` (
  `ID_USER` varchar(255) COLLATE utf8_bin NOT NULL,
  `ID_REGIME` int(11) NOT NULL
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
  `ID_QUESTION` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `sondage`
--

CREATE TABLE `sondage` (
  `NOM_SONDAGE` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Contenu de la table `sondage`
--

INSERT INTO `sondage` (`NOM_SONDAGE`) VALUES
('aaa'),
('bbbb');

-- --------------------------------------------------------

--
-- Structure de la table `sport`
--

CREATE TABLE `sport` (
  `NOM_SPORT` varchar(255) COLLATE utf8_bin NOT NULL,
  `TYPE` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `symptome`
--

CREATE TABLE `symptome` (
  `ID_SYMPTOME` int(11) NOT NULL,
  `NOM_SYMPTOME` varchar(255) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(255) COLLATE utf8_bin NOT NULL
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
  `VILLE` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur_sondage`
--

CREATE TABLE `utilisateur_sondage` (
  `ID_USER` varchar(255) COLLATE utf8_bin NOT NULL,
  `NOM_SONDAGE` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

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
-- Index pour la table `ligne_commande`
--
ALTER TABLE `ligne_commande`
  ADD PRIMARY KEY (`ID_PRODUIT`,`ID_COMMANDE`),
  ADD KEY `fk_COMMANDE_LIGNECOMMANDE` (`ID_COMMANDE`);

--
-- Index pour la table `ligne_promotion`
--
ALTER TABLE `ligne_promotion`
  ADD PRIMARY KEY (`ID_PROMOTION`,`ID_PRODUIT`),
  ADD KEY `fk_PRODUIT_LIGNEPROMOTION` (`ID_PRODUIT`);

--
-- Index pour la table `maladie`
--
ALTER TABLE `maladie`
  ADD PRIMARY KEY (`ID_MALADIE`);

--
-- Index pour la table `maladie_symptome`
--
ALTER TABLE `maladie_symptome`
  ADD PRIMARY KEY (`ID_MALADIE`,`ID_SYMPTOME`),
  ADD KEY `fk_SYMPTOME_MALADIESYMPTOME` (`ID_SYMPTOME`);

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
  ADD PRIMARY KEY (`REFERENCE`);

--
-- Index pour la table `promotion`
--
ALTER TABLE `promotion`
  ADD PRIMARY KEY (`ID_PROMOTION`);

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
-- Index pour la table `sondage`
--
ALTER TABLE `sondage`
  ADD PRIMARY KEY (`NOM_SONDAGE`);

--
-- Index pour la table `sport`
--
ALTER TABLE `sport`
  ADD PRIMARY KEY (`NOM_SPORT`);

--
-- Index pour la table `symptome`
--
ALTER TABLE `symptome`
  ADD PRIMARY KEY (`ID_SYMPTOME`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`CIN`);

--
-- Index pour la table `utilisateur_sondage`
--
ALTER TABLE `utilisateur_sondage`
  ADD PRIMARY KEY (`ID_USER`,`NOM_SONDAGE`),
  ADD KEY `fk_SONDAGEUSERSONDAGE` (`NOM_SONDAGE`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `evennement`
--
ALTER TABLE `evennement`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `maladie`
--
ALTER TABLE `maladie`
  MODIFY `ID_MALADIE` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `promotion`
--
ALTER TABLE `promotion`
  MODIFY `ID_PROMOTION` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `question`
--
ALTER TABLE `question`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `regime`
--
ALTER TABLE `regime`
  MODIFY `ID_REGIME` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `rendez_vous`
--
ALTER TABLE `rendez_vous`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT pour la table `reponse`
--
ALTER TABLE `reponse`
  MODIFY `ID_REPONSE` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `symptome`
--
ALTER TABLE `symptome`
  MODIFY `ID_SYMPTOME` int(11) NOT NULL AUTO_INCREMENT;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `aliment_regime`
--
ALTER TABLE `aliment_regime`
  ADD CONSTRAINT `fk_aliment_regimealiment` FOREIGN KEY (`ID_REGIME`) REFERENCES `regime` (`ID_REGIME`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_regime_regimealiment` FOREIGN KEY (`NOM_ALIMENT`) REFERENCES `aliment` (`NOM_ALIMENT`) ON DELETE CASCADE ON UPDATE CASCADE;

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
-- Contraintes pour la table `ligne_commande`
--
ALTER TABLE `ligne_commande`
  ADD CONSTRAINT `fk_COMMANDE_LIGNECOMMANDE` FOREIGN KEY (`ID_COMMANDE`) REFERENCES `commande` (`NUMERO_COMMANDE`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_PRODUIT_LIGNECOMMANDE` FOREIGN KEY (`ID_PRODUIT`) REFERENCES `produit` (`REFERENCE`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `ligne_promotion`
--
ALTER TABLE `ligne_promotion`
  ADD CONSTRAINT `fk_PRODUIT_LIGNEPROMOTION` FOREIGN KEY (`ID_PRODUIT`) REFERENCES `produit` (`REFERENCE`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_PROMOTION_LIGNEPROMOTION` FOREIGN KEY (`ID_PROMOTION`) REFERENCES `promotion` (`ID_PROMOTION`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `maladie_symptome`
--
ALTER TABLE `maladie_symptome`
  ADD CONSTRAINT `fk_MALADIE_MALADIESYMPTOME` FOREIGN KEY (`ID_MALADIE`) REFERENCES `maladie` (`ID_MALADIE`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_SYMPTOME_MALADIESYMPTOME` FOREIGN KEY (`ID_SYMPTOME`) REFERENCES `symptome` (`ID_SYMPTOME`) ON DELETE CASCADE ON UPDATE CASCADE;

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
  ADD CONSTRAINT `FK_regime_sportregime` FOREIGN KEY (`ID_REGIME`) REFERENCES `regime` (`ID_REGIME`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_sport_sportregime` FOREIGN KEY (`NOM_SPORT`) REFERENCES `sport` (`NOM_SPORT`) ON DELETE CASCADE ON UPDATE CASCADE;

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
-- Contraintes pour la table `utilisateur_sondage`
--
ALTER TABLE `utilisateur_sondage`
  ADD CONSTRAINT `fk_SONDAGEUSERSONDAGE` FOREIGN KEY (`NOM_SONDAGE`) REFERENCES `sondage` (`NOM_SONDAGE`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_SONDAGE_USERSONDAGE` FOREIGN KEY (`ID_USER`) REFERENCES `patient` (`LOGIN`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
