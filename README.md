# Popular Movies Android app
## Getting started

- Replace the `bearerToken` variable in `app/build.gradle` with your own TMDB bearer token
- Compile in Android Studio

### Beskrivning
Jag valde att använda Picasso framför Glide då paketstorleken på Picasso är mindre än Glide, vilket då resulterar i en mindre total appstorlek.

Texterna såsom bland annat rubriker la jag manuellt in i strings.xml, men i mån av tid så hade jag nog istället valt att lägga in dem på t.ex. localize och gjort ett script som drar ner dem därifrån och automatiskt lägger in dem i strings.xml.

Jag skickar med id och titel på filmen från `MainActivity.kt` till`MovieDetailActivity.kt` via via `intent.putExtra` och satte `MOVIE_ID` som default till `0` och lade sedan till en if sats som koller om id:et ör mer än 0, och är det inte det så är så har troligtvis id:et inte skickats med korrekt och då avsluatar jag aktiviteten och går tillbaka till huvudvyn.

Jag skapade också en `Interceptor` som skickar med Bearer token i alla requests så att man inte behöver skicka med dem manuellt i varje request.

Om något går fel med requesten så visas en Toast med felmeddelandet.

Eftersom att uppgiften gick ut på att plocka hem dem 10 populäraste filmerna och api:et returnerade fler än 10 filmer, så körde jag `.take(10)` på responsen

