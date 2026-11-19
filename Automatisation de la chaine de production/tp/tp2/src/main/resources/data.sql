INSERT INTO Utilisateur (Nom, Prenom, Telephone, Adresse, Code_postal, Ville, mdp, email) VALUES ('Duez', 'Jean', '1234567890', '123 Rue Principale', '31000', 'Toulouse', 'mdp1', 'exemple1@gmail.com');
INSERT INTO Utilisateur (Nom, Prenom, Telephone, Adresse, Code_postal, Ville, mdp, email) VALUES ('Bricout', 'Ulysse', '9876543210', '456 Avenue des Fleurs', '73000', 'Paris', 'mdp2', 'exemple2@gmail.com');
INSERT INTO Utilisateur (Nom, Prenom, Telephone, Adresse, Code_postal, Ville, mdp, email) VALUES ('Bricout2', 'Ulysse', '9876543210', '456 Avenue des Fleurs', '73000', 'Paris', 'mdp2', 'exemple3@gmail.com');

-- Insertion de données dans la table Offre
-- menage
INSERT INTO Offre (posteur_id, type, date_creneau, heure_debut, heure_fin, adresse, code_postal, ville, description, note, prix, surface, produits_menages_fournis, ramassage, animaux, linge) VALUES (1, 'Ménage', '2022-01-01', '10:00:00', '12:00:00', '123 Rue du Paradis', '75000', 'Paris', 'Service de ménage à domicile', 4, 30, 10, true, true, true, true);
INSERT INTO Offre (posteur_id, type, date_creneau, heure_debut, heure_fin, adresse, code_postal, ville, description, note, prix, surface, produits_menages_fournis, ramassage, animaux, linge) VALUES (2, 'Ménage', '2022-01-01', '10:00:00', '12:00:00', '123 Rue du Paradis', '75000', 'Paris', 'Service de ménage à domicile', 4, 30, 10, true, true, true, true);
-- assistance senior
INSERT INTO Offre (posteur_id, type, date_creneau, heure_debut, heure_fin, adresse, code_postal, ville, description, note, prix, age, medicaments, preparation_repas, sortie) VALUES (1, 'Assistance senior', '2022-03-03', '09:00:00', '15:00:00', '321 Chemin des Tulipes', '54000', 'Nancy', 'Aide à la personne âgée, préparation des repas et gestion des médicaments', 4.5, 50, 85, true, true, true);
INSERT INTO Offre (posteur_id, type, date_creneau, heure_debut, heure_fin, adresse, code_postal, ville, description, note, prix, age, medicaments, preparation_repas, sortie) VALUES (2, 'Assistance senior', '2022-03-03', '09:00:00', '15:00:00', '321 Chemin des Tulipes', '54000', 'Nancy', 'Aide à la personne âgée, préparation des repas et gestion des médicaments', 4.5, 50, 85, true, true, false);

-- babysitting
INSERT INTO Offre (posteur_id, type, date_creneau, heure_debut, heure_fin, adresse, code_postal, ville, description, note, prix, nb_enfants, age_du_plus_jeune, aide_au_devoir) VALUES (3, 'Babysitting', '2022-03-03', '09:00:00', '15:00:00', '321 Chemin des Tulipes', '54000', 'Nancy', 'Aide à la personne âgée, préparation des repas et gestion des médicaments', 4.5, 50, 10, 10, true);

-- jardinage
INSERT INTO Offre (posteur_id, type, date_creneau, heure_debut, heure_fin, adresse, code_postal, ville, description, note, prix, surface, taille_haie, materiel_jardinage_fourni, tonte) VALUES (3, 'Jardinage', '2022-03-03', '09:00:00', '15:00:00', '321 Chemin des Tulipes', '54000', 'Nancy', 'Aide à la personne âgée, préparation des repas et gestion des médicaments', 4.5, 50, 100, true, true, false);


-- Attribution des offres aux utilisateurs
INSERT INTO utilisateur_offre (offre_id, utilisateur_id) VALUES (1, 1);
-- INSERT INTO utilisateur_offre (offre_id, utilisateur_id) VALUES (1, 2);
INSERT INTO utilisateur_offre (offre_id, utilisateur_id) VALUES (3, 1);
INSERT INTO utilisateur_offre (offre_id, utilisateur_id) VALUES (4, 1);

INSERT INTO utilisateur_offre (offre_id, utilisateur_id) VALUES (2, 2);

-- Attribution des parrains aux utilisateurs
INSERT INTO utilisateur_parrains (parrains_id, utilisateur_id) VALUES (3, 2);
INSERT INTO utilisateur_parrains (parrains_id, utilisateur_id) VALUES (1, 2);

-- Attribution des filleuls aux utilisateurs
INSERT INTO utilisateur_filleuls (filleuls_id, utilisateur_id) VALUES (1, 2);
INSERT INTO utilisateur_filleuls (filleuls_id, utilisateur_id) VALUES (3, 2);
