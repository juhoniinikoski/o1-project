package o1.otaniemiGame


/** The class `Action` represents actions that a player may take in a text adventure game.
  * `Action` objects are constructed on the basis of textual commands and are, in effect,
  * parsers for such commands. An action object is immutable after creation.
  * @param input  a textual in-game command such as "go east" or "rest" */
class Action(input: String) {

  private val commandText = input.trim.toLowerCase
  private val verb        = commandText.takeWhile( _ != ' ' )
  private val modifiers   = commandText.drop(verb.length).trim


  /** Causes the given player to take the action represented by this object, assuming
    * that the command was understood. Returns a description of what happened as a result
    * of the action (such as "You go west."). The description is returned in an `Option`
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
//    case "rest"        => Some(actor.rest())
//    case "xyzzy"       => Some("The grue tastes yummy.")
//    case "quit"        => Some(actor.quit())
//    case "inventory"   => Some(actor.inventory)
//    case "tee"         => Some(actor.doTask(this.modifiers))
//    case "drop"        => Some(actor.drop(this.modifiers))
//    case "examine"     => Some(actor.examine(this.modifiers))
    case _               => None
  }


  /** Returns a textual description of the action object, for debugging purposes. */
  override def toString: String = this.verb + " (modifiers: " + this.modifiers + ")"


}

