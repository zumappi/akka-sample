package com.github.zumappi.akka.sample.actor.future

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout
import com.github.zumappi.akka.sample.actor.model.Person
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory

import scala.util.{Failure, Success}

/**
  * Created by zumappi on 2017/06/25.
  */
object BootFutureActor extends App {

  protected val actorSystem = ActorSystem("actor-system")

  val actor = actorSystem.actorOf(Props[FutureActor], FutureActor.ACTOR_NAME)

  val log = Logger(LoggerFactory.getLogger(this.getClass))

  implicit val timeout = Timeout(1 seconds)

  log.info("boot start.")
  val f1 = (actor ? "test").mapTo[Person]
  f1 onComplete {
    case Success(p) => log.info(s"Success!. ${p}")
    case Failure(t) => log.error(s"Failure!. ${t.getMessage}")
  }

  val f2 = (actor ? Person("tom", 25)).mapTo[Person]
  f2 onComplete {
    case Success(p) => log.info(s"Success!. ${p}")
    case Failure(t) => log.error(s"Failure!. ${t.getMessage}")
  }

  val f3 = (actor ? "sample").mapTo[Person]
  f3 onComplete {
    case Success(p) => log.info(s"Success!. ${p}")
    case Failure(t) => log.error(s"Failure!. ${t.getMessage}")
  }

  log.info("boot finish.")
  Thread.sleep(3000)

  actorSystem.stop(actor)
  Thread.sleep(1000)

  actorSystem.terminate
  System.exit(0)
}
