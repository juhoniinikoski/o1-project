package o1.otaniemiGame


class Action(input: String) {

  private val commandText = input.trim.toLowerCase
  private val verb        = commandText.takeWhile( _ != ' ' )
  private val modifiers   = commandText.drop(verb.length).trim


  /** Causes the given player to take the action represented by this object, assuming
    * that the command was understood. Returns a description of what happened as a result
    * of the action (such as "Tietokone otettu mukaan."). The description is returned in an `Option`
    * wrapper; if the command was not recognized, `None` is returned. */
  def execute(actor: Player): Option[(Int, String)] = this.verb match {
    case "kulje"    => Some(actor.walk(this.modifiers))
    case "mene"     => Some(actor.go(this.modifiers))
    case "tee"      => Some(actor.doTask(this.modifiers))
    case "tarkista" => Some(actor.checkList)
    case "syö"      => Some(actor.eat(this.modifiers))
    case "laula"    => Some(actor.sing)
    case "pelästytä"=> Some(actor.scare)
    case "ota"      => Some(actor.take(this.modifiers))
    case "tavarat"  => Some(actor.examine)
    case "juttele"  => Some(actor.talk)
    case "käytä"    => Some(actor.useItem(this.modifiers))
    case "apua"     => Some(this.help())
    case "help"     => Some(this.help())
    case _          => None
  }

  def help(): (Int, String) = {

    val string = "\nTämän pelin idea on suorittaa kaikki to do listan tehtävät aikarajoituksen puitteissa." +
      "\nNäet tehtävät tervetuloviestistä tai komennolla \"tarkista\". Kun olet suorittanut jonkin tehtävän," +
      "\nmerkitään se to do listaan ruksilla. Näet suorittamasi tehtävät tarkastelemalla to do listaa." +
      "\n\nTehtävälistan tehtäviä voi suorittaa liikkumalla pelialueella ja suorittamalla eri toimintoja." +
      "\nKaikilla alueilla ei ole mahdollista suorittaa kaikkia toimintoja, mutta osa toiminnoista on mahdollisia" +
      "\nusealla eri alueella. Kun liikut jollekkin alueelle, tulostetaan konsoliin kyseisellä alueella suoritettavissa" +
      "\nolevat toiminnot. Lisäksi pelissä on eräs hupitoiminto, joka toimii kaikilla alueilla, ja jota ei tulosteta minkään alueen kuvaukseen." +
      "\n\nTässä näet listan kaikista pelin toiminnoista sekä kuinka monta minuuttia kyseinen toiminto kuluttaa peliaikaasi:" +
      "\n\n(5 min) " + "\"kulje (suuntaan)\"" + "\t" * 4 + "-> Siirtää pelihahmoa annettuun suuntaan. Kaikki suunnat, joihin voit " +
      "\n" + "\t" * 18 + " tämänhetkiseltä alueelta siirtyä tulostetaan alueen kuvaukseen." +
      "\n" + "\t" * 18 + " Kirjoita suunta samassa muodossa, jossa se on tulostettu kuvaukseen. Esim. \"kulje etelään\"." +
      "\n(5 min) " + "\"mene (tekemään jotakin)\"\t-> Siirtää pelihahmon annettuun tilaan, joka sijaitsee nykyisen alueen sisällä. " +
      "\n" + "\t" * 18 + " Kaikki mahdolliset tilat ovat: opiskelutila (komennon loppuosa: opiskelemaan), luento (komennon loppuosa: luennolle) ja lounas (komennon loppuosa: lounaalle)." +
      "\n" + "\t" * 18 + " Kirjoita komento edellä annetussa muodossa. Esim. \"mene opiskelemaan\"." +
      "\n" + "\t" * 18 + " Tämän toiminnon suoritettua voit joko suorittaa lisätoimintoja kyseisessä tilassa (katso komento \"tee (jotakin)\" tai palata takaisin komennolla \"kulje takaisin\"." +
      "\n(5 min) " + "\"tee (jotakin)\"" + "\t" * 6 + "->" + " Asettaa pelihahmon tekemään jotakin tehtävää. Kaikki mahdolliset tehtävät ovat: laskuharjoituksia, ohjelmointia ja muistiinpanoja." +
      "\n" + "\t" * 18 + " Kirjoita komento edellä annetussa muodossa. Esim. \"tee laskuharjoituksia\"." +
      "\n" + "\t" * 18 + " Tämän toiminnon suoritettua voit valita mitä tavaraa käytät tehtävän tekemiseen (katso komento \"käytä (tavaraa)\").)" +
      "\n(5 min) " + "\"tarkista\"" + "\t" * 8 + "->" + " Tulostaa konsoliin to do listan. Listassa näkyy kaikki tehtävät, jotka tulee suorittaa ennen peliajan loppumista. Pelaajan tekemät tehtävät on merkitty ruksilla." +
      "\n(5 min) " + "\"syö (jotakin)\"" + "\t" * 6 + "->" + " Saa pelaajan syömään. Pelaaja voi valita joko kasvisruokavaihtoehdon (komennon loppuosa: kasvista) tai liharuokavaihtoehdon (komennon loppuosa: lihaa)." +
      "\n" + "\t" * 18 + " Kirjoita komento edellä annetussa muodossa. Esim. \"syö kasvista\"." +
      "\n(5 min) " + "\"laula\"" + "\t" * 10 + "->" + " Hupikomento, joka saa pelaajan laulamaan." +
      "\n(5 min) " + "\"pelästytä\"" + "\t" * 8 + "->" + " Saa pelaajan pelästyttämään hanhet Alvarin aukiolta." +
      "\n(5 min) " + "\"ota (tavara)\"" + "\t" * 6 + "->" + " Saa pelaajan keräämään jonkin tavaran. Kaikki mahdolliset tavarat, joita pelaaja voi kerätä ovat: vihko ja tietokone." +
      "\n" + "\t" * 18 + " Kirjoita komento edellä annetussa muodossa. Esim. \"ota vihko\"." +
      "\n(5 min) " + "\"tavarat\"" + "\t" * 9 + "->" + " Tulostaa listan pelaajan kantamista tavaroista." +
      "\n(5 min) " + "\"juttele\"" + "\t" * 9 + "->" + " Saa pelaajan juttelemaan kavereille." +
      "\n(5 min) " + "\"käytä (tavaraa)\"" + "\t" * 5 + "->" + " Asettaa pelaajan tekemään jotakin tehtävää käyttäen kyseistä tavaraa. Pelaajan tulee olla jossakin tilassa" +
      "\n" + "\t" * 18 + " voidakseen käyttää tavaraa (katso komento \"mene (tekemään jotakin)\") sekä kantaa kyseistä tavaraa mukanaan (katso komennot \"ota (tavara)\" ja \"tavarat\")." +
      "\n" + "\t" * 18 + " Kaikki käytettävissä olevat tavarat ovat: vihko (komennon loppuosa: vihkoa) ja tietokone (komennon loppuosa: tietokonetta)." +
      "\n" + "\t" * 18 + " Kirjoita komento edellä annetussa muodossa. Esim. \"käytä vihkoa\"." +
      "\n(5 min) " + "\"apua\"" + "\t" * 10 + "->" + " Tulostaa pelaajalle tekstin, jossa on esitetty kaikki pelissä toimivat komennot ja komentojen kuluttama aika."

    0 -> string
  }

  /** Returns a textual description of the action object, for debugging purposes. */
  override def toString: String = this.verb + " (modifiers: " + this.modifiers + ")"


}
