package o1.otaniemiGame


class Game {

  /** The title of the adventure game. */
  val title = "Koulupäivä Otaniemessä"

  /** Items */
  private val seat = new Item("seat", "Mennä opiskelemaan päivän askareita.")
  private val exercise = new Item("laskuharjoituksia", "Tehdä matematiikan laskuharjoituksia.")
  private val programming = new Item("ohjelmointia", "Tehdä O1 ohjelmoinnin keskeneräisiä tehtäviä.")
  private val restaurant = new Item("restaurant", "Mennä lounaalle täyttämään vatsasi.")
  private val lunch = new Item("lounas", "Syödä ravitsevan opiskelijalounaan.")
  private val lecture = new Item("lecture", "Mennä luennolle kuuntelemaan päivän polttavaa luentoa!")
  private val notes = new Item("muistiinpanoja", "Kirjoittaa muistiinpanoja vihkoon.")
  private val friends = new Item("kavereille", "Jutella kavereiden kanssa kuulumisista.")
  private val coffee = new Item("coffee", "Ottaa mukaan lounaskahvin.")

  /** Areas */
  private val metro      = new Area("Metro", "Olet metroasemalla.\nMetrot metelöivät ja ihmisiä vilisee.")
  private val aBloc      = new Area("A Bloc", "Olet A Bloc ostoskeskuksessa.\nRuoka ja kahvi tuoksuu, nam.")
  private val dipoli     = new Area("Dipoli", "Olet Aalto-yliopiston päärakennuksessa Dipolissa.\nTäällä ei yksikään ikkuna ole toistensa kanssa samankokoinen.")
  private val kirjasto   = new Area("Kirjasto", "Olet kirjastossa, toiselta nimeltään Harald Herlin oppimiskeskuksessa.\nHiljainen on tunnelma ja ihmiset ovat keskittyneitä.")
  private val alvari     = new Area("Alvarin aukio", "Olet Alvarin aukiolla. Kannattaa katsoa mihin astuu, sillä hanhet ovat olleet asialla jälleen.")
  private val tTalo      = new Area("T-Talo", "T-Talo, täällä opiskellaan, syödään ja hengaillaan.\nTiesitkö, että toisessa kerroksessa on pienimuotoinen tietokonemuseo?")
  private val tuas       = new Area("TUAS", "Olet TUASilla. Täällä kädet käy ja juttu lentää.\nTäällä on yksi kylän parhaista opikelijaravintoloista!")
  private val taffa      = new Area("Täffä", "Olispa keskiviikko.\nSpagua odotellessa!")
  private val aukio      = new Area("Aukio", "Olet aukiolla metroaseman ja Väreen edustalla.\nHuomaat kuinka jokainen ohikulkija tuijottaa puhelintaan.")
  private val kandilafka = new Area("Kandidaattikeskus", "Olet kandidaattikeskuksessa, legendaarisen TKK:n päärakennuksessa.")

  /** Restaurant */
  private val restaurantArea = new Area("Ravintola", "Olet ravintolassa, jossa tuoksut leijailevat.")
  restaurantArea.addItem(lunch)
  restaurantArea.addItem(coffee)

  /** Study area */
  private val studyArea = new Area("Opiskelutila", "Olet opiskelutilassa, jossa ajatus lentää ja luovuus on huipussaan.")
  studyArea.addItem(exercise)
  studyArea.addItem(programming)

  private val lectureHall = new Area("Luentosali", "Olet luentosalissa. Keskity, niin ymmärrät.")
  lectureHall.addItem(notes)

  /** Setting neighbours */
  metro.setNeighbors(Vector("itään" -> aBloc, "etelään" -> kirjasto ))
  aBloc.setNeighbors(Vector("etelään" -> aukio, "länteen" -> metro))
  dipoli.setNeighbors(Vector("itään" -> kirjasto, "etelään" -> taffa))
  kirjasto.setNeighbors(Vector("pohjoiseen" -> metro, "itään" -> aukio, "etelään" -> alvari, "länteen" -> dipoli))
  aukio.setNeighbors(Vector("pohjoiseen" -> aBloc, "itään" -> tTalo, "etelään" -> kandilafka, "länteen" -> kirjasto))
  alvari.setNeighbors(Vector("pohjoiseen" -> kirjasto, "itään" -> kandilafka, "länteen" -> taffa))
  tTalo.setNeighbors(Vector("itään" -> tuas, "länteen" -> aukio))
  tuas.setNeighbors(Vector("länteen" -> tTalo))
  taffa.setNeighbors(Vector("pohjoiseen" -> dipoli, "itään" -> alvari))
  kandilafka.setNeighbors(Vector("pohjoiseen" -> aukio, "länteen" -> alvari))

