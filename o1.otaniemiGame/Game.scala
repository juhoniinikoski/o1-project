package o1.otaniemiGame


class Game {

  /** The title of the adventure game. */
  val title = "Koulupäivä Otaniemessä"

  /** Activities */
  private val seat = new Item("seat", "Mennä opiskelemaan päivän askareita.")
  private val exercise = new Item("laskuharjoituksia", "Tehdä matematiikan laskuharjoituksia.")
  private val programming = new Item("ohjelmointia", "Tehdä O1 ohjelmoinnin keskeneräisiä tehtäviä.")
  private val restaurant = new Item("ravintola", "Mennä lounaalle täyttämään vatsasi.")
  private val lunch = new Item("lounas", "Syödä ravitsevan opiskelijalounaan. Vaihtoehtoina on joko kasvis- tai liharuokaa.")
  private val lecture = new Item("luento", "Mennä luennolle kuuntelemaan päivän polttavinta luentoa!")
  private val notes = new Item("muistiinpanoja", "Tehdä muistiinpanoja.")
  private val friends = new Item("kaverit", "Jutella kavereiden kanssa kuulumisista.")
  private val coffee = new Item("kahvi", "Ottaa mukaan lounaskahvin.")
  private val scare = new Item("pelästytys", "Pelästyttää hanhia.")
  private val lectureNotebook = new Item("luentovihko", "Käyttää vihkoa muistiinpanoihin.")
  private val lectureComputer = new Item("luentotietokone", "Käyttää tietokonetta muistiinpanoihin.")
  private val exerciseNoteBook = new Item("laskarivihko", "Käyttää vihkoa harjoitusten tekemiseen.")
  private val exerciseComputer = new Item("laskaritietokone", "Käyttää tietokonetta harjoitusten tekemiseen.")
  private val programmingComputer = new Item("koodauskone", "Käyttää tietokonetta ohjelmointiin.")

  /** Items */
  private val coffeeItem = new Item("kahvi", "Kahvi piristämään päivää. Tekeepäs eetvarttia.")
  private val notebookItem = new Item("vihko", "Vihko muistiinpanoja ja laskareita varten.")
  private val computerItem = new Item("tietokone", "Läppäri esimerkiksi koodausta varten.")

  /** Usable items */
  private val usableNotebook = new Item("vihkoa", "vihko")
  private val usableComputer = new Item("tietokonetta", "tietokone")

  /** Areas */
  private val home       = new Area("Koti", "Olet kotona.\nOma koti kullan kallis.")
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

  /** Laskuharjoitus */
  private val exerciseArea = new Area("Laskuharjoitus", "Alat tekemään laskuharjoitusta.")
  exerciseArea.addUsableItem(usableComputer)
  exerciseArea.addUsableItem(usableNotebook)
  exerciseArea.addActivity(exerciseComputer)
  exerciseArea.addActivity(exerciseNoteBook)

  /** Ohjelmointi */
  private val programmingArea = new Area("Ohjelmointi", "Alat ohjelmoimaan.")
  programmingArea.addUsableItem(usableComputer)
  programmingArea.addActivity(programmingComputer)

  /** Muistiinpano */
  private val noteTakingArea = new Area("Muistiinpanot", "Alat kirjoittamaan muistiinpanoja.")
  noteTakingArea.addUsableItem(usableComputer)
  noteTakingArea.addUsableItem(usableNotebook)
  noteTakingArea.addActivity(lectureNotebook)
  noteTakingArea.addActivity(lectureComputer)

  /** Restaurant */
  private val restaurantArea = new Area("Ravintola", "Olet ravintolassa, jossa tuoksut leijailevat.")
  restaurantArea.addActivity(lunch)
  restaurantArea.addActivity(coffee)
  restaurantArea.addItem(coffeeItem)

  /** Study area */
  private val studyArea = new Area("Opiskelutila", "Olet opiskelutilassa, jossa ajatus lentää ja luovuus on huipussaan.")
  studyArea.addActivity(exercise)
  studyArea.addActivity(programming)
  studyArea.setSubareas(Vector("laskuharjoituksia" -> exerciseArea, "ohjelmointia" -> programmingArea))

  private val lectureHall = new Area("Luentosali", "Olet luentosalissa. Keskity, niin ymmärrät.")
  lectureHall.addActivity(notes)
  lectureHall.setSubareas(Vector("muistiinpanoja" -> noteTakingArea))

