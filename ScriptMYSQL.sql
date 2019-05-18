create database carnetsPatient;

use carnetsPatient;

CREATE TABLE consultation(
	dateConsultation TIMESTAMP NOT NULL,
	observations VARCHAR(250),
	soignant_id INT NOT NULL,
	patient_id INT NOT NULL,
    primary key (dateConsultation, soignant_id, patient_id)
) ENGINE = INNODB;

CREATE TABLE patient(
	patient_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    numeroNational VARCHAR(11) NOT NULL,
	nom VARCHAR(30) NOT NULL,
	prenom VARCHAR(30) NOT NULL,
    nbEnfants INT NOT NULL,
	dateNaissance date NOT NULL,
	numTelFixe VARCHAR(20),
	numTelMobile VARCHAR(20),
	remarque VARCHAR(250),
	aSurveiller VARCHAR(250),
	conseils VARCHAR(250),
	donnerEtat boolean NOT NULL,
	besoinAval boolean NOT NULL,
	acharnementTherapeutique boolean NOT NULL,
	causeDecesPere VARCHAR(250),
	causeDecesMere VARCHAR(250),
    primeAnuelle DOUBLE,
	mutualite_id INT
) ENGINE = INNODB;

CREATE TABLE mutualite(
	mutualite_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	libelle VARCHAR(50) NOT NULL,
    affiliationPolitique VARCHAR(50) NOT NULL,
    diminutif VARCHAR(10) NOT NULL
) ENGINE = INNODB;

CREATE TABLE soignant(
	soignant_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    numeroNational VARCHAR(11) UNIQUE NOT NULL,
    motDePasse VARCHAR(128) NOT NULL,
	nom VARCHAR(30) NOT NULL,
	prenom VARCHAR(30) NOT NULL,
	numTel VARCHAR(20) NOT NULL,
    numeroINAMI VARCHAR(11),
    specialite VARCHAR(50) NOT NULL
) ENGINE = INNODB;

CREATE TABLE proche(
	proche_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
	numTel VARCHAR(20) NOT NULL,
    remarques VARCHAR(250),
	aAccesInfosMedicales boolean NOT NULL,
	aAppellerSiUrgence boolean NOT NULL,
    patient_id INT NOT NULL
) ENGINE = INNODB;

CREATE TABLE souffrance(
	patient_id INT NOT NULL,
    allergie_id INT NOT NULL,
    primary key (patient_id, allergie_id)
) ENGINE = INNODB;

CREATE TABLE allergie(
	allergie_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    libelle VARCHAR(50) NOT NULL,
    symptome VARCHAR(250) NOT NULL,
    conditionnement VARCHAR(250) NOT NULL
) ENGINE = INNODB;

CREATE TABLE reaction(
    allergie_id INT NOT NULL,
    medicament_id INT NOT NULL,
    primary key (allergie_id, medicament_id)
) ENGINE = INNODB;

CREATE TABLE medicament(
	medicament_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	codeCNK VARCHAR(7) NOT NULL,
    nom VARCHAR(50) NOT NULL,
    firme VARCHAR(50) NOT NULL,
    principeActif VARCHAR(50) NOT NULL,
    codeATC VARCHAR(8) NOT NULL,
    caracteristique VARCHAR(250) NOT NULL,
    tauxRemboursement DOUBLE NOT NULL
) ENGINE = INNODB;

CREATE TABLE traitement(
	dateDeDebut DATE NOT NULL,
    dateDeFin DATE NOT NULL,
    frequence VARCHAR(100),
    patient_id INT NOT NULL,
    soignant_id INT NOT NULL,
    medicament_id INT NOT NULL,
    primary key (dateDeDebut, dateDeFin, patient_id, soignant_id, medicament_id)
) ENGINE = INNODB;

ALTER TABLE consultation
	add(
		constraint cons_soignant_id_fk foreign key (soignant_id) references soignant(soignant_id),
		constraint cons_patient_id_fk foreign key (patient_id) references patient(patient_id)
	);
    
ALTER TABLE patient
	add(
		constraint pat_mutualite_id_fk foreign key (mutualite_id) references mutualite(mutualite_id) ON DELETE SET NULL,
        constraint pat_nbEnfants_ck check(nbEnfants>=0 and nbEnfants<=50)
	);
    
ALTER TABLE proche
	add(
		constraint pro_patient_id_fk foreign key (patient_id) references patient(patient_id)
	);
    
ALTER TABLE mutualite
	add(
        constraint mut_prime_ck check(primeAnuelle>=0)
	);

