package com.github.zumappi.akka.sample.child

import akka.actor.{ActorSystem, Props}
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory

/**
  * Created by zumappi on 2017/06/25.
  */
object BootChildActor extends App {

  protected val actorSystem = ActorSystem("actor-system")

  val actor = actorSystem.actorOf(Props[ParentActor], ParentActor.ACTOR_NAME)

  val log = Logger(LoggerFactory.getLogger(this.getClass))

  log.info("boot start.")
  actor ! "test"
  actor ! "child-error"
  Thread.sleep(1000)
  actor ! "error"
  actor ! "sample"
  actor ! 1
  log.info("boot finish.")

  System.exit(0)
}
