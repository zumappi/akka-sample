package com.github.zumappi.akka.sample.actor.lifecycle

import akka.actor.{Actor, ActorLogging}

/**
  * Created by zumappi on 2017/06/25.
  */
class LifeCycleActor extends Actor with ActorLogging {

  override def preStart() = log.info(s"preStart.")

  override def receive = {
    case "test"  => log.info(s"received test.")
    case "error" => {
      log.info(s"received error.")
      throw new Exception("manual error!")
    }
    case unknown => log.info(s"received unknown message. ${unknown}")
  }

  override def preRestart(reason: Throwable, message: Option[Any]) = {
    log.info(s"preRestart start. ${reason.getMessage}")
    for {
      msg <- message
    } yield {
      log.error(s"message : ${msg}")
    }
    super.preRestart(reason, message)
    log.info(s"preRestart end.")
  }

  override def postRestart(reason: Throwable) = {
    log.info(s"postRestart start. ${reason.getMessage}")
    super.postRestart(reason)
    log.info(s"postRestart end.")
  }

  override def postStop() = log.info(s"postStop.")
}

object LifeCycleActor {
  val ACTOR_NAME = "life-cycle-actor"
}
