# Käyttöönotto

1. Paina vihreää code-näppäintä oikealta ylhäältä.
2. Lataa zip-tiedosto.
3. Nimeä ladattu kansio nimellä OtaniemiGame
4. Lisää kyseinen kansio muiden O1 moduulien joukkoon.
5. Jos OtaniemiGame näkyy muiden moduulien seassa vasemmassa reunassa, voidaan sitä kokeilla ajaa tiedostoista OtaniemiGameGUI tai OtaniemiGameTextUI.


# Pelin tavoite

Pelin tavoitteena on, että pelaaja liikkuu ympäri Otaniemeä suorittaen koulupäivän onnistumiseksi vaadittavat tehtävät, jotka löytyvät pelaajan to do -listasta.
Kaikilla alueilla ei ole mahdollisuutta suorittaa samoja tehtäviä, vaan pelaajan tulee liikkua pelin eri alueilla etsimässä mahdollisia paikkoja suorittaa tietty tehtävä.
Jokainen siirtymä sekä tehtävän tekeminen kuluttaa aikaa.
Pelaajan tulee pitää huoli, että hän saa päivän tehtävät suortettua kahdeksassa tunnissa, muuten koulun tilat sulkeutuvat.
Kun pelaaja on tehnyt to do -listassa vaaditun tehtävän, se merkitään hänen listaansa tehdyksi.
Pelaaja läpäisee pelin, kun to do -listasta kaikki vaaditut tehtävät ovat tehty.


# Pelin ominaisuudet

* Pelaaaja voi liikkua ympäri Otaniemeä komennolla `suuntana <ilmansuunta>`
* Pelaaja voi suorittaa tehtäviä kyseisen alueen tehtävälistasta. Tehtävä suoritetaan komennolla `tee <tehtävänumero>`
* Mikäli pelaajan to do -listassa on esimerkiksi kahden aineen laskarit, hänen tulee käydä kaksi kertaa itseopiskelutilassa.
* Pelaaja voi tarkistaa oman to do -listansa tilanteen komennolla `tarkista`
* Pelaajalla on kaksi kaveria, jotka liikkuvat sattumanvaraisesti pelialueella. Onnistunut koulupäivä vaatii myös heidän kanssa jutustelua.


# Käskyt

* liikkuminen alueiden välillä `mene <suunta>`
* meneminen paikkoihin `mene <paikan nimi>`
* itemin käyttö `käytä <tavaran nimi>`
* ruokailu `syö` (kasvis tai liha vaihtoehto?)
* laulaminen `laula`
* pelästytä hanhet `pelästytä`
* puhua toisten kanssa `juttele`
* pelin lopetus `lopeta`
* to do -listan katsominen `tarkista`
* tavaroille `ota <tavara>` ja `jätä <tavara>`


# Eri alueilla auki olevat tehtävät

* Metro: 
  * Kotiinlähtö (ennen kuin aika loppuu)
  * Tehtävää ei voi suorittaa, ennen kuin vähintään 3 to do -listan tehtävää on suoritettu
  * Ominaisuus lisätään metrolle vasta, kun 3 tehtävää on suoritettu
* Abloc:
  * Ruokailu
  * Kahvi
* Dipoli:
  * Ruokailu
* Täffä:
  * Ruokailu
* Kirjasto:
  * Laskarin tekeminen
  * Koodaa O1 kierrosta läppärillä
* Alvarin aukio:
  * Ei toimintoja
* Aukio:
  * Ei toimintoja
* Kandilafka:
  * Opiskelutilaan meneminen
  * Lounas
  * Luento
* T-talo:
  * Opiskelutilaan meneminen
  * Luento
* TUAS:
  * Opiskelutilaan meneminen
  * Lounas
  * Luento
* Opiskelutila:
  * Laskarin tekeminen
  * Koodaa O1 kierrosta läppärillä
* Luento
  * Luentomuistiinpanot

# Tehtäväluokat

* Task: Lounas/ruokailu, kahvittelu, luento, laskarin tekeminen, O1 koodaus
* Item: Läppäri, vihko, sitsilaulukirja

# TODO ennen ku peli on valmis

* Laskuharjoituksen tekeminen tietokoneelle tai vihkolle (katariina)
* Kotoa lähtö ja tavaroiden pakkaaminen (katariina) ✅
* Voitto- ja häviöehto (kotona tarkastetaan, onko kaikki tehtävät tehty) (katariina)
* Kotiin palaaminen (juho) ✅
* Rajoita koulun tilojen käyttö (jos kello on yli 16, ei anneta enää vaihtoehdoiksi luentoa, ruokailua, opiskleutiloja jne) (juho) ✅

# A+ ohjeet

Pelillä pitää olla seuraavat ominaisuudet:

* Alueita, joihin pelaaja voi mennä.
* Haasteita tai pulmia, joista pelaajan täytyy jotenkin selvitä läpäistäkseen pelin.
* Vähintään muutama uusi komento, joita pelaaja voi antaa ja jotka tekevät jotain hyödyllistä.
* Uusi voittoehto, joka pelaajan täytyy saavuttaa läpäistäkseen pelin. Pelin läpäisyn täytyy edellyttää jotain muutakin kuin vain kävelemistä tietylle aluelle. Voit myös laittaa peliin "häviämisehtoja" (esim. pelaajahahmo kuolee), mutta se ei ole pakollista.
* Tervehdysteksti, joka kertoo mistä pelissä on kysymys ja mitä pelaajan on tarkoitus tehdä. Jos haluat, että pelin tarkoitus/voittoehto selviää vasta pelin aikana, niin sekin on sallittua, kunhan asian selvittäminen ei vaadi pelaajalta kohtuutonta vaivaa.
* help-komento, joka tulostaa näytölle ohjeita pelin pelaamiseen. Kuvaile tässä ainakin lisäämäsi uudet komennot.
* Jompikumpi tai molemmat näistä kahdesta:
  * Komento muotoa use itemname, jolla pelaaja voi käyttää esinettä. Käyttämisen seuraukset riippuvat esineestä.
  * Muita henkilöitä tai olentoja, jotka liikkuvat pelimaailmassa ja ovat jollain tapaa hyödyllisiä, vaarallisia tai muuten merkityksellisiä pelin kannalta.
