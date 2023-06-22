$(document).ready(function() {
    // Fonction appelée lors du clic sur le bouton de connexion
    $('#connecter').click(function() {
        var login = $('#txtLogin').val();
        var password = $('#txtPassword').val();

        // Envoie une requête POST à l'API Gateway avec les paramètres de connexion
        $.ajax({
            url: 'http://gfellerm01.emf-informatique.ch/javaAPI-Gateway/GatewayServlet?action=apiLogin',
            type: 'POST',
            crossDomain: true,
            xhrFields: {
                withCredentials: true
            },
            data: {
                login: login,
                password: password
            },
            success: function(data) {
                // Affiche la réponse de l'API Gateway
                console.log(data);
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log(textStatus);
                console.log(errorThrown);
            }
        });
    });
});