/*
 * Copyright 2016, 2017 TECNOLOGIA, SISTEMAS Y APLICACIONES S.L.
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

import akka.actor.{ Actor, ActorLogging, Address, Props }
import akka.cluster.ClusterEvent.{ MemberEvent, MemberJoined, MemberRemoved, MemberUp, UnreachableMember }
import akka.cluster.Cluster

object SimpleClusterListener {

  case object GetMemberNodes

  final val Name = "clusterListener"

  def props: Props = Props(new SimpleClusterListener)
}

class SimpleClusterListener extends Actor with ActorLogging {
  import SimpleClusterListener._

  val cluster = Cluster(context.system)

  private var members = Set.empty[Address]

  override def preStart(): Unit =
    cluster.subscribe(self, classOf[MemberEvent], classOf[UnreachableMember])

  override def postStop(): Unit = cluster.unsubscribe(self)

  override def receive = {
    case GetMemberNodes =>
      sender() ! members
    case MemberJoined(member) =>
      log.info("Member joined: {}", member.address)
      members += member.address
    case MemberUp(member) =>
      log.info("Member up: {}", member.address)
      members += member.address
    case MemberRemoved(member, _) =>
      log.info("Member removed: {}", member.address)
      members -= member.address
  }
}
