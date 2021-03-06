drop table OUVRIERDUCHANTIER;
drop table ENGINDUCHANTIER;
drop table MATERIAUDUCHANTIER;
drop table PETITMATERIELDUCHANTIER;
drop table CODEREFERENCEDUCHANTIER;
drop table VOITUREDUCHANTIER;
drop table CAMIONDUCHANTIER;
drop table CONDUCTEURDUCHANTIER;
drop table DEVIS;

drop table CHANTIER;

drop table ENGIN;
drop table MATERIAU;
drop table PETITMATERIEL;
drop table CODEREFERENCE;
drop table VOITURE;
drop table CAMION;
drop table CLIENT;
drop table OUVRIER;
drop table PATRON;
drop table CONDUCTEUR;
drop table SEQUENCES;

create table ENGIN (
	idEngin numeric(10) primary key not null,
	nom varchar(20) not null,
	type_ varchar(20) not null,
	reference varchar(20) not null,
	prixHeure double not null
);

create table MATERIAU (
	idMateriau numeric(10) primary key not null,
	nom varchar(20) not null,
	type_ varchar(20) not null,
	reference varchar(20) not null,
	fourniture varchar(20) not null,
	siteProduction varchar(20) not null,
	prixHtva double not null
);

create table PETITMATERIEL (
	idPetitMateriel numeric(10) primary key not null, 
	nom varchar(20) not null,
	type_ varchar(20) not null,
	reference varchar(20) not null,
	prixHtva double not null
);

create table CODEREFERENCE (
	idCodeReference numeric(10) primary key not null, 
	reference varchar(20) not null,
	typeTravail varchar(20) not null,
	prixHtva double not null
);

create table VOITURE (
	idVoiture numeric(10) primary key not null, 
	marque varchar(20) not null,
	modele varchar(20) not null,
	numeroChassis varchar(30) unique not null,
	carburant varchar(20) not null,
	prixHtva double not null
	);

create table CAMION (
	idCamion numeric(10) primary key not null, 
	categorie varchar(20) not null,
	tonnage numeric(10) not null,
	capacite double not null,
	marque varchar(20) not null,
	modele varchar(20) not null,
	numeroChassis varchar(20) unique not null,
	carburant varchar(20) not null,
	prixHtva double not null
	);

create table OUVRIER (
	idOuvrier numeric(10) primary key not null, 
	nom varchar(20) not null,
	prenom varchar(20) not null,
	dateNaissance date not null,
	numeroTelephone varchar(20) unique not null,
	email varchar(50) unique not null,
	remuneration double not null,
	entreeFonction date not null,
	cout double not null
	);

create table CLIENT (
	idClient numeric(10) primary key not null, 
	nom varchar(20) not null,
	prenom varchar(20) not null,
	dateNaissance date not null,
	numeroTelephone varchar(20) unique not null,
	email varchar(50) unique not null
	);
	
create table CONDUCTEUR(
	idConducteur numeric(10) primary key not null,
	password varchar(50) not null,
	nom varchar(20) not null,
	prenom varchar(20) not null,
	dateNaissance date not null,
	numeroTelephone varchar(20) unique not null,
	numeroTelephonePro varchar(20) unique not null,
	email varchar(50) unique not null,
	remuneration double not null,
	entreeFonction date not null,
	cout double not null
	);
	
create table PATRON (
	idPatron numeric(10) primary key not null,
	
    constraint fkPatron foreign key (idPatron) references CONDUCTEUR(idConducteur) ON DELETE CASCADE
	);
	
create table CHANTIER (
	idChantier numeric(10) primary key not null, 
    idClient numeric(10) not null,
    localisation varchar(20) not null,
	designationProjet varchar(20) not null,
	commentaire varchar(500) not null,
	dateCreationProjet date,
	dateDebutPrevue date,
	dateDebutEffective date,
	dateFinPrevue date,
	dateFinEffective date,
	validationProjet boolean not null,
        
    constraint fkClient foreign key (idClient) references CLIENT(idClient) ON DELETE CASCADE
	);
	
	create table DEVIS (
	idDevis numeric(10) primary key not null, 
	designationDevis varchar(20) not null,
	statut varchar(20) not null,
	dateDevis date not null,
	idChantier numeric(10) not null,
	
	constraint fkDevis foreign key (idChantier) references CHANTIER(idChantier) ON DELETE CASCADE
	);

