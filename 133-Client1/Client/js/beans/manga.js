/*
 * Bean "Manga".
 *
 * @author de Sousa Raphael
 * @project Mangatek module 151
 * @version 1.0 / 27.02.2023
 */

var Manga = function() {
}

/**
 * Setter pour le pk du manga
 * @param String nom
 * @returns {undefined}
 */
Manga.prototype.setPk = function(pk) {
  this.pk = pk;
}

/**
 * Setter pour le nom du manga
 * @param String nom
 * @returns {undefined}
 */
Manga.prototype.setNomDuManga = function(nomDuManga) {
  this.nomDuManga = nomDuManga;
}

/**
 * Setter pour le nom du tome
 * @param String nom
 * @returns {undefined}
 */
Manga.prototype.setNomDuTome = function(nom) {
  this.nomTome = nom;
}

/**
 * Setter pour le numero du tome du manga
 * @param String num
 * @returns {undefined}
 */
Manga.prototype.setNumeroDuTome = function(num) {
  this.num = num;
}

/**
 * Setter pour l'image du manga
 * @param String img
 * @returns {undefined}
 */
Manga.prototype.setImage = function(img) {
  this.image = img;
}

/**
 * getter de la pk du manga
 * @returns String
 */
Manga.prototype.getPk = function () {
  return this.pk;
}

/**
 * getter pour l'image du tome
 * @returns String
 */
Manga.prototype.getImg = function () {
  return this.image;
}

/**
 * getter pour le nom du tome
 * @returns String
 */
Manga.prototype.getNomDuTome = function (){
  return this.nomTome;
}

/**
 * getter pour le nom du manga
 * @returns String
 */
Manga.prototype.getNomDuManga = function (){
  return this.nomDuManga;
}

/**
 * getter pour le numero du tome
 * @returns String
 */
Manga.prototype.getNumeroDuTome = function (){
  return this.num;
}

/**
 * Retourne le manga en format texte
 * @returns Le manga en format texte
 */
Manga.prototype.toString = function () {
  return this.nom;
}


