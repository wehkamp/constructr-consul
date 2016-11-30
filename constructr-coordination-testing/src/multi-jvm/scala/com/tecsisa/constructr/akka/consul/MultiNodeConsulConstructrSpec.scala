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

import akka.actor.{ Address, AddressFromURIString }
import io.circe.Json
import io.circe.parser.parse
import java.util.Base64._

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
    def jsonToNode(json: Json) = {
      val a =
        json.cursor
          .get[String]("Key")
          .fold(throw _, identity)
          .stripPrefix("constructr/MultiNodeConstructrSpec/nodes/")
      AddressFromURIString(new String(getUrlDecoder.decode(a), "UTF-8"))
    }
    import cats.syntax.either._ // for Scala 2.11
    parse(s)
      .fold(throw _, identity)
      .as[Set[Json]]
      .getOrElse(Set.empty)
      .map(jsonToNode)
  }
}

abstract class MultiNodeConsulConstructrSpec
  extends MultiNodeConstructrSpec(
    8501,
    "/v1/kv/constructr/MultiNodeConstructrSpec?recurse",
    "/v1/kv/constructr/MultiNodeConstructrSpec/nodes?recurse",
    MultiNodeConsulConstructrSpec.toNodes
  )
