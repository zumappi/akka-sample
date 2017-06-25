package com.github.zumappi.akka.sample.args

import akka.actor.{ActorSystem, Props}
import com.github.zumappi.akka.sample.model.Person
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory

/**
  * Created by zumappi on 2017/06/25.
  */
object BootArgsActor extends App {

  protected val actorSystem = ActorSystem("actor-system")

  val person = Person("same", 20)
  val actor = actorSystem.actorOf(Props(classOf[ArgsActor], person), ArgsActor.ACTOR_NAME)

  val log = Logger(LoggerFactory.getLogger(this.getClass))

  log.info("boot start.")
  actor ! "test"
  actor ! Person("tom", 25)
  actor ! "sample"
  log.info("boot finish.")

  System.exit(0)
}
