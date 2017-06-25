package com.github.zumappi.akka.sample.args

import akka.actor.{Actor, ActorLogging}
import com.github.zumappi.akka.sample.model.Person

/**
  * Created by zumappi on 2017/06/25.
  */
class ArgsActor(val person: Person) extends Actor with ActorLogging {

  override def receive = {
    case "test"    => log.info(s"received test. args : ${person}")
    case p: Person => log.info(s"received test. args : ${person} : ${p}")
    case unknown   => log.info(s"received unknown message. args : ${person} : ${unknown}")
  }
}

object ArgsActor {
  val ACTOR_NAME = "args-actor"
}
