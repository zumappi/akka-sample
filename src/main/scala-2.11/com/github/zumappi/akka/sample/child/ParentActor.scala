package com.github.zumappi.akka.sample.child

import scala.concurrent.duration._
import akka.actor.SupervisorStrategy.{Restart, Stop}
import akka.actor.{Actor, ActorInitializationException, ActorKilledException, ActorLogging, DeathPactException, OneForOneStrategy, Props}
/**
  * Created by zumappi on 2017/06/25.
  */
class ParentActor extends Actor with ActorLogging {
  val child = context.actorOf(Props[ChildActor], ChildActor.ACTOR_NAME)

  override def preStart() = log.info(s"preStart.")

  override val supervisorStrategy = OneForOneStrategy() {
    case _: ActorInitializationException => Stop
    case _: ActorKilledException         => Stop
    case _: DeathPactException           => Stop
    case e: Exception                    => {
      log.error(s"exception! ${e.getMessage} => restart")
      Restart
    }
  }

  override def receive = {
    case "error" => {
      log.info(s"received error.")
      throw new Exception("manual parent error!")
    }
    case "child-error" => {
      log.info(s"received child error.")
      child ! "error"
    }
    case s: String => {
      log.info(s"received string. ${s}")
      child ! s
    }
    case unknown => {
      log.info(s"received unknown message. ${unknown}")
    }
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

object ParentActor {
  val ACTOR_NAME = "parent-actor"
}
