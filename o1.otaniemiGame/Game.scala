package o1.otaniemiGame


class Game {

  /** The title of the adventure game. */
  val title = "Koulupäivä Otaniemessä"

  private val metro      = new Area("Metro", "Olet metroasemalla.\nMetrot metelöivät ja ihmisiä vilisee.")
  private val aBloc      = new Area("A Bloc", "Olet A Bloc ostoskeskuksessa.\nRuoka ja kahvi tuoksuu, nam.")
  private val dipoli     = new Area("Dipoli", "Olet Aalto-yliopiston päärakennuksessa Dipolissa.\nTäällä ei yksikään ikkuna ole toistensa kanssa samankokoinen.")
  private val kirjasto   = new Area("Kirjasto", "Olet kirjastossa, toiselta nimeltään Harald Herlin oppimiskeskuksessa.\nHiljainen on tunnelma ja ihmiset ovat keskittyneitä.")
  private val alvari     = new Area("Alvarin aukio", "Olet Alvarin aukiolla. Kannattaa katsoa mihin astuu, sillä hanhet ovat olleet asialla jälleen.")
  private val tTalo      = new Area("T-Talo", "T-Talo, täällä opiskellaan, syödään ja hengaillaan.\nTiesitkö, että toisessa kerroksessa on pienimuotoinen tietokonemuseo?")
  private val tuas       = new Area("TUAS", "Olet TUASilla. Täällä kädet käy ja juttu lentää.\nTäällä on yksi kylän parhaista opikelijaravintoloista!")
  private val taffa      = new Area("Täffä", "Olispa keskiviikko.\nSpagua odotellessa!")
  private val aukio      = new Area("Aukio", "Olet aukiolla metroaseman ja Väreen edustalla.\nHuomaat kuinka jokainen ohikulkija tuijottaa puhelintaan.")
  private val kandilafka = new Area("Kandidaattikeskus", "Olet kandidaattikeskuksessa, legendaarisen TKK:n päärakennuksessa.\nOnkohan jo kiire luennolle!?")

  metro.setNeighbors(Vector("itä" -> aBloc, "etelä" -> kirjasto ))
  aBloc.setNeighbors(Vector("etelä" -> aukio, "länsi" -> metro))
  dipoli.setNeighbors(Vector("itä" -> kirjasto, "etelä" -> taffa))
  kirjasto.setNeighbors(Vector("pohjoinen" -> metro, "itä" -> aukio, "etelä" -> alvari, "länsi" -> dipoli))
  aukio.setNeighbors(Vector("pohjoinen" -> aBloc, "itä" -> tTalo, "etelä" -> kandilafka, "länsi" -> kirjasto))
  tTalo.setNeighbors(Vector("itä" -> tuas, "länsi" -> aukio))
  tuas.setNeighbors(Vector("länsi" -> tTalo))
  taffa.setNeighbors(Vector("pohjoinen" -> dipoli, "itä" -> alvari))
  alvari.setNeighbors(Vector("pohjoinen" -> kirjasto, "itä" -> kandilafka, "länsi" -> taffa))
  kandilafka.setNeighbors(Vector("pohjoinen" -> aukio, "länsi" -> alvari))

  private val seat = new Item("seat", "Istuutua alas opiskelemaan. Laskareissa on tekemistä!")
  private val lunch = new Item("lunch", "Käydä haukkaamassa ravitsevan opiskelijalounaan.")
  private val lecture = new Item("lecture", "Käydä kuuntelemassa päivän polttavan luennon!")
  private val friends = new Item("friends", "Vaihtaa kavereiden kanssa kuulumisia.")

  aBloc.addItem(lunch)
  aBloc.addItem(new Item("coffee", "Ottaa ärrältä kahvin suhteellisen edullisesti mukaan!"))
  dipoli.addItem(new Item("flyer", "Otta mukaan esite koskien Aallon rekrymessuja, jotka järjestetään Otahallissa!"))
  dipoli.addItem(lunch)
  kirjasto.addItem(new Item("book", "Lainata fysiikan kirjan jo ajoissa, tarvitset sitä tenttiviikkoa ajatellen."))
  kirjasto.addItem(seat)
  tTalo.addItem(lecture)
  tTalo.addItem(seat)
  tTalo.addItem(lunch)
  tTalo.addItem(friends)
  tuas.addItem(seat)
  tuas.addItem(lunch)
  tuas.addItem(lecture)
  tuas.addItem(friends)
  taffa.addItem(lunch)
  taffa.addItem(friends)
  kandilafka.addItem(seat)
  kandilafka.addItem(friends)
  kandilafka.addItem(lunch)
  kandilafka.addItem(lecture)



  /** The character that the player controls in the game. */
  val player = new Player(aukio)

  /** The number of turns that have passed since the start of the game. */
  var turnCount = 0
  /** The maximum number of turns that this adventure game allows before time runs out. */
  val timeLimit = 40


  /** Determines if the adventure is complete, that is, if the player has won. */
  def isComplete: Boolean = {
//    this.player.location == this.destination && this.player.has("remote") && this.player.has("battery")
    false
  }

  /** Determines whether the player has won, lost, or quit, thereby ending the game. */
  def isOver: Boolean = this.isComplete || this.player.hasQuit || this.turnCount == this.timeLimit

  private val toDo = Map[String, String](
    "lecture" -> "Käy luennolla",
    "seat" -> "Tee laskuharjoituksia",
    "lunch" -> "Nauti lounas",
    "coffee" -> "Nappaa kahvi mukaan"
  )

  /** Returns a message that is to be displayed to the player at the beginning of the game. */
  def welcomeMessage: String = {
    var string = "Huh, taas uusi koulupäivä koittaa.\n\nTee vähintään kolme tehtävää päivän to-do listasta, niin voit iloisin mielin lopettaa päivän. \n\nTo do:"
    string = string + this.handlePrint
    string
  }

  def handlePrint: String = {
    var string = ""
    this.toDo.values.foreach(v =>
      if (this.player.hasDone(v)) {
        string = string + s"\n[x] ${v}"
      } else {
        string = string + s"\n[ ] ${v}"
      }
    )
    string
  }


  /** Returns a message that is to be displayed to the player at the end of the game. The message
    * will be different depending on whether or not the player has completed their quest. */
  def goodbyeMessage: String = {
    if (this.isComplete)
      "Home at last... and phew, just in time! Well done!"
    else if (this.turnCount == this.timeLimit)
      "Oh no! Time's up. Starved of entertainment, you collapse and weep like a child.\nGame over!"
    else  // game over due to player quitting
      "Quitter!"
  }


  /** Plays a turn by executing the given in-game command, such as "go west". Returns a textual
    * report of what happened, or an error message if the command was unknown. In the latter
    * case, no turns elapse. */
  def playTurn(command: String): String = {
    val action = new Action(command)
    val outcomeReport = action.execute(this.player)
    if (outcomeReport.isDefined) {
      this.turnCount += 1
    }
    outcomeReport.getOrElse("Unknown command: \"" + command + "\".")
  }


}

