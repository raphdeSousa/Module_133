$().ready(function () {
    wrk = new Service();
    window.ctrl = new IndexCtrl();
});


class IndexCtrl {
    /**
     * Constructor of IndexCtrl.
     */
    constructor() {
    }

    login() {
        var loginName = $("#username").val();
        var password = $("#password").val();
        wrk.login(loginName, password, this.loginSuccessCallBack, this.loginErrorCallBack);
    }

    /**
     * Adds an article with the filled fields. If any of the field isn't filled, we show an alert.
     */
    addManga() {
        var nomDuManga = $("#articleName").val();
        var nomDuTome = $("#imageArticle").val();
        var numDuTome = $("#price").val();
        var image = $("#listType option:selected").val();
        if (nomDuManga === "" || image === "" || nomDuTome === "" || numDuTome === "") {
            alert("Please fill all the fields");
            return;
        } else {
            wrk.addManga(nomDuManga, nomDuTome, numDuTome, image, this.addSuccessCallBack, this.addErrorCallBack);
            ctrl.emptyEverything();
        }

    }

    /**
    * Adds an article with the filled fields. If any of the field isn't filled, we show an alert.
     */
    addFavoris() {
        var FK_user = $("#articleName").val();
        var FK_manga = $("#imageArticle").val();
        if (name === "" || image === "") {
            alert("Please fill all the fields");
            return;
        } else {
            wrk.getMangaFavoris(FK_manga, FK_user,this.addSuccessCallBack, this.addErrorCallBack);
            ctrl.emptyEverything();
        }
    }
   
    loginSuccessCallBack() {
        wrk.isAdmin(ctrl.isAdminSuccessCallBack, ctrl.isAdminErrorCallBack);
        return;
    }
  
    /**
     * Alert with the error message
     * @param msg The message to show.
     */
    afficherErreurHttp(msg) {
        alert(msg);
    }

    /**
     * Load the connected main page with the username received by the request.
     * @param data The json form of the response
     */
    successCheck(data) {
        if (data.result == true) {
            ctrl.loadAccueilLogin(data.name);
        } else {
            ctrl.loadAccueil();
        }
    }

    /**
     * Check if the login is successful. Load the connected main page, show "NOT WORKING" otherwise.
     * @param data The json form of the response
     */
    loginSuccess(data) {
        if (data.result != "false") {
            $("#result").text("")
            var usr = $("#username").val()
            ctrl.loadAccueilLogin(usr);
        } else {
            $("#result").text("NOT WORKING")
        }
    }

    /**
     * Call the wrk to log in on the server
     */
    loginUser() {
        wrk.login($("#username").val(), $("#password").val(), this.loginSuccess)
    }

    /**
     * Function call when the disconnection is a success.
     */
    successDisconnect() {
        ctrl.loadAccueil();
    }

    /**
     * Load the login page.
     */
    loadLogin() {
        wrk.loadView("login");
    }


}