package o1.otaniemiGame

import scala.collection.mutable

/** The class `Area` represents locations in a text adventure game world. A game world
  * consists of areas. In general, an "area" can be pretty much anything: a room, a building,
  * an acre of forest, or something completely different. What different areas have in
  * common is that players can be located in them and that they can have exits leading to
  * other, neighboring areas. An area also has a name and a description.
  * @param name         the name of the area
  * @param description  a basic description of the area (typically not including information about items) */
class Area(var name: String, var description: String) {

  private val neighbors = mutable.Map[String, Area]()
  private val subAreas = mutable.Map[String, Area]()
  private val activities = mutable.Map[String, Item]()
  private val items = mutable.Map[String, Item]()

  def contains(itemName: String): Boolean = {
    this.activities.contains(itemName)
  }

  def addItem(item: Item): Unit = {
    items += item.name -> item
  }

  def removeItem(itemName: String): Option[Item] = {
    val item = this.items.get(itemName)
    if (item.isDefined) {
      this.items -= itemName
    }
    item
  }

  def getItem(itemName: String): Option[Item] = this.items.get(itemName)

  def addActivity(activity: Item): Unit = {
    activities += activity.name -> activity
  }

  def removeActivity(activityName: String): Option[Item] = {
    val activity = this.activities.get(activityName)
    if (activity.isDefined) {
      this.activities -= activityName
    }
    activity
  }

  def getActivity(activityName: String): Option[Item] = this.activities.get(activityName)

  /** Returns the area that can be reached from this area by moving in the given direction. The result
    * is returned in an `Option`; `None` is returned if there is no exit in the given direction. */
  def neighbor(direction: String): Option[Area] = this.neighbors.get(direction)


  /** Adds an exit from this area to the given area. The neighboring area is reached by moving in
    * the specified direction from this area. */
  def setNeighbor(direction: String, neighbor: Area): Unit = {
    this.neighbors += direction -> neighbor
  }


  /** Adds exits from this area to the given areas. Calling this method is equivalent to calling
    * the `setNeighbor` method on each of the given direction--area pairs.
    * @param exits  contains pairs consisting of a direction and the neighboring area in that direction
    * @see [[setNeighbor]] */
  def setNeighbors(exits: Vector[(String, Area)]): Unit = {
    this.neighbors ++= exits
  }

  /** Returns the subArea that can be reached from this area by moving in the given direction. The result
    * is returned in an `Option`; `None` is returned if there is no exit in the given direction. */
  def subArea(location: String): Option[Area] = this.subAreas.get(location)

  /** Adds exits from this area to the given subAreas.
    * @param exits  contains pairs consisting of an activity and the neighboring subArea in that direction. */
  def setSubareas(exits: Vector[(String, Area)]): Unit = {
    this.subAreas ++= exits
  }

  /** Returns a multi-line description of the area as a player sees it. This includes a basic
    * description of the area as well as information about exits and items. The return
    * value has the form "DESCRIPTION\n\nExits available: DIRECTIONS SEPARATED BY SPACES".
    * The directions are listed in an arbitrary order. */
  def fullDescription: String = {
    val exitList = "\n\nVoit kulkea: " + this.neighbors.keys.mkString(" ")
    var string = this.description
    if (this.activities.nonEmpty) {
      var itemList = "\n\nTäällä voit: "
      val descriptions = this.activities.values.map(_.description)
      for (description <- descriptions) {
        itemList += s"\n* $description"
      }
      string = string + itemList
    }
    string + exitList
  }


  /** Returns a single-line description of the area for debugging purposes. */
  override def toString: String = this.name + ": " + this.description.replaceAll("\n", " ").take(150)



}
