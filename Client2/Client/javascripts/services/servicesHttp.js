/*
 * Couche de services HTTP .
 *
 * @author de Sousa Raphael
 * @version 1.0 / 05.05.2023
 */

var URL = "http://gfellerm01.emf-informatique.ch/javaAPI-Gateway/GatewayServlet";



class Service {

  constructor() {

  }

  /**
   * Login function, if the login is successful, we call loginSuccessCallBack, else we call loginErrorCallBack.
   * @param login login name
   * @param password password
   * @param successCallback success callback
   * @param errorCallBack error callback
   */
  login(login, password, successCallback, errorCallBack) {
    $.ajax({
      type: "POST",
      dataType: "json",
      data: "action=apiLogin&username=" + login + "&password=" + password,
      url: URL,
      xhrFields: {
        withCredentials: true,
      },
      async: false,
      crossDomain: true,
      success: successCallback,
      error: errorCallBack
    });


  }

  /**
   * Get all the manga
   * @param successCallback success callback
   * @param errorCallBack error callback
   */
  getManga(successCallback, errorCallBack) {
    $.ajax({
      type: "GET",
      dataType: "json",
      data: "action=getManga",
      url: URL,
      xhrFields: {
        withCredentials: true
      },
      async: false,
      crossDomain: true,
      success: successCallback,
      error: errorCallBack
    });
  }

  /**
   * Get all the favoris
   * @param successCallback success callback
   * @param errorCallBack error callback
   */
  getMangaFavoris(successCallback, errorCallBack) {
    $.ajax({
      type: "GET",
      dataType: "json",
      data: "action=getFavoris",
      url: URL,
      xhrFields: {
        withCredentials: true
      },
      async: false,
      crossDomain: true,
      success: successCallback,
      error: errorCallBack
    });
  }

  /**
   * adds an manga
   * @param NomDuManga name of the manga
   * @param Image image of the tome
   * @param NumeroDuTome num of the tome
   * @param NomDuTome name of the tome
   * @param successCallback success callback
   * @param errorCallBack error callback
   */
  addManga(NomDuManga, NomDuTome, NumeroDuTome, Image, successCallback, errorCallback) {
    $.ajax({
      type: "POST",
      datatype: 'json',
      data: "action=apiAjoutManga&nomDuManga=" + NomDuManga + "&nomDuTome=" + NomDuTome + "&numDuTome=" + NumeroDuTome + "&image=" + Image,
      url: URL,
      xhrFields: {
        withCredentials: true
      },
      async: false,
      crossDomain: true,
      success: successCallback,
      error: errorCallback
    });
  }
  /**
   * adds an favoris
   * @param FK_manga fk of the manga
   * @param FK_user fk of the user
   * @param successCallback success callback
   * @param errorCallBack error callback
   */
  addFavoris(FK_manga, FK_user, successCallback, errorCallback) {
    $.ajax({
      type: "POST",
      datatype: 'json',
      data: "action=apiAjoutFavoris&fkManga=" + FK_manga + "&fkUser=" + FK_user,
      url: URL,
      xhrFields: {
        withCredentials: true
      },
      async: false,
      crossDomain: true,
      success: successCallback,
      error: errorCallback
    });
  }

  /**
   * Checks if there is a session
   * @param successCallback success callback
   * @param errorCallBack error callback
   */
  checkSession(successCallback, errorCallBack) {
    $.ajax({
      type: "POST",
      dataType: "json",
      data: "action=check",
      url: URL,
      xhrFields: {
        withCredentials: true
      },
      async: false,
      crossDomain: true,
      success: successCallback,
      error: errorCallBack
    });
  }

  /**
   * disconnects the user
   * @param successCallback success callback
   * @param errorCallBack error callback
   */
  disconnect(successCallback, errorCallBack) {
    $.ajax({
      type: "POST",
      dataType: "json",
      data: "action=logout",
      url: URL,
      xhrFields: {
        withCredentials: true

      },
      async: false,
      crossDomain: true,
      success: successCallback,
      error: errorCallBack
    });

  }
  isAdmin(successCallback, errorCallBack) {
    $.ajax({
      type: "GET",
      dataType: "json",
      data: "action=admin",
      url: URL,
      xhrFields: {
        withCredentials: true

      },
      async: false,
      crossDomain: true,
      success: successCallback,
      error: errorCallBack
    });
  }
}