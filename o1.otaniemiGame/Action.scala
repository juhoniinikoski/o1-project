package o1.otaniemiGame


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
    case _               => None
  }


  /** Returns a textual description of the action object, for debugging purposes. */
  override def toString: String = this.verb + " (modifiers: " + this.modifiers + ")"


}

