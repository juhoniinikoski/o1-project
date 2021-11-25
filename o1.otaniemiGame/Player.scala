package o1.otaniemiGame

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
    "lounaalle" -> "Nauti lounas käymällä jossakin opiskelijaravintolassa.",
    "kahvi" -> "Nappaa kahvi mukaan, jotta jaksat pitkän päivän.",
  )

  def doTask(activityName: String): (Int, String) = {
    val activity = this.currentLocation.getActivity(activityName)
    var string = "Tämä toimenpide ei onnistu täällä."
    if (activity.isDefined) {
      activity.foreach(a => this.doneTasks += activityName)
      string = s"Teit $activityName. Aikaa kului 10 minuuttia."
      10 -> string
    }
    else {
      0 -> string
    }
  }

  def drop(itemName: String): String = {
    val item = this.items.get(itemName)
    var string = "You don't have that!"
    if (item.isDefined) {
      item.foreach(this.currentLocation.addItem(_))
      string = s"You drop the $itemName."
      this.items -= itemName
    }
    string
  }

//  // voi tarkastella päivän to-do listaa tämän avulla
//  def examine(itemName: String): String = {
//    val item = this.items.get(itemName)
//    var string = "Jos haluat tarkastella päivän to-do -listaa"
//    if (item.isDefined) {
//      val description = item.map(_.description).getOrElse("")
//      string = s"You look closely at the $itemName.\n$description"
//    }
//    string
//  }

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
      string = "Kuljit " + direction + ". Aikaa kului " + 5 + " minuuttia."
      // walking time taken from constants
      walkingTime -> string
    } else {
      0 -> string
    }
  }

  def go(subArea: String): (Int, String) = {
    val destination = this.location.subArea(subArea)
    destination.foreach(_.setNeighbor("takaisin", this.currentLocation))
    this.currentLocation = destination.getOrElse(this.currentLocation)
    var string = "Et voi mennä " + subArea + " täällä."
    if (destination.isDefined) {
      string = "Menit " + subArea + ". Aikaa kului " + walkingTime + " minuuttia."
      // walking time taken from constants
      walkingTime -> string
    } else {
      0 -> string
    }
  }


  /** Causes the player to rest for a short while (this has no substantial effect in game terms).
    * Returns a description of what happened. */
  def rest(): String = {
    "You rest for a while. Better get a move on, though."
  }

  def sing(): (Int, String) = {
    val song = "Joku laulu" // pitäiskö täs olla vaikka 3 vaihtoehtoa joista aina arvotaan yks
    val string = "\nOtaniemessä vallitsee aurinkoinen sää, joten olet niin hyvällä tuulella, että alkoi laulattaa."
    0 -> song+string
  }

  def eat(food: String): (Int, String) = {
    food match {
      case "kasvista" => vegetarianFood -> "Mums, herkullista kasvispaellaa!"
      case "lihaa" => meatFood -> "Nam nam, jauhenlihatortillat kelpaavat aina!"
    }
  }

  def scare(): (Int, String) = {
    5 -> "Kvaak, kvaak!!\nHanhet lähtivät karkuun, kun pelästytit ne juoksemalla Alvarin aukion läpi."
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


