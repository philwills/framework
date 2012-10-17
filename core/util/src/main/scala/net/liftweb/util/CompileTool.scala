package net.liftweb
package util

import common._
import tools.nsc.{Global, CompilerCommand, Settings}
import tools.nsc.reporters.ConsoleReporter
import tools.nsc.interactive.Response
import tools.nsc.io.AbstractFile
import java.io.{InputStream, File, OutputStream}
import reflect.internal.util.SourceFile

/**
 * Created with IntelliJ IDEA.
 * User: dpp
 * Date: 10/16/12
 * Time: 1:45 PM
 *
 * A set of tools for compiling text into Scala code
 */
class CompileTool {

  private def captureError(err: String) {

  }

  def compile(in: Seq[(String, String)]): Box[Seq[Class[_]]] = {
    val settings = new Settings(captureError)
    val reporter = new ConsoleReporter(settings)
    val command = new CompilerCommand(Nil, settings)
    object compiler extends Global(command.settings, reporter) {
      // printTypings = true
    }

    // new SourceFile {}
    (new compiler.Run).compileSources(Nil)

    Empty
  }
}
