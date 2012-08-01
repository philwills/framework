/*
 * Copyright 2012 WorldWide Conferencing, LLC
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

package net.liftweb
package mongodb
package record

/**
 * Systems under specification for MongoRecord.
 */
class MongoRecordFieldSpec extends MongoTestSpecification {
  import fixtures._

  "MongoRecord field introspection" should {
    val rec = MongoFieldTypeTestRecord.createRecord
    val allExpectedFieldNames: List[String] = "_id" :: "mandatoryMongoCaseClassField" ::
      (for {
        typeName <- "Date JsonObject ObjectId Pattern UUID".split(" ")
        flavor <- "mandatory legacyOptional".split(" ")
      } yield flavor + typeName + "Field").toList

    "introspect only the expected fields" in {
      rec.fields().map(_.name).filterNot(allExpectedFieldNames.contains(_)) must_== Nil
    }

    "correctly look up fields by name" in {
      for (name <- allExpectedFieldNames) {
        rec.fieldByName(name).isDefined must beTrue
      }
      success
    }

    "not look up fields by bogus names" in {
      for (name <- allExpectedFieldNames) {
        rec.fieldByName("x" + name + "y").isDefined must beFalse
      }
      success
    }
  }
}

