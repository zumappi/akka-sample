package com.github.zumappi.akka.sample.future

import akka.actor.{Actor, ActorLogging}
import com.github.zumappi.akka.sample.model.Person

/**
  * Created by zumappi on 2017/06/25.
  */
class FutureActor extends Actor with ActorLogging {

  override def receive = {
    case "test"    => {
      log.info(s"received test.")
      sender ! Person("test", 1)
    }
    case p: Person => {
      log.info(s"received test. ${p}")
      sender ! Person("same", 20)
    }
    case unknown   => {
      log.info(s"received unknown message. ${unknown}")
      Thread.sleep(3000)
      sender ! Person("error", 99)
    }
  }
}

object FutureActor {
  val ACTOR_NAME = "future-actor"
}
