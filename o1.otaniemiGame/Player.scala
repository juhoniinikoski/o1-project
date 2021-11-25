package o1.otaniemiGame

import scala.collection.mutable.Map


/** A `Player` object represents a player character controlled by the real-life user of the program.
  *
  * A player object's state is mutable: the player's location and possessions can change, for instance.
  *
  * @param startingArea  the initial location of the player */
class Player(startingArea: Area) {

  private var currentLocation = startingArea        // gatherer: changes in relation to the previous location
  private var quitCommandGiven = false              // one-way flag
  private val items = Map[String, Item]()

  def doTask(itemName: String): String = {
    val item = this.currentLocation.removeItem(itemName)
    var string = s"There is no $itemName here to pick up."
    if (item.isDefined) {
      item.foreach(i => items += itemName -> i)
      string = s"You pick up the $itemName."
    }
    string
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

  // voi tarkastella päivän to-do listaa tämän avulla
  def examine(itemName: String): String = {
    val item = this.items.get(itemName)
    var string = "Jos haluat tarkastella päivän to-do -listaa"
    if (item.isDefined) {
      val description = item.map(_.description).getOrElse("")
      string = s"You look closely at the $itemName.\n$description"
    }
    string
  }

  def hasDone(itemName: String): Boolean = {
    this.items.contains(itemName)
  }

  def inventory: String = {
    var string = "You are empty-handed."
    if (this.items.nonEmpty) {
      val keys = this.items.keys.mkString("\n")
      string = "You are carrying:\n" + keys
    }
    string
  }


  /** Determines if the player has indicated a desire to quit the game. */
  def hasQuit: Boolean = this.quitCommandGiven


  /** Returns the current location of the player. */
  def location: Area = this.currentLocation


  /** Attempts to move the player in the given direction. This is successful if there
    * is an exit from the player's current location towards the direction name. Returns
    * a description of the result: "You go DIRECTION." or "You can't go DIRECTION." */
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


  /** Signals that the player wants to quit the game. Returns a description of what happened within
    * the game as a result (which is the empty string, in this case). */
  def quit(): String = {
    this.quitCommandGiven = true
    ""
  }


  /** Returns a brief description of the player's state, for debugging purposes. */
  override def toString: String = "Now at: " + this.location.name

}


