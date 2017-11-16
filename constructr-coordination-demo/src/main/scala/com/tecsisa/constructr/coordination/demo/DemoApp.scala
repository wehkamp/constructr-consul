/*
 * Copyright 2016, 2017 TECNOLOGIA, SISTEMAS Y APLICACIONES S.L. <http://www.tecsisa.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tecsisa.constructr.coordination
package demo

import akka.actor.{ ActorRef, ActorSystem, Address }
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives
import akka.pattern.ask
import akka.stream.ActorMaterializer
import akka.util.Timeout
import com.typesafe.config.ConfigFactory

import scala.concurrent.duration.{ Duration, MILLISECONDS }

object DemoApp {

  val conf     = ConfigFactory.load()
  val hostname = conf.getString("demo.hostname")
  val httpPort = conf.getInt("demo.port")

  def main(args: Array[String]): Unit = {
    // Create an Akka system
    implicit val system = ActorSystem("ConstructR-Consul")
    import system.dispatcher
    implicit val mat = ActorMaterializer()

    // Create an actor that handles cluster domain events
    val cluster =
      system.actorOf(SimpleClusterListener.props, SimpleClusterListener.Name)
    Http().bindAndHandle(route(cluster), hostname, httpPort)
  }

  private def route(cluster: ActorRef) = {
    import Directives._
    implicit val timeout = Timeout(
      Duration(
        conf.getDuration("demo.cluster-view-timeout").toMillis,
        MILLISECONDS
      )
    )
    path("member-nodes") { // List cluster nodes
      get {
        onSuccess(
          (cluster ? SimpleClusterListener.GetMemberNodes).mapTo[Set[Address]]
        )(addresses => complete(addresses.mkString("\n")))
      }
    }
  }

}
