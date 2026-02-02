\#TrackTheRun - Pågående

TrackTheRun är en webbapplikation där användare kan registrera sina löprundor, se statistik gällande sin prestation och delta i community skapade utmaningar. Backend körs i Spring Boot, frontend i NextJS och containeriserat med docker. Postgres används som databas, och tanken är att längre fram kommer Keycloak och Caddy in i bilden för autentisering, proxy och HTTPS.



Utvecklingen sker lite då jag har tid, först fokus på kärnfunktionalitet, därefter andra delar som gamification, dashboards och kanske en AI driven löp coach.



\#Kärnfunktioner

* Logga löprundor (Distans, Tid, Starttid, ansträgningsnivå etc...)
* Väderintegration (Automatisk hämtning av väder vid rundans start)
* Grundläggande statistik
* 

\#Framtida implementeringar

* Lägga till vänner/se andras genomförda träningspass
* Autentisering med Keycloak
* Caddy reverese proxy, HTTPS, kanske rate limiting...
* Dashboard och visualisering av data
* AI driven coach



\#Projektstruktur

backend - Spring Boot frontend - NextJS Övrigt: docker-compose.yml, env.example.txt (ex på miljövariabler som behövs)

