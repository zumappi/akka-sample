package com.github.zumappi.akka.sample.simple

import akka.actor.{Actor, ActorLogging}

/**
  * Created by zumappi on 2017/06/25.
  */
class SimpleActor extends Actor with ActorLogging {

  override def receive = {
    case "test"  => log.info(s"received test.")
    case unknown => log.info(s"received unknown message. ${unknown}")
  }
}

object SimpleActor {
  val ACTOR_NAME = "simple-actor"
}
