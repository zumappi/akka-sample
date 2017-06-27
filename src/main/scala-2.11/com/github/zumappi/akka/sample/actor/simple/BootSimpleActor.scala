package com.github.zumappi.akka.sample.actor.simple

import akka.actor.{ActorSystem, Props}
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory

/**
  * Created by zumappi on 2017/06/25.
  */
object BootSimpleActor extends App {

  protected val actorSystem = ActorSystem("actor-system")

  val actor = actorSystem.actorOf(Props[SimpleActor], SimpleActor.ACTOR_NAME)

  val log = Logger(LoggerFactory.getLogger(this.getClass))

  log.info("boot start.")
  actor ! "test"
  actor ! "sample"
  log.info("boot finish.")
  Thread.sleep(1000)

  actorSystem.stop(actor)
  Thread.sleep(1000)

  actorSystem.terminate
  System.exit(0)
}