create table CONDUCTEURDUCHANTIER (
	idCONDUCTEURDUCHANTIER numeric(10) primary key not null, 
    idChantier numeric(10) not null,
    idConducteur numeric(10) not null,
	dateDebut date,
    dateFin date,
    nombreHeures double not null,
        
    constraint fkCONDUCTEURDUCHANTIER foreign key (idConducteur) references CONDUCTEUR(idConducteur) ON DELETE CASCADE,
    constraint fkCONDUCTEURDUCHANTIER2 foreign key (idChantier) references CHANTIER(idChantier) ON DELETE CASCADE
	);

create table OUVRIERDUCHANTIER (
	idOUVRIERDUCHANTIER numeric(10) primary key not null, 
    idChantier numeric(10) not null,
    idOuvrier numeric(10) not null,
	dateDebut date,
    dateFin date,
    nombreHeures double not null,

    constraint fkOUVRIERDUCHANTIER foreign key (idChantier) references CHANTIER(idChantier) ON DELETE CASCADE,
    constraint fkOUVRIERDUCHANTIER2 foreign key (idOuvrier) references OUVRIER(idOuvrier) ON DELETE CASCADE,
constraint OuvrierChantier UNIQUE(idChantier, idOuvrier)
	
	);

create table ENGINDUCHANTIER (
	idENGINDUCHANTIER numeric(10) primary key not null, 
    idChantier numeric(10) not null,
    idEngin numeric(10) not null,
	debutDisponibilite date,
    finDisponibilite date,
    nombreHeures double not null,
    quantite double not null,

    constraint fkENGINDUCHANTIER foreign key (idChantier) references CHANTIER(idChantier) ON DELETE CASCADE,
    constraint fkENGINDUCHANTIER2 foreign key (idEngin) references ENGIN(idEngin) ON DELETE CASCADE
	);

create table MATERIAUDUCHANTIER (
	idMATERIAUDUCHANTIER numeric(10) primary key not null, 
    idChantier numeric(10) not null,
    idMateriau numeric(10) not null,
	debutDisponibilite date,
    finDisponibilite date,
    quantite double not null,

    constraint fkMATERIAUDUCHANTIER foreign key (idChantier) references CHANTIER(idChantier) ON DELETE CASCADE,
    constraint fkMATERIAUDUCHANTIER2 foreign key (idMateriau) references MATERIAU(idMateriau) ON DELETE CASCADE
	);

create table PETITMATERIELDUCHANTIER (
	idPETITMATERIELDUCHANTIER numeric(10) primary key not null, 
    idChantier numeric(10) not null,
    idPetitMateriel numeric(10) not null,
	debutDisponibilite date,
    finDisponibilite date,
    quantite double not null,

    constraint fkPETITMATERIELDUCHANTIER foreign key (idChantier) references CHANTIER(idChantier) ON DELETE CASCADE,
    constraint fkPETITMATERIELDUCHANTIER2 foreign key (idPetitMateriel) references PETITMATERIEL(idPetitMateriel) ON DELETE CASCADE
	);

create table CODEREFERENCEDUCHANTIER (
	idCODEREFERENCEDUCHANTIER numeric(10) primary key not null, 
    idChantier numeric(10) not null,
    idCodeReference numeric(10) not null,
    quantite double not null,

    constraint fkCODEDEREFERENCEDUCHANTIER foreign key (idChantier) references CHANTIER(idChantier) ON DELETE CASCADE,
    constraint fkCODEDEREFERENCEDUCHANTIER2 foreign key (idCodeReference) references CODEREFERENCE(idCodeReference) ON DELETE CASCADE
    );

create table VOITUREDUCHANTIER (
	idVOITUREDUCHANTIER numeric(10) primary key not null, 
    idChantier numeric(10) not null,
    idVoiture numeric(10) not null,
    debutDisponibilite date,
    finDisponibilite date,
	nombreJours numeric(10) not null,

    constraint fkVOITUREDUCHANTIER foreign key (idChantier) references CHANTIER(idChantier) ON DELETE CASCADE,
    constraint fkVOITUREDUCHANTIER2 foreign key (idVoiture) references VOITURE(idVoiture) ON DELETE CASCADE
    );

create table CAMIONDUCHANTIER (
	idCAMIONDUCHANTIER numeric(10) primary key not null, 
    idChantier numeric(10) not null,
    idCamion numeric(10) not null,
    nombreHeures double not null,
    debutDisponibilite date,
    finDisponibilite date,

    constraint fkCAMIONDUCHANTIER foreign key (idChantier) references CHANTIER(idChantier) ON DELETE CASCADE,
    constraint fkCAMIONDUCHANTIER2 foreign key (idCamion) references CAMION(idCamion) ON DELETE CASCADE
	);

