package com.github.zumappi.akka.sample.actor.args

import akka.actor.{ActorSystem, Props}
import com.github.zumappi.akka.sample.actor.model.Person
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
  Thread.sleep(1000)

  actorSystem.stop(actor)
  Thread.sleep(1000)

  actorSystem.terminate
  System.exit(0)
}