ALTER TABLE souffrance
	add(
		constraint souf_patient_id_fk foreign key (patient_id) references patient(patient_id),
        constraint souf_allergie_id_fk foreign key (allergie_id) references allergie(allergie_id)
	);
    
ALTER TABLE reaction
	add(
		constraint rea_allergie_id_fk foreign key (allergie_id) references allergie(allergie_id),
        constraint rea_medicament_id_fk foreign key (medicament_id) references medicament(medicament_id)
	);
    
ALTER TABLE medicament
	add(
        constraint med_taux_ck check(tauxRemboursement>=0)
	);
    
ALTER TABLE traitement
	add(
		constraint trt_patient_id_fk foreign key (patient_id) references patient(patient_id),
        constraint trt_soignant_id_fk foreign key (soignant_id) references soignant(soignant_id),
        constraint trt_medicament_id_fk foreign key (medicament_id) references medicament(medicament_id),
		constraint trt_dateCorrecte_ck check(dateDeDebut < dateDeFin)
	);

INSERT INTO soignant VALUES
	(null, 11111111111, 'a74d29dfcb83304b297c4d7142cabe54adad7cc219be42c3a1b1bf1f71b68d3ab3577eacf4db64ef39764deb75b28d0ddb5ab8b70eb7fe78beca19d5424fa442', 'Moray', 'Jérémy', '0485 46 25 10', '15481644897', 'Psychiatre'),
	(null, 98071216926, '18441a0e1b5887369ac2248af5da6c3b213f4719a56e461a9b99318e83d3c80295793390cc36663ff38d437ac87f68f0e40a95b38be5005913324a08bb9b954e', 'Marceau', 'Claude', '0490 93 31 01', '54795765125', 'Docteur'),
	(null, 85112459874, 'f367a4cf71f904cdbe3cd287637b8a1b4742687346022b2f05ffca05ab879346aec965e2e006dce51006eb62c9416c65503fa543dc77a87a46e4ae95ddd743ad', 'Boisclair', 'Guillaume ', '0477 25 93 94', '54795765125', 'Neurochirurgien');
INSERT INTO mutualite VALUES
	(null, 'Mutualité chrétienne', 'CDH', 'MC'),
    (null, 'Mutualité neutre', 'Neutre', 'MN'),
    (null, 'Mutualité socialiste', 'PS', 'Solidaris'),
    (null, 'Mutualité libérale', 'MR', 'ML');
INSERT INTO patient VALUES
	(null, '85062575469', 'Fongemie', 'Baptiste', 3, '1985-06-25', null, '0473 27 91 38', 'Très instable', null, 'Etre délicat', 0, 1, 1, 'Mort dans un accident', null, 25.24, 1),
    (null, '74022648547', 'Martineau', 'Thierry', 1, '1974-02-26', null, '0476 79 85 23', null, 'Foie, rate', 'Maintenir sous perfusion', 1, 0, 1, null, null, 33.52, 2),
    (null, '88073003328', 'Laprise', 'Raina', 2, '1988-07-30', null, '0492 61 83 59', 'Parler de sa famille', null, null, 0, 0, 0, null, null, 20.00, 1),
    (null, '95111158964', 'Chnadonnet', 'Davet', 0, '1995-11-11', null, '0481 65 56 77', null, null, null, 1, 1, 1, null, null, 20.00, 3),
    (null, '47042054397', 'Petrie', 'Brunella', 3, '1947-04-20', null, '0492 17 33 87', null, null, null, 0, 1, 0, 'Mort de vieillesse', 'Mort de vieillesse', 47.10, 4);
INSERT INTO consultation VALUES
	('2018-06-12 18:20:00', null, 1, 1),
    ('2019-12-15 13:30:00', 'Etre à l\'écoute', 2, 1),
    ('2019-06-03 15:15:00', null, 3, 1),
    ('2019-01-12 09:00:00', null, 1, 2),
    ('2019-06-02 08:00:00', null, 2, 2),
    ('2017-03-12 17:50:00', 'Passer les tests', 3, 2),
    ('2019-06-14 16:00:00', null, 1, 2),
    ('2019-02-15 13:45:00', 'Faire une radio', 2, 3),
    ('2019-06-16 12:45:00', null, 3, 3),
    ('2019-02-17 13:30:00', 'Prendre ses précautions', 3, 3),
    ('2019-04-18 18:30:00', null, 2, 4),
    ('2019-06-19 16:35:00', 'Faire attention', 1, 4),
    ('2019-06-20 17:30:00', null, 1, 4),
    ('2019-06-21 10:00:00', null, 2, 4),
    ('2019-02-22 09:55:00', 'Etre attentif', 1, 4),
    ('2019-06-23 11:10:00', null, 3, 5),
    ('2019-04-24 16:00:00', 'Poser beaucoup de questions sur état mental', 1, 5),
    ('2019-06-25 17:00:00', null, 1, 5),
    ('2019-02-26 13:00:00', 'Faire analyse', 1, 5),
    ('2019-06-27 13:40:00', null, 2, 5);
