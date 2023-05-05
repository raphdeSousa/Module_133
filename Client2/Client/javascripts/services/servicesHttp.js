/*
 * Couche de services HTTP (worker).
 *
 * @author de Sousa Raphael
 * @version 1.0 / 13-SEP-2013
 */

var BASE_URL = "http://gfellerm01.emf-informatique.ch/javaAPI-Gateway/GatewayServlet";



class Service{

  addManga(NomDuManga, NomDuTome,NumeroDuTome,Image, successCallback, errorCallback) {
    let param = "action=apiAjout&nomDuManga=" + NomDuManga + "&nomDuTome=" + NomDuTome + "&numDuTome=" +NumeroDuTome+ "&image="+Image;
    $.ajax({
        type: "POST",
        datatype: 'json',
        data: param,
        url: BASE_URL,
        success: successCallback,
        error: errorCallback
    });
  }
 
}
