package o1.otaniemiGame

import o1.Constants._

import scala.collection.mutable


/** A `Player` object represents a player character controlled by the real-life user of the program.
  *
  * A player object's state is mutable: the player's location and possessions can change, for instance.
  *
  * @param startingArea  the initial location of the player */
class Player(startingArea: Area) {

  private var currentLocation = startingArea
  private var quitCommandGiven = false
  private val items = mutable.Map[String, Item]()
  private val doneTasks = mutable.Buffer[String]()

  private val toDo = Map[String, String](
    "muistiinpanoja" -> "Käy luennolla ja tee muistiinpanoja vihkoon.",
    "laskuharjoituksia" -> "Tee matematiikan laskuharjoituksia vihkoon.",
    "ohjelmointia" -> "Jatka O1-kurssin tehtäviä tietokoneellasi.",
    "lounas" -> "Nauti lounas käymällä jossakin opiskelijaravintolassa.",
    "kahvi" -> "Nappaa kahvi mukaan, jotta jaksat pitkän päivän.",
    "juttele" -> "Juttele kavereiden kanssa. Se piristää päivää ja auttaa jaksamaan."
  )

  def doTask(activityName: String): (Int, String) = {
    val activity = this.currentLocation.getActivity(activityName) // wrappaa aktiviteetin optioniin
    if (this.doneTasks.contains(activityName)) { // jos tehtävä on jo tehty
      0 -> s"Sinä olet tehnyt jo $activityName, keskity muihin asioihin."
    } else if (activity.isDefined) { // jos tehtävä on määritelty
      var time = 0
      activityName match {
        case "ohjelmointia"      => time = programmingTime
        case "laskuharjoituksia" => time = exerciseTime
        case "muistiinpanoja"    => time = lectureTime
      }
      activity.foreach(_ => this.doneTasks += activityName)
      time -> s"Teit $activityName. Aikaa kului $time minuuttia."
    } else {
      0 -> "Tämä toimenpide ei onnistu täällä."
    }
  }

  def take(itemName: String): (Int, String) = {
    val item = this.currentLocation.getItem(itemName)
    var string = s"${itemName.capitalize} ei ole otettavissa täältä."
    if (this.items.contains(itemName)) {
      string = s"Sinullahan on jo $itemName."
    } else if (item.isDefined) {
      item.foreach(i => this.items += itemName -> i)
      this.currentLocation.removeItem(itemName)
      if (itemName == "kahvi") {
        this.doneTasks += itemName
      }
      string = s"${itemName.capitalize} otettu mukaan."
    }
    0 -> string
  }

  def talk: (Int, String) = {
    val activity = this.currentLocation.getActivity("kaverit")
    if (activity.isDefined) {
      this.doneTasks += "juttele"
      talkingTime -> s"Pälä pälä pälä\npälä pälä\npälä pälä pälä pälä\n\nJuttelit kavereiden kanssa, aikaa kului $talkingTime minuuttia."
    } else {
      0 -> "Täällä ei oikein ole ketään, kelle jutella."
    }
  }

  def sing: (Int, String) = {
    var string = "Lallalaa!\nKaivoit laukkuun unohtuneen sitsilaulukirjan ja repäisit kauniin virren."
    string = string + "\nOtaniemessä vallitsee aurinkoinen sää, joten olet niin hyvällä tuulella, että alkoi laulattaa."
    string = string + "\nLaulaminen ei kuluta aikaasi."
    0 -> string
  }

  def eat(food: String): (Int, String) = {
    if (this.hasDone("lounas")) {
      0 -> "Olet syönyt jo lounaan, nyt kannattaa keskittyä muihin asioihin."
    } else {
      food match {
        case "kasvista" =>
          this.doneTasks += "lounas"
          vegetarianFood -> s"Mums, herkullista kasvispaellaa! Aikaa lounaaseen kului $vegetarianFood minuuttia."
        case "lihaa" =>
          this.doneTasks += "lounas"
          meatFood -> s"Nam nam, jauhenlihatortillat kelpaavat aina! Aikaa lounaaseen kului $meatFood minuuttia."
        case _ => 0 -> "Vaihtoehtoina on joko syödä lihaa tai kasvista."
      }
    }
  }

  def scare: (Int, String) = {
    val activity = this.currentLocation.getActivity("pelästytys")
    val string = "Täällä ei ole ketään, jota pelästyttää."
    if (activity.isDefined) {
      this.currentLocation.removeActivity("pelästytys")
      5 -> "Kvaak, kvaak!!\nHanhet lähtivät karkuun, kun pelästytit ne juoksemalla Alvarin aukion läpi.\nTähän toimintaan kului 5 minuuttia."
    }
    else {
      0 -> string
    }
  }

  def examine: (Int, String) = {
    var string = "Mukana olevat tavarat:"
    for (item <- this.items) {
      string += s"\n\n${item._1.capitalize}\n${item._2.description}"
    }
    0-> string
  }

  def hasDone(itemName: String): Boolean = {
    this.doneTasks.contains(itemName)
  }

  def checkList: (Int, String) = {
    var string = "\nAlta voit tarkastella päivän to do -listaa.\n\nTo do:"
    string = string + this.handlePrint
    0 -> string
  }


  /** Determines if the player has indicated a desire to quit the game. */
  def hasQuit: Boolean = this.quitCommandGiven


  /** Returns the current location of the player. */
  def location: Area = this.currentLocation


  /** Attempts to move the player in the given direction. This is successful if there
    * is an exit from the player's current location towards the direction name. Returns
    * a description of the result. */
  def walk(direction: String): (Int, String) = {
    val destination = this.location.neighbor(direction)
    this.currentLocation = destination.getOrElse(this.currentLocation)
    var string = "Et voi kulkea " + direction + "."
    if (destination.isDefined) {
      if (direction == "kotiin" || direction == "kouluun") {
        string = "Kuljit " + direction + ". Aikaa kului " + 20 + " minuuttia."
        20 -> string
      } else {
        string = "Kuljit " + direction + ". Aikaa kului " + 5 + " minuuttia."
        walkingTime -> string
      }
    } else {
      0 -> string
    }
  }

  /** Attempts to move the player to the given activity. This is successful if there
    * is an activity from the player's current location that corresponds the given name. Returns
    * a description of the result. */
  def go(subArea: String): (Int, String) = {
    val destination = this.location.subArea(subArea)
    destination.foreach(_.setNeighbor("takaisin", this.currentLocation))
    this.currentLocation = destination.getOrElse(this.currentLocation)
    var string = "Et voi mennä " + subArea + " täällä."
    if (subArea == "") {
      string = "Sinun tulee valita paikka, jonne haluat mennä."
    }
    if (destination.isDefined) {
      string = "Menit " + subArea + ". Aikaa kului " + walkingTime + " minuuttia."
      // walking time taken from constants
      walkingTime -> string
    } else {
      0 -> string
    }
  }


  /** Signals that the player wants to quit the game. Returns a description of what happened within
    * the game as a result (which is the empty string, in this case). */
  def quit(): String = {
    this.quitCommandGiven = true
    ""
  }

  def handlePrint: String = {
    var string = ""
    this.toDo.foreach(todo =>
      if (this.hasDone(todo._1)) {
        string = string + s"\n[x] ${todo._2}"
      } else {
        string = string + s"\n[ ] ${todo._2}"
      }
    )
    string
  }


  /** Returns a brief description of the player's state, for debugging purposes. */
  override def toString: String = "Now at: " + this.location.name

}


