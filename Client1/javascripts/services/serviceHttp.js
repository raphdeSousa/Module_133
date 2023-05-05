/*
 * Couche de services HTTP (worker).
 *
 * @author de Malcolm Gfeller
 * @version 1.0 / 05.05.2023
 */

var BASE_URL = "http://gfellerm01.emf-informatique.ch/javaAPI-Gateway/GatewayServlet";



class Service{

  login(){
	  
  }
  
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