package com.sardes.thegabworkproject.data.provider

import com.sardes.thegabworkproject.data.models.Job

object JobDataProvider {


    val jobs = mutableListOf(

//        # AERONAUTIQUE
        Job(name = "Agent(e) de trafic", activityArea = "Aéronautique", ),
        Job(name = "Chef(fe) d’avion", activityArea = "Aéronautique", ),
        Job(name = "Contrôleur(euse) aérien(ne)", activityArea = "Aéronautique", ),
        Job(name = "Hôtesse de l’air/steward", activityArea = "Aéronautique", ),
        Job(name = "Ingénieur(e) aéronautique", activityArea = "Aéronautique", ),
        Job(name = "Mécanicien(ne) moteur", activityArea = "Aéronautique", ),
        Job(name = "Pilote de ligne", activityArea = "Aéronautique", ),
        Job(name = "TSA (technicien(ne) supérieur(e) de l’aviation)", activityArea = "Aéronautique", ),


//        # AGROALIMENTAIRE
        Job(name = "Acheteur(euse) dans l’agroalimentaire", activityArea = "Agroalimentaire", ),
        Job(name = "Animateur(trice) logistique", activityArea = "Agroalimentaire", ),
        Job(name = "Assistante paysagiste : le témoignage de Nathalie, apprentie", activityArea = "Agroalimentaire", ),
        Job(name = "Attaché(e) commercial(e) / merchandising", activityArea = "Agroalimentaire", ),
        Job(name = "Auxiliaire vétérinaire", activityArea = "Agroalimentaire", ),
        Job(name = "Conducteur(trice) de ligne de fabrication ou de conditionnement", activityArea = "Agroalimentaire", ),
        Job(name = "Conducteur(trice) de machine de fabrication ou de conditionnement", activityArea = "Agroalimentaire", ),
        Job(name = "Conducteur(trice) de machines agricoles", activityArea = "Agroalimentaire", ),
        Job(name = "Conseiller(ère) agricole", activityArea = "Agroalimentaire", ),
        Job(name = "Contrôleur(euse) laitier(ière)", activityArea = "Agroalimentaire", ),
        Job(name = "Éleveur(euse)", activityArea = "Agroalimentaire", ),
        Job(name = "Exploitant(e) agricole", activityArea = "Agroalimentaire", ),
        Job(name = "Horticulteur(trice)", activityArea = "Agroalimentaire", ),
        Job(name = "Ingénieur(e) agronome", activityArea = "Agroalimentaire", ),
        Job(name = "Ingénieur(e) en agroalimentaire", activityArea = "Agroalimentaire", ),
        Job(name = "Œnologue", activityArea = "Agroalimentaire", ),
        Job(name = "Opérateur(trice) de fabrication", activityArea = "Agroalimentaire", ),
        Job(name = "Pépiniériste : le témoignage de Virginie, apprentie", activityArea = "Agroalimentaire", ),
        Job(name = "Responsable d’atelier de fabrication ou de conditionnement", activityArea = "Agroalimentaire", ),
        Job(name = "Responsable de la supply chain", activityArea = "Agroalimentaire", ),
        Job(name = "Responsable de maintenance", activityArea = "Agroalimentaire", ),
        Job(name = "Responsable de production", activityArea = "Agroalimentaire", ),
        Job(name = "Responsable qualité", activityArea = "Agroalimentaire", ),
        Job(name = "Technicien(ne) agricole", activityArea = "Agroalimentaire", ),
        Job(name = "Technicien(ne) de maintenance", activityArea = "Agroalimentaire", ),
        Job(name = "Technicien(ne) en aquaculture", activityArea = "Agroalimentaire", ),
        Job(name = "Technicien(ne) qualité", activityArea = "Agroalimentaire", ),
        Job(name = "Vétérinaire", activityArea = "Agroalimentaire", ),
        Job(name = "Viticulteur(trice)", activityArea = "Agroalimentaire", ),


//        # ARTISANAT
        Job(name = "Boucher", activityArea = "Artisanat", ),
        Job(name = "Boulanger(ère)-pâtissier(ère)", activityArea = "Artisanat", ),
        Job(name = "Charpentier(ière)", activityArea = "Artisanat", ),
        Job(name = "Coiffeur(euse)", activityArea = "Artisanat", ),
        Job(name = "Fleuriste", activityArea = "Artisanat", ),
        Job(name = "Plombier(ière), installateur(trice) thermique", activityArea = "Artisanat", ),



//        # Audiovisuel, cinéma
        Job(name = "Animateur 3D", activityArea = "Audiovisuel, cinéma", ),
        Job(name = "Assistant(e) de production", activityArea = "Audiovisuel, cinéma", ),
        Job(name = "Assistant(e) réalisateur(trice)", activityArea = "Audiovisuel, cinéma", ),
        Job(name = "Cadreur(euse)", activityArea = "Audiovisuel, cinéma", ),
        Job(name = "Chargé(e) de diffusion", activityArea = "Audiovisuel, cinéma", ),
        Job(name = "Chef(fe) opérateur(trice)", activityArea = "Audiovisuel, cinéma", ),
        Job(name = "Ingénieur(e) de la vision", activityArea = "Audiovisuel, cinéma", ),
        Job(name = "Ingénieur(e) du son", activityArea = "Audiovisuel, cinéma", ),
        Job(name = "Monteur(euse)", activityArea = "Audiovisuel, cinéma", ),
        Job(name = "Monteur(euse)-Truquiste", activityArea = "Audiovisuel, cinéma", ),
        Job(name = "Producteur(trice)", activityArea = "Audiovisuel, cinéma", ),
        Job(name = "Réalisateur(trice)", activityArea = "Audiovisuel, cinéma", ),
        Job(name = "Régisseur(euse) général(e)", activityArea = "Audiovisuel, cinéma", ),
        Job(name = "Scénariste", activityArea = "Audiovisuel, cinéma", ),
        Job(name = "Scripte", activityArea = "Audiovisuel, cinéma", ),


//        # Audit, comptabilité, gestion
        Job(name = "Assistant(e) comptable", activityArea = "Audit, comptabilité, gestion", ),
        Job(name = "Auditeur(trice) à la Cour des comptes", activityArea = "Audit, comptabilité, gestion", ),
        Job(name = "Auditeur(trice) financier(ière)", activityArea = "Audit, comptabilité, gestion", ),
        Job(name = "Auditeur(trice) interne", activityArea = "Audit, comptabilité, gestion", ),
        Job(name = "Auditeur(trice) spécialisé(e)", activityArea = "Audit, comptabilité, gestion", ),
        Job(name = "Commissaire aux comptes", activityArea = "Audit, comptabilité, gestion", ),
        Job(name = "Comptable", activityArea = "Audit, comptabilité, gestion", ),
        Job(name = "Contrôleur(euse) de gestion", activityArea = "Audit, comptabilité, gestion", ),
        Job(name = "Directeur(trice) comptable", activityArea = "Audit, comptabilité, gestion", ),
        Job(name = "Directeur(trice) financier(ière)", activityArea = "Audit, comptabilité, gestion", ),
        Job(name = "Expert(e)-comptable", activityArea = "Audit, comptabilité, gestion", ),
        Job(name = "Fiscaliste", activityArea = "Audit, comptabilité, gestion", ),
        Job(name = "Responsable consolidation", activityArea = "Audit, comptabilité, gestion", ),
        Job(name = "Risk manager", activityArea = "Audit, comptabilité, gestion", ),
        Job(name = "Trésorier(ière)", activityArea = "Audit, comptabilité, gestion", ),


//        # Automobile
        Job(name = "Expert(e) automobile", activityArea = "Automobile", ),
        Job(name = "Ingénieur(e) d’études", activityArea = "Automobile", ),
        Job(name = "Mécanicien poids lourds : le témoignage d’André, apprenti", activityArea = "Automobile", ),
        Job(name = "Mécanicien(ne) réparateur(trice)", activityArea = "Automobile", ),
        Job(name = "Peintre en carrosserie : le témoignage de Damien, apprenti", activityArea = "Automobile", ),
        Job(name = "Vendeur d’équipement auto : le témoignage de William, apprenti", activityArea = "Automobile", ),
        Job(name = "Vendeur(euse) automobile (A)", activityArea = "Automobile", ),





//        # Banque, assurance
        Job(name = "Actuaire", activityArea = "Banque, assurance", ),
        Job(name = "Agent(e) général(e) d'assurance", activityArea = "Banque, assurance", ),
        Job(name = "Analyste financier(ière)", activityArea = "Banque, assurance", ),
        Job(name = "Banquier(ière) du commerce international", activityArea = "Banque, assurance", ),
        Job(name = "Chargé(e) de clientèle", activityArea = "Banque, assurance", ),
        Job(name = "Conseiller bancaire : le témoignage de Steeve, apprenti", activityArea = "Banque, assurance", ),
        Job(name = "Conseiller(ère) commercial(e) en assurances", activityArea = "Banque, assurance", ),
        Job(name = "Courtier(ère) d’assurances", activityArea = "Banque, assurance", ),
        Job(name = "Credit manager", activityArea = "Banque, assurance", ),
        Job(name = "Directeur(trice) d’agence bancaire", activityArea = "Banque, assurance", ),
        Job(name = "Expert(e) d’assurances", activityArea = "Banque, assurance", ),
        Job(name = "Gestionnaire assurance : le témoignage de Cynthia, apprentie", activityArea = "Banque, assurance", ),
        Job(name = "Gestionnaire de contrats d’assurance", activityArea = "Banque, assurance", ),
        Job(name = "Gestionnaire de patrimoine", activityArea = "Banque, assurance", ),
        Job(name = "Rédacteur(trice) dans les assurances", activityArea = "Banque, assurance", ),
        Job(name = "Responsable du back-office", activityArea = "Banque, assurance", ),
        Job(name = "Souscripteur(trice)", activityArea = "Banque, assurance", ),
        Job(name = "Trader (opérateur/opératrice de marché)", activityArea = "Banque, assurance", ),



//        # Bâtiment, travaux publics
        Job(name = "Architecte", activityArea = "Bâtiment, travaux publics ", ),
        Job(name = "BIM manager", activityArea = "Bâtiment, travaux publics ", ),
        Job(name = "Chargé(e) d’affaires dans le BTP", activityArea = "Bâtiment, travaux publics ", ),
        Job(name = "Chef de chantier", activityArea = "Bâtiment, travaux publics ", ),
        Job(name = "Conducteur(trice) d’engins de chantier", activityArea = "Bâtiment, travaux publics ", ),
        Job(name = "Conducteur(trice) de travaux", activityArea = "Bâtiment, travaux publics ", ),
        Job(name = "Dessinateur(trice)-projeteur(euse) en bâtiment", activityArea = "Bâtiment, travaux publics ", ),
        Job(name = "Électricien(ne) du BTP", activityArea = "Bâtiment, travaux publics ", ),
        Job(name = "Ingénieur(e) études de prix", activityArea = "Bâtiment, travaux publics ", ),
        Job(name = "Ingénieur(e) géomètre", activityArea = "Bâtiment, travaux publics ", ),
        Job(name = "Maçon", activityArea = "Bâtiment, travaux publics ", ),
        Job(name = "Ouvrier routier", activityArea = "Bâtiment, travaux publics ", ),
        Job(name = "Ouvrier(ière) qualifié(e)", activityArea = "Bâtiment, travaux publics ", ),
        Job(name = "Peintre décorateur", activityArea = "Bâtiment, travaux publics ", ),


//        # Biologie, chimie, pharmacie
        Job(name = "Bio-informaticien(ne)", activityArea = "Biologie, chimie, pharmacie", ),
        Job(name = "Hydrobiologiste", activityArea = "Biologie, chimie, pharmacie", ),
        Job(name = "Ingénieur(e) chimiste", activityArea = "Biologie, chimie, pharmacie", ),
        Job(name = "Pharmacien(ne)", activityArea = "Biologie, chimie, pharmacie", ),
        Job(name = "Préparateur(trice) en pharmacie", activityArea = "Biologie, chimie, pharmacie", ),
        Job(name = "Responsable des affaires réglementaires", activityArea = "Biologie, chimie, pharmacie", ),
        Job(name = "Technicien(ne) d’analyses biomédicales", activityArea = "Biologie, chimie, pharmacie", ),
        Job(name = "Technicien(ne) de l’industrie pharmaceutique", activityArea = "Biologie, chimie, pharmacie", ),


//        # Commerce, distribution
        Job(name = "Acheteur(euse)", activityArea = "Commerce, distribution", ),
        Job(name = "Agent(e) commercial(e)", activityArea = "Commerce, distribution", ),
        Job(name = "Assistant(e) export", activityArea = "Commerce, distribution", ),
        Job(name = "Chargé(e) d’affaires", activityArea = "Commerce, distribution", ),
        Job(name = "Chef(fe) de rayon", activityArea = "Commerce, distribution", ),
        Job(name = "Commercial(e)", activityArea = "Commerce, distribution", ),
        Job(name = "Directeur(trice) de centre de profit", activityArea = "Commerce, distribution", ),
        Job(name = "Responsable de magasin", activityArea = "Commerce, distribution", ),
        Job(name = "Vendeuse esthéticienne : le témoignage de Sophie, apprentie", activityArea = "Commerce, distribution", ),



//        # Communication
        Job(name = "Assistant(e) en communication", activityArea = "Communication", ),
        Job(name = "Attaché(e) de presse", activityArea = "Communication", ),
        Job(name = "Chargé(e) de communication", activityArea = "Communication", ),
        Job(name = "Directeur(trice) de la communication", activityArea = "Communication", ),
        Job(name = "Journaliste d’entreprise", activityArea = "Communication", ),



//        # Création, métiers d'art
        Job(name = "Architecte d’intérieur", activityArea = "Création, métiers d'art", ),
        Job(name = "Artiste plasticien(ne)", activityArea = "Création, métiers d'art", ),
        Job(name = "Céramiste", activityArea = "Création, métiers d'art", ),
        Job(name = "Concepteur(trice) de jeux vidéo", activityArea = "Création, métiers d'art", ),
        Job(name = "Décorateur(trice)", activityArea = "Création, métiers d'art", ),
        Job(name = "Designer industriel(le)", activityArea = "Création, métiers d'art", ),
        Job(name = "Dessinateur(trice) de BD", activityArea = "Création, métiers d'art", ),
        Job(name = "Ébéniste", activityArea = "Création, métiers d'art", ),
        Job(name = "Facteur(trice) d’instruments", activityArea = "Création, métiers d'art", ),
        Job(name = "Graphiste", activityArea = "Création, métiers d'art", ),
        Job(name = "Illustrateur(trice)", activityArea = "Création, métiers d'art", ),
        Job(name = "Infographiste 2D-3D", activityArea = "Création, métiers d'art", ),
        Job(name = "Modiste", activityArea = "Création, métiers d'art", ),
        Job(name = "Photographe", activityArea = "Création, métiers d'art", ),
        Job(name = "Professeur(e) d’arts plastiques", activityArea = "Création, métiers d'art", ),
        Job(name = "Réalisatrice multimédia : le témoignage de Caroline, apprentie", activityArea = "Création, métiers d'art", ),
        Job(name = "Relieur(euse)-doreur(euse)", activityArea = "Création, métiers d'art", ),
        Job(name = "Verrier(ière)", activityArea = "Création, métiers d'art", ),




//        # Culture, patrimoine
        Job(name = "Animateur(trice) de l’architecture et du patrimoine", activityArea = "Culture, patrimoine",),
        Job(name = "Archéologue", activityArea = "Culture, patrimoine",),
        Job(name = "Commissaire-priseur(se)", activityArea = "Culture, patrimoine",),
        Job(name = "Conservateur(trice) du patrimoine", activityArea = "Culture, patrimoine",),
        Job(name = "Galeriste", activityArea = "Culture, patrimoine",),
        Job(name = "Guide-conférencier(ière) de musée et monument", activityArea = "Culture, patrimoine",),
        Job(name = "Médiateur(trice) culturel(le)", activityArea = "Culture, patrimoine",),
        Job(name = "Régisseur(euse) d’œuvres d’art", activityArea = "Culture, patrimoine",),
        Job(name = "Restaurateur(trice) d’œuvres d’art", activityArea = "Culture, patrimoine",),



//        # Documentation, bibliothèque
        Job(name = "Archiviste", activityArea = "Documentation, bibliothèque", ),
        Job(name = "Bibliothécaire", activityArea = "Documentation, bibliothèque", ),
        Job(name = "Conservateur(trice) de bibliothèque", activityArea = "Documentation, bibliothèque", ),
        Job(name = "Documentaliste", activityArea = "Documentation, bibliothèque", ),




//        # Droit
        Job(name = "Assistant(e) parlementaire", activityArea = "Droit", ),
        Job(name = "Avocat(e)", activityArea = "Droit", ),
        Job(name = "Clerc de notaire", activityArea = "Droit", ),
        Job(name = "Greffier(ère)", activityArea = "Droit", ),
        Job(name = "Huissier(ère) de justice", activityArea = "Droit", ),
        Job(name = "Juge aux affaires familiales", activityArea = "Droit", ),
        Job(name = "Juge d’instruction", activityArea = "Droit", ),
        Job(name = "Juge de l’application des peines", activityArea = "Droit", ),
        Job(name = "Juge des enfants", activityArea = "Droit", ),
        Job(name = "Juriste d’entreprise", activityArea = "Droit", ),
        Job(name = "Notaire", activityArea = "Droit", ),
        Job(name = "Substitut(e) du procureur", activityArea = "Droit", ),



//        # Edition, livre
        Job(name = "Auteur(e)", activityArea = "Edition, livre", ),
        Job(name = "Chef(fe) de fabrication dans l'édition", activityArea = "Edition, livre", ),
        Job(name = "Correcteur(trice)", activityArea = "Edition, livre", ),
        Job(name = "Iconographe", activityArea = "Edition, livre", ),
        Job(name = "Libraire", activityArea = "Edition, livre", ),
        Job(name = "Responsable d’édition", activityArea = "Edition, livre", ),


//        # Enseignement
        Job(name = "Conseiller(ère) principal(e) d’éducation", activityArea = "Enseignement", ),
        Job(name = "Enseignant(e) à l’étranger", activityArea = "Enseignement", ),
        Job(name = "Enseignant(e)-chercheur(euse)", activityArea = "Enseignement", ),
        Job(name = "Formateur(trice) d’adultes", activityArea = "Enseignement", ),
        Job(name = "Professeur(e) de collège et de lycée", activityArea = "Enseignement", ),
        Job(name = "Professeur(e) de lycée professionnel ou technique", activityArea = "Enseignement", ),
        Job(name = "Professeur(e) de musique", activityArea = "Enseignement", ),
        Job(name = "Professeur(e) des écoles", activityArea = "Enseignement", ),
        Job(name = "Professeur(e) du privé", activityArea = "Enseignement", ),


//        # Environnement
        Job(name = "Acousticien(ne)", activityArea = "Environnement", ),
        Job(name = "Agent(e) de qualité de l’eau", activityArea = "Environnement", ),
        Job(name = "Agent(e) des réseaux d’eau potable (fontainier-ère)", activityArea = "Environnement", ),
        Job(name = "Agent(e) technique de l'environnement (garde-pêche)", activityArea = "Environnement", ),
        Job(name = "Agent(e) technique sites et sols pollués", activityArea = "Environnement", ),
        Job(name = "Agriculteur/trice biologique", activityArea = "Environnement", ),
        Job(name = "Analyste extrafinancier", activityArea = "Environnement", ),
        Job(name = "Animateur(trice) en écotourisme", activityArea = "Environnement", ),
        Job(name = "Biologiste en environnement", activityArea = "Environnement", ),
        Job(name = "Botaniste", activityArea = "Environnement", ),
        Job(name = "Chargé(e) de mission Agenda 21", activityArea = "Environnement", ),
        Job(name = "Climatologue", activityArea = "Environnement", ),
        Job(name = "Conseiller(ère) en environnement", activityArea = "Environnement", ),
        Job(name = "Conseiller(ère) en environnement domestique", activityArea = "Environnement", ),
        Job(name = "Écodéveloppeur(se)", activityArea = "Environnement", ),
        Job(name = "Écogarde", activityArea = "Environnement", ),
        Job(name = "Entomologiste", activityArea = "Environnement", ),
        Job(name = "Étanchéiste", activityArea = "Environnement", ),
        Job(name = "Garde-moniteur(trice)", activityArea = "Environnement", ),
        Job(name = "Géologue", activityArea = "Environnement", ),
        Job(name = "Hydrogéologue", activityArea = "Environnement", ),
        Job(name = "Ingénieur(e) écoconception", activityArea = "Environnement", ),
        Job(name = "Ingénieur(e) écologue", activityArea = "Environnement", ),
        Job(name = "Ingénieur(e) en analyse de l’air", activityArea = "Environnement", ),
        Job(name = "Ingénieur(e) hydrologue", activityArea = "Environnement", ),
        Job(name = "Inspecteur(trice) de la santé publique vétérinaire", activityArea = "Environnement", ),
        Job(name = "Juriste spécialisé en droit de l’environnement", activityArea = "Environnement", ),
        Job(name = "Juriste spécialisé(e) en droit de l'environnement", activityArea = "Environnement", ),
        Job(name = "Paysagiste", activityArea = "Environnement", ),
        Job(name = "Psychologue environnementaliste", activityArea = "Environnement", ),
        Job(name = "Responsable commercial(e) environnement", activityArea = "Environnement", ),
        Job(name = "Responsable de l’animation scientifique", activityArea = "Environnement", ),
        Job(name = "Responsable de station d’épuration", activityArea = "Environnement", ),
        Job(name = "Responsable environnement", activityArea = "Environnement", ),
        Job(name = "Rudologue", activityArea = "Environnement", ),
        Job(name = "Technicien(ne) de laboratoire (environnement)", activityArea = "Environnement", ),
        Job(name = "Technicien(ne) de mesure de la pollution", activityArea = "Environnement", ),
        Job(name = "Technicien(ne) forestier(ière)", activityArea = "Environnement", ),
        Job(name = "Technicien(ne) hygiène, sécurité, environnement", activityArea = "Environnement", ),



//        # Ferroviaire
        Job(name = "Agent(e) d’escale ferroviaire (agence cadre)", activityArea = "Ferroviaire", ),
        Job(name = "Agent(e) de la sûreté ferroviaire (agence transverse)", activityArea = "Ferroviaire", ),
        Job(name = "Commercial(e) en gare (agence voyageurs)", activityArea = "Ferroviaire", ),
        Job(name = "Conducteur(trice) de train", activityArea = "Ferroviaire", ),
        Job(name = "Ingénieur(e) d’études génie électrique (agence cadre)", activityArea = "Ferroviaire", ),
        Job(name = "Manageur(euse) maintenance et travaux en génie civil", activityArea = "Ferroviaire", ),
        Job(name = "Opérateur(trice) de maintenance des trains (agence matériel)", activityArea = "Ferroviaire", ),
        Job(name = "Opérateur(trice) de signalisation électrique", activityArea = "Ferroviaire", ),
        Job(name = "Technicien(ne) de la voie ferrée", activityArea = "Ferroviaire", ),
        Job(name = "Technicien(ne) de maintenance des trains (agence matériel)", activityArea = "Ferroviaire", ),



//        # Foires, salons et congrès
        Job(name = "Chargé(e) de sécurité sur des foires, salons ou congrès", activityArea = "Foires, salons et congrès", ),
        Job(name = "Chef(fe) de hall(s) sur des salons, foires ou congrès", activityArea = "Foires, salons et congrès", ),
        Job(name = "Chef(fe) de projet marketing TIC", activityArea = "Foires, salons et congrès", ),
        Job(name = "Concepteur(trice) de stand sur des foires, salons, congrès", activityArea = "Foires, salons et congrès", ),
        Job(name = "Conseiller(ère) d’exposant(s) sur des salons, foires ou congrès", activityArea = "Foires, salons et congrès", ),
        Job(name = "Décorateur(trice) floral(e) sur des foires, salons, congrès", activityArea = "Foires, salons et congrès", ),
        Job(name = "Directeur(trice) de congrès-expositions", activityArea = "Foires, salons et congrès", ),
        Job(name = "Directeur(trice) de foire(s)", activityArea = "Foires, salons et congrès", ),
        Job(name = "Directeur(trice) de manifestation sur des salons, foires ou congrès", activityArea = "Foires, salons et congrès", ),
        Job(name = "Directeur(trice) de salons", activityArea = "Foires, salons et congrès", ),
        Job(name = "Directeur(trice) juridique pour des foires, salons ou congrès", activityArea = "Foires, salons et congrès", ),
        Job(name = "Directeur(trice) technique sur des foires, salons ou congrès", activityArea = "Foires, salons et congrès", ),
        Job(name = "Installateur(trice) général(e) sur des foires, salons, congrès", activityArea = "Foires, salons et congrès", ),
        Job(name = "Régisseur(euse) sur des salons, foires ou congrès", activityArea = "Foires, salons et congrès", ),
        Job(name = "Responsable commercial(e) et marketing pour des foires, salons ou congrès", activityArea = "Foires, salons et congrès", ),
        Job(name = "Responsable développement salon", activityArea = "Foires, salons et congrès", ),
        Job(name = "Responsable marketing pour des salons, foires ou congrès", activityArea = "Foires, salons et congrès", ),
        Job(name = "Responsable sécurité sur des foires, salons, congrès", activityArea = "Foires, salons et congrès", ),
        Job(name = "Scénographe pour des foires, salons ou congrès", activityArea = "Foires, salons et congrès", ),
        Job(name = "Tapissier(ière) sur des foires, salons, congrès", activityArea = "Foires, salons et congrès", ),
        Job(name = "Technicien(ne) sur des salons, foires ou congrès", activityArea = "Foires, salons et congrès", ),


//        # Hôtellerie, restauration
        Job(name = "Chef(fe) de cuisine", activityArea = "Hôtellerie, restauration", ),
        Job(name = "Concierge d'hôtel", activityArea = "Hôtellerie, restauration", ),
        Job(name = "Cuisinier : le témoignage de Sébastien, apprenti", activityArea = "Hôtellerie, restauration", ),
        Job(name = "Directeur(trice) d’hôtel", activityArea = "Hôtellerie, restauration", ),
        Job(name = "Gérant(e) de restauration collective", activityArea = "Hôtellerie, restauration", ),
        Job(name = "Maître(sse) d’hôtel", activityArea = "Hôtellerie, restauration", ),
        Job(name = "Serveur : le témoignage de Jérémie, apprenti", activityArea = "Hôtellerie, restauration", ),
        Job(name = "Sommelier(ière)", activityArea = "Hôtellerie, restauration", ),
        Job(name = "Yield manager", activityArea = "Hôtellerie, restauration", ),


//        # Immobilier
        Job(name = "Administrateur(trice) de biens", activityArea = "Immobilier", ),
        Job(name = "Agent(e) immobilier(ière)", activityArea = "Immobilier", ),
        Job(name = "Chasseur(euse) de biens immobiliers", activityArea = "Immobilier", ),
        Job(name = "Gestionnaire d’actifs immobiliers", activityArea = "Immobilier", ),
        Job(name = "Gestionnaire de galerie marchande", activityArea = "Immobilier", ),
        Job(name = "Gestionnaire social(e)", activityArea = "Immobilier", ),
        Job(name = "Juriste immobilier", activityArea = "Immobilier", ),
        Job(name = "Négociateur(trice) immobilier", activityArea = "Immobilier", ),
        Job(name = "Syndic de copropriété", activityArea = "Immobilier", ),



//        # Industrie
        Job(name = "Acheteur(euse) industriel(le)", activityArea = "Industrie", ),
        Job(name = "Automaticien(ne)", activityArea = "Industrie", ),
        Job(name = "Conducteur(trice) de ligne de production", activityArea = "Industrie", ),
        Job(name = "Conducteur(trice) production automatisée", activityArea = "Industrie", ),
        Job(name = "Designer d'environnement", activityArea = "Industrie", ),
        Job(name = "Dessinateur(rice) industriel(le)", activityArea = "Industrie", ),
        Job(name = "Étalagiste", activityArea = "Industrie", ),
        Job(name = "Ingénieur(e) calcul", activityArea = "Industrie", ),
        Job(name = "Ingénieur(e) commercial(e)", activityArea = "Industrie", ),
        Job(name = "Ingénieur(e) de production", activityArea = "Industrie", ),
        Job(name = "Ingénieur(e) éco-conception", activityArea = "Industrie", ),
        Job(name = "Ingénieur(e) en cobotique", activityArea = "Industrie", ),
        Job(name = "Ingénieur(e) en fabrication additive", activityArea = "Industrie", ),
        Job(name = "Ingénieur(e) méthodes", activityArea = "Industrie", ),
        Job(name = "Ingénieur(e) R&D", activityArea = "Industrie", ),
        Job(name = "Manager de maintenance multiservices", activityArea = "Industrie", ),
        Job(name = "Mécanicien(ne) outilleur(euse)", activityArea = "Industrie", ),
        Job(name = "Opérateur d’ouvrages chaudronnés : le témoignage de Damien, apprenti", activityArea = "Industrie", ),
        Job(name = "Opératrice sur machine : le témoignage de Vanessa, apprentie", activityArea = "Industrie", ),
        Job(name = "Pilote de drone", activityArea = "Industrie", ),
        Job(name = "Responsable d’ordonnancement", activityArea = "Industrie", ),
        Job(name = "Responsable QSE (qualité, sécurité, environnement)", activityArea = "Industrie", ),
        Job(name = "Tailleur(euse) de pierre", activityArea = "Industrie", ),
        Job(name = "Technicien qualité : le témoignage de Frédéric, apprenti", activityArea = "Industrie", ),
        Job(name = "Technicien(ne) contrôle", activityArea = "Industrie", ),
        Job(name = "Technicien(ne) contrôle non destructif", activityArea = "Industrie", ),
        Job(name = "Technicien(ne) d’essais", activityArea = "Industrie", ),
        Job(name = "Technicien(ne) d’études", activityArea = "Industrie", ),
        Job(name = "Technicien(ne) de maintenance", activityArea = "Industrie", ),
        Job(name = "Technicien(ne) de maintenance multiservices", activityArea = "Industrie", ),
        Job(name = "Technicien(ne) électronicien(ne)", activityArea = "Industrie", ),
        Job(name = "Technicien(ne) inspection conformité", activityArea = "Industrie", ),
        Job(name = "Technicien(ne) jumeau numérique", activityArea = "Industrie", ),
        Job(name = "Technicien(ne) modeleur(euse)", activityArea = "Industrie", ),
        Job(name = "Technicien(ne) photovoltaïque", activityArea = "Industrie", ),
        Job(name = "Vendeur en électronique : le témoignage de Nicolas, apprenti", activityArea = "Industrie", ),



//        # Informatique, télécoms, Web
        Job(name = "Administrateur(trice) de base de données", activityArea = "Informatique, télécoms, Web", ),
        Job(name = "Architecte de système d’information", activityArea = "Informatique, télécoms, Web", ),
        Job(name = "Business analyst", activityArea = "Informatique, télécoms, Web", ),
        Job(name = "Chef(fe) de projet informatique", activityArea = "Informatique, télécoms, Web", ),
        Job(name = "Community manager", activityArea = "Informatique, télécoms, Web", ),
        Job(name = "Concepteur(trice) Web", activityArea = "Informatique, télécoms, Web", ),
        Job(name = "Consultant(e) en référencement de sites Web (consultant MO/SEO)", activityArea = "Informatique, télécoms, Web", ),
        Job(name = "Data scientist", activityArea = "Informatique, télécoms, Web", ),
        Job(name = "Développeur(euse) front end", activityArea = "Informatique, télécoms, Web", ),
        Job(name = "Développeur(se)", activityArea = "Informatique, télécoms, Web", ),
        Job(name = "Digital learning manager", activityArea = "Informatique, télécoms, Web", ),
        Job(name = "Graphiste web", activityArea = "Informatique, télécoms, Web", ),
        Job(name = "Hotliner", activityArea = "Informatique, télécoms, Web", ),
        Job(name = "Ingénieur(e) cybersécurité", activityArea = "Informatique, télécoms, Web", ),
        Job(name = "Ingénieur(e) IoT", activityArea = "Informatique, télécoms, Web", ),
        Job(name = "Ingénieur(e) réseaux", activityArea = "Informatique, télécoms, Web", ),
        Job(name = "Ingénieur(e) sécurité", activityArea = "Informatique, télécoms, Web", ),
        Job(name = "Ingénieur(e) technico-commercial(e)", activityArea = "Informatique, télécoms, Web", ),
        Job(name = "Installateur télécoms : le témoignage de Christophe, apprenti", activityArea = "Informatique, télécoms, Web", ),
        Job(name = "Motion designer", activityArea = "Informatique, télécoms, Web", ),
        Job(name = "Référenceur(euse)", activityArea = "Informatique, télécoms, Web", ),
        Job(name = "Technicien(ne) en informatique industrielle", activityArea = "Informatique, télécoms, Web", ),
        Job(name = "Technicien(ne) en télécommunications", activityArea = "Informatique, télécoms, Web", ),
        Job(name = "Traffic manager", activityArea = "Informatique, télécoms, Web", ),
        Job(name = "UX designer", activityArea = "Informatique, télécoms, Web", ),
        Job(name = "Vendeur(euse) en micro-informatique", activityArea = "Informatique, télécoms, Web", ),
        Job(name = "Webdesigner", activityArea = "Informatique, télécoms, Web", ),
        Job(name = "Webmaster", activityArea = "Informatique, télécoms, Web", ),



//        # jeux vidéo
        Job(name = "Business developer jeu vidéo et esport", activityArea = "Jeu vidéo", ),
        Job(name = "Chef(fe) de produit jeu vidéo et esport", activityArea = "Jeu vidéo", ),
        Job(name = "Chef(fe) de projet esport", activityArea = "Jeu vidéo", ),
        Job(name = "Chef(fe) de projet jeu vidéo", activityArea = "Jeu vidéo", ),
        Job(name = "Data analyst jeu vidéo et esport", activityArea = "Jeu vidéo", ),
        Job(name = "Graphiste 2D/3D", activityArea = "Jeu vidéo", ),
        Job(name = "Programmeur gameplay", activityArea = "Jeu vidéo", ),
        Job(name = "Programmeur(euse) de jeux vidéo", activityArea = "Jeu vidéo", ),
        Job(name = "Programmeur(euse) mobile jeu vidéo", activityArea = "Jeu vidéo", ),
        Job(name = "Technical artist", activityArea = "Jeu vidéo", ),




//        # Journalisme
        Job(name = "Agencier(ière)", activityArea = "Journalisme", ),
        Job(name = "Journaliste radio", activityArea = "Journalisme", ),
        Job(name = "Journaliste reporter d’images", activityArea = "Journalisme", ),
        Job(name = "Journaliste Web", activityArea = "Journalisme", ),
        Job(name = "Maquettiste de presse", activityArea = "Journalisme", ),
        Job(name = "Photographe de presse", activityArea = "Journalisme", ),
        Job(name = "Rédacteur(rice) en presse écrite", activityArea = "Journalisme", ),
        Job(name = "Rédacteur(trice) en chef", activityArea = "Journalisme", ),
        Job(name = "Secrétaire de rédaction", activityArea = "Journalisme", ),



//        # Langues
        Job(name = "Interprète", activityArea = "Langues", ),
        Job(name = "Professeur(e) de FLE (Français Langue Étrangère)", activityArea = "Langues", ),
        Job(name = "Professeur(e) de langue vivante étrangère", activityArea = "Langues", ),
        Job(name = "Terminologue", activityArea = "Langues", ),
        Job(name = "Traducteur(rice) technique", activityArea = "Langues", ),
        Job(name = "Traducteur(trice) audiovisuel(le)", activityArea = "Langues", ),
        Job(name = "Traducteur(trice) dans la fonction publique", activityArea = "Langues", ),
        Job(name = "Traducteur(trice) expert(e)", activityArea = "Langues", ),
        Job(name = "Traducteur(trice) littéraire", activityArea = "Langues", ),

//        # Luxe
        Job(name = "Bijoutier(ière) - joaillier(ière)", activityArea = "Luxe", ),
        Job(name = "Créateur(trice) de parfum", activityArea = "Luxe", ),



//        # Marketing, publicité
        Job(name = "Chargé(e) d’études marketing", activityArea = "Marketing, publicité ", ),
        Job(name = "Chargé(e) de marketing direct", activityArea = "Marketing, publicité ", ),
        Job(name = "Chargé(e) de promotion", activityArea = "Marketing, publicité ", ),
        Job(name = "Chef de produit", activityArea = "Marketing, publicité ", ),
        Job(name = "Chef(fe) de publicité", activityArea = "Marketing, publicité ", ),
        Job(name = "Concepteur(trice)-rédacteur(trice)", activityArea = "Marketing, publicité ", ),
        Job(name = "Directeur(trice) artistique", activityArea = "Marketing, publicité ", ),
        Job(name = "Directeur(trice) de clientèle", activityArea = "Marketing, publicité ", ),
        Job(name = "Directeur(trice) de création", activityArea = "Marketing, publicité ", ),
        Job(name = "Directeur(trice) du marketing", activityArea = "Marketing, publicité ", ),
        Job(name = "Maquettiste de pub", activityArea = "Marketing, publicité ", ),
        Job(name = "Médiaplanneur(euse)", activityArea = "Marketing, publicité ", ),
        Job(name = "Responsable base de données", activityArea = "Marketing, publicité ", ),
        Job(name = "Responsable du merchandising", activityArea = "Marketing, publicité ", ),
        Job(name = "Roughman(woman)", activityArea = "Marketing, publicité ", ),



//        # Médical
        Job(name = "Anesthésiste-réanimateur(trice)", activityArea = "Médical", ),
        Job(name = "Chirurgien(ne)", activityArea = "Médical", ),
        Job(name = "Chirurgien(ne)-dentiste", activityArea = "Médical", ),
        Job(name = "Dermatologue", activityArea = "Médical", ),
        Job(name = "Médecin de l’Éducation nationale", activityArea = "Médical", ),
        Job(name = "Médecin du sport", activityArea = "Médical", ),
        Job(name = "Médecin du travail", activityArea = "Médical", ),
        Job(name = "Médecin généraliste", activityArea = "Médical", ),
        Job(name = "Médecin régulateur(trice)", activityArea = "Médical", ),
        Job(name = "Médecin urgentiste", activityArea = "Médical", ),
        Job(name = "Ophtalmologue", activityArea = "Médical", ),
        Job(name = "Oto-rhino-laryngologiste", activityArea = "Médical", ),
        Job(name = "Pédiatre", activityArea = "Médical", ),
        Job(name = "Psychiatre", activityArea = "Médical", ),
        Job(name = "Sage-femme", activityArea = "Médical", ),



//        # Mode-textile
        Job(name = "Acheteur(euse) habillement", activityArea = "Mode-textile ", ),
        Job(name = "Chef(fe) de produit", activityArea = "Mode-textile ", ),
        Job(name = "Designer(euse) textile", activityArea = "Mode-textile ", ),
        Job(name = "Ingénieur(e) textile", activityArea = "Mode-textile ", ),
        Job(name = "Modéliste", activityArea = "Mode-textile ", ),
        Job(name = "Styliste", activityArea = "Mode-textile ", ),
        Job(name = "Technicien(ne) de fabrication", activityArea = "Mode-textile ", ),



//        # Paramédical
        Job(name = "Aide-soignant(e)", activityArea = "Paramédical", ),
        Job(name = "Ambulancier(ière)", activityArea = "Paramédical", ),
        Job(name = "Assistant(e) dentaire", activityArea = "Paramédical", ),
        Job(name = "Audioprothésiste", activityArea = "Paramédical", ),
        Job(name = "Auxiliaire de puériculture", activityArea = "Paramédical", ),
        Job(name = "Diététicien(ne)", activityArea = "Paramédical", ),
        Job(name = "Ergothérapeute", activityArea = "Paramédical", ),
        Job(name = "Infirmier(ère) puériculteur(trice)", activityArea = "Paramédical", ),
        Job(name = "Infirmier(ière)", activityArea = "Paramédical", ),
        Job(name = "Manipulateur(trice) en électroradiologie médicale", activityArea = "Paramédical", ),
        Job(name = "Masseur(euse)-kinésithérapeute", activityArea = "Paramédical", ),
        Job(name = "Opticien(ne)-lunetier(ière)", activityArea = "Paramédical", ),
        Job(name = "Orthophoniste", activityArea = "Paramédical", ),
        Job(name = "Orthoptiste", activityArea = "Paramédical", ),
        Job(name = "Ostéopathe, chiropracteur(trice)", activityArea = "Paramédical", ),
        Job(name = "Pédicure-podologue", activityArea = "Paramédical", ),
        Job(name = "Podo-orthésiste", activityArea = "Paramédical", ),
        Job(name = "Prothésiste dentaire", activityArea = "Paramédical", ),
        Job(name = "Psychomotricien(ne)", activityArea = "Paramédical", ),
        Job(name = "Secrétaire médical(e)", activityArea = "Paramédical", ),
        Job(name = "Technicien(ne) de laboratoire (paramédical)", activityArea = "Paramédical", ),
        Job(name = "Visiteur(euse) médical(e)", activityArea = "Paramédical", ),


//        # Propreté et services associés
        Job(name = "Agent(e) d’entretien et de rénovation", activityArea = "Propreté et services associés", ),
        Job(name = "Agent(e) machiniste", activityArea = "Propreté et services associés", ),
        Job(name = "Chef(fe) d’équipe", activityArea = "Propreté et services associés", ),
        Job(name = "Laveur(euse) de vitres en hauteur", activityArea = "Propreté et services associés", ),
        Job(name = "Responsable de secteur", activityArea = "Propreté et services associés", ),
        Job(name = "Technicien(ne) de traitement des déchets", activityArea = "Propreté et services associés", ),



//        # Psychologie
        Job(name = "Psychologue clinicien(ne)", activityArea = "Psychologie", ),
        Job(name = "Psychologue de l'Éducation nationale", activityArea = "Psychologie", ),
        Job(name = "Psychologue du travail", activityArea = "Psychologie", ),
        Job(name = "Psychologue scolaire", activityArea = "Psychologie", ),
        Job(name = "Psychothérapeute", activityArea = "Psychologie", ),


//        # Ressources humaines
        Job(name = "Assistant(e) des ressources humaines", activityArea = "Ressources humaines", ),
        Job(name = "Chargé(e) du recrutement", activityArea = "Ressources humaines", ),
        Job(name = "Directeur(trice) des ressources humaines", activityArea = "Ressources humaines", ),
        Job(name = "Gestionnaire de carrières", activityArea = "Ressources humaines", ),
        Job(name = "Juriste social(e)", activityArea = "Ressources humaines", ),
        Job(name = "Outplacer", activityArea = "Ressources humaines", ),



//        # Sciences humaines et sociales
        Job(name = "Cartographe", activityArea = "Sciences humaines et sociales", ),
        Job(name = "Démographe", activityArea = "Sciences humaines et sociales", ),
        Job(name = "Géographe", activityArea = "Sciences humaines et sociales", ),
        Job(name = "Historien(ne)", activityArea = "Sciences humaines et sociales", ),
        Job(name = "Sociologue", activityArea = "Sciences humaines et sociales", ),
        Job(name = "Urbaniste", activityArea = "Sciences humaines et sociales", ),





//        # Secrétariat
        Job(name = "Assistant(e) commercial(e)", activityArea = "Secrétariat", ),
        Job(name = "Assistant(e) de manager", activityArea = "Secrétariat", ),
        Job(name = "Assistant(e) trilingue", activityArea = "Secrétariat", ),
        Job(name = "Secrétaire administratif(ve)", activityArea = "Secrétariat", ),
        Job(name = "Secrétaire comptable", activityArea = "Secrétariat", ),
        Job(name = "Secrétaire de mairie", activityArea = "Secrétariat", ),
        Job(name = "Secrétaire juridique", activityArea = "Secrétariat", ),
        Job(name = "Secrétaire médical(e)", activityArea = "Secrétariat", ),




//        # Social
        Job(name = "Animateur(trice) socioculturel(le)", activityArea = "Social", ),
        Job(name = "Assistant(e) de service social", activityArea = "Social", ),
        Job(name = "Conseiller(ère) en économie sociale et familiale", activityArea = "Social", ),
        Job(name = "Conseiller(ère) pénitentiaire d’insertion et de probation", activityArea = "Social", ),
        Job(name = "Directeur(trice) de structure sociale", activityArea = "Social", ),
        Job(name = "Éducateur(trice) de jeunes enfants", activityArea = "Social", ),
        Job(name = "Éducateur(trice) de la protection judiciaire de la jeunesse", activityArea = "Social", ),
//        Metier(nom = "Éducateur(trice) spécialisé(e)", secteur = "Social", ),
//        Metier(nom = "Éducateur(trice) technique spécialisé(e)", secteur = "Social", ),


//        # Spectacle - Métiers de la scène
        Job(name = "Chanteur(euse)", activityArea = "Spectacle - Métiers de la scène ", ),
        Job(name = "Comédien(ne)", activityArea = "Spectacle - Métiers de la scène ", ),
        Job(name = "Costumier(ière)", activityArea = "Spectacle - Métiers de la scène ", ),
        Job(name = "Danseur(euse)", activityArea = "Spectacle - Métiers de la scène ", ),
        Job(name = "Décorateur(trice)-scénographe", activityArea = "Spectacle - Métiers de la scène ", ),
        Job(name = "Musicien(ne)", activityArea = "Spectacle - Métiers de la scène ", ),


//        # Sport
        Job(name = "Directeur(trice) d'équipement sportif", activityArea = "Sport", ),
        Job(name = "Entraîneur(e)", activityArea = "Sport", ),
        Job(name = "Journaliste sportif(ve)", activityArea = "Sport", ),
        Job(name = "Maître-nageur(euse) sauveteur(euse)", activityArea = "Sport", ),
        Job(name = "Moniteur(trice) de sport", activityArea = "Sport", ),
        Job(name = "Professeur(e) d’EPS (éducation physique et sportive)", activityArea = "Sport", ),
        Job(name = "Professeur(e) de sport", activityArea = "Sport", ),
        Job(name = "Vendeur(euse) d’articles de sport", activityArea = "Sport", ),




//        # Transport-Logistique
        Job(name = "Attaché commercial : le témoignage de Jean, apprenti", activityArea = "Transport-Logistique ", ),
        Job(name = "Chef(fe) d’agence de transport", activityArea = "Transport-Logistique ", ),
        Job(name = "Conducteur(trice) de train de marchandises", activityArea = "Transport-Logistique ", ),
        Job(name = "Conducteur(trice) routier(ière)", activityArea = "Transport-Logistique ", ),
        Job(name = "Contrôleuse de train : le témoignage de Ilham, apprentie", activityArea = "Transport-Logistique ", ),
        Job(name = "Gestionnaire de stocks", activityArea = "Transport-Logistique ", ),
        Job(name = "Officier(ière) de la marine marchande", activityArea = "Transport-Logistique ", ),
        Job(name = "Responsable d’entrepôt", activityArea = "Transport-Logistique ", ),
        Job(name = "Responsable d’exploitation", activityArea = "Transport-Logistique ", ),
        Job(name = "Responsable logistique", activityArea = "Transport-Logistique ", ),



//        # Tourisme
        Job(name = "Agent(e) de comptoir (vendeur(euse)-conseil)", activityArea = "Tourisme", ),
        Job(name = "Animateur(trice)", activityArea = "Tourisme", ),
        Job(name = "Chef(fe) de produit touristique", activityArea = "Tourisme", ),
        Job(name = "Guide-accompagnateur(trice)", activityArea = "Tourisme", ),
        Job(name = "Guide-conférencier(ière)", activityArea = "Tourisme", ),
        Job(name = "Hôtesse d’accueil : le témoignage de Kathia, apprentie", activityArea = "Tourisme", ),

    )
}

