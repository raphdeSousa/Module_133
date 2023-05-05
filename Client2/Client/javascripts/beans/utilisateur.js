/**
* Bean "utilisateur".
*
* @author de Sousa Raphael
* @project Mangatek module 151
* @version 1.0 / 27.02.2023
*/

/**
 * Bean utilisateur
 * @returns {Continent}
 */
var utilisateur = function() {
};

/**
 * Setter pour le prenom de l'utilisateur
 * @param String nom
 */
utilisateur.prototype.setusername = function(username) {
  this.username = username;
};

/**
 * Setter pour le mdp de l'utilisateur
 * @param String nom
 */
utilisateur.prototype.setMdp = function(mdp){
  this.mdp = mdp;
};