create table SEQUENCES (
	id varchar(50) primary key not null,
	sValue numeric(10) not null
	);

Insert into SEQUENCES Values ('CAMION',2);
Insert into SEQUENCES Values ('CAMIONDUCHANTIER',2);
Insert into SEQUENCES Values ('CHANTIER',2);
Insert into SEQUENCES Values ('CLIENT',2);
Insert into SEQUENCES Values ('CODEREFERENCE',2);
Insert into SEQUENCES Values ('CODEREFERENCEDUCHANTIER',2);
Insert into SEQUENCES Values ('CONDUCTEUR',3);
Insert into SEQUENCES Values ('CONDUCTEURDUCHANTIER',2);
Insert into SEQUENCES Values ('DEVIS',2);
Insert into SEQUENCES Values ('ENGIN',2);
Insert into SEQUENCES Values ('ENGINDUCHANTIER',2);
Insert into SEQUENCES Values ('MATERIAU',2);
Insert into SEQUENCES Values ('MATERIAUDUCHANTIER',2);
Insert into SEQUENCES Values ('OUVRIER',2);
Insert into SEQUENCES Values ('OUVRIERDUCHANTIER',2);
Insert into SEQUENCES Values ('PATRON',2);
Insert into SEQUENCES Values ('PETITMATERIEL',2);
Insert into SEQUENCES Values ('PETITMATERIELDUCHANTIER',2);
Insert into SEQUENCES Values ('VOITURE',2);
Insert into SEQUENCES Values ('VOITUREDUCHANTIER',2);

Insert into CAMION Values (1,'C', 2, 1500, 'Mercedes', 'Worker', 'ZE25695d2d5', 'Diesel', 99);
Insert into CLIENT Values (1, 'Benoit', 'Marteans', '1960-02-04', '0488365222', 'benoit@hotmail.com');
Insert into CODEREFERENCE Values (1, 'ER25698', 'Canalisation', 15);
Insert into CONDUCTEUR Values (1, 'af3e4dd9d4eadafc95d2f5e1fe75bef0661e56b4', 'Laurent', 'Cordenier', '1990-09-11', '0485658999', '0499321587', 'laurent@melin.com', 3300, '2013-02-01', -1);
Insert into ENGIN Values (1, 'Grue', 'Gravier', 'REZ89851', 20);
Insert into MATERIAU Values (1, 'Sable', 'Terrassement', '698.325.21', 'Externe', 'Hulpe', 10);
Insert into OUVRIER Values (1, 'Jack', 'Bauer', '1988-05-03', '0477235987', 'jackbauer@melin.be', 1745, '2001-11-05', -1);
Insert into PATRON Values (1);
Insert into PETITMATERIEL Values (1, 'Marteau', 'Nivellement', '2REZEDD', 5);
Insert into VOITURE Values (1, 'Ford', 'Transporter', '36d5d5d48sd', 'Diesel', 19);

Insert into CHANTIER Values (1, 1, 'Bruxelles', 'parc de la woluwe', 'Cest un bon projet', '2017-02-01','2017-03-03', null,'2017-03-27', null, false);
Insert into DEVIS Values (1, 'Parc de Woluwe', 'En validation', '2017-07-15', 1);

Insert into CAMIONDUCHANTIER Values (1, 1, 1, 5, '2017-01-01', '2017-01-11');
Insert into OUVRIERDUCHANTIER Values (1, 1, 1, '2017-09-01', '2017-09-10', 36);
Insert into CODEREFERENCEDUCHANTIER Values (1, 1, 1, 2);
Insert into CONDUCTEURDUCHANTIER Values (1, 1, 1, '2017-08-17', '2017-08-25', 48);
Insert into ENGINDUCHANTIER Values (1, 1, 1, '2017-09-01', '2017-09-10', 5, 1);
Insert into MATERIAUDUCHANTIER Values (1, 1, 1, '2017-09-01', '2017-09-10', 325);
Insert into PETITMATERIELDUCHANTIER Values (1, 1, 1, '2017-09-01', '2017-09-10', 3);
Insert into VOITUREDUCHANTIER Values (1, 1, 1, '2017-01-01', '2017-01-11', 14);


