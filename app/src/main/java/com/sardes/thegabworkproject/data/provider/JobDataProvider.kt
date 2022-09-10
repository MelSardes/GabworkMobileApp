package com.sardes.thegabworkproject.data.provider

import com.sardes.thegabworkproject.data.models.Metier

object JobDataProvider {


    val metiers = mutableListOf(

//        # AERONAUTIQUE
        Metier(nom = "Agent(e) de trafic", secteur = "Aéronautique", ),
        Metier(nom = "Chef(fe) d’avion", secteur = "Aéronautique", ),
        Metier(nom = "Contrôleur(euse) aérien(ne)", secteur = "Aéronautique", ),
        Metier(nom = "Hôtesse de l’air/steward", secteur = "Aéronautique", ),
        Metier(nom = "Ingénieur(e) aéronautique", secteur = "Aéronautique", ),
        Metier(nom = "Mécanicien(ne) moteur", secteur = "Aéronautique", ),
        Metier(nom = "Pilote de ligne", secteur = "Aéronautique", ),
        Metier(nom = "TSA (technicien(ne) supérieur(e) de l’aviation)", secteur = "Aéronautique", ),


//        # AGROALIMENTAIRE
        Metier(nom = "Acheteur(euse) dans l’agroalimentaire", secteur = "Agroalimentaire", ),
        Metier(nom = "Animateur(trice) logistique", secteur = "Agroalimentaire", ),
        Metier(nom = "Assistante paysagiste : le témoignage de Nathalie, apprentie", secteur = "Agroalimentaire", ),
        Metier(nom = "Attaché(e) commercial(e) / merchandising", secteur = "Agroalimentaire", ),
        Metier(nom = "Auxiliaire vétérinaire", secteur = "Agroalimentaire", ),
        Metier(nom = "Conducteur(trice) de ligne de fabrication ou de conditionnement", secteur = "Agroalimentaire", ),
        Metier(nom = "Conducteur(trice) de machine de fabrication ou de conditionnement", secteur = "Agroalimentaire", ),
        Metier(nom = "Conducteur(trice) de machines agricoles", secteur = "Agroalimentaire", ),
        Metier(nom = "Conseiller(ère) agricole", secteur = "Agroalimentaire", ),
        Metier(nom = "Contrôleur(euse) laitier(ière)", secteur = "Agroalimentaire", ),
        Metier(nom = "Éleveur(euse)", secteur = "Agroalimentaire", ),
        Metier(nom = "Exploitant(e) agricole", secteur = "Agroalimentaire", ),
        Metier(nom = "Horticulteur(trice)", secteur = "Agroalimentaire", ),
        Metier(nom = "Ingénieur(e) agronome", secteur = "Agroalimentaire", ),
        Metier(nom = "Ingénieur(e) en agroalimentaire", secteur = "Agroalimentaire", ),
        Metier(nom = "Œnologue", secteur = "Agroalimentaire", ),
        Metier(nom = "Opérateur(trice) de fabrication", secteur = "Agroalimentaire", ),
        Metier(nom = "Pépiniériste : le témoignage de Virginie, apprentie", secteur = "Agroalimentaire", ),
        Metier(nom = "Responsable d’atelier de fabrication ou de conditionnement", secteur = "Agroalimentaire", ),
        Metier(nom = "Responsable de la supply chain", secteur = "Agroalimentaire", ),
        Metier(nom = "Responsable de maintenance", secteur = "Agroalimentaire", ),
        Metier(nom = "Responsable de production", secteur = "Agroalimentaire", ),
        Metier(nom = "Responsable qualité", secteur = "Agroalimentaire", ),
        Metier(nom = "Technicien(ne) agricole", secteur = "Agroalimentaire", ),
        Metier(nom = "Technicien(ne) de maintenance", secteur = "Agroalimentaire", ),
        Metier(nom = "Technicien(ne) en aquaculture", secteur = "Agroalimentaire", ),
        Metier(nom = "Technicien(ne) qualité", secteur = "Agroalimentaire", ),
        Metier(nom = "Vétérinaire", secteur = "Agroalimentaire", ),
        Metier(nom = "Viticulteur(trice)", secteur = "Agroalimentaire", ),


//        # ARTISANAT
        Metier(nom = "Boucher", secteur = "Artisanat", ),
        Metier(nom = "Boulanger(ère)-pâtissier(ère)", secteur = "Artisanat", ),
        Metier(nom = "Charpentier(ière)", secteur = "Artisanat", ),
        Metier(nom = "Coiffeur(euse)", secteur = "Artisanat", ),
        Metier(nom = "Fleuriste", secteur = "Artisanat", ),
        Metier(nom = "Plombier(ière), installateur(trice) thermique", secteur = "Artisanat", ),



//        # Audiovisuel, cinéma
        Metier(nom = "Animateur 3D", secteur = "Audiovisuel, cinéma", ),
        Metier(nom = "Assistant(e) de production", secteur = "Audiovisuel, cinéma", ),
        Metier(nom = "Assistant(e) réalisateur(trice)", secteur = "Audiovisuel, cinéma", ),
        Metier(nom = "Cadreur(euse)", secteur = "Audiovisuel, cinéma", ),
        Metier(nom = "Chargé(e) de diffusion", secteur = "Audiovisuel, cinéma", ),
        Metier(nom = "Chef(fe) opérateur(trice)", secteur = "Audiovisuel, cinéma", ),
        Metier(nom = "Ingénieur(e) de la vision", secteur = "Audiovisuel, cinéma", ),
        Metier(nom = "Ingénieur(e) du son", secteur = "Audiovisuel, cinéma", ),
        Metier(nom = "Monteur(euse)", secteur = "Audiovisuel, cinéma", ),
        Metier(nom = "Monteur(euse)-Truquiste", secteur = "Audiovisuel, cinéma", ),
        Metier(nom = "Producteur(trice)", secteur = "Audiovisuel, cinéma", ),
        Metier(nom = "Réalisateur(trice)", secteur = "Audiovisuel, cinéma", ),
        Metier(nom = "Régisseur(euse) général(e)", secteur = "Audiovisuel, cinéma", ),
        Metier(nom = "Scénariste", secteur = "Audiovisuel, cinéma", ),
        Metier(nom = "Scripte", secteur = "Audiovisuel, cinéma", ),


//        # Audit, comptabilité, gestion
        Metier(nom = "Assistant(e) comptable", secteur = "Audit, comptabilité, gestion", ),
        Metier(nom = "Auditeur(trice) à la Cour des comptes", secteur = "Audit, comptabilité, gestion", ),
        Metier(nom = "Auditeur(trice) financier(ière)", secteur = "Audit, comptabilité, gestion", ),
        Metier(nom = "Auditeur(trice) interne", secteur = "Audit, comptabilité, gestion", ),
        Metier(nom = "Auditeur(trice) spécialisé(e)", secteur = "Audit, comptabilité, gestion", ),
        Metier(nom = "Commissaire aux comptes", secteur = "Audit, comptabilité, gestion", ),
        Metier(nom = "Comptable", secteur = "Audit, comptabilité, gestion", ),
        Metier(nom = "Contrôleur(euse) de gestion", secteur = "Audit, comptabilité, gestion", ),
        Metier(nom = "Directeur(trice) comptable", secteur = "Audit, comptabilité, gestion", ),
        Metier(nom = "Directeur(trice) financier(ière)", secteur = "Audit, comptabilité, gestion", ),
        Metier(nom = "Expert(e)-comptable", secteur = "Audit, comptabilité, gestion", ),
        Metier(nom = "Fiscaliste", secteur = "Audit, comptabilité, gestion", ),
        Metier(nom = "Responsable consolidation", secteur = "Audit, comptabilité, gestion", ),
        Metier(nom = "Risk manager", secteur = "Audit, comptabilité, gestion", ),
        Metier(nom = "Trésorier(ière)", secteur = "Audit, comptabilité, gestion", ),


//        # Automobile
        Metier(nom = "Expert(e) automobile", secteur = "Automobile", ),
        Metier(nom = "Ingénieur(e) d’études", secteur = "Automobile", ),
        Metier(nom = "Mécanicien poids lourds : le témoignage d’André, apprenti", secteur = "Automobile", ),
        Metier(nom = "Mécanicien(ne) réparateur(trice)", secteur = "Automobile", ),
        Metier(nom = "Peintre en carrosserie : le témoignage de Damien, apprenti", secteur = "Automobile", ),
        Metier(nom = "Vendeur d’équipement auto : le témoignage de William, apprenti", secteur = "Automobile", ),
        Metier(nom = "Vendeur(euse) automobile (A)", secteur = "Automobile", ),





//        # Banque, assurance
        Metier(nom = "Actuaire", secteur = "Banque, assurance", ),
        Metier(nom = "Agent(e) général(e) d'assurance", secteur = "Banque, assurance", ),
        Metier(nom = "Analyste financier(ière)", secteur = "Banque, assurance", ),
        Metier(nom = "Banquier(ière) du commerce international", secteur = "Banque, assurance", ),
        Metier(nom = "Chargé(e) de clientèle", secteur = "Banque, assurance", ),
        Metier(nom = "Conseiller bancaire : le témoignage de Steeve, apprenti", secteur = "Banque, assurance", ),
        Metier(nom = "Conseiller(ère) commercial(e) en assurances", secteur = "Banque, assurance", ),
        Metier(nom = "Courtier(ère) d’assurances", secteur = "Banque, assurance", ),
        Metier(nom = "Credit manager", secteur = "Banque, assurance", ),
        Metier(nom = "Directeur(trice) d’agence bancaire", secteur = "Banque, assurance", ),
        Metier(nom = "Expert(e) d’assurances", secteur = "Banque, assurance", ),
        Metier(nom = "Gestionnaire assurance : le témoignage de Cynthia, apprentie", secteur = "Banque, assurance", ),
        Metier(nom = "Gestionnaire de contrats d’assurance", secteur = "Banque, assurance", ),
        Metier(nom = "Gestionnaire de patrimoine", secteur = "Banque, assurance", ),
        Metier(nom = "Rédacteur(trice) dans les assurances", secteur = "Banque, assurance", ),
        Metier(nom = "Responsable du back-office", secteur = "Banque, assurance", ),
        Metier(nom = "Souscripteur(trice)", secteur = "Banque, assurance", ),
        Metier(nom = "Trader (opérateur/opératrice de marché)", secteur = "Banque, assurance", ),



//        # Bâtiment, travaux publics
        Metier(nom = "Architecte", secteur = "Bâtiment, travaux publics ", ),
        Metier(nom = "BIM manager", secteur = "Bâtiment, travaux publics ", ),
        Metier(nom = "Chargé(e) d’affaires dans le BTP", secteur = "Bâtiment, travaux publics ", ),
        Metier(nom = "Chef de chantier", secteur = "Bâtiment, travaux publics ", ),
        Metier(nom = "Conducteur(trice) d’engins de chantier", secteur = "Bâtiment, travaux publics ", ),
        Metier(nom = "Conducteur(trice) de travaux", secteur = "Bâtiment, travaux publics ", ),
        Metier(nom = "Dessinateur(trice)-projeteur(euse) en bâtiment", secteur = "Bâtiment, travaux publics ", ),
        Metier(nom = "Électricien(ne) du BTP", secteur = "Bâtiment, travaux publics ", ),
        Metier(nom = "Ingénieur(e) études de prix", secteur = "Bâtiment, travaux publics ", ),
        Metier(nom = "Ingénieur(e) géomètre", secteur = "Bâtiment, travaux publics ", ),
        Metier(nom = "Maçon", secteur = "Bâtiment, travaux publics ", ),
        Metier(nom = "Ouvrier routier", secteur = "Bâtiment, travaux publics ", ),
        Metier(nom = "Ouvrier(ière) qualifié(e)", secteur = "Bâtiment, travaux publics ", ),
        Metier(nom = "Peintre décorateur", secteur = "Bâtiment, travaux publics ", ),


//        # Biologie, chimie, pharmacie
        Metier(nom = "Bio-informaticien(ne)", secteur = "Biologie, chimie, pharmacie", ),
        Metier(nom = "Hydrobiologiste", secteur = "Biologie, chimie, pharmacie", ),
        Metier(nom = "Ingénieur(e) chimiste", secteur = "Biologie, chimie, pharmacie", ),
        Metier(nom = "Pharmacien(ne)", secteur = "Biologie, chimie, pharmacie", ),
        Metier(nom = "Préparateur(trice) en pharmacie", secteur = "Biologie, chimie, pharmacie", ),
        Metier(nom = "Responsable des affaires réglementaires", secteur = "Biologie, chimie, pharmacie", ),
        Metier(nom = "Technicien(ne) d’analyses biomédicales", secteur = "Biologie, chimie, pharmacie", ),
        Metier(nom = "Technicien(ne) de l’industrie pharmaceutique", secteur = "Biologie, chimie, pharmacie", ),


//        # Commerce, distribution
        Metier(nom = "Acheteur(euse)", secteur = "Commerce, distribution", ),
        Metier(nom = "Agent(e) commercial(e)", secteur = "Commerce, distribution", ),
        Metier(nom = "Assistant(e) export", secteur = "Commerce, distribution", ),
        Metier(nom = "Chargé(e) d’affaires", secteur = "Commerce, distribution", ),
        Metier(nom = "Chef(fe) de rayon", secteur = "Commerce, distribution", ),
        Metier(nom = "Commercial(e)", secteur = "Commerce, distribution", ),
        Metier(nom = "Directeur(trice) de centre de profit", secteur = "Commerce, distribution", ),
        Metier(nom = "Responsable de magasin", secteur = "Commerce, distribution", ),
        Metier(nom = "Vendeuse esthéticienne : le témoignage de Sophie, apprentie", secteur = "Commerce, distribution", ),



//        # Communication
        Metier(nom = "Assistant(e) en communication", secteur = "Communication", ),
        Metier(nom = "Attaché(e) de presse", secteur = "Communication", ),
        Metier(nom = "Chargé(e) de communication", secteur = "Communication", ),
        Metier(nom = "Directeur(trice) de la communication", secteur = "Communication", ),
        Metier(nom = "Journaliste d’entreprise", secteur = "Communication", ),



//        # Création, métiers d'art
        Metier(nom = "Architecte d’intérieur", secteur = "Création, métiers d'art", ),
        Metier(nom = "Artiste plasticien(ne)", secteur = "Création, métiers d'art", ),
        Metier(nom = "Céramiste", secteur = "Création, métiers d'art", ),
        Metier(nom = "Concepteur(trice) de jeux vidéo", secteur = "Création, métiers d'art", ),
        Metier(nom = "Décorateur(trice)", secteur = "Création, métiers d'art", ),
        Metier(nom = "Designer industriel(le)", secteur = "Création, métiers d'art", ),
        Metier(nom = "Dessinateur(trice) de BD", secteur = "Création, métiers d'art", ),
        Metier(nom = "Ébéniste", secteur = "Création, métiers d'art", ),
        Metier(nom = "Facteur(trice) d’instruments", secteur = "Création, métiers d'art", ),
        Metier(nom = "Graphiste", secteur = "Création, métiers d'art", ),
        Metier(nom = "Illustrateur(trice)", secteur = "Création, métiers d'art", ),
        Metier(nom = "Infographiste 2D-3D", secteur = "Création, métiers d'art", ),
        Metier(nom = "Modiste", secteur = "Création, métiers d'art", ),
        Metier(nom = "Photographe", secteur = "Création, métiers d'art", ),
        Metier(nom = "Professeur(e) d’arts plastiques", secteur = "Création, métiers d'art", ),
        Metier(nom = "Réalisatrice multimédia : le témoignage de Caroline, apprentie", secteur = "Création, métiers d'art", ),
        Metier(nom = "Relieur(euse)-doreur(euse)", secteur = "Création, métiers d'art", ),
        Metier(nom = "Verrier(ière)", secteur = "Création, métiers d'art", ),




//        # Culture, patrimoine
        Metier(nom = "Animateur(trice) de l’architecture et du patrimoine", secteur = "Culture, patrimoine",),
        Metier(nom = "Archéologue", secteur = "Culture, patrimoine",),
        Metier(nom = "Commissaire-priseur(se)", secteur = "Culture, patrimoine",),
        Metier(nom = "Conservateur(trice) du patrimoine", secteur = "Culture, patrimoine",),
        Metier(nom = "Galeriste", secteur = "Culture, patrimoine",),
        Metier(nom = "Guide-conférencier(ière) de musée et monument", secteur = "Culture, patrimoine",),
        Metier(nom = "Médiateur(trice) culturel(le)", secteur = "Culture, patrimoine",),
        Metier(nom = "Régisseur(euse) d’œuvres d’art", secteur = "Culture, patrimoine",),
        Metier(nom = "Restaurateur(trice) d’œuvres d’art", secteur = "Culture, patrimoine",),



//        # Documentation, bibliothèque
        Metier(nom = "Archiviste", secteur = "Documentation, bibliothèque", ),
        Metier(nom = "Bibliothécaire", secteur = "Documentation, bibliothèque", ),
        Metier(nom = "Conservateur(trice) de bibliothèque", secteur = "Documentation, bibliothèque", ),
        Metier(nom = "Documentaliste", secteur = "Documentation, bibliothèque", ),




//        # Droit
        Metier(nom = "Assistant(e) parlementaire", secteur = "Droit", ),
        Metier(nom = "Avocat(e)", secteur = "Droit", ),
        Metier(nom = "Clerc de notaire", secteur = "Droit", ),
        Metier(nom = "Greffier(ère)", secteur = "Droit", ),
        Metier(nom = "Huissier(ère) de justice", secteur = "Droit", ),
        Metier(nom = "Juge aux affaires familiales", secteur = "Droit", ),
        Metier(nom = "Juge d’instruction", secteur = "Droit", ),
        Metier(nom = "Juge de l’application des peines", secteur = "Droit", ),
        Metier(nom = "Juge des enfants", secteur = "Droit", ),
        Metier(nom = "Juriste d’entreprise", secteur = "Droit", ),
        Metier(nom = "Notaire", secteur = "Droit", ),
        Metier(nom = "Substitut(e) du procureur", secteur = "Droit", ),



//        # Edition, livre
        Metier(nom = "Auteur(e)", secteur = "Edition, livre", ),
        Metier(nom = "Chef(fe) de fabrication dans l'édition", secteur = "Edition, livre", ),
        Metier(nom = "Correcteur(trice)", secteur = "Edition, livre", ),
        Metier(nom = "Iconographe", secteur = "Edition, livre", ),
        Metier(nom = "Libraire", secteur = "Edition, livre", ),
        Metier(nom = "Responsable d’édition", secteur = "Edition, livre", ),


//        # Enseignement
        Metier(nom = "Conseiller(ère) principal(e) d’éducation", secteur = "Enseignement", ),
        Metier(nom = "Enseignant(e) à l’étranger", secteur = "Enseignement", ),
        Metier(nom = "Enseignant(e)-chercheur(euse)", secteur = "Enseignement", ),
        Metier(nom = "Formateur(trice) d’adultes", secteur = "Enseignement", ),
        Metier(nom = "Professeur(e) de collège et de lycée", secteur = "Enseignement", ),
        Metier(nom = "Professeur(e) de lycée professionnel ou technique", secteur = "Enseignement", ),
        Metier(nom = "Professeur(e) de musique", secteur = "Enseignement", ),
        Metier(nom = "Professeur(e) des écoles", secteur = "Enseignement", ),
        Metier(nom = "Professeur(e) du privé", secteur = "Enseignement", ),


//        # Environnement
        Metier(nom = "Acousticien(ne)", secteur = "Environnement", ),
        Metier(nom = "Agent(e) de qualité de l’eau", secteur = "Environnement", ),
        Metier(nom = "Agent(e) des réseaux d’eau potable (fontainier-ère)", secteur = "Environnement", ),
        Metier(nom = "Agent(e) technique de l'environnement (garde-pêche)", secteur = "Environnement", ),
        Metier(nom = "Agent(e) technique sites et sols pollués", secteur = "Environnement", ),
        Metier(nom = "Agriculteur/trice biologique", secteur = "Environnement", ),
        Metier(nom = "Analyste extrafinancier", secteur = "Environnement", ),
        Metier(nom = "Animateur(trice) en écotourisme", secteur = "Environnement", ),
        Metier(nom = "Biologiste en environnement", secteur = "Environnement", ),
        Metier(nom = "Botaniste", secteur = "Environnement", ),
        Metier(nom = "Chargé(e) de mission Agenda 21", secteur = "Environnement", ),
        Metier(nom = "Climatologue", secteur = "Environnement", ),
        Metier(nom = "Conseiller(ère) en environnement", secteur = "Environnement", ),
        Metier(nom = "Conseiller(ère) en environnement domestique", secteur = "Environnement", ),
        Metier(nom = "Écodéveloppeur(se)", secteur = "Environnement", ),
        Metier(nom = "Écogarde", secteur = "Environnement", ),
        Metier(nom = "Entomologiste", secteur = "Environnement", ),
        Metier(nom = "Étanchéiste", secteur = "Environnement", ),
        Metier(nom = "Garde-moniteur(trice)", secteur = "Environnement", ),
        Metier(nom = "Géologue", secteur = "Environnement", ),
        Metier(nom = "Hydrogéologue", secteur = "Environnement", ),
        Metier(nom = "Ingénieur(e) écoconception", secteur = "Environnement", ),
        Metier(nom = "Ingénieur(e) écologue", secteur = "Environnement", ),
        Metier(nom = "Ingénieur(e) en analyse de l’air", secteur = "Environnement", ),
        Metier(nom = "Ingénieur(e) hydrologue", secteur = "Environnement", ),
        Metier(nom = "Inspecteur(trice) de la santé publique vétérinaire", secteur = "Environnement", ),
        Metier(nom = "Juriste spécialisé en droit de l’environnement", secteur = "Environnement", ),
        Metier(nom = "Juriste spécialisé(e) en droit de l'environnement", secteur = "Environnement", ),
        Metier(nom = "Paysagiste", secteur = "Environnement", ),
        Metier(nom = "Psychologue environnementaliste", secteur = "Environnement", ),
        Metier(nom = "Responsable commercial(e) environnement", secteur = "Environnement", ),
        Metier(nom = "Responsable de l’animation scientifique", secteur = "Environnement", ),
        Metier(nom = "Responsable de station d’épuration", secteur = "Environnement", ),
        Metier(nom = "Responsable environnement", secteur = "Environnement", ),
        Metier(nom = "Rudologue", secteur = "Environnement", ),
        Metier(nom = "Technicien(ne) de laboratoire (environnement)", secteur = "Environnement", ),
        Metier(nom = "Technicien(ne) de mesure de la pollution", secteur = "Environnement", ),
        Metier(nom = "Technicien(ne) forestier(ière)", secteur = "Environnement", ),
        Metier(nom = "Technicien(ne) hygiène, sécurité, environnement", secteur = "Environnement", ),



//        # Ferroviaire
        Metier(nom = "Agent(e) d’escale ferroviaire (agence cadre)", secteur = "Ferroviaire", ),
        Metier(nom = "Agent(e) de la sûreté ferroviaire (agence transverse)", secteur = "Ferroviaire", ),
        Metier(nom = "Commercial(e) en gare (agence voyageurs)", secteur = "Ferroviaire", ),
        Metier(nom = "Conducteur(trice) de train", secteur = "Ferroviaire", ),
        Metier(nom = "Ingénieur(e) d’études génie électrique (agence cadre)", secteur = "Ferroviaire", ),
        Metier(nom = "Manageur(euse) maintenance et travaux en génie civil", secteur = "Ferroviaire", ),
        Metier(nom = "Opérateur(trice) de maintenance des trains (agence matériel)", secteur = "Ferroviaire", ),
        Metier(nom = "Opérateur(trice) de signalisation électrique", secteur = "Ferroviaire", ),
        Metier(nom = "Technicien(ne) de la voie ferrée", secteur = "Ferroviaire", ),
        Metier(nom = "Technicien(ne) de maintenance des trains (agence matériel)", secteur = "Ferroviaire", ),



//        # Foires, salons et congrès
        Metier(nom = "Chargé(e) de sécurité sur des foires, salons ou congrès", secteur = "Foires, salons et congrès", ),
        Metier(nom = "Chef(fe) de hall(s) sur des salons, foires ou congrès", secteur = "Foires, salons et congrès", ),
        Metier(nom = "Chef(fe) de projet marketing TIC", secteur = "Foires, salons et congrès", ),
        Metier(nom = "Concepteur(trice) de stand sur des foires, salons, congrès", secteur = "Foires, salons et congrès", ),
        Metier(nom = "Conseiller(ère) d’exposant(s) sur des salons, foires ou congrès", secteur = "Foires, salons et congrès", ),
        Metier(nom = "Décorateur(trice) floral(e) sur des foires, salons, congrès", secteur = "Foires, salons et congrès", ),
        Metier(nom = "Directeur(trice) de congrès-expositions", secteur = "Foires, salons et congrès", ),
        Metier(nom = "Directeur(trice) de foire(s)", secteur = "Foires, salons et congrès", ),
        Metier(nom = "Directeur(trice) de manifestation sur des salons, foires ou congrès", secteur = "Foires, salons et congrès", ),
        Metier(nom = "Directeur(trice) de salons", secteur = "Foires, salons et congrès", ),
        Metier(nom = "Directeur(trice) juridique pour des foires, salons ou congrès", secteur = "Foires, salons et congrès", ),
        Metier(nom = "Directeur(trice) technique sur des foires, salons ou congrès", secteur = "Foires, salons et congrès", ),
        Metier(nom = "Installateur(trice) général(e) sur des foires, salons, congrès", secteur = "Foires, salons et congrès", ),
        Metier(nom = "Régisseur(euse) sur des salons, foires ou congrès", secteur = "Foires, salons et congrès", ),
        Metier(nom = "Responsable commercial(e) et marketing pour des foires, salons ou congrès", secteur = "Foires, salons et congrès", ),
        Metier(nom = "Responsable développement salon", secteur = "Foires, salons et congrès", ),
        Metier(nom = "Responsable marketing pour des salons, foires ou congrès", secteur = "Foires, salons et congrès", ),
        Metier(nom = "Responsable sécurité sur des foires, salons, congrès", secteur = "Foires, salons et congrès", ),
        Metier(nom = "Scénographe pour des foires, salons ou congrès", secteur = "Foires, salons et congrès", ),
        Metier(nom = "Tapissier(ière) sur des foires, salons, congrès", secteur = "Foires, salons et congrès", ),
        Metier(nom = "Technicien(ne) sur des salons, foires ou congrès", secteur = "Foires, salons et congrès", ),


//        # Hôtellerie, restauration
        Metier(nom = "Chef(fe) de cuisine", secteur = "Hôtellerie, restauration", ),
        Metier(nom = "Concierge d'hôtel", secteur = "Hôtellerie, restauration", ),
        Metier(nom = "Cuisinier : le témoignage de Sébastien, apprenti", secteur = "Hôtellerie, restauration", ),
        Metier(nom = "Directeur(trice) d’hôtel", secteur = "Hôtellerie, restauration", ),
        Metier(nom = "Gérant(e) de restauration collective", secteur = "Hôtellerie, restauration", ),
        Metier(nom = "Maître(sse) d’hôtel", secteur = "Hôtellerie, restauration", ),
        Metier(nom = "Serveur : le témoignage de Jérémie, apprenti", secteur = "Hôtellerie, restauration", ),
        Metier(nom = "Sommelier(ière)", secteur = "Hôtellerie, restauration", ),
        Metier(nom = "Yield manager", secteur = "Hôtellerie, restauration", ),


//        # Immobilier
        Metier(nom = "Administrateur(trice) de biens", secteur = "Immobilier", ),
        Metier(nom = "Agent(e) immobilier(ière)", secteur = "Immobilier", ),
        Metier(nom = "Chasseur(euse) de biens immobiliers", secteur = "Immobilier", ),
        Metier(nom = "Gestionnaire d’actifs immobiliers", secteur = "Immobilier", ),
        Metier(nom = "Gestionnaire de galerie marchande", secteur = "Immobilier", ),
        Metier(nom = "Gestionnaire social(e)", secteur = "Immobilier", ),
        Metier(nom = "Juriste immobilier", secteur = "Immobilier", ),
        Metier(nom = "Négociateur(trice) immobilier", secteur = "Immobilier", ),
        Metier(nom = "Syndic de copropriété", secteur = "Immobilier", ),




//        # Immobilier
        Metier(nom = "Administrateur(trice) de biens", secteur = "Immobilier", ),
        Metier(nom = "Agent(e) immobilier(ière)", secteur = "Immobilier", ),
        Metier(nom = "Chasseur(euse) de biens immobiliers", secteur = "Immobilier", ),
        Metier(nom = "Gestionnaire d’actifs immobiliers", secteur = "Immobilier", ),
        Metier(nom = "Gestionnaire de galerie marchande", secteur = "Immobilier", ),
        Metier(nom = "Gestionnaire social(e)", secteur = "Immobilier", ),
        Metier(nom = "Juriste immobilier", secteur = "Immobilier", ),
        Metier(nom = "Négociateur(trice) immobilier", secteur = "Immobilier", ),
        Metier(nom = "Syndic de copropriété", secteur = "Immobilier", ),


//        # Industrie
        Metier(nom = "Acheteur(euse) industriel(le)", secteur = "Industrie", ),
        Metier(nom = "Automaticien(ne)", secteur = "Industrie", ),
        Metier(nom = "Conducteur(trice) de ligne de production", secteur = "Industrie", ),
        Metier(nom = "Conducteur(trice) production automatisée", secteur = "Industrie", ),
        Metier(nom = "Designer d'environnement", secteur = "Industrie", ),
        Metier(nom = "Dessinateur(rice) industriel(le)", secteur = "Industrie", ),
        Metier(nom = "Étalagiste", secteur = "Industrie", ),
        Metier(nom = "Ingénieur(e) calcul", secteur = "Industrie", ),
        Metier(nom = "Ingénieur(e) commercial(e)", secteur = "Industrie", ),
        Metier(nom = "Ingénieur(e) de production", secteur = "Industrie", ),
        Metier(nom = "Ingénieur(e) éco-conception", secteur = "Industrie", ),
        Metier(nom = "Ingénieur(e) en cobotique", secteur = "Industrie", ),
        Metier(nom = "Ingénieur(e) en fabrication additive", secteur = "Industrie", ),
        Metier(nom = "Ingénieur(e) méthodes", secteur = "Industrie", ),
        Metier(nom = "Ingénieur(e) R&D", secteur = "Industrie", ),
        Metier(nom = "Manager de maintenance multiservices", secteur = "Industrie", ),
        Metier(nom = "Mécanicien(ne) outilleur(euse)", secteur = "Industrie", ),
        Metier(nom = "Opérateur d’ouvrages chaudronnés : le témoignage de Damien, apprenti", secteur = "Industrie", ),
        Metier(nom = "Opératrice sur machine : le témoignage de Vanessa, apprentie", secteur = "Industrie", ),
        Metier(nom = "Pilote de drone", secteur = "Industrie", ),
        Metier(nom = "Responsable d’ordonnancement", secteur = "Industrie", ),
        Metier(nom = "Responsable QSE (qualité, sécurité, environnement)", secteur = "Industrie", ),
        Metier(nom = "Tailleur(euse) de pierre", secteur = "Industrie", ),
        Metier(nom = "Technicien qualité : le témoignage de Frédéric, apprenti", secteur = "Industrie", ),
        Metier(nom = "Technicien(ne) contrôle", secteur = "Industrie", ),
        Metier(nom = "Technicien(ne) contrôle non destructif", secteur = "Industrie", ),
        Metier(nom = "Technicien(ne) d’essais", secteur = "Industrie", ),
        Metier(nom = "Technicien(ne) d’études", secteur = "Industrie", ),
        Metier(nom = "Technicien(ne) de maintenance", secteur = "Industrie", ),
        Metier(nom = "Technicien(ne) de maintenance multiservices", secteur = "Industrie", ),
        Metier(nom = "Technicien(ne) électronicien(ne)", secteur = "Industrie", ),
        Metier(nom = "Technicien(ne) inspection conformité", secteur = "Industrie", ),
        Metier(nom = "Technicien(ne) jumeau numérique", secteur = "Industrie", ),
        Metier(nom = "Technicien(ne) modeleur(euse)", secteur = "Industrie", ),
        Metier(nom = "Technicien(ne) photovoltaïque", secteur = "Industrie", ),
        Metier(nom = "Vendeur en électronique : le témoignage de Nicolas, apprenti", secteur = "Industrie", ),



//        # Informatique, télécoms, Web
        Metier(nom = "Administrateur(trice) de base de données", secteur = "Informatique, télécoms, Web", ),
        Metier(nom = "Architecte de système d’information", secteur = "Informatique, télécoms, Web", ),
        Metier(nom = "Business analyst", secteur = "Informatique, télécoms, Web", ),
        Metier(nom = "Chef(fe) de projet informatique", secteur = "Informatique, télécoms, Web", ),
        Metier(nom = "Community manager", secteur = "Informatique, télécoms, Web", ),
        Metier(nom = "Concepteur(trice) Web", secteur = "Informatique, télécoms, Web", ),
        Metier(nom = "Consultant(e) en référencement de sites Web (consultant MO/SEO)", secteur = "Informatique, télécoms, Web", ),
        Metier(nom = "Data scientist", secteur = "Informatique, télécoms, Web", ),
        Metier(nom = "Développeur(euse) front end", secteur = "Informatique, télécoms, Web", ),
        Metier(nom = "Développeur(se)", secteur = "Informatique, télécoms, Web", ),
        Metier(nom = "Digital learning manager", secteur = "Informatique, télécoms, Web", ),
        Metier(nom = "Graphiste web", secteur = "Informatique, télécoms, Web", ),
        Metier(nom = "Hotliner", secteur = "Informatique, télécoms, Web", ),
        Metier(nom = "Ingénieur(e) cybersécurité", secteur = "Informatique, télécoms, Web", ),
        Metier(nom = "Ingénieur(e) IoT", secteur = "Informatique, télécoms, Web", ),
        Metier(nom = "Ingénieur(e) réseaux", secteur = "Informatique, télécoms, Web", ),
        Metier(nom = "Ingénieur(e) sécurité", secteur = "Informatique, télécoms, Web", ),
        Metier(nom = "Ingénieur(e) technico-commercial(e)", secteur = "Informatique, télécoms, Web", ),
        Metier(nom = "Installateur télécoms : le témoignage de Christophe, apprenti", secteur = "Informatique, télécoms, Web", ),
        Metier(nom = "Motion designer", secteur = "Informatique, télécoms, Web", ),
        Metier(nom = "Référenceur(euse)", secteur = "Informatique, télécoms, Web", ),
        Metier(nom = "Technicien(ne) en informatique industrielle", secteur = "Informatique, télécoms, Web", ),
        Metier(nom = "Technicien(ne) en télécommunications", secteur = "Informatique, télécoms, Web", ),
        Metier(nom = "Traffic manager", secteur = "Informatique, télécoms, Web", ),
        Metier(nom = "UX designer", secteur = "Informatique, télécoms, Web", ),
        Metier(nom = "Vendeur(euse) en micro-informatique", secteur = "Informatique, télécoms, Web", ),
        Metier(nom = "Webdesigner", secteur = "Informatique, télécoms, Web", ),
        Metier(nom = "Webmaster", secteur = "Informatique, télécoms, Web", ),



//        # jeux vidéo
        Metier(nom = "Business developer jeu vidéo et esport", secteur = "Jeu vidéo", ),
        Metier(nom = "Chef(fe) de produit jeu vidéo et esport", secteur = "Jeu vidéo", ),
        Metier(nom = "Chef(fe) de projet esport", secteur = "Jeu vidéo", ),
        Metier(nom = "Chef(fe) de projet jeu vidéo", secteur = "Jeu vidéo", ),
        Metier(nom = "Data analyst jeu vidéo et esport", secteur = "Jeu vidéo", ),
        Metier(nom = "Graphiste 2D/3D", secteur = "Jeu vidéo", ),
        Metier(nom = "Programmeur gameplay", secteur = "Jeu vidéo", ),
        Metier(nom = "Programmeur(euse) de jeux vidéo", secteur = "Jeu vidéo", ),
        Metier(nom = "Programmeur(euse) mobile jeu vidéo", secteur = "Jeu vidéo", ),
        Metier(nom = "Technical artist", secteur = "Jeu vidéo", ),




//        # Journalisme
        Metier(nom = "Agencier(ière)", secteur = "Journalisme", ),
        Metier(nom = "Journaliste radio", secteur = "Journalisme", ),
        Metier(nom = "Journaliste reporter d’images", secteur = "Journalisme", ),
        Metier(nom = "Journaliste Web", secteur = "Journalisme", ),
        Metier(nom = "Maquettiste de presse", secteur = "Journalisme", ),
        Metier(nom = "Photographe de presse", secteur = "Journalisme", ),
        Metier(nom = "Rédacteur(rice) en presse écrite", secteur = "Journalisme", ),
        Metier(nom = "Rédacteur(trice) en chef", secteur = "Journalisme", ),
        Metier(nom = "Secrétaire de rédaction", secteur = "Journalisme", ),



//        # Langues
        Metier(nom = "Interprète", secteur = "Langues", ),
        Metier(nom = "Professeur(e) de FLE (Français Langue Étrangère)", secteur = "Langues", ),
        Metier(nom = "Professeur(e) de langue vivante étrangère", secteur = "Langues", ),
        Metier(nom = "Terminologue", secteur = "Langues", ),
        Metier(nom = "Traducteur(rice) technique", secteur = "Langues", ),
        Metier(nom = "Traducteur(trice) audiovisuel(le)", secteur = "Langues", ),
        Metier(nom = "Traducteur(trice) dans la fonction publique", secteur = "Langues", ),
        Metier(nom = "Traducteur(trice) expert(e)", secteur = "Langues", ),
        Metier(nom = "Traducteur(trice) littéraire", secteur = "Langues", ),

//        # Luxe
        Metier(nom = "Bijoutier(ière) - joaillier(ière)", secteur = "Luxe", ),
        Metier(nom = "Créateur(trice) de parfum", secteur = "Luxe", ),



//        # Marketing, publicité
        Metier(nom = "Chargé(e) d’études marketing", secteur = "Marketing, publicité ", ),
        Metier(nom = "Chargé(e) de marketing direct", secteur = "Marketing, publicité ", ),
        Metier(nom = "Chargé(e) de promotion", secteur = "Marketing, publicité ", ),
        Metier(nom = "Chef de produit", secteur = "Marketing, publicité ", ),
        Metier(nom = "Chef(fe) de publicité", secteur = "Marketing, publicité ", ),
        Metier(nom = "Concepteur(trice)-rédacteur(trice)", secteur = "Marketing, publicité ", ),
        Metier(nom = "Directeur(trice) artistique", secteur = "Marketing, publicité ", ),
        Metier(nom = "Directeur(trice) de clientèle", secteur = "Marketing, publicité ", ),
        Metier(nom = "Directeur(trice) de création", secteur = "Marketing, publicité ", ),
        Metier(nom = "Directeur(trice) du marketing", secteur = "Marketing, publicité ", ),
        Metier(nom = "Maquettiste de pub", secteur = "Marketing, publicité ", ),
        Metier(nom = "Médiaplanneur(euse)", secteur = "Marketing, publicité ", ),
        Metier(nom = "Responsable base de données", secteur = "Marketing, publicité ", ),
        Metier(nom = "Responsable du merchandising", secteur = "Marketing, publicité ", ),
        Metier(nom = "Roughman(woman)", secteur = "Marketing, publicité ", ),



//        # Médical
        Metier(nom = "Anesthésiste-réanimateur(trice)", secteur = "Médical", ),
        Metier(nom = "Chirurgien(ne)", secteur = "Médical", ),
        Metier(nom = "Chirurgien(ne)-dentiste", secteur = "Médical", ),
        Metier(nom = "Dermatologue", secteur = "Médical", ),
        Metier(nom = "Médecin de l’Éducation nationale", secteur = "Médical", ),
        Metier(nom = "Médecin du sport", secteur = "Médical", ),
        Metier(nom = "Médecin du travail", secteur = "Médical", ),
        Metier(nom = "Médecin généraliste", secteur = "Médical", ),
        Metier(nom = "Médecin régulateur(trice)", secteur = "Médical", ),
        Metier(nom = "Médecin urgentiste", secteur = "Médical", ),
        Metier(nom = "Ophtalmologue", secteur = "Médical", ),
        Metier(nom = "Oto-rhino-laryngologiste", secteur = "Médical", ),
        Metier(nom = "Pédiatre", secteur = "Médical", ),
        Metier(nom = "Psychiatre", secteur = "Médical", ),
        Metier(nom = "Sage-femme", secteur = "Médical", ),



//        # Mode-textile
        Metier(nom = "Acheteur(euse) habillement", secteur = "Mode-textile ", ),
        Metier(nom = "Chef(fe) de produit", secteur = "Mode-textile ", ),
        Metier(nom = "Designer(euse) textile", secteur = "Mode-textile ", ),
        Metier(nom = "Ingénieur(e) textile", secteur = "Mode-textile ", ),
        Metier(nom = "Modéliste", secteur = "Mode-textile ", ),
        Metier(nom = "Styliste", secteur = "Mode-textile ", ),
        Metier(nom = "Technicien(ne) de fabrication", secteur = "Mode-textile ", ),



//        # Paramédical
        Metier(nom = "Aide-soignant(e)", secteur = "Paramédical", ),
        Metier(nom = "Ambulancier(ière)", secteur = "Paramédical", ),
        Metier(nom = "Assistant(e) dentaire", secteur = "Paramédical", ),
        Metier(nom = "Audioprothésiste", secteur = "Paramédical", ),
        Metier(nom = "Auxiliaire de puériculture", secteur = "Paramédical", ),
        Metier(nom = "Diététicien(ne)", secteur = "Paramédical", ),
        Metier(nom = "Ergothérapeute", secteur = "Paramédical", ),
        Metier(nom = "Infirmier(ère) puériculteur(trice)", secteur = "Paramédical", ),
        Metier(nom = "Infirmier(ière)", secteur = "Paramédical", ),
        Metier(nom = "Manipulateur(trice) en électroradiologie médicale", secteur = "Paramédical", ),
        Metier(nom = "Masseur(euse)-kinésithérapeute", secteur = "Paramédical", ),
        Metier(nom = "Opticien(ne)-lunetier(ière)", secteur = "Paramédical", ),
        Metier(nom = "Orthophoniste", secteur = "Paramédical", ),
        Metier(nom = "Orthoptiste", secteur = "Paramédical", ),
        Metier(nom = "Ostéopathe, chiropracteur(trice)", secteur = "Paramédical", ),
        Metier(nom = "Pédicure-podologue", secteur = "Paramédical", ),
        Metier(nom = "Podo-orthésiste", secteur = "Paramédical", ),
        Metier(nom = "Prothésiste dentaire", secteur = "Paramédical", ),
        Metier(nom = "Psychomotricien(ne)", secteur = "Paramédical", ),
        Metier(nom = "Secrétaire médical(e)", secteur = "Paramédical", ),
        Metier(nom = "Technicien(ne) de laboratoire (paramédical)", secteur = "Paramédical", ),
        Metier(nom = "Visiteur(euse) médical(e)", secteur = "Paramédical", ),


//        # Propreté et services associés
        Metier(nom = "Agent(e) d’entretien et de rénovation", secteur = "Propreté et services associés", ),
        Metier(nom = "Agent(e) machiniste", secteur = "Propreté et services associés", ),
        Metier(nom = "Chef(fe) d’équipe", secteur = "Propreté et services associés", ),
        Metier(nom = "Laveur(euse) de vitres en hauteur", secteur = "Propreté et services associés", ),
        Metier(nom = "Responsable de secteur", secteur = "Propreté et services associés", ),
        Metier(nom = "Technicien(ne) de traitement des déchets", secteur = "Propreté et services associés", ),



//        # Psychologie
        Metier(nom = "Psychologue clinicien(ne)", secteur = "Psychologie", ),
        Metier(nom = "Psychologue de l'Éducation nationale", secteur = "Psychologie", ),
        Metier(nom = "Psychologue du travail", secteur = "Psychologie", ),
        Metier(nom = "Psychologue scolaire", secteur = "Psychologie", ),
        Metier(nom = "Psychothérapeute", secteur = "Psychologie", ),


//        # Ressources humaines
        Metier(nom = "Assistant(e) des ressources humaines", secteur = "Ressources humaines", ),
        Metier(nom = "Chargé(e) du recrutement", secteur = "Ressources humaines", ),
        Metier(nom = "Directeur(trice) des ressources humaines", secteur = "Ressources humaines", ),
        Metier(nom = "Gestionnaire de carrières", secteur = "Ressources humaines", ),
        Metier(nom = "Juriste social(e)", secteur = "Ressources humaines", ),
        Metier(nom = "Outplacer", secteur = "Ressources humaines", ),



//        # Sciences humaines et sociales
        Metier(nom = "Cartographe", secteur = "Sciences humaines et sociales", ),
        Metier(nom = "Démographe", secteur = "Sciences humaines et sociales", ),
        Metier(nom = "Géographe", secteur = "Sciences humaines et sociales", ),
        Metier(nom = "Historien(ne)", secteur = "Sciences humaines et sociales", ),
        Metier(nom = "Sociologue", secteur = "Sciences humaines et sociales", ),
        Metier(nom = "Urbaniste", secteur = "Sciences humaines et sociales", ),





//        # Secrétariat
        Metier(nom = "Assistant(e) commercial(e)", secteur = "Secrétariat", ),
        Metier(nom = "Assistant(e) de manager", secteur = "Secrétariat", ),
        Metier(nom = "Assistant(e) trilingue", secteur = "Secrétariat", ),
        Metier(nom = "Secrétaire administratif(ve)", secteur = "Secrétariat", ),
        Metier(nom = "Secrétaire comptable", secteur = "Secrétariat", ),
        Metier(nom = "Secrétaire de mairie", secteur = "Secrétariat", ),
        Metier(nom = "Secrétaire juridique", secteur = "Secrétariat", ),
        Metier(nom = "Secrétaire médical(e)", secteur = "Secrétariat", ),




//        # Social
        Metier(nom = "Animateur(trice) socioculturel(le)", secteur = "Social", ),
        Metier(nom = "Assistant(e) de service social", secteur = "Social", ),
        Metier(nom = "Conseiller(ère) en économie sociale et familiale", secteur = "Social", ),
        Metier(nom = "Conseiller(ère) pénitentiaire d’insertion et de probation", secteur = "Social", ),
        Metier(nom = "Directeur(trice) de structure sociale", secteur = "Social", ),
        Metier(nom = "Éducateur(trice) de jeunes enfants", secteur = "Social", ),
        Metier(nom = "Éducateur(trice) de la protection judiciaire de la jeunesse", secteur = "Social", ),
//        Metier(nom = "Éducateur(trice) spécialisé(e)", secteur = "Social", ),
//        Metier(nom = "Éducateur(trice) technique spécialisé(e)", secteur = "Social", ),


//        # Spectacle - Métiers de la scène
        Metier(nom = "Chanteur(euse)", secteur = "Spectacle - Métiers de la scène ", ),
        Metier(nom = "Comédien(ne)", secteur = "Spectacle - Métiers de la scène ", ),
        Metier(nom = "Costumier(ière)", secteur = "Spectacle - Métiers de la scène ", ),
        Metier(nom = "Danseur(euse)", secteur = "Spectacle - Métiers de la scène ", ),
        Metier(nom = "Décorateur(trice)-scénographe", secteur = "Spectacle - Métiers de la scène ", ),
        Metier(nom = "Musicien(ne)", secteur = "Spectacle - Métiers de la scène ", ),


//        # Sport
        Metier(nom = "Directeur(trice) d'équipement sportif", secteur = "Sport", ),
        Metier(nom = "Entraîneur(e)", secteur = "Sport", ),
        Metier(nom = "Journaliste sportif(ve)", secteur = "Sport", ),
        Metier(nom = "Maître-nageur(euse) sauveteur(euse)", secteur = "Sport", ),
        Metier(nom = "Moniteur(trice) de sport", secteur = "Sport", ),
        Metier(nom = "Professeur(e) d’EPS (éducation physique et sportive)", secteur = "Sport", ),
        Metier(nom = "Professeur(e) de sport", secteur = "Sport", ),
        Metier(nom = "Vendeur(euse) d’articles de sport", secteur = "Sport", ),




//        # Transport-Logistique
        Metier(nom = "Attaché commercial : le témoignage de Jean, apprenti", secteur = "Transport-Logistique ", ),
        Metier(nom = "Chef(fe) d’agence de transport", secteur = "Transport-Logistique ", ),
        Metier(nom = "Conducteur(trice) de train de marchandises", secteur = "Transport-Logistique ", ),
        Metier(nom = "Conducteur(trice) routier(ière)", secteur = "Transport-Logistique ", ),
        Metier(nom = "Contrôleuse de train : le témoignage de Ilham, apprentie", secteur = "Transport-Logistique ", ),
        Metier(nom = "Gestionnaire de stocks", secteur = "Transport-Logistique ", ),
        Metier(nom = "Officier(ière) de la marine marchande", secteur = "Transport-Logistique ", ),
        Metier(nom = "Responsable d’entrepôt", secteur = "Transport-Logistique ", ),
        Metier(nom = "Responsable d’exploitation", secteur = "Transport-Logistique ", ),
        Metier(nom = "Responsable logistique", secteur = "Transport-Logistique ", ),



//        # Tourisme
        Metier(nom = "Agent(e) de comptoir (vendeur(euse)-conseil)", secteur = "Tourisme", ),
        Metier(nom = "Animateur(trice)", secteur = "Tourisme", ),
        Metier(nom = "Chef(fe) de produit touristique", secteur = "Tourisme", ),
        Metier(nom = "Guide-accompagnateur(trice)", secteur = "Tourisme", ),
        Metier(nom = "Guide-conférencier(ière)", secteur = "Tourisme", ),
        Metier(nom = "Hôtesse d’accueil : le témoignage de Kathia, apprentie", secteur = "Tourisme", ),

    )
}

