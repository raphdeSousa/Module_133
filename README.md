# Module_133
site de manga
![](/schéma.png)

API-Gateway :

Login (POST)
https://gfellerm01.emf-informatique.ch/javaAPI-Gateway/GatewayServlet?action=apiLogin
username : malcolm ou raphael
password : Pa$$w0rd

apiAjoutManga (POST)
https://gfellerm01.emf-informatique.ch/javaAPI-Gateway/GatewayServlet?action=apiAjoutManga
nomDuManga : testmanga
nomDuTome : testtome
numDuTome : 555
image : testimage

apiAjoutFavoris (POST)
https://gfellerm01.emf-informatique.ch/javaAPI-Gateway/GatewayServlet?action=apiAjoutFavoris
fkUser : 1
fkManga : 12

apiGetManga (GET)
https://gfellerm01.emf-informatique.ch/javaAPI-Gateway/GatewayServlet?action=apiAjoutFavoris