  /** Setting subareas */
  aBloc.setSubareas(Vector("lounaalle" -> restaurantArea))
  dipoli.setSubareas(Vector("lounaalle" -> restaurantArea))
  taffa.setSubareas(Vector("lounaalle" -> restaurantArea))
  tTalo.setSubareas(Vector("lounaalle" -> restaurantArea, "opiskelemaan" -> studyArea, "luennolle" -> lectureHall))
  tuas.setSubareas(Vector("lounaalle" -> restaurantArea, "opiskelemaan" -> studyArea, "luennolle" -> lectureHall))
  kandilafka.setSubareas(Vector("lounaalle" -> restaurantArea, "opiskelemaan" -> studyArea, "luennolle" -> lectureHall))

  /** Adding items */
  aBloc.addItem(restaurant)
  aBloc.addItem(new Item("coffee", "Ottaa ärrältä kahvin suhteellisen edullisesti mukaan!"))

  dipoli.addItem(new Item("flyer", "Ottaa mukaan esite koskien Aallon rekrymessuja, jotka järjestetään Otahallissa!"))
  dipoli.addItem(restaurant)

  kirjasto.addItem(new Item("book", "Lainata fysiikan kirjan jo ajoissa, tarvitset sitä tenttiviikkoa ajatellen."))
  kirjasto.addItem(seat)

  tTalo.addItem(lecture)
  tTalo.addItem(seat)
  tTalo.addItem(restaurant)
  tTalo.addItem(friends)

  tuas.addItem(seat)
  tuas.addItem(restaurant)
  tuas.addItem(lecture)
  tuas.addItem(friends)

  taffa.addItem(restaurant)
  taffa.addItem(friends)

  kandilafka.addItem(seat)
  kandilafka.addItem(friends)
  kandilafka.addItem(restaurant)
  kandilafka.addItem(lecture)


  /** The character that the player controls in the game. */
  val player = new Player(aukio)

  /** The number of minutes that have passed since the start of the game. */
  var minuteCount: Int = 0
  /** The maximum number of minutes that this game allows before time runs out. */
  val minuteLimit: Int = 8 * 60 // eight hours


  /** Determines if the adventure is complete, that is, if the player has won. */
  // Päivä päättyy, jos kaikki to do listan tehtävät on tehty ja pelaaja lähtee metrolla kotiin tai jos aika on loppunut
  // Jos aika loppuu kesken, tulee ankara läksytys ja pelaaja "häviää" (joku teksti)
  // Jos pelaaja ehtii tehdä tarvittavat tehtävät ja pääsee metrolle, tulee kehuja ja pelaaja "voittaa"
  def isComplete: Boolean = {
//    this.player.location == this.destination && this.player.has("remote") && this.player.has("battery")
    false
  }

  /** Determines whether the player has won, lost, or quit, thereby ending the game. */
  def isOver: Boolean = this.isComplete || this.player.hasQuit || this.minuteCount >= this.minuteLimit

  /** Returns a message that is to be displayed to the player at the beginning of the game. */
  def welcomeMessage: String = {
    // haluisin ehkä vähän muokkaa tätä
    var string = "\nHuh, taas uusi koulupäivä koittaa.\nTee vähintään kolme tehtävää päivän to do listasta, niin voit iloisin mielin illalla lopettaa päivän. \n\nTo do:"
    string = string + this.player.handlePrint
    string
  }


  /** Returns a message that is to be displayed to the player at the end of the game. The message
    * will be different depending on whether or not the player has completed their quest. */
  def goodbyeMessage: String = {
    if (this.isComplete)
      "Olipahan koulupäivä, mutta hommat on nyt hoidettu! Nyt ei auta kun löhöillä sohvalla."
    else if (this.minuteCount >= this.minuteLimit)
      "Voi ei, kello on niin paljon, että koulun tilat sulkeutuvat jo.\nHuomiselle jää todella paljon hommaa, harmin paikka!"
    else  // game over due to player quitting
      "Luovuttajat eivät pärjää tosimaailmassa!!"
  }


  /** Plays a turn by executing the given in-game command, such as "go west". Returns a textual
    * report of what happened, or an error message if the command was unknown. In the latter
    * case, no turns elapse. */
  def playTurn(command: String): String = {
    val action = new Action(command)
    val outcomeReport = action.execute(this.player)
    val description = outcomeReport.map(_._2)
    val time = outcomeReport.map(_._1).getOrElse(0)
    if (description.isDefined) {
      this.minuteCount += time
    }
    description.getOrElse("Tuntematon komento: \"" + command + "\".")
  }
}

