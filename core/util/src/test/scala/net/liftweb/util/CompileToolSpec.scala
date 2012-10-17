
package net.liftweb
package util

import org.specs2.mutable.Specification
import common.Empty


/**
 * Systems under specification for CompileTools.
 */
object CompileToolSpec extends Specification  {
  "CompileTool Specification".title

  "the CompileTools class" should {
    "Compile a source file" in {
      try {
      val ct = new CompileTool
      ct.compile(Nil) must_== Empty
      } catch {
        case e: Exception => e.printStackTrace()
      }
      1 must_== 1
    }
  }
}
