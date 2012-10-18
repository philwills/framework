
package net.liftweb
package util

import org.specs2.mutable.Specification
import common._


/**
 * Systems under specification for CompileTools.
 */
object CompileToolSpec extends Specification  {
  "CompileTool Specification".title

  def example = """
     class Mook {
         def moose = 1 + 1
     }

     class Foo {
         def bar() = "Hello World" // you snarky weasle
     }
                """

  def badExample =
    """
      |class DogMeat {
      |  def moose 99 frog = "hello"
      |}
    """.stripMargin

  def futureExample =
    """
      |
      |
      |import java.util.concurrent._
      |
      |case class Meow() extends Future[String] {
      |  def cancel(mayInterruptIfRunning: Boolean): Boolean = true

      |  def get(timeout: Long, unit: TimeUnit): String = get()
      |  def isCancelled() = false
      |  def isDone() = true
      |  def get() = "44"
      |}
    """.stripMargin

  def funcExample =
    """
      |case class Meow() extends Function1[Int, String] {
      |  def apply(in: Int) = in.toString
      |}
    """.stripMargin

  "the CompileTools class" should {
    "Compile a source file" in {
      val ct = new CompileTool
      ct.compile(List("Frog.scala" -> example)).map(_.size) must_== Full(2)
    }

    "Fail to compile a bad source" in {
      val ct = new CompileTool
      ct.compile(List("Frog.scala" -> badExample)).isEmpty must_== true
    }

    "Load a class" in {
      val ct = new CompileTool
      this.getClass.getClassLoader.loadClass("scala.Product$class")
      val cl = ct.classloaderFor(List("Frog.scala" -> futureExample)).openOrThrowException("The code should compile")
      val inst = cl.loadClass("Meow").newInstance().asInstanceOf[java.util.concurrent.Future[String]]
      inst.get() must_== "44"
    }

    "Load a function class" in {
      val ct = new CompileTool
      this.getClass.getClassLoader.loadClass("scala.Product$class")
      val cl = ct.classloaderFor(List("Frog.scala" -> funcExample)).openOrThrowException("The code should compile")
      val inst = cl.loadClass("Meow").newInstance().asInstanceOf[Function1[Int, String]]
      inst(55) must_== "55"
    }
  }
}
