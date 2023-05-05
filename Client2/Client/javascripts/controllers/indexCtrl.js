$().ready(function () {
  wrk = new Service();
  window.ctrl = new IndexCtrl();
  wrk.centraliserErreurHttp(ctrl.afficherErreurHttp);  
});


class IndexCtrl {
  /**
   * Constructor of IndexCtrl.
   */
  constructor() {
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
   * Sends a request to get all articles in the player inventory
   */
  getInventory() {
      wrk.getInventory(this.invSucess);
  }

  /**
   * Create Element for each article found in the database and append them in a div.
   * @param data The json form of the response
   */
  async articlesSucess(data) {

      $("#content").empty();
      var types = await wrk.getTypes();
      var dict = {}
      types.forEach(obj => {
          dict[obj.pk_type] = obj.name;
      })

      data.forEach(obj => {

          var div = document.createElement("div");
          div.className = "item";

          var name = document.createElement("h3");
          name.textContent = obj.name;
          div.appendChild(name);

          var img = document.createElement("img");
          img.src = obj.image;
          img.width = 280;
          img.alt = obj.pk_article;
          div.appendChild(img);

          var price = document.createElement("p");
          price.textContent = obj.price + " Gold";
          div.appendChild(price);

          var fk = document.createElement("p");
          fk.textContent = dict[obj.fk_type];
          div.appendChild(fk);

          var buy = document.createElement("button");
          buy.innerText = "Purchase";
          buy.onclick = () => {
              ctrl.buy(obj.pk_article);
          }
          div.appendChild(buy);

          $("#content").append(div);
      })

  }

  /**
   * Create Element for each article found in the inventory of the user and append them in a div.
   * @param data The json form of the response
   */
  async invSucess(data) {

      $("#content").empty();
      var types = await wrk.getTypes();
      var dict = {}
      types.forEach(obj => {
          dict[obj.pk_type] = obj.name;
      })

      data.forEach(obj => {

          var div = document.createElement("div");
          div.className = "item";

          var name = document.createElement("h3");
          name.textContent = obj.name;
          div.appendChild(name);

          var img = document.createElement("img");
          img.src = obj.image;
          img.width = 280;
          img.alt = obj.pk_article;
          div.appendChild(img);

          var price = document.createElement("p");
          price.textContent = obj.price + " Gold";
          div.appendChild(price);

          var fk = document.createElement("p");
          fk.textContent = dict[obj.fk_type];
          div.appendChild(fk);

          $("#content").append(div);
      })

  }

  /**
   * Add the article to the inventory of the user
   * @param pk the pk of the article
   */
  buy(pk) {
      wrk.buy(pk, this.buySuccess);
  }

  /**
   * Callback from the buy request. Shows "Thanks for the purchase"
   */
  buySuccess() {
      alert("Thanks for the purchase");
  }

  /**
   * Check the response of the register user request. Loads the main page if valid, show "NOT WORKING" otherwise.
   * @param data The json form of the response
   */
  registerSuccess(data) {
      if (data.result == true) {
          $("#Rresult").text("")
          ctrl.loadLogin();
      } else {
          $("#Rresult").text("NOT WORKING")
      }
  }

  /**
   * Check if the login is successful. Load the connected main page, show "NOT WORKING" otherwise.
   * @param data The json form of the response
   */
  loginSuccess(data) {
      if (data.result != "false") {
          $("#Lresult").text("")
          var usr = $("#Lusername").val()
          ctrl.loadAccueilLogin(usr);
      } else {
          $("#Lresult").text("NOT WORKING")
      }
  }


  /**
   * Get the username and password and call the Wrk to make register user request. Verify both password field are the same before making the call.
   */
  registerUser() {
      if ($("#Rpasswordcheck").val() === $("#Rpassword").val() && $("#Rusername").val()) {
          wrk.registerUser($("#Rusername").val(), $("#Rpassword").val(), this.registerSuccess);
      } else {
          $("#Rresult").text("Password not matching or empty username");
      }
  }

  /**
   * Call the wrk to log in on the server
   */
  loginUser() {
      wrk.login($("#Lusername").val(), $("#Lpassword").val(), this.loginSuccess)
  }

  /**
   * Function used by a button in the html to disconnect the session.
   */
  disconnect() {
      wrk.disconnect(this.successDisconnect);
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

  /**
   * Load the connected main page and show the current user.
   * @param usr The user to show. The one who is connected.
   */
  loadAccueilLogin(usr) {
      wrk.loadView("accueilLogin", () => document.getElementById("status").innerText = "User: " + usr);
      wrk.getArticles(this.articlesSucess);

  }

  /**
   * Load the basic main page.
   */
  loadAccueil() {
      wrk.loadView("accueil")
      wrk.getArticles(this.articlesSucess);

  }

  /**
   * Load the register page.
   */
  loadRegister() {
      wrk.loadView("register");
  }

}