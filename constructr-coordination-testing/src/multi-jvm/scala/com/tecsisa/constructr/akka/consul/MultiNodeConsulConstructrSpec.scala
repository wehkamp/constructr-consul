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

import java.util.Base64._

import akka.actor.{ Address, AddressFromURIString }

class MultiNodeConsulConstructrSpecMultiJvmNode1
  extends MultiNodeConsulConstructrSpec
class MultiNodeConsulConstructrSpecMultiJvmNode2
  extends MultiNodeConsulConstructrSpec
class MultiNodeConsulConstructrSpecMultiJvmNode3
  extends MultiNodeConsulConstructrSpec
class MultiNodeConsulConstructrSpecMultiJvmNode4
  extends MultiNodeConsulConstructrSpec
class MultiNodeConsulConstructrSpecMultiJvmNode5
  extends MultiNodeConsulConstructrSpec

object MultiNodeConsulConstructrSpec {
  def toNodes(s: String): Set[Address] = {
    import rapture.json._
    import rapture.json.jsonBackends.circe._
    def jsonToNode(json: Json) = {
      val a = json.Key
        .as[String]
        .stripPrefix("constructr/MultiNodeConstructrSpec/nodes/")
      AddressFromURIString(new String(getUrlDecoder.decode(a), "UTF-8"))
    }
    Json.parse(s).as[Set[Json]].map(jsonToNode)
  }
}

abstract class MultiNodeConsulConstructrSpec
  extends MultiNodeConstructrSpec(
    8501,
    "/v1/kv/constructr/MultiNodeConstructrSpec?recurse",
    "/v1/kv/constructr/MultiNodeConstructrSpec/nodes?recurse",
    MultiNodeConsulConstructrSpec.toNodes
  )