INSERT INTO proche VALUES
	(null, 'Turcotte', 'Eustache', '0495 82 64 08', 'Appeller uniquement entre 16h et 22h', 1, 1, 3),
	(null, 'Jodoin', 'Gaetan', '0472 87 40 50', null, 1, 1, 3),
	(null, 'Labossière', 'Orson', '0492 91 94 68', null, 0, 1, 3),
    (null, 'Peppin ', 'Bernard', '0474 89 18 61', 'En dernier recours uniquement', 0, 1, 3),
    (null, 'Talon', 'Hélène', '0491 87 10 50', null, 1, 1, 2),
	(null, 'Margand', 'France', '0495 46 90 88', null, 1, 0, 2),
    (null, 'Leclair', 'Florence', '0488 75 54 36', null, 1, 1, 4),
    (null, 'Givry', 'Beltane', '0474 37 58 69', 'Appeller uniquement entre 17h et 18h', 0, 1, 2),
    (null, 'Chauveret', 'Olivie', '0493 56 74 37', null, 1, 0, 2);
INSERT INTO allergie VALUES
	(null, 'Allergie aux arachides', 'Démangeaison, éruption et enflure', 'Par probiotiques'),
    (null, 'Allergie aux lactose', 'Eczéma, des vomissements ou diarrhée', 'Pas de remèdes'),
    (null, 'Allergie aux noix', 'Bouffée de chaleur au visage, urticaire ou éruption cutanée, rougeur et démangeaison de la peau', 'Par probiotiques'),
    (null, 'Allergie aux pollen', 'Crises d\'éternuement, écoulement nasal (rhinite), nez bouché, respiration nasale difficile, yeux rougis, irrités et larmoyants', 'Huiles essentielles');
INSERT INTO medicament VALUES
	(null, '1545982', 'Ibuprofène', 'McCabes Pharmacy', 'GFD45G4', 'D5dsF85m', 'Soulage l\'arthrite', 20.0),
    (null, '4856791', 'Doliprane', 'Bayer', 'TRET4R', 'g854F2Ma', 'Soulage les douleurs légères', 40.0),
    (null, '6598343', 'Efferalgan', 'McCabes Pharmacy', 'HGFH84', '4F5ed1ZD', 'Soulage les douleurs légères', 10.0),
    (null, '4586432', 'Dafalgan', 'Bayer', 'GHGF45', '6f4FSF4d', 'Soulage les douleurs légères', 21.0),
    (null, '7982734', 'Imodium', 'Bayer', 'HG4F5H4G', '48Pk4fMh', 'Traite les diarrhées', 10.0);
INSERT INTO traitement VALUES
	('2019-05-15', '2019-05-17', '2 fois par jour', 3, 1, 1),
    ('2019-06-05', '2019-08-05', null, 1, 1, 2),
    ('2018-05-15', '2019-12-27', '1 fois par jour', 5, 2, 3),
    ('2019-01-15', '2019-02-11', '3 fois par jour', 1, 1, 3),
    ('2019-01-16', '2019-01-17', '4 fois par jour', 4, 1, 4),
    ('2020-05-15', '2021-05-17', null, 1, 3, 2),
    ('2021-05-15', '2021-05-17', '5 fois en tout', 2, 1, 5),
    ('2018-11-13', '2019-06-13', '2 fois par mois', 2, 3, 2),
    ('2016-11-10', '2020-11-10', null, 3, 3, 2),
    ('2021-03-10', '2022-07-18', null, 1, 1, 4),
    ('2015-05-19', '2016-05-05', '10 fois en tout', 5, 3, 5),
    ('2018-12-26', '2019-02-06', '1 fois par mois', 2, 3, 1),
    ('2019-07-15', '2019-09-23', null, 4, 3, 1),
    ('2020-05-22', '2020-10-29', '3 fois par mois', 2, 2, 3);