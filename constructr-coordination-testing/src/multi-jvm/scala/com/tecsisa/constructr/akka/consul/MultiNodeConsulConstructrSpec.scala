/*
 * Copyright 2016 TECNOLOGIA, SISTEMAS Y APLICACIONES S.L.
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

package com.tecsisa.constructr.akka.consul

import akka.actor.Address
import de.heikoseeberger.constructr.akka.AkkaAddressNodeSerialization
import de.heikoseeberger.constructr.coordination.Coordination
import java.util.Base64

class MultiNodeConsulConstructrSpecMultiJvmNode1 extends MultiNodeConsulConstructrSpec
class MultiNodeConsulConstructrSpecMultiJvmNode2 extends MultiNodeConsulConstructrSpec
class MultiNodeConsulConstructrSpecMultiJvmNode3 extends MultiNodeConsulConstructrSpec
class MultiNodeConsulConstructrSpecMultiJvmNode4 extends MultiNodeConsulConstructrSpec
class MultiNodeConsulConstructrSpecMultiJvmNode5 extends MultiNodeConsulConstructrSpec

object MultiNodeConsulConstructrSpec {
  def toNodes(s: String): Set[Address] = {
    import rapture.json._
    import rapture.json.jsonBackends.spray._
    def jsonToNode(json: Json) = {
      implicitly[Coordination.NodeSerialization[Address]]
        .fromBytes(Base64.getUrlDecoder.decode(json.Key.as[String].stripPrefix("constructr/akka/MultiNodeConstructrSpec/nodes/")))
    }
    Json.parse(s).as[Set[Json]].map(jsonToNode)
  }
}

abstract class MultiNodeConsulConstructrSpec extends MultiNodeConstructrSpec(
  8501,
  "/v1/kv/constructr/akka?recurse",
  "/v1/kv/constructr/akka/MultiNodeConstructrSpec/nodes?recurse",
  MultiNodeConsulConstructrSpec.toNodes
)