  /** Setting neighbours */
  home.setNeighbors(Vector("kouluun" -> metro))
  metro.setNeighbors(Vector("itään" -> aBloc, "etelään" -> kirjasto, "kotiin" -> home))
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
  kirjasto.setSubareas(Vector("opiskelemaan" -> studyArea))

  /** Adding activities and items */
  home.addItem(computerItem)
  home.addItem(notebookItem)
  home.addActivity(new Item("tietokone", "Ottaa tietokoneen mukaan."))
  home.addActivity(new Item("vihko", "Ottaa vihkon mukaan."))

  aBloc.addActivity(restaurant)
  aBloc.addActivity(new Item("kahvi", "Ottaa ärrältä kahvin suhteellisen edullisesti mukaan!"))
  aBloc.addItem(coffeeItem)

  dipoli.addActivity(restaurant)

  kirjasto.addActivity(seat)

  tTalo.addActivity(lecture)
  tTalo.addActivity(seat)
  tTalo.addActivity(restaurant)
  tTalo.addActivity(friends)

  tuas.addActivity(seat)
  tuas.addActivity(restaurant)
  tuas.addActivity(lecture)
  tuas.addActivity(friends)

  taffa.addActivity(restaurant)
  taffa.addActivity(friends)

  kandilafka.addActivity(seat)
  kandilafka.addActivity(friends)
  kandilafka.addActivity(restaurant)
  kandilafka.addActivity(lecture)

  alvari.addActivity(scare)


  /** The character that the player controls in the game. */
  val player = new Player(home)

  /** The number of minutes that have passed since the start of the game. */
  var minuteCount: Int = 0
  /** The maximum number of minutes that this game allows before time runs out. */
  val minuteLimit: Int = 8 * 60 // eight hours




  /** Determines if the adventure is complete, that is, if the player has won by doing all the given tasks and returning home before time runs out. */
  def isComplete: Boolean = {
    if (this.player.allTasksDone && this.player.location == home && this.minuteCount <= this.minuteLimit) true else false
  }

  /** Determines whether the player has won, lost, or quit, thereby ending the game. */
  def isOver: Boolean = this.isComplete || this.player.hasQuit || this.minuteCount >= this.minuteLimit

  /** Returns a message that is to be displayed to the player at the beginning of the game. */
  def welcomeMessage: String = {
    var string = "\nHuh, taas uusi koulupäivä koittaa.\nTee päivän tehtävät to do listasta ja palaa kotiin kello 16:00 mennessä, niin voit iloisin mielin illalla lopettaa päivän.\n\nTo do:"
    string = string + this.player.handlePrint
    string = string + "\n\n Ennen kuin lähdet kouluun, muista ottaa tietokone ja vihko mukaan, jotta voit tehdä päivän tehtävät!"
    string
  }


  /** Returns a message that is to be displayed to the player at the end of the game. The message
    * will be different depending on whether or not the player has completed their quest. */
  def goodbyeMessage: String = {
    if (this.isComplete)
      "Olipahan koulupäivä, mutta hommat on nyt hoidettu! Nyt ei auta kuin löhöillä sohvalla."
    else if (this.minuteCount >= this.minuteLimit)
      "Voi ei, kello on niin paljon, että koulun tilat sulkeutuvat jo.\nHuomiselle jää todella paljon hommaa, harmin paikka!\n\nPeli päättyi."
    else  // game over due to player quitting
      "Luovuttajat eivät pärjää tosimaailmassa!!"
  }

  def countHours: String = {
    val hours = this.minuteCount / 60
    val minutes = this.minuteCount % 60
    var string = ""
    if (minutes < 10) string = s"\n\nKello on nyt ${8+hours}:0$minutes." else string = s"\n\nKello on nyt ${8+hours}:$minutes."
    if (8 + hours >= 16) {
      string += "\nKoulun tilat ovat sulkeutuneet."
    } else {
      string += "\nKoulun tilat sulkeutuvat kello 16:00."
    }
    string
  }

  def closeActivities(): Unit = {
    val limitedAreas = Vector[Area](dipoli, kirjasto, tTalo, tuas, taffa, kandilafka)
    limitedAreas.foreach(_.removeActivities())
  }


  /** Plays a turn by executing the given in-game command, such as "kulje etelään". Returns a textual
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
    if (this.minuteCount >= this.minuteLimit) this.closeActivities()
    description.getOrElse("Tuntematon komento: \"" + command + "\".") + this.countHours
  }
}
